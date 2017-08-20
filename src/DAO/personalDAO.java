
package DAO;

import Beans.docenteSinNota;
import Beans.docente_notas;
import Beans.util;
import Conexiones.Conexion;

import ENTIDAD.personalC;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java.util.List;


public class personalDAO extends Conexion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
public String insertarCodigoDocente(int  modalidad,String periodo,String personal,String persona,String cpersonal) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta =true;
        String codigo=null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_GENERAR_CODIGO_DOCENTE (1,?,?,?,?,?)}");
            cs.setInt(1, modalidad);
            cs.setString(2, periodo);
            cs.setString(3, personal);
            cs.setString(4, persona);
            cs.setString(5, cpersonal);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.executeUpdate() ;
            if (rpta) {
                c.commit();
                codigo=cs.getString(5); 
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return codigo;
    }

	public String insertarPersonal(personalC item) {
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo="";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_PERSONAL (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("PERSONAL", item.getPersonal());
            cs.setString("PERSONA", item.getPersona());
            cs.setString("CPERSONAL", item.getcPersonal());
            cs.setBoolean("PLANILLA", item.isCodigoPlanilla());
            cs.setInt("SEDE", item.getSede());
            cs.setInt("OFICINA", item.getOficina());
            cs.setString("CUSPP", item.getCuspp());
            cs.setInt("TIPO_PERSONAL", item.getTipoPersonal()); // tipo trabajaro
            cs.setInt("SITUACION", item.getSituacion());
            cs.setInt("TURNO", item.getTurno());
            cs.setInt("AFILIADO_EPS", 1); //afiliado
            
            cs.setInt("EPS", item.getEps());
            cs.setInt("REGIMEN_PENSION", item.getRegimenPension());
            cs.setInt("TIPO_REGIMEN", item.getTipoRegimen());
            cs.setInt("OCUPACION", item.getOcupacion());
            cs.setInt("CARGO", item.getCargo());
            cs.setInt("FORMA_PAGO", item.getFormaPago());
            cs.setInt("TIPO_MONEDA", item.getTipoMoneda());
            cs.setString("CTS", item.getNumeroCTS());
            cs.setString("ESSALUD", item.getEsSalud());
            
           
            cs.setString("FECHA_INICIO_REGIMEN",  util.convertDate(item.getFechaRegimen(),"dd-MM-yyyy") );
            cs.setString("FECHA_INGRESO_EMPRESA", util.convertDate(item.getFechaIngreso(),"dd-MM-yyyy"));
            cs.setInt("BANCO_REMUNERACION", item.getBancoRemuneracion());
            cs.setString("NRO_CUENTA", item.getNumeroCuenta());
            cs.setInt("BANCO_CTS", item.getBancoCTS());
            cs.setInt("CENTRO_COSTO", item.getCentroCosto());
            cs.setInt("ESTADO", item.getEstadoRegistro());          
            cs.registerOutParameter("PERSONAL", Types.VARCHAR);
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
                codigo=cs.getString("PERSONAL"); 
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            //e.printStackTrace();
        }
        return codigo;
    }

    public List<ArrayList<String>> docenteAutorizacion(String carrera, String curso, String seccion) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<ArrayList<String>> lista = new ArrayList<>();

        try {

            c = ConexionWeb();
            cs = c.prepareCall(" select distinct C.APELLIDO_PATERNO,C.APELLIDO_MATERNO,C.NOMBRES,A.PERSONAL\n"
                    + " FROM [SIGU].[HOR_CURSO_SECCION_DOCENTE] A,\n"
                    + " [SIGU].[PER_PERSONAL] B ,UPA.DAT_PERSONAS C, [UPA].[ACA_CURSOS] D, SIGU.HOR_SECCION HC\n"
                    + " WHERE A.PERSONAL=B.PERSONAL AND C.PERSONA=B.PERSONA AND D.CURSO=A.CURSO AND hc.SECCION=a.SECCION AND\n"
                    + " A.CARRERA LIKE ? and A.CURSO LIKE ? AND A.SECCION LIKE ? UNION SELECT '','','TODOS','%'");

            cs.setString(1, carrera);
            cs.setString(2, curso);
            cs.setString(3, seccion);

            rs = cs.executeQuery();

            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();

                array.add(rs.getString("APELLIDO_PATERNO"));
                array.add(rs.getString("APELLIDO_MATERNO"));
                array.add(rs.getString("NOMBRES"));
                array.add(rs.getString("PERSONAL"));
                lista.add(array);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<docenteSinNota.dtDetalle> mostrarDocenteSinNota(int institucion, int periodo, String carrera, String personal, String examen, String nivel) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<docenteSinNota.dtDetalle> lista = new ArrayList<>();
        docenteSinNota.dtDetalle item;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_DOCENTE_SIN_NOTAS (?,?,?,?,?,?) }");
            cs.setInt(1, institucion);
            cs.setInt(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, personal);
            cs.setString(5, examen);
            cs.setString(6, nivel);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new docenteSinNota.dtDetalle();
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setMalla(rs.getString("MALLA"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCiclo(rs.getString("NIVEL_MODULAR"));
                item.setTipoExamen(rs.getString("TIPO_EXAMEN"));
                item.setPersonal(rs.getString("DESPERSONAL"));
                item.setTotalAlumno(rs.getString("VACANTES_ACTUALES"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( "error" + e.getMessage());
        }
        return lista;
    }

    public List<docente_notas.tbCargaCursos> cargaCursosPersonal(String institucion, String periodo, String personal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        List<docente_notas.tbCargaCursos> lista = new ArrayList<>();
        docente_notas.tbCargaCursos item;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("  SELECT A.INSTITUCION,E.DESCRIPCION DESISTITUCION ,A.PERIODO,A.CARRERA,A.GRUPO,B.ABREVIATURA,A.MALLA,A.SECCION,A.CURSO,C.DESCRIPCION\n"
                    + " FROM SIGU.HOR_CURSO_SECCION_DOCENTE A,\n"
                    + " UPA.ACA_CARRERAS B,\n"
                    + " UPA.ACA_CURSOS C,\n"
                    + " SIGU.PER_PERSONAL D,\n"
                    + " UPA.SYS_INSTITUCION E	\n"
                    + " WHERE A.INSTITUCION LIKE ? AND A.PERIODO=? AND D.PERSONA=?\n"
                    + " AND A.CARRERA=B.CARRERA\n"
                    + " AND A.CURSO=C.CURSO	\n"
                    + " AND D.PERSONAL=A.PERSONAL\n"
                    + " AND E.INSTITUCION=A.INSTITUCION AND A.ESTADO_REGISTRO=1");
            cs.setString(1, "%");
            cs.setString(2, periodo);
            cs.setString(3, personal);

            rs = cs.executeQuery();
            int x = 1;
            while (rs.next()) {
                item = new docente_notas.tbCargaCursos();
                item.setItem(x);
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setDesInstitucion(rs.getString("DESISTITUCION").toLowerCase());
                item.setPeriodo(rs.getInt("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("ABREVIATURA"));
                item.setMalla(rs.getString("MALLA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setCurso(rs.getString("CURSO"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setDesCurso(rs.getString("DESCRIPCION"));

                lista.add(item);
                x++;
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( "eroor " + e.getMessage());
            

        }
        return lista;
    }

    public personalC mostrarPersonal(String personal) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalC item = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select * from [SIGU].[PER_PERSONAL] where PERSONAL=?");
            cs.setString(1, personal);
            rs = cs.executeQuery();
            while (rs.next()) {
            	item = new personalC();
            	item.setPersonal(rs.getString("personal"));
            	item.setPersona(rs.getString("persona"));
            	item.setcPersonal(rs.getString("CPERSONAL"));
            	item.setOficina(rs.getInt("OFICINA"));
            	item.setCodigoPlanilla(rs.getBoolean("codigo_planilla"));
            	item.setEstadoRegistro(rs.getInt("estado_registro"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }

    public personalC BuscaPersonaCodigo(String codPersona) {

        Connection c ;
        CallableStatement cs;
        ResultSet rs;
        personalC per = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from [SIGU].[PER_PERSONAL] where PERSONA=?");
            cs.setString(1, codPersona);

            rs = cs.executeQuery();

            while (rs.next()) {
                per = new personalC();
                per.setPersonal(rs.getString("personal"));
                per.setPersona(rs.getString("persona"));
                per.setcPersonal(rs.getString("CPERSONAL"));
                per.setCodigoPlanilla(rs.getBoolean("codigo_planilla"));
                per.setCargo(rs.getInt("CARGO"));
                per.setEstadoRegistro(rs.getInt("estado_registro"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return per;
    }
    
    
    public List<personalC> mostrarListaPersonal(String persona) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalC item ;
        List<personalC> lista=new ArrayList<>();
        try {
            c = ConexionWeb();
            cs = c.prepareCall("select * from [SIGU].[PER_PERSONAL] where PERSONA=?");
            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personalC();
                item.setPersonal(rs.getString("personal"));
                item.setPersona(rs.getString("persona"));
                item.setcPersonal(rs.getString("CPERSONAL"));
                item.setCodigoPlanilla(rs.getBoolean("codigo_planilla"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public personalC mostrarCPersonal(String cpersonal) {

        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalC per = null;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from [SIGU].[PER_PERSONAL] where CPERSONAL=?");
            cs.setString(1, cpersonal);

            rs = cs.executeQuery();

            while (rs.next()) {
                per = new personalC();
                per.setPersonal(rs.getString("personal"));
                per.setPersona(rs.getString("persona"));
                per.setcPersonal(rs.getString("CPERSONAL"));
                per.setCodigoPlanilla(rs.getBoolean("codigo_planilla"));
                per.setEstadoRegistro(rs.getInt("estado_registro"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());
            
        }
        return per;
    }

    public personalC mostrarPersona(String persona) {
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personalC item =null;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("select * from [SIGU].[PER_PERSONAL] where PERSONA=? AND ESTADO_REGISTRO=1");
            cs.setString(1, persona);

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new personalC();
                item.setPersonal(rs.getString("personal"));
                item.setPersona(rs.getString("persona"));
                item.setcPersonal(rs.getString("CPERSONAL"));
                item.setSede(rs.getInt("SEDE"));
                item.setCargo(rs.getInt("CARGO"));
                item.setOcupacion(rs.getInt("OCUPACION"));
                item.setOficina(rs.getInt("OFICINA"));
                item.setEsSalud(rs.getString("ESSALUD"));
                item.setCuspp(rs.getString("CUSPP"));
                item.setFormaPago(rs.getInt("FORMA_PAGO"));
                item.setNumeroCTS(rs.getString("CTS"));
                item.setBancoCTS(rs.getInt("BANCO_CTS"));
                item.setBancoRemuneracion(rs.getInt("BANCO_REMUNERACION"));
                item.setNumeroCuenta(rs.getString("NRO_CUENTA"));
                item.setFechaIngreso(rs.getDate("FECHA_INGRESO_EMPRESA"));
                item.setFechaRegimen(rs.getDate("FECHA_INICIO_REGIMEN"));
                item.setTipoRegimen(rs.getInt("TIPO_REGIMEN"));
                item.setTipoMoneda(rs.getInt("TIPO_MONEDA"));
                item.setTipoPersonal(rs.getInt("TIPO_TRABAJADOR"));
                item.setRegimenPension(rs.getInt("REGIMEN_PENSION"));
                item.setSituacion(rs.getInt("SITUACION"));
                item.setTurno(rs.getInt("TURNO"));
                item.setEps(rs.getInt("EPS"));
                item.setCentroCosto(rs.getInt("CENTRO_COSTO"));
                
               
                
                
                item.setCodigoPlanilla(rs.getBoolean("codigo_planilla"));
                item.setEstadoRegistro(rs.getInt("estado_registro"));

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           System.out.println(e.getMessage());
            
        }
        return item;
    }

}
