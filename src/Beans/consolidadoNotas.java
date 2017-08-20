

package Beans;

import DAO.consolidadoNotasDAO;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="consolidadoNotasB")
@ViewScoped
public class consolidadoNotas {
    private List<detalleNotas> detalleNotasL;
    private String cursoS;
    
    consolidadoNotasDAO consolidadoNotasD;
    public List<detalleNotas> mostrarConsolidadoNotas(int institucion,String periodo,String alumno,String curso,String tipoExamenPadre){
    
        cursoS=curso;
        consolidadoNotasD =new consolidadoNotasDAO();
        detalleNotasL=consolidadoNotasD.mostrarConsolidadoNotas(institucion, periodo, alumno, curso, tipoExamenPadre);
        return detalleNotasL;
    }
    public List<detalleNotas> getDetalleNotasL() {
        return detalleNotasL;
    }
    public void setDetalleNotasL(List<detalleNotas> detalleNotasL) {
        this.detalleNotasL = detalleNotasL;
    }
    public String getCursoS() {
        return cursoS;
    }
    public void setCursoS(String cursoS) {
        this.cursoS = cursoS;
    }
    
    public static class detalleNotas{
        private int formula;
        private String tipoExamen;
        private String descripcion;
        private String linea1;
        private String linea2;
        private String subFormula;
        private int nota;
        private String tipoExamenPadre;
        private String fechaInicio;
        private String fechaFin;

        
        public int getFormula() {
            return formula;
        }
        public void setFormula(int formula) {
            this.formula = formula;
        }
        public String getTipoExamen() {
            return tipoExamen;
        }
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
        }
        public String getDescripcion() {
            return descripcion;
        }
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        public String getLinea1() {
            return linea1;
        }
        public void setLinea1(String linea1) {
            this.linea1 = linea1;
        }
        public String getLinea2() {
            return linea2;
        }
        public void setLinea2(String linea2) {
            this.linea2 = linea2;
        }
        public String getSubFormula() {
            return subFormula;
        }
        public void setSubFormula(String subFormula) {
            this.subFormula = subFormula;
        }
        public String getTipoExamenPadre() {
            return tipoExamenPadre;
        }
        public void setTipoExamenPadre(String tipoExamenPadre) {
            this.tipoExamenPadre = tipoExamenPadre;
        }
        public String getFechaInicio() {
            return fechaInicio;
        }
        public void setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
        }
        public String getFechaFin() {
            return fechaFin;
        }
        public void setFechaFin(String fechaFin) {
            this.fechaFin = fechaFin;
        }
        public int getNota() {
            return nota;
        }
        public void setNota(int nota) {
            this.nota = nota;
        }
    }
}
