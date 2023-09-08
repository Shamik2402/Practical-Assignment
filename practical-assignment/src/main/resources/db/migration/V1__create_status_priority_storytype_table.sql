CREATE TABLE IF NOT EXISTS Status(
Status_Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Name VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS Priority(
Priority_Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
Severity VARCHAR(15) NOT NULL);

CREATE TABLE IF NOT EXISTS Story_Type(
Story_Type_Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
Type VARCHAR(25) NOT NULL);

INSERT INTO Status (Name)
VALUES('To-Do'),
    ('In-Progress'),
    ('Completed');

INSERT INTO Priority (Severity)
VALUES
    ('High'),
    ('Medium'),
    ('Low');

INSERT INTO Story_Type (Type)
VALUES
    ('Bug'),
    ('Feature'),
    ('Enhancement');


