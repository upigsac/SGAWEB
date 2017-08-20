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
import ENTIDAD.tipoPersonalC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tipoPersonalDAO {
    public List<tipoPersonalC> mostrarTipoPersonal(String personal) {

        List<tipoPersonalC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        tipoPersonalC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.PER_TIPO_PERSONAL WHERE INSTITUCION=1 AND PERSONAL=@PERSONAL");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoPersonalC();
                item.setTipoPersona(rs.getInt("TIPO_PERSONA"));
                item.setPersonal(rs.getString("PERSONAL"));                
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
