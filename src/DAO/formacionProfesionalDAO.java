/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.formacionProfesionalC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class formacionProfesionalDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<formacionProfesionalC> mostrarformacionProfesional() {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<formacionProfesionalC> lista = new ArrayList<>();
        formacionProfesionalC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.SYS_FORMACION_PROFESIONAL where ESTADO_REGISTRO=1");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new formacionProfesionalC();
                item.setFormacionProfesional(rs.getInt("FORMACION_PROFESIONAL"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
