
/**
 * ReporteSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.reporte;
    /**
     *  ReporteSkeleton java skeleton for the axisService
     */
    
    import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

import exception.WSKeyNoValidaException;
import exception.RangoFechasException;
import utils.*;

import org.example.www.validacion.ValidacionSkeleton;
import org.example.www.validacion.ValidarFechas;

import ConexionDB.EmpresaRepository;
import ConexionDB.ReporteRepository;

    public class ReporteSkeleton{
    	
    	private ReporteRepository reporteRepository;
    	private ValidacionSkeleton validaciones;
    	
    	public ReporteSkeleton() {
    		this.reporteRepository = new ReporteRepository();
    		this.validaciones = new ValidacionSkeleton();
    	}
         
        /**
         * Auto generated method signature
         * 
                                     * @param calcularDatosReporte 
             * @return calcularDatosReporteResponse 
         * @throws WSKeyNoValidaException 
         * @throws SQLException 
         * @throws RangoFechasException 
         */
        
                 public org.example.www.reporte.CalcularDatosReporteResponse calcularDatosReporte
                  (
                  org.example.www.reporte.CalcularDatosReporte calcularDatosReporte
                  ) throws WSKeyNoValidaException, SQLException, RangoFechasException
            {
                	CalcularDatosReporteResponse response = new CalcularDatosReporteResponse();
                	 
            		String WSKey = calcularDatosReporte.getWSKey();
            		 
             		Utils.verificarWSKey(WSKey);
             		 
              		Calendar calFechaInicio = calcularDatosReporte.getDatosEntrada().getFechaInicio();
              		
             		LocalDateTime fechaInicio = LocalDateTime.ofInstant(
         				calFechaInicio.toInstant(),
         				calFechaInicio.getTimeZone().toZoneId()
             		);
             		
             		Calendar calFechaFin = calcularDatosReporte.getDatosEntrada().getFechaFin();
             		
             		LocalDateTime fechaFin = LocalDateTime.ofInstant(
             				calFechaFin.toInstant(),
             				calFechaFin.getTimeZone().toZoneId());
             		
             		ValidarFechas validarFechas = new ValidarFechas();
             		
             		validarFechas.setFechaInicio(calFechaInicio);
             		validarFechas.setFechaFin(calFechaFin);
             		validarFechas.setWSKey(WSKey);
             		
             		if (!this.validaciones.validarFechas(validarFechas).getValido()) {
             			throw new RangoFechasException("ERROR: El rango de fechas introducido NO es válido");
             		}
             		
             		String email = calcularDatosReporte.getDatosEntrada().getEmailEmpresa();
             		
             		int numeroTotalFacturasEmitidas = this.reporteRepository.devolverTotalFacturasEmitidasEmpresa(email, fechaInicio, fechaFin);
             		double sumaTotalImportes = this.reporteRepository.devolverSumaTotalImportesEmpresa(email, fechaInicio, fechaFin);
             		int numeroTotalFacturasValidas = this.reporteRepository.devolverTotalFacturasEmpresaPorEstado(email, fechaInicio, fechaFin, "VALIDA");
             		int numeroTotalFacturasSubsanadas = this.reporteRepository.devolverTotalFacturasEmpresaPorEstado(email, fechaInicio, fechaFin, "RECTIFICADA");
             		int numeroTotalFacturasAnuladas = this.reporteRepository.devolverTotalFacturasEmpresaPorEstado(email, fechaInicio, fechaFin, "ANULADA");
             		int numeroTotalFacturasInvalidas = this.reporteRepository.devolverTotalFacturasEmpresaPorEstado(email, fechaInicio, fechaFin, "INVALIDA");
             		
             		ReporteType datosReporte = new ReporteType();
             		
             		datosReporte.setFechaInicio(calFechaInicio);
             		datosReporte.setFechaFin(calFechaFin);
             		datosReporte.setEmailEmpresa(email);
             		datosReporte.setNumeroTotalFacturasEmitidas(numeroTotalFacturasEmitidas);
             		datosReporte.setSumaTotalImportes(sumaTotalImportes);
             		datosReporte.setNumeroTotalFacturasValidas(numeroTotalFacturasValidas);
             		datosReporte.setNumeroTotalFacturasSubsanadas(numeroTotalFacturasSubsanadas);
             		datosReporte.setNumeroTotalFacturasAnuladas(numeroTotalFacturasAnuladas);
             		datosReporte.setNumeroTotalFacturasInvalidas(numeroTotalFacturasInvalidas);
             		
             		response.setDatosReporte(datosReporte);
             		
             		if (datosReporte.getNumeroTotalFacturasEmitidas() == 0) {
                 		response.setMensajeSalida("La empresa con email: " + email + " no ha realizado ninguna facturación"
                 				+ " en el periodo de tiempo seleccionado.");
             		}
             		
             		else {
	             		response.setMensajeSalida("Datos del reporte de estadísticas calculados correctamente"
	             				+ "para la empresa con email: " + email);
             		}

             		
             		return response;

        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param calcularDatosReporteGlobal 
             * @return calcularDatosReporteGlobalResponse 
         */
        
                 public org.example.www.reporte.CalcularDatosReporteGlobalResponse calcularDatosReporteGlobal
                  (
                  org.example.www.reporte.CalcularDatosReporteGlobal calcularDatosReporteGlobal
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#calcularDatosReporteGlobal");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param guardarReporteEnBD 
             * @return guardarReporteEnBDResponse 
         */
        
                 public org.example.www.reporte.GuardarReporteEnBDResponse guardarReporteEnBD
                  (
                  org.example.www.reporte.GuardarReporteEnBD guardarReporteEnBD
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#guardarReporteEnBD");
        }
     
    }
    