
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tramTipoTramiteC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class tramTipoTramiteDAO {
 
    
    
    public List<tramTipoTramiteC> mostrarTipoTramite() {

        List<tramTipoTramiteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTipoTramiteC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_TIPO_TRAMITE");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTipoTramiteC();
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setIcono(rs.getString("ICONO"));
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
    
    public tramTipoTramiteC mostrarTipoTramite(int tipoTramite) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tramTipoTramiteC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TRAM_TIPO_TRAMITE WHERE TIPO_TRAMITE=?");
            cs.setInt(1, tipoTramite);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTipoTramiteC();
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setDescripcion(rs.getString("DESCRIPCION"));                
                item.setAbreviatura(rs.getString("ABREVIATURA"));      
                item.setIcono(rs.getString("ICONO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error metodo turno " + e.getMessage());
        }
        return item;
    }
}
