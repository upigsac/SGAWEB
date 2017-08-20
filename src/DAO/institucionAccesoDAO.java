
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.institucionAccesoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class institucionAccesoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public institucionAccesoC mostrarInstitucionAcceso(int institucion) {
      
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionAccesoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.WEB_INSTITUCION_ACCESO WHERE INSTITUCION=?  AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new institucionAccesoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPrograma(rs.getInt("PROGRAMA"));
                item.setColorCabecera(rs.getString("COLOR_CAB"));
                item.setColorPie(rs.getString("COLOR_PIE"));
                item.setColorMenu(rs.getString("COLOR_MENU"));
                item.setColorLetraMenu(rs.getString("COLOR_LETRA_MENU"));
                item.setColorLinea(rs.getString("COLOR_LINEA"));
                item.setLogoTipo(rs.getString("LOGO_INSTITUCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
           System.out.println(e.getMessage());
           
        }
        return item;
    }

    public List<institucionAccesoC> mostrarInstitucionAcceso() {

        List<institucionAccesoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        institucionAccesoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.WEB_INSTITUCION_ACCESO  ");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new institucionAccesoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPrograma(rs.getInt("PROGRAMA"));
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
