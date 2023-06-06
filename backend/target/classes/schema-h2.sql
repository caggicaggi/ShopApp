CREATE TABLE utente (
  idUtente INT PRIMARY KEY,
  name VARCHAR(200),
  surname VARCHAR(200),
  email VARCHAR(200),
  password VARCHAR(800),
  address VARCHAR(200),
  phonenumber VARCHAR(200),
  salt VARCHAR(200),
  usertype VARCHAR(200)
);

CREATE TABLE cart (
    idcart INT PRIMARY KEY,
    idproduct INT not null,
    idutente INT not null,
    quantity INT not null
    
);

INSERT INTO cart (idcart, idproduct, idutente, quantity)
VALUES (1, 2, 1, 10);
INSERT INTO cart (idcart, idproduct, idutente, quantity)
VALUES (2, 1, 1, 4);
INSERT INTO cart (idcart, idproduct, idutente, quantity)
VALUES (3, 3, 2, 13);

INSERT INTO utente (idUtente, name, surname, email, password, address, phonenumber, salt, usertype)
VALUES (1, 'Marco', 'Rossi', 'prova@gmail.com', '723f7df96a28d84f0da8094ecde34d94cea8eadf0e0f696bfa921b831df08f86',
        'via vivaldi 122', '7418529631', 'rJtvUr0A1', 'USER');