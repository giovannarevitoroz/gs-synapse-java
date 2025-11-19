ALTER TABLE usuario DROP CONSTRAINT IF EXISTS usuario_role_check;

ALTER TABLE usuario ADD CONSTRAINT usuario_role_check
    CHECK (role IN ('ROLE_ADMIN', 'ROLE_USER', 'USER', 'ADMIN'));