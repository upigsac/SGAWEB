/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import ENTIDAD.facultadC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class facultadDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public facultadC mostrarEstadoCodigo(int institucion, String carrera) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        facultadC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" select *from SIGU.ACA_FACULTAD A,SIGU.ACA_FACULTAD_CARRERA B \n"
                    + " WHERE A.INSTITUCION=B.INSTITUCION AND A.FACULTAD=B.FACULTAD AND A.INSTITUCION=? AND B.CARRERA=?");

            cs.setInt(1, institucion);
            cs.setString(2, carrera);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new facultadC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setFalcutad(rs.getInt("FACULTAD"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstado(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return item;
    }

    public List<facultadC> mostrarFacultad(int institucion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<facultadC> lista = new ArrayList<>();
        facultadC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" select *from SIGU.ACA_FACULTAD where INSTITUCION=?");

            cs.setInt(1, institucion);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new facultadC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setFalcutad(rs.getInt("FACULTAD"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstado(rs.getInt("ESTADO_REGISTRO"));
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
