package com.sds.secframework.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.dept.dto.DeptVO;
import com.sds.secframework.user.dto.UserVO;

/**
 * 임직원 조회
 * @author Jiyoung Han
 */
public interface UserService {
	
	/**
	 * 사용자 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List getUserInfo(HashMap hm) throws Exception;
	
	/**
	 * 사용자 정보 등록(ESB 정보)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	int insertUserInfo(String authCompCd, String epid) throws Exception;
	
	/**
	 * 사용자 정보 등록(ESB 정보)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	int insertUserInfo(String userId) throws Exception;

	/**
	 * 사용자 정보 수정(ESB 정보)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	int modifyUserInfo(String authCompCd, String epid) throws Exception;
	
	/**
	 * 사용자 정보 검증
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	//List getClmsUserInfo(String epid, String sys_cd, String adminEpId) throws Exception;
	List getClmsUserInfo(String epid, String sysCd) throws Exception;
	
	List processClmsUserInfo(String epid, String sys_cd, String gubun) throws Exception;
	
	/**
	 * Locale 변경
	 * @param
	 * @return
	 * @throws Exception
	 */
	int changeLocale(HashMap hm) throws Exception;

	/* 사용자 기본역활 여부 조회
	 */	
	List existsRoleUser(AuthVO vo) throws Exception;

	/* 역할별사용자 등록
	 */	
	int insertRoleUser(AuthVO vo) throws Exception;		

	/**
	 * 임직원 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	List listUser(UserVO vo) throws Exception;
	
	/**
	 * 임직원의 역활리스트 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List listUserRole(HashMap hm) throws Exception;
	
	/**
	 * 임직원의 권한리스트 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List listUserAuth(HashMap hm) throws Exception;

	/**
	 * 소속지역 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public HashMap getRegionInfo(HashMap hm) throws Exception;

	/**
	 * 부서 클릭시 인력 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	JSONArray listUserByDeptCd(DeptVO vo) throws Exception;
	
	/**
	 * 상위부서조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	List listUpDeptInfo(HashMap hm) throws Exception;
	
	/**
	 * 소속조직코드 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	HashMap listOrgnzList(HashMap hm) throws Exception;
	HashMap searchOrgnz(HashMap hm) throws Exception;
	
	/**
	 * 소속조직코드로 소속조직명 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	HashMap listOrgnzName(HashMap hm) throws Exception;

	/**
	 *  조직장 여부 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	List listManagerYNList(HashMap hm) throws Exception;
	
	/**
	 *  지원부서 여부 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	List listSupportYNList(HashMap hm) throws Exception;	

	/**
	 *  사용자 전체 리스트
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	List listTotalUser(UserVO vo) throws Exception;	

	
	/**
	 * 개인정보활용 동의 업데이트 
	 * @param user_id
	 * @throws Exception
	 */
	public void upateGthrCnstYn(String user_id) throws Exception;
	
	/**
	 *  계약연계테이블 정보 조회
	 *  sys_nm과 key_id로 계약번호 찾기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	HashMap getCnsdReqId(HashMap hm) throws Exception;	
	
	/**
	 *  계약연계테이블 정보 조회
	 *  cntrt_id로 의뢰번호 찾기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	HashMap getCnsdReqIdByCntrtID(HashMap hm) throws Exception;	

	/**
	 *  계약연계테이블 정보 조회
	 *  cntrt_no로 cntrt_id 찾기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	HashMap getCntrtIDByCntrtNO(HashMap hm) throws Exception;

	/**
	 * 자회사인지 여부 체크(로그인 시 12개 회사인지 판단함과 동시에 각 회사의 약어를 가지고 온다.)
	 * <p>
	 * @param comp_cd 회사코드
	 * @return 
	 * @throws Exception
	 */
	List isExist_comp_cd(String comp_cd, String user_id) throws Exception;
	
	/**
	 * 해당 사용자가 전자검토자인지 여부를 체크하여 전자검토자이면 관리하는 회사코드 목록을 조회
	 * <p>
	 * @param user_id 사용자ID
	 * @return
	 * @throws Exception
	 */
	List getMngComps(HashMap hm) throws Exception;

	/**
	 * 계약지정인인인지 여부 조회.  이 메서드를 호출하는 경우는 EpTray에서 회사코드가 C10인
	 * 경우에만 조회한다.
	 * <p>
	 * @param hMap HashMap
	 * @return
	 * @throws Exception
	 */
	HashMap getAuthApntYn(HashMap<String, String> hMap) throws Exception;

	/**
	 * 시스템에 사용되는 자회사 회사코드 목록을 조회
	 * <p>
	 * @return
	 * @throws Exception
	 */
	List getMngComps() throws Exception;
	
	/**
	 * 해당 사용자가 전자임원의 Role을 가졌는지 여부를 체크
	 * <p>
	 * @param hMap HashMap
	 * @return
	 * @throws Exception
	 */
	List getUserSecRole(HashMap<String, String> hMap) throws Exception;

	String getAccessYn(String userId) throws Exception;

	int requestAccess(UserVO vo) throws Exception;	
	
	/**
	 * 기존 구주 selms 사용자 조회
	 * <p>
	 * @param hMap HashMap
	 * @return
	 * @throws Exception
	 */
	List listOldUser(HashMap<String, String> hMap) throws Exception;

	/**
	 * 기존 구주 selms 사용자 처리후 상태 업데이트
	 * <p>
	 * @param hMap HashMap
	 * @return
	 * @throws Exception
	 */
	int updateMigStatus(AuthVO vo) throws Exception;
	
	/**
	 * 기존 구주 selms Ecms사용자 처리후 상태 업데이트
	 * <p>
	 * @param hMap HashMap
	 * @return
	 * @throws Exception
	 */
	int updateEcmsMigStatus(AuthVO vo) throws Exception;
	
	/**
	 * 사용자 조회
	 * <p>
	 * @param hMap HashMap
	 * @return
	 * @throws Exception
	 */
	List listUserUpdJikgubByESB(HashMap<String, String> hMap) throws Exception;
	
	/**
	 * 사용자정보 직급 업데이트 처리 -ESB
	 * <p>
	 * @param hMap HashMap
	 * @return
	 * @throws Exception
	 */
	int updUserUpdJikgubByESB(AuthVO vo) throws Exception;		

	/* FERNANDO (MFA project) Jan/2024 - start */
	boolean getUserOTP(String sys_cd, String user_id) throws Exception;
	/* FERNANDO (MFA project) Jan/2024 - end */
}
