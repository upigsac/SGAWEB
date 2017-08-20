package DAO;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.constanciaPractica;


public class constanciaPracticaDAO {
	
	public List<constanciaPractica.constanciaC> mostrarPracticaCostancia(String desPersona, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs;
        List<constanciaPractica.constanciaC> lista = new ArrayList<>();
        constanciaPractica.constanciaC item ;

        try {
        
        	
            c = ConexionWeb();
            cs = c.prepareCall("SELECT TOP 50 A.ALUMNO,A.CURSO,A.OFICINA,A.TUTOR,A.OBSERVACION,A.INFORME,A.FECHAINFORME,A.FECHACONSTANCIA,A.ESTADO_REGISTRO,C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES,D.FECHAINICIO,D.FECHAFINAL FROM DI.ACA_ALUMNO_CURSO_PRACTICA A,SIGU.ACA_ALUMNO B ,UPA.DAT_PERSONAS C,DI.ACA_ALUMNO_CURSO_PRACTICA_CRONOGRAMA D WHERE  C.APELLIDO_PATERNO +' '+ C.APELLIDO_MATERNO +' '+ C.NOMBRES LIKE ? AND A.ALUMNO LIKE ? AND A.ESTADO_REGISTRO=53 AND B.INSTITUCION=1 AND B.ALUMNO=A.ALUMNO  AND C.PERSONA=B.PERSONA AND D.ALUMNO=A.ALUMNO AND D.CURSO=A.CURSO AND D.ESTADO_REGISTRO=1 ");
            cs.setString(1,"%" + desPersona + "%");
            cs.setString(2, alumno + "%");
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new constanciaPractica.constanciaC();
                item.getAlumnoPractica().setAlumno(rs.getString("ALUMNO"));
                item.getAlumnoPractica().setCurso(rs.getString("CURSO"));
                item.getAlumnoPractica().setOficina(rs.getInt("OFICINA"));
                item.getAlumnoPractica().setTutor(rs.getString("TUTOR"));
                item.getAlumnoPractica().setObservacion(rs.getString("OBSERVACION"));
                item.getAlumnoPractica().setInforme(rs.getString("INFORME"));
                item.getAlumnoPractica().setFechaInforme(rs.getDate("FECHAINFORME"));
                item.getAlumnoPractica().setFechaConstancia(rs.getDate("FECHACONSTANCIA"));
                item.getAlumnoPractica().setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
               
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));
                item.setFechaInicio(rs.getDate("FECHAINICIO"));
                item.setFechaFinal(rs.getDate("FECHAFINAL"));
               
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
