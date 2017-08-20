/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.practicaCalificacion;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.alumnoPracticaCriterioC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class alumnoPracticaCriterioDAO {
    public String insertar(alumnoPracticaCriterioC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CURSO_PRACTICA_CRITERIO(1,?,?,?,?,?)}");            
            
            cs.setString(1, item.getAlumno());
            cs.setString(2, item.getCurso());                        
            cs.setString(3, item.getCriterio());
            cs.setDouble(4, item.getValor());
            cs.setInt(5, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
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
    
    
    public List<alumnoPracticaCriterioC> mostrarAlumnoPracticaCriterio(String alumno,String curso) {
        alumnoPracticaCriterioC item=null ;
        List<alumnoPracticaCriterioC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ACA_ALUMNO_CURSO_PRACTICA_CRITERIO WHERE  ALUMNO=? AND CURSO=?");            
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new alumnoPracticaCriterioC();
                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setCriterio(rs.getString("CRITERIO"));
                item.setValor(rs.getDouble("VALOR"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
            
        }
        return lista;
    }
    public List<practicaCalificacion.alumnoCriterio> mostrarAlumnoCriterio(String alumno,String curso) {
        practicaCalificacion.alumnoCriterio item=null ;
        List<practicaCalificacion.alumnoCriterio> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.CRITERIO,A.DESCRIPCION,B.VALOR,B.ESTADO_REGISTRO FROM DI.PRA_PRACTICA_CRITERIO A LEFT JOIN DI.ACA_ALUMNO_CURSO_PRACTICA_CRITERIO B ON  B.CRITERIO=A.CRITERIO AND B.ALUMNO=? AND CURSO=?");            
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new practicaCalificacion.alumnoCriterio();
                item.setCriterio(rs.getString("CRITERIO"));
                item.setDesCriterio(rs.getString("DESCRIPCION"));
                item.setValor(rs.getDouble("VALOR"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
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
