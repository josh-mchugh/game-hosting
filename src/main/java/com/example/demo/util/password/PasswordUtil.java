package com.example.demo.util.password;

import com.example.demo.util.password.model.ValidatePasswordRequest;
import com.example.demo.util.password.model.ValidatePasswordResponse;
import org.apache.commons.lang3.StringUtils;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;

import java.util.Arrays;

public class PasswordUtil {

    public static ValidatePasswordResponse validatePassword(ValidatePasswordRequest request) {

        if(StringUtils.isBlank(request.getPassword()) && StringUtils.isBlank(request.getConfirmPassword())) {

            return ValidatePasswordResponse.builder()
                    .valid(false)
                    .errorMessageKey("error.password.invalid")
                    .errorDefaultMessage("Password is invalid")
                    .build();
        }

        if (!StringUtils.equals(request.getPassword(), request.getConfirmPassword())) {

            return ValidatePasswordResponse.builder()
                    .valid(false)
                    .errorMessageKey("error.password.mismatch")
                    .errorDefaultMessage("Passwords must match")
                    .build();
        }

        if (!PasswordUtil.isValid(request.getPassword()) || !PasswordUtil.isValid(request.getConfirmPassword())) {

            return ValidatePasswordResponse.builder()
                    .valid(false)
                    .errorMessageKey("error.password.invalid")
                    .errorDefaultMessage("Password is invalid")
                    .build();
        }

        return ValidatePasswordResponse.builder()
                .valid(true)
                .build();
    }

    private static boolean isValid(String password) {

        return new PasswordValidator(Arrays.asList(
                new LengthRule(8, Integer.MAX_VALUE),
                new CharacterRule(EnglishCharacterData.Digit),
                new CharacterRule(EnglishCharacterData.Special),
                new CharacterRule(EnglishCharacterData.UpperCase)
        )).validate(new PasswordData(password))
        .isValid();
    }
}
