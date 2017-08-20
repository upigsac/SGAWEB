/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Beans.matriculaUpig;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.alumnoPeriodoC;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.util;


public class matriculaUpigDAO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public List<matriculaUpig.asignaturasAsigandas> mostrarCursosAlumno(int institucion, String periodo, String malla, String carrera, String alumno) {

        List<matriculaUpig.asignaturasAsigandas> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        matriculaUpig.asignaturasAsigandas item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MATRICULA_UPIG_CURSO_ASIGNADOS (?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, alumno);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new matriculaUpig.asignaturasAsigandas();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCiclo(rs.getInt("NIVEL_MODULAR"));
                item.setCurso(rs.getString("CURSO"));
                item.setDesCurso(rs.getString("DESCURSO"));
                item.setHoraPractica(rs.getInt("HORAS_PRACTICA"));
                item.setHoraTeoria(rs.getInt("HORAS_TEORIA"));
                item.setCreditos(rs.getInt("CREDITOS"));
                item.setTipoCurso(rs.getInt("TIPO_CURSO"));
                item.setDesTipoCurso(rs.getString("DESTIPOCURSO"));
                item.setVeces(rs.getInt("VECES")+1);
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error " + e.getMessage());
        }

        return lista;
    }
    
    
    public List<matriculaUpig.seccionesAsigandas> mostrarSeccionCurso(int institucion, String periodo, String malla, String carrera, String curso) {
      
        List<matriculaUpig.seccionesAsigandas> lista = new ArrayList<>();

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        matriculaUpig.seccionesAsigandas item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MATRICULA_UPIG_SECCION_ASIGNADOS (?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, malla);
            cs.setString(4, carrera);
            cs.setString(5, curso);

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new matriculaUpig.seccionesAsigandas();
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setMatriculados(rs.getInt("VACANTES_ACTUALES"));
                item.setCapacidad(rs.getInt("VACANTES_MAXIMAS"));
                item.setTurno(rs.getInt("TURNO"));
                item.setDesTurno(rs.getString("DESTURNO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setPersona(rs.getString("PERSONA"));
                item.setDesPersona(rs.getString("DESPERSONA"));
                
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error " + e.getMessage());
        }

        return lista;
    }
    
    
    
    
    public boolean InsertaMatricula(alumnoPeriodoC alumnoPeriodo,alumnoCursoSeccionC alumnoCursoSeccion) {
        Connection c = null;
        CallableStatement insertcab ;
        CallableStatement insertdet ;
        boolean rp = true;
        try {
            c = ConexionWeb();
            c.setAutoCommit(false);
            insertcab = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_PERIODO (?,?,?,?,?,?,?,?) }");
            insertcab.setInt(1, alumnoPeriodo.getInstitucion());
            insertcab.setString(2, alumnoPeriodo.getPeriodo());
            insertcab.setString(3, alumnoPeriodo.getAlumno());
            insertcab.setString(4, alumnoPeriodo.getCarrera());
            insertcab.setString(5, alumnoPeriodo.getMalla());
            insertcab.setString(6, alumnoPeriodo.getCategoria());
            insertcab.setString(7, alumnoPeriodo.getDescuento());
            insertcab.setInt(8, alumnoPeriodo.getEstado_registro());
            
            insertcab.executeUpdate();

            insertdet = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CURSO_SECCION(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?)}");

            insertdet.setInt(1, alumnoCursoSeccion.getInstitucion());
            insertdet.setString(2, alumnoCursoSeccion.getPeriodo());
            insertdet.setString(3, alumnoCursoSeccion.getCarrera());
            insertdet.setString(4, alumnoCursoSeccion.getMalla());
            insertdet.setString(5, alumnoCursoSeccion.getCurso());
            insertdet.setString(6, alumnoCursoSeccion.getSeccion());
            insertdet.setString(7, alumnoCursoSeccion.getAlumno());
            insertdet.setString(8, alumnoCursoSeccion.getGrupo());                    
            insertdet.setDouble(9, alumnoCursoSeccion.getPromedio());
            insertdet.setInt(10, 0); //resolucion
            insertdet.setString(11, ""); //observacion                    
            insertdet.setInt(12, 0); //convalidado
            insertdet.setString(13, ""); //persona convalida
            insertdet.setString(14, ""); //AUT_PER_REGISTRO
            insertdet.setString(15, ""); //AUT_doc_REGISTRO
            insertdet.setString(16, ""); //AUT_obs_REGISTRO
            insertdet.setInt(17, alumnoCursoSeccion.getEstadoRegistro());
                    
            c.commit();

            cerrarConexion(c);
        } catch (SQLException e) {
            if (c != null) {
                try {
                    deshacerCambios(c);
                    rp = false;
                } catch (Exception ex) {
                    util.consolaCliente( ex.getMessage());
                    
                }

            }
             util.consolaCliente( e.getMessage());
            

        }
        return rp;
    }
}
