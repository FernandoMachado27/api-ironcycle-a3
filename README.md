# Ironcycle

## Descrição do Projeto

**Ironcycle** é um projeto desenvolvido como parte da disciplina de Estruturas de Dados e Análise de Algoritmos, cujo objetivo é construir uma API para uma seguradora de motos. O projeto foi projetado com os princípios do SOLID em mente, garantindo um código limpo, modular e de fácil manutenção. A aplicação utiliza **Spring Boot** como framework para o backend e se conecta a um banco de dados **MySQL**. O front-end foi desenvolvido com as tecnologias **HTML**, **CSS** e **JavaScript**, proporcionando uma interface interativa e amigável para o usuário.

## Funcionalidades

- Registro e gerenciamento de usuários.
- Listagem e gestão de informações sobre motos.
- Testes automatizados para garantir a qualidade do software.
- Spring Security para garantir a segurança dos dados.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** MySQL
- **Front-end:** HTML, CSS, JavaScript
- **Testes Automatizados:** Java, Selenium, Cucumber, Maven

## Estruturas de Dados

O projeto implementa duas estruturas de dados dinâmicas para os métodos `list()`, otimizando o desempenho e a eficiência na manipulação de dados.

## Testes Automatizados

Os testes automatizados são essenciais para a verificação da funcionalidade do sistema. Utilizamos **Selenium** para automação de testes de interface e **Cucumber** para definir testes em uma linguagem mais natural e compreensível. O gerenciamento de dependências e a execução dos testes são realizados com **Maven**.

## Integrantes do Projeto

- **Arthur Cassemiro Fernandes RA:125111375561**
- **Fernando Machado Fernandes RA:125111352882**
- **Henrique Pedrosa Canhadas RA:125111362170**
- **Maria Fernanda Dias da Silva RA:125111360392**

## Pré-Requisitos

Antes de iniciar a instalação do projeto, verifique se você tem as seguintes ferramentas instaladas em sua máquina:

- JDK 11 ou superior
- Maven
- MySQL Server
- Um IDE Java (como IntelliJ IDEA ou Eclipse)
- Preencher application.properties (gitignore por questões de segurança)

## application.properties

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/ironcycle
spring.datasource.username=
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.erro.include-stacktrace=never

api.security.token.secret=

logging.level.org.springframework.security=DEBUG

## Links Relacionados

- **Repositório do Front-End:** [Frontend Ironcycle](https://github.com/FernandoMachado27/frontend-ironcycle-a3)  
- **Repositório da Automação de Testes:** [Automação Ironcycle](https://github.com/FernandoMachado27/automacao-ironcycle-a3)











