/*******************************************************
 * menu function
*******************************************************/
var Menu = {
		/**
		 * 해당 메뉴로 이동
		 */
		go:function(formId, actionUrl, methodNm, menuId, targetNm) {
			var frm = document.getElementById(formId);
			
			if (actionUrl == "") {
				return;
			}else if(menuId  != ""){				
				if(actionUrl.indexOf('?') == -1){
					actionUrl +=("?menu_id="+menuId);
				}else{
					actionUrl +=("&menu_id="+menuId);			
				}
			}
			frm.action = actionUrl;
			frm.target = targetNm;
			frm.method.value = methodNm;
			frm.targetMenuId.value = menuId;
			frm.selected_menu_id.value = menuId;
			
			frm.submit();
		},

		//특정 메뉴로 직접 접근시
		detail:function(formId, target, menuId2, menuId3, initUrl) {
			var frm = document.getElementById(formId);
			
			frm.action = "/secfw/main.do";
			frm.target = target;
			frm.method.value = "forwardMainFrame";
			frm.targetMenuId.value = menuId2;
			frm.selected_menu_id.value = menuId2;
			frm.selected_menu_detail_id.value = menuId3;
			frm.set_init_url.value = initUrl;
			frm.submit();
			viewHiddenProgress(true) ;
		},
		
		//특정 메뉴로 직접 접근시 - 통합검색에서만 사용됩니다. 
		detail2:function(formId, target, menuId2, menuId3, initUrl) {
			var frm = document.getElementById(formId);
			
			frm.action = "/secfw/main.do";
			frm.target = target;
			frm.method.value = "forwardMainFrame";
			frm.targetMenuId.value = menuId2;
			frm.selected_menu_id.value = menuId2;
			frm.selected_menu_detail_id.value = menuId3;
			frm.set_init_url.value = initUrl;
			frm.submit();
			viewHiddenProgress(false) ;
		},
		
		//특정 메뉴로 직접 접근시 - left 메뉴에서만 사용됩니다.
		detail3:function(formId, target, menuId2, menuId3, initUrl) {
			var frm = document.getElementById(formId);
			
			frm.action = "/secfw/main.do";
			frm.target = target;
			frm.method.value = "forwardMainFrame";
			frm.targetMenuId.value = menuId2;
			frm.selected_menu_id.value = menuId2;
			frm.selected_menu_detail_id.value = menuId3;
			frm.set_init_url.value = initUrl;
			frm.submit();
		},	
		
		/**
		 * Main Page로 이동
		 */
		main:function(formId, flag){
			var frm = document.getElementById(formId);
			
			frm.action = '/secfw/main.do';
			frm.target = '_top';
			frm.flag.value = flag;
			frm.method.value = 'forwardMainPage';
			
			frm.submit();
		},
		/**
		 * SiteMap 이동
		 */
		goSiteMap:function(formId, flag){
			frm.action = '/secfw/main.do';
			frm.target = '_top';
			frm.flag.value = flag;
			frm.method.value = 'forwardSiteMap';
			
			frm.submit();
		},
		/**
		 * LogOut 처리
		 */
		logout:function(formId, flag){
			var frm = document.getElementById(formId);

			frm.action = '/secfw/user2.do';
			frm.method.value = 'clmsLogOut';
			frm.flag.value = flag;
			frm.submit();
			window.close();			
		},
		/**
		 * sub 메뉴를 보여준다
		 */
		subMenuDisplay:function(selectedMenuId, className, displayYN) {
			$('div.' + className).each(function(index) {
				if (this.id == selectedMenuId) {
					if (displayYN == "Y") {
						this.style.display = "block";
					}else {
						this.style.display = "none";
					}
				}else {
					this.style.display = "none";
				}
			});
		},		
		/**
		 * 클릭된 menu 를 활성화 한다
		 */
		 menuTitleEnable:function(formId, objId, enableClsNm, disableClsNm) {
			$('.' + enableClsNm).each(function(index) {
				this.className = disableClsNm;
			});
			var obj = document.getElementById(objId);
			obj.className = enableClsNm;
		},
		
		gotoTnc:function(){
			var loc = document.location.hostname;
			
			if("selmsplus.sec.samsung.net" == loc ){
				parent.parent.location.href="http://tcms.sec.samsung.net/TNC/system/main.do";
			} else {
				parent.parent.location.href="http://10.40.65.96:6060/TNC/";
			}
			
		}
};

/*******************************************************
 * UI Function
*******************************************************/
/** Top Menu Start **/
//메뉴 마우스 over 시 서브메뉴 보여주기
$(function (){

	$('.gnb > ul > li > a').hover(
	function(){
		$('.gnb > ul > li').removeClass('on');
		$(this).parent().addClass('on');
	},
		function(){
	});


	$('.slide-btn01').bind('click',function(){
		$('.slide-target01').toggleClass('slide-area');
	});

	$('.slide-btn02').bind('click',function(){
		$('.slide-target02').toggleClass('slide-area');
	});


});
/** Top Menu End **/

/** TopMenu Toggle Start**/
function menu_Toggle(imgCtr, sysCd, divName, status)
{		
	if(document.getElementById(divName).style.display == "none")
	{
		document.getElementById(divName).style.display = "block";
		imgCtr.src = "/images/"+sysCd+"/ko/icon/ico_collapse.png";
		imgCtr.title = "collapse";
		if("top" == status){
			doResize('top', 'show');
		}
	}
	else
	{
		document.getElementById(divName).style.display = "none" ;
		imgCtr.src = "/images/"+sysCd+"/ko/icon/ico_expand.png";	
		imgCtr.title = "expand";
		if("top" == status){
			doResize('top', 'hide');
		}
	}				
}
/** TopMenu Toggle End**/

/** Left Menu Start **/
var before_level2 = 0;	//이전단계 레벨 2단계
var before_select_level2 = 0;	//이전 선택된 3단계 2Depth
var before_select_level3 = 0;	//이전 선택된 3단계 3Depth
var before_menuCnt = 0;
function leftMenuClick(level2, level3, menuCnt, initYn){
	//Top Menu로부터 넘어온 경우
	if("Y" == initYn){
		$('#LM_A_' + level2).attr('class', 'on');
		//3Depth 메뉴 펼치기
		if(level3 > 0){
			$('#LM_AREA_' + level2).attr('style', 'display:block');
			$('#LM_A_' + level2+'_'+level3).attr('class', 'on');
		}else{
			if(menuCnt > 0){
				$('#LM_AREA_' + level2).attr('style', 'display:block');
			}			
		}
		before_select_level2 = level2;
		before_select_level3 = level3;
		before_menuCnt = menuCnt;
	}else{
		//이전 2레벨과 현재 2레벨이 다를경우
		if(before_select_level2 != level2){
			//
			$('#LM_A_' + before_select_level2).attr('class', 'off');
			$('#LM_A_' + level2).attr('class', 'on');
			$('#LM_AREA_' + level2).attr('style', 'display:block');
			$('#LM_AREA_' + before_select_level2).attr('style', 'display:none');
			
			//이전 블럭 3레벨이 존재할경우
			if(before_select_level3 > 0){
				$('#LM_A_' + before_select_level2+'_'+before_select_level3).attr('class', 'off');
			}
			
			//2레벨의 url이 없을 경우 3레벨 메뉴 첫번째 클래스 변경
			if(level3 > 0 ){
				$('#LM_A_' + level2+'_'+level3).attr('class', 'on');
				before_select_level3 = level3;
			}
			
			before_select_level2 = level2;
		//이전 2레벨과 현재 2레벨이 같을 경우
		}else{
			//3레벨 메뉴가 다른경우
			if(before_select_level3 != level3){
				$('#LM_A_' + level2+'_'+before_select_level3).attr('class', 'off');
				$('#LM_A_' + level2+'_'+level3).attr('class', 'on');
				before_select_level3 = level3;
			}			
		}		
	}
}
/** Left Menu End **/

/** LeftMenu Toggle Start**/
function left_menu_Toggle(objId, sysCd, gbn)
{		
	if(gbn == "show"){
		document.getElementById(objId).style.display = "block";
		document.getElementById('hide').style.display = "block";
		document.getElementById('show').style.display = "none"; 
		doResize('left', 'show');
	}else{
		document.getElementById(objId).style.display = "none";
		document.getElementById('hide').style.display = "none"; 
		document.getElementById('show').style.display = "block"; 
		doResize('left', 'hide');
	}
}
/** LeftMenu Toggle End**/

/** Frame Size Control Start**/
function doResize(menu, gbn){

	if(menu == "top"){
		if("hide" == gbn){
			parent.frTop.rows = "69, *";
		}else{
			parent.frTop.rows = "102, *";
		}
	}else{
		if("hide" == gbn){
			parent.frameSub.cols = "23, *";
		}else{
			parent.frameSub.cols = "180, *";
		}
	}
}
/** Frame Size Control End**/

/** Sub메뉴 위치설정 Start **/
var temp = "";
var beforeMaxWidth = 0;
function subMenuSet(i){
	var iList = temp.split(",");
	var chk = true;
	//alert(iList);
	// 위치를 지정한 메뉴인지 체크
	if(iList.length > 1){
		for(var j=0; j < iList.length-1; j++ ) {
			if(iList[j] == i){
//				alert('걸림');
				chk = false;
				break;
			}
		}
	}
	var maxWidth = $('.gnb > ul').width();
	if(maxWidth < 980){
		maxWidth = 980;
	}

	//전체창의 크기가 달라질경우 초기화하여
	if(beforeMaxWidth != maxWidth){ 
//		alert('크기달라짐');
		chk = true;
		temp = "";
	}
	if(chk){
		beforeMaxWidth = maxWidth;
		//1레벨 메뉴 수
		var firstDepth = $('.gnb_2depth').length;
		//1레벨 메뉴 크기
		var firstWidth = $('#menu_'+i+' > a').width();
		var menuObj = $('#menu_'+i+' > a').position();
		var menuLeft = menuObj.left + 18;
	
		// 해당 메뉴 2레벨 갯수 구하기
		var twoDepth = $('#gnb_2depth_0'+i+' > li').length;
		// 2레벨 메뉴길이 구하기
		var subMenuFirstLeft = $('#menu_'+i+'_1 > a').position();
		//alert(twoDepth); 
		var subMenuEndRight = $('#menu_'+i+'_'+twoDepth+' > a').position();
	
		//2레벨 메뉴 총길이
		var twoWidth = 0;
		//alert(twoDepth );
		if(twoDepth>0){
			for(var j=1;j<=twoDepth;j++){
				//각 메뉴 길이 합, 각메뉴 길이 더할시 좌우 padding값 24더하기
				twoWidth = twoWidth + $('#menu_'+i+'_'+j+' > a').width()+24;
			}
		}		
		//alert(twoWidth);

		//left-padding값 구하기
		var subMenuLeft = 0; 
		if(i == 1){
			subMenuLeft = 31;
		}else{
			var twoTotalWidth = menuLeft + twoWidth;
			//alert('menuLeft='+menuLeft+'twoWidth='+twoWidth);
 			//alert('maxWidth='+maxWidth+'twoTotalWidth='+twoTotalWidth);
			if(twoTotalWidth > maxWidth){
				var gap = maxWidth -  twoTotalWidth ;
				
				var beI = 0;
				var k = eval(i);
				for(k; k > 0 ; k--){
					var beWidth = $('#menu_'+k).width();
					gap = gap + beWidth;
					beI = k;
					if(gap > 0){
						beI = k - 1;
						break;
					}
				}
				if(beI > 0){
					subMenuLeft = $('#menu_'+beI+' > a').position().left + 18;
				}else{
					subMenuLeft = 22;
				}
			}else{
				subMenuLeft = menuLeft;
			}
			
		}
		temp += i+",";
		
		$('#gnb_2depth_0'+i).css('min-width', maxWidth+'px');
		$('#gnb_2depth_0'+i).css('padding-left', subMenuLeft+'px');
	}
	
}
/** Sub메뉴 위치설정 End **/