package br.com.fiap.parquimetro.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parquimetro.dto.MailEstacionamentoDTO;
import br.com.fiap.parquimetro.service.JavaMailService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("mail")
@Hidden
public class MailController {
    @Autowired
    JavaMailService emailService;
    // String to = "thiago.rvargas@sp.senac.br";
    // String subject = "Email Sender";
    // String text = "Email sended!";
    // Object object;

    @GetMapping()  
    public String teste(){
        return "It Works!";
    }

    // @PostMapping("/send")
    // public String envioEmail(@RequestBody MailEstacionamentoDTO body){
       
    //     emailService.sendSimpleMessage(to,subject,text);
    //     return "Email enviado!";
    // }

    @PostMapping("/send-template")
    public String envioEmailTemplate(@RequestBody MailEstacionamentoDTO body){
  
        Map<String,Object> template = new HashMap<>();
        LocalDateTime diaAtual = LocalDateTime.now();
		template.put("dia", diaAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		template.put("chegada", body.horarioEntrada().format(DateTimeFormatter.ofPattern("HH:mm")));
		template.put("saida", body.horarioSaida().format(DateTimeFormatter.ofPattern("HH:mm")));
        template.put("valor", body.valorPagamento());

        try{
        emailService.sendMessageUsingFreemarkerTemplate(body.to(),body.subject(),template);
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

