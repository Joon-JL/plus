package com.sds.secframework.util.service.impl;

import java.util.HashMap;
import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.util.service.ZipService;

public class ZipServiceImpl extends CommonServiceImpl implements ZipService {
	
	/**
	 * 우편번호 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List listZipcode(HashMap hm) throws Exception {

		return  commonDAO.list("secframework.user.listZipcode", hm);

	}
}
