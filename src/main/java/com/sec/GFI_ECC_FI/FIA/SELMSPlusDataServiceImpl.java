package com.sec.GFI_ECC_FI.FIA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import anyframe.core.query.QueryServiceException;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.service.PropertyService;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.joda.time.DateTime;

public class SELMSPlusDataServiceImpl extends CommonServiceImpl implements SELMSPlusDataService {
	
	private final static String IPAAS_API_URL  = "http://ipaas-nerp.sec.samsung.net:8400/nerp/erp_set/erp_set_zgwmk7000300_f01_di/1.0/F01_Set";
	//private final static String IPAAS_API_NAME = "";
	//DEV :private final static String TOKEN = "1dc6630a-86fa-39fe-99d1-e779261a0d37";
	private final static String TOKEN = "08ec883a-f95b-34dc-9196-b155e33fcd60";
	//private final static String ARGS_CONFIG_FILENAME = "ipaas_client.properties";
	//private final static String DEV = "DEV";
	//private final static String QA = "QA";
    //private final static String PROD = "PROD";
    //private final static String pgmID = "NERP_ZMMM_GSCM_TT_RCV_INFO";
    //private final static String IPAAS_LOG_FILENAME = "SELMSPlusDataJob.log";
	/*public static Connection conn= null;
	private static iPaaSHTTPClient client = new iPaaSHTTPClient();*/
	public static String id = "";
	private static String m_msgId = "";
	public static int iCnt;
	public static String requestBody;
	private final static String IPAAS_API_IFKEY = "109614";
	private static Properties m_prop = new Properties();
	public final static String PROP_NAME_SERVER_URL   = "IPAAS.server_url";
	public final static String PROP_NAME_TOKEN        = "IPAAS.token";
	public final static String HTTP_METHOD_POST			= "POST";
	private URL m_url = null;
	private HttpURLConnection m_conn = null;
	public final static String HTTP_HDR_AUTH 			= "Authorization";
	public final static String HTTP_HDR_ACCEPT 			= "Accept";
	public final static String HTTP_HDR_CONTENT_TYPE 	= "Content-Type";
	public final static String HTTP_HDR_ACTION 			= "action";
	public final static String HTTP_HDR_CONDITION 		= "condition";
	public final static String HTTP_HDR_MSGID 			= "msgID";
	public final static String AUTH_BEARER 				= "Bearer ";
	public final static String CONTENT_TYPE_APPL_JSON 	= "application/json";
	public final static String ENCODING_UTF8 = "UTF-8";
	private String peCode;
	
	public SELMSPlusDataServiceImpl(){}
	
	public void setCommonDAO(CommonDAO commonDAO){
		this.commonDAO = commonDAO;
	}
	
	// 2014-05-07 Kevin added.
	private PropertyService propertyService = null;
	public void setPropertyService(PropertyService propertyService){
		this.propertyService = propertyService;
	}
	
	// in some cases, the link address has got incompleted address(cropped string), so changed with constant string value. 
	final String SELMSPLUS_URL = "http://selmsplus.sec.samsung.net/external/gateway.do?t=G&mt=c&id=";
	/*private String _detailUrl = null;*/
	private String getDetailUrl()
	{
		/*if(_detailUrl == null){
			_detailUrl =  this.propertyService.getProperty("secfw.GERP.selmsplusDetailAddress")+"?t=G&mt=c&id=";
		}*/
		
		return SELMSPLUS_URL;
	}
	/**
	 * 2014-05-15 Kevin: 3th parameter value changed from CNTRT_ID to CNTRT_NO by consensus
	 * 2014-05-15 Kevin: SELMS_URL has been changed to be short.
	 * 
	 * @param item
	 * @return
	 */
	private DT_ZCG_CONT_IF_SELMSINTAB ConvertrowDataToParamObj(ListOrderedMap item)
	{
		if(item == null) return null;
		
		return new DT_ZCG_CONT_IF_SELMSINTAB(
				StringUtil.bvl(item.get("GERP_CODE"), ""),
				StringUtil.bvl(item.get("BIZ_CODE"), ""),
				StringUtil.bvl(item.get("CNTRT_NO"), ""),
				StringUtil.bvl(item.get("GERP_CD"), ""),
				StringUtil.bvl(item.get("REQ_TITLE"), ""),
				StringUtil.bvl(item.get("SINGLE_ID").toString().toUpperCase(), ""),
				StringUtil.bvl(item.get("REG_DT"), ""),
				StringUtil.bvl(item.get("CUSTOMER_NM1"), ""),
				StringUtil.bvl(item.get("VENDOR_TYPE"), ""),
				StringUtil.bvl(item.get("STATUS_CODE"), ""),
				StringUtil.bvl(item.get("STATUS_CODE_DESC"), ""),
				StringUtil.bvl(item.get("CNTRTPERIOD_STARTDAY"), ""),
				StringUtil.bvl(item.get("CNTRTPERIOD_ENDDAY"), ""),
				StringUtil.bvl(item.get("CNTRT_AMT"), ""),
				StringUtil.bvl(item.get("CRRNCY_UNIT"), ""),
				StringUtil.bvl(item.get("CLOSE_YN"), ""),
				StringUtil.bvl(item.get("MANDATORY"), ""),
				StringUtil.bvl(item.get("DEL_YN"), ""),
				StringUtil.bvl(item.get("CHANGE_DATE"), ""),
				StringUtil.bvl(item.get("CONTRACTTYPE"), ""),
				StringUtil.bvl(item.get("COSTTYPE"), ""),
				getDetailUrl()+StringUtil.bvl(item.get("CNTRT_ID"), "")
				);
	}

	@Override
	public void SendData(){
		SendData(null);
	}
	@Override
	public int SendData(SELMSPlusDataVO vo) {
		
		SELMSPlusDataLogVO logVO = new SELMSPlusDataLogVO();
		// Batch starting time.
		logVO.setExecutionDate(new DateTime());
		
		int resultCnt = 0;
		
		try {
			final int TARGET_INTERVAL = 6;
			
			if(vo == null){
				vo = new SELMSPlusDataVO();
				
				// Get current Date
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date currentDate = new Date();
				String today = dateFormat.format(currentDate);
				vo.setTargetDate(today);
				
				Calendar now = Calendar.getInstance();
				vo.setTargetHour(now.get(Calendar.HOUR_OF_DAY));
				vo.setTargetInterval(TARGET_INTERVAL);
				
				HashMap<String, String> compCdList = new HashMap<String, String>();
				// 2014-02-25 Kevin added. At this time, just only SEF is a subjected subsidiary of this GERP integration process as pilot.
				compCdList.put("C480", "480A");
				vo.setTargetCompCdList(compCdList);
			}
			
			// stage: http://gxqci01.sec.samsung.net:8170/sap/xi/engine?type=entry&amp;version=3.0&amp;Sender.Service=D229_SELMS_EU_Q&amp;Interface=http%3A%2F%2Fsec.com%2FGFI_ECC_FI%2FFIA%5EFI11004_ZCG_CONT_IF_SELMS_SO
		    // product: http://gerppi.sec.samsung.net:8130/sap/xi/engine?type=entry&amp;version=3.0&amp;Sender.Service=D229_SELMS_EU_P&amp;Interface=http%3A%2F%2Fsec.com%2FGFI_ECC_FI%2FFIA%5EFI11004_ZCG_CONT_IF_SELMS_SO
			/*FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator lc = new FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator();
			String portAddress = this.propertyService.getProperty("secfw.GERP.portAddress");
			lc.setPortAddress(portAddress);*/
			
			//FI11004_ZCG_CONT_IF_SELMS_SOBindingStub stub = (FI11004_ZCG_CONT_IF_SELMS_SOBindingStub)lc.getFI11004_ZCG_CONT_IF_SELMS_SOPort();
			//stub.setUsername("CPIC_S1666A");
			//stub.setPassword("cba2596zyx");
			
			for(Map.Entry<String, String> entry : vo.getTargetCompCdList().entrySet())
			{
				String targetCompCd = entry.getKey();
				String targetBA = entry.getValue();
				// set target comp_cd
				// 2014-05-12 Kevin changed. Changed targetCompCd to TargetBA
				vo.setTargetCompCd(targetBA);
				
				// Extract SELMS+ contract data
				List dataList = commonDAO.list("clm.gerp.extractSELMSPlusContractData", vo);
				
				if(dataList != null && dataList.size() > 0){
				
					// The number of extracted contract data for interaction.
					logVO.setSELMSPlusDataCnt(dataList.size());
					DT_ZCG_CONT_IF_SELMSINTAB[] arrInputParam = new DT_ZCG_CONT_IF_SELMSINTAB[dataList.size()];
					
					for(int i = 0; i < dataList.size(); i++)
					{
						ListOrderedMap lom = (ListOrderedMap)dataList.get(i);
						DT_ZCG_CONT_IF_SELMSINTAB paramObj = ConvertrowDataToParamObj(lom);
						paramObj.setCEDATE(getTimeFromDate(paramObj.getCEDATE()));
						paramObj.setCSDATE(getTimeFromDate(paramObj.getCSDATE()));
						arrInputParam[i] = paramObj;
					}
					// Actual transferred data count.
					logVO.setTransferredDataCnt(dataList.size());
					
					final String CALL_TYPE = "S";
					//String url = getServerUrl() + IPAAS_API_URL;
					String url = IPAAS_API_URL;
					String token = TOKEN;
					DT_ZCG_CONT_IF_SELMS inputParamObj = new DT_ZCG_CONT_IF_SELMS(arrInputParam, "", "", targetCompCd, targetBA, CALL_TYPE);
					// Method call
					//DT_ZCG_CONT_IF_SELMS_R returnObj = stub.FI11004_ZCG_CONT_IF_SELMS_SO(inputParamObj);
					JSONObject requestBody = JSONObject.fromObject(inputParamObj);
					JSONArray intab = requestBody.getJSONArray("INTAB");
					JSONObject request = new JSONObject();
					JSONObject F01_TO_I = new JSONObject();
					JSONObject F01_TO_E = new JSONObject();
					//JSONObject F01_TO_T_PT_INTAB = new JSONObject();
					F01_TO_I.put("I_BUKRS", targetCompCd);
					F01_TO_I.put("I_GSBER", targetBA);
					F01_TO_I.put("I_GUBUN", CALL_TYPE);
					F01_TO_E.put("R_MESSAGE", "");
					request.put("F01_TO_I",F01_TO_I );
					request.put("F01_TO_E",F01_TO_E );
					request.put("F01_TO_T_PT_INTAB",intab );
					String returnObj = callAPIByPost(url, token, request.toString());
					if (null != returnObj && !"".equals(returnObj) ) {
		                JSONObject jsonObject = JSONObject.fromObject(returnObj); 
		                JSONObject d = jsonObject.getJSONObject("d");
		                JSONObject fo1Toe = d.getJSONObject("F01_TO_E");
		                String returnMsg = fo1Toe.getString("R_MESSAGE");
						logVO.setFaildData("[S]"+targetCompCd+":"+returnMsg);
		            }
					
					resultCnt += dataList.size();
				}
				else {
					logVO.setSELMSPlusDataCnt(0);
					logVO.setTransferredDataCnt(0);
					logVO.setFaildData("No data extracted");
				}
				
				if(vo.getCNSDReqID() != null && vo.getCNSDReqID() != ""){
					logVO.setFaildData(logVO.getFaildData()+"["+vo.getCNSDReqID()+"]");
				}
				
				RecordLog(logVO);
			}
						
		} catch(Exception e){
			e.printStackTrace();
			System.out.print(e.getMessage());
			logVO.setFaildData(e.getMessage());
			RecordLog(logVO);
		}
		
		return resultCnt;
	}
	
	private void RecordLog(SELMSPlusDataLogVO vo){
		try {
			commonDAO.insert("clm.gerp.insertSELMSPlusContractDataLog", vo);
		}
		catch(QueryServiceException ex){
			ex.printStackTrace();
			System.out.print(ex.getMessage());
		}
	}
	
	@Override
	public void ReceiveData(){
		ReceiveData(null);
	}
	@Override
	public void ReceiveData(SELMSPlusDataVO vo) {
		SELMSPlusDataLogVO logVO = new SELMSPlusDataLogVO();
		// Batch starting time.
		logVO.setExecutionDate(new DateTime());
		
		try {
			
			if(vo == null){
				vo = new SELMSPlusDataVO();
				
				HashMap<String, String> compCdList = new HashMap<String, String>();
				// 2014-02-28 Kevin added. At this time, just only SEF is a subjected subsidiary of this GERP integration process as pilot.
				compCdList.put("C480", "480A");
				vo.setTargetCompCdList(compCdList);
			}
			
			// stage: http://gxqci01.sec.samsung.net:8170/sap/xi/engine?type=entry&amp;version=3.0&amp;Sender.Service=D229_SELMS_EU_Q&amp;Interface=http%3A%2F%2Fsec.com%2FGFI_ECC_FI%2FFIA%5EFI11004_ZCG_CONT_IF_SELMS_SO
		    // product: http://gerppi.sec.samsung.net:8130/sap/xi/engine?type=entry&amp;version=3.0&amp;Sender.Service=D229_SELMS_EU_P&amp;Interface=http%3A%2F%2Fsec.com%2FGFI_ECC_FI%2FFIA%5EFI11004_ZCG_CONT_IF_SELMS_SO
			FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator lc = new FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator();
			String portAddress = this.propertyService.getProperty("secfw.GERP.portAddress");
			lc.setPortAddress(portAddress);
			
			FI11004_ZCG_CONT_IF_SELMS_SOBindingStub stub = (FI11004_ZCG_CONT_IF_SELMS_SOBindingStub)lc.getFI11004_ZCG_CONT_IF_SELMS_SOPort();
			stub.setUsername("CPIC_S1666A");
			stub.setPassword("cba2596zyx");
			
			final String CALL_TYPE = "R";
			List<DT_ZCG_CONT_IF_SELMS_ROUTTAB[]> linkedSELMSPlusDataList = new ArrayList<DT_ZCG_CONT_IF_SELMS_ROUTTAB[]>();
			for(Map.Entry<String, String> entry : vo.getTargetCompCdList().entrySet()){
				String targetCompCd = entry.getKey();
				String targetBA = entry.getValue();
				
				DT_ZCG_CONT_IF_SELMS inputParamObj = new DT_ZCG_CONT_IF_SELMS(null, "", "", targetCompCd, targetBA, CALL_TYPE);
				// Method call
				DT_ZCG_CONT_IF_SELMS_R returnObj = stub.FI11004_ZCG_CONT_IF_SELMS_SO(inputParamObj);
				String returnMsg = returnObj.getR_MESSAGE();
				DT_ZCG_CONT_IF_SELMS_ROUTTAB[] returnedDataList = returnObj.getOUTTAB();
				if(returnedDataList != null && returnedDataList.length > 0){
					linkedSELMSPlusDataList.add(returnedDataList);				
					
					logVO.setSELMSPlusDataCnt(returnedDataList.length);
					logVO.setTransferredDataCnt(returnedDataList.length);
				}
				logVO.setFaildData("[R]"+targetCompCd+":"+returnMsg);
				RecordLog(logVO);
			}
			
			for(DT_ZCG_CONT_IF_SELMS_ROUTTAB[] items : linkedSELMSPlusDataList){
				for(DT_ZCG_CONT_IF_SELMS_ROUTTAB item : items){
					
					String aDate = item.getA_DATE();
					String aTime = item.getA_TIME();
					
					SELMSPlusNGERPLinkedDataVO linkedVO = new SELMSPlusNGERPLinkedDataVO();
					linkedVO.setCompCD(item.getBUKRS());
					linkedVO.setBACD(item.getGSBER());
					linkedVO.setSELMSPlusID(item.getSELMSID());
					linkedVO.setGERPID(item.getID());
					linkedVO.setLinked(item.getLINKED());
					linkedVO.setLinkedDateTime(aDate.substring(0, 4)+"-"+aDate.substring(4, 6)+"-"+aDate.substring(6, 8)+" "+aTime.substring(0, 2)+":"+aTime.substring(2, 4)+":"+aTime.substring(4, 6));
					linkedVO.setModule(item.getMODULE());
					linkedVO.setAmount(Float.parseFloat(item.getAMOUNT()));
					linkedVO.setCurrency(item.getCURRENCY());
					
					commonDAO.modify("clm.gerp.insertSELMSPlusGERPLinkedData", linkedVO);
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.print(ex.getMessage());
			RecordLog(logVO);
		}
	}
	
	public static String getServerUrl() {
		return m_prop.getProperty(PROP_NAME_SERVER_URL);
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
	
	public static String getTimeFromDate(String date) throws Exception{
        if(date == null || "".equals(date))
            return "";
        SimpleDateFormat sdf =  new SimpleDateFormat( "yyyyMMdd" ); 
        Date d = sdf.parse(date);
        return String.format("/Date(%s)/", d.getTime());
    }

}
