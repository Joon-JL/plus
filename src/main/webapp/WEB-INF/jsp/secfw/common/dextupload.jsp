<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
String compAction = request.getParameter("compAction");
String compMethod = request.getParameter("compMethod");
%>
<script type="text/javascript" src="/dextupload/dextuploadfl/DEXTUploadFL.js"></script>
<script type="text/javascript">
DEXTUploadFL.createUploadManager(
  "manager_container", // target div containera
  "DEXTUPMAN", // id
  "/dextupload/dextuploadfl/DEXT_LIST_UP_MANAGER.swf", // swf path
  "#ffffff", // background color
  "window", // window, transparent
  "", // ko, en
  "", // reserved name
  "simple", // simple, monitor
  "ForDEXTUPMAN" // postfix name
);

var uploadManager = null;

// 업로드 관리자에서 오류가 발생하면 호출됩니다.
function onErrorForDEXTUPMAN(err) {
  alert(err.code + "\r\n" + err.message + "\r\n" + err.detail);
}

// 업로드 관리자가 준비되면 호출됩니다.
function onApplicationReadyForDEXTUPMAN() {
  // 업로드 관리자 객체를 얻습니다.
  uploadManager = DEXTUploadFL.getUploadManager("DEXTUPMAN");
  // 업로드 경로를 설정합니다. (상대경로가 아닌 전체경로가 필요합니다.)
  //uploadManager.setUploadUrl("http://localhost:8080/dextupload/samples/server/upload_process.jsp");
<c:set var='server_division'><spring:message code="secfw.server.division"/></c:set>
<c:choose>
  <c:when test="${server_division=='DEV'}">
    //uploadManager.setUploadUrl("http://localhost:8080/dextUpload.du?method=uploadFiles"); // localhost용
    uploadManager.setUploadUrl("http://slasdev.samsung.net/dextUpload.du?method=uploadFiles"); // slasdev.samsung.net 용
  </c:when>
  <c:otherwise>
    uploadManager.setUploadUrl("http://slas.sec.samsung.net/dextUpload.du?method=uploadFiles"); // slas.sec.samsung.net 용
  </c:otherwise>
</c:choose>
  // UI를 설정합니다.
  uploadManager.setUIStyle({
    buttonPanel: {
      toolButton: { visible: false, enabled: false },
      transferButton : { visible: false, enabled: false }
    },
    context: {
      edit: { visible: false, enabled: false } 
    }
  });
}

function onTransferCompletedForDEXTUPMAN() {
    var frm = document.frm;
    var cnt = uploadManager.getTotalFileCount();    
    for (var i = 0; i < cnt; i++) {
      var fi = uploadManager.getFileInfoByIndex(i);
      // 업로드가 완료된 파일만 응답 정보를 얻습니다.
      if (fi.status == "completed") {       
        frm.fileInfos.value += fi.response;
      }
    }
    
    afterUpload();
  }
</script>


