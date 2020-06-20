package br.com.cauezito.java_mail;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	private String user = "";
	private String password = "";
	private String recipients = "";
	private String sender = "";
	private String subject = "";
	private String textBody = "";
	
	
	public SendEmail(String recipients, String sender,String subject, String textBody) {
		this.user = user;
		this.password = password;
		this.recipients = recipients;
		this.sender = sender;
		this.subject = subject;
		this.textBody = textBody;
	}

	public void send() {
		try {
			/*configs SMTP*/
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
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

			Address[] toUser = InternetAddress.parse(recipients); //add e-mails 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user, sender));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(subject);
			message.setContent(textBody, "text/html");
			Transport.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
