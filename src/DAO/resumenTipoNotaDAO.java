/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.resumenTipoNota;
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


public class resumenTipoNotaDAO {
    public List<resumenTipoNota.cabecera> mostrarCabecera(int institcion,String periodo,String carrera,String tipoExamen) {
        List<resumenTipoNota.cabecera> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        resumenTipoNota.cabecera item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_DETALLE_TIPO_NOTA_DETALLADO (?,?,?,?)}");
            cs.setInt(1, institcion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, tipoExamen);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new resumenTipoNota.cabecera();
                
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDesperiodo(rs.getString("DESPERIODO"));
                item.setDescarrera(rs.getString("DESCARRERA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesTurno(rs.getString("DESTURNO"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setMatriculados(rs.getInt("MATRICULADOS"));
                item.setAprobados(rs.getInt("APROBADOS"));
                item.setDesaprobados(rs.getInt("DESAPROBADO"));
                item.setTotalPresente(rs.getInt(21));
                item.setTotalFaltas(rs.getInt(20));
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
           // e.printStackTrace();
        }
        return lista;
    }
    
    
    public List<resumenTipoNota.detalle> mostrarDetalle(int institucion,String periodo,String carrera,String curso,String seccion,String tipoExamen ) {
        List<resumenTipoNota.detalle> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        resumenTipoNota.detalle item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MOSTRAR_DETALLE_TIPO_NOTA (?,?,?,?,?,?)}");
            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, tipoExamen);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new resumenTipoNota.detalle();
                
                item.setAlumno(rs.getString("ALUMNO"));
                item.setCodigo(rs.getString("CODIGO"));
                item.setNota(rs.getInt("NOTA"));
                item.setEstadoNota(rs.getString("ESTADONOTA"));
                item.setPresente(rs.getString("ASISTIO"));
                item.setFalta(rs.getString(21));                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
           // e.printStackTrace();
        }
        return lista;
    }
}
