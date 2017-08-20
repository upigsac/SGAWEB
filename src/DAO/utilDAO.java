package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexiones.Conexion;

public class utilDAO extends Conexion {
	
	
	public byte[] obtenImagen(String opcion, String codigo) {
        Connection c  ;
        c = ConexionWeb();
        ResultSet rs ;
        PreparedStatement pr  ;
        byte[] buffer =null;
        try {
           
            pr = c.prepareCall("{CALL DI.SP_OBTENER_IMAGEN(?,?)}");
            pr.setString(1, opcion);
            pr.setString(2, codigo);
            rs = pr.executeQuery();
            while (rs.next()) {
                Blob bin = rs.getBlob("IMAGEN");
                if (bin != null) {
                    InputStream inStream = bin.getBinaryStream();
                    int size = (int) bin.length();
                    buffer = new byte[size];
                  
                    try {
                        inStream.read(buffer, 0, size);
                    } catch (IOException ex) {
                    	System.out.println(ex.getMessage());
                    }
                }
            }
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
            return null;
        } finally {
          
        }
        return buffer;
    }
	
	public byte[] obtenerArchivo(String opcion, String codigo) {
        Connection c  ;
        c = ConexionWeb();
        ResultSet rs ;
        PreparedStatement pr  ;
        byte[] buffer =null;
        try {
           
            pr = c.prepareCall("{CALL DI.SP_OBTENER_ARCHIVO(?,?)}");
            pr.setString(1, opcion);
            pr.setString(2, codigo);
            rs = pr.executeQuery();
            while (rs.next()) {
                Blob bin = rs.getBlob("ARCHIVO");
                if (bin != null) {
                    InputStream inStream = bin.getBinaryStream();
                    int size = (int) bin.length();
                    buffer = new byte[size];
                  
                    try {
                        inStream.read(buffer, 0, size);
                    } catch (IOException ex) {
                    	System.out.println(ex.getMessage());
                    }
                }
            }
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
            return null;
        } finally {
          
        }
        return buffer;
    }
}
