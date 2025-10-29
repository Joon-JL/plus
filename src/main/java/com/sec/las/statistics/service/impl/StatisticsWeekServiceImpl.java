/**
 * Project Name : 법무시스템 주간보고
 * File name	: StatisticsWeekServiceImpl.java
 * Description	: 통계 Service impl(concrete class)
 * Author		: 김재원
 * Date			: 2011. 09. 15
 * Modify		: 서백진
 * Date			: 2011. 10. 27
 * Copyright	:
 */
package com.sec.las.statistics.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.statistics.dto.StatisticsMonthVO;
import com.sec.las.statistics.dto.StatisticsWeekVO;
import com.sec.las.statistics.service.StatisticsWeekService;
/**
 * Description	: 통계 - 주간보고 Service impl(concrete class)
 * Author 		: 김재원
 * Date			: 2011. 09. 15
 */

public class StatisticsWeekServiceImpl extends CommonServiceImpl implements StatisticsWeekService {
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	/**
	 * 주간업무(계약)를 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return List
	 * @throws Exception
	 */
	public List listStatisticsContract(StatisticsWeekVO vo) throws Exception {
		List list = commonDAO.list("las.statisticsweek.listStatisticsWeekContract", vo);
		return list;			
	}	
	/**
	 * 주간업무(자문)를 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return List
	 * @throws Exception
	 */
	public List listStatisticsConsult(StatisticsWeekVO vo) throws Exception {
		List list = commonDAO.list("las.statisticsweek.listStatisticsWeekConsult", vo);
		
		return list;			
	}	
	/**
	 * 주간업무(기타) 담당자 목록을 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return List
	 * @throws Exception
	 */
	public List listRespman(StatisticsWeekVO vo) throws Exception {
		return commonDAO.list("las.statisticsweek.listRespman", vo);
	}
	
	/**
	 * 주간간업무 전체 목록을 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return List
	 * @throws Exception
	 */
	public List listStatistics(StatisticsWeekVO vo) throws Exception {
		return commonDAO.list("las.statisticsweek.listStatisticsTotal", vo);
	}

	/**
	 * 주간간업무(기타) 기본을 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	public ListOrderedMap detailStatisticsWeek(StatisticsWeekVO vo) throws Exception {
		List list = commonDAO.list("las.statisticsweek.listStatistics", vo);
		ListOrderedMap lom = new ListOrderedMap();
		
		if(list!=null && list.size() != 0){
			lom = (ListOrderedMap)list.get(0);
		}

		return lom;			
	}	
	/**
	 * 주간업무(기타) 수정한다.
	 * @param  vo StatisticsWeekVO
	 * @return List
	 * @throws Exception
	 */
	public int modifyStatisticsWeek(StatisticsWeekVO vo) throws Exception {
		
		int result = 0 ;
		
		result = commonDAO.modify("las.statisticsweek.modifyStatisticsWeek", vo);
		
		
		return result;
	}
	/**
	 * 주간업무(기타)를 등록한다.
	 * @param  vo StatisticsWeekVO
	 * @return int
	 * @throws Exception
	 */
	public int insertStatisticsWeek(StatisticsWeekVO vo) throws Exception {
		int seqno = idGenerationService.getNextIntegerId();
		vo.setSeqno(Integer.toString(seqno));
		int result = commonDAO.insert("las.statisticsweek.insertWeekduty", vo);

		return result;			
	}	
	/**
	 * 주간업무(전체)를 수정한다.
	 * @param  vo StatisticsWeekVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyStatisticsWeekContract(StatisticsWeekVO vo) throws Exception {
		int result = 0;

		String[] seqnos	= vo.getSeqnos();	// update key
		String[] tabs	= vo.getTabs();		// update table
		String[] conts	= vo.getConts();	// update 내용
		String[] weekdutyyns	= vo.getWeekdutyyns();	// update 주간표시 여부
		if(seqnos !=null && seqnos.length>0) {
			for(int i=0; i<seqnos.length; i++) {
				String seq = StringUtil.bvl(seqnos[i],"");
				String[] key = seq.split("@");
				String[] key1 = tabs[i].split("@");
				String tab = key1[0].trim(); 
				String weekdutyyn = "";
				String cont = "";  
				if(weekdutyyns != null){
					weekdutyyn = weekdutyyns[i];
				}
				if(conts != null){
					cont = StringUtil.convertHtmlTochars(conts[i]); 
				}

				vo.setTab(tab);
				vo.setCont(cont);
				vo.setWeekdutyyn(weekdutyyn); 
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
				}else if(tab.equals("TN_LAS_ETC_WEEKDUTY")){ 
				}
				
				result += commonDAO.modify("las.statisticsweek.modifyWeekContract", vo);

			}
		}
		return result;			
	}	
}