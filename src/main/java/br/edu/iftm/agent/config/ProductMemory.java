package br.edu.iftm.agent.config;

import br.edu.iftm.agent.dto.ProductEmbedding;
import org.springframework.stereotype.Component;
import smile.math.MathEx;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductMemory {

    private final List<ProductEmbedding> embeddings = new ArrayList<>();

    public void addEmbedding(ProductEmbedding pe) {
        embeddings.add(pe);
    }

    public List<ProductEmbedding> search(float[] queryVector, float threshold) {
        return embeddings.stream()
            .map(pe -> new AbstractMap.SimpleEntry<>(pe, calculateCosine(queryVector, pe.getVector())))
            .filter(entry -> entry.getValue() >= threshold)
            .sorted((a, b) -> Float.compare(b.getValue(), a.getValue()))
            .map(Map.Entry::getKey)
            .toList();
    }

    private float calculateCosine(float[] v1, float[] v2) {
        return MathEx.dot(v1, v2);
    }
}