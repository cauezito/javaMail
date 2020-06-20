package br.com.cauezito.java_mail.java_mail;

import org.junit.Test;

import br.com.cauezito.java_mail.SendEmail;

public class AppTest {
	
	@Test
	public void testEmail() {
		try {
			StringBuilder bodyEmail = new StringBuilder();
			bodyEmail.append("<h2>Oi!</h2> <br/><br/>");
			bodyEmail.append("Você está recebendo um <b>e-mail de teste.</b>");
			SendEmail sendEmail = new SendEmail("nokylevs@gmail.com", "Cauê", "Teste!", bodyEmail.toString());
			sendEmail.send();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


