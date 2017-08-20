
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.tramTramiteOficinaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class tramTramiteOficinaDAO {
    public String insertar(tramTramiteOficinaC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TRAMITE_OFICINA(1,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, 1);
            cs.setInt(2, item.getTramite());            
            cs.setInt(3, item.getItem());
            cs.setInt(4, item.getOficinaEmisor());
            cs.setInt(5, item.getOficinaReceptor());
            cs.setInt(6, item.getOrden());
            cs.setString(7, item.getDescripcion());
            cs.setInt(8, item.getEstadoRegistro());
            
           
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
        return codigo;
    }
    
    
    public String eliminar(tramTramiteOficinaC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_TRAMITE_OFICINA(2,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, 1);
            cs.setInt(2, item.getTramite());            
            cs.setInt(3, item.getItem());
            cs.setInt(4, item.getOficinaEmisor());
            cs.setInt(5, item.getOficinaReceptor());
            cs.setInt(6, item.getOrden());
            cs.setString(7, item.getDescripcion());
            cs.setInt(8, item.getEstadoRegistro());
            
           
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
        return codigo;
    }
     public List<tramTramiteOficinaC> mostrarTramiteOficina(int tramite) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramiteOficinaC> lista=new ArrayList<>();
        tramTramiteOficinaC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_TRAMITE_OFICINA WHERE TRAMITE = ? AND ESTADO_REGISTRO=1  ORDER BY ORDEN");
            cs.setInt(1, tramite);
           
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteOficinaC();
                item.setTramite(rs.getInt("TRAMITE"));
                item.setItem(rs.getInt("ITEM"));
                item.setOficinaEmisor(rs.getInt("OFICINA_EMISOR"));
                item.setOficinaReceptor(rs.getInt("OFICINA_RECEPTOR"));
                item.setOrden(rs.getInt("ORDEN"));                                
                item.setDescripcion(rs.getString("DESCRIPCION"));                                
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
}
