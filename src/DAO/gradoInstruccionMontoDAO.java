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

import ENTIDAD.gradoInstruccionMontoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class gradoInstruccionMontoDAO {
    public gradoInstruccionMontoC mostrarGradoInstruccionMonto(int gradoInstruccion) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        gradoInstruccionMontoC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.SYS_GRADO_INSTRUCCION_MONTO  A WHERE GRADO_INSTRUCCION=?");
            cs.setInt(1, gradoInstruccion);
            rs = cs.executeQuery();
            
            while (rs.next()) {
                item = new gradoInstruccionMontoC();
                item.setGradoInstruccion(rs.getInt("GRADO_INSTRUCCION"));
                item.setMonto(rs.getDouble("MONTO"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {        
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
