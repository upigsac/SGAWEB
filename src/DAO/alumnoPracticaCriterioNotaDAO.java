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
import ENTIDAD.alumnoPracticaCriterioNotaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class alumnoPracticaCriterioNotaDAO {
    public String insertar(alumnoPracticaCriterioNotaC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CURSO_PRACTICA_CRITERIO_NOTA(1,?,?,?,?,?,?,?)}");            
            
            cs.setString(1, item.getAlumno());
            cs.setString(2, item.getCurso());
            cs.setInt(3, item.getCronograma());
            cs.setString(4, util.convertDate(item.getFecha(), "dd-MM-yyyy"));
            cs.setString(5, item.getCriterio());
            cs.setDouble(6, item.getNota());
            cs.setInt(7, item.getEstadoRegistro());

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
    
    
    public List<alumnoPracticaCriterioNotaC> mostrarAlumnoPractica(String alumno,String curso,String criterio) {
        alumnoPracticaCriterioNotaC item=null ;
        List<alumnoPracticaCriterioNotaC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ALUMNO_CURSO_PRACTICA_CRITERIO_NOTA(1,?,?,?)}");            
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            cs.setString(3, criterio);
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new alumnoPracticaCriterioNotaC();
                item.setCronograma(rs.getInt("CRONOGRAMA"));                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setFecha(rs.getDate("FECHA"));
                item.setCriterio(rs.getString("CRITERIO"));
                item.setNota(rs.getDouble("NOTA"));
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
