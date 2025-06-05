package com.sds.secframework.volatilization.httpclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * @author kyt
 *
 */
public class HttpClientTest {

	public static void main(String[] args) {
		getMethodTest();
	}
	
	public static void getMethodTest()
	{
		HttpClient client = new HttpClient();
		PostMethod post = null;
		try
		{
			//요청 URL
			post = new PostMethod("http://localhost:7080/secfw/mobileOffice.mbl");
			
			String requestXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ " <channel>                                                              "
				+ "   <servicename>mobileoffice.sampleService</servicename>                                                "
				+ "   <methodname>insertSample</methodname>                                                "
				+ "   <voname>guntae</voname>                                                "
				+ "   <title>guntae</title>                                                "
				+ "   <category>REQUPDATE_GUNTAE</category>                                "
				+ "   <userid>fm.park</userid>                                             "
				+ "   <employeenumber>17518</employeenumber>                               "
				+ "   <action>DEL</action>                                                 "
				+ "                                                                        "
				+ "   <guntaevo>                                                           "
				+ "    <guntaelist>                                                        "
				+ "      <guntaeitem>                                                      "
				+ "        <guntaeid>17518</guntaeid>                                      "
				+ "        <guntaecode>B05</guntaecode>                                    "
				+ "        <fromdate>20101013</fromdate>                                   "
				+ "        <contents><![CDATA[연차 휴가 신청을 취소합니다.]]></contents>   "
				+ "      </guntaeitem>                                                     "
				+ "      <guntaeitem>                                                      "
				+ "        <guntaeid>17518</guntaeid>                                      "
				+ "        <guntaecode>B05</guntaecode>                                    "
				+ "        <fromDate>20101012</fromDate>                                   "
				+ "        <contents><![CDATA[연차 휴가 신청을 취소합니다.]]></contents>   "
				+ "      </guntaeitem>                                                     "
				+ "    </guntaelist>                                                       "
				+ "                                                                        "
				+ "    <approvalemployeenumber1>23445</approvalemployeenumber1>            "
				+ "    <approvalusername1>황우상</approvalusername1>                       "
				+ "   </guntaevo>                                                          "
				+ "                                                                        "
				+ " </channel>                                                             ";
					

			byte []output = requestXML.getBytes();
			ByteArrayInputStream bis = new ByteArrayInputStream(output);
			post.setRequestBody(bis);
			
			int statusCode = client.executeMethod(post);
			
			if(statusCode == HttpStatus.SC_OK)
			{
				String responseXML = readZipRequestData(post.getResponseBodyAsStream());				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			post.releaseConnection();
			client = null;
		}
	}
	
	protected static String readZipRequestData(InputStream is) throws Exception {
		String requestXML = null;
		InflaterInputStream iis = null;
		try {
			
			iis = new InflaterInputStream(is);	
			requestXML = readRequestData(iis);
			
		} catch (EOFException eofExp) {
			
			requestXML = "[ERROR]" + eofExp.toString();			
			
		} catch (ZipException zipExp) {
			
			requestXML = "[ERROR]" + zipExp.toString();
			
			
		} finally {
			try { 
				if (is != null) { 
					is.close(); 
				}
			} catch (Exception ioe) {}
		}
		
		if (requestXML == null) {
			requestXML = "[ERROR]The number of bytes that can be read is 0.";
		}
		
		return requestXML;
	}
	
	public static byte[] deflate(String inputString) throws Exception {
		byte[] input = inputString.getBytes("UTF-8");
		Deflater compresser = new Deflater();
		compresser.setInput(input);
		compresser.finish();
		
		byte[] output = new byte[inputString.length() + 8192];
		int compressedDataLength = compresser.deflate(output);
		
		byte [] ret = new byte[compressedDataLength];
		System.arraycopy(output, 0, ret, 0, compressedDataLength);		
		
		input = null;
		output = null;
		
		return ret;
	}
	
	public static String readInputStream(InputStream is) throws IOException {
		byte []readBytes = readInputStreamAsByteArray(is);
		return new String(readBytes, "UTF-8");
	}
	
	public static byte[] readInputStreamAsByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
		
		byte []buffer = new byte[1024];
		
		int len;
		while ((len = is.read(buffer)) > 0) {
			outStream.write(buffer, 0, len);
		}		
		
		outStream.close();
		return outStream.toByteArray();		
	}
	
	protected static String readRequestData(InputStream is) throws IOException {
		return readInputStream(is);
	}
	
}
