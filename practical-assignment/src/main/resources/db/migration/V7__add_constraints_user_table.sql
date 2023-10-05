CREATE INDEX idx_user_role ON Role (Id);

ALTER TABLE User
ADD CONSTRAINT fk_Role_Id FOREIGN KEY (Role) REFERENCES Role (Id);