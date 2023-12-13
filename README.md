Como Clonar o Projeto do Git
Este repositório contém um projeto Java utilizando o framework Spring Boot. Abaixo estão as instruções para clonar o projeto e começar a trabalhar.

Pré-requisitos
Antes de começar, certifique-se de que você tenha o Git e o Java instalados em sua máquina.

Backend: 

Git
Java
Spring Boot
Clonar o Repositório
Abra o terminal ou prompt de comando no diretório onde deseja clonar o projeto.

Execute o seguinte comando:

bash
Copy code
git clone https://github.com/seu-usuario/seu-repositorio.git
Levando em consideração que você está utilizando o Spring Tool Suite
1 - Abra o Spring, clique em File -> import -> Existing Maven Project -> Next -> Browse 
2 - Selecione o Projeto totvs e aperte Finish, aguarde importar as dependências
3 - Abra o projeto, vá em TotvsApplication, abra e -> Botão direito no Mouse, Run As -> E clique em Spring Boot App, e espere iniciar

Rotas para caso queira testar no Postaman
 Post: http://localhost:8080/clientes
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
Put: http://localhost:8080/clientes/1
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
Get: http://localhost:8080/clientes
GetById: http://localhost:8080/clientes/1


-------------------------------------------------------------------
Instrução para o Front
Git
Node
Visual Studio
Clonar o Repositório
Abra o bash na pasta totvs-app.

Aperte npm install
npm install -g @angular/cli  <- caso não tenha

agora aperte ng serve

espere iniciar, coloque o host http://localhost:4200/ em qualquer navegador

e pronto e só apertar Novo Cliente, cadastro, caso queria editar clique no lapes, caso querira delerar na lixeira, e pesquisa pela barra

