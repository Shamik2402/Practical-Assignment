CREATE TABLE IF NOT EXISTS Story (
  Story_Id BIGINT NOT NULL AUTO_INCREMENT,
  Title VARCHAR(25) NOT NULL,
  Description LONGTEXT NULL,
  Status_Id INT NOT NULL,
  Priority_Id INT NOT NULL,
  Story_Type_Id INT NOT NULL,
  Created_On DATE NOT NULL,
  PRIMARY KEY (Story_Id)
);

CREATE INDEX Status_Id_idx ON Story (Status_Id);
CREATE INDEX Priority_Id_idx ON Story (Priority_Id);
CREATE INDEX Story_Type_Id_idx ON Story (Story_Type_Id);

ALTER TABLE Story
ADD CONSTRAINT fk_Status_Id FOREIGN KEY (Status_Id) REFERENCES Status (Status_Id),
ADD CONSTRAINT fk_Priority_Id FOREIGN KEY (Priority_Id) REFERENCES Priority (Priority_Id),
ADD CONSTRAINT fk_Story_Type_Id FOREIGN KEY (Story_Type_Id) REFERENCES Story_Type (Story_Type_Id);
