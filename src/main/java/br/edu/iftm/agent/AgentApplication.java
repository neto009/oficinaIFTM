package br.edu.iftm.agent;

import br.edu.iftm.agent.config.ProductMemory;
import br.edu.iftm.agent.dto.Product;
import br.edu.iftm.agent.dto.ProductEmbedding;
import br.edu.iftm.agent.service.EmbeddingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class AgentApplication implements CommandLineRunner {

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private EmbeddingService embeddingService;
	@Autowired
	private ProductMemory productMemory;

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
						productMemory.addEmbedding(new ProductEmbedding(products.get(i), vectors.get(i)));
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		System.out.println("✅ Produtos carregados e embeddados (float[]) na memória.");
	}

}
