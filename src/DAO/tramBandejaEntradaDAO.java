/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.tramTramiteBandeja;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class tramBandejaEntradaDAO {
    public List<tramTramiteBandeja.detalleBandeja> mostrarTramiteSeguimiento(String oficina,Date fechaInicio,Date fechaFin,String estadoCorreo) {
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        List<tramTramiteBandeja.detalleBandeja> lista = new ArrayList<>();
        tramTramiteBandeja.detalleBandeja item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MOSTRAR_BANDEJA_TRAMITE(?,?,?,?,?)}");
            cs.setInt("ITEMWEB", 1);
            cs.setString("OFICINA", oficina);
            cs.setString("FECHAINICIO", util.convertDate(fechaInicio));
            cs.setString("FECHAHASTA", util.convertDate(fechaFin));
            cs.setString("ESTADOCORREO", estadoCorreo);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new tramTramiteBandeja.detalleBandeja();                
                item.setExpediente(rs.getString("EXPEDIENTE"));                            
                item.setTipoTramite(rs.getInt("TIPO_TRAMITE"));                
                item.setDesTipoTramite(rs.getString("DESTIPOTRAMITE"));          
                item.setTramite(rs.getInt("TRAMITE"));                
                item.setDesTramite(rs.getString("DESTRAMITE"));                
                item.setTipoPersona(rs.getInt("TIPO_PERSONA"));                
                item.setPersona_empresa(rs.getString("PERSONA_EMPRESA"));                
                item.setDesPersona(rs.getString("DESPERSONA"));
                item.setAlumno(rs.getString("ALUMNO"));                
                item.setObservacion(rs.getString("OBSERVACION"));                
                item.setOficina(rs.getInt("OFICINA"));                
                item.setDesOficina(rs.getString("DESOFICINA"));                
                item.setItem(rs.getInt("ITEM"));                
                item.setImportancia(rs.getInt("IMPORTANCIA"));                
                item.setFechaEntrega(rs.getDate("FECHA_INGRESO"));
                item.setHoraEntrega(rs.getTime("HORA_INGRESO"));                
                item.setAsunto(rs.getString("ASUNTO"));             
                item.setUsuarioEmisor(rs.getString("USUARIO_EMISOR"));             
                item.setEstadoCorreo(rs.getInt("ESTADO_CORREO"));
                item.setEstadoTramite(rs.getInt("ESTADO_TRAMITE"));
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
