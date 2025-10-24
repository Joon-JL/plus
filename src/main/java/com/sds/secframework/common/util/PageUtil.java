package com.sds.secframework.common.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * <pre>
 * 페이지정보를 처리하는 JavaBean 객체
 * 
 * 사용예
 * 	PageBean pBean = new PageBean();
 * 	pBean.setThisPage(page);
 * 	pBean.setRowPerPage(10);
 * 		.
 * 		.
 * 		.
 * 	pBean.setGroup();
 *
 * </pre>
 */
public class PageUtil implements Serializable{

	public PageUtil() {
	}
	
	public PageUtil(int totalRow, int rowPerPage, int thisPage){
		setTotalRow(totalRow);
		setRowPerPage(rowPerPage);
		setThisPage(thisPage);
	}

	protected int totalRow = 0;
	protected int rowPerPage = 10;
	protected int pageGroup = 10;
	protected int totalPage = 1;
	protected int thisPage = 1;
	protected int lastInGroup = 1;
	protected int firstInGroup = 1;
	protected String keyword = "";
	protected String searchType = "";
	private int index=-1;
	protected String page = "";

	/**
	 * 페이지를 설정한다.
	 * @param page 페이지번호
	 */
	public void setPage(String page){
		this.page = page;
	}
	
	/**
	 * 전체 갯수를 리턴한다.
	 * @return long 전체갯수
	 */
	public int getTotalRow() {
	  return this.totalRow;
	}

	/**
	 * 전체 갯수를 설정한다.
	 * @param totalrow 전체갯수
	 */
	public void setTotalRow(int totalrow) {
	  this.totalRow = totalrow;
	}
	
	/**
	 * 전체 갯수를 설정한다.
	 * @param totalrow 전체갯수
	 */
	public void setTotalRow(Object o) {		
		if(o instanceof BigDecimal) {
			this.totalRow = ((BigDecimal)o).intValue();
		} else if(o instanceof Integer) {
			this.totalRow = ((Integer)o).intValue();
		} 
	}

	/**
	 * 페이지당 게시될 갯수를 리턴한다.
	 * @return long 페이지당 갯수
	 */
	public int getRowPerPage() {
	  return this.rowPerPage;
	}

	/**
	 * 페이지당 게시될 갯수를 설정한다.
	 * @param row 페이지당 갯수
	 */
	public void setRowPerPage(int row) {
	  this.rowPerPage = row;
	}

	public int getPageGroup() {
	  return this.pageGroup;
	}

	public void setPageGroup(int group) {
	  this.pageGroup = group;
	}

	public int getPrevGroup() {
	  int prevGroup = getFirstInGroup() - 1;
	  if (prevGroup > 1) return prevGroup; else return 1;
	}

	public int getNextGroup() {
	  int nextGroup = getLastInGroup()+1;
      return ( nextGroup > totalPage ? totalPage : nextGroup );
	  //if (nextGroup % this.pageGroup == 1) return nextGroup; else return getLastInGroup();
	}

	public int getFirstInGroup() {
	  return this.firstInGroup;
	}

	public int getLastInGroup() {
	  if (this.lastInGroup < this.totalPage) return this.lastInGroup; else return this.totalPage;
	}

	public int getStartGroup() {
	  return 1;
	}

	public int getEndGroup() {
	  return this.getThisPage();
	}

	/**
	 * 현재 페이지를 리턴한다.
	 * @return long 현재페이지
	 */
	public int getThisPage() {
	  return this.thisPage;
	}

	/**
	 * 현재 페이지를 설정한다.
	 * @param page 현재페이지
	 */
	public void setThisPage(int page) {
	  this.thisPage = page;
	}

	/**
	 * 현재 페이지를 설정한다.
	 * @param page 현재페이지
	 */
	public void setThisPage(String page) {
	  if (page != null && !page.equals("")) this.thisPage = Integer.parseInt(page);
	}


	public void setKeyword(String keyword) {
	  this.keyword = keyword;
	}

	public String getKeyword() {
	  return this.keyword;
	}

	public void setSearchType(String searchType) {
	  this.searchType = searchType;
	}

	public String getSearchType() {
	  return this.searchType;
	}

	/**
	 * 전체 페이지를 설정한다.
	 * @param page 전체페이지 갯수
	 */
	public void setTotalPage(int page) {
	  this.totalPage = page;
	}

	public void setIndex(int index){
		this.index = index;
	}

	public int getIndex(){
		return this.index;
	}

	/**
	 * 전체 페이지 정보를 설정한다.
	 *
	 */
	public void setGroup() {

	  double rowPerPage = this.rowPerPage;
	  double pageGroup = this.pageGroup;

	  Double doubleTotalPage = new Double(Math.ceil(totalRow/rowPerPage));
	  Double doubleGroupOrder = new Double(Math.ceil(this.thisPage/pageGroup));

	  int totalPage = doubleTotalPage.intValue();
	  int groupOrder = doubleGroupOrder.intValue();

	  this.lastInGroup = groupOrder * this.pageGroup;
	  this.firstInGroup = lastInGroup - this.pageGroup + 1;
	  setTotalPage(totalPage);
	}

	/**
     * Use getPageNavi() instead
     * 
     * @deprecated Use getPageNavi() instead
	 * @return Paging 처리를 위한 네비게이션 바에 대한 html 
	 */
	public String getPage(){

		setGroup();
		StringBuffer pageString = new StringBuffer();
		pageString.append("<table border='0' cellspacing='0' cellpadding='0'>");
		pageString.append("<tr>");
		pageString.append("<td width='24' align='left'><a href=\"javascript:_action_page(").append(getFirstInGroup()).append(")\"><img src='/npm/images/i_front.gif' width='18' height='16' border='0'></a></td>");
        
        // 현재가 첫번째 Group 인 경우
        if ( getFirstInGroup() == 1 ) {
            pageString.append("<td width='42' align='left'><img src='/npm/images/i_preview.gif' width='18' height='16' border='0'></td>");
        }
        else {
            pageString.append("<td width='42' align='left'><a href=\"javascript:_action_page(").append(getPrevGroup()).append(")\"><img src='/npm/images/i_preview.gif' width='18' height='16' border='0'></a></td>");
        }
            
		pageString.append("<td align='center'>");

		for (int currentPage = getFirstInGroup(); currentPage <= getLastInGroup(); currentPage ++) {
			if (currentPage != getThisPage()) {
				pageString.append("<a href=\"javascript:_action_page(").append(currentPage).append(")\">").append("[").append(currentPage).append("]</a> ");
			}else{
				pageString.append("<span class='text_orange'><strong>[").append(currentPage).append("]</strong></span> ");
			}
		}

		pageString.append("</td>");
        
        // 현재가 마지막 Group 인 경우 
        if ( getLastInGroup() == getTotalPage() ) {
            pageString.append("<td width='42' align='right'><img src='/npm/images/i_next.gif' width='18' height='16' border='0'></td>");
        }
        else {
            pageString.append("<td width='42' align='right'><a href=\"javascript:_action_page(").append(getNextGroup()).append(")\"><img src='/npm/images/i_next.gif' width='18' height='16' border='0'></a></td>");
        }
		pageString.append("<td width='24' align='right'><a href=\"javascript:_action_page(").append(getLastInGroup()).append(")\"><img src='/npm/images/i_back.gif' width='18' height='16' border='0'></a></td>");
		pageString.append("</tr>");
		pageString.append("</table>");

		return pageString.toString();
	}
    
    /**
     * <pre>
     * 페이지 네비게이션바 생성<br>
     * &lt;&lt;  &lt;  [1]  [2]  [3]  [4]  [5]  [6]  [7]  [8]  [9]  [10]  &gt;  &gt;&gt; 형태의 
     * 네이게이션 바 생성
     * </pre>
     * 
     * @param context web context path
     * @param width 네비게이션바를 구성하는 Table의 width
     * @return 페이지 네비게이션바에 해당하는 html 
     */
    public String getPageNavi(PageUtil pageUtil, String sContext){
   
    	StringBuffer pageString = new StringBuffer();
    	pageString.append("");
    	
    	String sPrefix = "";
        if ( ! "".equals(StringUtil.nvl(sContext,""))) {
            sPrefix = sContext;
        }
    	
        /*
        String sFirstImage = sPrefix + "/images/secfw/btn/page-first.gif";
        String sPrevImage  = sPrefix + "/images/secfw/btn/page-prev.gif";
        String sNextImage  = sPrefix + "/images/secfw/btn/page-next.gif";
        String sLastImage  = sPrefix + "/images/secfw/btn/page-last.gif";
        */
        
        String sFirstImage = sPrefix + "/images/secfw/btn/page_first.gif";
        String sPrevImage  = sPrefix + "/images/secfw/btn/page_prev.gif";
        String sNextImage  = sPrefix + "/images/secfw/btn/page_next.gif";
        String sLastImage  = sPrefix + "/images/secfw/btn/page_last.gif";
    	
    	if(pageUtil.getTotalRow() > 0) {
	    	long nowPage = pageUtil.getThisPage();
			long prevPage = 0, nextPage = 0, pagingStart = 0, pagingEnd = 0;
								
			if(nowPage > 1) prevPage = nowPage - 1;
				else prevPage = 1;
			if(nowPage < pageUtil.getTotalPage()) nextPage = nowPage + 1;
				else nextPage = nowPage;
								
			pagingStart = ((nowPage-1)/10)*10 + 1;
			pagingEnd = (pageUtil.getTotalPage() < pagingStart + 9 ? pageUtil.getTotalPage() : pagingStart+9);
			
			/*
			pageString.append("<a href=\"javascript:goPage(1)\" class=\"first\"><img src='" + sFirstImage + "'></a>");
			pageString.append("<a href=\"javascript:goPage(" + prevPage + ")\" class=\"pre\"><img src='" + sPrevImage + "'></a><span class=\"page_num\">");
			*/
			pageString.append("<a href=\"javascript:goPage(1)\" class=\"img\"><img src='" + sFirstImage + "'></a>");
			pageString.append("<a href=\"javascript:goPage(" + prevPage + ")\" class=\"img\"><img src='" + sPrevImage + "' style='margin-right:10px'></a>");
			
			for(long i=pagingStart;i<=pagingEnd;i++) {
			    if(i == nowPage) {

			    	//pageString.append("<span class=\"selected\">" + i + "</span>");
			    	pageString.append("<strong>" + i + "</strong>");

			    } else {

			    	//pageString.append("<a href=\"javascript:goPage(" + i + ")\"><span>" + i + "</span></a>");
			    	pageString.append("<a href=\"javascript:goPage(" + i + ")\">" + i + "</a>");
			
			    }
			}

			/*
			pageString.append("</span><a href=\"javascript:goPage(" + nextPage + ")\" class=\"next\"><img src='" + sNextImage + "'></a>");
			pageString.append("<a href=\"javascript:goPage(" + pageUtil.getTotalPage() + ")\" class=\"end\"><img src='" + sLastImage + "'></a>");
			*/
			pageString.append("<a href=\"javascript:goPage(" + nextPage + ")\" class=\"img\"><img src='" + sNextImage + "' style='margin-left:10px'></a>");
			pageString.append("<a href=\"javascript:goPage(" + pageUtil.getTotalPage() + ")\" class=\"img\"><img src='" + sLastImage + "'></a>");
			
    	}

        return pageString.toString();
    }
    
    /**
     * 페이지 네비게이션바 생성 (CPIS용)
     */
    public String getCpisPageNavi(PageUtil pageUtil, String sContext) {
    	StringBuffer pageString = new StringBuffer();
    	pageString.append("");
    	
    	String sPrefix = "";
    	
    	if (!"".equals(StringUtil.nvl(sContext, ""))) {
    		sPrefix = sContext;
    	}
    	
    	String sFirstImage = sContext + "/images/cpis/common/btnPageFirst.gif";
    	String sPrevImage = sContext + "/images/cpis/common/btnPagePrev.gif";
    	String sNextImage = sContext + "/images/cpis/common/btnPageNext.gif";
    	String sLastImage = sContext + "/images/cpis/common/btnPageLast.gif";
    	
    	if (pageUtil.getTotalRow() > 0) {
    		long nowPage = pageUtil.getThisPage();
    		long prevPage = 0;
    		long nextPage = 0;
    		long pagingStart = 0;
    		long pagingEnd = 0;
    		
    		if (nowPage > 1) {
    			prevPage = nowPage - 1;
    		}else {
    			prevPage = 1;
    		}
    		
    		if (nowPage < pageUtil.getTotalPage()) {
    			nextPage = nowPage + 1;
    		}else {
    			nextPage = nowPage;
    		}
    		
    		pagingStart = ((nowPage - 1) / 10) * 10 + 1;
    		pagingEnd = (pageUtil.getTotalPage() < pagingStart + 9 ? pageUtil.getTotalPage() : pagingStart + 9);
    		
    		pageString.append("<table class='pageTb' align='center' cellspacing='2' cellpadding='2'>");
    		pageString.append("<tr>");
    		pageString.append("<td><img src='" + sFirstImage + "' onClick='javascript:goPage(1)'></td>");
    		pageString.append("<td><img src='" + sPrevImage + "' onClick='javascript:goPage(" + prevPage + ")'></td>");
    		
    		for (long i = pagingStart; i <= pagingEnd; i++) {
    			if (i == nowPage) {
    				pageString.append("<td><span>" + i + "</span></td>");
    				
    				if (pageUtil.getTotalPage() != i) {
    					pageString.append("<td class='bar'></td>");
    				}
    			}else {
    				pageString.append("<td onClick='javascript:goPage(" + i + ")'>" + i + "</td>");
    				
    				if (pageUtil.getTotalPage() != i) {
    					pageString.append("<td class='bar'></td>");
    				}
    			}
    		}
    		
    		pageString.append("<td><img src='" + sNextImage + "' onClick='javascript:goPage(" + nextPage + ")'></td>");
    		pageString.append("<td><img src='" + sLastImage + "' onClick='javascript:goPage(" + pageUtil.getTotalPage() + ")'></td>");
    		
    		
    		pageString.append("</tr>");
    		pageString.append("</table>");
    	}
    	
    	
    	return pageString.toString();
    }
	
	/**
	 * 현재 페이지의 마지막 게시물번호를 리턴한다.
	 * @return long 
	 */
	public String getEndIndex() {
		int endIndex = 0;
		if(thisPage == 0 || thisPage == 1){
			endIndex = rowPerPage;
		}else{
			endIndex = thisPage*rowPerPage;
		}
		return new Integer(endIndex).toString();
	}

	/**
	 * 현재 페이지의 첫번째 게시물번호를 리턴한다.
	 * @return long
	 */
	public String getStartIndex() {
		int startIndex = 0;
		if(thisPage == 0 || thisPage == 1){
			startIndex = 1;
		}else{
			startIndex = (thisPage-1)*rowPerPage + 1;
		}
		return new Integer(startIndex).toString();
	}

	/**
	 * 전체 페이지 갯수를 리턴한다.
	 * @return long
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 리스트 안에 있는 total cnt를 리턴한다. 
	 * @param list
	 * @param fieldNm
	 * @return
	 */
	public int getTotalRow(List list, String fieldNm) {
		if(list!=null && list.size()>0) {
			ListOrderedMap lm = (ListOrderedMap)list.get(0);
			setTotalRow(lm.get(fieldNm)) ;
		}
		
		return getTotalRow() ;
	}
  }
