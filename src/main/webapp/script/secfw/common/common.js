/**
상기 프로그램에 대한 저작권을 포함한 지적재산권은 삼성SDS(주)에 있으며, 삼성SDS(주)가 명시적  으로 허용하지 않은 사용, 복사,
변경, 제3자에의 공개, 배포는 엄격히 금지되며, 삼성SDS(주)의 지적 재산권 침해에 해당됩니다.
(Copyright ⓒ 2005 Samsung SDS Co., Ltd. All Rights Reserved| Confidential)

  * 소스명   : common.js
  * 버   전   : 4.0
  * 수정내역
  * No    수정일       수정자    수정내용
  * 01  2005-07-18  김윤태    getYYYYMMDD,chkNumberHyphen 추가
  * 02
  */

/**
설명     : 입력한 날짜가 유효한 날짜인지 체크
파라미터 : 체크할 문자열, 날짜 포맷(YYYYMMDD, YYYY-MM-DD)
리턴값   : 정상인 경우 "", 에러인 경우 해당 에러의 코드(리소스 번들 참고)
*/


function replaceString(sText, str1, str2)
{
    var isFind, ieFind, sTextLength;
    var replacedStr;
    if(sText.indexOf(str1) >=0 )
    {
        isFind = sText.indexOf(str1);
        ieFind = sText.lastIndexOf(str1);
        sTextLength = str1.length ;
        ieFind = (isFind + sTextLength);
        replacedStr = sText.substring(0, isFind) + str2 + sText.substring(ieFind);
    }
    else
    {
        return sText;
    }

    return replacedStr;
}


    var    _natValue   = '0123456789';
    var    _intValue   = '-0123456789';
    var    _floatValue = '-0123456789.';
    var    _ipValue = '0123456789.';
    var    _upperValue = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var    _lowerValue = 'abcdefghijklmnopqrstuvwxyz';
    var    _etcValue   = ' ~`!@#$%%^&*()-_=+\|[{]};:\'\",<.>/?';
    var _dayOfMonth = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

    var IEYES = 0;
    var menufacture = navigator.appName;
    var version = navigator.appVersion;
    var brow = navigator.appName;

    if((0 < brow.indexOf('Explorer'))
      && (version.indexOf('4') >= 0 || version.indexOf('5') > 0
      || version.indexOf('6') > 0 || version.indexOf('7') > 0
      || version.indexOf('8') > 0 || version.indexOf('9') > 0))
    {
         IEYES = 1;
    }

function CheckByte(str)
{
    var i;
    var strLen;
    var strByte;
    strLen = str.length;

    if(IEYES == 1)
    {
        for(i=0, strByte=0;i<strLen;i++)
        {
            if(str.charAt(i) >= ' ' && str.charAt(i) <= '~' )
                strByte++;
            else
                strByte += 2;
        }
        return strByte;
    }
    else
    {
        return strLen;
    }
}

function ltrim(para)
{
    while(para.substring(0,1) == ' ')
        para = para.substring(1, para.length);
    return para;
}
function mtrim(para)
{
    for ( i = 0; i < para.length;)
        if ( para.substring(i,i+1) == ' ' )
            para = para.substring(0, i) + para.substring(i+1, para.length);
        else
            i++;
    return para;
}

function rtrim(para)
{
    while(para.substring(para.length-1, para.length) == ' ')
        para = para.substring(0, para.length-1);
    return para;
}


function montharr(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11)
{
    this[0] = m0; this[1] = m1; this[2] = m2; this[3] = m3;
    this[4] = m4; this[5] = m5; this[6] = m6; this[7] = m7;
    this[8] = m8; this[9] = m9; this[10] = m10; this[11] = m11;
}

function lastday(calyear, calmonth)
{
    var monthDays = new montharr(31, 28, 31, 30, 31, 30, 31, 31,30, 31, 30, 31);
    if (((calyear % 4 == 0) && (calyear % 100 != 0)) || (calyear % 400 == 0))
        monthDays[1] = 29;
    var nDays = monthDays[calmonth - 1];
    return nDays;
}

function IsUpper(value)
{
    var   i;

    for (i = 0; i < _upperValue.length; i++)
        if(value == _upperValue.charAt(i))
            return true;
    return false;
}

function IsLower(value)
{
    var   i;

    for (i = 0; i < _lowerValue.length; i++)
        if(value == _lowerValue.charAt(i))
            return true;
    return false;
}

function IsNat(value)
{
    var   j;

    for (j = 0; j < _natValue.length; j++)
        if(value == _natValue.charAt(j))
            return true;
    return false;
}

function IsInt(value)
{
    var   j;

    for (j = 0; j < _intValue.length; j++)
        if(value == _intValue.charAt(j))
            return true;
    return false;
}

function IsFloat(value)
{
    var   j;

    for (j = 0; j < _floatValue.length; j++)
        if(value == _floatValue.charAt(j))
            return true;
    return false;
}

function IsIP(value)
{
    var   j;

    for (j = 0; j < _ipValue.length; j++)
        if(value == _ipValue.charAt(j))
            return true;
    return false;
}

function IsEtc(value)
{
    var   j;

    for (j = 0; j < _etcValue.length; j++)
        if(value == _etcValue.charAt(j))
            return true;
    return false;
}

function IsTelChar(value)
{
    var   j;

    for(j = 0; j < _intValue.length; j++)
        if(value == _intValue.charAt(j) || value == '-')
            return true;
    return false;
}

function IsTel(tel)
{
    var    i;

    for (i = 0; i < tel.length; i++)
        if(!IsTelChar(tel.charAt(i)))
            return false;
    return true;
}

function IsNatStr(obj)
{
    var     i;
    var     str = null;
    var     tmp = null;

    str = new String(obj.value);

    if (str == null || str.length == 0)
    {
        obj.value = 0;
        return true;
    }
    tmp =  parseInt(obj.value, 10);
    if (isNaN(tmp) == true)
    {
        obj.focus();
        obj.select();
        return false;
    }
    for (i = 0; i < str.length; i++)
        if(!IsNat(str.charAt(i)))
        {
            obj.focus();
            obj.select();
            return false;
        }

    obj.value = tmp;
    return true;
}

function IsIntStr(obj)
{
    var     i;
    var     str = null;
    var     tmp = null;

    str = new String(obj.value);

    if (str == null || str.length == 0)
    {
        obj.value = 0;
        return true;
    }
    tmp =  parseInt(obj.value, 10);
    if (isNaN(tmp) == true)
    {
        obj.focus();
        obj.select();
        return false;
    }
    for (i = 0; i < str.length; i++)
        if(!IsInt(str.charAt(i)))
        {
            obj.focus();
            obj.select();
            return false;
        }

    obj.value = tmp;
    return true;
}

function IsFloatStr(obj)
{
    var    i;
    var    str = null;
    var     tmp = null;

    str = new String(obj.value);

    if (str == null || str.length == 0)
    {
        obj.value = 0;
        return true;
    }
    tmp =  parseFloat(obj.value);

    if (isNaN(tmp) == true)
    {
        obj.focus();
        obj.select();
        return false;
    }
    for (i = 0; i < str.length; i++)
        if(!IsFloat(str.charAt(i)))
        {
            obj.focus();
            obj.select();
            return false;
        }

    obj.value = tmp;
    return true;
}

function IsIPStr(obj)
{
    var    i;
    var    str = null;

    str = new String(obj.value);

    if (str == null || str.length == 0)
    {
        return true;
    }
    for (i = 0; i < str.length; i++)
        if(!IsIP(str.charAt(i)))
        {
            obj.focus();
            obj.select();
            return false;
        }
    return true;
}

function IsLeapYear(year)
{
    if(year % 4 == 0)
    {
        if(year % 100 == 0)
        {
            if(year % 400 == 0)
                return true;
            return false;
        }
        else
            return true;
    }
    return false;
}

function IsLeapYear(year)
{
    if(year % 4 == 0)
    {
        if(year % 100 == 0)
        {
            if(year % 400 == 0)
                return true;
            return false;
        }
        else
            return true;
    }
    return false;
}

function SetDaySelectBox(yearObj, monthObj, dayObj)
{
    var    i, year, month ;

    year = parseInt(yearObj.options[yearObj.selectedIndex].text,10);

    month = parseInt(monthObj.options[monthObj.selectedIndex].text,10);

    if (IsLeapYear(year) == true)
        _dayOfMonth[1] = 29;
    else
        _dayOfMonth[1] = 28;

    dayObj.length = _dayOfMonth[month - 1];

    for (i = 1; i <= dayObj.length; i++)
    {
        dayObj.options[i - 1].text  = (i < 10) ? ('0' + i) : i;
    }
}

function UnComma(input)
{
    var    inputString  = new String;
    var    outputString = new String;
    var    outputNumber = new Number;
    var    counter = 0;

    inputString  = input;
    outputString = '';

    for (counter = 0; counter < inputString.length; counter++)
    {
        outputString += (inputString.charAt(counter) != ',' ?
                         inputString.charAt(counter) : '');
    }
    outputNumber = parseFloat(outputString);
    return (outputNumber);
}

function Comma(input)
{
//	var reg = /(^[+-]?\d+)(\d{3})/;		// 정규식
//	input += '';                        // 숫자를 문자열로 변환
//	while (reg.test(input))
//		input = input.replace(reg, '$1' + ',' + '$2');
//	
//	return input;
	  
    var inputString = new String;
    var outputString = new String;
    var counter = 0;
    var decimalPoint = 0;
    var end =0;

    inputString = input.toString();

    outputString = '';

    decimalPoint = inputString.indexOf('.', 1);
    
    if(decimalPoint == -1)
    {
        end = inputString.length;
        for (counter = 1; counter <= inputString.length; counter++)
        {
        	
            outputString = (counter % 3 == 0  && counter < end ? ',' : '') +
                           inputString.charAt(inputString.length - counter) +
                           outputString;
        }
    }
    else
    {
        end = decimalPoint - (inputString.charAt(0) == '-' ? 1 : 0);
        for (counter = 1; counter <= decimalPoint; counter++)
        {
            outputString = (counter % 3 == 0 && counter < end ? ',' : '') +
                         inputString.charAt(decimalPoint - counter) +
                         outputString;
        }
        for (counter = decimalPoint; counter < decimalPoint + 3; counter++)
        {
            outputString += inputString.charAt(counter);
        }
    }
    return (outputString);
}
function check_int_plus(comp)
{
    var  i;
    var  str = new String(comp.value);

    for( i=0; i<str.length; i++ )
        if( !IsInt( str.charAt(i) ) )
            return false;

    return true;
}

function del_slash(para)
{
    for (i = 0; i < para.length;)
        if (para.substring(i, i + 1) == '/' )
            para = para.substring(0, i) + para.substring(i + 1, para.length);
        else
            i++;
    return para;
}

function add_slash(para)
{
    var str = '';
    if (para.length != 8)
        return para;
    str = para.substring(0,4) + '/';
    str = str + para.substring(4,6) + '/';
    str = str + para.substring(6,8);
    return str;
}

function chkNumber()
{
    if(event.keyCode ==8 ) return;      //backspace
    if(event.keyCode ==9 ) return;      //TAB
    if(event.keyCode ==33 ) return;     //PageUp
    if(event.keyCode ==34 ) return;     //PageDown
    if(event.keyCode ==35 ) return;     //EndKey
    if(event.keyCode ==36 ) return;     //HomeKey
    if(event.keyCode ==46 ) return;     //DeleteKey
    if(event.keyCode ==190 ) return;
    if(event.keyCode ==110 ) return;
    if(event.keyCode >=37 && event.keyCode <= 40 ) return;  //CURSOR KEY
    if((event.keyCode<48)||(event.keyCode>57 && event.keyCode<96)||(event.keyCode>105)){
         event.returnValue=false;
    }
}

function handleEnter (field, event)
{
  var keyCode = event.keyCode ? event.keyCode :
                event.which ? event.which : event.charCode;
  if (keyCode == 13) {
    var i;

    for (i = 0; i < field.form.elements.length; i++)
      if (field == field.form.elements[i])
        break;

    i = (i + 1) % field.form.elements.length;

    field.form.elements[i].focus();
    return false;
  }
  else
    return true;
}

function chkNumberMinus()
{
    if(event.keyCode ==8 ) return;      //backspace
    if(event.keyCode ==9 ) return;      //TAB
    if(event.keyCode ==33 ) return;     //PageUp
    if(event.keyCode ==34 ) return;     //PageDown
    if(event.keyCode ==35 ) return;     //EndKey
    if(event.keyCode ==36 ) return;     //HomeKey
    if(event.keyCode ==46 ) return;     //DeleteKey
    if(event.keyCode == 109 ) return;   //Minus
    if(event.keyCode == 189 ) return;   //Minus
    if(event.keyCode ==190 ) return;
    if(event.keyCode ==110 ) return;
    if(event.keyCode >=37 && event.keyCode <= 40 ) return;  //CURSOR KEY
    if((event.keyCode<48)||(event.keyCode>57 && event.keyCode<96)||(event.keyCode>105)){
         event.returnValue=false;
    }
}

function chkNumberMinusC()
{
    if(event.keyCode ==8 ) return;      //backspace
    if(event.keyCode ==9 ) return;      //TAB
    if(event.keyCode ==33 ) return;     //PageUp
    if(event.keyCode ==34 ) return;     //PageDown
    if(event.keyCode ==35 ) return;     //EndKey
    if(event.keyCode ==36 ) return;     //HomeKey
    if(event.keyCode ==46 ) return;     //DeleteKey
    if(event.keyCode == 109 ) return;   //Minus
    if(event.keyCode == 189 ) return;   //Minus
    if(event.keyCode ==190 ) return;
    if(event.keyCode ==110 ) return;
    if(event.keyCode ==188 ) return;
    if(event.keyCode >=37 && event.keyCode <= 40 ) return;  //CURSOR KEY
    if((event.keyCode<48)||(event.keyCode>57 && event.keyCode<96)||(event.keyCode>105)){
         event.returnValue=false;
    }
}

function chkNumberComma()
{
    if(event.keyCode ==8 ) return;      //backspace
    if(event.keyCode ==9 ) return;      //TAB
    if(event.keyCode ==33 ) return;     //PageUp
    if(event.keyCode ==34 ) return;     //PageDown
    if(event.keyCode ==35 ) return;     //EndKey
    if(event.keyCode ==36 ) return;     //HomeKey
    if(event.keyCode ==46 ) return;     //DeleteKey
    if(event.keyCode ==109 ) return;   //Minus
    if(event.keyCode ==189 ) return;   //Minus
    if(event.keyCode ==188 ) return;
    if(event.keyCode >=37 && event.keyCode <= 40 ) return;  //CURSOR KEY
    if((event.keyCode<48)||(event.keyCode>57 && event.keyCode<96)||(event.keyCode>105)){
         event.returnValue=false;
    }
}


function chkIntNumber()
{
    if(event.keyCode ==8 ) return;      //backspace
    if(event.keyCode ==9 ) return;      //TAB
    if(event.keyCode ==33 ) return;     //PageUp
    if(event.keyCode ==34 ) return;     //PageDown
    if(event.keyCode ==35 ) return;     //EndKey
    if(event.keyCode ==36 ) return;     //HomeKey
    if(event.keyCode ==46 ) return;     //DeleteKey
    if(event.keyCode >=37 && event.keyCode <= 40 ) return;  //CURSOR KEY
    if((event.keyCode<48)||(event.keyCode>57 && event.keyCode<96)||(event.keyCode>105)){
         event.returnValue=false;
    }
}

function lrtrim(src)
{
    var search = 0

    while ( src.charAt(search) == " ")
    {
        search = search + 1
    }

    src = src.substring(search, (src.length))

    search = src.length - 1

    while (src.charAt(search) ==" ")
    {
        search = search - 1
    }

    return src.substring(0, search + 1)
}

function is_dateformat(strDate)
{
      var strMonth = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

      if ( strDate.length != 10 )
      	  return false;

      var YYYY  = eval(strDate.substr(0,4));
      var MM    = eval(strDate.substr(5,2));
      var DD    = eval(strDate.substr(8,2));

      if ( !IsNumeric(YYYY) || !IsNumeric(MM) || !IsNumeric(DD) )
           return false;
		if ( strDate.charAt(4) != '-' || strDate.charAt(7) != '-' )
			  return false

      var MonthDays = (YYYY != 2) ? strMonth[MM-1] : (( YYYY%4==0 && YYYY%100 !=0 || YYYY%400 ==0 ) ? 29:28 );

      if( ( MM <13 && MM >0 && DD >0 && DD <= MonthDays) == false )
      	 return false;

      return true;
}

function replaceStr(str, from, to)
{
        if( str==null || str == "" )
            return "";

	var i = 0;
        var rStr = "";
        while( str.indexOf(from) > -1 )
        {
            i = str.indexOf(from);
            rStr += str.substring(0,i) + to;
            str = str.substring( i+from.length );
        }
	return rStr+str;
}

function BytesToLen(inputText,dbMax) {
	var len = 0;
	var text = inputText;
	var max = dbMax;
	var temp = "";

        if (text.length == 0) {
            return false;
        }else{
            len = 0;
            for (i=0;i < text.length;i++) {
            	temp = "";
            	temp = escape(text.charAt(i));
            	if(temp.length>3){
            	    len+=2;
            	}else{
            	    len++;
            	}

            	if (len > max) {
            	   return false;
            	}
            }
            return true;
	    }
}

function checkByteLen(inputText,dbMax)
{
	var text = inputText;
	var max = dbMax;

    if( CheckByte(text) > dbMax)
    {
        return false;
    }
    else
    {
        return true;
    }
}

function checkStrLen(inputText, MaxLen)
{
	var i, len=0;

	for(i=0 ; i < inputText.length ; i++) (inputText.charCodeAt(i) > 255)? len+=2:len++;

	if (MaxLen < len)
		return false;
	else
		return true;
}

function mouseOut(f){
	if (!f.childNodes[0].childNodes[0].checked) f.className='';
	else f.className='';
}

function mouseOver(f){
	if (f.childNodes[0].childNodes[0].checked){
		f.className='list-over';
	}else {
		f.className='list-over';
	}
}

function chkNumberHyphen()
{
    if(event.keyCode ==8 ) return;      //backspace
    if(event.keyCode ==9 ) return;      //TAB
    if(event.keyCode ==33 ) return;     //PageUp
    if(event.keyCode ==34 ) return;     //PageDown
    if(event.keyCode ==35 ) return;     //EndKey
    if(event.keyCode ==36 ) return;     //HomeKey
    if(event.keyCode ==46 ) return;     //DeleteKey
    if(event.keyCode ==190 ) return;
    if(event.keyCode ==110 ) return;
    if(event.keyCode >=37 && event.keyCode <= 40 ) return;  //CURSOR KEY

    if ( event != null && event.keyCode != 45)
    {
           if ( event.keyCode < 48 || event.keyCode > 57 ) {
             event.returnValue = false;
           }
   }
}

function getYYYYMMDD( val )
{
            if (val == null) {
                return "";
            }

            while (val.indexOf("-") > -1)
                val = val.replace("-", "");

            return val;
}

function checkDigit(strHour)
{
	    if(strHour.length == 1){
	        strHour = "0" + strHour;
	    }
		return strHour;
}

function chkHour(startDate,endDate,startHour,endHour,selIndex, checkType)
{
    var v_FromDate = getYYYYMMDD(startDate.value)+ checkDigit(startHour.value);
    var v_ToDate = getYYYYMMDD(endDate.value)+ checkDigit(endHour.value);

    if(checkType=='S' && selIndex != '0' && selIndex != '23') selIndex = selIndex+1;
    if(checkType=='E' && selIndex != '0' && selIndex != '23') selIndex = selIndex-1;


    // 날짜 선후관계 check
    //alert(v_FromDate);
    //alert(v_ToDate);
    if(  v_FromDate  > v_ToDate)
    {
        if(checkType=='S')
        {
            //alert('S');
            endHour.options[selIndex].selected = true;
            endHour.focus();
        }
        else if(checkType=='E')
        {
        //alert('E');
//            startHour.value = selIndex;
			startHour.options[selIndex].selected = true;
			startHour.focus();
        }
       return;
    }
}

function chkMin(startDate,endDate,startHour,endHour,startMin,endMin,selIndex, checkType)
{
    var v_FromDate = getYYYYMMDD(startDate.value)+ checkDigit(startHour.value) + startMin.value;
    var v_ToDate = getYYYYMMDD(endDate.value)+ checkDigit(endHour.value) + endMin.value;

   //alert(selIndex);
    if(checkType=='S' && selIndex != 0 && selIndex != startMin.options.length) selIndex = selIndex+1;
    if(checkType=='E' && selIndex != 0 && selIndex != endMin.options.length) selIndex = selIndex-1;


    // 날짜 선후관계 check
    //alert(v_FromDate);
    //alert(v_ToDate);
    if(  v_FromDate  > v_ToDate)
    {
        if(checkType=='S')
        {
            //alert('S');
            endMin.options.selectedIndex = selIndex;
            endMin.focus();
        }
        else if(checkType=='E')
        {
        //alert('E');
            startMin.options.selectedIndex = selIndex;
            startMin.focus();
        }
       return;
    }
}

function callUp(voipnumber)
{
	var url = "http://mysingle.wyz070.com/c2d.do?telephone="+voipnumber;
	var winpos = "left="+(window.screen.width/2-162)+",top="+(window.screen.height/2-112);
    var winstyle="width=324,height=224,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=no,copyhistory=no," + winpos;
	var callwin = window.open(url,"voipcaller",winstyle);
	callwin.focus();
}

function sendSMS(voipnumber)
{
	var url = "http://mysingle.wyz070.com/clickToX.do?clickToNumber=" + voipnumber + "&clickToGb=S";
	var winpos = "left="+(window.screen.width/2-162)+",top="+(window.screen.height/2-112);
    var winstyle="width=285,height=310,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=no,copyhistory=no," + winpos;
	var smswin = window.open(url,"pop_sms_extention",winstyle);
	smswin.focus();
}

function callSmsUp(number){
	window.open('http://mysingle.wyz070.com/clickToX.do?clickToNumber='+number+'&clickToGb=S','pop_sms_extention','top=0, left=0, width=285, height=310');		
}		
/* ---------------------------------------------------------------------------------------------------------------------------------------------
숫자여부 체크
--------------------------------------------------------------------------------------------------------------------------------------------- */
function CheckNum(s){
	var anum=/(^\d+$)|(^\d+\.\d+$)/;
	
	if (anum.test(s)) {
		return true;
	} else {
		return false;
	}
}

/* ---------------------------------------------------------------------------------------------------------------------------------------------
IE Flicker Bug위한 Hack
--------------------------------------------------------------------------------------------------------------------------------------------- */
(function(){ 
	/*Use Object Detection to detect IE6*/ 
	var m = document.uniqueID /*IE*/ 
				&& document.compatMode /*>=IE6*/ 
				&& !window.XMLHttpRequest /*<=IE6*/ 
				&& document.execCommand ; 
				
				try{ 
					if (!!m) { 
						m("BackgroundImageCache", false, true) /* = IE6 only */ 
					} 
				} catch(oh) {}; 
})();

/* ---------------------------------------------------------------------------------------------------------------------------------------------
Layer 영역에 포함된 Select Box를 보이지 않게 함.
@param {Object} el    해당 영역의 Select Box를 보이지 않게 해야할 Layer
--------------------------------------------------------------------------------------------------------------------------------------------- */

hiddenSelectBox = function(el) {
	//alert(el);
	var sb = document.all.tags("SELECT");
	var rect = document.getElementById(el).getBoundingClientRect();
	var minX = rect.left;
	var maxX = rect.right;
	var minY = rect.top;
	var maxY = rect.bottom;

	for(var i=0; i<sb.length; i++){
		var target = sb[i].getBoundingClientRect();
		var x = target.left;
		var xx = target.right;
		var y = target.top;
		var yy = target.bottom;
		if((x >= minX && x <= maxX && y >= minY && y <= maxY) || (xx >= minX && xx <= maxX && yy >= minY && yy <= maxY)) {
			sb[i].style.visibility = "hidden";
		}
	}
}

/* ---------------------------------------------------------------------------------------------------------------------------------------------
Layer 영역에 포함된 Select Box를 보이게 함.
--------------------------------------------------------------------------------------------------------------------------------------------- */

showSelectBox = function() {
	var sb = document.all.tags("SELECT");

	for(var i=0; i<sb.length; i++){
		if(sb[i].style.visibility=="hidden") {
			sb[i].style.visibility = "visible";
		}
	}

	showSelectLayer();
}



/********* 임직원 상세보기 팝업 start *************************************************************************************
********** 주의 : 전체메일보내기는 개별적으로 수정해야함.
********** - manage\SetCommMailMaster.jsp
********** - member\SetCommMailMember.jsp
*/

//본문 사이즈 변수
var contentMaxSize = 500;

function viewempinfo(userId)
{
	var winW = 900;
	var winH = 600;
	var winL = (window.screen.availWidth - winW) / 2;
	var winT = (window.screen.availHeight - winH) / 2;
	var addr = "/service/em/EmpController?cmd=EmpInfo&key=epid&keyvalue=" + userId;
	window.open(addr, "EmpInfo", "top=" + winT + ",left=" + winL + ",height=" + winH + ",width="+winW);
}

/********* 임직원 상세보기 팝업 end **************************************************************************************/

// 문장에서 html tag를 없애 준다.
// string : 원래 문자열
// return value : html tag가 없어진 문자열
function stripHTML(string)
{
	var strip = new RegExp();

	strip = /[\r][\n]/gi;
	var retString =  string.replace(strip, "");
	strip = /[<][b][r][>]/gi;
	retString =  retString.replace(strip, "\r\n");
	//strip = /[<][p][>]/gi;
	strip = new RegExp("[<][p][>]", "gi");
	retString =  retString.replace(strip, "");
	//strip = /[<][/][p][>]/gi;
	strip = new RegExp("[<][/][p][>]", "gi");
	retString =  retString.replace(strip, "\r\n");
	strip = /[<][^>]*[>]/gi;
	var retString =  retString.replace(strip, "");
	strip = /[&][a][m][p][;]/gi;
	retString =  retString.replace(strip, "&");
	strip = /[&][l][t][;]/gi;
	retString =  retString.replace(strip, "<");
	strip = /[&][g][t][;]/gi;
	retString =  retString.replace(strip, ">");
	strip = /[&][q][u][o][t][;]/gi;
	retString =  retString.replace(strip, '"');
	strip = /[&][#][0][3][9][;]/gi;
	retString =  retString.replace(strip, "'");
	strip = /[&][n][b][s][p][;]/gi;
	retString =  retString.replace(strip, " ");
	return retString;
}

// text를 html형식으로 변경한다.
function textToHtml(textVal, obj)
{
	var strip = new RegExp();
	var retString = "";
	strip = /[&]/gi;
	retString =  retString.replace(strip, "&amp;");
	strip = /[\r][\n][<][b][r][>]/gi;
	retString =  textVal.replace(strip, "\r\n");
	strip = /[<][b][r][>][\r][\n]/gi;
	retString =  retString.replace(strip, "\r\n");
	strip = /[<][b][r][>]/gi;
	retString =  retString.replace(strip, "\r\n");
	strip = /[<]/gi;
	retString =  retString.replace(strip, "&lt;");
	strip = /[>]/gi;
	retString =  retString.replace(strip, "&gt;");
	strip = /[\r][\n]/gi;
	retString =  retString.replace(strip, "<br>\r\n");
	strip = /["]/gi;
	retString =  retString.replace(strip, '&quot;');
	strip = /[']/gi;
	retString =  retString.replace(strip, "'");
	strip = /[ ]/gi;
	retString =  retString.replace(strip, "&nbsp;");

	if(document.all.txtTextContent!=null){
		document.all.txtTextContent.value = retString;
	}

	return retString;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////// 글로벌 지원 ////////////////////////////////////////////////////////////////////////////////////
function ChangeLocale(sellocal)
{
	curlocal = document.all.orginlocale.value;
	document.all.regLocale.value = curlocal.substring(0,curlocal.indexOf(".")) +"."+ sellocal;
}


function fnMultiLang(Lang, Enc, fontfamily) {
	ChangeLocale(Enc);
	document.all.wec.ActiveTab  = 0; //일단 edit창으로 전환후  header 변경
	document.all.wec.HeadValue = "\n<META http-equiv=Content-Type content='text/html; charset="+ Enc +"'>" +
									   "\n<title>Samsung Enterprise Portal mySingle</title>" +
									   "\n<style> P, td, li {font-family:"+ fontfamily +", arial; font-size:9pt; margin-top:5px;margin-bottom:5px;}</style></HEAD>";
}
//////////// 글로벌 지원 ////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////

function ShowEncodingToolTip(locale)  {

		var drmOptionObject = new Object();
		var scripLocale = locale;
		if(scripLocale.indexOf(".")>0)
			scripLocale = locale.substring(0, locale.indexOf("."));
		else
			scripLocale = "en_US";

		//var MDUrl = "/mail/EPEncodingToolTip_<%=Locale.substring(0, Locale.indexOf("."))%>.htm";
		var MDUrl = "/mail/EPEncodingToolTip_" + scripLocale + ".htm";
		var sFeatures = "dialogWidth:900px;dialogHeight:400px;scroll:on;status:on;resizable:yes;";

		window.showModalDialog(MDUrl, drmOptionObject, sFeatures);
}
    
/* ---------------------------------------------------------------------------------------------------------------------------------------------
Layer Toggle Function

@param { object } el		레이어를 띄우는 Element
@param { top } 레이어의 Top 위치
	- 0 일경우 el.getBoundingClientRect()로 계산
	- + 혹은 - 로 넘어오는 경우는 el.getBoundingClientRect()로 계산하여 그값에 넘어온 값만큼 더하거나 빼줌
@param { left } 레이어의 Left 위치
	- 0 일경우 el.getBoundingClientRect()로 계산
	- + 혹은 - 로 넘어오는 경우는 el.getBoundingClientRect()로 계산하여 그값에 넘어온 값만큼 더하거나 빼줌
@param { layer } 레이어 명
@param { layer } 레이어 index

[활용예]
1. 위치 지정 안할때(A 태그를 기준)
<a href="#" onclick="ToggleLayer(this, 0, 0, 'layer_view', 0)" class="btn_top_view hover_hack"><em><strong><i><span>View</span></i></strong></em></a>

1. 위치 지정 안할때 (A 태그를 기준으로 하여 top: -20px, left: +20px)
<a href="#" onclick="ToggleLayer(this, '-20', '+20', 'layer_view', 0)" class="btn_top_view hover_hack"><em><strong><i><span>View</span></i></strong></em></a>

3. 위치 지정 할때(화면에서 top: 100px, left: 200px)
<a href="#" onclick="ToggleLayer(this, 100, 200, 'layer_view', 0)" class="btn_top_view hover_hack"><em><strong><i><span>View</span></i></strong></em></a>
--------------------------------------------------------------------------------------------------------------------------------------------- */


var layerObj;
var layerClassName = new Array();
var layerIndex = 0;

var layer_zIndex = 7;

function ToggleLayer(el, top, left, layer, index) {

	var layerClassNameCheck = true;

	for ( var i=0; i<layerClassName.length ; i++ )	{
		if( layerClassName[i] == layer ){
			layerClassName = false;
			i = layerClassName.length;
		}
	}

	if( layerClassNameCheck ){
		layerClassName[layerClassName.length] = layer;
	}

	var rect;
	var posTop;
	var posLeft;

	rect = el.getBoundingClientRect();
	if (CheckNum(top)) {
		if (top == 0) {
			posTop = rect.top;			
		} else {
			posTop = top;
		}	
	} else {
//		posTop = rect.top + parseInt(top);
		var curtop = 0;
		if (el.offsetParent) {
			while (el.offsetParent) {
				curtop += el.offsetTop;
				el = el.offsetParent;
			}
		} else if (el.y) curtop += el.y;
		posTop = curtop;
	}

	if (CheckNum(left)) {
		if (left == 0) {
			posLeft = rect.left;
		} else {
			posLeft = left;
		}	
	} else {
		posLeft = rect.left + parseInt(left);
	}
	
	oToggleLayer = document.getElementsByClassName(layer);

	var tmp_iframe_name = layer+"_ifrm_bg";

	//alert( tmp_iframe_name );

	for (var i=0; i<oToggleLayer.length; i++) {

//alert( Element.getStyle(oToggleLayer[i], 'display') );

		if (i == index && oToggleLayer[i].style.display =='none') {
		//if (Element.getStyle(oToggleLayer[i], 'display') == 'none') {

/*
			Element.setStyle(oToggleLayer[i], { top: posTop });
			Element.setStyle(oToggleLayer[i], { left: posLeft });
			Element.setStyle(oToggleLayer[i], { display: 'block' });
			Element.setStyle(oToggleLayer[i], { zIndex: layer_zIndex +1  });
*/
			//-- layer 작동 이후 contents target 을 잃어버리는 문제때문에 변경함.
			oToggleLayer[i].style.top = posTop;
			oToggleLayer[i].style.left = posLeft;
			oToggleLayer[i].style.display = 'block';
			oToggleLayer[i].style.zIndex = (layer_zIndex + 1);

			var oIfrm = document.createElement("IFRAME");

			oIfrm.id = tmp_iframe_name;
			oIfrm.style.position = "absolute";
			oIfrm.style.top = posTop + 30;
			oIfrm.style.left = posLeft;
			oIfrm.style.width = oToggleLayer[i].offsetWidth;
			oIfrm.style.height = oToggleLayer[i].offsetHeight - 30;
			oIfrm.frameBorder = 0;
			oIfrm.marginWidth = 0;
			oIfrm.marginHeight = 0;
			oIfrm.style.zIndex = layer_zIndex ;
			document.body.appendChild(oIfrm);

			layer_zIndex += 2;

		} else {
			oToggleLayer[i].style.display = 'none';
			try{
				document.body.removeChild(document.getElementById(tmp_iframe_name));
			}catch(e){
			}
		}
	}
}



function LayerToggle(el, top, left, layer, index) {

	layerObj		= el;
	layerClassName	= layer;
	layerIndex		= index;

	layerClassName = new Array();

	var layerClassNameCheck = true;

	for ( var i=0; i<layerClassName.length ; i++ )	{
		if( layerClassName[i] == layer ){
			layerClassName = false;
			i = layerClassName.length;
		}
	}

	if( layerClassNameCheck ){
		layerClassName[layerClassName.length] = layer;
	}

	var rect;
	var posTop;
	var posLeft;

	if (top == 0 || left == 0 ) {
		rect = el.getBoundingClientRect();
		posTop = rect.top;
		posLeft = rect.left + 193;
	} else {
		posTop = top;
		posLeft = left + 193;
	}

	if( eval("document.forms[0].isPop") ){
			posLeft -= 193;
			//posLeft += 10;
			//posTop += 10;
	}

	posTop += 1;

	oToggleLayer = document.getElementsByClassName(layer);

	for (var i=0; i<oToggleLayer.length; i++) {
		if (i == index && Element.getStyle(oToggleLayer[i], 'display') == 'none') {

			/*Element.setStyle(oToggleLayer[i], { top: posTop - 1 });
			Element.setStyle(oToggleLayer[i], { left: posLeft });
			Element.setStyle(oToggleLayer[i], { display: 'block' });
			Element.setStyle(oToggleLayer[i], { zIndex: '11' });*/
			//Element.setStyle(oToggleLayer[i], { border: '1px solid red' });

			oToggleLayer[i].style.top = posTop - 1;
			oToggleLayer[i].style.left = posLeft;
			oToggleLayer[i].style.display = 'block';
			oToggleLayer[i].style.zIndex = '11';

			$("ifrm_blank").style.visibility = 'visible';
			$("ifrm_blank").style.top = posTop + 20;
			$("ifrm_blank").style.left = posLeft;
			$("ifrm_blank").width = oToggleLayer[i].offsetWidth;

			$("ifrm_blank").height = oToggleLayer[i].offsetHeight - 20;
			$("ifrm_blank").style.zIndex = 10;
			//$("ifrm_blank").style.border = "1px solid blue";

		} else {
			//Element.setStyle(oToggleLayer[i], { display: 'none' });
			oToggleLayer[i].style.display = 'none';
			$("ifrm_blank").style.visibility = 'hidden';
		}
	}
}

function LayerToggleOff(){
/*
	if( event ) alert("clicked left");
	else alert("clicked contents ");
*/
	var selectedLayer = "";

	if( layerClassName.length == 0 ) {
		return false; 
	}

	for (var k=0 ; k<layerClassName.length ; k++ )	 {

		//alert( layerClassName[k] );
		oToggleLayer = document.getElementsByClassName(layerClassName[k]);
/*
		if( !event ){
			alert( "parent." + layerClassName[k] );
			oToggleLayer = parent.document.getElementsByClassName(layerClassName[k]);
		}
*/
		for (var i=0; i<oToggleLayer.length; i++) {

			//-- 클릭된 지점의 좌표를 계산하여 보내기 레이어를 클릭했으면 return;
			var rect = oToggleLayer[i].getBoundingClientRect();
			var x1 = rect.left;
			var y1 = rect.top;
			var x2 = rect.right;
			var y2 = rect.bottom;

			if( event ) {

				var eventX = event.x;
				var eventY = event.y;

				// alert( eventX +" / " + eventY );

				// alert( x1 +" "+ y1 +" "+ x2 +" "+ y2 +" / "+ eventX +" "+ eventY );

				if( !$('contents') && !$('supp_main') ){

					if( ( eventX >= x1 && eventX <=x2 && eventY >= y1 && eventY <= y2 ) 
						|| ( eventX >= x1 && eventX <=x2 && eventY <= 110 ) 
						|| ( eventX <=100 && eventY <= 100 ) ){
						return false;
					} 
				}
			}

			if (i == layerIndex && oToggleLayer[i].style.display != 'none') {
				//Element.setStyle(oToggleLayer[i], { display: 'none' });
				oToggleLayer[i].style.display = 'none';
				$("ifrm_blank").style.visibility = 'hidden';

				var tmp_iframe_name = layerClassName[k]+"_ifrm_bg";

				var ifrm_layer_bg = document.getElementById( tmp_iframe_name );
				if( ifrm_layer_bg ){
					document.body.removeChild( ifrm_layer_bg );
				}

				selectedLayer = layerClassName[k];
			}
		}
		
	}

	// 클릭하여 display:none 된 layer 를 layer 목록에서 삭제하고
	// 새로운 레이어 목록을 작성
	var tmpLayer = new Array();

	for ( var i=0 ; i<layerClassName.length ; i++ ){

		if( layerClassName[i] != selectedLayer ){
			tmpLayer[tmpLayer.length] = layerClassName[i];
		}
	}

	layerClassName = tmpLayer;
}


// 20070307: 이명민이 추가함.
var realbody = (document.compatMode=="CSS1Compat")? document.documentElement : document.body;
var sUserAgent = navigator.userAgent;
var fAppVersion = parseFloat(navigator.appVersion);
function compareVersions(sVersion1, sVersion2) {
    var aVersion1 = sVersion1.split(".");
    var aVersion2 = sVersion2.split(".");
    
    if (aVersion1.length > aVersion2.length) {
        for (var i=0; i < aVersion1.length - aVersion2.length; i++) {
            aVersion2.push("0");
        }
    } else if (aVersion1.length < aVersion2.length) {
        for (var i=0; i < aVersion2.length - aVersion1.length; i++) {
            aVersion1.push("0");
        }    
    }    
    for (var i=0; i < aVersion1.length; i++) { 
        if (aVersion1[i] < aVersion2[i]) {
            return -1;
        } else if (aVersion1[i] > aVersion2[i]) {
            return 1;
        }    
    }    
    return 0;
}

var isOpera = sUserAgent.indexOf("Opera") > -1;
var isMinOpera4 = isMinOpera5 = isMinOpera6 = isMinOpera7 = isMinOpera7_5 = false;

if (isOpera) {
    var fOperaVersion;
    if(navigator.appName == "Opera") {
        fOperaVersion = fAppVersion;
    } else {
        var reOperaVersion = new RegExp("Opera (\\d+\\.\\d+)");
        reOperaVersion.test(sUserAgent);
        fOperaVersion = parseFloat(RegExp["$1"]);
    }

    isMinOpera4 = fOperaVersion >= 4;
    isMinOpera5 = fOperaVersion >= 5;
    isMinOpera6 = fOperaVersion >= 6;
    isMinOpera7 = fOperaVersion >= 7;
    isMinOpera7_5 = fOperaVersion >= 7.5;
}

var isKHTML = sUserAgent.indexOf("KHTML") > -1 || sUserAgent.indexOf("Konqueror") > -1 || sUserAgent.indexOf("AppleWebKit") > -1;               
var isMinSafari1 = isMinSafari1_2 = false;
var isMinKonq2_2 = isMinKonq3 = isMinKonq3_1 = isMinKonq3_2 = false;

if (isKHTML) {
    isSafari = sUserAgent.indexOf("AppleWebKit") > -1;
    isKonq = sUserAgent.indexOf("Konqueror") > -1;

    if (isSafari) {
        var reAppleWebKit = new RegExp("AppleWebKit\\/(\\d+(?:\\.\\d*)?)");
        reAppleWebKit.test(sUserAgent);
        var fAppleWebKitVersion = parseFloat(RegExp["$1"]);

        isMinSafari1 = fAppleWebKitVersion >= 85;
        isMinSafari1_2 = fAppleWebKitVersion >= 124;
    } else if (isKonq) {

        var reKonq = new RegExp("Konqueror\\/(\\d+(?:\\.\\d+(?:\\.\\d)?)?)");
        reKonq.test(sUserAgent);
        isMinKonq2_2 = compareVersions(RegExp["$1"], "2.2") >= 0;
        isMinKonq3 = compareVersions(RegExp["$1"], "3.0") >= 0;
        isMinKonq3_1 = compareVersions(RegExp["$1"], "3.1") >= 0;
        isMinKonq3_2 = compareVersions(RegExp["$1"], "3.2") >= 0;
    } 
    
}

var isIE = sUserAgent.indexOf("compatible") > -1 && sUserAgent.indexOf("MSIE") > -1 && !isOpera;           
var isMinIE4 = isMinIE5 = isMinIE5_5 = isMinIE6 = isMinIE7 = false;
if (isIE) {
    var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
    reIE.test(sUserAgent);
    var fIEVersion = parseFloat(RegExp["$1"]);

    isMinIE4 = fIEVersion >= 4;
    isMinIE5 = fIEVersion >= 5;
    isMinIE5_5 = fIEVersion >= 5.5;
    isMinIE6 = fIEVersion >= 6.0;
    isMinIE7 = fIEVersion >= 7.0;
}

var isMoz = sUserAgent.indexOf("Gecko") > -1 && !isKHTML;
var isMinMoz1 = sMinMoz1_4 = isMinMoz1_5 = false;

if (isMoz) {
    var reMoz = new RegExp("rv:(\\d+\\.\\d+(?:\\.\\d+)?)");
    reMoz.test(sUserAgent);
    isMinMoz1 = compareVersions(RegExp["$1"], "1.0") >= 0;
    isMinMoz1_4 = compareVersions(RegExp["$1"], "1.4") >= 0;
    isMinMoz1_5 = compareVersions(RegExp["$1"], "1.5") >= 0;
}

var isNS4 = !isIE && !isOpera && !isMoz && !isKHTML && (sUserAgent.indexOf("Mozilla") == 0) && (navigator.appName == "Netscape") && (fAppVersion >= 4.0 && fAppVersion < 5.0);
var isMinNS4 = isMinNS4_5 = isMinNS4_7 = isMinNS4_8 = false;

if (isNS4) {
    isMinNS4 = true;
    isMinNS4_5 = fAppVersion >= 4.5;
    isMinNS4_7 = fAppVersion >= 4.7;
    isMinNS4_8 = fAppVersion >= 4.8;
}

var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh");
var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
var isWin95 = isWin98 = isWinNT4 = isWin2K = isWinME = isWinXP = false;
var isMac68K = isMacPPC = false;
var isSunOS = isMinSunOS4 = isMinSunOS5 = isMinSunOS5_5 = false;

if (isWin) {
    isWin95 = sUserAgent.indexOf("Win95") > -1 || sUserAgent.indexOf("Windows 95") > -1;
    isWin98 = sUserAgent.indexOf("Win98") > -1 || sUserAgent.indexOf("Windows 98") > -1;
    isWinME = sUserAgent.indexOf("Win 9x 4.90") > -1 || sUserAgent.indexOf("Windows ME") > -1;
    isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
    isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
    isWinNT4 = sUserAgent.indexOf("WinNT") > -1 || sUserAgent.indexOf("Windows NT") > -1 || sUserAgent.indexOf("WinNT4.0") > -1 || sUserAgent.indexOf("Windows NT 4.0") > -1 && (!isWinME && !isWin2K && !isWinXP);
} 

if (isMac) {
    isMac68K = sUserAgent.indexOf("Mac_68000") > -1 || sUserAgent.indexOf("68K") > -1;
    isMacPPC = sUserAgent.indexOf("Mac_PowerPC") > -1 || sUserAgent.indexOf("PPC") > -1;  
}

if (isUnix) {
    isSunOS = sUserAgent.indexOf("SunOS") > -1;

    if (isSunOS) {
        var reSunOS = new RegExp("SunOS (\\d+\\.\\d+(?:\\.\\d+)?)");
        reSunOS.test(sUserAgent);
        isMinSunOS4 = compareVersions(RegExp["$1"], "4.0") >= 0;
        isMinSunOS5 = compareVersions(RegExp["$1"], "5.0") >= 0;
        isMinSunOS5_5 = compareVersions(RegExp["$1"], "5.5") >= 0;
    }
}

//frame size start
function frameSize(sizeValue) {
	if (sizeValue == 'S') {
		parent.frMnaLeft.cols = '17,*';
	}
	else if (sizeValue == 'L') {
		parent.frMnaLeft.cols = '280,*';
	}
}

function frameSizeC(sizeValueC) {
	if (sizeValueC == 'S') {
		parent.frComLeft.cols = '17,*';
	}
	else if (sizeValueC == 'L') {
		parent.frComLeft.cols = '220,*';
	}
}

function frameSizeT(sizeValueT) {
	if (sizeValueT == 'S') {
		top.frTop.rows = '17,*';
	}
	else if (sizeValueT == 'L') {
		top.frTop.rows = '104,*';
	}
}

function divShow(obj) {
  obj.style.display='block';
}
function divHidden(obj) {
  obj.style.display='none';
}
// frame size end

// 공통코드에 대한 selectbox 생성  
// searchUrl 에 gbn, upCd, defaultCd, delYn, localeVal, abbrVal 포함 생성
// 공통코드 : /cpis/cpCom.do?method=listComCdByGrpCd&sys_cd=CPIS&grp_cd=C08
// 그          외 : /cpis/cpCom.do?method=getComboHTML&gbn=CATE&up_cd=TOP&defaultCd=&del_yn=N&localeVal=ko&abbr=A
/*
function getCodeSelectByUpCd(formId, selObjId, searchUrl) {
	var html = "";
	var options = {
			url: searchUrl,
			type: "post",
			//dataType: "json",
			async: false,
			success: function (data) {
				$("#" + selObjId).html(data);
			}
	}
	
	$("#" + formId).ajaxSubmit(options);
}
*/

//-------------------------------------------------------------------
//PopUp Window Open 함수.
//-------------------------------------------------------------------
var PopUpWindow;
var PopUpWindow2;
var popup_window;
function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll)
{
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
	
	//Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	
	Future = "toolbar=no,location=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

	PopUpWindow = window.open(surl, "PopUpWindow" , Future);
	//PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	PopUpWindow.focus();
	
}

//-------------------------------------------------------------------
//PopUp Window Open 함수. (target을 지정해줄 경우)
//-------------------------------------------------------------------
function PopUpTargetWindowOpen(surl, popupwidth, popupheight, bScroll, starget)
{
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
	
	Future = "toolbar=no,location=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

	PopUpWindow = window.open(surl, starget , Future);
	
	PopUpWindow.focus();
	
}

//-------------------------------------------------------------------
//PopUp Window Modal Open 함수.
//-------------------------------------------------------------------
function PopUpWindowModalOpen(surl, popupwidth, popupheight, bScroll){
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

	PopUpWindow = window.showModalDialog(surl, "PopUpWindow" , Future);
	//PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	PopUpWindow.focus();	
	
}

//-------------------------------------------------------------------
//PopUp Window Modal Open 함수.
//-------------------------------------------------------------------
function PopUpWindowModalOpen2(surl, popupwidth, popupheight, bScroll){
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

	PopUpWindow = window.showModalDialog(surl, "PopUpWindow2" , Future);
	//PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	PopUpWindow.focus();	
	
}

//-------------------------------------------------------------------
//PopUp Window Open 함수2.( 이미 PopUp된 Window에서 다시 PopUp window를 Open할때 사용)
//-------------------------------------------------------------------
function PopUpWindowOpen2(surl, popupwidth, popupheight, bScroll)
{
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
	
	//Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	Future = "toolbar=no,location=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

	PopUpWindow2 = window.open(surl, "PopUpWindow2" , Future)
	//PopUpWindow2.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	PopUpWindow2.focus();
}

//-------------------------------------------------------------------
//PopUp Window Open 함수 - Window 명까지 파라미터로 받음
//-------------------------------------------------------------------
function PopUpWindowOpenWithName(surl, popupwidth, popupheight, winName,bScroll)
{
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
	
	//var bScroll = true;

	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	
	popupwidth = parseInt(popupwidth) + 10 ;
	popupheight = parseInt(popupheight) + 29 ;
	
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

	popup_window = window.open(surl, winName , Future)
	//popup_window.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	popup_window.focus();
}

//-------------------------------------------------------------------
//PopUp Window Close 함수.( 활성화된 PopUp window를 Close할때 사용) : 
//ex) <body onUnLoad="PopUpWindowClose()">
//-------------------------------------------------------------------
function PopUpWindowClose()
{
	if(PopUpWindow != null) PopUpWindow.close();
	if(PopUpWindow2 != null) PopUpWindow2.close();
	if(popup_window != null) popup_window.close();
}
//-----------------------------------------------------------------------------------------------------------
// 2011/03/29 디자인 반영을 위한 스크립트. 
//-----------------------------------------------------------------------------------------------------------
//main link

function main() {
	top.document.location.href = '../main.html';
}



// mainmenu link
function menu01_info() {
	top.document.location.href ='../01.HP_info/info.html';
}
function menu02_communication() {
	top.document.location.href = '../02.HP_communication/communication.html';
}
function menu03_file() {
	top.document.location.href = '../03.HP_file/file.html';
}
function menu04_report() {
	top.document.location.href = '../04.HP_report/report.html';
}






/** popup **/
function open_window(name, url, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable)
{
  toolbar_str = toolbar ? 'yes' : 'no';
  menubar_str = menubar ? 'yes' : 'no';
  statusbar_str = statusbar ? 'yes' : 'no';
  scrollbar_str = scrollbar ? 'yes' : 'no';
  resizable_str = resizable ? 'yes' : 'no';
  window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str);
}
/** popup center **/
function MoveCenterWindow(){
  var cx = document.body.clientWidth;
  var cy = document.body.clientHeight;
  var sw = screen.availWidth;
  var sh = screen.availHeight;
  var px = (sw-cx)/2;
  var py = (sh-cy)/2
  window.moveTo(px, py);
}
//MoveCenterWindow();
// 팝업창 중앙으로 위치 
function center(){ 
    var x,y; 
    if (self.innerHeight) { // IE 외 모든 브라우저 
        x = (screen.availWidth - self.innerWidth) / 2; 
        y = (screen.availHeight - self.innerHeight) / 2; 
    }else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict 모드 
        x = (screen.availWidth - document.documentElement.clientWidth) / 2; 
        y = (screen.availHeight - document.documentElement.clientHeight) / 2; 
    }else if (document.body) { // 다른 IE 브라우저( IE < 6) 
        x = (screen.availWidth - document.body.clientWidth) / 2; 
        y = (screen.availHeight - document.body.clientHeight) / 2; 
    } 
    window.moveTo(x,y); 
} 

function PopESBUser(UserId){/*김정곤추가 2011,04,07 사용자정보팝업.*/
	if(UserId==""){
		alert("사용자 ID가 없습니다.") ;
		return ;
	}
	var frm = document.frm;
	var menuId = frm.menu_id!=null ? frm.menu_id.value : "" ;

	PopUpWindowOpen("/secfw/userMng.do?method=EsbUserPopUp&pop_user_id="+UserId + "&menu_id=" + menuId, 720, 350, false);
};


function divShow(obj) {
  obj.style.display='block';
}
function divHidden(obj) {
  obj.style.display='none';
}


/* ----------------------------------------- [테이블고정 스크립터] ---------------------------------------------- */

//oTitleTable 테이블갯수만큼 생성한다.
var  preTableWidth = new Array(document.getElementsByName("oTitleTable").length);

//사이즈를 조절한다.
function setTitleSize(){ 
	var oTitle = document.getElementsByName("oTitleTable");
	var oList = document.getElementsByName("oListTable");

	for(var n=0; n<oTitle.length; n++){
		if(oList[0].clientWidth==preTableWidth[n]) continue;
		preTableWidth[n] = oList[0].clientWidth;
	
		oTitle[n].style.width = oList[0].clientWidth;
		var oListRows1 = document.getElementsByName("trTitle")[n];
		var oTitleRows1 = oTitle[n].rows.item(0);
	
		for(var i=0; i<oTitleRows1.length; i++){
			var cWidth = oListRows1.item(i).clientWidth;
			oTitleRows1.item(i).style.width = cWidth;
			oListRows1.item(i).innerText='';
		}
	}
}


/* ----------------------------------------- [ DIV숨기고 보이기] ---------------------------------------------- */
function divShow(obj) {
  obj.style.display='block';
}
function divHidden(obj) {
  obj.style.display='none';
}

// left menu start
var oldMenu = '';
function menuClick( subMenu )
{
  if( oldMenu != subMenu ) {
    if( oldMenu !='' ) {
      oldMenu.style.display = 'none';
    }
    subMenu.style.display = 'block';
    oldMenu = subMenu;

  } else {
    subMenu.style.display = 'none';
    oldMenu = '';
  }
}
// left menu end

/************************************************
 * function : 공백처리(trim)
 * variable 
 * 	- strobj  : 대상String
 * example	: comTrim(str)
*************************************************/
function comTrim(strobj)
{
    return comRTrim(comLTrim(strobj));
}


/************************************************
 * function : 왼쪽공백처리(trim)
 * 	: 넘어온 문자열들중에 왼쪽에 포함된 공백을 모두 없앤 문자열을 리턴한다
 * variable 
 * 	- strobj  : 대상String
 * example	: comLTrim(str)
*************************************************/
function comLTrim(str)
{
    var retstr = "";
    var c;
    var end=0;
    for(var idx=0;idx<str.length;idx++) {
        c = str.charAt(idx);
        if(c != ' ' || end==1){
        	retstr += c;
           end = 1;
        }
    }
    return(retstr);
}
/************************************************
* function : 오른쪽공백처리(trim)
* 	: 넘어온 문자열들중에 왼쪽에 포함된 공백을 모두 없앤 문자열을 리턴한다
* variable 
* 	- strobj  : 대상String
* example	: comRTrim(str)
*************************************************/
function comRTrim(str)
{
    var retstr = "";
    var c;
    var end=0;
    for(var idx=str.length - 1;idx>=0;idx--) {
        c = str.charAt(idx);
        if(c != ' ' || end==1){
        	retstr = c + retstr;
           end = 1;
        }
    }
    return(retstr);
}

/************************************************
 * function : Byte 계산(UTF-8)
 * 	: 넘어온 문자열로 Byte를 계산해서 리턴
 * variable 
 * 	- strobj  : 대상String
 * example	: getByteLength(str)
*************************************************/
function getByteLength(formValue){
    var temp;
    var bytes = 0;
    var len = formValue.length;

    for(ii=0; ii<len; ii++){
        temp = formValue.charAt(ii) ;

		//escape code의 길이가 4보다 크면 한글
		if(escape(temp).length > 4){
		    bytes += 3;
		}else{
		    bytes++;
		}
    }
    
    return bytes;
}  

/************************************************
 * function : 문자열 길이 체크(UTF-8)
 * 	: 넘어온 문자열로 vmaxLen Byte초과하는지 여부를 판단하여 결과값 리턴
 * variable 
 * 	- strobj  : 대상String
 * example	: frmChkLen(str, vmaxLen, spanName)
*************************************************/
function frmChkLen(objTextarea, vmaxLen, spanName){
	
   	var str = objTextarea.value; 
   	var nchar = str.length;  
   
   	var nmax = vmaxLen;
   	var nbyte = 0;
   	var npos = 0;
   
   	for(var i=0; i< nchar; i++){
      	nbyte+=(escape(str.charAt(i)).length > 4 ? 3:1);
   	  	if(nbyte <= nmax) npos = i + 1;
   	}
   	
   	document.getElementById(spanName).innerHTML = nbyte;
   	if(nbyte > nmax){
      	alert( "The maximum number of characters allowed has been exceeded.\n It will be deleted.");
      	str = str.substr(0, npos);
      	objTextarea.value = str;
   	}
   	document.getElementById(spanName).innerHTML = getByteLength(str);
   	//objTextarea.focus();
}

/************************************************
 * function : 문자열 길이 체크(UTF-8), 언어 분기
 * 	: 넘어온 문자열로 vmaxLen Byte초과하는지 여부를 판단하여 결과값 리턴
 * variable 
 * 	- strobj  : 대상String
 * example	: frmChkLen(str, vmaxLen, spanName)
*************************************************/
function frmChkLenLang(objTextarea, vmaxLen, spanName, langCd){
	
   	var str = objTextarea.value; 
   	var nchar = str.length;  
   
   	var nmax = vmaxLen;
   	var nbyte = 0;
   	var npos = 0;

   	for(var i=0; i< nchar; i++){
      	nbyte+=(escape(str.charAt(i)).length > 4 ? 3:1);
   	  	if(nbyte <= nmax) npos = i + 1;
   	}
   	
   	document.getElementById(spanName).innerHTML = nbyte;
   	if(nbyte > nmax){
   		if(langCd == "ko") {
   			alert( "입력가능한 글자수를 초과 하였습니다.\n초과된 내용은 자동으로 삭제 됩니다.");
   		} else if(langCd == "fr") {
   			alert( "Vous avez dépassé le nombre maximum de caractères autorisés.");
   		} else if(langCd == "de") {
   			alert( "Die maximale Zeichenzahl wurde überschritten.");
   		} else{
   			alert( "The maximum number of characters allowed has been exceeded.\n It will be deleted.");
   		}
   		
      	str = str.substr(0, npos);
      	objTextarea.value = str;
   	}
   	document.getElementById(spanName).innerHTML = getByteLength(str);
   	//objTextarea.focus();
}

/************************************************
 * function : 메시지 Alert
 * 	: 메시지를 Alert 으로 띄운다.
 * variable 
 * 	- strobj  : 대상String
 * example	: alertMessage(str)
*************************************************/
function alertMessage(message){
	if(message!=null && message!=""){
		alert(message) ;
	}
}

/************************************************
 * function : 상태표시줄 링크주소 Hidden
 * 	: 상태표시줄 링크주소를 숨긴다.
 * variable 
 * 	- strobj  : N/A
 * example	: hidestatus()
*************************************************/
function hidestatus(){
	window.status='';
	timerID=setTimeout("hidestatus()", 0);
}

hidestatus();

/************************************************
 * function : 거래선 상세 페이지로 이동
 * 	: 각화면에서 거래선 상세 페이지를 팝업으로 띄운다.
 * variable 
 * 	- strobj  : custmoerCd : 거래선 코드, dodun : 중복을 피한 값 
 * example	: 
*************************************************/
function customerPop(customerCd, dodun){
	
	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false,"custmoerPOP");
}

/************************************************
 * function : 연관계약 상세 페이지로 이동
 * 	: 각화면에서 연관계약를 팝업으로 띄운다.
 * variable 
 * 	- strobj  : CNSDREQ_ID : 의뢰번호 
 * modify : 신성우 2014-04-10 cntrt_id -> cnsdreq_id로 변경처리.. 설명과 다르게 마스터 id를 가져오게 구성되어있었음
 * example	: 
*************************************************/
function goDetail(cnsdreq_id){

	PopUpWindowOpen('/clm/manage/completion.do?method=relatedContractDetail&menu_id=20130319155624549_0000000374&conListGu=Z1000&cnsdreq_id='+cnsdreq_id, 900, 600, true,"detailPopUp");
}

/************************************************
 * function : 문자열 길이 체크(UTF-8) 스프링 메시지
 * 	: 넘어온 문자열로 vmaxLen Byte초과하는지 여부를 판단하여 결과값 리턴
 * variable 
 * 	- strobj  : 대상String
 * example	: frmChkLenMe(str, vmaxLen, spanName)
*************************************************/
function frmChkLenMe(objTextarea, vmaxLen, spanName){
	
   	var str = objTextarea.value; 
   	var nchar = str.length;  
   
   	var nmax = vmaxLen;
   	var nbyte = 0;
   	var npos = 0;
   
   	for(var i=0; i< nchar; i++){
      	nbyte+=(escape(str.charAt(i)).length > 4 ? 3:1);
   	  	if(nbyte <= nmax) npos = i + 1;
   	}
   	
   	document.getElementById(spanName).innerHTML = nbyte;
   	if(nbyte > nmax){
      	alert( nmax +text.bbs_alert.textarea_overbyte);
      	str = str.substr(0, npos);
      	objTextarea.value = str;
   	}
   	document.getElementById(spanName).innerHTML = getByteLength(str);
   	//objTextarea.focus();
}