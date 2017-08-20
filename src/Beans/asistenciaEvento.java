
package Beans;

import DAO.eventoDAO;
import DAO.eventoExpositorDAO;
import DAO.personaDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.eventoC;
import ENTIDAD.eventoExpositorC;
import ENTIDAD.notasC;
import ENTIDAD.personaC;
import ENTIDAD.tipoExamenC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "asistenciaEventoB")
@ViewScoped
public class asistenciaEvento  {
	
	
	

    private String numero;
    private List<detalle> detalleL;
    private personaC persona;
    private List<ColumnModel> columnasL = new ArrayList<ColumnModel>();
    private List<notasC> seleccionL = new ArrayList<notasC>();
    private List<notasC> notasL = new ArrayList<notasC>();
    private List<eventoC> eventoL = new ArrayList<eventoC>();
    private List<eventoExpositorC> eventoExpositorL = new ArrayList<eventoExpositorC>();
    private String eventoS;
    private String tipoEvento;

    public asistenciaEvento() {
        columnasL.add(new ColumnModel("PERSONA", "alumno", false));
        columnasL.add(new ColumnModel("DATOS", "nombres", false));
        tipoExamenD = new tipoExamenDAO();
        for (tipoExamenC item : tipoExamenD.mostrarTipoExamenFormula(17)) {
            columnasL.add(new ColumnModel(item.getTipo_examen(), item.getTipo_examen().toLowerCase(), true));
        }
        columnasL.add(new ColumnModel("PROMEDIO", "promedio", false));

        personaD = new personaDAO();
//        notasL = personaD.getDocente_notas(1, 20141, "00", "17");
    }

    personaDAO personaD;
    tipoExamenDAO tipoExamenD;
    eventoDAO eventoD;
    eventoExpositorDAO eventoExpositorD;

   

    public void resgistrarAsistencia() {

        personaD = new personaDAO();
        personaD.insertarAsistenciaEvento(eventoS, numero);
        numero = "";
    }

    public List<eventoExpositorC> eventoExpositor() {
        eventoExpositorD = new eventoExpositorDAO();
        eventoExpositorL = eventoExpositorD.mostrarEventosExpositor("00000003");
        return eventoExpositorL;

    }

    public void eliminarAsistencia(String persona) {
        personaD = new personaDAO();
        personaD.eliminaAsistenciaEvento(eventoS, persona);

    }

    public void onCellEdit(CellEditEvent event) {
        Object newValue = event.getNewValue();
        notasC lista;
        lista = ((notasC) notasL.get(event.getRowIndex()));
        personaD = new personaDAO();
        personaD.insertarNotaEvento(1, 20141, "00", lista.getAlumno().toString(), event.getColumn().getHeaderText(), newValue.toString());
    }

    public void onRowSelect(SelectEvent event) {

    }

    public void onRowSelectBusqueda(SelectEvent event) {

        personaC item = (personaC) event.getObject();
        personaD = new personaDAO();
        personaD.insertarAsistenciaEvento(eventoS, item.getNumero_documento());
        numero = "";

    }

    public void onRowUnselect(UnselectEvent event) {

    }

    public void imprimir() {
        util.consolaCliente(""+ seleccionL.size());

    }

    public String parramArray() {
        String cadena = "";

        for (int i = 0; i <= seleccionL.size() - 1; i++) {
            cadena += seleccionL.get(i).getAlumno() + "-";
        }

        return cadena.substring(0, cadena.length() - 1);
    }

    /**
     * @return the detalleL
     */
    public List<detalle> getDetalleL() {
        personaD = new personaDAO();
        detalleL = personaD.mostrarAsistenciaEvento(eventoS);
        return detalleL;
    }

    /**
     * @param detalleL the detalleL to set
     */
    public void setDetalleL(List<detalle> detalleL) {
        this.detalleL = detalleL;
    }

    /**
     * @return the columnasL
     */
    public List<ColumnModel> getColumnasL() {

        return columnasL;
    }

    /**
     * @param columnasL the columnasL to set
     */
    public void setColumnasL(List<ColumnModel> columnasL) {
        this.columnasL = columnasL;
    }

    /**
     * @return the notasL
     */
    public List<notasC> getNotasL() {
        return notasL;
    }

    /**
     * @param notasL the notasL to set
     */
    public void setNotasL(List<notasC> notasL) {
        this.notasL = notasL;
    }

    /**
     * @return the seleccionL
     */
    public List<notasC> getSeleccionL() {
        return seleccionL;
    }

    /**
     * @param seleccionL the seleccionL to set
     */
    public void setSeleccionL(List<notasC> seleccionL) {
        this.seleccionL = seleccionL;
    }

    /**
     * @return the persona
     */
    public personaC getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(personaC persona) {
        this.persona = persona;
    }

    /**
     * @return the eventoL
     */
    public void mostrarEvento(String tipo) {
        eventoD = new eventoDAO();
        eventoL = eventoD.mostrarEventos(tipo);
    }

    public List<eventoC> getEventoL() {

        return eventoL;
    }

    /**
     * @param eventoL the eventoL to set
     */
    public void setEventoL(List<eventoC> eventoL) {
        this.eventoL = eventoL;
    }

    /**
     * @return the eventoS
     */
    public String getEventoS() {
        return eventoS;
    }

    /**
     * @param eventoS the eventoS to set
     */
    public void setEventoS(String eventoS) {
        this.eventoS = eventoS;
    }

    /**
     * @return the eventoExpositorL
     */
    public List<eventoExpositorC> getEventoExpositorL() {
        return eventoExpositorL;
    }

    /**
     * @param eventoExpositorL the eventoExpositorL to set
     */
    public void setEventoExpositorL(List<eventoExpositorC> eventoExpositorL) {
        this.eventoExpositorL = eventoExpositorL;
    }

    /**
     * @return the tipoEvento
     */
    public String getTipoEvento() {
        return tipoEvento;
    }

    /**
     * @param tipoEvento the tipoEvento to set
     */
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public static class ColumnModel {

        private String header;
        private String property;
        private boolean editable;

        public ColumnModel(String header, String property, boolean editable) {
            this.header = header;
            this.property = property;
            this.editable = editable;
        }

        /**
         * @return the header
         */
        public String getHeader() {
            return header;
        }

        /**
         * @param header the header to set
         */
        public void setHeader(String header) {
            this.header = header;
        }

        /**
         * @return the property
         */
        public String getProperty() {
            return property;
        }

        /**
         * @param property the property to set
         */
        public void setProperty(String property) {
            this.property = property;
        }

        /**
         * @return the editable
         */
        public boolean isEditable() {
            return editable;
        }

        /**
         * @param editable the editable to set
         */
        public void setEditable(boolean editable) {
            this.editable = editable;
        }
    }

    public static class detalle {

        private String persona;
        private String nombres;
        private String documento;
        private String numero;
        private String hora_ingreso;

        public detalle() {
        }

        public detalle(String persona, String nombres, String documento, String numero, String hora_ingreso) {
            this.persona = persona;
            this.nombres = nombres;
            this.documento = documento;
            this.numero = numero;
            this.hora_ingreso = hora_ingreso;
        }

        /**
         * @return the persona
         */
        public String getPersona() {
            return persona;
        }

        /**
         * @param persona the persona to set
         */
        public void setPersona(String persona) {
            this.persona = persona;
        }

        /**
         * @return the nombres
         */
        public String getNombres() {
            return nombres;
        }

        /**
         * @param nombres the nombres to set
         */
        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        /**
         * @return the documento
         */
        public String getDocumento() {
            return documento;
        }

        /**
         * @param documento the documento to set
         */
        public void setDocumento(String documento) {
            this.documento = documento;
        }

        /**
         * @return the numero
         */
        public String getNumero() {
            return numero;
        }

        /**
         * @param numero the numero to set
         */
        public void setNumero(String numero) {
            this.numero = numero;
        }

        /**
         * @return the hora_ingreso
         */
        public String getHora_ingreso() {
            return hora_ingreso;
        }

        /**
         * @param hora_ingreso the hora_ingreso to set
         */
        public void setHora_ingreso(String hora_ingreso) {
            this.hora_ingreso = hora_ingreso;
        }

    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
