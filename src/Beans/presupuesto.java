
package Beans;

import DAO.presupuestoDAO;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "presupuestoB")
@ViewScoped
public class presupuesto {

    private List<ColumnModel> columnaDinamica;
    private List<ColumnModel> columnaNieto;
    private List<ColumnModel> columnaSuministro;
    //private List<ColumnModel> columnas;

    private List<ArrayList<String>> detalleL;
    private List<ArrayList<String>> detalleHijoL;
    private List<ArrayList<String>> detalleNietoL;
    private List<ArrayList<String>> subGrupoL;
    private List<ArrayList<String>> cabeceraL;
    private List<ArrayList<String>> grupoL;
    private List<ArrayList<String>> suministrosL;

    private String periodo;
    private String subTitulo;
    private String tituloColumna;

    private String num;
    private String grupo;
    private String padre;
    private String hijo;
    private String nieto;
    private String bisnieto;
    private String subGrupo;
    private String agregarsubGrupo;
    private boolean agregarsubNieto;
    private boolean colm_alumno;
    private boolean editable = true;
    private boolean agregarNieto = true;

    presupuestoDAO presupuestoD;

    @PostConstruct
    public void init() {
        periodo = "20142";
        grupo = "1";
        mostrarGrupos();
        //System.out.println("post construct mostrar presupuesto");
    }

    public void mostrarGrupos() {
        presupuestoD = new presupuestoDAO();
        detalleL = presupuestoD.mostrarPresupuesto(periodo, grupo);
        /*
         for (int i = 0; i < detalleL.size(); i++) {
         System.out.println("listado de detalleL " + detalleL.get(i).get(0));
         }
         */
        //System.out.println("en mostrar grupos " + periodo + " " + grupo);
    }

    public List<ColumnModel> columnasDinamicas() {
        columnaDinamica = new ArrayList<ColumnModel>();
        presupuestoD = new presupuestoDAO();
        cabeceraL = presupuestoD.cabecera(periodo, grupo);
        for (int x = 0; x < cabeceraL.size(); x++) {
            columnaDinamica.add(new ColumnModel(cabeceraL.get(x).get(0), "xx", false));
            //System.out.println("columnas en columnas dinamicas " + cabeceraL.get(x).get(0));
        }
        columnaDinamica.add(new ColumnModel("TOTAL", "xx", false));
        //System.out.println("en columnas dinamicas " + periodo + " " + grupo + " " + this.padre + " " + this.hijo);
        return columnaDinamica;

    }

    public List<ColumnModel> columnasDinamicasNieto() {
        columnaNieto = new ArrayList<ColumnModel>();
        presupuestoD = new presupuestoDAO();
        cabeceraL = presupuestoD.cabecera(periodo, grupo);
        for (int x = 0; x < cabeceraL.size(); x++) {
            columnaNieto.add(new ColumnModel(cabeceraL.get(x).get(0), cabeceraL.get(x).get(1), true));
            //System.out.println("columnas en columnas dinamicas nieto " + cabeceraL.get(x).get(0) + " " + cabeceraL.get(x).get(1));
        }
        columnaNieto.add(new ColumnModel("TOTAL", "xx", false));
        //System.out.println("en columnas dinamicas nieto " + periodo + " " + grupo + " " + this.padre + " " + this.hijo);
        return columnaNieto;
    }

    /*
     public List<ColumnModel> columnasGeneradas() {
     columnas = new ArrayList<ColumnModel>();
     presupuestoD = new presupuestoDAO();
     cabeceraL = presupuestoD.columnas(periodo, grupo, padre, hijo, nieto, "0");
     for (int x = 0; x < cabeceraL.size(); x++) {
     columnas.add(new ColumnModel(cabeceraL.get(x).get(0), cabeceraL.get(x).get(1), true));
     System.out.println("columnas en columnas generadas " + cabeceraL.get(x).get(0) + " " + cabeceraL.get(x).get(1));
     }
     System.out.println("en columnas generadas " + periodo + " " + grupo + " " + padre + " " + hijo + " " + nieto);
     return columnas;
     }
     */
    public List<ArrayList<String>> mostrarHijos(String grupo, String padre) {
        if (!padre.isEmpty()) {
            presupuestoD = new presupuestoDAO();
            detalleHijoL = presupuestoD.mostrarPresupuestoPadre(periodo, grupo, padre);

        }
        return detalleHijoL;
    }

    public List<ArrayList<String>> mostrarGrupoTotal(String periodo, String grupo) {
        presupuestoD = new presupuestoDAO();
        grupoL = presupuestoD.mostrarPresupuestoTotal(periodo, grupo);

        return grupoL;
    }

    public void registrar(String periodo, String grupo, String padre, String hijo, String nieto, String bisnieto, String mes, String valor/*, String tipo*/) {
        presupuestoD = new presupuestoDAO();
        presupuestoD.insertarSubGrupo(periodo, grupo, padre, hijo, nieto, bisnieto, mes, valor/*, tipo*/);
        System.out.println("periodo " + periodo + " grupo " + grupo + " " + " padre " + padre + " hijo " + hijo + " nieto " + nieto + " bisnieto " + bisnieto + " mes " + mes + " valor " + valor/* + " tipo " + tipo*/);
        System.out.println("en registrar");
    }

    public void editarSubgrupo(String periodo, String grupo, String padre, String hijo, String nieto, String posicion, String valor) {
        presupuestoD = new presupuestoDAO();
        presupuestoD.editarSubGrupo(periodo, grupo, padre, hijo, nieto, posicion, valor);
        System.out.println("periodo " + periodo + " grupo " + grupo + " padre " + padre + " hijo " + hijo + " nieto " + nieto + " posicion " + posicion + " valor " + valor);
        System.out.println("en editar subgrupo");
    }

    public void editarNieto(String periodo, String grupo, String padre, String hijo, String nieto, String bisnieto, String posicion, String valor) {
        presupuestoD = new presupuestoDAO();
        presupuestoD.editarNieto(periodo, grupo, padre, hijo, nieto, bisnieto, posicion, valor);
        System.out.println("periodo " + periodo + " grupo " + grupo + " padre " + padre + " hijo " + hijo + " nieto " + nieto + " bisnieto " + bisnieto + " posicion " + posicion + " valor " + valor);
        System.out.println("en editar nieto");
    }

    public void nuevoNieto(String grupo, String padre, String hijo, String nieto) {
        presupuestoD = new presupuestoDAO();
        presupuestoD.nuevoNieto(periodo, grupo, padre, hijo, nieto);
        presupuestoD = new presupuestoDAO();
        detalleNietoL = presupuestoD.mostrarPresupuestoSubGrupoNieto(periodo, grupo, padre, hijo, nieto);
        subTitulo = null;
        tituloColumna = null;
        subTitulo = subGrupoL.get(0).get(5).toString();
        //tituloColumna = subGrupoL.get(0).get(5).toString();
        System.out.println("en nuevo nieto");
    }

    public void nuevoHijo(String grupo, String padre, String hijo) {
        presupuestoD = new presupuestoDAO();
        presupuestoD.nuevoHijo(periodo, grupo, padre, hijo);
        presupuestoD = new presupuestoDAO();
        subGrupoL = presupuestoD.mostrarPresupuestoSubGrupo(periodo, grupo, padre, hijo);
        subTitulo = null;
        tituloColumna = null;
        subTitulo = subGrupoL.get(0).get(4).toString();
        tituloColumna = subGrupoL.get(0).get(5).toString();
        System.out.println("en nuevo hijo");
    }

    public void subHijo(String grupo, String padre, String hijo, String editable, String agregar) {
        columnasDinamicasNieto();
        this.padre = padre;
        this.hijo = hijo;
        this.nieto = "0";
        this.bisnieto = "0";
        presupuestoD = new presupuestoDAO();
        subGrupoL = presupuestoD.mostrarPresupuestoSubGrupo(periodo, grupo, padre, hijo);

        this.editable = (editable.endsWith("1") ? true : false);
        this.agregarNieto = (agregar.endsWith("1") ? true : false);
        subTitulo = null;
        tituloColumna = null;
        if (!subGrupoL.isEmpty()) {
            subTitulo = subGrupoL.get(0).get(4).toString();
            tituloColumna = subGrupoL.get(0).get(5).toString();
        }
        //System.out.println("en subhijo " + grupo + " " + padre + " " + hijo + " " + subGrupoL.size());
    }

    public void subNieto(String grupo, String padre, String hijo, String nieto, String agregar) {
        columnasDinamicasNieto();
        this.padre = padre;
        this.hijo = hijo;
        this.nieto = nieto;
        presupuestoD = new presupuestoDAO();
        detalleNietoL = presupuestoD.mostrarPresupuestoSubGrupoNieto(periodo, grupo, padre, hijo, nieto);
        this.subGrupo = nieto;
        agregarsubNieto = agregar.endsWith("1") ? true : false;

        subTitulo = null;
        tituloColumna = null;
        if (!detalleNietoL.isEmpty()) {
            subTitulo = detalleNietoL.get(0).get(5).toString();
            //tituloColumna = detalleNietoL.get(0).get(5).toString();
        }

    }

    public boolean ocultarColumnaAlumnos() {
        //System.out.println("en ocultar columna alumnos " + this.grupo + " " + this.padre + " " + this.hijo);
        if (this.padre != null && this.hijo != null) {
            //solo se mostrara la columna            
            if (this.grupo.equals("1") && this.padre.equals("1") && this.hijo.equals("0")) {
                return true;
            } else if (this.grupo.equals("1") && this.padre.equals("2") && this.hijo.equals("0")) {
                return true;
            } else if (this.grupo.equals("1") && this.padre.equals("3") && this.hijo.equals("0")) {
                return true;
            } else if (this.grupo.equals("2")) {
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean ocultarDescripcion() {
        //System.out.println("en ocultar columna descripcion " + this.grupo + " " + this.padre + " " + this.hijo);
        if (this.padre != null && this.hijo != null) {
            //no se mostrara la columna
            if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("1")) {
                return false;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("2")) {
                return false;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("3")) {
                return false;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("4")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean ocultarCargoFuncional() {
        //System.out.println("en ocultar columna cargo " + this.grupo + " " + this.padre + " " + this.hijo);
        if (this.padre != null && this.hijo != null) {
            //solo se mostrara la columna
            if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("1")) {
                return true;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("2")) {
                return true;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("3")) {
                return true;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("4")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean ocultarApeNom() {
        //System.out.println("en ocultar columna ape nom " + this.grupo + " " + this.padre + " " + this.hijo);
        if (this.padre != null && this.hijo != null) {
            //solo se mostrara la columna
            if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("1")) {
                return true;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("2")) {
                return true;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("3")) {
                return true;
            } else if (this.grupo.equals("2") && this.padre.equals("1") && this.hijo.equals("4")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //suministros
    public void listarSuministros(String grupo, String padre, String hijo, String nieto) {
        presupuestoD = new presupuestoDAO();
        suministrosL = presupuestoD.mostrarSuministros(periodo, "2", "2", "1", "1");
        columnasDinamicasSuministros();
    }

    public List<ColumnModel> columnasDinamicasSuministros() {
        columnaSuministro = new ArrayList<ColumnModel>();
        presupuestoD = new presupuestoDAO();
        cabeceraL = presupuestoD.cabecera(padre, "2");
        //SOLO LOS MESES
        for (int x = 0; x < cabeceraL.size(); x++) {
            columnaSuministro.add(new ColumnModel(cabeceraL.get(x).get(0), cabeceraL.get(x).get(1), true));
        }
        return columnaSuministro;
    }

    public void nuevoSuministro() {
        presupuestoD = new presupuestoDAO();
        presupuestoD.insertarSuministros(periodo, "2", "2", "1", "1");
    }

    public void actualizarSuministro(String bisnieto, String descripcion, String tipo_unid, String pu) {
        System.out.println(periodo + "2" + "2" + "1" + "1" + bisnieto + " " + descripcion + " " + tipo_unid + " " + pu);
        presupuestoD.actualizarSuministros(periodo, "2", "2", "1", "1", bisnieto, descripcion, tipo_unid, pu);
        listarSuministros(grupo, padre, hijo, nieto);
    }

    public void insertarSuministroMes(String bisnieto, String descripcion, String tipo_unid, String pu, String mes, String cantidad) {
        System.out.println(periodo + "2" + "2" + "1" + "1" + bisnieto + " " + mes + " " + cantidad);
        presupuestoD.ingresarSuministrosMes(periodo, "2", "2", "1", "1", bisnieto, descripcion, tipo_unid, pu, mes, cantidad);
        listarSuministros(grupo, padre, hijo, nieto);
    }

    /*
     public List<ColumnModel> getColumnas() {
     return columnas;
     }
    
     public void setColumnas(List<ColumnModel> columnas) {
     this.columnas = columnas;
     }
     */
    /**
     * @return the detalleL
     */
    public List<ArrayList<String>> getDetalleL() {
        //presupuestoD = new presupuestoDAO();
        //detalleL = presupuestoD.mostrarPresupuesto(periodo, grupo);
        return detalleL;
    }

    /**
     * @param detalleL the detalleL to set
     */
    public void setDetalleL(List<ArrayList<String>> detalleL) {
        this.detalleL = detalleL;
    }

    /**
     * @return the subTitulo
     */
    public String getSubTitulo() {
        return subTitulo;
    }

    /**
     * @param subTitulo the subTitulo to set
     */
    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }
    public List<ArrayList<String>> getSubGrupoL() {
        return subGrupoL;
    }
    public void setSubGrupoL(List<ArrayList<String>> subGrupoL) {
        this.subGrupoL = subGrupoL;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getPadre() {
        return padre;
    }
    public void setPadre(String padre) {
        this.padre = padre;
    }
    public boolean isColm_alumno() {
        return colm_alumno;
    }
    public void setColm_alumno(boolean colm_alumno) {
        this.colm_alumno = colm_alumno;
    }
    public List<ArrayList<String>> getDetalleHijoL() {
        return detalleHijoL;
    }
    public void setDetalleHijoL(List<ArrayList<String>> detalleHijoL) {
        this.detalleHijoL = detalleHijoL;
    }
    public List<ArrayList<String>> getDetalleNietoL() {
        return detalleNietoL;
    }
    public void setDetalleNietoL(List<ArrayList<String>> detalleNietoL) {
        this.detalleNietoL = detalleNietoL;
    }
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public String getHijo() {
        return hijo;
    }
    public void setHijo(String hijo) {
        this.hijo = hijo;
    }
    public String getSubGrupo() {
        return subGrupo;
    }
    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }
    public String getAgregarsubGrupo() {
        return agregarsubGrupo;
    }
    public void setAgregarsubGrupo(String agregarsubGrupo) {
        this.agregarsubGrupo = agregarsubGrupo;
    }
    public List<ArrayList<String>> getGrupoL() {
        return grupoL;
    }
    public void setGrupoL(List<ArrayList<String>> grupoL) {
        this.grupoL = grupoL;
    }
    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    public List<ArrayList<String>> getCabeceraL() {
        return cabeceraL;
    }
    public void setCabeceraL(List<ArrayList<String>> cabeceraL) {
        this.cabeceraL = cabeceraL;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public boolean isAgregarNieto() {
        return agregarNieto;
    }
    public void setAgregarNieto(boolean agregarNieto) {
        this.agregarNieto = agregarNieto;
    }
    public String getBisnieto() {
        return bisnieto;
    }
    public void setBisnieto(String bisnieto) {
        this.bisnieto = bisnieto;
    }
    public String getNieto() {
        return nieto;
    }
    public void setNieto(String nieto) {
        this.nieto = nieto;
    }
    public boolean isAgregarsubNieto() {
        return agregarsubNieto;
    }
    public void setAgregarsubNieto(boolean agregarsubNieto) {
        this.agregarsubNieto = agregarsubNieto;
    }
    public List<ColumnModel> getColumnaDinamica() {
        return columnaDinamica;
    }
    public void setColumnasDinamicas(List<ColumnModel> columnaDinamicas) {
        this.columnaDinamica = columnaDinamicas;
    }
    public List<ColumnModel> getColumnaNieto() {
        return columnaNieto;
    }
    public void setColumnasNieto(List<ColumnModel> columnasNieto) {
        this.columnaNieto = columnasNieto;
    }
    public String getTituloColumna() {
        return tituloColumna;
    }
    public void setTituloColumna(String tituloColumna) {
        this.tituloColumna = tituloColumna;
    }
    public List<ArrayList<String>> getSuministrosL() {
        return suministrosL;
    }
    public void setSuministrosL(List<ArrayList<String>> suministrosL) {
        this.suministrosL = suministrosL;
    }
    public List<ColumnModel> getColumnaSuministro() {
        return columnaSuministro;
    }
    public void setColumnaSuministro(List<ColumnModel> columnaSuministro) {
        this.columnaSuministro = columnaSuministro;
    }

    /**
     * ***************************************************************************************************
     */
    static public class ColumnModel  {

        private String cabecera;
        private String propiedad;
        private boolean editable;

        public ColumnModel(String cabecera, String propiedad, boolean editable) {
            this.cabecera = cabecera;
            this.propiedad = propiedad;
            this.editable = editable;

        }
        public String getCabecera() {
            return cabecera;
        }
        public void setCabecera(String cabecera) {
            this.cabecera = cabecera;
        }
        public String getPropiedad() {
            return propiedad;
        }
        public void setPropiedad(String propiedad) {
            this.propiedad = propiedad;
        }
        public boolean isEditable() {
            return editable;
        }
        public void setEditable(boolean editable) {
            this.editable = editable;
        }

    }
}
