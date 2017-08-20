/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.cursoPeriodoDAO;
import ENTIDAD.cursoPeriodoC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "cursoPeriodoB")
@ViewScoped
public class cursoPeriodo {

    private cursoPeriodoC cursoPeriodo;
    private List<cursoPeriodoC> cursoPeriodoL;
    private boolean bandera;

    cursoPeriodoDAO cursoPeriodoD;

    public void mostrarBandera(boolean valor) {
        bandera = valor;
    }


    public void eliminar(cursoPeriodoC item) {        
        cursoPeriodoD = new cursoPeriodoDAO();
        cursoPeriodoD.eliminar(item);
        cursoPeriodoL=cursoPeriodoD.mostrarCursoPerido(item.getInstitucion(), item.getPeriodo(), item.getCarrera());
        
    }
    public List<cursoPeriodoC> mostrarCursoPeriodo(int institucion, String periodo, String carrera) {
        cursoPeriodoD = new cursoPeriodoDAO();
        cursoPeriodoL = cursoPeriodoD.mostrarCursoPerido(institucion, periodo, carrera);
        return cursoPeriodoL;
    }
    public cursoPeriodoC mostrarCursoPeriodo(int institucion, String periodo, String carrera,String curso) {
        cursoPeriodoD = new cursoPeriodoDAO();
        cursoPeriodo = cursoPeriodoD.mostrarCursoPerido(institucion, periodo, carrera,curso);
        return cursoPeriodo;
    }
    
    
    public void onRowEdit(RowEditEvent event) {
        
        insertar(((cursoPeriodoC) event.getObject()));
        
    }
    

    public void insertar(cursoPeriodoC item) {
        cursoPeriodoD = new cursoPeriodoDAO();
        cursoPeriodoD.insertar(item);
        
    }

    public cursoPeriodoC getCursoPeriodo() {
        return cursoPeriodo;
    }
    public void setCursoPeriodo(cursoPeriodoC cursoPeriodo) {
        this.cursoPeriodo = cursoPeriodo;
    }
    public List<cursoPeriodoC> getCursoPeriodoL() {
        return cursoPeriodoL;
    }
    public void setCursoPeriodoL(List<cursoPeriodoC> cursoPeriodoL) {
        this.cursoPeriodoL = cursoPeriodoL;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
}
