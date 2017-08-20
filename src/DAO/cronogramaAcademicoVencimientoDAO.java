/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.cronogramaAcademicoVencimientoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class cronogramaAcademicoVencimientoDAO {
     public cronogramaAcademicoVencimientoC mostrarCronogramaAcademicoVencimiento(int institucion, String periodo,  int vencimiento) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademicoVencimientoC item =null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.DOC_CRONOGRAMA_ACADEMICO_VENCIMIENTO WHERE INSTITUCION=? AND PERIODO=? AND VENCIMIENTO=? ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);            
            cs.setInt(3, vencimiento);            
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cronogramaAcademicoVencimientoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setTitulo(rs.getString("TITULO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setSemana(rs.getInt("SEMANAS"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return item;
    }
     
     
     public List<cronogramaAcademicoVencimientoC> mostrarCronogramaAcademicoVencimiento(int institucion, String periodo  ) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        cronogramaAcademicoVencimientoC item =null;
        List<cronogramaAcademicoVencimientoC> lista=new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.DOC_CRONOGRAMA_ACADEMICO_VENCIMIENTO WHERE INSTITUCION=? AND PERIODO=?  ");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);            
               
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cronogramaAcademicoVencimientoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setTitulo(rs.getString("TITULO"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setSemana(rs.getInt("SEMANAS"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
