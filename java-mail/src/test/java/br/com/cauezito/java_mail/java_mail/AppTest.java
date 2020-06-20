package br.com.cauezito.java_mail.java_mail;

import org.junit.Test;

import br.com.cauezito.java_mail.SendEmail;

public class AppTest {
	
	
	@Test
	public void testEmail() {
		try {
			SendEmail sendEmail = new SendEmail("nokylevs@gmail.com", "Cauê", "Teste!", "Chegou?");
			sendEmail.send();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


