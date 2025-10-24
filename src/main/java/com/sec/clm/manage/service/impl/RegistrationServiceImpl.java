package com.sec.clm.manage.service.impl;

import java.io.File;
import java.io.Serializable;
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
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationExecVO;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.clm.manage.dto.RegistrationVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.manage.service.RegistrationService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.util.ClmsDataUtil;

/**
 * Description	: 계약관리 검토의뢰 Service impl(concrete class)
 * Author 		: 유영섭
 * Date			: 2011. 08. 04
 */
public class RegistrationServiceImpl extends CommonServiceImpl implements RegistrationService {
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	/**
	 * 검토의뢰 아이디 설정 - 사용 안함
	 */
	public String getId() throws Exception {
		String getId = "";
		List resultList = null;
		
		resultList = commonDAO.list("clm.manage.getId"); 
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			getId = (String)lom.get("GET_ID");			
		} 		
		return getId;
	}
	/**
	 * 검토의뢰테이블 아이디 포맷 새로 설정 
	 * @data 2011.10.20
	 * @author HyunjinCha    사용을 하는지 안하는지 개발 시 확인 필요 만약 필요 없다면 삭제 할 것 김재원 20130903
	 * @param vo
	 * @return "국내해외구분(1) + YY + MM + DD + SEQ(3)"
	 * @throws Exception
	 */
	public String getNewReqId(String str_dmstfrgn_gbn) throws Exception {
		String getNewReqId = "";
		List resultList = null;
		
		ClmsDataUtil.debug(">>>>>str_dmstfrgn_gbn>>>>>>>>>>>>>>>>"+str_dmstfrgn_gbn);
		
		HashMap iHm = new HashMap();
		iHm.put("str_dmstfrgn_gbn", str_dmstfrgn_gbn);
		
		resultList = commonDAO.list("clm.manage.getNewReqId", iHm);  // 해당 쿼리가 사용되지 않고 있는거 같아서 일단 삭제 함.
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			getNewReqId = (String)lom.get("NEWREQ_ID");			
		} 		
		return getNewReqId.trim();
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
	
	public String getReqStatus(String prev_cnsdreq_id,String plndbn_req_yn, ConsultationVO vo) throws Exception{
		
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		if("".equals(prev_cnsdreq_id) && "N".equals(plndbn_req_yn)){//최초의뢰
			//메시지 처리 - 검토
			return (String)messageSource.getMessage("clm.page.field.registration.getReqStatus01", null, locale1);
		}else if(!"".equals(prev_cnsdreq_id) && "N".equals(plndbn_req_yn)){//재의뢰
			//메시지 처리 - 재검토
			return (String)messageSource.getMessage("clm.page.field.registration.getReqStatus02", null, locale1);
		}else if(!"".equals(prev_cnsdreq_id) && "Y".equals(plndbn_req_yn)){//최종본의뢰
			//메시지 처리 - 최종본
			return (String)messageSource.getMessage("clm.page.field.registration.getReqStatus03", null, locale1);
		}else{
			return "Error";
		}
	}
	
	/** 
	 * 연관계약 입력시 계약 리스트 
	 */
	public List popupListContract(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.popupListContract",vo);
		
	}

	/**
	 * 계약건 상세정보  view 화면에서 사용
	 */
	public List detailContractMaster(ConsultationVO vo) throws Exception {
		ClmsDataUtil.debug("tabConsMaster   >>>>>>>>>>  ");
		return commonDAO.list("clm.manage.detailContractMaster", vo);
	}
	
	/**
	 * 계약건 수정시 상세정보  master 테이블 데이타만 가져옴
	 */
	public List detailForwardContractMaster(ConsultationVO vo) throws Exception {
		ClmsDataUtil.debug("tabConsMaster   >>>>>>>>>>  ");
		return commonDAO.list("clm.manage.detailForwardContractMaster", vo);
	}
	
	/**
	 * 계약 탭클릭시 정보 저장 master key 유무에 따라 update or insert
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertContractMaster(ConsultationVO vo) throws Exception {
		
		ClmsDataUtil.debug("ConsiderationServiceImpl >> insertContractMaster " + vo.getCntrt_id());
		List resultList = null;
		
		ListOrderedMap returnLom = new ListOrderedMap();
		
		/*********************************************************
		 * 파라미터세팅
		**********************************************************/
		/* C002
		 * F - 해외
		 * H - 국내
		 * A - 전략
		 */
		//계약상대방에 따라서 입력됨  - 수정 요망 		
		if("C01801".equals(vo.getRegion_gbn())){
			vo.setRegion_gbn("C01801");	//국내 - master	REGION_GBN		
			vo.setSeal_mthd("C02101");		//날인_방법 C02101 날인
		}else if("C01802".equals(vo.getRegion_gbn())){
			vo.setRegion_gbn("C01802");	//해외				
			vo.setSeal_mthd("C02102");		//날인_방법 C02101 날인
		}
		
		if("save".equals(vo.getSubmit_status()) || "tab_save".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04201"); //임시저장				
			vo.setDepth_status("C02601");//단계상태 작성중
			
		}else if("req".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04201"); //의뢰
			vo.setDepth_status("C02601");//단계상태 - 검토중 
			
		}else if("again".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04204"); //의뢰->검토
			vo.setDepth_status("C02606");//단계상태 작성중
			
		}else if("last".equals(vo.getSubmit_status())){		
			vo.setPrcs_depth("C02501");//프로세스 단계				
			vo.setPrgrs_status("C04204"); //의뢰->검토
			vo.setDepth_status("C02606");//단계상태 - 검토중
		}
		
		//의뢰 아이디가 없으면 설정 한다.
		if("".equals(vo.getCnsdreq_id())  || vo.getCnsdreq_id() == null){
			vo.setCnsdreq_id(getNewReqId((String)vo.getDmstfrgn_gbn()).trim());
		}
		
		if("C01801".equals(vo.getRegion_gbn())){
			vo.setSeal_mthd("C02101");		//날인_방법 C02101 날인
		}else{
			vo.setSeal_mthd("C02102");		//날인_방법 C02102 서명
		}
		
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
		
		//REG_DT
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setDel_yn("N");
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
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
		clmsFileService.mngClmsAttachFile(fvo);		
		
		//#2 계약서 기타첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120205");
		fvo.setRef_key(vo.getCntrt_id()+"@"+vo.getCnsdreq_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos2());
		clmsFileService.mngClmsAttachFile(fvo);
		
		//#3 단가첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120211");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos3());
		clmsFileService.mngClmsAttachFile(fvo);
		
		//#4사전검토정보첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01201");
		fvo.setFile_smlclsfcn("F0120101");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos4());
		clmsFileService.mngClmsAttachFile(fvo);
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/		
		
		if(null != vo.getBody_mime1()  && !"".equals(vo.getBody_mime1())){
		String decodeText = vo.getBody_mime1();		
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
		
		if (hm.get("TYPE").equals("M")) {
			ArrayList fileList = (ArrayList)hm.get("FILE_INFO");
			
			for (int i = 0; i < fileList.size(); i++) {
				HashMap fileMap = (HashMap)fileList.get(i);
				
				Integer seq = new Integer(i);
				String fileNm = (String)fileMap.get("FILE_NM");
				String filePath = (String)fileMap.get("FILE_PTH");
				String fileUrl = (String)fileMap.get("FILE_URL");
				
				File f = new File(filePath);
				Long fileSize = new Long(f.length());
			}			
			vo.setPshdbkgrnd_purps((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setPshdbkgrnd_purps((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}
		}
		
		/*******************************************************************
		 *  계약 마스테테이블 해당 계약건 유무 조회
		 ********************************************************************/
		resultList = commonDAO.list("clm.manage.getContractMaster", vo);		
		if (resultList.size() > 0) {  //master id O	
			
			//master update
			commonDAO.modify("clm.manage.modifyContractMaster", vo);
			//cnsd update
			commonDAO.modify("clm.manage.modifyContractCnsd",vo);
			
			if(!"save".equals(vo.getSubmit_status())){
				String master_cntrt_ids[] = vo.getArr_cntrt_id();
				for(int i=0; i<master_cntrt_ids.length; i++) {	
					vo.setMaster_cntrt_id((String)master_cntrt_ids[i]);
					commonDAO.modify("clm.manage.modifyContractMaster.depthStatus",vo);	//상태값 완료 검토의견없뎃 의뢰에 계약 여러건 상태 업데이트
				}
				
			}
			
			ClmsDataUtil.debug("ConsiderationServiceImpl >> clm.manage.deleteContractAuthreq");
			commonDAO.delete("clm.manage.deleteContractAuthreq", vo);		//권한요청
			
			ClmsDataUtil.debug("ConsiderationServiceImpl >> clm.manage.deleteContTypeSpclinfo");
			
			
			
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
			
			ClmsDataUtil.debug("ConsiderationServiceImpl >> insertContractMaster master id X");
			commonDAO.insert("clm.manage.insertContractMaster", vo);
			//계약관리_계약별_검토
			//vo.setCnsd_dept(vo.getMn_cnsd_dept());
			vo.setCnsdreq_id(vo.getCnsdreq_id());
			vo.setCnsd_dept(vo.getSession_dept_cd());  //임시설정 
			vo.setCnsd_status("C04301");
			commonDAO.insert("clm.manage.insertContractCnsd",vo);
		}
		
		//권한요청 - 관련자 리스트 저장		
		if(vo.getArr_trgtman_id() != null){			
			for(int j=0; j<vo.getArr_trgtman_id().length; j++){
				
				String[] arrTmpId = vo.getArr_trgtman_id();
				String[] arrTmpNm = vo.getArr_trgtman_nm();
				String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
				String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();
					
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
				commonDAO.insert("clm.manage.insertContractAuthreq", vo);
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
				
				commonDAO.insert("clm.manage.insertContTypeSpclinfo", vo);		//유형특화정보
			}
		}
	
		if (resultList.size() > 0) {  //master id O	
			returnLom.put("cd", "");
			returnLom.put("cnsdreq_id", vo.getCnsdreq_id());
			returnLom.put("cntrt_id", vo.getCntrt_id());
			returnLom.put("msg", "");
			returnLom.put("exe_status", "update");
		}else{
			returnLom.put("cd", "");
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
		return 1;
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
		
		if("H".equals(vo.getDmstfrgn_gbn())){
			vo.setRegion_gbn("C01801");	//국내 - master			
			vo.setSeal_mthd("C02101");		//날인_방법 C02101 날인
		}else if("F".equals(vo.getDmstfrgn_gbn())){
			vo.setRegion_gbn("C01802");	//해외				
			vo.setSeal_mthd("C02102");		//날인_방법 C02101 날인
		}
		
		if("Y".equals(vo.getPlndbn_req_yn()) || !vo.getPrev_cnsdreq_id().isEmpty()){
			vo.setMn_cnsd_dept(vo.getMn_cnsd_dept());
		}else{
			//계약 이 한건인경우 입력받은 계약 유형으로 정/부 코드 설정
			//정검토부서			
			if("T0302".equals(vo.getCnclsnpurps_bigclsfcn())){	//특허
				vo.setMn_cnsd_dept("A00000003"); //IP센터				
				// 2012.08.17 특허유형 부부서 삭제 modified by hanjihoon
				vo.setVc_cnsd_dept("");
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
		vo.setLast_cnsd_yn("N"); 	//마지막_검토_여부
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
		
		if("save".equals(vo.getSubmit_status()) || "tab_save".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04201"); //임시저장				
			vo.setDepth_status("C02601");//단계상태 작성중
			
		}else if("req".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04201"); //의뢰
			vo.setDepth_status("C02602");//단계상태 - 결재중 
			
		}else if("again".equals(vo.getSubmit_status())){
			vo.setPrcs_depth("C02501");//프로세스 단계
			vo.setPrgrs_status("C04204"); //의뢰
			vo.setDepth_status("C02606");//단계상태 - 검토중
			
		}else if("last".equals(vo.getSubmit_status())){
		
			vo.setPrcs_depth("C02501");//프로세스 단계				
			vo.setPrgrs_status("C04204"); //의뢰
			vo.setDepth_status("C02606");//단계상태 - 검토중
			vo.setPlndbn_chge_cont(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getPlndbn_chge_cont(), "")));//예정본_변경_내용
		}
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
		vo.setPshdbkgrnd_purps(StringUtil.convertCharsToHtml(vo.getPshdbkgrnd_purps()));
		//권한요청 - 수정 해야 함 
		vo.setTrgtman_id(vo.getSession_user_id());
		vo.setRd_auth("C03601");
		vo.setAuth_startday(DateUtil.getTime("yyyyMMdd"));
		vo.setDemnd_status("C03701");  //
			
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		
		if(null != vo.getBody_mime() && "" != vo.getBody_mime()){
			String decodeText = vo.getBody_mime();		
			HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);				
	
			if (hm.get("TYPE").equals("M")) {
				ArrayList fileList = (ArrayList)hm.get("FILE_INFO");			
				for (int i = 0; i < fileList.size(); i++) {
					HashMap fileMap = (HashMap)fileList.get(i);
					
					Integer seq = new Integer(i);
					String fileNm = (String)fileMap.get("FILE_NM");
					String filePath = (String)fileMap.get("FILE_PTH");
					String fileUrl = (String)fileMap.get("FILE_URL");
					
					File f = new File(filePath);
					Long fileSize = new Long(f.length());
				}			
				vo.setCnsd_demnd_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
			}else {
				vo.setCnsd_demnd_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
			}
		}
		
		/*********************************************************** 
		 * 해당 의뢰건 정보 유무 조회 
		 ************************************************************/
		List resultList = commonDAO.list("clm.manage.detailConsideration", vo);
		
		if (resultList.size() > 0) { //수정
			returnLom = insertContractMaster(vo);
			commonDAO.insert("clm.manage.modifyConsideration", vo);			
		}else{
			vo.setCnsdreq_id(getNewReqId((String)vo.getDmstfrgn_gbn()).trim());			
			commonDAO.insert("clm.manage.insertConsideration", vo);
			commonDAO.modify("clm.manage.modifyConsiderationAdd", vo);
			returnLom = insertContractMaster(vo);
		}	
		
		//재의뢰시 예정본 의뢰시 최초 의뢰건의 배정자 정보 copy
		if(!"".equals(vo.getPrev_cnsdreq_id())){			
			ClmsDataUtil.debug("vo.getPrev_cnsdreq_id() ===> "+vo.getPrev_cnsdreq_id());
			ClmsDataUtil.debug("vo.getCnsdreq_id() ===> "+vo.getCnsdreq_id());
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
		
		//returnLom.put("cd", "");
		//returnLom.put("cnsdreq_id", vo.getCnsdreq_id());
		//returnLom.put("cntrt_id", vo.getCntrt_id());
		//returnLom.put("msg", "");
		
		//returnLom.put("exe_status", "insert");
		
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
	
	public ListOrderedMap prevCnsdReqCopyConsideration(ConsultationVO vo) throws Exception {
		ListOrderedMap returnLom = new ListOrderedMap();
		
		//의뢰 아이디가 없으면 설정 한다.
		vo.setCnsdreq_id(getNewReqId((String)vo.getDmstfrgn_gbn()).trim());
		
		//최종본의뢰이면 이전 의뢰 최종본 여부 필드 N으로 업데이트 
		if("Y".equals(vo.getPlndbn_req_yn())){
			commonDAO.modify("clm.manage.prevContractMaster.plndbnReqYn", vo);  
		}		
		commonDAO.insert("clm.manage.prevContractCnsd", vo);
		commonDAO.insert("clm.manage.prevContCnsdreqRespman", vo);
		commonDAO.insert("clm.manage.prevContCnsdreq", vo);
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
		fvo.setFile_smlclsfcn("F0120205");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfosEtc());
		clmsFileService.mngClmsAttachFile(fvo);
		
		//#2 기타 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120205");
		fvo.setRef_key(vo.getCntrt_id());	//key 설정 ~
		fvo.setFileInfos(vo.getFileInfos2());
		clmsFileService.mngClmsAttachFile(fvo);
		
		/*************************************************
		 * 나모 웹 에디터 저장 검토요청 내용 저장 
		 *************************************************/
		if(null != vo.getBody_mime() && "" != vo.getBody_mime()){
			String decodeText = vo.getBody_mime();		
			HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);				

			if (hm.get("TYPE").equals("M")) {
				ArrayList fileList = (ArrayList)hm.get("FILE_INFO");			
				for (int i = 0; i < fileList.size(); i++) {
					HashMap fileMap = (HashMap)fileList.get(i);
					
					Integer seq = new Integer(i);
					String fileNm = (String)fileMap.get("FILE_NM");
					String filePath = (String)fileMap.get("FILE_PTH");
					String fileUrl = (String)fileMap.get("FILE_URL");
					
					File f = new File(filePath);
					Long fileSize = new Long(f.length());
				}			
				vo.setCnsd_demnd_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
			}else {
				vo.setCnsd_demnd_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
			}
		}
		
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
		
		returnLom.put("cd", returnVal);
		return returnLom;		
	}	
	
	/**
	 * 파일 업로드 Test
	 */
	public int saveFileUpload(ConsultationVO vo) throws Exception {
		ClmsDataUtil.debug("saveFileUpload  vo.getFileInfos() >>>   "+vo.getFileInfos());
		ClmsDataUtil.debug("saveFileUpload  vo.getFileInfos1() >>>   "+vo.getFileInfos1());
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
	public int getHoldSeqno(ConsultationVO vo) throws Exception {
		int seqNo = -1;
		List resultList = null;
		
		ClmsDataUtil.debug("==========cnsdreq_id==========>>" + vo.getCnsdreq_id());
		
		resultList = commonDAO.list("clm.manage.deferContCnsdreq.seqno",vo); 
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			seqNo = (Integer)lom.get("SEQNO");			
		} 		
		return seqNo;
	}
	
	/**
	 * 의뢰건보류/의뢰건drop/의뢰건삭제/계약건삭제/계약건dorp
	 */
	public ListOrderedMap deleteDropDefer(ConsultationVO vo) throws Exception {
		int returnVal = -1;
		String returnStr = "N";
		
		ListOrderedMap returnMap = new ListOrderedMap();
		
		ClmsDataUtil.debug("deleteDropDefer  >>>>>>>>>>>>" + vo.getSubmit_status());
		
		if("deferRequest".equals(vo.getSubmit_status())){
			 //의뢰건보류			
			vo.setHold_seqno(getHoldSeqno(vo));			
			vo.setHold_cause(StringUtil.convertCharsToHtml(StringUtil.bvl(vo.getChage_cause(), "")));			
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			//master		
			
			for(int j=0; j<vo.getArr_cntrt_id().length; j++){				
				String arrTmpId[] = vo.getArr_cntrt_id();	
				
				vo.setCntrt_id((String)arrTmpId[j]);
				vo.setDepth_status("C02607");										//C02607 보류				
				returnVal = commonDAO.modify("clm.manage.deferContract.update", vo); //계약건보류		
			}
			
			returnVal = commonDAO.modify("clm.manage.deferContCnsdreq.insert", vo);
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
			}
			 //의뢰건 drop
			vo.setPrgrs_status("C04208");									//PRGRS_STATUS //C04208 Drop
			returnVal = commonDAO.modify("clm.manage.dropReqeust.update", vo);		//의뢰건
			
		}else if("deleteRequest".equals(vo.getSubmit_status())){			//의뢰건 삭제 하기 
			
			vo.setDel_yn("Y");
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			
			//모든 계약건 삭제 
			for(int j=0; j<vo.getArr_cntrt_id().length; j++){				
				String[] arrTmpId = vo.getArr_cntrt_id();				
				vo.setCntrt_id((String)arrTmpId[j]);
				returnVal = commonDAO.modify("clm.manage.deleteContractMaster", vo);
			}
			//의뢰건 삭제
			returnVal = commonDAO.modify("clm.manage.deleteContCnsdreq", vo);
			
		}else if("deleteContract".equals(vo.getSubmit_status())){			//계약건 삭제 하기 
			//계약건 삭제
			vo.setDel_yn("Y");
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			
			returnVal = commonDAO.modify("clm.manage.deleteContractMaster", vo);
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
			
		}
		
		if(returnVal == 1){ returnStr = "Y";}
		
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
	
	public List detailConsideration(ConsultationVO vo) throws Exception {
		// 2014-08-11 Kevin Commented. 
		/*commonDAO.insert("clm.manage.insertConsiderationLog", vo);	//조회로그 */		
		return commonDAO.list("clm.manage.detailConsideration", vo);
	}
	//계약마스터 아이디로 특화 속성정보 가져오기 
	public List listTypeSpclinfo(ConsultationVO vo) throws Exception {
		ClmsDataUtil.debug("listTypeSpclinfo  >>>   "+vo.getCnsdreq_id());
		ClmsDataUtil.debug("listTypeSpclinfo  >>>   "+vo.getCntrt_id());
		List resultList = null;
		
		resultList = commonDAO.list("clm.manage.listTypeSpclinfo", vo);
		
		return resultList;
	}
	//계약마스터 아이디로 특화 속성정보 가져오기 수정 가능할때 
	public List listTypeSpclinfoMod(ConsultationVO vo) throws Exception {
		ClmsDataUtil.debug("listTypeSpclinfo  >>>   "+vo.getCnsdreq_id());
		ClmsDataUtil.debug("listTypeSpclinfo  >>>   "+vo.getCntrt_id());
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
				
				resultSb.append("<tr id=\"trRelationContractCont\" alt=\"valid\">" );
				resultSb.append(" <td> " + (String)lom.get("real_type_nm") + "</td>" );
				resultSb.append(" <td> <!--sam--><a href=\"javascript:goDetail('"+(String)lom.get("CNSDREQ_ID")+"');\">"+ StringUtil.convertHtmlTochars((String)lom.get("req_title"))+ "</a><BR>(" + StringUtil.convertHtmlTochars((String)lom.get("PARENT_CNTRT_NM"))  + ")</td>" );
				// resultSb.append(" <td> " + (String)lom.get("parent_cntrt_nm") + "</td>" );
				resultSb.append(" <td> " + (String)lom.get("rel_defn") + "</td>" );
				resultSb.append(" <td> " + (String)lom.get("expl")  );
				if(!"".equals(vo.getSubmit_status())){  //insert http://legal2.sec.samsung.net:8080/script/secfw/jquery/uploadify/cancel_old.gif
					resultSb.append(" <a href=\"javascript:actionRelationContract('delete','"+(String)lom.get("parent_cntrt_id")+"');\"><img src=\"/script/secfw/jquery/uploadify/cancel_new_en.gif\"></a>" );
				}else{
					resultSb.append("</td>");
				}
				resultSb.append("</tr>");
			}//end for			
		}else{
			//등록된 연관계약이 없습니다.
			resultSb.append("<tr id=\"trRelationContractCont\"><td  class=\"tC\" colspan=\"4\">"+(String)messageSource.getMessage("las.msg.succ.noResult", null, locale1)+" </td></tr>" );
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
		
		ClmsDataUtil.debug("actionRelationContract  >>>   "+vo.getCnsdreq_id());
		ClmsDataUtil.debug("actionRelationContract  >>>   "+vo.getCntrt_id());
				
		if("insert".equals(vo.getSubmit_status())){
			HashMap map = listRelationContract(vo);
			
			if((Integer)map.get("cntRc") > 0){	//0 보다 크면 이미 입력된 데이타 있음
				returnVal = 2;
			}else{				
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

		resultList.add(commonDAO.list("clm.manage.listReqContractReview", vo));
		resultList.add(commonDAO.list("clm.manage.listContractApprove", vo));
		resultList.add(commonDAO.list("clm.manage.listContractSign", vo));
		//사전검토정보
		resultList.add(commonDAO.list("clm.manage.listContractInfo", vo));
		//보류정보
		resultList.add(commonDAO.list("clm.manage.listConsultationHold", vo));
		return resultList;
	}
	
	/**
	 * 계약  전체목록을 조회한다.
	 * @param  vo RegistrationVO
	 * @return List
	 * @throws Exception
	 */
	public List listContract(RegistrationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listContract", vo);
	}
	
	/**
	 * 계약  상세정보를 조회한다.
	 * @param  vo RegistrationVO
	 * @return List
	 * @throws Exception
	 */
	public List detailManageCompletion(RegistrationVO vo) throws Exception {
		List resultList = new ArrayList();
		
		resultList.add(commonDAO.list("clm.manage.detailConclusionContractMaster", vo));//계약정보
		resultList.add(commonDAO.list("clm.manage.listConsultationRelation", vo));//연관계약정보
		resultList.add(commonDAO.list("clm.manage.listContractApproveMan", vo));//승인자(결재자)정보
		
		resultList.add(commonDAO.list("clm.manage.listContractReview", vo));
		resultList.add(commonDAO.list("clm.manage.listContractApprove", vo));
		
		resultList.add(commonDAO.list("clm.manage.listContractInfo", vo));//사전검토정보
		resultList.add(commonDAO.list("clm.manage.listExecution", vo));
		
		
		return resultList;
	}
	
	/**
	 * 체결후 등록 승인 및 반려
	 * @param  vo RegistrationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyContract(RegistrationVO vo) throws Exception {
		
		List<?> listAgree = commonDAO.list("clm.manage.AgreeMaxSeqno",vo);
		
		ListOrderedMap lom = null;
		int result = 0;
		
		if(listAgree != null && listAgree.size() > 0){
			lom = (ListOrderedMap) listAgree.get(0);
		}
		vo.setAgree_seqno(Integer.parseInt(String.valueOf(lom.get("max_seq"))));

		//TN_CLM_CNCLSN_AGREE에 이력 삽입
		result = commonDAO.modify("clm.manage.insertMyApprovalAgree", vo);

		//마스터 정보 수정 (계약상태 등)
		vo.setOrg_acptday(StringUtil.replace(vo.getOrg_acptday(),"-", ""));
		result = commonDAO.modify("clm.manage.modifyRegistrationApproval", vo);

		//cnsdreq 상태정보 수정
		result = commonDAO.modify("clm.manage.modifyRegistCnsdreqStatus", vo);
		
		//담당자에게 이메일 보내기
		//1.계약 id, 계약명
		//2.담당자id
		//3.메인_title
		//4.내용
		String mailContents = "";
		HashMap<String, Serializable> hm = new HashMap<String, Serializable>();		
		
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		//1.승인처리일 경우
		if("Y".equals(vo.getAgree_yn())){
			//메시지 처리 - 요청하신 체결후등록 요청 건이 승인되었습니다.<br>계약서 원본을 위 계약관리자에게 제출하시기 바랍니다.
			mailContents 	= (String)messageSource.getMessage("clm.page.field.registration.modifyContract02", null, locale1);
		}else{
			//메시지 처리 - 요청하신 체결후등록 건이 위와 같은 사유로 반려되었습니다.<br>이후 계약관리시스템 상 프로세스 진행과 관련하여서는 계약관리자에게 문의하시기 바랍니다.
			mailContents 	= (String)messageSource.getMessage("clm.page.field.registration.modifyContract04", null, locale1);
		}

		hm.put("mail_type"	, "24");	
		hm.put("opnn"		, vo.getReject_info());
		hm.put("cnsdreq_id"		, vo.getCnsdreq_id());
		hm.put("cntrt_id"		, vo.getCntrt_id());
		hm.put("req_title"		, vo.getReq_title());
		hm.put("cntrt_nm"		, vo.getCntrt_nm());
		hm.put("receiver_id"	, vo.getCntrt_respman_id());
		hm.put("respman_info"	, vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm());
		hm.put("main_title"		, (String)messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
		hm.put("mail_content"	, mailContents);
		hm.put("app_day"		, vo.getReq_dt());
		hm.put("locale", locale1);
		
		mailSendService.sendMailAfterApproval(hm);

		// 검토자에게 메일알림 JOON 12/03/2014
		// 검토자 조회
		List reviewerList = commonDAO.list("clm.manage.selectReviewers", vo);
		
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
		 	
		return result;
	}
	
	/**
	 * 체결후등록 입력
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertRegistration(ConsultationVO vo) throws Exception {
		
		/*********************************************************
		 * 각 상태의 결과 
		**********************************************************/
		int contract_master      = 0;     // 마스터 저장
		int contract_cnsd        = 0;     // 계약별 검토
		int cont_cnsdreq         = 0;     // 계약별 검토 의뢰
		int cont_type_spclinfo   = 0;     // 특화정보
		int cont_execinfo        = 0;     // 이행정보
		int relation_contract    = 0;     // 연관계약
		int contract_authreq     = 0;     // 계약관리_계약권한요청(참조인)
		int contract_deptcnsd    = 0;     // 계약서 부서 검토
		
		int contract_info        = 0;     // 체결 정보
		
		/*********************************************************
		 * 파라미터세팅
		**********************************************************/
		ListOrderedMap returnLom = new ListOrderedMap();

		//Plndbn_req_yn() : 예정본 의뢰여부
		//if("forwardLast".equals(vo.getStatus()) || "forwardReq".equals(vo.getStatus())){//예정본 의뢰 재의뢰시 앞전 의뢰스이 검토부서 을 그대로 가져온다
		if("Y".equals(vo.getPlndbn_req_yn()) || !vo.getPrev_cnsdreq_id().isEmpty()){
			vo.setMn_cnsd_dept(vo.getMn_cnsd_dept());	//정검토부서
			
		}else{
			//vo.setDmstfrgn_gbn("H");
			vo.setMn_cnsd_dept("A00000001"); //국내
			vo.setVc_cnsd_dept("");
		}
		//req
		vo.setReq_title(StringUtil.convertCharsToHtml(vo.getReq_title()));
		vo.setReq_up_dept(vo.getSession_up_level_dept_cd());						//의뢰_상위_부서			
		vo.setRe_demndday(StringUtil.replace(vo.getRe_demndday(),"-",""));			// 회신요청일 		
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
        vo.setReq_operdiv(vo.getSession_blngt_orgnz());//이건 뭘까나?
        vo.setReg_operdiv(vo.getSession_blngt_orgnz());

		//master
		vo.setCntrtperiod_startday(StringUtil.replace(vo.getCntrtperiod_startday(), "-",""));
		vo.setCntrtperiod_endday(vo.getCntrtperiod_endday().replace("-",""));
		vo.setAuto_rnew_yn("N");										//not null 없어질것
		vo.setCntrt_respman_id(vo.getSession_user_id());  				//계약_담당자_ID 		
		vo.setCntrt_respman_nm(vo.getSession_user_nm());				//계약_담당자_명
		vo.setCntrt_resp_dept(vo.getSession_dept_cd());					//계약_담당_부서
		vo.setCntrt_resp_dept_nm(vo.getSession_dept_nm());				//계약_담당_부서_명
		vo.setCntrt_resp_up_dept(vo.getSession_up_level_dept_cd());		//계약_담당_상위_부서
		vo.setCntrt_respman_jikgup_nm(vo.getSession_jikgup_nm());		//계약_담당자_직급_명			
		vo.setSign_plndman_id(vo.getSession_user_id());					//서명_예정자_ID 수정 요망		
		vo.setReqman_jikgup_nm(vo.getSession_jikgup_nm()); 				// 2011-10-15 mod by 김현구
		
		vo.setCntrt_cnclsn_yn("N"); 									//계약 체결 여부 
		vo.setCntrt_status("C02401");									//계약상태 미체결 master
		vo.setCntrt_chge_conf_yn("N");									//not null
		
		vo.setBfhdcstn_apbtday(StringUtil.replace(vo.getBfhdcstn_apbtday(), "-",""));	//사전품의_승인일
		
		vo.setPlndbn_chge_cont(StringUtil.convertCharsToHtml(StringUtil.bvl((String)vo.getPlndbn_chge_cont(), "")));//예정본_변경_내용
		
		vo.setPrcs_depth("C02502");										//프로세스 단계
		vo.setPrgrs_status("C04211"); 									//임시저장				
		vo.setDepth_status("C02631");									//단계상태 작성중
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.replace(StringUtil.bvl(vo.getCntrt_amt(), ""),",", ""));
		//vo.setPshdbkgrnd_purps(StringUtil.convertCharsToHtml(vo.getPshdbkgrnd_purps()));
		
		//권한요청 - 수정 해야 함 
		vo.setTrgtman_id(vo.getSession_user_id());
		vo.setRd_auth("C03601");
		vo.setAuth_startday(DateUtil.getTime("yyyyMMdd"));
		vo.setDemnd_status("C03701");  //
		
		// 추진목적 및 배경 (나모에서 텍스트에이리어로 변경  !@#$)
		vo.setPshdbkgrnd_purps(vo.getBody_mime1());
		
		
		
		/************************************************************
		 * 계약 마스터 테이블 정보 저장 
		 *************************************************************/
		//TAB 저장시  tn_clm_cont_cnsdreq 에 입력안하다.
		
		/*********************************************************** 
		 * 해당 의뢰건 정보 유무 조회 
		 ************************************************************/
		List resultList = commonDAO.list("clm.manage.detailRegistration", vo); // 의뢰ID(CNSDREQ_ID)값으로 조회를 함.
		
		if (resultList.size() > 0) { //수정
			returnLom = insertContractMasterReg(vo);
			
			if(returnLom.size() > 0){
				cont_cnsdreq = commonDAO.insert("clm.manage.modifyConsideration", vo);             // TN_CLM_CONT_CNSDREQ 수정이 되는 쿼리입니다.
				// History Table insert TH_CLM_CONT_CNSDREQ
				if(cont_cnsdreq > 0){
					try{
						commonDAO.insert("las.contractmanager.insertContCnsdreqHistory", vo);
					}catch(Exception e){}
				}
			}
			
			
		}else{                                                                  // 값이 없으므로 의뢰 Table에 inset하게 됨.
			//vo.setCnsdreq_id(getNewReqId((String)vo.getDmstfrgn_gbn()).trim());			
				
			returnLom = insertContractMasterReg(vo);                             
			
			if(returnLom.size() > 0){
				
				vo.setCnsdreq_id((String)returnLom.get("cnsdreq_id"));
				
				cont_cnsdreq = commonDAO.insert("clm.manage.insertConsideration", vo);	            // TN_CLM_CONT_CNSDREQ insert가 되는 쿼리 입니다. 김재원
				// History Table insert TH_CLM_CONT_CNSDREQ
	 			if(cont_cnsdreq > 0){
					try{
						commonDAO.insert("las.contractmanager.insertContCnsdreqHistory", vo);
					}catch(Exception e){}
				}
				
				commonDAO.modify("clm.manage.modifyConsiderationAdd", vo);				
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

		if(cont_cnsdreq > 0){  // TN_CLM_CONT_CNSDREQ insert, update가 된 후에 다음 실행
			
			commonDAO.delete("clm.manage.deleteConsultationExec", vo);  //이행정보 삭제
				
			//이행정보등록(TN_CLM_CONT_EXECINFO)-delete
			String[] exec_cont_arr = vo.getExec_cont_arr();
			String[] exec_plndday_arr = vo.getExec_plndday_arr();
			String[] exec_gbn_arr	= vo.getExec_gbn_arr();
			String[] exec_item_arr	= vo.getExec_itm_arr();
			String[] exec_amt_arr	= vo.getExec_amt_arr();
			//String[] exec_cmpltday_arr = execVo.getExec_cmpltday_arr();
			//String[] note_arr		  = execVo.getNote_arr();
			
			
			if(exec_cont_arr != null) {
				for(int i=0; i < exec_cont_arr.length; i++){
				
					vo.setCntrt_id(vo.getCntrt_id());
					vo.setExec_status("C03102");
					vo.setExec_cont(exec_cont_arr[i]);
					vo.setExec_plndday(StringUtil.replace(exec_plndday_arr[i],"-", ""));
					vo.setExec_gbn(exec_gbn_arr[i]);
					vo.setExec_itm(exec_item_arr[i]);
					vo.setExec_amt(new BigDecimal(StringUtil.ltrim(exec_amt_arr[i].replace(",",""))));
					vo.setExec_cmpltday("");
					vo.setNote("");
				
					commonDAO.insert("clm.manage.insertConsultationExec", vo); //이행정보 등록
				}
			}
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
		
		String[] returnVal = new String[3];
		List resultList = null;
		int resultVal = -1;
		
		int iUpdateResult  = 0; //TN_CLM_CONTRACT_MASTER update 결과  1월 7일 김재원 추가
		int iUpdateResult2 = 0; //TN_CLM_CONTRACT_CNSD update 결과  1월 7일 김재원 추가
		
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

		//vo.setExprt_notiday(StringUtil.replace(vo.getExprt_notiday(),"-",""));	//신성우 주석처리 2014-04-04
		vo.setCntrt_cnclsnday(StringUtil.replace(vo.getCntrt_cnclsnday(), "-", ""));
		vo.setCntrt_cnclsn_yn("Y");						//체결후등록은 무조건 계약체결여부 'Y'
		vo.setCnclsn_plndday(vo.getCntrt_cnclsnday());	//체결예정일은 체결일로 저장
		vo.setOrg_acpt_dlay_cause("");
		
		//체결본사본 파일 등록 시 사본등록일 세팅
		if(!"".equals(vo.getFileInfos6()) && !"".equals(vo.getCpy_regday())){
			vo.setCpy_regday(DateUtil.today());
		}else{
			vo.setCpy_regday("");
		}
		
		/*******************************************************************
		 *  계약 마스테테이블 해당 계약건 유무 조회
		 ********************************************************************/
		resultList = commonDAO.list("clm.manage.getContractMaster", vo);		
		if (resultList.size() > 0) {  //master id O	
			
			//master update
			iUpdateResult = commonDAO.modify("clm.manage.modifyRegistrationMaster", vo);
			
			if(iUpdateResult > 0){
				try{
					//history insert TH_CLM_CONTRACT_MASTER
					commonDAO.insert("las.contractmanager.insertContractMasterHistory",vo);
				}catch(Exception e){}
				
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
						try{
							commonDAO.insert("las.contractmanager.insertContractMasterHistory",hisVo);
						}catch(Exception e){}
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
			
			int insCnt = commonDAO.insert("clm.manage.insertRegistrationMaster", vo);
			//history insert TH_CLM_CONTRACT_MASTER
			if(insCnt > 0){
				try{
					commonDAO.insert("las.contractmanager.insertContractMasterHistory",vo);
				}catch(Exception e){}
			}
			//계약관리_계약별_검토
			vo.setCnsd_dept(vo.getSession_dept_cd());  //임시설정 
			vo.setCnsd_status("C04301");
			
			if("".equals(vo.getCnsdreq_id())){
				//없으면  의뢰 아이디 구하기 
				List rt = commonDAO.list("clm.manage.insertContractCnsdFinal",vo);
				
				if(rt.size() > 0){					
					ListOrderedMap map = (ListOrderedMap)rt.get(0);
					vo.setCnsdreq_id((String)map.get("cnsdreq_id"));
					
					commonDAO.insert("clm.manage.insertContractCnsd",vo);
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
				ArrayList listCntrtId = new ArrayList();
				if(vo.getArr_cntrt_id() != null){
					for(int k=0; k<vo.getArr_cntrt_id().length; k++){	//계약건만틈 관련자 정보 저장
						String[] arrTmpCntrtId = vo.getArr_cntrt_id();
						listCntrtId.add(arrTmpCntrtId[k]); 
					}
					if(!listCntrtId.contains(vo.getCntrt_id())){
						//ClmsDataUtil.debug("리스트에 계약 아이디가 없으면 ");
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
		
		//#6체결본사본첨부파일
		HashMap hm = new HashMap();
		hm.put("cntrt_id", vo.getCntrt_id());
		ListOrderedMap lom 	= null;
		String fileAdd = "";  
		List list = commonDAO.list("clm.manage.selectCntrtNo", hm);
		ConclusionVO cvo = new ConclusionVO();
		if (list != null && list.size() > 0) {
			lom = (ListOrderedMap)list.get(0);
			
			//연도 4자리중 2자리만 넣도록 수정
			if(vo.getCntrt_cnclsnday() !=null && vo.getCntrt_cnclsnday().length()>=8) fileAdd = vo.getCntrt_cnclsnday().substring(2);
			fileAdd = fileAdd + "_" + StringUtil.bvl((String)lom.get("auto_cntrd_nm"), "");
		}
		cvo.setSession_user_locale(vo.getSession_user_locale());
		cvo.setCntrt_cnclsnday(vo.getCntrt_cnclsnday());
		cvo.setCntrt_id(vo.getCntrt_id());
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01203");
		fvo.setFile_smlclsfcn("");
		fvo.setRef_key(vo.getCntrt_id());
		fvo.setFileInfos(vo.getFileInfos6());
		fvo.setFinalAdd(fileAdd); //날짜4자리_일련번호3자리
		fvo.setReg_id(vo.getSession_user_id());
		clmsFileService.mngClmsAttachFile(fvo, cvo);
		
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
	 * 체결후등록 상태변경
	 * @param vo
	 * @return
	 * @throws Exception
	 */	
	public int insertRegistrationStatus(ConsultationVO vo) throws Exception{
		int returnValue = -1;
		
		vo.setPrgrs_status("C04214");    // 체결후등록 결재중
		vo.setPrcs_depth("C02502");      // 프로세스 단계
		vo.setDepth_status("C02632");    // 단계상태 

		ArrayList listCntrtId = new ArrayList();
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
	 * 체결후등록 등록상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */	
	public List detailConsiderationReg(ConsultationVO vo) throws Exception {
		// 2014-08-11 Kevin Commented.
		/*commonDAO.insert("clm.manage.insertConsiderationLog", vo);	//조회로그 */		
		return commonDAO.list("clm.manage.detailRegistration", vo);
	}
	/**
	 * 체결목록이행정보조회 
	 * @param  vo ConsultationExecVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationExec(ConsultationExecVO execVo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationExec", execVo);
	}
	
	/**
	 * 체결품의 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationApprovalAttachInfo(ConsultationVO vo) throws Exception {
		//return commonDAO.list("clm.manage.listCompletionAttachInfo", vo);
		return commonDAO.list("clm.manage.listRegistrationAttachInfo", vo);//사본등록 첨부파일만 조회
	}
	
	/**
	 * 체결품의계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultationContractMasterApproval(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsultationContractMasterApproval", vo);
	}
	
	/**
	 * 체결목록특화정보조회 
	 * @param  vo ConsultationSpecialVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationSpecial(ConsultationSpecialVO specialVo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationSpecial", specialVo);
	}
	
	/**
	 * 얀관계약정보 목록을 조회한다.
	 * @param  vo RegistrationVO
	 * @return List
	 * @throws Exception
	 */
	public List listContractRelation(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationRelation", vo);
	}
	/*===========================================
	 * 결재관련서비스시작
	 ============================================*/
	/**
	 * 의뢰정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultationApprovalRequest(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsultationApprovalRequest", vo);
	}
	
	/**
	 * 엑셀 다운로드
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listRegistrationExcel(ManageVO vo) throws Exception {
		return commonDAO.list("clm.manage.listManage4", vo);
	}
	

	/**
	 * Excel Download for Legal Admin
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listRegistrationExcelForLegalAdmin(ManageVO vo)	throws Exception {
		return commonDAO.list("clm.manage.listManage4_legalAdmin", vo);
	}
}