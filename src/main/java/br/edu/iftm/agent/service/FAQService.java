package br.edu.iftm.agent.service;

import br.edu.iftm.agent.config.EmbeddingMemory;
import br.edu.iftm.agent.dto.DocumentEmbedding;
import br.edu.iftm.agent.dto.ProductEmbedding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FAQService {

    private static final String SYSTEM_PROMPT = """
        Com base nas seguintes informações, responda de forma natural e humanizada à pergunta. Seja cordial, profissional e forneça uma resposta completa baseada apenas nas informações fornecidas.
        """;

    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    private final EmbeddingMemory embeddingMemory;

    public FAQService(ChatClient.Builder chatClient, EmbeddingModel embeddingModel, EmbeddingMemory embeddingMemory) {
        this.embeddingMemory = embeddingMemory;
        this.embeddingModel = embeddingModel;
        this.chatClient = chatClient.build();
    }

    public String ask(String question, float distance) {
        try {
            log.info("Processando pergunta: '{}' com threshold: {}", question, distance);

            float[] queryVector = embeddingModel.embed(question);
            List<DocumentEmbedding> matches = embeddingMemory.searchDocument(queryVector, distance);

            log.info("Encontrados {} documentos com similaridade >= {}", matches.size(), distance);

            if (matches.isEmpty()) {
                return "Não encontrei documentos correspondentes agora. Pode tentar reformular ou fornecer mais detalhes?";
            }

            String context = matches.stream()
                .map(de -> de.document().getContent())
                .collect(Collectors.joining("\n"));

            log.debug("Contexto gerado para LLM: {}", context);
            return this.generateAnswer(question, context);
        } catch (Exception e) {
            log.error("Erro ao processar pergunta: {}", question, e);
            return "Ocorreu um erro interno. Tente novamente em alguns instantes.";
        }
    }

    private String generateAnswer(String question, String context) {
        try {
            return chatClient.prompt()
                .messages(
                    new SystemMessage(SYSTEM_PROMPT),
                    new SystemMessage("CONTEXTO:\n" + context),
                    new UserMessage(question)
                )
                .call()
                .content();
        } catch (Exception e) {
            log.error("Erro na chamada para o modelo de chat", e);
            throw new RuntimeException("Falha na comunicação com o modelo de linguagem", e);
        }
    }
}
