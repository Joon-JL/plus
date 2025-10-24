/**
 * Project Name : 법무시스템
 * File Name : LawfirmRemitCertServiceImpl.java
 * Description : 송금증 ServiceImpl
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawfirmRemitCertVO;
import com.sec.las.lawservice.service.LawfirmRemitCertService;

public class LawfirmRemitCertServiceImpl extends CommonServiceImpl implements LawfirmRemitCertService {
	
	/**
	 * 송금증 목록 조회
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listLawfirmRemitCert(LawfirmManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listRemitCert", vo);
	}
	
	/**
	 * 송금증 등록
	 * @param LawfirmRemitCertVO
	 * @return int
	 * @throws Exception
	 */
	public int insertLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception {
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getRemitcert_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);	
		
		return commonDAO.insert("las.lawservice.insertLawfirmRemitCert", vo);
	}
	
	/**
	 * 송금증 수정
	 * @param LawfirmRemitCertVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception {
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getRemitcert_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);	
		
		return commonDAO.modify("las.lawservice.modifyLawfirmRemitCert", vo);
	}
	
	/**
	 * 송금증 삭제
	 * @param LawfirmRemitCertVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception {
		
        ClmsFileVO fvo = new ClmsFileVO(); 

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
        fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
        fvo.setRef_key((vo.getRemitcert_no()));
        fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());        

        clmsFileService.delClmsAttachFile(fvo);
		
		 return commonDAO.delete("las.lawservice.deleteLawfirmRemitCert", vo);
	}
	
	/**
	 * 송금증 상세
	 * @param LawfirmRemitCertVO
	 * @return List
	 * @throws Exception
	 */
	public List detailLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception {
		return commonDAO.list("las.lawservice.detailLawfirmRemitCert", vo);
	}

}