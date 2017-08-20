

package DAO;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.personalExperienciaLaboralC;
import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class personalExperienciaLaboralDAO implements Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean insertar(personalExperienciaLaboralC item) {
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_EXPERIENCIA_LABORAL(?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("PERSONAL", item.getPersonal());
            cs.setInt("ITEM", item.getItem());
            cs.setString("CARGO", item.getCargo());
            cs.setString("FECHAINICIO", util.convertDate(item.getFechaInicio(),"dd-MM-yyyy"));            
            cs.setString("FECHAFINAL", util.convertDate(item.getFechaFinal(),"dd-MM-yyyy"));            
            cs.setString("EMPRESA",item.getEmpresa() );            
            cs.setInt("ESTADOREGISTRO", item.getEstadoRegistro());
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

            //e.printStackTrace();
        }
        return rpta;
    }
     
     public boolean eliminar(personalExperienciaLaboralC item) {
       
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL_EXPERIENCIA_LABORAL(2,?,?,?,?,?,?,?)}");
            cs.setString(1, item.getPersonal());
            cs.setInt(2, item.getItem());
            cs.setString(3, item.getCargo());
            cs.setString(4, util.convertDate(item.getFechaInicio(),"dd-MM-yyyy"));            
            cs.setString(5, util.convertDate(item.getFechaFinal(),"dd-MM-yyyy"));            
            cs.setString(6,item.getEmpresa() );            
            cs.setInt(7, item.getEstadoRegistro());
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

            //e.printStackTrace();
        }
        return rpta;
    }
     
     public List<personalExperienciaLaboralC> mostrarPersonalExperienciaLaboral(String personal) {
        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<personalExperienciaLaboralC> lista = new ArrayList<>();
        personalExperienciaLaboralC item;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PER_EXPERIENCIA_LABORAL WHERE PERSONAL=? AND ESTADO_REGISTRO=1");
            cs.setString(1, personal);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                
                item = new personalExperienciaLaboralC();
                item.setPersonal(rs.getString("PERSONAL"));
                item.setItem(rs.getInt("ITEM"));
                item.setCargo(rs.getString("CARGO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFinal(rs.getDate("FECHA_FIN"));
                item.setEmpresa(rs.getString("EMPRESA"));
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
