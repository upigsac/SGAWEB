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
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.encuestaCursoSeccionDocenteC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class encuestaCursoSeccionDocenteDAO {
    
    public boolean ingreso(encuestaCursoSeccionDocenteC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_ENCUESTA_CURSO_SECCION_DOCENTE  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getGrupo());
            cs.setString(8, item.getPersonal());
            cs.setString(9, item.getAlumno());
            cs.setInt(10, item.getEncuesta());
            cs.setInt(11, item.getGrupoEncuesta());
            cs.setInt(12, item.getPregunta());
            cs.setInt(13, item.getRespuesta());
            cs.setInt(14, item.getEstadoRegistro());

            

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
    public List<encuestaCursoSeccionDocenteC> mostrarEncuestaCursoSeccionDocente(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String personal,String alumno) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<encuestaCursoSeccionDocenteC> lista = new ArrayList<>();
        encuestaCursoSeccionDocenteC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ENCUESTA_CURSO_SECCION_DOCENTE WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND PERSONAL=? AND ALUMNO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, personal);
            cs.setString(8, alumno);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaCursoSeccionDocenteC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setPersonal(rs.getString("PERSONAL"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( "error ssss" + ex.getMessage());
 
            //e.printStackTrace();
        }

        return lista;
    }
}
