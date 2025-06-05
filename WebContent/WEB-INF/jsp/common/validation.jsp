<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JavaScript" type="text/JavaScript">
<!--

	/* validation 메인 함수 */
	function validateForm(form){
	     for(vi=0; vi<form.elements.length;vi++){
	        var formField = form.elements[vi];
	        var fieldType = formField.type;
	        if(fieldType != 'hidden' && fieldType !='password' && !formField.disabled){
	            var fieldName = formField.name;
	            var fieldValue = trimStr(formField.value);
	            var num = formField.num;
	            var email = formField.email ;
	            var fieldTitle = formField.fieldTitle
	
	            if(formField.required != null && fieldType == "radio"){
	                fieldValue = "";
	                var wi = vi;
	                while(form.elements[wi].type == "radio"){
	                    if(form.elements[wi].checked == true){
	                        fieldValue = form.elements[wi].value
	                    }
	                    wi++
	                }
	                form.elements[wi].value = fieldValue
	            }
	            if(formField.required != null && fieldValue == ""){
		            /*
		            message : 항목은 필수 입력 항목입니다
		            */
		            alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.001" />');
		            return returnFalse(formField);
	            }
	            if(formField.filter != null && checkSpecialChar2(fieldValue,formField.filter)){
		            /*
		            message : 항목에 다음과 같은 문자는 입력할 수 없습니다
		            */		            
	                alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.002" />'+"\n\n"+formField.filter);
	                return returnFalse(formField);
	            }
	            if(formField.maxLength != null && !checkMaxLengthUtf8(fieldValue,formField.maxLength)){
		            /*
		            message : 항목은 최대 문자열 수를 초과했습니다 + 최대 문자열 수(영문기준)
		            */
		            alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.003" />'+ "\n\n" + '<spring:message code="shri.com.msg.validation.004" />' + " : " + formField.maxLength);
	                return returnFalse(formField);
	            }
	            if(formField.minLength != null && !checkMinLengthUtf8(fieldValue,formField.minLength)){
		            /*
		            message : 항목은 입력해야할 최소 문자열 수보다 작습니다 + 최소 문자열 수(영문기준)
		            */
		            alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.005" />'+ "\n\n" + '<spring:message code="shri.com.msg.validation.006" />' + " : " + formField.minLength);
	                return returnFalse(formField);
	            }
	            
	            if(formField.email !=null){
	            	if(!checkEmail(fieldValue)){
		            	/*
		            	message : 항목은 유효한 이메일 형식이 아닙니다
		            	*/
	            		alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.007" />');
	            		return returnFalse(formField);
	            	}
	            }
	        } // hidden if
	    } // end for
	    return true;
	}

	/* validation 메인 함수 */
	function validateFormLength(form){
	     for(vi=0; vi<form.elements.length;vi++){
	        var formField = form.elements[vi];
	        var fieldType = formField.type;
	        if(fieldType != 'hidden' && fieldType !='password' && !formField.disabled){
	            var fieldName = formField.name;
	            var fieldValue = trimStr(formField.value);
	            var num = formField.num;
	            var email = formField.email ;
	            var fieldTitle = formField.fieldTitle
	
	            if(formField.required != null && fieldType == "radio"){
	                fieldValue = "";
	                var wi = vi;
	                while(form.elements[wi].type == "radio"){
	                    if(form.elements[wi].checked == true){
	                        fieldValue = form.elements[wi].value
	                    }
	                    wi++
	                }
	                form.elements[wi].value = fieldValue
	            }
	            if(formField.required != null && fieldValue == ""){
		            /*
		            message : 항목은 필수 입력 항목입니다
		            */
	                alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.001" />');
	                return returnFalse(formField);
	            }
	            if(formField.filter != null && checkSpecialChar2(fieldValue,formField.filter)){
		            /*
		            message : 항목에 다음과 같은 문자는 입력할 수 없습니다
		            */		            
	                alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.002" />'+"\n\n"+formField.filter);
	                return returnFalse(formField);
	            }	            
	            if(formField.maxLength != null && fieldValue.length > formField.maxLength){
		            /*
		            message : 항목은 최대 문자열 수를 초과했습니다 + 최대 문자열 수
		            */
	                alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.003" />'+ "\n\n" + '<spring:message code="shri.com.msg.validation.004" />' + " : " + formField.maxLength);
	                return returnFalse(formField);
	            }
	            if(formField.minLength != null && fieldValue.length < formField.minLength){
		            /*
		            message : 항목은 입력해야할 최소 문자열 수보다 작습니다 + 최소 문자열 수
		            */
	                alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.005" />'+ "\n\n" + '<spring:message code="shri.com.msg.validation.006" />' + " : " + formField.minLength);
	                return returnFalse(formField);
	            }

	            if(formField.email !=null){
	            	if(!checkEmail(fieldValue)){
		            	/*
		            	message : 항목은 유효한 이메일 형식이 아닙니다
		            	*/
	            		alert(fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.007" />');
	            		return returnFalse(formField) ;
	            	}
	            }
	            	            
	        } // hidden if
	    } // end for
	    return true;
	}

	/* value를 trim 처리 */
	function trimStr(str) {
	    if(str == null)
	        return ''
	    var count = str.length;
	    var len = count;
	    var st = 0;
	
	    while ((st < len) && (str.charAt(st) <= ' ')) {
	        st++;
	    }
	    while ((st < len) && (str.charAt(len - 1) <= ' ')) {
	        len--;
	    }
	    return ((st > 0) || (len < count)) ? str.substring(st, len) : str ;
	}

	/* value의 최대 바이트 수를 체크 */
	function checkMaxLength(formValue, maxlength){
	    var temp;
	    var bytes = 0;
	    var len = formValue.length;
	
	    for(ii=0; ii<len; ii++){
	        temp = formValue.charAt(ii);
	
		//escape code의 길이가 4보다 크면 한글
		if(escape(temp).length > 4){
		    bytes += 3;
		}else{
		    bytes++;
		}
	    }
	    
	    if(maxlength >= bytes){
		return true;
	    } else {
		return false;
	    }
	}

	/* value의 최소 바이트 수를 체크 */
	function checkMinLength(formValue, minlength){
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
	    
	    if(minlength <= bytes){
			return true;
	    } else {
			return false;
	    }
	}

	/** UTF-8 에서 최대길이 체크 **/
	function checkMaxLengthUtf8(formValue, maxlength){
		var bytes = stringByteSizeUtf8(formValue) ;
		
		if(bytes>maxlength) {
			return false ;
		} else {
			return true ;
		}
	}

	/** UTF-8 에서 최소길이 체크 **/
	function checkMinLengthUtf8(formValue, minlength){
		var bytes = stringByteSizeUtf8(formValue) ;
		
		if(bytes<minlength) {
			return false ;
		} else {
			return true ;
		}
	}

	/** utf-8 에서 문자 byte 수 **/
	function charByteSizeUtf8(ch) {
	    if (ch == null || ch.length == 0) {
	    	return 0;
	    }
	
	    var charCode = ch.charCodeAt(0);
	    
	    if (charCode <= 0x00007F) {
	    	return 1;
	    } else if (charCode <= 0x0007FF) {
	    	return 2;
	    } else if (charCode <= 0x00FFFF) {
	    	return 3;
	    } else {
	    	return 4;
	    }
	}
 
	// 문자열을 UTF-8로 변환했을 경우 차지하게 되는 byte 수를 리턴한다.
	function stringByteSizeUtf8(str) {
	    if (str == null || str.length == 0) {
	    	return 0;
	    }
	
	    var size = 0;
	    for (var i = 0; i < str.length; i++) {
	    	size += charByteSizeUtf8(str.charAt(i));
	    }
	
	    return size;
	}

	/* 허용하지 않는 문자 체크 (recommended)*/
	function checkSpecialChar(fieldValue, str) {
	
	    var re=new RegExp(str);
		
	    if(re.test(fieldValue)){
	        return true
		}
	    return false
	}

	/* 허용하지 않는 문자 체크 2 */
	function checkSpecialChar2(fieldValue, str) {
	    var retCode = 0
	    for (sc = 0; sc < str.length; sc++) {
	        var code = str.charCodeAt(sc)
	        var ch = str.substr(sc,1).toUpperCase()
	
	        code = parseInt(code)
	        if(fieldValue.indexOf(ch) >= 0){
	            return true
	        }
	    }
	    return false
	}

	/* 문자 체크
	    type = k : 한글
	                e : 영어
	                n : 숫자
	*/
	function checkChar(str, type){
	    var retCode = 0
	    for (i = 0; i < str.length; i++) {
	        var code = str.charCodeAt(i)
	        var ch = str.substr(i,1).toUpperCase()
	
	        code = parseInt(code)
	        if (type=="k" && (ch < "0" || ch > "9") && ( ch < "A" || ch > "Z") && ((code > 255) || (code < 0))) {
	            return true
	        }
	        if (type=="e" && (ch >= "A") && (ch <= "Z")) {
	            return true
	        }
	        if (type=="n" && (ch >= "0" && ch <= "9")) {
	            return true
	        }
	    }
	    return false
	}

	/* 숫자와 허용되는 문자(z) 체크 */
	function checkNumber(x,z){
	    var cmp = "1234567890";
	    var ifor;
	
	    var return_value = true;
	
	    len = x.length;
	
	    if (z !=''){
	        cmp = "1234567890"+z;
	    }
	
	    if (x == "" || x == null || len < 1){
	        return_value = false;
	    }else{
		for(ifor=0;ifor<len;ifor++){
	            if(cmp.indexOf(x.substring(ifor,ifor+1))<0){
			return_value=false;
		    }
		}
	    }
	    return return_value;
	}
	
	/* 일정 형식의 value 체크
	    format default value 가 9 : 숫자
	                                           s : 문자를 나타냄
	    구분기호('-',','...)는 상관없다
	 */
	function maskFormat(fieldValue, format){
	
	    var sStr = fieldValue
	    var ik;
	    var jk=0;
	    var tLen = sStr.length +1 ;
	    if(fieldValue.length != format.length){
	        return false
	    }
	
	    for(ik=0; ik< sStr.length; ik++){
	        if(format.charAt(jk)!="9" && format.charAt(jk)!="s"){
	            if(sStr.charAt(ik) != format.charAt(jk)){
	                return false
	            }
	        }else{
	            if(format.charAt(jk) =="9" && !isTemPositiveInteger(sStr.charAt(ik))){
	                return false
	            }
	            if(format.charAt(jk) =="s" && isTemPositiveInteger(sStr.charAt(ik))){
	                return false
	            }
	
	        }
	        jk++;
	    }
	    return true;
	}

	/* return 공통 함수 */
	function returnFalse(formField){
	    formField.focus();
	    return false;
	}

	function checkEmail(fieldValue){
	    if(fieldValue != "" && (fieldValue.indexOf('@') == -1 || fieldValue.indexOf('.') == -1)){
		return false;
	    }
	    return true;
	}
	
	function checkURL(fieldValue){
	    if(fieldValue != "" &&fieldValue.indexOf('.') == -1){
		return false;
	    }
	    return true;
	}

	/* 전화번호 체크 ( '-' 포함) */
	function checkPhoneStr(fieldValue){
	    if(fieldValue != "" && fieldValue.indexOf('-')==-1){
	    	return false;
	    }else{
	    	notN = false;
	    	phones = fieldValue.split('-');
	    	for(pc=0; pc < phones.length ; pc++){
	    		if(isNaN(phones[pc])) {
	    			notN = true;
	    		}
	    	}
	    	if (notN == true || fieldValue.indexOf(".") >=0) {
	    		return false;
	    	}
	    	return true;
	    }
	}

	/*  엔터키 체크 */
	function checkEnterKey(){
	    if(event.keyCode==13) {
	        return true;
	    }
	    return false;
	}

	/* textarea 문자열 바이트수 체크 */
	function checkTextLength(formField, maxlength){
	    var formValue = formField.value;
	    
	    if(!checkMaxLength(formValue, maxlength)) {
			var count=0;
			for(var bytes=0; bytes < maxlength; bytes++,count++){
		        var temp = formValue.charAt(count) ;
			    if(escape(temp).length > 4) {
	                if(bytes == eval(maxlength-1)) {
	                    count--;
	                }else{
	                	bytes++;
	                }
			    }
			}
	        var fieldTitle = formField.fieldTitle;
	        /* 
	        message : 최대 문자열 길이를 초과했습니다 + 최대 글자수(영문기준)
	        */	        
	        alert ( fieldTitle + ' ' + '<spring:message code="shri.com.msg.validation.003" />'+ "\n\n" + '<spring:message code="shri.com.msg.validation.004" />' + " : " + maxlength);
			formField.value = formValue.substring(0, count);
			return returnFalse(formField);
	    }
	}

	/* 숫자 체크
	    first :정수부 최대 자리수, last:소수부 자리수
	*/
	function checkNumLength(value, first, last){
	
	    var value = trimStr(value);
	    var temp = value.split(".");
	    if(temp.length > 2) return false;
	
	    if(value.length > 0){
	        if(checkLength(temp[0])){
	            if(temp[0].length <= first){
	                if(temp[1] != null && temp[1].length > 0){
	                    if(checkLength(temp[1])){
	                        if(temp[1].length <= last){
	                            return true;
	                        }else {
	                            return false;
	                        }
	                    }else {
	                        return false;
	                    }
	                }else return true;
	            }else {
	                return false;
	            }
	        }else {
	            return false;
	        }
	    }else return true;
	}

    function checkLength(value){
        if(isTemSignedIntegers(value)){
            if(isTemPositiveInteger(value)){
                return true;
            }else return false;
        }else return false;
    }

    function isTemPositiveInteger(s){
        return (isTemSignedIntegers(s) && (parseInt (s) >= 0 ));
    }

    function isTemSignedIntegers(s){
        if(isTemEmpty(s))
            return false;

        var startPos = 0;

        if((s.charAt(0) == "-") || (s.charAt(0) == "+"))
            startPos = 1;
        return (isTemInteger(s.substring(startPos, s.length)))
    }

    function isTemEmpty(s){
        return ((s == null) || (s.length == 0))
    }

    function isTemInteger(s){
        var i;

        if(isTemEmpty(s))
            return false

        for(i=0 ; i< s.length ; i++){
            var c = s.charAt(i);
            if(!isTemDigit(c)) return false;
        }

        return true;
    }

    function isTemDigit(c){
        return ((c >= "0") && (c <="9"))
    }

//-->
</script>
</head>
<body leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">
</body>
</html>