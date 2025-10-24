package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.manage.dto.MyNoticeVO;

public interface MyNoticeService {

	List listMyNotice(MyNoticeVO vo) throws Exception;

	List detailMyNotice(MyNoticeVO vo) throws Exception;
	
	/**
	 * 수신자 리스트
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List listRecman(MyNoticeVO vo) throws Exception;
	
	List listMyNoticeByMain(MyNoticeVO vo) throws Exception;
}