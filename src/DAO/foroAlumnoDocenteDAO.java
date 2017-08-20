/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.usuario;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class foroAlumnoDocenteDAO {
    public List<usuario.foroAlumnoDocente> mostrarCursoAlumno(int institucion,String periodo,String alumno) {
        
        List<usuario.foroAlumnoDocente> lista=new ArrayList<>();
        usuario.foroAlumnoDocente item=null ;
        Connection c ;        
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*,D.PERSONA,D.APELLIDO_PATERNO,D.APELLIDO_MATERNO,D.NOMBRES FROM SIGU.ACA_ALUMNO_CURSO_SECCION A,SIGU.HOR_CURSO_SECCION_DOCENTE B ,SIGU.PER_PERSONAL C,UPA.DAT_PERSONAS D WHERE A.INSTITUCION=? AND  A.PERIODO=?  AND  A.ALUMNO=? AND B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO AND B.CARRERA=A.CARRERA AND B.MALLA=A.MALLA AND B.CURSO=A.CURSO AND B.SECCION=A.SECCION AND B.ESTADO_REGISTRO=1 AND C.PERSONAL=B.PERSONAL AND D.PERSONA=C.PERSONA");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new usuario.foroAlumnoDocente();                
                item.getAlumnoCursoSeccion().setInstitucion(rs.getInt("INSTITUCION"));
                item.getAlumnoCursoSeccion().setPeriodo(rs.getString("PERIODO"));
                item.getAlumnoCursoSeccion().setCarrera(rs.getString("CARRERA"));
                item.getAlumnoCursoSeccion().setMalla(rs.getString("MALLA"));
                item.getAlumnoCursoSeccion().setSeccion(rs.getString("SECCION"));
                item.getAlumnoCursoSeccion().setCurso(rs.getString("CURSO"));
                item.getAlumnoCursoSeccion().setAlumno(rs.getString("ALUMNO"));
                item.getAlumnoCursoSeccion().setGrupo(rs.getString("GRUPO"));
                item.getAlumnoCursoSeccion().setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));            
                
                item.getPersona().setPersona(rs.getString("PERSONA"));
                item.getPersona().setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.getPersona().setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.getPersona().setNombres(rs.getString("NOMBRES"));
                
                lista.add(item);
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return lista;
    }
}
