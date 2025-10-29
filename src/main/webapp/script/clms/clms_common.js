var MAP_XML_ROOT = "DATA";
var XML_NEW_LINE = true;
var ENTITY = new Array("&", "<", ">", '"');
var ENTITY_REF = new Array("&amp;", "&lt;", "&gt;", "&quot;");

// java api 의 Vector 기능을 한다 (Vector class)
function Vector() {
	this.values = new Array();
	this.count = 0;
	
	this.checkIndex = Vector_checkIndex;
	this.add = Vector_add;
	this.contains = Vector_contains;
	this.get = Vector_get;
	this.remove = Vector_remove;
	this.clear = Vector_clear;
	this.set = Vector_set;
	this.size = Vector_size;
	this.getValues = Vector_getValues;
	this.toString = Vector_toString;
}

// check index method
function Vector_checkIndex(index) {
	if (index < 0 || index >= this.count) {
		alert("index is out of bounds");
		return false;
	}
	
	return true;
}

// add method
function Vector_add(value) {
	this.values[this.count++] = value;
}

// contains method
function Vector_contains(value) {
	for (var i = 0; i < this.values.length; i++) {
		if (this.values[i] == value) {
			return true;
		}
	}
	
	return false;
}

// get method
function Vector_get(index) {
	if (!this.checkIndex(index)) {
		return null;
	}
	
	return this.values[index];
}

// remove method
function Vector_remove(index) {
	if (!this.checkIndex(index)) {
		return;
	}else {
		var tmpValues = new Array();
		var i = 0;
		
		for (var j = 0; j < this.count; j++) {
			if (j != index) {
				tmpValues[i++] = this.values[j];
			}
		}
		
		this.count--;
		this.values = tmpValues;
	}
}

// clear method
function Vector_clear() {
	if (this.count > 0) {
		this.values = new Array();
		this.count = 0;
	}
}

// set method
function Vector_set(index, value) {
	if (!this.checkIndex(index)) {
		return;
	}
	
	this.values[index] = value;
}

// size method
function Vector_size() {
	return this.count;
}

// getValues method
function Vector_getValues() {
	return this.values;
}

// toString method
function Vector_toString() {
	var str = "";
	
	for (var i = 0; i < this.count; i++) {
		str += this.values[i];
	}
	
	return str;
}

// java api 의  Map 기능을 한다 (Map class)
function Map(data) {
	this.keys = new Vector();
	this.values = new Vector();
	
	this.indexOf = Map_indexOf;
	this.containsKey = Map_containsKey;
	this.get = Map_get;
	this.getKeys = Map_getKeys;
	this.getValues = Map_getValues;
	this.remove = Map_remove;
	this.clear = Map_clear;
	this.size = Map_size;
	this.put = Map_put;
	this.xmlToMap = Map_xmlToMap;
	this.toString = Map_toString;
	this.mapToForm = Map_mapToForm;
}

// indexOf method
function Map_indexOf(key) {
	for (var i = 0; i < this.keys.size(); i++) {
		if (this.keys.get(i) == key) {
			return i;
		}
	}
	
	return -1;
}

// containsKey method
function Map_containsKey(key) {
	return this.indexOf(key) >= 0;
}

// get method
function Map_get(key) {
	var index = this.indexOf(key);
	
	if (index < 0) {
		return null;
	}
	
	return this.values.get(index);
}

// getKeys method
function Map_getKeys() {
	return this.keys.getValues();
}

// getValues method
function Map_getValues() {
	return this.values.getValues();
}

// remove method
function Map_remove(key) {
	var index = this.indexOf(key);
	
	if (index >= 0) {
		this.keys.remove(index);
		this.values.remove(index);
	}
}

// clear method
function Map_clear() {
	if (this.size() > 0) {
		this.keys.clear();
		this.values.clear();
	}
}

// size method
function Map_size() {
	return this.keys.size();
}

// put method
function Map_put(key, value) {
	var index = this.indexOf(key);
	
	if (index < 0) {
		this.keys.add(key);
		this.values.add(value);
	}else {
		this.keys.set(index, key);
		this.values.set(index, value);
	}
}

// xmlToMap method
function Map_xmlToMap(xml) {
	//var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	var xmlDoc = new ActiveXObject("Msxml2.FreeThreadedDOMDocument.3.0"); 
	xmlDoc.async = false;
	xmlDoc.loadXML(xml);
	
	var keyNodes = xmlDoc.selectNodes("/*/*");
	
	for (var i = 0; i < keyNodes.length; i++) {
		this.put(keyNodes.item(i).nodeName, keyNodes.item(i).nodeTypedValue);
	}
}

// toString method
function Map_toString(newLine, root) {
	root = typeof(root) == "undefined" ? MAP_XML_ROOT : root;
	newLine = isBoolean(newLine) ? newLine : XML_NEW_LINE;
	var str = "<" + root + ">" + (newLine ? "\n" : "");
	
	var key = null;
	var value = null;
	
	for (var i = 0; i < this.keys.size(); i++) {
		key = this.keys.get(i);
		value = this.values.get(i);
		
		if (value != "") {
		      str += "<" + key + ">" + translate(value) + "</" + key + ">" + (newLine ? "\n" : "");
		}
	}
	str += "</" + root + ">" + (newLine ? "\n" : "");
	
	return str;
}

// mapToForm
function Map_mapToForm(form) {
	var elmtAry = form.elements;
	
	for (var i = 0; i < elmtAry.length; i++) {
		if (!this.containsKey(elmtAry[i].name)) {
			continue;
		}
		
		if (isInput(elmtAry[i])) {
			//alert('name : ' + elmtAry[i].name + '  value : ' + this.get(elmtAry[i].name));
			elmtAry[i].value = this.get(elmtAry[i].name);
		}else if (isSelect(elmtAry[i])) {
			var optAry = elmtAry[i].options;
			
			for (var j = 0; j < optAry.length; j++) {
				if (optAry[j].value == this.get(elmtAry[i].name)) {
					elmtAry[i].selectedIndex = j;
					break;
				}
			}
		}
	}
}

// type 이 boolean 형인지 체크
function isBoolean(val) {
	return typeof(val) == "boolean";
}

// text input 여부 반환 
function isTextInput(inputObj) {
	return inputObj.type == "text";
}

// hidden input 여부반환
function isHiddenInput(inputObj) {
	return inputObj.type == "hidden";
}

// password input 여부반환
function isPasswordInput(inputObj) {
	return inputObj.type == "password";
}

// input 여부반환
function isInput(obj) {
	return isTextInput(obj) || isHiddenInput(obj) || isPasswordInput(obj); 
}

// select 여부반환
function isSelect(obj) {
	return obj.type.indexOf("select") >= 0;
}

// radio 여부반환
function isRadio(obj) {
	return obj.type = "radio";
}

// 문자열을 변환한다
function translate(str) {
	str = '' + str;
	
	for (var i = 0; i < ENTITY.length; i++) {
		str = replace(str, ENTITY[i], ENTITY_REF[i]);
	}
	
	return str;
}

// 문자열안의 특정 단어 교체
function replace(str, before, after) {
	var find = 0;
	var from = 0;
	var result = '';
	
	while ((find = str.indexOf(before, from)) >= 0) {
		result += str.substring(from, find) + after;
		from = find + before.length;
	}
	
	result += str.substring(from);
	return result;
}

// code 성 selectbox 를 생성한다
function createCodeSelectBox(selObjId, data, tagAry) {
	if (tagAry == null || typeof(tagAry) == "undefined" || tagAry.length != 2) {
		alert("tagAry parameter 정보를 확인하세요 .\n tarAry parameter 는 배열로 2 사이즈이어야만 합니다");
		return;
	}
	/*
	var keyVt = new Vector();  // key vector
	var valVt = new Vector();  // value vector
	var html = "";
	
	for (var i = 0; i < data.length; i++) {
		keyVt.add(data[i].code);
	}
	
	for (var i = 0; i < data.length; i++) {
		valVt.add(data[i].code_dscr);
	}
	
	for (var i = 0; i < keyVt.size(); i++) {
		html += "<option value=" + keyVt.get(i) + ">" + valVt.get(i) + "</option>" 
	}
	*/
	
	var html = "";
	$.each(data, function (entryIndex, entry) {
		html +="<option value=" + entry[tagAry[0]] + ">" + entry[tagAry[1]] + "</option>";
	});
	
	$("#" + selObjId).append(html);
}

// code 성 selectbox 를 생성한다
function createCodeSelectBox2(selObjId, data) {
	$("#" + selObjId).append(data[0].selectValue);
}

// id 에 해당되는 Object 를 반환한다
function getElementById(selObjId){
	var Obj = document.getElementById(selObjId);
	return Obj;
}

// 대분류에 대한 하위 코드 selectbox 를 생성한다	getCodeSelectByUpCd(this, this.value, '', 'Y', 'ko', 'A')
/*
function getCodeSelectByUpCd(formId, selObjId, gbn, upCd, defaultCd, delYn, localeVal, abbrVal) {
	alert("1");

	var options = {
			url: "/cpis/cpCom.do?method=getComboHTML&gbn=" + gbn + "&up_cd=" + upCd + "&del_yn=" + delYn + "&locale=" + localeVal + "&abbr=" + abbrVal,
			type: "post",
			dataType: "json",
			async: false,
			success: function (data) {
				$("#" + selObjId).html(data[0].selectValue);
				//selObjId.innerHTML = data[0].selectValue;
			}
	}
	
	$("#" + formId).ajaxSubmit(options);
}
*/

// 공통코드 / CP분야코드 / 지역코드 / 제품유형코드 / 자가진단분류코드에 대한 selectbox 생성  
// searchUrl 에 gbn, upCd, defaultCd, delYn, localeVal, abbrVal 포함 생성
// 공통코드 : /cpis/cpCom.do?method=listComCdByGrpCd&sys_cd=CPIS&grp_cd=C08
// 그          외 : /cpis/cpCom.do?method=getComboHTML&gbn=CATE&up_cd=TOP&defaultCd=&del_yn=N&localeVal=ko&abbr=A
function getCodeSelectByUpCd(formId, selObjId, searchUrl, rowIndex) {
	var html = "";
	var options = {
			url: searchUrl,
			type: "post",
			//dataType: "json",
			async: false,
			success: function (data) {				
				if (rowIndex != null) {
					var objAry = document.getElementsByName(selObjId);
					
					for (var i = 0; i < objAry.length; i++) {
						objAry[i].id = selObjId + i;
					}
					
					$("#" + selObjId + rowIndex).html(data);
				}else {
					$("#" + selObjId).html(data);
				}
			}
	};
	$("#" + formId).ajaxSubmit(options);
}
/**
 * jQuery ajax
 * param	formId : Form ID, selObjId : select ID, searchUrl : url
 * 작성자 : 신승완
 */
function getCodeSelectByUpCd2(formId, selObjId, searchUrl) {
	var options = {
		url: searchUrl,
		type: "post",
		async: false,
		error: function(e){
			$('#'+selObjId).html("<option value='' selected>-- 전체 --</option>");
		},
		success: function (data) {
			$('#'+selObjId).html("");
			$('#'+selObjId).html(data);			
		}
	}
	$("#" + formId).ajaxSubmit(options);
}

/*********************************************
 * 매뉴얼 트리 관련 스크립트
 * *******************************************/
 var larCate = '';
 function larCateClick(treeBook, cateNo, manExistYn, catePath, cateLevel, obj)
 {
 	// added by tiger380
 	jsCateNo = cateNo;
 	jsManExistYn = manExistYn;
 	jsCatePath = catePath;
 	jsCateLevel = cateLevel;
 	
 	if (treeBook != null && typeof(treeBook) != "undefined") {
	 	if (treeBook.style.display == "block") {
	 		treeBook.style.display = "none";
	 	}else {
	 		treeBook.style.display = "block";
	 	}
 	}
 	
 	$('*').attr('class', function(index) {
 		if (this != null && typeof(this) != "undefined") {
 			var clsNm = this.className;
 			
 			if (clsNm == "deco") {
 				return "";
 			}
 			if (clsNm == "treeSetOver") {
 				return "treeSet";
 			}
 		}
 	});
 	
 	if (obj != null && typeof(obj) != "undefined") {
 		obj.className = "treeSetOver";
 	}
 	
 	/*
   if( larCate != treeBook ) {
     if( larCate !='' ) {
       larCate.style.display = 'none';
     }
     treeBook.style.display = 'block';
     larCate = treeBook;

   } else {
     treeBook.style.display = 'none';
     larCate = '';
   }
   */
   
   // added by tiger380
 }
 
 var midCate = '';
 var midImg = '';
 function midCateClick(treeNote, cateNo, manExistYn, catePath, cateLevel, obj)
 {
 	// added by tiger380
 	jsCateNo = cateNo;
 	jsManExistYn = manExistYn;
 	jsCatePath = catePath;
 	jsCateLevel = cateLevel;

 	if (treeNote != null && typeof(treeNote) != "undefined") {
	 	if (treeNote.style.display == "block") {
	 		treeNote.style.display = "none";
	 	}else {
	 		treeNote.style.display = "block";
	 	}
 	}
 	
 	$('*').attr('class', function(index) {
 		if (this != null && typeof(this) != "undefined") {
 			var clsNm = this.className;
 			
 			if (clsNm == "deco") {
 				return "";
 			}
 			if (clsNm == "treeSetOver") {
 				return "treeSet";
 			}
 		}
 	});
 	
 	if (obj != null && typeof(obj) != "undefined") {
 		obj.className = "deco";	
 	}
 	
 	/*
   if( midCate != treeNote ) {
     if( midCate !='' ) {
       midCate.style.display = 'none';
     }
     treeNote.style.display = 'block';
     midCate = treeNote;

   } else {
     treeNote.style.display = 'none';
     midCate = '';
   }
   */
 }
 
 var smlFont = ''
 function smlCateClick(obj, cateNo, manExistYn, cateLevel){
 	// added by tiger380
 	jsCateNo = cateNo;
 	jsManExistYn = manExistYn;
 	jsCateLevel = cateLevel;
 	
 	$('*').attr('class', function(index) {
 		if (this != null && typeof(this) != "undefined") {
 			var clsNm = this.className;
 			
 			if (clsNm == "deco") {
 				return "";
 			}
 			if (clsNm == "treeSetOver") {
 				return "treeSet";
 			}
 		}
 	});
 	
 	if (obj != null && typeof(obj) != "undefined") {
 		obj.className = "deco";
 	}
 }
 
 function frameSizeMnaView(sizeValue) {
		if (sizeValue == 'S') {
			parent.frMnaViewLeft.cols = '17,*';
		}
		else if (sizeValue == 'L') {
			parent.frMnaViewLeft.cols = '280,*';
		}
	}
 
 function frameSizeMnaMng(sizeValue) {
	if (sizeValue == 'S') {
		parent.frMnaLeft.cols = '17,*';
	}
	else if (sizeValue == 'L') {
		parent.frMnaLeft.cols = '280,*';
	}
} 
 
//달력날자 숫자로 리턴
function getYYYYMMDD( val )
{
	if(val == null) {
		return "";
	}

	while(val.indexOf("-") > -1){
		val = val.replace("-", "");
	}
	return parseInt(val, 10);
}
 
//오늘날짜 리턴 ex)20100611 (임선애)
function getToday(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();	
	if(("" + month).length == 1){ month="0" + month; }
	if(("" + day).length == 1) { day="0" + day; }
	var today = year + month + day;
	return today; 
}

//validation 숫자형식체크
function isNumber(n){
	return !isNaN(parseFloat(n)) && isFinite(n);
}

// 현재일로부터 날짜 더하기
// addDiv : day (일수), month(달수), year(년수)
// addValue : +정수 (더하기), -정수(빼기)
function currentAddDate(addDiv, addValue) {
	var dateObj = new Date();

	var yy = dateObj.getFullYear();
	var mm = dateObj.getMonth()+1;
	var dd = dateObj.getDate();

	var month_lastday = "";

	if(addDiv == "day") {
		dd += addValue;
		if(dd > 0) {
			month_lastday = lastday(yy, mm);
			while(dd > month_lastday) {
				dd -= month_lastday;
				mm += 1;
				if(mm > 12) {
					mm = 1;
					yy += 1;
				}
				month_lastday = lastday(yy, mm);
			}
		} else {
			while(dd < 1) {
				mm -= 1;
				if(mm < 1) {
					mm = 12;
					yy -= 1;
				}
				month_lastday = lastday(yy, mm);
				dd = month_lastday + dd;
			}
		}
	} else if(addDiv == "month") {
		if(addValue > 0) {
			if((12-mm) < addValue) {
				yy += parseInt((addValue + mm - 12) / 12) + 1;
				mm = ((addValue + mm - 12) % 12);
			} else {
				mm += addValue;
			}
		} else {
			if(mm < Math.abs(addValue)) {
				yy -= parseInt((Math.abs(addValue) - mm) / 12) + 1;
				mm = 12 - ((Math.abs(addValue) - mm) % 12);
			} else {
				mm -= Math.abs(addValue);
			}
		}
		month_lastday = lastday(yy, mm);
		if(dd > month_lastday) {
			dd = month_lastday;
		}
	} else if(addDiv == "year") {
		yy += addValue;
		if(mm == 2) {
			month_lastday = lastday(yy, mm);
			if(dd > month_lastday) {
				dd = month_lastday;
			}
		}
	}
	var retValue = yy + comLPad("" + mm, '0', 2) + comLPad("" + dd, '0', 2);

	return retValue;	
}

// Month Array 선언
function montharr(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11) {
    this[0] = m0; this[1] = m1; this[2] = m2; this[3] = m3;
    this[4] = m4; this[5] = m5; this[6] = m6; this[7] = m7;
    this[8] = m8; this[9] = m9; this[10] = m10; this[11] = m11;
}

// 해당년월의 마지막날짜 구하기
function lastday(calyear, calmonth) {
    var monthDays = new montharr(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    if (((calyear % 4 == 0) && (calyear % 100 != 0)) || (calyear % 400 == 0)) {
        monthDays[1] = 29;
    }
    var nDays = monthDays[calmonth - 1];
    return nDays;
}

/**
 * select box 범위를 초기화
 * sbObj : selectbox 객체
 * minVal : 시작값
 * maxVal : 종료값
 * selVal : 선택되어져야할 값
 * ex) 년도 select box 시 : initRangeSelectBox(sbObj, 2000, 2020, 2010)
 */
function initRangeSelectBox(sbObj, minVal, maxVal, selVal) {
	 var len = (maxVal + '').length;
	 var optionElmt = null;
	 var val = null;

	 for (var i = minVal; i <= maxVal; i++) {
	   val = padZero(i, len);
	   optionElmt = document.createElement("option");
	   optionElmt.value = val;
	   optionElmt.text = val;
	   sbObj.add(optionElmt);
	 }

	 sbObj.selectedIndex = typeof(selVal) == "undefined" ? 0 : selVal - minVal;
}

/**
 * 0값으로 채운다
 */
function padZero(val, len) {
	 var diffLen = len - (val + '').length;
	 if (diffLen <= 0) {
	    return val;
	 }

	 var zeroStr = '';

	 for (var i = 0; i < diffLen; i++) {
	    zeroStr += '0';
	 }

	 return zeroStr + val;
}
 
//parent checkbox 체크여부에 따라 child checkbox toggle 
 function toggleChildCheckbox(parObj, childObj, offset, size) {
 	if (parObj.checked) {
 		for (var i = offset; i < (offset + size); i++) {
 			childObj[i].checked = true;
 		}
 	}else {
 		for (var i = offset; i < (offset + size); i++) {
 			childObj[i].checked = false;
 		}
 	}
 }

 // child checkbox 에 따라 parent checkbox toggle
 function toggleParentCheckbox(parObj, childObj, offset, size) {
 	var chkCnt = 0;
 	
 	for (var i = offset; i < (offset + size); i++) {
 		if (childObj[i].checked) {
 			++chkCnt;
 		}
 	}
 	
 	chkCnt > 0 ? (parObj.checked = true) : (parObj.checked = false);
 }
 
 // number check
 function chkNumber(obj) {
	    if((event.keyCode<45||(57<event.keyCode&&event.keyCode<96)||event.keyCode>105)&&event.keyCode!=13&&event.keyCode!=8&&event.keyCode!=9&&event.keyCode!=46&&event.keyCode!=190&&event.keyCode!=110&&event.keyCode!=37&&event.keyCode!=39){
	        event.returnValue=false;
	    }else {
	        var str=obj.value;
	        if(str.indexOf(".")!=-1) {
	            if(event.keyCode==190||event.keyCode==110){
	                event.returnValue=false;
	            }
	        }
	    }
	}

/**
 * 시스템공지 팝업
 */
var sysNoticePopup;

function sysNoticePopupOpen(surl, popupwidth, popupheight, bScroll, targetNm) {
	if( popupwidth  > window.screen.width )
		popupwidth = window.screen.width;
	if( popupheight > window.screen.height )
		popupheight = window.screen.height; 
		
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (window.screen.availHeight - 600) / 2;
		Left = (window.screen.availWidth  - 800) / 2;
	} else {
		Top  = (window.screen.availHeight - popupheight)  / 2;
		Left = (window.screen.availWidth  - popupwidth) / 2;
	}

	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	
	popupwidth = parseInt(popupwidth) + 10 ;
	popupheight = parseInt(popupheight) + 29 ;
	
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	
	sysNoticePopup = window.open(surl, targetNm, Future);
	sysNoticePopup.focus();
}

/**
 * 분리자를 이용하여 날짜의 유효성 체크
 * 2010-11-22 -> '-' 를 이용하여 체크
 * param inputDate : 체크할 날짜
 * param point : 년,월,일 분리자
 */
 function dateCheck(inputDate, point, str){
	 var dateElement = new Array(3);
	 var msg = "";
	 if(str == "start"){
		 msg = "시작";
	 }else{
		 msg = "종료";
	 }
	    
	 if(point != ""){
		 var datelength = inputDate.length;
		 dateElement = inputDate.split(point);
		 if(datelength != 10 || dateElement.length != 3 ){
			 alert(msg + "날짜 타입이 잘못되었습니다. (예: 2010" + point + "11" + point + "22)");
			 return false;
		 }
	 }else{
		 dateElement[0] = inputDate.substring(0,4);
		 dateElement[1] = inputDate.substring(4,6);
		 dateElement[2] = inputDate.substring(6,9);
	 }
	 
	 //년도 검사
	 if( !( 1800 <= dateElement[0] && dateElement[0] <= 4000 ) ) {
		 alert(msg + "날짜의 년도 입력이 잘못되었습니다.");
		 return false;
	 }

	 //달 검사
	 if( !( 0 < dateElement[1] &&  dateElement[1] < 13  ) ) {
		 alert(msg + "날짜의 월 입력이 잘못되었습니다.");
		 return false;
	 }

	 // 해당 년도 월의 마지막 날
	 var tempDate = new Date(dateElement[0], dateElement[1], 0);
	 var endDay = tempDate.getDate();

	 //일 검사
	 if( !( 0 < dateElement[2] && dateElement[2] <= endDay ) ) {
		 alert(msg + "날짜의 일 입력이 잘못되었습니다.");
		 return false;
	 }

	 return true;
}
 
function dateCompare(startDate, endDate, point, hrMM) {
	var star_date;
	var end_date;
	var sDate = null;
	var eDate = null;
	
	startDate = replace(startDate, point, "");
	endDate = replace(endDate, point, "");
	
	if (hrMM) {
		start_date = new Array(5);
		end_date = new Array(5);
		
		start_date[0] = startDate.substring(0, 4);		//	년
		start_date[1] = startDate.substring(4, 6);		//	월
		start_date[2] = startDate.substring(6, 8);		//	일
		start_date[3] = startDate.substring(8, 10);		//	시
		start_date[4] = startDate.substring(10, startDate.length);	//	분
		
		end_date[0] = endDate.substring(0, 4);		//	년
		end_date[1] = endDate.substring(4, 6);		//	월
		end_date[2] = endDate.substring(6, 8);		//	일
		end_date[3] = endDate.substring(8, 10);		//	시
		end_date[4] = endDate.substring(10, startDate.length);	//	분
		sDate = new Date(start_date[0], parseInt(start_date[1], 10) - 1, start_date[2], start_date[3], start_date[4]);
		eDate = new Date(end_date[0], parseInt(end_date[1], 10) - 1, end_date[2], end_date[3], end_date[4]);
	}else {
		start_date = new Array(3);
		end_date = new Array(3);
		
		start_date[0] = startDate.substring(0, 4);		//	년
		start_date[1] = startDate.substring(4, 6);		//	월
		start_date[2] = startDate.substring(6, 8);		//	일
		
		end_date[0] = endDate.substring(0, 4);		//	년
		end_date[1] = endDate.substring(4, 6);		//	월
		end_date[2] = endDate.substring(6, 8);		//	일
		
		sDate = new Date(start_date[0], start_date[1], start_date[2]);
		eDate = new Date(end_date[0], end_date[1], end_date[2]);
	}
	
	if (sDate >= eDate) {
		alert("종료날짜 시 분 은 시작날짜 시 분  보다 작습니다");
		return false;
	}
	
	if (eDate <= new Date()) {
		alert("종료날짜 시 분 은 현재시간 보다 커야 합니다");
		return false;
	}
	
	return true;
}

/**
 * 메시지를 Alert 으로 띄운다.
 * @param message
 * @return
 */
function alertMessage(message){
	if(message!=null && message!=""){
		alert(message) ;
	}
}

/**
 * 숫자 세 자리마다 콤마찍기
 * @param n
 * @returns
 */
function commify(n) {
	  var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
	  n += '';                          // 숫자를 문자열로 변환

	  while (reg.test(n))
	    n = n.replace(reg, '$1' + ',' + '$2');

	  return n;
	}

/**
 * 첨부파일창 iframe 높이 자동 조절
 * @param ifr
 * @param frNm
 */
function resizeActsfileiframe(ifr, frNm) {

    ifr.setExpression('height', eval(frNm + '.document.body.scrollHeight')+10);

}


/**
 * 첨부파일 유무에 따른 해당 프레임 삭제
 * @param form Id
 */
function getClmsFilePageCheck(formId){
	var frm = document.getElementById(formId);
	frm.submit();
	
	// 2014-09-08 Kevin commented.
	// unnecessary code.
	/*var options = {
			
		url: "/clms/common/clmsfile.do?method=getClmsFilePageCheck",
		type: "post",
		async: false,
		dataType: "json",
			
		success: function(responseText,returnMessage) {
			if(responseText != null && responseText.length != 0) {
				$.each(responseText, function(entryIndex, entry) {
					//2014-05-08 신성우 파라미터 위치이동. exists와 관계없이 첨부파일업로드 Flah 처리 위해 파라미터 바인딩 필요.
					//초기화
					$('input[name=fileInfoName]').val("");
					$('input[name=fileFrameName]').val("");
					$('input[name=file_bigclsfcn]').val("");
					$('input[name=file_midclsfcn]').val("");
					$('input[name=file_smlclsfcn]').val("");
					$('input[name=ref_key]').val("");
					$('input[name=view_gbn]').val("");
					//세팅
					$('input[name=fileInfoName]').val(entry['fileInfoName']);
					$('input[name=fileFrameName]').val(entry['fileFrameName']);
					$('input[name=file_bigclsfcn]').val(entry['file_bigclsfcn']);
					$('input[name=file_midclsfcn]').val(entry['file_midclsfcn']);
					$('input[name=file_smlclsfcn]').val(entry['file_smlclsfcn']);
					$('input[name=ref_key]').val(entry['ref_key']);
					$('input[name=view_gbn]').val(entry['view_gbn']);
					frm.submit();

					//2014-08-06 Sungwoo added without modify pages
					if(entry['exists']!='Y' & entry['view_gbn']!='modify'){
						// 2014-03-20 Kevin. 첨부 파일 보여주는 화면 화면 디자인은 아래 코드를 통해 첨부 파일의 visibility를 제어하지만, 버그가 있고 코드가 복잡하여 아예 모두 주석처리함.
						// 모든 첨부파일의 종류는 화면에 보여질 것이며 대신 iframe의 height를 조정하여 화면에서 차지하는 공간을 줄임.
						$('#'+entry['fileFrameName']).css("height", "25px");
					}
				});
			}
		}
	};
	$("#"+formId).ajaxSubmit(options);*/

}

/**
 * 표준계약서 등록 시 파일 첨부 확인 절차
 * @param form Id
 */
function getClmsStdFilePageCheck(formId){
	var frm = document.getElementById(formId);

	var options = {
			
		url: "/clms/common/clmsfile.do?method=getClmsFilePageCheck",
		type: "post",
		async: false,
		dataType: "json",
			
		success: function(responseText,returnMessage) {
			if(responseText != null && responseText.length != 0) {
				$.each(responseText, function(entryIndex, entry) {
					
					if(entry['exists']=='Y'){
						if(entry['exists']=='Y'){
							//초기화
							$('input[name=fileInfoName]').val("");
							$('input[name=fileFrameName]').val("");
							$('input[name=file_bigclsfcn]').val("");
							$('input[name=file_midclsfcn]').val("");
							$('input[name=file_smlclsfcn]').val("");
							$('input[name=ref_key]').val("");
							$('input[name=view_gbn]').val("");
							//세팅
							$('input[name=fileInfoName]').val(entry['fileInfoName']);
							$('input[name=fileFrameName]').val(entry['fileFrameName']);
							$('input[name=file_bigclsfcn]').val(entry['file_bigclsfcn']);
							$('input[name=file_midclsfcn]').val(entry['file_midclsfcn']);
							$('input[name=file_smlclsfcn]').val(entry['file_smlclsfcn']);
							$('input[name=ref_key]').val(entry['ref_key']);
							$('input[name=view_gbn]').val(entry['view_gbn']);
							frm.submit();
						}else{
							//UI 변경 시 수정 필요
							if(typeof($('#'+entry['fileFrameName']).parent().parent().contents('th:eq(0)').attr('rowspan'))=="undefined"){

								if($('#'+entry['fileFrameName']).parent().parent().prev().contents('th:eq(0)').attr('rowspan') >= 1){
									if($('#'+entry['fileFrameName']).parent().parent().prev().contents('th:eq(0)').attr('rowspan') == 1){
										
									}else{
										var rowspan = $('#'+entry['fileFrameName']).parent().parent().prev().contents('th:eq(0)').attr('rowspan') -1;
										
										$('#'+entry['fileFrameName']).parent().parent().prev().contents('th:eq(0)').attr('rowspan',rowspan);
									}
								}else if($('#'+entry['fileFrameName']).parent().parent().prev().prev().contents('th:eq(0)').attr('rowspan') > 1){
									var rowspan = $('#'+entry['fileFrameName']).parent().parent().prev().prev().contents('th:eq(0)').attr('rowspan') -1;
									
									$('#'+entry['fileFrameName']).parent().parent().prev().prev().contents('th:eq(0)').attr('rowspan',rowspan);
								}
								$('#'+entry['fileFrameName']).parent().parent().remove();
							}else if(typeof($('#'+entry['fileFrameName']).parent().parent().contents('th:eq(0)').attr('rowspan'))!="undefined"){
								
								if($('#'+entry['fileFrameName']).parent().parent().contents('th:eq(0)').attr('rowspan') > 1){
									//alert("not >1");
									var rowspan = $('#'+entry['fileFrameName']).parent().parent().contents('th:eq(0)').attr('rowspan') -1;
									var thHTML = "<th rowspan="+rowspan+"><spring:message code='clm.page.msg.common.attachment' /></th>";
									$('#'+entry['fileFrameName']).parent().parent().next().contents(':eq(0)').before(thHTML);
									
									$('#'+entry['fileFrameName']).parent().parent().remove();
								}else if($('#'+entry['fileFrameName']).parent().parent().contents('th:eq(0)').attr('rowspan')==1){
									//alert("not ==1");
									$('#'+entry['fileFrameName']).parent().parent().remove();	
								}
							}
						}
					} else {
						alert("Attachment for the selected Contract  does not exist.");  // 김재원 표준계약서 첨부 시 파일이 있는지 체크 하는 것으로 없는 경우에만 표시 됨. 그래서 다국어는 필요 하지 않음.
						return;
					}
					
					
				});
			}
		}
	}
	$("#"+formId).ajaxSubmit(options);
}

//Sungwoo added 2014-09-03 공통 첨부파일 다운로드 위치이동.
function downloadFile(fileID){
	//$("#hidFile_id").val(fileID);
	// 가장 가까운 form을 가져와 submit 한다. 다른 코드에 영향을 미치지 않도록, submit 이후에는 원래 지정되어 있던 action을 다시 지정한다.
	var dest = "/clms/common/clmsfile.do?method=doClmsDownload";
	var closestForm = document.forms[document.forms.length-1];
	//Sungwoo added 2014-09-03 공통 사용을 위해 file_id를 동적 생성처리
	if($("#hidFile_id").length>0){
		$("#hidFile_id").val(fileID);
	}
	else{
		$('<input>').attr('type','hidden').attr('name','file_id').attr('id','hidFile_id').attr('value',fileID).appendTo(closestForm);
	}
	var originDest = $("<a></a>").attr("href", closestForm.action);
	originDest = originDest.pathname+originDest.search;
	closestForm.action = dest;
	closestForm.target = "_self";
	closestForm.submit();
	
	closestForm.action = originDest;
}

//Sungwoo added 2014-09-12 공통으로 위치이동
/**
 * 이력정보
 */
function contractHisList() {
	var options = {
		url: "/clm/manage/consideration.do?method=listContractHis",
		type: "post",
		async: false,
		dataType: "html",
		success: function (data) {
			$("#contractHis-list").html("");
			$("#contractHis-list").html(data);
		}
	};
	$("#frm").ajaxSubmit(options);
}

//Sungwoo added 2014-09-12 공통으로 위치이동
/**
* approval history detail popup
*/
function considerationPreview(mis_id) {
	var frm = document.frm;
	var options = {
		url: "/clm/manage/completion.do?method=detailForPop",
		type : "post",
		async : false,
		dataType : "html",
		success : function(data){
			frm.contents.value = data;
			openDetailPop();
		}
	}
	$("#frm").ajaxSubmit(options);
}

//Sungwoo added 2014-09-15 공통으로 위치이동
function openDetailPop(){
	var frm = document.frm;
	
	PopUpWindowOpen('', "1024", "768", false, "PopUpWindow");
	
	frm.action	= "/clm/manage/completion.do";
	frm.method.value = "forwardDetailPop";
	frm.target   = "PopUpWindow";
	frm.submit();
}

//Sungwoo added 2014-09-15 공통으로 위치이동
//인쇄팝업, Detail 호출 스크립트
function openPrint(mis_d){
	var frm = document.frm;
	var options = {
		url: "/clm/manage/completion.do?method=detailForPop",
		type : "post",
		async : false,
		dataType : "html",
		success : function(data){
			frm.contents.value = data;
			openDetailPop();
		}
	}
	$("#frm").ajaxSubmit(options);
}

//Sungwoo added 2014-09-15 공통으로 위치이동
//Legal Advice 상세 조회(for print)
function goPrint(){
	var frm = document.frm;
	var options = {
		url: "/las/lawconsulting/lawConsult.do?method=forwardPrintDetailLawConsult",
		type : "post",
		async : false,
		dataType : "html",
		success : function(data){
			frm.contents.value = data;
			openDetailPop();
		}
	}
	$("#frm").ajaxSubmit(options);
}

//Sungwoo added 2014-09-23 공통으로 위치이동
//초과된 문자열 자르기
function chkLength(text, vmaxLen){
	var str = text;
	var nchar = str.length;  
	var nmax = vmaxLen;
	var nbyte = 0;  
	var npos = 0;  

	for(var i=0; i< nchar; i++){
		nbyte+=(escape(str.charAt(i)).length > 4 ? 2:1);
		if(nbyte <= nmax) npos = i + 1;
		}

	if(nbyte > nmax){
		str = str.substr(0, npos);
	}
	return str;
}	
