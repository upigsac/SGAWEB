/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.registroMarcacionDocente;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class registroMarcacionDocenteDAO {
    public List< registroMarcacionDocente.cabecera> mostrarCabecera(String institucion,String periodo,String personal) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<registroMarcacionDocente.cabecera> lista = new ArrayList<>();
        registroMarcacionDocente.cabecera item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_REGISTRO_MARCACION_DOCENTE_SEMESTRAL (1,?,?,'','','','',?)}");
            
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new registroMarcacionDocente.cabecera();
                item.setDia(rs.getInt("DIA"));
                item.setFecha(rs.getDate("FECHA"));
                item.setItem(rs.getInt("ITEM"));
                item.setFeriado(rs.getBoolean("FERIADO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    
     public List< registroMarcacionDocente.cuerpoHora> mostrarCuerpoHoras(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String personal) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<registroMarcacionDocente.cuerpoHora> lista = new ArrayList<>();
        registroMarcacionDocente.cuerpoHora item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_REGISTRO_MARCACION_DOCENTE_SEMESTRAL (2,?,?,?,?,?,?,?)}");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, personal);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new registroMarcacionDocente.cuerpoHora();
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
                item.setHoraMarcada(rs.getDouble("HORA_MARCADA"));
                item.setHoraProyectada(rs.getDouble("HORA_PROYECTADA"));
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     
     
     public List< registroMarcacionDocente.pie> mostrarPie(String institucion,String periodo,String personal) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<registroMarcacionDocente.pie> lista = new ArrayList<>();
        registroMarcacionDocente.pie item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_REGISTRO_MARCACION_DOCENTE_SEMESTRAL (3,?,?,'','','','',?)}");
            
            cs.setString(1, institucion);
            cs.setString(2, periodo);            
            cs.setString(3, personal);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new registroMarcacionDocente.pie();
                item.setMes(rs.getInt("MES"));
                item.setCantidad(rs.getInt("CANTIDAD"));
                item.setAnio(rs.getString("ANIO"));
                
                
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
