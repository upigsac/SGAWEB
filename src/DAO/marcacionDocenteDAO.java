
package DAO;

import Beans.marcacion;
import Beans.reporteMarcacion;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import Beans.util;
import java.util.Date;


public class marcacionDocenteDAO implements Serializable {

    
	private static final long serialVersionUID = 1L;

	public List<marcacion.marcacionDocenteC> listadoMarcacionDocente(String codigoDocente3) {
        List<marcacion.marcacionDocenteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        marcacion.marcacionDocenteC mdc ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_LISTADO_MARCACION_DOCENTE (?)}");

            cs.setString(1, codigoDocente3);

            rs = cs.executeQuery();

            while (rs.next()) {
                mdc = new marcacion.marcacionDocenteC();
                mdc.setInstitucion(rs.getInt("institucion"));
                mdc.setDia(rs.getInt("dia"));
                mdc.setInicioClase(rs.getString("hor_ing"));
                mdc.setFinClase(rs.getString("hor_sal"));
                mdc.setCpersonal(rs.getString("cpersonal"));
                mdc.setDocente(rs.getString("docente"));
                mdc.setTurno(rs.getString("turno"));
                mdc.setCurso(rs.getString("curso"));
                mdc.setDesc_curso(rs.getString("desasignatura"));
                mdc.setCiclo(rs.getString("ciclo"));
                mdc.setCarrera(rs.getString("carrera"));
                mdc.setDesc_carrera(rs.getString("descarrera"));
                mdc.setAula(rs.getString("aula"));
                mdc.setSemana(rs.getInt("semana") == 0 ? null : rs.getInt("semana"));
                mdc.setSesion_clase(rs.getInt("sesion_clase") == 0 ? null : rs.getInt("sesion_clase"));
                mdc.setTemaDesarrollo(rs.getString("tema_desarrollado"));

                mdc.setHoraEntrada(rs.getString("hora_entrada"));
                mdc.setHoraSalida(rs.getString("hora_salida"));
                mdc.setTardanza(rs.getString("tardanza"));

                mdc.setNalumnos(rs.getInt("estudiantes"));
                mdc.setTotalHoras(rs.getInt("total_horas"));
                mdc.setEstado_registro(rs.getInt("estado_registro"));
                lista.add(mdc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public int ingresoRegistroMarcacionDocente(marcacion.marcacionDocenteC mdc, String temaDesarrollo, int semana, int sesion, int totalestud, int tipo) {

        int i = 2;

        try {
            Connection c = null;
            c = ConexionWeb();
            CallableStatement pr = null;
            pr = c.prepareCall("{CALL di.sp_insertarMarcacionDocente (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            pr.setInt(1, mdc.getInstitucion());
            pr.setInt(2, mdc.getDia());
            pr.setString(3, mdc.getInicioClase());
            pr.setString(4, mdc.getFinClase());
            pr.setString(5, mdc.getCpersonal());
            pr.setString(6, mdc.getDocente());
            pr.setString(7, mdc.getTurno());
            pr.setString(8, mdc.getCurso());
            pr.setString(9, mdc.getDesc_curso());
            pr.setString(10, mdc.getCiclo());
            pr.setString(11, mdc.getCarrera());
            pr.setString(12, mdc.getDesc_carrera());
            pr.setString(13, mdc.getAula());
            pr.setInt(14, semana);
            pr.setInt(15, sesion);
            pr.setString(16, temaDesarrollo);
            pr.setInt(17, totalestud);
            pr.setInt(18, tipo);
            pr.registerOutParameter(19, Types.INTEGER);
            pr.executeUpdate();
            //recordar debugear esto
            i = pr.getInt(19);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return i;
    }

    public List<marcacion.marcacionDocenteC> listadoCompletoSemanaDocente(String codigoDocente3) {

        List<marcacion.marcacionDocenteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        marcacion.marcacionDocenteC mdc ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_REPORTE_MARCACION_DOCENTE (6,'',7,'',?)}");

            cs.setString(1, codigoDocente3);

            rs = cs.executeQuery();

            while (rs.next()) {
                mdc = new marcacion.marcacionDocenteC();
                mdc.setInstitucion(rs.getInt("institucion"));
                mdc.setDia_texto(rs.getString("dia"));
                mdc.setInicioClase(rs.getString("hor_ing"));
                mdc.setFinClase(rs.getString("hor_sal"));
                //mdc.setCpersonal(rs.getString("cpersonal"));
                //mdc.setDocente(rs.getString("docente"));
                mdc.setTurno(rs.getString("turno"));
                mdc.setCurso(rs.getString("curso"));
                mdc.setDesc_curso(rs.getString("desasignatura"));
                mdc.setCiclo(rs.getString("ciclo"));
                //mdc.setCarrera(rs.getString("carrera"));
                mdc.setDesc_carrera(rs.getString("descarrera"));
                mdc.setAula(rs.getString("aula"));
                //mdc.setSemana(rs.getInt("semana") == 0 ? null:rs.getInt("semana"));
                //mdc.setSesion_clase(rs.getInt("sesion_clase") == 0 ? null:rs.getInt("sesion_clase"));
                //mdc.setTemaDesarrollo(rs.getString("tema_desarrollado"));
                /*
                 mdc.setHoraEntrada(rs.getString("hora_entrada"));
                 mdc.setHoraSalida(rs.getString("hora_salida"));
                 mdc.setTardanza(rs.getString("tardanza"));
                 */
                //mdc.setNalumnos(rs.getInt("estudiantes"));
                mdc.setTotalHoras(rs.getInt("total_horas"));
                mdc.setHora_acu(rs.getInt("total_hora_acu"));
                mdc.setTardanza_acu(rs.getInt("total_tardanza_acu"));
                //mdc.setEstado_registro(rs.getInt("estado_registro"));
                lista.add(mdc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<marcacion.marcacionDocenteC> listadoReportesMarcacion(int tipoReporte, String carrera, int mes, String dia, String personal) {

        List<marcacion.marcacionDocenteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        marcacion.marcacionDocenteC mdc ;

        try {
            if (tipoReporte != 5) {

                c = ConexionWeb();
                cs = c.prepareCall("{CALL DI.SP_REPORTE_MARCACION_DOCENTE (?,?,?,?,?)}");

                cs.setInt(1, tipoReporte);
                cs.setString(2, carrera);
                cs.setInt(3, mes);
                cs.setString(4, dia);
                cs.setString(5, personal);

                rs = cs.executeQuery();

                while (rs.next()) {
                    mdc = new marcacion.marcacionDocenteC();
                    mdc.setInstitucion(rs.getInt("institucion"));
                    mdc.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    mdc.setCpersonal(rs.getString("cpersonal"));
                    mdc.setDocente(rs.getString("docente"));
                    mdc.setCarrera(rs.getString("carrera"));
                    mdc.setDesc_carrera(rs.getString("descarrera"));
                    mdc.setCurso(rs.getString("curso"));
                    mdc.setDesc_curso(rs.getString("desasignatura"));
                    mdc.setTemaDesarrollo(rs.getString("tema_desarrollado"));
                    mdc.setHoraEntrada(rs.getString("hora_entrada"));
                    mdc.setHoraSalida(rs.getString("hora_salida"));
                    mdc.setTotalHoras(rs.getInt("total"));
                    mdc.setTardanza(rs.getString("tardanza"));
                    mdc.setNalumnos(rs.getInt("total_estudiantes"));
                    if (tipoReporte == 1) {
                        mdc.setEstado_registro(rs.getInt("estado_registro"));
                        mdc.setDesc_estado_registro(rs.getString("desc_estado_registro"));
                    }
                    lista.add(mdc);
                }
                cerrarCall(cs);
                cerrarConexion(c);

            } else {//para caso reporte 5                
                c = ConexionWeb();
                cs = c.prepareCall("{CALL DI.SP_REPORTE_MARCACION_DOCENTE (?,?,?,?,?)}");

                cs.setInt(1, tipoReporte);
                cs.setString(2, carrera);
                cs.setInt(3, mes);
                cs.setString(4, dia);
                cs.setString(5, personal);

                rs = cs.executeQuery();

                while (rs.next()) {
                    mdc = new marcacion.marcacionDocenteC();
                    mdc.setInstitucion(rs.getInt("institucion"));
                    mdc.setHoraEntrada(rs.getString("hor_ing"));
                    mdc.setHoraSalida(rs.getString("hor_sal"));
                    mdc.setCpersonal(rs.getString("cpersonal"));
                    mdc.setDocente(rs.getString("docente"));
                    mdc.setCarrera(rs.getString("carrera"));
                    mdc.setDesc_carrera(rs.getString("descarrera"));
                    mdc.setCurso(rs.getString("curso"));
                    mdc.setDesc_curso(rs.getString("desasignatura"));
                    lista.add(mdc);
                }
                cerrarCall(cs);
                cerrarConexion(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( e.getMessage());
        }
        return lista;
    }

    public List<marcacion.marcacionDocenteC> listadoCompletoMarcacionDocente(String dia, String turno, String carrera, String ciclo, String docente) {

        List<marcacion.marcacionDocenteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        marcacion.marcacionDocenteC mdc ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.LISTADO_MANTENIMIENTO_MARCACION (?,?,?,?,?)}");

            cs.setString(1, dia);
            cs.setString(2, turno);
            cs.setString(3, carrera);
            cs.setString(4, ciclo);
            cs.setString(5, docente);

            rs = cs.executeQuery();

            while (rs.next()) {
                mdc = new marcacion.marcacionDocenteC();
                mdc.setInstitucion(rs.getInt("institucion"));
                mdc.setDia(rs.getInt("dia"));
                mdc.setDia_texto(rs.getString("desdia"));
                mdc.setInicioClase(rs.getString("hor_ing"));
                mdc.setFinClase(rs.getString("hor_sal"));
                mdc.setCpersonal(rs.getString("cpersonal"));
                mdc.setDocente(rs.getString("docente"));
                mdc.setTurno(rs.getString("turno"));
                mdc.setCurso(rs.getString("curso"));
                mdc.setDesc_curso(rs.getString("desasignatura"));
                mdc.setCiclo(rs.getString("ciclo"));
                mdc.setCarrera(rs.getString("carrera"));
                mdc.setDesc_carrera(rs.getString("descarrera"));
                mdc.setAula(rs.getString("aula"));
                mdc.setTotalHoras(rs.getInt("total_horas"));
                //mdc.setEstado_registro(rs.getInt("estado_registro"));
                lista.add(mdc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( e.getMessage());
            System.out.println("problema en marcacion dao listado completo marcacion");
        }
        return lista;
    }

    public List<reporteMarcacion.ColumnModel> listadoColumnaDinamica(Date fIni, Date fFin) {
        List<reporteMarcacion.ColumnModel> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        reporteMarcacion.ColumnModel item ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_COLUMNAS_DINAMICAS_MARCACION(?,?)}");
            cs.setString(1, util.convertDate( fIni,"dd-MM-yyyy"));
            cs.setString(2, util.convertDate( fFin,"dd-MM-yyyy"));

            rs = cs.executeQuery();

            while (rs.next()) {
                item = new reporteMarcacion.ColumnModel();
                item.setHeader(rs.getString("COLUMNA"));
                item.setProperty(rs.getString("PROPIEDAD"));
                item.setIndice(rs.getInt("ITEM"));
                item.setFeriado(rs.getInt("FERIADO"));
                item.setEditable(rs.getBoolean("EDITABLE"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            
            util.consolaCliente( e.getMessage());

        }
        return lista;
    }

    public List<marcacion.marcacionDocenteC> listadoDocentesMarcacion(String nombredocente) {

        List<marcacion.marcacionDocenteC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        marcacion.marcacionDocenteC mdc;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT DISTINCT CPERSONAL,DOCENTE \n"
                    + "FROM DI.REGISTRO_DOCENTE_TM \n"
                    + "WHERE ESTADO_REGISTRO=1\n"
                    + "AND DOCENTE LIKE ?\n"
                    + "ORDER BY DOCENTE");

            cs.setString(1, nombredocente + "%");

            rs = cs.executeQuery();

            while (rs.next()) {
                mdc = new marcacion.marcacionDocenteC();
                mdc.setCpersonal(rs.getString("cpersonal"));
                mdc.setDocente(rs.getString("docente"));
                lista.add(mdc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //util.consolaCliente( e.getMessage());
            System.out.println("problema en marcacion dao listado completo marcacion");
        }
        return lista;
    }

    public List<ArrayList<String>> listadoMensualMarcacion(String institucion,Date fechaInicio, Date fechaFin, String periodo, String docente, String carrera, String tipo, boolean faltas) {
    	
    	 System.out.println(util.convertDate(fechaInicio,"dd-MM-yyyy"));
    	 System.out.println( util.convertDate(fechaFin, "dd-MM-yyyy") );
    	 System.out.println( periodo);
    	 System.out.println( docente);
    	 System.out.println( carrera);
    	 System.out.println( tipo);
    	 System.out.println( faltas);
    	
    	
        List<ArrayList<String>> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        try {
            c = ConexionWeb();
            if (faltas) {
                cs = c.prepareCall("{CALL [DI].[SP_REPORTE_MARCACION_MENSUAL] (?,?,?,?,?,?,?)}");
            } else {
                cs = c.prepareCall("{CALL [DI].[SP_REPORTE_SIN_MARCACION_MENSUAL] (?,?,?,?,?,?,?)}");
            }
           
            cs.setString("FINI", util.convertDate(fechaInicio,"dd-MM-yyyy"));
            cs.setString("FFIN", util.convertDate(fechaFin, "dd-MM-yyyy") );
            cs.setString("PERIODO", periodo);
            cs.setString("CPERSONA", docente);
            cs.setString("CARRERA", carrera);
            cs.setString("TIPO", tipo);
            cs.setString("INSTITUCION", institucion);
            rs = cs.executeQuery();
            rss = rs.getMetaData();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("cpersonal"));
                array.add(rs.getString("docente"));
                for (int i = 3; i < rss.getColumnCount() + 1; i++) {
                    array.add(rs.getString(i));                   
                }       
               
                
                //array.add("" + (rs.getDouble((rss.getColumnCount()-1))*(rs.getDouble((rss.getColumnCount())))));
                array.add("" + (rs.getDouble("TOTAL_FINAL")*(rs.getDouble("MONTO"))));
                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {            
            
            System.out.println(e.getMessage());
        
        }
        return lista;
    }

    public List<ArrayList<String>> listadoMensualTardanzas(Date fechaInicio, Date fechaFin, String docente, String carrera, String tipo) {
        List<ArrayList<String>> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ResultSetMetaData rss ;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MARACACION_TARDANZAS (?,?,?,?,?)}");
            cs.setString(1, util.convertDate( fechaInicio,"dd-MM-yyyy"));
            cs.setString(2, util.convertDate( fechaFin,"dd-MM-yyyy"));
            cs.setString(3, docente);
            cs.setString(4, carrera);
            cs.setString(5, tipo);
            rs = cs.executeQuery();
            rss = rs.getMetaData();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("cpersonal"));
                array.add(rs.getString("docente"));
                for (int i = 3; i < rss.getColumnCount() + 1; i++) {
                    array.add(rs.getString(i));
                }

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public List<ArrayList<String>> listadoSemestralMarcacion(int periodo, String docente, String turno) {
        List<ArrayList<String>> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_CARGA_HORARIA_SEMENESTRA (?,?,?,?,?)}");
            cs.setInt(1, periodo);
            cs.setString(2, docente);
            cs.setString(3, "%");
            cs.setString(4, "%");
            cs.setString(5, turno);
            rs = cs.executeQuery();
            while (rs.next()) {
                ArrayList<String> array = new ArrayList<>();
                array.add(rs.getString("cpersonal"));
                array.add(rs.getString("docente"));
                array.add(rs.getString("curso"));
                array.add(rs.getString("desasignatura"));
                array.add(rs.getString("turno"));
                array.add(rs.getString("horas_semanales"));
                array.add(rs.getString("horas_mensuales"));
                array.add(rs.getString("total_semestre"));

                lista.add(array);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            util.consolaCliente( e.getMessage());
            //System.out.println("problema en marcacion MES dao listado completo marcacion");
        }
        return lista;
    }

    public boolean actualizarDetalle(String tema, String horaIng, String horaSal, String ingreso, String salida, String id) {
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("UPDATE DI.REGISTRO_DOCENTE_TD SET tema_Desarrollado=? , HOR_ING=? ,HOR_SAL=? ,HORA_ENTRADA=?, HORA_SALIDA=? ,TARDANZA=case when DATEDIFF(mi,'" + horaIng + "','" + ingreso + "')>0 then DATEDIFF(mi,'" + horaIng + "','" + ingreso + "') else '0' end,control_usuario=user,control_fecha=GETDATE() WHERE ID=?");

            cs.setString(1, tema);
            cs.setString(2, horaIng);
            cs.setString(3, horaSal);
            cs.setString(4, ingreso);
            cs.setString(5, salida);
            cs.setString(6, id);

            rpta = cs.executeUpdate() == 1;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR ELIMINAR " + e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

    public boolean ingresoDetalle(String periodo, String horaIng, String horaSal, String ingreso, String salida, String fecha, String cpersona, String docente, String curso, String turno, String aula, String carrera, String tema, String tipo, String descurso, String ciclo, String seccion) {

        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;
        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_INSERTAR_DETALLE_MARCACION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            cs.setString(1, periodo);
            cs.setString(2, horaIng);
            cs.setString(3, horaSal);
            cs.setString(4, ingreso);
            cs.setString(5, salida);
            cs.setString(6, fecha);
            cs.setString(7, cpersona);
            cs.setString(8, docente);
            cs.setString(9, curso);
            cs.setString(10, turno);
            cs.setString(11, aula);
            cs.setString(12, carrera);
            cs.setString(13, tema);
            cs.setString(14, tipo);
            cs.setString(15, descurso);
            cs.setString(16, ciclo);
            cs.setString(17, seccion);

            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {
                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "ERROR INGRESAR " + e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }

    public List<reporteMarcacion.detalleMarcacion> listadoDetalleMarcacion(String docente, String fecha, String tipo) {
    	
    	
    	System.out.println(docente);
    	System.out.println(fecha);            
    	System.out.println(tipo);

        List<reporteMarcacion.detalleMarcacion> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        reporteMarcacion.detalleMarcacion item;
        try {
            c = ConexionWeb();
            
             cs = c.prepareCall("{CALL DI.SP_DETALLE_MARCACION(?,?,?)}");
            
            cs.setString(1, docente);
            cs.setString(2, fecha);            
            cs.setString(3, tipo);
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new reporteMarcacion.detalleMarcacion();
                
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setDia(rs.getInt("DIA"));
                item.setFecha(rs.getDate("FECHA_INGRESO"));
                item.setHora_ing(rs.getTimestamp("HOR_ING"));
                item.setHora_sal(rs.getTimestamp("HOR_SAL"));
                item.setCpersonal(rs.getString("CPERSONAL"));
                item.setDocente(rs.getString("DOCENTE"));
                item.setTurno(rs.getInt("TURNO"));
                item.setTipo(rs.getString("TIPO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDescarrera(rs.getString("descarrera"));
                item.setCiclo(rs.getString("CICLO"));
                item.setDesCurso(rs.getString("desasignatura"));
                item.setSeccion(rs.getString("SECCION"));
                item.setAula(rs.getString("AULA"));
                item.setSemana(rs.getInt("SEMANA"));

                item.setTema(rs.getString("tema_desarrollado"));
                
                
                item.setRegistroIngreso(rs.getTimestamp("hora_entrada"));
               
                item.setRegistroSalida(rs.getTimestamp("hora_salida"));
                
                item.setTardanza(rs.getString("TARDANZA"));
                item.setId(rs.getInt("ID"));
                item.setTotal_horas(rs.getString("HORAS"));
                item.setCurso(rs.getString("CURSO"));

                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
           System.out.println("linea 635: " + e.getMessage());

        }
        return lista;
    }

    public List<reporteMarcacion.detalleMarcacion> listadoDetalleSinMarcacion(String docente, Date fecha) {

        List<reporteMarcacion.detalleMarcacion> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        reporteMarcacion.detalleMarcacion item;
        try {
        	System.out.println( docente);
        	System.out.println( util.convertDate(fecha, "yyyy-MM-dd"));
        	
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_DETALLE_SIN_MARACION (?,?)}");

            cs.setString(1, docente);
            cs.setString(2, util.convertDate(fecha, "yyyy-MM-dd"));
            rs = cs.executeQuery();
            while (rs.next()) {
            	
                item = new reporteMarcacion.detalleMarcacion();
                item.setId(rs.getInt("ID"));
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setDia(rs.getInt("dia"));
                item.setFecha(rs.getDate("FECHA_INGRESO"));
                item.setTipoClase(rs.getInt("TIPO_CLASE"));
                item.setTipoHora(rs.getInt("TIPO_HORA"));
                item.setHora_ing(rs.getTimestamp("hor_ing"));
                item.setHora_sal(rs.getTimestamp("hor_sal"));
                item.setCpersonal(rs.getString("cpersonal"));
             
                item.setTurno(rs.getInt("turno"));
                item.setTipo(rs.getString("tipo"));
                item.setCarrera(rs.getString("carrera"));
                item.setDescarrera(rs.getString("descarrera"));
                item.setCiclo(rs.getString("ciclo"));
                item.setDesCurso(rs.getString("desasignatura"));
                item.setAula(rs.getString("aula"));
                item.setSemana(rs.getInt("SEMANA"));         
                //item.setRegistroIngreso(rs.getTimestamp("hora_entrada"));                
                //item.setRegistroSalida(rs.getTimestamp("hora_salida"));
                item.setTotal_horas(rs.getString("HORAS"));
                item.setCurso(rs.getString("CURSO"));
                item.setSeccion(rs.getString("SECCION"));
                item.setPeriodo(rs.getString("PERIODO"));                
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
