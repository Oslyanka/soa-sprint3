# Sprint 3 – SOA (Arquitetura Orientada a Serviços) – Recomendação de Investimentos

Para testar os endpoints, importe a coleção:
docs/SOA_Sprint3_Investimentos.postman_collection.json

# Membros do grupo:
- [Ian Xavier Kuraoka] - RM98860
- [Aksel Viktor Caminha Rae] - RM99011
- [Miguel Ruan de Souza] - RM551239
- [Arthur Wollmann Petrin] - RM98735

Projeto base em Spring Boot 3 (Java 17) com:
- REST API (CRUD de Ativos/Assets)
- Camadas (Controller, Service, Repository)
- DTO, VO (Money), Enums (AssetClass, RiskLevel)
- Validações (Jakarta Validation)
- JPA + Flyway + H2 (migrações)
- Tratamento de erros com @RestControllerAdvice
- Consumo de serviço HTTP com RestClient (mock externo + agregador)

## Como rodar
```
mvn spring-boot:run
# ou
mvn clean package && java -jar target/soa-sprint3-invest-0.0.1-SNAPSHOT.jar
```

A API sobe em http://localhost:8080.

## Endpoints principais
- GET /api/assets
- GET /api/assets/{id}
- POST /api/assets
- PUT /api/assets/{id}
- DELETE /api/assets/{id}
- GET /api/assets/by-code/{code}
- GET /api/assets/quote/{code}  (consome mock externo /external/quotes/{code})

## Consumo de serviço (HTTP)
- Mock externo: GET /external/quotes/{code}
- Cliente HTTP: QuoteClient (RestClient)
- Agregador: GET /api/assets/quote/{code}

Exemplo:
```
curl http://localhost:8080/api/assets/quote/TESOUROIPCA
```

## Banco de dados
- H2 em arquivo: target/h2db
- Flyway: V1__init.sql (tabela) e V2__seed.sql (dados)

## H2 Console
- Habilitado em /h2-console
- JDBC: jdbc:h2:file:./target/h2db/soadb
- user: sa  senha: (vazia)
