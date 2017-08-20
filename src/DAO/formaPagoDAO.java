/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.formaPagoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class formaPagoDAO implements Serializable  {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<formaPagoC> mostrarFormaPago() {
      
       
        Connection c = null;
        CallableStatement cs ;
        ResultSet rs ;
        formaPagoC item;
        List<formaPagoC> lista=new ArrayList<>();
       
        try {
           
          
            c = ConexionWeb();                      
            cs = c.prepareCall("SELECT *FROM SIGU.TES_FORMA_PAGO WHERE ESTADO_REGISTRO=1");
            
            rs = cs.executeQuery();
           
            while (rs.next()) {
                item = new formaPagoC();
                item.setFormaPago(rs.getInt("FORMA_PAGO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                
                
            }
            cerrarCall(cs);
            cerrarConexion(c);
              
        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente(e.getMessage());
        }
        return lista;
    }
}
