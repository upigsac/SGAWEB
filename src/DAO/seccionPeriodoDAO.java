/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.seccionPeriodoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


public class seccionPeriodoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public seccionPeriodoC mostrarSeccionPeriodo(int institucion, String periodo, String carrera, String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionPeriodoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_PERIODO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA = ? AND SECCION = ? AND ESTADO_REGISTRO NOT IN(0)");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new seccionPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setTurno(rs.getInt("TURNO"));
                item.setFechaInicio(rs.getDate("FEC_CLASES_INI"));
                item.setFechaFin(rs.getDate("FEC_CLASES_FIN"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setVacantes(rs.getInt("VACANTES"));
                item.setSede(rs.getInt("SEDE"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

        }
        return item;
    }

    public List<seccionPeriodoC> mostrarSeccionPeriodo(int institucion, String periodo, String carrera) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionPeriodoC item ;
        List<seccionPeriodoC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_PERIODO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA like ?  AND  ESTADO_REGISTRO IN (40,42) ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new seccionPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setTurno(rs.getInt("TURNO"));
                item.setFechaInicio(rs.getDate("FEC_CLASES_INI"));
                item.setFechaFin(rs.getDate("FEC_CLASES_FIN"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setVacantes(rs.getInt("VACANTES"));
                item.setFrecuencia(rs.getInt("FRECUENCIA"));
                item.setSede(rs.getInt("SEDE"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

        }
        return lista;
    }
    
     public String cantVacantes(int institucion, String periodo, String carrera,String seccion) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        String vacantes="";
       

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT COUNT(distinct ALUMNO)VACANTES FROM SIGU.ACA_ALUMNO_CURSO_SECCION WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND SECCION=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                
                vacantes=rs.getString("VACANTES");
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

        }
        return vacantes;
    }
     
     
     public List<seccionPeriodoC> mostrarReservaSeccion(int institucion, String periodo, String alumno) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionPeriodoC item ;
        List<seccionPeriodoC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{call DI.SP_RESERVA_SECCION(?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new seccionPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setTurno(rs.getInt("TURNO"));
                item.setFechaInicio(rs.getDate("FEC_CLASES_INI"));
                item.setFechaFin(rs.getDate("FEC_CLASES_FIN"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setVacantes(rs.getInt("VACANTES"));
                item.setFrecuencia(rs.getInt("FRECUENCIA"));
                item.setSede(rs.getInt("SEDE"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

        }
        return lista;
    }
     
     
     
     public List<seccionPeriodoC> mostrarSeccionPeriodo(int institucion, String periodo) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        seccionPeriodoC item ;
        List<seccionPeriodoC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.HOR_PERIODO_SECCION WHERE INSTITUCION=? AND PERIODO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new seccionPeriodoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setTurno(rs.getInt("TURNO"));
                item.setFechaInicio(rs.getDate("FEC_CLASES_INI"));
                item.setFechaFin(rs.getDate("FEC_CLASES_FIN"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setVacantes(rs.getInt("VACANTES"));
                item.setFrecuencia(rs.getInt("FRECUENCIA"));
                item.setSede(rs.getInt("SEDE"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {

        }
        return lista;
    }
     
     
     public List<ArrayList<String>> cantSemanas(String institucion, String periodo,String carrera,String seccion) {

        List<ArrayList<String>> semanas = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DATEDIFF(WEEK,FEC_CLASES_INI,FEC_CLASES_FIN) SEMANAS FROM SIGU.HOR_PERIODO_SECCION WHERE  INSTITUCION LIKE ? AND PERIODO=? AND CARRERA=? AND SECCION=? ");

            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {

                for (int i = 1; i <= rs.getInt("SEMANAS"); i++) {
                    ArrayList<String> array = new ArrayList<>();
                    array.add("SEMANA " + i);
                    array.add("SEMANA_" + i);
                    semanas.add(array);
                }

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
           
        }
        return semanas;
    }
     
     
     
     public List<HashMap<String,String>> horarioCarreraSeccion(int institucion, String periodo, String carrera,String turno) {
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
    
        ResultSetMetaData rss ;  
        Map<String,String> map = new HashMap<>();
        List<HashMap<String,String>> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_HORARIO_CARRERA_PERIODO   (?, ?, ?, ?)} ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, turno);
            
            

            rs = cs.executeQuery();
            rss = rs.getMetaData();            
              while (rs.next()) {
                map = new HashMap<>();
                for (int x = 0; x < rss.getColumnCount(); x++) {
                    util.consolaCliente(rss.getColumnLabel(x+1).toString().toLowerCase() +' '+ rs.getString(rss.getColumnLabel(x+1).toString()));
                    map.put(rss.getColumnLabel(x+1).toString().toLowerCase(), rs.getString(rss.getColumnLabel(x+1).toString()));
                }
               lista.add((HashMap<String, String>) map);
                
            }

            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error docente_nota xx " + e.getMessage());
            JOptionPane.showMessageDialog(null,"error en los alumno"+ e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
}
