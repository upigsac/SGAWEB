
package ENTIDAD;
import java.io.Serializable;


public class formulaC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int formula;
    private String descripcion;
    private String abreviatura;
    private String des_formula;
    private int estadoRegistro;

    /**
     * @return the formula
     */
    public int getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(int formula) {
        this.formula = formula;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return the des_formula
     */
    public String getDes_formula() {
        return des_formula;
    }

    /**
     * @param des_formula the des_formula to set
     */
    public void setDes_formula(String des_formula) {
        this.des_formula = des_formula;
    }

    /**
     * @return the estadoRegistro
     */
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * @param estadoRegistro the estadoRegistro to set
     */
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
