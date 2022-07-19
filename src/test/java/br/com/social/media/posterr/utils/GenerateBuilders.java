package br.com.social.media.posterr.utils;

import br.com.social.media.posterr.adapters.controller.request.PostContentRequest;

public class GenerateBuilders {

    public static PostContentRequest generatePostContentRequest(){
        return  PostContentRequest.builder()
                .userId("2891821921")
                .content("OLA ESTOU TENTANDO ENVIAR ESSA MENSAGEM AQUI")
                .build();
    }
}
