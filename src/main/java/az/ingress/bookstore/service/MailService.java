package az.ingress.bookstore.service;

import java.util.List;

public interface MailService {
    void sendNewBookNotification(String[] to, String body);
}
