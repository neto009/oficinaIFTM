# Agente LLM com Embedding para E-commerce

## 📋 Sobre o Projeto

Este é um agente inteligente de recomendação de produtos para e-commerce, desenvolvido em Java com Spring Boot. O sistema utiliza **RAG (Retrieval-Augmented Generation)** combinando embeddings vetoriais com modelos de linguagem (LLM) para fornecer recomendações precisas e contextualizadas.

## 🏗️ Arquitetura

### Componentes Principais:

- **Embeddings**: Conversão de descrições de produtos em vetores usando OpenAI
- **Vector Search**: Busca por similaridade cosseno para encontrar produtos relevantes
- **LLM Integration**: Geração de respostas naturais usando GPT
- **RAG Pipeline**: Combinação de recuperação de informações + geração de texto

### Fluxo de Funcionamento:

1. **Indexação**: Produtos são carregados e convertidos em embeddings na inicialização
2. **Query**: Usuário faz uma pergunta sobre produtos
3. **Retrieval**: Sistema busca produtos similares usando similaridade vetorial
4. **Generation**: LLM gera resposta baseada nos produtos encontrados

## 🚀 Como Executar

### Pré-requisitos:
- Java 17+
- Maven 3.6+
- Chave de API da OpenAI

### Configuração:

1. Clone o repositório
2. Configure sua API key no `application.yml`:
```yaml
spring:
  ai:
    openai:
      api-key: sua-chave-aqui
```

3. Execute o projeto:
```bash
mvn spring-boot:run
```

## 📡 Endpoints da API

### Chat com o Agente
```http
POST /agent/ecommerce/chat
Content-Type: application/json

{
  "query": "Quero uma camiseta preta masculina",
  "distance": 0.7
}
```

**Resposta:**
```json
{
  "response": "👋 Encontrei uma opção perfeita para você!\n\n• Camiseta Básica Masculina Preta - R$59,90\n  Algodão 100%, modelagem slim, ideal para o dia a dia\n  Link: https://loja.exemplo.com/produtos/camiseta-basica-masculina-preta",
  "query": "Quero uma camiseta preta masculina",
  "threshold": 0.7
}
```

### Status do Sistema
```http
GET /agent/ecommerce/status
```

**Resposta:**
```json
{
  "status": "online",
  "productsLoaded": 24,
  "message": "Agente de e-commerce operacional"
}
```

## 🎯 Conceitos de IA Demonstrados

### 1. **Embeddings Vetoriais**
- Conversão de texto em representações numéricas densas
- Captura semântica e contexto das descrições
- Permite busca por similaridade conceitual

### 2. **RAG (Retrieval-Augmented Generation)**
- Combina busca de informações com geração de texto
- Evita alucinações do LLM fornecendo contexto específico
- Mantém respostas factuais e relevantes

### 3. **Similaridade Cosseno**
- Métrica para medir proximidade entre vetores
- Fundamental para busca semântica
- Threshold configurável para controlar precisão

### 4. **Prompt Engineering**
- System prompts estruturados para guiar o comportamento do LLM
- Regras específicas para evitar informações incorretas
- Tom conversacional adequado para vendas

## 🔧 Melhorias Implementadas

✅ **Cálculo Correto de Similaridade Cosseno**: Implementação matemática precisa com normalização dos vetores

✅ **Tratamento Robusto de Erros**: Logs detalhados e respostas de fallback graceful

✅ **Validação de Entrada**: Verificação de parâmetros e valores padrão inteligentes

✅ **Endpoint de Status**: Monitoramento da saúde do sistema e produtos carregados

✅ **Logs Estruturados**: Rastreabilidade completa do pipeline de processamento

## 📊 Dados de Exemplo

O sistema inclui produtos de exemplo em 4 categorias:
- **Camisetas**: Básicas e estampadas
- **Calças**: Jeans e sociais  
- **Blusas**: Femininas variadas
- **Tênis**: Esportivos e casuais

## 🎓 Aplicação Educacional

Este projeto demonstra conceitos essenciais de IA no desenvolvimento:

- **Vector Databases**: Armazenamento e busca de embeddings
- **Semantic Search**: Busca baseada em significado, não apenas palavras-chave
- **LLM Integration**: Uso prático de modelos de linguagem em aplicações
- **Hybrid AI Systems**: Combinação de diferentes técnicas de IA

## 🔮 Possíveis Expansões

- Implementar cache de embeddings para performance
- Adicionar filtros por categoria, preço, etc.
- Histórico de conversas para contexto temporal
- Integração com banco de dados real
- Interface web para demonstrações
- Métricas de satisfação do usuário

---

**Desenvolvido para demonstração acadêmica do uso de IA no ciclo de desenvolvimento de software**
