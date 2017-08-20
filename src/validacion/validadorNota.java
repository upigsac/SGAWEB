package validacion;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;

@FacesValidator(value="notaValida")
public class validadorNota implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
          
           int numeroMaximo=Integer.parseInt(component.getAttributes().get("numeroMaximo").toString()) ; //always 0
          
        String valor = StringUtils.defaultString((String)value);
        if (!valor.endsWith("NSP")){
            int nota=Double.valueOf(valor).intValue();
            if(nota>numeroMaximo ){
                 
                throw new ValidatorException(new FacesMessage("El componente " + component.getId() + " no contiene un NIF válido")); 
            }
            
        }
           
           
//           System.out.println(value.toString());
//        double valor=(double)value ;
//        int nota=Double.valueOf(valor).intValue();
//        if(nota>numeroMaximo ){                
//               throw new ValidatorException(new FacesMessage("El componente " + component.getId() + " no contiene un NIF válido")); 
//           }
    }
    
}
