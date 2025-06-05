package com.sds.secframework.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import model.outldap.samsung.net.Employee;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.jfree.util.Log;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.dto.UserManagerVO;
import com.sds.secframework.user.service.UserManagerService;
import com.sec.clm.sign.dto.SignManageVO;

public class UserManagerServiceImpl extends CommonServiceImpl implements UserManagerService {
	
	/**
	 * CommonDAO에 대한 DAO선언 및 세팅
	 * @param commonDAO
	 */
	private CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}		
	
	/**
	 * 사용자 정보 목록 조회 
	 * 사용자관리 화면에서 조회 시 사용된다. 2011.03.21 by 김정곤
	 * <p>
	 * @param vo UserManagerVO
	 * @return HashMap
	 * @throws Exception
	 */	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap listUserMng(UserManagerVO vo)throws Exception {
		List listUser  = null;
		HashMap result = new HashMap();
		
		String srch_cntnt_type = new String(StringUtil.bvl(vo.getSrch_cntnt_type(), "user_nm"));
		
		// 사용자 역할(Role) 목록 조회
		List RollList = commonDAO.list("secfw.usermng.list.roll", vo);
		
		vo.setSrch_role_cd(StringUtil.bvl(vo.getSrch_role_cd(), "%"));
		
		// 화면에서 조회를 눌렀을 때만 조회
		if ("Y".equals(vo.getSrch_auth_apnt_yn())) {
			if (srch_cntnt_type.equals("user_nm")) {
				listUser =  commonDAO.list("secfw.usermng.list.usernm",vo);
			//} else if (srch_cntnt_type.equals("comp_nm")) {
			//	listUser =  commonDAO.list("secfw.usermng.list.compnm",vo);
			} else if (srch_cntnt_type.equals("single_id")) {
				listUser =  commonDAO.list("secfw.usermng.list.singlenm",vo);
			}
		}
		
		// 소속조직 목록 조회
		List orgnzList = commonDAO.list("secfw.usermng.list.orgnz", vo);
		
		result.put("RollList", RollList);
		result.put("listUser", listUser);
		result.put("orgnzList", orgnzList);
		

		return result;
	}
	
	/**
	 * 사용자 정보 조회 김정곤 2011/04/07
	 * <P>
	 * ESB 정보 조회
	 * </P>
	 * @param form UserManagerVO
	 * @return HashMap
	 * @throws Exception
	 */	
	public HashMap SerchUserEsbInfo(UserManagerVO vo)throws Exception{
		HashMap result 		=	new HashMap();		
		String popUserId 	=	new String(StringUtil.bvl( vo.getPop_user_id()  ,""));
		return EsbSerchHash(vo.getSession_comp_cd(), popUserId);
	}	
	/**
	 * 사용자 정보 저장 김정곤 2011/03/21
	 * @param form UserManagerVO
	 * @return String
	 * @throws Exception
	 */		
	public String SaveUserMng(UserManagerVO vo, Locale locale) throws Exception {
		String result_msg 		="";
		String user_id    		=StringUtil.bvl(vo.getUser_id(),"");
		
		if(user_id.equals("")) {
//			return result_msg 	="저장할 정보가 없습니다.";
			return result_msg = messageSource.getMessage("secfw.page.field.alert.noUserInfo", null, locale);
		}

		HashMap esbUser 		=EsbSerchHash(vo.getSession_comp_cd(), user_id);
		if(esbUser.isEmpty()){
			return result_msg 	= messageSource.getMessage("clm.page.msg.manage.announce096", null, locale);
//			return result_msg 	="사용자의 정보가 존재하지 않습니다.";
		}else{
	        //이메일 수신여부
	        esbUser.put("email_rcv_yn", vo.getEmail_rcv_yn());			
	        esbUser.put("blngt_orgnz", vo.getBlngt_orgnz());
	        esbUser.put("resp_operdiv", vo.getResp_operdiv());
	        esbUser.put("auto_rnew_yn", vo.getAuto_rnew_yn());
	        esbUser.put("approval_yn", vo.getApproval_yn());
	        esbUser.put("access_yn", vo.getAccess_yn());
	        esbUser.put("comp_cd", vo.getLoc_gbn());
	        esbUser.put("comp_nm", vo.getComp_nm());
		}
        
		if(TbComUserHaveChack(vo)){//UPDATE 
			commonDAO.modify("secfw.usermng.user.update", esbUser);
		}else{//INSERT 
			commonDAO.insert("secfw.usermng.user.insert", esbUser);			
		}
	
        /*사용자 권한 정보를 INSERT 합니다.*/
        String rolls[] = SpliteArry(vo.getRole_cd(),",");      
        ArrayList<String> arr = new ArrayList<String>();
        
        for (int i = 0; i < rolls.length; i++) {
        	if(rolls[i].equals("RA01")){
        		arr.add(rolls[i]);
        		arr.add("RD01");
        		arr.add("RM00");
        	}else 
        		arr.add(rolls[i]);
		}
        
        InsertRolls(vo,arr);
        //저장되었습니다.
        result_msg 	= messageSource.getMessage("secfw.msg.succ.save", null, locale);
		return result_msg;
	}
	
	/**
	 * ROLL_INSERT.  
	 * @param 
	 * @return int
	 * @throws Exception 
	 */
	public int InsertRolls(UserManagerVO vo, ArrayList<String> Rolls) throws Exception {
		int result =-1;
		commonDAO.delete("secfw.usermng.auth.delete", vo);
		for(int idx=0;Rolls!=null && idx<Rolls.size();idx++){
			HashMap Result = new HashMap(); 
			Result.put("sys_cd", vo.getSys_cd());
			Result.put("roll_cd", Rolls.get(idx));
			Result.put("user_id", vo.getUser_id());
			Result.put("reg_id", vo.getLogin_id());
			
			commonDAO.insert("secfw.usermng.auth.insert", Result);	
			result =idx;
		}        
		return result ;
	}	
	
	/**
	 * ROLL_INSERT.  
	 * @param 
	 * @return int
	 * @throws Exception 
	 */
	public int InsertRolls(UserManagerVO vo,String[] Rolls) throws Exception {
		int result =-1;
		commonDAO.delete("secfw.usermng.auth.delete", vo);
		for(int idx=0;Rolls!=null && idx<Rolls.length;idx++){
			HashMap Result = new HashMap(); 
			Result.put("sys_cd", vo.getSys_cd());
			Result.put("roll_cd", Rolls[idx]);
			Result.put("user_id", vo.getUser_id());
			Result.put("reg_id", vo.getLogin_id());
			
			commonDAO.insert("secfw.usermng.auth.insert", Result);	
			result =idx;
		}        
		return result ;
	}	
	
	/**
	 * TB_COM_USER 테이블에 존제 를 확인한다.  
	 * @param 
	 * @return boolean
	 * @throws Exception 
	 */
	private boolean TbComUserHaveChack(UserManagerVO vo) throws Exception {
		boolean result =false;
		
		ArrayList authList = (ArrayList)commonDAO.list("secfw.usermng.user.have", vo);
		if(((ListOrderedMap)authList.get(0)).get("chack").equals("HAVE")){
			return true;
		}
		return result ;
	}
	
	/**
	 * ESB 를 EP_ID를 기준으로 조회리턴  
	 * @param 
	 * @return HashMap
	 * @throws Exception 
	 */		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private HashMap EsbSerchHash(String compCd, String EpId) throws Exception {
		
		HashMap Result = new HashMap(); 
		Employee[] employee; 
		employee = esbOrgService.userSearchByEpid(EpId);
			if(employee.length==1) {		
				Result.put("user_id"			,StringUtil.bvl(employee[0].getEpid(), ""));   
				//Result.put("res_no"				,StringUtil.bvl(employee[0].getEpregisternumber(), ""));       
				Result.put("res_no"				,"");
				Result.put("emp_no"				,StringUtil.bvl(employee[0].getEmployeenumber(), ""));    
				Result.put("user_nm"			,StringUtil.bvl(employee[0].getCn(), ""));   
				Result.put("user_real_nm"		,StringUtil.bvl(employee[0].getCn(), ""));
				Result.put("user_nm_eng"		,StringUtil.bvl(employee[0].getEpencn(), ""));
				Result.put("user_real_nm_eng"	,StringUtil.bvl(employee[0].getEpencn(), ""));
				Result.put("chinese_nm"			,"");   
				Result.put("single_id"			,StringUtil.bvl(employee[0].getUserid(), ""));          
				Result.put("single_epid"		,StringUtil.bvl(employee[0].getEpid(), ""));          
				Result.put("comp_cd"			,StringUtil.bvl(employee[0].getEporganizationnumber(), ""));   
				Result.put("comp_nm"			,StringUtil.bvl(employee[0].getO(), ""));       
				Result.put("comp_nm_eng"		,StringUtil.bvl(employee[0].getEpenorganizationname(), ""));       
				Result.put("business_cd"		,StringUtil.bvl(employee[0].getEpsuborgcode(), ""));         //총괄코드      
				Result.put("business_nm"		,StringUtil.bvl(employee[0].getEpsuborgname(), ""));         //총괄명           
				Result.put("business_nm_eng"	,StringUtil.bvl(employee[0].getEpensuborgname(), ""));   //총괄영문명
				Result.put("division_cd"		,StringUtil.bvl(employee[0].getEpbusicode(), ""));         
				Result.put("division_nm"		,StringUtil.bvl(employee[0].getEpbusiname(), ""));    
				Result.put("division_nm_eng"	,StringUtil.bvl(employee[0].getEpenpostaladdress(), ""));        
				Result.put("area_cd"			,"");         
				Result.put("dept_cd"			,StringUtil.bvl(employee[0].getDepartmentnumber(), ""));         
				Result.put("in_dept_cd"			,StringUtil.bvl(employee[0].getEpindeptcode(), ""));  
				Result.put("dept_nm"			,StringUtil.bvl(employee[0].getDepartment(), ""));       
//				Result.put("dept_nm"			,StringUtil.bvl(employee[0].getOu(), ""));
				Result.put("dept_nm_eng"		,StringUtil.bvl(employee[0].getEpendepartment(), ""));       
				Result.put("change_dept_ymd"	,"");       
				Result.put("origin_dept_cd"		,"");       
				Result.put("origin_dept_nm"		,StringUtil.bvl(employee[0].getDepartment(), ""));       
				Result.put("appoint_cd"			,"");           
				Result.put("grp_join_ymd"		,"");        
				Result.put("join_term"			,"");        
				Result.put("join_level"			,"");           
				Result.put("join_ymd"			,"");         
				Result.put("raise_ymd"			,"");         
				Result.put("promotion_ymd"		,"");          
				Result.put("next_raise_ym"		,"");        
				Result.put("next_promotion_ym"	,"");    
				Result.put("retirement_ymd"		,"");       
				Result.put("suspension_ymd"		,"");         
				Result.put("reinstatment_ymd"	,"");         
				Result.put("jikgun_cd"			,"");          
				Result.put("jikgun_nm"			,StringUtil.bvl(employee[0].getEpbusiname(), ""));         
				Result.put("jikgun_nm_eng"		,StringUtil.bvl(employee[0].getEpencn(), ""));      
				Result.put("jikgup_cd"			,StringUtil.bvl(employee[0].getEptitlenumber(), ""));          //직급코드          
				Result.put("jikgup_nm"			,StringUtil.bvl(employee[0].getTitle(), ""));        
				Result.put("jikgup_nm_eng"		,StringUtil.bvl(employee[0].getEpentitle(), ""));          
				Result.put("grp_jikgup_cd"		,"");          
				Result.put("grp_jikgup_nm"		,"");          
				Result.put("grp_jikgup_nm_eng"	,"");         
				Result.put("jikmu_cd"			,StringUtil.bvl(employee[0].getEpjob(), ""));                   //직무코드        
				Result.put("jikmu_nm"			,StringUtil.bvl(employee[0].getDescription(), ""));          
				Result.put("jikmu_nm_eng"		,"");        
				Result.put("jikchek_cd"			,"");       
				Result.put("schooling_cd"		,"");          
				Result.put("graduate_school_cd"	,"");         
				Result.put("graduate_school_nm"	,"");        
				Result.put("graduate_school_nm_eng","");         
				Result.put("master_major_cd"	,"");         
				Result.put("master_major_nm"	,"");          
				Result.put("master_major_nm_eng","");          
				Result.put("univ_cd"			,"");           
				Result.put("univ_nm"			,"");         
				Result.put("univ_nm_eng"		,"");         
				Result.put("bachelor_major_cd"	,"");          
				Result.put("bachelor_major_nm"	,"");           
				Result.put("bachelor_major_nm_eng","");       
				Result.put("military"			,"");         
				Result.put("sex_cd"				,StringUtil.bvl(employee[0].getEpgender(), ""));         
				Result.put("abo_blood"			,"");          
				Result.put("rh_blood"			,"");     
				Result.put("hobby"				,"");        
				Result.put("birth_ymd"			,"");         
				Result.put("solar_yn"			,"");           
				Result.put("height"				,"");            
				Result.put("weight"				,"");          
				Result.put("marriage_ymd"		,"");        
				Result.put("region_cd"			,"");       
				Result.put("email"				,StringUtil.bvl(employee[0].getMail(), ""));           
				Result.put("home_address"		,StringUtil.bvl(employee[0].getHomepostaladdress(), ""));          
				Result.put("comp_zip_cd"		,StringUtil.bvl(employee[0].getPostalcode(), ""));      
				Result.put("comp_address"		,StringUtil.bvl(employee[0].getL(), ""));        
				Result.put("office_tel_no"		,StringUtil.bvl(employee[0].getTelephonenumber(), ""));           
				Result.put("home_tel_no"		,StringUtil.bvl(employee[0].getHomephone(), ""));     
				Result.put("mobile_no"			,StringUtil.bvl(employee[0].getMobile(), ""));        
				Result.put("status"				,StringUtil.bvl(employee[0].getEpuserstatus(), ""));          
				Result.put("last_locale"		,StringUtil.bvl(employee[0].getEppreferredlanguage(), ""));        
				Result.put("sys_gbn"			,"");        
				Result.put("reg_dt"				,"");  
				Result.put("mna_mng_yn"			,"N");           
				Result.put("area_nm"			,"");    
				Result.put("user_level"			,StringUtil.bvl(employee[0].getEpsecuritylevel(), ""));       //보안레벨 
				Result.put("region_cd"			,StringUtil.bvl(employee[0].getEpregioncode(), ""));           //지역코드
				Result.put("region_nm"			,StringUtil.bvl(employee[0].getEpregionname(), ""));           //지역명       
				Result.put("region_nm_eng"		,StringUtil.bvl(employee[0].getEpenregionname(), ""));     //영문지역명          
				Result.put("employee_type"		,StringUtil.bvl(employee[0].getEmployeetype(), ""));       //사용자타입				
				Result.put("result_action"		, "ok");
			}
		return Result;
		
	}
	
	/**
	 * 문자열을 배열로 생성하여 리턴한다. 
	 * @param 
	 * @return String[]
	 * @throws Exception 
	 */	
	private String[] SpliteArry(String Parm,String div){
		String[] Result;		
		String 	 str_arry =StringUtil.bvl( new String(Parm),"");
		if( !Parm.equals("")){
			Result = str_arry.split(new String(div));
		}else{
			Result =null;
		}
		return Result;
	}
	
	
	
	/**
	 * 메뉴 목록 ESB 에서 해당 사용자 정보를조회한다.
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray SerchUserEsb(UserManagerVO vo) throws Exception {
		
		//ArrayList listMenuAl = (ArrayList)commonDAO.list("secfw.usermng.info.user", vo);

		JSONArray jsonArray  = new JSONArray();
		String    emailRcvYn ="Y";

        Employee[] employee;
        String EpId="";
    	String result_action="";
    	
    	if(vo.getSerch_detail_div().equals("user_id")){
    		EpId =vo.getSerch_detail_vel();		
    	}else{
            Vector employeeVactor;
            // 이 부분 수정 필요
            // 참조 : userSearchByName(a, b) => userSearchByName(a, b, c) & 해당 Impl에 신규메서드 생성
            //employeeVactor = esbOrgService.userSearchByCompName(vo.getComp_cd(), vo.getSerch_detail_vel(), new Locale("en"));
            employeeVactor = esbOrgService.userSearchByName(vo.getSerch_detail_vel(), new Locale("do"));

            if(employeeVactor==null||employeeVactor.size()==0){   
    		}else if(employeeVactor.size()==1){
    			EpId=(String)((Hashtable)employeeVactor.get(0)).get("epid");
    		}else{    			
    			result_action="popup";
    		}
    	}

    	if(EpId!=null&&!EpId.equals("")){   
    		/**************************************************************
    		 *  EP 아이디 조회 
    		 **************************************************************/    	
    		employee = esbOrgService.userSearchByEpid(EpId);
    	
    		if(employee!=null && employee.length >0) {
			
    			//for(int i=0; i<employee.length; i++) {

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("user_id"			,StringUtil.bvl(employee[0].getEpid(), ""));
					//jsonObject.put("res_no"				,StringUtil.bvl(employee[0].getEpregisternumber(), ""));
					jsonObject.put("res_no"				,"");
					jsonObject.put("emp_no"				,StringUtil.bvl(employee[0].getEmployeenumber(), ""));
					jsonObject.put("user_nm"			,StringUtil.bvl(employee[0].getCn(), ""));
					jsonObject.put("user_nm_eng"		,StringUtil.bvl(employee[0].getEpencn(), ""));
					jsonObject.put("chinese_nm"			,"");
					jsonObject.put("single_id"			,StringUtil.bvl(employee[0].getUserid(), ""));                        
					jsonObject.put("single_epid"		,StringUtil.bvl(employee[0].getEpid(), ""));                          
					jsonObject.put("comp_cd"			,StringUtil.bvl(employee[0].getEporganizationnumber(), ""));          
					jsonObject.put("comp_nm"			,StringUtil.bvl(employee[0].getO(), ""));                             
					jsonObject.put("comp_nm_eng"		,StringUtil.bvl(employee[0].getEpenorganizationname(), ""));          
					jsonObject.put("business_cd"		,StringUtil.bvl(employee[0].getEpsuborgcode(), ""));         //총괄코드   
					jsonObject.put("business_nm"		,StringUtil.bvl(employee[0].getEpsuborgname(), ""));         //총괄명    
					jsonObject.put("business_nm_eng"	,StringUtil.bvl(employee[0].getEpensuborgname(), ""));   //총괄영문명      
					jsonObject.put("division_cd"		,StringUtil.bvl(employee[0].getEpbusicode(), ""));                    
					jsonObject.put("division_nm"		,StringUtil.bvl(employee[0].getEpbusiname(), ""));                    
					jsonObject.put("division_nm_eng"	,StringUtil.bvl(employee[0].getEpenpostaladdress(), ""));                 
					jsonObject.put("area_cd"			,"");
					jsonObject.put("dept_cd"			,StringUtil.bvl(employee[0].getDepartmentnumber(), ""));
					jsonObject.put("in_dept_cd"			,StringUtil.bvl(employee[0].getEpindeptcode(), ""));
					jsonObject.put("dept_nm"			,StringUtil.bvl(employee[0].getDepartment(), ""));
//					jsonObject.put("dept_nm"			,StringUtil.bvl(employee[0].getOu(), ""));
					jsonObject.put("dept_nm_eng"		,StringUtil.bvl(employee[0].getEpendepartment(), ""));
					jsonObject.put("change_dept_ymd"	,"");
					jsonObject.put("origin_dept_cd"		,"");
					jsonObject.put("origin_dept_nm"		,StringUtil.bvl(employee[0].getDepartment(), ""));
					jsonObject.put("appoint_cd"			,"");
					jsonObject.put("grp_join_ymd"		,"");
					jsonObject.put("join_term"			,"");
					jsonObject.put("join_level"			,"");
					jsonObject.put("join_ymd"			,"");
					jsonObject.put("raise_ymd"			,"");
					jsonObject.put("promotion_ymd"		,"");
					jsonObject.put("next_raise_ym"		,"");
					jsonObject.put("next_promotion_ym"	,"");
					jsonObject.put("retirement_ymd"		,"");
					jsonObject.put("suspension_ymd"		,"");
					jsonObject.put("reinstatment_ymd"	,"");
					jsonObject.put("jikgun_cd"			,"");
					jsonObject.put("jikgun_nm"			,StringUtil.bvl(employee[0].getEpbusiname(), "")); // 직군명에 사업장명을 넣고 있음. 이유는 모름
					jsonObject.put("jikgun_nm_eng"		,StringUtil.bvl(employee[0].getEpencn(), "")); // 직군영문명에 영어이름을 넣고 있음. 이유는 모름
					jsonObject.put("jikgup_cd"			,"");
					jsonObject.put("jikgup_nm"			,StringUtil.bvl(employee[0].getTitle(), ""));
					jsonObject.put("jikgup_nm_eng"		,StringUtil.bvl(employee[0].getEpentitle(), ""));
					jsonObject.put("grp_jikgup_cd"		,"");
					jsonObject.put("grp_jikgup_nm"		,"");
					jsonObject.put("grp_jikgup_nm_eng"	,"");
					jsonObject.put("jikmu_cd"			,"");
					jsonObject.put("jikmu_nm"			,StringUtil.bvl(employee[0].getDescription(), "")); // 직무명에 담당업무를 넣고 있음. 이유는 모름
					jsonObject.put("jikmu_nm_eng"		,StringUtil.bvl(employee[0].getEpendescription(), ""));
					jsonObject.put("jikchek_cd"			,"");
					jsonObject.put("schooling_cd"		,"");
					jsonObject.put("graduate_school_cd"	,"");
					jsonObject.put("graduate_school_nm"	,"");
					jsonObject.put("graduate_school_nm_eng","");
					jsonObject.put("master_major_cd"	,"");
					jsonObject.put("master_major_nm"	,"");
					jsonObject.put("master_major_nm_eng","");
					jsonObject.put("univ_cd"			,"");
					jsonObject.put("univ_nm"			,"");
					jsonObject.put("univ_nm_eng"		,"");
					jsonObject.put("bachelor_major_cd"	,"");
					jsonObject.put("bachelor_major_nm"	,"");
					jsonObject.put("bachelor_major_nm_eng","");
					jsonObject.put("military"			,"");
					jsonObject.put("sex_cd"				,StringUtil.bvl(employee[0].getEpgender(), ""));
					jsonObject.put("abo_blood"			,"");
					jsonObject.put("rh_blood"			,"");
					jsonObject.put("hobby"				,"");
					jsonObject.put("birth_ymd"			,"");
					jsonObject.put("solar_yn"			,"");
					jsonObject.put("height"				,"");
					jsonObject.put("weight"				,"");
					jsonObject.put("marriage_ymd"		,"");
					jsonObject.put("region_cd"			,"");
					jsonObject.put("email"				,StringUtil.bvl(employee[0].getMail(), ""));
					jsonObject.put("home_address"		,StringUtil.bvl(employee[0].getHomepostaladdress(), ""));
					jsonObject.put("comp_zip_cd"		,StringUtil.bvl(employee[0].getPostalcode(), ""));
					jsonObject.put("comp_address"		,StringUtil.bvl(employee[0].getL(), ""));
					jsonObject.put("office_tel_no"		,StringUtil.bvl(employee[0].getTelephonenumber(), ""));
					jsonObject.put("home_tel_no"		,StringUtil.bvl(employee[0].getHomephone(), ""));
					jsonObject.put("mobile_no"			,StringUtil.bvl(employee[0].getMobile(), ""));
					jsonObject.put("status"				,StringUtil.bvl(employee[0].getEpuserstatus(), ""));
					jsonObject.put("last_locale"		,StringUtil.bvl(employee[0].getEppreferredlanguage(), ""));
					jsonObject.put("sys_gbn"			,"");
					jsonObject.put("reg_dt"				,"");
					jsonObject.put("mna_mng_yn"			,"");
					jsonObject.put("area_nm"			,"");
					jsonObject.put("user_level"			,StringUtil.bvl(employee[0].getEpsecuritylevel(), ""));       //보안레벨
					jsonObject.put("region_cd"			,StringUtil.bvl(employee[0].getEpregioncode(), ""));           //지역코
					jsonObject.put("region_nm"			,StringUtil.bvl(employee[0].getEpregionname(), ""));           //지역명
					jsonObject.put("region_nm_eng"		,StringUtil.bvl(employee[0].getEpenregionname(), ""));     //영문지역명  
					jsonObject.put("employee_type"		,StringUtil.bvl(employee[0].getEmployeetype(), ""));       //사용자타입	
					jsonObject.put("result_action"		, "ok");
				
					/*사용자의 권한 정보 조회*/				
					vo.setUser_id(StringUtil.bvl(employee[0].getEpid(), ""));
					ArrayList authList 	= (ArrayList)commonDAO.list("secfw.usermng.user.auth", vo);

					String role_cd		="";
					String role_cd_show	="";
				
					if(authList.size()!=0){
						for(int idx=0; idx<authList.size();idx++){
							ListOrderedMap lom = (ListOrderedMap)authList.get(idx);
							
							if(!"RM00".equals((String)lom.get("role_cd"))&&!"RD01".equals((String)lom.get("role_cd"))){
								//소유 권한 리스트
						    	if(idx==0){
						    		role_cd = (String)lom.get("role_cd");
						    	}else{
						    		role_cd += (","+(String)lom.get("role_cd")); 
						    	}
								//소유 권한 리스트 명칭
						    	if(idx==0){
						    		role_cd_show = (String)lom.get("role_nm");
						    	}else{
						    		role_cd_show += (" , "+(String)lom.get("role_nm")); 
						    	}			
							}
						}
					}
				
					jsonObject.put("role_cd"			, role_cd);
					jsonObject.put("role_cd_show"		, role_cd_show);
				
					if(authList.size()!=0){
						/*사용자의 이메일 수신 여부 체크*/
						ArrayList emmail_rcv	= (ArrayList)commonDAO.list("secfw.usermng.emailrcvyn.have", vo);
						emailRcvYn = StringUtil.bvl(((ListOrderedMap)emmail_rcv.get(0)).get("email_rcv_yn"), "Y");
						/*사용자의 소속조직코드*/
						String blngt_orgnz = StringUtil.bvl(((ListOrderedMap)emmail_rcv.get(0)).get("blngt_orgnz"), "");
						/*사용자의 담당부서코드*/
						String resp_operdiv = StringUtil.bvl(((ListOrderedMap)emmail_rcv.get(0)).get("resp_operdiv"), "");
						/*사용자의 정보 업데이트 여부*/
						String auto_rnew_yn = StringUtil.bvl(((ListOrderedMap)emmail_rcv.get(0)).get("auto_rnew_yn"), "Y");
						/*결재자 여부*/
						String approval_yn = StringUtil.bvl(((ListOrderedMap)emmail_rcv.get(0)).get("approval_yn"), "N");
						
						String access_yn = StringUtil.bvl(((ListOrderedMap)emmail_rcv.get(0)).get("access_yn"), "");
						
						String comp_cd = StringUtil.bvl(((ListOrderedMap)emmail_rcv.get(0)).get("comp_cd"), "");
						
						jsonObject.put("email_rcv_yn"		, emailRcvYn);
						jsonObject.put("blngt_orgnz"		, blngt_orgnz);
						jsonObject.put("resp_operdiv"		, resp_operdiv);
						jsonObject.put("auto_rnew_yn"		, auto_rnew_yn);
						jsonObject.put("approval_yn"		, approval_yn);
						jsonObject.put("access_yn"			, access_yn);
						jsonObject.put("comp_cd"			, comp_cd);
					}else{
						jsonObject.put("email_rcv_yn"		, "Y");
						jsonObject.put("blngt_orgnz"		, "");
						jsonObject.put("resp_operdiv"		, "");
						jsonObject.put("auto_rnew_yn"		, "Y");
						jsonObject.put("approval_yn"		, "N");
						jsonObject.put("access_yn"			, "");
						jsonObject.put("comp_cd"			, "");
					}
					
					jsonArray.add(jsonObject);
    			//}
    		}else{
    			JSONObject jsonObject = new JSONObject();
				if(result_action.equals("popup")){
					jsonObject.put("result_action"		, "popup");				
				}else{
					jsonObject.put("result_action"		, "close");
				}
				jsonArray.add(jsonObject);
			}    
        
    		/**************************************************************
    		 *  EP 아이디 조회 END
    		 **************************************************************/         
    	}else{
    		JSONObject jsonObject = new JSONObject();
    		if(result_action.equals("popup")){
    			jsonObject.put("result_action"		, "popup");				
    		}else{
    			jsonObject.put("result_action"		, "close");
    		}
    		jsonArray.add(jsonObject);
    	}        
    	
		return jsonArray;			
	}	
	
	
	
	/**
	 * 사용자 권한과 정보를 삭제한다. 
	 * @param 
	 * @return String 
	 * @throws Exception 
	 */
	public String DeleteUser(UserManagerVO vo, Locale locale) throws Exception{
		
		String result_msg="";
		
		 int auth_del=-1;
		 int user_del=-1;		 
		 
		 auth_del= commonDAO.delete("secfw.usermng.auth.delete", vo);
		 user_del= commonDAO.delete("secfw.usermng.user.delete", vo); 
		
		 Log.debug("DELETE INFORMATION "+vo.getUser_id()
			    +"\nAUTH DEL COUNT :"+auth_del
			    +"\nUSER DEL COUNT :"+user_del);
		 
		 
		 if(user_del>1){ throw new Exception("WORRING ROOLBACK: User Deleted Worring!"); }
		 
		 if(user_del<=0){
//			 result_msg = "사용자의 정보가 존재하지 않습니다.";
			 result_msg = messageSource.getMessage("clm.page.msg.manage.announce096", null, locale);
		 }else{
//			 result_msg ="사용자의 정보가 삭제되었 습니다.";			 
			 result_msg = messageSource.getMessage("secfw.msg.succ.delete",null, locale);
		 }
		 
		return result_msg;
	}
	
	/**
	 * 사용자의 이름으로 검색 Table 타입
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray SerchEsbName(UserManagerVO vo) throws Exception {
		
        Vector employeeVactor; 
        
        //employeeVactor = esbOrgService.userSearchByCompName(vo.getComp_cd(), vo.getPopup_user_nm(), new Locale("en"));
        employeeVactor = esbOrgService.userSearchByName( vo.getPopup_user_nm(), new Locale("en"));

		JSONArray jsonArray = new JSONArray();

		if(employeeVactor!=null && employeeVactor.size()>0) {
			Hashtable hashList;
			for(int i=0; i<employeeVactor.size(); i++) {
				hashList=(Hashtable)employeeVactor.get(i);
		
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("user_id"			,hashList.get("epid"));    
				jsonObject.put("user_nm"			,hashList.get("cn"));   
				jsonObject.put("single_id"			,hashList.get("userid"));          
				jsonObject.put("comp_nm"			,hashList.get("o"));       
				jsonObject.put("division_nm"		,hashList.get("epbusiname"));    				
				jsonObject.put("dept_nm"			,hashList.get("department"));   
				jsonObject.put("jikgup_nm"			,hashList.get("epgradename"));    				
				jsonObject.put("email"				,hashList.get("mail"));           
				jsonObject.put("office_tel_no"		,hashList.get("telephonenumber"));           
				jsonObject.put("mobile_no"			,hashList.get("mobile"));        				
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;			
	}	
	
	/**
	 * 계약지정인 리스트 조회 - 김형준 2012/1/4
	 * <P>
	 * 1. TB_COM_ROL_USER 정보입력
	 * </P>
	 * @param form UserManagerVO
	 * @return HashMap
	 * @throws Exception
	 */	
	public HashMap listApntUserMng(UserManagerVO vo)throws Exception{
		HashMap result = new HashMap();
		
		String srch_cntnt_type =new String(StringUtil.bvl( vo.getSrch_cntnt_type(),"user_nm"));
		
		vo.setSrch_auth_apnt_yn(StringUtil.bvl(vo.getSrch_auth_apnt_yn(),"%")); 
		List listUser = null;
		if(srch_cntnt_type.equals("user_nm")){
			listUser =  commonDAO.list("secfw.apntusermng.list.usernm",vo);
		}else if(srch_cntnt_type.equals("dept_nm")){
			listUser =  commonDAO.list("secfw.apntusermng.list.deptnm",vo);
		}else if(srch_cntnt_type.equals("single_id")){
			listUser =  commonDAO.list("secfw.apntusermng.list.singlenm",vo);
		}
		
		result.put("listUser",listUser);
		
		return result;
	}	
	
	/**
	 * 계약지정인 정보 저장 - 김형준 2012/1/10
	 * @param form UserManagerVO
	 * @return String
	 * @throws Exception
	 */		
	public String SaveApntUserMng(UserManagerVO vo) throws Exception {
		String result_msg 		="";
		vo.getSession_user_locale();
		commonDAO.modify("secfw.user.updateAuthApntInfo", vo);
		
		String last_locale = StringUtil.bvl((String)vo.getSession_user_locale(),"en");
		Locale locale1 = new Locale(last_locale);
		
//        result_msg 	="사용자의 정보가 저장되었습니다.";
		result_msg 	= messageSource.getMessage("secfw.msg.succ.save", null, locale1);
		return result_msg;
	}
	
	/**
	 * 날인담당자 리스트 조회 - 
	 * <P>
	 * 1. TB_COM_ROL_USER 정보입력
	 * </P>
	 * @param form UserManagerVO
	 * @return HashMap
	 * @throws Exception
	 */	
	public HashMap listSignUserMng(UserManagerVO vo)throws Exception{
		HashMap result = new HashMap();
		
		vo.setSrch_auth_apnt_yn(StringUtil.bvl(vo.getSrch_auth_apnt_yn(),"%")); 
		List listUser = null;
		
		vo.setRole_cd("RE01");		
		vo.setDivision_cd("SC0101");
		listUser =  commonDAO.list("secfw.signusermng.list",vo);
	
		result.put("listUser",listUser);
		
		return result;
	}	
	
	/**
	 * 증명서발급자 리스트 조회 
	 * <P>
	 * 1. TB_COM_ROL_USER 정보입력
	 * </P>
	 * @param form UserManagerVO
	 * @return HashMap
	 * @throws Exception
	 */	
	public HashMap listSignDocUserMng(UserManagerVO vo)throws Exception{
		HashMap result = new HashMap();
		
		vo.setSrch_auth_apnt_yn(StringUtil.bvl(vo.getSrch_auth_apnt_yn(),"%")); 
		List listUser = null;
		
		vo.setRole_cd("RE02");		
		vo.setDivision_cd("SC0102");
		listUser =  commonDAO.list("secfw.signusermng.list",vo);
	
		result.put("listUser",listUser);
		
		return result;
	}	
	
	/**
	 * 날인담당자 정보 저장 - 
	 * @param form UserManagerVO
	 * @return String
	 * @throws Exception
	 */		
	public int SaveSignUserMng(UserManagerVO vo, SignManageVO svo) throws Exception {
		int result_msg = 0;
		
		if("SC0101".equals(svo.getGubn_cd())){
			vo.setRole_cd("RE01");
		} else {
			vo.setRole_cd("RE02");
		}
		
		svo.setSession_user_id(vo.getSession_user_id());
		svo.setComp_cd(vo.getComp_cd());
		svo.setSeal_ffmtman_id(vo.getUser_id());
		
		// TB_COM_USER_ROLE 테이블은 INSERT/DELETE 처리 , TN_CLM_PRTB_CSTB_INSA 은 DEL_YN 을 업데이트 하는 형식으로 처리한다.		
		if("N".equals(vo.getAuth_apnt_yn())){
			result_msg = commonDAO.modify("secfw.user.AuthSignInfo.delete", vo);
			if(result_msg==1){
				commonDAO.modify("secfw.user.TN_CLM_PRTB_CSTB_INSA.delete", svo);
			}
		} else {
			
			List cnt1 =  commonDAO.list("secfw.user.AuthSignInfo.count",vo);
			List cnt2 =  commonDAO.list("secfw.user.TN_CLM_PRTB_CSTB_INSA.count",svo);
			
			// 구분코드 ( SC0101: 날인 담당자  / SC0102:증명서류 발급자
			if(cnt1.size()==0){
				result_msg = commonDAO.insert("secfw.user.AuthSignInfo.insert", vo);
			} else {
				result_msg =1;
			}

			if(result_msg==1){ //  트랜잭션 처리 : result_msg =1 일 경우에만 업데이트
				if(cnt2.size()==0){
					commonDAO.insert("secfw.user.TN_CLM_PRTB_CSTB_INSA.insert", svo);
				} else {
					commonDAO.modify("secfw.user.TN_CLM_PRTB_CSTB_INSA.update", svo);
				}
			}
		}
		
		return result_msg;
	}
	
	/**
	 * 사용자정보를 조회 한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */		
	public UserManagerVO findUserByPk(String user_id) throws Exception {
		Map map = new HashMap();
		map.put("user_id", user_id);
		return (UserManagerVO)commonDAO.findByPk("secfw.user.TB_COM_USER.select", map);
	}
	
	/**
	 * 사용자정보를 갱신 한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */		
	public void updateUser(UserManagerVO vo) throws Exception {
		commonDAO.modify("secfw.user.TB_COM_USER.update", vo);
	}
	
	/**
	 * 날인담당자 리스트 조회 - 
	 * <P>
	 * 1. TB_COM_ROL_USER 정보입력
	 * </P>
	 * @param form UserManagerVO
	 * @return HashMap
	 * @throws Exception
	 */	
	public HashMap listUserAccess(UserManagerVO vo)throws Exception{
		HashMap result = new HashMap();
		
		if("A".equals(vo.getSrch_access_yn())){
			vo.setSrch_access_yn("%");
		}else{
			vo.setSrch_access_yn(StringUtil.bvl(vo.getSrch_access_yn(),"P")); 
		}
		
		List listUser = null;
		
//		vo.setRole_cd("RE01");		
//		vo.setDivision_cd("SC0101");
		listUser =  commonDAO.list("secfw.useraccess.list",vo);
	
		result.put("listUser",listUser);
		
		return result;
	}	
	
	/**
	 * 사용승인 정보 저장 - 
	 * @param form UserManagerVO
	 * @return String
	 * @throws Exception
	 */		
	public int SaveUserAccess(UserManagerVO vo) throws Exception {
		int result_msg = 0;
		
//		if("SC0101".equals(svo.getGubn_cd())){
//			vo.setRole_cd("RE01");
//		} else {
//			vo.setRole_cd("RE02");
//		}
		
//		svo.setSession_user_id(vo.getSession_user_id());
//		svo.setComp_cd(vo.getComp_cd());
//		svo.setSeal_ffmtman_id(vo.getUser_id());
		
		// TB_COM_USER_ROLE 테이블은 INSERT/DELETE 처리 , TN_CLM_PRTB_CSTB_INSA 은 DEL_YN 을 업데이트 하는 형식으로 처리한다.		
//		if("N".equals(vo.getAuth_apnt_yn())){
//			result_msg = commonDAO.modify("secfw.user.AuthSignInfo.delete", vo);
//			if(result_msg==1){
//				commonDAO.modify("secfw.user.TN_CLM_PRTB_CSTB_INSA.delete", svo);
//			}
//		} else {
//			
//			List cnt1 =  commonDAO.list("secfw.user.AuthSignInfo.count",vo);
//			List cnt2 =  commonDAO.list("secfw.user.TN_CLM_PRTB_CSTB_INSA.count",svo);
//			
//			// 구분코드 ( SC0101: 날인 담당자  / SC0102:증명서류 발급자
//			if(cnt1.size()==0){
//				result_msg = commonDAO.insert("secfw.user.AuthSignInfo.insert", vo);
//			} else {
//				result_msg =1;
//			}
//
//			if(result_msg==1){ //  트랜잭션 처리 : result_msg =1 일 경우에만 업데이트
//				if(cnt2.size()==0){
//					commonDAO.insert("secfw.user.TN_CLM_PRTB_CSTB_INSA.insert", svo);
//				} else {
//					commonDAO.modify("secfw.user.TN_CLM_PRTB_CSTB_INSA.update", svo);
//				}
//			}
//		}
		
		commonDAO.modify("secfw.useraccess.update",vo);
		
		return result_msg;
	}

	@Override
	public int updateUser(HashMap userInfoMap) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.modify("secfw.usermng.user.update", userInfoMap);
	}

	@Override
	public int insertUserInfo(HashMap userInfoMap) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.insert("secfw.usermng.user.insertNew", userInfoMap);
	}

	@Override
	public void insertRoleUser(AuthVO tvo) throws Exception {
		// TODO Auto-generated method stub
		commonDAO.insert("secfw.role.insertRoleUser", tvo);
	}
}
