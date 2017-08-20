/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import ENTIDAD.ColumnModel;
import ENTIDAD.carrerasC;
import ENTIDAD.controlDocente;
import ENTIDAD.cursoSeccionC;
import ENTIDAD.cursosC;
import ENTIDAD.institucionC;
import ENTIDAD.periodoC;

import ENTIDAD.personaC;
import ENTIDAD.personalC;
import ENTIDAD.seccionPeriodoC;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class recepcionActaDAO extends Conexiones.Conexion {

    public List<controlDocente> controlEntregaActas(int institucion, String periodo, String carrera, String personal) {
        List<controlDocente> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs;
        ResultSet rs;

        controlDocente mdc;
        institucionC i;
        periodoC p;
        personaC per;
        carrerasC ca;
        cursoSeccionC css;
        seccionPeriodoC sp;
        cursosC cu;
        personalC perl;
        List<ColumnModel> cm;
        
        try {
            c = ConexionWeb();            
            cs = c.prepareCall("		SELECT \n" +
"                    CSD.INSTITUCION,CSD.PERIODO,CSD.CARRERA,CA.ABREVIATURA CARRERA_ABR,CSD.MALLA,CSD.CURSO,CU.DESCRIPCION CURSO_DESC,CSD.SECCION,CSD.PERSONAL,\n" +
"                    DP.APELLIDO_PATERNO+' '+DP.APELLIDO_MATERNO+' '+DP.NOMBRES APELLIDOS_NOMBRES,DP.TIPO_DOCUMENTO,TD.ABREVIATURA TIPO_DOC_ABR,DP.NRO_DOCUMENTO,\n" +
"                    ISNULL(CONVERT(DATE,DP.NACIMIENTO_FECHA),'1900-01-01') NACIMIENTO_FECHA,\n" +
"                    DP.EMAIL_PRINCIPAL,DP.EMAIL_OPCIONAL,\n" +
"                    CSD.GRUPO,PS.NIVEL_MODULAR CICLO,SIGU.SF_NUMERO_ROMANO(1,PS.NIVEL_MODULAR) CICLO_DESC,PS.TURNO,T.DESCRIPCION TURNO_DESC,F.DESCRIPCION FACULTAD_DESC,\n" +
"                    CONVERT(DATE,U4.RECEP_FECHA) U4,CONVERT(DATE,EP.RECEP_FECHA) EP,CONVERT(DATE,U9.RECEP_FECHA) U9,CONVERT(DATE,EF.RECEP_FECHA) EF ,CONVERT(DATE,AF.RECEP_FECHA) AF\n" +
"                    FROM \n" +
"                    SIGU.HOR_CURSO_SECCION_DOCENTE CSD JOIN SIGU.PER_PERSONAL PP ON CSD.PERSONAL=PP.PERSONAL JOIN UPA.DAT_PERSONAS DP ON PP.PERSONA=DP.PERSONA JOIN SIGU.SYS_TIPOS_DOCUMENTOS TD \n" +
"                    ON DP.TIPO_DOCUMENTO=TD.TIPO_DOCUMENTO JOIN SIGU.ACA_FACULTAD_CARRERA FC ON FC.INSTITUCION=CSD.INSTITUCION AND FC.CARRERA=CSD.CARRERA JOIN SIGU.ACA_FACULTAD F \n" +
"                    ON F.INSTITUCION=CSD.INSTITUCION AND FC.FACULTAD=F.FACULTAD JOIN SIGU.HOR_PERIODO_SECCION PS ON PS.INSTITUCION=CSD.INSTITUCION AND PS.PERIODO=CSD.PERIODO AND PS.SECCION=CSD.SECCION AND PS.CARRERA=CSD.CARRERA\n" +
"                    JOIN UPA.ACA_TURNOS T ON PS.TURNO=T.TURNO JOIN UPA.ACA_CARRERAS CA ON CSD.CARRERA=CA.CARRERA JOIN UPA.ACA_CURSOS CU ON CSD.CURSO=CU.CURSO\n" +
"                    LEFT JOIN \n" +
"                    DI.CONTROL_DOCENTE U4 \n" +
"                    ON U4.INSTITUCION=CSD.INSTITUCION AND U4.PERIODO=CSD.PERIODO AND U4.CARRERA=CSD.CARRERA AND U4.MALLA=CSD.MALLA AND U4.CURSO=CSD.CURSO AND U4.SECCION=CSD.SECCION AND U4.PERSONAL=CSD.PERSONAL AND U4.TIPO_EXAMEN='U4'\n" +
"                    LEFT JOIN \n" +
"                    DI.CONTROL_DOCENTE EP \n" +
"                    ON EP.INSTITUCION=CSD.INSTITUCION AND EP.PERIODO=CSD.PERIODO AND EP.CARRERA=CSD.CARRERA AND EP.MALLA=CSD.MALLA AND EP.CURSO=CSD.CURSO AND EP.SECCION=CSD.SECCION AND EP.PERSONAL=CSD.PERSONAL AND EP.TIPO_EXAMEN='EP'\n" +
"                    LEFT JOIN \n" +
"                    DI.CONTROL_DOCENTE U9 \n" +
"                    ON U9.INSTITUCION=CSD.INSTITUCION AND U9.PERIODO=CSD.PERIODO AND U9.CARRERA=CSD.CARRERA AND U9.MALLA=CSD.MALLA AND U9.CURSO=CSD.CURSO AND U9.SECCION=CSD.SECCION AND U9.PERSONAL=CSD.PERSONAL AND U9.TIPO_EXAMEN='U9'\n" +
"                    LEFT JOIN\n" +
"                    DI.CONTROL_DOCENTE EF \n" +
"                    ON EF.INSTITUCION=CSD.INSTITUCION AND EF.PERIODO=CSD.PERIODO AND EF.CARRERA=CSD.CARRERA AND EF.MALLA=CSD.MALLA AND EF.CURSO=CSD.CURSO AND EF.SECCION=CSD.SECCION AND EF.PERSONAL=CSD.PERSONAL AND EF.TIPO_EXAMEN='EF'\n" +
"                    LEFT JOIN\n" +
"                    DI.CONTROL_DOCENTE AF \n" +
"                    ON AF.INSTITUCION=CSD.INSTITUCION AND AF.PERIODO=CSD.PERIODO AND AF.CARRERA=CSD.CARRERA AND AF.MALLA=CSD.MALLA AND AF.CURSO=CSD.CURSO AND AF.SECCION=CSD.SECCION AND AF.PERSONAL=CSD.PERSONAL AND AF.TIPO_EXAMEN='AF'                    \n" +
"                    WHERE \n" +
"                    CSD.INSTITUCION=? AND CSD.PERIODO=?   AND CSD.CARRERA LIKE ? AND CSD.PERSONAL LIKE ? AND CSD.ESTADO_REGISTRO IN (1,59) AND DP.ESTADO_REGISTRO=1\n" +
"                    ORDER BY CSD.CARRERA,APELLIDOS_NOMBRES,LEN(CSD.CURSO),CSD.CURSO");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, personal);

            rs = cs.executeQuery();

            while (rs.next()) {
                mdc = new controlDocente();
                i = new institucionC();
                p = new periodoC();
                per = new personaC();
                ca = new carrerasC();
                css = new cursoSeccionC();
                sp = new seccionPeriodoC();
                cu = new cursosC();
                perl = new personalC();
                cm = new ArrayList<>();

                i.setInstitucion(rs.getInt("INSTITUCION"));

                p.setPeriodo(rs.getString("PERIODO"));

                ca.setCarrera(rs.getString("CARRERA"));
                ca.setAbreviatura(rs.getString("CARRERA_ABR"));

                css.setInstitucion(rs.getInt("INSTITUCION"));
                css.setPeriodo(rs.getString("PERIODO"));
                css.setCarrera(rs.getString("CARRERA"));
                css.setMalla(rs.getString("MALLA"));
                css.setCurso(rs.getString("CURSO"));
                css.setSeccion(rs.getString("SECCION"));
                css.setGrupo(rs.getString("GRUPO"));

                cu.setCurso(rs.getString("CURSO"));
                cu.setDescripcion(rs.getString("CURSO_DESC"));

                perl.setPersonal(rs.getString("PERSONAL"));

                per.setNombres(rs.getString("APELLIDOS_NOMBRES"));
                per.setTipodocumento(rs.getInt("TIPO_DOCUMENTO"));
                per.setNumero_documento(rs.getString("NRO_DOCUMENTO"));
                per.setFecha_nacimiento(rs.getDate("NACIMIENTO_FECHA"));
                per.setEmailP(rs.getString("EMAIL_PRINCIPAL"));
                per.setEmailS(rs.getString("EMAIL_OPCIONAL"));

                sp.setNivelModular(rs.getInt("CICLO"));
                sp.setTurno(rs.getInt("TURNO"));

                cm.add(new ColumnModel("U4", rs.getDate("U4")));
                cm.add(new ColumnModel("EP", rs.getDate("EP")));
                cm.add(new ColumnModel("U9", rs.getDate("U9")));
                cm.add(new ColumnModel("EF", rs.getDate("EF")));
                cm.add(new ColumnModel("AF", rs.getDate("AF")));

                mdc.setInstitucion(i);
                mdc.setPeriodo(p);
                mdc.setCarrera(ca);
                mdc.setCurso_seccion(css);
                mdc.setSeccion_periodo(sp);
                mdc.setCurso(cu);
                mdc.setPersonal(perl);
                mdc.setExamenes(cm);
                mdc.setPersona(per);

                lista.add(mdc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("controlEntregaActas " + e.getMessage());            
        }

        return lista;
    }

    public List<controlDocente> controlEntregaActasDetalle(int institucion, String periodo, String carrera, String personal) {
        List<controlDocente> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs;
        ResultSet rs;

        controlDocente mdc;
        institucionC i;
        periodoC p;
        personaC per;
        carrerasC ca;
        cursoSeccionC css;
        seccionPeriodoC sp;
        cursosC cu;
        personalC perl;
        List<ColumnModel> cm;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[CONTROL_DOCENTE_ACTAS] (1,?,?,?,?,'%','%','%','%')}");            
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, personal);

            rs = cs.executeQuery();

            while (rs.next()) {
                mdc = new controlDocente();
                i = new institucionC();
                p = new periodoC();
                per = new personaC();
                ca = new carrerasC();
                css = new cursoSeccionC();
                sp = new seccionPeriodoC();
                cu = new cursosC();
                perl = new personalC();
                cm = new ArrayList<>();              

                p.setPeriodo(rs.getString("PERIODO"));
                
                ca.setAbreviatura(rs.getString("CARRERA"));
                
                css.setPeriodo(rs.getString("PERIODO"));                                
                css.setCurso(rs.getString("CURSO"));
                css.setSeccion(rs.getString("SECCION"));
                
                cu.setCurso(rs.getString("CURSO"));
                cu.setDescripcion(rs.getString("ASIGNATURA"));

                perl.setPersonal(rs.getString("PERSONAL"));

                per.setNombres(rs.getString("NOMBRES"));
                
                sp.setNivelModular(rs.getString("CICLO").equals("I") ? 1 : rs.getString("CICLO").equals("II") ? 2 :rs.getString("CICLO").equals("III") ? 3 :rs.getString("CICLO").equals("IV") ? 4 :rs.getString("CICLO").equals("V") ? 5 :rs.getString("CICLO").equals("VI") ? 6 :rs.getString("CICLO").equals("VII") ? 7 :rs.getString("CICLO").equals("VIII") ? 8 :rs.getString("CICLO").equals("IX") ? 9 :rs.getString("CICLO").equals("X") ? 10 :rs.getString("CICLO").equals("XI") ? 11 :rs.getString("CICLO").equals("XII") ? 12 : 0);
                sp.setTurno(rs.getString("TURNO").equals("MAÑANA") ? 1 : rs.getString("TURNO").equals("TARDE") ? 2 : rs.getString("TURNO").equals("NOCHE") ? 3 : 0);

                cm.add(new ColumnModel("P.O I", rs.getString("P.O I")));
                cm.add(new ColumnModel("P.O II", rs.getString("P.O II")));
                cm.add(new ColumnModel("P.O III", rs.getString("P.O III")));
                cm.add(new ColumnModel("TR I", rs.getString("TR I")));
                cm.add(new ColumnModel("EXP I", rs.getString("EXP I")));
                cm.add(new ColumnModel("2U. P.O I", rs.getString("2U. P.O I")));
                cm.add(new ColumnModel("2U. P.O II", rs.getString("2U. P.O II")));
                cm.add(new ColumnModel("2U. P.O III", rs.getString("2U. P.O III")));
                cm.add(new ColumnModel("2U. TR II", rs.getString("2U. TR II")));
                cm.add(new ColumnModel("2U. EXP II", rs.getString("2U. EXP II")));
                cm.add(new ColumnModel("1. RELE", rs.getString("1. RELE")));
                cm.add(new ColumnModel("2. METO", rs.getString("2. METO")));
                cm.add(new ColumnModel("3. ACON", rs.getString("3. ACON")));
                cm.add(new ColumnModel("4. PTI", rs.getString("4. PTI")));
                cm.add(new ColumnModel("5. SUT", rs.getString("5. SUT")));
                cm.add(new ColumnModel("EX. SUS", rs.getString("EX. SUS")));
                cm.add(new ColumnModel("EX. APLA", rs.getString("EX. APLA")));

                mdc.setInstitucion(i);
                mdc.setPeriodo(p);
                mdc.setCarrera(ca);
                mdc.setCurso_seccion(css);
                mdc.setSeccion_periodo(sp);
                mdc.setCurso(cu);
                mdc.setPersonal(perl);
                mdc.setExamenes(cm);
                mdc.setPersona(per);

                lista.add(mdc);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            System.out.println("controlEntregaActasDetalle " + e.getMessage());            
        }

        return lista;
    }

    
    public void registrarControlActaDocente(controlDocente cd, String tipo_ex) {
        Connection c;
        CallableStatement cs;

        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.REGISTRO_ACTAS_DOCENTE (?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, cd.getInstitucion().getInstitucion());
            cs.setString(2, cd.getPeriodo().getPeriodo());
            cs.setString(3, cd.getCarrera().getCarrera());
            cs.setString(4, cd.getCurso_seccion().getMalla());
            cs.setString(5, cd.getCurso_seccion().getCurso());
            cs.setString(6, cd.getCurso_seccion().getSeccion());
            cs.setString(7, cd.getPersonal().getPersonal());
            cs.setString(8, tipo_ex);
            cs.setDate(9, new java.sql.Date(cd.getRecepcion_fecha().getTime()));
            cs.executeUpdate();
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
