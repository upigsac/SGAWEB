/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.tipoMonedaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import java.io.Serializable;


public class tipoMonedaDAO implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<tipoMonedaC> mostrarTipoMoneda() {        
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        tipoMonedaC item = null;
        List<tipoMonedaC> lista=new ArrayList<tipoMonedaC>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.SYS_TIPOS_MONEDAS");
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoMonedaC();
                item.setTipoMoneda(rs.getInt("TIPO_MONEDA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
