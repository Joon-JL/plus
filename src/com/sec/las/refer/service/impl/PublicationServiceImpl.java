package com.sec.las.refer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.WebUtil;
import com.sec.las.refer.dto.PublicationVO;
import com.sec.las.refer.service.PublicationService;

public class PublicationServiceImpl extends CommonServiceImpl implements PublicationService {
	/**
	 * ID 생성 서비스
	 */
	IIdGenerationService idGenerationService ;
	
	/**
	 * ID 생성 서비스 세팅
	 * @param idGenerationService
	 */
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	/**
	 * 페이징 처리를 하는 리스트를 리턴한다.
	 * @param vo PublicationVO
	 * @return
	 * @throws Exception
	 */
	public List listPage(PublicationVO vo) throws Exception {
		return commonDAO.list("shri.publication.listPage", vo) ;
	}
	
	/**
	 * 글을 등록한다.
	 * <p>
	 * 1. 첨부파일 등록
	 * 2. 첨부파일ID를 vo에 세팅
	 * 3. 글등록
	 * </p>
	 * @param vo PublicationVO
	 * @return
	 * @throws Exception
	 */
	public int insert(PublicationVO vo) throws Exception {
		int result = 0 ;
		
		vo.setNotice_id(DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId());
		
		// 1. 첨부파일 등록
		String fileRefNo = attachFileService.FileSave(vo.getFileInfos(),vo.getFile_ref_no(),null);
		
		// 2. 첨부파일ID 세팅
		vo.setFile_ref_no(fileRefNo) ;
		
		// 3. 등록
		result = commonDAO.insert("shri.publication.insert", vo) ;
		
		return result ;
	}

	/**
	 * 상세 글을 조회한다.
	 * @param vo PublicationVO
	 * @return 조회 결과 없을 경우 빈 Map을 리턴한다.
	 * @throws Exception
	 */
	public Map detail(PublicationVO vo) throws Exception {
		List list = commonDAO.list("shri.publication.detail", vo) ;
		Map map = null ;
		if(list!=null && list.size()!=0) {
			map = (Map)list.get(0) ;
			
			//첨부파일 정보 가져오기
			map.put("fileInfos", attachFileService.FileList((String)map.get("file_ref_no"))) ;
			
			// 내용이 TEXT 타입일 경우 html tag를 변환한다.
			String contentsType = (String)map.get("contents_type") ;
			if(contentsType.equals("T")){
				String contents = (String)map.get("contents") ;
				contents = WebUtil.htmlToText(contents) ;
				map.put("contents", contents) ;
			}
		} else if(list.size()==0) {
			map = new ListOrderedMap() ;
		}
		
		return map;
	}
	
	/**
	 * 조회수를 증가 시킨다.
	 * @param vo
	 * @throws Exception
	 */
	public void increseRefCnt(PublicationVO vo) throws Exception {
		commonDAO.modify("shri.publication.increaseRefCnt", vo) ;
	}
	
	/**
	 * 글을 삭제한다.( 실제 삭제하는 것이 아니고 DEL_YN을 "Y"로 업데이트 한다.) 
	 * @param vo
	 * @throws Exception
	 */
	public int delete(PublicationVO vo) throws Exception {
		//  첨부파일이 있는 경우 첨부파일도 삭제
		if(vo.getFile_ref_no()!=null && !vo.getFile_ref_no().equals("")) {
			attachFileService.FileSave(vo.getFileInfos(), vo.getFile_ref_no(), "delete") ;
		}
		
		int result = 0 ;
		
		result = commonDAO.modify("shri.publication.modifyDelYn", vo) ;
		
		return result ;
	}
	
	/**
	 * 글을 수정한다.
	 * <p>
	 * 1. 첨부파일 등록
	 * 2. 첨부파일ID를 vo에 세팅
	 * 3. 글수정
	 * </p>
	 * @param vo PublicationVO
	 * @return
	 * @throws Exception
	 */
	public int modify(PublicationVO vo) throws Exception {
		int result = 0 ;
		
		// 1. 첨부파일 등록
		String fileRefNo = attachFileService.FileSave(vo.getFileInfos(),vo.getFile_ref_no(),null);
		
		// 2. 첨부파일ID 세팅
		vo.setFile_ref_no(fileRefNo) ;
		
		// 3. 등록
		result = commonDAO.modify("shri.publication.modify", vo) ;
		
		return result ;
	}
}
