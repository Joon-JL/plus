package com.sds.secframework.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.bbs.dto.BBSMngVO;
import com.sds.secframework.bbs.service.BBSMngService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;

public class BBSMngServiceImpl extends CommonServiceImpl implements BBSMngService {

	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	
	/**
	 * 게시판 목록 조회
	 * @param sys_cd 시스템 코드
	 * @return 관리자용 메뉴리스트
	 * @throws Exception
	 */
	public List listBBSMasterPage(HashMap hm) throws Exception {
		
		/***************************************************
		 * - Logging 사용방법
		 *    : Service의 경우 extends CommonService 
		 *   그리고, 아래처럼 사용
		 * *************************************************/ 

		return  commonDAO.list("secfw.bbs.listBBSMasterPage", hm);
	}

	/**
	 * 게시판 상세내역 조회
	 * @param HashMap 시스템 코드, 게시판ID
	 * @return 게시판 상세내역
	 * @throws Exception
	 */
	public List detailBBSMaster(HashMap hm) throws Exception {
		return commonDAO.list("secfw.bbs.detailBBSMaster", hm);
	}

	/**
	 * 게시판 상세내역 조회(게시내역에서 참조)
	 * @param HashMap 시스템 코드, 게시판ID
	 * @return 게시판 상세내역
	 * @throws Exception
	 */
	public Map getBBSMaster(HashMap hm) throws Exception {
		ListOrderedMap result = new ListOrderedMap();
		
		List temp = commonDAO.list("secfw.bbs.detailBBSMaster", hm);
		
		if(temp != null && temp.size()>0) {
			result = (ListOrderedMap)temp.get(0);
		}
		
		return result;
	}
	
	/**
	 * 게시판 내역 등록
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public int insertBBSMaster(BBSMngVO vo) throws Exception {
		
		//게시판 아이디 생성
		String bbs_id = DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
		vo.setBbs_id(bbs_id);
		
		return commonDAO.insert("secfw.bbs.insertBBSMaster", vo);
	}

	/**
	 * 게시판 내역 수정
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public int modifyBBSMaster(BBSMngVO vo) throws Exception {
		return commonDAO.modify("secfw.bbs.modifyBBSMaster", vo);		
	}

	/**
	 * 게시판 내역 삭제
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public int deleteBBSMaster(HashMap hm) throws Exception {
		return commonDAO.delete("secfw.bbs.deleteBBSMaster", hm);		
	}
}
