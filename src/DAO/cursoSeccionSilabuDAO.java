/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.administrarSilabus;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cursoSeccionSilabuC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class cursoSeccionSilabuDAO {
    
    
    public String insertar(cursoSeccionSilabuC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_HOR_CURSO_SECCION_SILABUS(1,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getSilabu());
            cs.setString(8, item.getRuta());
            cs.setString(9, item.getFormato());
            cs.setDouble(10, item.getPeso());
            cs.setInt(11, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                util.consolaCliente( "guardo..");
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
    
    public String eliminar(cursoSeccionSilabuC item) {
     
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_HOR_CURSO_SECCION_SILABUS(2,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getMalla());
            cs.setString(5, item.getCurso());
            cs.setString(6, item.getSeccion());
            cs.setString(7, item.getSilabu());
            cs.setString(8, item.getRuta());
            cs.setString(9, item.getFormato());
            cs.setDouble(10, item.getPeso());
            cs.setInt(11, item.getEstadoRegistro());

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
                util.consolaCliente( "ELIMINADO..");
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }

     public List<administrarSilabus.listaSilabus> mostrarDetalleSilabu(int institucion,String periodo,String carrera,String seccion,String desPersona,String personal,String estadoArchivo) {

        List<administrarSilabus.listaSilabus> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        administrarSilabus.listaSilabus item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_HOR_CURSO_SECCION_SILABUS (1,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            cs.setString(5, desPersona);
            cs.setString(6, personal);
            cs.setString(7, estadoArchivo);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new administrarSilabus.listaSilabus();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));
                item.setFechaInicio(rs.getDate("FEC_CLASES_INI"));
                item.setFechaFinal(rs.getDate("FEC_CLASES_FIN"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setSilabu(rs.getString("SILABUS"));
                item.setRuta(rs.getString("RUTA"));
                item.setPeso(rs.getString("PESO"));
                item.setFormato(rs.getString("FORMATO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }
}
