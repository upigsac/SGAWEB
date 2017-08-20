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
import ENTIDAD.examenRespuestaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class examRespuestaDAO {
     public boolean insertar(examenRespuestaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall(" {CALL DI.MANTENIMIENTO_EXAMEN(?,?,?)}");
            cs.setInt(1, item.getRespuesta());
            cs.setString(2, item.getDescripcion());
            cs.setInt(3, item.getEstadoRegistro());
            

            rpta = cs.executeUpdate() == 1 ;
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
        return rpta;
    }
    
    
    public List<examenRespuestaC> mostrarRespuesta() {

        List<examenRespuestaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        examenRespuestaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.EXAM_RESPUESTA");
            
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new examenRespuestaC();
                item.setRespuesta(rs.getInt("RESPUESTA"));
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
    
    
     public List<examenRespuestaC> mostrarRespuesta(int examen,int grupo,int pregunta) {

        List<examenRespuestaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        examenRespuestaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM DI.EXAM_RESPUESTA A ,DI.EXAM_EXAMEN_PREGUNTA_RESPUESTA B WHERE A.RESPUESTA=B.RESPUESTA AND B.EXAMEN=? AND B.GRUPO=? AND B.PREGUNTA=? ORDER BY B.ORDEN");
            cs.setInt(1, examen);
            cs.setInt(2, grupo);
            cs.setInt(3, pregunta);
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new examenRespuestaC();
                item.setRespuesta(rs.getInt("RESPUESTA"));
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
}
