/*
해당 function을 사용하기 위해선 html 객체에 
num, required,filter, maxLength, minLength 등의 사용자 정의 attribute들이 있어야 한다.
*/

/* validation 메인 함수 */
function validateForm(form){
     for(var vi=0; vi<form.elements.length;vi++){
        var formField = form.elements[vi];
        var fieldType = formField.type;
        var fieldName = formField.name;
             
        if((fieldName == "body_mime1" || fieldName == "body_mime" || fieldName == "body_mime_rq" || fieldType != 'hidden') && fieldType !='password' && !formField.disabled){
            var fieldValue = trimStr(formField.value);
            var num = formField.num;
            var email = formField.email ;
            var fieldTitle = formField.alt;
            
//            console.log("fieldName :"+fieldName);
//            console.log("formField.required  :"+formField.required );
//            console.log("formField.maxLength  :"+formField.maxLength );

            if(fieldName == "body_mime1" || fieldName == "body_mime_rq" || fieldName == "body_mime"){
            	fieldValue = stripHTMLtag(fieldValue);
                if(formField.required != null && formField.required == "true" && fieldValue == ""){
                	alert(fieldTitle+" is required.");
                    return false;
                }else{
                	return true;
                }
            }
            
            if(formField.required != null && formField.required != "" && fieldType == "radio"){
                fieldValue = "";
                var wi = vi;
                while(form.elements[wi].type == "radio"){
                    if(form.elements[wi].checked == true){
                        fieldValue = form.elements[wi].value;
                    }
                    wi++;
                }
                form.elements[wi].value = fieldValue;
            }
            if(formField.required != null && formField.required == "true" && fieldValue == ""){
            	alert(fieldTitle+" is required.");
                return returnFalse(formField);
            }
            if(formField.filter != null && checkSpecialChar2(fieldValue,formField.filter)){
            	alert(fieldTitle+" is/are invalid inputs."+"\n\n" + "Invalid : " + formField.filter);
                return returnFalse(formField);
            }
            if(formField.maxLength != null && formField.maxLength > 0 && !checkMaxLengthUtf8(fieldValue,formField.maxLength)){
            	alert("The maximum number of characters for the " +fieldTitle+ " box has been exceeded." +"\n\n"  + "Maximum No. of Characters (English) :" + formField.maxLength);
                return returnFalse(formField);
            }
            if(formField.minLength != null && formField.minLength > 0 && !checkMinLengthUtf8(fieldValue,formField.minLength)){
            	alert("Check the minimum number of letters required " +fieldTitle+ ".\n\n"  + "Minimum No. of Characters (English) : " + formField.minLength);
                return returnFalse(formField);
            }
            if(num != null && fieldValue != ""){
                if(num == "" && !checkNumber(fieldValue,".-")){
                	alert("Only real number can be inputted in " +fieldTitle);
                    return returnFalse(formField);
                }else if(num == "i" && !checkNumber(fieldValue,"-")){
                	alert("Only integer values can be inputted in " +fieldTitle);
                    return returnFalse(formField);
                }else if(num == "n" && !checkNumber(fieldValue,"")){
                	alert("Only numeric values can be inputted in " +fieldTitle);
                    return returnFalse(formField);
                }else if(num != "" && num != "i"){
                    if(!checkNumber(fieldValue,".-")){
                    	alert("Only real number can be inputted in " +fieldTitle);
                        return returnFalse(formField);
                    }
                    var oneTwo = num.split(".");
                    if(!checkNumLength(fieldValue,oneTwo[0],oneTwo[1])){
                    	alert(fieldTitle+" is/are invalid inputs. Enter numeric values.\n\n"+"Maximum number of digits in the integer part : "+oneTwo[0]+"   Number of decimal places : "+oneTwo[1]);
                        return returnFalse(formField);
                    }
                }
            }

            if(formField.fromNum != null && checkNumber(fieldValue,"-.")){
                if(eval(formField.fromNum) > eval(fieldValue)){
                	alert("Only numbers equal to or bigger than "+ formField.fromNum +" can be entered " + fieldTitle + ".");
                     return returnFalse(formField);
                }
            }
            if(formField.toNum != null && checkNumber(fieldValue,"-.")){
                if(eval(formField.toNum) < eval(fieldValue)){
                	alert("Only smaller numbers than "+ formField.fromNum +" can be entered " + fieldTitle + ".");
                    return returnFalse(formField);
                }
            }

            if(formField.format != null && formField.format != "" && fieldValue !=""){
                if(!maskFormat(fieldValue, formField.format)){
                	alert(fieldTitle+" is invalid Type.\n\nType : "+formField.format);
                    return returnFalse(formField);
                }
            }
            
            if(formField.email !=null){
            	if(!checkEmail(fieldValue)){
            		alert(fieldTitle+" is Invalid Email Address.");
            		return returnFalse(formField) ;
            	}
            }
            
            if(formField.url !=null){
            	if(!checkURL(fieldValue)){
            		alert(fieldTitle+" is Invalid Web Address.\n\n"+" ex) www.samsungsds.co.kr");
            		return returnFalse(formField) ;
            	}
            }
            
            if(formField.phone !=null){
            	if(!checkPhoneStr(fieldValue)){
            		alert(fieldTitle+" is Invalid Telephone Number.\n\n"+" ex) 02-202-0020");
            		return returnFalse(formField) ;
            	}
            }

            if(formField.char != null && !validateChar(formField.char,fieldValue,fieldTitle)){
                    return returnFalse(formField);
            }

            /*  if(formField.valCheck != null && !validateEtc(formField, vi)){
                return returnFalse(formField)
            }
           	사용자 정의 validation을 할 경우 fncUserValidation으로 만들어서 사용한다.
            if(fncUserValidation != null && formField.valCheck != null && !fncUserValidation(formField, vi)){
                return returnFalse(formField)
            }*/

        } // hidden if
    } // end for
    return true;
}

/* 필수입력항목을 제외한 검증[임시저장] */
function validateTempSave(form){
    for(var vi=0; vi<form.elements.length;vi++){
       var formField = form.elements[vi];
       var fieldType = formField.type;
       var fieldName = formField.name;
       if((fieldName == "body_mime1" || fieldName == "body_mime" || fieldName == "body_mime_rq" || fieldType != 'hidden') && fieldType !='password' && !formField.disabled){
           var fieldValue = trimStr(formField.value);
           var num = formField.num;
           var email = formField.email ;
           var fieldTitle = formField.alt;
           
          // alert("fieldName validateTempSave :"+fieldName);
          // alert("formField.required validateTempSave :"+formField.required );
          // alert("formField.maxLength validateTempSave :"+formField.maxLength );
           
           if(formField.required != null && formField.required != "" && fieldType == "radio"){
               fieldValue = "";
               var wi = vi;
               while(form.elements[wi].type == "radio"){
                   if(form.elements[wi].checked == true){
                       fieldValue = form.elements[wi].value;
                   }
                   wi++;
               }
               form.elements[wi].value = fieldValue;
           }
           /*
           if(formField.required != null && formField.required == "true" && fieldValue == ""){
               alert(fieldTitle+"은(는) 필수 입력 항목입니다.")
               return returnFalse(formField)
           }
           */
           if(formField.filter != null && checkSpecialChar2(fieldValue,formField.filter)){
        	   alert(fieldTitle+" is/are invalid inputs."+"\n\n" + "Invalid : " + formField.filter);
               return returnFalse(formField);
           }
           if(formField.maxLength != null && formField.maxLength > 0 && !checkMaxLengthUtf8(fieldValue,formField.maxLength)){
        	   alert("The maximum number of characters for the " +fieldTitle+ " box has been exceeded." +"\n\n"  + "Maximum No. of Characters (English) :" + formField.maxLength);
               return returnFalse(formField);
           }
           if(formField.minLength != null && formField.minLength > 0 && !checkMinLengthUtf8(fieldValue,formField.minLength)){
        	   alert("Check the minimum number of letters required " +fieldTitle+ ".\n\n"  + "Minimum No. of Characters (English) : " + formField.minLength);
               return returnFalse(formField);
           }
           
           if(num != null && fieldValue != ""){
               if(num == "" && !checkNumber(fieldValue,".-")){
            	   alert("Only real number can be inputted in " +fieldTitle);
                   return returnFalse(formField);
               }else if(num == "i" && !checkNumber(fieldValue,"-")){
            	   alert("Only integer values can be inputted in " +fieldTitle);
                   return returnFalse(formField);
               }else if(num == "n" && !checkNumber(fieldValue,"")){
            	   alert("Only numeric values can be inputted in " +fieldTitle);
                   return returnFalse(formField);
               }else if(num != "" && num != "i"){
                   if(!checkNumber(fieldValue,".-")){
                	   alert("Only real number can be inputted in " +fieldTitle);
                       return returnFalse(formField);
                   }
                   var oneTwo = num.split(".");
                   if(!checkNumLength(fieldValue,oneTwo[0],oneTwo[1])){
                	   alert(fieldTitle+" is/are invalid inputs. Enter numeric values.\n\n"+"Maximum number of digits in the integer part : "+oneTwo[0]+"   Number of decimal places : "+oneTwo[1]);
                       return returnFalse(formField);
                   }
               }
           }

           if(formField.fromNum != null && checkNumber(fieldValue,"-.")){
               if(eval(formField.fromNum) > eval(fieldValue)){
            	   alert("Only numbers equal to or bigger than "+ formField.fromNum +" can be entered " + fieldTitle + ".");
                    return returnFalse(formField);
               }
           }
           if(formField.toNum != null && checkNumber(fieldValue,"-.")){
               if(eval(formField.toNum) < eval(fieldValue)){
            	   alert("Only smaller numbers than "+ formField.fromNum +" can be entered " + fieldTitle + ".");
                   return returnFalse(formField);
               }
           }

           if(formField.format != null && formField.format != "" && fieldValue !=""){
               if(!maskFormat(fieldValue, formField.format)){
            	   alert(fieldTitle+" is invalid Type.\n\nType : "+formField.format);
                   return returnFalse(formField);
               }
           }
           
           if(formField.email !=null){
           	if(!checkEmail(fieldValue)){
           		alert(fieldTitle+" is Invalid Email Address.");
           		return returnFalse(formField) ;
           	}
           }
           
           if(formField.url !=null){
           	if(!checkURL(fieldValue)){
           		alert(fieldTitle+" is Invalid Web Address.\n\n"+" ex) www.samsungsds.co.kr");
           		return returnFalse(formField) ;
           	}
           }
           
           if(formField.phone !=null){
           	if(!checkPhoneStr(fieldValue)){
           		alert(fieldTitle+" is Invalid Telephone Number.\n\n"+" ex) 02-202-0020");
           		return returnFalse(formField) ;
           	}
           }

           if(formField.char != null && !validateChar(formField.char,fieldValue,fieldTitle)){
                   return returnFalse(formField);
           }

           /*  if(formField.valCheck != null && !validateEtc(formField, vi)){
               return returnFalse(formField)
           }
          	사용자 정의 validation을 할 경우 fncUserValidation으로 만들어서 사용한다.
           if(fncUserValidation != null && formField.valCheck != null && !fncUserValidation(formField, vi)){
               return returnFalse(formField)
           }*/

       } // hidden if
   } // end for
   return true;
}

function validateClmForm(form){
    for(var vi=0; vi<form.elements.length;vi++){
       var formField = form.elements[vi];
       var fieldType = formField.type;
       
       if((fieldName == "body_mime1" || fieldName == "body_mime" || fieldName == "body_mime_rq" || fieldType != 'hidden') && fieldType !='password' && !formField.disabled){
           var fieldName = formField.name;
           var fieldValue = trimStr(formField.value);
           var num = formField.num;
           var email = formField.email ;
           var fieldTitle = formField.alt;
           
           
          // alert("fieldName validateClmForm :"+fieldName);
          // alert("formField.required validateClmForm :"+formField.required );
          // alert("formField.maxLength validateClmForm :"+formField.maxLength );
           

           if(formField.required != null && formField.required != "" && fieldType == "radio"){
               fieldValue = "";
               var wi = vi;
               while(form.elements[wi].type == "radio"){
                   if(form.elements[wi].checked == true){
                       fieldValue = form.elements[wi].value;
                   }
                   wi++;
               }
               form.elements[wi].value = fieldValue;
           }
           if(formField.required != null && formField.required == "true" && fieldValue == ""){
        	   alert(fieldTitle+" is invalid.");
               return false;
               //return returnFalse(formField)
           }
           if(formField.filter != null && checkSpecialChar2(fieldValue,formField.filter)){
        	   alert(fieldTitle+" is/are invalid inputs."+"\n\n" + "Invalid : " + formField.filter);
               return returnFalse(formField);
           }
           if(formField.maxLength != null && formField.maxLength > 0 && !checkMaxLengthUtf8(fieldValue,formField.maxLength)){
        	   alert("The maximum number of characters for the " +fieldTitle+ " box has been exceeded." +"\n\n"  + "Maximum No. of Characters (English) :" + formField.maxLength);
               return returnFalse(formField);
           }
           if(formField.minLength != null && formField.minLength > 0 && !checkMinLengthUtf8(fieldValue,formField.minLength)){
        	   alert("Check the minimum number of letters required " +fieldTitle+ ".\n\n"  + "Minimum No. of Characters (English) : " + formField.minLength);
               return returnFalse(formField);
           }
           if(num != null && fieldValue != ""){
               if(num == "" && !checkNumber(fieldValue,".-")){
            	   alert("Only real number can be inputted in " +fieldTitle);
                   return returnFalse(formField);
               }else if(num == "i" && !checkNumber(fieldValue,"-")){
            	   alert("Only integer values can be inputted in " +fieldTitle);
                   return returnFalse(formField);
               }else if(num == "n" && !checkNumber(fieldValue,"")){
            	   alert("Only numeric values can be inputted in " +fieldTitle);
                   return returnFalse(formField);
               }else if(num != "" && num != "i"){
                   if(!checkNumber(fieldValue,".-")){
                	   alert("Only real number can be inputted in " +fieldTitle);
                       return returnFalse(formField);
                   }
                   var oneTwo = num.split(".");
                   if(!checkNumLength(fieldValue,oneTwo[0],oneTwo[1])){
                	   alert(fieldTitle+" is/are invalid inputs. Enter numeric values.\n\n"+"Maximum number of digits in the integer part : "+oneTwo[0]+"   Number of decimal places : "+oneTwo[1]);
                       return returnFalse(formField);
                   }
               }
           }

           if(formField.fromNum != null && checkNumber(fieldValue,"-.")){
               if(eval(formField.fromNum) > eval(fieldValue)){
            	   alert("Only numbers equal to or bigger than "+ formField.fromNum +" can be entered " + fieldTitle + ".");
                    return returnFalse(formField);
               }
           }
           if(formField.toNum != null && checkNumber(fieldValue,"-.")){
               if(eval(formField.toNum) < eval(fieldValue)){
            	   alert("Only smaller numbers than "+ formField.fromNum +" can be entered " + fieldTitle + ".");
                   return returnFalse(formField);
               }
           }

           if(formField.format != null && formField.format != "" && fieldValue !=""){
               if(!maskFormat(fieldValue, formField.format)){
            	   alert(fieldTitle+" is invalid Type.\n\nType : "+formField.format);
                   return returnFalse(formField);
               }
           }
           
           if(formField.email !=null){
           	if(!checkEmail(fieldValue)){
           		alert(fieldTitle+" is Invalid Email Address.");
           		return returnFalse(formField) ;
           	}
           }
           
           if(formField.url !=null){
           	if(!checkURL(fieldValue)){
           		alert(fieldTitle+" is Invalid Web Address.\n\n"+" ex) www.samsungsds.co.kr");
           		return returnFalse(formField) ;
           	}
           }
           
           if(formField.phone !=null){
           	if(!checkPhoneStr(fieldValue)){
           		alert(fieldTitle+" is Invalid Telephone Number.\n\n"+" ex) 02-202-0020");
           		return returnFalse(formField) ;
           	}
           }

           if(formField.char != null && !validateChar(formField.char,fieldValue,fieldTitle)){
                   return returnFalse(formField);
           }

           /*  if(formField.valCheck != null && !validateEtc(formField, vi)){
               return returnFalse(formField)
           }
          	사용자 정의 validation을 할 경우 fncUserValidation으로 만들어서 사용한다.
           if(fncUserValidation != null && formField.valCheck != null && !fncUserValidation(formField, vi)){
               return returnFalse(formField)
           }*/

       } // hidden if
   } // end for
   return true;
}




/* 문자 체크 모음 함수 (negative 임) */
function validateChar(char,fieldValue,fieldTitle){
    if(char.indexOf("k") >= 0 && !checkChar(fieldValue, "k")){
    	alert("Other characters than Korean letters are also required " + fieldTitle);
        return false;
    }
    if(char.indexOf("e") >= 0 && !checkChar(fieldValue, "e")){
    	alert("Other characters than English letters are also required " + fieldTitle);
        return false;
    }
    if(char.indexOf("n") >= 0 && !checkChar(fieldValue, "n")){
    	alert("Other characters than numeric values are also required " + fieldTitle);
        return false;
    }
    return true;
}

/* 공통적이지만 필수적이지 않은 필드들 체크*/
function validateEtc(formField, index){
    var fieldName = formField.name;
    var fieldValue = trimStr(formField.value);
    var fieldTitle = formField.alt;

    if(fieldName.toUpperCase().indexOf("MAIL") > -1 && fieldValue !="" && !checkEmail(fieldValue) ){
    	alert(fieldTitle+" is Invalid Email Address.\n\n"+" ex) sds@samsung.com");
        return false;
    }
    if(fieldName.toUpperCase().indexOf("URL") > -1 && fieldValue !="" && !checkURL(fieldValue) ){
    	alert(fieldTitle+" is Invalid Web Address.\n\n"+" ex) www.samsungsds.co.kr");
        return false;
    }
    if(fieldName.toUpperCase().indexOf("PHONE") > -1 && fieldValue !="" && !checkPhoneStr(fieldValue) ){
    	alert(fieldTitle+" is Invalid Telephone Number.\n\n"+" ex) 02-202-0020");
        return false;
    }
    return true;
}


/* value를 trim 처리 */
function trimStr(str) {
    if(str == null)
        return '';
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

    for(var ii=0; ii<len; ii++){
        temp = formValue.charAt(ii) ;

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

    for(var ii=0; ii<len; ii++){
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
	var bytes = stringByteSizeUtf8_2(formValue) ;
	
	if(bytes>maxlength) {
		return false ;
	} else {
		return true ;
	}
}

/** UTF-8 에서 최소길이 체크 **/
function checkMinLengthUtf8(formValue, minlength){
	var bytes = stringByteSizeUtf8_2(formValue) ;
	
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


function stringByteSizeUtf8_2(str) {
    if (str == null || str.length == 0) {
    	return 0;
    }

    var size = 0; var itemp = 0;
    for (var i = 0; i < str.length; i++) {
		itemp = charByteSizeUtf8(str.charAt(i));
        if(itemp==3) size += 1;
        else size += itemp;
    }

    return size;
}


/* 허용하지 않는 문자 체크 (recommended)*/
function checkSpecialChar(fieldValue, str) {

    var re=new RegExp(str);

    if(re.test(fieldValue)){
        return true;
	}
    return false;
}

/* 허용하지 않는 문자 체크 2 */
function checkSpecialChar2(fieldValue, str) {
    var retCode = 0;
    for (var sc = 0; sc < str.length; sc++) {
        var code = str.charCodeAt(sc);
        var ch = str.substr(sc,1).toUpperCase();

        code = parseInt(code);
        if(fieldValue.indexOf(ch) >= 0){
            return true;
        }
    }
    return false;
}

/* 문자 체크
    type = k : 한글
                e : 영어
                n : 숫자
*/
function checkChar(str, type){
    var retCode = 0;
    for (var i = 0; i < str.length; i++) {
        var code = str.charCodeAt(i);
        var ch = str.substr(i,1).toUpperCase();

        code = parseInt(code);
        if (type=="k" && (ch < "0" || ch > "9") && ( ch < "A" || ch > "Z") && ((code > 255) || (code < 0))) {
            return true;
        }
        if (type=="e" && (ch >= "A") && (ch <= "Z")) {
            return true;
        }
        if (type=="n" && (ch >= "0" && ch <= "9")) {
            return true;
        }
    }
    return false;
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

    var sStr = fieldValue;
    var ik;
    var jk=0;
    var tLen = sStr.length +1 ;
    if(fieldValue.length != format.length){
        return false;
    }

    for(ik=0; ik< sStr.length; ik++){
        if(format.charAt(jk)!="9" && format.charAt(jk)!="s"){
            if(sStr.charAt(ik) != format.charAt(jk)){
                return false;
            }
        }else{
            if(format.charAt(jk) =="9" && !isTemPositiveInteger(sStr.charAt(ik))){
                return false;
            }
            if(format.charAt(jk) =="s" && isTemPositiveInteger(sStr.charAt(ik))){
                return false;
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


/*
공통
*/

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
    	for(var pc=0; pc < phones.length ; pc++){
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
    if(!checkMaxLength(formValue, maxlength)){
	var count=0;
	for(var bytes=0; bytes < maxlength; bytes++,count++){
            var temp = formValue.charAt(count) ;
	    if(escape(temp).length > 4){
                if(bytes == eval(maxlength-1)){
                    count--;
                }else{
		    bytes++;
		}
	    }
	}
        var fieldTitle = formField.alt;
        alert("The maximum number of characters for the " +fieldTitle+ " box has been exceeded." +"\n\n"  + "Maximum No. of Characters (English) :" + maxLength);
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
        return (isTemInteger(s.substring(startPos, s.length)));
    }

    function isTemEmpty(s){
        return ((s == null) || (s.length == 0));
    }

    function isTemInteger(s){
        var i;

        if(isTemEmpty(s))
            return false;

        for(i=0 ; i< s.length ; i++){
            var c = s.charAt(i);
            if(!isTemDigit(c)) return false;
        }

        return true;
    }

    function isTemDigit(c){
        return ((c >= "0") && (c <="9"));
    }


function checkPinNo(J1,J2){
    if(J1 =="111111" || J2 =="1111118"){
    	alert("Input the 13-digit resident registration number.");
	return false;
    }else{
	// 주민등록번호 1 ~ 6 자리까지의 처리
	// 주민등록번호에 숫자가 아닌 문자가 있을 때 처리
        for(var i=0;i<J1.length;i++){
	    if (J1.charAt(i) >= 0 && J1.charAt(i) <= 9){
	    // 숫자면 값을 곱해 더한다.
		if(i == 0)
		    SUM = (i+2) * J1.charAt(i);
		else
		    SUM = SUM +(i+2) * J1.charAt(i);
	    }else{
		 // 숫자가 아닌 문자가 있을 때의 처리
	    alert("Only numeric values are allowed.");
		return false;
	    }
	}//end of for loop

	for(i=0;i<2;i++){
            // 주민등록번호 7 ~  8 자리까지의 처리
	    if(J2.charAt(i) >= 0 && J2.charAt(i) <= 9){
                SUM = SUM + (i+8) * J2.charAt(i);
	    }else{
		// 숫자가 아닌 문자가 있을 때의 처리
	    alert("Only numeric values are allowed.");
		return false;
	    }
	}

	for(i=2;i<6;i++){
	    // 주민등록번호 9 ~ 12 자리까지의 처리
	    if (J2.charAt(i) >= 0 && J2.charAt(i) <= 9) {
	        SUM = SUM + (i) * J2.charAt(i);
	    }else{
	        // 숫자가 아닌 문자가 있을 때의 처리
	    alert("Only numeric values are allowed.");
		return fale;
	    }
	}

	// 나머지 구하기
	var checkSUM = SUM % 11;
	// 나머지가 0 이면 10 을 설정
	if(checkSUM == 0){
	    var checkCODE = 10;
	    // 나머지가 1 이면 11 을 설정
	}else if(checkSUM ==1){
	    var checkCODE = 11;
	}else{
	    var checkCODE = checkSUM;
	}
	// 나머지를 11 에서 뺀다
	var check1 = 11 - checkCODE;
	if (J2.charAt(6) >= 0 && J2.charAt(6) <= 9) {
            var check2 = parseInt(J2.charAt(6));
	}else{
	    // 숫자가 아닌 문자가 있을 때의 처리
		alert("Only numeric values are allowed.");
	}
	if(check1 != check2){
	    // 주민등록번호가 틀릴 때의 처리
		alert("Input the 13-digit resident registration number.");
	    return false;
	}else{
	    return true;
	}
    }
    
    function checkEmail(value){
    	var reg_email=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/;
    	if(!reg_email.test(value)){
        	return false ;
        }
    	
    	return true ;
    }
}

function stripHTMLtag(string) { 
   	var objStrip = new RegExp(); 
   	objStrip = /[<][^>]*[>]/gi; 
   
	if(string.replace(objStrip, "") == "&nbsp;"){
		return "";
	}
	
   	return string.replace(objStrip, "");
} 

