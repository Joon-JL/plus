package com.sds.secframework.util.service;

import java.util.HashMap;
import java.util.List;

import com.sds.secframework.util.dto.ZipVO;
import com.sds.secframework.common.util.PageUtil;

/**
 * 우편번호 조회
 * @author Jiyoung Han
 */
public interface ZipService {

	/**
	 * 우편번호 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	List listZipcode(HashMap hm) throws Exception;	
}
