/* ====================================================================
   기       능 : 추가할 법무담당자명이 리스트에 존재하는지 체크한다.
   최초 작성일 : 2001-12-28
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmIsNameExist(sName) 
{

	var form = document.frm;
	for ( var i = 0 ; i < form.CmRespmanList.length ; i++ ) 
	{
		if (form.CmRespmanList[i].text == sName) 
		{
			return true;
		}
	}
	
	return false;
}

/* ====================================================================
   기       능 : 추가할 법무담당자명이 리스트에 존재하는지 체크한다.
   최초 작성일 : 2001-12-28
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmIsCheckedValue() 
{

	var form = document.frm;
	for ( var i = 0 ; i < form.CmRespDBList.length ; i++ ) 
	{
		if ( form.CmRespDBList[i].selected ) 
		{
			var name;
			name = form.CmRespDBList[i].text;
			name = name.substring(0,5);
						
			CmRespListSet(name,form.CmRespDBList[i].value)
			
			//CmRespListSet(form.CmRespDBList[i].text,form.CmRespDBList[i].value)
			return
		}
	}
	
}
/* ====================================================================
   기       능 : 선택한 법무담당자를 리턴.
   최초 작성일 : 2001-12-28
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmIsReturnCheckedValue() 
{

	var form = document.frm;
	for ( var i = 0 ; i < form.CmRespDBList.length ; i++ ) 
	{
		if ( form.CmRespDBList[i].selected ) 
		{
			//alert(form.CmRespDBList[i].text)
			return form.CmRespDBList[i].text
		}
	}
	
}

function CmRespList()
{

	var form = document.frm;
	var thename = "";
	var nLen
	
	//alert('목록 갯수: ' + form.CmRespmanList.length);
	
	//thename = form.CmRespman.value;
	//nLen = 	form.CmRespman.value.length;
	//if ( nLen > 0 )
	//{
		//법무담당자 리스트의 갯수를 체크한다. MAX는 3명까지 추가할수 있다.
		//cthename = '- ' + thename;
		cthename = CmIsReturnCheckedValue();
		
		if ( cthename == "법무담당자를 선택하세요" )  
		{
			return;
		}
		if ( form.CmRespmanList.length > 0 )
		{
			for ( var i = 0 ; i < form.CmRespmanList.length ; i++ ) 
			{
				if ( CmIsNameExist(cthename) )
				{
					alert("입력하신 법무담당자는 이미 추가목록에 있습니다.");
					return;				
				}
			}
		}
		
		if ( form.CmRespmanList.length > 3 ) 
		{
			alert("법무담당자는 최대 3명까지 지정하실수 있습니다.");
			return;				
		}			
		
		CmIsCheckedValue();
		
		/*
		else
		{				
			var winpos = "left=" + ((window.screen.width-570)/2) + ",top=" + ((window.screen.height-200)/2-110);
			var winstyle="height=250,width=470,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes,copyhistory=no," + winpos;
	       		window.open('CmContractRequestView_Sect.asp?Snm=' + thename,'법무담당자리스트',winstyle);
	       		
	       		return true;
		}
		*/	       		
	/*
	}
	else
	{

               	errorAlertMessage =  "추가하실 법무담당자 이름을 입력하세요.;"	
		alert(errorAlertMessage.split(';').join('\n'))
		form.CmRespman.focus();
		return;			
	}*/		       	
       	 
}

/* ====================================================================
   기       능 : 최종 선택된 값을 Hidden값에 넣어서 서버에 넘겨준다.
   최초 작성일 : 2001-12-28
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function  CmSendList()
{

	var form = document.frm;
	var chString = "";
	
	if ( form.CmRespmanList.length > 0 )
	{	
		for ( var j=0; j < form.CmRespmanList.length; j++)
		{
			if ( chString > "" ) chString += ";";
			chString += form.CmRespmanList[j].value;
		}		
	}

	//alert(chString)
	form.CmRespData.value = chString;
		
}

/* ====================================================================
   기       능 : Open창에 현재 입력값을 받아서 Listcontrol에 넣는다.
   최초 작성일 : 2001-12-28
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function  CmRespListSet(sName,sValue)
{

	var form = document.frm;

	form.CmRespmanList[form.CmRespmanList.length] = new Option(sName, sValue);
	       		
}

/* ====================================================================
   기       능 : ListControl의 Queue에서 삭제하는 함수.
   최초 작성일 : 2001-12-28
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmCancelList() 
{

	var form = document.frm;
	
	var sfileListIdx   = new Array();
	var nSelect = 0;

	
	for (var i = form.CmRespmanList.length - 1 ; i >= 0 ; i-- ) 
	{
		if (form.CmRespmanList[i].selected ) 
		{
			sfileListIdx[nSelect]   = i;
			nSelect++;
			form.CmRespmanList[i].selected = false;
		}
	}
	
	if ( nSelect == 0 ) {
		alert( "선택된 법무담당자가 없습니다." );
		return;
	}
	// MULTI로 선택된 항목을 지워준다.
	for (var i = 0; i < nSelect; i++) {
		form.CmRespmanList[sfileListIdx[i]] = null;
	}
	
	//testt()
	
	return;
}



/* ====================================================================
   기       능 : 문자열의 길이를 알려주는 함수(한글: 2Byte 포함)
   최초 작성일 : 2001-12-17
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function charSize(s) 
{
	var i, len=0;
	for(i=0;i < s.length; i++) (s.charCodeAt(i) > 255)? len+=2:len++;
	return len;
}




	
/* ====================================================================
   기       능 : 검색일의 값을 체크하는 함수!
   최초 작성일 : 2002-01-09
   리   턴  값 : 0 : True, 1:날짜값길이 초과, 2:허용되지 않는문자 삽입
   		3 : 잘못된 년도, 4 : 잘못된 월, 5 : 잘못된 일자
   		  
   작   성  자 : 최명석  
====================================================================== */
function  validateDate(sChar,sStartOrEnd)
{

       	var sIsDifinedDate   = "0123456789";
	var sCharValue   = "";
	var sReturnValue = "OK";
	var cStartOrEnd  = "";
	       	
    	var nLen;
    	sCharValue = sChar.value;
    	nLen = sChar.value.length;

	if ( nLen < 8 )
	{
			sReturnValue = "날짜 \'[" + sStartOrEnd + "\]' 항목이 잘못되었습니다.\n\n- 최대 입력문자길이는 8자까지입니다.";
	}
	else
	{		 
	    	for ( i=0; i<nLen; i++)
	    	{
			if ( sIsDifinedDate.indexOf(sChar.value.substring(i,i+1))< 0) 
			{
				sReturnValue = "날짜 \'[" + sStartOrEnd + "\]' 항목이 잘못되었습니다.\n\n- 접수일은 숫자만 허용됩니다.";
			}
		}    		
		
		
		//alert(parseFloat(sCharValue.cMid(4,2)))
		//alert(parseFloat(sCharValue.cMid(6,2)))		
		
				
		// 각 날자값 체크!
		if ( ( parseFloat(sCharValue.cMid(0,4))< parseInt(1900) ) || (parseFloat(sCharValue.cMid(0,4))> parseInt(9999)) )
		{
			sReturnValue = "날짜 \'[" + sStartOrEnd + "\]' 항목이 잘못되었습니다.\n\n- 년도값이 잘못입력되었습니다.\n\n- 입력형식은 \'2002\' 으로 적어주세요.";;
		}	

		if ( ( parseFloat(sCharValue.cMid(4,2)) < parseInt(1) ) || ( parseFloat(sCharValue.cMid(4,2)) > parseInt(12)) )
		{

			sReturnValue = "날짜 \'[" + sStartOrEnd + "\]' 항목이 잘못되었습니다.\n\n- 달값이 잘못입력되었습니다.\n\n- 입력형식은 \'01\' 으로 적어주세요.";;
		}			
		
		if ( ( parseFloat(sCharValue.cMid(6,2)) < parseInt(1) ) || ( parseFloat(sCharValue.cMid(6,2)) > parseInt(31)) )
		{
			sReturnValue = "날짜 \'[" + sStartOrEnd + "\]' 항목이 잘못되었습니다.\n\n- 일자값이 잘못입력되었습니다.\n\n- 입력형식은 \'01\' 으로 적어주세요.";;
		}	

	}		

	return sReturnValue;
}

/* ====================================================================
   기       능 : MID함수 구현!
   최초 작성일 : 2002-01-09
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function Private_Mid(inStart,inLen) 
{
  	var iEnd;
  
  	if (!inLen)
    		iEnd = this.length;
  	else
    		iEnd = inStart + inLen;
    
  	return this.substring(inStart,iEnd);
  	
}

/* ====================================================================
   기       능 : Mid함수 프로토타입 생성!
   최초 작성일 : 2002-01-09
   작   성  자 : 최명석  
====================================================================== */
String.prototype.cMid      = Private_Mid;
// HTML TAG 넣지 못하기..
function checkTag(obj) {
  var txt = obj.value.toLowerCase();
  if(txt.indexOf("<html")  > -1  ||
     txt.indexOf("<meta")  > -1  ||
     txt.indexOf("<scri")  > -1  ||
     txt.indexOf("<title") > -1  ||
     txt.indexOf("<body")  > -1  ||
     txt.indexOf("<input") > -1  ||
     txt.indexOf("<table") > -1  ||
     txt.indexOf("<form")  > -1  ||
     txt.indexOf("<tr") > -1  ||
     txt.indexOf("<td") > -1  ||
     txt.indexOf("<!-") > -1  ||
     txt.indexOf("-->") > -1  ||
     txt.indexOf("<a")  > -1  ||
     txt.indexOf("<h")  > -1  ||
     txt.indexOf("<b")  > -1  ||
     txt.indexOf("<p")  > -1  ||
     txt.indexOf("<\%") > -1  ||
     txt.indexOf("</")  > -1 ) {
     alert("HTML 태그를 직접 입력하시면 안됩니다..!!\n(예 : <a, <h, <b, <p, <tr, <td 등등)");
     return true;
  }else {
    return false;
  }
}



/* ====================================================================
   기       능 : 사용자가 선택한 파일이 목록에 존재하는지 체크.
   최초 작성일 : 2001-12-17
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmIsfileExist(sPath) 
{

	var form = document.frm;

	for ( var i = 0 ; i < form.CmattachList.length ; i++ ) 
	{
		if (form.CmattachList[i].value == sPath) 
		{
			return true;
		}
	}
	
	return false;
}

/* ====================================================================
   기       능 : 파일 문자열에서 이름만을 분리하여 보여준다.
   최초 작성일 : 2001-12-28
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function  CmSplitPathName(sPath)
{
	var  ReturnValue = "";
	
	ReturnValue = sPath.substring( sPath.lastIndexOf('\\') + 1 , sPath.length);

	return ReturnValue;
}

/* ====================================================================
   기       능 : 파일첨부시 해당 파일을 ListControl의 Queue에 저장.
   최초 작성일 : 2001-12-17
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmAttachFile() 
{
	var form = document.frm;
	var theattach = "";
	
	// 파일첨부여부  체크
	if ( !CmIsExistCheckError() ) 
	{
		alert("\"찾아보기\" 버튼을 눌러서 첨부할 파일을 선택하여 주십시오.");
		return;
	}

	// 현재 활성화 되어 있는 Form값을 가져와서..
	theattach = CmIsFileShowOption();
	theattach = CmSplitPathName(theattach)
	
	
	// ListControl에 올려놓은다.(해당 목록이 있는지 체크포함)
	if ( theattach.length > 0 )
	{
		for ( var i = 0 ; i < form.CmattachList.length ; i++ ) 
		{
			if ( CmIsfileExist(theattach) )
			{
				alert("선택하신 파일은 이미 첨부목록에 있습니다.");
				return;				
			}
		}
		
		form.CmattachList[form.CmattachList.length] = new Option('- '+theattach, theattach);
		                                                         // Text   &   Value 
	}
	
	// Call : 리스트에 현재 등록된 파일인덱스를 가져온다.
	//alert('인덱스: ' + CmIsFileShowIndex())
	
	// Call : 리스트에 등록된 파일이름을 히든값에 넣은다.
	CmAttachPutHiddenValue(theattach, CmIsFileShowIndex());


	return;
}  


/* ====================================================================
   기       능 : 첨부된 파일목록 리스트를 서버로 전송할 문자열 생성.
   최초 작성일 : 2001-12-17
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmAttachFilePut()
{

	var form = document.frm;
	var formattachValue = "";
			
	        for(i = 0; i < form.CmattachList.length; i++) 
	        {
		      if (formattachValue > "") { formattachValue += ";"; }
	       
	              if (form.CmattachList.options[i].value  > "") 
	              {
			  formattachValue += form.CmattachList.options[i].value;
		      }				
	       
		} 
	
	return formattachValue
}

	
/* ====================================================================
   기       능 : 해당 파일을 ListControl의 Queue에서 삭제하는 함수.
                 INPUT FILE TYPE의 해당값을 NULL로 셋팅
   최초 작성일 : 2001-12-17
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmAttachCancelFile() 
{

	var form = document.frm;
	
	var sfileListIdx   = new Array();
	var nSelect = 0;

	
	for (var i = form.CmattachList.length - 1 ; i >= 0 ; i-- ) 
	{
		if (form.CmattachList[i].selected ) 
		{
			sfileListIdx[nSelect]   = i;
			nSelect++;
			form.CmattachList[i].selected = false;
		}
	}
	
	if ( nSelect == 0 ) {
		alert( "선택된 파일 항목이 없습니다." );
		return;
	}
	// MULTI로 선택된 항목을 지워준다.
	for (var i = 0; i < nSelect; i++) {
		form.CmattachList[sfileListIdx[i]] = null;
	}

	CmAttachCancelFileType()
	
	return;
}


/* ====================================================================
   기       능 : ListControl에 해당값이 존재하는지 체크!
   최초 작성일 : 2001-12-19
   작   성  자 : 최명석  
   수   정  자 : 
====================================================================== */
function CmIsExistAttachFile() 
{

	var form        = document.frm;

	if ( form.CmattachList.length > 1 )
	{
		return true;
	}
	
	return false;	

}

/* ====================================================================
   기       능 : 계약관리에서 의뢰인을 선택할때 사용하는 팝업 함수.
   수   정  자 : 
====================================================================== */
function LcReqList()
{ 
	var form = document.frm;
	var sName = "";
	
	
	sName = form.MpReqManNm.value;
	
	
	if ( sName.length == 0 )
	{

      	errorAlertMessage =  "이름을 입력하여 주세요.\n;"	
		alert(errorAlertMessage.split(';').join('\n'))
		form.MpReqManNm.focus();

		return false;		
	}
	else
	{		

		 var winpos = "left=" + ((window.screen.width-450)/2) + ",top=" + ((window.screen.height-160)/2-130);
		 var winstyle="height=420,width=500,status=no,toolbar=no,menubar=no,location=no,resizable=no,"
			      +	 "scrollbars=0,copyhistory=no," + winpos;
			      
	       	 window.open('MpMainProjectInput_Sect.asp?Snm='+sName ,'의뢰인선택',winstyle);
	         return true;	
	}	         
}

//form.CmReqName.value,form.CmReqJuno.value,form.CmReqDeptName.value,form.CmReqInDept.value,form.CmReqJobGrdName.value,form.CmReqOfcTel.value
function  MpReqmanNameSet(sReqName, sReqJuno, sReqDeptName, sReqInDept, sReqJobGrdName, sReqOfcTel)
{

	var form = document.frm;

	form.MpReqJuno.value = sReqJuno;
	//HIDDEN
	form.MpReqManNm.value = sReqName;
	form.MpReqJobgrdNm.value = sReqJobGrdName;
	form.Mpreqofctel.value = sReqOfcTel;
	form.MpReqdeptnm.value = sReqDeptName;
	form.MpReqIndept.value = sReqInDept;
	
}

function  MpReqmanNameSet2(sCmno, sCmtitle, sReqName , sReqJuno, sReqDeptName, sReqInDept, sDealcomp, sCmlctype)

{
	
	var form = document.frm;

	form.Mptitle.value = sCmtitle;	
	form.MpReqManNm.value = sReqName;
	form.MpReqdeptnm.value = sReqDeptName;
	form.MpReqIndept.value = sReqInDept;
	form.MpReqJuno.value = sReqJuno;
	form.Mpdealcomp.value = sDealcomp;
	form.Mpcnlctype.value = sCmlctype;
	form.Mpcnlcno.value = sCmno;
	//alert(sCmlctype+":"+sCmno);
	
	
	form.Mpreqofctel.readOnly = true;
	form.MpReqJobgrdNm.readOnly = true;
	
	
	//form.Mpdealcomp.focus();
	form.Mptype.focus();
}
			
