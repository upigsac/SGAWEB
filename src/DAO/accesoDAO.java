
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.accesoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class accesoDAO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	 public boolean insertar(accesoC item) {
	      
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta = false;

	        try {

	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACCESO (1,?,?,?,?,?)}");
	            cs.setString(1, item.getUsuario());
	            cs.setInt(2, item.getPrograma());
	            cs.setInt(3, item.getModulo() );
	            cs.setInt(4, item.getTipoSeguridad());
	            cs.setInt(5, item.getEstadoRegistro() );

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
	 
	 public boolean eliminar(accesoC item) {
	      
	        Connection c ;
	        CallableStatement cs ;
	        boolean rpta = false;

	        try {

	            c = ConexionWeb();
	            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACCESO (2,?,?,?,?,?)}");
	            cs.setString(1, item.getUsuario());
	            cs.setInt(2, item.getPrograma());
	            cs.setInt(3, item.getModulo() );
	            cs.setInt(4, item.getTipoSeguridad());
	            cs.setInt(5, item.getEstadoRegistro() );

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


	public accesoC mostrarAccesos(String usuario, int programa, int modulo) {
		accesoC item = null;
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  SIGU.WEB_ACCESOS WHERE USUARIO=? AND PROGRAMA=? AND MODULO=? AND ESTADO_REGISTRO=1");
            cs.setString(1, usuario);
            cs.setInt(2, programa);
            cs.setInt(3, modulo);
            rs = cs.executeQuery();

            while (rs.next()) {
            	item=new accesoC();
                item.setUsuario(rs.getString("USUARIO"));
                item.setPrograma(rs.getInt("PROGRAMA"));
                item.setModulo(rs.getInt("MODULO"));
                item.setTipoSeguridad(rs.getInt("TIPO_SEGURIDAD"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
           
        }
        return item;
    }

	public List<accesoC> mostrarAccesos(int programa, String  mpadre, String tipo,String usuario) {

		
		
		accesoC item = null;
		List<accesoC> lista=new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM SIGU.WEB_MODULOS A ,SIGU.WEB_ACCESOS B WHERE  A.SIGU=? AND  A.MPADRE=? AND A.TIPO=? AND B.USUARIO=? AND B.PROGRAMA=A.SIGU AND B.MODULO=A.ID");
            cs.setInt(1, programa);
            cs.setString(2, mpadre);
            cs.setString(3, tipo);
            cs.setString(4, usuario);
            rs = cs.executeQuery();

            while (rs.next()) {
            	item=new accesoC();
                item.setUsuario(rs.getString("USUARIO"));
                item.setPrograma(rs.getInt("PROGRAMA"));
                item.setModulo(rs.getInt("MODULO"));
                item.setTipoSeguridad(rs.getInt("TIPO_SEGURIDAD"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item)	;
            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
           
        }
        return lista;
    }
    
    
    public accesoC seguridadWeb(int tipo, String pagina, String  usuario) {   
          
           Connection c ;
            CallableStatement cs ;
            ResultSet rs ;
            accesoC item=null ;  
            
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_SEGURIDAD_WEB(?,?,?)}");
            cs.setInt(1, tipo);
            cs.setString(2, pagina);
            cs.setString(3, usuario);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                item=new accesoC();
                item.setUsuario(rs.getString("USUARIO"));
                item.setPrograma(rs.getInt("PROGRAMA"));
                item.setModulo(rs.getInt("MODULO"));
                item.setTipoSeguridad(rs.getInt("TIPO_SEGURIDAD"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }

            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
           
        }
        return item;
    }

}
