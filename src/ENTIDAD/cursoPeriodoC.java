
package ENTIDAD;

import java.io.Serializable;

public class cursoPeriodoC implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;

    private int item;
    private int formula;
    private int estadoRegistro;

    public cursoPeriodoC(int institucion, String periodo, String carrera, String malla, String curso, int item, int formula, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.item = item;
        this.formula = formula;
        this.estadoRegistro = estadoRegistro;
    }

    public cursoPeriodoC() {
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getMalla() {
        return malla;
    }
    public void setMalla(String malla) {
        this.malla = malla;
    }
    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public int getFormula() {
        return formula;
    }
    public void setFormula(int formula) {
        this.formula = formula;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
}
