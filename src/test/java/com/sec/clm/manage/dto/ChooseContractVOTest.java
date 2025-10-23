package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import java.util.List;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ChooseContractVOTest {

    @Test(timeout = 20000)
    public void testGetCnsdreq_idShouldNotThrowException() {
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
    public void testGetCntrt_id_arrShouldNotThrowException() {
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
        ChooseContractVO clazz = new ChooseContractVO();
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
