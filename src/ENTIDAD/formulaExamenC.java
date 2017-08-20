
package ENTIDAD;
import java.io.Serializable;

public class formulaExamenC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int formula;
    private String tipoExamen;
    private int orden;
    private int numeroDecimal;
    private int numeroMinimo;
    private int numeroMaximo;
    private String tipoExamenRe;
    private int ordenRe;
    private String dependencia;
    private String subFormula;
    private int reeplazaFormula;
    private String tipoExamenPadre;
    private boolean lectura;
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
     * @return the tipoExamen
     */
    public String getTipoExamen() {
        return tipoExamen;
    }

    /**
     * @param tipoExamen the tipoExamen to set
     */
    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the numeroDecimal
     */
    public int getNumeroDecimal() {
        return numeroDecimal;
    }

    /**
     * @param numeroDecimal the numeroDecimal to set
     */
    public void setNumeroDecimal(int numeroDecimal) {
        this.numeroDecimal = numeroDecimal;
    }

    /**
     * @return the numeroMinimo
     */
    public int getNumeroMinimo() {
        return numeroMinimo;
    }

    /**
     * @param numeroMinimo the numeroMinimo to set
     */
    public void setNumeroMinimo(int numeroMinimo) {
        this.numeroMinimo = numeroMinimo;
    }

    /**
     * @return the numeroMaximo
     */
    public int getNumeroMaximo() {
        return numeroMaximo;
    }

    /**
     * @param numeroMaximo the numeroMaximo to set
     */
    public void setNumeroMaximo(int numeroMaximo) {
        this.numeroMaximo = numeroMaximo;
    }

    /**
     * @return the tipoExamenRe
     */
    public String getTipoExamenRe() {
        return tipoExamenRe;
    }

    /**
     * @param tipoExamenRe the tipoExamenRe to set
     */
    public void setTipoExamenRe(String tipoExamenRe) {
        this.tipoExamenRe = tipoExamenRe;
    }

    /**
     * @return the ordenRe
     */
    public int getOrdenRe() {
        return ordenRe;
    }

    /**
     * @param ordenRe the ordenRe to set
     */
    public void setOrdenRe(int ordenRe) {
        this.ordenRe = ordenRe;
    }

    /**
     * @return the dependencia
     */
    public String getDependencia() {
        return dependencia;
    }

    /**
     * @param dependencia the dependencia to set
     */
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    /**
     * @return the subFormula
     */
    public String getSubFormula() {
        return subFormula;
    }

    /**
     * @param subFormula the subFormula to set
     */
    public void setSubFormula(String subFormula) {
        this.subFormula = subFormula;
    }

    /**
     * @return the reeplazaFormula
     */
    public int getReeplazaFormula() {
        return reeplazaFormula;
    }

    /**
     * @param reeplazaFormula the reeplazaFormula to set
     */
    public void setReeplazaFormula(int reeplazaFormula) {
        this.reeplazaFormula = reeplazaFormula;
    }

    /**
     * @return the tipoExamenPadre
     */
    public String getTipoExamenPadre() {
        return tipoExamenPadre;
    }

    /**
     * @param tipoExamenPadre the tipoExamenPadre to set
     */
    public void setTipoExamenPadre(String tipoExamenPadre) {
        this.tipoExamenPadre = tipoExamenPadre;
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

    /**
     * @return the lectura
     */
    public boolean isLectura() {
        return lectura;
    }

    /**
     * @param lectura the lectura to set
     */
    public void setLectura(boolean lectura) {
        this.lectura = lectura;
    }
}
