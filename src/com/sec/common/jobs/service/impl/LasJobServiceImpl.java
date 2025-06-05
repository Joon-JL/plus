package com.sec.common.jobs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import model.outldap.samsung.net.Employee;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.w3c.dom.Element;

import samsung.esb.common.vo.ESBFaultVO;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.service.MailSendService;

/**
 * <P>법무시스템에서 배치성으로 체크해야 하는 기능을 관리 Class</P>
 *
 * [지원기능]<BR>
 * <BR>
 * - [그룹장 이관상태조회 및 Ownership 부서 설정] : 일정주기별로 의뢰건이 개발,라이센스계약일 경우 4시간동안 IP센터의 이관요청여부 체크후 요청반영처리<BR>
 *
 * @version com.sec.common.jobs V1.0
 * 작성일 : 2011/10/06
 * @author 금현서, ace4u_khs@samsung.com
 */
public class LasJobServiceImpl  {

	protected CommonDAO commonDAO;
	protected PropertyService propertyService;
	protected ComUtilService comUtilService;
	protected MessageSource messageSource;

	/**
	 * comUtilService 선언 및 세팅
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	/**
	 * set commonDAO
	 */
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	/**
	 * set propertyService
	 */
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbMailService esbMailService;
	public void setEsbMailService(EsbMailService esbMailService) {
		this.esbMailService = esbMailService;
	}


	private MailSendService mailSendService;
	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}

	/**
	 * set messageSourceAccessor
	 */
	 public void setMessageSource(MessageSource messageSource) {
			this.messageSource = messageSource;
	 }

	 protected MessageSource getMessageSource() {
		 return messageSource;
	 }

	/*
	 * <P>3가지 기능 : 메일자동 발송설정, 종료대상확인의 경우 상태변경, 퇴사자통보메일</P>
	 * 사용자명, 부서, 직급정보는 여기서는 안넣고  ESB에서 정상 메일을 발송처리후 사용자명, 부서, 직급정보를 업데이트 처리해줌.
	 */
	@SuppressWarnings("rawtypes")
	public synchronized void clmMailBatch() throws Exception {
		//if (comUtilService.isCronServerAndPort()) {
		if (comUtilService.isCronServer()) {
			try {
				/*********************************************************
				 * Form 및 VO Binding
				**********************************************************/
				MailVO vo = new MailVO();

				/*********************************************************
				 * 파라미터세팅 : DB에서 해당메일건을 가져오는 로직추가필요
				**********************************************************/
				// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
				String moduleId = "LAS";
				vo.setModule_id(moduleId);

				// 보내는 사람
				vo.setSender_mail_addr(propertyService.getProperty("clms.admin.email") );
				String[] sTempsingle_id = ((String)propertyService.getProperty("clms.admin.email")).split("@");

				vo.setSender_single_id(sTempsingle_id[0]);

				// 로케일, 인코딩 설정, Time Zone
				String defaultLocale = propertyService.getProperty("secfw.defaultLocale");
				String time_zone	 = propertyService.getProperty("secfw.mail.default.time_zone");
				String summerTimeYn  = "";

				vo.setLocale(defaultLocale);
				vo.setEncoding(StringUtil.bvl(vo.getEncoding(),"utf-8"));
				vo.setTime_zone(time_zone);

				if ("Y".equals(summerTimeYn)) {
					vo.setIs_dst("true");
				} else {
					vo.setIs_dst("false");
				}

				//STATUS 값 설정 - Default "0"
				vo.setStatus("0");

				ArrayList targetList = (ArrayList)getTargetMailList(vo);
				
				String[] iseq_ids  = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				if (targetList != null && targetList.size() > 0) {
					iseq_ids  = new String[1];
					rec_types = new String[1];
					rec_addrs = new String[1];

					iseq_ids[0]  = "1";
					rec_types[0] = "t";

					String sflag	   = "";
					String misId	   = "";
					String last_locale = ""; // 로케일 정보

					for (int i=0; i<targetList.size(); i++) {
						// 개별 메일건마다 제목과 내용이 모두 틀리므로 MISID를 건별로 새로 생성한다.
						misId = EsbUtil.generateMisId("MAIL", i);
						vo.setMis_id(misId);

						ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);
						sflag = ((String)resultTargetMap.get("flag")).trim();

						/*********************************************************
						 * 메일자동 발송설정
						**********************************************************/
						rec_addrs[0] = ((String)resultTargetMap.get("email")).trim();
						last_locale  = ((String)resultTargetMap.get("last_locale")).trim();

						vo.setIseq_ids(iseq_ids);
						vo.setRec_types(rec_types);
						vo.setRec_addrs(rec_addrs);

						// BODY TYPE 세팅
						vo.setBhtml_content_check("true");
						vo.setBody(getTerminationDueDateMailContents(sflag,resultTargetMap));
						
						Locale locale1 = new Locale(last_locale);
						
						// Sungwoo commented 2014-07-16 subject message replacement.
						//vo.setSubject(messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1).toString());
						vo.setSubject(messageSource.getMessage("selms.email.contract.termination.due.subject", null, locale1).toString());
						
						esbMailService.insertMail(vo);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// do nothing!!
			}

			/*********************************************************
			 * 종료대상확인의 경우 상태변경 : 하루 한번만 수행
			 * getConfirm_ending : PRCS_DEPTH(C02505 계약종료 : Termination)	DEPTH_STATUS(C02681 종료대상 : will be terminated)
			 * getUpdate_Expired : PRCS_DEPTH(C02505 계약종료 : Termination)	DEPTH_STATUS(C02686 종료 : Expired)
			 * 계약대상이 will be terminated로 변경됨. 현재날짜기준 4주전
			**********************************************************/
			try {
				getConfirm_ending();// 2012.06.28 종료대상으로 바꾸는 기준이 이행정보 완료일기준으로 바뀜
				getUpdate_Expired();// 2014-04-08 신성우 추가 End Date가 만료되는 다음날 Expired.
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// do nothing!!
			}
			
			/*********************************************************
			 * 퇴사자통보메일 : 하루 한번만 수행 
			 * 주석처리함 : 현재 부서장정보를 GAIS에서 가져오게 되어 있고 현시스템과 맞지 않음(2013. 7. 3)
			**********************************************************/
//			try {
//				getRetireUser();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				//do nothing!!
//			}
		}
	}

	public List getTargetMailList(MailVO vo) throws Exception {
		//Cross-site Scripting 방지
		return commonDAO.list("common.jobs.getTargetMailList", vo);
	}
	
	/* 메일내용 리턴
		Sungwoo Replaced 2014-09-19 기존 배치메일 전체 삭제 및 termination 알림 메일만 발송하게끔 변경
	 */
	@Deprecated
	public String getMailContentNew(String sflag1, ListOrderedMap resultTargetMap) throws Exception {
		String scontent = "";
		String locale1	= ((String)resultTargetMap.get("last_locale").toString()).trim();	//로케일 정보

		String lasdomain = propertyService.getProperty("secfw.url.lasdomain");

		HashMap<String, String> hm = new HashMap<String, String>();

		//Sungwoo 2014-07-16 mail_link 중복 코드 제거 및 위치이동
		hm.put("main_link", "http://"+lasdomain+"/login.do");
		hm.put("mail_type", "24");
		hm.put("main_title",messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, new Locale(locale1)).toString());
		hm.put("param1", resultTargetMap.get("req_title").toString());	//의뢰명
		hm.put("param2"	, (String)messageSource.getMessage("clm.page.field.completion.insertCompletion04", null, new Locale(locale1)).toString());
		hm.put("last_locale", locale1);
		scontent = mailSendService.getMailContent(hm);

		return scontent;
	}
	/**
	 * return an email contents for Termination Due date
	 * @author seil.p
	 * @param sflag1
	 * @param resultTargetMap
	 * @return
	 * @throws Exception
	 */
	private String getTerminationDueDateMailContents(String sflag1, ListOrderedMap resultTargetMap) throws Exception {
		String scontent = "";
		String locale1	= ((String)resultTargetMap.get("last_locale").toString()).trim();	//로케일 정보

		String lasdomain = propertyService.getProperty("secfw.url.lasdomain");

		HashMap<String, String> hm = new HashMap<String, String>();

		//Sungwoo 2014-07-16 mail_link 중복 코드 제거 및 위치이동
		hm.put("main_link", "http://"+lasdomain+"/login.do");
		hm.put("mail_type", "24");
		hm.put("main_title",messageSource.getMessage("selms.email.contract.termination.due.contents.title", null, new Locale(locale1)).toString());
		hm.put("param1", resultTargetMap.get("req_title").toString());	//의뢰명
		hm.put("param2"	, (String)messageSource.getMessage("selms.email.contract.termination.due.contents.body", null, new Locale(locale1)).toString());
		hm.put("last_locale", locale1);
		scontent = mailSendService.getMailContent(hm);

		return scontent;
	}

	/**
	 * 계약 종료일 전 30일이 되면, 계약마스터의 DEPTH_STATUS 상태값이  이행중(C02662)일경우  종료대상(C02681)으로 변경처리
	 * 해당 의뢰건의 모든 계약이 종료대상으로 바뀌면 의뢰테이블의 상태값(PRGRS_STATUS)도  종료대상(C04220) 으로 변경처리함.
	 * Termination Step C02505 / Will be terminate C02681로 변경처리
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void getConfirm_ending() throws Exception {
		HashMap targetMap = new HashMap();
		ArrayList targetList = (ArrayList)commonDAO.list("common.jobs.getConfirm_ending", targetMap);

		if(targetList != null && targetList.size() > 0) {
			String cntrt_id = "";
			String cnsdreq_id = "";
			HashMap updateDeptMap = new HashMap();

			for(int i=0; i<targetList.size(); i++){
				ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);

				cntrt_id = ((String)resultTargetMap.get("cntrt_id")).trim();
				cnsdreq_id = ((String)resultTargetMap.get("cnsdreq_id")).trim();

				updateDeptMap = new HashMap();
				updateDeptMap.put("cntrt_id", cntrt_id);
				updateDeptMap.put("cnsdreq_id", cnsdreq_id);
				commonDAO.modify("common.jobs.updateConfirm_ending", updateDeptMap);

			}
		}
	}

	/**
	 * 계약 종료일이 지나고 다음날, 계약마스터의 DEPTH_STATUS 상태값이  이행중/(C02681)일경우  종료(C02686)으로 변경처리
	 * 해당 의뢰건의 모든 계약이 종료대상으로 바뀌면 의뢰테이블의 상태값(PRGRS_STATUS)도  종료대상(C04223) 으로 변경처리함.
	 * Termination Step C02505 / Will be terminate C02686로 변경처리
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void getUpdate_Expired() throws Exception {
		HashMap targetMap = new HashMap();
		ArrayList targetList = (ArrayList)commonDAO.list("common.jobs.getUpdate_ExpiredList", targetMap);

		if(targetList != null && targetList.size() > 0) {
			String cntrt_id = "";
			String cnsdreq_id = "";
			HashMap updateDeptMap = new HashMap();

			for(int i=0; i<targetList.size(); i++){
				ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);

				cntrt_id = ((String)resultTargetMap.get("cntrt_id")).trim();
				cnsdreq_id = ((String)resultTargetMap.get("cnsdreq_id")).trim();

				updateDeptMap = new HashMap();
				updateDeptMap.put("cntrt_id", cntrt_id);
				updateDeptMap.put("cnsdreq_id", cnsdreq_id);
				commonDAO.modify("common.jobs.update_Expired", updateDeptMap);

			}
		}
	}

	/**
	 * 계약시스템사용자중에서 퇴사자를 가려내기위해 ESB 를 EP_ID를 기준으로 조회한다.
	 * 만일 여기서 검색이 안되는 경우 퇴사자로 처리한다.
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void getRetireUser() throws Exception {
		HashMap targetMap = new HashMap();
		ArrayList targetList = (ArrayList)commonDAO.list("common.jobs.getRetireUser", targetMap);

		if(targetList != null && targetList.size() > 0) {
			String[] tempEpid = new String[100];			//USER_ID
			String[] tempReqman_nm = new String[100];		//의뢰인명
			String[] tempDept_mgr_empno = new String[100];	//부서장 EMP_NO

			String[] Epid = null; int irow_count = 0;		//USER_ID
			String[] Reqman_nm = new String[100];			//의뢰인명
			String[] Dept_mgr_empno = new String[100];		//부서장 EMP_NO

			for(int i=0; i<targetList.size(); i++){
				ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);

				tempEpid[irow_count] = ((String)resultTargetMap.get("user_id")).trim();
				tempReqman_nm[irow_count] = ((String)resultTargetMap.get("reqman_nm")).trim();
				tempDept_mgr_empno[irow_count] = ((String)resultTargetMap.get("dept_mgr_emp_no")).trim();

				irow_count ++;

				//전체 사용자숫자가 100명이하일 경우
				if(targetList.size()<=100 && i == targetList.size()-1  ){
					Epid = new String[irow_count];
					Reqman_nm = new String[irow_count];
					Dept_mgr_empno = new String[irow_count];

					//배열복사
					System.arraycopy(tempEpid, 0, Epid, 0, irow_count);
					System.arraycopy(tempReqman_nm, 0, Reqman_nm, 0, irow_count);
					System.arraycopy(tempDept_mgr_empno, 0, Dept_mgr_empno, 0, irow_count);

					//사용자체크 호출
					procCheckEpid(Epid,Reqman_nm,Dept_mgr_empno);
					break;

				//전체 사용자숫자가 100명이 넘으면서 irow_count 가  100 이 되었을 경우
				}else if(targetList.size()>100 && irow_count == 99){
					Epid = new String[irow_count];
					Reqman_nm = new String[irow_count];
					Dept_mgr_empno = new String[irow_count];

					//배열복사
					System.arraycopy(tempEpid, 0, Epid, 0, irow_count);
					System.arraycopy(tempReqman_nm, 0, Reqman_nm, 0, irow_count);
					System.arraycopy(tempDept_mgr_empno, 0, Dept_mgr_empno, 0, irow_count);

					//사용자체크 호출
					procCheckEpid(Epid,Reqman_nm,Dept_mgr_empno);

					tempEpid = new String[100];	//초기화
					irow_count = 0;				//초기화

				//루핑카운트와 전체 사용자숫자가 동일한 경우
				}else if(i == targetList.size()-1){
					Epid = new String[irow_count];
					Reqman_nm = new String[irow_count];
					Dept_mgr_empno = new String[irow_count];

					//배열복사
					System.arraycopy(tempEpid, 0, Epid, 0, irow_count);
					System.arraycopy(tempReqman_nm, 0, Reqman_nm, 0, irow_count);
					System.arraycopy(tempDept_mgr_empno, 0, Dept_mgr_empno, 0, irow_count);

					//사용자체크 호출
					procCheckEpid(Epid,Reqman_nm,Dept_mgr_empno);
					break;
				}
			}
		}
	}

	/**
	 * 파라미터로 넘겨받은 최대 100명의 사용자EPID중 ESB에서 조회되지 않는 EPIP를 찾아서 메일발송처리를 한다.
	 * @param
	 * @return HashMap
	 * @throws Exception
	 */
	private void procCheckEpid(String[] EpId, String[] Reqman_nm, String[] Dept_mgr_empno){
		try {
			Employee[] employee=null;

				try{
					employee = esbOrgService.userSearchByEpidList(EpId);

				} catch(ESBFaultVO fault) {
					fault.printStackTrace();
					Element[] fault_elements = fault.getFaultDetails();

					for(int i=0; i<fault_elements.length; i++){
						System.out.println("1st Fault Elements : " + fault_elements[i]);
					}
				}catch(Exception e){
					System.out.println("procCheckEpid Error");
				}



			StringBuffer sbAll_Epid = new StringBuffer();	//ESB를 통한 조회후 정상인 경우 변수에 저장

			//ESB를 통한 EPID를 조회한다.
			for(int irow=0; irow < employee.length; irow++){
				if(employee[irow].getEpid()!=null)
					sbAll_Epid.append("[").append((String)employee[irow].getEpid().trim()).append("]");
			}

			HashMap targetMap = new HashMap();
			ArrayList targetList = null;

			//ESB를 통한 조회건과 사용자테이블의 EPID를 비교해서 없는 ID는 퇴사자이므로 메일 발송처리필요.
			for(int irow=0; irow < EpId.length; irow++){
				if(employee.length>=1){
					//EpId가 없으면
					if( sbAll_Epid ==null || (sbAll_Epid !=null && sbAll_Epid.toString().indexOf(  "["+EpId[irow].trim()+"]"  ) == -1) ){
						ListOrderedMap resultTargetMap = new ListOrderedMap();

						resultTargetMap.put("cntrt_id","");							//2.계약ID
						resultTargetMap.put("cntrt_nm","");							//3.계약명
						resultTargetMap.put("days1","");							//4.기간일
						resultTargetMap.put("exec_plndday","");						//5.예정일

						targetMap = new HashMap();
						targetMap.put("user_id", EpId[irow]);
						targetMap.put("emp_no", Dept_mgr_empno[irow]);
						targetList = (ArrayList)commonDAO.list("common.jobs.getRetireUserReqs", targetMap);

						resultTargetMap.put("exec_cont","");						//6.이행내용
						if(targetList != null && targetList.size() > 0) {
							ListOrderedMap resultTMap = (ListOrderedMap)targetList.get(0);

							resultTargetMap.put("email",((String)resultTMap.get("email")).trim()) ;					//1.email
							resultTargetMap.put("exec_cont",((String)resultTMap.get("retire_reqs")).trim());		//6.이행내용
						}
						resultTargetMap.put("cntrt_respman_nm",Reqman_nm[irow]);	//7.담당자(이름/직급/부서)
						resultTargetMap.put("cntrt_num_nm","");						//8.계약1, 계약2 표시
						resultTargetMap.put("wsvo_title","");						//9.품의명
						resultTargetMap.put("flag","FLAG5");						//10.flag
						resultTargetMap.put("exec_itm","");							//11.이행항목

						//사용자epid로 의뢰건을 조회해서 종료가 안된 의뢰건에 대해서는 해당사업부의 부서장에게 메일을 발송처리한다.
						sendRetireEpid(resultTargetMap);
					}
				}
			}

		} catch(ESBFaultVO fault) {
			fault.printStackTrace();
			String ls_actor	=  fault.getFaultActor1();
			String ls_code	 =  fault.getFaultCode1();
			String ls_string   =  fault.getFaultString1();
			String ls_message  =  fault.getFaultString();
			String ls_detail_message  =  fault.getFaultString1();
			Element[] fault_elements = fault.getFaultDetails();

			System.out.println("procCheckEpid Fault Actor   : " + ls_actor);
			System.out.println("procCheckEpid Fault Code	: " + ls_code);
			System.out.println("procCheckEpid Fault String  : " + ls_string);
			System.out.println("procCheckEpid Fault Message : " + ls_message);
			System.out.println("procCheckEpid Fault Detail Message : " + ls_detail_message);

			for(int i=0; i<fault_elements.length; i++){
				System.out.println("Fault Elements : " + fault_elements[i]);
			}

		}catch(Exception e){
			System.out.println("procCheckEpid Error  : " + e);
		}
	}

	/* 사용자epid로 의뢰건을 조회해서 종료가 안된 의뢰건에 대해서는 해당사업부의 부서장에게 메일을 발송처리한다.
	 * @param Reqman_nm : 의뢰인
	 * @param Dept_mgr_email : 부서장 email
	 * @param Retire_reqs :의뢰제목1(의뢰1 ID),의뢰제목2(의뢰2 ID)...
	 * */
	private void sendRetireEpid(ListOrderedMap resultTargetMap) throws Exception {

		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			MailVO vo = new MailVO();

			/*********************************************************
			 * 파라미터세팅 : DB에서 해당메일건을 가져오는 로직추가필요
			**********************************************************/
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = "LAS";
			vo.setModule_id(moduleId);

			//보내는 사람
			vo.setSender_mail_addr(propertyService.getProperty("clms.admin.email") );
			String[] sTempsingle_id = ((String)propertyService.getProperty("clms.admin.email")).split("@");

			vo.setSender_single_id(sTempsingle_id[0]);

			//로케일, 인코딩 설정, Time Zone
			String defaultLocale = propertyService.getProperty("secfw.defaultLocale");
			vo.setLocale(defaultLocale);
			vo.setEncoding(StringUtil.bvl(vo.getEncoding(),"utf-8"));
			String time_zone = propertyService.getProperty("secfw.mail.default.time_zone");
			vo.setTime_zone(time_zone);
			String summerTimeYn = "";
			if("Y".equals(summerTimeYn)) {
				vo.setIs_dst("true");
			} else {
				vo.setIs_dst("false");
			}

			//STATUS 값 설정 - Default "0"
			vo.setStatus("0");

			String[] iseq_ids  = null;
			String[] rec_types = null;
			String[] rec_addrs = null;

			iseq_ids  = new String[1];
			rec_types = new String[1];
			rec_addrs = new String[1];
			iseq_ids[0] = "1";
			rec_types[0] = "t";
			String sflag = "FLAG5"; //퇴사통보
			String wsvo_title = "";
			String misId = "";

			//개별 메일건마다 제목과 내용이 모두 틀리므로 MISID를 건별로 새로 생성한다.
			misId = EsbUtil.generateMisId("MAIL");
			vo.setMis_id(misId);

			rec_addrs[0] = ((String)resultTargetMap.get("email")).trim();
			wsvo_title = "[In Charge of Contract(" + ((String)resultTargetMap.get("cntrt_respman_nm")).trim() + ") Notify Resignation]";	//품의명

			vo.setIseq_ids(iseq_ids);
			vo.setRec_types(rec_types);
			vo.setRec_addrs(rec_addrs);
			//BODY TYPE 세팅
			vo.setBhtml_content_check("true");
			vo.setBody(getMailContentNew(sflag,resultTargetMap));
			vo.setSubject(wsvo_title);

			// TODO: 테스트중이며 운영반영시 주석해제할것.
			esbMailService.insertMail(vo);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sendRetireEpid Error========");
		} finally{

		}
	}

	public void modifyNotUpdatedStartProcessWsvo() throws Exception{
		//체결,종료 결재 완료가 되었는데도 불구하고 여전히 업데이트가 안된 놈들을 골라서 결재 진행중으로 바꾸는데 사용하는 쿼리

		//if(comUtilService.isCronServerAndPort()) {
		if(comUtilService.isCronServer()) {

			getLogger().info("modifyNotUpdatedStartProcessWsvo 시작");

			if(commonDAO == null) getLogger().info("commonDAO == null");

			int resultValue = commonDAO.modify("common.jobs.modifyNotUpdatedStartProcessWsvo");//체결품의

			getLogger().info("resultValue: "+resultValue);
			getLogger().info("modifyNotUpdatedStartProcessWsvo 종료");

			resultValue = commonDAO.modify("common.jobs.modifyNotUpdatedStartProcessWsvoAtEnd");//종료품의

			getLogger().info("resultValue: "+resultValue);
			getLogger().info("common.jobs.modifyNotUpdatedStartProcessWsvoAtEnd 종료");

		}
	}

	protected Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}
}
