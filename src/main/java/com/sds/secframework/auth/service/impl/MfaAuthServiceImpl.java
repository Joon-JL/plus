package com.sds.secframework.auth.service.impl;

import java.security.SecureRandom;

import com.sds.secframework.auth.service.MfaAuthService;
import org.springframework.stereotype.Service;

@Service("mfaAuthService")
public class MfaAuthServiceImpl implements MfaAuthService {

    public String sendMfaCodeEmail(String email) throws Exception {

        // 1. Java 1.8 SecureRandom으로 6자리 난수 생성
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(900000) + 100000;
        String mfaCode = String.valueOf(num);

        // 2. 개발/테스트용 콘솔 대형 강조 출력
        System.out.println("==================================================");
        System.out.println("  [MFA TEST CODE GENERATED]");
        System.out.println("  - Target Email : " + email);
        System.out.println("  - AUTH CODE    : >>> " + mfaCode + " <<<");
        System.out.println("==================================================");

        // 메일 발송 로직 제외, 생성된 코드만 세션 보관용으로 리턴
        return mfaCode;
    }
}
