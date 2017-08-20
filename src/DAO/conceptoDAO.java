/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import ENTIDAD.conceptoC;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import Conexiones.Conexion;


public class conceptoDAO extends Conexion {
	public String insertar(conceptoC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI_SP_MANTENIMIENTO_TES_CONCEPTOS(1,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getConcepto());
            cs.setInt(2, item.getTipoConcepto());
            cs.setString(3, item.getDescripcion());
            cs.setString(4, item.getAbreviatura());
            cs.setBoolean(5, item.isCertificacion());
            cs.setString(6, item.getCarrera());
            cs.setBoolean(7, item.isEntradaSalida());
            cs.setString(8, item.getCuenta());
            cs.setString(9, item.getRubro());
            cs.setInt(10, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
	
	public String eliminar(conceptoC item) {
	     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI_SP_MANTENIMIENTO_TES_CONCEPTOS(2,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getConcepto());
            cs.setInt(2, item.getTipoConcepto());
            cs.setString(3, item.getDescripcion());
            cs.setString(4, item.getAbreviatura());
            cs.setBoolean(5, item.isCertificacion());
            cs.setString(6, item.getCarrera());
            cs.setBoolean(7, item.isEntradaSalida());
            cs.setString(8, item.getCuenta());
            cs.setString(9, item.getRubro());
            cs.setInt(10, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
	
	
	
     public List<conceptoC> mostrarConcepto() {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        conceptoC item ;
        List<conceptoC> lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 100 * FROM SIGU.TES_CONCEPTOS");
           
            rs = cs.executeQuery();

            while (rs.next()) {
               
                item = new conceptoC();
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setCertificacion(rs.getBoolean("CERTIFICACION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setEntradaSalida(rs.getBoolean("ENTRADA_SALIDA"));
                item.setCuenta(rs.getString("CUENTA_70"));
                item.setRubro(rs.getString("RUBRO_CONCEPTO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);               

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     public List<conceptoC> filtroConcepto(String tipoConcepto,String desConcepto) {        
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         conceptoC item ;
         List<conceptoC> lista=new ArrayList<>();
         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT TOP 100 * FROM SIGU.TES_CONCEPTOS WHERE TIPO_CONCEPTO LIKE ? AND DESCRIPCION LIKE ? ");
            cs.setString(1, tipoConcepto);
            cs.setString(2, "%"+desConcepto +"%");
             rs = cs.executeQuery();

             while (rs.next()) {
                
                 item = new conceptoC();
                 item.setConcepto(rs.getString("CONCEPTO"));
                 item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                 item.setDescripcion(rs.getString("DESCRIPCION"));
                 item.setAbreviatura(rs.getString("ABREVIATURA"));
                 item.setCertificacion(rs.getBoolean("CERTIFICACION"));
                 item.setCarrera(rs.getString("CARRERA"));
                 item.setEntradaSalida(rs.getBoolean("ENTRADA_SALIDA"));
                 item.setCuenta(rs.getString("CUENTA_70"));
                 item.setRubro(rs.getString("RUBRO_CONCEPTO"));
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
     
     
     public List<conceptoC> mostrarConcepto(String num_comprobante) {        
         Connection c ;
         CallableStatement cs ;
         ResultSet rs ;
         conceptoC item ;
         List<conceptoC> lista=new ArrayList<>();
         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT B.* FROM SIGU.TES_DET_PAGOS_PERSONA A,SIGU.TES_CONCEPTOS B WHERE  A.NUM_COMPROBANTE LIKE ? AND B.CONCEPTO=A.CONCEPTO AND A.ESTADO_REGISTRO=28");
             cs.setString(1, num_comprobante);
             rs = cs.executeQuery();

             while (rs.next()) {
              
                 item = new conceptoC();
                 item.setConcepto(rs.getString("CONCEPTO"));
                 item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                 item.setDescripcion(rs.getString("DESCRIPCION"));
                 item.setAbreviatura(rs.getString("ABREVIATURA"));
                 item.setCertificacion(rs.getBoolean("CERTIFICACION"));
                 item.setCarrera(rs.getString("CARRERA"));
                 item.setEntradaSalida(rs.getBoolean("ENTRADA_SALIDA"));
                 item.setCuenta(rs.getString("CUENTA_70"));
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
     
     
     
     
      public conceptoC mostrarConceptoDescripcion(String descripcion) {        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        conceptoC item = null;
       
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.TES_CONCEPTOS WHERE DESCRIPCION=?");
           cs.setString(1, descripcion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new conceptoC();
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setCertificacion(rs.getBoolean("CERTIFICACION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setEntradaSalida(rs.getBoolean("ENTRADA_SALIDA"));
                item.setCuenta(rs.getString("CUENTA_70"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                             

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
      
     
}
