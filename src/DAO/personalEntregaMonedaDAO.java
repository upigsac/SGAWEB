/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.ingresoMoneda;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class personalEntregaMonedaDAO {
    public List<ingresoMoneda.detalleMoneda> mostrarMonedas(String personal,int anio,int mes,int dia) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<ingresoMoneda.detalleMoneda> lista = new ArrayList<>();
        ingresoMoneda.detalleMoneda item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_DETALLE_MONEDA (?,?,?,?)}");
            cs.setString(1, personal);
            cs.setInt(2, anio);
            cs.setInt(3, mes);
            cs.setInt(4, dia);
            

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ingresoMoneda.detalleMoneda();
                item.setDinero(rs.getString("DINERO"));
                item.setDesDinero(rs.getString("DESCRIPCION"));
                item.setTipoDinero(rs.getInt("TIPO_DINERO"));
                item.setCantidad(rs.getInt("CANTIDAD"));
                item.setTotal(rs.getDouble("TOTAL"));
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
