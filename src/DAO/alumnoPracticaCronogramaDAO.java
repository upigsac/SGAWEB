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
import ENTIDAD.alumnoPracticaCronogramaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class alumnoPracticaCronogramaDAO {
    public String insertar(alumnoPracticaCronogramaC item) {     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA(1,?,?,?,?,?,?)}");            
            cs.setInt(1, item.getCronograma());
            cs.setString(2, item.getAlumno());
            cs.setString(3, item.getCurso());
            cs.setString(4, util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
            cs.setString(5, util.convertDate(item.getFechaFinal(), "dd-MM-yyyy"));            
            cs.setInt(6, item.getEstadoRegistro());

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
    
    public String eliminar(alumnoPracticaCronogramaC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA(2,?,?,?,?,?,?)}");            
            cs.setInt(1, item.getCronograma());
            cs.setString(2, item.getAlumno());
            cs.setString(3, item.getCurso());
            cs.setString(4, util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
            cs.setString(5, util.convertDate(item.getFechaFinal(), "dd-MM-yyyy"));            
            cs.setInt(6, item.getEstadoRegistro());

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
    
    
    
    public List<alumnoPracticaCronogramaC> mostrarAlumnoPractica(String alumno,String curso) {
        alumnoPracticaCronogramaC item=null ;
        List<alumnoPracticaCronogramaC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA WHERE  ALUMNO=? AND CURSO=?");            
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new alumnoPracticaCronogramaC();
                item.setCronograma(rs.getInt("CRONOGRAMA"));                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setFechaInicio(rs.getDate("FECHAINICIO"));
                item.setFechaFinal(rs.getDate("FECHAFINAL"));
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
