package br.erik.challenges.emailservice.core;

// descreve a regra de negocios
public interface EmailSenderUseCase {
    void sendEmail(String to, String subject, String body);  // apenas o que a aplicação faz e não como faz
}
