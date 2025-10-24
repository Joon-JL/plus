/**
 * Project Name : 계약관리시스템
 * File name	: RoleServiceImpl.java
 * Description	: 결재라인Role관리 Service impl(concrete class)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.RoleVO;
import com.sec.clm.admin.service.RoleService;
import com.sds.secframework.user.service.UserService;

import flex.messaging.io.ArrayList;



/**
 * Description	: Service impl(concrete class)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public class RoleServiceImpl extends CommonServiceImpl implements RoleService {

	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	/**
	 * 
	 * 조회
	 * @param vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	public List listRole(RoleVO vo) throws Exception {
		
		//Cross-site Scripting 방0지
		vo.setSrch_role_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_role_name(),"")));
		vo.setSrch_use_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_use_yn(),"")));
		vo.setSrch_loc_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_loc_gbn(),"")));
//		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		
		return commonDAO.list("clm.admin.listRole", vo);
	}
	
	

	
	/**
	 * 등록
	 * @param  vo RoleVO
	 * @return String
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public int insertRole(RoleVO vo) throws Exception {
	
		//Cross-site Scripting 방지
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		vo.setRole_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRole_name(),"")));
		vo.setDescription(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDescription(),"")));
		vo.setUse_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getUse_yn(),"")));
		vo.setAss_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAss_id_list(),"")));
		vo.setReg_id((vo.getSession_user_id()));
		vo.setReview_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getReview_yn(),"")));
				
		//role key
		List role_id =  commonDAO.list("clm.admin.codeKey");
		ListOrderedMap map = (ListOrderedMap)role_id.get(0);
		vo.setRole_cd(map.get("role_cd").toString());
		

		String user_id[] = vo.getAss_id_list().split("/");
		
		
		for(int i=0; i < user_id.length; i++) {
			if(!"".equals(user_id[i])){
				vo.setUser_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(user_id[i],"")));
				vo.setRev_id_tmp(vo.getReview_yn());
				commonDAO.insert("clm.admin.insertRolePath", vo);

			}
		}	
		
		return commonDAO.insert("clm.admin.insertRole", vo);
	}
	
	/**
	 * 수정
	 * @param  vo RoleVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyRole(RoleVO vo) throws Exception {

		//Cross-site Scripting 방지
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		vo.setRole_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRole_name(),"")));
		vo.setDescription(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDescription(),"")));
		vo.setUse_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getUse_yn(),"")));
		vo.setAss_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAss_id_list(),"")));
		vo.setReg_id((vo.getSession_user_id()));
		vo.setRev_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRev_id_list(),"")));
		
		commonDAO.delete("clm.admin.deleteRolePath", vo);
		
		String user_id[] = vo.getAss_id_list().split("/");
		String Rev_id[] = vo.getRev_id_list().split("/");
		
		for(int i=0; i < user_id.length; i++) {
			if(!"".equals(user_id[i])){
				vo.setUser_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(user_id[i],"")));
				vo.setRev_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(Rev_id[i],"")));
				
				commonDAO.insert("clm.admin.insertRolePath", vo);
			}
		}
		
		return commonDAO.modify("clm.admin.modifyRole", vo);
	}
	
	/**
	 * 삭제
	 * @param  vo RoleVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteRole(RoleVO vo) throws Exception {
		
		//Cross-site Scripting 방지
		vo.setDel_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
			
		commonDAO.delete("clm.admin.deleteRolePath", vo);
		return commonDAO.modify("clm.admin.deleteRole", vo);
	}
	
	/**
	 * 상세조회
	 * @param  vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	public List detailRole(RoleVO vo) throws Exception {
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		return commonDAO.list("clm.admin.detailRole", vo);
	}
	
	/**
	 * AssignedUsers상세조회
	 * @param  vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	public List detailAssignedUsers(RoleVO vo) throws Exception {
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		return commonDAO.list("clm.admin.detailAssignedUsers", vo);
	}
	
	/**
	 * User 조회등록
	 * @param  vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	public void UsersInfo(RoleVO vo) throws Exception {
		
		vo.setAss_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAss_id_list(),"")));
				
		String user_id[] = vo.getAss_id_list().split("/");
		
		ListOrderedMap result = null;
		List resultList = null;
		
		

		for(int i=0; i < user_id.length; i++) {
			if(!"".equals(user_id[i])){
				vo.setUser_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(user_id[i],"")));
				
				resultList = commonDAO.list("clm.admin.RoleUsersInfo", vo);
				result = (ListOrderedMap)resultList.get(0);
				//유저정보 등록	
				userService.processClmsUserInfo(vo.getUser_id_tmp(), "LAS", result.get("val").toString());
			}
		}

	}
	
	/**
	 * 
	 * 조회
	 * @param vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	public List reviewAutolist(RoleVO vo) throws Exception {
		
		//Cross-site Scripting 방지
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		
		return commonDAO.list("clm.admin.reviewAutolist", vo);
	}	
	
	/**
	 * 
	 * checkApprovalYn 조회
	 * @param vo RoleVO
	 * @return int
	 * @throws Exception
	 */
	public int checkApprovalYn(RoleVO vo) throws Exception {
		
		//Cross-site Scripting 방지
		
		vo.setRole_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRole_cd(),"")));
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		List returnVal  = new ArrayList(); 
		returnVal = commonDAO.list("clm.admin.checkApprovalYn", vo);
		
		int intval = 0;
		
		if(returnVal.size() > 0){
			intval = 1;
		}
		
		return intval;
	}
	
}
