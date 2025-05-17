package ConexionDB;

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

import org.example.www.empresa.EmpresaType;



public class EmpresaRepository {
	private Conexion conexion;
	
	public EmpresaRepository() {
		this.conexion = new Conexion();
	} 
	
	public EmpresaType consultarEmpresa (String email) throws SQLException {
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	con = this.conexion.conectar();
        	String sql = "SELECT * FROM empresas WHERE email = ?";
        	stmt = con.prepareStatement(sql);
        	stmt.setString(1, email);
        	
        	rs = stmt.executeQuery();
        	
            if (!rs.next()) {
                throw new NoSuchElementException("ADVERTENCIA: No existe ninguna empresa con email: " + email);
            }
            
            EmpresaType empresa = new EmpresaType();
            
            empresa.setId(rs.getInt("id"));
            empresa.setUuid(rs.getString("uuid"));
            empresa.setNombre(rs.getString("nombre"));
            empresa.setEmail(rs.getString("email"));
            empresa.setIdentificadorFiscal(rs.getString("identificador_fiscal"));
            empresa.setIdentificadorEmpleador(rs.getString("identificador_empleador"));
            empresa.setIban(rs.getString("iban"));
            empresa.setPais(rs.getString("pais"));
            empresa.setProvincia(rs.getString("provincia"));
            empresa.setLocalidad(rs.getString("localidad"));
            empresa.setDireccionCompletaFacturacion(rs.getString("direccion_completa_facturacion"));
            empresa.setCodigoPostal(rs.getString("codigo_postal"));
            
            return empresa;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
		
	}
}
