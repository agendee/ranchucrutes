DROP DATABASE ranchucrutes;
CREATE DATABASE ranchucrutes;
use ranchucrutes;


CREATE TABLE LOGIN (
  ID INT(28) NOT NULL AUTO_INCREMENT,
  EMAIL VARCHAR(100) NOT NULL,
  SENHA VARCHAR(64) NOT NULL,
  DATA_CRIACAO DATE NOT NULL,
  DATA_ULTIMO_ACESSO DATE NOT NULL,
  DATA_CONFIRMACAO DATE,
  CODE_CONFIRMACAO VARCHAR(5),
  PRIMARY KEY (ID)
);

CREATE TABLE MEDICO (
  ID_LOGIN INT(28) NOT NULL,
  CRM INT(10),
  NOME VARCHAR(80) NOT NULL,
  DDD INT(2),
  CELULAR INT(10),
  FOREIGN KEY (ID_LOGIN)
          REFERENCES LOGIN(ID)
          ON DELETE CASCADE,
  PRIMARY KEY (ID_LOGIN)

);

CREATE TABLE ENDERECO(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  LOGRADOURO VARCHAR(80) NOT NULL,
  BAIRRO VARCHAR(60) NOT NULL,
  NUMERO INT(10) NOT NULL,
  LOCALIDADE VARCHAR(60) NOT NULL,
  UF VARCHAR(2) NOT NULL,
  COMPLEMENTO VARCHAR (40),
  CEP VARCHAR(8) NOT NULL,
  LATITUDE DOUBLE,
  LONGITUDE DOUBLE,
  PRIMARY KEY (ID)
);

CREATE TABLE CLINICA(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(200),
  DDD INT(2),
  TELEFONE INT(10),
  ID_ENDERECO INT (28) NOT NULL,
  FOREIGN KEY (ID_ENDERECO)
             REFERENCES ENDERECO(ID)
             ON DELETE CASCADE,
  PRIMARY KEY (ID)
);

CREATE TABLE MEDICO_CLINICA(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_MEDICO INT(28) NOT NULL,
  ID_CLINICA INT(28) NOT NULL,
  DDD INT(2),
  TELEFONE INT(10),
  ACEITA_PARTICULAR INT(1) NOT NULL,
  FOREIGN KEY (ID_MEDICO)
             REFERENCES MEDICO(ID_LOGIN)
             ON DELETE CASCADE,
  FOREIGN KEY (ID_CLINICA)
             REFERENCES CLINICA(ID)
             ON DELETE CASCADE,
  PRIMARY KEY (ID)
);

CREATE TABLE CACHE_CEP(
    ID INT(28) NOT NULL AUTO_INCREMENT,
    CEP VARCHAR(8) NOT NULL,
    LATITUDE DOUBLE NOT NULL,
    LONGITUDE DOUBLE NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ESPECIALIDADE(
  ID INT(10) NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(80) NOT NULL,
  POPULAR CHAR(1) NOT NULL,
  PRIMARY KEY (ID)
);


CREATE TABLE MEDICO_ESPECIALIDADE(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_MEDICO INT(28) NOT NULL,
  ID_ESPECIALIDADE INT(10) NOT NULL,
  FOREIGN KEY (ID_MEDICO)
            REFERENCES MEDICO(ID_LOGIN)
            ON DELETE CASCADE,
   FOREIGN KEY (ID_ESPECIALIDADE)
               REFERENCES ESPECIALIDADE(ID)
               ON DELETE CASCADE,
    PRIMARY KEY (ID)
);



CREATE TABLE CONVENIO(
  ID INT(10) NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(80) NOT NULL,
  POPULAR CHAR(1) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE CONVENIO_CATEGORIA(
  ID INT(10) NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(120) NOT NULL,
  ID_CONVENIO INT(10) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE CLINICA_CONVENIO_CATEGORIA(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_CLINICA INT(28) NOT NULL,
  ID_CATEGORIA INT(10) NOT NULL,
  FOREIGN KEY (ID_CLINICA)
            REFERENCES CLINICA(ID)
            ON DELETE CASCADE,
  FOREIGN KEY (ID_CATEGORIA)
               REFERENCES CONVENIO_CATEGORIA(ID)
               ON DELETE CASCADE,
  PRIMARY KEY (ID)
);

CREATE TABLE AGENDA(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  HORA_FUNCIONAMENTO_INI VARCHAR(5) NOT NULL,
  HORA_FUNCIONAMENTO_FIM VARCHAR(5) NOT NULL,
  TEMPO_CONSULTA_EM_MIN INT(5) NOT NULL,
  ID_CLINICA INT(28) NOT NULL,
  FOREIGN KEY (ID_CLINICA)
                   REFERENCES CLINICA(ID)
                   ON DELETE CASCADE,
  PRIMARY KEY (ID)
);

CREATE TABLE AGENDA_HORARIO(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  HORA_INI VARCHAR(5) NOT NULL,
  HORA_FIM VARCHAR(5) NOT NULL,
  ID_AGENDA INT(28) NOT NULL,
  FOREIGN KEY (ID_AGENDA)
                 REFERENCES AGENDA(ID)
                 ON DELETE CASCADE,
  PRIMARY KEY (ID)
);

/*EXEMPLOS
--ALTER TABLE CONVENIO_CATEGORIA MODIFY NOME VARCHAR(120) NOT NULL;
--ALTER TABLE PEDIDO DROP PATH_IMAGE;
--DROP TABLE MEDICO_ENDERECO;*/

