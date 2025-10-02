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
    "distance": 0.65
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
    "distance": 0.5
  }'
```

## Exemplos de Respostas Esperadas

### Resposta Típica para Camiseta:
```json
{
  "response": "👋 Encontrei uma opção perfeita para você!\n\n• Camiseta Básica Masculina Preta - R$59,90\n  Algodão 100%, modelagem slim, ideal para o dia a dia\n  Link: https://loja.exemplo.com/produtos/camiseta-basica-masculina-preta",
  "query": "Quero uma camiseta preta masculina básica",
  "threshold": 0.7
}
```

### Resposta quando não encontra produtos:
```json
{
  "response": "😕 Não encontrei produtos correspondentes agora. Pode tentar reformular ou fornecer mais detalhes?",
  "query": "produto inexistente",
  "threshold": 0.9
}
```

## Dicas para Demonstração

1. **Comece com o endpoint de status** para mostrar que o sistema está funcionando
2. **Use queries específicas primeiro** (ex: "camiseta preta masculina") 
3. **Depois teste queries mais vagas** (ex: "algo confortável")
4. **Varie o threshold** para demonstrar como afeta os resultados
5. **Mostre o comportamento quando não encontra produtos**
