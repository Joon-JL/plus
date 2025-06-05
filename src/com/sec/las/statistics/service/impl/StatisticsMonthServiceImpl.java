/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsMonthServiceImpl.java
 * Description	: 통계 월간업무 Service impl(concrete class)
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.las.statistics.dto.StatisticsMonthVO;
import com.sec.las.statistics.service.StatisticsMonthService;
/**
 * Description	: 통계 Service impl(concrete class)
 * Author 		: 서백진
 * Date			: 2011. 09. 08
 */

public class StatisticsMonthServiceImpl extends CommonServiceImpl implements StatisticsMonthService {
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	/**
	 * 월간업무(기타) 전체 목록을 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return List
	 * @throws Exception
	 */
	public List listStatistics(StatisticsMonthVO vo) throws Exception {
		return commonDAO.list("las.statisticsmonth.listStatistics", vo);
	}
	/**
	 * 월간업무(기타) 담당자 목록을 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return List
	 * @throws Exception
	 */
	public List listRespman(StatisticsMonthVO vo) throws Exception {
		return commonDAO.list("las.statisticsmonth.listRespman", vo);
	}	
	/**
	 * 월간업무(기타) 기본을 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	public ListOrderedMap detailStatisticsMonth(StatisticsMonthVO vo) throws Exception {
		List list = commonDAO.list("las.statisticsmonth.detailMonthduty", vo);
		ListOrderedMap lom = new ListOrderedMap();
		
		if(list!=null && list.size() != 0){
			lom = (ListOrderedMap)list.get(0);
		}

		return lom;			
	}	
	/**
	 * 월간업무(기타)를 등록한다.
	 * @param  vo StatisticsMonthVO
	 * @return int
	 * @throws Exception
	 */
	public int insertStatisticsMonth(StatisticsMonthVO vo) throws Exception {
		int seqno = idGenerationService.getNextIntegerId();
		vo.setSeqno(Integer.toString(seqno));
		int result = commonDAO.insert("las.statisticsmonth.insertMonthduty", vo);

		return result;			
	}
	
	/**
	 * 월간업무(기타)를 수정한다.
	 * @param  vo StatisticsMonthVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyStatisticsMonth(StatisticsMonthVO vo) throws Exception {
		int result = commonDAO.modify("las.statisticsmonth.modifyMonthduty", vo);

		return result;			
	}	
	
	/**
	 * 월간업무(중복)를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	public ListOrderedMap selectStatisticsMonth(StatisticsMonthVO vo) throws Exception {
		List list = commonDAO.list("las.statisticsmonth.selectStatistics", vo);
		ListOrderedMap lom = new ListOrderedMap();
		
		if(list!=null && list.size() != 0){
			lom = (ListOrderedMap)list.get(0);
		}

		return lom;			
	}
	
	/**
	 * 월간업무(계약)를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return List
	 * @throws Exception
	 */
	public List listStatisticsContract(StatisticsMonthVO vo) throws Exception {
		List list = commonDAO.list("las.statisticsmonth.listStatisticsContract", vo);
		return list;			
	}	
	/**
	 * 월간업무(자문)를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return List
	 * @throws Exception
	 */
	public List listStatisticsConsult(StatisticsMonthVO vo) throws Exception {
		List list = commonDAO.list("las.statisticsmonth.listStatisticsConsult", vo);
		
		return list;			
	}
	
	/**
	 * 월간업무(전체)를 수정한다.
	 * @param  vo StatisticsMonthVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyStatisticsMonthContract(StatisticsMonthVO vo) throws Exception {
		int result = 0;

		String[] seqnos	= vo.getSeqnos();	// update key
		String[] tabs	= vo.getTabs();		// update table
		String[] conts	= vo.getConts();	// update 내용
		String[] monthdutyyns	= vo.getMonthdutyyns();	// update 월간표시 여부
		if(seqnos !=null && seqnos.length>0) {
			for(int i=0; i<seqnos.length; i++) {

				String seq = StringUtil.bvl(seqnos[i],"");
				String[] key = seq.split("@");
				String[] key1 = tabs[i].split("@");
				String tab = key1[0].trim(); 
				String monthdutyyn = "";
				String cont = "";  
				if(monthdutyyns != null){
					monthdutyyn = monthdutyyns[i];
				}
				if(conts != null){
					cont = StringUtil.convertHtmlTochars(conts[i]); 
				}

				vo.setTab(tab);
				vo.setCont(cont);
				vo.setMonthdutyyn(monthdutyyn); 
				vo.setSeqno(seq);

				if(key1.length > 1)
					vo.setResult_yn(key1[1]);
				
				if(tab.equals("TN_LAS_CONSULT")){	
			    	vo.setCntrt_id(key[0]);
			    	vo.setCnsdreq_id(key[1]);					
				}else if(tab.equals("TN_LAS_SPEAKCONSULT")){  
				}else if(tab.equals("TN_LAS_SPEAKCONTRACT")){
				}else if(tab.equals("TN_CLM_CONTRACT_CNSD")){      
			     	vo.setCntrt_id(key[0]);
			    	vo.setCnsdreq_id(key[1]);
				}else if(tab.equals("TN_LAS_ETC_MONTHDUTY")){ 
				}

				result += commonDAO.modify("las.statisticsmonth.modifyMonthContract", vo);

			}
		}
		return result;			
	}	
}