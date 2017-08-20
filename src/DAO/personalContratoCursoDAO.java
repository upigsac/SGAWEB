/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Beans.util;

import Beans.contrato.contratoCursoPersonalC;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.carrerasC;
import ENTIDAD.cursosC;
import ENTIDAD.personalContratoCursosC;
import ENTIDAD.seccionC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class personalContratoCursoDAO {
    public boolean insertar(personalContratoCursosC item) {
         
        
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PER_CONTRATO_CURSOS (?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("CONTRATO", item.getContrato());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());            
            cs.setString("CARRERA", item.getCarrera());
            cs.setString("MALLA", item.getMalla());
            cs.setString("SECCION", item.getSeccion());
            cs.setString("CURSO", item.getCurso());            
            cs.setString("FECHA_INICIO", util.convertDate( item.getFechaInicio(),"dd-MM-yyyy"));            
            cs.setString("FECHA_FINAL", util.convertDate( item.getFechaFinal(),"dd-MM-yyyy"));            
            cs.setString("OBSERVACION", item.getObservacion());            
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
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
     public boolean eliminar(personalContratoCursosC item) {
         
        
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PER_CONTRATO_CURSOS (?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("CONTRATO", item.getContrato());
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());            
            cs.setString("CARRERA", item.getCarrera());
            cs.setString("MALLA", item.getMalla());
            cs.setString("SECCION", item.getSeccion());
            cs.setString("CURSO", item.getCurso());            
            cs.setString("FECHA_INICIO", util.convertDate( item.getFechaInicio(),"dd-MM-yyyy"));            
            cs.setString("FECHA_FINAL", util.convertDate( item.getFechaFinal(),"dd-MM-yyyy"));            
            cs.setString("OBSERVACION", item.getObservacion());            
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
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
    
    
   
    
    public personalContratoCursosC mostrarContratoCursoPersonal(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String personal) {

   
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        personalContratoCursosC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_CONTRATO_CURSOS WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND PERSONAL=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, personal);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personalContratoCursosC();
                item.setContrato(rs.getInt("CONTRATO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setInstitucion(rs.getInt("INSTITUCION"));              
                item.setPeriodo(rs.getString("PERIODO"));              
                item.setCarrera(rs.getString("CARRERA"));               
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFinal(rs.getDate("FECHA_FINAL"));
                item.setObservacion(rs.getString("OBSERVACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
         

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            System.out.println( e.getMessage());
        }
        return item;
    }
    
    
    public List<contratoCursoPersonalC> mostrarCursoSeccionDocenteContrato(int institucion, String periodo, String personal) {
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        contratoCursoPersonalC item;
        List<contratoCursoPersonalC> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select A.INSTITUCION,A.PERIODO,A.CARRERA,B.DESCRIPCION DESCARRERA,A.MALLA,A.CURSO,C.DESCRIPCION DESCURSO,A.SECCION,D.DESCRIPCION DESSECCION ,A.GRUPO,A.PERSONAL,A.HORAS_TEORIA,A.HORAS_PRACTICA,A.PRINCIPAL,ISNULL(AA.ESTADO_REGISTRO,59) ESTADO_REGISTRO FROM SIGU.HOR_CURSO_SECCION_DOCENTE A LEFT JOIN DI.PER_CONTRATO_CURSOS AA ON AA.INSTITUCION=A.INSTITUCION AND AA.PERIODO=A.PERIODO AND AA.CARRERA=A.CARRERA AND AA.MALLA=A.MALLA AND AA.CURSO=A.CURSO AND AA.SECCION=A.SECCION AND AA.PERSONAL=A.PERSONAL ,UPA.ACA_CARRERAS B,UPA.ACA_CURSOS C,SIGU.HOR_SECCION D WHERE  A.INSTITUCION=?  AND A.PERIODO  LIKE ? AND A.PERSONAL=? AND A.ESTADO_REGISTRO IN(1,59) AND B.CARRERA=A.CARRERA AND C.CURSO=A.CURSO AND D.INSTITUCION=A.INSTITUCION AND  D.SECCION=A.SECCION");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new contratoCursoPersonalC();
                item.getCursoSeccionDocente().setInstitucion(rs.getInt("INSTITUCION"));
                item.getCursoSeccionDocente().setPeriodo(rs.getString("PERIODO"));
                item.getCursoSeccionDocente().setGrupo(rs.getString("GRUPO"));
                item.getCursoSeccionDocente().setCarrera(rs.getString("CARRERA"));
                item.getCursoSeccionDocente().setMalla(rs.getString("MALLA"));
                item.getCursoSeccionDocente().setCurso(rs.getString("CURSO"));
                item.getCursoSeccionDocente().setSeccion(rs.getString("SECCION"));
                item.getCursoSeccionDocente().setHora_practica(rs.getInt("HORAS_PRACTICA"));
                item.getCursoSeccionDocente().setHora_teoria(rs.getInt("HORAS_TEORIA"));
                item.getCursoSeccionDocente().setPersonal(rs.getString("PERSONAL"));
                item.getCursoSeccionDocente().setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                item.setCarrera(new carrerasC(rs.getString("CARRERA"), rs.getString("DESCARRERA"), null, null, 1));
                item.setCurso(new cursosC(rs.getString("CURSO"), rs.getString("DESCURSO"), null, 1));
                item.setSeccion(new seccionC(rs.getInt("INSTITUCION"), rs.getString("SECCION"), rs.getString("DESSECCION"), 1));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return lista;
    }
    
    
}
