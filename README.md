# Estacionamentx

Documentação para instalação do projeto

## Como Iniciar o Projeto com o Eclipse

1. Abra a IDE vá até a aba "File" e clique em "Open Projects from File System...";
2. Vá até a pasta do Projeto, selecione a pasta e clique em "Finish".

### Configurando Ambiente

1. Abra o arquivo "pom.xml" e excute o mesmo cliacando no botão "Run";
2. Selecione a opção "Maven Install".

### Configurando a conexão com o banco de dados

[Criar Banco](https://www.quackit.com/mysql/workbench/create_a_database.cfm)
[Criar Usuário](https://www.youtube.com/watch?v=P7whjxMqYU4&ab_channel=PnTutorialsbyPradnyankurNikam)

#### Opção 1 - Utilizar a configuração atual

1. Acesse o mysql como super usuário (administrador, root);
2. Crie um banco de dados "estacionamentx";
3. Crie um usuário "estacionamentx" com senha "estacionamentx";
4. Adicione os privilegios do banco "estacionamentx" ao usuário "estacionamentx".

#### Opção 2 - Configuração personalizada

1. Abra o arquivo de [configuração](https://github.com/christianmurata/estacionamentx/blob/main/src/main/resources/application.properties);
2. Crie um banco de dados mysql, bem como usuário e senha se achar necessário;
3. Edite o arquivo da seguinte maneira:

```
.
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/{NOME_DO_BANCO_QUE_DESEJA_UTILIZAR}
spring.datasource.username={NOME_DE_USUARIO}
spring.datasource.password={SENHA_DO_USUARIO}
```
## Estrutura do Projeto
[Diretórios](https://github.com/christianmurata/estacionamentx/docs/ESTRUTURA.md)


## Ambiente
[Clique aqui para acessar](http://localhost:8000/)
