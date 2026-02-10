# AsyncFlow Checkout API 🛒

API de E-commerce de alta performance focada em **processamento assíncrono** de pedidos para suportar picos de tráfego (como Black Friday).

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-orange)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

## 🚀 O Problema vs. Solução

E-commerces tradicionais travam quando milhares de usuários clicam em "Comprar" ao mesmo tempo, pois tentam processar pagamento, estoque e email de forma síncrona (tudo na mesma requisição).

O **AsyncFlow** resolve isso implementando uma Arquitetura Orientada a Eventos:
1.  **API (Producer):** Recebe o pedido, salva como `PENDING` e joga em uma fila (RabbitMQ). Resposta imediata ao cliente (ms).
2.  **Worker (Consumer):** Processa os pedidos da fila em background, controlando a carga no banco de dados.

## 🛠️ Tech Stack

* **Linguagem:** Java 21
* **Framework:** Spring Boot 4
* **Mensageria:** RabbitMQ (Dockerizado)
* **Banco de Dados:** PostgreSQL (Dockerizado)
* **Infraestrutura:** Docker Compose
* **Cloud (Futuro):** AWS S3 para armazenamento de imagens

## ⚙️ Como Rodar Localmente

### Pré-requisitos
* Docker & Docker Desktop instalados e rodando.
* Java 21 instalado.

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/LuenderM/AsyncFlow-Checkout.git](https://github.com/LuenderM/AsyncFlow-Checkout.git)
    cd AsyncFlow-Checkout
    ```

2.  **Suba a Infraestrutura (Banco + Fila):**
    ```bash
    docker compose up -d
    ```

3.  **Configure o Ambiente:**
    * Renomeie o arquivo `src/main/resources/application.properties.example` para `application.properties`.
    * As configurações padrão já conectam no Docker local (`localhost:5432` e `localhost:5672`).

4.  **Execute a Aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

## 🗺️ Roadmap
- [x] Configuração Docker (Postgres + RabbitMQ)
- [x] Estrutura Inicial Spring Boot
- [ ] Endpoint de Criação de Pedidos (Producer)
- [ ] Worker de Processamento (Consumer)
- [ ] Integração AWS S3
- [ ] Frontend (React/Next.js)

---
Desenvolvido por [Luender Meira](https://www.linkedin.com/in/luender/)
