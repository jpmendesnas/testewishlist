package com.wishlist.app.api.exception;

import java.text.MessageFormat;
import java.util.Arrays;

public class MessageUtils {

    public static String getMessage(String key, Object... params) {
        String message = ErrorMessages.MESSAGES.get(key);
        if (message != null) {
            Object[] stringParams = Arrays.stream(params)
                    .map(Object::toString)
                    .toArray();
            return MessageFormat.format(message, stringParams);        }
        else {
            return "Não foi possivel realizar a operação"; // Ou retorne uma mensagem de erro padrão
        }
    }
}
