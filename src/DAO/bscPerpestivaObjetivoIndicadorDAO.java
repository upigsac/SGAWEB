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

import ENTIDAD.bscPerpestivaObjetivoIndicadorC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class bscPerpestivaObjetivoIndicadorDAO {
    public String insertar(bscPerpestivaObjetivoIndicadorC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_PERPESTIVA_OBJETIVO_INDICADOR (1,?,?,?,?)}");
            cs.setInt(1, item.getPerpestiva());
            cs.setString(2, item.getObjetivo());
            cs.setString(3, item.getIndicador());            
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
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
    
    public String eliminar(bscPerpestivaObjetivoIndicadorC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_BSC_PERPESTIVA_OBJETIVO_INDICADOR (2,?,?,?,?)}");
            cs.setInt(1, item.getPerpestiva());
            cs.setString(2, item.getObjetivo());
            cs.setString(3, item.getIndicador());            
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
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
}
