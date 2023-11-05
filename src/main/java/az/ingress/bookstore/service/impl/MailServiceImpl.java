package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.constant.MailConstant;
import az.ingress.bookstore.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final MailConstant mailConstant;

    @Override
    public void sendNewBookNotification(String[] to, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(mailConstant.getEmail());
        javaMailSender.send(simpleMailMessage);
    }
}
