package com.sec.GFI_ECC_FI.FIA;

public class FI11004_ZCG_CONT_IF_SELMS_SOProxy implements com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SO {
  private String _endpoint = null;
  private com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SO fI11004_ZCG_CONT_IF_SELMS_SO = null;
  
  public FI11004_ZCG_CONT_IF_SELMS_SOProxy() {
    _initFI11004_ZCG_CONT_IF_SELMS_SOProxy();
  }
  
  public FI11004_ZCG_CONT_IF_SELMS_SOProxy(String endpoint) {
    _endpoint = endpoint;
    _initFI11004_ZCG_CONT_IF_SELMS_SOProxy();
  }
  
  private void _initFI11004_ZCG_CONT_IF_SELMS_SOProxy() {
    try {
      fI11004_ZCG_CONT_IF_SELMS_SO = (new com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SOServiceLocator()).getFI11004_ZCG_CONT_IF_SELMS_SOPort();
      if (fI11004_ZCG_CONT_IF_SELMS_SO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)fI11004_ZCG_CONT_IF_SELMS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)fI11004_ZCG_CONT_IF_SELMS_SO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (fI11004_ZCG_CONT_IF_SELMS_SO != null)
      ((javax.xml.rpc.Stub)fI11004_ZCG_CONT_IF_SELMS_SO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sec.GFI_ECC_FI.FIA.FI11004_ZCG_CONT_IF_SELMS_SO getFI11004_ZCG_CONT_IF_SELMS_SO() {
    if (fI11004_ZCG_CONT_IF_SELMS_SO == null)
      _initFI11004_ZCG_CONT_IF_SELMS_SOProxy();
    return fI11004_ZCG_CONT_IF_SELMS_SO;
  }
  
  public com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS_R FI11004_ZCG_CONT_IF_SELMS_SO(com.sec.GFI_ECC_FI.FIA.DT_ZCG_CONT_IF_SELMS MT_ZCG_CONT_IF_SELMS) throws java.rmi.RemoteException{
    if (fI11004_ZCG_CONT_IF_SELMS_SO == null)
      _initFI11004_ZCG_CONT_IF_SELMS_SOProxy();
    return fI11004_ZCG_CONT_IF_SELMS_SO.FI11004_ZCG_CONT_IF_SELMS_SO(MT_ZCG_CONT_IF_SELMS);
  }
  
  
}