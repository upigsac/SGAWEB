
package Beans;

import DAO.opcionAccesoDAO;
import DAO.usuarioDAO;
import ENTIDAD.opcionAccesoC;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.component.html.HtmlDataTable;


@ManagedBean(name = "opcionAccesoB")
public class opcionAcceso {

    @ManagedProperty(value = "#{opcionModuloB}")
    private opcionModulo modulo;

    @ManagedProperty(value = "#{personaB}")
    private persona perB;

    private static List<opcionAccesoC> menuPadre;
    private static List<opcionAccesoC> menuHijo;

    private static List<opcionAccesoC> todoPadre;
    private static List<opcionAccesoC> todoHijo;

    private static opcionAccesoC opcionAccesoMenuPadre;
    private static opcionAccesoC opcionAccesoMenuHijo;
    private static opcionAccesoC opcionAccesoTodoPadre;
    private static opcionAccesoC opcionAccesoTodoHijo;

    private HtmlDataTable datatableMenuPadre;
    private HtmlDataTable datatableMenuHijo;
    private HtmlDataTable datatableTodoPadre;
    private HtmlDataTable datatableTodoHijo;

    private String nombretitulo;

    usuarioDAO usuarioD = null;

    @PostConstruct
    public void init() {
       //    cargarMenuPadre();

        //cargarMenuPadre();
        //cargarMenuHijo();
        //    CargarTodosPadre();
        //   CargarTodosHijos();
    }

    /**
     * @return the opcionAccesoTodoHijo
     */
    public static opcionAccesoC getOpcionAccesoTodoHijo() {
        return opcionAccesoTodoHijo;
    }

    /**
     * @param aOpcionAccesoTodoHijo the opcionAccesoTodoHijo to set
     */
    public static void setOpcionAccesoTodoHijo(opcionAccesoC aOpcionAccesoTodoHijo) {
        opcionAccesoTodoHijo = aOpcionAccesoTodoHijo;
    }

    /**
     * @return the opcionAccesoTodoPadre
     */
    public static opcionAccesoC getOpcionAccesoTodoPadre() {
        return opcionAccesoTodoPadre;
    }

    /**
     * @param aOpcionAccesoTodoPadre the opcionAccesoTodoPadre to set
     */
    public static void setOpcionAccesoTodoPadre(opcionAccesoC aOpcionAccesoTodoPadre) {
        opcionAccesoTodoPadre = aOpcionAccesoTodoPadre;
    }

    /**
     * @return the opcionAccesoMenuHijo
     */
    public static opcionAccesoC getOpcionAccesoMenuHijo() {
        return opcionAccesoMenuHijo;
    }

    /**
     * @param aOpcionAccesoMenuHijo the opcionAccesoMenuHijo to set
     */
    public static void setOpcionAccesoMenuHijo(opcionAccesoC aOpcionAccesoMenuHijo) {
        opcionAccesoMenuHijo = aOpcionAccesoMenuHijo;
    }

    opcionAccesoDAO acceso = null;

    public void cargarMenuPadre() {

        //util.consolaCliente( "carga padre");
        acceso = new opcionAccesoDAO();
        usuarioD = new usuarioDAO();

        menuPadre = acceso.menuPadre(modulo.getPrograma().getSeleccion(), modulo.getSeleccion(), modulo.getPrograma().getTipo(), usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario());
        CargarTodosPadre();
        CargarTodosHijos();

    }

    public void cargarMenuHijo() {
        acceso = new opcionAccesoDAO();
        usuarioD = new usuarioDAO();

        menuHijo = acceso.menuHijo(modulo.getPrograma().getSeleccion(), modulo.getSeleccion(), modulo.getPrograma().getTipo(), Integer.parseInt(opcionAccesoMenuPadre.getOpcion()), usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario());

    }

    public void insertarPadre() {

        String msg = "";
        acceso = new opcionAccesoDAO();
        usuarioD = new usuarioDAO();

        opcionAccesoTodoPadre = (opcionAccesoC) datatableTodoPadre.getRowData();

        msg = acceso.insertarMenuPadre(usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario(),
                modulo.getPrograma().getSeleccion(), modulo.getSeleccion(), Integer.parseInt(opcionAccesoTodoPadre.getOpcion()), 0, 5, modulo.getPrograma().getTipo(), 1);

        if (msg.equals("true")) {
            //util.consolaCliente( "Se Ingreso Correctamente");
            cargarMenuPadre();

        } else {
            //util.consolaCliente( msg);
        }

    }

    public void insertarHijo() {

        try {
            String msg = "";
            acceso = new opcionAccesoDAO();
            usuarioD = new usuarioDAO();

            opcionAccesoTodoHijo = (opcionAccesoC) datatableTodoHijo.getRowData();

            msg = acceso.insertarMenuHijo(usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario(),
                    modulo.getPrograma().getSeleccion(), modulo.getSeleccion(), Integer.parseInt(opcionAccesoTodoHijo.getOpcion()), Integer.parseInt(opcionAccesoMenuPadre.getOpcion()), 5, modulo.getPrograma().getTipo(), 1);
            if (msg.equals("true")) {
                //util.consolaCliente( "Se Ingreso Correctamente");
                cargarMenuHijo();
                CargarTodosHijos();
            } else {
                //util.consolaCliente( msg);
            }
        } catch (NullPointerException e) {
          //util.consolaCliente( "seleccione de la primera tabla");

        }

    }

    public void CargarTodosPadre() {
        acceso = new opcionAccesoDAO();
        usuarioD = new usuarioDAO();

        todoPadre = acceso.todosPadres(modulo.getPrograma().getSeleccion(), modulo.getSeleccion(),
                modulo.getPrograma().getTipo(), usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario());

    }

    public void CargarTodosHijos() {
        acceso = new opcionAccesoDAO();
        usuarioD = new usuarioDAO();

        todoHijo = acceso.todosHijos(modulo.getPrograma().getSeleccion(), modulo.getSeleccion(),
                modulo.getPrograma().getTipo(), usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario());

    }

    public void seleccionMenuPadre() {
        opcionAccesoMenuPadre = (opcionAccesoC) datatableMenuPadre.getRowData();
        nombretitulo = opcionAccesoMenuPadre.getDescripcion();

              //util.consolaCliente( perB.getBusCodigo());
        //util.consolaCliente( usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario());
        //util.consolaCliente( opcionAccesoMenuPadre.getDescripcion());
        cargarMenuHijo();
        CargarTodosPadre();
        CargarTodosHijos();

    }

    public void seleccionMenuHijo() {

        opcionAccesoMenuHijo = (opcionAccesoC) datatableMenuHijo.getRowData();
                   //util.consolaCliente( perB.getBusCodigo());
        //util.consolaCliente( opcionAccesoMenuHijo.getDescripcion());

              //cargarMenuHijo();
    }

    public void quitarHijo() {
        //util.consolaCliente( perB.getBusCodigo());
        String msg = "";
        acceso = new opcionAccesoDAO();
        usuarioD = new usuarioDAO();

        opcionAccesoMenuHijo = (opcionAccesoC) datatableMenuHijo.getRowData();
               //    util.consolaCliente( opcionAccesoMenuPadre.getDescripcion());

        msg = acceso.eliminarMenuHijo(modulo.getPrograma().getSeleccion(), modulo.getSeleccion(), 1,
                usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario(),
                Integer.parseInt(opcionAccesoMenuPadre.getOpcion()), Integer.parseInt(opcionAccesoMenuHijo.getOpcion()));
        if (msg.equals("true")) {
            //util.consolaCliente( "Se Elimino Correctamente");
            cargarMenuHijo();
            CargarTodosHijos();
        } else {
            //util.consolaCliente( msg);
        }

    }

    public void quitarPadre() {
        String msg = "";
        acceso = new opcionAccesoDAO();
        usuarioD = new usuarioDAO();

        opcionAccesoMenuPadre = (opcionAccesoC) datatableMenuPadre.getRowData();

        msg = acceso.eliminarMenuPadre(modulo.getPrograma().getSeleccion(), modulo.getSeleccion(), 1,
                usuarioD.busquedaUsuarioCodigo(perB.getBusCodigo()).getUsuario(), Integer.parseInt(opcionAccesoMenuPadre.getOpcion()));
        if (msg.equals("true")) {
            //util.consolaCliente( "Se Elimino Correctamente");
            cargarMenuPadre();
            CargarTodosPadre();
        } else {
            //util.consolaCliente( msg);
        }

    }

    /**
     * @return the menuPadre
     */
    public List<opcionAccesoC> getMenuPadre() {
        return menuPadre;
    }
    public void setMenuPadre(List<opcionAccesoC> menuPadre) {
        this.menuPadre = menuPadre;
    }
    public List<opcionAccesoC> getMenuHijo() {
        return menuHijo;
    }
    public void setMenuHijo(List<opcionAccesoC> menuHijo) {
        this.menuHijo = menuHijo;
    }
    public List<opcionAccesoC> getTodoPadre() {
        return todoPadre;
    }
    public void setTodoPadre(List<opcionAccesoC> todoPadre) {
        this.todoPadre = todoPadre;
    }
    public List<opcionAccesoC> getTodoHijo() {
        return todoHijo;
    }
    public void setTodoHijo(List<opcionAccesoC> todoHijo) {
        this.todoHijo = todoHijo;
    }
    public opcionModulo getModulo() {
        return modulo;
    }
    public void setModulo(opcionModulo modulo) {
        this.modulo = modulo;
    }
    public HtmlDataTable getDatatableMenuPadre() {
        return datatableMenuPadre;
    }

    public void setDatatableMenuPadre(HtmlDataTable datatableMenuPadre) {
        this.datatableMenuPadre = datatableMenuPadre;
    }

    public opcionAccesoC getOpcionAccesoMenuPadre() {
        return opcionAccesoMenuPadre;
    }

    public void setOpcionAccesoMenuPadre(opcionAccesoC opcionAccesoMenuPadre) {
        this.opcionAccesoMenuPadre = opcionAccesoMenuPadre;
    }

    public HtmlDataTable getDatatableTodoPadre() {
        return datatableTodoPadre;
    }

    public void setDatatableTodoPadre(HtmlDataTable datatableTodoPadre) {
        this.datatableTodoPadre = datatableTodoPadre;
    }

    /**
     * @return the datatableTodoHijo
     */
    public HtmlDataTable getDatatableTodoHijo() {
        return datatableTodoHijo;
    }

    /**
     * @param datatableTodoHijo the datatableTodoHijo to set
     */
    public void setDatatableTodoHijo(HtmlDataTable datatableTodoHijo) {
        this.datatableTodoHijo = datatableTodoHijo;
    }

    /**
     * @return the datatableMenuHijo
     */
    public HtmlDataTable getDatatableMenuHijo() {
        return datatableMenuHijo;
    }

    /**
     * @param datatableMenuHijo the datatableMenuHijo to set
     */
    public void setDatatableMenuHijo(HtmlDataTable datatableMenuHijo) {
        this.datatableMenuHijo = datatableMenuHijo;
    }

    /**
     * @return the nombretitulo
     */
    public String getNombretitulo() {
        return nombretitulo;
    }

    /**
     * @param nombretitulo the nombretitulo to set
     */
    public void setNombretitulo(String nombretitulo) {
        this.nombretitulo = nombretitulo;
    }

    /**
     * @return the perB
     */
    public persona getPerB() {
        return perB;
    }

    /**
     * @param perB the perB to set
     */
    public void setPerB(persona perB) {
        this.perB = perB;
    }

}
