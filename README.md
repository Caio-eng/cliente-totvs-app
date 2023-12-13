                                               Clonando e Executando o Projeto Spring Boot:

Este repositório contém um projeto Java utilizando o framework Spring Boot. Abaixo estão as instruções para clonar o projeto e começar a trabalhar.

Pré-requisitos:

Antes de começar, certifique-se de ter o Git, o Java e o Spring Boot instalados em sua máquina.

Git,
 Java, 
 Spring Boot.
 
Clonando o Repositório:

Abra o terminal ou prompt de comando no diretório onde deseja clonar o projeto.

Execute o seguinte comando:

bash -> Copie a instrução abaixo

git clone https://github.com/seu-usuario/seu-repositorio.git

                                          Importando e Executando no Spring Tool Suite
                                          
Abra o Spring Tool Suite.

Clique em File -> Import -> Existing Maven Project -> Next -> Browse.

Selecione o diretório do projeto clonado e clique em Finish.

Aguarde as dependências serem importadas.

Abra o projeto, vá em TotvsApplication, clique com o botão direito do mouse, escolha Run As -> Spring Boot App e aguarde iniciar.

Rotas para Testar no Postman:

POST: http://localhost:8080/clientes

{
  "nome": "João da Silva",
  "cpf": "11330966031",
  "endereco": "Rua ABC, 123",
  "bairro": "Centro",
  "telefones": [
    {"numero": "625542-8525"},
    {"numero": "623525-5552"}
  ]
}

PUT: http://localhost:8080/clientes/1

{
  "nome": "João da Atualizado",
  "cpf": "11330966031",
  "endereco": "Rua ABC, 123",
  "bairro": "Centro",
  "telefones": [
    {"numero": "625542-8525"},
    {"numero": "623525-5552"}
  ]
}

GET: http://localhost:8080/clientes

GET por ID: http://localhost:8080/clientes/1

                                 Instruções para o Front-end Angular
                                 
Pré-requisitos:

Antes de começar, certifique-se de ter o Git, o Node.js e o Angular CLI instalados em sua máquina.

Git,
 Node.js,
 Angular CLI.
 
                           Clonando e Executando o Projeto Angular
                           
Abra o terminal ou prompt de comando na pasta totvs-app do projeto Angular.

Execute o seguinte comando para instalar as dependências:

npm install

Se o Angular CLI não estiver instalado globalmente, execute:

npm install -g @angular/cli

 Inicie o servidor de desenvolvimento:

ng serve

Abra um navegador e acesse http://localhost:4200 para visualizar a aplicação Angular.

Interaja com a aplicação para criar, editar e excluir cliente e pesquisar.
