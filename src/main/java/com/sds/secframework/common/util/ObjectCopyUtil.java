package com.sds.secframework.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ObjectCopyUtil {
	static final int PUBLIC = 1 ;
	static final int PROTECTED = 0 ;
	static final int PRIVATE = 2 ;
	
	/**
	 * VO를 Copy 
	 * @param srcVo 원본 VO
	 * @param dstVo 타겟 VO
	 * @throws Exception
	 */
	static public void copyValueObject(Object srcVo, Object dstVo) throws Exception{
		Field[] srcFieldArray = srcVo.getClass().getDeclaredFields() ;
		
		for(int i=0; i<srcFieldArray.length; i++) {
			String srcFieldName = srcFieldArray[i].getName() ;
			Object srcFieldValue = getFieldValue(srcVo, srcFieldName) ;
			Class  srcFieldType = srcFieldArray[i].getType() ;
			
			setFieldValue(dstVo, srcFieldArray[i], srcFieldValue, srcFieldType) ;
		}
	}
	
	/**
	 * Map을 VO에 저장
	 * @param srcMap 원본 Map
	 * @param dstVo 타겟 VO
	 * @throws Exception
	 */
	static public void copyMapToVo(Map srcMap, Object dstVo) throws Exception {
		Set keySet = srcMap.keySet() ;
		Iterator keyIterator =  keySet.iterator() ;
		while(keyIterator.hasNext()){
			Object key =keyIterator.next() ;
			Object value = srcMap.get(key) ;
			
			if(value!=null) {
				setFieldValue(dstVo, key, value, value.getClass()) ;
			}
		}
	}
	
	/**
	 * VO를 Map으로 저장
	 * @param srcVo
	 * @param dstMap
	 * @throws Exception
	 */
	static public void copyVoToMap(Object srcVo, Map dstMap) throws Exception {
		Field[] srcFieldArray = srcVo.getClass().getDeclaredFields() ;
		
		for(int i=0; i<srcFieldArray.length; i++) {
			String srcFieldName = srcFieldArray[i].getName() ;
			Object srcFieldValue = getFieldValue(srcVo, srcFieldName) ;
			dstMap.put(srcFieldName, srcFieldValue) ;
		}
	}
	
	/**
	 * HttpServletRequest  의 parameter 를 vo에 저장 
	 * @param request HttpServletRequest
	 * @param vo VO Object
	 * @throws Exception
	 */
	static public void copyRequestParameterToVo(HttpServletRequest request, Object vo) throws Exception {
		Enumeration paramNameEnum = request.getParameterNames() ;
		String paramName = null ;
		String[] paramValueArray = null ;
		
		while(paramNameEnum.hasMoreElements()) {
			paramName = (String)paramNameEnum.nextElement() ;
			paramValueArray = request.getParameterValues(paramName) ;
			setFieldValue(vo, paramName, paramValueArray, paramValueArray.getClass()) ;
		}
	}
	
	/**
	 * Object 에서 해당 필드의 값 가져오기
	 * 
	 * @param object VO Object
	 * @param fieldName 필드명
	 * @return
	 * @throws Exception
	 */
	static public Object getFieldValue(Object object, String fieldName) throws Exception {
		Object fieldValue = null ;
		
		try {
			Field field = object.getClass().getDeclaredField(fieldName) ;
			int srcFieldModifiers = field.getModifiers() ;
			
			// 변수에 직접 접근 불가능 할 때 GET 메소드 호출
			if(srcFieldModifiers!=PUBLIC){
				String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1) ;
				Method srcMethod = object.getClass().getDeclaredMethod(methodName, null) ;
				
				// 메소드에 직접 접근 가능한 경우 호출
				if(srcMethod.getModifiers()==PUBLIC) {
					fieldValue = srcMethod.invoke(object, null) ;
				}
			} 
			// 변수에 직접 가능할 경우
			else {
				fieldValue = field.get(object) ;
			}
		} catch (java.lang.NoSuchFieldException e) {

		} catch (java.lang.NoSuchMethodException e) {

		}
		
		return fieldValue ;
	}
	
	/**
	 * VO의 해당 필드에 값을 저장
	 * 
	 * @param vo 
	 * @param fieldNameObject 필드명
	 * @param fieldValue 저장할 값
	 * @param type 저장값의 type
	 * @throws Exception 
	 */
	static public void setFieldValue(Object vo, Object fieldNameObject, Object fieldValue, Class type) throws Exception {
		try {
			String fieldName = String.valueOf(fieldNameObject) ;
			Field field = vo.getClass().getDeclaredField(fieldName) ;
			setFieldValue(vo, field, fieldValue, type) ;
		} catch (NoSuchFieldException e) {
			//e.printStackTrace() ;
		}
	}
	
	/**
	 * VO의 해당 Field 에 값을 저장
	 * 
	 * @param vo 저장하고자 하는 VO
	 * @param srcField 저장하려고하는 Field
	 * @param fieldValue 저장하고자 하는 값
	 * @param type 저장하가조 하는 값의 Type
	 * @throws Exception
	 */
	static private  void setFieldValue(Object vo, Field srcField, Object fieldValue, Class type) throws Exception {
		try {
			String fieldName = srcField.getName() ;
			Field dstField = vo.getClass().getDeclaredField(fieldName) ;
			int dstFieldModifiers = dstField.getModifiers() ;
			
			// 변수에 직접 접근 불가능 할 때 SET 메소드 호출
			if(dstFieldModifiers!=PUBLIC){
				String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1) ;
				
				Class[] valueClass = {type} ;
				
				Method dstMethod = vo.getClass().getDeclaredMethod(methodName, valueClass) ;
				
				// 메소드에 직접 접근 가능할 때 메소드 호출
				if(dstMethod.getModifiers()==PUBLIC) {
					dstMethod.invoke(vo, new Object[]{fieldValue}) ;
				}
			}
			// 변수에 직접 접근 가능할 경우
			else {
				dstField.set(vo, fieldValue) ;
			}
			
		} catch (NoSuchFieldException e) {
			
		} catch (NoSuchMethodException e) {
			// 저장할 값이 숫자형일 경우에는 type을 바꿔서 다시 한번 저장 시도
			String t = type.getName() ;
			
			// 숫자형 객체인 경우
			if(t.equals("java.lang.Integer")) {
				type = Integer.TYPE ;
				setFieldValue(vo, srcField, fieldValue, type) ;
			} else if(t.equals("java.lang.Byte")) {
				type = Byte.TYPE ;
				setFieldValue(vo, srcField, fieldValue, type) ;
			} else if(t.equals("java.lang.Double")) {
				type = Double.TYPE ;
				setFieldValue(vo, srcField, fieldValue, type) ;
			} else if(t.equals("java.lang.Float")) {
				type = Float.TYPE ;
				setFieldValue(vo, srcField, fieldValue, type) ;
			} else if(t.equals("java.lang.Long")) {
				type = Long.TYPE ;
				setFieldValue(vo, srcField, fieldValue, type) ;
			} else if(t.equals("java.lang.Short")){
				type = Short.TYPE ;
				setFieldValue(vo, srcField, fieldValue, type) ;
			}
			// BigDecimal 인 경우
			else if(t.equals("java.math.BigDecimal")){
				t = srcField.getType().getName() ;
				
				if(t.equals("int")) {
					fieldValue = new Integer(((BigDecimal)fieldValue).intValue()) ;
					type = fieldValue.getClass() ;
				} else if(t.equals("byte")) {
					fieldValue = new Byte(((BigDecimal)fieldValue).byteValue()) ;
					type = fieldValue.getClass() ;
				} else if(t.equals("double")) {
					fieldValue = new Double(((BigDecimal)fieldValue).doubleValue()) ;
					type = fieldValue.getClass() ;
				} else if(t.equals("float")) {
					fieldValue = new Float(((BigDecimal)fieldValue).floatValue()) ;
					type = fieldValue.getClass() ;
				} else if(t.equals("long")) {
					fieldValue = new Long(((BigDecimal)fieldValue).longValue()) ;
					type = fieldValue.getClass() ;
				} else if(t.equals("short")) {
					fieldValue = new Short(((BigDecimal)fieldValue).shortValue()) ;
					type = fieldValue.getClass() ;
				}
				setFieldValue(vo, srcField, fieldValue, type) ;
			} 
			// 배열인 경우
			else if(t.startsWith("[")) {
				Object[] fieldValueArray =  (Object[])fieldValue ;
				if(fieldValueArray.length==1) {
					setFieldValue(vo, srcField, fieldValueArray[0], fieldValueArray[0].getClass()) ;
				}
			}
		}
	}
}
