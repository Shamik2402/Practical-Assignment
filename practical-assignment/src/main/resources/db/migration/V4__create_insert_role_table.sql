CREATE TABLE IF NOT EXISTS Role(
Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Role VARCHAR(20) NOT NULL);

INSERT INTO Role (Role)
VALUES('Developer'),
    ('Product Manager'),
    ('QA');