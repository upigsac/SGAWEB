
package DAO;


import Beans.util;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.personalContratoC;


import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class personalContratoDAO {
    
    
     
     public boolean insertar(personalContratoC item) {
         
    	  System.out.println( item.getInstitucion());
    	  System.out.println( item.getPeriodo());
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIEMTO_PER_CONTRATO (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("CONTRATO", item.getContrato());
            cs.setInt("TIPO_PERSONAL", item.getTipoPersonal());
            cs.setInt("TIPO_CONTRATO", item.getTipoContrato());
            cs.setInt("PERIOCIDAD", item.getPeriocidad());
            cs.setString("FECHA_INICIO", util.convertDate( item.getFechaInicio(),"dd-MM-yyyy"));
            cs.setString("FECHA_FIN", util.convertDate( item.getFechaFin(),"dd-MM-yyyy"));
            cs.setDouble("BASICO", item.getBasico());            
            cs.setDouble("MOVILIDAD", item.getMovilida());
            cs.setDouble("BONIFICACION", item.getBonificacion());
            cs.setString("OBJETIVO", item.getObjetivo());            
            cs.setDouble("SITUACION", item.getSituacion());            
            cs.setString("FECHA_IMPRESION", null);            
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
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

        }
        return rpta;
    }
     
     
     public boolean eliminar(personalContratoC item) {
         
        
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIEMTO_PER_CONTRATO (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 2);
            cs.setInt("INSTITUCION", item.getInstitucion());
            cs.setString("PERIODO", item.getPeriodo());
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("CONTRATO", item.getContrato());
            cs.setInt("TIPO_PERSONAL", item.getTipoPersonal());
            cs.setInt("TIPO_CONTRATO", item.getTipoContrato());
            cs.setInt("PERIOCIDAD", item.getPeriocidad());
            cs.setString("FECHA_INICIO", util.convertDate( item.getFechaInicio(),"dd-MM-yyyy"));
            cs.setString("FECHA_FIN", util.convertDate( item.getFechaFin(),"dd-MM-yyyy"));
            cs.setDouble("BASICO", item.getBasico());            
            cs.setDouble("MOVILIDAD", item.getMovilida());
            cs.setDouble("BONIFICACION", item.getBonificacion());
            cs.setString("OBJETIVO", item.getObjetivo());            
            cs.setDouble("SITUACION", item.getSituacion());            
            cs.setString("FECHA_IMPRESION", null);            
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	 System.out.println(e.getMessage());;

        }
        return rpta;
    }
     
     
     public List<personalContratoC> mostrarPersonalContrato(String personal) {

         Connection c ;
         CallableStatement cs;
         ResultSet rs;
         personalContratoC item = null;
         List<personalContratoC> lista=new ArrayList<>();
         try {

             c = ConexionWeb();
             cs = c.prepareCall("SELECT *FROM DI.PER_CONTRATO WHERE PERSONAL=? ORDER BY CONTRATO DESC");
             cs.setString(1, personal);

             rs = cs.executeQuery();

             while (rs.next()) {
                 item = new personalContratoC();
                 item.setContrato(rs.getInt("CONTRATO"));
                 item.setInstitucion(rs.getInt("INSTITUCION"));                 
                 item.setPersonal(rs.getString("PERSONAL"));
                 item.setPeriodo(rs.getString("PERIODO"));
                 item.setTipoContrato(rs.getInt("TIPO_CONTRATO"));
                 item.setTipoPersonal(rs.getInt("TIPO_PERSONAL"));
                 item.setPeriocidad(rs.getInt("PERIOCIDAD"));
                 item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                 item.setFechaFin(rs.getDate("FECHA_FIN"));
                 item.setBasico(rs.getDouble("BASICO"));
                 item.setMovilida(rs.getDouble("MOVILIDAD"));
                 item.setBonificacion(rs.getDouble("BONIFICACION"));
                 item.setObjetivo(rs.getString("OBJETIVO"));                
                 item.setEstadoRegistro(rs.getInt("estado_registro"));
                 lista.add(item);

             }
             cerrarCall(cs);
             cerrarConexion(c);

         } catch (SQLException e) {
             
             System.out.println(e.getMessage());
         }
         return lista;
     }
}
