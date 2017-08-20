/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.examenVirtual;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.examPreguntaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class examPreguntaDAO {
    
    
     public List<examPreguntaC> mostrarPregunta(int examen ,int grupo) {

        List<examPreguntaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        examPreguntaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM DI.EXAM_PREGUNTA A,DI.EXAM_EXAMEN_GRUPO_PREGUNTA B WHERE A.PREGUNTA=B.PREGUNTA AND B.EXAMEN=? AND B.GRUPO=? ORDER BY B.ORDEN");
            cs.setInt(1, examen);
            cs.setInt(2, grupo);
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new examPreguntaC();
                item.setPregunta(rs.getInt("PREGUNTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     
     public examenVirtual.detallePregunta mostrarDetallePregunta(int examen ) {

       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        examenVirtual.detallePregunta item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT COUNT(PREGUNTA)TOTAL_PREGUNTAS,SUM(MINUTOS)TOTAL_MINUTOS  FROM DI.EXAM_EXAMEN_GRUPO_PREGUNTA WHERE EXAMEN=?");
            cs.setInt(1, examen);
         
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new examenVirtual.detallePregunta();
                item.setTotal_preguntas(rs.getInt("TOTAL_PREGUNTAS"));                
                
                item.setTotal_minutos(rs.getInt("TOTAL_MINUTOS"));
                
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
