/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.explorardorArchivo;
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
import javax.swing.JOptionPane;

public class exploradorArchivoDAO {
    public List<explorardorArchivo.cursos> mostrarCursosDoncete(int institucion,String periodo,String alumno) {
        List<explorardorArchivo.cursos> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        explorardorArchivo.cursos item ;
        
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_CURSO_DOCENTE_ALUMNO (?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, alumno);
           
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new explorardorArchivo.cursos();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                
                
                
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
