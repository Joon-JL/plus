package com.sec.clm.draft.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.dto.ExcelVO;
import com.sec.clm.draft.service.ExcelUploadService;

public class ExcelUploadServiceImpl extends CommonServiceImpl implements ExcelUploadService{
	
	
	/**
	 * 엑셀 컨텐츠 DB에 저장
	 */
	public int saveExcelContent(List calList) throws Exception {
		int rs = 0;
		ListOrderedMap lom = null;
		if(calList != null && calList.size() > 0){
			for(int i = 0 ; i < calList.size() ; i++){
				lom = (ListOrderedMap) calList.get(i);
				String is_err = (String)lom.get("is_err");
				String delYn = (String)lom.get("delYn");
				
				//에러 데이터 및 삭제된 데이터가 아니면 insert
				if("N".equals(is_err) && "N".equals(delYn)){
//					lom.put("cal_no", (String)((ListOrderedMap)commonDAO.list("las.lawfirm.setCalNo",lom).get(0)).get("cal_no"));
				
					rs = commonDAO.insert("clm.draft.insertCustomerExcel",lom);
					if(rs == -1){
						throw new Exception("clm.draft.saveExcelContent Query Failed");
					}
				}
			}
		}
		return rs;
	}
	
	/**
	 * 유효성 체크
	 
	public int validateContent(List calList) throws Exception {
		int result = 0;
		
		ListOrderedMap lom = null;
		String lfm_nm = "";
		String case_id = "";
		String is_err = "";
		StringBuffer err_msg = null;
		List list = null;
		
		if(calList != null && calList.size() > 0){
			
			for(int i = 0 ; i < calList.size() ; i++){
				lom = (ListOrderedMap) calList.get(i);
				lfm_nm = (String)lom.get("lfm_nm");
				err_msg = (StringBuffer)lom.get("err_msg");
				case_id = (String)lom.get("case_id");
				is_err = (String)lom.get("is_err");
				
				//lfm_nm으로 lfm_id 존재 확인
				list = commonDAO.list("las.lawfirm.searchLfmId", lom);
				
				if(list!=null && list.size() > 0){
					
					//lfm_nm으로 하나이상의 로펌이 존재시 에러
					if(list.size() > 1){
						is_err = "Y";
						err_msg.append("There is more than one lawfirm.");
					}
					
					lom.put("lfm_id", (String)((ListOrderedMap)list.get(0)).get("lfm_id"));
					lom.put("nation_cd", (String)((ListOrderedMap)list.get(0)).get("nation_cd"));
				}
				else{
					is_err = "Y";
					err_msg.append("Lawfirm is not exist.");
				}
				
				//case_id 존재 확인
				list = commonDAO.list("las.lawfirm.searchCaseId", lom);
				
				if(list!=null && list.size() > 0){
					
					//하나이상의 case 존재시 에러
					if(list.size() > 1){
						is_err = "Y";
						err_msg.append("There is more than one case.");
					}
					
				}
				else{
					is_err = "Y";
					err_msg.append("Case is not exist.");
				}
				
				if(!("Y".equals(is_err))){
					//둘다 존재시 lfm_id, case_id, work_from_dt, work_to_dt, invoice_no 값이 동일한 row가 TN_LFM_CAL에 있는지 확인
					list = commonDAO.list("las.lawfirm.searchCal", lom);
					
					if(list!=null && list.size() > 0){
						is_err = "Y";
						err_msg.append("Data is duplicated.");
					}
				}
				
				lom.put("is_err", is_err);
				lom.put("err_msg", err_msg);
			}
		}
		return result;
	}
*/
	@Override
	public void saveExcelFile(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List saveTempTable(ExcelVO vo) throws Exception {
		// TODO Auto-generated method stub
		List resultList = null;
		
		// 1. 검토의뢰건 복사등록
		resultList = commonDAO.list("clm.draft.saveTempTable", vo); 
		
		return resultList;
	}

	@Override
	public List insertExcelToTable(ExcelVO vo) throws Exception {
		// TODO Auto-generated method stub
		List resultList = null;
		
		// 1. 검토의뢰건 복사등록
		resultList = commonDAO.list("clm.draft.insertExcelToTable", vo); 
		
		return resultList;
	}

	@Override
	public List listErrorReport(ExcelVO vo) throws Exception {
		// TODO Auto-generated method stub
		List resultList = null;
		
		// 에러 리스트 조회
		resultList = commonDAO.list("clm.draft.listExcelError", vo); 
		
		return resultList;
	}

	@Override
	public List listErrorDetail(ExcelVO vo) throws Exception {
		// TODO Auto-generated method stub
		List resultList = null;
		
		// 엑셀 에러 상세 조회
		resultList = commonDAO.list("clm.draft.listExcelErrorDetail", vo); 
		
		return resultList;
	}

	@Override
	public List excelInsertResult(ExcelVO vo) throws Exception {
		// TODO Auto-generated method stub
		List resultList = null;
		
		// 엑셀 에러 상세 조회
		resultList = commonDAO.list("clm.draft.excelInsertResult", vo); 
		
		return resultList;
	}

}
