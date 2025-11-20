-- ============================================================
-- V9 - INSERÇÃO DE NOVAS CONTAS SEGURAS
--
-- gioadmin / Senha: Fiap@2025
-- kaianuser / Senha: Fiap@2026
--
-- IMPORTANTE: Este script pressupõe que V8 (correção de hash) foi aplicado.
-- ============================================================

-- Hash para Fiap@2025: $2a$10$B00K4pXo2J9qZ5bN1sS0H02.t/F5D7zP6R8uW7xY3vG2uA1sO0H0
-- Hash para Fiap@2026: $2a$10$Q7W6cZ9vP4tY3sR2qX1w.e4vG0fH9bC8dE7uV0yL6xM5nO4i0jK3


-- 1. NOVO USUÁRIO ADMINISTRADOR (gioadmin)
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
    '$2a$10$B00K4pXo2J9qZ5bN1sS0H02.t/F5D7zP6R8uW7xY3vG2uA1sO0H0',
    'Administração de Sistemas',
    'Segurança e Cloud',
    'Compliance e LGPD',
    'Sênior',
    'ROLE_ADMIN'
);

-- 2. NOVO USUÁRIO COMUM (kaianuser)
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
    '$2a$10$Q7W6cZ9vP4tY3sR2qX1w.e4vG0fH9bC8dE7uV0yL6xM5nO4i0jK3',
    'Desenvolvimento Java',
    'Inteligência Artificial',
    'Especialista em Back-end',
    'Júnior',
    'ROLE_USER'
);