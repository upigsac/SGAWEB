/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.docenteTipoExamen;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;


import ENTIDAD.cursoSeccionDocenteTipoExamenC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class cursoSeccionDocenteTipoExamenDAO implements Serializable{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String insertar(cursoSeccionDocenteTipoExamenC item) {
        util.consolaCliente(""+ item.getInstitucion());
            util.consolaCliente( item.getPeriodo());
         util.consolaCliente(""+ item.getCarrera());
          util.consolaCliente(""+ item.getMalla());
           util.consolaCliente(""+ item.getCurso());
            util.consolaCliente(""+ item.getSeccion());
             util.consolaCliente(""+ item.getGrupo());
             util.consolaCliente(""+ item.getPersonal());
             util.consolaCliente(""+ item.getTipoExamen());
             util.consolaCliente(""+ item.getGrupoExamen()); 
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MATENIMIENTO_CURSO_SECCION_DOCENTE_TIPO_EXAMEN(?,?,?,?,?,?,?,?,?,?, ?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setInt(7, item.getGrupo());
            cs.setString(8, item.getPersonal());
            cs.setString(9, item.getTipoExamen());
            cs.setInt(10, item.getGrupoExamen());            
            cs.setString(11,util.convertDate(item.getFechaInicio(),"dd-MM-yyyy") ); 
            cs.setString(12,util.convertDate(item.getFechaFin(),"dd-MM-yyyy") );            
            cs.setString(13, item.getCentro());
            cs.setInt(14, item.getEstadoRegistro());
            rpta = cs.executeUpdate() >= 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
    
    
    public List<docenteTipoExamen.grupoTipoExamenDocente> mostrarCursoDocenteTipoExamen(int institucion, String periodo, String carrera,String malla,String curso,int grupoExamen ) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        docenteTipoExamen.grupoTipoExamenDocente item ;
        List<docenteTipoExamen.grupoTipoExamenDocente> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CURSO_GRUPO_EXAMEN(?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);           
            cs.setInt(6, grupoExamen);
           
            
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new docenteTipoExamen.grupoTipoExamenDocente();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setDesTipoExamen(rs.getString("DESTIPOEXAMEN"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));                
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setCentro(rs.getString("CENTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente("ERROR mostrarCursoSeccionDocenteTipoExamen " +  e.getMessage());
           
        }
        return lista;
    }
    
    public List<cursoSeccionDocenteTipoExamenC> mostrarCursoSeccionDocenteTipoExamen(int institucion, String periodo, String carrera,String malla,String curso,String seccion ,int grupo,int grupoExamen, String tipoExamen) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionDocenteTipoExamenC item ;
        List<cursoSeccionDocenteTipoExamenC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION LIKE ? AND GRUPO=? AND  GRUPO_EXAMEN=?  AND TIPO_EXAMEN=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setInt(7, grupo);
            cs.setInt(8, grupoExamen);
            cs.setString(9, tipoExamen);
            
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteTipoExamenC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));                
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setCentro(rs.getString("CENTRO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente("ERROR mostrarCursoSeccionDocenteTipoExamen " +  e.getMessage());
           
        }
        return lista;
    }
    
    public List<cursoSeccionDocenteTipoExamenC> mostrarCursoSeccionDocenteTipoExamenGrupo(int institucion, String periodo,String personal, String carrera,String seccion,String curso , String tipoExamen) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cursoSeccionDocenteTipoExamenC item ;
        List<cursoSeccionDocenteTipoExamenC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall
     ("SELECT *FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND PERSONAL=? AND CARRERA=? AND   SECCION like ? AND CURSO=?  AND TIPO_EXAMEN=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            cs.setString(4, carrera);            
            cs.setString(5, seccion);
            cs.setString(6, curso);            
            cs.setString(7, tipoExamen);
            
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cursoSeccionDocenteTipoExamenC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));
                
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setCentro(rs.getString("CENTRO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
           
        }
        return lista;
    }
    
    
    public boolean mostrarOpcion(int institucion, String periodo,String personal) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        
       
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND PERSONAL=? ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            
            
            rs = cs.executeQuery();

            while (rs.next()) {

                return true;

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
           
        }
        return false;
    }
}
