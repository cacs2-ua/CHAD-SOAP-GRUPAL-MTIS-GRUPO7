
/**
 * ReporteMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
        package org.example.www.reporte;

        /**
        *  ReporteMessageReceiverInOut message receiver
        */

        public class ReporteMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        ReporteSkeleton skel = (ReporteSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("calcularDatosReporte".equals(methodName)){
                
                org.example.www.reporte.CalcularDatosReporteResponse calcularDatosReporteResponse13 = null;
	                        org.example.www.reporte.CalcularDatosReporte wrappedParam =
                                                             (org.example.www.reporte.CalcularDatosReporte)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.reporte.CalcularDatosReporte.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               calcularDatosReporteResponse13 =
                                                   
                                                   
                                                         skel.calcularDatosReporte(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), calcularDatosReporteResponse13, false, new javax.xml.namespace.QName("http://www.example.org/Reporte/",
                                                    "calcularDatosReporte"));
                                    } else 

            if("calcularDatosReporteGlobal".equals(methodName)){
                
                org.example.www.reporte.CalcularDatosReporteGlobalResponse calcularDatosReporteGlobalResponse15 = null;
	                        org.example.www.reporte.CalcularDatosReporteGlobal wrappedParam =
                                                             (org.example.www.reporte.CalcularDatosReporteGlobal)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.reporte.CalcularDatosReporteGlobal.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               calcularDatosReporteGlobalResponse15 =
                                                   
                                                   
                                                         skel.calcularDatosReporteGlobal(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), calcularDatosReporteGlobalResponse15, false, new javax.xml.namespace.QName("http://www.example.org/Reporte/",
                                                    "calcularDatosReporteGlobal"));
                                    } else 

            if("guardarReporteEnBD".equals(methodName)){
                
                org.example.www.reporte.GuardarReporteEnBDResponse guardarReporteEnBDResponse17 = null;
	                        org.example.www.reporte.GuardarReporteEnBD wrappedParam =
                                                             (org.example.www.reporte.GuardarReporteEnBD)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.reporte.GuardarReporteEnBD.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               guardarReporteEnBDResponse17 =
                                                   
                                                   
                                                         skel.guardarReporteEnBD(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), guardarReporteEnBDResponse17, false, new javax.xml.namespace.QName("http://www.example.org/Reporte/",
                                                    "guardarReporteEnBD"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.reporte.CalcularDatosReporte param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.reporte.CalcularDatosReporte.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.reporte.CalcularDatosReporteResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.reporte.CalcularDatosReporteResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.reporte.CalcularDatosReporteGlobal param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.reporte.CalcularDatosReporteGlobal.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.reporte.CalcularDatosReporteGlobalResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.reporte.CalcularDatosReporteGlobalResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.reporte.GuardarReporteEnBD param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.reporte.GuardarReporteEnBD.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.reporte.GuardarReporteEnBDResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.reporte.GuardarReporteEnBDResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.reporte.CalcularDatosReporteResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.reporte.CalcularDatosReporteResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.reporte.CalcularDatosReporteResponse wrapcalcularDatosReporte(){
                                org.example.www.reporte.CalcularDatosReporteResponse wrappedElement = new org.example.www.reporte.CalcularDatosReporteResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.reporte.CalcularDatosReporteGlobalResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.reporte.CalcularDatosReporteGlobalResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.reporte.CalcularDatosReporteGlobalResponse wrapcalcularDatosReporteGlobal(){
                                org.example.www.reporte.CalcularDatosReporteGlobalResponse wrappedElement = new org.example.www.reporte.CalcularDatosReporteGlobalResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.reporte.GuardarReporteEnBDResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.reporte.GuardarReporteEnBDResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.reporte.GuardarReporteEnBDResponse wrapguardarReporteEnBD(){
                                org.example.www.reporte.GuardarReporteEnBDResponse wrappedElement = new org.example.www.reporte.GuardarReporteEnBDResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (org.example.www.reporte.CalcularDatosReporte.class.equals(type)){
                
                        return org.example.www.reporte.CalcularDatosReporte.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.reporte.CalcularDatosReporteGlobal.class.equals(type)){
                
                        return org.example.www.reporte.CalcularDatosReporteGlobal.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.reporte.CalcularDatosReporteGlobalResponse.class.equals(type)){
                
                        return org.example.www.reporte.CalcularDatosReporteGlobalResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.reporte.CalcularDatosReporteResponse.class.equals(type)){
                
                        return org.example.www.reporte.CalcularDatosReporteResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.reporte.GuardarReporteEnBD.class.equals(type)){
                
                        return org.example.www.reporte.GuardarReporteEnBD.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.reporte.GuardarReporteEnBDResponse.class.equals(type)){
                
                        return org.example.www.reporte.GuardarReporteEnBDResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    