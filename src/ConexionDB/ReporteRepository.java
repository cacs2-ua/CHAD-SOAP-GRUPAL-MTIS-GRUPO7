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
import java.sql.Statement;


import exception.*;

public class ReporteRepository {
	
	private Conexion conexion;
	
	public ReporteRepository() {
		this.conexion = new Conexion();
	}
	
	public Long obtenerIdPorempresaId(Long empresaId) throws SQLException {
	    Connection con = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        con = this.conexion.conectar();
	        String sql = "SELECT id FROM reportes_estadisticas WHERE empresa_id = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setLong(1, empresaId);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getLong("id");
	        } else {
	            return -1L;
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
	
	public int insertarReporte(
	        LocalDateTime fechaInicio,
	        LocalDateTime fechaFin,
	        int empresaId,
	        int numeroTotalFacturasEmitidas,
	        double sumaTotalImportes,
	        int numeroTotalFacturasValidas,
	        int numeroTotalFacturasSubsanadas,
	        int numeroTotalFacturasAnuladas,
	        int numeroTotalFacturasInvalidas) throws SQLException {

	    String sql = "INSERT INTO reportes_estadisticas "
	               + "(fecha_inicio, fecha_fin, empresa_id, numero_total_facturas_emitidas, suma_total_importes, "
	               + "numero_total_facturas_validas, numero_total_facturas_subsanadas, numero_total_facturas_anuladas, "
	               + "numero_total_facturas_invalidas) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = this.conexion.conectar();
	         PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
	        stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
	        stmt.setInt(3, empresaId);
	        stmt.setInt(4, numeroTotalFacturasEmitidas);
	        stmt.setDouble(5, sumaTotalImportes);
	        stmt.setInt(6, numeroTotalFacturasValidas);
	        stmt.setInt(7, numeroTotalFacturasSubsanadas);
	        stmt.setInt(8, numeroTotalFacturasAnuladas);
	        stmt.setInt(9, numeroTotalFacturasInvalidas);

	        int filasAfectadas = stmt.executeUpdate();
	        if (filasAfectadas == 0) {
	            throw new SQLException("Inserción fallida, no se generó ningún ID.");
	        }

	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            } else {
	                throw new SQLException("Inserción realizada pero no se pudo obtener el ID generado.");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

	public int devolverTotalReportesCreados(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
            String sql = "SELECT COUNT(DISTINCT r.id) AS total " +
                    "FROM reportes_estadisticas r " +
                    "WHERE r.fecha_inicio >= ? " +
                    "  AND r.fecha_fin    <= ?";
            stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
	}
	
	public int devolverTotalFacturasEmitidasEmpresa(String email, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
            String sql = "SELECT COUNT(DISTINCT f.id) AS total "
                    + "FROM facturas f "
                    + "JOIN empresas e ON f.empresa_id = e.id "
                    + "WHERE e.email = ? "
                    + "and f.fecha_emision >= ? "
                    + "and f.fecha_emision <= ? ";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(3, Timestamp.valueOf(fechaFin));
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
	}
	
	public double devolverSumaTotalImportesEmpresa(String email, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
            String sql = "SELECT SUM(f.base_imponible * (1 + f.iva)) AS total "
                    + "FROM facturas f "
                    + "JOIN empresas e ON f.empresa_id = e.id "
                    + "WHERE e.email = ? "
                    + "and f.fecha_emision >= ? "
                    + "and f.fecha_emision <= ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(3, Timestamp.valueOf(fechaFin));
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                double rawTotal = rs.getDouble("total");
                if (rs.wasNull()) return 0.0;

                return new BigDecimal(rawTotal)
                       .setScale(2, RoundingMode.HALF_UP)
                       .doubleValue();
            } else {
                return 0.0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
	}
	
	public int devolverTotalFacturasEmpresaPorEstado(String email, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
            String sql = "SELECT COUNT(DISTINCT f.id) AS total "
                    + "FROM facturas f "
                    + "JOIN empresas e ON f.empresa_id = e.id "
                    + "WHERE e.email = ? "
                    + "and f.fecha_emision >= ? "
                    + "and f.fecha_emision <= ? "
                    + "and f.estado = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setTimestamp(2, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(3, Timestamp.valueOf(fechaFin));
            stmt.setString(4, estado);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
	}
	
	public int devolverTotalFacturasEmitidasGlobal(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
            String sql = "SELECT COUNT(DISTINCT f.id) AS total "
                    + "FROM facturas f WHERE f.fecha_emision >= ?  " 
                    + "and f.fecha_emision <= ? ";
            stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
	}
	
	public double devolverSumaTotalImportesGlobal(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
            String sql = "SELECT SUM(f.base_imponible * (1 + f.iva)) AS total "
                    + "FROM facturas f WHERE f.fecha_emision >= ? "
                    + "and f.fecha_emision <= ?";
            stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                double rawTotal = rs.getDouble("total");
                if (rs.wasNull()) return 0.0;

                return new BigDecimal(rawTotal)
                       .setScale(2, RoundingMode.HALF_UP)
                       .doubleValue();
            } else {
                return 0.0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
	}
	
	public int devolverTotalFacturasGlobalPorEstado(LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
            String sql = "SELECT COUNT(DISTINCT f.id) AS total "
                    + "FROM facturas f WHERE f.fecha_emision >= ? "
                    + "and f.fecha_emision <= ? "
                    + "and f.estado = ?";
            stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));
            stmt.setString(3, estado);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
	}

}
