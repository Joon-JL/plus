package com.sds.secframework.log.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.file.service.impl.AttachFileServiceImpl;
import com.sds.secframework.log.dto.LogVO;
import com.sds.secframework.log.service.LogService;

public class LogServiceImpl extends CommonServiceImpl implements LogService {
	
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	/**
	 * 로그인 현황 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public int insertLoginLog(LogVO vo) throws Exception {
		return commonDAO.insert("secfw.log.insertLoginLog", vo);
	}
	/**
	 * 로그아웃 현황 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public int updateLogoutLog(LogVO vo) throws Exception{
		return commonDAO.modify("secfw.log.updateLogoutLog", vo);

	}
	/**
	 * 메뉴사용 현황 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public int insertMenuLog(LogVO vo) throws Exception {
		return commonDAO.insert("secfw.log.insertMenuLog", vo);		
	}

	/**
	 * 파일다운로드 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public void insertFileDownLog(LogVO vo) throws Exception {

		String file_info_str = (String)vo.getFile_info_str(); 
		
		AttachFileServiceImpl attachFileService = new AttachFileServiceImpl();		
		ArrayList fileList = attachFileService.getFileInfosList(file_info_str);
		
		for(int i=0; i<fileList.size(); i++) {
			
			HashMap tempFile = new HashMap();
			tempFile = (HashMap)fileList.get(i);
			
			vo.setFile_path((String)tempFile.get("file_path"));
			vo.setFile_nm((String)tempFile.get("file_nm"));

			commonDAO.insert("secfw.log.insertFileDownLog", vo);
			
		}

	}
	
	/**
	 * 로그인 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List listLoginLog(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listLoginLog", vo);		
	}

	/**
	 * 로그인 현황 조회(엑셀다운로드)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List excelDownLoginLog(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.excelDownLoginLog", vo);		
	}
	
	/**
	 * 부서별 로그인 현황 조회(엑셀다운로드)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List excelDownLoginDeptLog(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.excelDownLoginDeptLog", vo);		
	}

	/**
	 * 화면 접속 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List listMenuUseLog(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listMenuUseLog", vo);		
	}
	
	/**
	 * 파일다운로드 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List listFileLog(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listFileLog", vo);		
	}
	
	/**
	 * 파일다운로드 현황 조회(엑셀다운)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List excelDownFileDownLoadLog(LogVO vo) throws Exception{
		return commonDAO.list("secfw.log.excelDownFileDownLoadLog", vo);
	}
	/**
	 * 파일다운로드 현황 조회(엑셀다운)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List excelDownMenuUseLog(LogVO vo) throws Exception{
		return commonDAO.list("secfw.log.excelDownMenuUseLog", vo);
	}
	/**
	 * 로그인 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONObject listLoginLogDayChart(LogVO vo) throws Exception {

		List list = commonDAO.list("secfw.log.listLoginLogDayChart", vo);

		JSONObject jsonObject = new JSONObject();

		try {

		JSONArray  dataArray = new JSONArray();
		JSONArray  xAxisArray = new JSONArray();
		
		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String loginDate = (String)lom.get("login_date");
				
				//1.5
//				int    rn        = (FormatUtil.formatInt(lom.get("rn")));
//				int    loginCnt  = (FormatUtil.formatInt(lom.get("login_cnt")));
//				int    maxCnt    = (FormatUtil.formatInt(lom.get("max_cnt")));
//				int    minCnt    = (FormatUtil.formatInt(lom.get("min_cnt")));
				
				//1.4
				BigDecimal    rn        = (BigDecimal)lom.get("rn");
				BigDecimal    loginCnt  = (BigDecimal)lom.get("login_cnt");
				BigDecimal    maxCnt    = (BigDecimal)lom.get("max_cnt");
				BigDecimal    minCnt    = (BigDecimal)lom.get("min_cnt");
				
				//데이타
				JSONArray data = new JSONArray();
				
				data.add(rn);
				data.add(loginCnt);

				dataArray.add(data);
				
				//X축
				JSONArray xAxis = new JSONArray();
				
				xAxis.add(rn);
				xAxis.add(loginDate);
				
				xAxisArray.add(xAxis);
				
				//MIN.MAX
				jsonObject.put("min", minCnt);
				jsonObject.put("max", maxCnt);
				
			}			
		}

		jsonObject.put("data", dataArray);
		jsonObject.put("xaxis", xAxisArray);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 사용자 최근 접속 정보
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List listRecentUserInfo(String userId, String sysCd, String compCd) throws Exception {
		HashMap hm = new HashMap();
		hm.put("sys_cd", sysCd);
		hm.put("user_id", userId);
		hm.put("comp_cd", compCd);
		
		return commonDAO.list("secfw.log.recentUserInfo", hm);		
	}
	
	/**
	 * 컬럼 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listColumnInfo(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.column.info", vo) ;
	}
	
	/**
	 * 로그인 현황 페이징 처리 안하는 리스트
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listLoginLogNoPage(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listLoginLogNoPage", vo) ;
	}
	
	/**
	 * 회사별 로그인 현황
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listLoginLogComp(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listLoginLogComp", vo) ;
	}
	
	/**
	 * 부서별 로그인 현황
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listLoginLogDept(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listLoginLogDept", vo) ;
	}
	
	/**
	 * 메뉴사용 현황 페이징 처리 안하는 리스트
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listMenuLogNoPage(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listMenuLogNoPage", vo) ;
	}
	
	/**
	 * 메뉴사용 현황(접속자수/접속횟수)
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listMenuLogContact(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listMenuLogContact", vo) ;
	}
	
	/**
	 * 파일다운로드 현황 페이징 처리 안하는 리스트
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listFileLogNoPage(LogVO vo) throws Exception {
		return commonDAO.list("secfw.log.listFileLogNoPage", vo) ;
	}

}
