# 🚗 API de Parque de Estacionamento

Este projeto é uma **API RESTful** para gerenciar um sistema de **parque de estacionamento**, permitindo o cadastro, entrada, saída, histórico e controle de veículos. Desenvolvida com **Java e Spring Boot**, a API utiliza **H2 Database** para persistência relacional e **MongoDB** para armazenamento de logs ou histórico de movimentações.

---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Data MongoDB
- **Banco de Dados:**
  - H2 Database (Relacional, em memória)
  - MongoDB (NoSQL)
- **Maven** (gerenciador de dependências)
- **Lombok** (para simplificar o código Java)

---

## 🛠️ Funcionalidades Principais

- ✅ Cadastro de veículos
- ✅ Registro de entrada de veículos no estacionamento
- ✅ Registro de saída com cálculo de tempo e valor
- ✅ Consulta de veículos no pátio
- ✅ Histórico de entradas/saídas armazenado no MongoDB
- ✅ Relatório de ocupação
- ✅ Validações de placa única e horário
- ✅ Documentação da API com Swagger (se implementado)

---

## 🗃️ Estrutura de Dados

### Tabela H2: `veiculo`
```java
{
  "id": 1,
  "placa": "ABC1234",
  "modelo": "Civic",
  "cor": "Prata",
  "tipo": "CARRO", // Enum: CARRO, MOTO
  "dataEntrada": "2025-06-04T09:15:00",
  "dataSaida": "2025-06-04T11:45:00",
  "valorPago": 15.00
}
