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
    '$2a$10$4Yh8U2N3FJ7NfQW5ASzpuu0XgqIVq9C0t3G7zZCGfBl5soI3RFL/S', -- senha bcrypt
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
    '$2a$10$flJgh9BzU5QKsGzOT8MYMuizc47u9oCGsUzJ6u0yv6PZL7v7eFhyu', -- senha Fiap@2025
    'Tecnologia da Informação',
    'Segurança da Informação',
    'Crescer profissionalmente na área de TI',
    'Avançado',
    'ROLE_USER'
);
