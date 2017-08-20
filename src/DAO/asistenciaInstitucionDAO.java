/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.asistenciaInstitucion;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class asistenciaInstitucionDAO {
    
    public List<asistenciaInstitucion.detalle> mostrarAsistenciaInstitucion(String persona,Date fechaInicio,Date FechaFinal) {
        Connection c ;
        List<asistenciaInstitucion.detalle> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        asistenciaInstitucion.detalle item ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_ASISTENCIA_INSTITUCION(1,?,?,?) }");
            cs.setString(1, persona);
            cs.setString(2, util.convertDate(fechaInicio,"dd-MM-yyyy"));
            cs.setString(3, util.convertDate(FechaFinal,"dd-MM-yyyy"));
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new asistenciaInstitucion.detalle();
                item.setLocal(rs.getInt("LOCAL"));
                item.setDesLocal(rs.getString("DESLOCAL"));
                item.setFecha(rs.getDate("FECHA"));
                item.setEntrada(rs.getString("ENTRADA"));
                item.setSalida(rs.getString("SALIDA"));
                item.setDuracion(rs.getString("DURACION"));
               
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error " + e.getMessage());
        }
        return lista;
    }
    
}
