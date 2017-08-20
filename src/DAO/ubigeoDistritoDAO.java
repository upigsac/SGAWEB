/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.ubigeoDistritoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class ubigeoDistritoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<ubigeoDistritoC> mostrarDistrito(String departamento, String provincia) {

        List<ubigeoDistritoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ubigeoDistritoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.SYS_UBIGEO_DISTRITO WHERE DEPARTAMENTO=? AND PROVINCIA=?");
            cs.setString(1, departamento);
            cs.setString(2, provincia);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ubigeoDistritoC();
                item.setDepartamento(rs.getString("DEPARTAMENTO"));
                item.setProvincia(rs.getString("PROVINCIA"));
                item.setDistrito(rs.getString("DISTRITO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    public ubigeoDistritoC mostrarDistrito(String departamento, String provincia,String distrito) {

      
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ubigeoDistritoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.SYS_UBIGEO_DISTRITO WHERE DEPARTAMENTO=? AND PROVINCIA=? AND DISTRITO=?");
            cs.setString(1, departamento);
            cs.setString(2, provincia);
            cs.setString(3, distrito);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ubigeoDistritoC();
                item.setDepartamento(rs.getString("DEPARTAMENTO"));
                item.setProvincia(rs.getString("PROVINCIA"));
                item.setDistrito(rs.getString("PROVINCIA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setLatitud(rs.getDouble("LATITUD"));
                item.setLongitud(rs.getDouble("LONGITUD"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

              

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
    
    public ubigeoDistritoC mostrarUbigeo(String ubigeo) {

      
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ubigeoDistritoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_UBIGEO(?)}");
            cs.setString(1, ubigeo);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ubigeoDistritoC();
                item.setDepartamento(rs.getString("DEPARTAMENTO"));
                item.setProvincia(rs.getString("PROVINCIA"));
                item.setDistrito(rs.getString("PROVINCIA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));

              

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
    
    
    
}
