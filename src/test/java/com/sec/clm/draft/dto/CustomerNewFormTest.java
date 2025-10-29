package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class CustomerNewFormTest {

    @Test(timeout = 20000)
    public void testGetVatnoShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
    public void testGetLinbu_HShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
        boolean isNotException = true;
        try {
            clazz.getLinbu_H();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLinbu_HWithStringLinbuHShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
        String linbu_H = "linbu_H";
        boolean isNotException = true;
        try {
            clazz.setLinbu_H(linbu_H);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCustomer_nm1_HShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
        boolean isNotException = true;
        try {
            clazz.getCustomer_nm1_H();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCustomer_nm1_HWithStringCustomerNm1HShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
        String customer_nm1_H = "customer_nm1_H";
        boolean isNotException = true;
        try {
            clazz.setCustomer_nm1_H(customer_nm1_H);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHp_inputShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
    public void testGetVatno_detailShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
    public void testGetIsModifyShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
    public void testGetCustomer_nm1_detailShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
    public void testGetLegst_detailShouldNotThrowException() {
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
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
        CustomerNewForm clazz = new CustomerNewForm();
        String business_gubn_detail = "business_gubn_detail";
        boolean isNotException = true;
        try {
            clazz.setBusiness_gubn_detail(business_gubn_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
