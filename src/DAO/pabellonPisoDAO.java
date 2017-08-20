/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.pabellonPisoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class pabellonPisoDAO {
    public List<pabellonPisoC> mostrarPabellonPiso(String pabellon) {

        List<pabellonPisoC> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        pabellonPisoC item;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.SPT_PABELLON_PISO WHERE PABELLON=? AND ESTADO_REGISTRO=1");
          cs.setString(1, pabellon);
            rs=cs.executeQuery();
            while (rs.next()) {
                item = new pabellonPisoC();
                item.setPabellon(rs.getString("PABELLON"));
                item.setPiso(rs.getInt("PISO"));
                item.setMaxAula(rs.getInt("MAX_AULA"));
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
