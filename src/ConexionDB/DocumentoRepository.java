package ConexionDB;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import exception.*;

public class DocumentoRepository {
	
	private Conexion conexion;
	
	public DocumentoRepository() {
		this.conexion = new Conexion();
	}
	
    public void insertarDocumentoReporte(
            LocalDateTime fechaCreacion,
            String nombre,
            String rutaAlmacenamiento,
            Long tamanyo,
            String tipoArchivo,
            Long reporteEstadisticasId) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
        	con = this.conexion.conectar();

            String sql = "INSERT INTO documentos "
                       + "(fecha_creacion, nombre, ruta_almacenamiento, tamanyo, tipo_archivo, reporte_estadisticas_id) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";

            stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaCreacion));
            stmt.setString(2, nombre);
            stmt.setString(3, rutaAlmacenamiento);
            stmt.setLong(4, tamanyo);
            stmt.setString(5, tipoArchivo);
            stmt.setLong(6, reporteEstadisticasId);

            // 4. Ejecutar la insercion
            int filasAfectadas = stmt.executeUpdate();

        } catch (Exception e) {
	    	e.printStackTrace();
        	throw e;
        } finally {
            // Cerrar recursos en el finally
            if (stmt != null) {
                try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if (con != null) {
                try { con.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
    
    public String obtenerEmailEmpresaPorIdReporte(Long reporteEstadisticasId) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = this.conexion.conectar();
            String sql = "SELECT e.email FROM empresas e "
                       + "JOIN reportes_estadisticas r ON e.id = r.empresa_id "
                       + "WHERE r.id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, reporteEstadisticasId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("email");
            } else {
                return "Empresa no encontrada.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    public void modificarTamanyoPorIdReporte(Long reporteEstadisticasId, Long nuevoTamanyo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = this.conexion.conectar();

            String sql = "UPDATE documentos "
                       + "SET tamanyo = ? "
                       + "WHERE reporte_estadisticas_id = ?";

            stmt = con.prepareStatement(sql);
            stmt.setLong(1, nuevoTamanyo);
            stmt.setLong(2, reporteEstadisticasId);

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new NoSuchElementException("No se encontró ningún documento con ese reporte_estadisticas_id.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }



}
