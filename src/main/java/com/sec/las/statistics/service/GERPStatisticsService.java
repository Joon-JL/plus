package com.sec.las.statistics.service;

import java.util.List;

import com.sec.las.statistics.dto.GERPStatisticsVO;

public interface GERPStatisticsService {

	List GetGERPContractRateStatisticsData(GERPStatisticsVO vo)  throws Exception;
	
}
