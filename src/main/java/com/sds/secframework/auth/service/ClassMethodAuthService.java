package com.sds.secframework.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sds.secframework.auth.dto.ClassMethodAuthVO;

public interface ClassMethodAuthService {
	/**
	 * 클래스 메소드의 Role 리스트를 가져온다.
	 * @param vo ClassMethodAuthVO
	 * @return
	 * @throws Exception
	 */
	public List getClassMethodRoleList(ClassMethodAuthVO vo) throws Exception ;
	
	public List getClassMethodAuthList(ClassMethodAuthVO vo) throws Exception ;
	
	public List listPage(ClassMethodAuthVO vo) throws Exception ;
	
	public int insert(ClassMethodAuthVO vo) throws Exception ;
	
	public int insertRole(ClassMethodAuthVO vo) throws Exception;
	
	public int delete(ClassMethodAuthVO vo) throws Exception ;
	
	public int deleteMulti(ClassMethodAuthVO vo) throws Exception ;
	
	public int modify(ClassMethodAuthVO vo) throws Exception ;
	
	public Map detail(ClassMethodAuthVO vo) throws Exception ;
	
	public int deleteRole(ClassMethodAuthVO vo) throws Exception ;
	
	public List listRole(ClassMethodAuthVO vo) throws Exception ;
	
	public List listNotRole(ClassMethodAuthVO vo) throws Exception ;
	
	public int insertLogData(HashMap hm) throws Exception ;
}
