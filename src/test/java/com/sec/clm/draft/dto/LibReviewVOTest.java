package com.sec.clm.draft.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class LibReviewVOTest {

    @Test(timeout = 20000)
    public void testGetLib_noShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getLib_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLib_noWithIntLibNoShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        int lib_no = 0;
        boolean isNotException = true;
        try {
            clazz.setLib_no(lib_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLib_gbnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getLib_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLib_gbnWithStringLibGbnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String lib_gbn = "lib_gbn";
        boolean isNotException = true;
        try {
            clazz.setLib_gbn(lib_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegion_gbnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
        LibReviewVO clazz = new LibReviewVO();
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
    public void testGetTitleShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitleWithStringTitleShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String title = "title";
        boolean isNotException = true;
        try {
            clazz.setTitle(title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContWithStringContShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String cont = "cont";
        boolean isNotException = true;
        try {
            clazz.setCont(cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRdcntShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getRdcnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRdcntWithIntRdcntShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        int rdcnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRdcnt(rdcnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String reg_dt = "reg_dt";
        boolean isNotException = true;
        try {
            clazz.setReg_dt(reg_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String reg_id = "reg_id";
        boolean isNotException = true;
        try {
            clazz.setReg_id(reg_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String reg_nm = "reg_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_nm(reg_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtWithStringModDtShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String mod_dt = "mod_dt";
        boolean isNotException = true;
        try {
            clazz.setMod_dt(mod_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_idShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_idWithStringModIdShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String mod_id = "mod_id";
        boolean isNotException = true;
        try {
            clazz.setMod_id(mod_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_nmShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_nmWithStringModNmShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String mod_nm = "mod_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_nm(mod_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String del_yn = "del_yn";
        boolean isNotException = true;
        try {
            clazz.setDel_yn(del_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_dtShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String del_dt = "del_dt";
        boolean isNotException = true;
        try {
            clazz.setDel_dt(del_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String del_id = "del_id";
        boolean isNotException = true;
        try {
            clazz.setDel_id(del_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_nmShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String del_nm = "del_nm";
        boolean isNotException = true;
        try {
            clazz.setDel_nm(del_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_biz_clsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_biz_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_biz_clsfcnWithStringSrchBizClsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String srch_biz_clsfcn = "srch_biz_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_biz_clsfcn(srch_biz_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_depth_clsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_depth_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_depth_clsfcnWithStringSrchDepthClsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String srch_depth_clsfcn = "srch_depth_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_depth_clsfcn(srch_depth_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnclsnpurps_bigclsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnclsnpurps_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnclsnpurps_bigclsfcnWithStringSrchCnclsnpurpsBigclsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String srch_cnclsnpurps_bigclsfcn = "srch_cnclsnpurps_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnclsnpurps_bigclsfcn(srch_cnclsnpurps_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnclsnpurps_midclsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnclsnpurps_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnclsnpurps_midclsfcnWithStringSrchCnclsnpurpsMidclsfcnShouldNotThrowException() {
        LibReviewVO clazz = new LibReviewVO();
        String srch_cnclsnpurps_midclsfcn = "srch_cnclsnpurps_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnclsnpurps_midclsfcn(srch_cnclsnpurps_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
