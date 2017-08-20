/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.turnoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class turnoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<turnoC> mostrarTurno() {

        List<turnoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        turnoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.ACA_TURNOS");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new turnoC();
                item.setTurno(rs.getInt("TURNO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }

    public turnoC mostrarTurno(int institucion, String periodo, String seccion) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        turnoC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM  SIGU.HOR_PERIODO_SECCION A,UPA.ACA_TURNOS B WHERE A.INSTITUCION=? AND  A.PERIODO=? AND A.SECCION=? AND A.TURNO=B.TURNO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new turnoC();
                item.setTurno(rs.getInt("TURNO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return item;
    }
    
    
    public List<turnoC> mostrarTurno(int institucion, String periodo, String carrera,String nivelModular) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        turnoC item = null;
        List<turnoC> lista=new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT B.* FROM SIGU.HOR_PERIODO_SECCION A,UPA.ACA_TURNOS B where INSTITUCION=? and PERIODO=? and CARRERA=? AND NIVEL_MODULAR LIKE ? AND B.TURNO=A.TURNO ORDER BY B.TURNO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, nivelModular);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new turnoC();
                item.setTurno(rs.getInt("TURNO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
        return lista;
    }

    public turnoC mostrarTurno(int turno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        turnoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM UPA.ACA_TURNOS WHERE TURNO=?");
            cs.setInt(1, turno);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new turnoC();
                item.setTurno(rs.getInt("TURNO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setHoraInicio(rs.getTime("HORA_INICIO"));
                item.setHoraFin(rs.getTime("HORA_FIN"));
                item.setHoraInicioMarcacion(rs.getTime("HORA_INICIO_MARCACION"));
                item.setHoraFinMarcacion(rs.getTime("HORA_FIN_MARCACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente("error metodo turno: " + e.getMessage());
        }
        return item;
    }
}
