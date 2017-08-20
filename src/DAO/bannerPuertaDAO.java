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

import ENTIDAD.bannerPuertaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class bannerPuertaDAO {
    public List<bannerPuertaC> mostrarBannerPuerta() {

        Connection c ;
        List<bannerPuertaC> lista = new ArrayList<>();
        CallableStatement cs ;
        ResultSet rs ;
        bannerPuertaC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.PT_BANNER WHERE ESTADO_REGISTRO=1 ORDER BY ORDEN");

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new bannerPuertaC();
                item.setBanner(rs.getInt("BANNER"));
                item.setRuta(rs.getString("RUTA"));
                item.setOrden(rs.getInt("ORDEN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
