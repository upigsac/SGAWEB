/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.regimenPensionC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;

public class regimenPensionDAO {
    public List<regimenPensionC> mostrarRegimenPension() {
        List<regimenPensionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        regimenPensionC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.SYS_REGIMEN_PENSION WHERE ESTADO_REGISTRO=1");
          

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new regimenPensionC();
                item.setRegimenPension(rs.getInt("REGIMEN_PENSION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                          
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( "erroress" + e.getMessage());
        }
        return lista;
    }
}
