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
import ENTIDAD.encuestaRespuestaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class encuestaRespuestaDAO {
    
    public List<encuestaRespuestaC> mostrarRespuesta(int encuesta,int grupo, int pregunta) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<encuestaRespuestaC> lista = new ArrayList<>();
        encuestaRespuestaC item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.*FROM DI.ENCUESTA_PREGUNTA_RESPUESTA A ,DI.ENCUESTA_RESPUESTAS B WHERE B.RESPUESTA=A.RESPUESTA AND A.ENCUESTA=? AND A.GRUPO=? AND A.PREGUNTA=? ORDER BY A.ORDEN ASC");
            cs.setInt(1, encuesta);
            cs.setInt(2, grupo);
            cs.setInt(3, pregunta);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new encuestaRespuestaC();

                item.setRepuesta(rs.getInt("RESPUESTA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));

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
