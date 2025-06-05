package com.sec.clm.manage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.dto.ConsiderationVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.util.ClmsDataUtil;
import com.sec.clm.manage.dto.GERPInfoVO;

/**
 * Description	: 계약관리 검토의뢰 Service impl(concrete class)
 * Author 		: 김재원
 * Date			: 2013.04
 */
public class ConsiderationServiceImpl extends CommonServiceImpl implements ConsiderationService {
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	/**
	 * 계약 마스터  아이디 설정 
	 */
	public String getId() throws Exception {
		String getId = "";
		List<?> resultList = null;
		
		resultList = commonDAO.list("clm.manage.getId"); 
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			getId = StringUtil.replace((String)lom.get("GET_ID")," ","");
			
		} 		
		return getId;
	}
	
	/**
	 * 회신 요청일 일,토 빼고 3일 이후 로 설정 하기 
	 */
	public String getReDemndday() throws Exception {
		//회신 요청일 설정 
		String curYMD = DateUtil.today();
		// 3일후로 설정
		// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
		if(DateUtil.isDayOfWeek(curYMD, 7)) {
		    // 토요일이면 1일 증가          : 3 + 1 = 4
		    return DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),4),"-");
		} else if(DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
		    // 수, 목, 금요일이면 2일 증가      : 3 + 2 = 5
		    return DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),5),"-");
		} else {
		    // 그외 (일, 월, 화요일)이면 증가 없음  : 3 + 0 = 3
		    return DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),3),"-");
		}
	}
	
	public String getReqStatus(String prev_cnsdreq_id,String plndbn_req_yn, ConsiderationVO vo) throws Exception{
		
	
		
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		if("".equals(prev_cnsdreq_id) && "N".equals(plndbn_req_yn)){//최초의뢰
			//메시지 처리 - 검토
			return (String)messageSource.getMessage("clm.page.field.consideration.getReqStatus01", null, locale1);
		}else if(!"".equals(prev_cnsdreq_id) && "N".equals(plndbn_req_yn)){//재의뢰
			//메시지 처리 - 재검토
			return (String)messageSource.getMessage("clm.page.field.consideration.getReqStatus02", null, locale1);
		}
		//else if(!"".equals(prev_cnsdreq_id) && "Y".equals(plndbn_req_yn)){//최종본의뢰
		else if("Y".equals(plndbn_req_yn)){//최종본의뢰
			//메시지 처리 - 최종본
			return (String)messageSource.getMessage("clm.page.field.consideration.getReqStatus03", null, locale1);
		}else{
			return "Error";
		}
	}
	
	
	public String getReqStatus(String prev_cnsdreq_id,String plndbn_req_yn, String last_locale) throws Exception{
		

		
		Locale locale1 = new Locale(last_locale);
		
		if("".equals(prev_cnsdreq_id) && "N".equals(plndbn_req_yn)){//최초의뢰
			//메시지 처리 - 검토
			return (String)messageSource.getMessage("clm.page.field.consideration.getReqStatus01", null, locale1);
		}else if(!"".equals(prev_cnsdreq_id) && "N".equals(plndbn_req_yn)){//재의뢰
			//메시지 처리 - 재검토
			return (String)messageSource.getMessage("clm.page.field.consideration.getReqStatus02", null, locale1);
		}
		//else if(!"".equals(prev_cnsdreq_id) && "Y".equals(plndbn_req_yn)){//최종본의뢰
		else if("Y".equals(plndbn_req_yn)){//최종본의뢰
			//메시지 처리 - 최종본
			return (String)messageSource.getMessage("clm.page.field.consideration.getReqStatus03", null, locale1);
		}else{
			return "Error";
		}
	}
	
	
	
	/** 
	 * 연관계약 입력시 계약 리스트 (법무 그룹장 로그인 시)
	 */
	public List popupListContract(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.popupListContract",vo);
		
	}
	
	/** 
	 * 연관계약 입력시 계약 리스트 (법무 담당자 로그인 시)
	 */
	public List popupListContract1(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.popupListContract1",vo);
		
	}
	
	/** 
	 * 연관계약 입력시 계약 리스트 (법무 담당자 로그인 시)
	 */
	public List popupListContract4(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.popupListContract4",vo);
		
	}
	
	/** 
	 * 연관계약 입력시 계약 리스트 (법무 담당자 로그인 시)
	 */
	public List popupListContract2(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.popupListContract2",vo);
		
	}
	
	/** 
	 * 연관계약 입력시 계약 리스트 (시스템 관리자 로그인 시)
	 */
	public List popupListContract3(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.popupListContract3",vo);
		
	}

	/**
	 * 계약건 상세정보  view 화면에서 사용
	 */
	public List detailContractMaster(ConsultationVO vo) throws Exception {
	
		return commonDAO.list("clm.manage.detailContractMaster", vo);
	}
	
	/**
	 * 계약건 수정시 상세정보  master 테이블 데이타만 가져옴
	 */
	public List detailForwardContractMaster(ConsultationVO vo) throws Exception {

		return commonDAO.list("clm.manage.detailForwardContractMaster", vo);
	}
	
	/**
	 * 계약 탭클릭시 정보 저장 master key 유무에 따라 update or insert
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertContractMaster(ConsultationVO vo) throws Exception {
		
		List<?> resultList = null;
		int resultVal = -1;
		
		// update 후 cntrt_no 값이 있는지 없는지 판단하여 update하는 값.
		boolean exitUpdateYn = false;
		
		int iUpdateResult  = 0; //TN_CLM_CONTRACT_MASTER update 결과  1월 7일 김재원 추가
		ListOrderedMap returnLom = new ListOrderedMap();
		
		vo.setRegion_gbn("C01801");	//국내 - master	REGION_GBN
		vo.setSeal_mthd("C02102");	//구주법인은 서명만
		vo.setPrcs_depth("C02501");//프로세스 단계
		vo.setPrgrs_status("C04201"); //임시저장		
		
		if(!"".equals(vo.getStd_cnslt_no()) && ("req".equals(vo.getSubmit_status()) || "again".equals(vo.getSubmit_status()) || "last".equals(vo.getSubmit_status())) ){
			vo.setDepth_status("C02602");//단계상태 결재중
		}else{
			vo.setDepth_status("C02601");//단계상태 작성중
		}
		
		//master
		//거래 상대방 내역 
		vo.setCntrt_oppnt_nm(StringUtil.convertHtmlTochars(StringUtil.bvl((String)vo.getCntrt_oppnt_nm(),""))); // 계약상대방
		vo.setCntrt_oppnt_rprsntman(StringUtil.convertHtmlTochars(StringUtil.bvl((String)vo.getCntrt_oppnt_rprsntman(),""))); // 대표자명
		//기타주요사항 <BR>ETC_MAIN_CONT
		vo.setEtc_main_cont(StringUtil.convertHtmlTochars(StringUtil.bvl((String)vo.getEtc_main_cont(), "")));
		//단가내역cntrt_untprc_expl
		vo.setCntrt_untprc_expl(StringUtil.convertHtmlTochars(StringUtil.bvl((String)vo.getCntrt_untprc_expl(), "")));
		//계약대상 상세내역cntrt_trgt_det
		vo.setCntrt_trgt_det(StringUtil.convertHtmlTochars(StringUtil.bvl((String)vo.getCntrt_trgt_det(), "")));
		vo.setCntrtperiod_startday(StringUtil.removeChar(vo.getCntrtperiod_startday(), '-'));
		vo.setCntrtperiod_endday(StringUtil.removeChar(vo.getCntrtperiod_endday(),'-'));
		vo.setAuto_rnew_yn("N");//not null 없어질것
		vo.setCnclsn_plndday(DateUtil.getTime("yyyyMMdd"));  	//체결_예정일 수정 요망	
		vo.setCntrt_respman_id(vo.getSession_user_id());  		//계약_담당자_ID 		
		vo.setCntrt_respman_nm(vo.getSession_user_nm());	//계약_담당자_명
		vo.setCntrt_resp_dept(vo.getSession_dept_cd());		//계약_담당_부서
		vo.setCntrt_resp_dept_nm(vo.getSession_dept_nm());	//계약_담당_부서_명
		vo.setCntrt_resp_up_dept(vo.getSession_up_level_dept_cd());		//계약_담당_상위_부서
		vo.setCntrt_respman_jikgup_nm(vo.getSession_jikgup_nm());	//계약_담당자_직급_명			
		vo.setSign_plndman_id(vo.getSession_user_id());			//서명_예정자_ID 수정 요망		
		vo.setReqman_jikgup_nm(vo.getSession_jikgup_nm()); // 2011-10-15 mod by 김현구
		vo.setFst_cntrt_resp_dept(vo.getSession_dept_cd());
		vo.setFst_cntrt_resp_up_dept(vo.getSession_up_level_dept_cd());
		vo.setCntrt_cnclsn_yn("Y"); 		//계약 체결 여부 
		vo.setCntrt_status("C02401");		//계약상태 미체결 master
		vo.setCntrt_chge_conf_yn("N");		//not null
		vo.setReg_operdiv(vo.getSession_blngt_orgnz());// 사업부 - 소속조직코드		
		vo.setBfhdcstn_apbtday(StringUtil.removeChar(vo.getBfhdcstn_apbtday(), '-'));	//사전품의_승인일
		//REG_DT
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setDel_yn("N");
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
		
		vo.setPshdbkgrnd_purps((StringUtil.convertNamoCharsToHtml(vo.getBody_mime1())));
		/*******************************************************************
		 *  계약 마스테테이블 해당 계약건 유무 조회
		 ********************************************************************/
		resultList = commonDAO.list("clm.manage.getContractMaster", vo);
		
		if (resultList.size() > 0) {  //master id O	
			//master update
			iUpdateResult = commonDAO.modify("clm.manage.modifyContractMaster", vo);
			
			if(iUpdateResult > 0){
				//cnsd update
				commonDAO.modify("clm.manage.modifyContractCnsd",vo);
				exitUpdateYn = true;
			} 
			
			if(!"save".equals(vo.getSubmit_status())){
				String master_cntrt_ids[] = vo.getArr_cntrt_id();
				int modCnt = 0;
				ConsultationVO hisVo = null;
				for(int i=0; i<master_cntrt_ids.length; i++) {	
					vo.setMaster_cntrt_id((String)master_cntrt_ids[i]);
					modCnt = commonDAO.modify("clm.manage.modifyContractMaster.depthStatus",vo);	//상태값 완료 검토의견없뎃 의뢰에 계약 여러건 상태 업데이트
					if(modCnt > 0){
						hisVo = new ConsultationVO();
						hisVo.setCntrt_id((String)master_cntrt_ids[i]);
						hisVo.setDepth_status(vo.getDepth_status());
						hisVo.setSession_comp_cd(vo.getSession_comp_cd());
						hisVo.setSession_comp_nm(vo.getSession_comp_nm());
						hisVo.setSession_user_id(vo.getSession_user_id());
						hisVo.setSession_user_nm(vo.getSession_user_nm());
					}
				}
				
				//Sungwoo 2014-07-08 moved Save가 아닌 Request 일때만 번호 생성
				if(exitUpdateYn){
					// 위에서 조회해온 값에서 cntrt_no가 있는지 없는지 판단
					ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
					
					// exit_cntrt_no 값의 판단을 위해 생성
					String exit_cntrt_no = lom.get("CNTRT_NO") != null ? lom.get("CNTRT_NO").toString() : "" ;
					
					// cntrt_no값이 "" 또는 null 인 경우 cntrt_no 값을 update 함. 
					if("".equals(exit_cntrt_no) || null == exit_cntrt_no){
						// cntrt_id 값을 가지고 cntrt_no 값을 update함.
						commonDAO.modify("clm.manage.updateCntrtNo", vo);
					}
				}

			}
			
			//계약 유형특화정보 저장	
			if(vo.getArr_attr_seqno() != null){
				for(int j=0; j<vo.getArr_attr_seqno().length; j++){				
					
					String[] arrTmpSeqno = vo.getArr_attr_seqno();
					String[] arrTmpCd = vo.getArr_attr_cd();
					String[] arrTmpCont = vo.getArr_attr_cont();
					vo.setAttr_seqno(arrTmpSeqno[j]);
					vo.setAttr_cd(arrTmpCd[j]);
					vo.setAttr_cont(StringUtil.convertCharsToHtml(StringUtil.bvl((String)arrTmpCont[j],"")));
					commonDAO.delete("clm.manage.deleteContTypeSpclinfo", vo);		//유형특화정보
					
				}
			}
		} else {  //master id X
			
			System.out.println("*************************************************************");
			System.out.println("*************************************************************");
			System.out.println("*************************************************************");
			
			System.out.println("ConsiderationServiceImpl");
			
			System.out.println("*************************************************************");
			System.out.println("*************************************************************");
			System.out.println("*************************************************************");
			
			System.out.println(vo);
			
			System.out.println("*************************************************************");
			System.out.println("*************************************************************");
			System.out.println("*************************************************************");
			
			commonDAO.insert("clm.manage.insertContractMaster", vo);
			vo.setCnsd_dept(vo.getSession_dept_cd());  //임시설정 
			vo.setCnsd_status("C04301");
			
			if("".equals(vo.getCnsdreq_id())){
				//없으면  의뢰 아이디 구하기 
				List<?> rt = commonDAO.list("clm.manage.insertContractCnsdFinal",vo);
				if(rt.size() > 0){					
					ListOrderedMap map = (ListOrderedMap)rt.get(0);
					vo.setCnsdreq_id((String)map.get("cnsdreq_id"));
				}
				commonDAO.insert("clm.manage.insertContractCnsd",vo);
			}else{
				commonDAO.insert("clm.manage.insertContractCnsd",vo);
			}
		}
		
		//텝클리시에는 삭제 안함니다. 관련자는 임시저장,의뢰시에만 적용
		//권한요청 - 관련자 리스트 저장
		if(!"tab_save".equals(vo.getSubmit_status())){  //tab_save 가 아닌면 
			resultVal = commonDAO.delete("clm.manage.deleteContractAuthreq", vo);		//권한요청 삭제
			if(vo.getArr_trgtman_id() != null){
				//배열계약 아이디을 검색해서 활성화된 계약 아이디가 없으면 배열 계약 변수에 put
				ArrayList<String> listCntrtId = new ArrayList<String>();
				if(vo.getArr_cntrt_id() != null){
					for(int k=0; k<vo.getArr_cntrt_id().length; k++){	//계약건만틈 관련자 정보 저장
						String[] arrTmpCntrtId = vo.getArr_cntrt_id();
						listCntrtId.add(arrTmpCntrtId[k]); 
					}

					if(!listCntrtId.contains(vo.getCntrt_id())){
						listCntrtId.add(vo.getCntrt_id());						
					}
				}else{
					listCntrtId.add(vo.getCntrt_id());
				}
	
				for(int k=0; k < listCntrtId.size(); k++){
					for(int j=0; j<vo.getArr_trgtman_id().length; j++){						
						String[] arrTmpId = vo.getArr_trgtman_id();
						String[] arrTmpNm = vo.getArr_trgtman_nm();
						String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
						String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();
						
						vo.setMaster_cntrt_id((String)listCntrtId.get(k));	
						vo.setDemnd_seqno(Integer.toString(j+1));
						vo.setDemnd_gbn("C04601");
						vo.setDemndman_id(vo.getSession_user_id());	//요청자 아이디
						vo.setDemndman_nm(vo.getSession_user_nm());
						vo.setDemndman_dept_nm(vo.getSession_dept_nm());			
						vo.setTrgtman_id(arrTmpId[j]);  			//
						vo.setTrgtman_nm(arrTmpNm[j]);				
						vo.setTrgtman_dept_nm(arrTmpDeptNm[j]);
						vo.setTrgtman_jikgup_nm(arrTmpJikgupNm[j]);
						vo.setDemnd_status("C03702");
						
						commonDAO.insert("clm.manage.insertContractAuthreq",vo);
					}		
				}
			}else{
				resultVal= 1;
			}
		}else{
			resultVal= 1;
		}
		
		//계약 유형특화정보 저장
		if(vo.getArr_attr_seqno() != null){
			for(int j=0; j<vo.getArr_attr_seqno().length; j++){				
				
				String[] arrTmpSeqno = vo.getArr_attr_seqno();
				String[] arrTmpCd = vo.getArr_attr_cd();
				String[] arrTmpCont = vo.getArr_attr_cont();
				vo.setAttr_seqno(arrTmpSeqno[j]);
				vo.setAttr_cd(arrTmpCd[j]);
				vo.setAttr_cont(StringUtil.convertHtmlTochars(StringUtil.bvl((String)arrTmpCont[j],"")));
				
				commonDAO.insert("clm.manage.insertContTypeSpclinfo", vo);		//유형특화정보
				
			}
		}else{
			resultVal= 1;
		}
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();
		
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());		
		
		//#1 계약서 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120201");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos1());

		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//fileListEtc 첨부파일 - 첨부/별첨
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120208");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfosEtc());

		clmsFileService.mngClmsAttachFile(fvo, vo);		
		
		//#2 계약서 기타첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120205");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos2());

		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//#3 단가첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120211");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos3());

		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//#4사전검토정보첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01201");
		fvo.setFile_smlclsfcn("F0120101");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos4());

		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		if(resultVal > -1){
			 returnLom.put("cd","Y");
		}
		
		if (resultList.size() > 0) {  //master id O
			returnLom.put("cnsdreq_id", vo.getCnsdreq_id());
			returnLom.put("cntrt_id", vo.getCntrt_id());
			returnLom.put("msg", "");
			returnLom.put("exe_status", "update");
		}else{
			returnLom.put("cnsdreq_id", vo.getCnsdreq_id());
			returnLom.put("cntrt_id", vo.getCntrt_id());
			returnLom.put("exe_status", "insert");
		}
					
		return returnLom;
	}
	
	/**
	 * 계약 탭클릭시 정보 저장 master key 유무에 따라 update or insert -- 체결후등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertContractMasterReg(ConsultationVO vo) throws Exception {
		
		List<?> resultList = null;
		int resultVal = -1;
		
		int iUpdateResult  = 0; //TN_CLM_CONTRACT_MASTER update 결과  1월 7일 김재원 추가
		// update 후 cntrt_no 값이 있는지 없는지 판단하여 update하는 값.
		boolean exitUpdateYn = false;
		
		ListOrderedMap returnLom = new ListOrderedMap();
		
		/*********************************************************
		 * 파라미터세팅
		**********************************************************/
		/* C002
		 * F - 해외
		 * H - 국내
		 * A - 전략
		 */
		vo.setSeal_mthd("C02102");	//구주법인은 서명만
		vo.setPrcs_depth("C02508");//프로세스 단계
		vo.setPrgrs_status("C04211"); //임시저장				
		vo.setDepth_status("C02631");//단계상태 작성중
		
		//master
		//거래 상대방 내역 
		vo.setCntrt_oppnt_rprsntman(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getCntrt_oppnt_rprsntman(),"")));
		vo.setCntrt_oppnt_cd(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getCntrt_oppnt_cd(),"")));
		vo.setCntrt_oppnt_email(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getCntrt_oppnt_email(),"")));
		vo.setCntrt_oppnt_telno(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getCntrt_oppnt_telno(),"")));
		vo.setCntrt_oppnt_respman(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getCntrt_oppnt_respman(),"")));
		
		//기대효과  <BR>ANTCPTNEFCT
		vo.setAntcptnefct(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getAntcptnefct(), "")));
		//지불/수분조건 <BR>PAYMENT_COND
		vo.setPayment_cond(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getPayment_cond(), "")));
		//기타주요사항 <BR>ETC_MAIN_CONT
		vo.setEtc_main_cont(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getEtc_main_cont(), "")));
		//단가내역cntrt_untprc_expl
		vo.setCntrt_untprc_expl(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getCntrt_untprc_expl(), "")));
		//계약대상 상세내역cntrt_trgt_det
		vo.setCntrt_trgt_det(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getCntrt_trgt_det(), "")));
		
		
		vo.setCntrtperiod_startday(StringUtil.removeChar(vo.getCntrtperiod_startday(), '-'));
		vo.setCntrtperiod_endday(StringUtil.removeChar(vo.getCntrtperiod_endday(),'-'));
		vo.setAuto_rnew_yn("Y");//not null 없어질것
		vo.setCnclsn_plndday(DateUtil.getTime("yyyyMMdd"));  	//체결_예정일 수정 요망	
		
		//master
		vo.setCntrt_respman_id(vo.getSession_user_id());  		//계약_담당자_ID 		
		vo.setCntrt_respman_nm(vo.getSession_user_nm());	//계약_담당자_명
		vo.setCntrt_resp_dept(vo.getSession_dept_cd());		//계약_담당_부서
		vo.setCntrt_resp_dept_nm(vo.getSession_dept_nm());	//계약_담당_부서_명
		vo.setCntrt_resp_up_dept(vo.getSession_up_level_dept_cd());		//계약_담당_상위_부서
		vo.setCntrt_respman_jikgup_nm(vo.getSession_jikgup_nm());	//계약_담당자_직급_명			
		vo.setSign_plndman_id(vo.getSession_user_id());			//서명_예정자_ID 수정 요망		
		vo.setReqman_jikgup_nm(vo.getSession_jikgup_nm()); // 2011-10-15 mod by 김현구
		vo.setFst_cntrt_resp_dept(vo.getSession_dept_cd());
		vo.setFst_cntrt_resp_up_dept(vo.getSession_up_level_dept_cd());
		
		vo.setCntrt_cnclsn_yn("N"); 		//계약 체결 여부 
		vo.setCntrt_status("C02401");		//계약상태 미체결 master
		vo.setCntrt_chge_conf_yn("N");		//not null
		vo.setReg_operdiv(vo.getSession_blngt_orgnz());// 사업부 - 소속조직코드		
		vo.setBfhdcstn_apbtday(StringUtil.removeChar(vo.getBfhdcstn_apbtday(), '-'));	//사전품의_승인일
		
		// 사전승인정보
		vo.setBfhdcstn_mtnman_nm(StringUtil.convertCharsToHtml(StringUtil.nvl(vo.getBfhdcstn_mtnman_nm(),""))) ; // 발의자
		vo.setBfhdcstn_apbtman_nm(StringUtil.convertCharsToHtml(StringUtil.nvl(vo.getBfhdcstn_apbtman_nm(),""))) ; // 승인자
		
		//REG_DT
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setDel_yn("N");
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
		vo.setPshdbkgrnd_purps((StringUtil.convertNamoCharsToHtml(vo.getBody_mime1())));
		/*******************************************************************
		 *  계약 마스테테이블 해당 계약건 유무 조회
		 ********************************************************************/
		resultList = commonDAO.list("clm.manage.getContractMaster", vo);		
		if (resultList.size() > 0) {  //master id O	
			
			//master update
			iUpdateResult = commonDAO.modify("clm.manage.modifyContractMaster", vo);
			
			if(iUpdateResult > 0){
				//cnsd update
				commonDAO.modify("clm.manage.modifyContractCnsd",vo);
				
				exitUpdateYn = true;
				
			} else {

			}
			
			
			
			if(!"save".equals(vo.getSubmit_status())){
				String master_cntrt_ids[] = vo.getArr_cntrt_id();
				for(int i=0; i<master_cntrt_ids.length; i++) {	
					vo.setMaster_cntrt_id((String)master_cntrt_ids[i]);
					commonDAO.modify("clm.manage.modifyContractMaster.depthStatus",vo);	//상태값 완료 검토의견없뎃 의뢰에 계약 여러건 상태 업데이트
				}

				//Sungwoo 2014-07-08 moved Save가 아닌 Request 일때만 번호 생성
				if(exitUpdateYn){
					
					// 위에서 조회해온 값에서 cntrt_no가 있는지 없는지 판단
					ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
					
					// exit_cntrt_no 값의 판단을 위해 생성
					String exit_cntrt_no = lom.get("CNTRT_NO") != null ? lom.get("CNTRT_NO").toString() : "" ;
					
					// cntrt_no값이 "" 또는 null 인 경우 cntrt_no 값을 update 함. 
					if("".equals(exit_cntrt_no) || null == exit_cntrt_no){
						// cntrt_id 값을 가지고 cntrt_no 값을 update함.
						commonDAO.modify("clm.manage.updateCntrtNo", vo);
					}
				}

			}
			
			//계약 유형특화정보 저장	
			if(vo.getArr_attr_seqno() != null){
				for(int j=0; j<vo.getArr_attr_seqno().length; j++){				
					
					String[] arrTmpSeqno = vo.getArr_attr_seqno();
					String[] arrTmpCd = vo.getArr_attr_cd();
					String[] arrTmpCont = vo.getArr_attr_cont();
					vo.setAttr_seqno(arrTmpSeqno[j]);
					vo.setAttr_cd(arrTmpCd[j]);
					vo.setAttr_cont(StringUtil.convertCharsToHtml(StringUtil.bvl((String)arrTmpCont[j],"")));

					commonDAO.delete("clm.manage.deleteContTypeSpclinfo", vo);		//유형특화정보
					
				}
			}
			
		} else {  //master id X
			

			commonDAO.insert("clm.manage.insertContractMasterReg", vo);
			//계약관리_계약별_검토
			//vo.setCnsd_dept(vo.getMn_cnsd_dept());
			//vo.setCnsdreq_id(vo.getCnsdreq_id());
			vo.setCnsd_dept(vo.getSession_dept_cd());  //임시설정 
			vo.setCnsd_status("C04301");
			

			
			
			if("".equals(vo.getCnsdreq_id())){
				//없으면  의뢰 아이디 구하기 
				List<?> rt = commonDAO.list("clm.manage.insertContractCnsdFinal",vo);
				
				if(rt.size() > 0){					
					ListOrderedMap map = (ListOrderedMap)rt.get(0);
					vo.setCnsdreq_id((String)map.get("v_new_cnsdreq_id"));
				}
			}else{
				commonDAO.insert("clm.manage.insertContractCnsd",vo);
			}
		}
		

		
		
		//텝클리시에는 삭제 안함니다. 관련자는 임시저장,의뢰시에만 적용
		//권한요청 - 관련자 리스트 저장
		if(!"tab_save".equals(vo.getSubmit_status())){  //tab_save 가 아닌면 
			
			resultVal = commonDAO.delete("clm.manage.deleteContractAuthreq", vo);		//권한요청 삭제
			

			if(vo.getArr_trgtman_id() != null){

				//배열계약 아이디을 검색해서 활성화된 계약 아이디가 없으면 배열 계약 변수에 put
				
				ArrayList<String> listCntrtId = new ArrayList<String>();
				if(vo.getArr_cntrt_id() != null){
					for(int k=0; k<vo.getArr_cntrt_id().length; k++){	//계약건만틈 관련자 정보 저장
						String[] arrTmpCntrtId = vo.getArr_cntrt_id();

						listCntrtId.add(arrTmpCntrtId[k]); 
					}

					if(!listCntrtId.contains(vo.getCntrt_id())){

						listCntrtId.add(vo.getCntrt_id());						
					}
				}else{
					listCntrtId.add(vo.getCntrt_id());
				}

				
				for(int k=0; k < listCntrtId.size(); k++){
					//ListOrderedMap map = (ListOrderedMap)listCntrtId.get(k);					
		
					for(int j=0; j<vo.getArr_trgtman_id().length; j++){						
						String[] arrTmpId = vo.getArr_trgtman_id();
						String[] arrTmpNm = vo.getArr_trgtman_nm();
						String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
						String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();
						
						vo.setMaster_cntrt_id((String)listCntrtId.get(k));	
						vo.setDemnd_seqno(Integer.toString(j+1));
						vo.setDemnd_gbn("C04601");
						vo.setDemndman_id(vo.getSession_user_id());	//요청자 아이디
						vo.setDemndman_nm(vo.getSession_user_nm());
						vo.setDemndman_dept_nm(vo.getSession_dept_nm());			
						vo.setTrgtman_id(arrTmpId[j]);  			//
						vo.setTrgtman_nm(arrTmpNm[j]);				
						vo.setTrgtman_dept_nm(arrTmpDeptNm[j]);
						vo.setTrgtman_jikgup_nm(arrTmpJikgupNm[j]);
						vo.setDemnd_status("C03702");
						
						commonDAO.insert("clm.manage.insertContractAuthreq",vo);
					}		
				}
			}else{
				resultVal= 1;
			}
		}else{
			resultVal= 1;
		}
		
		//계약 유형특화정보 저장

		if(vo.getArr_attr_seqno() != null){
			for(int j=0; j<vo.getArr_attr_seqno().length; j++){				
				
				String[] arrTmpSeqno = vo.getArr_attr_seqno();
				String[] arrTmpCd = vo.getArr_attr_cd();
				String[] arrTmpCont = vo.getArr_attr_cont();
				vo.setAttr_seqno(arrTmpSeqno[j]);
				vo.setAttr_cd(arrTmpCd[j]);
				vo.setAttr_cont(StringUtil.convertCharsToHtml(StringUtil.bvl((String)arrTmpCont[j],"")));

				
				commonDAO.insert("clm.manage.insertContTypeSpclinfo", vo);		//유형특화정보
				
			}
		}else{
			resultVal= 1;
		}
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();
		
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());		

		
		//if(null != vo.getFileInfos1() && "" !=vo.getFileInfos1()){
		//#1 계약서 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120201");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos1());

		clmsFileService.mngClmsAttachFile(fvo, vo);
		//}
		
		//if(null != vo.getFileInfosEtc() && "" !=vo.getFileInfosEtc()){
		//fileListEtc 첨부파일 - 첨부/별첨
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120208");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfosEtc());

		clmsFileService.mngClmsAttachFile(fvo);		
		//}
		//if(null != vo.getFileInfos2() && ""!=vo.getFileInfos2()){
		//#2 계약서 기타첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120205");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos2());

		clmsFileService.mngClmsAttachFile(fvo);
		//}
		//if(null != vo.getFileInfos3() && "" !=vo.getFileInfos3()){
		//#3 단가첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120211");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos3());

		clmsFileService.mngClmsAttachFile(fvo);
		//}
		//if(null != vo.getFileInfos4() && ""!=vo.getFileInfos4()){
		//#4사전검토정보첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01201");
		fvo.setFile_smlclsfcn("F0120101");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos4());

		clmsFileService.mngClmsAttachFile(fvo);
		//}
		
		if(resultVal > -1){
			 returnLom.put("cd","Y");
		}
		
		if (resultList.size() > 0) {  //master id O
			
			returnLom.put("cnsdreq_id", vo.getCnsdreq_id());
			returnLom.put("cntrt_id", vo.getCntrt_id());
			returnLom.put("msg", "");
			returnLom.put("exe_status", "update");
		}else{
			
			returnLom.put("cnsdreq_id", vo.getCnsdreq_id());
			returnLom.put("cntrt_id", vo.getCntrt_id());
			returnLom.put("exe_status", "insert");
		}

			
		return returnLom;
	}
	
	/**
	 * 재의뢰 및 예정본 의뢰시 배정된 담당자 재 지정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int confirmRespman(ConsultationVO vo) throws Exception {		
		//법무담당자 삭제
		commonDAO.delete("clm.contractmanager.deleteRespman", vo);
		
		List<?> listRm = commonDAO.list("clm.manage.selectCnsdreqRespMan", vo);
		for(int j=0; j<listRm.size(); j++){
			ListOrderedMap lom = (ListOrderedMap)listRm.get(j);
			vo.setCnsdreq_id(vo.getCnsdreq_id());
			vo.setResp_dept((String)lom.get("resp_dept"));
			vo.setRespman_id((String)lom.get("respman_id"));				
			vo.setRespman_nm((String)lom.get("respman_nm"));
			vo.setRespman_jikgup_nm((String)lom.get("respman_jikgup_nm"));		         
	        vo.setMod_id(vo.getSession_user_id());
	        vo.setMod_nm(vo.getSession_user_nm());
	        vo.setMain_cnsd_yn((String)lom.get("main_cnsd_yn"));
	        vo.setAuto_apbt_yn((String)lom.get("auto_apbt_yn"));
			commonDAO.insert("clm.manage.insertCnsdreqRespMan", vo);
		}
		return 1;
	}
	
	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */	
	public int insertConsiderationStatus(ConsultationVO vo) throws Exception{
		int returnValue = -1;
		
		if("req".equals(vo.getSubmit_status())){
			
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04201"); //의뢰
			vo.setDepth_status("C02601");//단계상태 - 결재 이후 C02602 로 변경
			
		}else if("reqIF".equals(vo.getSubmit_status())){	//인터페이스에서 넘어온 데이타 경우 결재 타지 않음
			
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04203"); //의뢰
			vo.setDepth_status("C02606");//단계상태 - 검토중
			
			/** 작성중에서 검토단계로 넘어갈때 W처리
			계약유형1이 '개발T0301' 또는 '라이선스T0303' 이고 국내법무 1차 의뢰일 경우만 개발라이센스의 경우 패스트 트랙을 탄다 =>20120911추가
			**/
			if (("T0301".equals(vo.getCnclsnpurps_bigclsfcn())
					|| "T0303".equals(vo.getCnclsnpurps_bigclsfcn())) 
					&& "A00000001".equals(vo.getMn_cnsd_dept())) {
				
					vo.setFast_track_div("W");	//1차 의뢰인지는 쿼리에서 확인후 업데이트함
					
			}
		}else if("again".equals(vo.getSubmit_status())){
			
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04204"); //의뢰->검토
			vo.setDepth_status("C02606");//단계상태 작성중
			
		}else if("last".equals(vo.getSubmit_status())){	
			
			vo.setPrcs_depth("C02501");//프로세스 단계				
			vo.setPrgrs_status("C04204"); //의뢰
			vo.setDepth_status("C02606");//단계상태 - 검토중
			
		} else {   // 체결 후 등록 추가
			
			vo.setPrgrs_status("C04214");    // 체결후등록 결재중
			vo.setPrcs_depth("C02508");      // 프로세스 단계
			vo.setDepth_status("C02632");    // 단계상태 
			
		}
		
		
		ArrayList<String> listCntrtId = new ArrayList<String>();
		if(vo.getArr_cntrt_id() != null){
			for(int k=0; k<vo.getArr_cntrt_id().length; k++){	//계약건만틈 관련자 정보 저장
				String[] arrTmpCntrtId = vo.getArr_cntrt_id();
				listCntrtId.add(arrTmpCntrtId[k]); 
			}

			if(!listCntrtId.contains(vo.getCntrt_id())){

				listCntrtId.add(vo.getCntrt_id());						
			}
		}else{
			listCntrtId.add(vo.getCntrt_id());
		}
		
		String [] arrTmpCntrtId = new String[listCntrtId.size()];
		
		for(int k=0; k < listCntrtId.size(); k++){
			arrTmpCntrtId[k] = (String)listCntrtId.get(k);	
		}
		vo.setArr_cntrt_id(arrTmpCntrtId);
		
		returnValue = commonDAO.modify("clm.manage.modifyContractMaster.depthStatus", vo);		
		//의뢰테이블 상태 값 변경
		returnValue = commonDAO.modify("clm.manage.modifyContCnsdReq.prgrsStatus", vo);
		
		return returnValue;
		
	}
	
	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertConsideration(ConsultationVO vo) throws Exception {		
		/*********************************************************
		 * 파라미터세팅
		**********************************************************/
		ListOrderedMap returnLom = new ListOrderedMap();
		
		vo.setRegion_gbn("C01801");  	//국내 - master			
		vo.setSeal_mthd("C02102");		//날인_방법 C02101 날인
		
		//예정본 의뢰 재의뢰시 앞전 의뢰 히스토리의 검토부서 을 그대로 가져온다
		if("Y".equals(StringUtil.bvl(vo.getPlndbn_req_yn(),"N")) || !StringUtil.bvl(vo.getPrev_cnsdreq_id(),"").isEmpty()){
			vo.setMn_cnsd_dept(vo.getMn_cnsd_dept());
		}else{
			// 법무lite 버전에선 국내만 존재 하게 됨.
			vo.setMn_cnsd_dept("A00000001"); //국내
		}
		vo.setSession_user_locale(StringUtil.bvl(vo.getSession_user_locale(),"en"));
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		//표준계약서 사용하고 의뢰명에 "표준계약서" 단어가 없을 경우 의뢰명에 [표준계약서]를 자동으로 넣어준다.
		//메시지 처리 - 표준계약서
		if(!"".equals(StringUtil.bvl(vo.getStd_cnslt_no(),"")) && StringUtil.bvl(vo.getReq_title(),"").indexOf((String)messageSource.getMessage("clm.page.field.considerationImpl.insertConsideration01", null, locale1))== -1 ){
			//메시지 처리 - [표준계약서]
			vo.setReq_title((String)messageSource.getMessage("clm.page.field.considerationImpl.insertConsideration02", null, locale1)+StringUtil.convertHtmlTochars(vo.getReq_title()));
		}else{
			vo.setReq_title(StringUtil.convertHtmlTochars(vo.getReq_title()));
		}
		
		vo.setReq_up_dept(vo.getSession_up_level_dept_cd());	//의뢰_상위_부서			
		vo.setRe_demndday(StringUtil.removeChar(vo.getRe_demndday(), '-'));			// 회신요청일 		
		vo.setReq_dept(vo.getSession_dept_cd());
		vo.setReqman_nm(vo.getSession_user_nm());		
		vo.setReq_intnl_dept_cd(vo.getSession_in_dept_cd());		
		vo.setFst_req_dept(vo.getSession_dept_cd());
		vo.setFst_req_up_dept(vo.getSession_up_level_dept_cd());
		vo.setDraft_demnd_yn("N");  //의뢰 초안작성
        vo.setPlndbn_req_yn(StringUtil.nvl(vo.getPlndbn_req_yn(), "N")); 	//의뢰 예정본_의뢰_여부        
        vo.setLastbn_chge_yn(StringUtil.nvl(vo.getLastbn_chge_yn(), "N"));	//마지막본_변경_여부
		vo.setLast_cnsd_yn("Y"); 	//마지막_검토_여부
        vo.setRebfr_apprvl_yn("Y"); //회신전_결재_여부 
        vo.setPub_yn("Y");      	//공개여부          
        vo.setReq_operdiv(vo.getSession_blngt_orgnz());
        
		//master
        vo.setCntrtperiod_startday(StringUtil.bvl(vo.getCntrtperiod_startday(),""));
        vo.setCntrtperiod_startday(StringUtil.removeChar(vo.getCntrtperiod_startday(), '-'));
        vo.setCntrtperiod_endday(StringUtil.bvl(vo.getCntrtperiod_endday(),""));
        vo.setCntrtperiod_endday(StringUtil.removeChar(vo.getCntrtperiod_endday(),'-'));
		vo.setAuto_rnew_yn("N");//not null 없어질것
		vo.setCnclsn_plndday(DateUtil.getTime("yyyyMMdd"));  	//체결_예정일 수정 요망
		vo.setCntrt_respman_id(vo.getSession_user_id());  		//계약_담당자_ID 		
		vo.setCntrt_respman_nm(vo.getSession_user_nm());	//계약_담당자_명
		vo.setCntrt_resp_dept(vo.getSession_dept_cd());		//계약_담당_부서
		vo.setCntrt_resp_dept_nm(vo.getSession_dept_nm());	//계약_담당_부서_명
		vo.setCntrt_resp_up_dept(vo.getSession_up_level_dept_cd());		//계약_담당_상위_부서
		vo.setCntrt_respman_jikgup_nm(vo.getSession_jikgup_nm());	//계약_담당자_직급_명			
		vo.setSign_plndman_id(vo.getSession_user_id());			//서명_예정자_ID 수정 요망		
		vo.setReqman_jikgup_nm(vo.getSession_jikgup_nm()); // 2011-10-15 mod by 김현구
		vo.setCntrt_cnclsn_yn("Y"); 		//계약 체결 여부 
		vo.setCntrt_status("C02401");		//계약상태 미체결 master
		vo.setCntrt_chge_conf_yn("N");		//not null
		vo.setReg_operdiv(vo.getSession_blngt_orgnz());// 사업부 - 소속조직코드
		vo.setBfhdcstn_apbtday(StringUtil.bvl(vo.getBfhdcstn_apbtday(),""));
		vo.setBfhdcstn_apbtday(StringUtil.removeChar(vo.getBfhdcstn_apbtday(), '-'));	//사전품의_승인일
		//REG_DT
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setDel_yn("N");
		vo.setPlndbn_chge_cont(StringUtil.convertHtmlTochars(StringUtil.bvl((String)vo.getPlndbn_chge_cont(), "")));//예정본_변경_내용
		vo.setPrcs_depth("C02501");//프로세스 단계
		vo.setPrgrs_status("C04201"); //임시저장				
		vo.setDepth_status("C02601");//단계상태 작성중
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
		//권한요청 - 수정 해야 함 
		vo.setTrgtman_id(vo.getSession_user_id());
		vo.setRd_auth("C03601");
		vo.setAuth_startday(DateUtil.getTime("yyyyMMdd"));
		vo.setDemnd_status("C03701");  //
		vo.setEn_cntrt_nm(vo.getEn_cntrt_nm());
		vo.setCnsd_demnd_cont(vo.getBody_mime());
		
		String hq_req_yn = "";
        List<?> rePci = null;
        int update_result = 0;
		
		/*********************************************************** 
		 * 해당 의뢰건 정보 유무 조회 
		 ************************************************************/
		List<?> resultList = commonDAO.list("clm.manage.detailConsideration", vo); // 의뢰ID(CNSDREQ_ID)값으로 조회를 함.
		
		if (resultList.size() > 0) { //수정
			returnLom = insertContractMaster(vo);  // 계약 정보 저장 master key 유무에 따라 update or insert
			vo.setCnsdreq_id((String)returnLom.get("cnsdreq_id"));
			String prev_cnsdreq_id = "";   // tb_clm_cnsdreq 테이블에 앞의 key 값을 update하기 위해 필요한 값.
			
			// 위에서 만들어진 cnsdreq_id를 가지고 prev_cnsdreq_id 리턴을 해준다.
			List pci = commonDAO.list("clm.manage.setPrev_cnsdreq_id", vo);
			
			if(pci.size() > 0){
				ListOrderedMap lomRci = (ListOrderedMap)pci.get(0);
				prev_cnsdreq_id = StringUtil.bvl((String )lomRci.get("prev_cnsdreq_id"),"");
			}
			
			int rtVal = commonDAO.insert("clm.manage.modifyConsideration", vo);             // 수정이 되는 쿼리입니다.
			
			if("".equals(prev_cnsdreq_id)){    // prev_cnsdreq_id 값이 없을 경우 
				commonDAO.modify("clm.manage.modifyConsiderationAdd", vo);
			} else {
				commonDAO.modify("clm.manage.modifyConsiderationAdd_Prev_cnsdreq_id", vo);
				
                rePci = commonDAO.list("clm.manage.ListPrev_cnsdreq_id", vo);
				
				if(0 < rePci.size()){
					ListOrderedMap lomListRci = (ListOrderedMap)rePci.get(0);
					hq_req_yn = StringUtil.bvl((String)lomListRci.get("hq_req_yn"),"N");
					
					vo.setHq_req_yn(hq_req_yn);
					
					update_result = commonDAO.modify("las.contractmanager.updateHqRequest", vo);
					
				}
				
			}
		}else{                                                                  // 값이 없으므로 의뢰 Table에 inset하게 됨.
			returnLom = insertContractMaster(vo);                               // <---- 이건 왜 있는거지?
			vo.setCnsdreq_id((String)returnLom.get("cnsdreq_id"));
			
            String prev_cnsdreq_id = "";
			
            // 위에서 만들어진 cnsdreq_id를 가지고 prev_cnsdreq_id 리턴을 해준다.
 			List pci = commonDAO.list("clm.manage.setPrev_cnsdreq_id", vo);
 			
 			if(pci.size() > 0){
 				ListOrderedMap lomRci = (ListOrderedMap)pci.get(0);
 				prev_cnsdreq_id = StringUtil.bvl((String)lomRci.get("prev_cnsdreq_id"),"");
 			}
			
 			int rtVal = commonDAO.insert("clm.manage.insertConsideration", vo);	            // insert가 되는 쿼리 입니다. 김재원
			if("".equals(prev_cnsdreq_id)){    // prev_cnsdreq_id 값이 없을 경우 
				commonDAO.modify("clm.manage.modifyConsiderationAdd", vo);
			} else {
				commonDAO.modify("clm.manage.modifyConsiderationAdd_Prev_cnsdreq_id", vo);
				
                vo.setPrev_cnsdreq_id(prev_cnsdreq_id);
				
				rePci = commonDAO.list("clm.manage.ListPrev_cnsdreq_id", vo);
				
				if(0 < rePci.size()){
					ListOrderedMap lomListRci = (ListOrderedMap)rePci.get(0);
					hq_req_yn = StringUtil.bvl((String)lomListRci.get("hq_req_yn"),"N");
					
					vo.setHq_req_yn(hq_req_yn);
					
					update_result = commonDAO.modify("las.contractmanager.updateHqRequest", vo);
					
				}
			}
		}	
		
		if(!"".equals(StringUtil.bvl(vo.getPrev_cnsdreq_id(),""))){			
			commonDAO.delete("clm.manage.deleteCnsdreqRespMan", vo);
			List listRm = commonDAO.list("clm.manage.selectCnsdreqRespMan", vo);
			
			for(int j=0; j<listRm.size(); j++){
				ListOrderedMap lom = (ListOrderedMap)listRm.get(j);
				vo.setCnsdreq_id(vo.getCnsdreq_id());
				vo.setResp_dept((String)lom.get("resp_dept"));
				vo.setRespman_id((String)lom.get("respman_id"));				
				vo.setRespman_nm((String)lom.get("respman_nm"));
				vo.setRespman_jikgup_nm((String)lom.get("respman_jikgup_nm"));		         
		        vo.setMod_id(vo.getSession_user_id());
		        vo.setMod_nm(vo.getSession_user_nm());
		        vo.setMain_cnsd_yn((String)lom.get("main_cnsd_yn"));
		        vo.setAuto_apbt_yn((String)lom.get("auto_apbt_yn"));
				commonDAO.insert("clm.manage.insertCnsdreqRespMan", vo);
			}
		}
		
		/*********************************************************** 
		 * 해당 의뢰건 정보 유무 조회 
		 ************************************************************/
		//연관계약의 cntrt_id를 저장할때 가지고 오는 키로 업데이트 처리 함.
		
		String sRel_yn = StringUtil.bvl((String)vo.getRel_yn(),"");
		if("Y".equals(sRel_yn)){
			commonDAO.insert("clm.manage.updateRelKey", vo);
		}
		
		return returnLom;
	}
	
	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertConsiderationReg(ConsultationVO vo) throws Exception {
		
		int cont_cnsdreq         = 0;     // 계약별 검토 의뢰
		/*********************************************************
		 * 파라미터세팅
		**********************************************************/
		ListOrderedMap returnLom = new ListOrderedMap();
		
		
		// Lite 버전에선 국내만 존재합니다. 나머지 주석 처리 김재원 주석 --추가 서명만 가능
		//if("H".equals(vo.getDmstfrgn_gbn())){        //국내 - master
			vo.setRegion_gbn("C01801");
			vo.setSeal_mthd("C02102");		         //날인_방법 C02101 인감날인
//		}else if("F".equals(vo.getDmstfrgn_gbn())){  //해외
//			vo.setRegion_gbn("C01802");	     			
//			vo.setSeal_mthd("C02102");		         //날인_방법 C02102 서병날인
//		}
		
		//if("forwardLast".equals(vo.getStatus()) || "forwardReq".equals(vo.getStatus())){//예정본 의뢰 재의뢰시 앞전 의뢰스이 검토부서 을 그대로 가져온다
		if("Y".equals(vo.getPlndbn_req_yn()) || !vo.getPrev_cnsdreq_id().isEmpty()){
			vo.setMn_cnsd_dept(vo.getMn_cnsd_dept());
		}else{
			//계약이 여러건인경우 임시저장된 계약건중 맨처음 저장된 정보로 계약 유형을 설정해서 정/부 코드 설정
			//if(Integer.parseInt(vo.getTab_cnt()) > 1){
			//	ListOrderedMap lom = setReqCnsdDept(vo);
			//	vo.setCnclsnpurps_bigclsfcn((String)lom.get("cnclsnpurps_bigclsfcn"));
			//	vo.setRegion_gbn((String)lom.get("region_gbn"));
			//}
			
			
			//계약 이 한건인경우 입력받은 계약 유형으로 정/부 코드 설정
			//정검토부서			
			if("T0302".equals(vo.getCnclsnpurps_bigclsfcn())){	//특허
				vo.setMn_cnsd_dept("A00000003"); //IP센터				
				
				// 2012.08.17 특허유형 부부서 삭제 modified by hanjihoon
				vo.setVc_cnsd_dept("");
				/*
				if("C01801".equals(vo.getRegion_gbn())){	//국내
					vo.setVc_cnsd_dept("A00000001");
				}else if("C01802".equals(vo.getRegion_gbn())){	//해외
					vo.setVc_cnsd_dept("A00000002");
				}
				*/
			}else if("T0301".equals(vo.getCnclsnpurps_bigclsfcn()) || "T0303".equals(vo.getCnclsnpurps_bigclsfcn())){	//개발,라이선스 	
								
				if("C01801".equals(vo.getRegion_gbn())){
					vo.setMn_cnsd_dept("A00000001"); //국내
				}else{
					vo.setMn_cnsd_dept("A00000002"); //해외법무
				}						
				vo.setVc_cnsd_dept("A00000003");	//IP센터
				
			}else{	//기타
				if("C01801".equals(vo.getRegion_gbn())){
					vo.setMn_cnsd_dept("A00000001"); //국내
				}else{
					vo.setMn_cnsd_dept("A00000002"); //해외법무
				}				
				vo.setVc_cnsd_dept("");	//IP센터							
			}			
		}
		//req
		vo.setReq_title(StringUtil.convertCharsToHtml(vo.getReq_title()));
		vo.setReq_up_dept(vo.getSession_up_level_dept_cd());	//의뢰_상위_부서			
		vo.setRe_demndday(StringUtil.removeChar(vo.getRe_demndday(), '-'));			// 회신요청일 		
		vo.setReq_dept(vo.getSession_dept_cd());
		vo.setReqman_nm(vo.getSession_user_nm());		
		vo.setReq_intnl_dept_cd(vo.getSession_in_dept_cd());		
		vo.setFst_req_dept(vo.getSession_dept_cd());
		vo.setFst_req_up_dept(vo.getSession_up_level_dept_cd());
		
		
		vo.setDraft_demnd_yn("N");  //의뢰 초안작성
		
		vo.setLast_cnsd_yn("Y"); 	//마지막_검토_여부
        vo.setRebfr_apprvl_yn("Y"); //회신전_결재_여부 
        vo.setPub_yn("Y");      	//공개여부 
        
        vo.setReg_id(vo.getSession_user_id());
        vo.setReg_nm(vo.getSession_user_nm()) ;              
        vo.setDel_yn("N");
        vo.setReq_operdiv(vo.getSession_blngt_orgnz());
        
		//master
		vo.setCntrtperiod_startday(StringUtil.removeChar(vo.getCntrtperiod_startday(), '-'));
		vo.setCntrtperiod_endday(StringUtil.removeChar(vo.getCntrtperiod_endday(),'-'));
		vo.setAuto_rnew_yn("Y");//not null 없어질것
		vo.setCnclsn_plndday(DateUtil.getTime("yyyyMMdd"));  	//체결_예정일 수정 요망
		
		//master
		vo.setCntrt_respman_id(vo.getSession_user_id());  		//계약_담당자_ID 		
		vo.setCntrt_respman_nm(vo.getSession_user_nm());	//계약_담당자_명
		vo.setCntrt_resp_dept(vo.getSession_dept_cd());		//계약_담당_부서
		vo.setCntrt_resp_dept_nm(vo.getSession_dept_nm());	//계약_담당_부서_명
		vo.setCntrt_resp_up_dept(vo.getSession_up_level_dept_cd());		//계약_담당_상위_부서
		vo.setCntrt_respman_jikgup_nm(vo.getSession_jikgup_nm());	//계약_담당자_직급_명			
		vo.setSign_plndman_id(vo.getSession_user_id());			//서명_예정자_ID 수정 요망		
		vo.setReqman_jikgup_nm(vo.getSession_jikgup_nm()); // 2011-10-15 mod by 김현구
		
		
		vo.setCntrt_cnclsn_yn("N"); 		//계약 체결 여부 
		vo.setCntrt_status("C02401");		//계약상태 미체결 master
		vo.setCntrt_chge_conf_yn("N");		//not null
		vo.setReg_operdiv(vo.getSession_blngt_orgnz());// 사업부 - 소속조직코드
		
		vo.setBfhdcstn_apbtday(StringUtil.removeChar(vo.getBfhdcstn_apbtday(), '-'));	//사전품의_승인일
		
		//REG_DT
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setDel_yn("N");
		
		vo.setPlndbn_chge_cont(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getPlndbn_chge_cont(), "")));//예정본_변경_내용
		
		vo.setPrcs_depth("C02508");//프로세스 단계
		vo.setPrgrs_status("C04211"); //임시저장				
		vo.setDepth_status("C02631");//단계상태 작성중
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
		vo.setPshdbkgrnd_purps(StringUtil.convertCharsToHtml(vo.getPshdbkgrnd_purps()));
		
		//권한요청 - 수정 해야 함 
		vo.setTrgtman_id(vo.getSession_user_id());
		vo.setRd_auth("C03601");
		vo.setAuth_startday(DateUtil.getTime("yyyyMMdd"));
		vo.setDemnd_status("C03701");  //
		
		vo.setCnsd_demnd_cont(vo.getBody_mime());
		
		/*********************************************************** 
		 * 해당 의뢰건 정보 유무 조회 
		 ************************************************************/
		List resultList = commonDAO.list("clm.manage.detailConsiderationReg", vo); // 의뢰ID(CNSDREQ_ID)값으로 조회를 함.
		
		if (resultList.size() > 0) { //수정
			returnLom = insertContractMasterReg(vo);
			
			if(returnLom.size() > 0){
				cont_cnsdreq = commonDAO.insert("clm.manage.modifyConsideration", vo);             // TN_CLM_CONT_CNSDREQ 수정이 되는 쿼리입니다.
			}
			
			
		}else{                                                                  // 값이 없으므로 의뢰 Table에 inset하게 됨.

			returnLom = insertContractMasterReg(vo);                              // <---- 이건 왜 있는거지?
			
			if(returnLom.size() > 0){
				vo.setCnsdreq_id((String)returnLom.get("cnsdreq_id"));
				String prev_cnsdreq_id = "";
				prev_cnsdreq_id = (String )returnLom.get("prev_cnsdreq_id");
				cont_cnsdreq = commonDAO.insert("clm.manage.insertConsideration", vo);	  // TN_CLM_CONT_CNSDREQ insert가 되는 쿼리 입니다. 김재원.slas.20130403
				
				if("".equals(prev_cnsdreq_id)){    // prev_cnsdreq_id 값이 없을 경우 
					commonDAO.modify("clm.manage.modifyConsiderationAdd", vo);
				} else {
					commonDAO.modify("clm.manage.modifyConsiderationAdd_Prev_cnsdreq_id", vo);
				}
			}
			
			
		}
		
		//재의뢰시 예정본 의뢰시 최초 의뢰건의 배정자 정보 copy
		if(!"".equals(vo.getPrev_cnsdreq_id())){			

			commonDAO.delete("clm.manage.deleteCnsdreqRespMan", vo);
			List listRm = commonDAO.list("clm.manage.selectCnsdreqRespMan", vo);
			for(int j=0; j<listRm.size(); j++){
				ListOrderedMap lom = (ListOrderedMap)listRm.get(j);
				vo.setCnsdreq_id(vo.getCnsdreq_id());
				vo.setResp_dept((String)lom.get("resp_dept"));
				vo.setRespman_id((String)lom.get("respman_id"));				
				vo.setRespman_nm((String)lom.get("respman_nm"));
				vo.setRespman_jikgup_nm((String)lom.get("respman_jikgup_nm"));		         
		        vo.setMod_id(vo.getSession_user_id());
		        vo.setMod_nm(vo.getSession_user_nm());
		        vo.setMain_cnsd_yn((String)lom.get("main_cnsd_yn"));
		        vo.setAuto_apbt_yn((String)lom.get("auto_apbt_yn"));
				commonDAO.insert("clm.manage.insertCnsdreqRespMan", vo);
			}
		}

		
		/*********************************************************** 
		 * 이후 값들의 입력이 되어야 합니다.
		 * 테이블 목록
		 * 계약관리_계약유형_특화정보  = TN_CLM_CONT_TYPE_SPCLINFO
		 * 계약관리_계약별_부서검토 = TN_CLM_CONTRACT_DEPTCNSD  > DEFAULT
		 * 계약관리_이행정보 = TN_CLM_CONT_EXECINFO
		 * 계약관리_연관계약 = TN_CLM_RELATION_CONTRACT
		 * 계약관리_계약_검토의뢰 = TN_CLM_CONT_CNSDREQ         > DEFAULT (저장 되고 있음)
		 * 계약관리_계약권한요청(참조인) = TN_CLM_CONTRACT_AUTHREQ
		 * 계약관리_계약별_검토 = TN_CLM_CONTRACT_CNSD          > DEFAULT (저장 되고 있음)
		 * 계약관리_계약_마스터 = TN_CLM_CONTRACT_MASTER    (저장 되고 있음)
		 ************************************************************/
		
	    int iResult = 0;
	    int iResult2 = 0;
	    if(cont_cnsdreq > 0){  // TN_CLM_CONT_CNSDREQ insert, update가 된 후에 다음 실행
			
			iResult = commonDAO.insert("clm.manage.modifyRegMaster", vo);
			
			if(iResult > 0){
				iResult2 = commonDAO.delete("clm.manage.deleteConsultationExec", vo);  //이행정보 삭제
				
				//이행정보등록(TN_CLM_CONT_EXECINFO)-delete
				String[] exec_cont_arr = vo.getExec_cont_arr();
				String[] exec_plndday_arr = vo.getExec_plndday_arr();
				String[] exec_gbn_arr	= vo.getExec_gbn_arr();
				String[] exec_item_arr	= vo.getExec_itm_arr();
				String[] exec_amt_arr	= vo.getExec_amt_arr();
				
				
				if(exec_cont_arr != null) {
					for(int i=0; i < exec_cont_arr.length; i++){
					
						vo.setCntrt_id(vo.getCntrt_id());
						vo.setExec_status("C03102");
						vo.setExec_cont(exec_cont_arr[i]);
						vo.setExec_plndday(exec_plndday_arr[i].replace("-", ""));
						vo.setExec_gbn(exec_gbn_arr[i]);
						vo.setExec_itm(exec_item_arr[i]);
						vo.setExec_amt(new BigDecimal(StringUtil.ltrim(exec_amt_arr[i])));
						vo.setExec_cmpltday("");
						vo.setNote("");
					
						if(iResult2 > 0){
						}
					}
				}
			}
			
		}
		
			
		return returnLom;
	}
	
	/**
	 * 첨부 필수 사항 체크 
	 */
	public ListOrderedMap getFilevalidate(ConsultationVO vo) throws Exception {
		ListOrderedMap returnLom = new ListOrderedMap();
		
		List returnList = commonDAO.list("clm.manage.listFilevalidate", vo);
		if (returnList != null && returnList.size() > 0) {
			 returnLom = (ListOrderedMap)returnList.get(0);
			
			if(returnLom.get("FILE_VALIDATE").equals("N")){
				returnLom.put("fileValidate", "N");			
			}else{
				returnLom.put("fileValidate", "Y");
			}
		}else{
			returnLom.put("fileValidate", "Y");	
		}
		return returnLom;
	}
	
	/**
	 * 필수 입력 사항 검색
	 */
	public ListOrderedMap searchRequired(ConsultationVO vo) throws Exception {
		ListOrderedMap returnLom = new ListOrderedMap();    
		vo.setUser_id(vo.getSession_user_id()); 
		List returnList = commonDAO.list("clm.manage.searchRequired", vo);		
		
		if (returnList != null && returnList.size() > 0) {//RET_MSG  ret_msg
			 returnLom = (ListOrderedMap)returnList.get(0);			 
			 returnLom.put("ret_msg", StringUtil.replace((String)returnLom.get("ret_msg"), "|*|", "\n\r"));
		}		
		return returnLom;
	}
	
	public ListOrderedMap prevCnsdReqCopyConsideration(ConsultationVO vo) throws Exception {
		ListOrderedMap returnLom = new ListOrderedMap();
		
		//최종본 의뢰시 마지막 검토여부 필드  N 으로 업데이트
		vo.setLast_cnsd_yn("N");
		commonDAO.modify("clm.manage.prevContractMaster.lastCnsdYn", vo);
		List list = commonDAO.list("clm.manage.prevContractCnsdFinal", vo);  // 중복을 피하기 위해서 키값을 조회해 옴.
		
		if(list.size() > 0){  // 조회해온 키값을 세팅함.
			ListOrderedMap lom = (ListOrderedMap)list.get(0);
			vo.setCnsdreq_id((String)lom.get("cnsdreq_id"));   //V_NEW_CNSDREQ_ID
		}
		
		commonDAO.insert("clm.manage.prevContCnsdreqRespman", vo);
		commonDAO.insert("clm.manage.prevContCnsdreq", vo);
		commonDAO.insert("clm.manage.prevContractCnsd", vo);
		commonDAO.modify("clm.manage.prevContractMaster", vo);  //이전의뢰에 관계된 계약건 상태 변경 임시저장 상태로
		
		returnLom.put("cnsdreq_id", vo.getCnsdreq_id());
		
		return returnLom;
	}
	/**
	 * 재검토 의뢰 내용 저장 
	 */
	public ListOrderedMap modifyAgainReConsideration(ConsultationVO vo) throws Exception {		
		ListOrderedMap returnLom = new ListOrderedMap();
		//계약서, 첨부별첨, 기타 첨부파일 
		vo.setReg_operdiv(vo.getSession_blngt_orgnz());// 사업부 - 소속조직코드	
		vo.setRe_demndday(StringUtil.removeChar(vo.getRe_demndday(), '-'));			// 회신요청일
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());
		//#1 계약서 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120201");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos1());
		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//첨부 별첨 첨부파일 
		//#2 기타 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120208");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfosEtc());
		clmsFileService.mngClmsAttachFile(fvo);
		
		//#2 기타 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120205");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos2());
		clmsFileService.mngClmsAttachFile(fvo);
		
		vo.setCnsd_demnd_cont(vo.getBody_mime());
		
		if("save".equals(vo.getSubmit_status()) || "tab_save".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04201"); //임시저장				
			vo.setDepth_status("C02601");//단계상태 작성중
		}else if("again".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04204"); //의뢰->검토
			vo.setDepth_status("C02606");//단계상태 작성중
			
		}
		 		
		//재검토으뢰시에는 검토요청 내용만 의뢰 테이블에 저장 됨 
		int returnVal = commonDAO.modify("clm.manage.modifyAgainReqConsideration", vo);
		
		//계약 테이블 depth 상태값 변경 
		returnVal = commonDAO.modify("clm.manage.modifyContractMaster.depthStatus", vo);
		//의뢰 테이블 prgrs 상태값 변경
		//returnVal = commonDAO.modify("clm.manage.modifyContCnsdReq.prgrsStatus", vo);		
	
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		if(returnVal > -1){
			returnLom.put("cd", returnVal);
			returnLom.put("val", "SC");
			//메시지 처리 - 정상처리 되었습니다.
			returnLom.put("msg", (String)messageSource.getMessage("clm.page.field.consideration.modifyAgainReConsideration01", null, locale1));
		}else{
			returnLom.put("cd", returnVal);
			returnLom.put("val", "FA");
			//메시지 처리 - 정상적으로 처리 되지 않았습니다. 시스템 관리자에게 문의 바랍니다.
			returnLom.put("msg", (String)messageSource.getMessage("clm.page.field.consideration.modifyAgainReConsideration02", null, locale1));
		}
		
		return returnLom;		
	}	
	
	/**
	 * 파일 업로드 Test
	 */
	public int saveFileUpload(ConsultationVO vo) throws Exception {

		int resultVal = 0;
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();
		
		ClmsDataUtil.debugParamByRequest(vo, true);
		
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());
		//#0
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos());		
		clmsFileService.mngClmsAttachFile(fvo);
		//#1
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos1());
		clmsFileService.mngClmsAttachFile(fvo);
		//#2
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos2());
		clmsFileService.mngClmsAttachFile(fvo);
		//#3
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos3());
		clmsFileService.mngClmsAttachFile(fvo);
		//#4
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos4());
		clmsFileService.mngClmsAttachFile(fvo);
		
		return resultVal;
	}
	/**
	 * 의뢰건 보류  의뢰건별 SEQ 가져오기 
	 * @param cntrt_id
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getHoldSeqno(ConsultationVO vo) throws Exception {
		BigDecimal seqNo = new BigDecimal(-1);
		List resultList = null;
		
		resultList = commonDAO.list("clm.manage.deferContCnsdreq.seqno",vo); 
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			seqNo = (BigDecimal)lom.get("SEQNO");			
		} 		
		return seqNo;
	}
	
	/**
	 * 의뢰건보류/의뢰건drop/의뢰건삭제/계약건삭제/계약건dorp/보류 해제 / 의뢰작성취소
	 */
	public ListOrderedMap deleteDropDefer(ConsultationVO vo) throws Exception {
		
		int returnVal = -1;
		String returnStr = "N";
		String dropDiv = "N";			// DROP여부
		String holdOffDiv = "N";		// 보류여부
		String adreplyDiv = "N";		// admin reply 여부(구주 추가)
		List resultList  = null;		// DROP여부 리스트
		List resultList2 = null;		// 보류여부 리스트
		ListOrderedMap returnMap 	= new ListOrderedMap();
		ConsultationVO hisMasterVo	= null;
		ConsultationVO hisReqVo		= null;
		
		if("deferRequest".equals(vo.getSubmit_status())){
			
			
			 //의뢰건보류			
			vo.setHold_seqno(getHoldSeqno(vo).intValue());		
			vo.setHold_cause(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getChage_cause(), "")));	
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setDepth_status("C02607");
			
			returnVal = commonDAO.modify("clm.manage.modifyContractMaster.depthStatus", vo); //계약건보류
			
			if(returnVal > 0){
				if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
				hisMasterVo.setDepth_status(vo.getDepth_status());	
				hisMasterVo.setCntrt_id(vo.getMaster_cntrt_id());	
			}
			
			returnVal = commonDAO.modify("clm.manage.deferContCnsdreq.insert", vo);  // 의뢰 보류 건 insert
			
			
		//deferCancelRequest 보류 해제
		}else if("deferCancelRequest".equals(vo.getSubmit_status())){
			
			vo.setHold_seqno(vo.getHold_seqno());			
			vo.setMod_id(vo.getSession_user_id());
			vo.setMod_nm(vo.getSession_user_nm());
			
			ClmsDataUtil.debug(vo.getPrgrs_status());
			
			//의뢰의현재 상태에 따라서 분기 의뢰중?회신완료?		
			if("C04207".equals(vo.getPrgrs_status())){	//회신완료
				vo.setDepth_status("C02608");	//단계상태
			}else{
				vo.setDepth_status("C02606");	//단계상태 master 검토중 으로 변경 
				returnVal = commonDAO.modify("clm.manage.deferContCnsdReq.update",vo);
				if(returnVal > 0){
					if(null == hisReqVo) hisReqVo =new ConsultationVO();
					hisReqVo.setCnsdreq_id(vo.getCnsdreq_id());
					hisReqVo.setReq_dt(DateUtil.todayNnow());
				}
			}			
			
			returnVal = commonDAO.modify("clm.manage.modifyContractMaster.depthStatus", vo); //계약건보류 해제 - 보류 이전 상태로 롤백
			if(returnVal > 0){
				if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
				hisMasterVo.setDepth_status(vo.getDepth_status());	
				hisMasterVo.setCntrt_id(vo.getMaster_cntrt_id());	
			}
			returnVal = commonDAO.modify("clm.manage.deferContCnsdReqHold.update", vo);
			
		//dropRequest
		}else if("dropRequest".equals(vo.getSubmit_status())){
			
			//모든 계약건 drop 
			vo.setCntrt_status("C02406");											//계약_상태  drop C02406	
			vo.setDepth_status("C02603");											//C02603 Drop
			
			for(int j=0; j<vo.getArr_cntrt_id().length; j++){
				
				String arrTmpId[] = vo.getArr_cntrt_id();				
				
				vo.setCntrt_id((String)arrTmpId[j]);
				vo.setCntrt_chge_demndday(DateUtil.today());						//계약_변경_요청일     
				vo.setCntrt_chge_demndman_id(vo.getSession_user_id());				//계약_변경_요청자_ID
				vo.setCntrt_chge_demndman_nm(vo.getSession_user_nm());				//계약_변경_요청자_명
				vo.setCntrt_chge_demnd_dept_nm(vo.getSession_dept_cd());			//계약_변경_요청_부서_명
				vo.setCntrt_chge_demndman_jikgup_nm(vo.getSession_jikgup_nm());		//계약_변경_요청자_직급_명
				vo.setCntrt_chge_demnd_cause(StringUtil.convertCharsToHtml(StringUtil.bvl(vo.getChage_cause(), "")));//계약_변경_요청_사유
				vo.setCntrt_chge_plndday(DateUtil.today());								
				returnVal = commonDAO.modify("clm.manage.dropContract.update", vo); //계약건	
				if(returnVal > 0){
					if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
					hisMasterVo.setCntrt_id(vo.getCntrt_id());	
					hisMasterVo.setCntrt_status(vo.getCntrt_status());
					hisMasterVo.setDepth_status(vo.getDepth_status());	
					hisMasterVo.setCntrt_chge_demndday(vo.getCntrt_chge_demndday());	
					hisMasterVo.setCntrt_chge_demndman_id(vo.getCntrt_chge_demndman_id());	
					hisMasterVo.setCntrt_chge_demndman_nm(vo.getCntrt_chge_demndman_nm());	
					hisMasterVo.setCntrt_chge_demnd_dept_nm(vo.getCntrt_chge_demnd_dept_nm());	
					hisMasterVo.setCntrt_chge_demndman_jikgup_nm(vo.getCntrt_chge_demndman_jikgup_nm());	
					hisMasterVo.setCntrt_chge_demnd_cause(vo.getCntrt_chge_demnd_cause());	
					hisMasterVo.setCntrt_chge_plndday(vo.getCntrt_chge_plndday());	
				}
			}
			 //의뢰건 drop
			vo.setPrgrs_status("C04208");									//PRGRS_STATUS //C04208 Drop
			returnVal = commonDAO.modify("clm.manage.dropReqeust.update", vo);		//의뢰건
			if(returnVal > 0){
				if(null == hisReqVo) hisReqVo =new ConsultationVO();
				hisReqVo.setCnsdreq_id(vo.getCnsdreq_id());
				hisReqVo.setPrgrs_status(vo.getPrgrs_status());
			}
			
		}else if("deleteRequest".equals(vo.getSubmit_status())){			//의뢰건 삭제 하기 
			
			vo.setDel_yn("Y");
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			
			//모든 계약건 삭제 
			for(int j=0; j<vo.getArr_cntrt_id().length; j++){				
				String[] arrTmpId = vo.getArr_cntrt_id();				
				vo.setCntrt_id((String)arrTmpId[j]);
				returnVal = commonDAO.modify("clm.manage.deleteContractMaster", vo);
				if(returnVal > 0){
					if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
					hisMasterVo.setCntrt_id(vo.getCntrt_id());	
					hisMasterVo.setDel_yn(vo.getDel_yn());
					hisMasterVo.setDel_id(vo.getDel_id());	
					hisMasterVo.setDel_nm(vo.getDel_nm());
				}
			}
			//의뢰건 삭제
			returnVal = commonDAO.modify("clm.manage.deleteContCnsdreq", vo);
			if(returnVal > 0){
				if(null == hisReqVo) hisReqVo =new ConsultationVO();
				hisReqVo.setCnsdreq_id(vo.getCnsdreq_id());
				hisReqVo.setDel_yn(vo.getDel_yn());
				hisReqVo.setDel_id(vo.getDel_id());	
				hisReqVo.setDel_nm(vo.getDel_nm());
			}
			
		}else if("deleteContract".equals(vo.getSubmit_status())){			//계약건 삭제 하기 
			//계약건 삭제
			vo.setDel_yn("Y");
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			
			returnVal = commonDAO.modify("clm.manage.deleteContractMaster", vo);
			if(returnVal > 0){
				if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
				hisMasterVo.setCntrt_id(vo.getCntrt_id());	
				hisMasterVo.setDel_yn(vo.getDel_yn());
				hisMasterVo.setDel_id(vo.getDel_id());	
				hisMasterVo.setDel_nm(vo.getDel_nm());
			}
			if(returnVal == 0) returnVal = 1;
			
		}else if("dropContract".equals(vo.getSubmit_status())){				//계약건 드랍 하기 
			//계약건 drop
			vo.setCntrt_status("C02406");									//계약_상태  drop C02406	
			vo.setDepth_status("C02603");									//C02603 Drop
			vo.setCntrt_chge_demndday(DateUtil.today());						//계약_변경_요청일     
			vo.setCntrt_chge_demndman_id(vo.getSession_user_id());				//계약_변경_요청자_ID
			vo.setCntrt_chge_demndman_nm(vo.getSession_user_nm());				//계약_변경_요청자_명
			vo.setCntrt_chge_demnd_dept_nm(vo.getSession_dept_cd());			//계약_변경_요청_부서_명
			vo.setCntrt_chge_demndman_jikgup_nm(vo.getSession_jikgup_nm());		//계약_변경_요청자_직급_명
			vo.setCntrt_chge_demnd_cause(StringUtil.convertCharsToHtml(StringUtil.bvl(vo.getChage_cause(), "")));					//계약_변경_요청_사유
			
			returnVal = commonDAO.modify("clm.manage.dropContract.update", vo); //계약건 
			if(returnVal > 0){
				if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
				hisMasterVo.setCntrt_id(vo.getCntrt_id());	
				hisMasterVo.setCntrt_status(vo.getCntrt_status());
				hisMasterVo.setDepth_status(vo.getDepth_status());	
				hisMasterVo.setCntrt_chge_demndday(vo.getCntrt_chge_demndday());	
				hisMasterVo.setCntrt_chge_demndman_id(vo.getCntrt_chge_demndman_id());	
				hisMasterVo.setCntrt_chge_demndman_nm(vo.getCntrt_chge_demndman_nm());	
				hisMasterVo.setCntrt_chge_demnd_dept_nm(vo.getCntrt_chge_demnd_dept_nm());	
				hisMasterVo.setCntrt_chge_demndman_jikgup_nm(vo.getCntrt_chge_demndman_jikgup_nm());	
				hisMasterVo.setCntrt_chge_demnd_cause(vo.getCntrt_chge_demnd_cause());	
				hisMasterVo.setCntrt_chge_plndday(vo.getCntrt_chge_plndday());	
			}
			// 2012.02.16 의뢰에 포함된 모든 계약이 Drop된 경우 진행상태값을 변경 added by hanjihoon
			resultList = commonDAO.list("clm.manage.getDropDiv",vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				dropDiv = (String)lom.get("DROP_DIV");
			}
			
			if(dropDiv.equals("Y")){
				vo.setPrgrs_status("C04208");
				returnVal = commonDAO.modify("clm.manage.dropReqeust.update", vo);		// 진행상태 수정	
				if(returnVal > 0){
					if(null == hisReqVo) hisReqVo =new ConsultationVO();
					hisReqVo.setCnsdreq_id(vo.getCnsdreq_id());
					hisReqVo.setPrgrs_status(vo.getPrgrs_status());
				}
			}
		}else if("saveCancelRequest".equals(vo.getSubmit_status())){	//의뢰작성 취소 
			
			//해당의로건 && 검토 테이블 삭제 vo.getCnsdreq_id
			returnVal = commonDAO.delete("clm.manage.saveCancelContCnsdreq",vo);		//검토의뢰삭제
			returnVal = commonDAO.delete("clm.manage.saveCancelContractCnsd",vo);		//검토 삭제
			returnVal = commonDAO.delete("clm.manage.saveCancelCnsdReqRespMan",vo);	//배정자 삭제

			// 2012.05.24 단계상태를 [보류, 회신완료]로 복원 modified by hanjihoon
			vo.setPrev_cnsdreq_id(StringUtil.bvl(vo.getPrev_cnsdreq_id(),""));					// 이전검토의뢰ID
			resultList2 = commonDAO.list("clm.manage.getHoldOffDiv",vo);
			
			if (resultList2 != null && resultList2.size() > 0) {
				ListOrderedMap lom2 = (ListOrderedMap)resultList2.get(0);
				holdOffDiv = (String)lom2.get("HOLDOFF_DIV");
				adreplyDiv = (String)lom2.get("ADMRETURN_CNT");
			}
			
			if(holdOffDiv.equals("Y")){
				vo.setDepth_status("C02607");		// 보류
			}else{
				if(adreplyDiv.equals("Y")){
					vo.setDepth_status("C02609");		// admin reply Status  
				}else{
					vo.setDepth_status("C02608");		// 회신완료
				}
			}
			
			returnVal = commonDAO.modify("clm.manage.modifyContractMaster.depthStatus",vo);
			if(returnVal > 0){
				if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
				hisMasterVo.setDepth_status(vo.getDepth_status());	
				hisMasterVo.setCntrt_id(vo.getMaster_cntrt_id());	
			}
			//이전의뢰건 vo.getPrevCnsdreq_id  ??  마지막 검토의뢰 여부 필드 Y 로 업데이트
			vo.setLast_cnsd_yn("Y");
			returnVal = commonDAO.modify("clm.manage.prevContractMaster.lastCnsdYn",vo);
			if(returnVal > 0){
				if(null == hisMasterVo ) hisMasterVo = new ConsultationVO();
				hisMasterVo.setLast_cnsd_yn(vo.getLast_cnsd_yn());
				hisMasterVo.setCnsdreq_id(vo.getPrev_cnsdreq_id());
				hisMasterVo.setSession_comp_cd(vo.getSession_comp_cd());
				hisMasterVo.setSession_comp_nm(vo.getSession_comp_nm());
				hisMasterVo.setSession_user_id(vo.getSession_user_id());
				hisMasterVo.setSession_user_nm(vo.getSession_user_nm());
			}
			
		}
		
		if(returnVal > -1){ returnStr = "Y";}
		
		returnMap.put("cd", returnStr);
		returnMap.put("msg", "");
		
		return returnMap;
	}
	
	public ListOrderedMap setReqCnsdDept(ConsultationVO vo) throws Exception {			
		List list = null;
		ListOrderedMap lom = new ListOrderedMap();
		
		list =   commonDAO.list("clm.manage.setReqCnsdDept", vo);
		lom = (ListOrderedMap)list.get(0);
		
		return lom;
	}


	
	public List detailDropDefer(ConsultationVO vo) throws Exception {
		
		List resultList = null;
		
		if("deferRequest".equals(vo.getSubmit_status())){ //의뢰 보류 내역
			resultList =  commonDAO.list("clm.manage.listCnsdreqHold", vo);
		}else if("dropContract".equals(vo.getSubmit_status())){//계약 보류내역
			resultList =   commonDAO.list("clm.manage.detailContractMaster", vo);
		}		
		return resultList;
	}
	
	// 계약 상세
	public List detailConsideration(ConsultationVO vo) throws Exception {
		// 2014-08-11 Kevin Commented.
		/*commonDAO.insert("clm.manage.insertConsiderationLog", vo);	//조회로그 
*/		return commonDAO.list("clm.manage.detailConsideration", vo);
	}
	
	// 계약 상세 - prcs_depth를 변수로 받아서 조회
	public List detailConsideration2(ConsultationVO vo) throws Exception {
		// 2014-08-11 Kevin Commented.
		/*commonDAO.insert("clm.manage.insertConsiderationLog", vo);	//조회로그 
*/		return commonDAO.list("clm.manage.detailConsideration2", vo);
	}
	
	// 체결 후 등록 상세
	public List detailConsiderationReg(ConsultationVO vo) throws Exception {
		// 2014-08-11 Kevin Commented.
		/*commonDAO.insert("clm.manage.insertConsiderationLog", vo);	//조회로그 
*/		return commonDAO.list("clm.manage.detailConsiderationReg", vo);
	}
	
	//계약마스터 아이디로 특화 속성정보 가져오기 
	public List listTypeSpclinfo(ConsultationVO vo) throws Exception {
		List resultList = null;
		
		resultList = commonDAO.list("clm.manage.listTypeSpclinfo", vo);
		
		return resultList;
	}
	//계약마스터 아이디로 특화 속성정보 가져오기 수정 가능할때 
	public List listTypeSpclinfoMod(ConsultationVO vo) throws Exception {

		List resultList = null;
		
		resultList = commonDAO.list("clm.manage.listTypeSpclinfoMod", vo);
		
		return resultList;
	}
	
	/**
	 * 연관계약 select
	 */
	public HashMap listRelationContract(ConsultationVO vo) throws Exception {
		
		StringBuffer resultSb = new StringBuffer();
		HashMap resultMap = new HashMap();
		
			
		List list = commonDAO.list("clm.manage.listRelationContract", vo);
		
		/*TN_CLM_RELATION_CONTRACT	계약_ID	CNTRT_ID
		TN_CLM_RELATION_CONTRACT	모_계약_ID	PARENT_CNTRT_ID
		TN_CLM_RELATION_CONTRACT	관련_유형	REL_TYPE
		TN_CLM_RELATION_CONTRACT	관련_정의	REL_DEFN
		TN_CLM_RELATION_CONTRACT	설명	EXPL
		TN_CLM_RELATION_CONTRACT	등록_일시	REG_DT
		TN_CLM_RELATION_CONTRACT	등록자_ID	REG_ID
		TN_CLM_RELATION_CONTRACT	등록자_명	REG_NM*/
		
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				resultSb.append("<tr id=\"trRelationContractCont\" alt=\"valid\" >" );
				resultSb.append("<input type='hidden' name='db_parent_cntrt_id' id='db_parent_cntrt_id' value='"+ (String)lom.get("parent_cntrt_id") +"' />" );
				resultSb.append(" <td> " + (String)lom.get("real_type_nm") + "</td>" );
				if("20141010414540080".equals((String)lom.get("parent_cntrt_id"))){
					resultSb.append(" <td> "+ StringUtil.convertHtmlTochars((String)lom.get("req_title"))+ "<BR>(" + StringUtil.convertHtmlTochars((String)lom.get("PARENT_CNTRT_NM"))  + ")</td>" );
				} else {
					//resultSb.append(" <td> <a href=\"javascript:goDetail('"+(String)lom.get("parent_cntrt_id")+"');\">"+ StringUtil.convertHtmlTochars((String)lom.get("req_title"))+ "</a><BR>(" + StringUtil.convertHtmlTochars((String)lom.get("PARENT_CNTRT_NM"))  + ")</td>" );
					resultSb.append(" <td> <a href=\"javascript:goDetail('"+(String)lom.get("CNSDREQ_ID")+"');\">"+ StringUtil.convertHtmlTochars((String)lom.get("req_title"))+ "</a><BR>(" + StringUtil.convertHtmlTochars((String)lom.get("PARENT_CNTRT_NM"))  + ")</td>" );
				}
				
				resultSb.append(" <td> " + (String)lom.get("rel_defn") + "</td>" );
				resultSb.append(" <td> " + StringUtil.convertHtmlTochars((String)lom.get("expl"))  );
				if(!"".equals(vo.getSubmit_status())){  //insert http://legal2.sec.samsung.net:8080/script/secfw/jquery/uploadify/cancel_old.gif
					resultSb.append(" <a href=\"javascript:actionRelationContract('delete','"+(String)lom.get("parent_cntrt_id")+"');\"><img src=\"/script/secfw/jquery/uploadify/cancel_new_en.gif\"></a></td>" );
				}else{
					resultSb.append("</td>");
				}
				resultSb.append("</tr>");
			}//end for			
		}else{
			//메시지 처리 - 등록된 연관계약이 없습니다.
			resultSb.append("<input type='hidden' name='db_parent_cntrt_id' id='db_parent_cntrt_id' value='' />" );
			resultSb.append("<tr id=\"trRelationContractCont\"><td colspan=\"4\">"+ (String)messageSource.getMessage("clm.page.field.consideration.listRelationContract01", null, locale1) + "</td></tr>" );
		}// end if(list !=  null)
		
		resultMap.put("cntRc", list.size());
		resultMap.put("contRc", resultSb.toString());
		return resultMap;
	}
	
	/**
	 * 연관계약 insert/delete
	 */
	public int actionRelationContract(ConsultationVO vo) throws Exception {
		int returnVal = -1;			
		
		if("insert".equals(vo.getSubmit_status())){
			HashMap map = listRelationContract(vo);
			
			if((Integer)map.get("cntRc") > 0){	//0 보다 크면 이미 입력된 데이타 있음
				returnVal = 2;
			}else{
				vo.setExpl(StringUtil.convertHtmlTochars(vo.getExpl())) ;
				returnVal = commonDAO.insert("clm.manage.insertRelationContract", vo);
			}
		}else if("delete".equals(vo.getSubmit_status())){
			returnVal = commonDAO.insert("clm.manage.deleteRelationContract", vo);
		}else{
			returnVal = -1;
		}
		
		return returnVal;
	}	
	
	/**
	 * 권한요청자 -관련자 select
	 * @return
	 * @throws Exception
	 */
	public List listContractAuthreq(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.listContractAuthreq", vo);
	}

	/**
	 * Contract Management - Reviewer List
	 * Sungwoo added 2014-12-08 for Reviewer List Search.
	 * @return
	 * @throws Exception
	 */
	public List listContractReviewer(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.listContractrReviewer", vo);
	}
	
	/**
	 * 의뢰건 보류정보
	 */
	public List listCnsdreqHold(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.listCnsdreqHold", vo);
	}
	/**
	 * 계약관리 특화 속성 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */	
	public String listSpecialAttr(HashMap map) throws Exception {
		
		StringBuffer resultSb = new StringBuffer();		

		String bigClsFcn = StringUtil.bvl((String)map.get("CNCLSNPURPS_BIGCLSFCN"), "");
		String midClsFcn = StringUtil.bvl((String)map.get("CNCLSNPURPS_MIDCLSFCN"), "");		

		HashMap iHm = new HashMap();
		iHm.put("cnclsnpurps_bigclsfcn", bigClsFcn);
		iHm.put("cnclsnpurps_midclsfcn", midClsFcn);
		
		//common.clmscom.listComCdByGrpCd
		List list = commonDAO.list("clm.manage.listClmSpecialAttr", iHm);
		if (list != null && list.size() > 0) {
			
			Integer cntCntr   = 0;
				
			for (int m = 0; m < list.size(); m++) {
				ListOrderedMap cntLom = (ListOrderedMap)list.get(m);
				//프로젝트/과제명, 적용대상제품명, 라이선스범위및조건(법무에서 입력하느걸루 바꿈)
				if((Integer)cntLom.get("attr_seqno") == 11 || (Integer)cntLom.get("attr_seqno") == 9 ){
					cntCntr += 1;
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				Integer attrSeqno  = (Integer)lom.get("attr_seqno");		
				String crtnDeth    = (String)lom.get("crtn_deth");	
				String attrNm	   = (String)lom.get("attr_nm");	
				String mndtryYn    = (String)lom.get("mndtry_yn");	//tn_clm_type_spclinfo 필수여부
				String dpMndtry = "";				
				
				//프로젝트/과제명, 적용대상제품명, 라이선스범위및조건 5 번빠짐
				if(attrSeqno == 11 || attrSeqno == 9 ){
				
					//필수여부 
					if(mndtryYn.equals("Y")){
						dpMndtry = "required alt='"+attrNm+"'";					
					}
					
					if(i%2 == 0){					
						resultSb.append("<tr><th><span>"+attrNm+"</span></th> \n");
							if(i == cntCntr-1){
								resultSb.append("<td valign='top' colspan='6'> \n");
							}else{
								resultSb.append("<td valign='top' colspan='2'> \n");
							}
						resultSb.append("<input type='hidden' name='arr_attr_seqno' id='arr_attr_seqno' value='"+attrSeqno+"'> \n");
						resultSb.append("<input type='hidden' name='arr_attr_cd' id='arr_attr_cd' value='"+crtnDeth+"'> \n");
						resultSb.append("<input type='text' name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' value='' class='text_full' style='margin-top:4px;' /></td> \n");					
					}else if(i%2 == 1){
						resultSb.append("<th><span>"+attrNm+"</span></th> \n");
						resultSb.append("<td valign='top' colspan='3'> \n");
						resultSb.append("<input type='hidden' name='arr_attr_seqno' id='arr_attr_seqno' value='"+attrSeqno+"'> \n");
						resultSb.append("<input type='hidden' name='arr_attr_cd' id='arr_attr_cd' value='"+crtnDeth+"'> \n");
						resultSb.append("<input type='text' name='arr_attr_cont' " + dpMndtry + " id='arr_attr_cont' value='' class='text_full' style='margin-top:4px;' /></td></tr> \n");					
					}
					
				}else{
					resultSb.append("<input type='hidden' name='arr_attr_seqno' id='arr_attr_seqno' value='"+attrSeqno+"'> \n");
					resultSb.append("<input type='hidden' name='arr_attr_cd' id='arr_attr_cd' value='"+crtnDeth+"'> \n");
					resultSb.append("<input type='hidden' name='arr_attr_cont' id='arr_attr_cont' value='' class='text_full' style='margin-top:4px;' /></td> \n");					
				}

			}
		}
				
		return resultSb.toString();
	 }	
	
	//결재관련시작
	/**
	 * 의뢰정보조회 
	 * @param  vo ConsiderationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsiderationApprovalRequest(ConsultationVO vo) throws Exception {
		
		return commonDAO.list("clm.manage.detailConsiderationApprovalRequest", vo);
	}
	
	/**
	 * 계약마스터정보 
	 * @param  vo ConsiderationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsiderationApprovalContract(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsiderationApprovalContract", vo);
	}
	/**
	 * 체결정보등록 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	//public int modifyConsultation(ConsultationVO vo, ConsultationExecVO execVo, ConsultationSpecialVO specialVo) throws Exception {
	public int modifyConsiderationStatus(ConsultationVO vo) throws Exception {
		//체결정보입력(TN_CLM_CONTRACT_MASTER)-Update
		
		int result = 0;
		result = commonDAO.modify("clm.manage.modifyConsiderationReqStatus", vo);
		List contractList = this.listConsiderationApprovalContract(vo);
		ListOrderedMap lom	= null;
		
		if(contractList != null && contractList.size() > 0) {   
			
			for(int i=0; i < contractList.size(); i++) {
				lom = (ListOrderedMap)contractList.get(i);
				vo.setCntrt_id((String)lom.get("cntrt_id"));
				result = commonDAO.modify("clm.manage.modifyConsiderationCntrtStatus", vo);
			}	
		}
		 	
		return result;
	}
	
	/**
	 * 검토의뢰로 상태값 변경
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyConsiderationApprove(ConsultationVO vo) throws Exception {	
		vo.setCnsdreq_id(vo.getCnsdreq_id());
		
		//2012-09-18 김형준 - 
		//개발/라이센스 유형에서 국내거래선인데 주관부서를 임의로 해외에 셋팅할 경우 이메소드에서는 region_gbn이 jsp화면의 region_gbn으로 인식하여
		//여전히 국내로 진행된다. 국내/해외 구분에 따라 region_gbn을 변경시켜 준다.
		// Lite 버전에선 국내만 존재합니다. 나머지 주석 처리 김재원 주석
		//if("H".equals(vo.getDmstfrgn_gbn())){
			vo.setRegion_gbn("C01801");	//국내 - master			
//		}else if("F".equals(vo.getDmstfrgn_gbn())){
//			vo.setRegion_gbn("C01802");	//해외				
//		}
			
	    int result  = 0;
	    int result2  = 0;
	    
	    result = commonDAO.modify("clm.manage.modifyConsiderationApprove", vo);

		return result;
	}
	
	/**
	 * 체결품의 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsiderationApprovalAttachInfo(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsiderationAttachFileInfo", vo);
	}
	
	/**
	 * 이력정보 전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public List listHisExecution(ExecutionVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();

		resultList.add(commonDAO.list("clm.manage.listReqContractReview", vo));  // 검토이력 정보
		resultList.add(commonDAO.list("clm.manage.listContractApprove", vo));    // 승인정보
		resultList.add(commonDAO.list("clm.manage.listContractInfo", vo));       //사전검토정보
		resultList.add(commonDAO.list("clm.manage.detailConclusionContractMaster", vo));
		resultList.add(commonDAO.list("clm.manage.listContractAuthreq", vo));//관련자정보
		
		return resultList;
	}
	
	/**
	 * 의뢰 중복방지 구분값 조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List preventDuplication(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.preventDuplication", vo);
	}
	/**
	 * 최종본검토 가능여부 체크
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public String verifyFinalConsideration(ConsultationVO vo) throws Exception {
		String getId = "";
		List resultList = null;
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(),"")));		// 검토의뢰ID
		
		resultList = commonDAO.list("clm.manage.verifyFinalConsideration", vo); 
		
		if (resultList != null && resultList.size() > 0) {
			return "OK";			
		}else{
			return "FAIL";
		}
	}
	/**
	 * 검토의뢰 복사
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertCopyCnsd(ConsultationVO vo) throws Exception {
		
		String sVal;
		String sResult = "" ;
		vo.setRe_demndday(StringUtil.removeChar(vo.getRe_demndday(), '-')); //회신_요청일
		List resultList = null;
		
		// 1. 검토의뢰건 복사등록
		resultList = commonDAO.list("clm.manage.insertCopyCnsd", vo); 
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			sVal = (String)lom.get("o_msg");
			if(sVal != null && sVal.equals("0")){
				 sResult = "OK";
			}
		}
		 
		return sResult ;
	}
	
	/**
	 * 표준계약서 반려처리
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int rejctOpnn(ConsultationVO vo) throws Exception {
		int returnValue = -1;
		
		String reqTitle = StringUtil.bvl(vo.getReq_title(),"");
		/*************************************************
		 * 나모 웹 에디터 처리 - 사안개요MAIN_MATR_CONT, 부서검토-검토의견6
		 *************************************************/	
		 vo.setMain_matr_cont(""); 
		/*************************************************
		 * 나모 웹 에디터 처리 - 부서검토-검토의견6
		 *************************************************/	
		 vo.setCnsd_memo("");
		/*************************************************
		 * 나모 웹 에디터 처리 - 계약별_검토 - 최종검토의견8
		 *************************************************/
		 vo.setCnsd_opnn(StringUtil.convertHtmlTochars(vo.getRejct_opnn())); //Cross-site Scripting 방지 처리
		
		/*************************************************
		 * 파일첨부하기 -  
		 *************************************************/
		//Cross-site Scripting 방지
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(),"")));
		vo.setApbt_opnn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApbt_opnn(),"")));
		vo.setCnsd_dept(vo.getSession_blngt_orgnz());																	//검토부서
		vo.setReq_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getReq_title(),"")));							//의뢰제목

		vo.setLoac(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac(),"")));										//준거법
		vo.setLoac_etc(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoac_etc(),"")));								//준거법 기타
		vo.setDspt_resolt_mthd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd(),"")));				//분쟁해결방법
		vo.setDspt_resolt_mthd_det(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDspt_resolt_mthd_det(),"")));		//분쟁해결방법상세
		vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMaster_cntrt_id(),"")));				//계약ID
		vo.setActive_cntrt_id(vo.getMaster_cntrt_id());																	//활성화계약ID
		vo.setAuto_rnew_yn("N");//not null 없어질것
		
		vo.setDeptcnsd_cnsd_dept(vo.getSession_blngt_orgnz());
		vo.setDeptcnsd_cnsdman_id(vo.getSession_user_id());		
		vo.setDeptcnsd_cnsdman_nm(vo.getSession_user_nm());
		//등록자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());

		//계약관리_계약별_검토 진행상태 변경
		vo.setChg_prgrs_status("C04207");	//회신완료
		//계약관리_계약_마스터 단계상태 변경
		vo.setChg_depth_status("C02608");	//회신완료
		vo.setCnsd_status("C04305");
		
		String master_cntrt_ids[] = vo.getArr_cntrt_id();
		
		returnValue = commonDAO.modify("clm.returnContractMaster.modify",vo);
		
		// -2012.02.01 다수계약시 검토하지 않는 계약건을 TN_CLM_CONTRACT_DEPTCNSD 테이블에 INSERT modified by hanjihoon
		for(int i=0; i<master_cntrt_ids.length; i++) {	
			vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i],"")));
			vo.setCntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i],"")));
			
			returnValue = commonDAO.insert("clm.contractmanager.approvalOpnnDeptCnsdinsert",vo);
		}
		
		//활성화 계약 데이터 수정
		// -2012.02.02 계약ID vo객체 재설정  modified by hanjihoon
		vo.setCntrt_id(vo.getActive_cntrt_id());
		vo.setCnsdman_id(vo.getSession_user_id());		//검토자	
		vo.setCnsdman_nm(vo.getSession_user_nm());		//검토자
		returnValue = commonDAO.modify("clm.returnContractCnsd.modify", vo);
		
		returnValue = commonDAO.modify("clm.contractmanager.approvalOpnnCnsd", vo);
		//의뢰테이블 상태 변경
		returnValue = commonDAO.modify("clm.contractmanager.changeCnsdreqStatus", vo);

		//계약테이블 상태 변경 
		for(int i=0; i<master_cntrt_ids.length; i++) {
			if(!master_cntrt_ids[i].equals("")){
				vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(master_cntrt_ids[i],"")));
				returnValue = commonDAO.modify("clm.contractmanager.changeMasterStatus",vo);
			}
		}
		
		vo.setDeptcnsd_apbtman_id(vo.getSession_user_id());
		vo.setDeptcnsd_apbtman_nm(vo.getSession_user_nm());
		vo.setDeptcnsd_apbt_opnn(vo.getApbt_opnn());
		vo.setDeptcnsd_cnsd_status("C04305");
		
		returnValue = commonDAO.modify("clm.returnDeptCnsdApbt.modifyAll",vo);
			
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
	
		HashMap<String, String> hm = new HashMap<String, String>();		
		hm.put("cnsdreq_id"		, vo.getCnsdreq_id());
		hm.put("cntrt_id"		, vo.getCntrt_id());
		hm.put("receiver_id"	, vo.getReqman_id());
		hm.put("main_title"		, messageSource.getMessage("clm.page.field.consideration.rejctOpnn01", null, locale1).toString());
		hm.put("mail_content"	, messageSource.getMessage("clm.page.field.consideration.rejctOpnn02", null, locale1).toString());
		hm.put("req_title"		, reqTitle);
		hm.put("cntrt_nm"		, vo.getCntrt_nm());
		hm.put("respman_info"	, vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm());
		hm.put("app_day"		, vo.getReq_dt());
		hm.put("opnn"			, vo.getRejct_opnn());//반려사유
		hm.put("mail_type"		, "24");	

		mailSendService.sendMailAfterApproval(hm);
		
		return returnValue;
	}
	
	/**
	 * 표준계약서 승인 하기 
	 */
	public ListOrderedMap ApprovalStdContr(ConsultationVO vo) throws Exception {
		int returnVal = -1;
		String returnStr = "N";
		ListOrderedMap returnMap = new ListOrderedMap();
		
		//표준계약서 승인 하기 
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(),"")));
		vo.setMaster_cntrt_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getMaster_cntrt_id(),"")));	
		vo.setMod_id(vo.getSession_user_id());
		vo.setMod_nm(vo.getSession_user_nm());
		
		//계약관리_계약_마스터 단계상태 변경 (체결품의)
		vo.setChg_cntrt_status("C02401");   //계약상태        (미체결)
		vo.setChg_prcs_depth("C02502");     //프로세스 단계 (계약체결)
		vo.setChg_depth_status("C02631");	//회신완료        (확인중)
		
		//계약관리_계약별_검토 진행상태 변경
		vo.setChg_prgrs_status("C04211");	//진행상태       (확인중)
		
		returnVal = commonDAO.modify("clm.contractmanager.ApprchangeMasterStatus", vo);
		returnVal = commonDAO.modify("clm.contractmanager.ApprchangeCnsdreqStatus",vo);
		
		/*************************************************
		 * 계약서파일명 UPDATE(_FINAL)
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();
		
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());		
		//#1 계약서 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120201");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos1());
		fvo.setFinalVersion("Y");
		
		clmsFileService.modifyStdCntrtFile(fvo);
		
		if(returnVal == 0) returnVal = 1;
			
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		if(returnVal > -1){ returnStr = "Y";}
		
		returnMap.put("cd", returnStr);
		returnMap.put("msg", "");
	
		HashMap<String, String> hm = new HashMap<String, String>();	
		hm.put("cnsdreq_id"		, vo.getCnsdreq_id());
		hm.put("cntrt_id"		, vo.getCntrt_id());
		hm.put("receiver_id"	, vo.getReqman_id());
		hm.put("main_title"		, messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1).toString());
		hm.put("mail_content"	, messageSource.getMessage("clm.page.field.consideration.ApprovalStdContr03", null, locale1).toString());
		hm.put("req_title"		, vo.getReq_title());
		hm.put("cntrt_nm"		, vo.getCntrt_nm());
		hm.put("respman_info"	, vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm());
		hm.put("app_day"		, vo.getReq_dt());
		hm.put("mail_type"		, "24");
		
		mailSendService.sendMailAfterApproval(hm);
		
		// 검토자에게 메일알림 JOON 12/03/2014
		// 검토자 조회
		List<?> reviewerList = commonDAO.list("clm.manage.selectReviewers", vo);
		
		// 검토자 메일발송 JOON 12/03/2014
		for (int i=0; i < reviewerList.size(); i++) {
		 	Map map = new HashMap();
		 	map = (Map) reviewerList.get(i);
		 	if ( map.get("email") != null ) {
				hm.put("receiver_id", (String) map.get("email"));				
				mailSendService.sendMailAfterApproval(hm);
		 	}
		}
		
		// Email to Legal Admins. JOON 12/03/2014
		List legalAdmins = commonDAO.list("las.lawconsult.listLegalAdmin", vo);			
		for (int i=0; i < legalAdmins.size(); i++) {
			hm.put("receiver_id", (String) ((Map) legalAdmins.get(i)).get("email"));						
			mailSendService.sendMailAfterApproval(hm);
		}		
		
		return returnMap;
	}
	
	/**
	 * 인쇄용 검토정보
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map getConsiderationInfoPrint(ConsiderationVO vo) throws Exception {
		List list = commonDAO.list("clm.manage.print.considerationDetail", vo) ;
		Map map = null ;
		if(list!=null && list.size()>0) {
			map = (Map)list.get(0) ;
		}
		
		return map ;
	}
	
	/**
	 * 인쇄용 관련자정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String getReqAuthPrint(ConsiderationVO vo) throws Exception {
		List list = commonDAO.list("clm.manage.print.reqAuthList", vo) ;
		StringBuffer sb = new StringBuffer() ;
		
		if(list!=null) {
			for(int i=0; i<list.size(); i++) {
				Map map = (Map)list.get(i) ;
				if(i>0) {
					sb.append(", ") ;
				}
				
				sb.append(map.get("trgtman_nm") + " / ") ; // 관련자명
				sb.append(map.get("trgtman_dept_nm") + " / ") ; // 관련자 부서명
				sb.append(map.get("trgtman_jikgup_nm")) ; // 관려자 직급명
			}
		}
		
		return sb.toString();
		
	}

	/**
	 * 인쇄용 의뢰 첨부파일 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getReqAttachList(ConsiderationVO vo) throws Exception{
		return commonDAO.list("clm.manage.print.reqAttatchList", vo) ;
	}
	
	/**
	 * 인쇄용 회신 첨부파일 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getReplyAttachList(ConsiderationVO vo) throws Exception{
		return commonDAO.list("clm.manage.print.replyAttatchList", vo) ;
	}
	
	/**
	 * 인쇄용 기타 첨부파일 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getEtcAttachList(ConsiderationVO vo) throws Exception{
		return commonDAO.list("clm.manage.print.etcAttatchList", vo) ;
	}
	
	/**
	 * 인쇄용 특화정보 가져오기
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getSpclList(ConsiderationVO vo) throws Exception {
		
		return commonDAO.list("clm.manage.print.spclList", vo);
	}
	
	/**
	 * 인쇄용 이력 목록 가져오기
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getHisList(ExecutionVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();
		
		resultList.add(commonDAO.list("clm.manage.listReqContractReview", vo));
		resultList.add(commonDAO.list("clm.manage.print.approveList", vo));
		resultList.add(commonDAO.list("clm.manage.listContractSign", vo));
		//사전검토정보
		resultList.add(commonDAO.list("clm.manage.listContractInfo", vo));
		//보류정보
		resultList.add(commonDAO.list("clm.manage.listConsultationHold", vo));
		// 2012.05.24 보류이력을 검토이력에서 출력 modified by hanjihoon
		//보류이력
		//resultList.add(commonDAO.list("clm.manage.listDeferHis", vo));
		return resultList;
	}
	
	/**
	 * 인쇄용 결재라인 가져오기
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getApprLineList(ConsiderationVO vo) throws Exception {
		return commonDAO.list("clm.manage.print.apprLineList", vo);
	}
	
	/**
	 * 인쇄용 승인자(결재자)정보
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map getContractApproveMan(ConsiderationVO vo) throws Exception {
		List list = commonDAO.list("clm.manage.listContractApproveMan", vo) ;
		Map map = null ;
		if(list!=null && list.size()>0) {
			map = (Map)list.get(0) ;
		} else {
			map = new HashMap() ;
		}
		
		return map ;
	}
	
	/**
	 * 인쇄용 계약자동연장 이력
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getAutoRnewList(ConsiderationVO vo) throws Exception {
		vo.setGubun("'R','A','J'");
		return commonDAO.list("clm.manage.listOrgMngHistory", vo) ;
	}
	
	/**
	 * 리뷰어 휴가 여부
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List checkVacation(ConsiderationVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.manage.checkVacation", vo) ;
	}
	
	/**
	 * 계약의 GERP 정보 조회(SP version 적용됨)
	 * 2014-03-27 Kevin Added.
	 * @param GERPInfoVO vo
	 * @return List collection
	 * @throws Exception
	 */
	public List getGERPInformationDetail(GERPInfoVO vo) throws Exception{
		return commonDAO.list("clm.manage.gerpInformationdetail2", vo);
	}

	/**
	 * 정의된 코드 타입별로 GERP 관련 코드 리스트를 리턴한다.
	 * 2014-04-08 Kevin added.
	 *  @param GERPInfoVO vo
	 *  @return List collection
	 *  @throws Exception
	 */
	public List getGERPCodeListByType(GERPInfoVO vo) throws Exception{
		return commonDAO.list("clm.manage.getgerpcodelist", vo);
	}
	
	
	/**
	 * 2014-07-24 Kevin.
	 * Please refer to the comments at below.
	 * @see com.sec.clm.manage.service.ManageService#getStepStatusInformationWithCNSDREQID(com.sec.clm.manage.dto.ManageVO)
	 */
	public Map getStepStatusInformationWithCNSDREQID(ConsiderationVO vo)throws Exception {
		List list =  commonDAO.list("clm.manage.getStep_StatusInfo", vo);
		if(list == null || list.size() == 0) return null;
		
		ListOrderedMap lom = (ListOrderedMap)list.get(0);
		return lom;
	}
	
	/**
	 * 2014-08-12 Kevin.
	 * This is to get data to be used into basic information section.
	 */
	public List getDataForBasicInformationSection(ConsiderationVO vo) throws Exception {
		return commonDAO.list("clm.manage.getBasicInformationData", vo);
	}

	/**
	 * 2014-09-01 Sungwoo.
	 * This is to get data to be used into Contract History information section.
	 */
	public List listCompletionAttachInfo(ExecutionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listCompletionAttachInfo", vo);
	}
	
	
}