/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.revistaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class revistaDAO {
       public List<revistaC> mostrarRevista(String periodo, String carrera) {

        List<revistaC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        revistaC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.REVISTA WHERE PERIODO=? AND CARRERA LIKE ?");

            cs.setString(1, periodo);
            cs.setString(2, carrera);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new revistaC();
                item.setRevista(rs.getString("REVISTA"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setPortada(rs.getString("PORTADA"));
                item.setTitulo(rs.getString("TITULO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente(e.getMessage());
           // e.printStackTrace();
        }
        return lista;
    }
}
