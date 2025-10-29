package com.sds.secframework.menu.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class MenuFormTest {

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_idWithStringMenuIdShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        String menu_id = "menu_id";
        boolean isNotException = true;
        try {
            clazz.setMenu_id(menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_menu_idShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getUp_menu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_menu_idWithStringUpMenuIdShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        String up_menu_id = "up_menu_id";
        boolean isNotException = true;
        try {
            clazz.setUp_menu_id(up_menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_nmShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_nmWithStringMenuNmShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        String menu_nm = "menu_nm";
        boolean isNotException = true;
        try {
            clazz.setMenu_nm(menu_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_levelShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_levelWithIntMenuLevelShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        int menu_level = 0;
        boolean isNotException = true;
        try {
            clazz.setMenu_level(menu_level);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_orderShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_order();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_orderWithIntMenuOrderShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        int menu_order = 0;
        boolean isNotException = true;
        try {
            clazz.setMenu_order(menu_order);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_urlShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_url();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_urlWithStringMenuUrlShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        String menu_url = "menu_url";
        boolean isNotException = true;
        try {
            clazz.setMenu_url(menu_url);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_imgShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_img();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_imgWithStringMenuImgShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        String menu_img = "menu_img";
        boolean isNotException = true;
        try {
            clazz.setMenu_img(menu_img);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSelect_menu_idShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getSelect_menu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelect_menu_idWithStringSelectMenuIdShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        String select_menu_id = "select_menu_id";
        boolean isNotException = true;
        try {
            clazz.setSelect_menu_id(select_menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetResult_listShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.getResult_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetResult_listWithListResultListShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        List result_list = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setResult_list(result_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsHas_sub_menuShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean isNotException = true;
        try {
            clazz.isHas_sub_menu();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHas_sub_menuWithBooleanHasSubMenuShouldNotThrowException() {
        MenuForm clazz = new MenuForm();
        boolean has_sub_menu = false;
        boolean isNotException = true;
        try {
            clazz.setHas_sub_menu(has_sub_menu);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
