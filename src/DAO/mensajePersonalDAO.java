/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexiones.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import ENTIDAD.alumnoCarreraCambio;

import ENTIDAD.mensajePersonalC;
import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import Beans.util;


public class mensajePersonalDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<mensajePersonalC> mostrarMensajes() {

        List<mensajePersonalC> lista = new ArrayList<mensajePersonalC>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        mensajePersonalC mp = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT MP.MENSAJE,MP.CUERPO,MP.PIE,MP.FECHA_CADUCIDAD ,\n"
                    + "  (SELECT COUNT(*) FROM DI.MENSAJE_PERSONALIZADO_ALUMNO WHERE MENSAJE= MP.MENSAJE)ESTADO  \n"
                    + "  FROM DI.MENSAJE_PERSONALIZADO MP WHERE MP.ESTADO=1");
            rs = cs.executeQuery();

            while (rs.next()) {

                mp = new mensajePersonalC();
                mp.setMensaje(rs.getString("MENSAJE"));
                mp.setCuerpo(rs.getString("CUERPO"));
                mp.setPie(rs.getString("PIE"));
                mp.setFecha_caducidad(rs.getDate("FECHA_CADUCIDAD"));
                mp.setEstado(rs.getString("ESTADO"));
                lista.add(mp);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            util.consolaCliente( "error " + e.getMessage());
        }
        return lista;
    }

    public void registrarMensaje(String cuerpo, String pie, String caducidad) {

        Connection c = null;
        PreparedStatement pr = null;

        c = ConexionWeb();
        String sql = "INSERT INTO DI.MENSAJE_PERSONALIZADO (CUERPO,PIE,FECHA_CADUCIDAD) VALUES (?,?,?)";

        try {
            pr = c.prepareStatement(sql);
            pr.setString(1, cuerpo);
            pr.setString(2, pie);
            pr.setString(3, caducidad);

            pr.executeUpdate();

            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registrarMensajeAlumno(String mensaje, String carrera, String alumno) {

        Connection c = null;
        PreparedStatement pr = null;

        c = ConexionWeb();
        String sql = "INSERT INTO DI.MENSAJE_PERSONALIZADO_ALUMNO VALUES(?,?,?)";

        try {
            pr = c.prepareStatement(sql);
            pr.setString(1, mensaje);
            pr.setString(2, carrera);
            pr.setString(3, alumno);

            pr.executeUpdate();

            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cambiarEstadoMensajeAlumno(int mensaje) {

        Connection c = null;
        PreparedStatement pr = null;

        c = ConexionWeb();
        String sql = "UPDATE DI.MENSAJE_PERSONALIZADO SET ESTADO=0 WHERE MENSAJE=?";

        try {
            pr = c.prepareStatement(sql);
            pr.setInt(1, mensaje);

            pr.executeUpdate();

            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<alumnoCarreraCambio> filtroAlumnoCarrera(String carrera, int periodo) {

        List<alumnoCarreraCambio> lista = new ArrayList<alumnoCarreraCambio>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        alumnoCarreraCambio acc;

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" SELECT AAP.CARRERA,AAP.ALUMNO,UPPER(DP.APELLIDO_PATERNO+' '+DP.APELLIDO_MATERNO+' '+ DP.NOMBRES) AS NOMBRECOMPLETO\n"
                    + "  FROM SIGU.ACA_ALUMNO_PERIODO AAP ,UPA.ACA_CARRERAS AC,SIGU.SEG_USUARIO_ALUMNO SUA,UPA.DAT_PERSONAS DP\n"
                    + "  WHERE AAP.CARRERA LIKE ?  AND AAP.PERIODO=?  AND AC.CARRERA=AAP.CARRERA \n"
                    + "  AND SUA.USUARIO=AAP.ALUMNO   AND DP.PERSONA=SUA.PERSONA       \n"
                    + "  AND NOT EXISTS(\n"
                    + "  SELECT A.ALUMNO FROM DI.MENSAJE_PERSONALIZADO_ALUMNO A,DI.MENSAJE_PERSONALIZADO B\n"
                    + "  WHERE A.MENSAJE=B.MENSAJE AND B.MENSAJE=1 AND A.ALUMNO=AAP.ALUMNO )\n"
                    + "  UNION\n"
                    + "  SELECT '{TODO}','{TODO}','TODOS'\n"
                    + "  ORDER BY CARRERA,ALUMNO");

            cs.setString(1, "%" + carrera);
            cs.setInt(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {

                acc = new alumnoCarreraCambio(rs.getString("CARRERA"), rs.getString("ALUMNO"), rs.getString("NOMBRECOMPLETO"));
                //acc.setCarrera(rs.getString("CARRERA"));
                //acc.setCodigoAlumno(rs.getString("ALUMNO"));
                //acc.setNombreCompleto(rs.getString("NOMBRECOMPLETO"));

                lista.add(acc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            //util.consolaCliente( "error "+e.getMessage());
        }
        return lista;
    }

    public List<alumnoCarreraCambio> filtroAlumnoCarreraExitentes(String mensaje) {

        List<alumnoCarreraCambio> lista = new ArrayList<alumnoCarreraCambio>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        alumnoCarreraCambio acc;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT AC.CARRERA,MPA.ALUMNO,UPPER(DP.APELLIDO_PATERNO +' ' +DP.APELLIDO_MATERNO +' ' + DP.NOMBRES) AS NOMBRECOMPLETO\n"
                    + "  FROM DI.MENSAJE_PERSONALIZADO_ALUMNO  MPA ,[SIGU].[SEG_USUARIO_ALUMNO] UA,UPA.DAT_PERSONAS DP,UPA.ACA_CARRERAS AC\n"
                    + "  WHERE UA.USUARIO=MPA.ALUMNO AND DP.PERSONA=UA.PERSONA AND AC.CARRERA=MPA.CARRERA  AND MPA.MENSAJE=? \n"
                    + "  ORDER BY 1  ");

            cs.setString(1, mensaje);

            rs = cs.executeQuery();

            while (rs.next()) {

                acc = new alumnoCarreraCambio(rs.getString("CARRERA"), rs.getString("ALUMNO"), rs.getString("NOMBRECOMPLETO"));
                //acc.setCarrera(rs.getString("CARRERA"));
                //acc.setCodigoAlumno(rs.getString("ALUMNO"));
                //acc.setNombreCompleto(rs.getString("NOMBRECOMPLETO"));

                lista.add(acc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            //util.consolaCliente( "error "+e.getMessage());
        }
        return lista;
    }

    public List<ArrayList<String>> listadoAlumosPorMensaje(int mensaje, String carrera) {

        List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();

        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<String> lista2;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.LISTADO_MENSAJE_ALUMNO (?,?)}");
            cs.setInt(1, mensaje);
            cs.setString(2, carrera);
            rs = cs.executeQuery();

            while (rs.next()) {

                lista2 = new ArrayList<String>();
                lista2.add(rs.getString("MENSAJE"));
                lista2.add(rs.getString("ALUMNO"));
                lista2.add(rs.getString("NOMBRECOMPLETO"));
                lista.add(lista2);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            //util.consolaCliente( "error "+e.getMessage());
        }
        return lista;
    }

    public List<ArrayList<String>> listadoAlumosPorMensaje(String mensaje) {

        List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<String> lista2;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT MPA.MENSAJE,AC.CARRERA,AC.DESCRIPCION, MPA.ALUMNO,UA.PERSONA ,DP.APELLIDO_PATERNO,DP.APELLIDO_MATERNO,DP.NOMBRES\n"
                    + "  FROM DI.MENSAJE_PERSONALIZADO_ALUMNO  MPA ,[SIGU].[SEG_USUARIO_ALUMNO] UA,UPA.DAT_PERSONAS DP,UPA.ACA_CARRERAS AC\n"
                    + "  WHERE UA.USUARIO=MPA.ALUMNO AND DP.PERSONA=UA.PERSONA AND AC.CARRERA=MPA.CARRERA  AND MPA.MENSAJE=? \n"
                    + "  ORDER BY 6,7");
            cs.setString(1, mensaje);
            rs = cs.executeQuery();

            while (rs.next()) {

                lista2 = new ArrayList<String>();
                lista2.add(rs.getString("MENSAJE"));
                lista2.add(rs.getString("CARRERA"));
                lista2.add(rs.getString("DESCRIPCION"));
                lista2.add(rs.getString("ALUMNO"));
                lista2.add(rs.getString("PERSONA"));
                lista2.add(rs.getString("APELLIDO_PATERNO"));
                lista2.add(rs.getString("APELLIDO_MATERNO"));
                lista2.add(rs.getString("NOMBRES"));
                lista.add(lista2);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            //e.printStackTrace();
            util.consolaCliente( "error " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminarAlumnoMensajeTodos(String mensaje) {
        Connection c = null;
        CallableStatement cs = null;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM DI.MENSAJE_PERSONALIZADO_ALUMNO WHERE MENSAJE=? ");
            cs.setString(1, mensaje);

            rpta = cs.executeUpdate() == 1 ? true : false;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            System.out.println();

            //e.printStackTrace();
        }
        return rpta;
    }

    public boolean eliminarAlumnoMensaje(String mensaje, String alumno) {
        Connection c = null;
        CallableStatement cs = null;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("DELETE FROM DI.MENSAJE_PERSONALIZADO_ALUMNO WHERE MENSAJE=? AND   ALUMNO=?");
            cs.setString(1, mensaje);
            cs.setString(2, alumno);

            rpta = cs.executeUpdate() == 1 ? true : false;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            System.out.println();

            //e.printStackTrace();
        }
        return rpta;
    }

}
