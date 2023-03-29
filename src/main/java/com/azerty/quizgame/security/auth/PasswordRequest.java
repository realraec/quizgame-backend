package com.azerty.quizgame.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PasswordRequest {

    private Long id;
    private String oldPassword;
    private String newPassword;

}