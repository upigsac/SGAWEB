
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;


import ENTIDAD.usuarioHistorialC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class usuarioHistorialDAO implements Serializable {
    
    
   
	private static final long serialVersionUID = 1L;



	public String insertar(usuarioHistorialC item) {        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo="";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_SEG_USUARIO_HISTORIAL (?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setInt("ITEM", item.getItem());
            cs.setString("USUARIO", item.getUsuario());
            cs.setString("PERSONA", item.getPersona());
            cs.setString("PLATAFORMA", item.getPlataforma());
            cs.setString("NAVEGADOR", item.getNavegador());
            cs.setString("IP", item.getIp());
            cs.setString("PAIS", item.getPais());           
            cs.setString("FECHA", util.convertDate(item.getFecha(), "dd-MM-yyyy HH:mm:ss") );
            cs.setInt("PERFIL", item.getPerfil() );
            cs.setInt("ESTADO_REGISTRO", item.getEstadoRegistro());   
            
            cs.registerOutParameter("ITEM", Types.INTEGER);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
                 codigo =""+ cs.getInt("ITEM");
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println("ERROR GUARDAR PERSONA" + e.getMessage());
            
        }
        return codigo;
    }
    
    
    
    public List<usuarioHistorialC> mostrar(int cantidad,  String usuario) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        usuarioHistorialC item;
        List<usuarioHistorialC> lista=new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP "+cantidad+" *FROM DI.SEG_USUARIO_HISTORIAL WHERE USUARIO=? ORDER BY FECHA DESC");
            
            cs.setString(1, usuario);
           

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new usuarioHistorialC();
                item.setItem(rs.getInt("ITEM"));
                item.setUsuario(rs.getString("USUARIO"));                
                item.setPersona(rs.getString("PERSONA"));
                item.setPlataforma(rs.getString("PLATAFORMA"));
                item.setNavegador(rs.getString("NAVEGADOR"));
                item.setIp(rs.getString("IP"));
                item.setPais(rs.getString("PAIS"));
                item.setFecha(rs.getTimestamp("FECHA"));
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
