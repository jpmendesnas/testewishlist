package com.wishlist.app.api.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages {
    public static final Map<String, String> MESSAGES;

    static {
        MESSAGES = new HashMap<>();
        MESSAGES.put("notFoundError", "Item não encontrado com o  id: {0}");
        MESSAGES.put("badRequest", "Não foi possível processar a requisição. Verifique os dados enviados.");
        MESSAGES.put("limitItemsRequest", "A wishlist do cliente já atingiu o limite máximo de produtos.");
        MESSAGES.put("emptyList", "A wishlist do cliente está vazia.");
        // Adicione outras mensagens de erro aqui
    }
}
