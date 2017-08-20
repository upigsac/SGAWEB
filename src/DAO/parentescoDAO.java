/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.parentescoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class parentescoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<parentescoC> mostrarParentesco() {

        List<parentescoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        parentescoC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.DAT_PARENTESCO WHERE ESTADO_REGISTRO=1");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new parentescoC();
                item.setParentesco(rs.getInt("PARENTESCO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            // e.printStackTrace();
        }
        return lista;
    }
    
    public parentescoC mostrarParentesco(int parentesco) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        parentescoC item=null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.DAT_PARENTESCO WHERE PARENTESCO=? AND  ESTADO_REGISTRO=1");
            cs.setInt(1, parentesco);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new parentescoC();
                item.setParentesco(rs.getInt("PARENTESCO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            // e.printStackTrace();
        }
        return item;
    }

}
