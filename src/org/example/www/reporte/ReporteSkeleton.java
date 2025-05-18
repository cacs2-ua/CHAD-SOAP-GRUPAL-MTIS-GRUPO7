
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
    	private EmpresaRepository empresaRepository;
    	private ValidacionSkeleton validaciones;
    	
    	public ReporteSkeleton() {
    		this.reporteRepository = new ReporteRepository();
    		this.empresaRepository = new EmpresaRepository();
    		this.validaciones = new ValidacionSkeleton();
    	}
         
        /**
         * Auto generated method signature
         * 
                                     * @param calcularDatosReporte 
             * @return calcularDatosReporteResponse 
         * @throws WSKeyNoValidaException 
         * @throws RangoFechasException 
         * @throws SQLException 
         */
        
                 public org.example.www.reporte.CalcularDatosReporteResponse calcularDatosReporte
                  (
                  org.example.www.reporte.CalcularDatosReporte calcularDatosReporte
                  ) throws WSKeyNoValidaException, RangoFechasException, SQLException
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
         * @throws RangoFechasException 
         * @throws WSKeyNoValidaException 
         * @throws SQLException 
         */
        
                 public org.example.www.reporte.CalcularDatosReporteGlobalResponse calcularDatosReporteGlobal
                  (
                  org.example.www.reporte.CalcularDatosReporteGlobal calcularDatosReporteGlobal
                  ) throws WSKeyNoValidaException, RangoFechasException, SQLException
            {
                	CalcularDatosReporteGlobalResponse response = new CalcularDatosReporteGlobalResponse();
               	 
             		String WSKey = calcularDatosReporteGlobal.getWSKey();
             		 
              		Utils.verificarWSKey(WSKey);
              		 
               		Calendar calFechaInicio = calcularDatosReporteGlobal.getFechaInicio();
               		
              		LocalDateTime fechaInicio = LocalDateTime.ofInstant(
          				calFechaInicio.toInstant(),
          				calFechaInicio.getTimeZone().toZoneId()
              		);
              		
              		Calendar calFechaFin = calcularDatosReporteGlobal.getFechaFin();
              		
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
              		
              		int numeroTotalFacturasEmitidas = this.reporteRepository.devolverTotalFacturasEmitidasGlobal(fechaInicio, fechaFin);
              		double sumaTotalImportes = this.reporteRepository.devolverSumaTotalImportesGlobal(fechaInicio, fechaFin);
              		int numeroTotalFacturasValidas = this.reporteRepository.devolverTotalFacturasGlobalPorEstado(fechaInicio, fechaFin, "VALIDA");
              		int numeroTotalFacturasSubsanadas = this.reporteRepository.devolverTotalFacturasGlobalPorEstado(fechaInicio, fechaFin, "RECTIFICADA");
              		int numeroTotalFacturasAnuladas = this.reporteRepository.devolverTotalFacturasGlobalPorEstado(fechaInicio, fechaFin, "ANULADA");
              		int numeroTotalFacturasInvalidas = this.reporteRepository.devolverTotalFacturasGlobalPorEstado(fechaInicio, fechaFin, "INVALIDA");
              		
              		ReporteType datosReporte = new ReporteType();
              		
              		String emailEmpresaPrincipal = this.empresaRepository.obtenerEmailEmpresaPorIdEmpresa(1L);
              		
              		datosReporte.setFechaInicio(calFechaInicio);
              		datosReporte.setFechaFin(calFechaFin);
              		datosReporte.setEmailEmpresa(emailEmpresaPrincipal);
              		datosReporte.setNumeroTotalFacturasEmitidas(numeroTotalFacturasEmitidas);
              		datosReporte.setSumaTotalImportes(sumaTotalImportes);
              		datosReporte.setNumeroTotalFacturasValidas(numeroTotalFacturasValidas);
              		datosReporte.setNumeroTotalFacturasSubsanadas(numeroTotalFacturasSubsanadas);
              		datosReporte.setNumeroTotalFacturasAnuladas(numeroTotalFacturasAnuladas);
              		datosReporte.setNumeroTotalFacturasInvalidas(numeroTotalFacturasInvalidas);
              		
              		response.setDatosReporte(datosReporte);
              		
              		if (datosReporte.getNumeroTotalFacturasEmitidas() == 0) {
                  		response.setMensajeSalida("Aún no se ha realizado ninguna facturación dentro de la aplicación.");
              		}
              		
              		else {
 	             		response.setMensajeSalida("Las estadisticas globales han sido calculadas exitosamente.");
              		}
              		
              		return response;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param guardarReporteEnBD 
             * @return guardarReporteEnBDResponse 
         * @throws SQLException 
         * @throws WSKeyNoValidaException 
         * @throws RangoFechasException 
         */
        
                 public org.example.www.reporte.GuardarReporteEnBDResponse guardarReporteEnBD
                  (
                  org.example.www.reporte.GuardarReporteEnBD guardarReporteEnBD
                  ) throws SQLException, WSKeyNoValidaException, RangoFechasException
            {
                GuardarReporteEnBDResponse response = new GuardarReporteEnBDResponse();
                String WSKey = guardarReporteEnBD.getWSKey();
                 
                Utils.verificarWSKey(WSKey);
                 
                Calendar calFechaInicio = guardarReporteEnBD.getDatosEntrada().getFechaInicio();
                Calendar calFechaFin = guardarReporteEnBD.getDatosEntrada().getFechaFin();
                 
          		ValidarFechas validarFechas = new ValidarFechas();
         		
          		validarFechas.setFechaInicio(calFechaInicio);
          		validarFechas.setFechaFin(calFechaFin);
          		validarFechas.setWSKey(WSKey);
          		
          		if (!this.validaciones.validarFechas(validarFechas).getValido()) {
          			throw new RangoFechasException("ERROR: El rango de fechas introducido NO es válido");
          		}
          		
         		LocalDateTime fechaInicio = LocalDateTime.ofInstant(
     				calFechaInicio.toInstant(),
     				calFechaInicio.getTimeZone().toZoneId()
         		);
         		
         		LocalDateTime fechaFin = LocalDateTime.ofInstant(
         				calFechaFin.toInstant(),
         				calFechaFin.getTimeZone().toZoneId());
         		
         		String emailEmpresa = guardarReporteEnBD.getDatosEntrada().getEmailEmpresa();
         		int empresaId = this.empresaRepository.obtenerIdEmpresaPorEmail(emailEmpresa);
         		
         		int numeroTotalFacturasEmitidas = guardarReporteEnBD.getDatosEntrada().getNumeroTotalFacturasEmitidas();
         		double sumaTotalImportes = guardarReporteEnBD.getDatosEntrada().getSumaTotalImportes();
         		int numeroTotalFacturasValidas = guardarReporteEnBD.getDatosEntrada().getNumeroTotalFacturasValidas();
         		int numeroTotalFacturasSubsanadas = guardarReporteEnBD.getDatosEntrada().getNumeroTotalFacturasSubsanadas();
         		int numeroTotalFacturasAnuladas = guardarReporteEnBD.getDatosEntrada().getNumeroTotalFacturasAnuladas();
         		int numeroTotalFacturasInvalidas = guardarReporteEnBD.getDatosEntrada().getNumeroTotalFacturasInvalidas();

                this.reporteRepository.insertarReporte(fechaInicio, fechaFin, empresaId, numeroTotalFacturasEmitidas, sumaTotalImportes, numeroTotalFacturasValidas, numeroTotalFacturasSubsanadas, numeroTotalFacturasAnuladas, numeroTotalFacturasInvalidas);
                 
	            response.setMensajeSalida("Reporte de estadísticas insertado correctamente. ");
	             
	            return response;
              }
        }
     
    
    