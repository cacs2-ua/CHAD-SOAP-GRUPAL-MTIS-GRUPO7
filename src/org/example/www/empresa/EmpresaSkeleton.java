
/**
 * EmpresaSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.empresa;
    
    import java.sql.SQLException;
    import exception.WSKeyNoValidaException;
    
    import ConexionDB.EmpresaRepository;
    
    import utils.*;
    
    
    /**
     *  EmpresaSkeleton java skeleton for the axisService
     */
    public class EmpresaSkeleton{
    	
    	private EmpresaRepository empresaRepository;
    	
    	public EmpresaSkeleton() {
    		this.empresaRepository = new EmpresaRepository();
    	}
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param consultarEmpresa 
             * @return consultarEmpresaResponse 
         */
        
                 public org.example.www.empresa.ConsultarEmpresaResponse consultarEmpresa
                  (
                  org.example.www.empresa.ConsultarEmpresa consultarEmpresa
                  ) throws WSKeyNoValidaException, SQLException
            {
                	 ConsultarEmpresaResponse response = new ConsultarEmpresaResponse();
                	 
                	 try {
                		 String WSKey = consultarEmpresa.getWSKey();
                		 
                		 Utils.verificarWSKey(WSKey);
                		 
                		 String email = consultarEmpresa.getEmailEmpresa();
                		 
                		 EmpresaType empresaObtenida = this.empresaRepository.consultarEmpresa(email);
                		 
                         response.setEmpresa(empresaObtenida);
                         response.setMensajeSalida("OK: La empresa con email: " + email
                         		+ "ha sido consultado correctamente. ");
                         return response;
                	 } catch (Exception e) {
              			response.setMensajeSalida(e.getMessage());
             			return response;
             		}
                
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param consultarTodasEmpresas 
             * @return consultarTodasEmpresasResponse 
         */
        
                 public org.example.www.empresa.ConsultarTodasEmpresasResponse consultarTodasEmpresas
                  (
                  org.example.www.empresa.ConsultarTodasEmpresas consultarTodasEmpresas
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#consultarTodasEmpresas");
        }
     
    }
    