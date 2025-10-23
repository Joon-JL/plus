package com.sds.secframework.user.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class UserSortingManagerVOTest {

    @Test(timeout = 20000)
    public void testGetSrch_blngt_orgnzShouldNotThrowException() {
        UserSortingManagerVO clazz = new UserSortingManagerVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_blngt_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_blngt_orgnzWithStringSrchBlngtOrgnzShouldNotThrowException() {
        UserSortingManagerVO clazz = new UserSortingManagerVO();
        String srch_blngt_orgnz = "srch_blngt_orgnz";
        boolean isNotException = true;
        try {
            clazz.setSrch_blngt_orgnz(srch_blngt_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_srtShouldNotThrowException() {
        UserSortingManagerVO clazz = new UserSortingManagerVO();
        boolean isNotException = true;
        try {
            clazz.getUser_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_srtWithIntUserSrtShouldNotThrowException() {
        UserSortingManagerVO clazz = new UserSortingManagerVO();
        int user_srt = 0;
        boolean isNotException = true;
        try {
            clazz.setUser_srt(user_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_id_arrShouldNotThrowException() {
        UserSortingManagerVO clazz = new UserSortingManagerVO();
        boolean isNotException = true;
        try {
            clazz.getUser_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_id_arrWithStringArrayUserIdArrShouldNotThrowException() {
        UserSortingManagerVO clazz = new UserSortingManagerVO();
        String[] user_id_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setUser_id_arr(user_id_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
