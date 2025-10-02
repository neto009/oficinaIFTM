package br.edu.iftm.agent.service;

import br.edu.iftm.agent.config.ProductMemory;
import br.edu.iftm.agent.dto.ProductEmbedding;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private static final String SYSTEM_PROMPT = """
        Você é um vendedor especialista em e-commerce brasileiro.
        Objetivo: recomendar produtos somente com base no conteúdo enviado e no contexto da conversa.
        Regras:

        1) Nunca invente produto, preço, característica ou disponibilidade que não esteja no contexto.
        2) Se o contexto estiver vazio ou sem itens relevantes, responda exatamente:
           "😕 Não encontrei produtos correspondentes agora. Pode tentar reformular ou fornecer mais detalhes?"
        3) Se houver 1–2 itens, aprofunde vantagens práticas.
        4) Se houver vários itens, agrupe por necessidade e recomende no máximo 5, cada um com justificativa curta (1 frase).
        5) Tom: direto, amigável, natural, máximo 3 emojis adequados.
        6) Não repita a pergunta do cliente.
        7) Peça esclarecimento só se realmente necessário.
        8) Inclua sempre preço e link do produto na resposta.
        9) Responda como um vendedor humano, nunca como uma IA.

        Formato da resposta (quando houver itens):
        - Abertura curta calorosa.
        - Lista de recomendações em bullets simples.
        Proibido: dados não presentes, números inventados, promessas irreais.
        """;

    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    private final ProductMemory productMemory;

    public ChatService(ChatClient.Builder chatClient, EmbeddingModel embeddingModel, ProductMemory productMemory) {
        this.productMemory = productMemory;
        this.embeddingModel = embeddingModel;
        this.chatClient = chatClient.build();
    }

    public String ask(String question, float distance) {
        float[] queryVector = embeddingModel.embed(question);
        List<ProductEmbedding> matches = productMemory.search(queryVector, distance);

        if (matches.isEmpty()) {
            return "Desculpe, não encontrei nenhum produto com sua descrição.";
        }

        String context = matches.stream()
            .map(pe -> String.format("- %s (%s) | R$%.2f | Link: %s | %s",
                pe.getProduct().getName(),
                pe.getProduct().getCategory(),
                pe.getProduct().getPrice(),
                pe.getProduct().getLink(),
                pe.getProduct().getDescription()))
            .collect(Collectors.joining("\n"));
        return this.generateAnswer(question, context);
    }

    private String generateAnswer(String question, String context) {
        return chatClient.prompt()
            .messages(
                new SystemMessage(SYSTEM_PROMPT),
                new SystemMessage("CONTEXTO:\n" + context),
                new UserMessage(question)
            )
            .call()
            .content();
    }
}
