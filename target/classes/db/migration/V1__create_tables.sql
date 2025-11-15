-- DROP TABLES
DROP TABLE IF EXISTS usuario_competencia CASCADE;
DROP TABLE IF EXISTS registro_bem_estar CASCADE;
DROP TABLE IF EXISTS recomendacao_saude CASCADE;
DROP TABLE IF EXISTS recomendacao_profissional CASCADE;
DROP TABLE IF EXISTS recomendacao CASCADE;
DROP TABLE IF EXISTS competencia CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;

-- SEQUENCES
CREATE SEQUENCE seq_competencia START 1;
CREATE SEQUENCE seq_recomendacao START 1;
CREATE SEQUENCE seq_usuario START 1;
CREATE SEQUENCE seq_registro_bem_estar START 1;

----------------------------------------
-- TABELA COMPETENCIA
----------------------------------------
CREATE TABLE competencia (
    id_competencia        BIGINT PRIMARY KEY DEFAULT nextval('seq_competencia'),
    nome_competencia      VARCHAR(100) NOT NULL,
    categoria_competencia VARCHAR(50) NOT NULL,
    descricao_competencia VARCHAR(255) NOT NULL
);

----------------------------------------
-- TABELA RECOMENDACAO
----------------------------------------
CREATE TABLE recomendacao (
    id_recomendacao        BIGINT PRIMARY KEY DEFAULT nextval('seq_recomendacao'),
    data_recomendacao      DATE NOT NULL,
    descricao_recomendacao VARCHAR(1000) NOT NULL,
    prompt_usado           VARCHAR(1000) NOT NULL,
    titulo_recomendacao    VARCHAR(100) NOT NULL,
    usuario_id_usuario     BIGINT
);

----------------------------------------
-- TABELA RECOMENDACAO PROFISSIONAL
----------------------------------------
CREATE TABLE recomendacao_profissional (
    id_recomendacao        BIGINT PRIMARY KEY,
    categoria_recomendacao VARCHAR(50) NOT NULL CHECK (
        categoria_recomendacao IN ('Vaga', 'Curso')
    ),
    area_recomendacao      VARCHAR(100) NOT NULL CHECK (
        area_recomendacao IN ('Front-end', 'Back-end', 'DevOps', 'UX/UI', 'Data Science', 'Banco de Dados', 'Governança de TI', 'IA')
    ),
    fonte_recomendacao     VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX recomendacao_profissional_idx
ON recomendacao_profissional (id_recomendacao);

----------------------------------------
-- TABELA RECOMENDACAO SAÚDE
----------------------------------------
CREATE TABLE recomendacao_saude (
    id_recomendacao BIGINT PRIMARY KEY,
    tipo_saude      VARCHAR(50) NOT NULL CHECK (
        tipo_saude IN ('Sono', 'Produtividade', 'Saúde Mental')
    ),
    nivel_alerta    VARCHAR(50) NOT NULL CHECK (
        nivel_alerta IN ('Baixo', 'Moderado', 'Alto')
    ),
    mensagem_saude  VARCHAR(1000) NOT NULL
);

CREATE UNIQUE INDEX recomendacao_saude_idx
ON recomendacao_saude (id_recomendacao);

----------------------------------------
-- TABELA REGISTRO BEM ESTAR
----------------------------------------
CREATE TABLE registro_bem_estar (
    id_registro         BIGINT PRIMARY KEY DEFAULT nextval('seq_registro_bem_estar'),
    data_registro       DATE NOT NULL,
    humor_registro      VARCHAR(20) NOT NULL CHECK (
        humor_registro IN ('Feliz', 'Triste', 'Estressado', 'Bravo', 'Calmo')
    ),
    horas_sono          INTEGER NOT NULL,
    horas_trabalho      INTEGER NOT NULL,
    nivel_energia       INTEGER NOT NULL,
    nivel_estresse      INTEGER NOT NULL,
    observacao_registro VARCHAR(255),
    usuario_id_usuario  BIGINT
);

----------------------------------------
-- TABELA USUARIO
----------------------------------------
CREATE TABLE usuario (
    id_usuario        BIGINT PRIMARY KEY DEFAULT nextval('seq_usuario'),
    nome_usuario      VARCHAR(100) NOT NULL UNIQUE,
    senha_usuario     VARCHAR(255) NOT NULL,
    area_atual        VARCHAR(100) NOT NULL,
    area_interesse    VARCHAR(100) NOT NULL,
    objetivo_carreira VARCHAR(255) NOT NULL,
    nivel_experiencia VARCHAR(50) NOT NULL CHECK (
        nivel_experiencia IN ('Nenhuma', 'Estagiário', 'Júnior', 'Sênior', 'Pleno')
    )
);

----------------------------------------
-- TABELA ASSOCIATIVA USUARIO_COMPETENCIA
----------------------------------------
CREATE TABLE usuario_competencia (
    usuario_id_usuario         BIGINT NOT NULL,
    competencia_id_competencia BIGINT NOT NULL,
    PRIMARY KEY (usuario_id_usuario, competencia_id_competencia)
);

----------------------------------------
-- FOREIGN KEYS
----------------------------------------

ALTER TABLE recomendacao_profissional
    ADD CONSTRAINT recomendacao_profissional_fk
        FOREIGN KEY (id_recomendacao)
        REFERENCES recomendacao (id_recomendacao);

ALTER TABLE recomendacao_saude
    ADD CONSTRAINT recomendacao_saude_fk
        FOREIGN KEY (id_recomendacao)
        REFERENCES recomendacao (id_recomendacao);

ALTER TABLE recomendacao
    ADD CONSTRAINT recomendacao_usuario_fk
        FOREIGN KEY (usuario_id_usuario)
        REFERENCES usuario (id_usuario);

ALTER TABLE registro_bem_estar
    ADD CONSTRAINT registro_bem_estar_usuario_fk
        FOREIGN KEY (usuario_id_usuario)
        REFERENCES usuario (id_usuario);

ALTER TABLE usuario_competencia
    ADD CONSTRAINT usuario_comp_competencia_fk
        FOREIGN KEY (competencia_id_competencia)
        REFERENCES competencia (id_competencia);

ALTER TABLE usuario_competencia
    ADD CONSTRAINT usuario_comp_usuario_fk
        FOREIGN KEY (usuario_id_usuario)
        REFERENCES usuario (id_usuario);
