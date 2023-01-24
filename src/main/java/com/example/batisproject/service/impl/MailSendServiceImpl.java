package com.example.batisproject.service.impl;

import com.example.batisproject.service.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@Log4j2
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {

    private final JavaMailSenderImpl javaMailSender ;
    private final TemplateEngine templateEngine;

    private int authNumber;

    @Override
    public void makeRandomNumber() {
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
//        log.info("checkNum : " + checkNum);
        authNumber = checkNum;
    }

    @Override
    public String joinEmail(String email) {
        makeRandomNumber();

//        Context context = new Context();
//
//        context.setVariable("nickname", "규스");
//        context.setVariable("message", "회원 가입을 진행하시려면 아래 링크를 클릭해 이메일 인증을 시도해주세요.");
//        context.setVariable("host", "http://localhost:8080/"); // 메시지소스로 설정해두고 받아쓰면 참 편하다.
//        context.setVariable("link", "email-authentication"); // 인증을 진행할 링크
//        context.setVariable("linkName", "여기를 클릭해주세요"); // 위 링크를 덧씌울 텍스트

//        String message = templateEngine.process("email.html", context);
//
//        String setForm = ".com";
        String toMail = email;
        String title = "회원가입을 위한 인증메일입니다.";
        String message =
                "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" +
                "인증번호는 " + authNumber + " 입니다." +
                "<br><br>" +
                "해당 인증번호를 인증번호 확인한에 기입하여 주시기바랍니다.";
        mailSend(message,toMail, title);
        return Integer.toString(authNumber);
    }

    @Override
    public void mailSend(String message,String toMail, String title) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(toMail);
            helper.setSubject(title);
            // true 입력시 html 양식으로 전달됨. 안하면 일단 텍스트 형식임.
            helper.setText(message,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
