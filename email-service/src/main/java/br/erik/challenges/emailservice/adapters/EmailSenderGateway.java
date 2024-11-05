package br.erik.challenges.emailservice.adapters;

// contrato da aplicação com o provedor de serviço
public interface EmailSenderGateway {
    void sendEmail(String to, String subject, String body);
}
