/**
 * Project Name : 계약관리시스템
 * File name	: ApprovalPathServiceImpl.java
 * Description	: 결재라인 path관리 Service impl(concrete class)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.ApprovalPathVO;
import com.sec.clm.admin.service.ApprovalPathService;
import com.sds.secframework.user.service.UserService;

/**
 * Description	: Service impl(concrete class)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public class ApprovalPathServiceImpl extends CommonServiceImpl implements ApprovalPathService {

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 조회
	 * @param vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	public List listApprovalPath(ApprovalPathVO vo) throws Exception {
		
		//Cross-site Scripting 방0지
		vo.setSrch_path_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_path_name(),"")));
		vo.setSrch_use_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_use_yn(),"")));
		vo.setSrch_loc_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_loc_gbn(),"")));
//		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_comp_cd(),"")));
		
		return commonDAO.list("clm.admin.listApprovalPath", vo);
	}
	
	

	
	/**
	 * 등록
	 * @param  vo ApprovalPathVO
	 * @return String
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public int insertApprovalPath(ApprovalPathVO vo) throws Exception {
	
		//Cross-site Scripting 방지
//		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_comp_cd(),"")));
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		vo.setPath_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPath_nm(),"")));
		vo.setComments(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComments(),"")));
		vo.setPriority(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPriority(),"")));
		vo.setPriority(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPriority(),"")));
		vo.setCondition(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCondition(),"")));
		vo.setMandatory(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMandatory(),"")));
		vo.setUse_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getUse_yn(),"")));
		vo.setAss_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAss_id_list(),"")));
		vo.setReg_id((vo.getSession_user_id()));
		
		
		vo.setOperation(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getOperation(),"")));
		vo.setType_1(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_1(),"")));
		vo.setType_2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_2(),"")));
		vo.setType_3(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_3(),"")));
		vo.setType_4(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_4(),"")));
		
		 		
		//Path id key
		List path_id =  commonDAO.list("clm.admin.PathidKey");
		ListOrderedMap map = (ListOrderedMap)path_id.get(0);
		vo.setPath_id(map.get("path_id").toString());
		
		//codition detail INSERT
		
		String condi_list[] = vo.getInput_condi_list().split("/");//condition
		String condi_option[] = vo.getInput_condi_option().split("/");//operation
		String condi_val[] = vo.getInput_condi_val().split("/");//value

		for(int i=0; i < condi_val.length; i++) {
			if(!"".equals(condi_val[i])){
				vo.setCondition_id(i);
				vo.setCondi_list_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(condi_list[i],"")));
				vo.setCondi_option_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(condi_option[i],"")));
				vo.setCondi_val_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(condi_val[i],"")));
				commonDAO.insert("clm.admin.insertApprovalPathDetail", vo);
			}
		}
		
		
		//Approval Path List INSERT
		String user_id[] = vo.getAss_id_list().split("/");
		String atype_val[] = vo.getAtype_val().split("/");
		String rtype_val[] = vo.getRtype_val().split("/");


		for(int i=0; i < user_id.length; i++) {
			if(!"".equals(user_id[i])){
				vo.setUser_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(user_id[i],"")));
				vo.setAtype_val_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(atype_val[i],"")));
				vo.setRtype_val_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(rtype_val[i],"")));
				vo.setSort_no(String.valueOf(((i+1))));
				commonDAO.insert("clm.admin.insertApprovalPathUserlist", vo);
			}
		}
		
		
		
		return commonDAO.insert("clm.admin.insertApprovalPath", vo);
	}
	
	/**
	 * 수정
	 * @param  vo ApprovalPathVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyApprovalPath(ApprovalPathVO vo) throws Exception {

	

		
			//Cross-site Scripting 방지
//				vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_comp_cd(),"")));
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		vo.setPath_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPath_nm(),"")));
		vo.setComments(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComments(),"")));
		vo.setPriority(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPriority(),"")));
		vo.setPriority(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPriority(),"")));
		vo.setCondition(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCondition(),"")));
		vo.setMandatory(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMandatory(),"")));
		vo.setUse_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getUse_yn(),"")));
		vo.setAss_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAss_id_list(),"")));
		vo.setReg_id((vo.getSession_user_id()));
		vo.setSort_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSort_no(),"")));
		
		
		vo.setOperation(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getOperation(),"")));
		vo.setType_1(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_1(),"")));
		vo.setType_2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_2(),"")));
		vo.setType_3(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_3(),"")));
		vo.setType_4(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_4(),"")));

		
		
		vo.setPath_id(vo.getPath_id());
		
		commonDAO.delete("clm.admin.deleteApprovalPath", vo);

		commonDAO.delete("clm.admin.deleteApprovalDetail", vo);
		
		//codition detail INSERT
		
		String condi_list[] = vo.getInput_condi_list().split("/");//condition
		String condi_option[] = vo.getInput_condi_option().split("/");//operation
		String condi_val[] = vo.getInput_condi_val().split("/");//value

		for(int i=0; i < condi_val.length; i++) {
			if(!"".equals(condi_list[i])){
				vo.setCondition_id(i);
				vo.setCondi_list_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(condi_list[i],"")));
				vo.setCondi_option_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(condi_option[i],"")));
				vo.setCondi_val_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(condi_val[i],"")));
				commonDAO.insert("clm.admin.insertApprovalPathDetail", vo);
			}
		}
		
		
		//Approval Path List INSERT
		String user_id[] = vo.getAss_id_list().split("/");
		String atype_val[] = vo.getAtype_val().split("/");
		String rtype_val[] = vo.getRtype_val().split("/");
		String Sort_val[] = vo.getSort_no().split("/");//sort_no

		for(int i=0; i < user_id.length; i++) {
			if(!"".equals(user_id[i])){
				vo.setUser_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(user_id[i],"")));
				vo.setAtype_val_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(atype_val[i],"")));
				vo.setRtype_val_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(rtype_val[i],"")));
				vo.setSort_no(StringUtil.convertHtmlTochars(StringUtil.bvl(Sort_val[i],"")));
				commonDAO.insert("clm.admin.insertApprovalPathUserlist", vo);
			}
		}
		
		return commonDAO.modify("clm.admin.modifyApprovalPath", vo);
	}
	
	/**
	 * 삭제
	 * @param  vo ApprovalPathVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteApprovalPath(ApprovalPathVO vo) throws Exception {
		
		//Cross-site Scripting 방지
		vo.setDel_id((vo.getSession_user_id()));
		vo.setPath_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPath_id(),"")));
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		
		commonDAO.modify("clm.admin.deleteApprovalPathYn", vo);
		commonDAO.modify("clm.admin.deleteApprovalPathContDetail", vo);
		
		return commonDAO.modify("clm.admin.deleteApprovalPathCont", vo);
	}
	
	/**
	 * 상세조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	public List detailApprovalPath(ApprovalPathVO vo) throws Exception {
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		return commonDAO.list("clm.admin.detailApprovalPath", vo);
	}
	
	/**
	 * detail상세조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	public List detailApprovalPathDetail(ApprovalPathVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		vo.setPath_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPath_id(),"")));
		return commonDAO.list("clm.admin.detailApprovalPathDetail", vo);
	}
	
	
	/**
	 * AssignedUsers상세조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	public List detailApprovalUsers(ApprovalPathVO vo) throws Exception {
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
		return commonDAO.list("clm.admin.detailApprovalUsers", vo);
	}
	
	/**
	 * contract type 조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	public List listCombolist(ApprovalPathVO vo) throws Exception {
		
		return commonDAO.list("clm.admin.combolist", vo);
	}
	
	
	/**
	 * rolelist 조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	public List roleCombolist(ApprovalPathVO vo) throws Exception {
		return commonDAO.list("clm.admin.roleCombolist", vo);
	}
	
	/**
	 * User 조회등록
	 * @param  vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	public void UsersInfo(ApprovalPathVO vo) throws Exception {

//		vo.setAss_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAss_id_list(),"")));
//				
//		String user_id[] = vo.getAss_id_list().split("/");
//		
//		ListOrderedMap result = null;
//		List resultList = null;
//		
//		if("u".equals(vo.getR_type())){
//
//			for(int i=0; i < user_id.length; i++) {
//				if(!"".equals(user_id[i])){
//					vo.setUser_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(user_id[i],"")));
//					resultList = commonDAO.list("clm.admin.RoleUsersInfo", vo);
//					result = (ListOrderedMap)resultList.get(0);
//					//유저정보 등록	
//					userService.processClmsUserInfo(vo.getUser_id_tmp(), "LAS", result.get("val").toString());
//				}
//			}
//		}
		
		vo.setAss_id_list(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAss_id_list(),"")));
		
		String user_id[] = vo.getAss_id_list().split("/");
		String rtype_val[] = vo.getRtype_val().split("/");
		
		ListOrderedMap result = null;
		List resultList = null;
		

		for(int i=0; i < user_id.length; i++) {
			if(!"".equals(user_id[i]) && "u".equals(rtype_val[i])){
				vo.setUser_id_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(user_id[i],"")));
				resultList = commonDAO.list("clm.admin.RoleUsersInfo", vo);
				result = (ListOrderedMap)resultList.get(0);
				//유저정보 등록	
				userService.processClmsUserInfo(vo.getUser_id_tmp(), "LAS", result.get("val").toString());
			}
		}

	}
	
	/**
	 * excel 조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	public List listApprovalPathExcel(ApprovalPathVO vo) throws Exception {
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoc_gbn(),"")));
//		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_comp_cd(),"")));
		return commonDAO.list("clm.admin.listApprovalPathExcel", vo);
	}

	
}
