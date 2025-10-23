package com.sds.secframework.main.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class MainFormTest {

    @Test(timeout = 20000)
    public void testGetTarget_menu_idShouldNotThrowException() {
        MainForm clazz = new MainForm();
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
        MainForm clazz = new MainForm();
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
        MainForm clazz = new MainForm();
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
        MainForm clazz = new MainForm();
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
        MainForm clazz = new MainForm();
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
        MainForm clazz = new MainForm();
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
    public void testGetSelected_menu_nmShouldNotThrowException() {
        MainForm clazz = new MainForm();
        boolean isNotException = true;
        try {
            clazz.getSelected_menu_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelected_menu_nmWithStringSelectedMenuNmShouldNotThrowException() {
        MainForm clazz = new MainForm();
        String selected_menu_nm = "selected_menu_nm";
        boolean isNotException = true;
        try {
            clazz.setSelected_menu_nm(selected_menu_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReload_ynShouldNotThrowException() {
        MainForm clazz = new MainForm();
        boolean isNotException = true;
        try {
            clazz.getReload_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReload_ynWithStringReloadYnShouldNotThrowException() {
        MainForm clazz = new MainForm();
        String reload_yn = "reload_yn";
        boolean isNotException = true;
        try {
            clazz.setReload_yn(reload_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
