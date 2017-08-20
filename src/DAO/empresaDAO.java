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
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.empresaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class empresaDAO {
    public boolean insertar(empresaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_EMPRESA  (1,?,?,?,?,?,?)}");
            cs.setString(1, item.getEmpresa());
            cs.setString(2, item.getRuc());
            cs.setString(3, item.getRazonSocial());
            cs.setString(4, item.getTelefono());
            cs.setString(5, item.getDireccion());
            cs.setInt(6, item.getEstadoRegistro());

            

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
    
    
    public boolean eliminar(empresaC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_EMPRESA  (2,?,?,?,?,?,?)}");
            cs.setString(1, item.getEmpresa());
            cs.setString(2, item.getRuc());
            cs.setString(3, item.getRazonSocial());
            cs.setString(4, item.getTelefono());
            cs.setString(5, item.getDireccion());
            cs.setInt(6, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
    
    
    public List<empresaC> mostrarEmpresa() {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;    
        List<empresaC> lista=new ArrayList<>();
        empresaC item=null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.DAT_EMPRESA ORDER BY RAZON_SOCIAL ");
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new empresaC();
                item.setEmpresa(rs.getString("EMPRESA"));
                item.setRuc(rs.getString("RUC"));
                item.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                
                
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
    
    
    public empresaC mostrarEmpresa(String ruc) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;           
        empresaC item=null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.DAT_EMPRESA WHERE RUC =?");
            cs.setString(1, ruc);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new empresaC();
                item.setEmpresa(rs.getString("EMPRESA"));
                item.setRuc(rs.getString("RUC"));
                item.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            
                
                
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return item;
    }
    
    public List<empresaC> mostrarFiltroEmpresa(String razonSocial) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;    
        List<empresaC> lista=new ArrayList<>();
        empresaC item=null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 50 * FROM SIGU.DAT_EMPRESA WHERE RAZON_SOCIAL LIKE ? ORDER BY RAZON_SOCIAL ");
            cs.setString(1, razonSocial);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new empresaC();
                item.setEmpresa(rs.getString("EMPRESA"));
                item.setRuc(rs.getString("RUC"));
                item.setRazonSocial(rs.getString("RAZON_SOCIAL"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setDireccion(rs.getString("DIRECCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                
                
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
}
