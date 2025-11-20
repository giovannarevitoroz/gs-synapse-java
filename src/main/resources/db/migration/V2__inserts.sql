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
    '$2b$12$eDfMQ3W37ODC/Xdn4cYYgevZMjwxyBNQDOO86vkZNpU4bxGYOaOAa', -- senha bcrypt
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
    '$2b$12$aXwCoqljl7xI5avDt/cHs.YCUavHn6pf9z.d4NQD/QoGSV.6f5Yt6', -- senha Fiap@2025
    'Tecnologia da Informação',
    'Segurança da Informação',
    'Crescer profissionalmente na área de TI',
    'Avançado',
    'ROLE_USER'
);
