
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.feriadosC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import java.util.Date;


public class feriadoDAO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String mostrarTotalferiado(int  institucion, String periodo,String personal,String carrera,String curso,String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        String totalHoras = "0";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT DI.SF_MOSTRAR_TOTAL_FERIADOS_PERSONAL(?,?,?,?,?,?) AS TOTALFERIADO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            

            rs = cs.executeQuery();
            while (rs.next()) {
                totalHoras = rs.getString("TOTALFERIADO");
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error registrotm" + e.getMessage());
        }
        return totalHoras;
    }
    
     public List<feriadosC> mostrarFeriados(int institucion ,String periodo,String personal,String carrera,String curso,String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<feriadosC> lista=new ArrayList<>();
        feriadosC item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_FERIADOS_PERSONAL(?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            cs.setString(4, carrera);
            cs.setString(5, curso);
            cs.setString(6, seccion);

            rs = cs.executeQuery();
            while (rs.next()) {
                
                item=new feriadosC();
                item.setFeriado(rs.getInt("FERIADO"));
                item.setAño(rs.getString("AÃ‘O"));
                item.setMes(rs.getString("MES"));
                item.setDia(rs.getString("DIA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error registrotm" + e.getMessage());
        }
        return lista;
    }
      public List<feriadosC> mostrarFeriados(Date fecha) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<feriadosC> lista=new ArrayList<>();
        feriadosC item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_FERIADO(?)}");
            cs.setString(1, util.convertDate(fecha, "dd-MM-yyyy"));
            

            rs = cs.executeQuery();
            while (rs.next()) {
                
                item=new feriadosC();
                item.setFeriado(rs.getInt("FERIADO"));
                item.setAño(rs.getString("AÃ‘O"));
                item.setMes(rs.getString("MES"));
                item.setDia(rs.getString("DIA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error registrotm" + e.getMessage());
        }
        return lista;
    }
}
