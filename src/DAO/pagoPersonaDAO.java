/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.pagoPersonaC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class pagoPersonaDAO {
     public List<pagoPersonaC> mostrarPagoPersona(int institucion,String concepto,String alumno) {
       
        List<pagoPersonaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
        pagoPersonaC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.*FROM SIGU.TES_DET_PAGOS_PERSONA A ,SIGU.ACA_ALUMNO B ,SIGU.TES_PAGOS_PERSONA C\n" +
                        "WHERE  A.INSTITUCION=? AND A.CONCEPTO=? AND B.ALUMNO=? AND A.PERSONA= B.PERSONA AND \n" +
                        "B.INSTITUCION=A.INSTITUCION AND  C.NUM_COMPROBANTE=A.NUM_COMPROBANTE AND C.TIPO_DOCUMENTO=A.TIPO_DOCUMENTO AND C.ESTADO_REGISTRO=28");
            cs.setInt(1, institucion);
            cs.setString(2, concepto);
            cs.setString(3, alumno);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new pagoPersonaC();                
                item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           System.out.println(e.getMessage());
            // 
        }
        return lista;
    }
     public pagoPersonaC mostrarPagoPersona(String numComprobante) {
         
         
         Connection c ;
         CallableStatement cs  ;
         ResultSet rs ;
         pagoPersonaC item=null;
         try {

             c = ConexionWeb();
             cs = c.prepareCall("select  *from SIGU.TES_PAGOS_PERSONA WHERE NUM_COMPROBANTE=? AND ESTADO_REGISTRO=28");
          
             cs.setString(1, numComprobante);
          
             
             rs = cs.executeQuery();

             while (rs.next()) {
                 item = new pagoPersonaC();                
                 item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));
                 item.setPersonaEmpresa(rs.getString("PERSONA_EMPRESA"));
                 item.setTipoDocumento(rs.getInt("TIPO_DOCUMENTO"));
                
                
             }
             cerrarCall(cs);
             cerrarConexion(c);

         } catch (SQLException e) {
            System.out.println(e.getMessage());
             // 
         }
         return item;
     }
}
