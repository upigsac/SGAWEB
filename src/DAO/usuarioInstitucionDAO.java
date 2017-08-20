
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.usuarioInstitucionC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class usuarioInstitucionDAO {
     public List<usuarioInstitucionC> mostrarUsuarioInstitucion(String usuario) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;        
        List<usuarioInstitucionC> lista=new ArrayList<>();
        usuarioInstitucionC item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SEG_USUARIO_INSTITUCION_WEB WHERE USUARIO=?");
            cs.setString(1, usuario);
            rs = cs.executeQuery();
            while (rs.next()) {     
                item=new usuarioInstitucionC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setUsuario(rs.getString("USUARIO"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
                

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
                 util.consolaCliente( e.getMessage());
        }
        return lista;
    }
     
     public boolean insertar(usuarioInstitucionC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_USUARIO_INSTITUCION_WEB  (?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("USUARIO", item.getUsuario());
            cs.setInt("INSTITUCION", item.getInstitucion());            
            cs.setInt("ESTADO_REGISTRO", item.getEstado_registro());

            

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
     
     
     public boolean eliminar(usuarioInstitucionC item) {
         Connection c ;
         CallableStatement cs ;
         boolean rpta = false;

         try {

             c = ConexionWeb();
             cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_USUARIO_INSTITUCION_WEB  (?,?,?,?)}");
             cs.setInt("ITEMWEB", 2);
             cs.setString("USUARIO", item.getUsuario());
             cs.setInt("INSTITUCION", item.getInstitucion());            
             cs.setInt("ESTADO_REGISTRO", item.getEstado_registro());

             

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
     
     
}
