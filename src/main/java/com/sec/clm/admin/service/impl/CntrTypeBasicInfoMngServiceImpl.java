/**
 * Project Name : 계약관리체계 강화 프로젝트
 * File name	: CntrTypeBasicInfoMngServiceImpl.java
 * Description	: 계약유형별 속성관리 Service impl(concrete class)
 * Author		: 김형준
 * Date			: 2011. 09. 01
 * Copyright	:
 */
package com.sec.clm.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.admin.dto.CntrTypeBasicInfoMngVO;
import com.sec.clm.admin.service.CntrTypeBasicInfoMngService;
import com.sec.common.util.ClmsDataUtil;

import flex.messaging.io.ArrayList;

/**
 * Description	: Service impl(concrete class)
 * Author		: 김형준
 * Date			: 2011. 09. 01
 */
public class CntrTypeBasicInfoMngServiceImpl extends CommonServiceImpl implements CntrTypeBasicInfoMngService{
	
	/**
	 * 조회
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */
	public List listCntrTypeBasicInfoMng(CntrTypeBasicInfoMngVO vo) throws Exception{
		return  commonDAO.list("clm.admin.listCntrTypeBasicInfoMng", vo);
	}
	
	/**
	 * 조회(iFrame 부분)
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */
	public HashMap listCntrTypeBasicInfoMngIF(CntrTypeBasicInfoMngVO vo) throws Exception{
		HashMap resultMap = new HashMap();
		List list = null;
		
		String[] biz_clsfcn_arr 			= vo.getBiz_clsfcn_arr();
		String[] depth_clsfcn_arr			= vo.getDepth_clsfcn_arr();
		String[] cnclsnpurps_bigclsfcn_arr 	= vo.getCnclsnpurps_bigclsfcn_arr();
		String[] cnclsnpurps_midclsfcn_arr 	= vo.getCnclsnpurps_midclsfcn_arr();
		
		for(int i=0; i < biz_clsfcn_arr.length; i++){
			vo.setBiz_clsfcn(biz_clsfcn_arr[i]);
			vo.setDepth_clsfcn(depth_clsfcn_arr[i]);
			vo.setCnclsnpurps_bigclsfcn(cnclsnpurps_bigclsfcn_arr[i]);
			vo.setCnclsnpurps_midclsfcn(cnclsnpurps_midclsfcn_arr[i]);
			
			list = commonDAO.list("clm.admin.listCntrTypeBasicInfoMngIF", vo);
			
			resultMap.put("iframeList"+i, list);
		}
		
		//정보항목 리스트 구하기
		List infoItmlist = commonDAO.list("clm.admin.listInfoItm", vo);
		resultMap.put("infoItmlist", infoItmlist);
		
		return resultMap;
	}
	
	/**
	 * 상세조회
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */
	public HashMap detailCntrTypeBasicInfoMng(CntrTypeBasicInfoMngVO vo) throws Exception{
		HashMap resultMap = new HashMap();
		
		//상세 구하기
		List detaillist = commonDAO.list("clm.admin.detailCntrTypeBasicInfoMng", vo);
		resultMap.put("detaillist", detaillist);
		
		//정보항목 리스트 구하기
		List infoItmlist = commonDAO.list("clm.admin.listInfoItm", vo);
		resultMap.put("infoItmlist", infoItmlist);
		
		return resultMap;
	}

	/**
	 * 수정
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */
	public int modifyCntrTypeBasicInfoMng(CntrTypeBasicInfoMngVO vo) throws Exception{
		int result = -1;
		
		String[] mndtry_yn_arr 			= vo.getMndtry_yn_arr();
		String[] attr_seqno_arr			= vo.getAttr_seqno_arr();
		
		for(int i=0; i < attr_seqno_arr.length; i++){
			vo.setMndtry_yn(mndtry_yn_arr[i]);
			vo.setAttr_seqno(attr_seqno_arr[i]);
			
			commonDAO.modify("clm.admin.modifyCntrTypeBasicInfoMng", vo);
		}
		
		result = 1;
		return result;
	}
}
