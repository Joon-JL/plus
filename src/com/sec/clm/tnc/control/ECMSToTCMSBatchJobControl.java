package com.sec.clm.tnc.control;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

 



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.service.ECMSToTCMSBatchJobService;


/**
 * Description : ECMSToTCMSBatchJobControl
 * Author   : ???
 * Date   : 2014. 07. 26
 */
public class ECMSToTCMSBatchJobControl extends CommonController {
  
 /**
  *  userManagerService ?? ? ??
  * 
  */
 private ECMSToTCMSBatchJobService ecmsToTCMSBatchJobService;
 
 public final static String IPAAS_API_URL  = "/nerp/erp_set/erp_set_zgwmkc100200_f01_di/1.0/F01_Set";
 private final static String IPAAS_API_NAME = "08ec883a-f95b-34dc-9196-b155e33fcd60";
 //Dev
 //private final static String IPAAS_API_NAME = "1dc6630a-86fa-39fe-99d1-e779261a0d37";
 
 /*public static Connection conn= null;
 private static iPaaSHTTPClient client = new iPaaSHTTPClient();*/
 public static String id = "";
 private static String m_msgId = "";
 public static int iCnt;
 public static String requestBody;
 private final static String IPAAS_API_IFKEY = "404526";
 private static Properties m_prop = new Properties();
 public final static String PROP_NAME_SERVER_URL   = "IPAAS.server_url";
 public final static String PROP_NAME_TOKEN        = "IPAAS.token";
 public final static String HTTP_METHOD_POST   = "POST";
 private URL m_url = null;
 private HttpURLConnection m_conn = null;
 public final static String HTTP_HDR_AUTH    = "Authorization";
 public final static String HTTP_HDR_ACCEPT    = "Accept";
 public final static String HTTP_HDR_CONTENT_TYPE  = "Content-Type";
 public final static String HTTP_HDR_ACTION    = "action";
 public final static String HTTP_HDR_CONDITION   = "condition";
 public final static String HTTP_HDR_MSGID    = "msgID";
 public final static String AUTH_BEARER     = "Bearer ";
 public final static String CONTENT_TYPE_APPL_JSON  = "application/json";
 public final static String ENCODING_UTF8 = "UTF-8";
 private String peCode;
 
 public void setEcmsToTCMSBatchJobService(ECMSToTCMSBatchJobService ecmsToTCMSBatchJobService) {
  this.ecmsToTCMSBatchJobService = ecmsToTCMSBatchJobService;
 }

 private PropertyService propertyService; 
 public void setPropertyService(PropertyService propertyService) {
  this.propertyService = propertyService;
 }
 
 private ComUtilService comUtilService; 
 public void setComUtilService(ComUtilService comUtilService) {
  this.comUtilService = comUtilService;
 }
 
 
 
  
 public synchronized void makeTCMSBatch() throws Exception { 
  
//  if (comUtilService.isCronServer()) {

   startTCMSBatch();
  
 // }
 }
 


 public synchronized boolean getStatusBatch() throws Exception {
  
  if(comUtilService.isCronServer()) { // ?? ????? ?? ??
   String ins1pidFile = "/las/logs/las1/las1.pid"; //las2?? ???
         
   File ins1File = new File(ins1pidFile);
      
      if(ins1File.exists()) {
       FileInputStream fis = new FileInputStream(ins1File);
       BufferedInputStream bis = new BufferedInputStream(fis);
       DataInputStream dis = new DataInputStream(bis);
       
       String ins1Pid = "";
 
       if(dis.available() != 0) {
        ins1Pid = dis.readLine();
       }
       
       RuntimeMXBean rmb = ManagementFactory.getRuntimeMXBean();
          String processId = rmb.getName();
          
          if(processId.length() > ins1Pid.length()) {
           processId = processId.substring(0, ins1Pid.length());
          }
          
          // ??? ??????? ??
          if(processId.equals(ins1Pid)) {
           return true;
          }else{
           return false;
          }
          
      }else{
          return false;
         }
  }else{
         return false;
        }
 }


/**
 * Functions    : Send data to NERP
 * I/F Key      : 404526
 * I/F Id       : MK40006
 * System        : Selms+
 * Description    : 구주 법무 계약 상태 데이터를 ERP에 전송하기 위해 API를 호출하는 REST Client 프로그램
 * Note            : 
 * Change List     :  
 * 
 * @author      : zhanbo.he
 * @version     : 1.0
 * @since       : 2020.06.30
 */
 private void startTCMSBatch() throws Exception{
  try {
   String serverDir = propertyService.getProperty("secfw.legacyinf.tnc.send.data.dir");
   
   if (!new File(serverDir).exists()) {
    new File(serverDir).mkdirs();
   }
  
   List copySendInfoForList = ecmsToTCMSBatchJobService.getAllContractsForTCMS();
   JSONArray array = new JSONArray();
   if(copySendInfoForList !=null){
	   for(int i=0;i< copySendInfoForList.size();i++){
		   JSONObject obj = JSONObject.fromObject(copySendInfoForList.get(i));
		   JSONObject reqobj = new JSONObject();
		   reqobj.put("COMP_CD", String.valueOf(obj.get("comp_cd")));
		   reqobj.put("CNTRT_ID", String.valueOf(obj.get("cntrt_id")));
		   reqobj.put("REQ_TITLE", String.valueOf(obj.get("req_title")));
		   reqobj.put("PRCS_DEPTH_NM",String.valueOf(obj.get("prcs_depth_nm")));
		   reqobj.put("DEPTH_STATUS_NM", String.valueOf(obj.get("depth_status_nm")));
		   reqobj.put("KEY_ID", String.valueOf(obj.get("key_id")));
		   reqobj.put("CNTRT_NO", String.valueOf(obj.get("cntrt_no")));
		   reqobj.put("PRCS_DEPTH", String.valueOf(obj.get("prcs_depth")));
		   reqobj.put("DEPTH_STATUS", String.valueOf(obj.get("depth_status")));
		   array.add(reqobj);
	   }
   }

    //Dev
    //String url = "http://ipaas-nerpdev.sec.samsung.net:8400" + IPAAS_API_URL;
     String url = "http://ipaas-nerp.sec.samsung.net:8400" + IPAAS_API_URL;
    //String token = "1dc6630a-86fa-39fe-99d1-e779261a0d37"; //getAreaToken(url); //DEV
    //String token = "40bf22e6-ec06-35d5-8313-ffdb7eb503f4"; //QA
     String token = "08ec883a-f95b-34dc-9196-b155e33fcd60";
     // Method call
     JSONObject requestBody = new JSONObject();
     JSONObject d = new JSONObject();
     JSONObject object = new JSONObject();
     object.put("results",array);
     d.put("F01_TO_I_IT_CONTRACT_STATUS_INFO", object);
     d.put("F01_TO_E", new JSONObject());
     requestBody.put("d", d);
     
     System.out.println("requestBody " + ": " + requestBody);
     getLogger().info("requestBody " + ": " + requestBody);
     String returnObj = callAPIByPost(url, token, requestBody.toString());
     String returnMsg = getReturnMsg(returnObj);
   
   ListOrderedMap lom = null;
   
   for (int i = 0; i < copySendInfoForList.size(); i++) {
    lom = (ListOrderedMap)copySendInfoForList.get(i);
    
    if("Y".equals((String)lom.get("close_yn"))){
     InfContCnsdreqVO vo = new InfContCnsdreqVO();
     vo.setCntrt_id((String)lom.get("cntrt_id"));
     vo.setKey_id((String)lom.get("key_id"));
     ecmsToTCMSBatchJobService.updateAfterBatch(vo);
    }
   }
   
   
  } catch(Exception e) {
   e.printStackTrace();
   getLogger().info("::::::::::::::::Exception sendLAY023307");
  }
  
  ecmsToTCMSBatchJobService.insertAllContractsForTCMS();
  getLogger().info("::::::::::::::::End of sendLAY023307" + new Date());          
  
 }
 
  /**
  * Functionality:  Returned ReturnMsg
  * @param  : String returnObj 
  * @return  : String returnMsg
 * @throws Exception 
  */
 private String getReturnMsg(String returnObj) throws Exception {
  String returnMsg = "";
   try {
   JSONObject joRoot = JSONObject.fromObject(returnObj); 
   JSONObject dObject = (JSONObject) joRoot.get("d");
  if (null != dObject.get("F01_TO_E") && !"".equals(dObject.get("F01_TO_E"))) {
   JSONObject F01_TO_E = (JSONObject) dObject.get("F01_TO_E");
   if (null != F01_TO_E.get("MSGTY")) {
    String peCode = String.valueOf(F01_TO_E.get("MSGTY"));

       getLogger().info("MSGTY:" + peCode);
    System.out.println( "MSGTY:" + peCode);
   }
   if (null != F01_TO_E.get("MESSAGE")) {
    String peMsg = String.valueOf(F01_TO_E.get("MESSAGE"));
    System.out.println("MESSAGE " + ": " + peMsg);
    getLogger().info("MESSAGE " + ": " + peMsg);
   }
  }
  } catch (Exception e) {
   System.out.println(e.getMessage());
   getLogger().info(e.getMessage());
  }
  return returnMsg;
 }
 
 public static String getAreaToken(String i_url) {
  String tokenName = PROP_NAME_TOKEN + "." + getAreaCode(i_url);
  String token = m_prop.getProperty(tokenName); 
  return token;
 }
 
 private static String getAreaCode(String i_url) {
  String [] splitedUrl = i_url.split("/");
  return splitedUrl[3]; 
 }
 
 public String callAPIByPost(String i_url, String i_token, String i_requestBody) throws Exception {
  return callAPI(i_url, i_token, i_requestBody, HTTP_METHOD_POST);
 }

 private String callAPI(String i_url, String i_token, String i_requestBody, String i_httpMethod) throws Exception {
  openConnectionAndSetHttpProperties(i_url, i_httpMethod, i_token);

  try {
   sendRequestBody(i_requestBody);

   checkHttpResponse(i_url, i_httpMethod);
   readMessageId();

   return receiveResponseBody();

  } finally {
   closeConnection();
  }
 }
 
 private void openConnectionAndSetHttpProperties(String i_url, String i_httpMethod, String i_token)
 throws MalformedURLException, IOException, ProtocolException {
  m_url = new URL(i_url);
  m_conn = (HttpURLConnection) m_url.openConnection();
  m_conn.setDoOutput(true);
  m_conn.setRequestMethod(i_httpMethod);
  m_conn.setRequestProperty(HTTP_HDR_AUTH, AUTH_BEARER + i_token);
  m_conn.setRequestProperty(HTTP_HDR_ACCEPT, CONTENT_TYPE_APPL_JSON);
  m_conn.setRequestProperty(HTTP_HDR_CONTENT_TYPE, CONTENT_TYPE_APPL_JSON);
 }
 
 private void sendRequestBody(String i_strBody) throws Exception {
  if (i_strBody == null) {
   return;
  }

  OutputStream os = m_conn.getOutputStream();
  BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, ENCODING_UTF8));
  try {
   writer.write(i_strBody);
   writer.flush();
  } finally {
   writer.close();
   os.close();
  }
 }
 
 private void checkHttpResponse(String i_url, String i_httpMethod) throws IOException, UnsupportedEncodingException {
  if (!isSucceeded(m_conn.getResponseCode())) {
   throw new RuntimeException(String.format(
     "failed to call REST API URL(%s) Method(%s)\n\tHTTP error code : %s(%s)\n\tDetail: ", i_url,
     i_httpMethod, m_conn.getResponseCode(), m_conn.getResponseMessage(), getDetailErrorMsg()));
  }
 }
 
 /**
  * response code deal
  * 
  * @param i_responseCode
  * @return
  */
 private boolean isSucceeded(int i_responseCode) {
  boolean iresponseCode = false;
  switch (i_responseCode) {
  case HttpURLConnection.HTTP_BAD_REQUEST: // 400
   System.out.println("E");
   System.out.println("The server cannot or will not process the request due to client error");
   break;
  case HttpURLConnection.HTTP_UNAUTHORIZED: // 401
   System.out.println("E");
   System.out.println("The request has not been applied because of failed authentication");
   break;
  case HttpURLConnection.HTTP_NOT_FOUND: // 404
   System.out.println("E");
   System.out.println("The server can't find the requested resource");
   break;
  case HttpURLConnection.HTTP_INTERNAL_ERROR: // 500
   System.out.println("E");
   System.out.println("The server failed to fulfill an apparently valid request");
   break;
  case HttpURLConnection.HTTP_CREATED: // 201(성공)
     case HttpURLConnection.HTTP_OK: // 200 (성공)
         this.setPeCode("S");
         iresponseCode = true;
         break;
  }
  return iresponseCode;
 }

 private void setPeCode(String peCode) {
  this.peCode = peCode;
 }
 
 private String getDetailErrorMsg() throws UnsupportedEncodingException, IOException {
  BufferedReader br = new BufferedReader(
    new InputStreamReader(m_conn.getErrorStream(), ENCODING_UTF8));

  StringBuffer sb = new StringBuffer();

  String strLine = br.readLine();
  while (strLine != null) {
   sb.append(strLine);

   strLine = br.readLine();
  }

  return sb.toString();
 }
 
 private void readMessageId() {
  m_msgId = m_conn.getHeaderField(HTTP_HDR_MSGID);
 }
 
 private String receiveResponseBody() throws UnsupportedEncodingException, IOException {
  BufferedReader br = new BufferedReader(
    new InputStreamReader(m_conn.getInputStream(), ENCODING_UTF8));

  String lineData = null;
  StringBuffer sb = new StringBuffer();
  while ((lineData = br.readLine()) != null) {
   sb.append(lineData);
  }
  return sb.toString();
 }
 
 private void closeConnection() {
  try {
   m_conn.disconnect();
  } catch (Exception e) {
  }
 }
}

 
 
