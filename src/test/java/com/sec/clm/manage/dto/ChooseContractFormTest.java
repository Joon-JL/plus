package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ChooseContractFormTest {

    @Test(timeout = 20000)
    public void testGetCnsdreq_idShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getCnsdreq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdreq_idWithStringCnsdreqIdShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String cnsdreq_id = "cnsdreq_id";
        boolean isNotException = true;
        try {
            clazz.setCnsdreq_id(cnsdreq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String cntrt_id = "cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setCntrt_id(cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_nmShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_nmWithStringCntrtNmShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String cntrt_nm = "cntrt_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_nm(cntrt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegion_gbnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getRegion_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegion_gbnWithStringRegionGbnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String region_gbn = "region_gbn";
        boolean isNotException = true;
        try {
            clazz.setRegion_gbn(region_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBiz_clsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getBiz_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBiz_clsfcnWithStringBizClsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String biz_clsfcn = "biz_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setBiz_clsfcn(biz_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDepth_clsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getDepth_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDepth_clsfcnWithStringDepthClsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String depth_clsfcn = "depth_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setDepth_clsfcn(depth_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_bigclsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_bigclsfcnWithStringCnclsnpurpsBigclsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String cnclsnpurps_bigclsfcn = "cnclsnpurps_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_bigclsfcn(cnclsnpurps_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_midclsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_midclsfcnWithStringCnclsnpurpsMidclsfcnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String cnclsnpurps_midclsfcn = "cnclsnpurps_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_midclsfcn(cnclsnpurps_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_midclsfcn_etcShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_midclsfcn_etc();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_midclsfcn_etcWithStringCnclsnpurpsMidclsfcnEtcShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String cnclsnpurps_midclsfcn_etc = "cnclsnpurps_midclsfcn_etc";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_midclsfcn_etc(cnclsnpurps_midclsfcn_etc);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWorktypeShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getWorktype();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWorktypeWithStringWorktypeShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String worktype = "worktype";
        boolean isNotException = true;
        try {
            clazz.setWorktype(worktype);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrop_ynShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getDrop_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrop_ynWithStringDropYnShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String drop_yn = "drop_yn";
        boolean isNotException = true;
        try {
            clazz.setDrop_yn(drop_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_id_arrShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_id_arrWithStringArrayCntrtIdArrShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String[] cntrt_id_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCntrt_id_arr(cntrt_id_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApproval_yn_arrShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        boolean isNotException = true;
        try {
            clazz.getApproval_yn_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApproval_yn_arrWithStringArrayApprovalYnArrShouldNotThrowException() {
        ChooseContractForm clazz = new ChooseContractForm();
        String[] approval_yn_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setApproval_yn_arr(approval_yn_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
