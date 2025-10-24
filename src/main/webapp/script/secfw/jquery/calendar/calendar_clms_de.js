/*

*	2011.03.23 make by ahmax(ahmax95@gmail.com)
*	http://beans9.tistory.com
*
*	2011.09.15 edit by 신남원
*/

// max year ( max_year = 0 = now year)
var max_year = 2035;

// min year
var min_year = 2005;

// date format (xxxx-xx-xx)
var date_split_format = "-";

// today show(true)/hide(false)
var today_show = true;

// day of the week 
var yoil = ["Son","Mon","Die","Mit","Don","Fre","Sam","Son"];

// month of day
var mon = [31,28,31,30,31,30,31,31,30,31,30,31];

// start day ( 0: sunday, 1:monday )
var start_day = "0";

// image icon
var imageIcon = true;
var imageUrl  = "/script/secfw/jquery/calendar/ico_calendar.gif";
// readOnly
var readOnly = false;

// today			
var todayStr  = dateStr(new Date(),1);		

//현재 menu_id
var v_cur_menu_id = '';
function initCal(target,menu_id){
	var id = target + "Cal";
	$("#" + target).attr("maxlength", 10);

	if(!(menu_id == null || menu_id == undefined)) v_cur_menu_id = menu_id;

	// 입력가능할경우 아이콘을 생성한다.
	if( readOnly == false ) imageIcon = true;
		
	$(function(){		
		var tInput  = $("#" + target).offset();
		var tHeight = $("#" + target).outerHeight();		
		var tWidth 	= $("#" + target).outerWidth();
		
		// 입력값이 있을경우 달력초기화시 해당 입력달에 날짜로 셋팅
		var targetVal = $("#" + target).val();		
		//Replaced Sungwoo 2014-09-29
		if( targetVal == "" || targetVal == null) targetVal = todayStr;

		//Calendar 영역추가
		$(document.body).append($("<div id='"+ id +"' onselectstart='return false'>"));
		//Layer 영역추가 (ActiveX 위에 Calendar가 나타나지 않으므로 하단에 Layer로 처리
		$(document.body).append($("<div id='"+target+"_layer' class='calendar_layer'><iframe id='"+target+"_frm' width='200px' height='160px' frameborder='0'></iframe></div>"));
		$(document).click(function(event){
			if(event.target.id != id && event.target.id != target 
			&& $("#" + id).css('display') != "none" && (event.target.id).indexOf("calIcon") == -1 ){
				$("#" + target+"_layer").hide();
				$("#" + id).hide();
			};	
		});
								
		//browser별 처리
		if ($.browser.msie) {
	    }else {
	    		/*function reEnable(){return true;	}		
					function disableselect(e){	
						return false;
					}															
					document.getElementById(id).onmousedown=disableselect;
					document.getElementById(id).onmouseup=reEnable;*/			
	    }
		
		$("#" + id).addClass("divBody");   
		
		// readOnly
		if( readOnly == true ){
			$("#" + target).attr("readonly", true);			
			$("#" + target).click(function(e){			
				changeToday(id,target);
				calPosition(target,id);
				$("#" + target+"_layer").css("display","block");
				$("#" + id).show();
				addHover(id,target);	
			});	
		}else{
			$("#" + target).change(function(e){	
				userFuncProc(target);
			});	
		}	

		$("#" + id).html(makeCal(id, targetVal));
		
		$("#" + id).click(function(e){
				e.stopPropagation();
		});
		
		//wheel event
		$("#" + id).bind('mousewheel', function(event, delta) {
            if (delta > 0) {moveDate(id, 1);}
				else {	moveDate(id, -1); }
				addHover(id,target);
            return false;
		});
		
		$("#" + id + "_calYear").attr("value",targetVal.split(date_split_format)[0]);
		$("#" + id + "_calYear").change(function(){			
			changeCal(id,target);
		}).keyup(function(){
			changeCal(id,target);		
		}).mousewheel(function(e, d){			
			this.blur();
		});
		
		$("#" + id + "_calMon").attr("value",eval(targetVal.split(date_split_format)[1]));
		$("#" + id + "_calMon").change(function(){
			changeCal(id,target);
		}).keyup(function(){									
			changeCal(id,target);
		}).mousewheel(function(e, d){			
			this.blur();
		});
			
		$("#" + id + "_left").click(function(e){
			moveDate(id,-1);
			addHover(id,target);
			this.blur();
		}).mouseover(function(){
			$(this).addClass("divHeadOnleft");
		}).mouseout(function(){
			$(this).removeClass("divHeadOnleft");
		});
		
		$("#" + id + "_right").click(function(e){
			moveDate(id,1);
			addHover(id,target);
			this.blur();					
		}).mouseover(function(){
			$(this).addClass("divHeadOnRigth");
		}).mouseout(function(){
			$(this).removeClass("divHeadOnRigth");
		});;
		
		$("#" + id + "tBtn").addClass("tBtn");
		$("#" + id + "tBox").hover(function(){
			$(this).addClass("tBtnOn");
		},function(){
			$(this).removeClass("tBtnOn");
		}).click(function(){
			changeToday(id,target);
		});
		
		// today
		$("#" + id + "tBtn").addClass("tBtn");
		$("#" + id + "tBox").hover(function(){
			$(this).addClass("tBtnOn");
		},function(){
			$(this).removeClass("tBtnOn");
		}).click(function(){
			changeToday1(id,target);
			
		});
		
		// image Icon					
		if( imageIcon == true ){
			// 달력이미지 
 			$("#"+target).after($("<img id='"+id+"_calIcon' src='"+ imageUrl +"' class='imageIcon'>"));
			$("#" + id + "_calIcon").click(function(E){
				//기존 열린 달력 모두 숨기기
				$(".calendar_layer").hide();
				$(".divBody").hide();

				changeToday(id,target);
				calPosition(target,id);
				$("#" + target+"_layer").show();
				$("#" + id).show();	
				
				userFuncProc(target,id,'cntrtperiod_endday_addhover');
				
			});
		}	
		 	
		$(window).resize(function() {
	    	calPosition(target,id);
	    });
	});
}

/* Calendar 및 Layer 위치조정*/
function calPosition(target,id){

	var tInput  = $("#" + target).offset();
	var tHeight = $("#" + target).outerHeight();

	var boxInput = document.body.scrollWidth;
	
	//alert(boxInput);
	//alert(tInput.right);
	if( tInput != null){
		if(boxInput - tInput.left > 181){
			$("#" + id).css({"top":tInput.top + tHeight , "left":tInput.left});
			$("#" + target +"_layer").css({"top":tInput.top + tHeight , "left":tInput.left});
		}else{
			$("#" + id).css({"top":tInput.top + tHeight , "left":tInput.left-113+"px"});
			$("#" + target +"_layer").css({"top":tInput.top + tHeight , "left":tInput.left-113+"px"});
		}
	}
}

var one_proc = false;
function userFuncProc(target,option_id,option_param1,option_param2){
	//=======================단일날짜용 START==================================
	var rDemnday = document.getElementsByName('re_demndday');
	var rNotiday = document.getElementsByName('exprt_notiday');
	var rPlndday = document.getElementsByName('cnclsn_plndday');
	var rApbtday = document.getElementsByName('bfhdcstn_apbtday');
	var rCnclsnday = document.getElementsByName('cntrt_cnclsnday') ;
	
	var rSignday = document.getElementsByName('signday') ;
	var rOppntSignday = document.getElementsByName('oppnt_signday') ;
	
	//=======================단일날짜용 END====================================
	
	//=======================시작일-종료일 쌍 START=============================
	var rSregday = document.getElementsByName('srch_start_reg_dt');
	var rEregday = document.getElementsByName('srch_end_reg_dt');
	var rSreqday = document.getElementsByName('srch_start_reqday');
	var rEreqday = document.getElementsByName('srch_end_reqday');
	var rScnlsnday = document.getElementsByName('srch_start_cnlsnday');
	var rEcnlsnday = document.getElementsByName('srch_end_cnlsnday');
	var rSymd = document.getElementsByName('srch_start_ymd');
	var rEymd = document.getElementsByName('srch_end_ymd');
	var rSdt = document.getElementsByName('srch_start_dt');
	var rEdt = document.getElementsByName('srch_end_dt');
	var rSregdt1 = document.getElementsByName('srch_regdt1');
	var rEregdt1 = document.getElementsByName('srch_regdt2');
	var rSacptday = document.getElementsByName('str_org_acptday');
	var rEacptday = document.getElementsByName('end_org_acptday');
	
	var rSorgacptday = document.getElementsByName('srch_str_org_acptday');
	var rEorgacptday = document.getElementsByName('srch_end_org_acptday');
	
	var rSrch_cntrtperiod_startday = document.getElementsByName('srch_cntrtperiod_startday');
	var rSrch_cntrtperiod_endday = document.getElementsByName('srch_cntrtperiod_endday');
	//=======================시작일-종료일 쌍 END===============================
	
	//=======================대표변수=========================================
	var rStartday = document.getElementsByName('cntrtperiod_startday'); 
	var rEndday = document.getElementsByName('cntrtperiod_endday');
	 
	if(target=='cntrtperiod_endday' && option_param1=='cntrtperiod_endday_addhover' && one_proc == false ){
			addHover(option_id,target);
			one_proc = true;
			
	//단일 날짜체크시		 
	}else if(target=='re_demndday' || target=='exprt_notiday' || target=='cntrt_cnclsnday' ||
			target=='cnclsn_plndday' || target=='bfhdcstn_apbtday' || target=='signday' || target=='oppnt_signday'
	){ 
		if(target=='re_demndday')	{rStartday=rDemnday;} 
		else if(target=='exprt_notiday')	{rStartday=rNotiday;} 
		else if(target=='cnclsn_plndday')	{rStartday=rPlndday;} 
		else if(target=='bfhdcstn_apbtday')	{rStartday=rApbtday;} 
		else if(target=='cntrt_cnclsnday')	{rStartday=rCnclsnday;}
		
		else if(target=='signday')	{rStartday=rSignday;} 
		else if(target=='oppnt_signday')	{rStartday=rOppntSignday;} 
		
		if( rStartday[0]== undefined || rStartday[0] == null  ){
			return;
		}
		
		var vtempday = rStartday[0].value;
		if(vtempday.length==8) 
			rStartday[0].value = vtempday.substring(0,4) + '-' + vtempday.substring(4,6) + '-' + vtempday.substring(6,8);
		
		if( !js_DateCheck(rStartday[0].value, "y-m-d") ){	
			rStartday[0].value = "";
			return;
		}
		 
	
	//시작일-종료일 쌍일때 시작일을 수정했을 경우	
	}else if(target=='srch_start_reg_dt' ||  
			target=='srch_start_reqday' ||  
			target=='srch_start_cnlsnday' ||  
			target=='srch_start_ymd' ||  
			target=='srch_start_dt' ||
			target=='srch_regdt1' ||
			target=='str_org_acptday' ||
			target=='cntrtperiod_startday' ||
			target=='srch_str_org_acptday' ||
			target=='srch_cntrtperiod_startday'
	){
		if(target=='srch_start_reg_dt')	{rStartday=rSregday; rEndday=rEregday;}
		else if(target=='srch_start_reqday')	{rStartday=rSreqday; rEndday=rEreqday;}
		else if(target=='srch_start_cnlsnday') {rStartday=rScnlsnday; rEndday=rEcnlsnday;} 
		else if(target=='srch_start_ymd')	{rStartday=rSymd; rEndday=rEymd;}
		else if(target=='srch_start_dt') {rStartday=rSdt; rEndday=rEdt;}
		else if(target=='srch_regdt1') {rStartday=rSregdt1; rEndday=rEregdt1;}
		else if(target=='str_org_acptday') {rStartday=rSacptday; rEndday=rEacptday;}
		else if(target=='srch_str_org_acptday') {rStartday=rSorgacptday; rEndday=rEorgacptday;}
		else if(target=='srch_cntrtperiod_startday') {rStartday=rSrch_cntrtperiod_startday; rEndday=rSrch_cntrtperiod_endday;} 
		
		if( rStartday[0]== undefined || rStartday[0] == null  ){
			return;
		}
		
		var vtempday = rStartday[0].value;
		if(vtempday.length==8) 
			rStartday[0].value = vtempday.substring(0,4) + '-' + vtempday.substring(4,6) + '-' + vtempday.substring(6,8);
		
		if( !js_DateCheck(rStartday[0].value, "y-m-d") ){	
			rStartday[0].value = "";
			return;
		}else if( !(rEndday[0] == undefined || rEndday[0] == null) && js_DateCheck(rStartday[0].value, "y-m-d") ){
			if((rEndday[0].value).length==10 && rStartday[0].value > rEndday[0].value ){
				if(target=='srch_start_reg_dt' && v_cur_menu_id == '')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_reg_dt' && v_cur_menu_id == '20110803091536762_0000000047')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_reqday')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_cnlsnday')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_ymd' && v_cur_menu_id == '')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_ymd' && v_cur_menu_id == '20110803163201974_0000000117')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_dt' && v_cur_menu_id == '')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_dt' && v_cur_menu_id == '20110803092210347_0000000062')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_dt' && v_cur_menu_id == '20110803092535871_0000000071')
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_dt' && ( v_cur_menu_id == '20110804083152197_0000000154' ||
						v_cur_menu_id == '20110804083441819_0000000160') )
					alert("The date should be before the expiration date.");
				else if(target=='srch_start_dt' && (v_cur_menu_id == '20110804083152685_0000000158' ||
						v_cur_menu_id == '20110804083442204_0000000163' || v_cur_menu_id == 'SECFW_ADMIN_LOGIN_LOG_L'
							|| v_cur_menu_id == 'SECFW_ADMIN_SYSTEM_USE_LOG_L' || v_cur_menu_id == 'SECFW_ADMIN_FILE_LOG_L') )
					alert("The date should be before the expiration date.");
				else if(target=='cntrtperiod_startday')
					alert("The date should be before the expiration date.");
				else if(target=='srch_regdt1')
					alert("The date should be before the expiration date.");
				else if(target=='str_org_acptday')
					alert("The date should be before the expiration date.");
				else if(target=='srch_str_org_acptday')
					alert("The date should be before the expiration date.");
				
				rStartday[0].value = "";
				return;
			}
			
		}	
		 
		
	//시작일-종료일 쌍일때 종료일을 수정했을 경우		
	}else if(target=='srch_end_reg_dt' ||
			target=='srch_end_reqday' ||
			target=='srch_end_cnlsnday' ||
			target=='srch_end_ymd' ||
			target=='srch_end_dt' ||
			target=='srch_regdt2' ||
			target=='end_org_acptday' ||
			target=='cntrtperiod_endday' ||
			target=='srch_end_org_acptday' ||
			target=='srch_cntrtperiod_endday'
				
		
	){
		if(target=='srch_end_reg_dt')	{rStartday=rSregday; rEndday=rEregday;}
		else if(target=='srch_end_reqday')	{rStartday=rSreqday; rEndday=rEreqday;}
		else if(target=='srch_end_cnlsnday') {rStartday=rScnlsnday; rEndday=rEcnlsnday;} 
		else if(target=='srch_end_ymd')	{rStartday=rSymd; rEndday=rEymd;}
		else if(target=='srch_end_dt') {rStartday=rSdt; rEndday=rEdt;}
		else if(target=='srch_regdt2') {rStartday=rSregdt1; rEndday=rEregdt1;}
		else if(target=='end_org_acptday') {rStartday=rSacptday; rEndday=rEacptday;}
		else if(target=='srch_end_org_acptday') {rStartday=rSorgacptday; rEndday=rEorgacptday;}
		else if(target=='srch_cntrtperiod_endday') {rStartday=rSrch_cntrtperiod_startday; rEndday=rSrch_cntrtperiod_endday;} 
		
		if( rEndday[0]== undefined || rEndday[0] == null  ){
			return;
		}
		
		var vtempday = rEndday[0].value;
		if(vtempday.length==8) 
			rEndday[0].value = vtempday.substring(0,4) + '-' + vtempday.substring(4,6) + '-' + vtempday.substring(6,8);
		
		if( !js_DateCheck(rEndday[0].value, "y-m-d") ){	
			rEndday[0].value = "";
			return;
		}else if( !(rStartday[0] == undefined || rStartday[0] == null) && js_DateCheck(rStartday[0].value, "y-m-d") ){
			if(rStartday[0].value > rEndday[0].value ){
				if(target=='srch_end_reg_dt' && v_cur_menu_id == '')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_reg_dt' && v_cur_menu_id == '20110803091536762_0000000047')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_reqday')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_cnlsnday')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_ymd' && v_cur_menu_id == '')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_ymd' && v_cur_menu_id == '20110803163201974_0000000117')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_dt' && v_cur_menu_id == '')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_dt' && v_cur_menu_id == '20110803092210347_0000000062')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_dt' && v_cur_menu_id == '20110803092535871_0000000071')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_dt' && ( v_cur_menu_id == '20110804083152197_0000000154' ||
						v_cur_menu_id == '20110804083441819_0000000160') )
					alert("The date should be after the starting date.");
				else if(target=='srch_end_dt' && (v_cur_menu_id == '20110804083152685_0000000158' ||
						v_cur_menu_id == '20110804083442204_0000000163' || v_cur_menu_id == 'SECFW_ADMIN_LOGIN_LOG_L'
							|| v_cur_menu_id == 'SECFW_ADMIN_SYSTEM_USE_LOG_L' || v_cur_menu_id == 'SECFW_ADMIN_FILE_LOG_L') )
					alert("The date should be after the starting date.");
				else if(target=='cntrtperiod_endday')
					alert("The date should be after the starting date.");
				else if(target=='srch_regdt2')
					alert("The date should be after the starting date.");
				else if(target=='end_org_acptday')
					alert("The date should be after the starting date.");
				else if(target=='srch_end_org_acptday')
					alert("The date should be after the starting date.");
				 
				rEndday[0].value = "";
				return;
			}
		}
		/* 계약 만료일에 따른 만료사전알림일 자동 세팅 제거 (2013.05.24 jnam) 
		var rObj = document.getElementsByName('notiday_before'); 
		if( !(rObj== undefined || rObj == null || rObj[0] == undefined || rObj[0] == null) ){ 
			if(rObj[0].checked){
				getBeforeDay('30');
			}else if(rObj[1].checked){
				getBeforeDay('60');
			}else{
				getBeforeDay('0');
			}
		}
		*/
		 
	}
}

function addHover(id,target){
	$(function(){		
		$("#" + id + "_cal div[value]").hover(function(){
			$(this).addClass("onDay");
		},function(){
			$(this).removeClass("onDay");
		}).click(function(e){
			$("#" + target).attr("value", $(this).attr("value"));
			userFuncProc(target);
			$("#" + target+"_layer").hide();
			$("#" + id).hide();
			$("#" + id + " .selDay").removeClass("selDay");
			$(this).addClass("selDay");						
		});				
		
		$("#" + id + "_cal [day='0']").addClass("daySun");
		$("#" + id + "_cal [day='6']").addClass("daySat");
		for(var i=1;i<6;i++) $("#" + id + "_cal [day='" + i + "']").addClass("dayEv");
		$("#" + id + "_cal [value='"+$("#"+target).val() +"']").addClass("selDay");
		$("#" + id + "_cal [value='"+ todayStr + "']").addClass("dayToday");
		
		//기존 id의 창 크기 구하고 layer에 반영 
		//각 주라인수가 변경됨에 따라 높이가 달라짐 
		var layerWidth = $("#" + id).width()+ 4;
		var layerHeight = $("#" + id).height() + 4;
		$("#" + target + "_layer").attr("width", layerWidth);
		$("#" + target + "_layer").attr("height", layerHeight);
		$("#" + target + "_frm").attr("width", layerWidth);
		$("#" + target + "_frm").attr("height", layerHeight);
	});
}

function changeCal(id,target){
	$("#" + id + "_cal").html(makeDate( $("#" + id + "_calYear").val() , ($("#" + id + "_calMon").val()-1) , id ));
	addHover(id,target);
}

function changeToday(id,target){
	var targetVal = $("#" + target).val();					
	var today = dateStr(new Date(),2);
	
	//값이 있는지부터 확인
	if( targetVal.replace(/ /g,"") != "" ){
		today = targetVal.split(date_split_format);
		if( today.length != 3 || today[0].length != 4 || today[1].length != 2 || today[2].length != 2){
			today =  dateStr(new Date(),2);	
		}
	}
		
	$("#" + id + "_cal").html(makeDate(today[0],eval(today[1]-1)),id);
	$("#" + id + "_calMon").attr("value",eval(today[1]));
	$("#" + id + "_calYear").attr("value",today[0]);
	addHover(id,target);
}

function makeCal(id , tValue)
{	 
	 var cal_html = "";	 
	 var tDate = tValue.split(date_split_format);
	 
	 if ((tDate[0] % 4 == 0 )&& (tDate[0] % 100 != 0) || (Date[0] % 400 == 0)) mon[1] = 29;
	
	 cal_html += "<table width='100%'><tr><td><div class='divHead'><table class='calCss1'><tr><td id='"+ id +"_left' class='divHeadLeft'>◀</td><td class='divHeadCenter'><select id='"+id+"_calYear'>";				 
	
	 if( max_year == 0) max_year = tDate[0] ;
	 for(var i=min_year;i<=max_year;i++){	cal_html += "<option value='"+i+"'>" + i + "</option>"; }
	 cal_html += "</select>&nbsp;<select id='"+id+"_calMon' style='width:50px'>";
	 var v_mon_arr = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
	 for(var i=1;i<13;i++){
		cal_html += "<option value='"+ i +"'>" + v_mon_arr[i-1] +"</option>"; 
	 }
	 cal_html += "</select></td><td id='"+ id +"_right' class='divHeadRigth'>▶</td></tr></table></div></td></tr><tr><td><div id='" + id + "_cal" + "'>";			
	 
	 cal_html += makeDate(eval(tDate[0]),eval(tDate[1]-1),id);	
	 
	 // 하단 today 
	 if( today_show == true ){
	 	 cal_html +="</tr></table></div><div align='right' id='"+id+"tBtn'><span id='"+id+"tBox'></span></div></table>"
	 }else{
	 	 cal_html +="</tr></table></div></table>";
	 }
	 
	 return cal_html;	 
}

function makeDate(year,month,id){
	var today;
	var cal_html = "<table class='calCss'><tr>";
  
  // 윤년계산
  if ((year % 4 == 0 )&& (year % 100 != 0) || (year % 400 == 0)) mon[1] = 29;
	
	// day of the week 
	for (i=start_day;i<eval(start_day)+7;i++){ cal_html += "<td align='center'><font size=1>"+yoil[i]+"</font>";	}

	day = new Date(year,month,1);
	if (day == "NaN" ){
		day = new Date();
	}

	// before
	if (day.getDay() != start_day) {
	  var startDay = day.getMonth() == 0 ? 0: eval(day.getMonth()-1);
		var maxDay   = day.getDay()== 0 ? 6: eval(day.getDay()-start_day);
								
		cal_html +="<tr>";
	  for (i=maxDay;i>0;i--){
	   		cal_html +="<td><div value='" + dateStr(new Date(year,eval(day.getMonth()-1),(mon[startDay] - (i-1))),1)+"' class = 'dayOther'>" + (mon[startDay] - (i-1))+ "</div></td>";
	 	}
	}	
	
	// day
	for (i=1;i<=mon[day.getMonth()];i++){		
		today 		= new Date(year,month,i); // 오늘 날짜를 얻음 -> 요일을 알기 위해서.
		// 일요일이면 다음 줄로 넘어감.
		if (today.getDay() == start_day){ cal_html +="<tr>"; }
		cal_html += "<td><div value='" + dateStr(today,1) +"' day='" + today.getDay() +"' >" + i + "</div></td>";
	}
	
	 // next	 
	 if(((start_day == 0) && today.getDay() != 6) || (start_day == 1) && today.getDay() != 0){	 	 
		 var next_day = 1;		 
		 for (i=today.getDay();i<6+eval(start_day);i++){		 			 
			 cal_html +="<td><div value='" + dateStr(new Date(year,eval(day.getMonth()+1),next_day),1)+"' class='dayOther'>" + next_day++ + "</div></td>";		 
		 }	
	 }	 
	 
	 return cal_html;
}

function moveDate(id,plus){	
	var month = eval($("#" + id + "_calMon").val());
	var year  = eval($("#" + id + "_calYear").val());
	month += plus;
	
	if( month == 0){
		month = 12;
		year  += plus;
	}else if(month == 13){
		month = 1;
		year  += plus;
	}
	
	if( max_year == 0) max_year = todayStr.split(date_split_format)[0] ;
	if( year < min_year || year > max_year ) return;

	$("#" + id + "_calMon").attr("value",month);
	$("#" + id + "_calYear").attr("value",year);
	$("#" + id + "_cal").html(makeDate(year,month-1),id);
}

	
function dateStr( obj, returnType){
	var y = obj.getFullYear();
	var m = obj.getMonth()+ 1 + "";
	var d = obj.getDate() + "";
	
	if(m.length == 1) m = "0" + m;
	if(d.length == 1) d = "0" + d;
	
	var returnValue = y+ date_split_format + m + date_split_format +d; 
	
	if( returnType == "1"){
		return returnValue;
	}else if( returnType == "2"){
		return 	returnValue.split(date_split_format);	
	}
}

function changeToday1(id,target){
	var today =  dateStr(new Date(),2);
		
	$("#" + id + "_cal").html(makeDate(today[0],eval(today[1]-1)),id);
	$("#" + id + "_calMon").attr("value",eval(today[1]));
	$("#" + id + "_calYear").attr("value",today[0]);
	addHover(id,target);
}

//숫자로되어있는값 날짜형식체크[types:y,m,d,ym,md,ymd,y-m-d]
function js_DateCheck(val, types) {
    try {
        var yearfrom = 1899;
        var yearto = 2101;
        if (val == null || val == '' || val == 'undefined')
            return false;
        else {
            switch (types.toLowerCase()) {
                case 'y': //년
                    var tmp = parseInt(val);
                    if (!(tmp > yearfrom && tmp < yearto))
                        return false;
                    else return true;
                case 'm': //월
                    var tmp = parseInt(val);
                    if (!(tmp2 > 0 && tmp2 < 13))
                        return false;
                    else return true;
                case 'd': //일
                    var tmp = parseInt(val);
                    if (!(tmp > 0 && tmp < 32))
                        return false;
                    else return true;
                case 'ym': //년월
                    var tmp = parseInt(val.substring(0, 4));
                    var tmp2 = parseInt(val.substring(4, 6));
                    if ((tmp > yearfrom && tmp < yearto) && (tmp2 > 0 && tmp2 < 13))
                        return true;
                    else return false;
                case 'md': //월일
                    var tmp = parseInt(val.substring(0, 2));
                    var tmp2 = parseInt(val.substring(2, 4));
                    if ((tmp > 0 && tmp < 13) && (tmp2 > 0 && tmp2 < 32))
                        return true;
                    else return false;
                case 'y-m-d' :
                	var val2 = val.replace('-','');
                    val = val.length==10 && val.indexOf('-')==4 && val2.indexOf('-')==6 ? val2.replace('-','') : 'NON';
                    if(val == 'NON') return false;
                case 'ymd': //년월일
                    var tmp = parseInt(val.substring(0, 4));
                    var tmp2 = val.substring(4, 5)=='0' ? parseInt(val.substring(5, 6)) : parseInt(val.substring(4, 6));
                    var tmp3 = val.substring(6, 7)=='0' ? parseInt(val.substring(7, 8)) : parseInt(val.substring(6, 8));
                  
                    if (tmp2 == 2) {
                        if ((tmp % 4) == 0) {
                            if ((tmp > yearfrom && tmp < yearto) && (tmp2 > 0 && tmp2 < 13) && (tmp3 > 0 && tmp3 < 30))
                                return true;
                            else return false;
                        }
                        else {
                            if ((tmp > yearfrom && tmp < yearto) && (tmp2 > 0 && tmp2 < 13) && (tmp3 > 0 && tmp3 < 29))
                                return true;
                            else return false;
                        }
                    }
                    else if (tmp2 == 4 || tmp2 == 6 || tmp2 == 9 || tmp2 == 11) {
                        if ((tmp > yearfrom && tmp < yearto) && (tmp2 > 0 && tmp2 < 13) && (tmp3 > 0 && tmp3 < 31))
                            return true;
                        else return false;
                    }
                    else {
                        if ((tmp > yearfrom && tmp < yearto) && (tmp2 > 0 && tmp2 < 13) && (tmp3 > 0 && tmp3 < 32))
                            return true;
                        else return false;
                    }
            }
        }
    }
    catch (e) {
        return false;
    }
} 