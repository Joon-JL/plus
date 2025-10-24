<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
 
<%--
/**
 * Subject   : CalendarPopup.jsp
 * Author    : 금현서
 * Create On : 2009.11
 * 
 */
--%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/calendar_cul.css' />" rel="stylesheet" type="text/css">
<title>CALENDAR</title>
<script language="JavaScript">   
function bluring()
{ 
    if(event.srcElement.tagName=="A"||event.srcElement.tagName=="IMG") document.body.focus(); 
} 
document.onfocusin=bluring; 
</script>

</head>

<script language='JavaScript'>

var Selected_Date = new Date();
var Selected_Month = Selected_Date.getMonth();
var Selected_Year = Selected_Date.getYear();
var Selected_day = Selected_Date.getDate();
var Current_Date = new Date();
var Current_Year = Current_Date.getYear();
var Current_Month = Current_Date.getMonth();
var Today = Current_Date.getDate();
var gdCtrl = new Object();
var VicPopCal = new Object();
var Days_in_Month = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var Month_Label = new Array('1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12');
var IsPopModal = false;
var ifrmHeight = 0;

function fResize()
{
    if(this.name == "") return;
    var objFrame = parent.document.getElementsByName(this.name);
    var objBody = document.body; 
    ifrmHeight = objBody.scrollHeight + (objBody.offsetHeight - objBody.clientHeight); 
    if (ifrmHeight > 0) 
    {
        if(typeof objFrame == "object")
            objFrame[0].style.height = ifrmHeight; 
    }
}

function fPopCalendar(popCtrl, dateCtrl, popCal)
{
    fResize();

    parent.event.cancelBubble=true;
    
    VicPopCal = popCal;
    gdCtrl = dateCtrl;
    start();
    var point = fGetXY(popCtrl);
    
    with (VicPopCal.style) 
    {
        left = point.x;
        top    = point.y+popCtrl.offsetHeight-196;
        visibility = 'visible';
    }

    VicPopCal.focus();

	// 주간 갯수에 따른 팝업 높이 조절 
    fResize();
    with (VicPopCal.style) 
    {	
        // 4주
		if (ifrmHeight == 157) {
	    	top = point.y+popCtrl.offsetHeight-166;
		}
		// 5주
		else if (ifrmHeight == 177) {
	    	top = point.y+popCtrl.offsetHeight-186;
		}		
		// 6주
		else if (ifrmHeight == 197) {
			top = point.y+popCtrl.offsetHeight-206;
		}
    }
}

function fPopCalendarDown(popCtrl, dateCtrl, popCal)
{
    fResize();

    parent.event.cancelBubble=true;
    
    VicPopCal = popCal;
    gdCtrl = dateCtrl;
    start();
    var point = fGetXY(popCtrl);
    
    with (VicPopCal.style) 
    {
        left = point.x;
        top    = point.y+popCtrl.offsetHeight+1;
        visibility = 'visible';
    }

    VicPopCal.focus();
}

function fGetXY(aTag){
    var oTmp = aTag;
    var pt = new Point(0,0);
    do {
        pt.x += oTmp.offsetLeft;
        pt.y += oTmp.offsetTop;
        oTmp = oTmp.offsetParent;
    } while(oTmp.tagName!="BODY");
    return pt;
}

function Point(iX, iY){
    this.x = iX;
    this.y = iY;
}

function start()
{
    //window.moveTo (0,0);
    if(typeof gdCtrl.value == "undefined" || gdCtrl.value == "")
    {
        var temp_Month = Current_Month;
        document.when.year.value = Current_Year;
        document.when.month.selectedIndex = temp_Month;
        Selected_Year = Current_Year;
        Selected_Month = temp_Month;
        Make_Calendar(Current_Year, temp_Month);
    }
    else
    {
        fInitDate(gdCtrl.value);
        Make_Calendar(Selected_Year, Selected_Month);
    }
}

function fInitDate(time)
{
    var year  = "";
    var month = "";
    var day   = "";
    time = fGetNumOnly(time);

    if(time.length == 8)
    {
        year  = time.substr(0,4);
        month = time.substr(4,2)-1;
        day   = time.substr(6,2);
        Selected_Date = new Date(year, month, day);
    } 
    else
    {
        Selected_Date = new Date();
    }

    On_Year();

    Selected_Month = Selected_Date.getMonth();
    Selected_Year = Selected_Date.getYear();
    Selected_day = Selected_Date.getDate();

    document.when.year.value = Selected_Year;
    document.when.month.selectedIndex = Selected_Month;
}

function fToNumber(value)
{
    value = fGetNumOnly(value);
    if (isNaN(value))
    {
        return 0;
    }
    return 1 * value;
}

function fGetNumOnly(str)
{
    var strNew = "";
    var chkstr = "0123456789";

    for (var i = 0; i < str.length; i++) {
        if (chkstr.indexOf(str.substring(i, i + 1)) >= 0) {
            strNew += str.substring(i, i + 1);
        }
    }
    return strNew;
}

function Header(Year, Month)
{
    if (Month == 1)
    {
        if ((Year % 400 == 0) || ((Year % 4 == 0) && (Year % 100 != 0)))
        {
            Days_in_Month[1] = 29;
        }
        else
        {
            Days_in_Month[1] = 28;
        }
    }

    var Header_String = Year + "<spring:message code='common.page.field.CalendarPopup.theYear'/>" + Month_Label[Month];
    return Header_String;
}

function Make_Calendar(Year, Month)
{
    var First_Date = new Date(Year, Month, 1);
    var Heading = Header(Year, Month);
    var First_Day = First_Date.getDay() + 1;

    if (((Days_in_Month[Month] == 31) && (First_Day >= 6)) || ((Days_in_Month[Month] == 30) && (First_Day == 7)))
    {
        var Rows = 6;
    }
    else if ((Days_in_Month[Month] == 28) && (First_Day == 1))
    {
        var Rows = 4;
    }
    else
    {
        var Rows = 5;
    }

     var HTML_String    = '<table width="0%" border="0" cellspacing="0" cellpadding="0" ><tr>';
        HTML_String += '     <td width="25" height="18" align="center" bgcolor="f7f7f7"class="table-td38">S</td>';
        HTML_String += '     <td width="25" align="center" bgcolor="f7f7f7" class="table-td40">M</td>';
        HTML_String += '     <td width="25" align="center" bgcolor="f7f7f7" class="table-td40">T</td>';
        HTML_String += '     <td width="25" align="center" bgcolor="f7f7f7" class="table-td40">W</td>';
        HTML_String += '     <td width="25" align="center" bgcolor="f7f7f7" class="table-td40">T</td>';
        HTML_String += '     <td width="25" align="center" bgcolor="f7f7f7" class="table-td40">F</td>';
        HTML_String += '     <td width="25" align="center" bgcolor="f7f7f7" class="table-td39">S</td>';
        HTML_String += '  </tr></table>';
        HTML_String += '<TABLE width="100%" height="" border="0" cellpadding="0" cellspacing="0"><TR><TD width="25" height="3"></TD></TR></TABLE>';
        HTML_String += '<table width="0%" border="0" cellspacing="0" cellpadding="0" >';
    var Day_Counter    = 1;
    var Loop_Counter = 1;

    for (var j = 1; j <= Rows; j++)
    {
        HTML_String += '<TR>';
        for (var i = 1; i < 8; i++)
        {
            if ((Loop_Counter >= First_Day) && (Day_Counter <= Days_in_Month[Month]))
            {
                if ((Day_Counter == Today) && (Year == Current_Year) && (Month == Current_Month))
                {
                    HTML_String += '<TD width="25" height="20" class="table-td33" align="center" onclick=send(' + Day_Counter + ') onmouseover=mouse_over(this,"#FFECC4") onmouseout=mouse_out(this,"#FFFFFF")>';
                    HTML_String += Day_Counter + '</TD>';
                }
                else if ((Day_Counter == Selected_day) && (Year == Selected_Year) && (Month == Selected_Month))
                {
                    HTML_String += '<TD width="25" class="table-td32" align="center" onclick=send(' + Day_Counter + ') onmouseover=mouse_over(this,"#FFECC4") onmouseout=mouse_out(this,"#FFFFFF")><span class="11-greyca">';
                    HTML_String += Day_Counter + '</span></TD>';
                }
                else if (Loop_Counter % 7 == 0)
                {
                    HTML_String += '<TD width="25" class="table-td35" align="center" onclick=send(' + Day_Counter + ') onmouseover=mouse_over(this,"#FFECC4") onmouseout=mouse_out(this,"#FFFFFF")><span class="11-blue">';
                    HTML_String += Day_Counter + '</TD>';
                }
                else if (Loop_Counter % 7 == 1)
                {
                    HTML_String += '<TD width="25"  class="table-td34" align="center" onclick=send(' + Day_Counter + ') onmouseover=mouse_over(this,"#FFECC4") onmouseout=mouse_out(this,"#FFFFFF")><span class="11-red">';
                    HTML_String += Day_Counter + '</span></TD>';
                }
                else
                {
                    HTML_String += '<TD width="25"  class="table-td32" align="center" onclick=send(' + Day_Counter + ') onmouseover=mouse_over(this,"#FFECC4") onmouseout=mouse_out(this,"#FFFFFF")><span class="11-greyca">';
                    HTML_String += Day_Counter + '</span></TD>';
                }
                Day_Counter++;
            }
            else
            {
                HTML_String += '<TD width="25" class="table-td40">&nbsp;</TD>';
            }
            Loop_Counter++;
        }
        HTML_String += '</TR>';
    }
    HTML_String += '</TABLE>';
    document.all.Calendar.innerHTML = HTML_String;

    fResize();
}

function Check_Nums()
{
    if ((event.keyCode < 48) || (event.keyCode > 57))
    {
        return false;
    }
}

function On_Year()
{
    var Year = document.when.year.value;
    if (Year.length == 4)
    {
        Selected_Month = document.when.month.selectedIndex;
        Selected_Year = Year;
        Selected_day = "";
        Make_Calendar(Selected_Year, Selected_Month);
    }
}

function On_Month()
{
    var Year = document.when.year.value;
    if (Year.length == 4)
    {
        Selected_Month = document.when.month.selectedIndex;
        Selected_Year = Year;
        Selected_day = "";
        Make_Calendar(Selected_Year, Selected_Month);
    }
    else
    {
        alert("<spring:message code='common.page.field.CalendarPopup.inputCorrectYear'/>");
        document.when.year.focus();
    }
}

function Defaults()
{
    if (!document.all)
    return
    var Mid_Screen = Math.round(document.body.clientWidth / 2);
    document.when.month.selectedIndex = Current_Month;
    document.when.year.value = Current_Year;
    Selected_Month = Current_Month;
    Selected_Year = Current_Year;
    Make_Calendar(Current_Year, Current_Month);
}

function Skip(Direction)
{
    if (Direction == '+')
    {
        if (Selected_Month == 11)
        {
            Selected_Month = 0;
            Selected_Year++;
        }
        else
        {
            Selected_Month++;
        }
    }
    else
    {
        if (Selected_Month == 0)
        {
            Selected_Month = 11;
            Selected_Year--;
        }
        else
        {
            Selected_Month--;
        }
    }
    Selected_day = "";
    Make_Calendar(Selected_Year, Selected_Month);
    document.when.month.selectedIndex = Selected_Month;
    document.when.year.value = Selected_Year;
}

function send(a)
{

    var t_day, t_month, formName, textName;
    t_day = long_check(a);
    t_month = long_check(++Selected_Month);
    gdCtrl.value = Selected_Year+'-' + t_month + '-' + t_day;
    gdCtrl.focus();    
    closeCalendar();
}

function long_check(b)
{
    var rt;
    if (b < 10)
    {
        rt = '0' + b;
        return rt;
    }
    else
    {
        return b;
    }
}

function mouse_over(src,m_over)
{
    src.style.cursor = "hand";
    src.bgColor = m_over;
}

function mouse_out(src,m_out)
{
    src.style.cursor = "default";
    src.bgColor = m_out;
}

function closeCalendar()
{
    if(IsPopModal) 
    {
        self.close();
    }
    else
    {
    	parent.document.body.scrollLeft = 0;
        parent.document.body.scrollTop = 0;
    	VicPopCal.style.visibility = "hidden";
    }
}

-->
</script>
<body onload="start();" leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">
<form autocomplete="off" name="when" style="margin:0px;">

<table width="197" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#FF0000">
  <tr>
    <td height="144" align="center" bgcolor="dddddd" style="padding:3 3 3 3"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top"  bgcolor="#FFFFFF" style="padding:10 10 10 10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><table width="0%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="10"><img src="<c:url value='/images/btn/bt_calendar_prev.gif' />"  width="5" height="9" onclick="Skip('-')" style="cursor:hand;"></td>
                            <td width="53"> 
                            <select name='year' onchange="On_Year()" class="input-box">
                                    <script>
                                        for(i=2001;i<=2025;i++)
                                        {
                                            document.writeln("<OPTION value='"+i+"'>"+i+"</OPTION>");
                                        }
                                    </script>
                                    </select> 
                            </td>
                            <td width="43">
                            <select name="month" onchange="On_Month()" class="input-box">
                                    <script language='JavaScript1.2'>
                                        if (document.all)
                                        {
                                            for (j=0;j<Month_Label.length;j++)
                                            {
                                                document.writeln('<option value=' + j + '>' + Month_Label[j]);
                                            }
                                        }
                                    </script>
                                    </select>
                                    </td>
                            <td><img src="<c:url value='/images/btn/bt_calendar_next.gif' />"  width="5" height="9" onclick="Skip('+')" style="cursor:hand;"></td>
                          </tr>
                        </table></td>
                      <td align="right" class="11-greyca" style="padding-top:4" onclick="closeCalendar()" style="cursor:hand;">Close</td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td height="9"></td>
              </tr>
              <tr>
                <td height="8"> <div id="Calendar" align="center" valign="top"></div></td>
              </tr>
             
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>


</form>

<script>
<!--

    //////////////////// start script //////////////////////

    /**  
        dialog argument 는 다음과 같다.
        
        item	: Return Item.                                  // 첫번째 Argument
        month   : 0-11 for Jan-Dec; 12 for All Months.          // 두번째 Argument
        year	: 4-digit year                                  // 세번째 Argument
        format  : Date format (mm/dd/yyyy, dd/mm/yy, ...)       // 네번째 Argument
    */
    var _argObj = window.dialogArguments;
    if(typeof _argObj == "object")
    {
        var _item = _argObj.item;
        var _format = (_argObj.format == null) ? "YYYYMMDD" : _argObj.format;
        var _weekend = [0,6];
        var _gCal;
        var _gNow = new Date();
        
        IsPopModal = true;
        fPopModalCalendar(_item, _item);
    }

    function fPopModalCalendar(popCtrl, dateCtrl)
    {
        gdCtrl = dateCtrl;
        start();
        var point = fGetXY(popCtrl);
    }

//-->
</script>
</body>
</html>