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
    '$2a$10$xc6Oibun7tkNO90Iy29R9.Q7XJZF2hq5a1exTChJjnoR1h1JKuftW', -- senha bcrypt
    'Tecnologia da Informação',
    'Segurança da Informação',
    'Crescer profissionalmente na área de TI',
    'Avançado',
    'ROLE_ADMIN'
);

INSERT INTO usuario (
    nome_usuario,
    senha_usuario,
    area_atual,
    area_interesse,
    objetivo_carreira,
    nivel_experiencia,
    role
) VALUES (
    'giovanna',
    '$2a$12$IoFM0W2Wr8DXZFM809h.A.KGVjyx5mqEGiOJJS/2d8C1LkQTVY2Uy', -- senha Fiap@2025
    'Tecnologia da Informação',
    'Segurança da Informação',
    'Crescer profissionalmente na área de TI',
    'Avançado',
    'ROLE_USER'
);
