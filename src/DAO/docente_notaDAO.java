
package DAO;

import Conexiones.Conexion;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.admin_notas;
import Beans.util;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;



public class docente_notaDAO extends Conexion implements Serializable {

	private static final long serialVersionUID = 1L;

	public String insertarNotaAlumno(int institucion, String periodo, String malla, String carrera, String curso, String seccion, String alumno, String tipo, double nota) {
		System.out.println ("---jesus----");
		System.out.println (institucion);
		System.out.println (periodo);
		System.out.println (malla);
		System.out.println (carrera);
		System.out.println (curso);
		System.out.println (seccion);
		System.out.println (alumno);
		System.out.println (tipo);
		System.out.println (nota);
		System.out.println ("---jesus----");
		 
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        String promedio="";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL  SIGU.WEB_SQL_ACA_ALUMNO_CURSO_NOTA  (1, ?, ?, ?, ?, ?, ?, ?, ?,?,'','','1',?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
            cs.setString(8, tipo);
            cs.setDouble(9, nota);
            cs.setString(10, "");
            cs.registerOutParameter(10, Types.VARCHAR);
            
            rpta = cs.executeUpdate() >= 1 ;
            
            
            rpta=true;
            if (rpta) {
                
                c.commit();
                promedio=cs.getString(10);
               
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente("error insertar SIGU.WEB_SQL_ACA_ALUMNO_CURSO_NOTA " + e.getMessage());
            
        }
        return promedio;
    }

    public boolean insertarNotaAlumnoPromedio(int institucion, int periodo, String malla, String carrera, String curso, String seccion, String alumno, String tipo, double nota) {
        Connection c = null;
        CallableStatement cs ;
        CallableStatement ds ;
        boolean rpta = false;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  SIGU.WEB_SQL_ACA_ALUMNO_CURSO_NOTA  (1, ?, ?, ?, ?, ?, ?, ?, ?,?,'','','1','')}");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, alumno);
            cs.setString(8, tipo);
            cs.setDouble(9, nota);
            cs.executeUpdate();

            ds = c.prepareCall("UPDATE [SIGU].[ACA_ALUMNO_CURSO_SECCION]  set PROMEDIO=(SIGU.SF_PROMEDIO_ALUMNO_CURSO(1, 1, PERIODO, CARRERA,MALLA,curso, ALUMNO,''))\n"
                    + " where INSTITUCION=? AND PERIODO=? and CARRERA=? \n"
                    + " and curso=? and  SECCION=? and ALUMNO=? ");

            ds.setInt(1, institucion);
            ds.setInt(2, periodo);
            ds.setString(3, carrera);
            ds.setString(4, curso);
            ds.setString(5, seccion);
            ds.setString(6, alumno);

            c.commit();

            cerrarConexion(c);
        } catch (SQLException e) {
            if (c != null) {
                try {
                    deshacerCambios(c);
                    rpta = false;
                } catch (Exception ex) {
                    util.consolaCliente( ex.getMessage());                    
                }

            }
             util.consolaCliente( e.getMessage());

        }

        return rpta;
    }

    public boolean insertarPromedioAlumno(int institucion, String periodo, String carrera, String curso, String seccion, String alumno) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("UPDATE [SIGU].[ACA_ALUMNO_CURSO_SECCION] set PROMEDIO=ROUND(SIGU.SF_PROMEDIO_ALUMNO_CURSO(1, INSTITUCION, PERIODO, CARRERA,MALLA,CURSO, ALUMNO,''),0) where INSTITUCION=? AND PERIODO=? and CARRERA=? and curso=? and  SECCION=? and ALUMNO=? ");

            
            cs.setDouble(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, alumno);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

        }
        return rpta;
    }

    public List<HashMap<String,String>> getDocente_notas(int institucion, String periodo, String malla,String grupo, String carrera, String curso, String seccion,  String subnotas,String estadoAlumno) {
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;        
        ResultSetMetaData rss ;           
        
        Map<String,String> map = new HashMap<>();        
        List<HashMap<String,String>> lista = new ArrayList<>(); 

        try {

            c = ConexionWeb();
         
            
            if (subnotas.isEmpty()) {
                cs = c.prepareCall("{CALL  SIGU.CON_ALUMNO_CURSO_NOTA  (2, ?, ?, ?, ?, ?, ?, '', ?,'','%',?)} ");
            } else {
                cs = c.prepareCall("{CALL  SIGU.CON_ALUMNO_CURSO_NOTA  (3, ?, ?, ?, ?, ?, ?, '', ?, '" + subnotas + "','%',?)} ");
            }
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, grupo);
            cs.setString(8, estadoAlumno);

            rs = cs.executeQuery();
            rss = rs.getMetaData();            
              while (rs.next()) {
                map = new HashMap<>();
                for (int x = 0; x < rss.getColumnCount(); x++) {
                    map.put(rss.getColumnLabel(x+1).toString().toLowerCase(), rs.getString(rss.getColumnLabel(x+1).toString()));
                }
               lista.add((HashMap<String, String>) map);
                
            }

            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error docente_nota xx " + e.getMessage());
            
            //e.printStackTrace();
        }

        return lista;
    }

    public List<ArrayList<String>> getColumna(int institucion, String periodo, String malla, String carrera, String curso, String subnotas) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<ArrayList<String>> lista = new ArrayList<>();
        try {
            c = ConexionWeb();
            if (subnotas.isEmpty()) {
                cs = c.prepareCall("{CALL SIGU.CON_CURSO_PERIODO (1, ?,?,?,?,?,0,'') }");
            } else {

                cs = c.prepareCall("{CALL SIGU.CON_CURSO_PERIODO (3, ?,?,?,?,?,0,'" + subnotas + "') }");
            }

            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);

            rs = cs.executeQuery();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("TIPO_EXAMEN"));
                array.add(rs.getString("DESEXAMEN"));
                array.add(rs.getString("DESLINEA1"));
                array.add(rs.getString("DESLINEA2"));
                array.add(rs.getString("FORMULA"));
                array.add(rs.getString("LECTURA"));
                array.add(rs.getString("NRO_MIN"));
                array.add(rs.getString("NRO_MAX"));
                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error getColumna " + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }

    public List<ArrayList<String>> getformula(String periodo, String malla, String carrera, String curso, String subnotas) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<ArrayList<String>> lista = new ArrayList<>();
        try {

            c = ConexionWeb();
            if (subnotas.isEmpty()) {
                cs = c.prepareCall("{CALL SIGU.CON_CURSO_PERIODO (1, 1,?,?,?,?,0,'') }");
            } else {
                cs = c.prepareCall("{CALL SIGU.CON_CURSO_PERIODO (3, 1,?,?,?,?,0,'" + subnotas + "') }");
            }

            cs.setString(1, periodo);
            cs.setString(2, carrera);
            cs.setString(3, malla);
            cs.setString(4, curso);

            rs = cs.executeQuery();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();

                array.add(rs.getString("TIPO_EXAMEN"));
                array.add(rs.getString("DESEXAMEN"));
                array.add(rs.getString("DESLINEA1"));
                array.add(rs.getString("DESLINEA2"));
                array.add(rs.getString("FORMULA"));
                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }

    public boolean getEditable(String periodo, String malla, String carrera, String curso, String seccion, String docente, String tipo) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        boolean editable = false;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL sp_control_columna_nota_java (?,?,?,?,?,?,?)}");
            cs.setString(1, periodo);
            cs.setString(2, malla);
            cs.setString(3, carrera);
            cs.setString(4, curso);
            cs.setString(5, seccion);
            cs.setString(6, docente);
            cs.setString(7, tipo);
            rs = cs.executeQuery();

            while (rs.next()) {

                editable = true;
            }

            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error " + e.getMessage());
            //e.printStackTrace();
        }

        return editable;
    }

    public boolean controlNotaLibre(int institucion ,String periodo, String carrera, String malla, String curso, String seccion,  String tipoExamen,String personal) {

        
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        boolean editable = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CONTROL_FECHA_NOTAS (?,?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);            
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);            
            cs.setString(7, tipoExamen);
            cs.setString(8, personal);
            rs = cs.executeQuery();

            while (rs.next()) {

                editable = true;
            }

            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            
        }

        return editable;
    }
    
    // DOCENTE SECUNDARIO //
    
     public List<HashMap<String,String>>  mostrarAlumnosTipoExamen(int institucion, String periodo, String malla,String grupo, String carrera, String curso, String seccion,  String subnotas,String grupoExamen) {
        Connection c ;
        CallableStatement cs;
        ResultSet rs ;
        
        ResultSetMetaData rss ;       
        Map<String,String> map = new HashMap<>();
        
        List<HashMap<String,String>> lista = new ArrayList<>();       
        
        
        
        System.out.println(institucion);
        System.out.println( periodo);
        System.out.println( carrera);
        System.out.println( malla);
        System.out.println( curso);
        System.out.println( seccion);
        System.out.println( grupo);
        System.out.println( subnotas);
        System.out.println( grupoExamen);
         
        try {

            c = ConexionWeb();

           
            cs = c.prepareCall("{CALL  SIGU.CON_ALUMNO_CURSO_NOTA  (4, ?, ?, ?, ?, ?, ?, '', ?,?,?)} ");
          
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, malla);
            cs.setString(5, curso);
            cs.setString(6, seccion);
            cs.setString(7, grupo);
            cs.setString(8, subnotas);
            cs.setString(9, grupoExamen);

            rs = cs.executeQuery();
            rss = rs.getMetaData();

            while (rs.next()) {
                
                
                map = new HashMap<>();
                for (int x = 0; x < rss.getColumnCount(); x++) {
                    
                    //System.out.println(rss.getColumnLabel(x+1).toString() + " - " +rs.getString(rss.getColumnLabel(x+1).toString()));
                    map.put(rss.getColumnLabel(x+1).toString().toLowerCase(), rs.getString(rss.getColumnLabel(x+1).toString()));
                    
                    
                }
               lista.add((HashMap<String, String>) map);
                

            }

            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            util.consolaCliente( "error mostrarAlumnosTipoExamen  " + e.getMessage());
         
            //e.printStackTrace();
        }

        return lista;
    }
     
     
     public List<admin_notas.busquedaC>  busquedaPersonal(int institucion, String periodo, String desPersona,String desCurso) {
         Connection c ;
         CallableStatement cs;
         ResultSet rs ;
         
                
         
         admin_notas.busquedaC item=null;
         List<admin_notas.busquedaC> lista = new ArrayList<>();       
         
          
         try {

             c = ConexionWeb();

            
             cs = c.prepareCall("SELECT TOP 50 C.PERSONA,C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES,A.INSTITUCION,A.PERIODO,A.CARRERA,A.CURSO,D.DESCRIPCION DESCURSO,A.SECCION,E.DESCRIPCION DESSECCION,FA.DESCRIPCION FRECUENCIA_DIA,FB.DESCRIPCION FRECUENCIA_HORA FROM SIGU.HOR_CURSO_SECCION_DOCENTE A,SIGU.PER_PERSONAL B, UPA.DAT_PERSONAS C,UPA.ACA_CURSOS D,SIGU.HOR_SECCION E,SIGU.HOR_PERIODO_SECCION F LEFT JOIN DI.VEN_FRECUENCIA_DIA FA ON FA.FRECUENCIA_DIA=F.FRECUENCIA_DIA LEFT JOIN DI.VEN_FRECUENCIA_HORA FB ON FB.FRECUENCIA_DIA=FA.FRECUENCIA_DIA AND FB.FRECUENCIA_HORA=F.FRECUENCIA_HORA WHERE A.INSTITUCION LIKE ? AND A.PERIODO=? AND  C.APELLIDO_PATERNO +' '+ C.APELLIDO_MATERNO +' '+ C.NOMBRES LIKE  ? AND D.DESCRIPCION LIKE ? AND A.ESTADO_REGISTRO IN(1,59) AND B.PERSONAL=A.PERSONAL AND C.PERSONA=B.PERSONA AND D.CURSO=A.CURSO AND E.INSTITUCION=A.INSTITUCION AND E.SECCION=A.SECCION AND F.INSTITUCION=A.INSTITUCION AND F.PERIODO=A.PERIODO AND F.CARRERA=A.CARRERA AND F.SECCION=A.SECCION ");
								           
             cs.setInt(1, institucion);
             cs.setString(2, periodo);
             cs.setString(3,"%"+ desPersona+"%");
             cs.setString(4,"%"+ desCurso+"%");
             
             rs = cs.executeQuery();
             

             while (rs.next()) {
            	 item=new admin_notas.busquedaC();
                 item.setInstitucion(rs.getInt("INSTITUCION"));
                 item.setPeriodo(rs.getString("PERIODO"));
                 item.setCarrera(rs.getString("CARRERA"));
                 item.setCurso(rs.getString("CURSO"));
                 item.setSeccion(rs.getString("SECCION"));
                 item.setDesSeccion(rs.getString("DESSECCION"));
                 item.setDesCurso(rs.getString("DESCURSO"));
                 item.setFrecuenciaDia(rs.getString("FRECUENCIA_DIA"));
                 item.setFrecuenciaHora(rs.getString("FRECUENCIA_HORA"));
                 item.setPersona(rs.getString("PERSONA"));                 
                 item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                 item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                 item.setNombres(rs.getString("NOMBRES"));
                 
                 
              
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
