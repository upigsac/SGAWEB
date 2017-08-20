package DAO;
import Beans.util;
import Beans.ventas;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.alumnoC;
import ENTIDAD.alumnoCarreraC;

import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.periodoSeccionVencimientoC;
import ENTIDAD.seccionPeriodoC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;



public class ventasDAO {
	
	
	public String insertar(int  institucion,String periodo,String persona,String carrera,String malla,String seccion,String grupo,String[] cursoL) {
		
	
	    System.out.println("--------------------------------------------");
	    System.out.println("institucion "+ institucion);
	    System.out.println("periodo " + periodo);
	    System.out.println("persona " + persona);
	    System.out.println("carrera " + carrera);
	    System.out.println("malla " + malla);
	    System.out.println("seccion " + seccion);
		alumnoC alumno = null;
		alumnoCarreraC alumnoCarrera=null;
		alumnoPeriodoC alumnoPeriodo=null;
        CallableStatement cs = null;
        CallableStatement css = null;
        CallableStatement csss=null;
        CallableStatement cssss=null;     
        String codigo = "";   
        alumno =new alumnoDAO().mostrarAlumno(institucion, persona);
        Connection c;        
        c = ConexionWeb();
        
        try {
        	c.setAutoCommit(false);
            if(alumno ==null ){
            	alumno=new alumnoC(institucion, null, persona, null, 1);
            	cs = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO(?,?,?,?,?,?)}");
                cs.setInt("ITEMWEB", 1);
                cs.setInt("INSTITUCION", alumno.getInstitucion());
                cs.setString("ALUMNO", alumno.getAlumno());
                cs.setString("PERSONA", alumno.getPersona());
                cs.setInt("ESTADO_REGISTRO", alumno.getEstadoRegistro());
                cs.setString("PERIODO", periodo);
                cs.registerOutParameter("ALUMNO", Types.VARCHAR);      
                cs.executeUpdate();    
                alumno.setAlumno(cs.getString("ALUMNO"));                
                System.out.println("CODIGO DE ALUMNO GENERADO : "+ cs.getString("ALUMNO"));
                 
            }
            
        	System.out.println("--- alumno carrera -----");
            alumnoCarrera =new alumnoCarreraDAO().mostrarAlumnoCarrera(institucion, alumno.getAlumno());    
            if(alumnoCarrera ==null){          	
            
            	alumnoCarrera=new alumnoCarreraC(institucion, carrera, alumno.getAlumno(), malla, "A", "0000", 0, 0, 0, null, 6);
            	
            	 css = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ACA_ALUMNO_CARRERA(?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?)}");
                 css.setInt("ITEMWEB", 1);
                 css.setInt("INSTITUCION", alumnoCarrera.getInstitucion());
                 css.setString("CARRERA", alumnoCarrera.getCarrera());
                 css.setString("ALUMNO", alumnoCarrera.getAlumno());
                 css.setString("MALLA", alumnoCarrera.getMalla());
                 css.setString("CATEGORIA", alumnoCarrera.getCategoria());
                 css.setString("DESCUENTO", alumnoCarrera.getDescuento());
                 css.setDouble("CREDITOS_ACUMULADOS", alumnoCarrera.getCreditosAcumulados());
                 css.setDouble("PROMEDIO_PONDERADO", alumnoCarrera.getPromedioPonderado());
                 css.setInt("ORDEN_PONDERADO", alumnoCarrera.getOrdenPonderado());                 
                 css.setString("PERIODO_INGRESO", alumnoCarrera.getPeriodoIngreso());
                 css.setString("AUT_PER_MALLA", null);
                 css.setString("AUT_DOC_MALLA", null);
                 css.setString("AUT_OBS_MALLA", null);
                 css.setString("AUT_PER_CARRERA", null);
                 css.setString("AUT_DOC_CARRERA", null);
                 css.setString("AUT_OBS_CARRERA", null);
                 css.setString("AUT_PER_CATEGORIA", null);
                 css.setString("AUT_DOC_CATEGORIA", null);
                 css.setString("AUT_OBS_CATEGORIA", null);                 
                 css.setInt("ESTADO_REGISTRO", alumnoCarrera.getEstadoRegistro());
                 css.executeUpdate();
            }
            
        	System.out.println("--- alumno periodo -----");
            alumnoPeriodo =new alumnoPeriodoDAO().mostrarAlumnoPeriodo(institucion, periodo, alumnoCarrera.getCarrera(), alumnoCarrera.getMalla(), alumnoCarrera.getAlumno());    
            if(alumnoPeriodo==null){
               	System.out.println("--- NUEVO alumno periodo -----");
            		alumnoPeriodo=new alumnoPeriodoC(institucion, periodo, alumno.getAlumno(), alumnoCarrera.getCarrera(), alumnoCarrera.getMalla(), 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0.0, 0, 0, 0.0, 0, null, null, null, null, null, null, null, null, 0.0, 0.0, 0.0, 0.0, null, null, null, null, null, null, null, null, null, null, null, null, 5);	
            }
            
            
            
            csss = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_PERIODO(?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?)}");
            csss.setInt("ITEMWEB", 1);
            csss.setInt("INSTITUCION", alumnoPeriodo.getInstitucion());
            csss.setString("PERIODO", alumnoPeriodo.getPeriodo());
            csss.setString("ALUMNO", alumnoPeriodo.getAlumno());
            csss.setString("CARRERA", alumnoPeriodo.getCarrera());
            csss.setString("MALLA", alumnoPeriodo.getMalla());
            csss.setInt("CURSOS_MATRICULADOS", alumnoPeriodo.getCursos_matriculados());	            
            csss.setInt("CURSOS_APROBADOS", alumnoPeriodo.getCursos_aprobados());
            csss.setInt("CURSOS_CONVALIDADOS", alumnoPeriodo.getCursos_convalidados());
            csss.setInt("CURSOS_RETIRADOS", alumnoPeriodo.getCursos_retirados());
            
            csss.setInt("CREDITOS_MATRICULADOS", alumnoPeriodo.getCreditos_matriculados());
            csss.setInt("CREDITOS_APROBADOS", alumnoPeriodo.getCreditos_aprobados());
            csss.setInt("CREDITOS_CONVALIDADOS", alumnoPeriodo.getCreditos_convalidados());
            csss.setInt("CREDITOS_RETIRADOS", alumnoPeriodo.getCreditos_retirados());
            csss.setString("SECCION_REFERENCIAL", alumnoPeriodo.getSeccion_referencial());
            csss.setInt("NIVEL_REFERENCIAL", alumnoPeriodo.getNivel_referencial());
            csss.setDouble("PROMEDIO_SEMESTRAL", alumnoPeriodo.getPromedio_semestral());	            
            csss.setInt("ORDEN_SEMESTRAL", alumnoPeriodo.getOrden_semestral());
            csss.setInt("ORDEN_NIVEL", alumnoPeriodo.getOrden_nivel());
            csss.setDouble("PROMEDIO_PONDERADO", alumnoPeriodo.getPromedio_ponderado());
            
            csss.setInt("ORDEN_PONDERADO", alumnoPeriodo.getOrden_ponderado());
            csss.setString("DOCUMENTO_AUTORIZA", alumnoPeriodo.getDocumento_autoriza());
            csss.setString("RESOLUCION_NUMERO", alumnoPeriodo.getResolucion_numero());
            csss.setString("RESOLUCION_FECHA", util.convertDate(alumnoPeriodo.getResolucion_fecha(), "dd-MM-yyyy"));
            csss.setString("CODIGO_RESERVA", alumnoPeriodo.getCodigo_reserva());
            csss.setString("OBSERVACION", alumnoPeriodo.getObservacion());
            csss.setString("FECHA_MATRICULA", util.convertDate(alumnoPeriodo.getFecha_matricula(), "dd-MM-yyyy") );
            csss.setString("CATEGORIA", alumnoPeriodo.getCategoria());
            csss.setString("DESCUENTO", alumnoPeriodo.getDescuento());
            csss.setDouble("MONTO_BASE", alumnoPeriodo.getMonto_base());
            
            csss.setDouble("MONTO_PAGO", alumnoPeriodo.getMonto_pago());
            csss.setDouble("MONTO_PAGO_ADC", alumnoPeriodo.getMonto_pago_adc());
            csss.setDouble("MONTO_ADICIONAL", alumnoPeriodo.getMonto_adicinal());
            csss.setString("AUT_PER_MATRICULA", alumnoPeriodo.getAut_per_matricula());
            csss.setString("AUT_DOC_MATRICULA", alumnoPeriodo.getAut_doc_matricula());
            csss.setString("AUT_OBS_MATRICULA", alumnoPeriodo.getAut_obs_matricula());	            
            csss.setString("AUT_PER_MALLA", alumnoPeriodo.getAut_per_malla());
            csss.setString("AUT_DOC_MALLA", alumnoPeriodo.getAut_doc_malla());
            csss.setString("AUT_OBS_MALLA", alumnoPeriodo.getAut_obs_malla());	            
            csss.setString("AUT_PER_CARRERA", alumnoPeriodo.getAut_per_carrera());
            
            csss.setString("AUT_DOC_CARRERA", alumnoPeriodo.getAut_doc_carrera());
            csss.setString("AUT_OBS_CARRERA", alumnoPeriodo.getAut_obs_carrera());	            
            csss.setString("AUT_PER_CATEGORIA", alumnoPeriodo.getAut_per_categoria());
            csss.setString("AUT_DOC_CATEGORIA", alumnoPeriodo.getAut_doc_categoria());
            csss.setString("AUT_OBS_CATEGORIA", alumnoPeriodo.getAut_obs_categoria());  
            csss.setInt("ESTADO_REGISTRO", alumnoPeriodo.getEstado_registro());
            csss.executeUpdate();
            
           
            
            for(String item: cursoL){
            	
            	cssss = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_ALUMNO_CURSO_SECCION (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            	cssss.setInt("ITEMWEB", 1);
            	cssss.setInt("INSTITUCION", institucion);
            	cssss.setString("PERIODO", periodo);
            	cssss.setString("CARRERA", carrera);
            	cssss.setString("MALLA", malla);
            	cssss.setString("CURSO", item);
            	cssss.setString("SECCION", seccion);
            	cssss.setString("ALUMNO", alumno.getAlumno());
            	cssss.setString("GRUPO", grupo);
            	cssss.setDouble("PROMEDIO", 0);
            	cssss.setString("RESOLUCION", null);
            	cssss.setString("OBSERVACION", null);
            	cssss.setBoolean("CONVALIDADO", false);
            	cssss.setString("PERSONA_CONVALIDA", null);
            	cssss.setString("AUT_PER_REGISTRO", null);
            	cssss.setString("AUT_DOC_REGISTRO", null);
            	cssss.setString("AUT_OBS_REGISTRO", null);
            	cssss.setInt("ESTADO_REGISTRO", 1); 
            	cssss.executeUpdate();
            }

       
            c.commit();
             
            System.out.println("ingreso todo c");
            
        } catch (SQLException e) {        
        	
        	System.out.println("1 ERROR " + e.getMessage());        
             if (c != null) {
                try {
                    deshacerCambios(c);                    
                } catch (Exception ex) {                                        
                    System.out.println("2 ERROR" + ex.getMessage());
                }
            }
        }finally{
            cerrarCall(cs);
            cerrarCall(css);
            cerrarCall(csss);
            cerrarCall(cssss);
            cerrarConexion(c);
        
        }
        return codigo;
    }
	
    
    
    public String insertar(int webTipo, int institucion,String periodo,String carrera,String seccion,String paquete,String producto,String persona,int vencimiento,int nivelModular) {
        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_PERSONA_FINAL(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, webTipo);
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            cs.setString(4, carrera);
            cs.setString(5, seccion);
            cs.setString(6,  paquete);
            cs.setString(7,  producto);
            cs.setString(8,  persona);
            cs.setInt(9,  vencimiento);
            cs.setInt(10,  nivelModular);
            rpta = cs.executeUpdate() == 1;
            if (rpta) {
                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println("ERROR SP_VEN_PERSONA_FINAL: " + ex.getMessage());
        }
        return codigo;
    }
    
    
    public String cerrarMatricula( int institucion,String periodo,String carrera,String seccion,int estadoRegistro) {
        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("UPDATE SIGU.HOR_PERIODO_SECCION SET  ESTADO_REGISTRO=?   WHERE INSTITUCION=? AND PERIODO=? AND CARRERA=? AND SECCION=?");
           
            
            cs.setInt(1,  estadoRegistro);
            
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            cs.setString(4, carrera);
            cs.setString(5, seccion);            
           
            rpta = cs.executeUpdate() == 1;
            if (rpta) {
                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println("ERROR SP_VEN_PERSONA_FINAL: " + ex.getMessage());
        }
        return codigo;
    }
    
    
    
    
    
    
    public String insertarSeccionVencimiento(periodoSeccionVencimientoC item) {
        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_PERIODO_VENCIMIENTO(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());            
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getCarrera());
            cs.setString(4, item.getSeccion());
            cs.setInt(5,  item.getItem());
            cs.setString(6,  util.convertDate(item.getFechaInicio(), "dd-MM-yyyy") );
            cs.setInt(7,  item.getVencimiento());
            cs.setInt(8,  item.getEstadoRegistro());            
            rpta = cs.executeUpdate() == 1;
            if (rpta) {
                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
    
    
    
    
    
     public String insertar(int webTipo, int institucion,String periodo,String carrera,String seccion,String paquete,String producto,String persona,int vencimiento,int nivelModular,List<ventas.descuentoSeccion> itemDescuento) {
        Connection c;
        c = ConexionWeb();
        CallableStatement cs=null;
        CallableStatement css =null;
        String codigo = "";
        try {
             c.setAutoCommit(false);
            cs = c.prepareCall("{CALL DI.SP_VEN_PERSONA_FINAL(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, webTipo);
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            cs.setString(4, carrera);
            cs.setString(5, seccion);
            cs.setString(6,  paquete);
            cs.setString(7,  producto);
            cs.setString(8,  persona);
            cs.setInt(9,  vencimiento);
            cs.setInt(10,  nivelModular);
            cs.executeUpdate();
            
            for (ventas.descuentoSeccion item : itemDescuento) {
            
                
                css = c.prepareCall("{CALL DI.VEN_CUENTA_PERSONA(?,?,?,?,?,?,?,?,?)}");
                css.setInt(1, institucion);
                css.setString(2, periodo);
                css.setString(3,  persona);
                css.setString(4,  item.getConcepto());
                css.setInt(5, vencimiento );// vencimiento
                css.setString(6, item.getDescuento());
                css.setDouble(7, item.getMontoDecuento()); // monto descuento
                css.setString(8, carrera);
                css.setString(9, seccion);
                css.executeUpdate();
                
            }
            
            
            
            c.commit();
           
        } catch (SQLException e) {        
             System.out.println( e.getMessage());
              
             if (c != null) {
                try {
                    deshacerCambios(c);                    
                } catch (Exception ex) {                    
                	System.out.println( ex.getMessage());
                }
            }
        }finally{
            cerrarCall(cs);
            cerrarConexion(c);
        }
        
        return codigo;
    }
     
      public String insertarProducto(int institucion,String producto,String desProducto,int tipoProducto,String paquete,String carrera,int tipoFrecuencia,List<ventas.detalleProductoConcepto> productoConceptoL ,String[] cursos) {
          
        Connection c;
        CallableStatement cs=null;
        CallableStatement css;
        CallableStatement csss;
        CallableStatement cssss;
        c = ConexionWeb();
        String codigo = "";
        try {
             c.setAutoCommit(false);
           
            cs = c.prepareCall("{CALL  DI.SP_VEN_CREAR_PRODUCTO(?,?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, producto);
            cs.setString(3, desProducto);            
            cs.setInt(4,tipoProducto);
            cs.setString(5,paquete);
            cs.setString(6,carrera); 
            cs.setInt(7,tipoFrecuencia); // SEDE
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeUpdate();
             
            producto=cs.getString(2);
            
            for (ventas.detalleProductoConcepto itemConcepto : productoConceptoL) {
                  
                    css = c.prepareCall("{CALL  DI.SP_VEN_CREAR_PRODUCTO_CONCEPTO(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?)}");
                    css.setInt(1, institucion);
                    css.setString(2, paquete);//
                    css.setString(3, producto);            
                    css.setString(4,itemConcepto.getPeriodo());
                    css.setString(5,itemConcepto.getConcepto());
                    css.setString(6,itemConcepto.getDesconcepto());
                    css.setInt(7,itemConcepto.getTipoConcepto()); 
                    css.setString(8,itemConcepto.getDescuento());
                    css.setInt(9,1);  // NIVEL MODULAR
                    css.setInt(10,1);// TIPO NIVEL MODULAR
                    css.setInt(11,itemConcepto.getNumCuota());
                    css.setDouble(12,itemConcepto.getMontoPago());
                    css.setInt(13,itemConcepto.getTipoMoneda());
                    css.setInt(14,0); // TIPO INTEREZ
                    css.setDouble(15,0.0);// MONTO INTEREZ
                    css.registerOutParameter(5, Types.VARCHAR);
                    css.executeUpdate();
                    
                    itemConcepto.setConcepto(css.getString(5));   
                    System.out.println(itemConcepto.getConcepto());
                    for (int x=0;x< itemConcepto.getDescuentoL().size();x++) {
                    cssss = c.prepareCall("{CALL  [DI].[SP_VEN_CREAR_PRODUCTO_CONCEPTO_DESCUENTO](?,?,?,?,?)}");
                    cssss.setInt(1, institucion);
                    cssss.setString(2, itemConcepto.getPeriodo());
                    cssss.setString(3, itemConcepto.getConcepto());
                    cssss.setInt(4, itemConcepto.getTipoConcepto());
                    cssss.setString(5, itemConcepto.getDescuentoL().get(x).getDescuento());
                    cssss.executeUpdate();
                }

                    
            }
            
            
            for (String itemCurso : cursos) {
                 
                    csss = c.prepareCall("{CALL  DI.SP_VEN_PRODUCTO_CURSO(?,?,?,?,?,?,?)}");
                    csss.setInt(1, institucion);
                    csss.setString(2, paquete);
                    csss.setString(3, producto);
                    csss.setString(4, carrera);
                    csss.setString(5, "201501");//MALLA
                    csss.setString(6, itemCurso.toString());//MALLA
                    csss.setInt(7, 1); //estado registro
                    csss.executeUpdate();
            }
            
            
            c.commit();

          
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {        
        	
        	System.out.println(e.getMessage());
            
              
             if (c != null) {
                try {
                    deshacerCambios(c);                    
                } catch (Exception ex) {                    
                    
                    System.out.println(ex.getMessage());
                }
            }
        }finally{
            cerrarCall(cs);
            cerrarConexion(c);
        }
        return codigo;
    }
     
    
    
    public String insertarProductoSeccion(seccionPeriodoC item) {
        
     
        Connection c=null;
        CallableStatement cs=null;
        String rpta="EXITO";
       
        try {
            c = ConexionWeb();
            c.setAutoCommit(false);
            cs = c.prepareCall("{CALL DI.SP_VEN_CREAR_PRODUCTO_SECCION(?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?)}");
            cs.setInt(1, item.getInstitucion());
            cs.setString(2, item.getPeriodo());
            cs.setString(3, item.getSeccion());
            cs.setString(4, item.getCarrera());
            cs.setInt(5,item.getTurno());
            cs.setInt(6,item.getNivelModular());
            cs.setInt(7,1); // TIPO_NIVEL_MODULAR
            cs.setInt(8,item.getSede()); // SEDE
            cs.setInt(9,item.getVacantes());
            cs.setString(10,util.convertDate(item.getFechaInicio(), "dd-MM-yyyy"));            
            cs.setString(11,util.convertDate(item.getFechaFin(), "dd-MM-yyyy"));
            cs.setInt(12,item.getFrecuenciaDia()); // frecuencia -dia
            cs.setInt(13,item.getFrecuenciaHora()); // frecuencia -hora
            cs.setString(14,item.getPaquete()); 
            cs.setString(15,item.getProducto()); 
            cs.setInt(16,item.getVencimiento()); 
            cs.setInt(17,item.getEstadoRegistro()); 

            cs.executeUpdate();
            c.commit();
          
        } catch (SQLException ex) {
            rpta=ex.getMessage();
            System.out.println(ex.getMessage());
             deshacerCambios(c);
        }finally{
             cerrarCall(cs);
            cerrarConexion(c);
        }
        return rpta;
    }
    
    public String ElimnarProductoSeccion(int institucion,String periodo,String carrera,String seccion,String paquete,String producto) {

        Connection c;
        CallableStatement cs;
        boolean rpta;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_ELIMNAR_PRODUCTO_SECCION(?,?,?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            cs.setString(5,paquete);
            cs.setString(6,producto);
            

            rpta = cs.executeUpdate() == 1;
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
    
    
    public List<ventas.institucionCarrera> mostrarCarreraInstitucion(int institucion, String periodo,String sede) {

        List<ventas.institucionCarrera> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ventas.institucionCarrera item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_CARRERA_INSTITUCION(?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, sede);
            rs = cs.executeQuery();

            while (rs.next()) {
               
                item = new ventas.institucionCarrera();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDescarrera(rs.getString("DESCARRERA"));
                item.setPrefijo(rs.getString("PREFIJO"));
                item.setTipoCarrera(rs.getInt("TIPO_CARRERA"));
                item.setDesTipoCarrera(rs.getString("DESTIPOCARRERA"));
                item.setTotalSecciones(rs.getInt("TOTAL_SECCIONES"));
                item.setTotalMatricula(rs.getInt("TOTAL_MATRICULA"));
                item.setTotalCerrado(rs.getInt("TOTAL_CERRADO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
           
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    public List<ventas.carreraSecciones> mostrarCarreraSeccion(int webTipo, int institucion, String periodo,String sede,String carrera,String persona,String estadoRegistro,String tipoFrecuencia,String turno,String anio,String mes,String tipoProducto,String desProducto,String frecuenciaDia) {
        

        List<ventas.carreraSecciones> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ventas.carreraSecciones item ;
        
    

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_CARRERA_SECCION(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, webTipo);
            cs.setInt(2, institucion);
            cs.setString(3, periodo);
            cs.setString(4, sede);
            cs.setString(5, carrera);
            cs.setString(6, persona);
            cs.setString(7, estadoRegistro);
            cs.setString(8, tipoFrecuencia);
            cs.setString(9, turno);
            cs.setString(10, anio);
            cs.setString(11, mes);
            cs.setString(12, tipoProducto);
            cs.setString(13, desProducto);
            cs.setString(14, frecuenciaDia);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ventas.carreraSecciones();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setDesCarrera(rs.getString("DESCARRERA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setDesSeccion(rs.getString("DESSECCION"));
                item.setPaquete(rs.getString("PAQUETE"));                
                item.setProducto(rs.getString("PRODUCTO"));
                item.setFechaInicio(rs.getDate("FEC_CLASES_INI"));
                item.setVenFechaInicio(rs.getDate("VEN_FECHA_INICIO"));
                item.setFechaFinal(rs.getDate("FEC_CLASES_FIN"));
                item.setSeccion(rs.getString("SECCION"));
                item.setFrecuencia(rs.getInt("FRECUENCIA"));
                item.setDesFrecuencia(rs.getString("DESFRECUENCIA"));
                item.setDesTurno(rs.getString("DESTURNO"));
                item.setVencimiento(rs.getInt("VENCIMIENTO"));
                item.setVenVencimiento(rs.getInt("VEN_VENCIMIENTO"));
                item.setPreMatricula(rs.getInt("PRE_MATRICULA"));
                item.setInforme(rs.getInt("INFORME"));
                item.setNivelModular(rs.getInt("NIVEL_MODULAR"));
                item.setSede(rs.getString("SEDE"));
                item.setVacantes(rs.getInt("VACANTES"));
                item.setMatriculados(rs.getInt("MATRICULADOS"));
                item.setInterezados(rs.getInt("INTEREZADOS"));
                item.setDesProducto(rs.getString("DESPRODUCTO"));
                item.setDesFrecuenciaDia(rs.getString("DESFRECUENCIADIA"));
                item.setDesFrecuenciaHora(rs.getString("DESFRECUENCIAHORA"));
                item.setDesProducto(rs.getString("DESPRODUCTO"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    public List<ventas.seccionCurso> mostrarSeccionCurso(int institucion, String periodo,String carrera,String seccion) {

        List<ventas.seccionCurso> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ventas.seccionCurso item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_CURSO_SECCION(?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ventas.seccionCurso();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPeriodo(rs.getString("PERIODO"));
                item.setMalla(rs.getString("MALLA"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setSeccion(rs.getString("SECCION"));
                item.setGrupo(rs.getString("GRUPO"));
                item.setCurso(rs.getString("CURSO"));
                item.setDescurso(rs.getString("DESCURSO"));  
                item.setMarcar(rs.getBoolean("MARCAR"));
                item.setPersona(rs.getString("PERSONA"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setDesPersonal(rs.getString("DESPERSONA"));
                

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    
    
    public List<ventas.descuentoSeccion> mostrarDescuentoSeccion(int institucion, String periodo,String carrera,String seccion) {

        List<ventas.descuentoSeccion> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ventas.descuentoSeccion item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_SECCION_CONCEPTO(?,?,?,?)}");
            cs.setInt(1, institucion);
            cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ventas.descuentoSeccion();
                item.setTipoConcepto(rs.getInt("TIPO_CONCEPTO"));
                item.setDesTipoConcepto(rs.getString("DESTIPOCONCEPTO"));
                item.setConcepto(rs.getString("CONCEPTO"));
                item.setDesConcepto(rs.getString("DESCONCEPTO"));
                item.setTipoMoneda(rs.getInt("TI_MONEDA"));
                item.setDesTipoMoneda(rs.getString("DESMONEDA"));                 
                item.setMontoPago(rs.getDouble("MON_PAGO"));
                item.setNumCuota(rs.getInt("NUM_CUOTAS"));
                

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    
    public List<ventas.productoCarrera> mostrarProductoCarrera(int institucion, String carrera) {

        List<ventas.productoCarrera> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ventas.productoCarrera item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_VEN_PRODUCTO_CARRERA(?,?)}");
            cs.setInt(1, institucion);         
            cs.setString(2, carrera);            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ventas.productoCarrera();
                item.setInstitucion(rs.getInt("INSTITUCION"));
                item.setPaqueda(rs.getString("PAQUETE"));
                item.setProducto(rs.getString("PRODUCTO"));
                item.setDesProducto(rs.getString("DESPRODUCTO"));
                item.setCarrera(rs.getString("CARRERA"));
                item.setTipoFrecuencia(rs.getInt("TIPO_FRECUENCIA"));
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));  
                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            System.out.println( e.getMessage());
        }
        return lista;
    }
    
    
    public List<ventas.personaProducto> mostrarPersonaProducto(int institucion,String periodo, String carrera,String seccion) {

        List<ventas.personaProducto> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ventas.personaProducto item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.VEN_MOSTRAR_PERSONA_ALUMNO(?,?,?,?)}");
            cs.setInt(1, institucion);
         cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ventas.personaProducto();
                item.setPersona(rs.getString("PERSONA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("TELEFONO_CELULAR"));
                item.setCorreo(rs.getString("EMAIL_PRINCIPAL"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            System.out.println( e.getMessage());
        }
        return lista;
    }
    public List<ventas.personaProducto> mostrarPersonaInterezado(int institucion,String periodo, String carrera,String seccion) {

        List<ventas.personaProducto> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        ventas.personaProducto item ;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("SELECT B.PERSONA,'' ALUMNO,b.APELLIDO_PATERNO,b.APELLIDO_MATERNO,b.NOMBRES,b.TELEFONO,b.TELEFONO_CELULAR,B.EMAIL_PRINCIPAL FROM  SIGU.VTS_PERSONA_INFORME_DET A,UPA.DAT_PERSONAS B WHERE B.PERSONA=a.PERSONA AND A.INSTITUCION=? AND A.PERIODO=? AND A.CARRERA=? AND  A.SECCION=?");
            cs.setInt(1, institucion);
         cs.setString(2, periodo);
            cs.setString(3, carrera);
            cs.setString(4, seccion);
            
            rs = cs.executeQuery();

            while (rs.next()) {
                item = new ventas.personaProducto();
                item.setPersona(rs.getString("PERSONA"));
                item.setAlumno(rs.getString("ALUMNO"));
                item.setPaterno(rs.getString("APELLIDO_PATERNO"));
                item.setMaterno(rs.getString("APELLIDO_MATERNO"));
                item.setNombres(rs.getString("NOMBRES"));
                item.setTelefono(rs.getString("TELEFONO"));
                item.setCelular(rs.getString("TELEFONO_CELULAR"));
                item.setCorreo(rs.getString("EMAIL_PRINCIPAL"));

                lista.add(item);

            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
          
            System.out.println( e.getMessage());
        }
        return lista;
    }
}
