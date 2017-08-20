/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.frecuenciaC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class frecuenciaDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public frecuenciaC mostrarfrecuencia(int frecuencia) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        frecuenciaC item = null;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_FRECUENCIA WHERE FRECUENCIA=?");
            cs.setInt(1, frecuencia);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new frecuenciaC();
                item.setFrecuencia(rs.getInt("FRECUENCIA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    
     public List<frecuenciaC> mostrarfrecuencia() {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        frecuenciaC item ;
        List<frecuenciaC> lista=new ArrayList<>();

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_FRECUENCIA ");
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new frecuenciaC();
                item.setFrecuencia(rs.getInt("FRECUENCIA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
