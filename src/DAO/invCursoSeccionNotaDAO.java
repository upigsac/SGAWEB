/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.notasImnovacion;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.invAlumnoCursoNotaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class invCursoSeccionNotaDAO {
    
    
    public String insertar(invAlumnoCursoNotaC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        String promedio="";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_INV_ALUMNO_CURSO_NOTA  (1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getAlumno());
            cs.setInt(8, item.getGrupo());
            cs.setString(9, item.getTipoExamen());
            cs.setString(10, item.getValor());
            cs.setInt(11, item.getNota());
            cs.setInt(12, item.getEstadoRegistro());
            cs.executeUpdate() ;
            
            rpta=true;
            if (rpta) {                
                c.commit();
               
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return promedio;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     public List<notasImnovacion.detalleAlumno> mostrarCursoSeccionAlumno(int institucion,String periodo,String carrera,String curso,String seccion,int grupo) {

        List<notasImnovacion.detalleAlumno> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        notasImnovacion.detalleAlumno item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INV_MOTRAR_ALUMNO_CURSO_NOTA(?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setInt(6, grupo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new notasImnovacion.detalleAlumno();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setDesAlumno(rs.getString("DESALUMNO"));
                

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     public List<notasImnovacion.detalleNotaImnovacion> mostrarCursoSeccionAlumnoNota(int institucion,String periodo,String carrera,String curso,String seccion,int grupo,String alumno) {

        List<notasImnovacion.detalleNotaImnovacion> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        notasImnovacion.detalleNotaImnovacion item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INV_MOTRAR_ALUMNO_CURSO_NOTA_DETALLE(?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setInt(6, grupo);
            cs.setString(7, alumno);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new notasImnovacion.detalleNotaImnovacion();
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));                
                item.setValor(rs.getString("VALOR"));                
                item.setNota(rs.getInt("NOTA"));                
                item.setPersona(rs.getString("PERSONA")); 
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     
     
     public List<notasImnovacion.detalleTipoExamen> mostrarTipoExamen() {

        List<notasImnovacion.detalleTipoExamen> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        notasImnovacion.detalleTipoExamen item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.TIPO_EXAMEN,B.DESCRIPCION FROM  SIGU.HOR_FORMULA_EXAMEN A,SIGU.HOR_TIPO_EXAMEN B  WHERE A.FORMULA=10 AND A.TIPO_EXAMEN_PADRE='Y0' AND B.TIPO_EXAMEN=A.TIPO_EXAMEN");
          
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new notasImnovacion.detalleTipoExamen();
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setDesTipoExamen(rs.getString("DESCRIPCION"));
                
                
                

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    public List<notasImnovacion.detalleTipoExamenValor> mostrarTipoExamenValor(String tipoExamen) {

        List<notasImnovacion.detalleTipoExamenValor> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        notasImnovacion.detalleTipoExamenValor item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.TIPO_EXAMEN,A.VALOR,A.VALOR_MAXIMO,A.VALOR_MINIMO,BA.PERSONA FROM  DI.INV_VALOR_NOTA A LEFT JOIN  DI.INV_PERSONA_JURADO BA ON BA.INSTITUCION=1 AND BA.PERIODO='20152' AND BA.TIPO_EXAMEN=A.TIPO_EXAMEN WHERE A.TIPO_EXAMEN=? order by VALOR");
          cs.setString(1, tipoExamen);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new notasImnovacion.detalleTipoExamenValor();
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setValor(rs.getString("VALOR"));
                
                
                

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
