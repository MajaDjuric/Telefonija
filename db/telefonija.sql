DROP SCHEMA IF EXISTS vozproba;
CREATE SCHEMA vozproba DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE vozproba;

CREATE TABLE tarife (
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR (20) NOT NULL,
    opis VARCHAR (200)  NOT NULL,
    cena DECIMAL NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE pretplate (
	id BIGINT AUTO_INCREMENT,
    tarifaId BIGINT NOT NULL,
	pretplatnickiBroj VARCHAR(25) NOT NULL,
	datumPocetka DATETIME NOT NULL,
    trajanjeUgovora INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (tarifaId) REFERENCES tarife(id)
);
# tarife: naziv, opis, cena
INSERT INTO tarife (naziv, opis, cena) VALUES ('Potok 150', '150 min. 150 SMS, 2GB', 800);
INSERT INTO tarife (naziv, opis, cena) VALUES ('Potok 300', '300 min. 300 SMS, 2.5GB', 1200);
INSERT INTO tarife (naziv, opis, cena) VALUES ('Maslačak 12', 'neograničeni min., neograničeni SMS, 27GB', 1900);
INSERT INTO tarife (naziv, opis, cena) VALUES ('Maslačak 25', 'neograničeni min., neograničeni SMS, 40GB', 2300);
INSERT INTO tarife (naziv, opis, cena) VALUES ('Maslačak 50', 'neograničeni min., neograničeni SMS, 100GB', 2600);
INSERT INTO tarife (naziv, opis, cena) VALUES ('Vrabac lite', 'neograničeni min., neograničeni SMS, neograničeni G)B', 3000);
INSERT INTO tarife (naziv, opis, cena) VALUES ('Vrabac max', 'neograničeni min., neograničeni SMS, neograničeni GB, besplatni Phoebus', 4500);


# pretplate: tarifa, pretplatnički broj, datum početka, trajanje ugovora
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (1, '+381641111111', '2020-01-01', 12);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (1, '+381642222222', '2020-06-01', 36);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (2, '+381643333333', '2020-01-01', 24);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (2, '+381644444444', '2022-06-01', 12);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (3, '+381645555555', '2022-01-01', 24);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (4, '+381641111111', '2021-01-01', 12);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (4, '+381643333333', '2022-01-01', 12);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (4, '+381646666666', '2022-06-01', 24);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (4, '+381647777777', '2022-01-01', 36);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (5, '+381641111111', '2022-01-01', 24);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (5, '+381648888888', '2023-01-01', 36);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (5, '+381647777777', '2023-01-01', 36);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (6, '+381643333333', '2023-01-01', 12);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (6, '+381642222222', '2023-01-01', 24);
INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (6, '+381645555555', '2023-01-01', 12);
