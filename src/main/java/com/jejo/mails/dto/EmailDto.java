package com.jejo.mails.dto;

public record EmailDto(
        String to,
        String subject,
        String tittle,
        String message,
        String contact,
        String page,
        String email
) {
}
