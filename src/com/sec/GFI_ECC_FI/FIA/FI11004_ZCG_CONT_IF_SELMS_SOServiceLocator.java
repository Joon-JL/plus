/**
 * FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sec.GFI_ECC_FI.FIA;

public class FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator extends org.apache.axis.client.Service implements com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SOService {

    public FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator() {
    }


    public FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    
 // 2014-04-01 Kevin. Firstly stage end point has been applied for implementation.
    private java.lang.String FI11004_ZCG_CONT_IF_SELMS_SOPort_address = "http://gxqci01.sec.samsung.net:8170/sap/xi/engine?type=entry&version=3.0&Sender.Service=D229_SELMS_EU_Q&Interface=http%3A%2F%2Fsec.com%2FGFI_ECC_FI%2FFIA%5EFI11004_ZCG_CONT_IF_SELMS_SO";
    // 2014-05-07 Kevin added.
    public void setPortAddress(String portAddress){
    	FI11004_ZCG_CONT_IF_SELMS_SOPort_address = portAddress;
    }
    public String getPortAddrss(){
    	return this.FI11004_ZCG_CONT_IF_SELMS_SOPort_address;
    }
    // 2014-04-01 Kevin. Follows are actual end point address for this project.
    // stage: http://gxqci01.sec.samsung.net:8170/sap/xi/engine?type=entry&amp;version=3.0&amp;Sender.Service=D229_SELMS_EU_Q&amp;Interface=http%3A%2F%2Fsec.com%2FGFI_ECC_FI%2FFIA%5EFI11004_ZCG_CONT_IF_SELMS_SO
    // product: http://gerppi.sec.samsung.net:8130/sap/xi/engine?type=entry&amp;version=3.0&amp;Sender.Service=D229_SELMS_EU_P&amp;Interface=http%3A%2F%2Fsec.com%2FGFI_ECC_FI%2FFIA%5EFI11004_ZCG_CONT_IF_SELMS_SO
   
    public java.lang.String getFI11004_ZCG_CONT_IF_SELMS_SOPortAddress() {
        return FI11004_ZCG_CONT_IF_SELMS_SOPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FI11004_ZCG_CONT_IF_SELMS_SOPortWSDDServiceName = "FI11004_ZCG_CONT_IF_SELMS_SOPort";

    public java.lang.String getFI11004_ZCG_CONT_IF_SELMS_SOPortWSDDServiceName() {
        return FI11004_ZCG_CONT_IF_SELMS_SOPortWSDDServiceName;
    }

    public void setFI11004_ZCG_CONT_IF_SELMS_SOPortWSDDServiceName(java.lang.String name) {
        FI11004_ZCG_CONT_IF_SELMS_SOPortWSDDServiceName = name;
    }

    public com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SO getFI11004_ZCG_CONT_IF_SELMS_SOPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FI11004_ZCG_CONT_IF_SELMS_SOPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFI11004_ZCG_CONT_IF_SELMS_SOPort(endpoint);
    }

    public com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SO getFI11004_ZCG_CONT_IF_SELMS_SOPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SOBindingStub _stub = new com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SOBindingStub(portAddress, this);
            _stub.setPortName(getFI11004_ZCG_CONT_IF_SELMS_SOPortWSDDServiceName());
            
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFI11004_ZCG_CONT_IF_SELMS_SOPortEndpointAddress(java.lang.String address) {
        FI11004_ZCG_CONT_IF_SELMS_SOPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SO.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SOBindingStub _stub = new com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SOBindingStub(new java.net.URL(FI11004_ZCG_CONT_IF_SELMS_SOPort_address), this);
                _stub.setPortName(getFI11004_ZCG_CONT_IF_SELMS_SOPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FI11004_ZCG_CONT_IF_SELMS_SOPort".equals(inputPortName)) {
            return getFI11004_ZCG_CONT_IF_SELMS_SOPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sec.com/GFI_ECC_FI/FIA", "FI11004_ZCG_CONT_IF_SELMS_SOService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sec.com/GFI_ECC_FI/FIA", "FI11004_ZCG_CONT_IF_SELMS_SOPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FI11004_ZCG_CONT_IF_SELMS_SOPort".equals(portName)) {
            setFI11004_ZCG_CONT_IF_SELMS_SOPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
