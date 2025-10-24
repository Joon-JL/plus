package com.sec.clm.tnc.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import anyframe.core.query.QueryServiceException;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.dto.InfContractInfoVO;
import com.sec.clm.tnc.dto.InfFileAttachVO;
import com.sec.clm.tnc.service.LegacyInterfaceService;

/**
 * Description	: Legacy Interface Service impl(concrete class)
 * Author 		: 홍성현
 * Date			: 2014. 05. 26
 */
public class LegacyInterfaceServiceImpl extends CommonServiceImpl implements LegacyInterfaceService {
	
	/**
	 * CommonDAO에 대한 DAO선언 및 세팅
	 * @param commonDAO
	 */
	private CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public void setFileEncType(String fileEncType) {
		this.fileEncType = fileEncType;
	}
		
	private PropertyService propertyService;	
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	private EsbOrgService esbOrgService;	
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}
		
	private String fileEncType;	

	
	public int insertInfFileAttach(InfFileAttachVO infFileAttachVO) throws Exception{		
		
		int res = commonDAO.insert("common.legacyintf.insertInfFileAttach",infFileAttachVO);
		logQueryId("common.legacyintf.insertInfFileAttach",res);
		getLogger().info("key_id:"+infFileAttachVO.getKey_id());
		getLogger().info("key_nm:"+infFileAttachVO.getKey_nm());
		
		return res;
		
	}
	
	public int insertInfContCnsdreq(InfContCnsdreqVO infContCnsdreqVO){
		
		int res = 0;
		try {
			res = commonDAO.insert("common.legacyintf.insertInfContCnsdreq",infContCnsdreqVO);
			logQueryId("common.legacyintf.insertInfContCnsdreq",res);	
			getLogger().info("key_id:"+infContCnsdreqVO.getKey_id());
			getLogger().info("key_nm:"+infContCnsdreqVO.getKey_nm());
			
		} catch (QueryServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public List loadInfCopySendinfo(InfContractInfoVO vo) throws Exception{
		
		/*************************************************
		 * 인터페이스 데이터 로딩 - TN_INF_CONTRACT_INFO
		 *************************************************/
		return commonDAO.list("common.legacyintf.listInfContractInfo", vo); 
		
	}
	
	
	public int writeFileForCopySendInfo(List copySendInfoList, String path) throws Exception{

		int res = 0;
		FileOutputStream fos = null;
		BufferedWriter UniOutput = null;
		
		try {
			
			// 파일생성됨.			
			if(copySendInfoList.size()>0){
				ListOrderedMap tlom = (ListOrderedMap)copySendInfoList.get(0);				
				File outputFile = new File(path+File.separator+"IF_SELMS_"+(String)tlom.get("cntrt_no")+"_"+DateUtil.getDate(new Date())+".txt");
				
				fos = new FileOutputStream(outputFile);
				
				UniOutput = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
				
				
				for(int i=0; i <copySendInfoList.size();i++){	
					
					ListOrderedMap lom = (ListOrderedMap)copySendInfoList.get(i);				
					
					String inputStr = (String)lom.get("tcomp_cd")+"*%^&|"
							+(String)lom.get("key_id")+"*%^&|"
							+(String)lom.get("cntrt_id")+"*%^&|"
							+((String)lom.get("cntrt_no")).replaceAll("-", "")+"*%^&|"
							+(String)lom.get("cntrt_nm")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd1"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd2"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd3"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd4"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd5"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd6"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd7"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd8"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd9"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd10"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd11"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd12"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd13"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd14"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("item_cd15"),"")+"*%^&|"
							+StringUtil.nvl((String)lom.get("cont_draft"),"");
					
					
					UniOutput.write(inputStr);
					UniOutput.newLine();
					
					InfContractInfoVO infconVO = new InfContractInfoVO();
					
					infconVO.setTcomp_cd((String)lom.get("tcomp_cd"));
					infconVO.setKey_id((String)lom.get("key_id"));
					infconVO.setEcms_result_flag("Y");
					infconVO.setEcms_result("SUCCESS");
					
					getLogger().info("key_id:"+infconVO.getKey_id());	
					getLogger().info("cntrt_id:"+infconVO.getTcomp_cd());
					
					
					res = res + commonDAO.modify("common.legacyintf.updateSendinfo", infconVO);
	
				}
				
				getLogger().info(res+":건 작성됨");
				UniOutput.close();
				
				if(res==0){
					outputFile.delete();
				}
			}else{
				getLogger().info("================ 작성가능 List 없음 ================");
			}
						
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				fos.close();
				fos = null;
			}
			
			if(UniOutput!=null){
				UniOutput.close();
				UniOutput = null;
			}
		}
		
		return res;
	}

		
	
	protected Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}
	
	private void logQueryId(String queryId, int res){
		if(res>0){
			getLogger().info("실행 성공한 query id:  "+queryId);
		}else{
			getLogger().info("실행 실패한 query id:  "+queryId);
		}		
	}

}