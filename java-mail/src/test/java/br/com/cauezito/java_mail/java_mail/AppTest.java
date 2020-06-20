package br.com.cauezito.java_mail.java_mail;

import java.util.Properties;

import org.junit.Test;

public class AppTest {
	
	@Test
	public void testEmail() {
		/*configs SMTP*/
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); /*Autorização*/
		properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
		properties.put("mail.smtp.host", "smtp.gmail.com"); /*Servidor gmail*/
		properties.put("mail.smtp.port", "465"); /*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465"); /*Especifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /*Classe socket de conexão ao smtp*/
		
	}
}


