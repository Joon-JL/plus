package com.sec.clm.tnc.control;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import anyframe.core.query.QueryServiceException;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.FileUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.dto.InfContractInfoVO;
import com.sec.clm.tnc.dto.InfFileAttachVO;
import com.sec.clm.tnc.service.LegacyInterfaceService;



public class LegacyInterfaceFileJobControl extends CommonController {
		
	private LegacyInterfaceService legacyInterfaceService;
	public void setLegacyInterfaceService(LegacyInterfaceService legacyInterfaceService) {
		this.legacyInterfaceService = legacyInterfaceService;
	}

	
	private PropertyService propertyService;	
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	
	private ComUtilService comUtilService;	
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	
	
	public synchronized void makeInfMetaFile() throws Exception {		
		
		if (comUtilService.isCronServer()) {
//		if(true) {	
//			getLogger().info("##################################################################################");
//			getLogger().info("=============== Start 메타정보파일 쓰기 (DB TO FILE)===============");

			//송신데이터파일 생성 
//			writeFileInfCopySendInfoECMS();//임시 테이블 저장후 바로 생성되게 변경됨
			
			
			//수신 ECMS
			loadFileInfContCnsdreqECMS();
			loadFileInfFileAttachECMS();

			

		}
	}


	public synchronized boolean getStatusBatch() throws Exception {
		
		if(comUtilService.isCronServer()) { // 운영 서버에서만 데몬 실행
			String ins1pidFile = "/las/logs/las1/las1.pid"; //las2에서 돌아감
//        	getLogger().info("pidFile: "+ins1pidFile);
        	
			File ins1File = new File(ins1pidFile);
		    
		    if(ins1File.exists()) {
		    	FileInputStream fis = new FileInputStream(ins1File);
		    	BufferedInputStream bis = new BufferedInputStream(fis);
		    	DataInputStream dis = new DataInputStream(bis);
		     
		    	String ins1Pid = "";
	
		    	if(dis.available() != 0) {
		    		ins1Pid = dis.readLine();
		    	}
		    	
		    	RuntimeMXBean rmb = ManagementFactory.getRuntimeMXBean();
		        String processId = rmb.getName();
		        
		        if(processId.length() > ins1Pid.length()) {
		         processId = processId.substring(0, ins1Pid.length());
		        }
		        
		        // 첫번째 인스턴스에서만 실행
		        if(processId.equals(ins1Pid)) {
		        	return true;
		        }else{
		        	return false;
		        }
		        
		    }else{
	        	return false;
	        }
		}else{
        	return false;
        }
	}

	
	public int writeFileInfCopySendInfoECMS() throws Exception{

		int exeSize = 0;
		String serverDir = propertyService.getProperty("secfw.legacyinf.tnc.send.data.dir");
		
		if (!new File(serverDir).exists()) {
			new File(serverDir).mkdirs();
		}
	
		InfContractInfoVO infContractInfoVO = new InfContractInfoVO();
		
		infContractInfoVO.setSys_nm("TNC");
		
		List copySendInfoForList = legacyInterfaceService.loadInfCopySendinfo(infContractInfoVO);		
		
//		getLogger().info("TN_INF_COPY_SENDINFO의  'SEND' 대상 수:"+copySendInfoForList.size());	
		
		exeSize = legacyInterfaceService.writeFileForCopySendInfo(copySendInfoForList,serverDir);	
		
//		getLogger().info("파일에 작성된 'BPOS' 송신 데이터 수:"+exeSize);	

		return exeSize;
		
	}	
	
	
	
	private void loadFileInfContCnsdreqECMS() throws Exception{
		
		String fileEncType = propertyService.getProperty("secfw.legacyinf.fileEncType");		

		FileInputStream fis = null;
		InputStreamReader isr = null;  
		BufferedReader inFile = null;
		FileOutputStream fos = null;	
		OutputStreamWriter isw = null;
		BufferedWriter outFile = null;
		
		
		final String BIZ_CLSFCN           	="T0103";
		final String DEPTH_CLSFCN         	="T0201";
		final String CNCLSNPURPS_BIGCLSFCN	="T0307";
		final String CNCLSNPURPS_MIDCLSFCN	="T030705";
		final String BFHDCSTN_APBT_MTHD   	="C02801";
		final String VENDOR_TYPE		  	="C";	
		final String ECMS_RESULT_FLAG		="N";

				
		
		try {
			
			String serverDir = propertyService.getProperty("secfw.legacyinf.tnc.receive.data.dir");
			String serverTempDir = propertyService.getProperty("secfw.legacyinf.tnc.receive.temp.dir");
			String serverFailDir = propertyService.getProperty("secfw.legacyinf.tnc.receive.fail.dir");
			
			/**********************************************************************************
			 										파일 찾기
			 **********************************************************************************/
			String exeMessage = "";
			int failCount = 0;
			
			File files = new File(serverDir);
			
			File[] filteredfiles = files.listFiles();
			
			ArrayList<String> fileList = new ArrayList<String>();
			
			if(filteredfiles!=null&&filteredfiles.length>0){
				
				for(int i=0;i<filteredfiles.length;i++){
					
					LegacyInfFilenameFilter legacyInfFilenameFilter= new LegacyInfFilenameFilter(filteredfiles[i].getName());
					
					if(legacyInfFilenameFilter.accept(files,"CONTRACT_")){
						fileList.add(filteredfiles[i].getName());
					}
				}
//				getLogger().info("가장 최근에 생성된 TNC_TO_ECSM_FILE_IF_**************.txt 파일:");
				
			}else{
//				getLogger().info("TNC_TO_ECSM_FILE_IF_**************.txt 파일: 존재하지 않음");
			}
			
			/**********************************************************************************
			 * 					파일 읽어서 DB로 밀어넣기
			 **********************************************************************************/
			
			for(int i=0; i<fileList.size(); i++){
				
				File file = new File(serverDir+File.separator+fileList.get(i));
				File tempFile = new File(serverTempDir+File.separator+fileList.get(i));//모든작업이 끝난 후에는 TEMP디렉토리로 MOVE시킴
				File failFile = new File(serverFailDir+File.separator+fileList.get(i).substring(0, fileList.get(i).lastIndexOf("."))+"_fail.txt");//벨리데이션실패나 DB인서트시 오류난 경우 로우데이터를 이곳에 쌓는다
				
				InfContCnsdreqVO infContCnsdreqVO = new InfContCnsdreqVO();
				
				//기본 상수값 세팅
				infContCnsdreqVO.setBiz_clsfcn(BIZ_CLSFCN);
				infContCnsdreqVO.setDepth_clsfcn(DEPTH_CLSFCN);
				infContCnsdreqVO.setCnclsnpurps_bigclsfcn(CNCLSNPURPS_BIGCLSFCN);
				infContCnsdreqVO.setCnclsnpurps_midclsfcn(CNCLSNPURPS_MIDCLSFCN);
				infContCnsdreqVO.setBfhdcstn_apbt_mthd(BFHDCSTN_APBT_MTHD);
				infContCnsdreqVO.setVendor_type(VENDOR_TYPE);
				infContCnsdreqVO.setEcms_result_flag(ECMS_RESULT_FLAG);
				
				
				
				if(file.exists()){
	
					//파일읽기
					fis = new FileInputStream(file);
					isr = new InputStreamReader(fis,fileEncType);  
					inFile = new BufferedReader(isr);
					//파일쓰기(에러발생시)
					fos = new FileOutputStream(failFile);
					isw = new OutputStreamWriter(fos,fileEncType);
					outFile = new BufferedWriter(isw);				
	
					String sLine = null;
					String [] tempText = null;
					
					while( (sLine = inFile.readLine()) != null ){
						
						
						tempText = sLine.split("\\*\\%\\^\\&\\|");
						
						if(tempText.length == 35){
							
//							if("T030705002".equals(tempText[5]) || "T030705003".equals(tempText[5]) || "T030705006".equals(tempText[5]) || "T030706002".equals(tempText[5]) || "T030706003".equals(tempText[5]) || "T030706006".equals(tempText[5]))
							
							infContCnsdreqVO.setTcomp_cd(tempText[0]);
							infContCnsdreqVO.setTcomp_nm(tempText[1]);
							infContCnsdreqVO.setKey_id(tempText[2]);
							infContCnsdreqVO.setKey_nm(tempText[3]);							
							infContCnsdreqVO.setReq_title(tempText[4]);
							infContCnsdreqVO.setBfhdcstn_mtnman_id(tempText[5]);
							infContCnsdreqVO.setBfhdcstn_mtnman_nm(tempText[6]);
							infContCnsdreqVO.setBfhdcstn_mtnman_jikgup_nm(tempText[7]);
							infContCnsdreqVO.setBfhdcstn_mtn_dept_nm(tempText[8]);
							infContCnsdreqVO.setBfhdcstn_apbtman_id(tempText[9]);
							infContCnsdreqVO.setBfhdcstn_apbtman_nm(tempText[10]);
							infContCnsdreqVO.setBfhdcstn_apbtman_jikgup_nm(tempText[11]);
							infContCnsdreqVO.setBfhdcstn_apbt_dept_nm(tempText[12]);
							infContCnsdreqVO.setBfhdcstn_apbtday(tempText[13]);
							infContCnsdreqVO.setReqman_id(tempText[14]);
							infContCnsdreqVO.setReqman_nm(tempText[15]);
							infContCnsdreqVO.setReqman_jikgup_nm(tempText[16]);
							infContCnsdreqVO.setReq_dept_nm(tempText[17]);
							infContCnsdreqVO.setReq_dt(tempText[18]);
							infContCnsdreqVO.setCntrt_oppnt_cd(tempText[19]);
							infContCnsdreqVO.setCntrt_oppnt_nm(tempText[20]);
							infContCnsdreqVO.setPostalcode(tempText[21]);
							infContCnsdreqVO.setCityn(tempText[22]);
							infContCnsdreqVO.setNation(tempText[23]);
							infContCnsdreqVO.setStapr(tempText[24]);
							infContCnsdreqVO.setTnc_app_link(tempText[25]);
							infContCnsdreqVO.setCtc_link(tempText[26]);
							infContCnsdreqVO.setOtc_link(tempText[27]);
							infContCnsdreqVO.setTnc_summary_link(tempText[28]);
							infContCnsdreqVO.setSys_nm(tempText[29]);
							infContCnsdreqVO.setIf_dt(tempText[30]);
							infContCnsdreqVO.setCntrt_trgt(tempText[31]);
							infContCnsdreqVO.setTnc_source(tempText[32]);
							infContCnsdreqVO.setCntrtperiod_startday(tempText[33]);
							infContCnsdreqVO.setCntrtperiod_endday(tempText[34]);
							
							//회신 요청일 : 의뢰일 + 5일
							DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
							
							Date date = dateFormat.parse(tempText[18]);
							
							Calendar cal = Calendar.getInstance();
							
							cal.setTime(date);
							cal.add(Calendar.DATE, 5);
							
							String strDate = dateFormat.format(cal.getTime());
							
							infContCnsdreqVO.setRe_demndday(strDate);
							
							try{
//								outFile.newLine();	
								
//								outFile.write(new String (sLine.getBytes(fileEncType),fileEncType));	 

								outFile.write(sLine);	 
								outFile.newLine();	
								
						        int res = legacyInterfaceService.insertInfContCnsdreq(infContCnsdreqVO);
						        
						        if(res>0){
									exeMessage = "인터페이스 메타정보파일 등록(File to DB)을 성공했습니다";
//									getLogger().info("인터페이스 메타정보파일 등록(File to DB) 성공:"+exeMessage);
						        }else{
						        	//파일쓰기
						        	outFile.write(sLine);	
									outFile.newLine();	
									failCount++;					
	
									exeMessage = "테이블에 insert 실패";
									getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+exeMessage);	
						        }
				        	}catch(QueryServiceException e){
								getLogger().info("중복키로인한 오류 가능성있음...");	
					        	//파일쓰기
								outFile.write(sLine);	
								outFile.newLine();	
								failCount++;					

								exeMessage = "테이블에 insert 실패";
								getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+exeMessage);	
				        	}catch(Exception e){
				        		getLogger().info("인터페이스 메타정보파일 등록시 에러 발생:"+e.getMessage());
				        	}finally{
				        		
				        	}
								
						}else{
							outFile.write(sLine);	 
							outFile.newLine();
							failCount++;	
							
							getLogger().info("인터페이스 메타정보파일 로딩(File to DB) 실패(토탈사이즈 오류):("+tempText.length+"/35)");
									
						}
				    }
					
					outFile.close();
					inFile.close();
				}				
				

//				getLogger().info("temp로 이동 시작: "+file.getName());	
				FileUtil.moveFile(file, tempFile);//temp로 이동
//				getLogger().info("temp로 이동 완료: "+file.getName());	
				
				if(failCount==0){
//					getLogger().info("에러가 없을시 에러파일삭제: "+failFile.getName());	
					failFile.delete();//에러가 없을시 에러파일삭제
				}
				
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+e.getMessage());					
		} finally{
            try {if (fis != null)fis.close();} catch (Exception e) {}	            
            try {if (fos != null)fos.close();} catch (Exception e) {}
            try {if (inFile != null)inFile.close();} catch (Exception e) {}	            
            try {if (outFile != null)outFile.close();} catch (Exception e) {}	
            try {if (isr != null)isr.close();} catch (Exception e) {}
            try {if (isw != null)isw.close();} catch (Exception e) {}
		}
	}
	
	
	private void loadFileInfFileAttachECMS() throws Exception{
		
		String fileEncType = propertyService.getProperty("secfw.legacyinf.fileEncType");		
		
		FileInputStream fis = null;
		InputStreamReader isr = null;  
		BufferedReader inFile = null;
		FileOutputStream fos = null;	
		OutputStreamWriter isw = null;
		BufferedWriter outFile = null;
		
		final String SYS_CD          	="LAS";
		final String FILE_BIGCLSFCN     ="F012";
		final String FILE_MIDCLSFCN		="F01202";
//		final String FILE_SMLCLSFCN		="F0120208";
		final String DEL_YN   			="N";
		final String ECMS_RESULT_FLAG	="N";
		
		try {
			
			String serverDir = propertyService.getProperty("secfw.legacyinf.tnc.receive.attach.dir");
			String serverTempDir = propertyService.getProperty("secfw.legacyinf.tnc.receive.temp.dir");			
			String serverFailDir = propertyService.getProperty("secfw.legacyinf.tnc.receive.fail.dir");
			
			/**********************************************************************************
			 * 가장 최근에 생성된 TN_INF_FILE_ATTACH_**************_CMS.txt 파일 찾기
			 **********************************************************************************/
			String exeMessage = "";
			int failCount = 0;
			
			File files = new File(serverDir);
			File[] filteredfiles = files.listFiles();
			
			ArrayList<String> fileList = new ArrayList<String>();
			
			if(filteredfiles!=null&&filteredfiles.length>0){
				
				for(int i=0;i<filteredfiles.length;i++){
					
					LegacyInfFilenameFilter legacyInfFilenameFilter= new LegacyInfFilenameFilter(filteredfiles[i].getName());
					
					if(legacyInfFilenameFilter.accept(files,"FILE_")){
						fileList.add(filteredfiles[i].getName());
					}
				}
//				getLogger().info("가장 최근에 생성된 TNC_TO_ECSM_FILE_IF_**************.txt 파일:");
				
			}else{
//				getLogger().info("TNC_TO_ECSM_FILE_IF_**************.txt 파일: 존재하지 않음");
			}
	
			/**********************************************************************************
			 * 가장 최근에 생성된 TN_INF_FILE_ATTACH_**************_CMS.txt 파일 읽어서 DB로 밀어넣기
			 **********************************************************************************/
			
			for(int i=0; i<fileList.size(); i++){
				
				File file = new File(serverDir+File.separator+fileList.get(i));
				File tempFile = new File(serverTempDir+File.separator+fileList.get(i));//모든작업이 끝난 후에는 TEMP디렉토리로 MOVE시킴
				File failFile = new File(serverFailDir+File.separator+fileList.get(i).substring(0, fileList.get(i).lastIndexOf("."))+"_fail.txt");//벨리데이션실패나 DB인서트시 오류난 경우 로우데이터를 이곳에 쌓는다
				

				InfFileAttachVO infFileAttachVO = new InfFileAttachVO();	
				
				//기본상수값 세팅
				infFileAttachVO.setSys_cd(SYS_CD);
				infFileAttachVO.setFile_bigclsfcn(FILE_BIGCLSFCN);
				infFileAttachVO.setFile_midclsfcn(FILE_MIDCLSFCN);
//				infFileAttachVO.setFile_smlclsfcn(FILE_SMLCLSFCN);
				infFileAttachVO.setDel_yn(DEL_YN);
				infFileAttachVO.setEcms_result_flag(ECMS_RESULT_FLAG);
				
				if(file.exists()){
	
					//파일읽기
					fis = new FileInputStream(file);
					isr = new InputStreamReader(fis,fileEncType);  
					inFile = new BufferedReader(isr);
					//파일쓰기(에러발생시)
					fos = new FileOutputStream(failFile);
					isw = new OutputStreamWriter(fos,fileEncType);
					outFile = new BufferedWriter(isw);				
	
					String sLine = null;
					
					while( (sLine = inFile.readLine()) != null ){
						
						String [] tempText = sLine.split("\\*\\%\\^\\&\\|");
						
						if(tempText.length == 11){
							
							// 파일 실제 경로 구하기							
							String realPath = serverDir+File.separator+tempText[8];
							File rfile = new File(realPath);
							
							// 파일 확장자 구하기
							int pos = tempText[7].lastIndexOf( "." );
							String ext = tempText[7].substring( pos + 1 );
							
							
							infFileAttachVO.setTcomp_cd(tempText[0]);
							infFileAttachVO.setTcomp_nm(tempText[1]);
							infFileAttachVO.setKey_id(tempText[2]);
							infFileAttachVO.setKey_nm(tempText[3]);
							infFileAttachVO.setFile_id(tempText[4]);
							infFileAttachVO.setFile_srt(tempText[5]);
							infFileAttachVO.setFile_sz(String.valueOf(rfile.length()));
							infFileAttachVO.setFile_smlclsfcn(tempText[6]);
							infFileAttachVO.setOrg_file_nm(tempText[7]);
							infFileAttachVO.setFile_path(realPath);
							infFileAttachVO.setFile_info(ext);
							infFileAttachVO.setReg_id(tempText[9]);
//							infFileAttachVO.setReg_dt(tempText[10]);
							infFileAttachVO.setSys_nm(tempText[9]);
							infFileAttachVO.setIf_dt(tempText[10]);
							
//							System.out.println(tempText[0]);
//							System.out.println(tempText[1]);
					        
					        //TODO 풀것...
							try{
						        int res = legacyInterfaceService.insertInfFileAttach(infFileAttachVO);
						        if(res>0){
//									exeMessage = "인터페이스 메타정보파일 등록(File to DB)을 성공했습니다";
//									getLogger().info("인터페이스 메타정보파일 등록(File to DB) 성공:"+exeMessage);						        	
						        }else{
						        	//파일쓰기
									outFile.write(sLine);	 
									outFile.newLine();
									failCount++;			
									
									exeMessage = "테이블에 insert 실패";
									getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+exeMessage);			        	
						        }
							}catch(QueryServiceException e){
					        	//파일쓰기
								outFile.write(sLine);	 
								outFile.newLine();
								failCount++;			
								
								exeMessage = "테이블에 insert 실패";
								getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+exeMessage);			 								
				        	}catch(Exception e){
				        		getLogger().info("인터페이스 메타정보파일 등록시 에러 발생:"+e.getMessage());
				        	}finally{
				        		
				        	}
	
						}else{
							outFile.write(sLine);	 
							outFile.newLine();
							failCount++;	
							
							getLogger().info("인터페이스 메타정보파일 로딩(File to DB) 실패(토탈사이즈 오류):("+tempText.length+"/11)");
						}	
				    }
					
					outFile.close();
					inFile.close();
					
				}				
	
//				getLogger().info("temp로 이동 시작: "+file.getName());	
				FileUtil.moveFile(file, tempFile);//temp로 이동
//				getLogger().info("temp로 이동 완료: "+file.getName());	
				
				if(failCount==0){
//					getLogger().info("에러가 없을시 에러파일삭제: "+failFile.getName());	
					failFile.delete();//에러가 없을시 에러파일삭제
				}
				
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+e.getMessage());					
		} finally{
	        try {if (fis != null)fis.close();} catch (Exception e) {}	            
	        try {if (fos != null)fos.close();} catch (Exception e) {}
	        try {if (inFile != null)inFile.close();} catch (Exception e) {}	            
	        try {if (outFile != null)outFile.close();} catch (Exception e) {}	
	        try {if (isr != null)isr.close();} catch (Exception e) {}
	        try {if (isw != null)isw.close();} catch (Exception e) {}
		}
	}

}
