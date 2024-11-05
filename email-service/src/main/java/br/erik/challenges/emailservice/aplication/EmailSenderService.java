package br.erik.challenges.emailservice.aplication;

import br.erik.challenges.emailservice.core.EmailSenderUseCase;
import br.erik.challenges.emailservice.adapters.EmailSenderGateway;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    public EmailSenderService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.sendEmail(to, subject, body);
    }
}
