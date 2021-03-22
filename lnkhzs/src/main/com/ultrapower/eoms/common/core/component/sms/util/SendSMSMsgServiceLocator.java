/**
 * SendSMSMsgServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ultrapower.eoms.common.core.component.sms.util;

public class SendSMSMsgServiceLocator extends org.apache.axis.client.Service implements com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgService {

    public SendSMSMsgServiceLocator() {
    }


    public SendSMSMsgServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendSMSMsgServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendSMSMsgServiceSoap
    private java.lang.String SendSMSMsgServiceSoap_address = "http://10.64.27.133:81/SMS_M/SendSMSMsgService.asmx";

    public java.lang.String getSendSMSMsgServiceSoapAddress() {
        return SendSMSMsgServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendSMSMsgServiceSoapWSDDServiceName = "SendSMSMsgServiceSoap";

    public java.lang.String getSendSMSMsgServiceSoapWSDDServiceName() {
        return SendSMSMsgServiceSoapWSDDServiceName;
    }

    public void setSendSMSMsgServiceSoapWSDDServiceName(java.lang.String name) {
        SendSMSMsgServiceSoapWSDDServiceName = name;
    }

    public com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoap getSendSMSMsgServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendSMSMsgServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendSMSMsgServiceSoap(endpoint);
    }

    public com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoap getSendSMSMsgServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoapStub _stub = new com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoapStub(portAddress, this);
            _stub.setPortName(getSendSMSMsgServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendSMSMsgServiceSoapEndpointAddress(java.lang.String address) {
        SendSMSMsgServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
            	com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoapStub _stub = new com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoapStub(new java.net.URL(SendSMSMsgServiceSoap_address), this);
                _stub.setPortName(getSendSMSMsgServiceSoapWSDDServiceName());
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
        if ("SendSMSMsgServiceSoap".equals(inputPortName)) {
            return getSendSMSMsgServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "SendSMSMsgService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "SendSMSMsgServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendSMSMsgServiceSoap".equals(portName)) {
            setSendSMSMsgServiceSoapEndpointAddress(address);
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
