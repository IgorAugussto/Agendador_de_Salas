# 🗓️ Agendador de Sala
Bem-vindo ao Agendador de Sala, uma aplicação web desenvolvida para gerenciar salas, reservas e visitantes de forma simples e eficiente. Com esta aplicação, é possível cadastrar salas, realizar reservas, verificar a disponibilidade dos espaços e registrar visitantes.

# 📌 Visão Geral
O Agendador de Sala é uma aplicação backend desenvolvida com tecnologias modernas, pensada para ser escalável, segura e de fácil manutenção. Utiliza PostgreSQL em contêiner Docker, proporcionando uma solução robusta para o gerenciamento de recursos internos de uma organização (como uma igreja, escola, empresa ou espaço comunitário).

# ✅ Funcionalidades
Cadastro de Salas
Registre novas salas com informações como nome, capacidade e horários de funcionamento.

# Reserva de Salas
Permita que usuários reservem salas para datas e horários específicos.

# Verificação de Disponibilidade
Consulte rapidamente quais salas estão disponíveis em determinado período.

# Cadastro de Visitantes
Registre visitantes com dados como nome, idade, telefone e origem religiosa (se aplicável).

# 🛠️ Tecnologias Utilizadas
 - Linguagem: Java 23.0.1

 - Framework: Spring Boot 3.5.3

 - Spring Web

 - Spring Data JPA

 - Spring Security

 - Banco de Dados: PostgreSQL 15.13

 - Contêinerização: Docker + Docker Compose

 - Dependências: Lombok

 - Build & Gerenciamento: Maven

 - IDE Recomendada: IntelliJ IDEA

 - Testes de API: Postman

 - Gerenciador de Banco: DBeaver (opcional)

# ⚙️ Pré-requisitos
Antes de rodar o projeto, verifique se você possui os seguintes requisitos instalados em sua máquina:

✅ Java Development Kit (JDK) 23 ou superior

✅ Docker

✅ Docker Compose

✅ Maven

✅ IDE compatível com Java (IntelliJ IDEA recomendado)

✅ Postman (para testar os endpoints da API)

# Configuração e Instalação
## 1. Clone o Repositório

*git clone https://github.com/IgorAugussto/Agendador_de_Salas.git*
*cd agendador-de-sala*

## 2. Configure o Docker
 - Certifique-se de que o Docker e o Docker Compose estão instalados.
 - Crie ou edite o arquivo docker-compose.yml no diretório raiz com o seguinte conteúdo:

 - Inicie o container:
*docker-compose up -d*

## 3. Configure o Projeto
 - Abra o projeto no IntelliJ IDEA.
 - Edite o arquivo src/main/resources/application.properties com as configurações do banco:

## 4. Execute a Aplicação
 - No terminal ou IDE, execute:
*mvn spring-boot:run*

 - A aplicação estará disponível em http://localhost:8080.

# Uso
## Endpoints Disponíveis
 - Cadastrar Sala: POST /api/roms (JSON com id, nome, capacidade, etc.)
 - Fazer Reserva: POST /api/bookings (JSON com start_time, end_time, user_name, room_id)
 - Ver Salas Disponíveis: GET /api/rooms/available
 - Cadastrar Visitante: POST /api/visitor (JSON com id, nome, idade, phone)
# Testando com Postman
O Postman pode ser usado para testar os endpoints da API. Siga os passos abaixo:

**1. Abra o Postman.**
**2. Configure a Requisição:**
 - Método: Escolha POST ou GET conforme o endpoint.
 - URL: Insira a URL, ex.: http://localhost:8080/api/rooms.
 - Headers: Adicione Content-Type: application/json.
 - Body: Para requisições POST, use o formato JSON, ex.:

{
    "name": "Sala de Reunião 1",
    "capacity": 40,
    "availableDays": [
        "SUNDAY",
        "FRIDAY"
    ],
    "openTime": "07:00",
    "closeTime": "12:00"
}

**3. Envie a Requisição: Clique em "Send" e verifique a resposta.**
**4. Salve as Requisições: Crie uma coleção no Postman para organizar os endpoints (ex.: "Agendador de Sala API").**

## Exemplo de Requisição no Postman
**Cadastrar Sala:**
 - Método: POST
 - URL: http://localhost:8080/api/rooms
 - Body:

{
    "name": "Sala de Reunião 2",
    "capacity": 40,
    "availableDays": [
        "SUNDAY",
        "FRIDAY"
    ],
    "openTime": "07:00",
    "closeTime": "12:00"
}

 - Retorno esperado é o corpo da requisição (se bem sucedido)

# Estrutura do Projeto
 - src/main/java/com/agendador/demo: Contém as entidades JPA, repositórios, serviços e controllers.
 - src/main/resources: Configurações (ex.: application.properties).
 - docker-compose.yml: Definição dos serviços Docker.

# Contribuição
1. Faça um fork do repositório.
2. Crie uma branch para sua feature: git checkout -b feature/nova-funcionalidade.
3. Commit suas mudanças: git commit -m "Adiciona nova funcionalidade".
4. Envie para o repositório: git push origin feature/nova-funcionalidade.
5. Abra um Pull Request.

# Contato
 - Autor: Igor Auusto Ferreira Carlos
 - Email: igor.aferreirac@gmail.com
 - GitHub: IgorAugussto
