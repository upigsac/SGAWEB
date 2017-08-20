package Beans;

import DAO.formulaDAO;
import ENTIDAD.formulaC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="formulaB")
@ViewScoped
public class formula{
    private formulaC formula;
    private List<formulaC> formulaL;
    formulaDAO formulaD;
    public formulaC mostrarFormula(int formula){
        formulaD=new formulaDAO();
        this.formula=formulaD.mostrarFormula(formula);
        return this.formula;
    }
    
      public List<formulaC> mostrarFormula(){
        formulaD=new formulaDAO();
        formulaL=formulaD.mostrarFormula();
        return formulaL;
    }
    public formulaC getFormula() {
        return formula;
    }
    public void setFormula(formulaC formula) {
        this.formula = formula;
    }
    public List<formulaC> getFormulaL() {
        return formulaL;
    }
    public void setFormulaL(List<formulaC> formulaL) {
        this.formulaL = formulaL;
    }
}
