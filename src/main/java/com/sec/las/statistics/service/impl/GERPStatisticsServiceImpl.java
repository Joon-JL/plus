package com.sec.las.statistics.service.impl;

import java.util.List;
import com.sec.las.statistics.dto.GERPStatisticsVO;
import com.sec.las.statistics.service.GERPStatisticsService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;

public class GERPStatisticsServiceImpl extends CommonServiceImpl implements GERPStatisticsService {

	public List GetGERPContractRateStatisticsData(GERPStatisticsVO vo) throws Exception {
		return commonDAO.list("las.statistics.gerpContractRateStatisticsDataList", vo);
	}
}
