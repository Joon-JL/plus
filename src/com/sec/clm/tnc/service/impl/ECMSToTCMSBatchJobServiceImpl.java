package com.sec.clm.tnc.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.service.ECMSToTCMSBatchJobService;

/**
 * Description	: ECMSToTCMSBatchJobServiceImpl(concrete class)
 * Author 		: 홍성현
 * Date			: 2014. 07. 26
 */
public class ECMSToTCMSBatchJobServiceImpl extends CommonServiceImpl implements ECMSToTCMSBatchJobService {
	
	/**
	 * CommonDAO에 대한 DAO선언 및 세팅
	 * @param commonDAO
	 */
	private CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	

	@Override
	public List getAllContractsForTCMS() throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("common.legacyintf.getAllContractsForTCMS");
	}
	

	@Override
	public void writeFileForTCMSBatch(List copySendInfoList, String path)
			throws Exception {
		// TODO Auto-generated method stub
		int res = 0;
		FileOutputStream fos = null;
		BufferedWriter UniOutput = null;
		
		try {
			// 파일생성됨.			
			if(copySendInfoList.size()>0){
				File outputFile = new File(path+File.separator+"ST_ECMS_BATCH_"+DateUtil.getDate(new Date())+".txt");
				
				fos = new FileOutputStream(outputFile);
				
				UniOutput = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
				
				
				for(int i=0; i <copySendInfoList.size();i++){	
					
					ListOrderedMap lom = (ListOrderedMap)copySendInfoList.get(i);	
					String inputStr ="";
					if("Y".equals((String)lom.get("close_yn"))){
						inputStr = (String)lom.get("comp_cd")+"*%^&|"
							+(String)lom.get("key_id")+"*%^&|"
							+(String)lom.get("cntrt_id")+"*%^&|"
							+((String)lom.get("cntrt_no")).replaceAll("-", "")+"*%^&|"
							+(String)lom.get("req_title")+"*%^&|"
							+(String)lom.get("prcs_depth")+"*%^&|"
							+(String)lom.get("prcs_depth_nm")+"*%^&|"
							+"CLOSE*%^&|CLOSE";
					}else{
						inputStr = (String)lom.get("comp_cd")+"*%^&|"
								+(String)lom.get("key_id")+"*%^&|"
								+(String)lom.get("cntrt_id")+"*%^&|"
								+((String)lom.get("cntrt_no")).replaceAll("-", "")+"*%^&|"
								+(String)lom.get("req_title")+"*%^&|"
								+(String)lom.get("prcs_depth")+"*%^&|"
								+(String)lom.get("prcs_depth_nm")+"*%^&|"
								+(String)lom.get("depth_status")+"*%^&|"
								+(String)lom.get("depth_status_nm");
						
					}
					UniOutput.write(inputStr);
					UniOutput.newLine();
					res++;
					
				}
				
				getLogger().info(res+":건 작성됨");
				UniOutput.close();
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
		
	}


	@Override
	public void updateAfterBatch(InfContCnsdreqVO vo) throws Exception {
		// TODO Auto-generated method stub
		commonDAO.modify("common.legacyintf.updateAfterBatch", vo);
	}
	
	@Override
	public void insertAllContractsForTCMS() throws Exception {
		// TODO Auto-generated method stub
		commonDAO.modify("common.legacyintf.insertAfterBatch");
	}

}