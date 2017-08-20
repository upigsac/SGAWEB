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

import ENTIDAD.practicaCriterioC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class practicaCriterioDAO {
    public List<practicaCriterioC> mostrarPracticaCriterioAlumno(String alumno,String curso) {
        practicaCriterioC item=null ;
        List<practicaCriterioC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM DI.ACA_ALUMNO_CURSO_PRACTICA_CRITERIO A,DI.PRA_PRACTICA_CRITERIO B WHERE ALUMNO=? AND CURSO=? AND B.CRITERIO=A.CRITERIO");            
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new practicaCriterioC();
                item.setCriterio(rs.getString("CRITERIO"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
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
