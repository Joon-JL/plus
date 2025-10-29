package com.sds.secframework.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.auth.dto.ClassMethodAuthVO;
import com.sds.secframework.auth.service.ClassMethodAuthService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;

public class ClassMethodAuthServiceImpl extends CommonServiceImpl implements ClassMethodAuthService {

	IIdGenerationService idGenerationService ;

	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	/**
	 * 클래스 메소드의 Role 리스트를 가져온다.
	 * @param vo ClassMethodAuthVO
	 * @return
	 * @throws Exception
	 */
	public List getClassMethodRoleList(ClassMethodAuthVO vo) throws Exception {
		if(vo.getSys_cd()==null) {
			vo.setSys_cd(propertyService.getProperty("secfw.sysCd")) ;
		}

		return commonDAO.list("shri.method.listAuth", vo) ;
	}
	
	/**
	 * 클래스 메소드의 Auth 리스트를 가져온다.
	 * @param vo ClassMethodAuthVO
	 * @return
	 * @throws Exception
	 */
	public List getClassMethodAuthList(ClassMethodAuthVO vo) throws Exception {
		//if(vo.getSys_cd()==null) {
		//	vo.setSys_cd(propertyService.getProperty("secfw.sysCd")) ;
		//}

		return commonDAO.list("clms.method.listMethodAuth", vo) ;
	}


	public int insert(ClassMethodAuthVO vo) throws Exception {
		int result = 0;
		String[] assigned_auth_list = vo.getAssigned_auth_list();
		Map map = null;

		map = detail(vo);

		//중복되는 데이터가 있으면
		if(map.size()>0){
			result = -100;
		}
		//중복되는 데이터가 없으면 insert
		else{
			result = commonDAO.insert("secfw.classmethodauth.insert", vo);
			//삽입 성공 시
			if(result == 1){
				// 설정한 권한은 CLASS_METHOD_ROLE 테이블에 추가
				if(assigned_auth_list != null){
					for(int i = 0 ; i < assigned_auth_list.length ; i++){
						vo.setAuth_cd(assigned_auth_list[i]);
						result = insertRole(vo);
					}
				}

				//CLASS_METHOD는 정상 삽입되었지만, 
				//CLASS_METHOD_ROLE 추가 중 에러가 발생하였다면
				if(result != 1){
					//삽입한 CLASS METHOD와 CLASS_METHOD_ROLE 데이터 모두 삭제
					delete(vo);
				}
			}
		}
		return result;
	}

	/**
	 * 권한 리스트를 SELECT해서 LIST로 가져온다
	 */
	public List listPage(ClassMethodAuthVO vo) throws Exception {
		return commonDAO.list("secfw.classmethodauth.listPage", vo) ;
	}

	/**
	 * 사용자가 클릭한 메소드에 대한 세부정보(권한)를 보여준다
	 */
	public Map detail(ClassMethodAuthVO vo) throws Exception {
		List list = commonDAO.list("secfw.classmethodauth.detail", vo) ;
		Map map = null ;
		if(list!=null && list.size()!=0){
			map = (Map)list.get(0) ;
		}
		else if(list.size()==0){
			map = new ListOrderedMap() ;
		}
		return map;
	}

	/**
	 * 사용자가 수정한 data를 UPDATE
	 */
	public int modify(ClassMethodAuthVO vo) throws Exception {
		int result = 0;
		String[] available_auth_list = vo.getAvailable_auth_list();
		String[] assigned_auth_list = vo.getAssigned_auth_list();

		result = commonDAO.modify("secfw.classmethodauth.modify", vo);
		//수정 성공 시
		if(result == 1){
			// 설정하지 않은 권한은 CLASS_METHOD_ROLE 테이블에서 삭제
			if(available_auth_list != null){
				for(int i = 0 ; i < available_auth_list.length ; i++){
					vo.setAuth_cd(available_auth_list[i]);
					deleteRole(vo);
				}
			}
			// 설정한 권한은 CLASS_METHOD_ROLE 테이블에 추가(존재시 업데이트)
			if(assigned_auth_list != null){
				for(int i = 0 ; i < assigned_auth_list.length ; i++){
					vo.setAuth_cd(assigned_auth_list[i]);
					insertRole(vo);
				}
			}
		}
		//수정 실패시 관련된 CLASS_METHOD_ROLE 데이터 모두 삭제 
		else {
			commonDAO.delete("secfw.classmethodauth.deleteRole", vo);
		}

		return result;
	}

	/**
	 * 체크박스로 선택된 데이터를 삭제한다
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteMulti(ClassMethodAuthVO vo) throws Exception {
		int result = 0;

		result = delete(vo);

		//delete가 제대로 되지 않았으면
		if(result != 1)
			throw new Exception();
		return result;
	}


	/**
	 * TB_SHRI_CLASS_METHOD 데이터 삭제(관련된 권한도 삭제)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int delete(ClassMethodAuthVO vo) throws Exception {
		int result = 0;
		result = commonDAO.delete("secfw.classmethodauth.delete",vo);
		commonDAO.delete("secfw.classmethodauth.deleteRole", vo);

		return result;
	}

	/**
	 * TB_SHRI_CLASS_METHOD_ROLE 데이터 삭제
	 */
	public int deleteRole(ClassMethodAuthVO vo) throws Exception {
		int result = 0;
		result = commonDAO.delete("secfw.classmethodauth.deleteRole2", vo);
		return result;
	}

	/**
	 * TB_SHRI_CLASS_METHOD_ROLE 데이터 삽입
	 */
	public int insertRole(ClassMethodAuthVO vo) throws Exception {
		int result = 0;
		result = commonDAO.delete("secfw.classmethodauth.insertRole", vo);
		return result;
	}

	public List listRole(ClassMethodAuthVO vo) throws Exception {
		return commonDAO.list("secfw.classmethodauth.listAuth", vo) ;
	}

	public List listNotRole(ClassMethodAuthVO vo) throws Exception {
		return commonDAO.list("secfw.classmethodauth.listNotAuth", vo);
	}
	
	/** 
	 * 메서드 권한관리 부분에 대해 log 저장
	 */
	public int insertLogData(HashMap hm) throws Exception {
		int result = commonDAO.insert("secfw.classmethodauth.insertLog",hm);
		return result;
	}
}
