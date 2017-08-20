/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.estadoCivilC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class estadoCivilDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public estadoCivilC mostrarEstadoCivil(int codigo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        estadoCivilC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from UPA.SYS_ESTADO_CIVIL WHERE ESTADO_CIVIL=?");
            cs.setInt(1, codigo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new estadoCivilC();
                item.setEstadoCivil(rs.getInt("ESTADO_CIVIL"));
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

    public List<estadoCivilC> mostrarEstadoCivil() {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<estadoCivilC> lista = new ArrayList<>();
        estadoCivilC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from UPA.SYS_ESTADO_CIVIL ");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new estadoCivilC();
                item.setEstadoCivil(rs.getInt("ESTADO_CIVIL"));
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
