package br.edu.iftm.agent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public List<float[]> embed(List<String> text) {
        EmbeddingRequest req = new EmbeddingRequest(text, null);
        EmbeddingResponse resp = embeddingModel.call(req);

        return resp.getResults().stream()
            .filter(Objects::nonNull)
            .map(d -> d.getOutput())
            .toList();
    }
}
