package br.com.cauezito.java_mail.java_mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class AppTest {
	
	private String user = "";
	private String password = "";
	
	@Test
	public void testEmail() {
		try {
			/*configs SMTP*/
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true"); /*Autorização*/
			properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
			properties.put("mail.smtp.host", "smtp.gmail.com"); /*Servidor gmail*/
			properties.put("mail.smtp.port", "465"); /*Porta do servidor*/
			properties.put("mail.smtp.socketFactory.port", "465"); /*Especifica a porta a ser conectada pelo socket*/
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /*Classe socket de conexão ao smtp*/
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			
			
			Address[] toUser = InternetAddress.parse(""); //add e-mails 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("E-mail de teste.");
			message.setContent("Deu tudo certo!", "text/html");
			Transport.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


