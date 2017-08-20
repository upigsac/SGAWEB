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
import ENTIDAD.alumnoPracticaActividadC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class alumnoPracticaActividadDAO {
    public List<alumnoPracticaActividadC> mostrarAlumnoPracticaActivida(String alumno,String curso) {
        alumnoPracticaActividadC item=null ;
        List<alumnoPracticaActividadC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA_ACTIVIDAD  WHERE  ALUMNO=? AND CURSO=? order by FECHA");            
            
            cs.setString(1, alumno);
            cs.setString(2, curso);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item=new alumnoPracticaActividadC();
                item.setCronograma(rs.getInt("CRONOGRAMA"));                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCurso(rs.getString("CURSO"));
                item.setDia(rs.getInt("DIA"));
                item.setFecha(rs.getDate("FECHA"));
                item.setActividad(rs.getString("ACTIVIDAD"));
                item.setHoraEntrada(rs.getTimestamp("HORAENTRADA"));
                item.setHoraSalida(rs.getTimestamp("HORASALIDA"));
                item.setHoraInicio(rs.getTimestamp("HORAINICIO"));
                item.setHoraFinal(rs.getTimestamp("HORAFINAL"));
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
