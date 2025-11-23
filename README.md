

# Synapse

API para gerenciamento de recomendações de saúde e carreira, registro de bem-estar e competências de usuários para integração com IA.

👩‍💻 Integrantes
Giovanna Revito Roz - RM558981
Kaian Gustavo de Oliveira Nascimento - RM558986
Lucas Kenji Kikuchi - RM554424
---

## 🌟 O Futuro do Trabalho

O futuro do trabalho já começou. Avanços tecnológicos como inteligência artificial, robótica e automação estão transformando profissões, criando novas oportunidades e desafios inéditos. Mudanças sociais, demográficas e ambientais estão redesenhando como vivemos e trabalhamos.

O **GS Synapse** foi desenvolvido como parte do **Desafio FIAP – Futuro do Trabalho**, preparando usuários para carreiras emergentes e promovendo bem-estar, reskilling e acompanhamento de competências.

Principais pontos do desafio:

* Até 2030, milhões de empregos serão extintos ou transformados; requalificação constante será essencial.
* Habilidades humanas como **criatividade, empatia e pensamento crítico** serão altamente valorizadas.
* Ferramentas digitais e IA podem apoiar educação, saúde mental e decisões de carreira.
* O projeto conecta tecnologia e propósito para criar soluções inclusivas, sustentáveis e de impacto social.

## Deploy

O projeto está deployado no **Render**:

* [Link do deploy](https://gs-synapse-java-qyyy.onrender.com)
* [Link do vídeo](https://www.youtube.com/watch?v=nm4-LZsHj1M)
* [Link do pitch](https://youtu.be/s7cH_2vpfaY)

## 🧠 Descrição do Projeto

O **Synapse** é uma plataforma inteligente que combina:

### 🔹 Orientação Profissional  
O usuário informa área atual, área de interesse, competências e objetivos.  

- Vagas potenciais  
- Cursos e trilhas de aprendizado  
- Áreas sugeridas  
- Próximos passos de carreira  

### 🔹 Bem-estar (Saúde Emocional & Rotina)  
O usuário registra diariamente informações como:  

- Horas de sono  
- Horas de trabalho  
- Humor  
- Nível de estresse  
- Energia  

A IA analisa os registros e gera:

- Alertas de saúde emocional  
- Sugestões de rotina  
- Hábitos saudáveis  
- Recomendações personalizadas  

O Synapse une **carreira + bem-estar** em um ambiente inteligente para apoiar o desenvolvimento pessoal e profissional.

---

---

## Competências Desenvolvidas

* Spring Boot, JPA/Hibernate, SQL e PostgreSQL
* Segurança com Spring Security e JWT
* Implementação de IA generativa aplicada a recomendações
* Docker e deploy em Render
* Mensageria com RabbitMQ
* Internacionalização (i18n) e cache de aplicação
* Testes unitários e integração

---

## Estrutura do projeto 

```text
Synapse/
├── 📄 Dockerfile
├── 📁 src/main/java/com/fiap/gs_synapse/
│   ├── 📁 controller/
│   │   ├── CompetenciaViewController.java
│   │   ├── HomeViewController.java
│   │   ├── RecomendacaoProfissionalViewController.java
│   │   ├── RecomendacaoSaudeViewController.java
│   │   ├── RecomendacaoViewController.java
│   │   ├── RegistroBemEstarViewController.java
│   │   └── UsuarioViewController.java
│   ├── 📁 model/            # Entidades
│   │   ├── Competencia.java
│   │   ├── Recomendacao.java
│   │   ├── RecomendacaoProfissional.java
│   │   ├── RecomendacaoSaude.java
│   │   ├── RegistroBemEstar.java
│   │   └── Usuario.java
│   ├── 📁 repository/
│   │   ├── CompetenciaRepository.java
│   │   ├── RecomendacaoProfissionalRepository.java
│   │   ├── RecomendacaoRepository.java
│   │   ├── RecomendacaoSaudeRepository.java
│   │   ├── RegistroBemEstarRepository.java
│   │   └── UsuarioRepository.java
│   ├── 📁 service/
│   │   ├── CompetenciaService.java
│   │   ├── CustomUserDetailsService.java
│   │   ├── RecomendacaoProfissionalService.java
│   │   ├── RecomendacaoSaudeService.java
│   │   ├── RecomendacaoService.java
│   │   ├── RegistroBemEstarService.java
│   │   └── UsuarioService.java
│   ├── 📁 dto/
│   │   ├── CompetenciaDTO.java
│   │   ├── RecomendacaoDTO.java
│   │   ├── RecomendacaoProfissionalDTO.java
│   │   ├── RecomendacaoSaudeDTO.java
│   │   ├── RegistroBemEstarDTO.java
│   │   └── UsuarioDTO.java
│   ├── 📁 config/
│   │   ├── CacheConfig.java
│   │   ├── InternationalizationConfig.java
│   │   ├── JwtUtil.java
│   │   ├── JwtRequestFilter.java
│   │   ├── RabbitMQConfig.java
│   │   ├── SecurityConfig.java
│   │   └── WebConfig.java
│   └── 📁 exception/
│       ├── BusinessException.java
│       ├── CustomExceptionHandler.java
│       └── ResourceNotFoundException.java
├── 📁 messaging/
│   ├── EmailQueueListener.java
│   └── EmailQueueProducer.java
├── 📄 GsSynapseApplication.java
├── 📁 src/main/resources/
│   ├── 📁 db/migration/
│   │   ├── V2__novo_primeiro_script.sql
│   │   ├── V3__ajustar_restricao.sql
│   │   ├── V4__corrigir_admin_role.sql
│   │   ├── V5__creating_new_users.sql
│   │   └── V6__alter_table_usuario.sql
│   └── 📁 templates/
│       ├── 📁 static/
│       │   ├── 📁 css/
│       │   │   └── style.css
│       ├── 📁 competencias/
│       │   └── competencias.html
│       ├── 📁 home/
│       │   └── home.html
│       ├── 📁 login/
│       │   └── login.html
│       ├── 📁 recomendacoes/
│       │   └── recomendacoes.html
│       ├── 📁 recomendacoes-profissionais/
│       │   └── recomendacoes-profissionais.html
│       ├── 📁 recomendacoes-saude/
│       │   └── recomendacoes-saude.html
│       ├── 📁 registro-bem-estar/
│       │   └── registro-bem-estar.html
│       └── 📁 usuarios/
│           └── usuarios.html
└── 📄 pom.xml
```

### Relações importantes:

1. `usuario` → `recomendacao`
   Um usuário pode ter várias recomendações.

2. `recomendacao` → `recomendacao_profissional` / `recomendacao_saude`
   Cada recomendação é ou profissional ou de saúde (1:1).

3. `usuario` → `registro_bem_estar`
   Um usuário pode ter múltiplos registros de bem-estar.

4. `usuario` ↔ `competencia` (via `usuario_competencia`)
   Muitos para muitos: um usuário pode ter várias competências e vice-versa.



---

## Tecnologias e Dependências

* **Java 17**
* **Spring Boot 3.5.7**

  * Web, JPA, Security, Validation, Cache, Thymeleaf, AMQP
* **Banco de Dados**

  * PostgreSQL (produção no Render)
* **Flyway** para migrações de banco
* **Lombok** para redução de boilerplate
* **JWT** para autenticação
* **Springdoc OpenAPI** para documentação da API
* **RabbitMQ (CloudAMQP)** para filas assíncronas

Dependências no Maven:

```xml
<dependencies>
    <dependency>spring-boot-starter-web</dependency>
    <dependency>spring-boot-starter-data-jpa</dependency>
    <dependency>spring-boot-starter-security</dependency>
    <dependency>spring-boot-starter-validation</dependency>
    <dependency>spring-boot-starter-thymeleaf</dependency>
    <dependency>spring-boot-starter-cache</dependency>
    <dependency>spring-boot-starter-amqp</dependency>
    <dependency>postgresql</dependency>
    <dependency>h2</dependency>
    <dependency>lombok</dependency>
    <dependency>flyway-core</dependency>
    <dependency>jjwt-api/impl/jackson</dependency>
    <dependency>springdoc-openapi-starter-webmvc-ui</dependency>
    <dependency>springdoc-openapi-data-rest</dependency>
</dependencies>
```

---

## Configuração no Render

* **Banco PostgreSQL:**
  `jdbc:postgresql://dpg-d4fkosv5r7bs73cqcjr0-a.oregon-postgres.render.com:5432/synapse_t3j2`
  Usuário: `synapse_t3j2_user`
  Senha: `5cfvN6OJtYVsbJ1A6QNVI4zFdIviLmuU`

* **RabbitMQ (CloudAMQP)**:
  `amqps://mnufithp:7H4ttYAWYGuOyDwOtzvu2DfnUU9Hd4Lo@gorilla.lmq.cloudamqp.com/mnufithp`
  Fila: `gs_queue`

* **Porta do servidor:** 8080

O projeto está configurado para rodar no Render, incluindo o banco de dados PostgreSQL remoto e filas RabbitMQ, com suporte a i18n, paginação e logging detalhado.

---

## 🚀 Funcionalidades

* Cadastro e login de usuários com roles (`ROLE_USER`, `ROLE_ADMIN`)
* Registro de bem-estar (humor, horas de sono, nível de estresse e energia)
* Cadastro de competências e vinculação com usuários
* Recomendação de saúde e carreira (profissional)
* Segurança via **JWT** e Spring Security
* Filas assíncronas com **RabbitMQ**
* Paginação
* Aplicação adequada de caching
* Internacionalização em pt em en
* Pacote exception para tratamento de erros nas classes
* Validação


---

## Estrutura do Banco de Dados

### Tabelas principais

```mermaid
erDiagram
    USUARIO ||--o{ REGISTRO_BEM_ESTAR : possui
    USUARIO ||--o{ RECOMENDACAO : faz
    RECOMENDACAO ||--o| RECOMENDACAO_PROFISSIONAL : detalha
    RECOMENDACAO ||--o| RECOMENDACAO_SAUDE : detalha
    USUARIO ||--o{ USUARIO_COMPETENCIA : vincula
    COMPETENCIA ||--o{ USUARIO_COMPETENCIA : vincula

    USUARIO {
        BIGINT id_usuario PK
        VARCHAR nome_usuario
        VARCHAR senha_usuario
        VARCHAR area_atual
        VARCHAR area_interesse
        VARCHAR objetivo_carreira
        VARCHAR nivel_experiencia
        VARCHAR role
    }
    REGISTRO_BEM_ESTAR {
        BIGINT id_registro PK
        DATE data_registro
        VARCHAR humor_registro
        INTEGER horas_sono
        INTEGER horas_trabalho
        INTEGER nivel_energia
        INTEGER nivel_estresse
        VARCHAR observacao_registro
        BIGINT usuario_id_usuario FK
    }
    COMPETENCIA {
        BIGINT id_competencia PK
        VARCHAR nome_competencia
        VARCHAR categoria_competencia
        VARCHAR descricao_competencia
    }
    USUARIO_COMPETENCIA {
        BIGINT usuario_id_usuario FK
        BIGINT competencia_id_competencia FK
    }
    RECOMENDACAO {
        BIGINT id_recomendacao PK
        DATE data_recomendacao
        VARCHAR descricao_recomendacao
        VARCHAR prompt_usado
        VARCHAR titulo_recomendacao
        BIGINT usuario_id_usuario FK
    }
    RECOMENDACAO_PROFISSIONAL {
        BIGINT id_recomendacao PK
        VARCHAR categoria_recomendacao
        VARCHAR area_recomendacao
        VARCHAR fonte_recomendacao
    }
    RECOMENDACAO_SAUDE {
        BIGINT id_recomendacao PK
        VARCHAR tipo_saude
        VARCHAR nivel_alerta
        VARCHAR mensagem_saude
    }
```

---

## Rodando o Projeto Localmente

1. Clone o repositório:

```bash
https://github.com/giovannarevitoroz/gs-synapse-java.git
cd gs-synapse-java
```

2. Configure o `application.properties` para o PostgreSQL local ou remoto.

3. Compile e rode com Maven:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

4. Acesse a API:
   `http://localhost:8080`

5. Documentação via OpenAPI:
   `http://localhost:8080/swagger-ui.html`

---

## Estrutura de dependencias

```mermaid
graph LR
    subgraph GS Synapse
        A[com.fiap:gs-synapse:0.0.1-SNAPSHOT]
    end

    subgraph Web
        B[spring-boot-starter-web:3.5.7]
        B --> B1[spring-boot-starter:3.5.7]
        B1 --> B1a[spring-boot:3.5.7]
        B1 --> B1b[spring-boot-autoconfigure:3.5.7]
        B1 --> B1c[spring-boot-starter-logging:3.5.7]
        B1c --> B1c1[logback-classic:1.5.20]
        B1c --> B1c2[log4j-to-slf4j:2.24.3]
        B1c --> B1c3[jul-to-slf4j:2.0.17]
        B1 --> B1d[jakarta.annotation-api:2.1.1]
        B1 --> B1e[snakeyaml:2.4]
        B --> B2[spring-boot-starter-json:3.5.7]
        B2 --> B2a[jackson-databind:2.19.2]
        B2 --> B2b[jackson-datatype-jdk8:2.19.2]
        B2 --> B2c[jackson-module-parameter-names:2.19.2]
        B --> B3[spring-boot-starter-tomcat:3.5.7]
        B3 --> B3a[tomcat-embed-core:10.1.48]
        B3 --> B3b[tomcat-embed-websocket:10.1.48]
        B --> B4[spring-web:6.2.12]
        B --> B5[spring-webmvc:6.2.12]
    end

    subgraph JPA
        C[spring-boot-starter-data-jpa:3.5.7]
        C --> C1[spring-boot-starter-jdbc:3.5.7]
        C1 --> C1a[hikariCP:6.3.3]
        C1 --> C1b[spring-jdbc:6.2.12]
        C --> C2[hibernate-core:6.6.33.Final]
        C --> C3[spring-data-jpa:3.5.5]
    end

    subgraph Outros
        D[h2:2.3.232]
        E[spring-boot-starter-validation:3.5.7]
        F[spring-boot-starter-security:3.5.7]
        G[spring-boot-starter-thymeleaf:3.5.7]
        H[spring-boot-starter-cache:3.5.7]
        I[spring-boot-starter-amqp:3.5.7]
        J[postgresql:42.7.8]
        K[lombok:1.18.38]
        L[flyway-core:11.7.2]
        M[jjwt-api/impl/jackson:0.11.5]
        N[springdoc-openapi:2.8.11]
        O[springdoc-openapi-data-rest:1.7.0]
    end

    A --> B
    A --> C
    A --> D
    A --> E
    A --> F
    A --> G
    A --> H
    A --> I
    A --> J
    A --> K
    A --> L
    A --> M
    A --> N
    A --> O
```

## Aprendizados

* Integração completa Spring Boot + PostgreSQL + RabbitMQ
* Configuração de Flyway para versionamento de banco
* Implementação de segurança JWT e roles de usuário
* Estrutura modularizada (API, dados, segurança, UI com Thymeleaf)

## Aprendizados

---
Giovanna Revito Roz
Lucas Kenji Kikuchi
Kaian Gustavo 

