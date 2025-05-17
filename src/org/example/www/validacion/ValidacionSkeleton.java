
/**
 * ValidacionSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.validacion;
    /**
     *  ValidacionSkeleton java skeleton for the axisService
     */
    
import java.time.LocalDateTime;
import java.util.Calendar;

import exception.WSKeyNoValidaException;
	import utils.*;
    public class ValidacionSkeleton{
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param validarFechas 
             * @return validarFechasResponse 
         */
        
                 public org.example.www.validacion.ValidarFechasResponse validarFechas
                  (
                  org.example.www.validacion.ValidarFechas validarFechas
                  )
            {
                	 ValidarFechasResponse response = new ValidarFechasResponse();

             		try {
                		String WSKey = validarFechas.getWSKey();
                        
                        Utils.verificarWSKey(WSKey);
            		} catch (Exception e) {
            			response.setValido(false);
            	        response.setMensajeSalida(e.getMessage());
            	        return response;
            		}
             		
             		
             		Calendar calFechaInicio = validarFechas.getFechaInicio();
             		LocalDateTime fechaInicio = LocalDateTime.ofInstant(
         				calFechaInicio.toInstant(),
         				calFechaInicio.getTimeZone().toZoneId()
             		);
             		
             		Calendar calFechaFin = validarFechas.getFechaFin();
             		LocalDateTime fechaFin = LocalDateTime.ofInstant(
             				calFechaFin.toInstant(),
             				calFechaFin.getTimeZone().toZoneId()
             		);
             		
             		if (fechaInicio.isAfter(fechaFin) || fechaInicio.isEqual(fechaFin)) {
             			response.setValido(false);
             			response.setMensajeSalida("ERROR: El rango de fechas introducido NO es válido");
             		}
             		
             		else {
             			response.setValido(true);
             			response.setMensajeSalida("OK: El rango de fechas introducido SÍ es válido");
             		}
             		
             		return response;

        }
     
    }
    