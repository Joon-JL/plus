package com.sds.secframework.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import model.outldap.samsung.net.Employee;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.dept.dto.DeptVO;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.dto.UserVO;
import com.sds.secframework.user.service.UserService;
import com.sec.common.util.ClmsDataUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class UserServiceImpl extends CommonServiceImpl implements UserService {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}

	/**
	 * 사용자 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List getUserInfo(HashMap hm) throws Exception {
		return commonDAO.list("secfw.user.getUserInfo", hm);
	}
	
	/**
	 * 사용자 정보 등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public int insertUserInfo(String compCd, String epid) throws Exception {
		int result = -1;
		
		// !@# ESB인터페이스 변경 (authCompCd 추가) 2013.04.25
		Employee employee[] = esbOrgService.userSearchByEpid(epid);
		Employee userInfo = employee[0];

		//사용자 정보
		HashMap userInfoMap = new HashMap();
		
		userInfoMap.put("user_id", StringUtil.bvl(userInfo.getEpid(), ""));                     //사용자아이디
		//userInfoMap.put("res_no", StringUtil.bvl(userInfo.getEpregisternumber(), ""));          //주민번호
		userInfoMap.put("res_no", "");          //주민번호
		userInfoMap.put("emp_no", StringUtil.bvl(userInfo.getEmployeenumber(), ""));            //사번
		userInfoMap.put("user_nm", StringUtil.bvl(userInfo.getCn(), ""));                       //성명
		userInfoMap.put("user_real_nm", StringUtil.bvl(userInfo.getCn(), ""));                  //실제성명
		userInfoMap.put("user_nm_eng", StringUtil.bvl(userInfo.getEpengivenname(), ""));        //영문성명
		userInfoMap.put("user_real_nm_eng", StringUtil.bvl(userInfo.getEpengivenname(), ""));   //실제영문성명
		userInfoMap.put("single_id", StringUtil.bvl(userInfo.getUserid(), ""));                 //싱글아이디
		userInfoMap.put("single_epid", StringUtil.bvl(userInfo.getEpid(), ""));                 //싱글EPID
		userInfoMap.put("comp_cd", StringUtil.bvl(userInfo.getEporganizationnumber(), ""));     //회사코드
		//userInfoMap.put("comp_nm", StringUtil.bvl(userInfo.getO(), ""));                        //회사명
		//userInfoMap.put("comp_nm_eng", StringUtil.bvl(userInfo.getEpenorganizationname(), "")); //회사영문명
		userInfoMap.put("business_cd", StringUtil.bvl(userInfo.getEpsuborgcode(), ""));         //총괄코드
		userInfoMap.put("business_nm", StringUtil.bvl(userInfo.getEpsuborgname(), ""));         //총괄명
		userInfoMap.put("business_nm_eng", StringUtil.bvl(userInfo.getEpensuborgname(), ""));   //총괄영문명
		userInfoMap.put("division_cd", StringUtil.bvl(userInfo.getEpbusicode(), ""));           //사업장코드
		userInfoMap.put("division_nm", StringUtil.bvl(userInfo.getEpbusiname(), ""));           //사업장명
		userInfoMap.put("dept_cd", StringUtil.bvl(userInfo.getDepartmentnumber(), ""));         //부서코드
		userInfoMap.put("in_dept_cd", StringUtil.bvl(userInfo.getEpindeptcode(), ""));          //내부부서코드
		userInfoMap.put("dept_nm", StringUtil.bvl(userInfo.getDepartment(), ""));               //부서명
		userInfoMap.put("dept_nm_eng", StringUtil.bvl(userInfo.getEpendepartment(), ""));       //부서영문명
		userInfoMap.put("jikgup_cd", StringUtil.bvl(userInfo.getEptitlenumber(), ""));          //직급코드
		userInfoMap.put("jikgup_nm", StringUtil.bvl(userInfo.getTitle(), ""));                  //직급명
		userInfoMap.put("jikgup_nm_eng", StringUtil.bvl(userInfo.getEpentitle(), ""));          //직급영문명
		userInfoMap.put("jikmu_cd", StringUtil.bvl(userInfo.getEpjob(), ""));                   //직무코드
		userInfoMap.put("jikmu_nm", StringUtil.bvl(userInfo.getEpjobname(), ""));               //직무명
		userInfoMap.put("region_cd", StringUtil.bvl(userInfo.getEpregioncode(), ""));           //지역코드
		userInfoMap.put("region_nm", StringUtil.bvl(userInfo.getEpregionname(), ""));           //지역명
		userInfoMap.put("region_nm_eng", StringUtil.bvl(userInfo.getEpenregionname(), ""));     //영문지역명
		userInfoMap.put("email", StringUtil.bvl(userInfo.getMail(), ""));                       //이메일
		userInfoMap.put("office_tel_no", StringUtil.bvl(userInfo.getTelephonenumber(), ""));    //회사전화번호 //2012-02-01 법무팀 이효은 대리 유청으로 수집함
		//userInfoMap.put("home_tel_no", StringUtil.bvl(userInfo.getHomephone(), ""));            //집전화번호
		//userInfoMap.put("mobile_no", StringUtil.bvl(userInfo.getMobile(), ""));                 //핸드폰번호
		userInfoMap.put("home_tel_no", "");      //집전화번호
		userInfoMap.put("mobile_no", "");        //핸드폰번호
		userInfoMap.put("status", StringUtil.bvl(userInfo.getEpuserstatus(), "B"));              //재직상태
		userInfoMap.put("employee_type", StringUtil.bvl(userInfo.getEmployeetype(), ""));       //사용자타입
		userInfoMap.put("user_level", StringUtil.bvl(userInfo.getEpsecuritylevel(), ""));       //보안레벨   

		result = commonDAO.modify("clms.user.insertUserInfo", userInfoMap);
		
		return result;
	}
	
	
	/**
	 * 사용자 정보 등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public int insertUserInfo(String epid) throws Exception {
		int result = -1;
		
		// !@# ESB인터페이스 변경 (authCompCd 추가) 2013.04.25
		Employee employee[] = esbOrgService.userSearchByEpid(epid);
		
		
		
		
		if(employee.length > 0 ){
			
			Employee userInfo = employee[0];
			
			
			//사용자 정보
			HashMap userInfoMap = new HashMap();
			
			userInfoMap.put("user_id", StringUtil.bvl(userInfo.getEpid(), ""));                     //사용자아이디
			//userInfoMap.put("res_no", StringUtil.bvl(userInfo.getEpregisternumber(), ""));          //주민번호
			userInfoMap.put("res_no", "");          //주민번호
			userInfoMap.put("emp_no", StringUtil.bvl(userInfo.getEmployeenumber(), ""));            //사번
			userInfoMap.put("user_nm", StringUtil.bvl(userInfo.getCn(), ""));                       //성명
			userInfoMap.put("user_real_nm", StringUtil.bvl(userInfo.getCn(), ""));                  //실제성명
			userInfoMap.put("user_nm_eng", StringUtil.bvl(userInfo.getEpengivenname(), ""));        //영문성명
			userInfoMap.put("user_real_nm_eng", StringUtil.bvl(userInfo.getEpengivenname(), ""));   //실제영문성명
			userInfoMap.put("single_id", StringUtil.bvl(userInfo.getUserid(), ""));                 //싱글아이디
			userInfoMap.put("single_epid", StringUtil.bvl(userInfo.getEpid(), ""));                 //싱글EPID
			userInfoMap.put("comp_cd", StringUtil.bvl(userInfo.getEporganizationnumber(), ""));     //회사코드
			//userInfoMap.put("comp_nm", StringUtil.bvl(userInfo.getO(), ""));                        //회사명
			//userInfoMap.put("comp_nm_eng", StringUtil.bvl(userInfo.getEpenorganizationname(), "")); //회사영문명
			userInfoMap.put("business_cd", StringUtil.bvl(userInfo.getEpsuborgcode(), ""));         //총괄코드
			userInfoMap.put("business_nm", StringUtil.bvl(userInfo.getEpsuborgname(), ""));         //총괄명
			userInfoMap.put("business_nm_eng", StringUtil.bvl(userInfo.getEpensuborgname(), ""));   //총괄영문명
			userInfoMap.put("division_cd", StringUtil.bvl(userInfo.getEpbusicode(), ""));           //사업장코드
			userInfoMap.put("division_nm", StringUtil.bvl(userInfo.getEpbusiname(), ""));           //사업장명
			userInfoMap.put("dept_cd", StringUtil.bvl(userInfo.getDepartmentnumber(), ""));         //부서코드
			userInfoMap.put("in_dept_cd", StringUtil.bvl(userInfo.getEpindeptcode(), ""));          //내부부서코드
			userInfoMap.put("dept_nm", StringUtil.bvl(userInfo.getDepartment(), ""));               //부서명
			userInfoMap.put("dept_nm_eng", StringUtil.bvl(userInfo.getEpendepartment(), ""));       //부서영문명
			userInfoMap.put("jikgup_cd", StringUtil.bvl(userInfo.getEptitlenumber(), ""));          //직급코드
			userInfoMap.put("jikgup_nm", StringUtil.bvl(userInfo.getTitle(), ""));                  //직급명
			userInfoMap.put("jikgup_nm_eng", StringUtil.bvl(userInfo.getEpentitle(), ""));          //직급영문명
			userInfoMap.put("jikmu_cd", StringUtil.bvl(userInfo.getEpjob(), ""));                   //직무코드
			userInfoMap.put("jikmu_nm", StringUtil.bvl(userInfo.getEpjobname(), ""));               //직무명
			userInfoMap.put("region_cd", StringUtil.bvl(userInfo.getEpregioncode(), ""));           //지역코드
			userInfoMap.put("region_nm", StringUtil.bvl(userInfo.getEpregionname(), ""));           //지역명
			userInfoMap.put("region_nm_eng", StringUtil.bvl(userInfo.getEpenregionname(), ""));     //영문지역명
			userInfoMap.put("email", StringUtil.bvl(userInfo.getMail(), ""));                       //이메일
			userInfoMap.put("office_tel_no", StringUtil.bvl(userInfo.getTelephonenumber(), ""));    //회사전화번호 //2012-02-01 법무팀 이효은 대리 유청으로 수집함
			//userInfoMap.put("home_tel_no", StringUtil.bvl(userInfo.getHomephone(), ""));            //집전화번호
			//userInfoMap.put("mobile_no", StringUtil.bvl(userInfo.getMobile(), ""));                 //핸드폰번호
			userInfoMap.put("home_tel_no", "");      //집전화번호
			userInfoMap.put("mobile_no", "");        //핸드폰번호
			userInfoMap.put("status", StringUtil.bvl(userInfo.getEpuserstatus(), "B"));              //재직상태
			userInfoMap.put("employee_type", StringUtil.bvl(userInfo.getEmployeetype(), ""));       //사용자타입
			userInfoMap.put("user_level", StringUtil.bvl(userInfo.getEpsecuritylevel(), ""));       //보안레벨   

			result = commonDAO.modify("clms.user.insertUserInfo", userInfoMap);
			
			
			// TB_COM_ROLE_USER 테이블에 insert
			AuthVO vo = new AuthVO();
			
			vo.setSys_cd("LAS");
			vo.setRole_cd("RZ00");
			vo.setUser_id(epid);
			
			insertRoleUser(vo);
			
			
		} else {
			
			String content = epid+",";
            String fileName = "C:/epid.txt";
            
            File file = new File(fileName);
            
            if(fileName.length()==0){
                   try{
                          FileWriter file_writer = new FileWriter(file);
                          BufferedWriter buff_writer = new BufferedWriter(file_writer);
                          PrintWriter print_writer = new PrintWriter(buff_writer,true);
                          print_writer.println(content);
                         
                          if(print_writer.checkError()){
                        	  
                                
                          }
                         
                          file.createNewFile();
                         
                   } catch (Exception e) {
                          // TODO: handle exception
                          e.printStackTrace();
                   }
            } else {  //이어쓰기
            
                   BufferedWriter buff_writer = new BufferedWriter(new FileWriter(file, true));
                   PrintWriter print_writer = new PrintWriter(buff_writer,true);
                   print_writer.println(content);
                  
                   if(print_writer.checkError()) {
                	   
                   }
                   
            }
            

			
		}
		
		

		
		
//		if(employee.length <1){
//			
//		}
		
		return result;
	}

	/**
	 * 사용자 정보 수정
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public int modifyUserInfo(String compCd, String epid) throws Exception {
		
		int result = -1;
		
		// !@# ESB인터페이스 변경 (authCompCd 추가) 2013.04.25
		Employee employee[] = esbOrgService.userSearchByEpid(epid);
		Employee userInfo = employee[0];

		//사용자 정보
		HashMap userInfoMap = new HashMap();
		
		userInfoMap.put("user_id", StringUtil.bvl(userInfo.getEpid(), ""));                     //사용자아이디
		//userInfoMap.put("res_no", StringUtil.bvl(userInfo.getEpregisternumber(), ""));          //주민번호
		userInfoMap.put("res_no", "");          //주민번호
		userInfoMap.put("emp_no", StringUtil.bvl(userInfo.getEmployeenumber(), ""));            //사번
		userInfoMap.put("user_nm", StringUtil.bvl(userInfo.getCn(), ""));                       //성명
		userInfoMap.put("user_real_nm", StringUtil.bvl(userInfo.getCn(), ""));                  //실제성명
		userInfoMap.put("user_nm_eng", StringUtil.bvl(userInfo.getEpengivenname(), ""));        //영문성명
		userInfoMap.put("user_real_nm_eng", StringUtil.bvl(userInfo.getEpengivenname(), ""));   //실제영문성명
		userInfoMap.put("single_id", StringUtil.bvl(userInfo.getUserid(), ""));                 //싱글아이디
		userInfoMap.put("single_epid", StringUtil.bvl(userInfo.getEpid(), ""));                 //싱글EPID
		userInfoMap.put("comp_cd", StringUtil.bvl(compCd, ""));     //회사코드
		//userInfoMap.put("comp_nm", StringUtil.bvl(userInfo.getO(), ""));                        //회사명
		//userInfoMap.put("comp_nm_eng", StringUtil.bvl(userInfo.getEpenorganizationname(), "")); //회사영문명
		userInfoMap.put("business_cd", StringUtil.bvl(userInfo.getEpsuborgcode(), ""));         //총괄코드
		userInfoMap.put("business_nm", StringUtil.bvl(userInfo.getEpsuborgname(), ""));         //총괄명
		userInfoMap.put("business_nm_eng", StringUtil.bvl(userInfo.getEpensuborgname(), ""));   //총괄영문명
		userInfoMap.put("division_cd", StringUtil.bvl(userInfo.getEpbusicode(), ""));           //사업장코드
		userInfoMap.put("division_nm", StringUtil.bvl(userInfo.getEpbusiname(), ""));           //사업장명
		userInfoMap.put("dept_cd", StringUtil.bvl(userInfo.getDepartmentnumber(), ""));         //부서코드
		userInfoMap.put("in_dept_cd", StringUtil.bvl(userInfo.getEpindeptcode(), ""));          //내부부서코드
		userInfoMap.put("dept_nm", StringUtil.bvl(userInfo.getDepartment(), ""));               //부서명
		userInfoMap.put("dept_nm_eng", StringUtil.bvl(userInfo.getEpendepartment(), ""));       //부서영문명
		userInfoMap.put("jikgup_cd", StringUtil.bvl(userInfo.getEptitlenumber(), ""));          //직급코드
		userInfoMap.put("jikgup_nm", StringUtil.bvl(userInfo.getTitle(), ""));                  //직급명
		userInfoMap.put("jikgup_nm_eng", StringUtil.bvl(userInfo.getEpentitle(), ""));          //직급영문명
		userInfoMap.put("jikmu_cd", StringUtil.bvl(userInfo.getEpjob(), ""));                   //직무코드
		userInfoMap.put("jikmu_nm", StringUtil.bvl(userInfo.getEpjobname(), ""));               //직무명
		userInfoMap.put("region_cd", StringUtil.bvl(userInfo.getEpregioncode(), ""));           //지역코드
		userInfoMap.put("region_nm", StringUtil.bvl(userInfo.getEpregionname(), ""));           //지역명
		userInfoMap.put("region_nm_eng", StringUtil.bvl(userInfo.getEpenregionname(), ""));     //영문지역명
		userInfoMap.put("email", StringUtil.bvl(userInfo.getMail(), ""));                       //이메일
		userInfoMap.put("office_tel_no", StringUtil.bvl(userInfo.getTelephonenumber(), ""));    //회사전화번호 //2012-02-01 법무팀 이효은 대리 요청으로 수집함
		//userInfoMap.put("home_tel_no", StringUtil.bvl(userInfo.getHomephone(), ""));            //집전화번호 
		//userInfoMap.put("mobile_no", StringUtil.bvl(userInfo.getMobile(), ""));                 //핸드폰번호
		userInfoMap.put("home_tel_no", ""); 	//집전화번호
		userInfoMap.put("mobile_no", "");       //핸드폰번호
		userInfoMap.put("status", StringUtil.bvl(userInfo.getEpuserstatus(), "B"));              //재직상태
		userInfoMap.put("employee_type", StringUtil.bvl(userInfo.getEmployeetype(), ""));       //사용자타입
		userInfoMap.put("user_level", StringUtil.bvl(userInfo.getEpsecuritylevel(), ""));       //보안레벨  
		
		commonDAO.modify("clms.user.modifyUserInfo", userInfoMap);
		
		return result;
	}

	/**
	 * 사용자 정보 검증 후 사용자 정보 리턴
	 * @param 
	 * @return 
	 * @throws Exception
	 */
//	public List getClmsUserInfo(String epid, String sys_cd, String adminEpId) throws Exception {
//		List result = null;
//
//		String compCd     = "";
//		String userStatus = "";
//		
//		String employeeType = "";
//		
//		boolean processFlag = true;
//		StringTokenizer st = new StringTokenizer(adminEpId, ",");
//		while(st.hasMoreTokens()) { //admin이 아닌 경우만 조회
//			if(epid.equals(st.nextToken())) { 
//				processFlag = false;
//				break;
//			}
//		}
//		
//		try {
//			if(processFlag){
//				Employee employee[] = esbOrgService.userSearchByEpid(epid);
//				Employee userInfo = employee[0];
//				
//				compCd       = StringUtil.bvl(userInfo.getEporganizationnumber(), ""); //회사코드
//				userStatus   = StringUtil.bvl(userInfo.getEpuserstatus(), "B");        //재직상태
//			}
//		} catch(Exception e) {}
//			
//		try {	
//			// 사용자 테이블에 있으면 TRUE.
//			HashMap hm = new HashMap();
//			hm.put("user_id", epid);
//			
//			List userInfoList = getUserInfo(hm);
//	
//			getLogger().debug("USER_ID=" + userInfoList.size());
//			
//			//사용자 정보가 있으면
//			if(userInfoList != null && userInfoList.size() > 0 && !"R".equals(userStatus)) {
//
//				// 삼성전자 직원 중 재직/휴직/파견신청인  사원만 접근가능 : 퇴사는 불가함. SDS 및 개발자들은 Skip!
//				if("C10".equals(compCd)){	
//
//					//TODO - 일단 기존에 테스트를 위해 셋팅된 사용자들을 위해 update는 하지 않는다. 완전 오픈시 update한다.
//					/*
//					//role_cd 가 RA00,RA01,RA02,RA03,RC01 인 사용자는 TB_COM_USER 정보를 업데이트 하지 않는다.
//					boolean isUpdate = getUserRoleInfo(sys_cd, epid);
//
//					if(!isUpdate){
//						//사용자 정보 업데이트
//						try { modifyUserInfo(epid); } catch(Exception e) { e.printStackTrace();	}
//					}
//					*/
//				}
//				
//				result = getUserInfo(hm);
//							
//			} else {
//				// 삼성전자 직원 중 재직/휴직/파견신청인  사원만 접근가능 : 퇴사는 불가함. SDS 및 개발자들은 Skip!
//				if( "C10".equals(compCd) && !"R".equals(userStatus)){ //R : 퇴사
//					
//					//사용자 정보 입력
//					try { insertUserInfo(epid); } catch(Exception e) { e.printStackTrace();	}
//
//				}
//				
//				result = getUserInfo(hm);				
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			result = null;
//		}
//		
//		return result;		
//	}
	
	/**
	 * 사용자의 role이 RA00,RA01,RA02,RM00,RD01 인지 확인
	 * <p>
	 * @param String sys_cd
	 * @param String user_id
	 * @return 사용자의  role이 RA00,RA01,RA02,RM00,RD01 이면 true, 아니면 false
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getUserRoleInfo(String sys_cd, String user_id) throws Exception{
		boolean retValue = false;

		HashMap hm = new HashMap();
		hm.put("sys_cd", sys_cd);
		hm.put("user_id", user_id);
		List userRoleList = commonDAO.list("secfw.user.getUserRoleInfo", hm);
		
		if(userRoleList != null && userRoleList.size() > 0){
			retValue = true;
		}
		
		return retValue;
	}
	
	public List getClmsUserInfo(String epid, String sysCd) throws Exception {
		List userInfoList = null;

		try {	
			// 사용자 테이블에 있으면 TRUE.
			HashMap hm = new HashMap();
			hm.put("user_id", epid);
			hm.put("sys_cd", sysCd);
			
			userInfoList = getUserInfo(hm);
			
			//사용자 정보가 있으면
			if(userInfoList == null && userInfoList.size() <= 0) 
				throw new Exception("##### getClmsUserInfo #####");
			
		} catch(Exception e) {
			e.printStackTrace();
			userInfoList = null;
		}
		
		return userInfoList;		
	}
	
	
	//로그인시 ESB및 insert/update 제외되는 사람 리스트(SDS운영 및 TF인원)
	// true이면 로그인 업데이트 및 인서트 로직 제외
	public boolean getClmsExceptUserInfo(String epid) throws Exception{
		List userInfoList = null;
		boolean retValue = false;
		
		try{
			// 사용자 테이블에 있으면 TRUE.
			HashMap hm = new HashMap();
			hm.put("user_id", epid);
			
			userInfoList = commonDAO.list("secfw.user.getExceptUserInfo", hm);
			if(userInfoList != null && userInfoList.size() > 0){
				retValue = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			retValue = false;
		}
		
		return retValue;
	}
	
	public String getUserCompInfo(String epid) throws Exception{
		List userInfoList = null;
		String compCd = "";
		try{
			HashMap hm = new HashMap();
			hm.put("user_id", epid);
			
			userInfoList = commonDAO.list("secfw.user.getUserCompInfo", hm);
			if(userInfoList != null && userInfoList.size() > 0){
				ListOrderedMap returnLom = (ListOrderedMap)userInfoList.get(0);			 
				compCd = (String)returnLom.get("comp_cd");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return compCd;
	}
	
	/**
	 * 사용자 정보 검증 후 리턴
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List processClmsUserInfo(String epid, String sys_cd, String gubun) throws Exception {
		List result = null;

		String compCd     = "";
		String traycompCd = "";
		String userStatus = "";
		String mail       = "";
		String mailPass   = "N";

		// 로그인로직 제외여부 - true이면 제외
		boolean exceptFlag = getClmsExceptUserInfo(epid);
		
		try {
			// 회사코드(지법인)
			compCd = getUserCompInfo(epid);
			
			// 로그인로직 제외자는 esb조회를 할 필요가 없다!
			if (!exceptFlag) {
				
				// !@# ESB인터페이스 변경 (authCompCd 추가) 2013.04.25
				Employee employee[] = esbOrgService.userSearchByEpid(epid); // 사용자 정보 ESB 조회
				Employee userInfo = employee[0];
				
				traycompCd = StringUtil.bvl(userInfo.getEporganizationnumber(), ""); // 회사코드
				userStatus = StringUtil.bvl(userInfo.getEpuserstatus(), "B");        // 재직상태
				mail       = StringUtil.bvl(userInfo.getMail(), "");                 // 메일
				
				if ("".equals(mail)) { // 메일이 없으면 로그인 불가!
					mailPass = "N";
				} else {
					/* 메일 주소 비교*/
					/*
					String mailIndex = "";
					
					if ("OPER".equals(propertyService.getProperty("secfw.server.division"))) {
						mailIndex = "@samsung.com";
					} else {
						//개발기에서는 어짜피 @stage.samsung.com 으로 조회된다. 
						//수동으로 넣어놓은 데이터들이 많으므로 그냥 samsung.com으로 끝나는 사람들은 통과시키기로 한다.
						// ex) @stage.samsung.com, @samsung.com, @partner.samsung.com 등
						mailIndex = "samsung.com";  
					}
					
					if (mail.indexOf(mailIndex) == -1) { // 메일이 @samsung.com 이 아닌 임직원 로그인 불가!
						mailPass = "N";
					} else {
						mailPass = "Y"; // 메일주소가 @samsung.com 으로 끝나는 사람만 로그인 가능!
					}
					*/
					mailPass = "Y";
				}			
			}
		} catch(Exception e) {}
		
		try {
			HashMap hm = new HashMap();
			hm.put("user_id", epid);
			hm.put("sys_cd", sys_cd);
			
			// 구주지법인 허용(2013.11.25까지는 : EHQ, SEUK, SSEL, SEF, SEG, SESA,SEBN,SEGR,SEP,SENA만 허용)
			if ("U".equals(gubun)) { // 사용자 정보 Update!
				// 삼성전자 자회사 직원 중 재직/휴직/파견신청인  사원만 접근가능 : 퇴사는 불가함. SDS 및 개발자들은 Skip!
					if (("C10".equals(compCd) 
							  || "EHQ".equals(compCd)
							  || "SDS".equals(compCd)
							  || "SDSE".equals(compCd)
							  || "SEACE".equals(compCd)
							  || "SEUC".equals(compCd)
							  || "SEAD".equals(compCd)
							  || "SEAG".equals(compCd)
							  || "SEB".equals(compCd)
							  || "SEBN".equals(compCd)
							  || "SECZ".equals(compCd)
							  || "SEF".equals(compCd)
							  || "SEG".equals(compCd)
							  || "SEGR".equals(compCd)
							  || "SEH-P".equals(compCd)
							  || "SEH-S".equals(compCd)
							  || "SEI".equals(compCd)
							  || "SELS".equals(compCd)
							  || "SENA".equals(compCd)
							  || "SEP".equals(compCd)
							  || "SEPLO".equals(compCd)
							  || "SEPM".equals(compCd)
							  || "SEPOL".equals(compCd)
							  || "SEROM".equals(compCd)
							  || "SESA".equals(compCd)
							  || "SESG".equals(compCd)
							  || "SESK".equals(compCd)
							  || "SEUK".equals(compCd)
							  || "SRPOL".equals(compCd)
							  || "SRUK".equals(compCd)
							  
							  )
								&& !"R".equals(userStatus) && "Y".equals(mailPass)){ // R : 퇴사, 메일이 @samsung.com 인 사람들만 로그인 가능
								// role_cd 가 RA00, RA01, RA02, RM00, RD01 인 사용자는 TB_COM_USER 정보를 업데이트 하지 않는다.
								boolean isUpdate = getUserRoleInfo(sys_cd, epid);

								if (!isUpdate) {
									// 특정사용자는 업데이트를 하지 않는다(초기 현업 테스트 용도)
									//boolean chkEpid = checkEpid(epid);
									//if(!chkEpid){
										//사용자 정보 업데이트
										try { modifyUserInfo(compCd, epid); } catch(Exception e) { e.printStackTrace();	}
									//}
								}
							} else {
								// 삼성전자 자회사 및 재직중이 아닌 사용자에 한해 로그인 로직 제외자가 아닌 경우 exception처리
								if (!exceptFlag) {
									throw new Exception();
								}
							} 
			} else { // 사용자 정보 Insert
				// 삼성전자 자회사 직원 중 재직/휴직/파견신청인  사원만 접근가능 : 퇴사는 불가함. SDS 및 개발자들은 Skip!
				//구주의 최초로그인시에는 계약관리자가 지법인을 지정안한 상태이므로 지법인코드를 알 수없으므로 전자인 경우만 체크함.
				if( "C10".equals(traycompCd)
					&& !"R".equals(userStatus) && "Y".equals(mailPass)){ //R : 퇴사 , 메일이 @samsung.com 인사람들만 로그인 가능
					// 사용자 정보 입력
					try { insertUserInfo(traycompCd, epid); } catch(Exception e) { e.printStackTrace();	}
				} else {
					// 삼성전자 자회사 및 재직중이 아닌 사용자에 한해 로그인 로직 제외자가 아닌 경우 exception처리
					if (!exceptFlag) {
						throw new Exception();
					}
				}
			}
			
			result = getUserInfo(hm);				
		} catch(Exception e) {
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}
	
	//로그인시에 특정사용자는 업데이트를 하지 않는다.
	private boolean checkEpid(String epid) throws Exception{
		
		boolean retValue = false;
		//이경섭,심우규,임현승,법무시스템,이기원,김회기,이효은,배민우
		String[] ep_id_arr = {"M101221062328C605436","R020218102320C101152","R020218102326C100366","M110627234243C105628","M030702144020C108208","M030402040320C605656", "D060705193616C103463","M101213141935C108968"};
		
		for(int i=0; i < ep_id_arr.length; i++){
			if(ep_id_arr[i].equals(epid)){
				retValue = true;
				break;
			}
		}
		
		return retValue;
	}
	
	
	
	/**
	 * Locale 변경
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public int changeLocale(HashMap hm) throws Exception {
		
		return  commonDAO.modify("secfw.user.changeLocale", hm);
		
	}

	
	/* 사용자 기본역활 여부 조회
	 */	
	public List existsRoleUser(AuthVO vo) throws Exception {
		return  commonDAO.list("secfw.user.existsRoleUser", vo);
	}

	public int insertRoleUser(AuthVO vo) throws Exception {
		
		return commonDAO.insert("secfw.role.insertRoleUser", vo);
	}			

	/**
	 * 임직원 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List listUser(UserVO vo) throws Exception {
		
		return  commonDAO.list("secfw.user.listUser", vo);
		
	}
	
	/**
	 * 임직원의 역활리스트 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List listUserRole(HashMap hm) throws Exception {
		return  commonDAO.list("secfw.user.listUserRole", hm);
	}
	
	/**
	 * 임직원의 역활리스트 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List listUserAuth(HashMap hm) throws Exception {
		return  commonDAO.list("secfw.user.listUserAuth", hm);
	}

	/**
	 * 소속지역 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public HashMap getRegionInfo(HashMap hm) throws Exception {
		
		HashMap result = new HashMap();
		
		String bigCate = "";
		String midCate = "";
		
		List userCateList = commonDAO.list("cpis.adm.getUserRegionCate", hm);
		List bigCateList = commonDAO.list("cpis.adm.getRegionBigCate", hm);
		List midCateList = commonDAO.list("cpis.adm.getRegionMidCate", hm);
		
		
		if (userCateList != null && userCateList.size() > 0) {
			ListOrderedMap userCateMap = (ListOrderedMap)userCateList.get(0);
			bigCate = StringUtil.bvl((String)userCateMap.get("region_bigcate"), "");
			midCate = StringUtil.bvl((String)userCateMap.get("region_midcate"), "");
			
			if ("top".equals(bigCate) ||"*".equals(bigCate)) {
				if(bigCateList != null && bigCateList.size()>0) {
					ListOrderedMap bigMap = (ListOrderedMap)bigCateList.get(0);
					bigCate = (String)bigMap.get("region_cd");
				}
			}
			if ("top".equals(midCate) ||"*".equals(midCate)) {
				if(midCateList != null && midCateList.size()>0) {
					ListOrderedMap midMap = (ListOrderedMap)midCateList.get(0);
					midCate = (String)midMap.get("region_cd");
				}
			}
			
		}else {
			if(bigCateList != null && bigCateList.size()>0) {
				ListOrderedMap bigMap = (ListOrderedMap)bigCateList.get(0);
				bigCate = (String)bigMap.get("region_cd");
			}
	
			if(midCateList != null && midCateList.size()>0) {
				ListOrderedMap midMap = (ListOrderedMap)midCateList.get(0);
				midCate = (String)midMap.get("region_cd");
			}
		}

		result.put("region_bigcate", ("top".equals(bigCate) || "*".equals(bigCate)) ? "" : bigCate);
		result.put("region_midcate", ("top".equals(midCate) || "*".equals(midCate)) ? "" : midCate);
		
		return result;
	}
	
	/**
	 * 해당부서 임직원 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public JSONArray listUserByDeptCd(DeptVO vo) throws Exception {
		
		ArrayList listDeptAl = (ArrayList)commonDAO.list("secfw.user.listUserByDeptCd", vo);

		JSONArray jsonArray = new JSONArray();

		if(listDeptAl!=null && listDeptAl.size()>0) {
			
			for(int i=0; i<listDeptAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listDeptAl.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("user_id", (String)lom.get("user_id"));
				jsonObject.put("emp_no", (String)lom.get("emp_no"));
				jsonObject.put("user_nm", (String)lom.get("user_nm"));
				jsonObject.put("user_nm_eng", (String)lom.get("user_nm_eng"));
				jsonObject.put("chinese_nm", (String)lom.get("chinese_nm"));
				jsonObject.put("single_id", (String)lom.get("single_id"));
				jsonObject.put("single_epid", (String)lom.get("single_epid"));
				jsonObject.put("comp_cd", (String)lom.get("comp_cd"));
				jsonObject.put("comp_nm", (String)lom.get("comp_nm"));
				jsonObject.put("comp_nm_eng", (String)lom.get("comp_nm_eng"));
				jsonObject.put("business_cd", (String)lom.get("business_cd"));
				jsonObject.put("business_nm", (String)lom.get("business_nm"));
				jsonObject.put("business_nm_eng", (String)lom.get("business_nm_eng"));
				jsonObject.put("dept_cd", (String)lom.get("dept_cd"));
				jsonObject.put("dept_nm", (String)lom.get("dept_nm"));
				jsonObject.put("origin_dept_cd", (String)lom.get("origin_dept_cd"));
				jsonObject.put("origin_dept_nm", (String)lom.get("origin_dept_nm"));
				jsonObject.put("jikgun_cd", (String)lom.get("jikgun_cd"));
				jsonObject.put("jikgun_nm", (String)lom.get("jikgun_nm"));
				jsonObject.put("jikgun_nm_eng", (String)lom.get("jikgun_nm_eng"));
				jsonObject.put("jikgup_cd", (String)lom.get("jikgup_cd"));
				jsonObject.put("jikgup_nm", (String)lom.get("jikgup_nm"));
				jsonObject.put("jikgup_nm_eng", (String)lom.get("jikgup_nm_eng"));
				jsonObject.put("grp_jikgup_cd", (String)lom.get("grp_jikgup_cd"));
				jsonObject.put("grp_jikgup_nm", (String)lom.get("grp_jikgup_nm"));
				jsonObject.put("grp_jikgup_nm_eng", (String)lom.get("grp_jikgup_nm_eng"));
				jsonObject.put("jikmu_cd", (String)lom.get("jikmu_cd"));
				jsonObject.put("jikmu_nm", (String)lom.get("jikmu_nm"));
				jsonObject.put("jikmu_nm_eng", (String)lom.get("jikmu_nm_eng"));
				jsonObject.put("jikchek_cd", (String)lom.get("jikchek_cd"));
				jsonObject.put("birth_ymd", (String)lom.get("birth_ymd"));
				jsonObject.put("solar_yn", (String)lom.get("solar_yn"));
				jsonObject.put("office_tel_no", (String)lom.get("office_tel_no"));
				jsonObject.put("mobile_no", (String)lom.get("mobile_no"));
				jsonObject.put("status", (String)lom.get("status"));
				jsonObject.put("last_locale", (String)lom.get("last_locale"));
				jsonObject.put("total_cnt", lom.get("total_cnt"));
				
				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;
	}
	
	/**
	 * 상위부서 조회
	 */
	public List listUpDeptInfo(HashMap hm) throws Exception{
		return  commonDAO.list("secfw.user.listUpDeptInfo", hm);
	}
	
	/**
	 * 소속조직코드 조회
	 */
	public HashMap listOrgnzList(HashMap hm) throws Exception{
		
		HashMap retMap = new HashMap();
		
		String blngt_orgnz = "A00000001";
		String blngt_orgnz_nm = "";
		String blngt_orgnz_nm_abbr = "";
		
		List list = commonDAO.list("secfw.user.listOrgnzList", hm);
		if(list != null && list.size() > 0){
			ListOrderedMap map = (ListOrderedMap)list.get(0);
			
			// 2013.04 단위조직이 존재하지 않으며, 소속조직코드는 고정 by 전종효
			blngt_orgnz = "A00000001";
			//blngt_orgnz = StringUtil.bvl((String)map.get("orgnz_cd"), "");
			blngt_orgnz_nm = StringUtil.bvl((String)map.get("orgnz_nm"), "");
			blngt_orgnz_nm_abbr = StringUtil.bvl((String)map.get("orgnz_nm_abbr"), "");
			
			//tb_com_user update!
			hm.put("blngt_orgnz", blngt_orgnz);
			commonDAO.modify("clms.user.updateBlngtOrgnz", hm);
		}
		
		retMap.put("blngt_orgnz", blngt_orgnz);
		retMap.put("blngt_orgnz_nm", blngt_orgnz_nm);
		retMap.put("blngt_orgnz_nm_abbr", blngt_orgnz_nm_abbr);
		
		return  retMap;
	}
	
	//소속조직코드 조회 -- 로그인 체크용(update가 없다)
	public HashMap searchOrgnz(HashMap hm) throws Exception{
		HashMap retMap = new HashMap();
		
		String blngt_orgnz = "";
		String blngt_orgnz_nm = "";
		String blngt_orgnz_nm_abbr = "";
		
		List list = commonDAO.list("secfw.user.listOrgnzList", hm);
		if(list != null && list.size() > 0){
			ListOrderedMap map = (ListOrderedMap)list.get(0);
			
			blngt_orgnz = StringUtil.bvl((String)map.get("orgnz_cd"), "");
			blngt_orgnz_nm = StringUtil.bvl((String)map.get("orgnz_nm"), "");
			blngt_orgnz_nm_abbr = StringUtil.bvl((String)map.get("orgnz_nm_abbr"), "");
		}
		
		retMap.put("blngt_orgnz", blngt_orgnz);
		retMap.put("blngt_orgnz_nm", blngt_orgnz_nm);
		retMap.put("blngt_orgnz_nm_abbr", blngt_orgnz_nm_abbr);
		
		return  retMap;
	
	}
	
	
	/**
	 * 소속조직코드로 소속조직명 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public HashMap listOrgnzName(HashMap hm) throws Exception{
		HashMap retMap = new HashMap();
		
		String blngt_orgnz_nm = "";
		String blngt_orgnz_nm_abbr = "";
		
		List list = commonDAO.list("secfw.user.listOrgnzNameList", hm);
		if(list != null && list.size() > 0){
			ListOrderedMap map = (ListOrderedMap)list.get(0);
			
			blngt_orgnz_nm = StringUtil.bvl((String)map.get("orgnz_nm"), "");
			blngt_orgnz_nm_abbr = StringUtil.bvl((String)map.get("orgnz_nm_abbr"), "");
		}
		
		retMap.put("blngt_orgnz_nm", blngt_orgnz_nm);
		retMap.put("blngt_orgnz_nm_abbr", blngt_orgnz_nm_abbr);
		
		return retMap;
	}
	
	/**
	 * 조직장 여부 조회
	 */
	public List listManagerYNList(HashMap hm) throws Exception{
		return  commonDAO.list("secfw.user.listManagerYNList", hm);
	}
	
	/**
	 * 지원부서 여부 조회
	 */
	public List listSupportYNList(HashMap hm) throws Exception{
		return  commonDAO.list("secfw.user.listSupportYNList", hm);
	}	
	
	/**
	 *  사용자 전체 리스트
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public List listTotalUser(UserVO vo) throws Exception{
		List result = null;
		
		result = commonDAO.list("secfw.user.listTotalUser", vo);
		
		return result;
	}
	
	/**
	 * 개인정보활용 동의 업데이트 
	 * @param user_id
	 * @throws Exception
	 */
	public void upateGthrCnstYn(String user_id) throws Exception{
		HashMap hm = new HashMap();
		hm.put("user_id", user_id);
		commonDAO.modify("secfw.user.upateGthrCnstYn", hm);
	}
	
	/**
	 *  계약연계테이블 정보 조회
	 *  sys_nm과 key_id로 계약번호 찾기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public HashMap getCnsdReqId(HashMap hm) throws Exception{
		HashMap result = new HashMap();
		String cnsdreqId = "";
		String prcsDepth = "";
		List machList = commonDAO.list("secfw.inf.machList", hm);
		
		if(machList != null && machList.size()>0){
			ListOrderedMap mach = (ListOrderedMap)machList.get(0);
			cnsdreqId = (String)mach.get("cnsdreq_id");
			prcsDepth = (String)mach.get("prcs_depth");
		}
		result.put("cnsdreq_id", cnsdreqId);
		result.put("prcs_depth", prcsDepth);
		
		return result;
	}
	
	/**
	 *  계약연계테이블 정보 조회
	 *  cntrt_id로 의뢰ID 찾기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public HashMap getCnsdReqIdByCntrtID(HashMap hm) throws Exception{
		HashMap result = new HashMap();
		String cnsdreqId = "";
		List cnsdreqIdList = commonDAO.list("secfw.inf.getReqIdByCntrtId", hm);
		
		if(cnsdreqIdList != null && cnsdreqIdList.size()>0){
			ListOrderedMap cnsdreqIdMap = (ListOrderedMap)cnsdreqIdList.get(0);
			cnsdreqId = (String)cnsdreqIdMap.get("cnsdreq_id");
		}
		result.put("cnsdreq_id", cnsdreqId);
		
		return result;
	}	
	/**
	 *  계약연계테이블 정보 조회
	 *  cntrt_no로 cntrt_id 찾기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public HashMap getCntrtIDByCntrtNO(HashMap hm) throws Exception{
		HashMap result = new HashMap();
		String cntrtId = "";
		String prcsDepth = "";
		List cntrtIdList = commonDAO.list("secfw.inf.getCntrtIDByCntrtNO", hm);
		
		if(cntrtIdList != null && cntrtIdList.size()>0){
			ListOrderedMap cnsdreqIdMap = (ListOrderedMap)cntrtIdList.get(0);
			cntrtId = (String)cnsdreqIdMap.get("cntrt_id");
			prcsDepth = (String)cnsdreqIdMap.get("prcs_depth");
		}
		result.put("cntrt_id", cntrtId);
		result.put("prcs_depth", prcsDepth);
		
		return result;
	}

	/**
	 * 자회사인지 여부 체크
	 * 
	 * SQL에서 UNION ALL을 추가한 이유는
	 * 1. 실제 싱글(SSO)의 회사코드는 전자(C10)이면서 특정 자회사(예:리빙프로자(PC2)) 소속으로 셋팅이 필요한  사용자의 경우
	 * 
	 * [CASE 1] : 사용자선택화면(clmsSelLoginPrcs)에서 호출시 TB_COM_USER의 COMP_CD를 설정하므로 PC2가 됨
	 * [CASE 2] : SSO처리를 통한 로그인의 경우(processTrayInfo)에서 호출시 TRAY의 COMP_CD를 설정하므로 C10
	 * 
	 * 위에서 [CASE 1]의 경우 결과값이 2건이 나오지만 CONTROL단에서 1번째 ROW의 데이타만 사용하여 처리함
	 * [CASE 2]의 경우 결과값이 1건이 나오며 CONTROL단에서 이값을 사용하여 처리함
	 * 
	 * 2. 그외 싱글(SSO)의 회사코드가 자회사 소속이면 결과값이 2건이 나오겠지만 CONTROL단에서 1번째 ROW의 데이타만 사용하여 처리함.
	 *    
	 * <p>
	 * @param comp_cd 회사코드
	 * @return 
	 * @throws Exception
	 */
	@Override
	public List isExist_comp_cd(String comp_cd, String user_id) throws Exception {
		boolean isExist = false;
		
		HashMap hm = new HashMap();
		hm.put("comp_cd", comp_cd);
		hm.put("user_id", user_id);
		List compCdList = commonDAO.list("secfw.user.isExistCompCd", hm);
		
		return compCdList;
	}
	
	/**
	 * 해당 사용자가 전자검토자인지 여부를 체크하여 전자검토자이면 관리하는 회사코드 목록을 조회
	 * 하나 이상일 경우 comp_cd,comp_cd 형태로 가져온다.
	 * <p>
	 * @param hm HashMap
	 * @return List
	 * @throws Exception
	 */
	public List getMngComps(HashMap hm) throws Exception {
		return commonDAO.list("secfw.user.getMngCompCdList", hm);
	}

	/**
	 * 계약지정인인인지 여부 조회
	 */
	public HashMap getAuthApntYn(HashMap<String, String> hMap) throws Exception {
		HashMap result = new HashMap();
		String auth_apnt_yn = "";
		List authApntYnList = commonDAO.list("secfw.user.getAuthApntYn", hMap);
		
		if (authApntYnList != null && authApntYnList.size()>0) {
			ListOrderedMap authApntYnMap = (ListOrderedMap)authApntYnList.get(0);
			auth_apnt_yn = (String)authApntYnMap.get("auth_apnt_yn");
		} else {
			auth_apnt_yn = "N";
		}
		
		result.put("auth_apnt_yn", auth_apnt_yn);
		
		return result;
	}
	
	/**
	 * 시스템에 사용되는 자회사 회사코드 목록을 조회
	 */
	public List getMngComps() throws Exception {
		return commonDAO.list("secfw.user.getAllMngCompCdList");
	}

	/**
	 * 해당 사용자가 전자임원의 Role을 가졌는지 여부를 체크
	 */
	public List getUserSecRole(HashMap<String, String> hMap) throws Exception {
		return commonDAO.list("secfw.user.getUserSecRole", hMap);
	}

	@Override
	public String getAccessYn(String userId) throws Exception {
		// TODO Auto-generated method stub
		List userInfoList = null;
		String accessYn = "";
		try{
			HashMap hm = new HashMap();
			hm.put("user_id", userId);
			
			
			userInfoList = commonDAO.list("secfw.user.getRoleCd", hm);
			if(userInfoList != null && userInfoList.size() > 0){
				ListOrderedMap returnLom = (ListOrderedMap)userInfoList.get(0);			 
				if("RA00".equals((String)returnLom.get("role_cd"))){
					accessYn = "Y";
				}else{
					userInfoList = commonDAO.list("secfw.user.getAccessYn", hm);
					if(userInfoList != null && userInfoList.size() > 0){
						returnLom = (ListOrderedMap)userInfoList.get(0);			 
						accessYn = (String)returnLom.get("access_yn");
					}
					
				}
			}else{
				userInfoList = commonDAO.list("secfw.user.getAccessYn", hm);
				if(userInfoList != null && userInfoList.size() > 0){
					ListOrderedMap returnLom = (ListOrderedMap)userInfoList.get(0);			 
					accessYn = (String)returnLom.get("access_yn");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return accessYn;
	}

	@Override
	public int requestAccess(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.modify("secfw.user.updateAccessYn", vo);
	}
	
	/**
	 * 기존 구주 selms 사용자 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List listOldUser(HashMap hm) throws Exception {
		String ecms_tran_yn = (String)hm.get("ecms_tran_yn");
		
		if(ecms_tran_yn!=null && ecms_tran_yn.equals("N")){
			return  commonDAO.list("secfw.user.listOldUser", hm);
		}else{
			return  commonDAO.list("secfw.user.listOldECMSUser", hm);
		}
	}
	
	/**
	 * 기존 구주 selms 사용자 처리후 상태 업데이트
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int updateMigStatus(AuthVO vo) throws Exception {
		
		return commonDAO.insert("secfw.user.updateMigStatus", vo);
	}
	
	/**
	 * 기존 구주 ECMS 사용자 처리후 상태 업데이트
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int updateEcmsMigStatus(AuthVO vo) throws Exception {
		return commonDAO.insert("secfw.user.updateEcmsMigStatus", vo);
	}
	
	/**
	 * 사용자 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List listUserUpdJikgubByESB(HashMap hm) throws Exception {
		return  commonDAO.list("secfw.user.listUserUpdJikgubByESB", hm);
	}
	
	/**
	 * 사용자정보 직급 업데이트 처리 -ESB
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int updUserUpdJikgubByESB(AuthVO vo) throws Exception {
		int result = -1;
		
		Employee employee[] = esbOrgService.userSearchByEpid(vo.getUser_id());
		 
		if(employee.length > 0 ){
			
			Employee userInfo = employee[0];
			
			String gradeortitle = StringUtil.bvl(userInfo.getEpgradeortitle(), ""); // 
			String title = StringUtil.bvl(userInfo.getEpentitle(), "");         
			String gradename = StringUtil.bvl(userInfo.getEpengradename(), "");   
			
			vo.setJikgup_nm(title);
			if( gradeortitle.equals("G")){
				vo.setJikgup_nm(gradename);
			}else if( gradeortitle.equals("B")){
				vo.setJikgup_nm(title + " "+ gradename);;
			}
			
		}
		return commonDAO.insert("secfw.user.updUserUpdJikgubByESB", vo);
	}
	
	/* FERNANDO (MFA project) Jan/2024 - start */
	public boolean getUserOTP(String sys_cd, String user_id) throws Exception
	{
		boolean retValue = false;
		
		HashMap hm = new HashMap();
		hm.put("sys_cd", sys_cd);
		hm.put("user_id", user_id);
		List rows = commonDAO.list("secfw.user.getUserOTP", hm);
		
		if(rows != null && rows.size() > 0){
			retValue = true;
		}
		
		return retValue;
	}
	/* FERNANDO (MFA project) Jan/2024 - end */
	
}
