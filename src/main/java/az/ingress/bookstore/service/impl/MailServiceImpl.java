package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.constant.MailConstants;
import az.ingress.bookstore.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final MailConstants mailConstant;

    @Override
    public void sendNewBookNotification(String[] to, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(mailConstant.getEmail());
        javaMailSender.send(simpleMailMessage);
    }
}
