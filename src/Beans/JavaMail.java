
package Beans;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class JavaMail {

    private final Properties propiedades = new Properties();
    private Session sesion;

    private void init() {
        propiedades.put("mail.smtp.host", "smtp.live.com"); // hotmail "smtp.live.com" -- gmail "smtp.gmail.com" ofice368  "smtp.office365.com"
        propiedades.put("mail.smtp.starttls.enable", "true"); //
        propiedades.put("mail.smtp.port", 25); // // hotmail "25" -- gmail "587" ofice365 "587"
        propiedades.put("mail.smtp.mail.sender", "maicol_24@hotmail.com");//correo de upig gmail o yahoo
        propiedades.put("mail.smtp.password", "swit24");//clave de correo
        propiedades.put("mail.smtp.user", "maicol_24@hotmail.com");//correo denuevo
        propiedades.put("mail.smtp.auth", "true");
        sesion = Session.getDefaultInstance(propiedades);

    }

    public void enviarCorreo(String destino, String asunto, String nombrePersona, String clavePersona) {
        init();                                     //String mensajeCorreo
        try {

            MimeMessage mensaje = new MimeMessage(sesion);
            //de
            mensaje.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.mail.sender")));
            //para
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            //asunto
            mensaje.setSubject(asunto);
            //mensaje String html
            //mensaje.setContent(mensajeCorreo,"text/html");

            //mensaje multipart html
            Multipart multipart = new MimeMultipart("related");

            BodyPart bodyhtml = new MimeBodyPart();
            StringBuilder x = new StringBuilder();
            /*
             x.append("<html>");
             x.append("<body>");
             x.append("<div style='width: 100%;margin: 0 auto;height: 200px;position: relative;'>");
             x.append(" <label style='font-size: 1.2em'>").append(nombrePersona).append("</label><br/><br/>");
             x.append("<body>");
             x.append("<body>");
             */
            bodyhtml.setContent(""
                    + "<html>"
                    + "<body>"
                    + "<div style='width: 100%;margin: 0 auto;height: 400px;position: relative;'>"
                    + "<img src='http://www.logrosperu.com/images/logos/universidades/upig.jpg' alt='UPIG.' style='margin-left: -20px;border:1px solid '><br/>"
                    + "<label style='font-size: 1.2em'>" + nombrePersona + "</label>"
                    + "<br/>"
                    + "<label style='font-size: 1.05em'>"
                    + "Su clave es : " + clavePersona + ""
                    + "</div>"
                    + "</body>"
                    + "</html>", "text/html");
            multipart.addBodyPart(bodyhtml);

            //*****************************************************************************
            //envia el mensaje
            mensaje.setContent(multipart);

            //enviar mensaje
            Transport t = sesion.getTransport("smtp");
            t.connect((String) propiedades.get("mail.smtp.user"), (String) propiedades.get("mail.smtp.password"));
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
             System.out.println("correctamente");
        } catch (MessagingException e) {
              System.out.println(e.getMessage());
            return;
        }
    }

}
