/**
 * Project Name : 법무시스템 전자검토자
 * File name	: LibraryServiceImpl.java
 * Description	: 국내/해외계약서 Service impl(concrete class)
 * Author		: 신승완
 * Date			: 2011. 08. 04
 * Copyright	:
 */
package com.sec.las.elecReviewer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.elecReviewer.dto.ElecReviewerVO;
import com.sec.las.elecReviewer.service.ElecReviewerService;

/**
 * Description	: 법무시스템 전자검토자 Service impl(concrete class)
 * Author 		: 제이남
 * Date			: 2013.06.19
 */
public class ElecReviewerServiceImpl extends CommonServiceImpl implements ElecReviewerService {
	
	/**
	 * 전자검토자 업무이관  목록조회
	 * @param  vo ElecReviewerVO
	 * @return List
	 * @throws Exception
	 */
	public List listChangeReviewer(ElecReviewerVO vo) throws Exception{
		return commonDAO.list("las.elecreviewer.listChangeReviewer", vo);
	}
	
	/**
	 * 전자검토자 목록을 조회한다.
	 * @param  vo ElecReviewerVO
	 * @return List
	 * @throws Exception
	 */
	public List searchElecReviewer(ElecReviewerVO vo) throws Exception{
		return commonDAO.list("las.elecreviewer.listReviewers", vo);
	}
	
	/**
	 * 전자검토자 목록을 조회한다. (임시담당자)
	 * @param  vo ElecReviewerVO
	 * @return List
	 * @throws Exception
	 */
	public List searchElecReviewerTmp(ElecReviewerVO vo) throws Exception{
		return commonDAO.list("las.elecreviewer.listReviewers2", vo);
	}

                 
	/**
	 * 전자검토자 임시 담당자 지정 2013.06.21 김현구
	 * @param vo
	 * @return
	 * @throws Exception
	 * 
	 * 1. pk 세팅
	 * 2. 임시 담당자 전자검토자 ROLE 추가(기존에 존재할 시 X, TB_COM_ROLE_USER)
	 * 3. 임시담당자 현 담당회사코드 백업 (TN_LAS_TMPRESP_MNG_COMP)
	 * 4. TN_LAS_TMPRESP에 데이터 INSERT 
	 * 5. 담당자를 변경하려는 건에 대한 PK 데이터를 TN_LAS_TMPPRES_CONT에 INSERT
	 *    - 자문/표준계약서 : TN_LAS_CONSULT의 EXTNL_PRGRS_STATUS값이 S02,04인 경우
	 *    - 계약 :   
	 * 6. 원 담당자의 이관하는 회사코드 DELETE (TN_COM_SEC_MNG_COMP)
	 * 7. 임시 담당자의 이관받는 회사 코드 INSERT (기존에 존재할 시 X, TN_COM_SEC_MNG_COMP)
 	 * 8. 계약/자문/표준계약서 각 건에 대한 임시담당자 데이터 UPDATE
	 */
	public int appointTmpElecReviewer(ElecReviewerVO vo, HttpSession session) throws Exception {
		int rs = 0;
		int cnt_role = -1; //임시담당자 전자검토자 권한 존재여부 체크
		int cnt_code = -1; //임시담당자가 원래 관리하던 회사인지 체크

		/* 1. pk 세팅 */
		vo.setTmp_id((String)((Map)commonDAO.list("las.elecreviewer.createPrimaryKey").get(0)).get("PK"));
		
		/* 2. 임시 담당자 전자검토자 ROLE 추가(TB_COM_ROLE_USER) */
		
		//전자검토자 권한이 있는지 확인
		cnt_role = Integer.parseInt((String.valueOf(((Map)commonDAO.list("las.elecreviewer.isElecreviewer",vo).get(0)).get("CNT"))));
		
		//전자검토자 권한이 없다면 권한 삽입
		if(cnt_role == 0){
			rs = commonDAO.insert("las.elecreviewer.insertElecreviewerRole", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.insertElecreviewerRole Query Failed");
			}
		}
		
		//전자검토자 권한이 있을 시
		else if(cnt_role == 1){
			/* 3. 임시담당자 현 담당회사코드 백업 (TN_LAS_TMPRESP_MNG_COMP) */
			rs = commonDAO.insert("las.elecreviewer.insertMngComp", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.insertMngComp Query Failed");
			}
		}
		
		//COMP_CD, COMP_NM에 관한 처리
		String[] compCdArr = vo.getComp_cd().split(",");
		String[] compNmArr = vo.getComp_nm().split(",");
		
		for(int i = 0 ; i < compCdArr.length; i++){
			vo.setComp_cd(compCdArr[i]);
			vo.setComp_nm(compNmArr[i]);
			
			/* 4. TN_LAS_TMPRESP에 데이터 INSERT */
			rs = commonDAO.insert("las.elecreviewer.insert", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.insert Query Failed");
			}
			
			/* 5. 담당자를 변경하려는 건에 대한 PK 데이터를 TN_LAS_TMPPRES_CONT에 INSERT */
			rs = commonDAO.insert("las.elecreviewer.insertCont", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.insertCont Query Failed");
			}
			
			/* 6. 원 담당자의 이관하는 회사코드 DELETE (TN_COM_SEC_MNG_COMP) */
			rs = commonDAO.delete("las.elecreviewer.deleteOrgMngCompCd", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.deleteOrgMngCompCd Query Failed");
			}
			
			/* 7. 임시 담당자의 이관받는 회사 코드 INSERT (기존에 존재할 시 X, TN_COM_SEC_MNG_COMP) */
			cnt_code = Integer.parseInt((String.valueOf(((Map)commonDAO.list("las.elecreviewer.existTmpMngCompCd",vo).get(0)).get("CNT"))));
			
			if(cnt_code == 0){
				rs = commonDAO.insert("las.elecreviewer.insertTmpMngCompCd", vo);
				if(rs == -1){
					throw new Exception("las.elecreviewer.insertTmpMngCompCd Query Failed");
				}//end if
			}//end if
		}//end for
				
		/* 8. 계약/자문/표준계약서 각 건에 대한 임시담당자 데이터 UPDATE */
		// 자문/표준계약서 : TN_LAS_CONSULT의 EXTNL_PRGRS_STATUS값이 S02,04인 경우
		rs = commonDAO.modify("las.elecreviewer.updateLawConsultRespman", vo);
		if(rs == -1){
			throw new Exception("las.elecreviewer.updateLawConsultRespman Query Failed");
		}
		// 계약 검토 담당자 변경
		rs = commonDAO.modify("las.elecreviewer.updateContCnsdreqRespman", vo);
		if(rs == -1){
			throw new Exception("las.elecreviewer.updateContCnsdreqRespman Query Failed");
		}
		
		updateMngCompCd(vo,session);
		return 0;
	}

	
	/**
	 * 담당자 원복
	 * @param vo
	 * @return
	 * @throws Exception
	 * 
	 * 1. 임시 담당자 기존 회사코드와 원복하려는 회사 코드 존재여부 비교,
	 *    코드 존재시 삭제X, 존재하지 않으면 삭제(TN_COM_SEC_MNG_COMP)
	 * 2. 기존 담당자 코드에 원복하려는 회사코드 INSERT (기존에 존재할 시 X, TN_COM_SEC_MNG_COMP)
	 * 3. 계약/자문/표준계약서 각 건에 대한 원담당자 데이터 UPDATE   
	 * 4. TN_LAS_TMPRESP_MNG_COMP 해당데이터 삭제
	 * 5. TN_LAS_TMPRESP_CONT 해당데이터 삭제
	 * 6. TN_LAS_TMPRESP 해당데이터 삭제
	 */
	public int returnOrgElecReviewer(ElecReviewerVO vo,  HttpSession session) throws Exception {
		int rs = 0;
		int cnt_code = -1; //임시담당자가 원래 관리하던 회사인지 체크
		
		/* 1. 임시 담당자 기존 회사코드와 원복하려는 회사 코드 존재여부 비교,
        코드 존재시 삭제X, 존재하지 않으면 삭제(TN_COM_SEC_MNG_COMP) */
		cnt_code = Integer.parseInt((String.valueOf(((Map)commonDAO.list("las.elecreviewer.existTmpMngCompCd",vo).get(0)).get("CNT"))));
		
		if(cnt_code == 0){
			rs = commonDAO.delete("las.elecreviewer.deleteTmpMngCompCd", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.deleteTmpMngCompCd Query Failed");
			}
		}
		
		/* 2. 기존 담당자 코드에 원복하려는 회사코드 INSERT (기존에 존재할 시 X, TN_COM_SEC_MNG_COMP) */
		cnt_code = Integer.parseInt((String.valueOf(((Map)commonDAO.list("las.elecreviewer.existOrgMngCompCd",vo).get(0)).get("CNT"))));
		if(cnt_code == 0){
			rs = commonDAO.insert("las.elecreviewer.insertOrgMngCompCd", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.insertOrgMngCompCd Query Failed");
			}
		}
		
		/* 3. 계약/자문/표준계약서 각 건에 대한 원담당자 데이터 UPDATE */
		rs = commonDAO.modify("las.elecreviewer.returnLawConsultRespman", vo);
		if(rs == -1){
			throw new Exception("las.elecreviewer.returnLawConsultRespman Query Failed");
		}
		//계약 검토 원담당자 UPDATE
		rs = commonDAO.modify("las.elecreviewer.returnContCnsdreqRespman", vo);
		if(rs == -1){
			throw new Exception("las.elecreviewer.returnContCnsdreqRespman Query Failed");
		}

		/* 4. TN_LAS_TMPRESP_MNG_COMP 해당데이터 삭제 */
		if(cnt_code == 0){
			rs = commonDAO.delete("las.elecreviewer.deleteTmpMngComp", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.deleteTmpMngComp Query Failed");
			}
		}
		
		/* 5. TN_LAS_TMPRESP_CONT 해당데이터 삭제 */
		if(cnt_code == 0){
			rs = commonDAO.delete("las.elecreviewer.deleteTmpCont", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.deleteTmpCont Query Failed");
			}
		}
		
		/* 6. TN_LAS_TMPRESP 해당데이터 삭제 */
		if(cnt_code == 0){
			rs = commonDAO.delete("las.elecreviewer.deleteTmpResp", vo);
			if(rs == -1){
				throw new Exception("las.elecreviewer.deleteTmpResp Query Failed");
			}
		}
		updateMngCompCd(vo,session);
		return 0;
	}
	
	/**
	 * auth_comp_cd 세션값 최신화
	 * @param vo
	 * @param session
	 * @throws Exception
	 */
	public void updateMngCompCd(ElecReviewerVO vo, HttpSession session) throws Exception{
		
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("sys_cd", vo.getSys_cd());
		hMap.put("user_id", vo.getSession_user_id());
		
		//현재 접속자의 세션값 update
		List listMngComps  = commonDAO.list("secfw.user.getMngCompCdList", hMap);
		
		if (listMngComps.size() > 0) {
			ListOrderedMap listOrderMap = (ListOrderedMap)listMngComps.get(0);
			String mngCompCds = (String)listOrderMap.get("mng_comp_cds");
			
			session.setAttribute("secfw.session.auth_comp_cd", mngCompCds);
		}
	}

}