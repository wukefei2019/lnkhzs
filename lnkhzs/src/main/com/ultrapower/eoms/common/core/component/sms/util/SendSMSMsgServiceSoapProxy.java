package com.ultrapower.eoms.common.core.component.sms.util;

public class SendSMSMsgServiceSoapProxy implements com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoap {
  private String _endpoint = null;
  private com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoap sendSMSMsgServiceSoap = null;
  
  public SendSMSMsgServiceSoapProxy() {
    _initSendSMSMsgServiceSoapProxy();
  }
  
  public SendSMSMsgServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendSMSMsgServiceSoapProxy();
  }
  
  private void _initSendSMSMsgServiceSoapProxy() {
    try {
      sendSMSMsgServiceSoap = (new com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceLocator()).getSendSMSMsgServiceSoap();
      if (sendSMSMsgServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendSMSMsgServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendSMSMsgServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendSMSMsgServiceSoap != null)
      ((javax.xml.rpc.Stub)sendSMSMsgServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoap getSendSMSMsgServiceSoap() {
    if (sendSMSMsgServiceSoap == null)
      _initSendSMSMsgServiceSoapProxy();
    return sendSMSMsgServiceSoap;
  }
  
  public java.lang.String sendMessage(java.lang.String sMobile, java.lang.String strContent, java.lang.String strApplyCode, java.lang.String strAppId) throws java.rmi.RemoteException{
    if (sendSMSMsgServiceSoap == null)
      _initSendSMSMsgServiceSoapProxy();
    return sendSMSMsgServiceSoap.sendMessage(sMobile, strContent, strApplyCode, strAppId);
  }
  
  
}