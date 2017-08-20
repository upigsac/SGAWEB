/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class archivoFtpDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String insertarReporte(String nombre, String dirArchivo) {
        String inserto = "";
        Connection c = null;

        c = ConexionWeb();
        PreparedStatement pr = null;

        String sql = " insert into [SIGU].[SYS_ARCHIVOS_FTP]  (NOMBRE,FTP,ESTADO_REGISTRO) values(?,?,?)  ";
        try {

            pr = c.prepareStatement(sql);
            pr.setString(1, nombre);

            File fichero = new File(dirArchivo);
            FileInputStream streamEntrada = new FileInputStream(fichero);
            pr.setBinaryStream(2, streamEntrada, (int) fichero.length());
            pr.setInt(3, 1);
            if (pr.executeUpdate() == 1) {
                inserto = "true";
            } else {
                inserto = "false";
            }

        } catch (Exception ex) {
            inserto = ex.getMessage();
        } finally {
            try {
                pr.close();
                c.close();
            } catch (SQLException ex) {

            }
        }
        return inserto;
    }

}
