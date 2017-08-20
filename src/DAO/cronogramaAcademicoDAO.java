/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.cronogramaAcademico;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.cronogramaAcademicoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class cronogramaAcademicoDAO {
    
    public List<cronogramaAcademico.cronogramaVencimiento> mostrarCronogramaVencimiento(int institucion,String periodo,String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademico.cronogramaVencimiento item;
        List<cronogramaAcademico.cronogramaVencimiento> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CRONOGRAMA_ACADEMICO(1,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronogramaAcademico.cronogramaVencimiento();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setTitulo(rs.getString("TITULO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setSemana(rs.getInt("SEMANAS"));
                
                
                
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
        }
        return lista;
    }
    
    public List<cronogramaAcademico.cronogramaVencimiento> mostrarCronogramaVencimientoDocente(int institucion,String periodo,String personal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademico.cronogramaVencimiento item;
        List<cronogramaAcademico.cronogramaVencimiento> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CRONOGRAMA_ACADEMICO(2,?,?,'%',?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronogramaAcademico.cronogramaVencimiento();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setTitulo(rs.getString("TITULO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setSemana(rs.getInt("SEMANAS"));
                
                
                
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
        }
        return lista;
    }
    
    
    
    public List<cronogramaAcademico.cronograma> mostrarFechaCronogramaAcademico(Date fechaInicio,int semana) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademico.cronograma item;
        List<cronogramaAcademico.cronograma> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CRONOGRAMA_ACADEMICO_CABECERA(1,?,?)}");
            cs.setString(1, util.convertDate(fechaInicio, "dd-MM-yyyy") );
            cs.setInt(2, semana);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronogramaAcademico.cronograma();
                item.setSemana(rs.getInt("SEMANA"));
                item.setFechaIni(rs.getDate("FECHA_INI"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
        }
        return lista;
    }
    
    public List<cronogramaAcademicoC> mostrarCronogramaAcademico(int institucion,String periodo,int vencimiento) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademicoC item;
        List<cronogramaAcademicoC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.DOC_CRONOGRAMA_ACADEMICO  WHERE INSTITUCION=? AND PERIODO=? AND VENCIMIENTO=? ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, vencimiento);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronogramaAcademicoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setSemana(rs.getInt("SEMANA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setDuracion(rs.getInt("DURACION"));
                item.setRotacion(rs.getBoolean("ROTACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<cronogramaAcademicoC> mostrarCronogramaAcademico(int institucion,String periodo,int vencimiento,int modulo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademicoC item;
        List<cronogramaAcademicoC> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.DOC_CRONOGRAMA_ACADEMICO  WHERE INSTITUCION=? AND PERIODO=? AND VENCIMIENTO=? AND MODULO=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, vencimiento);
            cs.setInt(4, modulo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronogramaAcademicoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setSemana(rs.getInt("SEMANA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setDuracion(rs.getInt("DURACION"));
                item.setRotacion(rs.getBoolean("ROTACION"));
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
    
    public cronogramaAcademicoC mostrarCronogramaAcademico(int institucion,String periodo,int vencimiento,Date fecha) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademicoC item=null;
   
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM DI.DOC_CRONOGRAMA_ACADEMICO A,DI.DOC_CRONOGRAMA_ACADEMICO_VENCIMIENTO B WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.VENCIMIENTO=?  AND (DATEDIFF(WEEK,B.FECHA_INICIO,?)+1) BETWEEN A.SEMANA AND (A.SEMANA + A.DURACION-1) AND B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO AND B.VENCIMIENTO=A.VENCIMIENTO ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, vencimiento);
            cs.setString(4, util.convertDateString(fecha, "dd-MM-yyyy"));
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronogramaAcademicoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setSemana(rs.getInt("SEMANA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setDuracion(rs.getInt("DURACION"));
                item.setRotacion(rs.getBoolean("ROTACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return item;
    }

}
