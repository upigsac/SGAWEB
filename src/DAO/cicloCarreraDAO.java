
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.cicloCarreraC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class cicloCarreraDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<cicloCarreraC> mostrarCicloCarrera(int institucion, String carrera) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs;
        List<cicloCarreraC> lista = new ArrayList<>();
        cicloCarreraC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.ACA_CICLO_CARRERA WHERE INSTITUCION=? AND CARRERA=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, institucion);
            cs.setString(2, carrera);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cicloCarreraC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCiclo(rs.getInt("CICLO"));
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
