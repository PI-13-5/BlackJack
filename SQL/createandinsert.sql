CREATE TABLE users (
  id INT(11) NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL,
  balance INT(11) NOT NULL,
  games_won INT(11) NOT NULL,
  games_lost INT(11) NOT NULL,
  games_draws INT(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC),
  UNIQUE INDEX login_UNIQUE (login ASC),
  UNIQUE INDEX email_UNIQUE (email ASC));
  
  INSERT INTO users (login, email, password, balance, games_won, games_lost, games_draws) VALUES('USER', 'USER@MAIL.COM', '[-18, 17, -53, -79, -112, 82, -28, 11, 7, -86, -64, -54, 6, 12, 35, -18]', 25000, 12, 3, 10);