/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.consolidadoNotas;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class consolidadoNotasDAO {
     public List<consolidadoNotas.detalleNotas> mostrarConsolidadoNotas(int institucion,String periodo,String alumno,String curso,String tipoExamenPadre) {

        List<consolidadoNotas.detalleNotas> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        consolidadoNotas.detalleNotas item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CONSOLIDADO_NOTAS(?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
            cs.setString(4, curso);
            cs.setString(5, tipoExamenPadre);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new consolidadoNotas.detalleNotas();
                item.setFormula(rs.getInt("FORMULA"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setLinea1(rs.getString("DESLINEA1"));
                item.setLinea2(rs.getString("DESLINEA2"));
                item.setNota(rs.getInt("NOTA"));
                item.setTipoExamenPadre(rs.getString("TIPO_EXAMEN_PADRE"));
                item.setFechaInicio(rs.getString("FECHA_INICIO"));
                item.setFechaFin(rs.getString("FECHA_FIN"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
