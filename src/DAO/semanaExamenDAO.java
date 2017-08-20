/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.util;

import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.semanaExamenC;
import java.util.ArrayList;
import java.util.List;

public class semanaExamenDAO implements Serializable {
    
    
    
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


public String insertar(semanaExamenC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_SEMANA_EXAMEN(1,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setInt(3, item.getGrupo());            
            cs.setInt(4, item.getItem());            
            cs.setString(5, item.getTipoExamen());
            cs.setBoolean(6, item.isSuspension());
            cs.setString(7, util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
            cs.setString(8, util.convertDate(item.getFechaFinal(), "dd-MM-yyyy") );
            cs.setInt(9, item.getSemanaLibre());
            cs.setString(10, item.getDescripcion());
            cs.setInt(11, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
public String eliminar(semanaExamenC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_SEMANA_EXAMEN(2,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setInt(3, item.getGrupo());            
            cs.setInt(4, item.getItem());            
            cs.setString(5, item.getTipoExamen());
            cs.setBoolean(6, item.isSuspension());
            cs.setString(7, util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));
            cs.setString(8, util.convertDate(item.getFechaFinal(), "dd-MM-yyyy") );
            cs.setInt(9, item.getSemanaLibre());
            cs.setString(10, item.getDescripcion());
            cs.setInt(11, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }

    public semanaExamenC mostrarSemanaExamenes(int institucion, String periodo, int grupo, String tipo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        semanaExamenC item =null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_SEMANA_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND GRUPO=? AND TIPO_EXAMEN=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, grupo);
            cs.setString(4, tipo);

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new semanaExamenC();

                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFinal(rs.getDate("FECHA_FIN"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setSuspension(rs.getBoolean("SUSPENSION_CLASE"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("erroress" + e.getMessage());
            
        }
        return item;
    }
    
    
    public List<semanaExamenC> mostrarSemanaExamenes(int institucion, String periodo, int grupo) {
      
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        semanaExamenC item =null;
        List<semanaExamenC> lista=new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_SEMANA_EXAMEN WHERE INSTITUCION=? AND PERIODO=? AND GRUPO=? ORDER BY FECHA_INICIO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, grupo);            
            rs = cs.executeQuery();            
            while (rs.next()) {
                item = new semanaExamenC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFinal(rs.getDate("FECHA_FIN"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setSuspension(rs.getBoolean("SUSPENSION_CLASE"));
                item.setSemanaLibre(rs.getInt("SEMANA_LIBRE"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
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
    
    
     public semanaExamenC mostrarSemanaExamen(int institucion, String periodo, String carrera, String seccion,String tipoExamen) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        semanaExamenC item =null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT b.* FROM  SIGU.HOR_PERIODO_SECCION A, DI.SYS_SEMANA_EXAMEN B WHERE A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA LIKE ? AND A.SECCION LIKE ? AND B.TIPO_EXAMEN LIKE ? AND B.ESTADO_REGISTRO=1  AND B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO AND B.GRUPO=A.VENCIMIENTO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            cs.setString(5, tipoExamen);

            rs = cs.executeQuery();
            while (rs.next()) {

                item = new semanaExamenC();

                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFinal(rs.getDate("FECHA_FIN"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setSuspension(rs.getBoolean("SUSPENSION_CLASE"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("erroress" + e.getMessage());
            
        }
        return item;
    }

}
