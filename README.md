# Sistema de Venda de Ingressos de Cinema

Este projeto é um sistema de venda de ingressos de cinema desenvolvido utilizando a arquitetura de microserviços. O objetivo é fornecer uma plataforma escalável e independente para a consulta de filmes, horários, escolha de assentos, compra de ingressos e processamento de pagamentos.

## Estrutura do Projeto

O sistema é composto por quatro principais microserviços:

1. **Serviço de Catálogo de Filmes, Horários e Assentos (Session Service)**
2. **Serviço de Venda de Ingressos (TicketSales Service)**
3. **Serviço de Pagamentos (Payment Service)**
4. **Serviço de Notificações (SendEmail Service)**

## Descrição dos Microserviços

### 1. Serviço de Catálogo de Filmes, Horários e Assentos

Responsável por gerenciar e fornecer informações sobre os filmes disponíveis, horários de exibição e a disponibilidade dos assentos.

**Endpoints**:

#### Filmes:
- `GET /movies`: Listar todos os filmes.
- `GET /movies/{id}`: Obter detalhes de um filme específico.
- `POST /movies`: Adicionar um novo filme.
- `PUT /movies/{id}`: Atualizar informações de um filme.
- `DELETE /movies/{id}`: Remover um filme.

#### Horários:
- `GET /showtimes`: Listar todos os horários de exibição.
- `GET /showtimes/{id}`: Obter detalhes de um horário específico.
- `POST /showtimes`: Adicionar um novo horário.
- `PUT /showtimes/{id}`: Atualizar informações de um horário.
- `DELETE /showtimes/{id}`: Remover um horário.

#### Assentos: (NÃO IMPLEMENTADO AINDA)
- `GET /seats`: Listar todos os assentos.
- `GET /seats/{id}`: Obter detalhes de um assento específico.
- `POST /seats`: Adicionar um novo assento.
- `PUT /seats/{id}`: Atualizar informações de um assento.
- `DELETE /seats/{id}`: Remover um assento.

**Modelos de Dados**:

#### Movie:
- `id: Integer`
- `title : String`
- `synopsis : String`
- `duration : Integer`
- `genre: String`
- `classification : String`

#### Showtimes:
- `id: Integer`
- `movie_id : Integer`
- `cine_room : Integer`
- `start_time  : Date`
- `end_time   : Date`

### 2. Serviço de Venda de Ingressos

Responsável por gerenciar a venda de ingressos.

**Endpoints**:
- `GET /tickets`: Listar todos os ingressos.
- `GET /tickets/{id}`: Obter detalhes de um ingresso específico.
- `POST /tickets`: Adicionar um novo ingresso.
- `PUT /tickets/{id}`: Atualizar informações de um ingresso.
- `DELETE /tickets/{id}`: Remover um ingresso.

**Modelo de Dados (Ticket)**:
- `id: Integer`
- `user_id: String`
- `show_time_id : Integer`
- `price : String`
- `purchase_date : Date`
- `payment_status : (PAYMENT_COMPLETED, PAYMENTE_PROCESSES, PAYMENT_ERROR)`

### 3. Serviço de Pagamentos

Responsável por processar os pagamentos de ingressos.

**Endpoints**:
- `GET /payments`: Listar todos os pagamentos.
- `GET /payments/{id}`: Obter detalhes de um pagamento específico.
- `POST /payments`: Adicionar um novo pagamento.
- `PUT /payments/{id}`: Atualizar informações de um pagamento.
- `DELETE /payments/{id}`: Remover um pagamento.

**Modelo de Dados (Payments)**:
- `id: Integer`
- `ticket_id : Integer`
- `payment_method : String` (cartão de crédito, débito, PayPal, etc.)
- `payment_date : Date`
- `metodo: String`
- `payment_status : String` (PAYMENT_COMPLETED, PAYMENTE_PROCESSES, PAYMENT_ERROR)

### 4. Serviço de Notificações

Responsável por enviar notificações aos usuários sobre o status de suas reservas e compras.

**Endpoints**:
- `GET /notifications`: Listar todas as notificações.
- `GET /notifications/{id}`: Obter detalhes de uma notificação específica.
- `POST /notifications`: Adicionar uma nova notificação.
- `PUT /notifications/{id}`: Atualizar informações de uma notificação.
- `DELETE /notifications/{id}`: Remover uma notificação.

**Modelo de Dados (Send Email)**:
- `id: Integer`
- `user_id: Integer`
- `message: String`
- `send_At: DateTime`

## Fluxo de Compra de Ingressos

1. Um usuário acessa o sistema para visualizar os filmes disponíveis.
2. O Session Service retorna a lista de filmes.
3. O usuário escolhe um filme e vê as sessões disponíveis, que são gerenciadas no Session.
4. O usuário seleciona uma sessão e inicia o processo de compra. O TicketSales valida a disponibilidade de assentos e inicia a transação.
5. Uma vez confirmada a compra, o Payment Service processa o pagamento.
6. Após o pagamento, uma mensagem é publicada no tópico para notificar o Send Email Service e atualizar os outros serviços (como o de relatórios).

## Contribuições

Contribuições são bem-vindas! Por favor, abra um pull request ou issue para discutir mudanças ou melhorias.
