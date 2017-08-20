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

import ENTIDAD.tramDocumentoTramiteC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class tramDocumentoTramiteDAO {
    
    
    public boolean insertar(tramDocumentoTramiteC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta =false;
        
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_DOCUMENTO_TRAMITE(?,?,?)}");
            cs.setInt(1, item.getTipoTramite());
            cs.setInt(2, item.getTramite());
            cs.setInt(3, item.getEstadoRegistro());   
         
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
        return rpta;
    }
    
    
    
    public List<tramDocumentoTramiteC> mostrarDocumento(int documento) {

        List<tramDocumentoTramiteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramDocumentoTramiteC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_DOCUMENTO_TRAMITE WHERE TIPO_TRAMITE=?");
            cs.setInt(1, documento);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramDocumentoTramiteC();
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setTramite(rs.getInt("TRAMITE"));                
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
}
