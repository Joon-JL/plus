package com.sec.las.lawconsulting.service;

import java.util.HashMap;

import com.sec.las.lawconsulting.dto.LawConsultVO;

public interface CounselHttpInvokerClientService {

	String sendCounselToCPMS (LawConsultVO vo) throws Exception;
}
