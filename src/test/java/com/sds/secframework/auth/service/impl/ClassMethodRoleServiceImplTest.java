package com.sds.secframework.auth.service.impl;

import com.sds.secframework.auth.dto.ClassMethodRoleVO;
import com.sds.secframework.common.dao.CommonDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClassMethodRoleServiceImplTest {

    @Mock
    private CommonDAO commonDAO;

    private ClassMethodRoleServiceImpl service;

    @Before
    public void setUp() {
        service = new ClassMethodRoleServiceImpl();
        service.setCommonDAO(commonDAO);
    }

    @Test
    public void testGetClassMethodRoleList_Success() throws Exception {
        ClassMethodRoleVO vo = new ClassMethodRoleVO();
        List mockList = new ArrayList();
        when(commonDAO.list("shri.method.listRole", vo)).thenReturn(mockList);

        List result = service.getClassMethodRoleList(vo);

        assertNotNull(result);
        verify(commonDAO).list("shri.method.listRole", vo);
    }

    @Test
    public void testInsert_Success() throws Exception {
        ClassMethodRoleVO vo = new ClassMethodRoleVO();
        String[] assigned_role_list = {"ROLE001"};
        vo.setAssigned_role_list(assigned_role_list);

        when(commonDAO.list("secfw.classmethodrole.detail", vo)).thenReturn(new ArrayList());
        when(commonDAO.insert("secfw.classmethodrole.insert", vo)).thenReturn(1);
        when(commonDAO.delete("secfw.classmethodrole.insertRole", vo)).thenReturn(1);

        int result = service.insert(vo);

        assertEquals(1, result);
        verify(commonDAO).insert("secfw.classmethodrole.insert", vo);
        verify(commonDAO).delete("secfw.classmethodrole.insertRole", vo);
    }

    @Test
    public void testDetail_Success() throws Exception {
        ClassMethodRoleVO vo = new ClassMethodRoleVO();
        List mockList = new ArrayList();
        Map mockMap = new LinkedHashMap<>();
        mockList.add(mockMap);
        when(commonDAO.list("secfw.classmethodrole.detail", vo)).thenReturn(mockList);

        List result = Collections.singletonList(service.detail(vo));

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(commonDAO).list("secfw.classmethodrole.detail", vo);
    }
}
