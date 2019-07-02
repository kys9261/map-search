DROP TABLE IF EXISTS user CASCADE;

CREATE TABLE user (
  seq           bigint NOT NULL AUTO_INCREMENT,
  user_id       varchar(20) NOT NULL,
  password      varchar(80) NOT NULL,
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (seq),
  CONSTRAINT unq_user_id UNIQUE (user_id)
);

CREATE TABLE search_keyword (
  seq           bigint NOT NULL AUTO_INCREMENT,
  user_seq      bigint NOT NULL,
  keyword       varchar(10) NOT NULL,
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (seq),
  CONSTRAINT fk_keywords_to_user FOREIGN KEY (user_seq) REFERENCES user (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);