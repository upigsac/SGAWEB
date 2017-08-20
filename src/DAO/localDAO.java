/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.localC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class localDAO implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public localC mostrarLocal(int local) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        localC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from sigu.LOG_LOCAL WHERE LOCAL=?");
            cs.setInt(1, local);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new localC();
                item.setLocal(rs.getInt("LOCAL"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTelefono(rs.getString("TELEFONO1"));
                item.setUbicacion(rs.getString("UBICACION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
   
   
   public List<localC> filtroLocal(int institucion) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<localC> lista=new ArrayList<>();
        localC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.*FROM SIGU.LOG_LOCAL A,SIGU.LOG_SEDE B WHERE A.LOCAL=B.SEDE AND  B.INSTITUCION=?");
            cs.setInt(1, institucion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new localC();
                item.setLocal(rs.getInt("LOCAL"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTelefono(rs.getString("TELEFONO1"));
                item.setUbicacion(rs.getString("UBICACION"));
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
   
    public List<localC> mostrarLocal() {

      
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        localC item ;
        List<localC> lista=new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select *from sigu.LOG_LOCAL ");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new localC();
                item.setLocal(rs.getInt("LOCAL"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setTelefono(rs.getString("TELEFONO1"));
                item.setUbicacion(rs.getString("UBICACION"));
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
}
