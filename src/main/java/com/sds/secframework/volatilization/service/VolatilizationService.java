package com.sds.secframework.volatilization.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;


public interface VolatilizationService {

	// 목록조회
	public String listTestDupConn() throws Exception;

	// 신규등록
	public int insertTestDupConn() throws Exception;
}
