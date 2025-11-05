# Exemplos de Teste para o Agente E-commerce

## Testes via cURL

### 1. Buscar Camiseta Masculina
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Quero uma camiseta preta masculina básica",
    "distance": 0.7
  }'
```

### 2. Buscar Tênis para Corrida
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Preciso de um tênis bom para correr",
    "distance": 0.6
  }'
```

### 3. Buscar Roupa Feminina
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Blusa feminina elegante para trabalho",
    "distance": 0.5
  }'
```

### 4. Teste com Threshold Alto (Busca Mais Restritiva)
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Calça jeans masculina",
    "distance": 0.9
  }'
```

### 5. Teste com Query Vaga (Threshold Baixo)
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Algo confortável para o fim de semana",
    "distance": 0.4
  }'
```

### 6. FAQ - Segurança de Dados
```bash
curl -X POST http://localhost:8080/agent/ecommerce/faq \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Como funciona a segurança dos dados?",
    "distance": 0.7
  }'
```

### 7. FAQ - Planos de Assinaturas
```bash
curl -X POST http://localhost:8080/agent/ecommerce/faq \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Quais são os planos de assinaturas disponíveis?",
    "distance": 0.7
  }'
```

### 8. FAQ - Suporte e Atendimento
```bash
curl -X POST http://localhost:8080/agent/ecommerce/faq \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Como entro em contato com o suporte?",
    "distance": 0.6
  }'
```

### 9. FAQ - Threshold Alto (Mais Restritivo)
```bash
curl -X POST http://localhost:8080/agent/ecommerce/faq \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Explique as políticas de segurança e privacidade",
    "distance": 0.9
  }'
```

---

## Testes via cURL (Windows cmd.exe)

FAQ - Segurança de Dados (Windows):
```cmd
curl -X POST http://localhost:8080/agent/ecommerce/faq ^
  -H "Content-Type: application/json" ^
  -d "{\"query\":\"Como funciona a segurança dos dados?\",\"distance\":0.7}"
```

FAQ - Planos de Assinaturas (Windows):
```cmd
curl -X POST http://localhost:8080/agent/ecommerce/faq ^
  -H "Content-Type: application/json" ^
  -d "{\"query\":\"Quais são os planos de assinaturas disponíveis?\",\"distance\":0.7}"
```
