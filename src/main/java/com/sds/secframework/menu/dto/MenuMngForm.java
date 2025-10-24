package com.sds.secframework.menu.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * 메뉴관리 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class MenuMngForm extends CommonForm {

	  /**메뉴ID */
	  private String menu_id;           
	  /**메뉴명 */
	  private String menu_nm;           
	  /**메뉴영문명 */
	  private String menu_nm_eng;        
	  /**메뉴중문명 */
	  private String menu_nm_cha;        
	  /**메뉴일문명 */
	  private String menu_nm_jpn;
	  /**메뉴불문명 */
	  private String menu_nm_fra;
	  /**메뉴독문명 */
	  private String menu_nm_deu;
	  /**메뉴이문명 */
	  private String menu_nm_ita;
	  /**메뉴스페인어문명 */
	  private String menu_nm_esp;
	  /**상위메뉴아이디 */
	  private String up_menu_id;        
	  /**상위메뉴명 */
	  private String up_menu_nm;        
	  /**메뉴레벨 */
	  private int    menu_level;        
	  /**메뉴순서 */
	  private int    menu_order;        
	  /**설명 */
	  private String comments;          
	  /**사용여부 */
	  private String use_yn;            
	  /**메뉴URL */
	  private String menu_url;          
	  /**메뉴타입(M:메뉴,P:페이지) */
	  private String menu_type;        
	  /**권한체크여부 */
	  private String authcheck_yn;         
	  /**메뉴이미지 */
	  private String menu_img;         
	  /**메뉴영문이미지 */
	  private String menu_img_eng;         
	  /**메뉴중문이미지 */
	  private String menu_img_cha;         
	  /**메뉴일문이미지 */
	  private String menu_img_jpn;         
	  /**팝업명 */
	  private String popup_nm;          
	  /**팝업가로사이즈 */
	  private int    popup_width;       
	  /**팝업세로사이즈 */
	  private int    popup_height;      
	  /**표시여부 */
	  private String display_yn;        
	  /**등록자아이디 */
	  private String reg_id;            
	  /**등록일시 */
	  private String reg_dt;            
	  /**수정자아이디 */
	  private String mod_id;            
	  /**수정일시 */
	  private String mod_dt;            
	  
	  /**********************************************************************
	  * 검색조건
	   **********************************************************************/
	  /**선택메뉴아이디 */
	  private String select_menu_id;   
	  
	  /**********************************************************************
	  * 메뉴삭제
	  **********************************************************************/
	  /**메뉴삭제리스트 */
	  private String[] chk_id;     

	  /**********************************************************************
	  * 메뉴데이타(입력용)
	  **********************************************************************/
	  /**메뉴ID */
	  private String[] menu_ids;           
	  /**메뉴명 */
	  private String[] menu_nms;           
	  /**메뉴영문명 */
	  private String[] menu_nm_engs;       
	  /**메뉴중문명 */
	  private String[] menu_nm_chas;         
	  /**메뉴일문명 */
	  private String[] menu_nm_jpns;
	  /**메뉴불문명 */
	  private String[] menu_nm_fras;
	  /**메뉴독문명 */
	  private String[] menu_nm_deus;
	  /**메뉴이문명 */
	  private String[] menu_nm_itas;
	  /**메뉴스페인어문명 */
	  private String[] menu_nm_esps;
	  /**상위메뉴아이디 */
	  private String[] up_menu_ids;        
	  /**상위메뉴명 */
	  private String[] up_menu_nms;        
	  /**메뉴레벨 */
	  private String[] menu_levels;        
	  /**메뉴순서 */
	  private String[] menu_orders;        
	  /**설명 */
	  private String[] commentss;          
	  /**사용여부 */
	  private String[] use_yns;            
	  /**메뉴URL */
	  private String[] menu_urls;          
	  /**메뉴타입(M:메뉴,P:팝업페이지) */
	  private String[] menu_types;            
	  /**권한체크여부 */
	  private String[] authcheck_yns;       
	  /**메뉴이미지 */
	  private String[] menu_imgs;           
	  /**메뉴영문이미지 */
	  private String[] menu_img_engs;           
	  /**메뉴중문이미지 */
	  private String[] menu_img_chas;           
	  /**메뉴일문이미지 */
	  private String[] menu_img_jpns;         
	  /**팝업명 */
	  private String[] popup_nms;          
	  /**팝업가로사이즈 */
	  private String[] popup_widths;       
	  /**팝업가로사이즈 */
	  private String[] popup_heights;      
	  /**표시여부 */
	  private String[] display_yns;        
	 
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_nm() {
		return menu_nm;
	}
	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	public String getMenu_nm_eng() {
		return menu_nm_eng;
	}
	public void setMenu_nm_eng(String menu_nm_eng) {
		this.menu_nm_eng = menu_nm_eng;
	}
	public String getUp_menu_id() {
		return up_menu_id;
	}
	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}
	public String getUp_menu_nm() {
		return up_menu_nm;
	}
	public void setUp_menu_nm(String up_menu_nm) {
		this.up_menu_nm = up_menu_nm;
	}
	public int getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(int menu_level) {
		this.menu_level = menu_level;
	}
	public int getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(int menu_order) {
		this.menu_order = menu_order;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getPopup_nm() {
		return popup_nm;
	}
	public void setPopup_nm(String popup_nm) {
		this.popup_nm = popup_nm;
	}
	public int getPopup_width() {
		return popup_width;
	}
	public void setPopup_width(int popup_width) {
		this.popup_width = popup_width;
	}
	public int getPopup_height() {
		return popup_height;
	}
	public void setPopup_height(int popup_height) {
		this.popup_height = popup_height;
	}
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getSelect_menu_id() {
		return select_menu_id;
	}
	public void setSelect_menu_id(String select_menu_id) {
		this.select_menu_id = select_menu_id;
	}
	public String[] getChk_id() {
		return chk_id;
	}
	public void setChk_id(String[] chk_id) {
		this.chk_id = chk_id;
	}
	public String[] getMenu_ids() {
		return menu_ids;
	}
	public void setMenu_ids(String[] menu_ids) {
		this.menu_ids = menu_ids;
	}
	public String[] getMenu_nms() {
		return menu_nms;
	}
	public void setMenu_nms(String[] menu_nms) {
		this.menu_nms = menu_nms;
	}
	public String[] getMenu_nm_engs() {
		return menu_nm_engs;
	}
	public void setMenu_nm_engs(String[] menu_nm_engs) {
		this.menu_nm_engs = menu_nm_engs;
	}
	public String[] getUp_menu_ids() {
		return up_menu_ids;
	}
	public void setUp_menu_ids(String[] up_menu_ids) {
		this.up_menu_ids = up_menu_ids;
	}
	public String[] getUp_menu_nms() {
		return up_menu_nms;
	}
	public void setUp_menu_nms(String[] up_menu_nms) {
		this.up_menu_nms = up_menu_nms;
	}
	public String[] getMenu_levels() {
		return menu_levels;
	}
	public void setMenu_levels(String[] menu_levels) {
		this.menu_levels = menu_levels;
	}
	public String[] getMenu_orders() {
		return menu_orders;
	}
	public void setMenu_orders(String[] menu_orders) {
		this.menu_orders = menu_orders;
	}
	public String[] getCommentss() {
		return commentss;
	}
	public void setCommentss(String[] commentss) {
		this.commentss = commentss;
	}
	public String[] getUse_yns() {
		return use_yns;
	}
	public void setUse_yns(String[] use_yns) {
		this.use_yns = use_yns;
	}
	public String[] getMenu_urls() {
		return menu_urls;
	}
	public void setMenu_urls(String[] menu_urls) {
		this.menu_urls = menu_urls;
	}
	public String[] getMenu_types() {
		return menu_types;
	}
	public void setMenu_types(String[] menu_types) {
		this.menu_types = menu_types;
	}
	public String[] getPopup_nms() {
		return popup_nms;
	}
	public void setPopup_nms(String[] popup_nms) {
		this.popup_nms = popup_nms;
	}
	public String[] getPopup_widths() {
		return popup_widths;
	}
	public void setPopup_widths(String[] popup_widths) {
		this.popup_widths = popup_widths;
	}
	public String[] getPopup_heights() {
		return popup_heights;
	}
	public void setPopup_heights(String[] popup_heights) {
		this.popup_heights = popup_heights;
	}
	public String[] getDisplay_yns() {
		return display_yns;
	}
	public void setDisplay_yns(String[] display_yns) {
		this.display_yns = display_yns;
	}
	public String getMenu_nm_cha() {
		return menu_nm_cha;
	}
	public void setMenu_nm_cha(String menu_nm_cha) {
		this.menu_nm_cha = menu_nm_cha;
	}
	public String getMenu_nm_jpn() {
		return menu_nm_jpn;
	}
	public void setMenu_nm_jpn(String menu_nm_jpn) {
		this.menu_nm_jpn = menu_nm_jpn;
	}
	public String getMenu_img() {
		return menu_img;
	}
	public void setMenu_img(String menu_img) {
		this.menu_img = menu_img;
	}
	public String getMenu_img_eng() {
		return menu_img_eng;
	}
	public void setMenu_img_eng(String menu_img_eng) {
		this.menu_img_eng = menu_img_eng;
	}
	public String getMenu_img_cha() {
		return menu_img_cha;
	}
	public void setMenu_img_cha(String menu_img_cha) {
		this.menu_img_cha = menu_img_cha;
	}
	public String getMenu_img_jpn() {
		return menu_img_jpn;
	}
	public void setMenu_img_jpn(String menu_img_jpn) {
		this.menu_img_jpn = menu_img_jpn;
	}
	public String[] getMenu_nm_chas() {
		return menu_nm_chas;
	}
	public void setMenu_nm_chas(String[] menu_nm_chas) {
		this.menu_nm_chas = menu_nm_chas;
	}
	public String[] getMenu_nm_jpns() {
		return menu_nm_jpns;
	}
	public void setMenu_nm_jpns(String[] menu_nm_jpns) {
		this.menu_nm_jpns = menu_nm_jpns;
	}
	public String[] getMenu_imgs() {
		return menu_imgs;
	}
	public void setMenu_imgs(String[] menu_imgs) {
		this.menu_imgs = menu_imgs;
	}
	public String[] getMenu_img_engs() {
		return menu_img_engs;
	}
	public void setMenu_img_engs(String[] menu_img_engs) {
		this.menu_img_engs = menu_img_engs;
	}
	public String[] getMenu_img_chas() {
		return menu_img_chas;
	}
	public void setMenu_img_chas(String[] menu_img_chas) {
		this.menu_img_chas = menu_img_chas;
	}
	public String[] getMenu_img_jpns() {
		return menu_img_jpns;
	}
	public void setMenu_img_jpns(String[] menu_img_jpns) {
		this.menu_img_jpns = menu_img_jpns;
	}
	public String getAuthcheck_yn() {
		return authcheck_yn;
	}
	public void setAuthcheck_yn(String authcheck_yn) {
		this.authcheck_yn = authcheck_yn;
	}
	public String[] getAuthcheck_yns() {
		return authcheck_yns;
	}
	public void setAuthcheck_yns(String[] authcheck_yns) {
		this.authcheck_yns = authcheck_yns;
	}
	public void setMenu_nm_fra(String menu_nm_fra) {
		this.menu_nm_fra = menu_nm_fra;
	}
	public String getMenu_nm_fra() {
		return menu_nm_fra;
	}
	public void setMenu_nm_deu(String menu_nm_deu) {
		this.menu_nm_deu = menu_nm_deu;
	}
	public String getMenu_nm_deu() {
		return menu_nm_deu;
	}
	public void setMenu_nm_esp(String menu_nm_esp) {
		this.menu_nm_esp = menu_nm_esp;
	}
	public String getMenu_nm_esp() {
		return menu_nm_esp;
	}
	public void setMenu_nm_ita(String menu_nm_ita) {
		this.menu_nm_ita = menu_nm_ita;
	}
	public String getMenu_nm_ita() {
		return menu_nm_ita;
	}
	public void setMenu_nm_fras(String[] menu_nm_fras) {
		this.menu_nm_fras = menu_nm_fras;
	}
	public String[] getMenu_nm_fras() {
		return menu_nm_fras;
	}
	public void setMenu_nm_deus(String[] menu_nm_deus) {
		this.menu_nm_deus = menu_nm_deus;
	}
	public String[] getMenu_nm_deus() {
		return menu_nm_deus;
	}
	public void setMenu_nm_itas(String[] menu_nm_itas) {
		this.menu_nm_itas = menu_nm_itas;
	}
	public String[] getMenu_nm_itas() {
		return menu_nm_itas;
	}
	public void setMenu_nm_esps(String[] menu_nm_esps) {
		this.menu_nm_esps = menu_nm_esps;
	}
	public String[] getMenu_nm_esps() {
		return menu_nm_esps;
	}

}
