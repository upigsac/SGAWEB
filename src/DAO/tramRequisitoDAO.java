/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.tramRequisitoC;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class tramRequisitoDAO {
    
     public String insertar(tramRequisitoC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TRAM_REQUISITO(1,?,?,?,?)}");
            cs.setInt(1, item.getRequisito());
            cs.setString(2, item.getDescripcion());
            cs.setString(3, item.getAbreviatura());
            
            cs.setInt(4, item.getEstadoRegistro());
            
            cs.registerOutParameter(1, Types.INTEGER);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                codigo = cs.getString(1);
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
     public String eliminar(tramRequisitoC item) {
         
         Connection c ;
         CallableStatement cs ;
         boolean rpta ;
         
         try {
             c = ConexionWeb();
             cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TRAM_REQUISITO(2,?,?,?,?)}");
             cs.setInt(1, item.getRequisito());
             cs.setString(2, item.getDescripcion());
             cs.setString(3, item.getAbreviatura());             
             cs.setInt(4, item.getEstadoRegistro());             
            
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
         return "";
     }
    
    
    public List<tramRequisitoC> mostrarRequisito(int tramite) {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.REQUISITO,A.DESCRIPCION,A.ABREVIATURA,B.ESTADO_REGISTRO FROM DI.TRAM_REQUISITO A ,DI.TRAM_TRAMITE_REQUISITO B WHERE  A.REQUISITO=B.REQUISITO AND B.TRAMITE=?  ORDER BY A.DESCRIPCION");
            cs.setInt(1, tramite);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
    
    
    public List<tramRequisitoC> mostrarTodoRequisito() {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.TRAM_REQUISITO WHERE ESTADO_REGISTRO=1 ORDER BY DESCRIPCION");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION").toUpperCase());                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
    
    
    public List<tramRequisitoC> mostrarRequisitoEstado(int tramite) {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.* FROM DI.TRAM_REQUISITO A ,DI.TRAM_TRAMITE_REQUISITO B WHERE  A.REQUISITO=B.REQUISITO AND B.TRAMITE=? AND B.ESTADO_REGISTRO=1");
            cs.setInt(1, tramite);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
    
    public List<tramRequisitoC> filtroRequisito(String descripcion) {
        
        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.TRAM_REQUISITO WHERE DESCRIPCION LIKE ? ORDER BY DESCRIPCION ");
            
            cs.setString(1,  descripcion + "%");
            rs = cs.executeQuery();

            while (rs.next()) {
                
                item = new tramRequisitoC();
                
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION").toUpperCase());                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "FILTRO REQUISITO " + e.getMessage());
        }
        return lista;
    }
    
     public List<tramRequisitoC> filtroRequisito(String tramite ,String descripcion) {
        
        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.TRAM_REQUISITO A WHERE A.DESCRIPCION LIKE '%'+ ? + '%' AND NOT EXISTS(SELECT *FROM DI.TRAM_TRAMITE_REQUISITO WHERE TRAMITE like ? AND REQUISITO=A.REQUISITO) ORDER BY DESCRIPCION ");           
            
            cs.setString(1,  descripcion);
            cs.setString(2,tramite);
            rs = cs.executeQuery();

            while (rs.next()) {
                
                item = new tramRequisitoC();
                
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION").toUpperCase());                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "FILTRO REQUISITO " + e.getMessage());
        }
        return lista;
    }
    
    
    public List<tramRequisitoC> mostrarRequisitoEntregados(int tramite ,String expediente) {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.REQUISITO,A.DESCRIPCION,A.ABREVIATURA,ISNULL(C.ESTADO_REGISTRO,0) ESTADO_REGISTRO FROM DI.TRAM_REQUISITO A,DI.TRAM_TRAMITE_REQUISITO B LEFT JOIN DI.TRAM_REQUISITOS_ENTREGADOS C ON C.REQUISITO=B.REQUISITO AND C.EXPEDIENTE=? AND C.ESTADO_REGISTRO=1 WHERE A.REQUISITO=B.REQUISITO AND B.TRAMITE=?");
            cs.setString(1, expediente);
            cs.setInt(2, tramite);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
    
    
    public List<tramRequisitoC> mostrarRequisitoEntregado(String expediente,String entregado) {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM DI.TRAM_REQUISITOS_ENTREGADOS A,DI.TRAM_REQUISITO B WHERE A.EXPEDIENTE=? AND A.ENTREGADO LIKE ? AND B.REQUISITO=A.REQUISITO ORDER BY DESCRIPCION");
            cs.setString(1, expediente);
            cs.setString(2, entregado);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public List<tramRequisitoC> mostrarRequisito(String expediente) {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select B.REQUISITO,B.DESCRIPCION,B.ABREVIATURA,ISNULL(BA.ESTADO_REGISTRO,0)ESTADO_REGISTRO\n" +
                                " from DI.TRAM_TRAMITE_PERSONA A,DI.TRAM_REQUISITO B LEFT JOIN DI.TRAM_REQUISITOS_ENTREGADOS BA \n" +
                                "ON BA.REQUISITO=B.REQUISITO AND BA.EXPEDIENTE=?\n" +
                                ",DI.TRAM_TRAMITE_REQUISITO C\n" +
                                "WHERE A.EXPEDIENTE=? AND C.TRAMITE=A.TRAMITE AND B.REQUISITO=C.REQUISITO");
            cs.setString(1, expediente);
            cs.setString(2, expediente);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
    
    public List<tramRequisitoC> mostrarRequisitoExpediente(String expediente) {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select B.*from DI.TRAM_REQUISITOS_ENTREGADOS A, DI.TRAM_REQUISITO B where B.REQUISITO=A.REQUISITO AND  A.EXPEDIENTE=?");
            cs.setString(1, expediente);
            
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return lista;
    }
    
    
     public List<tramRequisitoC> mostrarRequisito(int tipoTramite,int tramite) {

        List<tramRequisitoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramRequisitoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.* from DI.TRAM_TRAMITE_DOCUMENTARIO A,DI.TRAM_TRAMITE_REQUISITO B,DI.TRAM_REQUISITO C WHERE A.TIPO_TRAMITE=? AND A.TRAMITE=? AND B.TRAMITE=A.TRAMITE AND C.REQUISITO=B.REQUISITO");
            cs.setInt(1, tipoTramite);
            cs.setInt(2, tramite);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramRequisitoC();
                item.setRequisito(rs.getInt("REQUISITO"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "mostrarRequisito  " + e.getMessage());
        }
        return lista;
    }
}
