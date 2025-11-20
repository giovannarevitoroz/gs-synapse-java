-- Fixa a senha do usuário 'admin' para um hash BCrypt conhecido e testado.
-- Senha plana: 123456
-- Hash (BCrypt): $2a$10$uE.K131pW29zP5fV7XqYJ.I5Q70mX8eB3f4D6xY4J7C9N2R1S0H02

UPDATE usuario
SET senha_usuario = '$2a$10$uE.K131pW29zP5fV7XqYJ.I5Q70mX8eB3f4D6xY4J7C9N2R1S0H02'
WHERE nome_usuario = 'admin' OR nome_usuario = 'user' OR nome_usuario = 'giovanna';

-- Certifique-se de que a coluna tem espaço suficiente (255 caracteres é o padrão e está OK)
-- Certifique-se também que o campo 'role' está correto, apenas por segurança:
UPDATE usuario
SET role = 'ROLE_ADMIN'
WHERE nome_usuario = 'admin' OR nome_usuario = 'giovanna';

UPDATE usuario
SET role = 'ROLE_USER'
WHERE nome_usuario = 'user';