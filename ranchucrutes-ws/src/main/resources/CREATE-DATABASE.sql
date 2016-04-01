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
  KEY_DEVICE_GCM VARCHAR(255),
  PRIMARY KEY (ID)
);

CREATE TABLE PROFISSIONAL (
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

CREATE TABLE PROFISSIONAL_CLINICA(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_PROFISSIONAL INT(28) NOT NULL,
  ID_CLINICA INT(28) NOT NULL,
  DDD INT(2),
  TELEFONE INT(10),
  ACEITA_PARTICULAR INT(1) NOT NULL,
  FOREIGN KEY (ID_PROFISSIONAL)
             REFERENCES PROFISSIONAL(ID_LOGIN)
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
  ID_PROFISSAO INT(10) NOT NULL,
  POPULAR CHAR(1) NOT NULL,
  FOREIGN KEY (ID_PROFISSAO)
       REFERENCES PROFISSAO(ID),
  PRIMARY KEY (ID)

);


CREATE TABLE PROFISSIONAL_ESPECIALIDADE(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_PROFISSIONAL INT(28) NOT NULL,
  ID_ESPECIALIDADE INT(10) NOT NULL,
  FOREIGN KEY (ID_PROFISSIONAL)
            REFERENCES PROFISSIONAL(ID_LOGIN)
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
  ABERTURA_AGENDA INT(2) NOT NULL,
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


CREATE TABLE APP_CONFIG (
  ID INT(10) NOT NULL AUTO_INCREMENT,
  CHAVE VARCHAR(50) NOT NULL,
  VALOR VARCHAR(200) NOT NULL,
  DATA_CRIACAO DATE NOT NULL,
  DATA_ATUALIZACAO DATE NOT NULL,
  PRIMARY KEY (ID)
);


CREATE TABLE PACIENTE (
  ID_LOGIN INT(28) NOT NULL,
  NOME VARCHAR(100) NOT NULL,
  TELEFONE VARCHAR(15) NOT NULL,
  SOCIAL_KEY VARCHAR(64),
  REDE_SOCIAL INT(1),
  ID_CATEGORIA INT(10),
  FOREIGN KEY (ID_CATEGORIA)
          REFERENCES CONVENIO_CATEGORIA(ID),
  FOREIGN KEY (ID_LOGIN)
            REFERENCES LOGIN(ID)
            ON DELETE CASCADE,
  PRIMARY KEY (ID_LOGIN)

);


CREATE TABLE AGENDAMENTO (
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_PROFISSIONAL INT(28) NOT NULL,
  ID_PACIENTE INT(28) NOT NULL,
  ID_CLINICA INT(28) NOT NULL,
  DATA_AGENDAMENTO DATETIME NOT NULL,
  DATA_CRIACAO DATETIME NOT NULL,
  DATA_CONFIRMACAO DATETIME,
  CODIGO_CONFIRMACAO VARCHAR(8),
  CANCELADO INT (1) NOT NULL,
  DATA_CONFIRMACAO_CONSULTA DATETIME,
  DATA_CONFIRMACAO_PROFISSIONAL DATETIME,
  FOREIGN KEY (ID_PROFISSIONAL)
          REFERENCES PROFISSIONAL(ID_LOGIN),
  FOREIGN KEY (ID_PACIENTE)
            REFERENCES PACIENTE(ID_LOGIN),
  FOREIGN KEY (ID_CLINICA)
                   REFERENCES CLINICA(ID),
  PRIMARY KEY (ID)
);

CREATE TABLE AGENDA_CANCELADA(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_PROFISSIONAL INT(28) NOT NULL,
  ID_CLINICA INT(28) NOT NULL,
  DATA_CANCELADA DATETIME NOT NULL,
  DATA_CANCELAMENTO DATETIME NOT NULL,
  FOREIGN KEY (ID_PROFISSIONAL)
          REFERENCES PROFISSIONAL(ID_LOGIN),
  FOREIGN KEY (ID_CLINICA)
                   REFERENCES CLINICA(ID)
                   ON DELETE CASCADE,
  PRIMARY KEY (ID)
);

CREATE TABLE NOTIFICACAO(
  ID INT(28) NOT NULL AUTO_INCREMENT,
  ID_LOGIN INT(28) NOT NULL,
  TIPO INT(2) NOT NULL,
  DATA_CRIACAO DATETIME NOT NULL,
  ID_REFERENCIA INT(28) NOT NULL,
  DATA_ENVIO DATETIME,
  FOREIGN KEY (ID_LOGIN)
          REFERENCES LOGIN(ID),
  PRIMARY KEY (ID)
);

CREATE TABLE PROFISSAO(
    ID INT(10) NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(100) NOT NULL,
    POPULAR CHAR(1) NOT NULL,
    PRIMARY KEY (ID)
);


CREATE TABLE ESPECIALIDADE_TAGS(
   ID INT(10) NOT NULL AUTO_INCREMENT,
   ID_ESPECIALIDADE INT(10) NOT NULL,
   TAG VARCHAR(50) NOT NULL,
   FOREIGN KEY (ID_ESPECIALIDADE)
                 REFERENCES ESPECIALIDADE(ID),
   PRIMARY KEY (ID)
);


/*EXEMPLOS
--ALTER TABLE CONVENIO_CATEGORIA MODIFY NOME VARCHAR(120) NOT NULL;
--ALTER TABLE PEDIDO DROP PATH_IMAGE;
--DROP TABLE MEDICO_ENDERECO;*/

