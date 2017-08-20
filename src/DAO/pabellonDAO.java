/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.pabellonC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class pabellonDAO {
    public List<pabellonC> mostrarPabellon() {

        List<pabellonC> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        pabellonC item;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.SPT_PABELLON WHERE ESTADO_REGISTRO=1");
          
            rs=cs.executeQuery();
            while (rs.next()) {

                item = new pabellonC();
                item.setPabellon(rs.getString("PABELLON"));
                item.setDescripcion(rs.getString("descripcion"));
                item.setAbreviatura(rs.getString("abreviatura"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));
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
