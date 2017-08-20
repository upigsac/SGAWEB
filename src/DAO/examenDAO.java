/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.examenC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class examenDAO {
    public boolean insertar(examenC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_EXAMEN(1,?,?,?,?,?)}");
            cs.setInt(1, item.getExamen());
            cs.setString(2, item.getDescripcion());
            cs.setString(3, util.convertDate(item.getFechaInicio(),"dd-MM-yyyy") );
            cs.setString(4, util.convertDate(item.getFechaFin(),"dd-MM-yyyy") );
            cs.setInt(5, item.getEstadoRegistro());
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
        return rpta;
    }
    public boolean eliminar(examenC item) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_EXAMEN (2,?,?,?,?,?)}");
            cs.setInt(1, item.getExamen());
            cs.setString(2, item.getDescripcion());
            cs.setString(3, util.convertDate(item.getFechaInicio()) );
            cs.setString(4, util.convertDate(item.getFechaFin()) );
            cs.setInt(5, item.getEstadoRegistro());
            

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return rpta;
    }
    
    
    public List<examenC> mostrarExamen() {

        List<examenC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        examenC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.EXAM_EXAMEN");

           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new examenC();
                item.setExamen(rs.getInt("EXAMEN"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

}
