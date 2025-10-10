package br.edu.iftm.agent;

import br.edu.iftm.agent.config.EmbeddingMemory;
import br.edu.iftm.agent.dto.DocumentEmbedding;
import br.edu.iftm.agent.dto.Product;
import br.edu.iftm.agent.dto.Document;
import br.edu.iftm.agent.dto.ProductEmbedding;
import br.edu.iftm.agent.service.EmbeddingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class AgentApplication implements CommandLineRunner {

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private EmbeddingService embeddingService;
	@Autowired
	private EmbeddingMemory embeddingMemory;

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Path resourceDir = Paths.get("src/main/resources/products");
		try (Stream<Path> paths = Files.walk(resourceDir)) {
			paths.filter(Files::isRegularFile).forEach(path -> {
				try {
					// Lê produtos do JSON
					List<Product> products = Arrays.asList(
							mapper.readValue(path.toFile(), Product[].class)
					);

					// Cria lista de descrições
					List<String> descriptions = products.stream()
							.map(Product::getDescription)
							.toList();

					// Faz embedding em batch
					List<float[]> vectors = embeddingService.embed(descriptions);

					// Associa cada embedding ao produto correspondente
					for (int i = 0; i < products.size(); i++) {
						embeddingMemory.addEmbedding(new ProductEmbedding(products.get(i), vectors.get(i)));
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		log.info("Produtos carregados e embeddados na memória.");

		// Carregamento dos documentos FAQ
		Path documentResourceDir = Paths.get("src/main/resources/faq");
		try (Stream<Path> paths = Files.walk(documentResourceDir)) {
			paths.filter(Files::isRegularFile)
			.filter(path -> path.toString().endsWith(".txt"))
			.forEach(path -> {
				try {
					String content = Files.readString(path, StandardCharsets.UTF_8);
					Document document = new Document(content);

					List<float[]> vectors = embeddingService.embed(List.of(content));
					embeddingMemory.addEmbedding(new DocumentEmbedding(document, vectors.get(0)));

				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		log.info("Documentos carregados e embeddados na memória.");
	}
}
