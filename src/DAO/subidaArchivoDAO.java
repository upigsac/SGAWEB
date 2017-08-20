
package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import ENTIDAD.subidaArchivoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class subidaArchivoDAO {
    public List<subidaArchivoC> mostrarSubidaArchivo(int institucion,String periodo,String carrera,String curso,String seccion,String carpeta) {
    
        
        List<subidaArchivoC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        subidaArchivoC item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM SIGU.SUBIDA_ARCHIVOS WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND CURSO=? AND SECCION=? AND TIPO_EXAMEN=? AND RUTAARCHIVO LIKE ?  AND ESTADO_REGISTRO=5");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, carpeta);
            cs.setString(7, "%");
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new subidaArchivoC();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setPersonal(rs.getString("DOCENTE"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                //item.setSemana(rs.getString("SEMANA"));                
                item.setCarpeta(rs.getString("TIPO_EXAMEN"));
                item.setArchivo(rs.getString("NOMBREARCHIVO"));
                item.setRuta(rs.getString("RUTAARCHIVO"));
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
