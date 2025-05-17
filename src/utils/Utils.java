package utils;

import ConexionDB.Conexion;
import exception.*;

public final class Utils {
	
	private static final Conexion conexion = new Conexion();

    private Utils() {
        throw new UnsupportedOperationException("ERROR: No se puede instanciar esta clase.");
    }
    
    public static void verificarWSKey(String WSKey) throws WSKeyNoValidaException {
        String WSKeyDB = conexion.obtenerWSKey();
        
		if (!WSKeyDB.equals(WSKey)){
			throw new WSKeyNoValidaException("ERROR: Operacion no autorizada. ");
		}
		
    }
}
