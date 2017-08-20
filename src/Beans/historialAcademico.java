

package Beans;

import DAO.historialAcademicoDAO;
import ENTIDAD.periodoC;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;



import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name="historialAcademicoB")
@ViewScoped
public class historialAcademico {
    
    private String periodoInicio;
    private String periodoFinal;
    private List<detalleHistorial> detalleHistorialL=new ArrayList<>();
    private List<historialPromedioP> historialPromedioL=new ArrayList<>();
     private LineChartModel animatedModel1;
     private int institucion;
     private String usuario;
    
    

    
    
  public void load(int institucion,String usuario,List<periodoC> periodoL){
	  this.institucion=institucion;
	  this.usuario=usuario;
	  periodoFinal=periodoL.get(0).getPeriodo();
	  periodoInicio=periodoL.get(periodoL.size()-1).getPeriodo();
	  mostrar();
   	 
	  
  }
    
    
    public historialAcademico() {
    	createAnimatedModels();
    
	}
    
    
    
    private LineChartModel initLinearModel() {
         LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.set("20152", 18.5);
      
        model.addSeries(series1);
       
        return model;
    }
    
    
    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("PROGRESO ..");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        animatedModel1.setShowPointLabels(true);
        animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("PERIODOS"));
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(20);
         
      
       
    }
    
    public void mostrar(){
 
        detalleHistorialL=new historialAcademicoDAO().mostrarHistorialAcademico(institucion, periodoInicio, periodoFinal, usuario);
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel(usuario);
  
    historialPromedioL=new historialAcademicoDAO().mostrarCuadroResumen(institucion, periodoInicio, periodoFinal, usuario);
   
      for (historialPromedioP item : historialPromedioL) {
        series1.set(item.desPeriodo, item.promedioPonderado);
        
      }
      model.addSeries(series1);
      model.setSeriesColors("0088CE");
    animatedModel1 = model;  
    animatedModel1.setAnimate(true);
    animatedModel1.setLegendPosition("se");
    animatedModel1.setShowPointLabels(true);
    animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("PERIODOS"));
    Axis yAxis = animatedModel1.getAxis(AxisType.Y);
    yAxis.setMin(0);
    yAxis.setMax(20);
        
    }
    
    
    
      
      
    public List<detalleHistorial> getDetalleHistorialL() {
        return detalleHistorialL;
    }
    public void setDetalleHistorialL(List<detalleHistorial> detalleHistorialL) {
        this.detalleHistorialL = detalleHistorialL;
    }
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }
    public List<historialPromedioP> getHistorialPromedioL() {
        return historialPromedioL;
    }
    public void setHistorialPromedioL(List<historialPromedioP> historialPromedioL) {
        this.historialPromedioL = historialPromedioL;
    }
    
    
    public static class historialPromedioP{
        private String periodo;
        private String desPeriodo;
        private double promedioPonderado;

       
        public String getPeriodo() {
            return periodo;
        }
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }
        public String getDesPeriodo() {
            return desPeriodo;
        }
        public void setDesPeriodo(String desPeriodo) {
            this.desPeriodo = desPeriodo;
        }
        public double getPromedioPonderado() {
            return promedioPonderado;
        }
        public void setPromedioPonderado(double promedioPonderado) {
            this.promedioPonderado = promedioPonderado;
        }
    }
    
    
    public static class detalleHistorial{
        private String periodo;
        private String desPeriodo;
        private int credito;
        private String desSeccion;
        private String desCurso;
        private int promedio;
        private double promedioSemestral;

       
        public String getPeriodo() {
            return periodo;
        }
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }
        public String getDesPeriodo() {
            return desPeriodo;
        }
        public void setDesPeriodo(String desPeriodo) {
            this.desPeriodo = desPeriodo;
        }
        public String getDesSeccion() {
            return desSeccion;
        }
        public void setDesSeccion(String desSeccion) {
            this.desSeccion = desSeccion;
        }
        public String getDesCurso() {
            return desCurso;
        }
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }
        public int getPromedio() {
            return promedio;
        }
        public void setPromedio(int promedio) {
            this.promedio = promedio;
        }
        public double getPromedioSemestral() {
            return promedioSemestral;
        }
        public void setPromedioSemestral(double promedioSemestral) {
            this.promedioSemestral = promedioSemestral;
        }
        public int getCredito() {
            return credito;
        }
        public void setCredito(int credito) {
            this.credito = credito;
        }
    }
    
    
    public String getPeriodoInicio() {
        return periodoInicio;
    }
    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }
    public String getPeriodoFinal() {
        return periodoFinal;
    }
    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

}
