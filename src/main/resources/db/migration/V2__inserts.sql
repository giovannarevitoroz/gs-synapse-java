---------------------------------------------------------------
-- INSERTS PADRÃO (Senha = 123456)
---------------------------------------------------------------

-- Hash BCrypt válido para "123456"
-- $2a$10$tJ92o1LgI4h0gq.Gk6z7E.2f/4k/121L9N0P5P9qP8/3mJ/6w2A3

INSERT INTO usuario (
    nome_usuario, senha_usuario, area_atual, area_interesse,
    objetivo_carreira, nivel_experiencia, role
) VALUES (
    'admin',
    '$2a$10$uE.K131pW29zP5fV7XqYJ.I5Q70mX8eB3f4D6xY4J7C9N2R1S0H02',
    'Administração',
    'Gestão',
    'Gerenciar o sistema',
    'Sênior',
    'ROLE_ADMIN'
);

INSERT INTO usuario (
    nome_usuario, senha_usuario, area_atual, area_interesse,
    objetivo_carreira, nivel_experiencia, role
) VALUES (
    'user',
    '$2a$10$uE.K131pW29zP5fV7XqYJ.I5Q70mX8eB3f4D6xY4J7C9N2R1S0H02',
    'Desenvolvimento',
    'IA',
    'Aprender novas tecnologias',
    'Júnior',
    'ROLE_USER'
);

