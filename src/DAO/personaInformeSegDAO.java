
package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;

import ENTIDAD.personaInformeSegC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class personaInformeSegDAO {
    
     public String insertar(personaInformeSegC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.MANTENIMIENTO_PERSONA_INFORME_DET(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getInforme());
            cs.setInt(3, item.getInstitucion());
            cs.setString(4, item.getPaquete());
            cs.setString(5, item.getProducto());
            cs.setString(6, item.getCarrera());
            cs.setString(7, item.getGrupo());
            cs.setString(8, item.getPeriodo());
            cs.setString(9, item.getSeccion());
            cs.setString(10, item.getPersonal());
            cs.setInt(11, item.getLocal());
            cs.setInt(12, item.getPuntoVenta());
            cs.setInt(13, item.getPreMatricula());
            cs.setInt(14, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage());
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
}
