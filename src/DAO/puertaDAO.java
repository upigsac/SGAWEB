/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.puerta;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class puertaDAO {
    
    
    
    
    
    
    
    
     public List<puerta.personaDeuda> mostrarPersonaDeuda(String persona) {
        List<puerta.personaDeuda> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        puerta.personaDeuda item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT A.PERSONA,A.PERIODO,B.CONCEPTO,B.DESCRIPCION DESCONCEPTO,FECHA_VENCIMIENTO,DATEDIFF(D,FECHA_VENCIMIENTO,GETDATE()) DIA,NUM_CUOTA,A.MONTO_TOTAL,A.MONTO_TOTAL FROM SIGU.TES_CUENTA_PERSONA A,SIGU.TES_CONCEPTOS B WHERE CONVERT(DATE,A.FECHA_VENCIMIENTO)<CONVERT(DATE,GETDATE()) AND  A.ESTADO_REGISTRO=27 AND a.TIPO_CONCEPTO=1 AND  A.PERSONA LIKE ?  AND A.MONTO_TOTAL>0 AND A.INSTITUCION=1 AND B.CONCEPTO=A.CONCEPTO ORDER BY PERSONA,PERIODO,DESCRIPCION,NUM_CUOTA");
            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new puerta.personaDeuda();
                item.setPersona(rs.getString("PERSONA"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setDesConcepto(rs.getString("DESCONCEPTO"));                
                item.setFecha(rs.getDate("FECHA_VENCIMIENTO"));                
                item.setDia(rs.getInt("DIA"));   
                item.setNumCuota(rs.getInt("NUM_CUOTA"));   
                item.setMontoTotal(rs.getDouble("MONTO_TOTAL"));   
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
