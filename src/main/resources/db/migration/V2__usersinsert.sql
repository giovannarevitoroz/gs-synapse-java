-- Hash para Fiap@2025: $2a$10$B00K4pXo2J9qZ5bN1sS0H02.t/F5D7zP6R8uW7xY3vG2uA1sO0H0
-- Hash para Fiap@2026: $2a$10$Q7W6cZ9vP4tY3sR2qX1w.e4vG0fH9bC8dE7uV0yL6xM5nO4i0jK3


-- 1. USUÁRIO ADMINISTRADOR (gioadmin)
INSERT INTO usuario (
    nome_usuario,
    senha_usuario,
    area_atual,
    area_interesse,
    objetivo_carreira,
    nivel_experiencia,
    role
) VALUES (
    'gioadmin',
    '12345',
    'Administração de Sistemas',
    'Segurança e Cloud',
    'Compliance e LGPD',
    'Sênior',
    'ROLE_ADMIN'
);

-- 2. USUÁRIO COMUM (kaianuser)
INSERT INTO usuario (
    nome_usuario,
    senha_usuario,
    area_atual,
    area_interesse,
    objetivo_carreira,
    nivel_experiencia,
    role
) VALUES (
    'kaianuser',
    '123456',
    'Desenvolvimento Java',
    'Inteligência Artificial',
    'Especialista em Back-end',
    'Júnior',
    'ROLE_USER'
);

-- Insere um registro base de competência para evitar FK errors
INSERT INTO competencia (nome_competencia, categoria_competencia, descricao_competencia)
VALUES ('Programação Java', 'Hard Skill', 'Conhecimento em ecossistema Java e Spring Boot.');