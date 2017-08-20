/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.vencimientoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class vencimientoDAO {
    public List<vencimientoC> mostrarVencimientoGrupo(int institucion,String periodo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        vencimientoC item = null;
        List<vencimientoC> lista=new ArrayList<>();
        
        try {

            c = ConexionWeb();
            
            cs = c.prepareCall("SELECT DISTINCT B.* FROM SIGU.HOR_PERIODO_SECCION A,SIGU.TES_VENCIMIENTO B WHERE B.INSTITUCION=A.INSTITUCION AND B.PERIODO=A.PERIODO  AND B.VENCIMIENTO=A.VENCIMIENTO AND A.INSTITUCION=? AND A.PERIODO=? AND A.ESTADO_REGISTRO=1");
                     
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new vencimientoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
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
    public vencimientoC mostrarVencimiento(int institucion,String periodo,int vencimiento) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        vencimientoC item = null;
       
        
        try {

            c = ConexionWeb();
            
            cs = c.prepareCall("SELECT  * FROM SIGU.TES_VENCIMIENTO  WHERE  INSTITUCION=? AND PERIODO=? AND VENCIMIENTO=? AND  ESTADO_REGISTRO=1");
                     
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, vencimiento);
            rs = cs.executeQuery();
            while (rs.next()) {

                item = new vencimientoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
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
