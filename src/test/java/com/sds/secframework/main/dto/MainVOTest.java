package com.sds.secframework.main.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class MainVOTest {

    @Test(timeout = 20000)
    public void testGetTarget_menu_idShouldNotThrowException() {
        MainVO clazz = new MainVO();
        boolean isNotException = true;
        try {
            clazz.getTarget_menu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTarget_menu_idWithStringTargetMenuIdShouldNotThrowException() {
        MainVO clazz = new MainVO();
        String target_menu_id = "target_menu_id";
        boolean isNotException = true;
        try {
            clazz.setTarget_menu_id(target_menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_gbnShouldNotThrowException() {
        MainVO clazz = new MainVO();
        boolean isNotException = true;
        try {
            clazz.getMenu_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_gbnWithStringMenuGbnShouldNotThrowException() {
        MainVO clazz = new MainVO();
        String menu_gbn = "menu_gbn";
        boolean isNotException = true;
        try {
            clazz.setMenu_gbn(menu_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSelected_menu_idShouldNotThrowException() {
        MainVO clazz = new MainVO();
        boolean isNotException = true;
        try {
            clazz.getSelected_menu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelected_menu_idWithStringSelectedMenuIdShouldNotThrowException() {
        MainVO clazz = new MainVO();
        String selected_menu_id = "selected_menu_id";
        boolean isNotException = true;
        try {
            clazz.setSelected_menu_id(selected_menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelected_menu_nmWithStringSelectedMenuNmShouldNotThrowException() {
        MainVO clazz = new MainVO();
        String selected_menu_nm = "selected_menu_nm";
        boolean isNotException = true;
        try {
            clazz.setSelected_menu_nm(selected_menu_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
