package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {
	
	private String userName = "j.rafaeltol1@gmail.com";
	private String senha= "ra190020";
	
	private String listaDestinatarios = "";
	private String remetente = "";
	private String assunto = "";
	private String mensagem = "";
	
	public ObjetoEnviaEmail(String listaDestinatario , String remetente , String assunto, String mensagem) {
		this.remetente = remetente;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.listaDestinatarios = listaDestinatario;
	}
	
	public void enviarEmail(boolean envioHtml) throws Exception {
	
	Properties properties = new Properties();
	properties.put("mail.smtp.ssl.trust", "*");/*Autenticacão SSL*/
	properties.put("mail.smtp.auth", "true");/*Autorização*/
	properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
	properties.put("mail.smtp.host", "smtp.gmail.com"); /*Sercidor gmail Google*/
	properties.put("mail.smtp.port", "465");/*Porta do servidor*/
	properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao SMTP*/
	
	Session session = Session.getInstance(properties, new Authenticator() {
		
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, senha);
		}
	});
	
	
	Address[] toUser = InternetAddress.parse(listaDestinatarios);
	
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(userName, remetente)); /*Quem está enviano*/
	message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
	message.setSubject(assunto);/*Assunto do e-mail*/
	
	if (envioHtml) {
		message.setContent(mensagem, "text/html; charset=utf-8");
	}else {
	message.setText(mensagem);
	}
	
	Transport.send(message);
	}
	
	
	/*Esse método simula o PDF ou qualquer arquivo que possa ser enviado por anexo no email
	 * Voe pode pegar o arquivo no banco de dados ou stream, ou em uma pasta
	 * Retorna um PDF em branco com o texto do paragrafo*/
	
	private FileInputStream simuladorDePDF() throws Exception {
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF a ser anexado "));
		document.close();
		return new FileInputStream(file);
		
	}
	}



