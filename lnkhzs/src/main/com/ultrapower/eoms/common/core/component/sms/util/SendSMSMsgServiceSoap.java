/**
 * SendSMSMsgServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ultrapower.eoms.common.core.component.sms.util;

public interface SendSMSMsgServiceSoap extends java.rmi.Remote {
    public java.lang.String sendMessage(java.lang.String sMobile, java.lang.String strContent, java.lang.String strApplyCode, java.lang.String strAppId) throws java.rmi.RemoteException;
}
