/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.cuadroAsistenciaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class cuadroAsistenciaDAO {
    
    
    
    public List<cuadroAsistenciaC> mostrarCuadroAsistencia(int institucion, String periodo,String carrera,String curso,String seccion,String alumno) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cuadroAsistenciaC item=null ;
        List<cuadroAsistenciaC> lista=new ArrayList<>();
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MOSTRAR_ASISTENCIA_ALUMNO](?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("INSTITUCION", institucion);
            cs.setString("PERIODO", periodo);
            cs.setString("CARRERA", carrera);
            cs.setString("CURSO", curso);
            cs.setString("SECCION", seccion);
            cs.setString("ALUMNO", alumno);
            
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cuadroAsistenciaC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));
                item.setSemana(rs.getInt("SEMANA"));
                item.setClase(rs.getInt("CLASE"));
                item.setAsistencia(rs.getInt("ASISTENCIA"));
                item.setPorcentaje(rs.getInt("PORCENTAJE"));
                item.setAlumno(rs.getString("ALUMNO"));
                lista.add(item);
              
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("Error "  + e.getMessage());
        }
        return lista;
    }
}
