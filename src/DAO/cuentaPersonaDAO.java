/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.cuentaPersona;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cuentaPersonaC;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;

import java.util.List;


public class cuentaPersonaDAO {
    
public boolean insertarMultiple(List<cuentaPersonaC> cuentaPersonaL) {
        
        Connection c ;
        c = ConexionWeb();
        CallableStatement cs =null;
        boolean rpta = false;
        try {
            c.setAutoCommit(false);
            
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CUENTA_PERSONA (?,?,?,?,?,?,?,?,?,?  ,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
            for (cuentaPersonaC item : cuentaPersonaL) {                
                cs.setString(1, item.getPersona());
                cs.setInt(2, item.getItem());
                cs.setInt(3, item.getInstitucion());
                cs.setString(4, item.getPeriodo_concepto());
                cs.setInt(5, item.getTipo_concepto());
                cs.setString(6, item.getConcepto());
                cs.setString(7, item.getDescuento());
                cs.setString(8, item.getDescuento_adi());
                cs.setInt(9, item.getNum_cuota());
                cs.setInt(10, item.getNum_parte());
                cs.setInt(11, item.getTipo_moneda());
                cs.setDouble(12, item.getMonto_pago());            
                cs.setDouble(13, item.getMonto_parte());            
                cs.setDouble(14, item.getMonto_descuento());
                cs.setDouble(15, item.getMonto_descuento_adi());
                cs.setDouble(16, item.getMonto_interes());
                cs.setDouble(17, item.getMonto_total());
                cs.setString(18, util.convertDate(item.getFecha_vencimiento()));
                cs.setString(19, item.getObservacion());
                cs.setString(20, item.getPeriodo());
                cs.setString(21, item.getAlumno());
                cs.setString(22, item.getCarrera());
                cs.setString(23, item.getSeccion());
                cs.setString(24, item.getGrupo());
                cs.setString(25, item.getCategoria());
                cs.setInt(26, item.getTipo_documento());
                cs.setString(27, item.getNum_comprobante());
                cs.setInt(28, item.getEstadoRegistro());
                cs.executeUpdate() ;
            }
             c.commit();   
        } catch (SQLException e) {           
              util.consolaCliente("erro" + e.getMessage());
             if (c != null) {
                try {
                    deshacerCambios(c);                    
                } catch (Exception ex) {
                    util.consolaCliente( ex.getMessage());                    
                }
            }
        }finally{
            cerrarCall(cs);
            cerrarConexion(c);
        }
        return rpta;
    }
    
    
    
public boolean insertar(cuentaPersonaC item) {
      
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CUENTA_PERSONA (?,?,?,?,?,?,?,?,?,?  ,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getInstitucion());
            cs.setString(4, item.getPeriodo_concepto());
            cs.setInt(5, item.getTipo_concepto());
            cs.setString(6, item.getConcepto());
            cs.setString(7, item.getDescuento());
            cs.setString(8, item.getDescuento_adi());
            cs.setInt(9, item.getNum_cuota());
            cs.setInt(10, item.getNum_parte());
            cs.setInt(11, item.getTipo_moneda());
             cs.setDouble(12, item.getMonto_pago());            
                cs.setDouble(13, item.getMonto_parte());            
                cs.setDouble(14, item.getMonto_descuento());
                cs.setDouble(15, item.getMonto_descuento_adi());
                cs.setDouble(16, item.getMonto_interes());
                cs.setDouble(17, item.getMonto_total());
                cs.setString(18, util.convertDate(item.getFecha_vencimiento()));
                cs.setString(19, item.getObservacion());
                cs.setString(20, item.getPeriodo());
                cs.setString(21, item.getAlumno());
                cs.setString(22, item.getCarrera());
                cs.setString(23, item.getSeccion());
                cs.setString(24, item.getGrupo());
                cs.setString(25, item.getCategoria());
                cs.setInt(26, item.getTipo_documento());
                cs.setString(27, item.getNum_comprobante());
                cs.setInt(28, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR CUENTA PERSONA " + e.getMessage());
           
            //e.printStackTrace();
        }
        return rpta;
    }
    public List<cuentaPersona.detalleCuentaPersona> listarECPersona(String institucion, String periodo,String carrera, String persona,String estado) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cuentaPersona.detalleCuentaPersona item ;
        List<cuentaPersona.detalleCuentaPersona> lista = new ArrayList<>();
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.CON_CUENTA_PERSONA_WEB (?,?,?,?,?)}");
            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, persona);
            cs.setString(5, estado);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cuentaPersona.detalleCuentaPersona();
                item.setPersona(rs.getString("PERSONA"));
                item.setInstitucion_desc(rs.getString("DESINSTITUCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCarrera_desc(rs.getString("DESCARRERA"));
                item.setConcepto_desc(rs.getString("DESCONCEPTO"));
                item.setPeriodo_desc(rs.getString("DESPERIODO"));
                
                item.setFecha_venc(rs.getDate("FECHA_VENCIMIENTO"));

                item.setMonto_base(rs.getBigDecimal("MONTO_BASE"));
                item.setMonto_parte(rs.getBigDecimal("MONTO_PARTE"));
                item.setMonto_desc(rs.getBigDecimal("MONTO_DESCUENTO"));
                item.setMonto_desc_adic(rs.getBigDecimal("MONTO_DESCUENTO_ADI"));
                item.setMonto_interes(rs.getBigDecimal("MONTO_INTERES"));

                

                item.setMonto_total(rs.getBigDecimal("MONTO_TOTAL"));

                item.setTipo_comp_desc(rs.getString("DESTDOCUMENTO"));
                item.setNum_comp(rs.getString("NUM_COMPROBANTE"));
                item.setEstado_registro_desc(rs.getString("DESESTADO"));

                item.setItem(rs.getInt("ITEM"));

                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo_concepto(rs.getString("PERIODO_CONCEPTO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setTipo_concepto(rs.getInt("TIPO_CONCEPTO"));

                item.setNum_cuota(rs.getInt("NUM_CUOTA"));
                item.setNum_parte(rs.getInt("NUM_PARTE"));

                item.setTipo_comp(rs.getInt("TIPO_DOCUMENTO"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPeriodo(rs.getString("PERIODO"));
              
                item.setObservacion(rs.getString("OBSERVACION"));

                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("TRAER listar listarECPersona " + e.getMessage());
        }
        return lista;
    }

    

    
    
    public int fraccionCuentaPersona(int tipoAutorizacion,int autorizacion,String persona,cuentaPersona.detalleCuentaPersona item) {
        int resultado = 0;
       
        try {
            Connection c ;
            CallableStatement cs ;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_SQL_FRACCIONA_CUENTA_PERSONA (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,? ,?,?,?,?)}");
            cs.setString(1, persona);
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getInstitucion());
            cs.setString(4, item.getPeriodo_concepto());
            cs.setInt(5, item.getTipo_concepto());
            cs.setString(6, item.getConcepto());
            cs.setString(7, "0000"); // DESCUENTO 
            cs.setString(8, "0000"); // DESCUENTO_ADI
            cs.setInt(9, item.getNum_cuota()); // NUMERO DE CUOTAS
            cs.setInt(10, item.getNum_parte()); // NUMERO DE PARTE
            cs.setInt(11, 1); // TIPO MONEDA
            cs.setDouble(12, item.getMonto_base().doubleValue()); // MONTO PAGO
            cs.setDouble(13, item.getMonto_parte().doubleValue()); // MONTO PARTE
            cs.setDouble(14, 0.0); // MONTO DESCUENTO
            cs.setDouble(15, 0.0); // MONTO DESCUENTO ADI
            cs.setDouble(16, item.getMonto_interes().doubleValue()); // MONTO INTERES
            cs.setDouble(17, 0.0); // MONTO TOTAL
            cs.setString(18, util.convertDate(item.getFecha_venc()) ); // FECHA VENCIMIENTO
            cs.setInt(19, item.getItemPadre() ); // ITEM PADRE
            cs.setString(20, item.getPeriodo()); // PERIODO
            cs.setString(21, item.getAlumno()); // ALUMNO
            cs.setString(22, item.getCarrera()); // ALUMNO
            cs.setInt(23, autorizacion);
            cs.setInt(24, tipoAutorizacion);
            
            cs.executeUpdate();            
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println("FRACIONAMIENTO" + e.getMessage());
        }
        return resultado;
    }
}
