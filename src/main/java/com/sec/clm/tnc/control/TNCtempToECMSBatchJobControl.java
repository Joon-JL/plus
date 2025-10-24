package com.sec.clm.tnc.control;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.service.CustomerNewService;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.RegistrationService;
import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.dto.InfContractInfoVO;
import com.sec.clm.tnc.dto.InfFileAttachVO;
import com.sec.clm.tnc.service.LegacyInterfaceService;
import com.sec.clm.tnc.service.TNCtempToECMSBatchJobService;
import com.sec.common.clmsfile.service.ClmsFileService;



public class TNCtempToECMSBatchJobControl extends CommonController {
		
	/**
	 *  userManagerService 선언 및 세팅
	 * 
	 */
	private TNCtempToECMSBatchJobService tNCtempToECMSBatchJobService;
	
	public void setTNCtempToECMSBatchJobService(TNCtempToECMSBatchJobService tNCtempToECMSBatchJobService) {
		this.tNCtempToECMSBatchJobService = tNCtempToECMSBatchJobService;
	}
	
	private ConsultationService consultationService;	
	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	/**
	 * 
	 */
	private ConsiderationService considerationService;
	/**
	 * 
	 * @param considerationService
	 */
	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
	}
	
	private RegistrationService registrationService;
	/**
	 * 
	 * @param considerationService
	 */
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	private CustomerNewService customerNewService;
	
	public void setCustomerNewService(CustomerNewService customerNewService) {
		this.customerNewService = customerNewService;
	}
	
	/**
	 * 첨부파일 
	 */
	private ClmsFileService clmsFileService;
	public void setClmsFileService(ClmsFileService clmsFileService) {
		this.clmsFileService = clmsFileService;
	}
	
	private PropertyService propertyService;	
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	private ComUtilService comUtilService;	
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	protected CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	private LegacyInterfaceService legacyInterfaceService;
	public void setLegacyInterfaceService(LegacyInterfaceService legacyInterfaceService) {
		this.legacyInterfaceService = legacyInterfaceService;
	}
	
	
	
	public synchronized void makeECMSBatch() throws Exception {	
		
		if (comUtilService.isCronServer()) {

			insertToECMSContractBatch();
			
		}
	}
	
	
	public synchronized void migTNCBatch() throws Exception {	
		
		if (comUtilService.isCronServer()) {

			insertMigTNCContractBatch();
			
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

	
@SuppressWarnings("unchecked")
private void insertToECMSContractBatch() throws Exception{
		
		
//		final String SYS_CD          	="LAS";
//		final String FILE_BIGCLSFCN     ="F012";
//		final String FILE_MIDCLSFCN		="F01202";
//		final String FILE_SMLCLSFCN		="F0120208";
//		final String DEL_YN   			="N";
		
		try {
			
			
			/**********************************************************************************
			 * 가장 최근에 생성된 TN_INF_FILE_ATTACH_**************_CMS.txt 파일 찾기
			 **********************************************************************************/
			
			List tncTempList = null;
			int InstSucc = 0;
			int InstFail = 0;
			
			tncTempList   = tNCtempToECMSBatchJobService.listTempECMSContractBatch();
			
			if (tncTempList != null && tncTempList.size() > 0) {	
				for (int i = 0; i < tncTempList.size(); i++) {
					
					InfContCnsdreqVO infContCnsdreqVO = new InfContCnsdreqVO();
					ListOrderedMap lom = (ListOrderedMap) tncTempList.get(i);
					
					infContCnsdreqVO.setTcomp_cd((String)lom.get("tcomp_cd"));
					infContCnsdreqVO.setTcomp_nm((String)lom.get("tcomp_nm"));
					infContCnsdreqVO.setKey_id((String)lom.get("key_id"));
					infContCnsdreqVO.setKey_nm((String)lom.get("key_nm"));
					infContCnsdreqVO.setReq_title((String)lom.get("req_title"));
					infContCnsdreqVO.setBiz_clsfcn((String)lom.get("biz_clsfcn"));
					infContCnsdreqVO.setDepth_clsfcn((String)lom.get("depth_clsfcn"));
					infContCnsdreqVO.setCnclsnpurps_bigclsfcn((String)lom.get("cnclsnpurps_bigclsfcn"));
					infContCnsdreqVO.setCnclsnpurps_midclsfcn((String)lom.get("cnclsnpurps_midclsfcn"));
//					infContCnsdreqVO.setPayment_gbn((String)lom.get("payment_gbn"));
					infContCnsdreqVO.setBfhdcstn_mtnman_id((String)lom.get("bfhdcstn_mtnman_id"));
					infContCnsdreqVO.setBfhdcstn_mtnman_nm((String)lom.get("bfhdcstn_mtnman_nm"));
					infContCnsdreqVO.setBfhdcstn_mtnman_jikgup_nm((String)lom.get("bfhdcstn_mtnman_jikgup_nm"));
					infContCnsdreqVO.setBfhdcstn_mtn_dept_nm((String)lom.get("bfhdcstn_mtn_dept_nm"));
					infContCnsdreqVO.setBfhdcstn_apbtman_id((String)lom.get("bfhdcstn_apbtman_id"));
					infContCnsdreqVO.setBfhdcstn_apbtman_nm((String)lom.get("bfhdcstn_apbtman_nm"));
					infContCnsdreqVO.setBfhdcstn_apbtman_jikgup_nm((String)lom.get("bfhdcstn_apbtman_jikgup_nm"));
					infContCnsdreqVO.setBfhdcstn_apbt_dept_nm((String)lom.get("bfhdcstn_apbt_dept_nm"));
					infContCnsdreqVO.setBfhdcstn_apbtday((String)lom.get("bfhdcstn_apbtday"));
					infContCnsdreqVO.setBfhdcstn_apbt_mthd((String)lom.get("bfhdcstn_apbt_mthd"));
					infContCnsdreqVO.setRe_demndday((String)lom.get("re_demndday"));
					infContCnsdreqVO.setReqman_id((String)lom.get("reqman_id"));
					infContCnsdreqVO.setReqman_nm((String)lom.get("reqman_nm"));
					infContCnsdreqVO.setReq_dept((String)lom.get("req_dept"));
					infContCnsdreqVO.setReq_dept_nm((String)lom.get("req_dept_nm"));
					infContCnsdreqVO.setReq_dt((String)lom.get("req_dt"));
					infContCnsdreqVO.setCntrt_oppnt_cd((String)lom.get("cntrt_oppnt_cd"));
					infContCnsdreqVO.setCntrt_oppnt_nm((String)lom.get("cntrt_oppnt_nm"));
					infContCnsdreqVO.setPostalcode((String)lom.get("postalcode"));
					infContCnsdreqVO.setCityn((String)lom.get("cityn"));
					infContCnsdreqVO.setNation((String)lom.get("nation"));
					infContCnsdreqVO.setStapr((String)lom.get("stapr"));
					infContCnsdreqVO.setVendor_type((String)lom.get("vendor_type"));
					infContCnsdreqVO.setTnc_app_link((String)lom.get("tnc_app_link"));
					infContCnsdreqVO.setCtc_link((String)lom.get("ctc_link"));
					infContCnsdreqVO.setOtc_link((String)lom.get("otc_link"));
					infContCnsdreqVO.setTnc_summary_link((String)lom.get("tnc_summary_link"));
					infContCnsdreqVO.setSys_nm((String)lom.get("sys_nm"));
					infContCnsdreqVO.setIf_dt((String)lom.get("if_dt"));
					infContCnsdreqVO.setEcms_dt((String)lom.get("ecms_dt"));
					infContCnsdreqVO.setEcms_result_flag((String)lom.get("ecms_result_flag"));
					infContCnsdreqVO.setEcms_result((String)lom.get("ecms_result"));
					infContCnsdreqVO.setCntrt_trgt((String)lom.get("cntrt_trgt"));
					infContCnsdreqVO.setTnc_source((String)lom.get("tnc_source"));
					infContCnsdreqVO.setCntrtperiod_startday((String)lom.get("cntrtperiod_startday"));
					infContCnsdreqVO.setCntrtperiod_endday((String)lom.get("cntrtperiod_endday"));
					
					
					ListOrderedMap returnLom = new ListOrderedMap();
					ConsultationVO vo = new ConsultationVO();
					
					
					
//					vo.setComp_cd((String)lom.get("tcomp_cd"));
//					vo.setComp_nm((String)lom.get("tcomp_nm"));
//					vo.setKey_id((String)lom.get("key_id"));
//					vo.setKey_nm((String)lom.get("key_nm"));
					vo.setReq_title((String)lom.get("req_title"));
					vo.setBiz_clsfcn((String)lom.get("biz_clsfcn"));
					vo.setDepth_clsfcn((String)lom.get("depth_clsfcn"));
					vo.setCnclsnpurps_bigclsfcn((String)lom.get("cnclsnpurps_bigclsfcn"));
					vo.setCnclsnpurps_midclsfcn((String)lom.get("cnclsnpurps_midclsfcn"));
//					vo.setPayment_gbn((String)lom.get("payment_gbn"));
					vo.setBfhdcstn_mtnman_id((String)lom.get("bfhdcstn_mtnman_id"));
					vo.setBfhdcstn_mtnman_nm((String)lom.get("bfhdcstn_mtnman_nm"));
					vo.setBfhdcstn_mtnman_jikgup_nm((String)lom.get("bfhdcstn_mtnman_jikgup_nm"));
					vo.setBfhdcstn_mtn_dept_nm((String)lom.get("bfhdcstn_mtn_dept_nm"));
					vo.setBfhdcstn_apbtman_id((String)lom.get("bfhdcstn_apbtman_id"));
					vo.setBfhdcstn_apbtman_nm((String)lom.get("bfhdcstn_apbtman_nm"));
					vo.setBfhdcstn_apbtman_jikgup_nm((String)lom.get("bfhdcstn_apbtman_jikgup_nm"));
					vo.setBfhdcstn_apbt_dept_nm((String)lom.get("bfhdcstn_apbt_dept_nm"));
					vo.setBfhdcstn_apbtday((String)lom.get("bfhdcstn_apbtday"));
					vo.setBfhdcstn_apbt_mthd((String)lom.get("bfhdcstn_apbt_mthd"));
					vo.setRe_demndday((String)lom.get("re_demndday"));
					vo.setReqman_id((String)lom.get("reqman_id"));
					vo.setReqman_nm((String)lom.get("reqman_nm"));
					vo.setReq_dept((String)lom.get("req_dept"));
					vo.setReq_dept_nm((String)lom.get("req_dept_nm"));
					vo.setReq_dt((String)lom.get("req_dt"));					
//					vo.setCntrt_oppnt_cd((String)lom.get("cntrt_oppnt_cd"));
					vo.setCntrt_oppnt_nm((String)lom.get("cntrt_oppnt_nm"));
					vo.setTnc_app_link((String)lom.get("tnc_app_link"));
					vo.setCtc_link((String)lom.get("ctc_link"));
					vo.setOtc_link((String)lom.get("otc_link"));
					vo.setTnc_summary_link((String)lom.get("tnc_summary_link"));
					vo.setSys_nm((String)lom.get("sys_nm"));
					vo.setCntrt_trgt((String)lom.get("cntrt_trgt"));
					vo.setCntrtperiod_startday((String)lom.get("cntrtperiod_startday"));
					vo.setCntrtperiod_endday((String)lom.get("cntrtperiod_endday"));
							
						
					// TNC tcomp 값을 이용하여 우리 comp_cd, comp_nm 검색
					
					ListOrderedMap tcompLom = new ListOrderedMap();
					List compInfo = tNCtempToECMSBatchJobService.getCompInformation(infContCnsdreqVO);
					if(compInfo != null && compInfo.size()>0){							
						tcompLom =  (ListOrderedMap)compInfo.get(0);
						
						if (tcompLom.get("comp_cd") == null){
							infContCnsdreqVO.setEcms_result_flag("F");
							infContCnsdreqVO.setEcms_result("COMP_CD is not matched");
							tNCtempToECMSBatchJobService.updateECMSContractBatch(infContCnsdreqVO);
							
							return;
							
						}
					
					
						// not null 항목 셋팅
						vo.setComp_cd((String)tcompLom.get("comp_cd"));
						vo.setSession_comp_cd((String)tcompLom.get("comp_cd"));
						vo.setSession_comp_nm((String)tcompLom.get("comp_nm"));
						
						// ====== vendorType 체크로직 S ========
						String customerCd="";
						List isCusTomer = null;
						// customer 존재 여부 체크
						infContCnsdreqVO.setComp_cd((String)tcompLom.get("comp_cd"));
						infContCnsdreqVO.setComp_nm((String)tcompLom.get("comp_nm"));
						isCusTomer = tNCtempToECMSBatchJobService.checkCustomerYN(infContCnsdreqVO);
	
						
						if(isCusTomer ==null || isCusTomer.size()<1){
							// ECMS Customer Table 새로운 customer 생성
							// * infContCnsdreqVO.CNTRT_OPPNT_CD = GERP_CD
							CustomerNewVO cvo = new CustomerNewVO();
							cvo.setSession_comp_cd((String)tcompLom.get("comp_cd"));
							cvo.setSession_comp_nm((String)tcompLom.get("comp_nm"));
							cvo.setGerp_cd((String)lom.get("cntrt_oppnt_cd"));
							cvo.setCustomer_nm1((String)lom.get("cntrt_oppnt_nm"));
							cvo.setIv_nm1((String)lom.get("cntrt_oppnt_nm"));
							cvo.setGunam((String)lom.get("cntrt_oppnt_nm"));
							cvo.setHqnam((String)lom.get("cntrt_oppnt_nm"));
							cvo.setNationRd("G");
							
							cvo.setPostalcode((String)lom.get("postalcode"));
							cvo.setCityn((String)lom.get("cityn"));
							cvo.setNation((String)lom.get("nation"));
							cvo.setStapr((String)lom.get("stapr"));
							
							cvo.setVendor_type((String)lom.get("vendor_type"));
							
							customerCd = customerNewService.insertCustomerReturnCd(cvo);
							vo.setCntrt_oppnt_cd(customerCd);
							
							String cntrt_nm = infContCnsdreqVO.getComp_cd()+"_"+infContCnsdreqVO.getCntrt_oppnt_nm()+"_"+(String)((ListOrderedMap)tNCtempToECMSBatchJobService.getCntrtNm(infContCnsdreqVO).get(0)).get("cntrt_nm");
							
							vo.setCntrt_nm(cntrt_nm);
							
						}else{
							vo.setCntrt_oppnt_nm((String)((ListOrderedMap)isCusTomer.get(0)).get("customer_nm1"));
							vo.setCntrt_oppnt_cd((String)((ListOrderedMap)isCusTomer.get(0)).get("customer_cd"));
							
							String cntrt_nm = infContCnsdreqVO.getComp_cd()+"_"+vo.getCntrt_oppnt_nm()+"_"+(String)((ListOrderedMap)tNCtempToECMSBatchJobService.getCntrtNm(infContCnsdreqVO).get(0)).get("cntrt_nm");
							
							vo.setCntrt_nm(cntrt_nm);
						}
						// ====== vendorType 체크로직 E ========
						
						
						
						
						
						// ======= Insert Temp Table to S ========
						// TN_CLM_CONTRACT_MASTER, 
						// TN_INF_CONT_CNSDREQ, 
						// TN_CLM_CONTRACT_CNSD
						
						// 파일 정보를 가져옴
						List fileList = null;
						ListOrderedMap tfileLom = null;
						
						List fileListInfos1 = new ArrayList();
						List fileListInfos2 = new ArrayList();
						List fileListInfosEtc = new ArrayList();
						
						String fileInfos1 = "";
						String fileInfos2 = "";
						String fileInfosEtc = "";
						
						
						fileList =  tNCtempToECMSBatchJobService.getECMSFileInfo(infContCnsdreqVO);
						
						boolean fileFlag = true;
						InfFileAttachVO infFileAttachVO = new InfFileAttachVO();;
						
						if(fileList != null && fileList.size() > 0){
							for (int j = 0; j < fileList.size(); j++) {
								
								tfileLom = (ListOrderedMap) fileList.get(j);
								
								String fileSmlClsFcn = (String) tfileLom.get("file_smlclsfcn");
								
								if("F0120201".equals(fileSmlClsFcn)){
									fileListInfos1.add(fileList.get(j));
								}else if("F0120205".equals(fileSmlClsFcn)){
									fileListInfos2.add(fileList.get(j));
								}else if("F0120208".equals(fileSmlClsFcn)){
									fileListInfosEtc.add(fileList.get(j));
								}
							}
						
						}
						
						if(fileListInfos1.size()>0){
							//파일정보 서버에 저장 #1 계약서 첨부파일
							List savefileList1 = clmsFileService.tncFileListSave(fileListInfos1);
						
							if(savefileList1 != null && savefileList1.size() == fileListInfos1.size()){
								//File List -> String 변환
								fileInfos1 = clmsFileService.getClmsFileListToFileInfos(savefileList1, "add");
								vo.setFileInfos1(fileInfos1);
							}else{
//								infFileAttachVO.setFile_id(file_id);
//								infFileAttachVO.setFile_srt(file_srt);
//								infFileAttachVO.setEcms_result_flag("F");
//								infFileAttachVO.setEcms_result("첨부파일 없음");
								fileFlag = false;
							}
						}
						
						if(fileListInfos2.size()>0){
							//파일정보 서버에 저장 #2 계약서 기타첨부파일
							List savefileList2 = clmsFileService.tncFileListSave(fileListInfos2);
							
							if(savefileList2 != null && savefileList2.size() == fileListInfos2.size()){
								//File List -> String 변환
								fileInfos2 = clmsFileService.getClmsFileListToFileInfos(savefileList2, "add");
								vo.setFileInfos2(fileInfos2);
							}else{
								fileFlag = false;
							}
						}
						
						if(fileListInfosEtc.size()>0){
							//파일정보 서버에 저장 #fileListEtc 첨부파일 - 첨부/별첨
							List savefileListEtc = clmsFileService.tncFileListSave(fileListInfosEtc);
							
							if(savefileListEtc != null && savefileListEtc.size() == fileListInfosEtc.size()){
								//File List -> String 변환
								fileInfosEtc = clmsFileService.getClmsFileListToFileInfos(savefileListEtc, "add");
								vo.setFileInfosEtc(fileInfosEtc);				
							}else{
								fileFlag = false;
							}
						}
	
						
						vo.setSession_user_id((String)lom.get("reqman_id"));
						vo.setSession_user_nm((String)lom.get("reqman_nm"));
//						vo.setSession_dept_cd((String)lom.get("req_dept"));
						vo.setSession_dept_nm((String)lom.get("req_dept_nm"));
						vo.setSession_jikgup_nm((String)lom.get("reqman_jikgup_nm"));
						vo.setSession_user_locale("en");
						
						vo.setPayment_gbn("C02004");
						vo.setCntrt_rel_prd("XXXXX");
						vo.setSys_cd("LAS");
						
						
						
						vo.setCntrt_id(considerationService.getId());
						vo.setPrev_cnsdreq_id("");
						
						List tPostCont = null;
						String tPostGbn = "";
						tPostCont = tNCtempToECMSBatchJobService.getPostConYnByComp(infContCnsdreqVO);
						
						if(tPostCont!=null && tPostCont.size()>0){
							ListOrderedMap tPost = (ListOrderedMap)tPostCont.get(0);
							tPostGbn = (String) tPost.get("useman_mng_itm4");
						}
						
						if("L".equals((String)lom.get("tnc_source"))){
							vo.setCntrt_src_cont_drft("SOC20");
						}else if("S".equals((String)lom.get("tnc_source"))){
							vo.setCntrt_src_cont_drft("SOC10");
						}else if("C".equals((String)lom.get("tnc_source"))){
							vo.setCntrt_src_cont_drft("SOC30");
						}
						
						/*   ============== 임시 상태 S ================ */
//						vo.setReq_title("[T&C Post-Conclusion Registration]"+vo.getReq_title());
//						vo.setPlndbn_req_yn("");
//						
//						vo.setRegion_gbn("C01801");	//국내 - master			
//						vo.setSeal_mthd("C02102");  //날인
//						vo.setCnsdreq_id("");
//						returnLom = registrationService.insertRegistration(vo);
//						
//						if(returnLom.size() > 0){
//							
//							infContCnsdreqVO.setCntrt_id((String)returnLom.get("cntrt_id"));
//							//ECMS_DT, ECMS_RESULT_FLAG, ECMS_RESULT update
//							infContCnsdreqVO.setEcms_result_flag("Y");
//							infContCnsdreqVO.setEcms_result("SUCCESS");
//							InstSucc += tNCtempToECMSBatchJobService.updateECMSContractBatch(infContCnsdreqVO);
//							
//							//TNC NO & 계약ID 매핑
//							tNCtempToECMSBatchJobService.insertTnInfContCnsdReq(infContCnsdreqVO);
//						}
						/*   ============== 임시 상태 E ================ */
						
						
						/*   ============== 임시 주석 S ================ */
						if(fileFlag){
						
							vo.setPlndbn_req_yn("");
							vo.setCnsdreq_id("");
							
							// TB_COM_CD 테이블의 USEMAN_MNG_ITM4 에 Y 로 표시된 법인의 경우
							if("Y".equals(tPostGbn)){
							/* T030705002  OTC Only			SOC10	HQ Standard
					         * T030705003  CTC Only			SOC20	Local Standard
					         * T030705006  OTC + CTC 이고	SOC30	Counterparty
					         */
							
							
								// Legal 이 없는 4,5,6 번의경우
								if("T030705002".equals((String)lom.get("cntrt_trgt")) || "T030705003".equals((String)lom.get("cntrt_trgt")) || "T030705006".equals((String)lom.get("cntrt_trgt")) ){
									
									// HQ Standard, Local Standard 인경우 
									if("L".equals((String)lom.get("tnc_source")) || "S".equals((String)lom.get("tnc_source"))){    
										// Post-Conclusion에 insert
										
										vo.setReq_title("[T&C Post-Conclusion Registration]"+vo.getReq_title());
										
										vo.setRegion_gbn("C01801");	//국내 - master			
										vo.setSeal_mthd("C02102");  //날인
										
										returnLom = registrationService.insertRegistration(vo);
									}else{
										// Counterparty 인경우 
										returnLom = considerationService.insertConsideration(vo);
									}
													
								}else{
									// Legal 이 있는 계약
									returnLom = considerationService.insertConsideration(vo);							
								}
							}else{
								
								// TB_COM_CD 테이블의 USEMAN_MNG_ITM4 에 표시되지 않은 경우
								returnLom = considerationService.insertConsideration(vo);
							}
							
							
							
							// =========ECMS 후처리 결과 update S ===========
							if(returnLom.size() > 0){
								
								infContCnsdreqVO.setCntrt_id((String)returnLom.get("cntrt_id"));
								//ECMS_DT, ECMS_RESULT_FLAG, ECMS_RESULT update
								infContCnsdreqVO.setEcms_result_flag("Y");
								infContCnsdreqVO.setEcms_result("SUCCESS");
								InstSucc += tNCtempToECMSBatchJobService.updateECMSContractBatch(infContCnsdreqVO);
								
								//TNC NO & 계약ID 매핑
								tNCtempToECMSBatchJobService.insertTnInfContCnsdReq(infContCnsdreqVO);
							}
							// =========ECMS 후처리 결과 update E ===========
							
						}else{
							infContCnsdreqVO.setEcms_result_flag("F");
							infContCnsdreqVO.setEcms_result("Attach File does not exist");
							InstFail += tNCtempToECMSBatchJobService.updateECMSContractBatch(infContCnsdreqVO);
							
						}
						/*   ============== 임시 주석 E ================ */
					
					}else{
						infContCnsdreqVO.setEcms_result_flag("F");
						infContCnsdreqVO.setEcms_result("COMP_CD is not matched");
						tNCtempToECMSBatchJobService.updateECMSContractBatch(infContCnsdreqVO);
					}
				}
			}

			
			
//			getLogger().info("테이블 인서트 완료 : "+InstSucc);		
			
		} catch(Exception e) {
			e.printStackTrace();
			getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+e.getMessage());					
		} finally{
	        
		}
	}
	

	@SuppressWarnings("unchecked")
	private void insertMigTNCContractBatch() throws Exception{	
		
		try {
			
			
			/**********************************************************************************
			 *
			 **********************************************************************************/
			
			List tncTempList = null;
			InfContractInfoVO vo = new InfContractInfoVO();
			InfContCnsdreqVO infContCnsdreqVO = new InfContCnsdreqVO();
			
			infContCnsdreqVO.setComp_cd("SEAU");
			tncTempList   = tNCtempToECMSBatchJobService.listMigTNCContractBatch(infContCnsdreqVO);
			
			for (int i = 0; i < tncTempList.size(); i++) {
				
			
				ListOrderedMap tLom = (ListOrderedMap)tncTempList.get(i);
				
				vo.setCntrt_id((String)tLom.get("cntrt_id"));
				
				/* =========== tnc 로직 추가 Start ================ */
				List tncInfo = commonDAO.list("common.legacyintf.getTncMatchInfo", vo);
				
				if(tncInfo.size()>0){
					InfContractInfoVO infContractInfoVO = new InfContractInfoVO();
					
					ListOrderedMap tncLom = (ListOrderedMap)tncInfo.get(0);
					
					infContractInfoVO.setTcomp_cd((String)tncLom.get("tcomp_cd"));
					infContractInfoVO.setKey_id((String)tncLom.get("key_id"));
					infContractInfoVO.setCntrt_no((String)tncLom.get("cntrt_no"));
					infContractInfoVO.setCntrt_nm((String)tncLom.get("cntrt_nm"));
					infContractInfoVO.setCont_draft((String)tncLom.get("cont_draft"));
					infContractInfoVO.setSys_nm("TNC");
					infContractInfoVO.setEcms_result_flag("N");
					infContractInfoVO.setReg_id("SYSTEM_BATCH");
					infContractInfoVO.setCntrt_id((String)tncLom.get("cntrt_id"));
					infContractInfoVO.setComp_cd((String)tncLom.get("comp_cd"));
					
					
					List tempList = commonDAO.list("common.legacyintf.countRequiredItemInfoForPost", infContractInfoVO);
					
					if(tempList.size()>0){
						ListOrderedMap tempLom = (ListOrderedMap)tempList.get(0); 
						if("Y".equals((String)tempLom.get("check_list_yn"))){
						
							List reqItemInfo = commonDAO.list("common.legacyintf.getRequiredItemInfoForPost", infContractInfoVO);
						
							if(reqItemInfo.size()>0){
								infContractInfoVO.setItem_cd1((String)((ListOrderedMap)reqItemInfo.get(0)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd2((String)((ListOrderedMap)reqItemInfo.get(1)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd3((String)((ListOrderedMap)reqItemInfo.get(2)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd4((String)((ListOrderedMap)reqItemInfo.get(3)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd5((String)((ListOrderedMap)reqItemInfo.get(4)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd6((String)((ListOrderedMap)reqItemInfo.get(5)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd7((String)((ListOrderedMap)reqItemInfo.get(6)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd8((String)((ListOrderedMap)reqItemInfo.get(7)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd9((String)((ListOrderedMap)reqItemInfo.get(8)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd10((String)((ListOrderedMap)reqItemInfo.get(9)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd11((String)((ListOrderedMap)reqItemInfo.get(10)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd12((String)((ListOrderedMap)reqItemInfo.get(11)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd13((String)((ListOrderedMap)reqItemInfo.get(12)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd14((String)((ListOrderedMap)reqItemInfo.get(13)).get("ITEM_YN"));
								infContractInfoVO.setItem_cd15((String)((ListOrderedMap)reqItemInfo.get(14)).get("ITEM_YN"));
								
								commonDAO.insert("common.legacyintf.insertInfCntTncMatch", infContractInfoVO);
								
								//이행상태로 마스터 값 변경
								ConsultationVO consultationVO = new ConsultationVO();
								
								consultationVO.setCntrt_id(infContractInfoVO.getCntrt_id());
								consultationVO.setCntrt_status("C02402");									//계약상태 체결 master
								consultationVO.setPrcs_depth("C02504");										//프로세스 계약이행
								consultationVO.setDepth_status("C02662");									//단계상태 이행중
								consultationVO.setPrgrs_status("C04219"); 									//이행중
								
								tNCtempToECMSBatchJobService.updateMigTNCContractStatus(consultationVO);
								
							}
						}
					}
				}
			}
			
			
			// txt 파일 만들기 
			
			int exeSize = 0;
			String serverDir = propertyService.getProperty("secfw.legacyinf.tnc.send.data.dir");
			
			if (!new File(serverDir).exists()) {
				new File(serverDir).mkdirs();
			}
		
			InfContractInfoVO infContractInfoVO = new InfContractInfoVO();
			
			infContractInfoVO.setSys_nm("TNC");
			
			List copySendInfoForList = legacyInterfaceService.loadInfCopySendinfo(infContractInfoVO);		
			
			getLogger().info("TN_INF_COPY_SENDINFO의  'SEND' 대상 수:"+copySendInfoForList.size());	
			
			exeSize = legacyInterfaceService.writeFileForCopySendInfo(copySendInfoForList,serverDir);	
			
			
		} catch(Exception e) {
			e.printStackTrace();
			getLogger().info("인터페이스 메타정보파일 등록(File to DB) 실패:"+e.getMessage());					
		} finally{
	        
		}
		
	}

}
