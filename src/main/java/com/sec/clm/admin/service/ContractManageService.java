package com.sec.clm.admin.service;

import com.sec.clm.admin.dto.ContractManageVO;

import java.util.List;

public interface ContractManageService {

    List listContracts(ContractManageVO vo) throws Exception;

    int changeCntrtStatus(ContractManageVO vo) throws Exception;

}
