--
-- Senha de ambos os usuários é: 123456
--
-- O hash BCrypt para '123456' é: $2a$10$tJ92o1LgI4h0gq.Gk6z7E.2f/4k/121L9N0P5P9qP8/3mJ/6w2A3
--

-- Insere Usuário ADMIN
INSERT INTO usuario (
    nome_usuario,
    senha_usuario,
    area_atual,
    area_interesse,
    objetivo_carreira,
    nivel_experiencia,
    role
) VALUES (
    'admin',
    '$2a$10$tJ92o1LgI4h0gq.Gk6z7E.2f/4k/121L9N0P5P9qP8/3mJ/6w2A3',
    'Administração',
    'Gestão',
    'Gerenciar o sistema',
    'Sênior',
    'ROLE_ADMIN'
);

-- Insere Usuário Normal (USER)
INSERT INTO usuario (
    nome_usuario,
    senha_usuario,
    area_atual,
    area_interesse,
    objetivo_carreira,
    nivel_experiencia,
    role
) VALUES (
    'user',
    '$2a$10$tJ92o1LgI4h0gq.Gk6z7E.2f/4k/121L9N0P5P9qP8/3mJ/6w2A3',
    'Desenvolvimento',
    'IA',
    'Aprender novas tecnologias',
    'Júnior',
    'ROLE_USER'
);