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

import ENTIDAD.foroPersonaCursoSeccionPreguntaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class foroPersonaCursoSeccionPreguntaDAO {
     public boolean insertar(foroPersonaCursoSeccionPreguntaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_FORO_PERSONA_CURSO_SECCION_PREGUNTA(1,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPregunta());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setString(4, item.getCarrera());
            cs.setString(5, item.getMalla());
            cs.setString(6, item.getCurso());
            cs.setString(7, item.getSeccion());
            cs.setInt(8, item.getTipoPersona());
            cs.setString(9, item.getPersona());
            cs.setString(10, item.getMensaje());
            cs.setInt(11, item.getEstadoRegistro());  
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rpta;
    }
     
     
      public boolean eliminar(foroPersonaCursoSeccionPreguntaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_FORO_PERSONA_CURSO_SECCION_PREGUNTA(2,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPregunta());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setString(4, item.getCarrera());
            cs.setString(5, item.getMalla());
            cs.setString(6, item.getCurso());
            cs.setString(7, item.getSeccion());
            cs.setInt(8, item.getTipoPersona());
            cs.setString(9, item.getPersona());
            cs.setString(10, item.getMensaje());
            cs.setInt(11, item.getEstadoRegistro());    
            
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return rpta;
    }
     
     
     public List<foroPersonaCursoSeccionPreguntaC> mostrarPregunta(int institucion,String periodo,String carrera,String malla,String curso,String seccion) {
     
     
        List<foroPersonaCursoSeccionPreguntaC> lista=new ArrayList<>();
        foroPersonaCursoSeccionPreguntaC item ;
        Connection c ;        
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.FORO_PERSONA_CURSO_SECCION_PREGUNTA A,UPA.DAT_PERSONAS B WHERE A.PERSONA=B.PERSONA AND  INSTITUCION LIKE ? AND PERIODO LIKE ? AND CARRERA LIKE ? AND MALLA LIKE ? AND CURSO LIKE ? AND SECCION LIKE ? AND A.ESTADO_REGISTRO=1 ORDER BY A.CREACION_FECHA DESC ");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new foroPersonaCursoSeccionPreguntaC();
                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));                
                item.setMalla(rs.getString("MALLA"));                
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setTipoPersona(rs.getInt("TIPO_PERSONA"));
                item.setPersona(rs.getString("PERSONA"));
                item.setMensaje(rs.getString("MENSAJE"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));
                
                
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
            
        }
        return lista;
    }
}
