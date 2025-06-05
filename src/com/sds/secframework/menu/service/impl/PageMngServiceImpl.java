package com.sds.secframework.menu.service.impl;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelLoader;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.menu.dto.PageMngVO;
import com.sds.secframework.menu.service.PageMngService;

/**
 * 페이지관리 Class
 *
 */
public class PageMngServiceImpl extends CommonServiceImpl implements PageMngService {
	
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	/**
	 * 페이지 목록 Table 타입
	 * @param 
	 * @return 페이지리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray listPageTable(PageMngVO vo) throws Exception {
		
		ArrayList list = (ArrayList)commonDAO.list("secfw.menu.listPageTable", vo);

		JSONArray jsonArray = new JSONArray();

		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd"       ,(String)lom.get("sys_cd"));
				jsonObject.put("page_id"      ,(String)lom.get("page_id"));
				jsonObject.put("page_nm"      ,(String)lom.get("page_nm"));
				jsonObject.put("page_url"     ,(String)lom.get("page_url"));
				jsonObject.put("use_yn"       ,(String)lom.get("use_yn"));
				jsonObject.put("authcheck_yn" ,(String)lom.get("authcheck_yn"));
				jsonObject.put("developer_nm" ,(String)lom.get("developer_nm"));
				jsonObject.put("comments"     ,(String)lom.get("comments"));
				jsonObject.put("total_cnt"    ,FormatUtil.formatNumToString(lom.get("total_cnt")));

				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;			
	}

	/**
	 * 페이지를 등록한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public void insertPage(PageMngVO vo) throws Exception {
		
		int result = 0;

		  String[] page_ids       = vo.getPage_ids();         // 페이지ID
		  String[] page_nms       = vo.getPage_nms();         // 페이지명
		  String[] page_urls      = vo.getPage_urls();        // 페이지URL
		  String[] use_yns        = vo.getUse_yns();          // 사용여부
		  String[] authcheck_yns  = vo.getAuthcheck_yns();    // 권한체크여부
		  String[] developer_nms  = vo.getDeveloper_nms();    // 개발자성명
		  String[] commentss      = vo.getCommentss();        // 설명
		  
		if(page_ids!=null && page_ids.length>0) {
			for(int i=0; i<page_ids.length; i++) {

				String menuId = StringUtil.bvl(page_ids[i],"");
				
				vo.setPage_id(page_ids[i]);              // 페이지ID
				vo.setPage_nm(StringUtil.convertHtmlTochars(page_nms[i]));              // 페이지명
				vo.setPage_url(page_urls[i]);            // 페이지URL
				vo.setUse_yn(use_yns[i]);                // 사용여부
				vo.setAuthcheck_yn(authcheck_yns[i]);    // 권한체크여부
				vo.setDeveloper_nm(StringUtil.convertHtmlTochars(developer_nms[i]));    // 개발자성명
				vo.setComments(StringUtil.convertHtmlTochars(commentss[i]));            // 설명
				
				if("".equals(menuId)) {

					//페이지아이디 생성, 상위페이지아이딧세팅
					String getPageId = DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
					vo.setPage_id(getPageId);
					
					result += commonDAO.insert("secfw.menu.insertPage", vo);
				} else {
					result += modifyPage(vo);
				}
			}
		}
		
	}

	/**
	 * 페이지정보를 수정한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public int modifyPage(PageMngVO vo) throws Exception {
		
		int result = 0;

		vo.setPage_nm(StringUtil.convertHtmlTochars(vo.getPage_nm()));              // 페이지명
		vo.setDeveloper_nm(StringUtil.convertHtmlTochars(vo.getDeveloper_nm()));    // 개발자성명
		vo.setComments(StringUtil.convertHtmlTochars(vo.getComments()));            // 설명

		//페이지정보 수정
		result += commonDAO.modify("secfw.menu.modifyPage", vo);
		
		return result;
	}

	/**
	 * 페이지를 삭제.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public int deletePage(PageMngVO vo) throws Exception {

		int result = 0;
		String[] mappingInfo = vo.getChk_id();

		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setPage_id(mappingInfo[i]);
				result += commonDAO.delete("secfw.menu.deletePage", vo);	
			}
		}
		
		return result;			
	}
	
	/**
	 * 페이지 목록 Table 타입
	 * @param 
	 * @return 페이지리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray listPageTableUpload(PageMngVO vo) throws Exception {
		
		ArrayList list = (ArrayList)commonDAO.list("secfw.menu.listPageTableUpload", vo);

		JSONArray jsonArray = new JSONArray();

		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd"       ,(String)lom.get("sys_cd"));
				jsonObject.put("page_id"      ,(String)lom.get("page_id"));
				jsonObject.put("page_nm"      ,(String)lom.get("page_nm"));
				jsonObject.put("page_url"     ,(String)lom.get("page_url"));
				jsonObject.put("use_yn"       ,(String)lom.get("use_yn"));
				jsonObject.put("authcheck_yn" ,(String)lom.get("authcheck_yn"));
				jsonObject.put("developer_nm" ,(String)lom.get("developer_nm"));
				jsonObject.put("comments"     ,(String)lom.get("comments"));
				jsonObject.put("comments"     ,(String)lom.get("upload_yn"));
				jsonObject.put("origin_total_cnt"    ,FormatUtil.formatNumToString(lom.get("origin_total_cnt")));
				jsonObject.put("upload_total_cnt"    ,FormatUtil.formatNumToString(lom.get("upload_total_cnt")));

				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;			
	}

	/**
	 * 페이지를 등록한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public String uploadExcel(PageMngVO vo, ExcelLoader excel) throws Exception {
		
		String result = "F";
		ArrayList al = new ArrayList();

		int sheetsCount = excel.getSheetsCount();                              // 읽은 Excel File의 Sheet 갯수를 얻는다.
	    for(int i=0;i<sheetsCount;i++) {                                       // Sheet 갯수만큼 Loop
	    	ArrayList sheetData = excel.getSheetData(i);                         // Sheet Data를 ArrayList 형태로 받음

	    	for(int j=0;j<excel.getRowsCount(i);j++) {
	    		
	    		Object[] obj = new Object[9];
	    		
	    		ArrayList rowData = (ArrayList)sheetData.get(j);                   // Row Data를 ArrayList 형태로 받음

	    		//페이지아이디 생성
	    		obj[0] = propertyService.getProperty("secfw.sysCd");
	    		obj[1] = DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
	    		obj[2] = (String)rowData.get(0);    // 페이지명
	    		obj[3] = (String)rowData.get(1);    // 페이지URL
	    		obj[4] = (String)rowData.get(2);    // 사용여부
	    		obj[5] = (String)rowData.get(3);    // 권한체크여부
	    		obj[6] = (String)rowData.get(4);    // 개발자성명
	    		obj[7] = (String)rowData.get(5);    // 설명
	    		obj[8] = vo.getReg_id();
	    		
	    		al.add(obj);
	    	}
	    }
					
		commonDAO.batchUpdate("secfw.menu.uploadExcel", al);
		
		return "T";
		
	}
	
	/**
	 * 페이지를 등록한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public void insertPageUpload(PageMngVO vo) throws Exception {
		
		int result = 0;

		  String[] page_ids       = vo.getPage_ids();         // 페이지ID
		  String[] page_nms       = vo.getPage_nms();         // 페이지명
		  String[] page_urls      = vo.getPage_urls();        // 페이지URL
		  String[] use_yns        = vo.getUse_yns();          // 사용여부
		  String[] authcheck_yns  = vo.getAuthcheck_yns();    // 권한체크여부
		  String[] developer_nms  = vo.getDeveloper_nms();    // 개발자성명
		  String[] commentss      = vo.getCommentss();        // 설명
		  
		if(page_ids!=null && page_ids.length>0) {
			for(int i=0; i<page_ids.length; i++) {

				String pageId = StringUtil.bvl(page_ids[i],"");
				
				vo.setPage_id(page_ids[i]);              // 페이지ID
				vo.setPage_nm(StringUtil.convertHtmlTochars(page_nms[i]));              // 페이지명
				vo.setPage_url(page_urls[i]);            // 페이지URL
				vo.setUse_yn(use_yns[i]);                // 사용여부
				vo.setAuthcheck_yn(authcheck_yns[i]);    // 권한체크여부
				vo.setDeveloper_nm(StringUtil.convertHtmlTochars(developer_nms[i]));    // 개발자성명
				vo.setComments(StringUtil.convertHtmlTochars(commentss[i]));            // 설명
				
				if("".equals(pageId)) {

					//페이지아이디 생성, 상위페이지아이딧세팅
					String getPageId = DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
					vo.setPage_id(getPageId);
					
					result += commonDAO.insert("secfw.menu.insertPage", vo);
				} else {
					result += modifyPage(vo);
				}
			}
		}
		
	}
	
	/**
	 * 페이지정보를 수정한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public int modifyPageUpload(PageMngVO vo) throws Exception {
		
		int result = 0;
	
		vo.setPage_nm(StringUtil.convertHtmlTochars(vo.getPage_nm()));              // 페이지명
		vo.setDeveloper_nm(StringUtil.convertHtmlTochars(vo.getDeveloper_nm()));    // 개발자성명
		vo.setComments(StringUtil.convertHtmlTochars(vo.getComments()));            // 설명

		//페이지정보 수정
		result += commonDAO.modify("secfw.menu.modifyPageUpload", vo);
		
		return result;
	}

	/**
	 * 페이지를 삭제.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public int deletePageUpload(PageMngVO vo) throws Exception {

		int result = 0;
		String[] mappingInfo = vo.getChk_id();

		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setPage_id(mappingInfo[i]);
				result += commonDAO.delete("secfw.menu.deletePageUpload", vo);	
			}
		}
		
		return result;			
	}

	/**
	 * 페이지 엑셀 업로드 데이타를 운영시스템으로 전송
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public int transferExcelUpload(PageMngVO vo) throws Exception {
		
		return commonDAO.insert("secfw.menu.transferExcelUpload", vo);
		
	}

}
