/**
* Project Name : 계약관리시스템
* File Name : ShareBoardController.java
* Description : 공지사항 Controller(일반 조회용)
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.counsel.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sec.clm.admin.control.BoardController;

public class NoticeBoardController extends CommonController {
	private BoardController adminBoardControl;
	
	public void setAdminBoardControl(BoardController adminBoardControl) {
		this.adminBoardControl = adminBoardControl;
	}

	/**
	* 공지사항 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		ModelAndView mav = new ModelAndView();
		mav = adminBoardControl.listBoard(request, response);
		
		return mav;
	}

	/**
	* 공지사항 상세화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = adminBoardControl.detailBoard(request, response);
		
		return  mav;
	}

}