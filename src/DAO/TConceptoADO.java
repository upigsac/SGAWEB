
package DAO;
import Conexiones.Conexion;
import ENTIDAD.CPendientes;
import ENTIDAD.ConceptosC;
import ENTIDAD.TTramites;

import java.sql.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class TConceptoADO extends Conexion {

    public List<ConceptosC> getTConceptos(int inst) {
        List<ConceptosC> tconcep = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.CONCEPTO_JAVA(?)}");
            cs.setInt(1, inst);
            rs = cs.executeQuery();
            while (rs.next()) {
                ConceptosC con = new ConceptosC();
                con.setTipconcepto(rs.getInt("TIPO_CONCEPTO"));
                con.setDescripcion(rs.getString("DESCRIPCION"));
                tconcep.add(con);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return tconcep;
    }

    public List<CPendientes> getTabConceptos(String persona, int inst, int tcon, String per, String descrip) {
        List<CPendientes> tabcon = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.TABLA_CONCEPTO_JAVA(?,?,?,?,?)}");
            cs.setString(1, persona);
            cs.setInt(2, inst);
            cs.setInt(3, tcon);
            cs.setString(4, per);
            cs.setString(5, descrip);
            rs = cs.executeQuery();
            while (rs.next()) {
                CPendientes con = new CPendientes();
                con.setAbreviatura(rs.getString("ABREVIATURA"));
                con.setConcepto(rs.getString("CONCEPTO"));
                con.setTconcepto(rs.getInt("TIPO_CONCEPTO"));
                con.setDescripcion(rs.getString("DESCRIPCION"));
                con.setCantidad(rs.getInt("CANTIDAD"));
                con.setMpago(rs.getDouble("MONTO"));
                con.setMparte(rs.getDouble("MONTO"));
                con.setSubtotal(rs.getDouble("MONTO"));
                con.setMtotal(rs.getDouble("MONTO"));
                con.setCuota(rs.getString("NUM_CUOTA") + "-" + rs.getString("NUM_PARTE"));
                con.setCarrera(rs.getString("CARRERA"));
                con.setPeriodo(rs.getString("PERCONCEPTO"));
                con.setTipo(2);
                tabcon.add(con);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return tabcon;
    }

    public List<CPendientes> getTabConceptosPagos(String persona, int inst, int tcon, String per, String descrip) {
        List<CPendientes> tabcon = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.TABLA_CONCEPTO_PAGOS_JAVA(?,?,?,?,?)}");
            cs.setString(1, persona);
            cs.setInt(2, inst);
            cs.setInt(3, tcon);
            cs.setString(4, per);
            cs.setString(5, descrip);
            rs = cs.executeQuery();
            int p = 2000;
            while (rs.next()) {

                CPendientes con = new CPendientes();
                con.setInstitucion(rs.getInt("INSTITUCION"));
                con.setAbreviatura(rs.getString("ABREVIATURA"));
                con.setConcepto(rs.getString("CONCEPTO"));
                con.setTconcepto(rs.getInt("TIPO_CONCEPTO"));
                con.setDescripcion(rs.getString("DESCRIPCION"));
                con.setCantidad(rs.getInt("CANTIDAD"));
                con.setMpago(rs.getDouble("MONTO"));
                con.setItem(p++);
                con.setMparte(rs.getDouble("MONTO"));
                con.setSubtotal(rs.getDouble("MONTO"));
                con.setMtotal(rs.getDouble("MONTO"));
                con.setCuota(rs.getString("NUM_CUOTA") + "-" + rs.getString("NUM_PARTE"));
                con.setCarrera(rs.getString("CARRERA"));
                con.setPeriodo(rs.getString("PERCONCEPTO"));
                con.setTipo(2);
                tabcon.add(con);

            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return tabcon;
    }

    public List<TTramites> gettramites() {
        List<TTramites> tabcon = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SEL_TIPO_TRAMITES_JAVA}");
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites tt = new TTramites();
                tt.setTipo_tramite(rs.getInt("TIPO_TRAMITE"));
                tt.setDesctramite(rs.getString("DESCTRAMITE"));
                tabcon.add(tt);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return tabcon;
    }

    public List<TTramites> gettramitesVentas() {
        List<TTramites> tabcon = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SEL_CONCEPTOS_JAVA}");
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites tt = new TTramites();
                tt.setTipo_tramite(rs.getInt("TIPO_CONCEPTO"));
                tt.setDesctramite(rs.getString("DESCRIPCION"));
                tabcon.add(tt);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return tabcon;
    }

    public List<ConceptosC> getConceptos(int insti, String texto) {
        List<ConceptosC> tconcep = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SEL_CONCEPTOS(?,?)}");
            cs.setInt(1, insti);
            cs.setString(2, texto);
            rs = cs.executeQuery();
            while (rs.next()) {
                ConceptosC con = new ConceptosC();
                con.setConcepto(rs.getString("CONCEPTO"));
                con.setDescripcion(rs.getString("DESCRIPCION"));
                con.setTipo_concepto(rs.getInt("TIPO_CONCEPTO"));
                tconcep.add(con);
            }
            cerrarConexion(c);
            cerrarCall(cs);
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return tconcep;
    }

    public List<TTramites> listtdocumentos() {
        List<TTramites> tconcep = new ArrayList<>();
        Connection c = ConexionWeb();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_TIPO_DOCUMENTO_JAVA}");
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                t.setDocumento(rs.getString("DESCRIPCION"));
                tconcep.add(t);
            }
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return  tconcep;
    }
    public List<TTramites> listanucomprobante(String numcomp) {
        List<TTramites> tconcep = new ArrayList<>();
        Connection c = ConexionWeb();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{CALL DI.SEL_BUSC_COMPROBANTE}");
            rs = cs.executeQuery();
            while (rs.next()) {
                TTramites t = new TTramites();
                t.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                t.setDocumento(rs.getString("DESCRIPCION"));
                tconcep.add(t);
            }
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return  tconcep;
    }

    /**
     *
     * @param numcomp
     * @param tdoc
     * @return
     */
    public TTramites getcomprobante(String numcomp,int tdoc){
        TTramites t=new TTramites();
        Connection c = ConexionWeb();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs=c.prepareCall("{CALL DI.SEL_BUSC_COMPROBANTE(?,?)}");
            cs.setString(1, numcomp);
            cs.setInt(2, tdoc);
            rs=cs.executeQuery();
            while(rs.next()){
                t.setTipo_documento(rs.getInt("TIPO_DOCUMENTO"));
                t.setDocumento(rs.getString("NUM_COMPROBANTE"));
                t.setPerempresa(rs.getString("PERSONA"));
            }
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return t;
    } 
    public int cancelacion_comprobante(String obs,int tipo,String comprobante,String usuario){
        Connection c = ConexionWeb();
        CallableStatement cs = null;
        int r=1;
        try {
            cs=c.prepareCall("{CALL DI.UPD_CANCEL_PAGOS_JAVA(?,?,?,?)}");
            cs.setString(1, obs);
            cs.setInt(2, tipo);
            cs.setString(3, comprobante);
            cs.setString(4, usuario);
            r=cs.executeUpdate();
        } catch (SQLException e) {
            showMessageDialog(null, e.getMessage());
        }
        return r;
    }
}
