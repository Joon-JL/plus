package com.sds.secframework.user.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.user.dto.UserSortingManagerVO;
import com.sds.secframework.user.service.UserSortingManagerService;

public class UserSortingManagerServiceImpl extends CommonServiceImpl implements UserSortingManagerService{
	
	
	public List listSortingUser(UserSortingManagerVO vo) throws Exception{
		return commonDAO.list("secfw.usersortingmng.list.blngt_orgnz", vo);
	}
	
	public boolean saveSortingUser(UserSortingManagerVO vo) throws Exception{
		boolean retFlag = false;
		
		if(vo.getUser_id_arr() != null && vo.getUser_id_arr().length > 0){
			
			for(int idx=0;idx<vo.getUser_id_arr().length; idx++){
				
				vo.setUser_id(vo.getUser_id_arr()[idx]);
				vo.setUser_srt(idx+1);
				
				commonDAO.modify("secfw.usersortingmng.modify.blngt_orgnz", vo);
			}
		}
		
		retFlag = true;
		return retFlag;
	}
}
