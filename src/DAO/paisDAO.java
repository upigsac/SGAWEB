/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.paisC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class paisDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<paisC> mostrarPais() {
      
        List<paisC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        paisC item;
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *from UPA.SYS_PAISES");
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new paisC();
                item.setPais(rs.getInt("PAIS"));
                item.setNacionalidad(rs.getString("NACIONALIDAD"));
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
}
