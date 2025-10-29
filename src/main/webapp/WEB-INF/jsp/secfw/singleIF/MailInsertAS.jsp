<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Locale"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ page import="org.springframework.web.servlet.support.RequestContext"%>
<%--
/**
 * 파    일    명 : MailInsertAS.jsp
 * 프 로 그 램 명 : 메일내역 발송록 페이지
 * 설          명 :
 * 작    성    자 : 금현서
 * 작    성    일 : 2009.11
 */
--%>
<%
    String mailTitle    = StringUtil.bvl((String) request.getAttribute("mailTitle"), "");
    String mailContent  = StringUtil.bvl((String) request.getAttribute("mailContent"), "");
    String mailHtmlGbn  = StringUtil.bvl((String) request.getAttribute("mailHtmlGbn"), "1");
    String mailEncoding = StringUtil.bvl((String) request.getAttribute("mailEncoding"), "UTF-8");

    // 사용자 로케일
    Locale lc = new RequestContext(request).getLocale();
    String locale = StringUtil.bvl(lc.getLanguage(), "en");
    String localeDisplayType = "";

    if ("en".equals(locale)) {
        localeDisplayType = "Eng";
    }

    // 메뉴네비게이션 스트링
    String menuNavi = (String) request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="secfw.page.title.log.LoginLogList" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script type="text/javascript">
<!--
var encodeItem = new Array(27);
encodeItem[0]  = new Array ("Arabic(Windows)",             "ISO-8859-6"    );
encodeItem[1]  = new Array ("Baltic(Windows)",             "WINDOWS-1257"  );
encodeItem[2]  = new Array ("Baltic(ISO)",                 "ISO-8859-4"    );
encodeItem[3]  = new Array ("Central European(ISO)",       "WINDOWS-1250"  );
encodeItem[4]  = new Array ("Central European(Windows)",   "ISO-8859-2"    );
encodeItem[5]  = new Array ("Chinese Simplified(GB18030)", "GB18030"       );
encodeItem[6]  = new Array ("Chinese Simplified(GB2312)",  "GB2312"        );
encodeItem[7]  = new Array ("Chinese Simplified(HZ)",      "GB2312"        );
encodeItem[8]  = new Array ("Chinese Traditinal(Big5)",    "BIG5"          );
encodeItem[9]  = new Array ("Cyrillic(ISO)",               "ISO-8859-5"    );
encodeItem[10] = new Array ("Cyrillic(KOI8-R)",            "KOI8-R"        );
encodeItem[11] = new Array ("Cyrillic(KOI8-U)",            "KOI8-R"        );
encodeItem[12] = new Array ("Cyrillic(Windows)",           "WINDOWS-1251"  );
encodeItem[13] = new Array ("Greek(ISO)",                  "ISO-8859-7"    );
encodeItem[14] = new Array ("Greek(Windows)",              "WINDOWS-1253"  );
encodeItem[15] = new Array ("Hebrew(Windows)",             "ISO-8859-8"    );
encodeItem[16] = new Array ("Korean(EUC)",                 "EUC-KR"        );
encodeItem[17] = new Array ("Japanese(SJIS)",              "SJIS"          );
encodeItem[18] = new Array ("Japanese(MS932)",             "MS932"         );
encodeItem[19] = new Array ("Japanese(JIS)",               "ISO-2022-JP"   );
encodeItem[20] = new Array ("Latin 9(ISO)",                "ISO-8859-15"   );
encodeItem[21] = new Array ("Thai(Windows)",               "WINDOWS-874"   );
encodeItem[22] = new Array ("Trukish(ISO)",                "ISO-8859-9"    );
encodeItem[23] = new Array ("Trukish(Windows)",            "WINDOWS-1254"  );
encodeItem[24] = new Array ("Vietnames(Windows)",          "WINDOWS-1258"  );
encodeItem[25] = new Array ("Western European(ISO)",       "WINDOWS-1252"  );
encodeItem[26] = new Array ("Unicode(UTF-8)",              "UTF-8"         );

/**
* - 페이지 초기화
*/
$(document).ready(function(){

    var frm = document.frm;

    initPage();

  //메일수신 타입 초기화 설정
  $('#rcvTypeT').attr('checked', true);

  $("#receiver_nm").keypress(function(event){
    if(event.keyCode == "13") {
      searchUser();
      $("#receiver_nm").val('');
      return false;
    }
  });

  $('#tCnt').html(0);
  $('#cCnt').html(0);
  $('#bCnt').html(0);

  //수신자 검색-ESB임직원 검색
  $('#srchReceiverBtn1').click(function(){
    searchUser();
    return;
  });

  //인코딩정보 세팅
  for(var encodeLoop=0; encodeLoop < encodeItem.length; encodeLoop++) {
    var eachItem = encodeItem[encodeLoop];
    var encodeValue = "<option value='" + eachItem[1] + "'>"
        + eachItem[0]+ "</option>";
    $('select[name=encoding]').append(encodeValue);
  }

  $('select[name=encoding] option')
    .each(function(){
      var optionValue = $(this).val();
      if(optionValue == '<%=mailEncoding%>') {
        $(this).attr('selected', true);
      }
    });

  //메일수신 타입 변경
  $('input:radio').click(function(event){
    var target = event.target;
    if(target.id == "rcvTypeT") {
      $('#receivers option:selected')
        .each(function(){
          var originValue = $(this).val();
          var originText  = $(this).text();

          var newValue;
          var newText;

          if(originValue.indexOf("c|") >= 0) {
            newValue = comStrReplace(originValue, "c|", "t|");
            newText  = comStrReplace(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /");
          } else if(originValue.indexOf("b|") >= 0) {
            newValue = comStrReplace(originValue, "b|", "t|");
            newText  = comStrReplace(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /");
          } else {
            newValue = originValue;
            newText  = originText;
          }

          $(this).val(newValue);
          $(this).text(newText);
        });
    } else if(target.id == "rcvTypeC") {
      $('#receivers option:selected')
      .each(function(){
        var originValue = $(this).val();
        var originText  = $(this).text();

        var newValue;
        var newText;

        if(originValue.indexOf("t|") >= 0) {
          newValue = comStrReplace(originValue, "t|", "c|");
          newText  = comStrReplace(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /");
        } else if(originValue.indexOf("b|") >= 0) {
          newValue = comStrReplace(originValue, "b|", "c|");
          newText  = comStrReplace(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /");
        } else {
          newValue = originValue;
          newText  = originText;
        }
        $(this).val(newValue);
        $(this).text(newText);
      });
    } else if(target.id == "rcvTypeB") {
      $('#receivers option:selected')
      .each(function(){
        var originValue = $(this).val();
        var originText  = $(this).text();

        var newValue;
        var newText;

        if(originValue.indexOf("t|") >= 0) {
          newValue = comStrReplace(originValue, "t|", "b|");
          newText  = comStrReplace(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /");
        } else if(originValue.indexOf("c|") >= 0) {
          newValue = comStrReplace(originValue, "c|", "b|");
          newText  = comStrReplace(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /");
        } else {
          newValue = originValue;
          newText  = originText;
        }

        $(this).val(newValue);
        $(this).text(newText);
      });
    }

    $('#receivers').focus();
    setTypeCount();
  });

  /**
  * 임직원 조회 function
  */
  var searchUser = function() {

    var frm = document.frm;
    var srchValue = comTrim(frm.receiver_nm.value);

    if(srchValue == '' || getByteLength(srchValue) < 4) {
      alert("<spring:message code='secfw.msg.error.nameMinByte' />");
      frm.receiver_nm.focus();
      return false;
    }

    frm.srch_user_cntnt_type.value = 'userName';
    frm.srch_user_cntnt.value = frm.receiver_nm.value;

    PopUpWindowOpen('', 800, 450, false);
    frm.target = "PopUpWindow";

    frm.srch_user_cntnt_type.value = 'userName';
    frm.srch_user_cntnt.value = srchValue;

    frm.action = "<c:url value='/secfw/esbOrg.do' />";
    frm.method.value = "listEsbEmployee";

    frm.submit();

  };

  /**
  * 버튼 동작 부분
  */
  // 취소
  $('.btnw').click(function(){
    pageAction('close');
  });

  // 메일발신
  $('.btnb').click(function(){
    pageAction('send');
  });

  // 수신자 순서조정 - UP
  $('#moveUp').click(function(){
    moveOrder('U');
  });

  // 수신자 순서조정 - DOWN
  $('#moveDown').click(function(){
    moveOrder('D');
  });

  // 수신자삭제
  $('#deleteBtn').click(function(){
    $('#receivers option:selected').remove();
  });

  /**
  * 메일순서조정
  */
  var moveOrder = function(upDown)
  {
    var rightDocLen = frm.receivers.options.length;

    var tempName = "" ;
    var tempValue = "" ;

    if(upDown == "U") {
      for ( i = 0; i < rightDocLen; i++)
      {
        if( frm.receivers.options[i].selected && i != 0 && !frm.receivers.options[i - 1].selected)
        {
          tempName = frm.receivers.options[i - 1].value ;
          tempValue = frm.receivers.options[i - 1].text ;

          frm.receivers.options[i - 1].value = frm.receivers.options[i].value ;
          frm.receivers.options[i - 1].text  = frm.receivers.options[i].text;

          frm.receivers.options[i].value = tempName ;
          frm.receivers.options[i].text  = tempValue;

          frm.receivers.options[i - 1].selected = true ;
          frm.receivers.options[i].selected = false ;
        }
        else if( i == 0 && frm.receivers.options[i].selected)
        {
          frm.receivers.options[i].selected = true ;
        }
      }
    } else {
      for ( i = (rightDocLen - 1); i >= 0; i--)
      {
        if( frm.receivers.options[i].selected && i != (rightDocLen - 1) && !frm.receivers.options[i + 1].selected)
        {
          tempName = frm.receivers.options[i + 1].value ;
          tempValue = frm.receivers.options[i + 1].text ;

          frm.receivers.options[i + 1].value = frm.receivers.options[i].value ;
          frm.receivers.options[i + 1].text  = frm.receivers.options[i].text;

          frm.receivers.options[i].value = tempName ;
          frm.receivers.options[i].text  = tempValue;

          frm.receivers.options[i + 1].selected = true ;
          frm.receivers.options[i].selected = false ;
        }
        else if( i == (rightDocLen - 1) && frm.receivers.options[i].selected)
        {
          frm.receivers.options[i].selected = true ;
        }
      }
    }
  };

  /**
  * Text - HTML 변환
  */
  if("0" == '<%=mailHtmlGbn%>') {
    $('#body_type0').attr('checked',true);
        $('input[name=checkedBodyType]').val('0');
    $('#textEdit').show();
    $('#htmlEdit').hide();
  } else if("1" == '<%=mailHtmlGbn%>') {
          $('#body_type1').attr('checked', true);
          $('input[name=checkedBodyType]').val('1');
          $('#textEdit').hide();
          $('#htmlEdit').show();
        }

        $('input[name=body_type]').click(function() {
          var typeValue = $(this).val();
          bodyChange(typeValue);
        });

        var bodyChange = function(iType) {

          var wecObj = document.getElementById("wec");
          ;
          var textObj = document.getElementById("body");

          var strBody = wecObj.Value;
          var strBody1 = textObj.value;
          var strBody2 = wecObj.BodyValue; //0903 추가

          if (iType == "0") {
            if ($('input[name=checkedBodyType').val() == "0")
              return;

            if (!confirm("<spring:message code='secfw.msg.approval.toTextMode' />")) {
              if (document.all.body_type0.checked) // Text Checked
              {
                document.all.body_type0.checked = false;
                document.all.body_type1.checked = true;
              }
              return;
            } else {
              $('#htmlEdit').hide();
              $('#textEdit').show();
            }

            textObj.value = stripHTML(strBody2); //0903 추가
            document.frm.checkedBodyType.value = '0';
            textObj.focus();
          } else {

            if ($('input[name=checkedBodyType]').val() == "1")
              return;

            $('#textEdit').hide();
            $('#htmlEdit').show();

            wecObj.EditMode = 1;
            wecObj.Value = "<HTML><HEAD>" + document.frm.wec.HeadValue + "</HEAD><BODY>" + textToHtml(strBody1) + "</BODY></HTML>";

            document.frm.checkedBodyType.value = '1';
            wecObj.focus();
          }
        };

        /**
         * bodySize - 변경
         */
        $('#bodySize').change(function() {
          var changeSize = $(this).val();
          onLoadBodyHeight(changeSize);
        });

        var initChangeBodyHeight = function(screenSize) {
          var bodyHeight = 100;

          if (screenSize == 100)
            bodyHeight = 100;
          else if (screenSize == 200)
            bodyHeight = 200;
          else if (screenSize == 300)
            bodyHeight = 300;

          //본문입력창 저장된 값으로 resize
          onLoadBodyHeight(bodyHeight);
        };

        var onLoadBodyHeight = function(iFormat) {
          if (iFormat == 100) {
            document.getElementById("wec").style.height = "300px";//Activesquare 7
            document.getElementById("body").style.height = "300px";
          } else if (iFormat == 200) {
            document.getElementById("wec").style.height = "525px";
            document.getElementById("body").style.height = "525px";
          } else if (iFormat == 300) {
            document.getElementById("wec").style.height = "825px";
            document.getElementById("body").style.height = "825px";
          }

          iFormat = parseInt(iFormat);
          if (isNaN(iFormat))
            iFormat = 100;

          return;
        };

        //텍스트-HTML 초기설정
  bodyChange('<%=mailHtmlGbn%>');

        //본문 사이즈 체크
        var isOverDocSize = function(limit) {
          var object1 = document.wec.GetDocumentSize();
          if (object1 > limit * 1024)
            return true;
          else
            return false;
        };

        /**
         * 버튼 동작 부분
         */
        var pageAction = function(flag) {
          var frm = document.frm;

          if (flag == "send") {

            //테스트를 위하여 개발인력 추가
            //var html = "<option value='t|namwon.shin@stage.samsung.com'>"
            //  + '신남원' + " / " + '수신' + " / " + '선임' + " / " + '협력사' + " / " + '유비포럼' + " / " + 'namwon.shin@samsung.com'
            //  + "</option>";
            //$('#receivers').append(html);

            //상태값설정
            frm.status.value = '0';

            //수신인여부 체크
            var receiverCnt = 0;
            $('#receivers option').each(function() {
              receiverCnt = receiverCnt + 1;
            });

            if (!(receiverCnt > 0)) {
              //수신인을 입력해 주십시오.
              alert('<spring:message code="secfw.msg.mail.alert2" />');
              return;
            }

            //본문의 크기 제한(1024KB 이하로)
            if ($('input[name=checkedBodyType]').val() == "1") {
              if (isOverDocSize(1024)) {
                //alert('본문의 크기는 1M를 초과할수 없습니다.');
                alert('<spring:message code="secfw.msg.mail.alert3" />');
                return;
              }
            } else {
              var textVal = $('textarea[name=body]').html();
              if (CheckByte(textVal) > 1024 * 1024) {
                alert('<spring:message code="secfw.msg.mail.alert3" />');
                return;
              }
            }

            //수신인 제한 (100명 이하)
            if (document.frm.receivers.options.length > 100) {
              alert('<spring:message code="secfw.msg.mail.alert5" />');
              return;
            }

            //첨부파일 갯수 제한(50개이하)

            //FORM Validation
            if (validateForm(document.frm) != false) {

              $('#receivers option').each(function() {
                $(this).attr("selected", true);
              });

              frm.body_mime.value = frm.wec.MIMEValue;

              if(uploadManager.getTotalFileCount() < 1) {
                  afterUpload();
              } else {
                  uploadManager.transferByForce();
              }


              //첨부파일 업로드
              /* fileList.UploadFile(function(uploadCount) {
                if (uploadCount == -1) {
                  initPage();
                  alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
                  return;
                }

                var options = {
                  url : "<c:url value='/secfw/esbMail.do?method=sendMail' />",
                  type : "post",
                  dataType : "json",
                  beforeSubmit : function(formData, form) {
                    //comLayerPopCenter('ProgressLayer1');
                  },
                  success : function(responseText, returnMessage) {
                    alert(responseText.returnMessage);
                    window.close();
                  }
                };
                $("#frm").ajaxSubmit(options);

              }) */;

              /*        var options = {
               url: "<c:url value='/secfw/esbMail.do?method=sendMail' />",
               type: "post",
               dataType: "json",
               beforeSubmit: function(formData, form){
               //comLayerPopCenter('ProgressLayer1');
               },
               success: function(responseText,returnMessage) {
               alert(responseText.returnMessage);
               window.close();
               }
               }
               $("#frm").ajaxSubmit(options);
               */
            }
          } else if (flag == "refresh") {

            frm.action = "<c:url value='/secfw/esbMail.do' />";
            frm.method.value = "goInsertMail";
            frm.submit();

          } else if (flag == "temp") {

            //수신인여부 체크
            var receiverCnt = 0;

            $('#receivers option').each(function() {
              receiverCnt = receiverCnt + 1;
            });

            if (!(receiverCnt > 0)) {
              //수신인을 입력해 주십시오.
              alert('<spring:message code="secfw.msg.mail.alert2" />');
              return;
            }

            //Form Validation
            if (validateForm(document.frm) != false) {

              //첨부파일 업로드
              fileList.UploadFile(function(uploadCount) {
                if (uploadCount == -1) {
                  initPage();
                  alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
                  return;
                }
              });
              //        var uploadCount = fileList.UploadFile();

              $('#receivers option').each(function() {
                $(this).attr("selected", true);
              });

              frm.status.value = 'T';

              $('#receivers option').each(function() {
                $(this).attr("selected", true);
              });

              frm.target = '_self';
              frm.action = "<c:url value='/secfw/esbMail.do' />";
              frm.method.value = "sendMail";
              frm.submit();

            }
          } else if (flag == "close") {
            self.close();
          }
        };

      });

  //첨부파일 호출
  function initPage() {
    /* var frm = document.frm;

    frm.target = "fileList";
    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value = "forwardComFilePage";
    frm.submit(); */
  }

  /**
   * 수신, 참조, 비밀참조 수 설정
   */
  function setTypeCount() {

    var totalTCnt = 0;
    var totalCCnt = 0;
    var totalBCnt = 0;

    $('#receivers option').each(function() {

      var val = $(this).val();

      if (val.substr(0, 1) == 't') {
        totalTCnt++;
      } else if (val.substr(0, 1) == 'c') {
        totalCCnt++;
      } else if (val.substr(0, 1) == 'b') {
        totalBCnt++;
      }
    });

    $('#tCnt').html(totalTCnt);
    $('#cCnt').html(totalCCnt);
    $('#bCnt').html(totalBCnt);

  }

  /**
   * 임직원정보 Setting
   */
  function setUserInfos(obj) {

    var name = obj.cn;
    var type;
    var jikgupCd = obj.eptitlenumber;
    var jikgupNm = obj.title;
    var deptNm = obj.department;
    var compNm = obj.o;
    var email = obj.mail;
    var typeNm;

    type = $('input:radio:checked').val();

    if (type == 't') {
      typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeT" />';
    } else if (type == 'c') {
      typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeC" />';
    } else if (type == 'b') {
      typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeB" />';
    }

    if (email == null || comTrim(email) == '') {
      alert("<spring:message code='secfw.page.field.singleIF.noEmailAccount'/>");
      return;
    }

    var flag = true;

    //기존 추가된 사용자 인지 체크
    $('#receivers option').each(function() {
      var originValue = $(this).val();
      if (originValue.indexOf(email) >= 0) {
        flag = false;
      }
    });

    if (!flag) {
      //이미 등록되어 있습니다.
      alert('<spring:message code="secfw.msg.mail.alert4" />');
      return;
    } else {
      var html = "<option value='" + type + "|" + email + "'>" + name + " / " + typeNm + " / " + jikgupNm + " / " + deptNm + " / " + compNm + " / "
          + email + "</option>";

      $('#receivers').append(html);
    }

    setTypeCount();
  }

  /**
   * 임직원정보 Setting
   */
  function setCPUserInfos(listObj) {

    for ( var idx = 0; idx < listObj.length; idx++) {

      var obj = listObj[idx];

      var name = obj.cn;
      var type;
      var jikgupNm = obj.title;
      var deptNm = obj.department;
      var compNm = obj.o;
      var email = obj.mail;
      var typeNm;

      type = $('input:radio:checked').val();

      if (type == 't') {
        typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeT" />';
      } else if (type == 'c') {
        typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeC" />';
      } else if (type == 'b') {
        typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeB" />';
      }

      if (email == null || comTrim(email) == '') {
        alert(name + "<spring:message code='secfw.page.field.singleIF.noEmailAccount'/>");
        return;
      }

      var flag = true;

      //기존 추가된 사용자 인지 체크
      $('#receivers option').each(function() {
        var originValue = $(this).val();
        if (originValue.indexOf(email) >= 0) {
          flag = false;
        }
      });

      if (!flag) {
        //alert('이미 등록된 사용자 입니다.');
        continue;
      } else {
        var html = "<option value='" + type + "|" + email + "'>" + name + " / " + typeNm + " / " + jikgupNm + " / " + deptNm + " / " + compNm + " / "
            + email + "</option>";

        $('#receivers').append(html);
      }
    }

    setTypeCount();
  }

  function afterUpload() {
      var options = {
                url : "<c:url value='/secfw/esbMail.do?method=sendMail'/>",
                type : "post",
                dataType : "json",
                success : function(responseText, returnMessage) {
                  alert(responseText.returnMessage);
                  window.close();
                }
              };
              $("#frm").ajaxSubmit(options);
  }

//-->
</script>
</head>
<body>
<div id="wrap">
  <div id="container">
    <!-- location -->
    <div class="location">
      <img src="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"> <c:out value='${menuNavi}' escapeXml="false" />
    </div>
    <!-- //location -->
    <!-- title -->
    <div class="title">
      <h3><spring:message code="secfw.page.field.singleIF.sendMail" /></h3>
    </div>
    <!-- // title -->
    <!-- content -->
    <div id="content">
      <div id="content_in">
        <form:form id="frm" name="frm" method="post" autocomplete="off">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          <!-- ESB사용자검색 -->
          <input type='hidden' name='srch_user_cntnt_type' />
          <input type='hidden' name='srch_user_cntnt' />
          <input type='hidden' name='doSearch' value='Y' />
          <!-- Controller Method -->
          <input type='hidden' name='method' value='' />
          <input type='hidden' name='msg_key' value='11' />
          <!-- 텍스트-편집기입력 구분 -->
          <input type='hidden' name='checkedBodyType' value='' />
          <input type='hidden' name='body_mime' value='' />
          <!-- 첨부파일정보 -->
          <input type="hidden" name="fileInfos" value="" />
          <input type="hidden" name="sys_gbn" value="mail" />
          <input type="hidden" name="view_gbn" value="upload" />
          <!-- 이중등록 방지 -->
          <input type="hidden" name="status" value="" />
          <input type="hidden" name="TOKEN" value="<%=Token.set(request)%>">
          <div class='btnwrap'>
            <span class='btnb'>
              <span class="btn_all_w"><span class="mail"></span><a><spring:message code="secfw.page.button.send" /></a></span>
            </span>
            <span class='btnw'>
              <span class="btn_all_w"><span class="cancel"></span><a><spring:message code="secfw.page.field.approval.cancel" /></a></span>
            </span>
          </div>
          <table class='list_basic'>
            <colgroup>
               <col width="15%"/>
               <col width="*%"/>
               <col width="20%"/>
            </colgroup>
            <tr>
              <th><spring:message code="secfw.page.field.mail.subject" /></th>
              <td colspan='2'>
                <input type='text' name='subject' alt='<spring:message code="secfw.page.field.mail.subject" />' value='<%= mailTitle %>' class='text w_96' fieldTitle='<spring:message code="secfw.page.field.mail.subject" />' required="*" maxLength="500">
              </td>
            </tr>
            <tr>
              <th><spring:message code="secfw.page.field.mail.receiver" /></th>
              <td colspan='2'>
                <input type='text' id="receiver_nm" name='receiver_nm' class='text w_96'>
                <div id='srchReceiverBtn1' class='confirm<%= localeDisplayType %>'></div>
              </td>
            </tr>
            <tr>
              <th><spring:message code="secfw.page.field.mail.receivers" /></th>
              <td>
                <select id="receivers" name="receivers" multiple="multiple" class='IpTextLe'style='width: 99%; height: 100px;border:1px solid #cdcdcd'></select>
              </td>
              <td style='border-left:1px solid #fff'>
                <table id='opa' style='margin:-5px -7px'>
                  <tr>
                    <td>
                      <input id="rcvTypeT" type="radio" name="rdSendItem" value="t" textValue="<spring:message code="secfw.page.field.mail.rcvTypeT" />"> <spring:message code="secfw.page.field.mail.rcvTypeT" />[<span id="tCnt"></span>]
                    </td>
                    <td>
                      <span class="btn_all_b" id="deleteBtn"><span class="delete1"></span> <a><spring:message code="clm.page.button.delete" /></a></span>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input id="rcvTypeC" type="radio" name="rdSendItem" value="c" textValue="<spring:message code="secfw.page.field.mail.rcvTypeC" />"> <spring:message code="secfw.page.field.mail.rcvTypeC" />[<span id="cCnt"></span>]
                    </td>
                    <td>
                      <span class="btn_all_b" id="moveUp" name="moveUpBtn"><span class="up1"></span><a><spring:message code="las.page.field.approval.moveUp" /></a></span>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input id="rcvTypeB" type="radio" name="rdSendItem" value="b" textValue="<spring:message code="secfw.page.field.mail.rcvTypeB" />"> <spring:message code="secfw.page.field.mail.rcvTypeB" />[<span id="bCnt"></span>]
                    </td>
                    <td>
                      <span class="btn_all_b" id="moveDown" name="moveDownBtn"><span class="down1"></span><a><spring:message code="las.page.field.approval.moveDown" /></a></span>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <th><spring:message code="secfw.page.field.mail.mailCntnt" /></th>
              <td colspan="2">
                <input type='radio' id="body_type0" name='body_type' value='0'>
                <spring:message code="secfw.page.field.approval.planText" />
                <input type='radio' id="body_type1" name='body_type' value='1'>
                <spring:message code="secfw.page.field.approval.richText" />
                <select id='bodySize'>
                  <option value="100">
                    <spring:message code="secfw.page.field.approval.content.size1" />
                  </option>
                  <option value="200">
                    <spring:message code="secfw.page.field.approval.content.size2" />
                  </option>
                  <option value="300">
                    <spring:message code="secfw.page.field.approval.content.size3" />
                  </option>
                </select>
                <select name='encoding'>
                </select>
              </td>
            </tr>
            <tr>
              <td colspan="3" id="htmlEdit">
                <%-- /* Namo Active Square 7 start */ --%><%@include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%><%-- /* Namo Active Square 7 end */ --%>
              </td>
            </tr>
            <tr>
              <td colspan="3" id="textEdit">
                <textarea id='body' name='body' class='IpTextLe' style='width: 100%; height: 300px;'><%= mailContent %></textarea>
              </td>
            </tr>
            <tr>
              <td colspan="3" id="file">
              	<div id="manager_container" style="width:100%;height:200px;"></div>
                <jsp:include page="/WEB-INF/jsp/secfw/common/dextupload.jsp">
                  <jsp:param name="compAction" value="/secfw/esbMail.do" />
                  <jsp:param name="compMethod" value="sendMail" />
                </jsp:include>
              </td>
            </tr>
          </table>
          <div class='btnwrap mt10'>
            <span class='btnb'>
              <span class="btn_all_w"><span class="mail"></span><a><spring:message code="secfw.page.button.send" /></a></span>
            </span>
            <span class='btnw'>
              <span class="btn_all_w"><span class="cancel"></span><a><spring:message code="secfw.page.field.approval.cancel" /></a></span>
            </span>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<script type="text/javascript" src="/script/clms/footer.js"></script>
<!-- footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />
</body>
<script type="text/javascript" for="wec" event="OnInitCompleted()">
    var wecObj = document.wec;
    wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
    wecObj.SetDefaultFontSize("9");
    wecObj.EditMode = 1;
    wecObj.Value = document.frm.body.value;
</script>
</html>