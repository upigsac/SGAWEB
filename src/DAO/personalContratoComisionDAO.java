
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.personalContratoComisionC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class personalContratoComisionDAO {
    public String insertar(personalContratoComisionC item) {     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIEMTO_PER_CONTRATO_COMISION(?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("CONTRATO", item.getContrato());
            cs.setInt("COMISION", item.getComision());
            cs.setInt("ASIGNACION", item.getAsignacion());
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
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
