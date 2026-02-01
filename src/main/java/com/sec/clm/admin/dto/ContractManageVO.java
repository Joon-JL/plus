package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonVO;

public class ContractManageVO extends CommonVO {


    public String getCntrt_no() {
        return cntrt_no;
    }

    public void setCntrt_no(String cntrt_no) {
        this.cntrt_no = cntrt_no;
    }

    private String cntrt_no;

    private String srch_cntrt_no;
    private String newStatus;
    private String cntrtStatus;

    public String getSrch_cntrt_no() {
        return srch_cntrt_no;
    }

    public void setSrch_cntrt_no(String srch_cntrt_no) {
        this.srch_cntrt_no = srch_cntrt_no;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getCntrtStatus() {
        return cntrtStatus;
    }

    public void setCntrtStatus(String cntrtStatus) {
        this.cntrtStatus = cntrtStatus;
    }
}
