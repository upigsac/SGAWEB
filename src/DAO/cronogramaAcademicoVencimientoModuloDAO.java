/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.cronogramaAcademicoVencimientoModuloC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cronogramaAcademicoVencimientoModuloDAO {
     public List<cronogramaAcademicoVencimientoModuloC> mostrarCronogramaAcademicoVencimientoModulo(int institucion, String periodo,  int vencimiento) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<cronogramaAcademicoVencimientoModuloC> lista=new ArrayList<>();
        cronogramaAcademicoVencimientoModuloC item =null;

        try {
            System.out.println(institucion);
            System.out.println(periodo);
            System.out.println(vencimiento);
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DI.DOC_CRONOGRAMA_ACADEMICO_VENCIMIENTO_MODULO WHERE INSTITUCION=? AND PERIODO=? AND VENCIMIENTO=? ORDER BY MODULO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);            
            cs.setInt(3, vencimiento);            
            rs = cs.executeQuery();

            while (rs.next()) {

                item = new cronogramaAcademicoVencimientoModuloC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setModulo(rs.getInt("MODULO"));
                item.setSemanas(rs.getInt("SEMANAS"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setFechaInicio(rs.getDate("FECHA_INICIO"));
                item.setPieModulo(rs.getString("PIE_MODULO"));
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
