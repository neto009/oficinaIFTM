# Prompts para Workshop - Assistente de E-commerce com IA

## 📋 Instruções

Este documento contém os prompts que você deve usar para pedir à IA que implemente cada parte do sistema. Copie e cole cada prompt na ordem apresentada.

---

## 🚀 Prompt 1: Adicionar Dependências Maven

```
Adicione as dependências necessárias no arquivo pom.xml para integração com Spring AI e OpenAI.
```

---

## ⚙️ Prompt 2: Configurar application.yml

```
Ignore o arquivo application.yml porque já está configurado com a chave da API do OpenAI.
```

---

## 🔧 Prompt 3: Implementar EmbeddingService

```
Complete a implementação do método embed() na classe EmbeddingService que:
1. Recebe uma lista de textos como parâmetro
2. Cria um EmbeddingRequest com os textos
3. Chama o embeddingModel para gerar os embeddings
4. Extrai e retorna uma lista de vetores float[] dos resultados

O método já tem a assinatura: public List<float[]> embed(List<String> text)
```

---

## 📚 Prompt 4: Criar ProductLoaderService

```
Crie uma classe ProductLoaderService no pacote br.edu.iftm.agent.service que:
1. Seja anotada com @Service, @Slf4j e @RequiredArgsConstructor
2. Injete EmbeddingService e EmbeddingMemory
3. Tenha um método anotado com @PostConstruct chamado loadProducts() que:
   - Use PathMatchingResourcePatternResolver para buscar todos os arquivos .json em "classpath:products/*.json"
   - Leia cada arquivo JSON e converta para List<Product> usando ObjectMapper
   - Crie uma representação textual de cada produto no formato: "Produto: {nome}. Categoria: {categoria}. Descrição: {descrição}. Preço: R$ {preço}"
   - Chame embeddingService.embed() para gerar embeddings de todos os textos
   - Crie ProductEmbedding para cada produto e adicione no embeddingMemory usando addEmbedding()
   - Adicione logs informativos em cada etapa
```

---

## 📄 Prompt 5: Criar FAQLoaderService

```
Crie uma classe FAQLoaderService no pacote br.edu.iftm.agent.service que:
1. Seja anotada com @Service, @Slf4j e @RequiredArgsConstructor
2. Injete EmbeddingService e EmbeddingMemory
3. Tenha um método anotado com @PostConstruct chamado loadFAQs() que:
   - Use PathMatchingResourcePatternResolver para buscar todos os arquivos .txt em "classpath:faq/*.txt"
   - Leia cada arquivo de texto usando StandardCharsets.UTF_8
   - Crie objetos Document com o conteúdo e nome do arquivo
   - Chame embeddingService.embed() para gerar embeddings dos conteúdos
   - Crie DocumentEmbedding para cada documento e adicione no embeddingMemory usando addEmbedding()
   - Adicione logs informativos em cada etapa
```

---

## 🔍 Prompt 6: Implementar Método ask() do ChatService

```
Complete a implementação do método ask() na classe ChatService que recebe uma pergunta (String question) e um threshold de similaridade (float distance). O método deve:
1. Gerar o embedding da pergunta usando embeddingModel.embed(question)
2. Buscar produtos similares usando embeddingMemory.search(queryVector, distance)
3. Se não encontrar produtos, retornar mensagem informando que não encontrou produtos correspondentes
4. Formatar os produtos encontrados em texto estruturado com nome, categoria, preço, link e descrição
5. Chamar o método privado generateAnswer() passando a pergunta e o contexto formatado
6. Tratar exceções e retornar mensagens de erro apropriadas
7. Adicionar logs em cada etapa
```

---

## 📋 Prompt 7: Implementar Método generateAnswer() do ChatService

```
Complete a implementação do método privado generateAnswer() na classe ChatService que recebe uma pergunta (String question) e contexto (String context). O método deve:
1. Usar o chatClient.prompt() para criar um prompt com três mensagens:
   - SystemMessage com o SYSTEM_PROMPT (constante já definida)
   - SystemMessage com o texto "CONTEXTO:\n" + context
   - UserMessage com a pergunta do usuário
2. Chamar .call().content() para obter a resposta
3. Retornar a resposta como String
4. Tratar exceções da chamada ao modelo
```

---

## 🔎 Prompt 8: Implementar Método ask() do FAQService

```
Complete a implementação do método ask() na classe FAQService que recebe uma pergunta (String question) e um threshold (float distance). O método deve:
1. Gerar o embedding da pergunta usando embeddingModel.embed(question)
2. Buscar documentos FAQ similares usando embeddingMemory.searchDocument(queryVector, distance)
3. Se não encontrar documentos, retornar mensagem informando que não encontrou informações correspondentes
4. Concatenar o conteúdo dos documentos encontrados separados por quebras de linha duplas
5. Chamar o método privado generateAnswer() passando a pergunta e o contexto
6. Tratar exceções e retornar mensagens de erro apropriadas
7. Adicionar logs em cada etapa
```

---

## 📝 Prompt 9: Implementar Método generateAnswer() do FAQService

```
Complete a implementação do método privado generateAnswer() na classe FAQService que recebe uma pergunta (String question) e contexto (String context). O método deve:
1. Usar o chatClient.prompt() para criar um prompt com três mensagens:
   - SystemMessage com o SYSTEM_PROMPT (constante já definida)
   - SystemMessage com o texto "CONTEXTO:\n" + context
   - UserMessage com a pergunta do usuário
2. Chamar .call().content() para obter a resposta
3. Retornar a resposta como String
4. Tratar exceções da chamada ao modelo
```

---

## ✅ Ordem de Execução Recomendada

1. **Prompt 1** - Adicionar dependências Maven
2. **Prompt 2** - Configurar application.yml (adicionar sua API key)
3. **Prompt 3** - Implementar EmbeddingService
4. **Prompt 4** - Criar ProductLoaderService
5. **Prompt 5** - Criar FAQLoaderService
6. **Prompt 6 e 7** - Implementar ChatService completo
7. **Prompt 8 e 9** - Implementar FAQService completo

---

## 💡 Dicas

- Use uma IA como GitHub Copilot, ChatGPT ou Claude para processar os prompts
- Copie cada prompt exatamente como está
- Execute os prompts na ordem apresentada
- Teste após implementar cada serviço
- Ajuste o threshold (distance) entre 0.6 e 0.8 para melhores resultados

