/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.administrarCurso;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cursoSeccionPrincipalC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class cursoSeccionPrincipalDAO {
    
    
    public String insertar(cursoSeccionPrincipalC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_CURSO_SECCION_PRINCIPAL(1,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPrincipal());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setString(4, item.getCarrera());
            cs.setString(5, item.getMalla());
            cs.setString(6, item.getCurso());
            cs.setString(7, item.getSeccion());         
            cs.setInt(8, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                util.consolaCliente( "guardo..");
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
    
    public String eliminar(cursoSeccionPrincipalC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_CURSO_SECCION_PRINCIPAL(2,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getPrincipal());
            cs.setInt(2, item.getInstitucion());
            cs.setString(3, item.getPeriodo());
            cs.setString(4, item.getCarrera());
            cs.setString(5, item.getMalla());
            cs.setString(6, item.getCurso());
            cs.setString(7, item.getSeccion());         
            cs.setInt(8, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                util.consolaCliente( "eliminado");
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
    
    
    
    public List<administrarCurso.cursoSeccion> mostrarCursoSeccionP(int institucion,String periodo,String carrera,String curso) {

        List<administrarCurso.cursoSeccion> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        administrarCurso.cursoSeccion item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CURSO_SECCION_PRINCIPAL (?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("PRINCIPAL", 0);
            cs.setInt("INSTITUCION", institucion);
            cs.setString("PERIODO", periodo);
            cs.setString("CARRERA", carrera);
            cs.setString("CURSO", "%");
            cs.setString("SECCION", "%");
            cs.setString("DESCURSO","%" + curso +"%");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new administrarCurso.cursoSeccion();
                item.setPrincipal(rs.getInt("PRINCIPAL"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));                
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setTurno(rs.getString("TURNO"));
                item.setDesTurno(rs.getString("DESTURNO"));
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("ITEMWEB 1 "+e.getMessage());
            
        }
        return lista;
    }
    
    public List<administrarCurso.cursoSeccion> mostrarCursoSeccionS(int principal,int institucion,String periodo ) {

        List<administrarCurso.cursoSeccion> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        administrarCurso.cursoSeccion item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CURSO_SECCION_PRINCIPAL (2,?,?,?)}");
            cs.setInt(1, principal);
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new administrarCurso.cursoSeccion();
                item.setPrincipal(rs.getInt("PRINCIPAL"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));                
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
    
    public List<administrarCurso.cursoSeccion> mostrarCursoSeccionBusqueda(int institucion,String periodo,String filtroCurso  ) {
        System.out.println(filtroCurso);
        List<administrarCurso.cursoSeccion> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        administrarCurso.cursoSeccion item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CURSO_SECCION_PRINCIPAL (3,'',?,?,'','','',?)}");            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, filtroCurso);
            
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new administrarCurso.cursoSeccion();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));                
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setEstado(rs.getString("ESTADO"));
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
