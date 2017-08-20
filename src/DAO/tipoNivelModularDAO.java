/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package  DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.tipoNivelModularC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class tipoNivelModularDAO {
    
    
    public List<tipoNivelModularC> mostrarTipoNivelModular() {

        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        tipoNivelModularC item = null;
        List<tipoNivelModularC> lista=new ArrayList<tipoNivelModularC>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_TIPO_NIVEL_MODULAR");
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoNivelModularC();
                item.setTipoNivelModular(rs.getInt("TIPO_NIVEL_MODULAR"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
    public tipoNivelModularC mostrarTipoNivelModular(int tipoNivelModular) {

        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        tipoNivelModularC item = null;
        

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.ACA_TIPO_NIVEL_MODULAR WHERE TIPO_NIVEL_MODULAR=?");
            cs.setInt(1, tipoNivelModular);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tipoNivelModularC();
                item.setTipoNivelModular(rs.getInt("TIPO_NIVEL_MODULAR"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
