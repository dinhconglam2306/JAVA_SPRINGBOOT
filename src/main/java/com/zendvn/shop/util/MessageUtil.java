package com.zendvn.shop.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class MessageUtil {
    private final MessageSource messageSource;

    public MessageUtil(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public void addMessage(RedirectAttributes redirectAttributes, String messageKey, String type , Locale locale){
        String message = messageSource.getMessage(messageKey,null, locale);

        redirectAttributes.addFlashAttribute(type + "Message", message);
    }
}
