package com.sds.secframework.menu.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class PageMngVOTest {

    @Test(timeout = 20000)
    public void testGetPage_idShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getPage_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_idWithStringPageIdShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String page_id = "page_id";
        boolean isNotException = true;
        try {
            clazz.setPage_id(page_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_nmShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getPage_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_nmWithStringPageNmShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String page_nm = "page_nm";
        boolean isNotException = true;
        try {
            clazz.setPage_nm(page_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_urlShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getPage_url();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_urlWithStringPageUrlShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String page_url = "page_url";
        boolean isNotException = true;
        try {
            clazz.setPage_url(page_url);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_ynShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getUse_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUse_ynWithStringUseYnShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String use_yn = "use_yn";
        boolean isNotException = true;
        try {
            clazz.setUse_yn(use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuthcheck_ynShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getAuthcheck_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuthcheck_ynWithStringAuthcheckYnShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String authcheck_yn = "authcheck_yn";
        boolean isNotException = true;
        try {
            clazz.setAuthcheck_yn(authcheck_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeveloper_nmShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getDeveloper_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeveloper_nmWithStringDeveloperNmShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String developer_nm = "developer_nm";
        boolean isNotException = true;
        try {
            clazz.setDeveloper_nm(developer_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getComments();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCommentsWithStringCommentsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String comments = "comments";
        boolean isNotException = true;
        try {
            clazz.setComments(comments);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
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
        PageMngVO clazz = new PageMngVO();
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
    public void testGetReg_dtShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
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
        PageMngVO clazz = new PageMngVO();
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
    public void testGetMod_idShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
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
        PageMngVO clazz = new PageMngVO();
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
    public void testGetMod_dtShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
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
        PageMngVO clazz = new PageMngVO();
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
    public void testGetSrch_page_nmShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_page_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_page_nmWithStringSrchPageNmShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String srch_page_nm = "srch_page_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_page_nm(srch_page_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_use_ynShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_use_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_use_ynWithStringSrchUseYnShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String srch_use_yn = "srch_use_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_use_yn(srch_use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChk_idShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getChk_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChk_idWithStringArrayChkIdShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] chk_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setChk_id(chk_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdsWithStringArraySysCdsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] sys_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setSys_cds(sys_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_idsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getPage_ids();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_idsWithStringArrayPageIdsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] page_ids = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setPage_ids(page_ids);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_nmsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getPage_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_nmsWithStringArrayPageNmsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] page_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setPage_nms(page_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_urlsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getPage_urls();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_urlsWithStringArrayPageUrlsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] page_urls = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setPage_urls(page_urls);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_ynsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getUse_yns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUse_ynsWithStringArrayUseYnsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] use_yns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setUse_yns(use_yns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuthcheck_ynsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getAuthcheck_yns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuthcheck_ynsWithStringArrayAuthcheckYnsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] authcheck_yns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAuthcheck_yns(authcheck_yns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeveloper_nmsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getDeveloper_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeveloper_nmsWithStringArrayDeveloperNmsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] developer_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDeveloper_nms(developer_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentssShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getCommentss();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCommentssWithStringArrayCommentssShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] commentss = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCommentss(commentss);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getReg_ids();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idsWithStringArrayRegIdsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] reg_ids = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setReg_ids(reg_ids);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtsWithStringArrayRegDtsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] reg_dts = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setReg_dts(reg_dts);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_idsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getMod_ids();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_idsWithStringArrayModIdsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] mod_ids = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMod_ids(mod_ids);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtsWithStringArrayModDtsShouldNotThrowException() {
        PageMngVO clazz = new PageMngVO();
        String[] mod_dts = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMod_dts(mod_dts);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
