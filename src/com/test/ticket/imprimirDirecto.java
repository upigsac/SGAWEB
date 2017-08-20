
package com.test.ticket;

import Beans.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.faces.bean.ManagedBean;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.ResolutionSyntax;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterResolution;


@ManagedBean(name="imprimirDirectoB")
public class imprimirDirecto implements Printable{
    
    
    
    
    public void iniciar(){
           // Resolucion.
  HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
  PrinterResolution pr = new PrinterResolution(120, 144, ResolutionSyntax.DPI);
  set.add(PrintQuality.HIGH);
  set.add(pr);
                    
                    
    PrinterJob pj = PrinterJob.getPrinterJob();

    PageFormat pf = pj.defaultPage();
    Paper paper = new Paper();
    double margin = 10; // half inch
    util.consolaCliente("anchor = " + paper.getWidth());
    util.consolaCliente("alto = " + paper.getHeight());
    //paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
    paper.setImageableArea(margin, margin, 500, 500);
    pf.setPaper(paper);
   
//            if (pj.printDialog()){
//                try{ 
//                 
//                    /*
//                    PageFormat pf = printJob.defaultPage();
//                    Paper paper = new Paper();
//                    paper.setSize(200.0, 500.0);
//                    double margin = 10;
//                   // paper.setImageableArea(margin, margin, paper.getWidth() – margin, paper.getHeight() – margin);
//                    pf.setPaper(paper);
//                    
//                    // Se coloco en forma Vertical la pagina para la impresion.
//                    pf.setOrientation(PageFormat.PORTRAIT);
//                    */
//                    //pj.print(set);
//                    pj.setPrintable(this);
//                    pj.print(set);
//                }catch(PrinterException pe) {
//                     System.out.println("Error printing: " + pe);
//                }
//            }
                pj.setPrintable(this, pf);

                try {
                    pj.print(set);
                } catch (PrinterException e) {
                   util.consolaCliente("printer exception " + e.getMessage());
                }
  
    }
    
    
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex){
        
      if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    g2.setFont(new Font("Serif", Font.PLAIN, 12));   
    g2.drawString("1.1- ESTO ES UNA PRUEBA DE INTRANET", 50, 10);
    
    g2.setFont(new Font("Serif", Font.CENTER_BASELINE, 12));   
    g2.drawString("1.2- ESTO ES UNA PRUEBA DE INTRANET", 50, 30);
    
    
    g2.setFont(new Font("Serif", Font.HANGING_BASELINE, 12));   
    g2.drawString("1.3- ESTO ES UNA PRUEBA DE INTRANET", 50, 50);
    
    g2.setFont(new Font("Serif", Font.ROMAN_BASELINE, 12));   
    g2.drawString("1.4- ESTO ES UNA PRUEBA DE INTRANET", 50, 70);
    
   
    
    
    
    g2.setFont(new Font("arial", Font.PLAIN, 12));   
    g2.drawString("2 - ESTO ES UNA PRUEBA DE INTRANET", 50, 90);
    
    g2.setFont(new Font("Calibri", Font.BOLD, 12));   
    g2.drawString("3 - ESTO ES UNA PRUEBA DE INTRANET", 50, 120);
    
    
      g2.setFont(new Font("Times New Roman", Font.PLAIN, 12));   
    g2.drawString("4 - ESTO ES UNA PRUEBA DE INTRANET", 50, 150);
    
    
      g2.setFont(new Font("Verdana", Font.PLAIN, 12));   
    g2.drawString("5 - ESTO ES UNA PRUEBA DE INTRANET", 50, 170);
    
       g2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 12));   
    g2.drawString("6 - ESTO ES UNA PRUEBA DE INTRANET", 50, 190);
    
    /*
    Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf        .getImageableWidth(), pf.getImageableHeight());
    g2.draw(outline);Lucida Sans Typewriter
    */
    return PAGE_EXISTS;
  }
        
        
        
        
    
}
