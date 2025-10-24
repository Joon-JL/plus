package com.sec.clm.tnc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.service.TNCtempToECMSBatchJobService;

/**
 * Description	: TNCtempToECMSBatchJobServiceImpl(concrete class)
 * Author 		: 홍성현
 * Date			: 2014. 05. 26
 */
public class TNCtempToECMSBatchJobServiceImpl extends CommonServiceImpl implements TNCtempToECMSBatchJobService {
	
	/**
	 * CommonDAO에 대한 DAO선언 및 세팅
	 * @param commonDAO
	 */
	private CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}


	

	@Override
	public List listTempECMSContractBatch() throws Exception {
		// TODO Auto-generated method stub		
		return commonDAO.list("common.legacyintf.listTempECMSContractBatch");
		
	}

//	@Override
//	public int insertToECMSContractBatch(InfContCnsdreqVO infContractInfoVO)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return commonDAO.insert("common.legacyintf.insertToECMSContractBatch");
//	}	

	
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


	@Override
	public List getCntrtNm(InfContCnsdreqVO infContCnsdreqVO) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("common.legacyintf.getCntrtNm", infContCnsdreqVO);
	}


	@Override
	public int updateECMSContractBatch(InfContCnsdreqVO infContCnsdreqVO) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.modify("common.legacyintf.updateECMSContractBatch", infContCnsdreqVO);
	}


	@Override
	public List getCompInformation(InfContCnsdreqVO infContCnsdreqVO) throws Exception {
		// TODO Auto-generated method stub
		 
		List retComp = commonDAO.list("common.legacyintf.getCompInformation", infContCnsdreqVO);
		
		// comp_cd 가 두개이상 중복으로 검색 될경우 사용자의 comp_cd 를 선택
		if(retComp != null && retComp.size()>1){
			
			List userComp = commonDAO.list("common.legacyintf.getUserComp", infContCnsdreqVO);
			
			return userComp;
			
		}
		
		return retComp;
		
	}


	@Override
	public void insertTnInfContCnsdReq(InfContCnsdreqVO infContCnsdreqVO) throws Exception {
		// TODO Auto-generated method stub
		commonDAO.insert("common.legacyintf.insertTnInfContCnsdReq", infContCnsdreqVO);
	}


	@Override
	public List checkCustomerYN(InfContCnsdreqVO infContCnsdreqVO)
			throws Exception {
		return commonDAO.list("common.legacyintf.checkCustomerYN", infContCnsdreqVO);
	}


	@Override
	public int insertNewTNCCustomer(InfContCnsdreqVO infContCnsdreqVO)
			throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.insert("common.legacyintf.insertNewTNCCustomer", infContCnsdreqVO);
	}


	@Override
	public List getECMSFileInfo(InfContCnsdreqVO infContCnsdreqVO)
			throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("common.legacyintf.getECMSFileInfo", infContCnsdreqVO);
	}


	@Override
	public List getPostConYnByComp(InfContCnsdreqVO infContCnsdreqVO)
			throws Exception {
		return commonDAO.list("common.legacyintf.getPostConYnByComp", infContCnsdreqVO);
	}




	@Override
	public List listMigTNCContractBatch(InfContCnsdreqVO infContCnsdreqVO) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("common.legacyintf.listMigTNCContractBatch", infContCnsdreqVO);
	}




	@Override
	public void updateMigTNCContractStatus(ConsultationVO consultationVO)
			throws Exception {
		// TODO Auto-generated method stub
		commonDAO.modify("common.legacyintf.updateMigTNCContractStatus", consultationVO);
	}
}