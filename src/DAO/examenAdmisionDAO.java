/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.examenAdmision;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class examenAdmisionDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<examenAdmision.detalle> mostrarEstadoCodigo(int institucion, String periodo, String carrera) {

        List<examenAdmision.detalle> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        examenAdmision.detalle item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_LISTA_POSTULANTES(?,?,?)}");

            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new examenAdmision.detalle();
                item.setAlumno(rs.getString("POSTULANTE"));
                item.setPostulante(rs.getString("PERSONA"));
                item.setAptitud_1(rs.getString("ACTITUD_1"));
                item.setAptitud_2(rs.getString("ACTITUD_2"));
                item.setGrupo_1(rs.getString("MODULO_1"));
                item.setGrupo_2(rs.getString("MODULO_2"));
                item.setGrupo_3(rs.getString("MODULO_3"));
                item.setCulturaGeneral(rs.getString("CULTURA"));
                item.setPromedio(rs.getString("PROMEDIO"));
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

    public boolean insertarPuntuacion(int institucion, int periodo, String carrera, String personal, String area, String nota) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_INSERTAR_PUNTUACION_POSTULANTE(?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, personal);
            cs.setString(5, area);
            cs.setString(6, nota);

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

            e.printStackTrace();
        }
        return rpta;
    }

    public boolean insertarPuntuacionBD(int institucion, int periodo, String carrera) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_INGRESAR_POSTULANTES_BD(?,?,?)}");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);

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

            e.printStackTrace();
        }
        return rpta;
    }
}
