
package com.test.ticket;

import Beans.util;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.util.List;
import java.awt.print.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="printTicketB")
public class printTicket  implements Printable {

    //1 inch to mm = 25.4
    static final double INCH = 72;//dpi    
    static final double CM_INCH = 0.393700787;
    static final double INCH_MM = 25.4;

    static final double PAGE_WIDTH = 22.7;//INCH_MM
    static final double PAGE_HEIGHT = 14.5;//INCH_MM

    //parece que las coordenadas ya contaban con los margenes
    private final static double MARGIN_LEFT_RIGHT = 1.0;//* INCH_MM
    private final static double MARGIN_TOP_BOTTOM = 0.5;//* INCH_MM
    //static final double MARGIN_HEADER = 0;//podria obviarlo--2.0
    String TIPO_DOCUMENTO;
    String NUM_COMPROBANTE;

  

    public void imprimir(String tipoDocumento,String numeroDocumento) {

       
        try {
            /*
             GraphicsEnvironment ee = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Font[] fonts = ee.getAllFonts(); // Get the fonts
    for (Font f : fonts) {
      util.consolaCliente(f.getFontName());
    }
            */
//            PrintService[] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
//            for (PrintService printer : impresoras) {
//           // util.consolaCliente(printer.getName());
//            }
            
            
            
            TIPO_DOCUMENTO = tipoDocumento;
            NUM_COMPROBANTE = numeroDocumento;
            
            util.consolaCliente(tipoDocumento);
            util.consolaCliente(numeroDocumento);
            if (TIPO_DOCUMENTO != null && NUM_COMPROBANTE != null) {

                PrinterJob impresion = PrinterJob.getPrinterJob();
                Paper papel = new Paper();
                PageFormat pf = impresion.defaultPage();
           
                papel.setImageableArea(deCMaDPI(MARGIN_LEFT_RIGHT), deCMaDPI(MARGIN_TOP_BOTTOM), papel.getWidth(), papel.getHeight());
             
                pf.setPaper(papel);

                impresion.setPrintable(this, pf);

                try {
                    impresion.print();
                } catch (PrinterException e) {
                   util.consolaCliente("printer exception " + e.getMessage());
                }

            } else {
                util.consolaCliente("una o mas variables null | vacias");
            }

        } catch (NullPointerException npe) {
            util.consolaCliente(npe.getMessage() + " UNO O MAS CAMPOS NULL");
        }

    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        try {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());//punto para evitar estar fuera del area de impresion

            List<camposReporte> campos = cargarCampos();//campos de la BD
            List<camposReporte> cabecera = llenarCampos(campos, 1);//DATOS DEL COMPROBANTE - 1 CABECERA
            List<camposReporte> detalle = llenarCampos(campos, 2);//DATOS DEL COMPROBANTE - 2 DETALLE

            int MARGEN_DETALLE = 0;
            int CAMPO = 1;

            int CAMPOS_DETALLE = 0;
            for (camposReporte z : campos) {
                
                if (z.getTipo_item() == 2) {
                  
                    CAMPOS_DETALLE++;
                }
            }
            int DETALLE_TAMANIO = detalle.size();
            int ITEM = DETALLE_TAMANIO / CAMPOS_DETALLE;
            double MAXIMO_ITEMS_DETALLE = !TIPO_DOCUMENTO.equals("6") ? 6.0 : 3.0;//todos los tipo de documento excepto FACTURA (6) PUEDEN MOSTRAR HASTA 5-6 ITEM (TOMARE 6) . para factura SOLO 3

            double MAXIMO_HOJAS = ITEM / MAXIMO_ITEMS_DETALLE;
            int PAGINAS = Math.round((int) Math.ceil(MAXIMO_HOJAS));

            
            if (page >= PAGINAS) {
                System.out.println("PAGINAS " + page);
              
                return NO_SUCH_PAGE;
            }

            for (camposReporte c : cabecera) {
                Font fuente = new Font("Time new roman", Font.BOLD, c.tamanio_fuente);
                g2d.setFont(fuente);
                double ejeX = deCMaDPI(c.getEje_x());
                double ejeY = deCMaDPI(c.getEje_y());
                g2d.drawString(c.getNombre_columna(), Math.round((long) ejeX), Math.round((long) (ejeY)));
            }

            int SALTO = 0;
            int ITEM_DETALLE = (int) (MAXIMO_ITEMS_DETALLE * CAMPOS_DETALLE * page);
            int MAXIMO_PAGINA = (int) (MAXIMO_ITEMS_DETALLE * CAMPOS_DETALLE * (page += 1));

            for (camposReporte c : detalle) {
                if (SALTO == ITEM_DETALLE) {
                    if (ITEM_DETALLE < MAXIMO_PAGINA) {
                    
                        Font fuente = new Font("Time new roman", Font.BOLD, c.tamanio_fuente);
                        g2d.setFont(fuente);
                        double ejeX = deCMaDPI(c.getEje_x());
                        double ejeY = deCMaDPI(c.getEje_y());
                        g2d.drawString(c.getNombre_columna(), Math.round((long) ejeX), Math.round((long) (ejeY + MARGEN_DETALLE)));

                        //COMO RECORRE CAMPOS LO QUE HAGO ES "SEPARAR" FILAS
                        //CANTIDAD DE CAMPOS DE DETALLE PARTIDO EN LA CANTIDAD DE CAMPOS DE UN ITEM
                        if (CAMPO < CAMPOS_DETALLE) {
                            CAMPO++;
                        } else {
                            // ULTIMO CAMPO SE DEBE ROMPER LA FILA Y EMPEZAR DENUEVO ... ADEMAS AGREGAR UN MARGEN ENTRE ITEMS
                            CAMPO = 1;
                            MARGEN_DETALLE += (deCMaDPI(c.getIncremento_det()));
                        }
                    }
                    ITEM_DETALLE++;
                }
                SALTO++;
            }

        } catch (ArithmeticException e) {
            util.consolaCliente("no es posible la division entre 0 " + e.getMessage());
            return NO_SUCH_PAGE;//**********************PARA NO CREAR UN LOOP
        }

        return PAGE_EXISTS;
    }

    private List<camposReporte> cargarCampos() {
        List<camposReporte> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        camposReporte cr;

        try {
            c = ConexionWeb();
           
            cs = c.prepareCall("{CALL SIGU.CON_IMPRESIONX (2,'%','%',?,?,'%','%','%','%')}");
            cs.setString(1, TIPO_DOCUMENTO);
            cs.setString(2, NUM_COMPROBANTE);

            rs = cs.executeQuery();

            while (rs.next()) {
                cr = new camposReporte();
                cr.setValor_moneda(rs.getInt("VALOR_MONETARIO"));
                cr.setNombre_columna(rs.getString("NOMBRE_COLUMNA"));
                cr.setEje_x(rs.getDouble("COORDENADA_X"));
                cr.setEje_y(rs.getDouble("COORDENADA_Y"));
                cr.setTipo_item(rs.getInt("TIPO_ITEM"));
                cr.setTamanio_fuente(rs.getInt("FUENTE_TAMANIO"));
                cr.setIncremento_det(rs.getDouble("INCREMENTO_DET"));
                lista.add(cr);
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente("GENERAR CAMPOS " + e.getMessage());
            //e.printStackTrace();
        }
        return lista;
    }

    private List<camposReporte> llenarCampos(List<camposReporte> campos, int tipo_item) {
        //System.out.println(tipo_item == 2 ? "fue llamado por printer" : null);
        List<camposReporte> lista = new ArrayList<>();
        Connection c;
        CallableStatement cs;
        ResultSet rs;
        camposReporte cr;
        DecimalFormat df = new DecimalFormat("0.00");

        try {
            c = ConexionWeb();
            //System.out.println("TIPO_DOCUMENTO " + TIPO_DOCUMENTO + " NUM_COMPROBANTE " + NUM_COMPROBANTE);
            cs = c.prepareCall("{CALL SIGU.CON_IMPRESIONX (3,'%','%',?,?,'%','%','%','%')}");
            cs.setString(1, TIPO_DOCUMENTO);
            cs.setString(2, NUM_COMPROBANTE);

            rs = cs.executeQuery();

            if (tipo_item == 1) {
                if (rs.next()) {//solo quiero una fila para formar la cabecera                    
                    for (camposReporte s : campos) {
                        cr = new camposReporte();
                        if (s.getTipo_item() == tipo_item) {
                            //System.out.println(tipo_item + " " + s.getNombre_columna());
                            cr.setValor_moneda(s.getValor_moneda());

                            if (cr.getValor_moneda() == 1) {//formatear *NUMERO* a 2 decimales
                                cr.setNombre_columna(df.format(Double.parseDouble(rs.getString(s.getNombre_columna()))));
                            } else {
                                cr.setNombre_columna(rs.getString(s.getNombre_columna()));
                            }

                            cr.setEje_x(s.getEje_x());
                            cr.setEje_y(s.getEje_y());
                            cr.setTamanio_fuente(s.getTamanio_fuente());
                            //cr.setIncremento_det(s.getIncremento_det());
                            //cr.setTipo_item(s.getTipo_item());
                            lista.add(cr);
                        }
                    }
                }
            } else {
                int i = 1;//para separar detalles
                while (rs.next()) {//tomo todas las filas para formar el detalle                    
                    for (camposReporte s : campos) {
                        cr = new camposReporte();
                        if (s.getTipo_item() == tipo_item) {
                            //System.out.println(tipo_item + " " + s.getNombre_columna());
                            cr.setValor_moneda(s.getValor_moneda());

                            if (cr.getValor_moneda() == 1) {//formatear *NUMERO* a 2 decimales
                                cr.setNombre_columna(df.format(Double.parseDouble(rs.getString(s.getNombre_columna()))));
                            } else {
                                cr.setNombre_columna(rs.getString(s.getNombre_columna()));
                            }

                            cr.setEje_x(s.getEje_x());
                            cr.setEje_y(s.getEje_y());
                            cr.setTamanio_fuente(s.getTamanio_fuente());
                            cr.setTipo_item(i);//para separar detalles
                            cr.setIncremento_det(s.getIncremento_det());
                            lista.add(cr);
                        }
                    }
                    i++;
                }
            }
            cerrarCall(cs);
            cerrarConexion(c);

        } catch (SQLException e) {
            util.consolaCliente("LLENAR CAMPOS " + e.getMessage());
            //e.printStackTrace();
        }
        return lista;
    }

    private static double deCMaDPI(double cm) {
        return aDPI(cm * CM_INCH);
    }

    private static double aDPI(double in) {
        return in * INCH;
    }

    public static Connection ConexionWeb() {
        Connection c = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //c = DriverManager.getConnection("jdbc:sqlserver://172.16.1.60;databaseName=BDUP14022015", "sa", "123456");
            c = DriverManager.getConnection("jdbc:sqlserver://192.168.1.249;databaseName=BDUP", "sa", "Sql2015");
        } catch (SQLException | ClassNotFoundException | NullPointerException e) {
            System.out.println("conexion web " + e.getMessage());
        }
        return c;
    }

    public static synchronized void cerrarConexion(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            System.out.println("result set close " + e.getMessage());
        }
    }

    public static synchronized void cerrarConexion(Connection cn) {
        try {
            cn.close();
        } catch (SQLException e) {
            System.out.println("conn close " + e.getMessage());
        }
    }

    public static synchronized void cerrarCall(CallableStatement cl) {
        try {
            cl.close();
        } catch (SQLException e) {
            System.out.println("call close " + e.getMessage());
        }
    }

    public static class camposReporte {

        private String nombre_columna;
        private double eje_x;
        private double eje_y;
        private int tipo_item;
        private int tamanio_fuente;
        private int valor_moneda;
        private double incremento_det;

        /**
         * @return the nombre_columna
         */
        public String getNombre_columna() {
            return nombre_columna;
        }

        /**
         * @param nombre_columna the nombre_columna to set
         */
        public void setNombre_columna(String nombre_columna) {
            this.nombre_columna = nombre_columna;
        }

        /**
         * @return the eje_x
         */
        public double getEje_x() {
            return eje_x;
        }

        /**
         * @param eje_x the eje_x to set
         */
        public void setEje_x(double eje_x) {
            this.eje_x = eje_x;
        }

        /**
         * @return the eje_y
         */
        public double getEje_y() {
            return eje_y;
        }

        /**
         * @param eje_y the eje_y to set
         */
        public void setEje_y(double eje_y) {
            this.eje_y = eje_y;
        }

        /**
         * @return the tipo_item
         */
        public int getTipo_item() {
            return tipo_item;
        }

        /**
         * @param tipo_item the tipo_item to set
         */
        public void setTipo_item(int tipo_item) {
            this.tipo_item = tipo_item;
        }

        /**
         * @return the tamanio_fuente
         */
        public int getTamanio_fuente() {
            return tamanio_fuente;
        }

        /**
         * @param tamanio_fuente the tamanio_fuente to set
         */
        public void setTamanio_fuente(int tamanio_fuente) {
            this.tamanio_fuente = tamanio_fuente;
        }

        /**
         * @return the valor_moneda
         */
        public int getValor_moneda() {
            return valor_moneda;
        }

        /**
         * @param valor_moneda the valor_moneda to set
         */
        public void setValor_moneda(int valor_moneda) {
            this.valor_moneda = valor_moneda;
        }

        /**
         * @return the incremento_det
         */
        public double getIncremento_det() {
            return incremento_det;
        }

        /**
         * @param incremento_det the incremento_det to set
         */
        public void setIncremento_det(double incremento_det) {
            this.incremento_det = incremento_det;
        }
    }

}
