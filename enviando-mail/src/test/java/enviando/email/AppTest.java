package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Unit test for simple App.
 */
public class AppTest { 
	
	@org.junit.Test
	public void testeEmail() throws Exception{
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá, <br/><br/>");
		stringBuilderTextoEmail.append("<h3>Você esta recebendo o acesso ao curso Jdev Treinamentos<h3/><br/><br/>");
		
		
		
		
		
				
		
		
		
		
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail( "j.rafaeltol1@gmail.com",
				                                            "Rafael Oliveira", 
				                                            "Olá! Você está recebendo um E-mail", 
				                                            stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmail(true);
	}

	

	
	}
		

