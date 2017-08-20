
package convertidores;

import Beans.tramTramitePersona.personaEmpresa;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;




 
@FacesConverter("convert")
public class convert implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                personaEmpresa service = (personaEmpresa) fc.getExternalContext().getApplicationMap().get("themeService");
                //return service.getThemes().get(Integer.parseInt(value));
                return service;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((personaEmpresa) object).getCodigo());
        }
        else {
            return null;
        }
    }   
}     