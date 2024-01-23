package br.com.fiap.parquimetro.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parquimetro.service.JavaMailService;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    JavaMailService emailService;
    String to = "thiago.rvargas@sp.senac.br";
    String subject = "Email Sender";
    String text = "Email sended!";
    Object object;

    @GetMapping()  
    public String teste(){
        return "It Works!";
    }

    @GetMapping("/send")
    public String envioEmail(){
       
        emailService.sendSimpleMessage(to,subject,text);
        return "Email enviado!";
    }

    @GetMapping("/send-template")
    public String envioEmailTemplate(){
  
        Map<String,Object> template = new HashMap<>();
        template.put("teste",object);
        try{
        emailService.sendMessageUsingFreemarkerTemplate(to,subject,template);
        }catch(Exception e){
            e.printStackTrace();
            return "Erro";
        }
        return "Email enviado!";
    }

    @GetMapping("/send-mail-template-teste")
    public String envioEmailTemplateTeste(){
  
        try{
        emailService.sendmailTemplateTeste();
        }catch(Exception e){
            e.printStackTrace();
            return "Erro";
        }
        return "Email enviado!";
    }
}

