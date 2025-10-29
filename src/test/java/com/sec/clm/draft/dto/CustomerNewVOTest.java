package com.sec.clm.draft.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class CustomerNewVOTest {

    @Test(timeout = 20000)
    public void testGetVatnoShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
    public void testGetGerp_cdShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
    public void testGetHp_inputShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        boolean isNotException = true;
        try {
            clazz.getHp_input();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHp_inputWithStringHpInputShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        String hp_input = "hp_input";
        boolean isNotException = true;
        try {
            clazz.setHp_input(hp_input);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_nationShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
    public void testGetCheck_YNShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        boolean isNotException = true;
        try {
            clazz.getCheck_YN();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCheck_YNWithStringCheckYNShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        String check_YN = "check_YN";
        boolean isNotException = true;
        try {
            clazz.setCheck_YN(check_YN);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCheck_texNoShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        boolean isNotException = true;
        try {
            clazz.getCheck_texNo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCheck_texNoWithStringCheckTexNoShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        String check_texNo = "check_texNo";
        boolean isNotException = true;
        try {
            clazz.setCheck_texNo(check_texNo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCheck_texNmShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        boolean isNotException = true;
        try {
            clazz.getCheck_texNm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCheck_texNmWithStringCheckTexNmShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        String check_texNm = "check_texNm";
        boolean isNotException = true;
        try {
            clazz.setCheck_texNm(check_texNm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmailShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
    public void testGetSrch_customerShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
    public void testGetNationRdShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        boolean isNotException = true;
        try {
            clazz.getNationRd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNationRdWithStringNationRdShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
        String nationRd = "nationRd";
        boolean isNotException = true;
        try {
            clazz.setNationRd(nationRd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_cdShouldNotThrowException() {
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
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
        CustomerNewVO clazz = new CustomerNewVO();
        String del_nm = "del_nm";
        boolean isNotException = true;
        try {
            clazz.setDel_nm(del_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
