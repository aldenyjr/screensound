package com.github.aldenyjr.screensound.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaOpenAi {
    public static String obterInformacao(String texto) {
        OpenAiService service = new OpenAiService(System.getenv("OPEN_APIKEY"));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-4o-mini")
                .prompt("me fale sobre o artista: " + texto + " \n Observação: responda apenas o texto com no maximo 200 caracters mais nada.")
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText().trim();
    }
}
