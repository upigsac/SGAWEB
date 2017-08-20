/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.asistenciaAlumno;
import ENTIDAD.alumnoCursoAsistenciaC;
import ENTIDAD.registrotdC;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class asistenciaAlumnoDAO {
    public List<asistenciaAlumno.asistenciaCurso> mostrarAsistenciaAlumno(int institucion,String periodo,String carrera,String curso,String seccion,String alumno) {

        List<asistenciaAlumno.asistenciaCurso> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        asistenciaAlumno.asistenciaCurso item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_ASISTENCIA_ALUMNO (?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB",institucion);
            cs.setInt("INSTITUCION",institucion);
            cs.setString("PERIODO", periodo);
            cs.setString("CARRERA", carrera);
            cs.setString("CURSO", curso);
            cs.setString("SECCION", seccion);
            cs.setString("ALUMNO", alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                
                item = new asistenciaAlumno.asistenciaCurso();
                item.setRegistrotd(new registrotdC(rs.getInt("ID"), rs.getInt("INSTITUCION"), rs.getString("PERIODO"), rs.getInt("DIA"), rs.getInt("SEMANA"), rs.getDate("FECHA_INGRESO"), rs.getTimestamp("HOR_ING"), rs.getTimestamp("HOR_SAL"), rs.getString("CPERSONAL"), rs.getString("CARRERA"), rs.getString("CURSO"), rs.getString("SECCION"), rs.getInt("TURNO"),  rs.getString("CICLO"),  rs.getString("AULA"), null, 0, rs.getString("TEMA_DESARROLLADO"), null, null, rs.getInt("TARDANZA"), 0, rs.getInt("TIPO_HORA"), rs.getInt("TIPO_CLASE"), null, rs.getInt("ESTADO_REGISTRO")));
                item.setAlumnoCursoAsistencia(new alumnoCursoAsistenciaC(rs.getInt("INSTITUCION"), rs.getString("PERIODO"), rs.getString("CARRERA"), rs.getString("MALLA"), rs.getString("CURSO"), rs.getString("SECCION"), rs.getString("ALUMNO"), rs.getDate("FECHA"), rs.getBoolean("ASISTENCIA"), rs.getInt("ESTADO_REGISTRO")));

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
