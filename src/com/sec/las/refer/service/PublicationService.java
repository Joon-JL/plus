package com.sec.las.refer.service;

import java.util.List;
import java.util.Map;

import com.sec.las.refer.dto.PublicationVO;

public interface PublicationService {
	
	/**
	 * 페이징 처리를 하는 리스트를 리턴한다.
	 * @param vo PublicationVO
	 * @return
	 * @throws Exception
	 */
	public List listPage(PublicationVO vo) throws Exception ;
	
	/**
	 * 글을 등록한다.
	 * @param vo PublicationVO
	 * @return
	 * @throws Exception
	 */
	public int insert(PublicationVO vo) throws Exception ;
	
	/**
	 * 글을 조회한다.
	 * @param vo PublicationVO
	 * @return
	 * @throws Exception
	 */
	public Map detail(PublicationVO vo) throws Exception ;
	
	/**
	 * 조회수를 증가 시킨다.
	 * @param vo PublicationVO
	 * @throws Exception
	 */
	public void increseRefCnt(PublicationVO vo) throws Exception ;
	
	/**
	 * 글을 삭제한다.( 실제 삭제하는 것이 아니고 DEL_YN을 "Y"로 업데이트 한다.) 
	 * @param vo PublicationVO
	 * @throws Exception
	 */
	public int delete(PublicationVO vo) throws Exception ;
	
	/**
	 * 글을 수정한다.
	 * @param vo PublicationVO
	 * @return
	 * @throws Exception
	 */
	public int modify(PublicationVO vo) throws Exception ;
}
