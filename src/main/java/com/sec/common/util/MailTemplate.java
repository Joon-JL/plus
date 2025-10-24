package com.sec.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sds.secframework.common.util.StringUtil;

public class MailTemplate {
	/**
	 * 알려주세요 메일
	 * @param map
	 * @return
	 */
	public static String getMailQuestionToAdmin(Map map) {
		StringBuffer sb = new StringBuffer() ;
		
		sb.append("<html> \r\n") ;
		sb.append("<head> \r\n") ;
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/> \r\n") ;
		sb.append("<title>Health Priority</title> \r\n") ;
		sb.append("<LINK href=\"${url}/style/shri/common.css\" type=\"text/css\" rel=\"stylesheet\"> \r\n") ;
		sb.append("</head> \r\n") ;
		sb.append("<body style=\"margin:0px;\"> \r\n") ;
		sb.append("	<div class=\"mailTop\"><ul><li></li></ul></div > \r\n") ;
		sb.append("	<div class=\"mailWrap\"> \r\n") ;
		sb.append("		<ul> \r\n") ;
		sb.append("			<li> \r\n") ;
		sb.append("				<div class=\"mailArea\"> \r\n") ;
		sb.append("					<ul> \r\n") ;
		sb.append("						<li> \r\n") ;
		sb.append("							<div> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailTitle\">알려주세요 문의</li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("							<table class=\"mailTxt\"> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgT\"> </td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgM\"> \r\n") ;
		sb.append("										<table class=\"mailData\" > \r\n") ;
		sb.append("											<colgroup> \r\n") ;
		sb.append("												<col width=\"20%\"> \r\n") ;
		sb.append("												<col width=\"30%\"> \r\n") ;
		sb.append("												<col width=\"20%\"> \r\n") ;
		sb.append("												<col width=\"30%\"> \r\n") ;
		sb.append("											</colgroup> \r\n") ;
		sb.append("											<tr> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">제목</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\" colspan=\"3\"> \r\n") ;
		sb.append("													${title} \r\n") ;
		sb.append("												</td> \r\n") ;
		sb.append("											</tr> \r\n") ;
		sb.append("											<tr> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">공개여부</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\">${open_yn}</td> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">문의자</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\">${question_user}</td> \r\n") ;
		sb.append("											</tr> \r\n") ;
		sb.append("										</table> \r\n") ;
		sb.append("									</td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgB\"> </td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("							</table> \r\n") ;
		sb.append("							<div class=\"mailBt\"> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li><a href=\"${link}\" target=\"_new\"><img src=\"${url}/images/shri/mail/bt_detail.gif\"></a></li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("							<div class=\"mailInfo\"> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgT\"> </li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgM\"><img src=\"${url}/images/shri/mail/img_mailTxt.gif\"></li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgB\"> </li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("						</li> \r\n") ;
		sb.append("					</ul> \r\n") ;
		sb.append("				</div> \r\n") ;
		sb.append("				<!-- copyright--> \r\n") ;
		sb.append("				<div class=\"copyArea\"> \r\n") ;
		sb.append("					<ul> \r\n") ;
		sb.append("						<li class=\"copyright\"> \r\n") ;
		sb.append("							Copyright ⓒ 2011 Samsung. All right reserved. \r\n") ;
		sb.append("						</li> \r\n") ;
		sb.append("					</ul> \r\n") ;
		sb.append("				</div> \r\n") ;
		sb.append("			</li> \r\n") ;
		sb.append("		</ul> \r\n") ;
		sb.append("	</div> \r\n") ;
		sb.append("</body> \r\n") ;
		sb.append("</html> \r\n") ;
		
		String mailHtml = parsingValue(sb.toString(), map) ;
		
		return mailHtml ;
	}
	
	/**
	 * 연구제안 메일(to 관리자)
	 * @param map
	 * @return
	 */
	public static String getMailPropsalToAdmin(Map map) {
		StringBuffer sb = new StringBuffer() ;
		
		sb.append("<html> \r\n") ;
		sb.append("<head> \r\n") ;
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/> \r\n") ;
		sb.append("<title>Health Priority</title> \r\n") ;
		sb.append("<LINK href=\"${url}/style/shri/common.css\" type=\"text/css\" rel=\"stylesheet\"> \r\n") ;
		sb.append("</head> \r\n") ;
		sb.append("<body style=\"margin:0px;\"> \r\n") ;
		sb.append("	<div class=\"mailTop\"><ul><li></li></ul></div > \r\n") ;
		sb.append("	<div class=\"mailWrap\"> \r\n") ;
		sb.append("		<ul> \r\n") ;
		sb.append("			<li> \r\n") ;
		sb.append("				<div class=\"mailArea\"> \r\n") ;
		sb.append("					<ul> \r\n") ;
		sb.append("						<li> \r\n") ;
		sb.append("							<div> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailTitle\">연구제안</li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("							<table class=\"mailTxt\"> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgT\"> </td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgM\"> \r\n") ;
		sb.append("										<table class=\"mailData\" > \r\n") ;
		sb.append("											<colgroup> \r\n") ;
		sb.append("												<col width=\"20%\"> \r\n") ;
		sb.append("												<col width=\"30%\"> \r\n") ;
		sb.append("												<col width=\"20%\"> \r\n") ;
		sb.append("												<col width=\"30%\"> \r\n") ;
		sb.append("											</colgroup> \r\n") ;
		sb.append("											<tr> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">제목</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\" colspan=\"3\"> \r\n") ;
		sb.append("													${title} \r\n") ;
		sb.append("												</td> \r\n") ;
		sb.append("											</tr> \r\n") ;
		sb.append("											<tr> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">공개여부</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\">${open_yn}</td> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">제안자</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\">${user}</td> \r\n") ;
		sb.append("											</tr> \r\n") ;
		sb.append("										</table> \r\n") ;
		sb.append("									</td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgB\"> </td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("							</table> \r\n") ;
		sb.append("							<div class=\"mailBt\"> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li><a href=\"${link}\" target=\"_new\"><img src=\"${url}/images/shri/mail/bt_detail.gif\"></a></li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("							<div class=\"mailInfo\"> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgT\"> </li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgM\"><img src=\"${url}/images/shri/mail/img_mailTxt.gif\"></li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgB\"> </li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("						</li> \r\n") ;
		sb.append("					</ul> \r\n") ;
		sb.append("				</div> \r\n") ;
		sb.append("				<!-- copyright--> \r\n") ;
		sb.append("				<div class=\"copyArea\"> \r\n") ;
		sb.append("					<ul> \r\n") ;
		sb.append("						<li class=\"copyright\"> \r\n") ;
		sb.append("							Copyright ⓒ 2011 Samsung. All right reserved. \r\n") ;
		sb.append("						</li> \r\n") ;
		sb.append("					</ul> \r\n") ;
		sb.append("				</div> \r\n") ;
		sb.append("			</li> \r\n") ;
		sb.append("		</ul> \r\n") ;
		sb.append("	</div> \r\n") ;
		sb.append("</body> \r\n") ;
		sb.append("</html> \r\n") ;
		
		String mailHtml = parsingValue(sb.toString(), map) ;
		
		return mailHtml ;
	}
	
	
	/**
	 * 연구제안 메일(to 제안자)
	 * @param map
	 * @return
	 */
	public static String getMailPropsalToUser(Map map) {
		StringBuffer sb = new StringBuffer() ;
		
		sb.append("<html> \r\n") ;
		sb.append("<head> \r\n") ;
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/> \r\n") ;
		sb.append("<title>Health Priority</title> \r\n") ;
		sb.append("<LINK href=\"${url}/style/shri/common.css\" type=\"text/css\" rel=\"stylesheet\"> \r\n") ;
		sb.append("</head> \r\n") ;
		sb.append("<body style=\"margin:0px;\"> \r\n") ;
		sb.append("	<div class=\"mailTop\"><ul><li></li></ul></div > \r\n") ;
		sb.append("	<div class=\"mailWrap\"> \r\n") ;
		sb.append("		<ul> \r\n") ;
		sb.append("			<li> \r\n") ;
		sb.append("				<div class=\"mailArea\"> \r\n") ;
		sb.append("					<ul> \r\n") ;
		sb.append("						<li> \r\n") ;
		sb.append("							<div> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailTitle\">연구제안</li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("							<table class=\"mailTxt\"> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgT\"> </td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgM\"> \r\n") ;
		sb.append("										<table class=\"mailData\" > \r\n") ;
		sb.append("											<colgroup> \r\n") ;
		sb.append("												<col width=\"20%\"> \r\n") ;
		sb.append("												<col width=\"30%\"> \r\n") ;
		sb.append("												<col width=\"20%\"> \r\n") ;
		sb.append("												<col width=\"30%\"> \r\n") ;
		sb.append("											</colgroup> \r\n") ;
		sb.append("											<tr> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">제목</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\" colspan=\"3\"> \r\n") ;
		sb.append("													${title} \r\n") ;
		sb.append("												</td> \r\n") ;
		sb.append("											</tr> \r\n") ;
		sb.append("											<tr> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">결과</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\">${open_yn}</td> \r\n") ;
		sb.append("												<td class=\"mailDataTitle\">제안자</td> \r\n") ;
		sb.append("												<td class=\"mailDataTd\">${user}</td> \r\n") ;
		sb.append("											</tr> \r\n") ;
		sb.append("										</table> \r\n") ;
		sb.append("									</td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("								<tr> \r\n") ;
		sb.append("									<td class=\"img_mailBgB\"> </td> \r\n") ;
		sb.append("								</tr> \r\n") ;
		sb.append("							</table> \r\n") ;
		sb.append("							<div style=\"margin:0 10 0 15\">${contents}</div> \r\n") ;
		sb.append("							<div class=\"mailBt\"> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li><a href=\"${link}\" target=\"_new\"><img src=\"${url}/images/shri/mail/bt_detail.gif\"></a></li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("							<div class=\"mailInfo\"> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgT\"> </li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgM\"><img src=\"${url}/images/shri/mail/img_mailTxt.gif\"></li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("								<ul> \r\n") ;
		sb.append("									<li class=\"mailInfoBgB\"> </li> \r\n") ;
		sb.append("								</ul> \r\n") ;
		sb.append("							</div> \r\n") ;
		sb.append("						</li> \r\n") ;
		sb.append("					</ul> \r\n") ;
		sb.append("				</div> \r\n") ;
		sb.append("				<!-- copyright--> \r\n") ;
		sb.append("				<div class=\"copyArea\"> \r\n") ;
		sb.append("					<ul> \r\n") ;
		sb.append("						<li class=\"copyright\"> \r\n") ;
		sb.append("							Copyright ⓒ 2011 Samsung. All right reserved. \r\n") ;
		sb.append("						</li> \r\n") ;
		sb.append("					</ul> \r\n") ;
		sb.append("				</div> \r\n") ;
		sb.append("			</li> \r\n") ;
		sb.append("		</ul> \r\n") ;
		sb.append("	</div> \r\n") ;
		sb.append("</body> \r\n") ;
		sb.append("</html> \r\n") ;
		
		String mailHtml = parsingValue(sb.toString(), map) ;
		
		return mailHtml ;
	}
	
	/**
	 * 연구제안 메일(to 제안자)
	 * @param map
	 * @return
	 */
	public static String getMailSpeakContractToUser(Map map) {
		StringBuffer sb = new StringBuffer() ;
		
		sb.append("<html> \r\n") ;
		sb.append("<head> \r\n") ;
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/> \r\n") ;
		sb.append("<title>Health Priority</title> \r\n") ;
		sb.append("<LINK href=\"${url}/style/shri/common.css\" type=\"text/css\" rel=\"stylesheet\"> \r\n") ;
		sb.append("</head> \r\n") ;
		sb.append("<body style=\"margin:0px;\"> \r\n") ;
		sb.append("${title}") ;
		sb.append("</body> \r\n") ;
		sb.append("</html> \r\n") ;
		
		String mailHtml = parsingValue(sb.toString(), map) ;
		
		return mailHtml ;
	}
	
	private static String parsingValue(String mailHtml, Map map) {
		Set keySet = map.keySet() ;
		Iterator it = keySet.iterator() ;
		while(it.hasNext()) {
			String key = (String)it.next();
			String value = (String)map.get(key) ;
			
			mailHtml = StringUtil.replace(mailHtml, "${" + key + "}", value) ;
		}
		
		return mailHtml ;
	}
}
