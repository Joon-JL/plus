package com.sds.secframework.auth.service;

public interface MfaAuthService {
    /**
     * 6자리 난수 인증코드를 생성하고 대상 이메일로 발송
     * @param email 수신자 이메일 주소
     * @return 생성된 6자리 인증코드 (세션 저장용)
     * @throws Exception
     */
    String sendMfaCodeEmail(String email) throws Exception;
}
