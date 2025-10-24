package com.sds.secframework.main.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.jfree.util.Log;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.main.dto.MainForm;
import com.sds.secframework.main.dto.MainVO;
import com.sds.secframework.main.service.MainService;
import com.sds.secframework.menu.service.MenuService;
import com.sds.secframework.user.service.UserService;
import com.sec.clm.admin.dto.BoardForm;
import com.sec.clm.admin.dto.BoardVO;
import com.sec.clm.admin.service.BoardService;
import com.sec.clm.manage.dto.ManageForm;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.clm.manage.dto.MyNoticeForm;
import com.sec.clm.manage.dto.MyNoticeVO;
import com.sec.clm.manage.service.ManageService;
import com.sec.clm.manage.service.MyNoticeService;
import com.sec.clm.review.dto.ConsiderationVO;
import com.sec.clm.review.service.ConsiderationService;
import com.sec.common.util.ClmsBoardUtil;
import com.sec.common.util.ClmsDataUtil;
import com.sec.las.board.dto.NoticeForm;
import com.sec.las.board.dto.NoticeVO;
import com.sec.las.board.service.NoticeService;
import com.sec.las.lawconsulting.dto.LawConsultForm;
import com.sec.las.lawconsulting.dto.LawConsultVO;
import com.sec.las.lawconsulting.service.LawConsultService;
import com.sec.las.lawinformation.dto.MainLawInfoForm;
import com.sec.las.lawinformation.dto.MainLawInfoVO;
import com.sec.las.lawinformation.service.MainLawInfoService;

public class MainController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private MainService mainService;
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	/**
	 * 메뉴 서비스 세팅
	 * @param noticeService 메뉴 서비스
	 */
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * 계약관리 - 공지사항 관련 서비스
	 */
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * 계약관리 - My Approval 관련 서비스
	 */
	private ManageService manageService;
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	/**
	 * 계약관리 - MyNoticeController 서비스
	 */
	private MyNoticeService myNoticeService;
	public void setMyNoticeService(MyNoticeService myNoticeService) {
		this.myNoticeService = myNoticeService;
	}

	/**
	 * 법무 - 공지사항 관련 서비스
	 */
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	/**
	 * 법무 - 입법동향 관련 서비스
	 */
	private MainLawInfoService mainLawInfoService;
	public void setMainLawInfoService(MainLawInfoService mainLawInfoService) {
		this.mainLawInfoService = mainLawInfoService;
	}

	/**
	 * 법무 - 법률자문 관련 서비스
	 */
	private LawConsultService lawConsultService;
	public void setLawConsultService(LawConsultService lawConsultService){
		this.lawConsultService = lawConsultService;
	}

	/**
	 * 법무 - 계약검토 관련 서비스
	 */
	private ConsiderationService considerationService;
	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 로그인 시 메인페이지로 이동
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView forwardMainPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);

		String radioChk = (String)request.getParameter("role_cd");

		if ((!"".equals(radioChk)) && (radioChk != null)) {
			// 역할 재설정
			ArrayList tmpRoleList = new ArrayList();
			List tmpSessionRoleList = (List)session.getAttribute("secfw.session.role");

			for (int i=0; i<tmpSessionRoleList.size(); i++) {
				Map tmpSessionRoleMap = (Map)tmpSessionRoleList.get(i) ;

				if (tmpSessionRoleMap.containsKey("role_cd")) {
					tmpSessionRoleMap.remove("role_cd");
					tmpSessionRoleMap.remove("role_nm");
					tmpSessionRoleMap.put("role_cd", (String)request.getParameter("role_cd"));
					tmpSessionRoleMap.put("role_nm", (String)request.getParameter("role_nm"));
				}
				
				tmpRoleList.add(i, tmpSessionRoleMap);
			}
			
			session.removeAttribute("secfw.session.role");
			session.setAttribute("secfw.session.role",  tmpRoleList);

			session.setAttribute("secfw.session.user_id",				(String)request.getParameter("user_id"));
			session.setAttribute("secfw.session.user_nm",				(String)request.getParameter("user_nm"));
			session.setAttribute("secfw.session.up_level_dept_cd",		(String)request.getParameter("up_level_dept_cd"));
			session.setAttribute("secfw.session.blngt_orgnz",			(String)request.getParameter("blngt_orgnz"));
			session.setAttribute("secfw.session.blngt_orgnz_nm",		(String)request.getParameter("blngt_orgnz_nm"));
			session.setAttribute("secfw.session.blngt_orgnz_nm_abbr",	(String)request.getParameter("blngt_orgnz_nm_abbr"));
			// 2012.05.29 사업부 담당자 관련 세션 추가 by 한지훈
			session.setAttribute("secfw.session_clms_user_orgnz",		(String)request.getParameter("clms_user_orgnz"));
			session.setAttribute("secfw.session_resp_operdiv",			(String)request.getParameter("resp_operdiv"));

			session.setAttribute("secfw.session.manager_yn",			(String)request.getParameter("manager_yn"));
			session.setAttribute("secfw.session.support_yn",			(String)request.getParameter("support_yn"));
			session.setAttribute("secfw.session.language_flag",			"en");
			session.setAttribute("secfw.server.division_gbn",			propertyService.getProperty("secfw.server.division")); // 개발서버와 운영서버 구분값. 다국어 오픈 전 개발서버에만 독어/불어 옵션 선택 가능.
			session.setAttribute("secfw.session.locale",				StringUtil.bvl("en", ""));
		}
		//=================================== 처리 end =======================================
		
		String chgLangflag = StringUtil.bvl(request.getParameter("chgLangflag"),"");

		if (!"".equals(chgLangflag)) {
			Locale locale = null;

			if ("ko".equals(chgLangflag)) {
				locale = new Locale("ko");
			} else if ("zh".equals(chgLangflag)) {
				locale = new Locale("zh");
			} else if ("ja".equals(chgLangflag)) {
				locale = new Locale("ja");
			} else if ("fr".equals(chgLangflag)) {
				locale = new Locale("fr");
		/*	} else if ("de".equals(chgLangflag)) {
				locale = new Locale("de");   */
			} else if ("it".equals(chgLangflag)) {
				locale = new Locale("it");
			} else if ("es".equals(chgLangflag)) {
				locale = new Locale("es");
			} else {
				locale = new Locale("en");
			}

			// 싱글 선택 언어(ep_local)임의값을 세션에 적용.
			session.setAttribute("secfw.session.language_flag", chgLangflag);
			session.setAttribute("secfw.server.division_gbn",		propertyService.getProperty("secfw.server.division")); // 개발서버와 운영서버 구분값. 다국어 오픈 전 개발서버에만 독어/불어 옵션 선택 가능.
			// localeResolver에 locale 셋팅
			localeResolver.setLocale(request, response, locale);
		}
		
		/*********************************************************
		 * 사용자 로케일 정보에 따른 변경
		 **********************************************************/
		Locale lc = (Locale) localeResolver.resolveLocale(request);

		/*- TOP JSP 로직 카피 - 김정곤 2011년4월1일*/
		/*********************************************************
		 * 변수선언
		 **********************************************************/
		ModelAndView mav  = new ModelAndView();
		String forwardURL = propertyService.getProperty("secfw.url.mainPageLas");

		try {
			String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
			String MenuId = propertyService.getProperty("secfw.menu.topMenuId");
			String userId = (String)session.getAttribute("secfw.session.user_id");
			String compCd = (String)session.getAttribute("secfw.session.comp_cd");
			
			/*********************************************************
			 * bind Form, VO
			 **********************************************************/
			MainForm form = new MainForm();
			MainVO vo     = new MainVO();

			bind(request, form);
			bind(request, vo);

			vo.setSys_cd(sysCd);
			vo.setMenu_id(MenuId);
			vo.setUser_id(userId);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * business logic
			 **********************************************************/
			HashMap paramMap = new HashMap();
			paramMap.put("menuGbn",				StringUtil.bvl("TOP", ""));
			paramMap.put("targetMenuId",		StringUtil.bvl(form.getTarget_menu_id(), ""));
			paramMap.put("sys_cd",				sysCd);
			paramMap.put("user_id",				userId);
			paramMap.put("session_blngt_orgnz",	(String)session.getAttribute("secfw.session.blngt_orgnz"));
			paramMap.put("comp_cd",				(String)session.getAttribute("secfw.session.comp_cd"));

			HashMap hm    = mainService.listMenu(paramMap); // 메뉴 목록
			List menuList = (List)hm.get("menuList");

			Map map = new HashMap();
			int Level_One = 0;

			if (menuList != null && menuList.size() > 0) {
				String compMenuId = "";

				for (int i = 0; i < menuList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap) menuList.get(i);

					if ("1".equals(StringUtil.bvl(lom.get("menu_level"), ""))) {
						compMenuId = (String)lom.get("menu_id");
						map.put(compMenuId, new Integer(0));
						Level_One++;
					} 
				}
			}

			// 1레벨의 사이즈
			mav.addObject("menuSize", Integer.toString(Level_One));
			

			Map freeMarkerMap = new HashMap();
			freeMarkerMap.put("targetMenuId", StringUtil.bvl(request.getParameter("target_menu_id"), ""));
			freeMarkerMap.put("topMenuLevel", StringUtil.bvl(propertyService.getProperty("secfw.menu.topMenuLevel"), "1")); // 상단 메뉴 레벨

			mav.addObject("targetMenuId",	StringUtil.bvl(form.getTarget_menu_id(), ""));
			mav.addObject("menuList",		menuList);
			mav.addObject("freeMarkerMap",	freeMarkerMap);
			mav.addObject("topMenuLevel",	StringUtil.bvl(propertyService.getProperty("secfw.menu.topMenuLevel"), "1")); // 상단 메뉴 레벨

			// !@# 회사별 메인이미지 URL /images/las/<%=langCd%>/common/main_img2-1_<%=compCd %>.jpg
			String imgCompCd   = "_" + compCd;
			String mainImgNm   = "";
			String mainBgImgNm = "";
			String existCompYn = (String)session.getAttribute("secfw.session.exist_comp_yn");

			if ("Y".equals(existCompYn)) {// 12개사에 속하는 사람인 경우
				mainImgNm   = propertyService.getProperty("secfw.image.name.mainImgNm",   new Object[] {(Object)imgCompCd});
				mainBgImgNm = propertyService.getProperty("secfw.image.name.mainBgImgNm", new Object[] {(Object)imgCompCd});
			} else {
				mainImgNm   = propertyService.getProperty("secfw.image.name.mainImgNm",   new Object[] {""});
				mainBgImgNm = propertyService.getProperty("secfw.image.name.mainBgImgNm", new Object[] {""});
			}

			mav.addObject("mainImgNm", mainImgNm);
			mav.addObject("mainBgImgNm", mainBgImgNm);
			
			mav.addObject("lasBoardList", getLasBoardList(request, response)); // 공지사항 목록
			mav.addObject("latestNoticeSeqno",getLatestNoticeSeqno(request, response));
			
			//------------------------------- 메인 카운트 부분 시작 -----------------------------------------------------------
			//if("LAS".equals(sysCd)){
				ConsiderationVO cntvo = new ConsiderationVO();
				LawConsultVO lascntvo = new LawConsultVO();
				bind(request, cntvo);
				bind(request, lascntvo);
				COMUtil.getUserAuditInfo(request, cntvo);
				COMUtil.getUserAuditInfo(request, lascntvo);

				ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
				if(tmpSessionRoleList.contains("RA01")){
					cntvo.setTop_role("RA01");
					lascntvo.setTop_role("RA01");
				} else if(tmpSessionRoleList.contains("RA02")){
					cntvo.setTop_role("RA02");
					lascntvo.setTop_role("RA02");
				} else if(tmpSessionRoleList.contains("RB01")){
					cntvo.setTop_role("RB01");
					lascntvo.setTop_role("RB01");
				} else if(tmpSessionRoleList.contains("HQ01")){	//HQ Admin
					cntvo.setTop_role("HQ01");
					lascntvo.setTop_role("HQ01");
				} else if(tmpSessionRoleList.contains("HQ02")){	//HQ Reviewer
					cntvo.setTop_role("HQ02");
					lascntvo.setTop_role("HQ02");	
				} else {
					cntvo.setTop_role("ETC");
					lascntvo.setTop_role("ETC");
				}
				mav.addObject("topRole", lascntvo.getTop_role());
				
				//의뢰자 계약 카운트
				int cntCont01 = 0;
				int cntCont02 = 0;
				int cntCont03 = 0;
				int cntCont04 = 0;
				int cntCont05 = 0;
				int cntNotServed = 0; //원본미등록(C02642) 건 조회
				if("ETC".equals(lascntvo.getTop_role())){
					HashMap cntContMap = getStatusCount(request, response, (String)cntvo.getTop_role());
					
					cntCont01 = Integer.parseInt(String.valueOf(cntContMap.get("C02501")    == null ? "0" : cntContMap.get("C02501"))); //검토
					cntCont02 = Integer.parseInt(String.valueOf(cntContMap.get("C02502")    == null ? "0" : cntContMap.get("C02502"))); //체결
					cntCont03 = Integer.parseInt(String.valueOf(cntContMap.get("C02503")    == null ? "0" : cntContMap.get("C02503"))); //등록
					cntCont04 = Integer.parseInt(String.valueOf(cntContMap.get("C02504")    == null ? "0" : cntContMap.get("C02504"))); //이행
					cntCont05 = Integer.parseInt(String.valueOf(cntContMap.get("C02505")    == null ? "0" : cntContMap.get("C02505"))); //종료
				}
				
				//계약관리 시스템 메인 - 원본미등록(C02642) 건 조회
				HashMap notServedMap = new HashMap();
				notServedMap.put("user_id", StringUtil.bvl(session.getAttribute("secfw.session.user_id"), ""));
				notServedMap.put("only_mine_yn", "ETC".equals(lascntvo.getTop_role()) ? "Y" : "N"); //ETC인 경우 본인건만 조회
				cntNotServed = Integer.parseInt(mainService.getNotServed(notServedMap));
				
				mav.addObject("cntCont01", cntCont01);	//검토
				mav.addObject("cntCont02", cntCont02);	//체결
				mav.addObject("cntCont03", cntCont03);	//등록
				mav.addObject("cntCont04", cntCont04);	//이행
				mav.addObject("cntCont05", cntCont05);	//종료
				mav.addObject("cntNotServed", cntNotServed);	//원본미등록(C02642) 건 조회
				
				//본인의 자동연장 건수
				mav.addObject("countAutoExtension", Integer.parseInt(mainService.countAutoExtension(notServedMap)));

				
				// 계약 카운트
				List listCountConsideration = considerationService.countConsideration(cntvo);

				int cntTotal    = 0;
				int cntUnassign = 0;
				int cntConsider = 0;
				int cntResign	= 0;
				int cntTempsave = 0;
				int cntPendding = 0;
				int cntTransfer = 0;
				
				int cntReviewinProgress = 0;
				int cntSaved = 0;
				int cntNotapproved = 0;
				
				if(listCountConsideration != null && listCountConsideration.size() > 0) {
					ListOrderedMap clm = (ListOrderedMap)listCountConsideration.get(0);

					cntTotal    = Integer.parseInt(String.valueOf(clm.get("TOTAL_CNT")    == null ? "0" : clm.get("TOTAL_CNT")));
					cntUnassign = Integer.parseInt(String.valueOf(clm.get("UNASSIGN_CNT") == null ? "0" : clm.get("UNASSIGN_CNT")));
					cntConsider = Integer.parseInt(String.valueOf(clm.get("CONSIDER_CNT") == null ? "0" : clm.get("CONSIDER_CNT")));
					cntResign   = Integer.parseInt(String.valueOf(clm.get("RESIGN_CNT")   == null ? "0" : clm.get("RESIGN_CNT")));
					cntTempsave = Integer.parseInt(String.valueOf(clm.get("TEMPSAVE_CNT") == null ? "0" : clm.get("TEMPSAVE_CNT")));
					cntPendding = Integer.parseInt(String.valueOf(clm.get("PENDDING_CNT") == null ? "0" : clm.get("PENDDING_CNT")));
					cntTransfer = Integer.parseInt(String.valueOf(clm.get("TRANSFER_CNT") == null ? "0" : clm.get("TRANSFER_CNT")));
					
					cntReviewinProgress = Integer.parseInt(String.valueOf(clm.get("REVIEW_IN_PROGRESS_CNT") == null ? "0" : clm.get("REVIEW_IN_PROGRESS_CNT")));
					cntSaved = Integer.parseInt(String.valueOf(clm.get("SAVED_CNT") == null ? "0" : clm.get("SAVED_CNT")));
					cntNotapproved = Integer.parseInt(String.valueOf(clm.get("NOT_APPROVED_CNT") == null ? "0" : clm.get("NOT_APPROVED_CNT")));
				}

				mav.addObject("cntTotal", cntTotal);		//게약검토 현황 카운트 - 전체
				mav.addObject("cntUnassign", cntUnassign);	//게약검토 현황 카운트 - 미배정
				mav.addObject("cntConsider", cntConsider);	//게약검토 현황 카운트 - 검토중
				mav.addObject("cntResign", cntResign);		//게약검토 현황 카운트 - 재배정
				mav.addObject("cntTempsave", cntTempsave);	//게약검토 현황 카운트 - 임시저장
				mav.addObject("cntPendding", cntPendding);	//게약검토 현황 카운트 - 미결
				mav.addObject("cntTransfer", cntTransfer);	//게약검토 현황 카운트 - 이관요청
				
				mav.addObject("cntReviewinProgress", cntReviewinProgress);	//게약검토 현황 카운트 - HQ - Review In Progress
				mav.addObject("cntSaved", cntSaved);						//게약검토 현황 카운트 - HQ - Saved
				mav.addObject("cntNotapproved", cntNotapproved);			//게약검토 현황 카운트 - HQ - Not Approved
				mav.addObject("command", form);
				
				// 자문 카운트
				List listCountLawConsult = lawConsultService.countLawConsult(lascntvo);

				int cntLawTotal = 0;
				int cntNoassign = 0;
				int cntReview   = 0;
				int cntLawResign   = 0;
				int cntTemp     = 0;
				int cntPending  = 0;
				int cntReg      = 0;

				if(listCountLawConsult != null && listCountLawConsult.size() > 0) {
					ListOrderedMap cntLom = (ListOrderedMap)listCountLawConsult.get(0);

					cntLawTotal = Integer.parseInt(String.valueOf(cntLom.get("TOTAL_CNT") == null ? "0" : cntLom.get("TOTAL_CNT")));
					cntNoassign = Integer.parseInt(String.valueOf(cntLom.get("NOASSIGN_CNT") == null ? "0" : cntLom.get("NOASSIGN_CNT")));
					cntReview = Integer.parseInt(String.valueOf(cntLom.get("REVIEW_CNT") == null ? "0" : cntLom.get("REVIEW_CNT")));
					cntLawResign = Integer.parseInt(String.valueOf(cntLom.get("RESIGN_CNT") == null ? "0" : cntLom.get("RESIGN_CNT")));
					cntTemp = Integer.parseInt(String.valueOf(cntLom.get("TEMP_CNT") == null ? "0" : cntLom.get("TEMP_CNT")));
					cntPending = Integer.parseInt(String.valueOf(cntLom.get("PENDING_CNT") == null ? "0" : cntLom.get("PENDING_CNT")));
					cntReg = Integer.parseInt(String.valueOf(cntLom.get("REG_CNT") == null ? "0" : cntLom.get("REG_CNT")));
				}

				mav.addObject("lawCntTotal", cntLawTotal);		//법률자문 현황 카운트 - 전체
				mav.addObject("lawCntNoassign", cntNoassign);   //법률자문 현황 카운트 - 미배정
				mav.addObject("lawCntReview", cntReview);       //법률자문 현황 카운트 - 검토중
				mav.addObject("lawCntTemp", cntTemp);           //법률자문 현황 카운트 - 임시저장
				mav.addObject("lawCntPending", cntPending);     //법률자문 현황 카운트 - 미결
				mav.addObject("lawCntResign", cntLawResign);	//법률자문 현황 카운트 - 재배정
				mav.addObject("lawCntReg", cntReg);	            //법률자문 현황 카운트 - 의뢰인 의뢰건

				 
				//계약관리자(RD01)일 경우 계약관리 건수 출력
				if((tmpSessionRoleList.contains("RD01"))){
				
					//계약관리 카운트
					List listCountContractMng = considerationService.countContractMng(cntvo);

					int cntContMngTotal         = 0; //계약관리 - 전체
					int cntContMngOrgRcv        = 0; //계약관리 - 원본접수
					int cntContMngAutoExp       = 0; //계약관리 - 자동연장
					int cntContMngAfterConReg   = 0; //계약관리 - 체결후등록
					int cntContMngStdCont       = 0; //계약관리 - 표준계약서

					if (listCountContractMng != null && listCountContractMng.size() > 0) {
						ListOrderedMap cntLom = (ListOrderedMap)listCountContractMng.get(0);

						cntContMngTotal    = Integer.parseInt(String.valueOf(cntLom.get("TOTAL_CNT")    == null ? "0" : cntLom.get("TOTAL_CNT")));
						cntContMngOrgRcv = Integer.parseInt(String.valueOf(cntLom.get("ORGRCV_CNT") == null ? "0" : cntLom.get("ORGRCV_CNT")));
						cntContMngAutoExp   = Integer.parseInt(String.valueOf(cntLom.get("AUTOEXP_CNT")   == null ? "0" : cntLom.get("AUTOEXP_CNT")));
						cntContMngAfterConReg       = Integer.parseInt(String.valueOf(cntLom.get("AFTERCONREG_CNT") == null ? "0" : cntLom.get("AFTERCONREG_CNT")));
						cntContMngStdCont     = Integer.parseInt(String.valueOf(cntLom.get("STDCONT_CNT")     == null ? "0" : cntLom.get("STDCONT_CNT")));
					}

					mav.addObject("cntContMngTotal", cntContMngTotal);			// 계약관리 현황 카운트 - 전체
					mav.addObject("cntContMngOrgRcv", cntContMngOrgRcv);	    // 계약관리 현황 카운트 - 원본접수
					mav.addObject("cntContMngAutoExp", cntContMngAutoExp);		// 계약관리 현황 카운트 - 자동연장
					mav.addObject("cntContMngAfterConReg", cntContMngAfterConReg);		// 계약관리 현황 카운트 - 체결후등록
					mav.addObject("cntContMngStdCont", cntContMngStdCont);		// 계약관리 현황 카운트 - 표준계약서
					mav.addObject("isCntMng", "Y");
				}
				else{
					mav.addObject("isCntMng", "N");
				}
				
				
				//날인담당자(RE01)의 날인접수 건수 수정 : 날인접수, 증명서류 발급자 카운트 통합
				if(tmpSessionRoleList.contains("RE01") || tmpSessionRoleList.contains("RE02")){
					//계약관리 카운트
					List listCountSeal = considerationService.countSeal(cntvo);
					int cntSealSign         = 0; //날인접수 건수

					if (listCountSeal != null && listCountSeal.size() > 0) {
						ListOrderedMap cntLom = (ListOrderedMap)listCountSeal.get(0);
						cntSealSign    = Integer.parseInt(String.valueOf(cntLom.get("SEALSIGN_CNT")    == null ? "0" : cntLom.get("SEALSIGN_CNT")));
					}
					mav.addObject("cntSealSign", cntSealSign);		// 날인접수 건수
					mav.addObject("isCntSealorSign", "Y");
				}
				else{
					mav.addObject("isCntSealorSign", "N");
				}

			//------------------------------- 메인 카운트 부분 끝 ------------------------------------

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}
		/*- TOP JSP 로직 카피 - 끝*/

		mav.setViewName(forwardURL);
		return mav;
	}

	
	/**
	 * Main Frame 으로 포워드 한다
	 */
	public ModelAndView forwardMainFrame(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 변수선언
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			HttpSession session;
			String user_id="";
			String sysCd = "";
			try{
				/*Session 정보가 없어 Exception 이 발생할 수 있다. */
				session = request.getSession(false);
				user_id = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
				sysCd = (String)session.getAttribute("secfw.session.sys_cd");

				if(user_id.equals("")){
					Log.debug("Need Session Information.! ");
					mav.setViewName("/secfw/ssoCheck.do?method=clmsLogin&secfw.forwardURL=/secfw/main.do?method=forwardMainFrame");
					return mav;
				}
			}catch(Exception e){
				Log.debug("Not Found User Session   :"+e.toString());
				e.printStackTrace() ;
			}

			/*********************************************************
			 * forwardURL
			 **********************************************************/
			String forwardURL = "";

			String sysGbn = sysCd.toLowerCase();
			forwardURL = "/WEB-INF/jsp/"+sysGbn+"/main/FrameMain.jsp";

			/*********************************************************
			 * bind Form, VO
			 **********************************************************/
			MainForm form = new MainForm();
			MainVO vo = new MainVO();

			bind(request, form);
			bind(request, vo);

			mav.setViewName(forwardURL);
			mav.addObject("targetMenuId"			, form.getTarget_menu_id());
			mav.addObject("selected_menu_id"		, form.getSelected_menu_id());
			mav.addObject("selected_menu_detail_id"	, StringUtil.bvl(request.getParameter("selected_menu_detail_id"), ""));
			mav.addObject("set_init_url"			, StringUtil.bvl(request.getParameter("set_init_url"), ""));

			ClmsDataUtil.debug("## targetMenuId : " + form.getTarget_menu_id());
			ClmsDataUtil.debug("## selected_menu_id : " + form.getSelected_menu_id());
			ClmsDataUtil.debug("## selected_menu_detail_id : " + StringUtil.bvl(request.getParameter("selected_menu_detail_id"), ""));
			ClmsDataUtil.debug("##  set_init_url : " + StringUtil.bvl(request.getParameter("set_init_url"), ""));

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error:"+e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error:"+t.getMessage());
		}

	}

	/**
	 * Sub Frame 으로 포워드 한다
	 */
	public ModelAndView forwardSubFrame(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			String sysCd       = 	(String)session.getAttribute("secfw.session.sys_cd");

			/*********************************************************
			 * 변수선언
			 **********************************************************/
			String selectTopMenuId 		= StringUtil.mainBvl(request.getParameter("selectTopMenuId"), "");
			String targetMenuId 		= StringUtil.mainBvl(request.getParameter("target_menu_id"), "");
			String selectedMenuDetailId = StringUtil.mainBvl(request.getParameter("selected_menu_detail_id"), "");

			/*********************************************************
			 * forwardURL
			 **********************************************************/
			String forwardURL = "";
			String sysGbn = sysCd.toLowerCase();

			forwardURL = "/WEB-INF/jsp/"+sysGbn+"/main/FrameSub.jsp";

			/*********************************************************
			 * bind Form, VO
			 **********************************************************/
			MainForm form = new MainForm();
			MainVO vo = new MainVO();

			bind(request, form);
			bind(request, vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("selectTopMenuId"		, StringUtil.convertHtmlTochars(selectTopMenuId));
			mav.addObject("targetMenuId"		, StringUtil.convertHtmlTochars(targetMenuId));
			mav.addObject("selectedMenuId"		, StringUtil.convertHtmlTochars(StringUtil.mainBvl(form.getSelected_menu_id(), "")) );
			mav.addObject("selectedMenuDetailId", StringUtil.convertHtmlTochars(selectedMenuDetailId) ); /*김정곤 추가 2011/04/01 선택된 상세메뉴*/
			mav.addObject("setInitUrl"		    , StringUtil.convertHtmlTochars(StringUtil.mainBvl(request.getParameter("set_init_url"), "")));//선택된URL

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * Top Page로 포워드 한다
	 */
	public ModelAndView forwardTopPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * 변수선언
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

	        String sysCd       	= 	(String)session.getAttribute("secfw.session.sys_cd");
            String MenuId      	=    propertyService.getProperty("secfw.menu.topMenuId");
            String userId 		=   (String)session.getAttribute("secfw.session.user_id");
            String userNm 		=   (String)session.getAttribute("secfw.session.user_nm_en");
            String deptNm		= 	(String)session.getAttribute("secfw.session.dept_nm_en");
            String gradeNm      =   (String)session.getAttribute("secfw.session.grade_nm_en");
            String roleNm		=	"";
            
            
	    	Locale lc = new RequestContext(request).getLocale();
	        String localeCd = StringUtil.bvl(lc.getLanguage(), "en");
	        if(localeCd.equals("ko")){
	        	userNm 		=   (String)session.getAttribute("secfw.session.user_nm");
	            deptNm		= 	(String)session.getAttribute("secfw.session.dept_nm");
	            gradeNm      =   (String)session.getAttribute("secfw.session.grade_nm");
		    }
	        
	        String compCd = (String)session.getAttribute("secfw.session.comp_cd");       // 회사코드

	        // 2014-02-17 Kevin. 기존 한국의 관계사 if 문 모두 삭제.
	    	String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
	    	String titleImage = "system_logo";
	    	

			/*********************************************************
			 * forwardURL
			 **********************************************************/
			/** freeMarker Top.ftl */
			String forwardURL = "";
			String sysGbn = sysCd.toLowerCase();

			forwardURL = sysGbn + "/main/PageTop.ftl";

			/*********************************************************
			 * bind Form, VO
			 **********************************************************/
			MainForm form = new MainForm();
			MainVO vo = new MainVO();

			vo.setSys_cd(sysCd);
			vo.setMenu_id(MenuId);
			vo.setUser_id(userId);

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * business logic
			 **********************************************************/
			HashMap paramMap = new HashMap();


			userNm = StringUtil.bvl(userNm,""); 
	        deptNm = StringUtil.bvl(deptNm,""); 
	        gradeNm = StringUtil.bvl(gradeNm,"");
	        
			paramMap.put("menuGbn"			, StringUtil.convertHtmlTochars(StringUtil.mainBvl(form.getMenu_gbn(), "")));
			paramMap.put("targetMenuId"		, StringUtil.convertHtmlTochars(StringUtil.mainBvl(form.getTarget_menu_id(), "")));
			paramMap.put("selectedMenuId"	, StringUtil.convertHtmlTochars(StringUtil.mainBvl(form.getSelected_menu_id(), "")));
			paramMap.put("sys_cd"			, StringUtil.convertHtmlTochars(sysCd));
			paramMap.put("user_id"			, StringUtil.convertHtmlTochars(userId));
			paramMap.put("comp_cd"			, compCd);

			HashMap hm 			=   mainService.listMenu(paramMap);
			List 	menuList 	= 	(List)hm.get("menuList");


			Map map = new HashMap();
			int Level_One =0;
			if (menuList != null && menuList.size() > 0) {
				String compMenuId = "";

				for (int i = 0; i < menuList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap) menuList.get(i);

					if ("1".equals(StringUtil.bvl(lom.get("menu_level"), ""))) {
						compMenuId = (String) lom.get("menu_id");
						map.put(compMenuId, new Integer(0));
						Level_One++;
					}
				}
			}

			//1레벨의 싸이즈
			mav.addObject("menuSize", Integer.toString(Level_One));

			/***********************************************************************************************/
			// 세션유저의 권한명을 세팅 ( 영문으로 기본설정 ) - 2013.12.17
			List<?> tmpSessionRoleList = (List<?>)session.getAttribute("secfw.session.role");

			for (int i=0; i<tmpSessionRoleList.size(); i++) {
				Map tmpSessionRole = (Map)tmpSessionRoleList.get(i) ;
				
				String roleCd =(String)tmpSessionRole.get("role_cd"); 
				
								
				if(("RA01".equals(roleCd) || "RD01".equals(roleCd) || "RM00".equals(roleCd)) && i == 0){
					roleNm = "Legal Admin";
				}else if(("RA01".equals(roleCd) || "RD01".equals(roleCd) || "RM00".equals(roleCd)) && i != 0){
					if(!roleNm.contains("Legal Admin")){
						roleNm = roleNm + ", Legal Admin";
					}					
				}else if(i == 0){
					roleNm = (String)tmpSessionRole.get("role_nm");
				}else{
					if(!roleNm.contains((String)tmpSessionRole.get("role_nm"))){
						roleNm = roleNm + ", " + (String)tmpSessionRole.get("role_nm");
					}
				}
			}
			/***********************************************************************************************/
			
			
			Map freeMarkerMap = new HashMap();

			freeMarkerMap.put("targetMenuId", StringUtil.convertHtmlTochars(StringUtil.mainBvl(request.getParameter("target_menu_id"),"")));
			freeMarkerMap.put("topMenuLevel", StringUtil.bvl(propertyService.getProperty("secfw.menu.topMenuLevel"), "1"));
			freeMarkerMap.put("selected_menu_detail_id", StringUtil.convertHtmlTochars(StringUtil.mainBvl(request.getParameter("selected_menu_detail_id"), request.getParameter("selectedMenuId"))));


			mav.setViewName(forwardURL);
			mav.addObject("targetMenuId"	, StringUtil.convertHtmlTochars(StringUtil.mainBvl(request.getParameter("target_menu_id"),"")));
			mav.addObject("menuList"		, menuList);
			mav.addObject("freeMarkerMap"	, freeMarkerMap);
			mav.addObject("selected_menu_detail_id", StringUtil.convertHtmlTochars(StringUtil.mainBvl(request.getParameter("selected_menu_detail_id"), request.getParameter("selectedMenuId"))));
			mav.addObject("localeCd"		, localeCd);
			mav.addObject("userInfo"		, userNm+" / "+gradeNm+" / "+deptNm);
			mav.addObject("roleNm"			, roleNm);
			mav.addObject("titleImage"		, titleImage);
			mav.addObject("compCd"			, compCd);

			//2011-10-18 김형준 추가
			mav.addObject("lawfirmAccessFlag", lawfirmAccessFlag(request, "A")); //로펌
			mav.addObject("planAccessFlag", lawfirmAccessFlag(request, "B")); //일정관리
			mav.addObject("clmLoginUrl", propertyService.getProperty("secfw.url.clmLogin"));
			mav.addObject("slmsUrl", propertyService.getProperty("secfw.url.slms"));
			mav.addObject("slmsViewFlag", slmsViewFlag(request));
			mav.addObject("blngt_orgnz", blngt_orgnz);
			mav.addObject("statisticsViewFlag", statisticsViewFlag(request));
			mav.addObject("chgPersonflag", chgPersonflag(request)); //계약서담당자변경
			mav.addObject("consultFlag", consultFlag(request)); //법률자문

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * Left Page로 포워드 한다
	 */
	public ModelAndView forwardLeftPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * 변수선언
			 **********************************************************/
			HttpSession session = request.getSession(false);
			ModelAndView mav = new ModelAndView();

			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	    	Locale lc = new RequestContext(request).getLocale();
	        String localeCd = StringUtil.bvl(lc.getLanguage(), "en");
	        String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");

			/*********************************************************
			 * forwardURL
			 **********************************************************/
			String forwardURL = "";
			String sysGbn = sysCd.toLowerCase();

			forwardURL = sysGbn+"/main/PageLeft.ftl";

			/*********************************************************
			 * bind Form, VO
			 **********************************************************/
			MainForm form = new MainForm();
			MainVO vo = new MainVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * business logic
			 **********************************************************/
			HashMap paramMap = new HashMap();
			paramMap.put("targetMenuId"		, StringUtil.bvl(form.getTarget_menu_id(), ""));
			paramMap.put("sys_cd"			, sysCd);
			paramMap.put("user_id"			, (String) session.getAttribute("secfw.session.user_id"));
			paramMap.put("selectedMenuId"	, StringUtil.bvl(form.getSelected_menu_id(), ""));
			paramMap.put("comp_cd", (String)session.getAttribute("secfw.session.comp_cd"));

			HashMap hm 					= mainService.listMenu(paramMap);
			List menuList 				= (List)hm.get("menuList");
			String menuNm				= (String)hm.get("menuNm");
			String menuNmEng			= (String)hm.get("menuNmEng");
			String menuNmFra			= (String)hm.get("menuNmFra");
			String menuNmDeu			= (String)hm.get("menuNmDeu");
			String menuNmIta			= (String)hm.get("menuNmIta");
			String menuNmEsp			= (String)hm.get("menuNmEsp");
			String menuId				= (String)hm.get("menuId");
			Map map 					= new HashMap();
			String initUrl 				=   "";
			String SetinitUrl 			=   StringUtil.bvl(request.getParameter("set_init_url"),""); //강제설정 디테일 URL
			String selectedMenuId 		=   StringUtil.bvl(request.getParameter("selected_menu_id"),"");
			String selectedMenuDetailId = 	StringUtil.bvl(request.getParameter("selected_menu_detail_id"), selectedMenuId);
			String reloadYn 			= 	StringUtil.bvl(request.getParameter("reload_yn"), "n");

			if (menuList != null && menuList.size() > 0) {
				for (int i = 0; i < menuList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap) menuList.get(i);

					//menu_level=1 을 클릭하여 들어온 경우
					if(i == 0){
						if(selectedMenuDetailId.equals((String)lom.get("up_menu_id"))){
							selectedMenuDetailId = (String)lom.get("menu_id");
						}
					}

					//메뉴Id와 URL 설정
					if (((String) lom.get("menu_id")).equals(selectedMenuDetailId)) {
						//해당 메뉴의 Id의 URL이 존재한경우 설정하고 존재하지 않은 경우 하위 메뉴의 URL을 설정한다.
						if(Integer.parseInt(String.valueOf(lom.get("menu_level"))) == 2){
							if("".equals((String) lom.get("menu_url"))){
								//getLogger().error(">>>>>>>>>>>>>>url 없음");
								initUrl = StringUtil.bvl(lom.get("new_menu_url"), "");
								selectedMenuId = StringUtil.bvl(lom.get("new_menu_id"), "");
							}else{
								//getLogger().error(">>>>>>>>>>>>>>url 있음");
								initUrl = StringUtil.bvl(lom.get("menu_url"), "");
								selectedMenuId = StringUtil.bvl(lom.get("menu_id"), "");
							}
						}else{
							initUrl = StringUtil.bvl(lom.get("menu_url"), "");
							selectedMenuId = StringUtil.bvl(lom.get("menu_id"), "");
						}
					}
				}

				if ("".equals(initUrl)) {
					ListOrderedMap lm = (ListOrderedMap) menuList.get(0);
					initUrl = StringUtil.bvl(lm.get("new_menu_url"), "");
					selectedMenuId = StringUtil.bvl(lm.get("menu_id"), "");
				}

				if ("".equals(selectedMenuDetailId)) {
					ListOrderedMap lm = (ListOrderedMap) menuList.get(0);
					initUrl = StringUtil.bvl(lm.get("new_menu_url"), "");
					selectedMenuId = StringUtil.bvl(lm.get("menu_id"), "");
				}
			}
			mav.setViewName(forwardURL);
			mav.addObject("selectedMenuId", StringUtil.bvl(selectedMenuId, selectedMenuDetailId));
			mav.addObject("menuList", menuList);
			mav.addObject("menuNm", menuNm);
			mav.addObject("menuNmEng", menuNmEng);
			mav.addObject("menuNmFra", menuNmFra);
			mav.addObject("menuNmDeu", menuNmDeu);
			mav.addObject("menuNmIta", menuNmIta);
			mav.addObject("menuNmEsp", menuNmEsp);
			mav.addObject("menuId", menuId);
			mav.addObject("localeCd"		, localeCd);

			if(!SetinitUrl.equals("")){//디테일 프레임에 강제로 띄워줄 URL 이 있을경우
				initUrl = SetinitUrl.replace('^', '&');
			}
			if(!initUrl.equals("")){
				if(initUrl.indexOf('?')==-1) {
					initUrl+="?menu_id=" + selectedMenuId;
				}
				else {
					initUrl+="&menu_id=" + selectedMenuId;
				}

			}
			mav.addObject("initUrl", initUrl);
			mav.addObject("reloadYn", reloadYn);
			mav.addObject("lawfirmAccessFlag", lawfirmAccessFlag(request, "A")); //로펌
			mav.addObject("planAccessFlag", lawfirmAccessFlag(request, "B")); //일정관리
			mav.addObject("blngt_orgnz", blngt_orgnz);
			mav.addObject("statisticsViewFlag", statisticsViewFlag(request)); //통계
			mav.addObject("chgPersonflag", chgPersonflag(request));//계약서담당자변경

			ConsiderationVO cntvo = new ConsiderationVO();
			LawConsultVO lascntvo = new LawConsultVO();
			bind(request, cntvo);
			bind(request, lascntvo);
			COMUtil.getUserAuditInfo(request, cntvo);
			COMUtil.getUserAuditInfo(request, lascntvo);

			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			if(tmpSessionRoleList.contains("RA01")){
				cntvo.setTop_role("RA01");
				lascntvo.setTop_role("RA01");
			} else if(tmpSessionRoleList.contains("RA02")){
				cntvo.setTop_role("RA02");
				lascntvo.setTop_role("RA02");
			} else if(tmpSessionRoleList.contains("RB01")){
				cntvo.setTop_role("RB01");
				lascntvo.setTop_role("RB01");
			} else if(tmpSessionRoleList.contains("HQ01")){
				cntvo.setTop_role("HQ01");
				lascntvo.setTop_role("HQ01");
			} else if(tmpSessionRoleList.contains("HQ02")){
				cntvo.setTop_role("HQ02");
				lascntvo.setTop_role("HQ02");	
			} else {
				cntvo.setTop_role("ETC");
				lascntvo.setTop_role("ETC");
			}
			mav.addObject("topRole", lascntvo.getTop_role());
						
			//전자검토자의 검토유형
			String solo_yn = "";
			if("검토자(국내)".equals((String) session.getAttribute("secfw.session.user_nm"))){
				solo_yn = "1";
			}else if("검토자(해외)".equals((String) session.getAttribute("secfw.session.user_nm"))){
				solo_yn = "2";
			}
			mav.addObject("solo_yn", solo_yn);
			
			//의뢰자 계약 카운트
			int cntCont01 = 0;
			int cntCont02 = 0;
			int cntCont03 = 0;
			int cntCont04 = 0;
			int cntCont05 = 0;
			int closed = 0;	// 2014-01-31 Kevin. Review 단계에서 Closed 된 계약 건수를 표시.
			if("ETC".equals(lascntvo.getTop_role())){
				HashMap cntContMap = getStatusCount(request, response, (String)cntvo.getTop_role());
				
				cntCont01 = Integer.parseInt(String.valueOf(cntContMap.get("C02501")    == null ? "0" : cntContMap.get("C02501"))); //검토
				cntCont02 = Integer.parseInt(String.valueOf(cntContMap.get("C02502")    == null ? "0" : cntContMap.get("C02502"))); //체결
				cntCont03 = Integer.parseInt(String.valueOf(cntContMap.get("C02503")    == null ? "0" : cntContMap.get("C02503"))); //등록
				cntCont04 = Integer.parseInt(String.valueOf(cntContMap.get("C02504")    == null ? "0" : cntContMap.get("C02504"))); //이행
				cntCont05 = Integer.parseInt(String.valueOf(cntContMap.get("C02505")    == null ? "0" : cntContMap.get("C02505"))); //종료
				closed = Integer.parseInt(String.valueOf(cntContMap.get("Closed"))); //Review 단계에서 Closed 된 계약 건수.
			}
			mav.addObject("cntCont01", cntCont01);	//검토
			mav.addObject("cntCont02", cntCont02);	//체결
			mav.addObject("cntCont03", cntCont03);	//등록
			mav.addObject("cntCont04", cntCont04);	//이행
			mav.addObject("cntCont05", cntCont05);	//종료
			mav.addObject("closed", closed);		//Closed 된 계약 건수.
			
			// 계약 카운트
			List listCountConsideration = considerationService.countConsideration(cntvo);

			int cntTotal    = 0;
			int cntUnassign = 0;
			int cntConsider = 0;
			int cntClosed 	= 0; // 2014-02-05 Kevin. Closed 된 계약 건 분리.
			//int allClosed = 0; // 2014-04-28 신성우 주석처리. cntClosed 로 통합 2014-02-10 Kevin. Closed 된 모든 건 수 조회(legal admin은 법인의 모든 Closed 된 건, Reviewer은 할당된 건 중 Closed 된 것) 
			int cntResign	= 0;
			int cntTempsave = 0;
			int cntPendding = 0;
			int cntTransfer = 0;
			int cntContCced = 0;
			
			int cntReviewinProgress = 0;
			int cntSaved = 0;
			int cntNotapproved = 0;
				
			if(listCountConsideration != null && listCountConsideration.size() > 0) {
				ListOrderedMap clm = (ListOrderedMap)listCountConsideration.get(0);

				cntTotal    = Integer.parseInt(String.valueOf(clm.get("TOTAL_CNT")    == null ? "0" : clm.get("TOTAL_CNT")));
				cntUnassign = Integer.parseInt(String.valueOf(clm.get("UNASSIGN_CNT") == null ? "0" : clm.get("UNASSIGN_CNT")));
				cntConsider = Integer.parseInt(String.valueOf(clm.get("CONSIDER_CNT") == null ? "0" : clm.get("CONSIDER_CNT")));
				// 2014-02-05 Kevin CONSIDER_CNT에서 CLOSED_CNT를 분리
				cntClosed = Integer.parseInt(String.valueOf(clm.get("CLOSED_CNT") == null ? "0" : clm.get("CLOSED_CNT")));
				// 2014-04-28 신성우 주석처리. cntClosed 로 통합 2014-2-10 Kevin 전체 Closed 된 건 조회
				//allClosed = Integer.parseInt(String.valueOf(clm.get("CLOSED_CNT") == null ? "0" : clm.get("ALLCLOSED_CNT")));
				
				cntResign   = Integer.parseInt(String.valueOf(clm.get("RESIGN_CNT")   == null ? "0" : clm.get("RESIGN_CNT")));
				cntTempsave = Integer.parseInt(String.valueOf(clm.get("TEMPSAVE_CNT") == null ? "0" : clm.get("TEMPSAVE_CNT")));
				cntPendding = Integer.parseInt(String.valueOf(clm.get("PENDDING_CNT") == null ? "0" : clm.get("PENDDING_CNT")));
				cntTransfer = Integer.parseInt(String.valueOf(clm.get("TRANSFER_CNT") == null ? "0" : clm.get("TRANSFER_CNT")));
				cntContCced = Integer.parseInt(String.valueOf(clm.get("CCED_CNT") == null ? "0" : clm.get("CCED_CNT")));	//신성우 추가 2014-04-17
			
				cntReviewinProgress = Integer.parseInt(String.valueOf(clm.get("REVIEW_IN_PROGRESS_CNT") == null ? "0" : clm.get("REVIEW_IN_PROGRESS_CNT")));
				cntSaved = Integer.parseInt(String.valueOf(clm.get("SAVED_CNT") == null ? "0" : clm.get("SAVED_CNT")));
				cntNotapproved = Integer.parseInt(String.valueOf(clm.get("NOT_APPROVED_CNT") == null ? "0" : clm.get("NOT_APPROVED_CNT")));
		
			
			}

			mav.addObject("cntTotal", cntTotal);		//게약검토 현황 카운트 - 전체
			mav.addObject("cntUnassign", cntUnassign);	//게약검토 현황 카운트 - 미배정
			mav.addObject("cntConsider", cntConsider);	//게약검토 현황 카운트 - 검토중(Closed 된 건은 제외)
			// 2014-02-05 Kevin
			mav.addObject("cntClosed", cntClosed);		//계약 검토 현황 카운트 - Closed
			// 2014-04-28 신성우 주석처리. cntClosed 로 통합 2014-02-10 Kevin
			//mav.addObject("allClosed", allClosed);		//Closed 된 계약 건수
			
			mav.addObject("cntResign", cntResign);		//게약검토 현황 카운트 - 재배정
			mav.addObject("cntTempsave", cntTempsave);	//게약검토 현황 카운트 - 임시저장
			mav.addObject("cntPendding", cntPendding);	//게약검토 현황 카운트 - 미결
			mav.addObject("cntTransfer", cntTransfer);	//게약검토 현황 카운트 - 이관요청
			mav.addObject("cntContCced", cntContCced);	// CC 된 카운드.	2014-04-17 신성우 추가 2014-04-17.
			
			mav.addObject("cntReviewinProgress", cntReviewinProgress);	//게약검토 현황 카운트 - HQ - Review In Progress
			mav.addObject("cntSaved", cntSaved);						//게약검토 현황 카운트 - HQ - Saved
			mav.addObject("cntNotapproved", cntNotapproved);			//게약검토 현황 카운트 - HQ - Not Approved
			mav.addObject("command", form);

			// 자문 카운트
			List listCountLawConsult = lawConsultService.countLawConsult(lascntvo);

			int cntLawTotal = 0;
			int cntNoassign = 0;
			int cntReview   = 0;
			int cntLawResign   = 0;
			int cntTemp     = 0;
			int cntPending  = 0;
			int cntReg      = 0;
			int cntCced = 0;	// 2014-03-03 Kevin.

			if(listCountLawConsult != null && listCountLawConsult.size() > 0) {
				ListOrderedMap cntLom = (ListOrderedMap)listCountLawConsult.get(0);

				cntLawTotal = Integer.parseInt(String.valueOf(cntLom.get("TOTAL_CNT") == null ? "0" : cntLom.get("TOTAL_CNT")));
				cntNoassign = Integer.parseInt(String.valueOf(cntLom.get("NOASSIGN_CNT") == null ? "0" : cntLom.get("NOASSIGN_CNT")));
				cntReview = Integer.parseInt(String.valueOf(cntLom.get("REVIEW_CNT") == null ? "0" : cntLom.get("REVIEW_CNT")));
				cntLawResign = Integer.parseInt(String.valueOf(cntLom.get("RESIGN_CNT") == null ? "0" : cntLom.get("RESIGN_CNT")));
				cntTemp = Integer.parseInt(String.valueOf(cntLom.get("TEMP_CNT") == null ? "0" : cntLom.get("TEMP_CNT")));
				cntPending = Integer.parseInt(String.valueOf(cntLom.get("PENDING_CNT") == null ? "0" : cntLom.get("PENDING_CNT")));
				cntReg = Integer.parseInt(String.valueOf(cntLom.get("REG_CNT") == null ? "0" : cntLom.get("REG_CNT")));
				cntCced = Integer.parseInt(String.valueOf(cntLom.get("CCED_CNT")));		// 2014-03-03 Kevin.
			}

			mav.addObject("lawCntTotal", cntLawTotal);		//법률자문 현황 카운트 - 전체
			mav.addObject("lawCntNoassign", cntNoassign);   //법률자문 현황 카운트 - 미배정
			mav.addObject("lawCntReview", cntReview);       //법률자문 현황 카운트 - 검토중
			mav.addObject("lawCntTemp", cntTemp);           //법률자문 현황 카운트 - 임시저장
			mav.addObject("lawCntPending", cntPending);     //법률자문 현황 카운트 - 미결
			mav.addObject("lawCntResign", cntLawResign);	//법률자문 현황 카운트 - 재배정
			mav.addObject("lawCntReg", cntReg);	            //법률자문 현황 카운트 - 의뢰인 의뢰건
			mav.addObject("lawCntCced", cntCced);			// CC 된 카운드.	2014-03-03 Kevin.
			
			//계약관리자(RD01)일 경우 계약관리 건수 출력
			if((tmpSessionRoleList.contains("RD01"))){
			
				//계약관리 카운트
				List listCountContractMng = considerationService.countContractMng(cntvo);

				int cntContMngTotal         = 0; //계약관리 - 전체
				int cntContMngOrgRcv        = 0; //계약관리 - 원본접수
				int cntContMngAutoExp       = 0; //계약관리 - 자동연장
				int cntContMngAfterConReg   = 0; //계약관리 - 체결후등록
				int cntContMngStdCont       = 0; //계약관리 - 표준계약서

				if (listCountContractMng != null && listCountContractMng.size() > 0) {
					ListOrderedMap cntLom = (ListOrderedMap)listCountContractMng.get(0);

					cntContMngTotal    = Integer.parseInt(String.valueOf(cntLom.get("TOTAL_CNT")    == null ? "0" : cntLom.get("TOTAL_CNT")));
					cntContMngOrgRcv = Integer.parseInt(String.valueOf(cntLom.get("ORGRCV_CNT") == null ? "0" : cntLom.get("ORGRCV_CNT")));
					cntContMngAutoExp   = Integer.parseInt(String.valueOf(cntLom.get("AUTOEXP_CNT")   == null ? "0" : cntLom.get("AUTOEXP_CNT")));
					cntContMngAfterConReg       = Integer.parseInt(String.valueOf(cntLom.get("AFTERCONREG_CNT") == null ? "0" : cntLom.get("AFTERCONREG_CNT")));
					cntContMngStdCont     = Integer.parseInt(String.valueOf(cntLom.get("STDCONT_CNT")     == null ? "0" : cntLom.get("STDCONT_CNT")));
				}

				mav.addObject("cntContMngTotal", cntContMngTotal);			// 계약관리 현황 카운트 - 전체
				mav.addObject("cntContMngOrgRcv", cntContMngOrgRcv);	    // 계약관리 현황 카운트 - 원본접수
				mav.addObject("cntContMngAutoExp", cntContMngAutoExp);		// 계약관리 현황 카운트 - 자동연장
				mav.addObject("cntContMngAfterConReg", cntContMngAfterConReg);		// 계약관리 현황 카운트 - 체결후등록
				mav.addObject("cntContMngStdCont", cntContMngStdCont);		// 계약관리 현황 카운트 - 표준계약서
				mav.addObject("isCntMng", "Y");
			}
			else{
				mav.addObject("isCntMng", "N");
			}
			
			
			//날인담당자(RE01)의 날인접수 건수 수정 : 날인접수, 증명서류 발급자 카운트 통합
			if(tmpSessionRoleList.contains("RE01") || tmpSessionRoleList.contains("RE02")){
				//계약관리 카운트
				List listCountSeal = considerationService.countSeal(cntvo);
				int cntSealSign         = 0; //날인접수 건수

				if (listCountSeal != null && listCountSeal.size() > 0) {
					ListOrderedMap cntLom = (ListOrderedMap)listCountSeal.get(0);
					cntSealSign    = Integer.parseInt(String.valueOf(cntLom.get("SEALSIGN_CNT")    == null ? "0" : cntLom.get("SEALSIGN_CNT")));
				}
				mav.addObject("cntSealSign", cntSealSign);		// 날인접수 건수
				mav.addObject("isCntSealorSign", "Y");
			}
			else{
				mav.addObject("isCntSealorSign", "N");
			}
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * Left 프레임에 건수들 업데이트 하기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView refreshLasCount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * 변수선언
			 **********************************************************/
			HttpSession session = request.getSession(false);
			ModelAndView mav = new ModelAndView();

			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	    	Locale lc = new RequestContext(request).getLocale();
	        String localeCd = StringUtil.bvl(lc.getLanguage(), "en");
	        String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");

			/*********************************************************
			 * forwardURL
			 **********************************************************/
			String forwardURL = "";

			forwardURL = "/WEB-INF/jsp/las/main/RefreshLeftCount.jsp";

			mav.setViewName(forwardURL);
			mav.addObject("blngt_orgnz", blngt_orgnz);

			ConsiderationVO cntvo = new ConsiderationVO();
			LawConsultVO lascntvo = new LawConsultVO();
			bind(request, cntvo);
			bind(request, lascntvo);
			COMUtil.getUserAuditInfo(request, cntvo);
			COMUtil.getUserAuditInfo(request, lascntvo);

			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			if(tmpSessionRoleList.contains("RA01")){
				cntvo.setTop_role("RA01");
				lascntvo.setTop_role("RA01");
			} else if(tmpSessionRoleList.contains("RA02")){
				cntvo.setTop_role("RA02");
				lascntvo.setTop_role("RA02");
			} else if(tmpSessionRoleList.contains("RB01")){
				cntvo.setTop_role("RB01");
				lascntvo.setTop_role("RB01");
			} else {
				cntvo.setTop_role("ETC");
				lascntvo.setTop_role("ETC");
			}
			mav.addObject("topRole", lascntvo.getTop_role());
			
			//의뢰자 계약 카운트
			int cntCont01 = 0;
			int cntCont02 = 0;
			int cntCont03 = 0;
			int cntCont04 = 0;
			int cntCont05 = 0;
			int closed = 0;	// 2014-01-31 Kevin. Review 단계에서 Closed 된 계약 건수를 표시.
			
			if("ETC".equals(lascntvo.getTop_role())){
				HashMap cntContMap = getStatusCount(request, response, (String)cntvo.getTop_role());
				
				cntCont01 = Integer.parseInt(String.valueOf(cntContMap.get("C02501")    == null ? "0" : cntContMap.get("C02501"))); //검토
				cntCont02 = Integer.parseInt(String.valueOf(cntContMap.get("C02502")    == null ? "0" : cntContMap.get("C02502"))); //체결
				cntCont03 = Integer.parseInt(String.valueOf(cntContMap.get("C02503")    == null ? "0" : cntContMap.get("C02503"))); //등록
				cntCont04 = Integer.parseInt(String.valueOf(cntContMap.get("C02504")    == null ? "0" : cntContMap.get("C02504"))); //이행
				cntCont05 = Integer.parseInt(String.valueOf(cntContMap.get("C02505")    == null ? "0" : cntContMap.get("C02505"))); //종료
				closed = Integer.parseInt(String.valueOf(cntContMap.get("Closed"))); //Review 단계에서 Closed 된 계약 건수.
			}
			mav.addObject("cntCont01", cntCont01);	//검토
			mav.addObject("cntCont02", cntCont02);	//체결
			mav.addObject("cntCont03", cntCont03);	//등록
			mav.addObject("cntCont04", cntCont04);	//이행
			mav.addObject("cntCont05", cntCont05);	//종료
			mav.addObject("closed", closed);		//Closed 된 계약 건수.
			
			// 계약 카운트
			List listCountConsideration = considerationService.countConsideration(cntvo);

			int cntTotal    = 0;
			int cntUnassign = 0;
			int cntConsider = 0;
			int cntClosed 	= 0; // 2014-02-05 Kevin. Closed 된 계약 건 분리.
			//int allClosed = 0; // 2014-04-28 신성우 주석처리. cntClosed 로 통합 2014-02-10 Kevin. Closed 된 모든 건 수 조회(legal admin은 법인의 모든 Closed 된 건, Reviewer은 할당된 건 중 Closed 된 것)
			int cntResign	= 0;
			int cntTempsave = 0;
			int cntPendding = 0;
			int cntTransfer = 0;

			if(listCountConsideration != null && listCountConsideration.size() > 0) {
				ListOrderedMap clm = (ListOrderedMap)listCountConsideration.get(0);

				cntTotal    = Integer.parseInt(String.valueOf(clm.get("TOTAL_CNT")    == null ? "0" : clm.get("TOTAL_CNT")));
				cntUnassign = Integer.parseInt(String.valueOf(clm.get("UNASSIGN_CNT") == null ? "0" : clm.get("UNASSIGN_CNT")));
				cntConsider = Integer.parseInt(String.valueOf(clm.get("CONSIDER_CNT") == null ? "0" : clm.get("CONSIDER_CNT")));
				
				// 2014-02-05 Kevin CONSIDER_CNT에서 CLOSED_CNT를 분리
				cntClosed = Integer.parseInt(String.valueOf(clm.get("CLOSED_CNT") == null ? "0" : clm.get("CLOSED_CNT")));
				// 2014-04-28 신성우 주석처리. cntClosed 로 통합 2014-2-10 Kevin 전체 Closed 된 건 조회
				//allClosed = Integer.parseInt(String.valueOf(clm.get("CLOSED_CNT") == null ? "0" : clm.get("ALLCLOSED_CNT")));
				
				cntResign   = Integer.parseInt(String.valueOf(clm.get("RESIGN_CNT")   == null ? "0" : clm.get("RESIGN_CNT")));
				cntTempsave = Integer.parseInt(String.valueOf(clm.get("TEMPSAVE_CNT") == null ? "0" : clm.get("TEMPSAVE_CNT")));
				cntPendding = Integer.parseInt(String.valueOf(clm.get("PENDDING_CNT") == null ? "0" : clm.get("PENDDING_CNT")));
				cntTransfer = Integer.parseInt(String.valueOf(clm.get("TRANSFER_CNT") == null ? "0" : clm.get("TRANSFER_CNT")));
			}

			mav.addObject("cntTotal", cntTotal);		//게약검토 현황 카운트 - 전체
			mav.addObject("cntUnassign", cntUnassign);	//게약검토 현황 카운트 - 미배정
			mav.addObject("cntConsider", cntConsider);	//게약검토 현황 카운트 - 검토중
			mav.addObject("cntResign", cntResign);		//게약검토 현황 카운트 - 재배정
			mav.addObject("cntTempsave", cntTempsave);	//게약검토 현황 카운트 - 임시저장
			mav.addObject("cntPendding", cntPendding);	//게약검토 현황 카운트 - 미결
			mav.addObject("cntTransfer", cntTransfer);	//게약검토 현황 카운트 - 이관요청
			// 2014-02-05 Kevin
			mav.addObject("cntClosed", cntClosed);		//계약 검토 현황 카운트 - Closed
			// 2014-04-28 신성우 주석처리. cntClosed 로 통합 2014-02-10 Kevin
			//mav.addObject("allClosed", allClosed);		//Closed 된 계약 건수	

			// 자문 카운트
			List listCountLawConsult = lawConsultService.countLawConsult(lascntvo);

			int cntLawTotal = 0;
			int cntNoassign = 0;
			int cntReview   = 0;
			int cntLawResign   = 0;
			int cntTemp     = 0;
			int cntPending  = 0;
			int cntReg      = 0;

			if(listCountLawConsult != null && listCountLawConsult.size() > 0) {
				ListOrderedMap cntLom = (ListOrderedMap)listCountLawConsult.get(0);

				cntLawTotal = Integer.parseInt(String.valueOf(cntLom.get("TOTAL_CNT") == null ? "0" : cntLom.get("TOTAL_CNT")));
				cntNoassign = Integer.parseInt(String.valueOf(cntLom.get("NOASSIGN_CNT") == null ? "0" : cntLom.get("NOASSIGN_CNT")));
				cntReview = Integer.parseInt(String.valueOf(cntLom.get("REVIEW_CNT") == null ? "0" : cntLom.get("REVIEW_CNT")));
				cntLawResign = Integer.parseInt(String.valueOf(cntLom.get("RESIGN_CNT") == null ? "0" : cntLom.get("RESIGN_CNT")));
				cntTemp = Integer.parseInt(String.valueOf(cntLom.get("TEMP_CNT") == null ? "0" : cntLom.get("TEMP_CNT")));
				cntPending = Integer.parseInt(String.valueOf(cntLom.get("PENDING_CNT") == null ? "0" : cntLom.get("PENDING_CNT")));
				cntReg = Integer.parseInt(String.valueOf(cntLom.get("REG_CNT") == null ? "0" : cntLom.get("REG_CNT")));
			}

			mav.addObject("lawCntTotal", cntLawTotal);		//법률자문 현황 카운트 - 전체
			mav.addObject("lawCntNoassign", cntNoassign);   //법률자문 현황 카운트 - 미배정
			mav.addObject("lawCntReview", cntReview);       //법률자문 현황 카운트 - 검토중
			mav.addObject("lawCntTemp", cntTemp);           //법률자문 현황 카운트 - 임시저장
			mav.addObject("lawCntPending", cntPending);     //법률자문 현황 카운트 - 미결
			mav.addObject("lawCntResign", cntLawResign);	//법률자문 현황 카운트 - 재배정
			mav.addObject("lawCntReg", cntReg);	            //법률자문 현황 카운트 - 의뢰인 의뢰건
			
			//계약관리자(RD01)일 경우 계약관리 건수 출력
			if((tmpSessionRoleList.contains("RD01"))){
			
				//계약관리 카운트
				List listCountContractMng = considerationService.countContractMng(cntvo);

				int cntContMngTotal         = 0; //계약관리 - 전체
				int cntContMngOrgRcv        = 0; //계약관리 - 원본접수
				int cntContMngAutoExp       = 0; //계약관리 - 자동연장
				int cntContMngAfterConReg   = 0; //계약관리 - 체결후등록
				int cntContMngStdCont       = 0; //계약관리 - 표준계약서

				if (listCountContractMng != null && listCountContractMng.size() > 0) {
					ListOrderedMap cntLom = (ListOrderedMap)listCountContractMng.get(0);

					cntContMngTotal    = Integer.parseInt(String.valueOf(cntLom.get("TOTAL_CNT")    == null ? "0" : cntLom.get("TOTAL_CNT")));
					cntContMngOrgRcv = Integer.parseInt(String.valueOf(cntLom.get("ORGRCV_CNT") == null ? "0" : cntLom.get("ORGRCV_CNT")));
					cntContMngAutoExp   = Integer.parseInt(String.valueOf(cntLom.get("AUTOEXP_CNT")   == null ? "0" : cntLom.get("AUTOEXP_CNT")));
					cntContMngAfterConReg       = Integer.parseInt(String.valueOf(cntLom.get("AFTERCONREG_CNT") == null ? "0" : cntLom.get("AFTERCONREG_CNT")));
					cntContMngStdCont     = Integer.parseInt(String.valueOf(cntLom.get("STDCONT_CNT")     == null ? "0" : cntLom.get("STDCONT_CNT")));
				}

				mav.addObject("cntContMngTotal", cntContMngTotal);			// 계약관리 현황 카운트 - 전체
				mav.addObject("cntContMngOrgRcv", cntContMngOrgRcv);	    // 계약관리 현황 카운트 - 원본접수
				mav.addObject("cntContMngAutoExp", cntContMngAutoExp);		// 계약관리 현황 카운트 - 자동연장
				mav.addObject("cntContMngAfterConReg", cntContMngAfterConReg);		// 계약관리 현황 카운트 - 체결후등록
				mav.addObject("cntContMngStdCont", cntContMngStdCont);		// 계약관리 현황 카운트 - 표준계약서
				mav.addObject("isCntMng", "Y");
			}
			else{
				mav.addObject("isCntMng", "N");
			}
			
			//날인담당자(RE01)의 날인접수 건수 수정 : 날인접수, 증명서류 발급자 카운트 통합
			if(tmpSessionRoleList.contains("RE01") || tmpSessionRoleList.contains("RE02")){
				//계약관리 카운트
				List listCountSeal = considerationService.countSeal(cntvo);
				int cntSealSign         = 0; //날인접수 건수

				if (listCountSeal != null && listCountSeal.size() > 0) {
					ListOrderedMap cntLom = (ListOrderedMap)listCountSeal.get(0);
					cntSealSign    = Integer.parseInt(String.valueOf(cntLom.get("SEALSIGN_CNT")    == null ? "0" : cntLom.get("SEALSIGN_CNT")));
				}
				mav.addObject("cntSealSign", cntSealSign);		// 날인접수 건수
				mav.addObject("isCntSealorSign", "Y");
			}
			else{
				mav.addObject("isCntSealorSign", "N");
			}

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * Body Page로 포워드 한다
	 */
	public ModelAndView forwardBodyPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		/*********************************************************
		 * 변수선언
		 **********************************************************/
		ModelAndView mav = new ModelAndView();

		String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		String targetMenuId = StringUtil.bvl((String) request.getParameter("targetMenuId")
											,(String) request.getAttribute("targetMenuId"));

		/*********************************************************
		 * forwardURL
		 **********************************************************/
		String forwardURL = "/WEB-INF/jsp/secfw/main/PageBody.jsp";

		/*********************************************************
		 * bind Form, VO
		 **********************************************************/
		MainForm form = new MainForm();
		MainVO vo = new MainVO();

		bind(request, form);
		bind(request, vo);

		mav.setViewName(forwardURL);
		mav.addObject("targetMenuId", targetMenuId);

		return mav;
	}

	/**
	 * 매뉴 구분에 따라 Top 메뉴, Left 메뉴를 반환한다
	 */
	public void listMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			HttpSession session = request.getSession(false);
			/*********************************************************
			 * 변수선언
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			String sysCd 			= StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String userId		 	= (String) session.getAttribute("secfw.session.user_id");
			String targetMenuId 	= StringUtil.bvl((String) request.getParameter("targetMenuId"),
												 	 (String) request.getAttribute("targetMenuId"));
			String menuGbn 			= StringUtil.bvl((String) request.getParameter("menuGbn"), "");
			String selectMenuId 	= StringUtil.bvl((String) request.getParameter("selectMenuId"), "");
			String comp_cd      = StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), "");

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap hm = new HashMap();

			// 시스템코드, 사용가능, 표시여부
			hm.put("sys_cd", sysCd);
			hm.put("user_id", userId);
			hm.put("menuGbn", menuGbn);
			hm.put("selectMenuId", selectMenuId);
			hm.put("use_yn", "Y");
			hm.put("display_yn", "Y");
			hm.put("comp_cd", comp_cd);

			/*********************************************************
			 * 메뉴조회
			 **********************************************************/
			HashMap menuHm 			=   mainService.listMenu(hm);
			List 	result 	= 	(List)menuHm.get("menuList");

			response.setContentType("applicattion/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * freeMarker
	 */
	public ModelAndView freeMarkerTest(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Map modelRoot = new HashMap();
		String now = (new java.util.Date()).toString();
		modelRoot.put("now", now);

		String forwardURL 	= "test.ftl";
		ModelAndView mav 	= new ModelAndView();
		mav.setViewName(forwardURL);
		mav.addObject("data1", modelRoot);

		return mav;
	}

	public ModelAndView forwardPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forwardURL = StringUtil.bvl(request.getParameter("forward_url"),"");

		ModelAndView mav = new ModelAndView();
		mav.setViewName(forwardURL);

		return mav;
	}

	/**
	 * 계약관리 - 공지사항 리스트
	 */
	public List getClmBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		BoardForm form = new BoardForm();
		BoardVO vo = new BoardVO();

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		vo.setAnnce_gbn("C01301"); //공지사항
		vo.setStart_index("1");
		vo.setEnd_index("5");

		//2 .리스트 조회 서비스 호출
		List boardList = (List) boardService.listBoardByMain(vo);

		return boardList;
	}

	/**
	 * 계약관리 - My Notice 리스트
	 */
	public List getClmMyNoticeList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		MyNoticeForm form = new MyNoticeForm();
		MyNoticeVO vo = new MyNoticeVO();

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		//데이터 초기화 해주기
	    form.setSrch_start_reg_dt(StringUtil.bvl(form.getSrch_start_reg_dt(), ""));
	    vo.setSrch_start_reg_dt(StringUtil.bvl(vo.getSrch_start_reg_dt(), ""));
	    form.setSrch_end_reg_dt(StringUtil.bvl(form.getSrch_end_reg_dt(), ""));
	    vo.setSrch_end_reg_dt(StringUtil.bvl(vo.getSrch_end_reg_dt(), ""));
	    form.setSrch_name(StringUtil.bvl(form.getSrch_name(), ""));
	    vo.setSrch_name(form.getSrch_name());
	    form.setSrch_stat(StringUtil.bvl(form.getSrch_stat(), ""));
	    vo.setSrch_stat(form.getSrch_stat());
	    form.setSrch_keyword_mailtitle(StringUtil.bvl(form.getSrch_keyword_mailtitle(),""));
	    vo.setSrch_keyword_mailtitle(form.getSrch_keyword_mailtitle());
	    form.setSrch_Addressee(StringUtil.bvl(form.getSrch_Addressee(), ""));
	    vo.setSrch_Addressee(form.getSrch_Addressee());


	    vo.setStart_index("1");  //현재 페이지의 첫번째 게시물번호  set
		vo.setEnd_index("5");   //현재 페이지의 마지막 게시물번호  set

	    List noticeList = myNoticeService.listMyNoticeByMain(vo);

		return noticeList;
	}

	/**
	 * 계약관리 - my approval 리스트
	 */
	public List getClmApprovalList(HttpServletRequest request, HttpServletResponse response) throws Exception{

		ManageForm form = new ManageForm();
		ManageVO vo = new ManageVO();

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		//1. 의뢰명 또는 계약명
		form.setSrch_review_title(StringUtil.bvl(form.getSrch_review_title(), ""));
		vo.setSrch_review_title(StringUtil.convertHtmlTochars(form.getSrch_review_title().toUpperCase()));
		//2. 의뢰자 명
		form.setSrch_reqman_nm(StringUtil.bvl(form.getSrch_reqman_nm(), ""));
		vo.setSrch_reqman_nm(StringUtil.convertHtmlTochars(form.getSrch_reqman_nm().toUpperCase()));
		//3. 의뢰일자
		form.setSrch_start_reqday(StringUtil.bvl(form.getSrch_start_reqday(), ""));
		vo.setSrch_start_reqday(form.getSrch_start_reqday().replace("-", ""));
		form.setSrch_end_reqday(StringUtil.bvl(form.getSrch_end_reqday(), ""));
		vo.setSrch_end_reqday(form.getSrch_end_reqday().replace("-", ""));
		//4. 계약담당자 명
		form.setSrch_respman_nm(StringUtil.bvl(form.getSrch_respman_nm(), ""));
		vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(form.getSrch_respman_nm().toUpperCase()));
		//5. 계약담당부서
		form.setSrch_resp_dept(StringUtil.bvl(form.getSrch_resp_dept(), ""));
		vo.setSrch_resp_dept(StringUtil.convertHtmlTochars(form.getSrch_resp_dept()));
		form.setSrch_resp_dept_nm(StringUtil.bvl(form.getSrch_resp_dept_nm(), ""));
		vo.setSrch_resp_dept_nm(StringUtil.convertHtmlTochars(form.getSrch_resp_dept_nm()));
		//6. 계약상대방
		form.setSrch_oppnt_cd(StringUtil.bvl(form.getSrch_oppnt_cd(), ""));
		vo.setSrch_oppnt_cd(StringUtil.convertHtmlTochars(form.getSrch_oppnt_cd()));
		form.setSrch_oppnt_nm(StringUtil.bvl(form.getSrch_oppnt_nm(), ""));
		vo.setSrch_oppnt_nm(StringUtil.convertHtmlTochars(form.getSrch_oppnt_nm()));
		//7. 법무검토자
		form.setSrch_cnsdman_nm(StringUtil.bvl(form.getSrch_cnsdman_nm(), ""));
		vo.setSrch_cnsdman_nm(StringUtil.convertHtmlTochars(form.getSrch_cnsdman_nm().toUpperCase()));
		//8. 비즈니스 단계
		form.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(),""));
		vo.setSrch_biz_clsfcn(form.getSrch_biz_clsfcn());
		//9. 대분류
		form.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(), ""));
		vo.setSrch_cnclsnpurps_bigclsfcn(form.getSrch_cnclsnpurps_bigclsfcn());
		//10. 단계
		form.setSrch_step(StringUtil.bvl(form.getSrch_step(), ""));
		vo.setSrch_step(form.getSrch_step());
		//11. 상태
		form.setSrch_state(StringUtil.bvl(form.getSrch_state(), ""));
		vo.setSrch_state(form.getSrch_state());
		//12. 리스트구분
		form.setList_mode(StringUtil.bvl(form.getList_mode(), "cnsdreq"));
		vo.setList_mode(form.getList_mode());
		//13. 요청구분(담당자변경시 사용)
		form.setSrch_demand_gbn(StringUtil.bvl(form.getSrch_demand_gbn(), "D"));
		vo.setSrch_demand_gbn(form.getSrch_demand_gbn());
		//14. 요청상태(담당자변경시 사용)
		form.setSrch_demand_status(StringUtil.bvl(form.getSrch_demand_status(), ""));
		vo.setSrch_demand_status(form.getSrch_demand_status());

		vo.setStart_index("1");
		vo.setEnd_index("5");

		List approvalList = manageService.listMyApproval(vo);

		return approvalList;
	}

	/*
	 * 법무관리 - 공지사항 리스트
	 */
	public List getLasBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		NoticeForm form = new NoticeForm();
		NoticeVO vo = new NoticeVO();

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		vo.setAnnce_knd("MEMO");
		vo.setStart_index("1");
		vo.setEnd_index("5");

		List boardList = (List) noticeService.listNoticeByMain(vo);

		return boardList;


	}
	
	/*
	 * 팝업공지사항의 대상 게시물 시퀀스번호 리스트 
	 */
	public List getLatestNoticeSeqno(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		NoticeForm form = new NoticeForm();
		NoticeVO vo = new NoticeVO();

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		vo.setAnnce_knd("MEMO");
		
		List noticeSeqnoList = (List)noticeService.listNoticeLatestSeqno(vo);
		
		return noticeSeqnoList;
	}

	/*
	 * 법무관리 - 입법동향 리스트
	 */
	public List getLasLawInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception{

		MainLawInfoForm form = new MainLawInfoForm();
		MainLawInfoVO   vo   = new MainLawInfoVO();

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		vo.setInfo_gbn("C00405");
		vo.setStart_index("1");
		vo.setEnd_index("5");

		List lawInfoList = (List) mainLawInfoService.listMainLawInfoByMain(vo);

		return lawInfoList;
	}

	/**
	 * 법률자문 리스트 최근게시물
	 */
	public List getLasLawConsultList(HttpServletRequest request, HttpServletResponse response) throws Exception{

		List list;
		LawConsultVO vo = new LawConsultVO();
		LawConsultForm form = new LawConsultForm();

		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		vo.setReg_id(vo.getSession_user_id());
		vo.setUser_id(vo.getSession_user_id());

		/*
		if (ClmsBoardUtil.searchRole(request, "RA01")) { 	//그룹장
			vo.setTop_role("RA01");
			logger.debug("Check Point : RA01");
			//list = lawConsultService.listLawConsultGrpmgrRecent(vo);
			list = lawConsultService.listLawConsultRecent(vo);
		} else if (ClmsBoardUtil.searchRole(request, "RA02") || ClmsBoardUtil.searchRole(request, "RA03") ) {		//담당자
			vo.setTop_role("RA02");
			logger.debug("Check Point : RA02");
			//list = lawConsultService.listLawConsultRespmanRecent(vo);
			list = lawConsultService.listLawConsultRecent(vo);
		} else if ( ClmsBoardUtil.searchRole(request, "RC01")
				|| ClmsBoardUtil.searchRole(request, "RD01")
				|| ClmsBoardUtil.searchRole(request, "RD02")
				|| ClmsBoardUtil.searchRole(request, "RZ00")) {
			vo.setTop_role("ETC");
			logger.debug("Check Point : ETC");
			//list = lawConsultService.listLawConsultReqmanRecent(vo);
			list = lawConsultService.listLawConsultRecent(vo);
		} else {
			list = null;
		}
		*/
		HttpSession session = request.getSession(false);
		ArrayList userRoleList = (ArrayList) session.getAttribute("secfw.session.roleList");
		if(userRoleList.contains("RA01")) {
			vo.setTop_role("RA01");
		} else if(userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
			vo.setTop_role("RA02");
		} else {
			vo.setTop_role("ETC");
		}
		list = lawConsultService.listLawConsultRecent(vo);

		return list;
	}
	
	/*
	 * 계약관리 시스템 - 계약별 건수
	 */
	public HashMap getStatusCount(HttpServletRequest request, HttpServletResponse response, String topRole) throws Exception{
		HttpSession session = request.getSession(false);
		
		HashMap returnMap 	= new HashMap();
		HashMap hm 			= new HashMap();
		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role"); //session 사용할 경우
		ArrayList userRoleList = new ArrayList(); //role_cd만 꺼내기 위한 arrayList

		if(roleList != null && roleList.size() > 0){
		    for(int idx = 0; idx < roleList.size(); idx++){
		        HashMap roleListMap = (HashMap)roleList.get(idx);

		        userRoleList.add((String)roleListMap.get("role_cd"));
		    }
		}

		String accessLevel = "";

		if(userRoleList != null && userRoleList.size() > 0){

			if(userRoleList.contains("RA00") || userRoleList.contains("RC01")){
				accessLevel = "A";
			}else if(userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")){
		        accessLevel = "B";
		    }else if(userRoleList.contains("RD01")){
		        accessLevel = "C";
		    }
		}
		hm.put("accessLevel", accessLevel);
		
		hm.put("user_id", StringUtil.bvl(session.getAttribute("secfw.session.user_id"), ""));
		hm.put("session_manager_yn", StringUtil.bvl(session.getAttribute("secfw.session.manager_yn"), ""));
		hm.put("session_support_yn", StringUtil.bvl(session.getAttribute("secfw.session.support_yn"), ""));
		hm.put("session_auth_comp_cd", StringUtil.bvl(session.getAttribute("secfw.session.auth_comp_cd"), "").replace("'", ""));
		hm.put("session_auth_apnt_yn", StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_yn"), ""));
		hm.put("user_dept_cd", StringUtil.bvl(session.getAttribute("secfw.session.dept_cd"), ""));
		
		if("ETC".equals(topRole)){
			returnMap = mainService.getClmStatuCount(hm);	
		}
		return returnMap;
	}

	/*
	 * 계약관리 시스템 - 계약별 건수
	 */
	public HashMap getClmStatuCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(false);

		HashMap hm = new HashMap();
		hm.put("user_id", StringUtil.bvl(session.getAttribute("secfw.session.user_id"), ""));
		hm.put("blngt_orgnz", StringUtil.bvl(session.getAttribute("secfw.session.blngt_orgnz"), ""));
		hm.put("manager_yn", StringUtil.bvl(session.getAttribute("secfw.session.manager_yn"), ""));
		hm.put("support_yn", StringUtil.bvl(session.getAttribute("secfw.session.support_yn"), ""));

		hm.put("auth_apnt_yn", StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_yn"), ""));

		hm.put("user_dept_cd", StringUtil.bvl(session.getAttribute("secfw.session.dept_cd"), ""));
		hm.put("apnt_dept_cd", StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_dept"), ""));

		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role"); //session 사용할 경우
		ArrayList userRoleList = new ArrayList(); //role_cd만 꺼내기 위한 arrayList

		if(roleList != null && roleList.size() > 0){
		    for(int idx = 0; idx < roleList.size(); idx++){
		        HashMap roleListMap = (HashMap)roleList.get(idx);

		        userRoleList.add((String)roleListMap.get("role_cd"));
		    }
		}

		String accessLevel = "";

		//사용자 role 비교
		if(userRoleList != null && userRoleList.size() > 0){

			if(userRoleList.contains("RA00") || userRoleList.contains("RC01")){
				accessLevel = "A";
			}else if(userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")){
		        accessLevel = "B";
		    }else if(userRoleList.contains("RD01")){
		        accessLevel = "C";
		    }
		}
		hm.put("accessLevel", accessLevel);

		hm.put("user_id", StringUtil.bvl(session.getAttribute("secfw.session.user_id"), ""));
		hm.put("session_manager_yn", StringUtil.bvl(session.getAttribute("secfw.session.manager_yn"), ""));
		hm.put("session_support_yn", StringUtil.bvl(session.getAttribute("secfw.session.support_yn"), ""));
		hm.put("session_auth_comp_cd", StringUtil.bvl(session.getAttribute("secfw.session.auth_comp_cd"), ""));
		hm.put("session_auth_apnt_yn", StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_yn"), ""));
		
		HashMap returnMap =   mainService.getClmStatuCount(hm);

		return returnMap;
	}

	/**
	 *  법무시스템 - 로펌 & 일정관리 메뉴 접근가능 여부 판단
	 */
	public boolean lawfirmAccessFlag(HttpServletRequest request, String gubun) throws Exception{
		HttpSession session = request.getSession(false);
		boolean retValue = false;

		String result = "";
		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
		String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");

		ArrayList userRoleList = new ArrayList();
	    if(roleList != null && roleList.size()>0){
	    	for(int i=0; i < roleList.size() ;i++){
	        	HashMap hm = (HashMap)roleList.get(i);
	        	String roleCd = (String)hm.get("role_cd");
	        	userRoleList.add(roleCd);
	        }
		}

	    if(userRoleList != null && userRoleList.size()>0) {
	    	if(userRoleList.contains("RA00")) {
	        	result = "A";
			}else if(userRoleList.contains("RA01") || userRoleList.contains("RA02")){
				result = "B";
			}else if(userRoleList.contains("RA03")){
				result = "C";
			}
		}

	    if("A".equals(gubun)){ //로펌
	    	//시스템관리자는 로펌을 보이게 한다.
	    	//소속조직이 해외법무팀이면서  RA01,RA02,RA03 인 사용자만 로펌이 보이게한다.
	    	//소속조직이 IP센터이면서 RA03인 사용자만 로펌이 보이게 한다.
	    	if("A".equals(result) || (("B".equals(result) || "C".equals(result)) && ("A00000002".equals(blngt_orgnz))) || ("C".equals(result) && ("A00000003".equals(blngt_orgnz)))){
	    		retValue = true;
	        }
	    }else{ //일정관리
	    	//시스템관리자 및 소속조직이 해외법무팀이면서 RA01,RA02,RA03 인 사용자만 일정관리가 보이게한다.
	    	if("A".equals(result) || (("B".equals(result) || "C".equals(result)) && ("A00000002".equals(blngt_orgnz)))){
		    	retValue = true;
		    }
	    }
		return retValue;
	}

	/**
	 * 사이트맵 이동
	 */
	public ModelAndView forwardSiteMap(HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession(false);
		String chgLangflag = StringUtil.bvl(request.getParameter("chgLangflag"),"");

		if(!"".equals(chgLangflag)){

			Locale locale = null;

			if("ko".equals(chgLangflag)) {
				locale = new Locale("ko");
			} else if("zh".equals(chgLangflag)) {
				locale = new Locale("zh");
			} else if("ja".equals(chgLangflag)) {
				locale = new Locale("ja");
			} else if ("fr".equals(chgLangflag)) {
				locale = new Locale("fr");
			/*} else if ("de".equals(chgLangflag)) {
				locale = new Locale("de");   */
			} else if ("it".equals(chgLangflag)) {
				locale = new Locale("it");
			} else if ("es".equals(chgLangflag)) {
				locale = new Locale("es");
			} else {
				locale = new Locale("en");
			}

			//싱글 선택 언어(ep_local)임의값을 세션에 적용.
			session.setAttribute("secfw.session.language_flag", chgLangflag);
			session.setAttribute("secfw.server.division_gbn",			propertyService.getProperty("secfw.server.division")); // 개발서버와 운영서버 구분값. 다국어 오픈 전 개발서버에만 독어/불어 옵션 선택 가능.
			//localeResolver에 locale 셋팅
			localeResolver.setLocale(request, response, locale);
		}

		/*********************************************************
		 * 사용자 로케일 정보에 따른 변경
		 **********************************************************/
		Locale lc = (Locale) localeResolver.resolveLocale(request);

		String locale = StringUtil.bvl(lc.getLanguage(), propertyService.getProperty("secfw.defaultLocale"));

		/*********************************************************
		 * (필요시)언어에 따라 세션정보(부서명, 사용자명, 회사명)을 언어에 맞게 setting 한다
		 **********************************************************/

		/*********************************************************
		 * 언어에 따라 해당된 언어의 인덱스 페이지로 forwardURL
		 **********************************************************/

		/*********************************************************
		 * 변수선언
		 **********************************************************/
		ModelAndView mav = new ModelAndView();
		String forwardURL = "";
		try{
			String sysCd	= (String)session.getAttribute("secfw.session.sys_cd");
            String MenuId	= propertyService.getProperty("secfw.menu.topMenuId");
            String userId	= (String)session.getAttribute("secfw.session.user_id");
            String comp_cd	= (String)session.getAttribute("secfw.session.comp_cd");

            if("CLM".equals(sysCd)){
            	forwardURL = "/WEB-INF/jsp/clm/main/SiteMap.jsp";
            }else{
            	forwardURL = "/WEB-INF/jsp/las/main/SiteMap.jsp";
            }

			/*********************************************************
			 * bind Form, VO
			 **********************************************************/
			MainForm form 	= new MainForm();
			MainVO vo 		= new MainVO();

			bind(request, form);
			bind(request, vo);

			vo.setSys_cd(sysCd);
			vo.setMenu_id(MenuId);
			vo.setUser_id(userId);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * business logic
			 **********************************************************/
			HashMap paramMap = new HashMap();
			paramMap.put("menuGbn"			, StringUtil.bvl("TOP", ""));
			paramMap.put("targetMenuId"		, StringUtil.bvl(form.getTarget_menu_id(), ""));
			paramMap.put("sys_cd"			, sysCd);
			paramMap.put("user_id"			, userId);
			paramMap.put("comp_cd"			, comp_cd);

			HashMap hm 			=   mainService.listMenu(paramMap);
			List 	menuList 	= 	(List)hm.get("menuList");

			Map map = new HashMap();
			int Level_One =0;
			if (menuList != null && menuList.size() > 0) {
				String compMenuId = "";

				for (int i = 0; i < menuList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap) menuList.get(i);

					if ("1".equals(StringUtil.bvl(lom.get("menu_level"), ""))) {
						compMenuId = (String) lom.get("menu_id");
						map.put(compMenuId, new Integer(0));
						Level_One++;
					}
				}
			}

			List menuSiteMapList = (List)mainService.listSiteMapMenu(paramMap);

			//1레벨의 싸이즈
			mav.addObject("menuSize", Integer.toString(Level_One));

			Map freeMarkerMap = new HashMap();
			freeMarkerMap.put("targetMenuId", StringUtil.bvl(request.getParameter("target_menu_id"), ""));
			freeMarkerMap.put("topMenuLevel", StringUtil.bvl(propertyService.getProperty("secfw.menu.topMenuLevel"), "1"));

			mav.addObject("targetMenuId"	, StringUtil.bvl(form.getTarget_menu_id(), ""));
			mav.addObject("menuList"		, menuList);
			mav.addObject("freeMarkerMap"	, freeMarkerMap);
			mav.addObject("topMenuLevel", StringUtil.bvl(propertyService.getProperty("secfw.menu.topMenuLevel"), "1"));

			mav.addObject("menuSiteMapList", menuSiteMapList);


			mav.addObject("itvocUrl", propertyService.getProperty("secfw.url.itvoc"));
			mav.addObject("clmLoginUrl", propertyService.getProperty("secfw.url.clmLogin"));
			mav.addObject("slmsUrl", propertyService.getProperty("secfw.url.slms"));

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}

		mav.setViewName(forwardURL);
		return mav;

	}

	//통계 메뉴 View 여부
	public boolean statisticsViewFlag(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession(false);
		String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");

		boolean retV = true;

		//소속 조직이 IP센터인 경우 통계를 안보이게 한다.
		if("A00000003".equals(blngt_orgnz)){
			retV = false;
		}
		return retV;
	}

	//계약서담당자변경 view 여부 판단
	public boolean chgPersonflag(HttpServletRequest request) throws Exception{
		boolean retV = false;
		HttpSession session = request.getSession(false);

		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
		String managerYN = (String)session.getAttribute("secfw.session.manager_yn");

		ArrayList userRoleList = new ArrayList();
	    if(roleList != null && roleList.size()>0){
	    	for(int i=0; i < roleList.size() ;i++){
	        	HashMap hm = (HashMap)roleList.get(i);
	        	String roleCd = (String)hm.get("role_cd");
	        	userRoleList.add(roleCd);
	        }
		}
	    String result = "";
	    if(userRoleList != null && userRoleList.size()>0) {

			if(userRoleList.contains("RA00") || userRoleList.contains("RD01")){
				result = "A";
			}else if(userRoleList.contains("RD02")){
				result = "B";
			}
		}

	    //RA00,RD01 보여야함
	    //RD02이면서 조직장인 경우 보여야함
	    if("A".equals(result) || ("B".equals(result) && "Y".equals(managerYN))){
	    	retV = true;
	    }

	    return retV;
	}

	//법률자문
	public boolean consultFlag(HttpServletRequest request) throws Exception{
		/*
		RZ00 : 일반사용자
		RD01 : 사업부계약관리자
		RD02 : 사업부계약담당자
		*/
		boolean retV = true;
		HttpSession session = request.getSession(false);

		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");

		ArrayList userRoleList = new ArrayList();
	    if(roleList != null && roleList.size()>0){
	    	for(int i=0; i < roleList.size() ;i++){
	        	HashMap hm = (HashMap)roleList.get(i);
	        	String roleCd = (String)hm.get("role_cd");
	        	userRoleList.add(roleCd);
	        }
		}

	    if(userRoleList != null && userRoleList.size()>0) {
	     	if(userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03") || userRoleList.contains("RA00")) {
	    		retV = false;
			}
		}
	    return retV;
	}

	//소송관리 메뉴 View 여부
	public boolean slmsViewFlag(HttpServletRequest request) throws Exception{

		HttpSession session = request.getSession(false);
		/*
		RA01 : 법무관리자
		RA02 : 법무담당자
		RA03 : 법무일반사용자
		RA00 : 시스템 관리자
		*/
		String result = "";
		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
		String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");

		ArrayList userRoleList = new ArrayList();
		if(roleList != null && roleList.size()>0){
			for(int i=0; i < roleList.size() ;i++){
		    	HashMap hm = (HashMap)roleList.get(i);
		    	String roleCd = (String)hm.get("role_cd");
		    	userRoleList.add(roleCd);
		    }
		}

		if(userRoleList != null && userRoleList.size()>0) {

			if(userRoleList.contains("RA00")) {
	        	result = "A";
			}else if(userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")){
				result = "B";
			}
		}

		boolean retV = false;

		//국내/해외 법무팀 및 RA01,02,03 권한 사용자만 보이게 한다.
		if("A".equals(result) || ("B".equals(result) && ("A00000001".equals(blngt_orgnz) || "A00000002".equals(blngt_orgnz)))){
			retV = true;
		}

		return retV;

	}

	/**
	 * 메인화면관리 조회
	 *
	 * @param
	 * @return Log
	 * @throws
	*/
	public ModelAndView previewMainImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			ModelAndView mav = new ModelAndView();
			String forwardURL = "/WEB-INF/jsp/secfw/main/MainImgView.jsp";
			MainForm form = null;
			form = new MainForm();
			MainVO vo = null;
			vo = new MainVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			return mav;
		} catch(Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	/**
	 * Main Image Update
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	public ModelAndView updateMainImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 업로드 처리
			**********************************************************/
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			mainService.updateMainImage(multiRequest);
			return previewMainImage(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}

    }
}
