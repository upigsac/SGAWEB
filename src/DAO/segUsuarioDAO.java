/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.segUsuarioC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class segUsuarioDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<segUsuarioC> mostrarSegUsuario(String persona) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        segUsuarioC item ;
        List<segUsuarioC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.SEG_USUARIO  WHERE PERSONA=? ");
            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new segUsuarioC();
                item.setUsuario(rs.getString("USUARIO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setClave(rs.getString("CLAVE"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( "erroress" + e.getMessage());
        }
        return lista;
    }

    public boolean insertaraSegUsuario(segUsuarioC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_SEGUSUARIO (?,?,?,?)}");
            cs.setString(1, item.getUsuario());
            cs.setString(2, item.getPersona());
            cs.setString(3, item.getClave());
            cs.setInt(4, 1);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
          

            //e.printStackTrace();
        }
        return rpta;
    }

}
