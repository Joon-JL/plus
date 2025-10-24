package com.sds.secframework.auth.service;

import java.util.List;
import java.util.Map;

import com.sds.secframework.auth.dto.ClassMethodRoleVO;

public interface ClassMethodRoleService {
	/**
	 * 클래스 메소드의 Role 리스트를 가져온다.
	 * @param vo ClassMethodAuthVO
	 * @return
	 * @throws Exception
	 */
	public List getClassMethodRoleList(ClassMethodRoleVO vo) throws Exception ;
	
	public List listPage(ClassMethodRoleVO vo) throws Exception ;
	
	public int insert(ClassMethodRoleVO vo) throws Exception ;
	
	public int insertRole(ClassMethodRoleVO vo) throws Exception;
	
	public int delete(ClassMethodRoleVO vo) throws Exception ;
	
	public int deleteMulti(ClassMethodRoleVO vo) throws Exception ;
	
	public int modify(ClassMethodRoleVO vo) throws Exception ;
	
	public Map detail(ClassMethodRoleVO vo) throws Exception ;
	
	public int deleteRole(ClassMethodRoleVO vo) throws Exception ;
	
	public List listRole(ClassMethodRoleVO vo) throws Exception ;
	
	public List listNotRole(ClassMethodRoleVO vo) throws Exception ;
}
