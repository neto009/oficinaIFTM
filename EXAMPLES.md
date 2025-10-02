# Exemplos de Teste para o Agente E-commerce

## Testes via cURL

### 1. Verificar Status do Sistema
```bash
curl -X GET http://localhost:8080/agent/ecommerce/status
```

### 2. Buscar Camiseta Masculina
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Quero uma camiseta preta masculina básica",
    "distance": 0.7
  }'
```

### 3. Buscar Tênis para Corrida
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Preciso de um tênis bom para correr",
    "distance": 0.6
  }'
```

### 4. Buscar Roupa Feminina
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Blusa feminina elegante para trabalho",
    "distance": 0.5
  }'
```

### 5. Teste com Threshold Alto (Busca Mais Restritiva)
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Calça jeans masculina",
    "distance": 0.9
  }'
```

### 6. Teste com Query Vaga (Threshold Baixo)
```bash
curl -X POST http://localhost:8080/agent/ecommerce/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "Algo confortável para o fim de semana",
    "distance": 0.4
  }'
```
