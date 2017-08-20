/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoAmonestacionC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tipoAmonestacionDAO implements Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public List<tipoAmonestacionC> mostrarTipoAmonestacion() {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoAmonestacionC item ;
        List<tipoAmonestacionC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from DI.SYS_TIPO_AMONESTACION WHERE ESTADO_REGISTRO=1");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoAmonestacionC();
                item.setTipoAmonestacion(rs.getInt("TIPO_AMONESTACION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     
     public tipoAmonestacionC mostrarTipoAmonestacion(int tipo) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoAmonestacionC item = null;
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from DI.SYS_TIPO_AMONESTACION WHERE TIPO_AMONESTACION=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, tipo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoAmonestacionC();
                item.setTipoAmonestacion(rs.getInt("TIPO_AMONESTACION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
