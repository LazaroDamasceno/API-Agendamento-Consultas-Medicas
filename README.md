# API de Gerenciamento de Consultas

## Introdução

Esta API foi desenvolvida como a parte prática da monografia API de Gerenciamento de Consultas.

[Clique aqui para acessar a monografia]()

## Tecnologias usadas

|Tecnologia|Versão|
|:-:|:-:|
|Java|21|
|Spring Boot|3.2.1|

## Configurações básicas

A versão usada para o desenvolvimento desta API é a Amazon Corretto. Para ter acesso ao Java da Amazon, [clique aqui](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html).

A partir da versão 3, a versão mínima do Java é a 17.

## Como baixar

### Via download

Vá no botão `Code` e presione `Download ZIP`. Um arquivo ZIP será gerado. Extraia o conteúdo.

### Via git

Caso o git não esteja instalada, [clique aqui](https://git-scm.com/downloads).

Abra o CMD e digite `git clone https://github.com/LazaroDamasceno/API-Gerenciamento-Consultas.git`.

## Como executar e parar

### Como executar

#### Via comando do Maven

Abre o CMD e digite `cd backend` e em seguida `cd v1`. 

Por fim, digite, caso o sistema operacional seja Windows, `./mvnw clean spring-boot:run`.

#### Via arquivo executável

Abre o CMD e digite `java -jar api_gerenciamento_consultas-v1.jar`. 

### Como parar

Para parar, pressione no CMD `CRTL + C` ou feche o CMD.

## Como inserir ou exibir dados

Foi adicionado a aplicação o Swagger UI. 

Por este motivo, recomanda-se o uso do Swagger após a execução. 

Para tanto, deve ser digitado na barra de navegadores `http://localhost:8080/swagger-ui/index.html`.

## Banco de dados

O banco de dados usado foi o PostgreSQL na sua versão online presente no site [elephantSQL](https://www.elephantsql.com/).

O sistema de gerenciamento de banco de dados escolhido o [DBeaver Community](https://dbeaver.io/download/). 
