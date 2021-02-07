package com.example.demo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtil implements IMessageUtil {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String key) {

        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessage(String key, Object... args) {

        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
