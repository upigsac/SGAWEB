/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Beans.cronograma;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.cronogramaPagoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class cronogramaPagoDAO {
    public List<cronogramaPagoC> mostrarCronogramaPago(int institucion, String periodo,int vecimiento,String carrera ,String ciclo) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaPagoC item ;
        List<cronogramaPagoC> lista = new ArrayList<>();        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CRONOGRAMA_PAGO (1,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, vecimiento);
            cs.setString(4, carrera);
            cs.setString(5, ciclo);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronogramaPagoC();
                
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setActividad(rs.getString("ACTIVIDAD"));
                item.setInversion(rs.getDouble("INVERSION"));
                
                item.setFechaIni(rs.getDate("FECHA_INI"));

                item.setFechaFin(rs.getDate("FECHA_FIN"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("mostrarCronogramaPago" + e.getMessage());
        }
        return lista;
    }
    public List<cronograma.vencimiento> mostrarCronogramaVencimiento(int institucion, String periodo,int vencimiento) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronograma.vencimiento item ;
        List<cronograma.vencimiento> lista = new ArrayList<>();        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CRONOGRAMA_PAGO (2,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setInt(3, vencimiento);           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new cronograma.vencimiento();
                
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setCuota(rs.getInt("CUOTA"));
                item.setDesConcepto(rs.getString("DESCONCEPTO"));
                item.setFecha(rs.getDate("FECHA_VENCIMIENTO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("mostrarCronogramaPago" + e.getMessage());
        }
        return lista;
    }
}