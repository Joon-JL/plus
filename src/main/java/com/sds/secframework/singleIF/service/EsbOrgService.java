package com.sds.secframework.singleIF.service;

import java.util.Locale;
import java.util.Vector;

import model.outldap.samsung.net.Employee;
import model.outldap.samsung.net.Organization;

public interface EsbOrgService {

	/**
	 * Employee객체에서 property에 해당하는 값을 리턴
	 * @param employee
	 * @param property
	 * @return String
	 */
	public String getEmployeeValue(Employee employee, String property) throws Exception;
	
	/**
	 * 임직원 이름으로 임직원 목록을 조회한다.
  	 *
	 * @param			userName 임직원 이름
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByName(String userName, Locale lc) throws Exception;
    
    /**
	 * 임직원 이름, 회사코드로 임직원 목록을 조회한다.
  	 *
	 * @param			userName 임직원 이름
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByCompName(String compCd, String userName, Locale lc) throws Exception;

	/**
	 * 마이싱글아이디로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByUid(String uid, Locale lc) throws Exception;
    
    /**
	 * 마이싱글아이디, 회사코드로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByCompUid(String compCd, String uid, Locale lc) throws Exception;
    
	/**
	 * 마이싱글아이디로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByUid(String uid) throws Exception;
    
    /**
	 * 마이싱글아이디로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByCompUid(String compCd, String uid) throws Exception;
    
	/**
	 * 임직원 EP ID으로 임직원 목록을 조회한다.
  	 *
	 * @param			epid EP_ID
	 * @return			임직원 목록
	 */
    public Vector userSearchByEpid(String epid, Locale lc) throws Exception;
    
    /**
	 * 임직원 EP ID으로 임직원 목록을 조회한다.
  	 *
	 * @param			epid EP_ID
	 * @return			임직원 목록
	 */
    public Vector userSearchByCompEpid(String compCd, String epid, Locale lc) throws Exception;
    
	/**
	 * 마이싱글 EPID로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByEpid(String epid) throws Exception;
    
    /**
	 * 마이싱글 EPID로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByCompEpid(String compCd, String epid) throws Exception;

	/**
	 * 마이싱글 EPID 배열로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByEpidList(String[] epid) throws Exception;
    
    /**
	 * 마이싱글 EPID 배열로 임직원 목록을 조회한다.
  	 *
	 * @param			uid 마이싱글아이디
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Employee[] userSearchByCompEpidList(String compCd, String[] epid) throws Exception;
    
	/**
	 * 임직원 사번으로 임직원 목록을 조회한다.
  	 *
	 * @param			empNo 사번
	 * @return			임직원 목록
	 */
    public Vector userSearchByEmpNo(String empNo, Locale lc) throws Exception;
    
	/**
	 * 임직원 사번으로 임직원 목록을 조회한다.
  	 *
	 * @param			empNo 사번
	 * @return			임직원 목록
	 */
    public Employee[] userSearchByEmpNo(String empNo) throws Exception;
    
	/**
	 * 임직원 주민등록번호으로 임직원 목록을 조회한다.
  	 *
	 * @param			idenNo 주민번호
	 * @return			임직원 목록
	 */
    public Vector userSearchByIdenNo(String idenNo, Locale lc) throws Exception;
    
	/**
	 * mySingle 메일 연계용 발신자("홍길동"<mailadd@samsung.com>) 을 조회한다.
  	 *
	 * @param			epid EP ID
	 * @return			"홍길동"<mailadd@samsung.com>
	 */
    public String userEpSenderSearch(String epid) throws Exception;
    
    /**
	 * 해당부서코드에 속한 임직원 정보를 조회한다.
  	 *
	 * @param			departmentnumber 부서코드
	 * @return			임직원 목록
	 * @throws 			Exception
	 */
    public Vector userSearchByDepartmentNumber(String departmentNumber, Locale lc) throws Exception;
    
	/**
	 * mySingle 메일 연계용 메일주소를 조회한다.
  	 *
	 * @param			epid EP ID
	 * @return			mailadd@
	 */
    public String getUserEpMailAddr(String epid) throws Exception;
    
	/**
	 * 부서정보 BATCH.
  	 *
	 * @param		
	 * @return		
	 */
    public void loadESBOrgData() throws Exception;
    
    /**
	 * 사용자 소속 조직코드 조회
  	 * 작성자: 소유진 
	 * @param			부서코드(ESB에서 조회해온)
	 * @return			조직코드 
	 */
    
    public String getOrgnzCd(String departmentNumber) throws Exception; 
    
    
    /**
	 * 회사코드로 조직도를 조회한다
  	 *
	 * @param			compCd 회사코드
	 * @return			조직도
	 */
    public Organization[] listDeptTreeByCompCd(String compCd, String upDeptCd) throws Exception;
    
    /**
	 * 회사코드, 조직명으로 조직을 조회한다
  	 *
	 * @param			compCd 회사코드, deptNm 조직명
	 * @return			조직도
	 */
    public Organization[] listDeptByDeptNm(String compCd, String deptNm) throws Exception;
    /**
	 * 회사코드, 조직번호으로 조직을 조회한다
  	 *
	 * @param			compCd 회사코드, deptNo 조직번호
	 * @return			조직도
	 */
    public Organization[] listDeptByDeptNo(String compCd, String deptNo) throws Exception;
    
    /**
     * 사용자가 입력한 email로 사용자를 조회 한다.
     * 
     * @param email
     * @return 사용자 정보
     */
    
    public Vector userSearchByMultMail(String setInputWord[], Locale lc) throws Exception;
    
    /**
     * 사용자가 입력한 email로 사용자를 조회 한다.
     * 
     * @param email
     * @return 사용자 정보
     */
    
    public Vector userSearchByMail(String setInputWord, Locale lc) throws Exception;
    
    
    /**
     * 사용자가 입력한 id로 사용자를 멀티로 조회 한다.
     * 
     * @param email
     * @return 사용자 정보
     */
    
    public Vector userSearchByMultId(String setInputWord[], Locale lc) throws Exception;
    
    
}
