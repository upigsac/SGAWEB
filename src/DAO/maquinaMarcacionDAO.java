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
import ENTIDAD.maquinaMarcacionC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class maquinaMarcacionDAO {
    
    
    
    
    public List<maquinaMarcacionC> mostrarMaquinaMarcacion() {

        List<maquinaMarcacionC> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        maquinaMarcacionC item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  DI.LISTA_MAQUINA_PERMITIDAS WHERE ESTADO=1");
          
            rs = cs.executeQuery();

            while (rs.next()) {

                 item = new maquinaMarcacionC();
                item.setItem(rs.getInt("ITEM"));
                item.setArea(rs.getString("AREA"));
                item.setPc(rs.getString("PC"));
                item.setIp(rs.getString("IP"));
                item.setEstadoRegistro(rs.getInt("ESTADO"));
                
                
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
