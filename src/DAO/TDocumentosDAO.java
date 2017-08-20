/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ENTIDAD.PersonaNET;

import Conexiones.Conexion;
import ENTIDAD.TTramites;
import ENTIDAD.cursosC;
import ENTIDAD.periodoC;
import java.sql.*;
import java.util.*;


public class TDocumentosDAO extends Conexion {

    Connection c = ConexionWeb();

    public List<TTramites> listtipo_documento() {
        List<TTramites> lstddocumento = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SELT_DOCUMENTO_JAVA()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites doc = new TTramites();
                doc.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                doc.setDocumento(rs.getString("DESCRIPCION"));
                lstddocumento.add(doc);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("listtipo_documento " + e.getMessage());
        }
        return lstddocumento;
    }

    public TTramites getTramites() {
        TTramites t = new TTramites();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_SERIE_TRAMITE_DOCUMENTARIO}");
            rs = cs.executeQuery();
            while (rs.next()) {
                t.setSeriedocu(rs.getString("SERIE"));
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("getTramites " + e.getMessage());
        }
        return t;
    }

    public List<TTramites> listareas() {
        List<TTramites> listareas = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_AREAS}");
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setArea(rs.getInt("OFICINA"));
                t.setDearea(rs.getString("DESCRIPCION"));
                listareas.add(t);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("listareas " + e.getMessage());
        }
        return listareas;
    }

    public TTramites listpersonas(String persona) {
        TTramites t = new TTramites();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_ALUMNO_PERSONA_TRAMITES(?)}");
            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                t.setCodigo_persona(rs.getString("CODIGO"));
                t.setPersona(rs.getString("PERSONA"));
                if (rs.getString("CARRERA") != null) {
                    t.setCarrera(rs.getString("CARRERA"));
                    t.setDescarrera(rs.getString("DESCCARRERA"));
                }
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("listpersonas " + e.getMessage());
        }
        return t;
    }

    public TTramites listpalumnos(String persona) {
        TTramites t = new TTramites();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_ALUMNO_TRAMITES(?)}");
            cs.setString(1, persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                t.setPersona(rs.getString("PERSONA"));
                t.setDescarrera(rs.getString("DESCCARRERA"));
                t.setCarrera(rs.getString("CARRERA"));
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("listpalumnos " + e.getMessage());
        }
        return t;
    }

    public int instdocumentario(int tipo_documento, String nroexp, String numdoc, int insti, String alumno,
            int tramite, String periodo, int oficina, int nrofolios, List<String> req, String fecha, String usuario, String obs) {
        int rp = 1;
        CallableStatement cs = null;
        try {
            cs = c.prepareCall("{CALL DI.INS_TDOCUMENTARIO_JAVA(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            for (String req1 : req) {
                cs.setInt(1, tipo_documento);
                cs.setString(2, nroexp);
                if (tipo_documento == 50) {
                    cs.setString(3, "002-" + numdoc);
                } else {
                    cs.setString(3, numdoc);
                }
                cs.setInt(4, insti);
                cs.setString(5, alumno);
                cs.setInt(6, tramite);
                cs.setString(7, periodo);
                cs.setInt(8, oficina);
                cs.setInt(9, nrofolios);
                cs.setInt(10, Integer.parseInt(req1));
                cs.setString(11, fecha);
                cs.setString(12, usuario);
                cs.setString(13, obs);
                rp = cs.executeUpdate();
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("instdocumentario " + e.getMessage());
        }
        return rp;
    }

    public List<TTramites> listReporte(String usuario, String fecha) {
        List<TTramites> listtramites = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_T_DOCUMENTARIO_PERSONAL(?,?)}");
            cs.setString(1, usuario);
            cs.setString(2, fecha);
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setNroexpediente(rs.getString("NRO_EXPEDIENTE"));
                t.setDocumento(rs.getString("NUM_DOCUMENTO"));
                t.setDesctramite(rs.getString("DESCRIPCION"));
                t.setDearea(rs.getString("AREA"));
                t.setOficina(rs.getInt("OFICINA"));
                t.setNrofolios(rs.getInt("nro_folios"));
                t.setDdocumento(rs.getString("DESDOCUMENTO"));
                t.setAlumno(rs.getString("ALUMNO"));
                t.setAsunto(rs.getString("ASUNTO"));
                t.setAnexos(rs.getString("REQUISITOS"));
                t.setFecha(rs.getString("FECHA"));
                t.setTipo_tramite(rs.getInt("TIPO_TRAMITE"));
                t.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                t.setEstaregistro(rs.getInt("ESTADO_REGISTRO"));
                t.setEstregistro(rs.getString("DESCRIPCION"));
                listtramites.add(t);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("listReporte " + e.getMessage());
        }
        return listtramites;
    }

    public List<TTramites> listReporteBYalumno(String alumno) {
        List<TTramites> listtramites = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_B_DOCUMENTARIO_ALUMNO(?)}");
            cs.setString(1, alumno);
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setNroexpediente(rs.getString("NRO_EXPEDIENTE"));
                t.setDocumento(rs.getString("NUM_DOCUMENTO"));
                t.setDesctramite(rs.getString("DESCRIPCION"));
                t.setDearea(rs.getString("AREA"));
                t.setOficina(rs.getInt("OFICINA"));
                t.setNrofolios(rs.getInt("nro_folios"));
                t.setDdocumento(rs.getString("DESDOCUMENTO"));
                t.setAlumno(rs.getString("ALUMNO"));
                t.setAsunto(rs.getString("ASUNTO"));
                t.setAnexos(rs.getString("ANEXOS"));
                t.setFecha(rs.getString("FECHA"));
                t.setCreacion_fecha(rs.getString("CREACION_FECHA"));
                t.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                t.setEstaregistro(rs.getInt("ESTADO_REGISTRO"));
                t.setEstregistro(rs.getString("DESCRIPCION"));
                listtramites.add(t);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("listReporteBYalumno " + e.getMessage());
        }
        return listtramites;
    }

    public List<TTramites> listReporteAlumno(String usuario) {
        List<TTramites> listtramites = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_T_DOCUMENTARIO_ALUMNO(?)}");

            cs.setString(1, usuario);
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setTipo_constancia(rs.getInt("TIPO_CONSTANCIA"));
                t.setOficina(rs.getInt("OFICINA"));
                t.setDescoficina(rs.getString("DOFICINA"));
                t.setNroexpediente(rs.getString("NRO_EXPEDIENTE"));
                t.setDocumento(rs.getString("NUM_DOCUMENTO"));
                t.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                t.setDdocumento(rs.getString("DOCUMENTO"));
                t.setTipo_tramite(rs.getInt("TIPO_TRAMITE"));
                t.setAsunto(rs.getString("OBS"));
                t.setTiempo_estimado(rs.getInt("TIEMPO_ESTIMADO"));
                t.setFecha(rs.getString("FECHA"));
                t.setEstaregistro(rs.getInt("ESTADO_REGISTRO"));
                t.setEstregistro(rs.getString("DESC_ESTADO"));
                listtramites.add(t);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("listReporteAlumno " + e.getMessage());
        }
        return listtramites;
    }

    public void updestregistro(String nroexp, String usuario) {
        try {
            CallableStatement cs;
            cs = c.prepareCall("{CALL DI.UPD_ESTADO_REGISTRO_JAVA(?,?)}");
            cs.setString(1, nroexp);
            cs.setString(2, usuario);
            cs.executeUpdate();
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("updestregistro " + e.getMessage());
        }
    }

    public void updestregistroap(String nroexp, String fechaini, String fechafin, String usuario) {
        //int rp = 0;
        CallableStatement cs = null;
        try {
            cs = c.prepareCall("{CALL DI.UPDA_ESTADO_REGISTRO_JAVA(?,?,?,?)}");
            cs.setString(1, nroexp);
            cs.setString(2, fechaini);
            cs.setString(3, fechafin);
            cs.setString(4, usuario);
            /*rp = */
            cs.executeUpdate();
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("updestregistroap " + e.getMessage());
        }
        //return rp;
    }

    public void updestregistro_aprobar_rechazar(int tipo, String nroexp, String usuario, String obs) {
        //tipo 1 aprueba
        //cualquier otro numero rechaza
        try {
            CallableStatement cs = null;
            cs = c.prepareCall("{CALL DI.UPDR_ESTADO_REGISTRO_JAVA(?,?,?,?)}");
            cs.setInt(1, tipo);
            cs.setString(2, nroexp);
            cs.setString(3, usuario);
            cs.setString(4, obs);
            cs.executeUpdate();
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("updestregistrorech " + e.getMessage());
        }
    }

    public int getoficinaUsuario(String usuario) {
        int ofi = 0;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.GET_OFICINA_PERSONAL(?)}");
            cs.setString(1, usuario);
            rs = cs.executeQuery();
            while (rs.next()) {
                ofi = rs.getInt("OFICINA");
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (Exception e) {
            System.out.println("getoficinaUsuario " + e.getMessage());
        }
        return ofi;
    }

    public int actoficina(String nro_exp, String nvaexp, String nro_doc, String asunto, int nrofolios, String anexos, int oficina, String usuario, String fechamod) {
        CallableStatement cs = null;
      
        int ra = 0;
        int sp = 0;
        try {
            if (!nvaexp.equals(nro_exp)) {
                sp = 1;
            } else {
                sp = 2;
            }
            cs = c.prepareCall("{CALL DI.ACT_OFICINA(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, sp);
            cs.setString(2, nro_exp);
            cs.setString(3, nvaexp);
            cs.setString(4, nro_doc);
            cs.setInt(5, nrofolios);
            cs.setInt(6, oficina);
            cs.setString(7, asunto);
            cs.setString(8, anexos);
            cs.setString(9, usuario);
            cs.setString(10, fechamod);
            ra = cs.executeUpdate();
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (Exception e) {
            System.out.println("actoficina " + e.getMessage());
        }
        return ra;
    }

    public String updserie(String serie) {
        CallableStatement cs = null;
        String rsserie = null;
        try {
            cs = c.prepareCall("{CALL DI.UPD_SERIE_TRAMITE_DOC(?,?)}");
            cs.setString(1, serie);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeUpdate();
            rsserie = cs.getString(2);
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (Exception e) {
            System.err.println("updserie " + e.getMessage());
        }
        return rsserie;
    }

    public int insnuevotramite(String tramite, String usuario) {
        CallableStatement cs = null;
        int r = 0;
        try {
            cs = c.prepareCall("{CALL DI.INS_TIPO_TRAMITE(?,?)}");
            cs.setString(1, tramite);
            cs.setString(2, usuario);
            r = cs.executeUpdate();
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (Exception e) {
            System.err.println("insnuevotramite " + e.getMessage());
        }
        return r;
    }

    public void delexpediente(String numero) {
        CallableStatement statement = null;
        try {
            statement = c.prepareCall("{CALL DI.DEL_TRAMITE_DOC(?)}");
            statement.setString(1, numero);
            statement.executeUpdate();
            cerrarConexion(c);
            cerrarCall(statement);
        } catch (Exception ex) {
            System.err.println("delexpediente " + ex.getMessage());
        }
    }

    public List<PersonaNET> getPersonasCaja(String pat, String mat, String nom) {
        List<PersonaNET> per = new ArrayList<>();

        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.CON_BUSCA_PERSONA_JAVA(?,?,?)}");
            cs.setString(1, pat);
            cs.setString(2, mat);
            cs.setString(3, nom);
            rs = cs.executeQuery();
            while (rs.next()) {
                PersonaNET p = new PersonaNET();
                p.setPersona(rs.getString(1));
                p.setNombres(rs.getString(2));
                per.add(p);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            System.out.println("getPersonasCaja " + e.getMessage());
        }
        return per;
    }

    public List<Object> getautomatico(String numfut) {
        List<Object> t = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.CON_NUM_FUT(?)}");
            cs.setString(1, numfut);
            rs = cs.executeQuery();
            while (rs.next()) {
                t.add(rs.getString("ALUMNO"));
                t.add(rs.getString("PERSONA"));
                t.add(rs.getInt("TRAMITE"));
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (Exception e) {
            System.err.println("getautomatico " + e.getMessage());
        }
        return t;
    }

    public List<TTramites> getrequisitos(int tipo_tramite) {
        List<TTramites> lstt = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT TIPO_REQUISITO,(SELECT DESCREQUISITOS FROM DI.TIPO_REQUISITOS_T B WHERE B.REQUISITOS=A.TIPO_REQUISITO) AS REQUISITO "
                    + "            FROM DI.TRAMITE_REQUISITO A "
                    + "            WHERE TIPO_TRAMITE=?");
            ps.setInt(1, tipo_tramite);
            rs = ps.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setTipo_requisito(rs.getInt("TIPO_REQUISITO"));
                t.setRequisito(rs.getString("REQUISITO"));
                lstt.add(t);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            System.err.println("getrequisitos " + e.getMessage());
        }
        return lstt;
    }

    public List<TTramites> getTramitesOficina(int tipo_tramite, String carrera) {
        List<TTramites> lsttram = new ArrayList<>();
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            if (tipo_tramite == 33 || tipo_tramite == 83 || tipo_tramite == 116 || tipo_tramite == 91) {
                cs = c.prepareCall("{CALL DI.ELEJIR_OFICINA(?)}");
                cs.setString(1, carrera);
                rs = cs.executeQuery();
            } else {
                ps = c.prepareStatement("select a.OFICINA,"
                        + "B.DESCRIPCION  from DI.TIPO_TRAMITE A, sigu.PER_OFICINA B "
                        + "WHERE B.OFICINA=A.OFICINA AND B.INSTITUCION=A.INSTITUCION AND TIPO_TRAMITE=? ");
                ps.setInt(1, tipo_tramite);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                TTramites tt = new TTramites();
                tt.setOficina(rs.getInt("OFICINA"));
                tt.setDescoficina(rs.getString("DESCRIPCION"));
                lsttram.add(tt);
            }
        } catch (Exception e) {
            System.err.println("getTramitesOficina " + e.getMessage());
        }
        return lsttram;
    }

    public List<TTramites> modtramites(String nro_exp) {
        List<TTramites> lst = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.UPD_T_DOCUMENTOS_PERSONAL(?)}");
            cs.setString(1, nro_exp);
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                t.setTipo_tramite(rs.getInt("TIPO_TRAMITE"));
                t.setNroexpediente(rs.getString("NRO_EXPEDIENTE"));
                t.setDocumento(rs.getString("NUM_DOCUMENTO"));
                t.setPersona(rs.getString("PERSONA"));
                t.setDesctramite(rs.getString("DESCTRAMITE"));
                t.setRequisito(rs.getString("DESCREQUISITOS"));
                t.setDescoficina(rs.getString("DESCOFICINA"));
                t.setFecha(rs.getString("FECHA"));
                t.setNrofolios(rs.getInt("NRO_FOLIOS"));
                t.setAsunto(rs.getString("ASUNTO"));
                lst.add(t);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (Exception e) {
            System.err.println("modtramites " + e.getMessage());
        }
        return lst;
    }

    public List<cursosC> selectcurso(String alumno, String periodo) {
        List<cursosC> listcursos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT A.CURSO,B.DESCRIPCION "
                    + "FROM SIGU.ACA_ALUMNO_CURSO_SECCION A,UPA.ACA_CURSOS B "
                    + "WHERE B.CURSO=A.CURSO AND ALUMNO=? AND PERIODO=?");
            ps.setString(1, alumno);
            ps.setString(2, periodo);
            rs = ps.executeQuery();
            while (rs.next()) {
                cursosC cu = new cursosC();
                cu.setCurso(rs.getString("CURSO"));
                cu.setDescripcion(rs.getString("DESCRIPCION"));
                listcursos.add(cu);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            System.err.println("selectcurso " + e.getMessage());
        }
        return listcursos;
    }

    public List<Object> selupdalumno(String nro_expediente) {
        List<Object> sl = new ArrayList<>();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SELU_ALUMNO(?)}");
            cs.setString(1, nro_expediente);
            rs = cs.executeQuery();
            while (rs.next()) {
                sl.add(rs.getString("NRO_EXPEDIENTE"));
                sl.add(rs.getString("NUM_DOCUMENTO"));
                sl.add(rs.getString("DOFICINA"));
                sl.add(rs.getString("TIEMPO_ESTIMADO"));
                sl.add(rs.getString("DOCUMENTO"));
                sl.add(rs.getInt("TIPO_CONSTANCIA"));
                sl.add(rs.getString("OBS"));
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (Exception e) {
            System.err.println("selupdalumno " + e.getMessage());
        }
        return sl;
    }

    public int verifalu(String nro_expediente) {
        int v = -1;
        CallableStatement statement = null;
        try {
            statement = c.prepareCall("{CALL DI.VERIF_CONSTANCIA(?,?)}");
            statement.setString(1, nro_expediente);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.executeUpdate();
            v = statement.getInt(2);
            cerrarCall(statement);
            cerrarConexion(c);
        } catch (Exception e) {
            System.err.println("verifalu " + e.getMessage());
        }
        return v;
    }

    public List<periodoC> getPeriodo() {
        List<periodoC> listper = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareCall("SELECT DISTINCT PERIODO,DESCRIPCION FROM UPA.ACA_PERIODOS WHERE PERIODO NOT IN(0000000000) AND INSTITUCION=1 ORDER BY PERIODO DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                periodoC p = new periodoC();
                p.setPeriodo(rs.getString("PERIODO"));
                p.setDesPeriodo(rs.getString("DESCRIPCION"));
                listper.add(p);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            System.err.println("getPeriodo " + e.getMessage());
        }
        return listper;
    }
}
