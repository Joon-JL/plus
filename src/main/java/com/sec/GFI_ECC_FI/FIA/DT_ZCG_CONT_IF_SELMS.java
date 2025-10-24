/**
 * DT_ZCG_CONT_IF_SELMS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sec.GFI_ECC_FI.FIA;

public class DT_ZCG_CONT_IF_SELMS  implements java.io.Serializable {
    private com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMSINTAB[] INTAB;

    private java.lang.String i_AFDATE;

    private java.lang.String i_ATDATE;

    private java.lang.String i_BUKRS;

    private java.lang.String i_GSBER;

    private java.lang.String i_GUBUN;

    public DT_ZCG_CONT_IF_SELMS() {
    }

    public DT_ZCG_CONT_IF_SELMS(
           com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMSINTAB[] INTAB,
           java.lang.String i_AFDATE,
           java.lang.String i_ATDATE,
           java.lang.String i_BUKRS,
           java.lang.String i_GSBER,
           java.lang.String i_GUBUN) {
           this.INTAB = INTAB;
           this.i_AFDATE = i_AFDATE;
           this.i_ATDATE = i_ATDATE;
           this.i_BUKRS = i_BUKRS;
           this.i_GSBER = i_GSBER;
           this.i_GUBUN = i_GUBUN;
    }


    /**
     * Gets the INTAB value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @return INTAB
     */
    public com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMSINTAB[] getINTAB() {
        return INTAB;
    }


    /**
     * Sets the INTAB value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @param INTAB
     */
    public void setINTAB(com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMSINTAB[] INTAB) {
        this.INTAB = INTAB;
    }

    public com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMSINTAB getINTAB(int i) {
        return this.INTAB[i];
    }

    public void setINTAB(int i, com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMSINTAB _value) {
        this.INTAB[i] = _value;
    }


    /**
     * Gets the i_AFDATE value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @return i_AFDATE
     */
    public java.lang.String getI_AFDATE() {
        return i_AFDATE;
    }


    /**
     * Sets the i_AFDATE value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @param i_AFDATE
     */
    public void setI_AFDATE(java.lang.String i_AFDATE) {
        this.i_AFDATE = i_AFDATE;
    }


    /**
     * Gets the i_ATDATE value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @return i_ATDATE
     */
    public java.lang.String getI_ATDATE() {
        return i_ATDATE;
    }


    /**
     * Sets the i_ATDATE value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @param i_ATDATE
     */
    public void setI_ATDATE(java.lang.String i_ATDATE) {
        this.i_ATDATE = i_ATDATE;
    }


    /**
     * Gets the i_BUKRS value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @return i_BUKRS
     */
    public java.lang.String getI_BUKRS() {
        return i_BUKRS;
    }


    /**
     * Sets the i_BUKRS value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @param i_BUKRS
     */
    public void setI_BUKRS(java.lang.String i_BUKRS) {
        this.i_BUKRS = i_BUKRS;
    }


    /**
     * Gets the i_GSBER value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @return i_GSBER
     */
    public java.lang.String getI_GSBER() {
        return i_GSBER;
    }


    /**
     * Sets the i_GSBER value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @param i_GSBER
     */
    public void setI_GSBER(java.lang.String i_GSBER) {
        this.i_GSBER = i_GSBER;
    }


    /**
     * Gets the i_GUBUN value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @return i_GUBUN
     */
    public java.lang.String getI_GUBUN() {
        return i_GUBUN;
    }


    /**
     * Sets the i_GUBUN value for this DT_ZCG_CONT_IF_SELMS.
     * 
     * @param i_GUBUN
     */
    public void setI_GUBUN(java.lang.String i_GUBUN) {
        this.i_GUBUN = i_GUBUN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ZCG_CONT_IF_SELMS)) return false;
        DT_ZCG_CONT_IF_SELMS other = (DT_ZCG_CONT_IF_SELMS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INTAB==null && other.getINTAB()==null) || 
             (this.INTAB!=null &&
              java.util.Arrays.equals(this.INTAB, other.getINTAB()))) &&
            ((this.i_AFDATE==null && other.getI_AFDATE()==null) || 
             (this.i_AFDATE!=null &&
              this.i_AFDATE.equals(other.getI_AFDATE()))) &&
            ((this.i_ATDATE==null && other.getI_ATDATE()==null) || 
             (this.i_ATDATE!=null &&
              this.i_ATDATE.equals(other.getI_ATDATE()))) &&
            ((this.i_BUKRS==null && other.getI_BUKRS()==null) || 
             (this.i_BUKRS!=null &&
              this.i_BUKRS.equals(other.getI_BUKRS()))) &&
            ((this.i_GSBER==null && other.getI_GSBER()==null) || 
             (this.i_GSBER!=null &&
              this.i_GSBER.equals(other.getI_GSBER()))) &&
            ((this.i_GUBUN==null && other.getI_GUBUN()==null) || 
             (this.i_GUBUN!=null &&
              this.i_GUBUN.equals(other.getI_GUBUN())));
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
        if (getINTAB() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getINTAB());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getINTAB(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getI_AFDATE() != null) {
            _hashCode += getI_AFDATE().hashCode();
        }
        if (getI_ATDATE() != null) {
            _hashCode += getI_ATDATE().hashCode();
        }
        if (getI_BUKRS() != null) {
            _hashCode += getI_BUKRS().hashCode();
        }
        if (getI_GSBER() != null) {
            _hashCode += getI_GSBER().hashCode();
        }
        if (getI_GUBUN() != null) {
            _hashCode += getI_GUBUN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_ZCG_CONT_IF_SELMS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sec.com/GFI_ECC_FI/FIA", "DT_ZCG_CONT_IF_SELMS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTAB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTAB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sec.com/GFI_ECC_FI/FIA", ">DT_ZCG_CONT_IF_SELMS>INTAB"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_AFDATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_AFDATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_ATDATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_ATDATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_BUKRS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_BUKRS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_GSBER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_GSBER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("i_GUBUN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "I_GUBUN"));
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
