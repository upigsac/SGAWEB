/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.invAlumnoCursoSeccionGrupoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class invAlumnoCursoSeccionGrupoDAO {
    public List<invAlumnoCursoSeccionGrupoC> mostrarAlumnoCursoSeccionGrupo(int institucion,String periodo,String carrera,String malla,String curso,String seccion,int grupo) {

        List<invAlumnoCursoSeccionGrupoC> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs ;
        ResultSet rs ;
        invAlumnoCursoSeccionGrupoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.INV_ALUMNO_CURSO_SECCION_GRUPO WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND MALLA=? AND CURSO=? AND SECCION=? AND GRUPO_EXAMEN=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setInt(7, grupo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new invAlumnoCursoSeccionGrupoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setGrupo(rs.getInt("GRUPO_EXAMEN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
           
         System.out.println(ex.getMessage());
        }
        return lista;
    }
}
