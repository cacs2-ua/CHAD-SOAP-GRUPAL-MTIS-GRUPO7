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

public class ReporteRepository {
	
	private Conexion conexion;
	
	public ReporteRepository() {
		this.conexion = new Conexion();
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

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignorado intencionalmente */ }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {  /* Ignorado intencionalmente */ }
            try { if (con != null) con.close(); } catch (SQLException e) { /* Ignorado intencionalmente */ }
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
                    + "and f.fecha_emision <= ? ";
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

        }  finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignorado intencionalmente */ }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {  /* Ignorado intencionalmente */ }
            try { if (con != null) con.close(); } catch (SQLException e) { /* Ignorado intencionalmente */ }
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
                    + "AND f.estado = ?";
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

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignorado intencionalmente */ }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {  /* Ignorado intencionalmente */ }
            try { if (con != null) con.close(); } catch (SQLException e) { /* Ignorado intencionalmente */ }
        }
	}
	
	

}
