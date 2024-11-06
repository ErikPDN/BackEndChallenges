package br.erik.challenges.emailservice.core;

public record EmailRequest(String to,
                           String subject,
                           String body) {
}
