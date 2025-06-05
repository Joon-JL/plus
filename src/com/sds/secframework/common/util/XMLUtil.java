package com.sds.secframework.common.util;

//import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

/**
 * JDom을 이용한  파서 예제
 * @author won
 *
 */
public class XMLUtil {

	Document doc;
	
	public XMLUtil() {}
    
    public XMLUtil(String rootElementName){
        
        /*
         * Create Root Element 
         */
        doc = new Document();        
        Element rootElement = new Element(rootElementName);
        doc.addContent(rootElement);
        
    }
    
    /**
     * 엘레멘트 하위 요소를 추가하여 준다.
     * @param target 추가하려는 엘레멘트 
     * @param eElement 신규 엘레멘트 이름
     * @param eValue 신규 엘레멘트 값
     * @return 신규 엘레멘트
     */
    public Element addInnerElement(Element target, String eElement, String eValue){
        Element newElement = new Element(eElement);
        newElement.setText(eValue);
        target.addContent(newElement);
        return newElement;
    }
    
    /**
     * <pre>
     * 엘레먼트 내부에 어트리뷰트를 추가하여 준다.
     * 추가시 입력된 순서대로 추가된다.
     * </pre>
     * @param target 추가하려는 엘레먼트
     * @param attName 어트리뷰트 이름
     * @param attValue 어트리뷰트 값
     */
    public void addInnerAttribute(Element target, String attName, String attValue){
        Attribute newElement = new Attribute(attName,attValue);
        target.setAttribute(newElement);        
    }
    
};
