# Sistema de Venda de Ingressos de Cinema

Este projeto é um sistema de venda de ingressos de cinema desenvolvido utilizando a arquitetura de microserviços. O objetivo é fornecer uma plataforma escalável e independente para a consulta de filmes, horários, escolha de assentos, compra de ingressos e processamento de pagamentos.

## Estrutura do Projeto

O sistema é composto por quatro principais microserviços:

1. **Serviço de Catálogo de Filmes, Horários e Assentos (Movie Catalog, Showtime, and Seat Management Service)**
2. **Serviço de Venda de Ingressos (Ticketing Service)**
3. **Serviço de Pagamentos (Payment Service)**
4. **Serviço de Notificações (Notification Service)**

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

#### Assentos:
- `GET /seats`: Listar todos os assentos.
- `GET /seats/{id}`: Obter detalhes de um assento específico.
- `POST /seats`: Adicionar um novo assento.
- `PUT /seats/{id}`: Atualizar informações de um assento.
- `DELETE /seats/{id}`: Remover um assento.

**Modelos de Dados**:

#### Filme:
- `id: String`
- `titulo: String`
- `sinopse: String`
- `diretor: String`
- `elenco: List<String>`
- `duracao: Integer`
- `genero: String`
- `classificacao: String`

#### Horario:
- `id: String`
- `filmeId: String`
- `dataHora: DateTime`
- `sala: String`

#### Assento:
- `id: String`
- `numero: String`
- `fila: String`
- `status: String` (disponível, reservado, ocupado)
- `horarioId: String`

### 2. Serviço de Venda de Ingressos

Responsável por gerenciar a venda de ingressos.

**Endpoints**:
- `GET /tickets`: Listar todos os ingressos.
- `GET /tickets/{id}`: Obter detalhes de um ingresso específico.
- `POST /tickets`: Adicionar um novo ingresso.
- `PUT /tickets/{id}`: Atualizar informações de um ingresso.
- `DELETE /tickets/{id}`: Remover um ingresso.

**Modelo de Dados (Ingresso)**:
- `id: String`
- `usuarioId: String`
- `horarioId: String`
- `assentoId: String`
- `status: String` (reservado, comprado)
- `preco: Double`

### 3. Serviço de Pagamentos

Responsável por processar os pagamentos de ingressos.

**Endpoints**:
- `GET /payments`: Listar todos os pagamentos.
- `GET /payments/{id}`: Obter detalhes de um pagamento específico.
- `POST /payments`: Adicionar um novo pagamento.
- `PUT /payments/{id}`: Atualizar informações de um pagamento.
- `DELETE /payments/{id}`: Remover um pagamento.

**Modelo de Dados (Pagamento)**:
- `id: String`
- `usuarioId: String`
- `ingressoId: String`
- `valor: Double`
- `metodo: String` (cartão de crédito, débito, PayPal, etc.)
- `status: String` (pendente, confirmado, falhado)

### 4. Serviço de Notificações

Responsável por enviar notificações aos usuários sobre o status de suas reservas e compras.

**Endpoints**:
- `GET /notifications`: Listar todas as notificações.
- `GET /notifications/{id}`: Obter detalhes de uma notificação específica.
- `POST /notifications`: Adicionar uma nova notificação.
- `PUT /notifications/{id}`: Atualizar informações de uma notificação.
- `DELETE /notifications/{id}`: Remover uma notificação.

**Modelo de Dados (Notificacao)**:
- `id: String`
- `usuarioId: String`
- `mensagem: String`
- `dataHora: DateTime`
- `tipo: String` (email, SMS, push notification)

## Fluxo de Compra de Ingressos

1. O usuário consulta os filmes disponíveis no **Serviço de Catálogo de Filmes, Horários e Assentos**.
2. Seleciona um filme e verifica os horários disponíveis no **Serviço de Catálogo de Filmes, Horários e Assentos**.
3. Escolhe um horário e verifica os assentos disponíveis no **Serviço de Catálogo de Filmes, Horários e Assentos**.
4. Reserva um assento e inicia o processo de compra no **Serviço de Venda de Ingressos**.
5. Realiza o pagamento no **Serviço de Pagamentos**.
6. Recebe uma confirmação de compra e o ingresso digital no **Serviço de Notificações**.

## Contribuições

Contribuições são bem-vindas! Por favor, abra um pull request ou issue para discutir mudanças ou melhorias.
