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
import ENTIDAD.serieC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class serieDAO {
    
    
    
    public serieC mostrarSerie(int tipoDocumento) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        serieC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.TES_SERIES_JAVA WHERE TIPO_DOCUMENTO=43");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new serieC();
                item.setLocal(rs.getInt("LOCAL"));
                item.setModulo(rs.getString("MODULO"));
                item.setSerie(rs.getString("SERIE"));
                item.setTipoDocumento(rs.getInt("TIPO_DOCUMENTO"));
                item.setNumeroSerie(rs.getString("NUMERO_SERIE"));
                item.setSecuencia(rs.getInt("SECUENCIA"));
                item.setNumeroInicio(rs.getInt("NUMERO_INICIO"));
                item.setNumeroFinal(rs.getInt("NUMERO_FIN"));
                item.setNumeroActual(rs.getInt("NUMERO_ACTUAL"));
                item.setNumeroDigitos(rs.getInt("NUMERO_DIGITOS"));
                item.setImpresion(rs.getInt("IMPRESION"));                
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());
        }
        return item;
    }
}
