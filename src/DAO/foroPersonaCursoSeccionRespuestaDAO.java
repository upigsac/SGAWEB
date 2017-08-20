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


import ENTIDAD.foroPersonaCursoSeccionRespuestaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class foroPersonaCursoSeccionRespuestaDAO {
    
    
     public boolean insertar(foroPersonaCursoSeccionRespuestaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_FORO_PERSONA_CURSO_SECCION_RESPUESTA(1,?,?,?,?,?,?,?,?,?,?,?,?)}");            
            cs.setInt(1, item.getPregunta());
            cs.setInt(2, item.getRespuesta());
            cs.setInt(3, item.getInstitucion());
            cs.setString(4, item.getPeriodo());
            cs.setString(5, item.getCarrera());
            cs.setString(6, item.getMalla());
            cs.setString(7, item.getCurso());
            cs.setString(8, item.getSeccion());
            cs.setInt(9, item.getTipoPersona());
            cs.setString(10, item.getPersona());
            cs.setString(11, item.getMensaje());
            cs.setInt(12, item.getEstadoRegistro());    
            
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
     
     
     public boolean eliminar(foroPersonaCursoSeccionRespuestaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.SP_MANTENIMIENTO_FORO_PERSONA_CURSO_SECCION_RESPUESTA(2,?,?,?,?,?,?,?,?,?,?,?,?)}");            
            cs.setInt(1, item.getPregunta());
            cs.setInt(2, item.getRespuesta());
            cs.setInt(3, item.getInstitucion());
            cs.setString(4, item.getPeriodo());
            cs.setString(5, item.getCarrera());
            cs.setString(6, item.getMalla());
            cs.setString(7, item.getCurso());
            cs.setString(8, item.getSeccion());
            cs.setInt(9, item.getTipoPersona());
            cs.setString(10, item.getPersona());
            cs.setString(11, item.getMensaje());
            cs.setInt(12, item.getEstadoRegistro());   
            
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
    
    
    
    
    public List<foroPersonaCursoSeccionRespuestaC> mostrarRespuesta(int pregunta,int institucion,String periodo,String carrera,String malla,String curso,String seccion) {
        
        List<foroPersonaCursoSeccionRespuestaC> lista=new ArrayList<>();
        foroPersonaCursoSeccionRespuestaC item ;
        Connection c ;        
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.FORO_PERSONA_CURSO_SECCION_RESPUESTA A,UPA.DAT_PERSONAS B WHERE B.PERSONA=A.PERSONA AND PREGUNTA LIKE ? AND INSTITUCION LIKE ? AND PERIODO LIKE ? AND CARRERA LIKE ? AND MALLA LIKE ? AND CURSO LIKE ? AND SECCION LIKE ? AND A.ESTADO_REGISTRO=1");
            
            cs.setInt(1, pregunta);
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            cs.setString(4, carrera);
            cs.setString(5, malla);
            cs.setString(6, curso);
            cs.setString(7, seccion);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new foroPersonaCursoSeccionRespuestaC();
                item.setRespuesta(rs.getInt("RESPUESTA"));
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
                item.setMaterno(rs.getString("APELLIDO_PATERNO"));
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
