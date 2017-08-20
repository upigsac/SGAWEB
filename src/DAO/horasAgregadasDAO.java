
package DAO;


import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.horasAgregadasC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class horasAgregadasDAO {
     public List<horasAgregadasC> mostrarhorasAgregadas(String cpersonal) {

        List<horasAgregadasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        horasAgregadasC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_HORAS_AGREGADAS where cpersonal=? and ESTADO_REGISTRO=1");
            cs.setString(1, cpersonal);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new horasAgregadasC();
                item.setItem(rs.getInt("ITEM"));
                item.setAnio(rs.getInt("ANIO"));
                item.setMes(rs.getInt("MES"));
                item.setDia(rs.getInt("DIA"));
                item.setHoras(rs.getInt("HORAS"));
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setObservacion(rs.getString("OBSERVACION"));                
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
     
     
      public boolean insertarHorasAgregadas(horasAgregadasC item) {
   
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_HORAS_AGREGADAS (?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getItem());
            cs.setInt(2, item.getAnio());
            cs.setInt(3, item.getMes());
            cs.setDouble(4, item.getHoras());
            cs.setString(5, item.getCpersonal());
            cs.setString(6, item.getObservacion());            
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

            
        }
        return rpta;
    }
}
