
/**
 * DocumentoSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.documento;
    /**
     *  DocumentoSkeleton java skeleton for the axisService
     */
    
    import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

import org.example.www.validacion.ValidacionSkeleton;
import org.example.www.validacion.ValidarFechas;

import exception.EmailPrincipalNoValidoException;
import exception.RangoFechasException;
import exception.WSKeyNoValidaException;
import utils.*;

import java.util.UUID;
import java.io.File;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TextAlignment;

import ConexionDB.DocumentoRepository;
import ConexionDB.EmpresaRepository;
import ConexionDB.ReporteRepository;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
    public class DocumentoSkeleton{
    	private DocumentoRepository documentoRepository;
    	private EmpresaRepository empresaRepository;
    	private ReporteRepository reporteRepository;
    	private ValidacionSkeleton validaciones;
    	
    	public DocumentoSkeleton() {
    		this.documentoRepository = new DocumentoRepository();
    		this.empresaRepository = new EmpresaRepository();
    		this.reporteRepository = new ReporteRepository();
    		this.validaciones = new ValidacionSkeleton();
    	}
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param generarPDF 
             * @return generarPDFResponse 
         * @throws Exception 
         */
        
                 public org.example.www.documento.GenerarPDFResponse generarPDF
                  (
                  org.example.www.documento.GenerarPDF generarPDF
                  ) throws Exception
            {
                	GenerarPDFResponse response = new GenerarPDFResponse();
               	 
             		String WSKey = generarPDF.getWSKey();
             		 
              		utils.Utils.verificarWSKey(WSKey);
              		 
               		Calendar calFechaInicio = generarPDF.getDatosReporte().getFechaInicio();
               		
              		Calendar calFechaFin = generarPDF.getDatosReporte().getFechaFin();
              		
              		ValidarFechas validarFechas = new ValidarFechas();
              		
              		validarFechas.setFechaInicio(calFechaInicio);
              		validarFechas.setFechaFin(calFechaFin);
              		validarFechas.setWSKey(WSKey);
              		
              		if (!this.validaciones.validarFechas(validarFechas).getValido()) {
              			throw new RangoFechasException("ERROR: El rango de fechas introducido NO es valido");
              		}
              		
              		LocalDateTime fechaInicio = LocalDateTime.ofInstant(
          				calFechaInicio.toInstant(),
          				calFechaInicio.getTimeZone().toZoneId()
              		);
              		
              		LocalDateTime fechaFin = LocalDateTime.ofInstant(
              				calFechaFin.toInstant(),
              				calFechaFin.getTimeZone().toZoneId());
              		
              		Long reporteId = (long) generarPDF.getDatosReporte().getReporteId();
              		String emailEmpresa = this.documentoRepository.obtenerEmailEmpresaPorIdReporte(reporteId);
              		
              		int numeroTotalFacturasEmitidas = generarPDF.getDatosReporte().getNumeroTotalFacturasEmitidas();
              		double sumaTotalImportes = generarPDF.getDatosReporte().getSumaTotalImportes();
              		int numeroTotalFacturasValidas = generarPDF.getDatosReporte().getNumeroTotalFacturasValidas();
              		int numeroTotalFacturasSubsanadas = generarPDF.getDatosReporte().getNumeroTotalFacturasSubsanadas();
              		int numeroTotalFacturasAnuladas = generarPDF.getDatosReporte().getNumeroTotalFacturasAnuladas();
              		int numeroTotalFacturasInvalidas = generarPDF.getDatosReporte().getNumeroTotalFacturasInvalidas();
              		
              		
              		try {
              			
              		
              		new File("src/resources/reports/global").mkdirs();
              		new File("src/resources/reports/normal").mkdirs();

              		String emailEmpresaPrincipal = this.empresaRepository.obtenerEmailEmpresaPorIdEmpresa(1L).trim();

              		String rutaBase;
              		String rutaPDF;
              		String tituloReporte;
              		
              		String uuid = UUID.randomUUID().toString();
              		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
              		String fileName = uuid + "_" + timestamp + ".pdf";

              		if (emailEmpresa.equals(emailEmpresaPrincipal)) {
              			rutaPDF = "C:/MTIS/workspaceEclipse/reporteEstadisticas/src/resources/reportes/global/" + fileName;
              		    tituloReporte = "GLOBAL STATISTICS REPORT";
              		} else {
              			rutaPDF = "C:/MTIS/workspaceEclipse/reporteEstadisticas/src/resources/reportes/normal/" + fileName;
              		    tituloReporte = "STATISTICS REPORT";
              		}
              		
                    File pdfFile = new File(rutaPDF);
                    LocalDateTime fechaCreacion = LocalDateTime.now();
                    
                    this.documentoRepository.insertarDocumentoReporte(fechaCreacion, fileName, rutaPDF, 0L, "application/pdf", reporteId);
                    


              		// Crear el documento PDF
              		PdfWriter pdfWriter = new PdfWriter(rutaPDF);
              		PdfDocument pdfDoc = new PdfDocument(pdfWriter);
              		Document document = new Document(pdfDoc);

              		PdfFont font  = PdfFontFactory.createFont(StandardFonts.HELVETICA);
              		PdfFont bold  = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
              		
                    pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new IEventHandler() {
                        @Override
                        public void handleEvent(Event event) {
                            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
                            PdfPage page = docEvent.getPage();
                            int pageNumber = docEvent.getDocument().getPageNumber(page);
                            Rectangle pageSize = page.getPageSize();
                            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), docEvent.getDocument());
                            // Aquí usamos sólo pdfCanvas y pageSize
                            Canvas canvas = new Canvas(pdfCanvas, pageSize);
                            canvas.setFont(font)
                                    .setFontSize(10)
                                    .showTextAligned(
                                            String.valueOf(pageNumber),
                                            pageSize.getRight() - 36,
                                            pageSize.getBottom() + 15,
                                            TextAlignment.RIGHT
                                    );
                            canvas.close();
                        }
                    });

              		
              	// Encabezado en inglés: "MTIS Group7, May 18, 2025 - 16:35"
              		LocalDateTime now = LocalDateTime.now();
              		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy - HH:mm", Locale.ENGLISH);
              		String formattedDate = now.format(formatter);

              		String header = "Electronic invoicing - MTIS Group7, " + formattedDate;

              		document.add(new Paragraph(header)
              		        .setFont(font)
              		        .setFontSize(10)
              		        .setTextAlignment(TextAlignment.RIGHT)
              		        .setMarginBottom(10)
              		);

              		
              		// T tulo del documento seg n tipo de empresa
              		document.add(new Paragraph(tituloReporte)
              		        .setFont(bold)
              		        .setFontSize(14)
              		        .setTextAlignment(TextAlignment.CENTER)
              		        .setMarginBottom(15)
              		);

	                    
	                    String fechaInicioStr = fechaInicio.format(formatter);
	                    String fechaFinStr = fechaFin.format(formatter);
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Company email: ").setFont(bold).setFontSize(10))
	                            .add(new Text(emailEmpresa).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Start date: ").setFont(bold).setFontSize(10))
	                            .add(new Text(fechaInicioStr).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("End date: ").setFont(bold).setFontSize(10))
	                            .add(new Text(fechaFinStr).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Total number of issued invoices: ").setFont(bold).setFontSize(10))
	                            .add(new Text(String.valueOf(numeroTotalFacturasEmitidas)).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Total sum of all amounts (including VAT): ").setFont(bold).setFontSize(10))
	                            .add(new Text(String.valueOf(sumaTotalImportes)).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Total number of valid invoices (corrected or not corrected): ").setFont(bold).setFontSize(10))
	                            .add(new Text(String.valueOf(numeroTotalFacturasValidas + numeroTotalFacturasSubsanadas)).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Total number of valid and not corrected invoices: ").setFont(bold).setFontSize(10))
	                            .add(new Text(String.valueOf(numeroTotalFacturasValidas)).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Total number of valid and corrected invoices: ").setFont(bold).setFontSize(10))
	                            .add(new Text(String.valueOf(numeroTotalFacturasSubsanadas)).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Total number of canceled invoices: ").setFont(bold).setFontSize(10))
	                            .add(new Text(String.valueOf(numeroTotalFacturasAnuladas)).setFont(font).setFontSize(10))
	                    );
	                    
	                    document.add(new Paragraph()
	                            .setMarginLeft(20)
	                            .add(new Text("- ").setFont(font).setFontSize(10))
	                            .add(new Text("Total number of invalid invoices: ").setFont(bold).setFontSize(10))
	                            .add(new Text(String.valueOf(numeroTotalFacturasInvalidas)).setFont(font).setFontSize(10))
	                    );
	                    

	                    
	                    document.close();
	                    
	                    long tamanyo = pdfFile.length();
	                    
	                    this.documentoRepository.modificarTamanyoPorIdReporte(reporteId, tamanyo);
	                    
	                    response.setMensajeSalida("PDF registrado correctamente y almacenado en: " + rutaPDF);
	              		
	              		return response;
	              		
	              	} catch (Exception e) {
	              		e.printStackTrace();
	              		throw e;
	              	}
        }
     
    }
    