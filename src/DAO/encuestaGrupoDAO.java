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
import ENTIDAD.encuestaGrupoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class encuestaGrupoDAO {
    public List<encuestaGrupoC> mostrarGrupo(int encuesta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<encuestaGrupoC> lista = new ArrayList<>();
        encuestaGrupoC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM DI.ENCUESTA_GRUPO WHERE ENCUESTA=? AND ESTADO_REGISTRO=1");
            cs.setInt(1, encuesta);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaGrupoC();
                item.setEncuesta(rs.getInt("ENCUESTA"));
                item.setGrupo(rs.getInt("GRUPO"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
}
