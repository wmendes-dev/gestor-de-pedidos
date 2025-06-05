# 📦 Gestor de Pedidos

Este projeto simula um ambiente real de **microsserviços**, com foco em **comunicação assíncrona baseada em eventos**. Foi criado com propósito didático e como parte do meu portfólio, para demonstrar a aplicação de conceitos como **Arquitetura Orientada a Eventos (Event-Driven Architecture)**, **Kafka**, **Outbox Pattern** e orquestração com **Docker Compose**.

---

## ⚙️ Tecnologias e Padrões Utilizados

### 🧩 Microsserviços
- Separação de responsabilidades por contexto (ex: pedidos, estoque, pagamento)
- Cada serviço possui seu próprio banco de dados
- Comunicação entre serviços é feita de forma assíncrona, orientada a eventos

### ⚡ Arquitetura Orientada a Eventos (Event-Driven)
- Os microsserviços publicam e consomem **eventos de domínio** como `PedidoCriado` e `EstoqueReservado`
- Os eventos são trafegados através do **Apache Kafka**, que atua como barramento de eventos
- Essa abordagem permite desacoplamento, escalabilidade e reatividade entre os serviços

### 📬 Kafka
- Integração feita com [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)
- Utilizado como barramento de eventos entre os microsserviços

### 📤 Outbox Pattern
- Garante a consistência entre as operações no banco de dados e o envio de eventos para o Kafka
- Eventos são persistidos em uma tabela intermediária (`EVENTO_OUTBOX`)
- (`@Scheduled`) tasks buscam periodicamente por eventos pendentes e os publicam no Kafka

### 🛠️ Docker & Docker Compose
- Cada microsserviço é containerizado individualmente
- O `docker-compose.yml` orquestra a aplicação completa, facilitando o ambiente de desenvolvimento local

### 🛢️ H2 Database
- Utilizado para testes e simplicidade no ambiente de desenvolvimento

---

## 🚀 Como rodar o projeto

Certifique-se de ter o Docker (com suporte ao Docker Compose) instalado.

```bash
cd gestor-de-pedidos
docker-compose up -d
