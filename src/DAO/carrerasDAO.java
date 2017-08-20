
package DAO;

import Conexiones.Conexion;

import ENTIDAD.carrerasC;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class carrerasDAO extends Conexion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public carrerasC mostrarCarrerasCodigo(String carrera) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC item = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from [UPA].[ACA_CARRERAS] where CARRERA=?");

            cs.setString(1, carrera);

            rs = cs.executeQuery();

            while (rs.next()) {
            	item = new carrerasC();
            	item.setCarrera(rs.getString("CARRERA"));
            	item.setDescripcion(rs.getString("DESCRIPCION"));
            	item.setAbreviatura(rs.getString("ABREVIATURA"));
            	item.setColor(rs.getString("COLOR"));
            	item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return item;
    }
	
	


	public List<carrerasC> listaCarrera() {

        List<carrerasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC carre ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT * from [UPA].[ACA_CARRERAS] ");
            rs = cs.executeQuery();

            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<carrerasC> listaCarreraInstitucion(int institucion) {

        List<carrerasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC carre ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select A.* from [UPA].[ACA_CARRERAS] A ,SIGU.ACA_INSTITUCION_CARRERA B where A.CARRERA =B.CARRERA AND B.INSTITUCION=? AND A.ESTADO_REGISTRO=1   ORDER BY DESCRIPCION");
            cs.setInt(1, institucion);
            rs = cs.executeQuery();

            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }

   
    
    
    public List<carrerasC> listaCarreraPeriodo(int institucion, String periodo) {
        List<carrerasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC carre ;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT B.*FROM SIGU.HOR_CURSO_PERIODO A,UPA.ACA_CARRERAS B WHERE A.INSTITUCION=? AND A.PERIODO=? AND B.CARRERA=A.CARRERA ORDER BY B.DESCRIPCION");

            cs.setInt(1, institucion);
            cs.setString(2, periodo);

            rs = cs.executeQuery();

            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
    
    public List<carrerasC> mostrarCarreraPracticaInduccion(int institucion,String estadoRegistro) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;
        carrerasC item ;
        List<carrerasC> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT C.* FROM DI.ACA_ALUMNO_CURSO_PRACTICA A,SIGU.ACA_ALUMNO_CARRERA B,UPA.ACA_CARRERAS C WHERE B.INSTITUCION=? AND A.ESTADO_REGISTRO LIKE ? AND A.ALUMNO=B.ALUMNO AND C.CARRERA=B.CARRERA ORDER BY DESCRIPCION");

            cs.setInt(1, institucion);
            cs.setString(2, estadoRegistro);
           

            rs = cs.executeQuery();

            while (rs.next()) {

                item = new carrerasC();
                item.setCarrera(rs.getString("CARRERA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
        	System.out.println(e.getMessage());
        }
        return lista;
    }

    


    

   

    public carrerasC mostrarCarreraAlumno(int institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC carre = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT C.CARRERA,C.DESCRIPCION,C.ABREVIATURA,C.ESTADO_REGISTRO    FROM [SIGU].[ACA_ALUMNO_CARRERA] AC, [UPA].[ACA_CARRERAS] C  where C.CARRERA=AC.CARRERA AND AC.ESTADO_REGISTRO in(6,1) AND AC.INSTITUCION=? AND AC.ALUMNO=?");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);

            rs = cs.executeQuery();
            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            
        }
        return carre;
    }
    
    public List<carrerasC> mostrarListaCarreraAlumno(int institucion, String alumno) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC item = null;
        List<carrerasC>lista=new ArrayList<>();
        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.* FROM SIGU.ACA_ALUMNO_CARRERA A,UPA.ACA_CARRERAS B WHERE A.INSTITUCION=? AND A.ALUMNO=? AND A.ESTADO_REGISTRO in(6,1) AND B.CARRERA=A.CARRERA");
            cs.setInt(1, institucion);
            cs.setString(2, alumno);

            rs = cs.executeQuery();
            while (rs.next()) {
            	item = new carrerasC();
            	item.setCarrera(rs.getString("CARRERA"));
            	item.setDescripcion(rs.getString("DESCRIPCION"));
            	item.setAbreviatura(rs.getString("ABREVIATURA"));
            	item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
            	lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return lista;
    }

    public List<carrerasC> mostrarCarrerasDocentePeriodo(int institucion, String periodo, String persona) {

        List<carrerasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC carre ;

        try {

            c = ConexionWeb();

            cs = c.prepareCall("SELECT distinct C.CARRERA,C.DESCRIPCION,C.ABREVIATURA,C.ESTADO_REGISTRO FROM [UPA].[ACA_CARRERAS] C JOIN SIGU.HOR_CURSO_SECCION_DOCENTE SD on C.CARRERA=SD.CARRERA JOIN SIGU.PER_PERSONAL PP ON PP.PERSONAL=SD.PERSONAL JOIN UPA.DAT_PERSONAS DP ON DP.PERSONA=PP.PERSONA WHERE SD.INSTITUCION like ? AND SD.PERIODO=? and DP.PERSONA=?  AND SD.ESTADO_REGISTRO in (1,59) order by CARRERA");
            
            cs.setString(1, institucion==0?"%":Integer.toString(institucion));
            cs.setString(2, periodo);
            cs.setString(3, persona);

            rs = cs.executeQuery();
            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public List<carrerasC> mostrarCarrerasPersonalSecundario(int institucion, String periodo, String personal) {

        List<carrerasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC carre ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT B.*FROM DI.HOR_CURSO_SECCION_DONCENTE_TIPO_EXAMEN A,UPA.ACA_CARRERAS B WHERE A.CARRERA=B.CARRERA AND A.INSTITUCION=? AND A.PERIODO=? AND A.PERSONAL=?");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            rs = cs.executeQuery();
            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<carrerasC> mostrarCarreras(String institucion, String periodo, String personal) {

        List<carrerasC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        carrerasC item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT A.*FROM UPA.ACA_CARRERAS A, SIGU.HOR_CURSO_SECCION_DOCENTE B WHERE A.CARRERA=B.CARRERA AND B.INSTITUCION like ? AND B.PERIODO=? AND B.PERSONAL=? AND B.ESTADO_REGISTRO=1");

            cs.setString(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, personal);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new carrerasC();
                item.setCarrera(rs.getString("CARRERA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<ArrayList<String>> horarioDocente(int institucion, int periodo, String carrera, String persona) {

        Connection c;
        CallableStatement cs;
        ResultSet rs;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_HORARIO_DOCENTE_CARRERA (?,?,?,?)}");

            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, persona);

            rs = cs.executeQuery();

            while (rs.next()) {

                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("ITEM"));
                array.add(rs.getString("HORA"));
                array.add(rs.getString("LUNES"));
                array.add(rs.getString("MARTES"));
                array.add(rs.getString("MIERCOLES"));
                array.add(rs.getString("JUEVES"));
                array.add(rs.getString("VIERNES"));
                array.add(rs.getString("SABADO"));
                array.add(rs.getString("DOMINGO"));

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
         
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
    
    public List<carrerasC> cuentaPersonaCarreras(String codigoAlumno, String periodo) {
        List<carrerasC> lista = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        carrerasC carre = null;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT\n"
                    + "DISTINCT AAC.CARRERA,AC.DESCRIPCION,AC.ABREVIATURA\n"
                    + "FROM\n"
                    + "SIGU.ACA_ALUMNO AA JOIN SIGU.ACA_ALUMNO_CARRERA AAC\n"
                    + "ON AA.ALUMNO=AAC.ALUMNO AND AA.INSTITUCION=AAC.INSTITUCION JOIN SIGU.ACA_ALUMNO_PERIODO AAP\n"
                    + "ON AAP.ALUMNO=AA.ALUMNO AND AAP.INSTITUCION=AA.INSTITUCION JOIN UPA.ACA_CARRERAS AC\n"
                    + "ON AAC.CARRERA=AC.CARRERA JOIN SIGU.TES_CUENTA_PERSONA TCP\n"
                    + "ON TCP.CARRERA = AC.CARRERA AND AA.PERSONA=TCP.PERSONA AND TCP.INSTITUCION=AA.INSTITUCION\n"
                    + "WHERE \n"
                    + "AA.ALUMNO = ?\n"
                    + "AND AAP.PERIODO = ?");
            
            cs.setString(1, codigoAlumno);
            cs.setString(2, periodo);
            rs = cs.executeQuery();
            while (rs.next()) {
                carre = new carrerasC();
                carre.setCarrera(rs.getString("CARRERA"));
                carre.setDescripcion(rs.getString("DESCRIPCION"));
                carre.setAbreviatura(rs.getString("ABREVIATURA"));
                //carre.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(carre);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return lista;
    }
    
    
    public List<carrerasC> mostrarCarreraPrincipal(int institucion, String periodo) {
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
        List<carrerasC> lista = new ArrayList<>();
        carrerasC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SP_MOSTRAR_HOR_CURSO_SECCION_DOCENTE] (5,?,?,'%','%','%','%','%')}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new carrerasC();
                item.setCarrera(rs.getString("CARRERA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public List<carrerasC> mostrarCarreraPrincipal(int institucion, String periodo,String persona) {
        Connection c ;
        CallableStatement cs  ;
        ResultSet rs ;
        List<carrerasC> lista = new ArrayList<>();
        carrerasC item ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_HOR_CURSO_SECCION_DOCENTE (6,?,?,'%','%','%','%','%',?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new carrerasC();
                item.setCarrera(rs.getString("CARRERA"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
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
