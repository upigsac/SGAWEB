/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.modalidadIngresoC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class modalidadIngresoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<modalidadIngresoC> mostrarModalidadIngreso() {

        Connection c ;
        CallableStatement cs;
        ResultSet rs ;

        modalidadIngresoC item ;
        List<modalidadIngresoC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_MODALIDAD_INGRESO WHERE ESTADO_REGISTRO=1");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new modalidadIngresoC();
                item.setModalidad(rs.getInt("MODALIDAD"));
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

    public modalidadIngresoC mostrarModalidadIngreso(int modalidad) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        modalidadIngresoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_MODALIDAD_INGRESO WHERE MODALIDAD=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, modalidad);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new modalidadIngresoC();
                item.setModalidad(rs.getInt("MODALIDAD"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

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
