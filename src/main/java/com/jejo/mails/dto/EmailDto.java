package com.jejo.mails.dto;

public record EmailDto(
        String to,
        String subject,
        String message
) {
}
