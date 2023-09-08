CREATE TABLE Story (
  Story_Id BIGINT NOT NULL,
  Title VARCHAR(25) NOT NULL,
  Description LONGTEXT NULL,
  Status_Id INT NOT NULL,
  Priority_Id INT NOT NULL,
  Story_Type_Id INT NOT NULL,
  Created_On DATE NOT NULL,
  PRIMARY KEY (Story_Id),
  INDEX Status_Id_idx (Status_Id ASC) VISIBLE,
  INDEX Priority_Id_idx (Priority_Id ASC) VISIBLE,
  INDEX Story_Type_Id_idx (Story_Type_Id ASC) VISIBLE,
  CONSTRAINT Status_Id
    FOREIGN KEY (Status_Id)
    REFERENCES Status (Status_Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT Priority_Id
    FOREIGN KEY (Priority_Id)
    REFERENCES Priority (Priority_Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT Story_Type_Id
    FOREIGN KEY (Story_Type_Id)
    REFERENCES Story_Type (Story_Type_Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);