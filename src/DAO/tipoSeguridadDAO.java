/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.tipoSeguridadC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class tipoSeguridadDAO {
    
    public List<tipoSeguridadC> mostrarTipoVivienda() {

        List<tipoSeguridadC> lista = new ArrayList<tipoSeguridadC>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        tipoSeguridadC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM  SIGU.SEG_TIPO_SEGURIDAD");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoSeguridadC();
                item.setTipoSeguridad(rs.getInt("TIPO_SEGURIDAD"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
    
}
