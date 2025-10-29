/**
 * Project Name : 법무시스템
 * File Name : LawfirmManageServiceImpl.java
 * Description : 로펌 관리 ServiceImpl
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */

package com.sec.las.lawservice.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawfirmEstimateVO;
import com.sec.las.lawservice.dto.LawyerManageVO;
import com.sec.las.lawservice.service.LawfirmManageService;

public class LawfirmManageServiceImpl extends CommonServiceImpl implements LawfirmManageService {

	/**
	 * 로펌관리 목록 조회
	 * 
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listLawfirmManage(LawfirmManageVO vo) throws Exception {
		
		//XSS
		vo.setSrch_event_no(StringUtil.bvl(vo.getSrch_event_no(),""));
		vo.setSrch_event_nm(StringUtil.bvl(vo.getSrch_event_nm(),""));
		vo.setSrch_lawfirm_id(StringUtil.bvl(vo.getSrch_lawfirm_id(),""));
		vo.setSrch_lawfirm_nm(StringUtil.bvl(vo.getSrch_lawfirm_nm(),""));
		vo.setSrch_kbn1(StringUtil.bvl(vo.getSrch_kbn1(),""));
		vo.setSrch_kbn2(StringUtil.bvl(vo.getSrch_kbn2(),""));
		vo.setSrch_mainftre_estmt(StringUtil.bvl(vo.getSrch_mainftre_estmt(),""));
		vo.setSrch_nation(StringUtil.bvl(vo.getSrch_nation(),""));
		vo.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_end_dt(),"")));
		vo.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_start_dt(),"")));

		return commonDAO.list("las.lawservice.listLawfirmManage", vo);

	}

	/**
	 * 로펌관리 등록
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	public int insertLawfirmManage(LawfirmManageVO vo) throws Exception {

		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getLawfirm_id());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);
		
		//XSS
		vo.setLawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawfirm_id(),"")));
		vo.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getNation(),"")));
		vo.setFst_cntrtday(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFst_cntrtday(),"")));
		vo.setRprsnt_tel(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRprsnt_tel(),"")));
		vo.setRprsnt_fax(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRprsnt_fax(),"")));
		vo.setRprsnt_email(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRprsnt_email(),"")));
		vo.setHomepage(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHomepage(),"")));
		vo.setState_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getState_gbn(),"")));
		vo.setAddr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAddr(),"")));
		vo.setMainftre_estmt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMainftre_estmt(),"")));
		vo.setBank_addr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBank_addr(),"")));
		vo.setBank_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBank_nm(),"")));
		vo.setAccnt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAccnt_no(),"")));
		vo.setBank_note(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBank_note(),"")));
		vo.setLog_ymd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLog_ymd(),"")));

		return commonDAO.insert("las.lawservice.insertLawfirmManage", vo);
	}

	/**
	 * 로펌관리 수정
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyLawfirmManage(LawfirmManageVO vo) throws Exception {

		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getLawfirm_id());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);
		
		//XSS
		vo.setLawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawfirm_id(),"")));
		vo.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getNation(),"")));
		vo.setFst_cntrtday(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFst_cntrtday(),"")));
		vo.setRprsnt_tel(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRprsnt_tel(),"")));
		vo.setRprsnt_fax(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRprsnt_fax(),"")));
		vo.setRprsnt_email(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRprsnt_email(),"")));
		vo.setHomepage(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getHomepage(),"")));
		vo.setState_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getState_gbn(),"")));
		vo.setAddr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAddr(),"")));
		vo.setMainftre_estmt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMainftre_estmt(),"")));
		vo.setBank_addr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBank_addr(),"")));
		vo.setBank_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBank_nm(),"")));
		vo.setAccnt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAccnt_no(),"")));
		vo.setBank_note(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getBank_note(),"")));
		vo.setLog_ymd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLog_ymd(),"")));

		return commonDAO.modify("las.lawservice.modifyLawfirmManage", vo);
	}

	/**
	 * 로펌관리 삭제
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteLawfirmManage(LawfirmManageVO vo) throws Exception {
		
        ClmsFileVO fvo = new ClmsFileVO(); 

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
        fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
        fvo.setRef_key((vo.getLawfirm_id()));
        fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());        

        clmsFileService.delClmsAttachFile(fvo);	
        
		return commonDAO.delete("las.lawservice.deleteLawfirmManage", vo);
	}

	/**
	 * 로펌관리 상세조회
	 * 
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	public List detailLawfirmManage(LawfirmManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.detailLawfirmManage", vo);
	}

	/**
	 * 로펌관리 하단 사건리스트 조회
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listLawfrimManageGetEventList(EventManageVO evo)
	throws Exception {
		return commonDAO.list("las.lawservice.listGetEventList", evo);
	}

	/**
	 * 로펌ID Seq 조회
	 * 
	 * @param LawfirmManageVO
	 * @return String
	 * @throws Exception
	 */
	public String getMaxLawfirmID(LawfirmManageVO vo) throws Exception {

		String lawfirm_id = "";
		List resultList = null;

		resultList = commonDAO.list("las.lawservice.getMaxLawfirmID", vo);

		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			lawfirm_id = vo.getNation() + (String) lom.get("lawfirm_id");
		}
		return lawfirm_id;
	}

	/**
	 * 로펌평가 목록
	 * 
	 * @param LawfirmEstimateVO
	 * @return List
	 * @throws Exception
	 */
	public List listLawfirmEstimate(LawfirmEstimateVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listLawfirmEstimate", vo);
	}


	/**
	 * 로펌평가 등록
	 * 
	 * @param LawfirmEstimateVO
	 * @return int
	 * @throws Exception
	 */
	public int insertLawfirmEstimate(LawfirmEstimateVO vo) throws Exception {

		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getEstmt_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);
		
		//Cross-site Scripting 방지
		vo.setLawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawfirm_id(),"")));
		vo.setEstmt_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getEstmt_name(),"")));
		vo.setEstmt_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getEstmt_cont(),"")));

		return commonDAO.insert("las.lawservice.insertLawfirmEstimate", vo);

	}

	/**
	 * 로펌평가 ID SEQ 조회
	 * 
	 * @param LawfirmEstimateVO
	 * @return List
	 * @throws Exception
	 */
	public String getNextEstmtNo(LawfirmManageVO vo) throws Exception {

		String estmt_no = "1";
		List resultList = null;

		resultList = commonDAO.list("las.lawservice.getMaxfirmEstimateNo", vo);

		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			estmt_no = (String) lom.get("estmt_no");
		}
		return estmt_no;
	}
	
	/**
	 * 로펌평가 삭제
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteLawfirmEstimate(LawfirmEstimateVO vo) throws Exception {
        ClmsFileVO fvo = new ClmsFileVO(); 

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn("F005");
        fvo.setFile_midclsfcn("F00506");
        fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
        fvo.setRef_key(vo.getEstmt_no());
        fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());        

        clmsFileService.delClmsAttachFile(fvo);	
		
		return commonDAO.delete("las.lawservice.deleteLawfirmEstimate", vo);
	}
	
	/**
	 * 로펌소속변호사 목록
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	public List getListLawfirmLawywer(LawyerManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.getListLawfirmLawywer", vo);
	}
	
}