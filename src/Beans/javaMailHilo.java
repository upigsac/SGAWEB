package Beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

public class javaMailHilo extends Thread {
	
	private String destino;
	private String asunto;
	private String correo;
	
	public javaMailHilo( String destino,	 String asunto,	 String correo){
		this.destino=destino;
		this.asunto=asunto;
		this.correo=correo;
		
	}
	
	
	
	public void run(){
		Properties config = new Properties();
	    InputStream entrada = null;
		
	    String ruta=Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"Conexiones/config.properties";
	    try {
			entrada = new FileInputStream(ruta.replace("%20"," "));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    try {
			config.load(entrada);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	    Properties propiedades = new Properties();
	    Session sesion;
	    propiedades.put("mail.smtp.host",config.getProperty("smtp")); // hotmail "smtp.live.com" -- gmail "smtp.gmail.com" ofice368  "smtp.office365.com"
	    propiedades.put("mail.smtp.starttls.enable", "true"); //
	    propiedades.put("mail.smtp.port", config.getProperty("puerto")); // // hotmail "25" -- gmail "587" ofice365 "587"
	    propiedades.put("mail.smtp.mail.sender", config.getProperty("correo"));
	    propiedades.put("mail.smtp.password", config.getProperty("passCorreo"));
	    propiedades.put("mail.smtp.user", config.getProperty("correo"));
	    propiedades.put("mail.smtp.auth", "true");
	    sesion = Session.getDefaultInstance(propiedades);                                  //String mensajeCorreo
	    try {
	    	System.out.println("----1-------");
	        MimeMessage mensaje = new MimeMessage(sesion);
	        System.out.println("----2-------");
	                    mensaje.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.mail.sender")));
	        System.out.println("----3-------");
	        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	        System.out.println("----4-------");
	        mensaje.setSubject(asunto);
	        System.out.println("----5-------");
	        Multipart multipart = new MimeMultipart("related");
	        System.out.println("----6-------");
	        BodyPart bodyhtml = new MimeBodyPart();
	        System.out.println("----7-------");
	        bodyhtml.setContent(correo, "text/html");
	        System.out.println("----8-------");
	        multipart.addBodyPart(bodyhtml);
	        System.out.println("----9-------");
	        mensaje.setContent(multipart);
	        System.out.println("----10-------");
	        Transport t = sesion.getTransport("smtp");
	        System.out.println("----11-------");
	        
	        t.connect(config.getProperty("correo"), config.getProperty("passCorreo"));
	        
	        System.out.println("----12-------");            
	        t.sendMessage(mensaje, mensaje.getAllRecipients());
	        
	        System.out.println("----13-------");
	        t.close();
	         
	         
	         System.out.println("Se genero Correctamen");
	         
	    } catch (MessagingException e) {              
	    	System.out.println("Error");
	       
	    }
	}

	
	
	
	
	
	

}
