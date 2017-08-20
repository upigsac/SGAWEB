/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.administrarGrupo;
import Beans.docenteTipoExamen;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class docenteTipoExamenDAO {
     public List<docenteTipoExamen.detalleAlumnoGrupo> mostrarDetalleAlumnoGrupo(int institucion,String periodo,String carrera,String malla,String curso,String seccion) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<docenteTipoExamen.detalleAlumnoGrupo> lista=new ArrayList<>();
        docenteTipoExamen.detalleAlumnoGrupo item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_GRUPO_ALUMNOS(?,?,?,?,?,?)}");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new docenteTipoExamen.detalleAlumnoGrupo();
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupoExamen(rs.getInt("GRUPO_EXAMEN"));
                
                
                lista.add(item);
                
                
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
      public List<docenteTipoExamen.detalleAlumnoGrupo> mostrareAlumnoGrupo(int institucion,String periodo,String carrera,String curso,int grupo_examen) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<docenteTipoExamen.detalleAlumnoGrupo> lista=new ArrayList<>();
        docenteTipoExamen.detalleAlumnoGrupo item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_FILTRO_ALUMNO_GRUPO(?,?,?,?,?)}");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);           
            cs.setString(4, curso);
            cs.setInt(5, grupo_examen);
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new docenteTipoExamen.detalleAlumnoGrupo();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setSeccion(rs.getString("SECCION"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
     public List<docenteTipoExamen.detalleAlumnoGrupo> mostrareAlumnoGrupoLibre(int institucion,String periodo,String carrera,String curso,String desPersonal) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<docenteTipoExamen.detalleAlumnoGrupo> lista=new ArrayList<>();
        docenteTipoExamen.detalleAlumnoGrupo item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ALUMNO_GRUPO_LIBRES(?,?,?,?,?)}");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);           
            cs.setString(4, curso);
            cs.setString(5, desPersonal);
          
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new docenteTipoExamen.detalleAlumnoGrupo();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setSeccion(rs.getString("SECCION"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
     
     
     
     
     
     
     
     public List<administrarGrupo.detalleAlumno> mostrarAlumnoGrupoInvestigacionLibre(int institucion,String periodo,String carrera,String curso,String seccion, String desPersonal) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<administrarGrupo.detalleAlumno> lista=new ArrayList<>();
        administrarGrupo.detalleAlumno item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ALUMNO_CURSO_SECCION_GRUPO_LIBRES(?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);           
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, desPersonal);
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new administrarGrupo.detalleAlumno();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setSeccion(rs.getString("SECCION"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
     
      
      
     public List<administrarGrupo.detalleAlumno> mostrarAlumnoGrupoInvestigacion(int institucion,String periodo,String carrera,String curso,String seccion,int grupo) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<administrarGrupo.detalleAlumno> lista=new ArrayList<>();
        administrarGrupo.detalleAlumno item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_FILTRO_ALUMNO_CURSO_SECCION_GRUPO(?,?,?,?,?,?)}");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);           
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setInt(6, grupo);
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new administrarGrupo.detalleAlumno();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setSeccion(rs.getString("SECCION"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
      
}
