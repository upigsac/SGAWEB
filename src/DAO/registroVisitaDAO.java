/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.registroVisita;
import Conexiones.Conexion;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.oficinaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class registroVisitaDAO {

    public String nombreXDNI(String dni) {

        String dato = null;

        try {
            Connection c = null;
            CallableStatement cs = null;
            ResultSet rs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (4,?,'','','','','','','','')}");
            cs.setString(1, dni);

            rs = cs.executeQuery();
            while (rs.next()) {
                dato = rs.getString("apellidos_nombres");
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("problema en nombre x dni " + e.getMessage());
        }
        return dato;
    }

    public String idDelRegistrado(String dni) {

        String dato = null;

        try {
            Connection c = null;
            CallableStatement cs = null;
            ResultSet rs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (10,?,'','','','','','','','')}");
            cs.setString(1, dni);

            rs = cs.executeQuery();
            while (rs.next()) {
                dato = rs.getString("ID");
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("problema en nombre x dni " + e.getMessage());
        }
        return dato;
    }

    public List<registroVisita.registroVisitaC> listarVisitas(String dni, String ape_nom, String oficina) {

        registroVisita.registroVisitaC dato = null;
        List<registroVisita.registroVisitaC> listado = new ArrayList<>();

        //System.out.println("dni " + dni + " " + ape_nom + " " + oficina);

        try {
            Connection c = null;
            c = ConexionWeb();
            CallableStatement pr = null;
            ResultSet rs = null;
            pr = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (5,?,?,?,'','','','','','')}");
            pr.setString(1, dni);
            pr.setString(2, ape_nom);
            pr.setString(3, oficina);

            rs = pr.executeQuery();
            while (rs.next()) {
                dato = new registroVisita.registroVisitaC();
                dato.setId(rs.getString("id"));
                dato.setDni(rs.getString("dni"));
                //dato.setN_visita(rs.getInt("visita"));
                dato.setFecha_visita(rs.getString("fecha_visita"));
                dato.setApellidos_nombres(rs.getString("apellidos_nombres"));
                dato.setOficina(rs.getInt("oficina"));
                dato.setOficina_texto(rs.getString("oficina_texto"));
                dato.setAutorizacion(rs.getString("autorizacion"));
                dato.setAutorizacion_nombre(rs.getString("autorizacion_nombre"));
                dato.setPertenencia(rs.getBoolean("pertenencia"));
                dato.setDato_pertenencia(rs.getString("dato_pertenencia"));
                dato.setMotivo(rs.getString("motivo"));
                dato.setHora_ingreso(rs.getString("hora_ingreso"));
                dato.setHora_salida(rs.getString("hora_salida"));
                dato.setEstado_registro(rs.getInt("estado_registro"));
                listado.add(dato);
            }
            cerrarCall(pr);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("problema en ultima visita " + e.getMessage());
        }
        return listado;
    }

    public LinkedHashMap<String, String> fechasDeVisita(String dni) {

        LinkedHashMap<String, String> lista = new LinkedHashMap<>();
        String llave = null;
        String valor = null;

        try {
            Connection c = null;
            CallableStatement cs = null;
            ResultSet rs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (6,?,'','','','','','','','')}");
            cs.setString(1, dni);

            rs = cs.executeQuery();
            while (rs.next()) {
                llave = rs.getString("ID");
                valor = rs.getString("VISITA");
                lista.put(valor, llave);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("problema en nombre x dni " + e.getMessage());
        }
        return lista;
    }

    public registroVisita.registroVisitaC ultimaVisita(String dni) {

        registroVisita.registroVisitaC dato = null;

        try {
            Connection c = null;
            c = ConexionWeb();
            CallableStatement pr = null;
            ResultSet rs = null;
            pr = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (2,?,'','','','','','','','')}");
            pr.setString(1, dni);

            rs = pr.executeQuery();
            while (rs.next()) {
                dato = new registroVisita.registroVisitaC();
                dato.setId(rs.getString("id"));
                dato.setDni(rs.getString("dni"));
                dato.setApellidos_nombres(rs.getString("apellidos_nombres"));
                dato.setOficina(rs.getInt("oficina"));
                dato.setAutorizacion(rs.getString("autorizacion"));
                dato.setPertenencia(rs.getBoolean("pertenencia"));
                dato.setDato_pertenencia(rs.getString("dato_pertenencia"));
                dato.setMotivo(rs.getString("motivo"));
                dato.setHora_ingreso(rs.getString("hora_ingreso"));
                dato.setEstado_registro(rs.getInt("estado_registro"));
            }

            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("problema en ultima visita " + e.getMessage());
        }
        return dato;
    }

    public registroVisita.registroVisitaC datosVisita(String visita) {

        registroVisita.registroVisitaC dato = null;

        try {
            Connection c = null;
            c = ConexionWeb();
            CallableStatement pr = null;
            ResultSet rs = null;
            pr = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (7,'','','','','','','',?,'')}");
            pr.setString(1, visita);

            rs = pr.executeQuery();
            while (rs.next()) {
                dato = new registroVisita.registroVisitaC();
                dato.setId(rs.getString("id"));
                dato.setDni(rs.getString("dni"));
                dato.setFecha_visita(rs.getString("fecha_visita"));
                dato.setApellidos_nombres(rs.getString("apellidos_nombres"));
                dato.setOficina(rs.getInt("oficina"));
                dato.setAutorizacion(rs.getString("autorizacion"));
                dato.setPertenencia(rs.getBoolean("pertenencia"));
                dato.setDato_pertenencia(rs.getString("dato_pertenencia"));
                dato.setMotivo(rs.getString("motivo"));
                dato.setHora_ingreso(rs.getString("hora_ingreso"));
                dato.setHora_salida(rs.getString("hora_salida"));
                dato.setEstado_registro(rs.getInt("estado_registro"));
            }

            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("problema en ultima visita " + e.getMessage());
        }
        return dato;
    }

    public void registrarVisita(boolean visitado, registroVisita.registroVisitaC mdc) {

        try {
            Connection c = null;
            c = ConexionWeb();
            CallableStatement pr = null;

            //por primera vez
            if (!visitado) {
                pr = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (1,?,?,?,?,?,?,?,?,'')}");
                pr.setString(1, mdc.getDni());
                pr.setString(2, mdc.getApellidos_nombres());
                pr.setInt(3, mdc.getOficina());
                pr.setString(4, mdc.getAutorizacion());
                pr.setString(5, mdc.getMotivo());
                pr.setBoolean(6, mdc.isPertenencia());
                pr.setString(7, mdc.getDato_pertenencia());
                pr.setString(8, mdc.getHora_ingreso());
            } else {
                //para registrar su salida
                pr = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (3,?,'','','','','','','',?)}");
                pr.setString(1, mdc.getDni());
                pr.setString(2, mdc.getHora_salida());
            }

            pr.executeUpdate();
            cerrarConexion(c);

        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("problema en registrar visita " + e.getMessage());
        }
    }

    public List<oficinaC> listarOficinas() {

        List<oficinaC> lista = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        oficinaC carre = null;

        try {

            c = Conexion.ConexionWeb();
            cs = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (8,'','','','','','','','','')}");
            rs = cs.executeQuery();

            while (rs.next()) {
                carre = new oficinaC();
                carre.setInstitucion(rs.getInt("institucion"));
                carre.setOficina(rs.getInt("oficina"));
                carre.setDescripcion(rs.getString("descripcion"));
                carre.setAbreviatura(rs.getString("abreviatura"));
                carre.setEstado_registro(rs.getInt("estado_registro"));
                lista.add(carre);
            }
            Conexion.cerrarCall(cs);
            Conexion.cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problema en listar oficinas " + e.getMessage());
        }
        return lista;
    }

    public List<registroVisita.personal_oficina_cargo> listarPersonalDeOficina(int oficina) {

        List<registroVisita.personal_oficina_cargo> lista = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        registroVisita.personal_oficina_cargo carre = null;

        try {

            c = Conexion.ConexionWeb();
            cs = c.prepareCall("{CALL DI.REGISTRO_DE_VISITAS (9,'','',?,'','','','','','')}");
            cs.setInt(1, oficina);
            rs = cs.executeQuery();

            while (rs.next()) {
                carre = new registroVisita.personal_oficina_cargo();
                carre.setPersonal(rs.getString("personal"));
                carre.setPersona(rs.getString("persona"));
                carre.setNombre_completo(rs.getString("nombre_completo"));
                carre.setInstitucion(rs.getString("institucion"));
                carre.setOficina(rs.getInt("oficina"));
                carre.setOficina_texto(rs.getString("oficina_texto"));
                carre.setCargo(rs.getInt("cargo"));
                carre.setCargo_texto(rs.getString("cargo_texto"));
                carre.setEstado_registro(rs.getInt("estado_registro"));
                lista.add(carre);
            }
            Conexion.cerrarCall(cs);
            Conexion.cerrarConexion(c);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problema en listar personal oficina " + e.getMessage());
        }
        return lista;
    }
}
