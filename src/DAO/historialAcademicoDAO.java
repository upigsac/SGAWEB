/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.historialAcademico;
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


public class historialAcademicoDAO {
    
    
    
    public List<historialAcademico.detalleHistorial> mostrarHistorialAcademico(int institucion, String periodoIni, String periodoFin,String alumno) {

        List<historialAcademico.detalleHistorial> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        historialAcademico.detalleHistorial item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_REPORTE_HISTORIAL (1,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodoIni);
            cs.setString(3, periodoFin);
            cs.setString(4, alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new historialAcademico.detalleHistorial();
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDesPeriodo(rs.getString("DESPERIODO"));
                item.setCredito(rs.getInt("CREDITOS"));
                item.setDesSeccion(rs.getString("DESSECCION"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setPromedio(rs.getInt("PROMEDIO"));
                item.setPromedioSemestral(rs.getDouble("PROMEDIO_SEMESTRAL"));
                

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
    public List<historialAcademico.historialPromedioP> mostrarCuadroResumen(int institucion, String periodoIni, String periodoFin,String alumno) {

        List<historialAcademico.historialPromedioP> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        historialAcademico.historialPromedioP item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_REPORTE_HISTORIAL (2,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodoIni);
            cs.setString(3, periodoFin);
            cs.setString(4, alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new historialAcademico.historialPromedioP();
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDesPeriodo(rs.getString("DESPERIODO"));                
                item.setPromedioPonderado(rs.getDouble("PROMEDIO_SEMESTRAL"));
                

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
