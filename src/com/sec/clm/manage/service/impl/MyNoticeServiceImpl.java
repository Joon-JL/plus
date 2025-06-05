package com.sec.clm.manage.service.impl;

import java.util.HashMap;
import java.util.List;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.dto.MyNoticeVO;
import com.sec.clm.manage.service.MyNoticeService;
   
public class MyNoticeServiceImpl extends CommonServiceImpl implements MyNoticeService {

	public List listMyNotice(MyNoticeVO vo) throws Exception {
		return commonDAO.list("clm.manage.listMyNotice", vo);
	}

    public List detailMyNotice(MyNoticeVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailMyNotice", vo);
	}
	/**
	 * 수신자 리스트
	 * @param vo MyNoticeVO
	 * @return List
	 * @throws Exception
	 */
	public List listRecman(MyNoticeVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setMis_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMis_id(),"")));
		return commonDAO.list("clm.manage.recipientMyNotice", vo);
	}
	
	public List listMyNoticeByMain(MyNoticeVO vo) throws Exception {
		return commonDAO.list("clm.manage.listMyNoticeByMain", vo);
	}

}