/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.tipoHoraC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tipoHoraDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<tipoHoraC> mostrarTipoHora() {

        List<tipoHoraC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoHoraC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_TIPO_HORA WHERE ESTADO_REGISTRO=1");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoHoraC();
                item.setTipoHora(rs.getInt("TIPO_HORA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setDuracion(rs.getInt("DURACION"));                
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
    
    public tipoHoraC mostrarTipoHora(int tipoHora) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoHoraC item=null ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_TIPO_HORA WHERE TIPO_HORA=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, tipoHora);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoHoraC();
                item.setTipoHora(rs.getInt("TIPO_HORA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setDuracion(rs.getInt("DURACION"));                
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
