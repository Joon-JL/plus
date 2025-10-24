package com.sds.secframework.singleIF.service.impl;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.rpc.ServiceException;

import model.outldap.samsung.net.Employee;
import model.outldap.samsung.net.Organization;
import mySingle.service.EmpServiceLocator;
import mySingle.service.OrgServiceLocator;

import org.apache.axis.AxisFault;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.ArrayUtils;

import OutldapFindEmpLib.UserService.UserService;
import OutldapFindOrgLib.OrganizationService.OrganizationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.dept.dto.DeptVO;
import com.sds.secframework.singleIF.service.EsbOrgService;

public class EsbOrgServiceImpl extends CommonServiceImpl implements EsbOrgService, Comparator {

	private static String esbOrgId="";
	private static String esbOrgPw="";

	final static String _C_NA = "N/A";
	final static String _C_NULL = "";

	final static String _inAttrs	= "";
	final static String _sortAttrs	= "cn eptitlesortorder o ou";	//eporganizationnumber : 내부적으로 1차 소팅 키로 참조되고 있음.
	//final static String _baseDn		= "ou=emp,ou=reg,o=sec";	// 사용되지 않음.

	static UserService clientStub = null;
	static OrganizationService orgStub = null;

	public void setEsbOrgId(String esbOrgId) {
		this.esbOrgId = esbOrgId;
	}

	public void setEsbOrgPw(String esbOrgPw) {
		this.esbOrgPw = esbOrgPw;
	}
	
	/**
	 * Employee객체의 값을 Hashtable로 변경하여 리턴
	 * @param employee : Employee 객체 변수
	 * @return Hashtable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Hashtable getEmployeeToHashtable(Employee employee, Locale lc)
	{
		Hashtable ht = new Hashtable();
		String strKeyAttribute = null;
		String language = "";
		
		if(lc != null) {
			language = StringUtil.bvl(lc.getLanguage(), propertyService.getProperty("secfw.defaultLocale"));
		} else {
			language = propertyService.getProperty("secfw.defaultLocale");
		}

		/*속도 향상을 위해서 아래 속성값은 기본적으로 가지고 온다.(자주사용하는 속성 및 소팅에서 사용하는 속성을 등록하여 사용한다)*/
		ht.put("departmentnumber", StringUtil.bvl(employee.getDepartmentnumber(), _C_NULL));//부서코드
		ht.put("employeenumber", StringUtil.bvl(employee.getEmployeenumber(), _C_NULL));//사번
		ht.put("employeetype", StringUtil.bvl(employee.getEmployeetype(), _C_NULL));//사용자구분
		ht.put("epbusicode", StringUtil.bvl(employee.getEpbusicode(), _C_NULL));//사업장 코드

		if("ko".equals(language)) {
			ht.put("o", StringUtil.bvl(employee.getO(), _C_NULL));//회사명
			ht.put("eppostaladdress", StringUtil.bvl(employee.getPostalcode(), _C_NULL) + " " + StringUtil.bvl(employee.getL(), _C_NULL) + " " + StringUtil.bvl(employee.getPostaladdress(), _C_NULL));//회사주소
			ht.put("epsuborgname", StringUtil.bvl(employee.getEpsuborgname(), _C_NULL));//총괄명
			ht.put("epbusiname", StringUtil.bvl(employee.getEpbusiname(), _C_NULL));//사업장명
			ht.put("department", StringUtil.bvl(employee.getDepartment(), _C_NULL));//부서명
			ht.put("epregionname", StringUtil.bvl(employee.getEpregionname(), _C_NULL));//지역명
			ht.put("cn", StringUtil.bvl(employee.getCn(), _C_NULL));//전체 이름
			ht.put("sn", StringUtil.bvl(employee.getSn(), _C_NULL));//성
			ht.put("givenname", StringUtil.bvl(employee.getGivenname(), _C_NULL));//이름
			ht.put("description", StringUtil.bvl(employee.getDescription(), _C_NULL));//담당업무
			ht.put("title", StringUtil.bvl(employee.getTitle(), _C_NULL));//직급명
			ht.put("epgradename", StringUtil.bvl(employee.getEpgradename(), _C_NULL));//직위명
			ht.put("epsendcompanyname", StringUtil.bvl(employee.getEpsendcompanyname(), _C_NULL));//파견 회사명
			ht.put("epsendsuborgname", StringUtil.bvl(employee.getEpsendsuborgname(), _C_NULL));//파견 총괄명
			ht.put("epsenddeptname", StringUtil.bvl(employee.getEpsenddeptname(), _C_NULL));//파견 부서명
			ht.put("epsendtitlename", StringUtil.bvl(employee.getEpsendtitlename(), _C_NULL));//파견 직급명
			ht.put("epsendgradename", StringUtil.bvl(employee.getEpsendgradename(), _C_NULL));//파견 직위명
		} else {
			ht.put("o", StringUtil.bvl(employee.getEpenorganizationname(), _C_NULL));//회사명
			ht.put("eppostaladdress", StringUtil.bvl(employee.getEpenpostaladdress(), _C_NULL));//회사주소
			ht.put("epsuborgname", StringUtil.bvl(employee.getEpensuborgname(), _C_NULL));//총괄명
			ht.put("epbusiname", StringUtil.bvl(employee.getEpenbusiname(), _C_NULL));//사업장명
			ht.put("department", StringUtil.bvl(employee.getEpendepartment(), _C_NULL));//부서명
			ht.put("epregionname", StringUtil.bvl(employee.getEpenregionname(), _C_NULL));//지역명
			ht.put("cn", StringUtil.bvl(employee.getEpencn(), _C_NULL));//전체 이름
			ht.put("sn", StringUtil.bvl(employee.getEpensn(), _C_NULL));//성
			ht.put("givenname", StringUtil.bvl(employee.getEpengivenname(), _C_NULL));//이름
			ht.put("description", StringUtil.bvl(employee.getEpendescription(), _C_NULL));//담당업무
			ht.put("title", StringUtil.bvl(employee.getEpentitle(), _C_NULL));//직급명
			ht.put("epgradename", StringUtil.bvl(employee.getEpengradename(), _C_NULL));//직위명
			ht.put("epsendcompanyname", StringUtil.bvl(employee.getEpensendcompanyname(), _C_NULL));//파견 회사명
			ht.put("epsendsuborgname", StringUtil.bvl(employee.getEpensendsuborgname(), _C_NULL));//파견 총괄명
			ht.put("epsenddeptname", StringUtil.bvl(employee.getEpensenddeptname(), _C_NULL));//파견 부서명
			ht.put("epsendtitlename", StringUtil.bvl(employee.getEpensendtitlename(), _C_NULL));//파견 직급명
			ht.put("epsendgradename", StringUtil.bvl(employee.getEpensendgradename(), _C_NULL));//파견 직위명
		}
		
		ht.put("epid", StringUtil.bvl(employee.getEpid(), _C_NULL));//EPID
		ht.put("eporganizationnumber", StringUtil.bvl(employee.getEporganizationnumber(), _C_NULL));//회사코드
		ht.put("epregioncode", StringUtil.bvl(employee.getEpregioncode(), _C_NULL));//지역코드
		ht.put("epsuborgcode", StringUtil.bvl(employee.getEpsuborgcode(), _C_NULL));//총괄코드
		ht.put("eptitlenumber", StringUtil.bvl(employee.getEptitlenumber(), _C_NULL));//직급코드
		ht.put("epuserstatus", StringUtil.bvl(employee.getEpuserstatus(), _C_NULL));//임직원 상태
		ht.put("l", StringUtil.bvl(employee.getL(), _C_NULL));//회사주소1
		ht.put("mail", StringUtil.bvl(employee.getMail(), _C_NULL));//메일주소
		ht.put("mobile", StringUtil.bvl(employee.getMobile(), _C_NULL));//핸드폰
		ht.put("postaladdress", StringUtil.bvl(employee.getPostaladdress(), _C_NULL));//회사 주소 2
		ht.put("postalcode", StringUtil.bvl(employee.getPostalcode(), _C_NULL));//회사 우편번호
		ht.put("telephonenumber", StringUtil.bvl(employee.getTelephonenumber(), _C_NULL));//회사 전화번호
		ht.put("userid", StringUtil.bvl(employee.getUserid(), _C_NULL));//마이싱글ID
		ht.put("eptitlesortorder", StringUtil.bvl(employee.getEptitlesortorder(), _C_NULL));//직급 정렬 순서
		ht.put("epsendtitlesortorder", StringUtil.bvl(employee.getEpsendtitlesortorder(), _C_NULL));//파견 직급 정렬 순서
		ht.put("c", StringUtil.bvl(employee.getC(), _C_NULL));//국가
		ht.put("dn", StringUtil.bvl(employee.getDn(), _C_NULL));//dn
		ht.put("epdefaultcompcode", StringUtil.bvl(employee.getEpdefaultcompcode(), _C_NULL));//기본소속구분코드
		ht.put("epgradeortitle", StringUtil.bvl(employee.getEpgradeortitle(), _C_NULL));//직급/직위 표기방식
		ht.put("episblue", StringUtil.bvl(employee.getEpisblue(), _C_NULL));//임원여부
		ht.put("eppreferredlanguage", StringUtil.bvl(employee.getEppreferredlanguage(), _C_NULL));//표현언어
		ht.put("epsecuritylevel", StringUtil.bvl(employee.getEpsecuritylevel(), _C_NULL));//보안등급
		ht.put("epsendbusicode", StringUtil.bvl(employee.getEpsendbusicode(), _C_NULL));//파견사업장코드
		ht.put("epsendcompanycode", StringUtil.bvl(employee.getEpsendcompanycode(), _C_NULL));//파견회사코드
		ht.put("epsenddeptcode", StringUtil.bvl(employee.getEpsenddeptcode(), _C_NULL));//파견부서코드
		ht.put("epsendgradeortitle", StringUtil.bvl(employee.getEpsendgradeortitle(), _C_NULL));//파견사 직급/직위 표기방식
		ht.put("epsendregioncode", StringUtil.bvl(employee.getEpsendregioncode(), _C_NULL));//파견지역코드
		ht.put("epsendsecuritylevel", StringUtil.bvl(employee.getEpsendsecuritylevel(), _C_NULL));//파견보안등급
		ht.put("epsendsuborgcode", StringUtil.bvl(employee.getEpsendsuborgcode(), _C_NULL));//파견총괄코드
		ht.put("epsendtitlenumber", StringUtil.bvl(employee.getEpsendtitlenumber(), _C_NULL));//파견직급코드
		ht.put("epuserlevel", StringUtil.bvl(employee.getEpuserlevel(), _C_NULL));//사용자등급
		ht.put("facsimiletelephonenumber", StringUtil.bvl(employee.getFacsimiletelephonenumber(), _C_NULL));//회사팩스번호
		ht.put("nickname", StringUtil.bvl(employee.getNickname(), _C_NULL));//Nickname
		ht.put("preferredlanguage", StringUtil.bvl(employee.getPreferredlanguage(), _C_NULL));//메일자동응답언어
		ht.put("epvoipnumber", StringUtil.bvl(employee.getEpvoipnumber(), _C_NULL));//인터넷전화
		ht.put("mailHost", StringUtil.bvl(employee.getMailHost(), _C_NULL));//메일호스트
		ht.put("epindeptcode", StringUtil.bvl(employee.getEpindeptcode(), _C_NULL));//내부부서코드
		ht.put("epjob", StringUtil.bvl(employee.getEpjob(), _C_NULL));//직무명
		ht.put("epjobname", StringUtil.bvl(employee.getEpjobname(), _C_NULL));//직무코드
		ht.put("epindeptcodename", StringUtil.bvl(employee.getEpindeptcodename(), _C_NULL));//내부부서명
		ht.put("epwithdrawdate", StringUtil.bvl(employee.getEpwithdrawdate(), _C_NULL));//퇴직/휴직일
		ht.put("epnative", StringUtil.bvl(employee.getEpnative(), _C_NULL));//현채인여부
		ht.put("epuserclassify", StringUtil.bvl(employee.getEpuserclassify(), _C_NULL));//실가명구분
		ht.put("serverlocation", StringUtil.bvl(employee.getServerlocation(), _C_NULL));//서버위치
		
		StringTokenizer st = new StringTokenizer(_inAttrs, " ");
		while (st.hasMoreTokens())
		{
			strKeyAttribute = st.nextToken();
			if (!ht.containsKey(strKeyAttribute))
			{
				ht.put(strKeyAttribute, StringUtil.bvl(getEmployeeValue(employee, strKeyAttribute), _C_NULL));
			}
		}

		//싱글가이드 참조 : epgradeortitle가 T면 title을 G면 epgradename을 B면 두값 모두 (title/epgradename)
		//공통 esb조회 jsp에서 직급명으로 title을 사용하고 있어서 아래와 같이 title에 값을 넣도록 설정함.
		if( StringUtil.bvl((String)ht.get("epgradeortitle"),"").equals("G")){
			ht.put("title",StringUtil.bvl((String)ht.get("epgradename"),""));
		}else if( StringUtil.bvl((String)ht.get("epgradeortitle"),"").equals("B")){
			ht.put("title",StringUtil.bvl((String)ht.get("title"),"")+ " " + StringUtil.bvl((String)ht.get("epgradename"),""));
		}
			
		return ht;
	}

	/**
	 * Employee객체에서 property에 해당하는 값을 리턴
	 * @param employee
	 * @param property
	 * @return String
	 */
	public String getEmployeeValue(Employee employee, String property)
	{
		if (property == null)
		{
			return null;
		}
		else if (property.equals("cn"))
		{
			return employee.getCn();
		}
		else if (property.equals("department"))
		{
			return employee.getDepartment();
		}
		else if (property.equals("departmentnumber"))
		{
			return employee.getDepartmentnumber();
		}
		else if (property.equals("description"))
		{
			return employee.getDescription();
		}
		else if (property.equals("employeenumber"))
		{
			return employee.getEmployeenumber();
		}
		else if (property.equals("employeetype"))
		{
			return employee.getEmployeetype();
		}
		else if (property.equals("epaccountstatus"))
		{
			return employee.getEpaccountstatus();
		}
		else if (property.equals("epbusicode"))
		{
			return employee.getEpbusicode();
		}
		else if (property.equals("epbusiname"))
		{
			return employee.getEpbusiname();
		}
		else if (property.equals("epenbusiname"))
		{
			return employee.getEpenbusiname();
		}
		else if (property.equals("epencn"))
		{
			return employee.getEpencn();
		}
		else if (property.equals("ependepartment"))
		{
			return employee.getEpendepartment();
		}
		else if (property.equals("ependescription"))
		{
			return employee.getEpendescription();
		}
		else if (property.equals("epengivenname"))
		{
			return employee.getEpengivenname();
		}
		else if (property.equals("epengradename"))
		{
			return employee.getEpengradename();
		}
		else if (property.equals("epenhomepostaladdress"))
		{
			return employee.getEpenhomepostaladdress();
		}
		else if (property.equals("epenorganizationname"))
		{
			return employee.getEpenorganizationname();
		}
		else if (property.equals("epenpostaladdress"))
		{
			return employee.getEpenpostaladdress();
		}
		else if (property.equals("epenregionname"))
		{
			return employee.getEpenregionname();
		}
		else if (property.equals("epensendbusiname"))
		{
			return employee.getEpensendbusiname();
		}
		else if (property.equals("epensendcompanyname"))
		{
			return employee.getEpensendcompanyname();
		}
		else if (property.equals("epensenddeptname"))
		{
			return employee.getEpensenddeptname();
		}
		else if (property.equals("epensendgradename"))
		{
			return employee.getEpensendgradename();
		}
		else if (property.equals("epensendregionname"))
		{
			return employee.getEpensendregionname();
		}
		else if (property.equals("epensendsuborgname"))
		{
			return employee.getEpensendsuborgname();
		}
		else if (property.equals("epensendtitlename"))
		{
			return employee.getEpensendtitlename();
		}
		else if (property.equals("epensn"))
		{
			return employee.getEpensn();
		}
		else if (property.equals("epensuborgname"))
		{
			return employee.getEpensuborgname();
		}
		else if (property.equals("epentitle"))
		{
			return employee.getEpentitle();
		}
		else if (property.equals("epgender"))
		{
			return employee.getEpgender();
		}
		else if (property.equals("epgradename"))
		{
			return employee.getEpgradename();
		}
		else if (property.equals("ephomel"))
		{
			return employee.getEphomel();
		}
		else if (property.equals("ephomepostalcode"))
		{
			return employee.getEphomepostalcode();
		}
		else if (property.equals("epid"))
		{
			return employee.getEpid();
		}
		else if (property.equals("eporganizationnumber"))
		{
			return employee.getEporganizationnumber();
		}
		else if (property.equals("epregioncode"))
		{
			return employee.getEpregioncode();
		}
		else if (property.equals("epregionname"))
		{
			return employee.getEpregionname();
		}
		else if (property.equals("epregisternumber"))
		{
			return employee.getEpregisternumber();
		}
		else if (property.equals("epsuborgcode"))
		{
			return employee.getEpsuborgcode();
		}
		else if (property.equals("epsuborgname"))
		{
			return employee.getEpsuborgname();
		}
		else if (property.equals("eptitlename"))
		{
			return employee.getEptitlename();
		}
		else if (property.equals("eptitlenumber"))
		{
			return employee.getEptitlenumber();
		}
		else if (property.equals("epuserstatus"))
		{
			return employee.getEpuserstatus();
		}
		else if (property.equals("homephone"))
		{
			return employee.getHomephone();
		}
		else if (property.equals("homepostaladdress"))
		{
			return employee.getHomepostaladdress();
		}
		else if (property.equals("l"))
		{
			return employee.getL();
		}
		else if (property.equals("mail"))
		{
			return employee.getMail();
		}
		else if (property.equals("mailHost"))
		{
			return employee.getMailHost();
		}
		else if (property.equals("mobile"))
		{
			return employee.getMobile();
		}
		else if (property.equals("o"))
		{
			return employee.getO();
		}
		else if (property.equals("facsimiletelephonenumber"))
		{
			return employee.getFacsimiletelephonenumber();
		}
		else if (property.equals("otherfacsimiletelephonenumber"))
		{
			return employee.getOtherfacsimiletelephonenumber();
		}
		else if (property.equals("ou"))
		{
			return employee.getOu();
		}
		else if (property.equals("postaladdress"))
		{
			return employee.getPostaladdress();
		}
		else if (property.equals("postalcode"))
		{
			return employee.getPostalcode();
		}
		else if (property.equals("sn"))
		{
			return employee.getSn();
		}
		else if (property.equals("telephonenumber"))
		{
			return employee.getTelephonenumber();
		}
		else if (property.equals("title"))
		{
			return employee.getTitle();
		}
		else if (property.equals("uid") || property.equals("userid"))
		{
			return employee.getUserid();
		}
		else if (property.equals("givenname"))
		{
			return employee.getGivenname();
		}
		else if (property.equals("eptitlesortorder"))
		{
			return employee.getEptitlesortorder();
		}
		else if (property.equals("epsendtitlesortorder"))
		{
			return employee.getEpsendtitlesortorder();
		}
		else
		{
			return _C_NA;
		}
	}

/**
 * 삼성전자를 가장 위쪽으로 보이도록 정렬하여 리턴한다.
 * @param obj1 비교할 임직원정보
 * @param obj2 비교할 임직원정보
 * @return 비교값
 */

	public int compare(Object obj1, Object obj2)
	{
		int intCompare = 0;
		String sToken = null;
		Hashtable employee1 = (Hashtable)obj1;
		Hashtable employee2 = (Hashtable)obj2;

		String s1 = StringUtil.bvl((String) employee1.get("eporganizationnumber"), _C_NULL);
		String s2 = StringUtil.bvl((String) employee2.get("eporganizationnumber"), _C_NULL);
		s1 = ("C10".equals(s1)) ? "000" : s1;
		s2 = ("C10".equals(s2)) ? "000" : s2;
		intCompare = s1.compareTo(s2);

		if (intCompare == 0)
		{
			StringTokenizer st = new StringTokenizer(_sortAttrs, " ");
			while (st.hasMoreTokens())
			{
				sToken = st.nextToken();
				s1 = StringUtil.bvl((String) employee1.get(sToken), _C_NULL);
				s2 = StringUtil.bvl((String) employee2.get(sToken), _C_NULL);
				if ((intCompare = s1.compareTo(s2)) != 0)
				{
					break;
				}
			}
		}
		return intCompare;
	}

	/**
	 * 웹서비스 객체를 통한 접근 포트 활성화한다.
	 * @return UserService
	 * @throws ServiceException
	 */
	public static UserService getESBClientStub() throws Exception
	{
		try
		{
			if (clientStub == null)
			{
				EmpServiceLocator empServiceLocator = new EmpServiceLocator();
				// 웹서비스 객체를 통한 접근 포트 활성화 Brice test 2020.02.21
				clientStub = empServiceLocator.getEmpService_InboundPort();
				//clientStub = empServiceLocator.getEmpService_InboundPort(new URL("http://182.198.117.37:8080/wsgwsoaphttp1/soaphttpengine/WSGWBus/EmpService/EmpService_InboundPort"));

			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return clientStub;
	}
	
	/**
	 * 웹서비스 객체를 통한 접근 포트 활성화한다.
	 * @return UserService
	 * @throws ServiceException
	 */
	public static OrganizationService getESBOrgStub() throws Exception
	{
		try
		{
			if (orgStub == null)
			{
				OrgServiceLocator orgServiceLocator = new OrgServiceLocator();
				// 웹서비스 객체를 통한 접근 포트 활성화
				orgStub = orgServiceLocator.getOrgService_InboundPort();
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return orgStub;
	}

	/**
	 * 임직원 이름으로 임직원 목록을 조회한다.
  	 *
	 * @param			userName 임직원 이름
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByName(String userName, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector();
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;

			// findByName 메소드 호출(마이싱글 제공 메소드)
			
			employee = clientStub.findByName(esbOrgId, userName, esbOrgPw);
			
			
			// null 처리추가 
			if(employee != null){
				for (int i=0, leni = employee.length; i < leni; i++)
				{
					Hashtable ht = new Hashtable();
					ht = getEmployeeToHashtable(employee[i], lc);
					ht.put("check", "");
					if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
						vtUserInfo.add(ht);
				}
			}

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return vtUserInfo;
	}
    
    /**
	 * 임직원 이름, 회사코드로 임직원 목록을 조회한다.
  	 *
	 * @param			userName 임직원 이름, authCompCd 회사코드
	 * @return			임직원 목록
	 * @throws 			Exception
	 */			  
    public Vector userSearchByCompName(String compCd, String userName, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector();
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
			
			employee = clientStub.findByName(esbOrgId, userName, esbOrgPw);
			
			if(employee != null){
				for (int i=0, leni = employee.length; i < leni; i++)
				{
					Hashtable ht = new Hashtable();
					ht = getEmployeeToHashtable(employee[i], lc);
					ht.put("check", "");
					if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
						vtUserInfo.add(ht);
				}
			}
			
//			String[] authCompCds = StringUtil.token(compCd, ",");
//			
//			for (String token : authCompCds) {
//				token = token.replace("'", "");
//				// findByCompName 메소드 호출(마이싱글 제공 메소드)
//				employee = clientStub.findByCompName(esbOrgId, token, userName, esbOrgPw);
//				if(employee == null) continue;
//				for (int i=0, leni = employee.length; i < leni; i++)
//				{
//					Hashtable ht = new Hashtable();
//					ht = getEmployeeToHashtable(employee[i], lc);
//					if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
//						vtUserInfo.add(ht);
//				}
//			
//			}

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
		}
		catch (AxisFault fault)
		{
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return vtUserInfo;
	}
    

	/**
	 * 마이싱글아이디로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByUid(String uid, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector();
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
	 			
			// findByUid 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByUid(esbOrgId, uid, esbOrgPw);
			
			for (int i=0, leni = employee.length; i < leni; i++)
			{
				Hashtable ht = new Hashtable();
				ht = getEmployeeToHashtable(employee[i], lc);
				ht.put("check", "Y");
				if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
					vtUserInfo.add(ht);
			}

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return vtUserInfo;
	}
    
    /**
	 * 마이싱글아이디, 회사코드로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByCompUid(String compCd, String uid, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector();
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
			
			employee = clientStub.findByCompUid(esbOrgId, compCd, uid, esbOrgPw);
			if(employee != null){
				for (int i=0, leni = employee.length; i < leni; i++)
				{
					Hashtable ht = new Hashtable();
					ht = getEmployeeToHashtable(employee[i], lc);
					ht.put("check", "Y");
					if( StringUtil.bvl((String)ht.get("epuserstatus"				),"").equals("B"))
						vtUserInfo.add(ht);
				}
			}
//			String[] authCompCds = StringUtil.token(authCompCd, ",");
//			
//			for (String token : authCompCds) {
//				token = token.replace("'", "");
//				// findByUid 메소드 호출(마이싱글 제공 메소드)
//				employee = clientStub.findByCompUid(esbOrgId, token, uid, esbOrgPw);
//	
//				for (int i=0, leni = employee.length; i < leni; i++)
//				{
//					Hashtable ht = new Hashtable();
//					ht = getEmployeeToHashtable(employee[i], lc);
//					if( StringUtil.bvl((String)ht.get("epuserstatus"				),"").equals("B"))
//						vtUserInfo.add(ht);
//				}
//			}

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return vtUserInfo;
	}
    
	/**
	 * 마이싱글아이디로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByUid(String uid) throws Exception {
		Employee employee[] = null;
		try
		{
			UserService clientStub = getESBClientStub();
	 			
			// findByUid 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByUid(esbOrgId, uid, esbOrgPw);
			employee = chgTitleByflag(employee);  
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return employee;
	}
    
    /**
	 * 조회된 Emplyee의 title값을  epgradeortitle의 값에 따라서 설정한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] chgTitleByflag(Employee[] employee){
    	
    	if(employee.length > 0){
    		
    	
    	
	    	String epgradeortitle = StringUtil.bvl(employee[0].getEpgradeortitle(),"");
	    	
	    	
	    	//싱글가이드 참조 : epgradeortitle가 T면 title을 G면 epgradename을 B면 두값 모두 (title/epgradename)
			//공통 esb조회 jsp에서 직급명으로 title을 사용하고 있어서 아래와 같이 title에 값을 넣도록 설정함.
			if( epgradeortitle.equals("G")){
				employee[0].setTitle(StringUtil.bvl(employee[0].getEpengradename(), "")); 
			}else if( epgradeortitle.equals("B")){
				employee[0].setTitle(StringUtil.bvl(employee[0].getTitle(), "") + " " + 
						StringUtil.bvl(employee[0].getEpengradename(), "") ); 
			}
		
    	}
		
		
    	return employee;
    }
    
    
    /**
	 * 마이싱글아이디로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByCompUid(String compCd, String uid) throws Exception {
		Employee employee[] = null;
		try
		{
			UserService clientStub = getESBClientStub();
			
			employee = clientStub.findByCompUid(esbOrgId, compCd, uid, esbOrgPw);
			employee = chgTitleByflag(employee); 
	 			
//			String[] authCompCds = StringUtil.token(compCd, ",");
//			
//			for (String token : authCompCds) {
//				token = token.replace("'", "");
//			// findByUid 메소드 호출(마이싱글 제공 메소드)
//				employee = (Employee[])ArrayUtils.addAll(employee, clientStub.findByCompUid(esbOrgId, compCd, uid, esbOrgPw));
//			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		
		return employee;
	}
    
   
    
	/**
	 * 임직원 EP ID으로 임직원 목록을 조회한다.
  	 *
	 * @param			epid EP_ID
	 * @return			임직원 목록
	 */
    public Vector userSearchByEpid(String epid, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector(1);
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
			
			// findByName 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByEpId(esbOrgId, epid, esbOrgPw);

			for (int i=0, leni = employee.length; i < leni; i++)
			{
				Hashtable ht = new Hashtable();
				ht = getEmployeeToHashtable(employee[i], lc);
				ht.put("check", "Y");
				if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
					vtUserInfo.add(ht);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return vtUserInfo;
	}
    
    /**
	 * 임직원 EP ID으로 임직원 목록을 조회한다.
  	 *
	 * @param			epid EP_ID
	 * @return			임직원 목록
	 */
    public Vector userSearchByCompEpid(String compCd, String epid, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector(1);
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
			
			employee = clientStub.findByCompEpId(esbOrgId, compCd, epid, esbOrgPw);
			
			for (int i=0, leni = employee.length; i < leni; i++)
			{
				Hashtable ht = new Hashtable();
				ht = getEmployeeToHashtable(employee[i], lc);
				ht.put("check", "Y");
				if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
					vtUserInfo.add(ht);
			}
			
//			authCompCd = StringUtil.replace(authCompCd, "'", "");
//			
//			String[] authCompCds = StringUtil.token(authCompCd, ",");
//			
//			for (String token : authCompCds) {
//				token = token.replace("'", "");
//				// findByName 메소드 호출(마이싱글 제공 메소드)
//				employee = clientStub.findByCompEpId(esbOrgId, authCompCd, epid, esbOrgPw);
//	
//				for (int i=0, leni = employee.length; i < leni; i++)
//				{
//					Hashtable ht = new Hashtable();
//					ht = getEmployeeToHashtable(employee[i], lc);
//					if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
//						vtUserInfo.add(ht);
//				}
//			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return vtUserInfo;
	}

	/**
	 * 마이싱글 EPID로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByEpid(String epid) throws Exception {
		Employee employee[] = null;
		try
		{
			UserService clientStub = getESBClientStub();
	 			
			// findByUid 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByEpId(esbOrgId, epid, esbOrgPw);
			employee = chgTitleByflag(employee); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return employee;
	}
    
    /**
	 * 마이싱글 EPID로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByCompEpid(String compCd, String epid) throws Exception {
		Employee employee[] = null;
		try
		{
			UserService clientStub = getESBClientStub();
			employee = clientStub.findByCompEpId(esbOrgId, compCd, epid, esbOrgPw);
			employee = chgTitleByflag(employee); 
			
//			String[] authCompCds = StringUtil.token(compCd, ",");
//			
//			for (String token : authCompCds) {
//			// findByUid 메소드 호출(마이싱글 제공 메소드)
//				token = token.replace("'", "");
//				employee = (Employee[])ArrayUtils.addAll(employee, clientStub.findByCompEpId(esbOrgId, token, epid, esbOrgPw));
//			}
	 			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return employee;
	}
    
	/**
	 * 마이싱글 EPID 배열로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByEpidList(String[] epid) throws Exception {
		Employee employee[] = null;
		try
		{
			UserService clientStub = getESBClientStub();
	 			
			// findByUid 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByEpIdList(esbOrgId, epid, esbOrgPw);
			employee = chgTitleByflag(employee); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//throw e; //이거 에러를 던지면 legacyinterface 패키지의 모듈에서 문제가 될 수 있음... 주의
		}
		return employee;
	}    
    
    /**
	 * 마이싱글 EPID 배열로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByCompEpidList(String compCd, String[] epid) throws Exception {
		Employee employee[] = null;
		try
		{
			UserService clientStub = getESBClientStub();
			employee = clientStub.findByCompEpIdList(esbOrgId, compCd, epid, esbOrgPw);
			employee = chgTitleByflag(employee); 
//			String[] authCompCds = StringUtil.token(compCd, ",");
//			
//			for (String token : authCompCds) {
//			// findByUid 메소드 호출(마이싱글 제공 메소드)
//				token = token.replace("'", "");
//				employee = (Employee[])ArrayUtils.addAll(employee, clientStub.findByCompEpIdList(esbOrgId, compCd, epid, esbOrgPw));
//			}
	 			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//throw e; //이거 에러를 던지면 legacyinterface 패키지의 모듈에서 문제가 될 수 있음... 주의
		}
		return employee;
	}    
    
	/**
	 * 임직원 사번으로 임직원 목록을 조회한다.
  	 *
	 * @param			empNo 사번
	 * @return			임직원 목록
	 */
    public Vector userSearchByEmpNo(String empNo, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector(1);
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;

			// findByName 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByEmployeeNumber(esbOrgId, empNo, esbOrgPw);

			for (int i=0, leni = employee.length; i < leni; i++)
			{
				if( StringUtil.bvl(employee[i].getEporganizationnumber(),"").equals("C90")){
					Hashtable ht = new Hashtable();
					ht = getEmployeeToHashtable(employee[i], lc);
					ht.put("check", "Y");
					if( StringUtil.bvl((String)ht.get("epuserstatus"				),"").equals("B"))
						vtUserInfo.add(ht);
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return vtUserInfo;
	}

	/**
	 * 임직원 사번으로 임직원 목록을 조회한다.
  	 *
	 * @param			empNo 사번
	 * @return			임직원 목록
	 */
    public Employee[] userSearchByEmpNo(String empNo) throws Exception {
		Employee employee[] = null;
		try
		{
			UserService clientStub = getESBClientStub();
	 			
			// findByUid 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByEmployeeNumber(esbOrgId, empNo, esbOrgPw);
			employee = chgTitleByflag(employee); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return employee;
	}
    
	/**
	 * 임직원 주민등록번호으로 임직원 목록을 조회한다.
  	 *
	 * @param			idenNo 주민번호
	 * @return			임직원 목록
	 */
    public Vector userSearchByIdenNo(String idenNo, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector(1);
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;

			// findByName 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByRegisterNumber(esbOrgId, idenNo, esbOrgPw);

			for (int i=0, leni = employee.length; i < leni; i++)
			{
				Hashtable ht = new Hashtable();
				ht = getEmployeeToHashtable(employee[i], lc);
				ht.put("check", "Y");
				if( StringUtil.bvl((String)ht.get("epuserstatus"				),"").equals("B"))
					vtUserInfo.add(ht);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return vtUserInfo;
	}

	/**
	 * mySingle 메일 연계용 발신자("홍길동"<mailadd@samsung.com>) 을 조회한다.
  	 *
	 * @param			epid EP ID
	 * @return			"홍길동"<mailadd@samsung.com>
	 */
    public String userEpSenderSearch(String epid) throws Exception {
		String sender	= "\"\"<>";

		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;

			// findByName 메소드 호출(마이싱글 제공 메소드)
			employee = clientStub.findByEpId(esbOrgId, epid, esbOrgPw);

			if (employee.length > 0)
			{
				sender = "\"" + employee[0].getCn()+ "\"<" + employee[0].getMail().replaceAll("stage.samsung.com","samsung.com")+ ">";
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return sender;
	}

	/**
	 * 해당부서코드에 속한 임직원 정보를 조회한다.
  	 *
	 * @param			departmentnumber 부서코드
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByDepartmentNumber(String departmentNumber, Locale lc) throws Exception {
		Vector vtUserInfo = new Vector();
		try
		{
			UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;

			// findByName 메소드 호출(마이싱글 제공 메소드)
			
			employee = clientStub.findByDepartmentNumber(esbOrgId, departmentNumber, esbOrgPw);

			for (int i=0, leni = employee.length; i < leni; i++)
			{
				Hashtable ht = new Hashtable();
				ht = getEmployeeToHashtable(employee[i], lc);
				ht.put("check", "");
				if( StringUtil.bvl((String)ht.get("epuserstatus"				),"").equals("B"))
					vtUserInfo.add(ht);
			}

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return vtUserInfo;
	}
    
	/**
	 * mySingle 메일 연계용 메일주소를 조회한다.
  	 *
	 * @param			epid EP ID
	 * @return			mailadd@
	 */
    public String getUserEpMailAddr(String epid) throws Exception {
		String sender	= "";

		try
		{
			//메일주소 요청 전 tb_com_user에서 메일수신 여부가 'Y'일 때만 email return 
			HashMap map = new HashMap();
			map.put("user_id", epid);
			
			if(commonDAO.list("secfw.user.userEmailSendYn", map).size() > 0){
			
				UserService clientStub = getESBClientStub();
				// employee 배열객체 초기화
				Employee employee[] = null;
	
				// findByName 메소드 호출(마이싱글 제공 메소드)
				
				employee = clientStub.findByEpId(esbOrgId, epid, esbOrgPw);
	
				if (employee.length > 0)
				{
					sender = employee[0].getMail();
				}
			}
			
		}
		catch (Exception e)
		{
			throw e;
		}
		return sender;
	}
    
	/**
	 * 부서정보 BATCH.
  	 *
	 * @param		
	 * @return		
	 */
    public void loadESBOrgData() throws Exception {
    	
    	if(comUtilService.isCronServer()) {

			OrganizationService orgStub = getESBOrgStub();
	
			// findByName 메소드 호출(마이싱글 제공 메소드)
	        // organizations = orgStub.findByEpOrganizationNumber(esbOrgId, esbOrgId.substring(0, 3), esbOrgPw);
	
			//부서정보 테이블(Temp Table) 데이타 삭제
			commonDAO.delete("secfw.dept.deleteDeptTemp");
	
			for (int i=1;; i++) {
				Organization[] organizations = null;
				
				organizations = orgStub.findByEpOrganizationNumberWithPaging(esbOrgId, esbOrgId.substring(0, 3), String.valueOf(i), esbOrgPw);
				
				if(organizations == null || organizations.length == 0) {
					break;
				} 
				
				//ArrayList al = new ArrayList();
				
				for(int j=0; j<organizations.length; j++) {
					//부서정보 입력(Temp Table)
					Organization orgVO = new Organization();
					orgVO = organizations[j];
					
					DeptVO deptVO = new DeptVO();
					
					deptVO.setDept_cd               (orgVO.getDepartmentnumber());
					deptVO.setDept_nm               (orgVO.getOu());
					deptVO.setDept_nm_eng           (orgVO.getEpendepartment());
					deptVO.setDept_level            (Integer.parseInt(orgVO.getEpdeptlevel()));
					deptVO.setDept_order            (Integer.parseInt(orgVO.getEpdeptorder()));
					deptVO.setIn_dept_cd            (orgVO.getEpindeptcode());
					deptVO.setUp_dept_cd            (orgVO.getEphighdeptnumber());
					deptVO.setUp_dept_nm            (orgVO.getEphighdeptname());
					deptVO.setUp_dept_nm_eng        (orgVO.getEpenhighdeptname());
					deptVO.setComp_cd               (orgVO.getEporganizationnumber());
					deptVO.setComp_nm               (orgVO.getO());
					deptVO.setComp_nm_eng           (orgVO.getEpenorganizationname());
					deptVO.setBusiness_cd           ("");
					deptVO.setBusiness_nm           ("");
					deptVO.setDown_dept_yn          (orgVO.getEplowdept());
					deptVO.setDept_mgr_emp_no       (orgVO.getEpmanagerid());
					deptVO.setDept_mgr_nm           (orgVO.getEpmanagername());
		//			deptVO.setDept_mgr_jikgup_cd    ("");
					deptVO.setDept_mgr_jikgup_nm    (orgVO.getEpmanagertitle());
					deptVO.setDept_mgr_jikgup_nm_eng(orgVO.getEpenmanagertitle());
		//			deptVO.setReorg_ymd             ("");
		//			deptVO.setLoad_version          ("");
					
					//al.add(deptVO);
					commonDAO.insert("secfw.dept.insertDeptTemp", deptVO);
				}
				//commonDAO.batchUpdate("secfw.dept.insertDeptTemp", al);
					
			}
			
			//운영테이블 데이타 삭제
			commonDAO.delete("secfw.dept.deleteDept");
			
			//운영테이블로 데이타 이관 (Temp Table -> Real Table)
			commonDAO.insert("secfw.dept.insertDept");
    	}
    }
    
    /**
	 * 사용자 소속 조직코드 조회
  	 * 작성자: 소유진 
	 * @param			부서코드(ESB에서 조회해온)
	 * @return			조직코드 
	 */
    

	@Override
	public String getOrgnzCd(String departmentNumber) throws Exception{
		HashMap retMap = new HashMap();
		String orgnzCd ="";
		// TODO Auto-generated method stub
		retMap.put("locale", "ko");
		retMap.put("dept_cd", departmentNumber);
		List list = commonDAO.list("secfw.user.listOrgnzList", retMap);
		
		if(list != null && list.size() > 0){
			ListOrderedMap map = (ListOrderedMap)list.get(0);
			orgnzCd = StringUtil.nvl((String)map.get("orgnz_cd"),"");		
		}
		return orgnzCd;
	}
	
	/**
	 * 회사코드로 조직도를 조회한다
  	 *
	 * @param			compCd 회사코드
	 * @return			조직도
	 */
    public Organization[] listDeptTreeByCompCd(String compCd, String upDeptCd) throws Exception {
    	Organization orgArr[] = null;
		try
		{
			OrganizationService clientStub = getESBOrgStub();
			orgArr = clientStub.findByEpHighDeptNumber(esbOrgId, compCd, upDeptCd, esbOrgPw);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return orgArr;
    }
    
    /**
	 * 부서명으로 조직도를 조회한다
  	 *
	 * @param			compCd 회사코드 deptNm 부서명
	 * @return			조직도
	 */
    public Organization[] listDeptByDeptNm(String compCd, String deptNm) throws Exception {
    	Organization orgArr[] = null;
    	
    	try {
    		OrganizationService clientStub = getESBOrgStub();
    		orgArr = clientStub.findByDepartmentName(esbOrgId, compCd, deptNm, esbOrgPw);
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	
    	return orgArr;
    }
    
    /**
	 * 부서번호로 조직도를 조회한다
  	 *
	 * @param			compCd 회사코드 deptNo 부서번호
	 * @return			조직도
	 */
    public Organization[] listDeptByDeptNo(String compCd, String deptNo) throws Exception {
    	Organization orgArr[] = null;
    	
    	try {
    		OrganizationService clientStub = getESBOrgStub();
    		orgArr = clientStub.findByDepartmentNumber(esbOrgId, compCd, deptNo, esbOrgPw);
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	
    	return orgArr;
    }
    
    /**
     * 사용자가 입력한 email로 사용자를 조회 한다.
     * 
     * @param email
     * @return 사용자 정보
     */
    public Vector userSearchByMail(String email, Locale lc) throws Exception{
    	
    	Vector vtUserInfo = new Vector();
    	
    	try{
    		
    		UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
			
			String splitEmail = "";

			// findByName 메소드 호출(마이싱글 제공 메소드)
			
			if(!"".equals(email)){
					
				employee = clientStub.findByMail(esbOrgId, email, esbOrgPw);
				
			}
			
			if(employee != null){
				for (int i=0, leni = employee.length; i < leni; i++)
				{
					Hashtable ht = new Hashtable();
					ht = getEmployeeToHashtable(employee[i], lc);
					ht.put("check", "Y");
					if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
						vtUserInfo.add(ht);
				}
			}			

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
    		
    		
    	} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	
    	return vtUserInfo;
    	
    }
    
    
    /**
     * 사용자가 입력한 email로 사용자를 조회 한다.
     * 
     * @param email
     * @return 사용자 정보
     */
    public Vector userSearchByMultMail(String email[], Locale lc) throws Exception{
    	
    	Vector vtUserInfo = new Vector();
    	
    	try{
    		
    		UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
			
			String splitEmail = "";

			// findByName 메소드 호출(마이싱글 제공 메소드)
			
			
			
			if(email.length > 0){
					
				employee = clientStub.findByMailList(esbOrgId, email, esbOrgPw);
				
			}
			
			if(employee != null){
				for (int i=0, leni = employee.length; i < leni; i++)
				{
					Hashtable ht = new Hashtable();
					ht = getEmployeeToHashtable(employee[i], lc);
					ht.put("check", "Y");
					if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
						vtUserInfo.add(ht);
				}
			}			

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
    		
    		
    	} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	
    	return vtUserInfo;
    	
    }
    
    
    
    /**
     * 사용자가 입력한 id로 사용자를 멀티로 조회 한다.
     * 
     * @param email
     * @return 사용자 정보
     */
    public Vector userSearchByMultId(String id[], Locale lc) throws Exception{
    	
    	Vector vtUserInfo = new Vector();
    	
    	try{
    		
    		UserService clientStub = getESBClientStub();
			// employee 배열객체 초기화
			Employee employee[] = null;
			
			// findByEpIdList 메소드 호출(마이싱글 제공 메소드)
			
			if(id.length > 0){
					
				employee = clientStub.findByUidList(esbOrgId, id, esbOrgPw);
				
			}
			
			if(employee != null){
				for (int i=0, leni = employee.length; i < leni; i++)
				{
					Hashtable ht = new Hashtable();
					ht = getEmployeeToHashtable(employee[i], lc);
					ht.put("check", "Y");
					if( StringUtil.bvl((String)ht.get("epuserstatus"),"").equals("B"))
						vtUserInfo.add(ht);
				}
			}			

			if (!vtUserInfo.isEmpty())
			{
				Collections.sort(vtUserInfo, new EsbOrgServiceImpl());
			}
    		
    		
    	} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    	
    	return vtUserInfo;
    	
    }
    
    
}
