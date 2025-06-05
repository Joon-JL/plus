package com.sds.secframework.user.service;


import java.util.HashMap;
import java.util.Locale;

import net.sf.json.JSONArray;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.user.dto.UserManagerVO;
import com.sec.clm.sign.dto.SignManageVO;


public interface UserManagerService {


	
	/**
	 * 사용자정보를 조회한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public HashMap listUserMng(UserManagerVO vo)throws Exception;
	
	/**
	 * 사용자정보를 TB_COM_USER에서 조회한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */	
//	public JSONArray SerchUserTable(UserManagerVO vo) throws Exception ;
	
	/**
	 * 사용자정보를 ESB에서 조회한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */	
	public JSONArray SerchUserEsb(UserManagerVO vo) throws Exception ;
	
	/**
	 * 사용자정보를 삭제 한다.
	 * @param locale 
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */		
	public String DeleteUser(UserManagerVO vo, Locale locale) throws Exception ;
	
	/**
	 * 사용자정보를 저장 한다.
	 * @param locale 
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */		
	public String SaveUserMng(UserManagerVO vo, Locale locale) throws Exception ;
	/**
	 * 사용자의 리스트 검색 ESB POPUP 타입
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray SerchEsbName(UserManagerVO vo) throws Exception;	
	
	/**
	 * 사용자정보를 ESB 에서 조회한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public HashMap SerchUserEsbInfo(UserManagerVO vo)throws Exception;

	/**
	 * 계약지정인정보를 조회한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public HashMap listApntUserMng(UserManagerVO vo)throws Exception;	
	
	/**
	 * 권한지정 정보를 저장한다.
	 * @param HashMap
	 * @return
	 * @throws Exception
	 */
	public String SaveApntUserMng(UserManagerVO vo) throws Exception;
	
	/**
	 * 날인담당자정보를 조회한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public HashMap listSignUserMng(UserManagerVO vo)throws Exception;	
	
	/**
	 * 증명서류발급자 정보를 조회한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public HashMap listSignDocUserMng(UserManagerVO vo)throws Exception;	
	
	/**
	 * 날인담당자 권한지정 정보를 저장한다.
	 * @param HashMap
	 * @return
	 * @throws Exception
	 */
	public int SaveSignUserMng(UserManagerVO vo,SignManageVO svo) throws Exception;
	
	/**
	 * 사용자정보를 조회 한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */		
	public UserManagerVO findUserByPk(String user_id) throws Exception ;
	
	/**
	 * 사용자정보를 갱신 한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */		
	public void updateUser(UserManagerVO vo) throws Exception ;

	public HashMap listUserAccess(UserManagerVO vo) throws Exception ;

	public int SaveUserAccess(UserManagerVO vo) throws Exception ;

	public int updateUser(HashMap userInfoMap) throws Exception ;

	public int insertUserInfo(HashMap userInfoMap) throws Exception ;

	public void insertRoleUser(AuthVO tvo) throws Exception ;

	public int InsertRolls(UserManagerVO vo, String[] rolls) throws Exception ;
}
