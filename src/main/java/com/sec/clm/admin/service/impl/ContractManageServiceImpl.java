package com.sec.clm.admin.service.impl;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.admin.dto.ContractManageVO;
import com.sec.clm.admin.service.ContractManageService;

import java.util.List;

public class ContractManageServiceImpl extends CommonServiceImpl implements ContractManageService {

    @Override
    public List listContracts(ContractManageVO vo) throws Exception {
        return commonDAO.list("clm.admin.listContracts", vo);
    }

    @Override
    public int changeCntrtStatus(ContractManageVO vo) throws Exception {
        return commonDAO.modify("clm.admin.changeContractStatus", vo);
    }
}
