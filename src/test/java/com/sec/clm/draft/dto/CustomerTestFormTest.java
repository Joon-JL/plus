package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class CustomerTestFormTest {

    @Test(timeout = 20000)
    public void testGetVatnoShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getVatno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetVatnoWithStringVatnoShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String vatno = "vatno";
        boolean isNotException = true;
        try {
            clazz.setVatno(vatno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_gerpShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_gerp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_gerpWithStringSrchGerpShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String srch_gerp = "srch_gerp";
        boolean isNotException = true;
        try {
            clazz.setSrch_gerp(srch_gerp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsModifyShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getIsModify();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsModifyWithStringIsModifyShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String isModify = "isModify";
        boolean isNotException = true;
        try {
            clazz.setIsModify(isModify);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHp_noShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getHp_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHp_noWithStringHpNoShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String hp_no = "hp_no";
        boolean isNotException = true;
        try {
            clazz.setHp_no(hp_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCongbnShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCongbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCongbnWithStringCongbnShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String congbn = "congbn";
        boolean isNotException = true;
        try {
            clazz.setCongbn(congbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNation2ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getNation2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNation2WithStringNation2ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String nation2 = "nation2";
        boolean isNotException = true;
        try {
            clazz.setNation2(nation2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_nationShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_nation();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_nationWithStringSrchNationShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String srch_nation = "srch_nation";
        boolean isNotException = true;
        try {
            clazz.setSrch_nation(srch_nation);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_taxNoShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_taxNo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_taxNoWithStringSrchTaxNoShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String srch_taxNo = "srch_taxNo";
        boolean isNotException = true;
        try {
            clazz.setSrch_taxNo(srch_taxNo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetVatnosShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getVatnos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetVatnosWithStringArrayVatnosShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] vatnos = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setVatnos(vatnos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGerp_cdShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getGerp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGerp_cdWithStringGerpCdShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String gerp_cd = "gerp_cd";
        boolean isNotException = true;
        try {
            clazz.setGerp_cd(gerp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetVendor_typeShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getVendor_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetVendor_typeWithStringVendorTypeShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String vendor_type = "vendor_type";
        boolean isNotException = true;
        try {
            clazz.setVendor_type(vendor_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGerp_cdsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getGerp_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGerp_cdsWithStringArrayGerpCdsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] gerp_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setGerp_cds(gerp_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetVendor_typesShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getVendor_types();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetVendor_typesWithStringArrayVendorTypesShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] vendor_types = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setVendor_types(vendor_types);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHp_nosShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getHp_nos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHp_nosWithStringArrayHpNosShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] hp_nos = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setHp_nos(hp_nos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getEmail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmailWithStringEmailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String email = "email";
        boolean isNotException = true;
        try {
            clazz.setEmail(email);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmailsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getEmails();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmailsWithStringArrayEmailsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] emails = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setEmails(emails);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_customerShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_customer();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_customerWithStringSrchCustomerShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String srch_customer = "srch_customer";
        boolean isNotException = true;
        try {
            clazz.setSrch_customer(srch_customer);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_gbShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_gb();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_gbWithStringCustomerGbShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String customer_gb = "customer_gb";
        boolean isNotException = true;
        try {
            clazz.setCustomer_gb(customer_gb);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSelectCntShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSelectCnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelectCntWithStringSelectCntShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String selectCnt = "selectCnt";
        boolean isNotException = true;
        try {
            clazz.setSelectCnt(selectCnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSelectRowShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSelectRow();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelectRowWithStringSelectRowShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String selectRow = "selectRow";
        boolean isNotException = true;
        try {
            clazz.setSelectRow(selectRow);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSpanIdxShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSpanIdx();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSpanIdxWithStringArraySpanIdxShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] spanIdx = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setSpanIdx(spanIdx);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_cdShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_cdWithStringCustomerCdShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String customer_cd = "customer_cd";
        boolean isNotException = true;
        try {
            clazz.setCustomer_cd(customer_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_nm1ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_nm1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_nm1WithStringCustomerNm1ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String customer_nm1 = "customer_nm1";
        boolean isNotException = true;
        try {
            clazz.setCustomer_nm1(customer_nm1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIv_nm1ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getIv_nm1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIv_nm1WithStringIvNm1ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String iv_nm1 = "iv_nm1";
        boolean isNotException = true;
        try {
            clazz.setIv_nm1(iv_nm1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGudunShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getGudun();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGudunWithStringGudunShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String gudun = "gudun";
        boolean isNotException = true;
        try {
            clazz.setGudun(gudun);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGunamShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getGunam();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGunamWithStringGunamShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String gunam = "gunam";
        boolean isNotException = true;
        try {
            clazz.setGunam(gunam);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDodunShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getDodun();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDodunWithStringDodunShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String dodun = "dodun";
        boolean isNotException = true;
        try {
            clazz.setDodun(dodun);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDonamShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getDonam();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDonamWithStringDonamShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String donam = "donam";
        boolean isNotException = true;
        try {
            clazz.setDonam(donam);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHqdunShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getHqdun();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHqdunWithStringHqdunShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String hqdun = "hqdun";
        boolean isNotException = true;
        try {
            clazz.setHqdun(hqdun);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHqnamShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getHqnam();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHqnamWithStringHqnamShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String hqnam = "hqnam";
        boolean isNotException = true;
        try {
            clazz.setHqnam(hqnam);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_sortShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_sort();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_sortWithStringCustomerSortShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String customer_sort = "customer_sort";
        boolean isNotException = true;
        try {
            clazz.setCustomer_sort(customer_sort);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSec_cdShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSec_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSec_cdWithStringSecCdShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String sec_cd = "sec_cd";
        boolean isNotException = true;
        try {
            clazz.setSec_cd(sec_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTax_noShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getTax_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTax_noWithStringTaxNoShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String tax_no = "tax_no";
        boolean isNotException = true;
        try {
            clazz.setTax_no(tax_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetErdatShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getErdat();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetErdatWithStringErdatShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String erdat = "erdat";
        boolean isNotException = true;
        try {
            clazz.setErdat(erdat);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStreetShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getStreet();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStreetWithStringStreetShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String street = "street";
        boolean isNotException = true;
        try {
            clazz.setStreet(street);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCitynShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCityn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCitynWithStringCitynShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String cityn = "cityn";
        boolean isNotException = true;
        try {
            clazz.setCityn(cityn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStaprShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getStapr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStaprWithStringStaprShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String stapr = "stapr";
        boolean isNotException = true;
        try {
            clazz.setStapr(stapr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNationShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getNation();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNationWithStringNationShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String nation = "nation";
        boolean isNotException = true;
        try {
            clazz.setNation(nation);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNation_nmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getNation_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNation_nmWithStringNationNmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String nation_nm = "nation_nm";
        boolean isNotException = true;
        try {
            clazz.setNation_nm(nation_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPostalcodeShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getPostalcode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPostalcodeWithStringPostalcodeShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String postalcode = "postalcode";
        boolean isNotException = true;
        try {
            clazz.setPostalcode(postalcode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTelephone1ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getTelephone1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTelephone1WithStringTelephone1ShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String telephone1 = "telephone1";
        boolean isNotException = true;
        try {
            clazz.setTelephone1(telephone1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRep_nmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getRep_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRep_nmWithStringRepNmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String rep_nm = "rep_nm";
        boolean isNotException = true;
        try {
            clazz.setRep_nm(rep_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLinbuShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLinbu();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLinbuWithStringLinbuShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String linbu = "linbu";
        boolean isNotException = true;
        try {
            clazz.setLinbu(linbu);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLegstShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLegst();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLegstWithStringLegstShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String legst = "legst";
        boolean isNotException = true;
        try {
            clazz.setLegst(legst);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLegst_nmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLegst_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLegst_nmWithStringLegstNmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String legst_nm = "legst_nm";
        boolean isNotException = true;
        try {
            clazz.setLegst_nm(legst_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusopenShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusopen();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusopenWithStringBusopenShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String busopen = "busopen";
        boolean isNotException = true;
        try {
            clazz.setBusopen(busopen);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusopen_nmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusopen_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusopen_nmWithStringBusopenNmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String busopen_nm = "busopen_nm";
        boolean isNotException = true;
        try {
            clazz.setBusopen_nm(busopen_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_gubnShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_gubn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_gubnWithStringBusinessGubnShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String business_gubn = "business_gubn";
        boolean isNotException = true;
        try {
            clazz.setBusiness_gubn(business_gubn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_gubn_nmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_gubn_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_gubn_nmWithStringBusinessGubnNmShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String business_gubn_nm = "business_gubn_nm";
        boolean isNotException = true;
        try {
            clazz.setBusiness_gubn_nm(business_gubn_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCeonm_engShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCeonm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCeonm_engWithStringCeonmEngShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String ceonm_eng = "ceonm_eng";
        boolean isNotException = true;
        try {
            clazz.setCeonm_eng(ceonm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAdd_engShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getAdd_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAdd_engWithStringAddEngShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String add_eng = "add_eng";
        boolean isNotException = true;
        try {
            clazz.setAdd_eng(add_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
        CustomerTestForm clazz = new CustomerTestForm();
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
    public void testGetCustomer_cdsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_cdsWithStringArrayCustomerCdsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] customer_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCustomer_cds(customer_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_nm1sShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_nm1s();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_nm1sWithStringArrayCustomerNm1sShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] customer_nm1s = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCustomer_nm1s(customer_nm1s);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIv_nm1sShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getIv_nm1s();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIv_nm1sWithStringArrayIvNm1sShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] iv_nm1s = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setIv_nm1s(iv_nm1s);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGudunsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getGuduns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGudunsWithStringArrayGudunsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] guduns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setGuduns(guduns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGunamsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getGunams();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGunamsWithStringArrayGunamsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] gunams = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setGunams(gunams);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDodunsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getDoduns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDodunsWithStringArrayDodunsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] doduns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDoduns(doduns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDonamsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getDonams();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDonamsWithStringArrayDonamsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] donams = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDonams(donams);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHqdunsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getHqduns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHqdunsWithStringArrayHqdunsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] hqduns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setHqduns(hqduns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHqnamsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getHqnams();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHqnamsWithStringArrayHqnamsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] hqnams = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setHqnams(hqnams);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_sortsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_sorts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_sortsWithStringArrayCustomerSortsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] customer_sorts = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCustomer_sorts(customer_sorts);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSec_cdsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getSec_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSec_cdsWithStringArraySecCdsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] sec_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setSec_cds(sec_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTax_nosShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getTax_nos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTax_nosWithStringArrayTaxNosShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] tax_nos = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setTax_nos(tax_nos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetErdatsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getErdats();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetErdatsWithStringArrayErdatsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] erdats = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setErdats(erdats);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStreetsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getStreets();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStreetsWithStringArrayStreetsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] streets = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setStreets(streets);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCitynsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCityns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCitynsWithStringArrayCitynsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] cityns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCityns(cityns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStaprsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getStaprs();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStaprsWithStringArrayStaprsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] staprs = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setStaprs(staprs);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNationsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getNations();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNationsWithStringArrayNationsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] nations = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setNations(nations);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNation_nmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getNation_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNation_nmsWithStringArrayNationNmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] nation_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setNation_nms(nation_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPostalcodesShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getPostalcodes();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPostalcodesWithStringArrayPostalcodesShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] postalcodes = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setPostalcodes(postalcodes);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTelephone1sShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getTelephone1s();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTelephone1sWithStringArrayTelephone1sShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] telephone1s = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setTelephone1s(telephone1s);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRep_nmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getRep_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRep_nmsWithStringArrayRepNmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] rep_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setRep_nms(rep_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLinbusShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLinbus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLinbusWithStringArrayLinbusShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] linbus = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setLinbus(linbus);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLegstsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLegsts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLegstsWithStringArrayLegstsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] legsts = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setLegsts(legsts);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLegst_nmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLegst_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLegst_nmsWithStringArrayLegstNmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] legst_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setLegst_nms(legst_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusopensShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusopens();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusopensWithStringArrayBusopensShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] busopens = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setBusopens(busopens);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusopen_nmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusopen_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusopen_nmsWithStringArrayBusopenNmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] busopen_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setBusopen_nms(busopen_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_gubnsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_gubns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_gubnsWithStringArrayBusinessGubnsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] business_gubns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setBusiness_gubns(business_gubns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_gubn_nmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_gubn_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_gubn_nmsWithStringArrayBusinessGubnNmsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] business_gubn_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setBusiness_gubn_nms(business_gubn_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCeonm_engsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCeonm_engs();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCeonm_engsWithStringArrayCeonmEngsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] ceonm_engs = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCeonm_engs(ceonm_engs);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAdd_engsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getAdd_engs();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAdd_engsWithStringArrayAddEngsShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String[] add_engs = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAdd_engs(add_engs);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetVatno_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getVatno_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetVatno_detailWithStringVatnoDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String vatno_detail = "vatno_detail";
        boolean isNotException = true;
        try {
            clazz.setVatno_detail(vatno_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGerp_cd_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getGerp_cd_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGerp_cd_detailWithStringGerpCdDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String gerp_cd_detail = "gerp_cd_detail";
        boolean isNotException = true;
        try {
            clazz.setGerp_cd_detail(gerp_cd_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetVendor_type_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getVendor_type_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetVendor_type_detailWithStringVendorTypeDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String vendor_type_detail = "vendor_type_detail";
        boolean isNotException = true;
        try {
            clazz.setVendor_type_detail(vendor_type_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_nm1_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_nm1_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_nm1_detailWithStringCustomerNm1DetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String customer_nm1_detail = "customer_nm1_detail";
        boolean isNotException = true;
        try {
            clazz.setCustomer_nm1_detail(customer_nm1_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLinbu_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLinbu_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLinbu_detailWithStringLinbuDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String linbu_detail = "linbu_detail";
        boolean isNotException = true;
        try {
            clazz.setLinbu_detail(linbu_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRep_nm_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getRep_nm_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRep_nm_detailWithStringRepNmDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String rep_nm_detail = "rep_nm_detail";
        boolean isNotException = true;
        try {
            clazz.setRep_nm_detail(rep_nm_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTax_no_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getTax_no_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTax_no_detailWithStringTaxNoDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String tax_no_detail = "tax_no_detail";
        boolean isNotException = true;
        try {
            clazz.setTax_no_detail(tax_no_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNation_nm_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getNation_nm_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNation_nm_detailWithStringNationNmDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String nation_nm_detail = "nation_nm_detail";
        boolean isNotException = true;
        try {
            clazz.setNation_nm_detail(nation_nm_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStapr_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getStapr_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStapr_detailWithStringStaprDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String stapr_detail = "stapr_detail";
        boolean isNotException = true;
        try {
            clazz.setStapr_detail(stapr_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCityn_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCityn_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCityn_detailWithStringCitynDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String cityn_detail = "cityn_detail";
        boolean isNotException = true;
        try {
            clazz.setCityn_detail(cityn_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStreet_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getStreet_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStreet_detailWithStringStreetDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String street_detail = "street_detail";
        boolean isNotException = true;
        try {
            clazz.setStreet_detail(street_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPostalcode_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getPostalcode_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPostalcode_detailWithStringPostalcodeDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String postalcode_detail = "postalcode_detail";
        boolean isNotException = true;
        try {
            clazz.setPostalcode_detail(postalcode_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTelephone1_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getTelephone1_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTelephone1_detailWithStringTelephone1DetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String telephone1_detail = "telephone1_detail";
        boolean isNotException = true;
        try {
            clazz.setTelephone1_detail(telephone1_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHp_no_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getHp_no_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHp_no_detailWithStringHpNoDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String hp_no_detail = "hp_no_detail";
        boolean isNotException = true;
        try {
            clazz.setHp_no_detail(hp_no_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmail_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getEmail_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmail_detailWithStringEmailDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String email_detail = "email_detail";
        boolean isNotException = true;
        try {
            clazz.setEmail_detail(email_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNation_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getNation_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNation_detailWithStringNationDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String nation_detail = "nation_detail";
        boolean isNotException = true;
        try {
            clazz.setNation_detail(nation_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_cd_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_cd_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_cd_detailWithStringCustomerCdDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String customer_cd_detail = "customer_cd_detail";
        boolean isNotException = true;
        try {
            clazz.setCustomer_cd_detail(customer_cd_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReturn_messageShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getReturn_message();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturn_messageWithStringReturnMessageShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String return_message = "return_message";
        boolean isNotException = true;
        try {
            clazz.setReturn_message(return_message);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLegst_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getLegst_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLegst_detailWithStringLegstDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String legst_detail = "legst_detail";
        boolean isNotException = true;
        try {
            clazz.setLegst_detail(legst_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusopen_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusopen_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusopen_detailWithStringBusopenDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String busopen_detail = "busopen_detail";
        boolean isNotException = true;
        try {
            clazz.setBusopen_detail(busopen_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_gubn_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_gubn_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_gubn_detailWithStringBusinessGubnDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String business_gubn_detail = "business_gubn_detail";
        boolean isNotException = true;
        try {
            clazz.setBusiness_gubn_detail(business_gubn_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_gbnShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getPage_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_gbnWithStringPageGbnShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String page_gbn = "page_gbn";
        boolean isNotException = true;
        try {
            clazz.setPage_gbn(page_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_top30_cusShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_top30_cus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_top30_cusWithStringCntrtTop30CusShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String cntrt_top30_cus = "cntrt_top30_cus";
        boolean isNotException = true;
        try {
            clazz.setCntrt_top30_cus(cntrt_top30_cus);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_top30_cus_detailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_top30_cus_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_top30_cus_detailWithStringCntrtTop30CusDetailShouldNotThrowException() {
        CustomerTestForm clazz = new CustomerTestForm();
        String cntrt_top30_cus_detail = "cntrt_top30_cus_detail";
        boolean isNotException = true;
        try {
            clazz.setCntrt_top30_cus_detail(cntrt_top30_cus_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
