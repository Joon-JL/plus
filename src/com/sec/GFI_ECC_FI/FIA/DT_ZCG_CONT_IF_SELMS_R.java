/**
 * DT_ZCG_CONT_IF_SELMS_R.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sec.GFI_ECC_FI.FIA;

public class DT_ZCG_CONT_IF_SELMS_R  implements java.io.Serializable {
    private com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS_ROUTTAB[] OUTTAB;

    private java.lang.String r_MESSAGE;

    public DT_ZCG_CONT_IF_SELMS_R() {
    }

    public DT_ZCG_CONT_IF_SELMS_R(
           com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS_ROUTTAB[] OUTTAB,
           java.lang.String r_MESSAGE) {
           this.OUTTAB = OUTTAB;
           this.r_MESSAGE = r_MESSAGE;
    }


    /**
     * Gets the OUTTAB value for this DT_ZCG_CONT_IF_SELMS_R.
     * 
     * @return OUTTAB
     */
    public com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS_ROUTTAB[] getOUTTAB() {
        return OUTTAB;
    }


    /**
     * Sets the OUTTAB value for this DT_ZCG_CONT_IF_SELMS_R.
     * 
     * @param OUTTAB
     */
    public void setOUTTAB(com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS_ROUTTAB[] OUTTAB) {
        this.OUTTAB = OUTTAB;
    }

    public com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS_ROUTTAB getOUTTAB(int i) {
        return this.OUTTAB[i];
    }

    public void setOUTTAB(int i, com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS_ROUTTAB _value) {
        this.OUTTAB[i] = _value;
    }


    /**
     * Gets the r_MESSAGE value for this DT_ZCG_CONT_IF_SELMS_R.
     * 
     * @return r_MESSAGE
     */
    public java.lang.String getR_MESSAGE() {
        return r_MESSAGE;
    }


    /**
     * Sets the r_MESSAGE value for this DT_ZCG_CONT_IF_SELMS_R.
     * 
     * @param r_MESSAGE
     */
    public void setR_MESSAGE(java.lang.String r_MESSAGE) {
        this.r_MESSAGE = r_MESSAGE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ZCG_CONT_IF_SELMS_R)) return false;
        DT_ZCG_CONT_IF_SELMS_R other = (DT_ZCG_CONT_IF_SELMS_R) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.OUTTAB==null && other.getOUTTAB()==null) || 
             (this.OUTTAB!=null &&
              java.util.Arrays.equals(this.OUTTAB, other.getOUTTAB()))) &&
            ((this.r_MESSAGE==null && other.getR_MESSAGE()==null) || 
             (this.r_MESSAGE!=null &&
              this.r_MESSAGE.equals(other.getR_MESSAGE())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getOUTTAB() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOUTTAB());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOUTTAB(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getR_MESSAGE() != null) {
            _hashCode += getR_MESSAGE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_ZCG_CONT_IF_SELMS_R.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sec.com/GFI_ECC_FI/FIA", "DT_ZCG_CONT_IF_SELMS_R"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OUTTAB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OUTTAB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sec.com/GFI_ECC_FI/FIA", ">DT_ZCG_CONT_IF_SELMS_R>OUTTAB"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r_MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "R_MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
