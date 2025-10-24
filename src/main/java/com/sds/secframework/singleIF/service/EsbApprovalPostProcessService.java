package com.sds.secframework.singleIF.service;

import com.sds.secframework.singleIF.dto.ApprovalVO;

/**
 * <P>Single ESB 결재 후처리 Process Interface</P>
 * 결재완료된 건에 대하여 문건 상신시 등록한 메소드명을
 * Java Reflection 기능을 사용하여 동적으로 호출처리
 * 
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public interface EsbApprovalPostProcessService {

	
	/**
	 * <P>Single결재 후처리 Process Method</P>
	 * 결재상태 조회시 완결된 문건에 대한 후처리 프로세스 지원.<BR>
	 * Java Reflection을 이용한 Method Invoke방식.
	 * 
	 * @param argModuleId:모듈 ID, argMisId:MIS ID(ESB연동KEY), argStatus:결재상태코드
	 */
	public void postProcess(String argModuleId, String argMisId,String argStatus);
	
}
