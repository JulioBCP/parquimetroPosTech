package br.com.fiap.parquimetro.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.config.FreeMarkerConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class JavaMailService {

	
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("naoresponda@teste.com.br");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }

    public void sendMessageUsingFreemarkerTemplate(String to, String subject, Map<String, Object> map)
            throws IOException, TemplateException, MessagingException {
            

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(FreeMarkerConfig.class, "/templates/email/");
        Template freemarkerTemplate = cfg.getTemplate("ReciboTemplate.ftl");

        StringWriter writer = new StringWriter();
        freemarkerTemplate.process(map, writer);
        String emailContent = writer.toString();

        sendHtmlMessage(to, subject, emailContent);
}

public void sendmailTemplateTeste() throws Exception{
		String to = "thiagorv169572@gmail.com";
		String subject = "Recibo parquimetro";

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dia", "22/01/2024");
		map.put("chegada", "22:41");
		map.put("saida", "23:42");
        map.put("valor", "100.00");
		
		sendMessageUsingFreemarkerTemplate(to,subject,map);
	}
}
