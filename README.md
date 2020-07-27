# Desafio Pitang TCE

Projeto com finalidade de demonstração de conhecimento em desenvolvimento back e front-end.
Técnologias/Frameworks utilizados (back-end):
 - spring-boot
 - spring-boot-security
 - JPA/Hibernate
 - Java 8
 - Maven
 - RESTful
 - JWT
 - JSONWEBTOKEN
 - lombok
 - Banco de dados em H2.
 
# ESTÓRIAS DE USUÁRIO
 - Criar arquitetura do Projeto
 - Criar classes/esqueleto de usuário
 - Configurar banco de dados H2
 - Criar rotas do Usuário
 - Validações de Usuário
 - Login de Usuário
 - Criar classes/esqueleto de carros
 - Autorização JWT
 - Validações da API Carros
 - API Me
 
 # SOLUÇÃO

Os frameworks utilizados permitiram a implementação dos requisitos propostos com bom aproveitando, a seguir design parttern. 
Utilizei o spring-boot e todos os recursos para utilização de filtros, validações de login, criptografia de dados, autenticação de usuário buscou utilizar 
ao máximo os frameworks JWT, spring-boot-security e JSONWebToken, dentro das limitações e possibilidade de polimorfismo para automação dos requisitos. 

Adicionei filtros para validações de autenticação e autorização durante a utilização do sistema. 
Desabilitei o Cors para liberar acessos, uma vez que utilizamos STALESS como princípio de sessão, ou seja, não temos sessão de usuário e as validações e permissões são via token.

Foi trabalhado os tratamentos de exceções afim de evitar erros para o usuário final e para fins de tratamentos de erro conforme solicitação do requisito proposto. 
No intuito de reduzir o código, utilizei o framework lombok.

# BUILD PROJETO
- Baixar IDE https://spring.io/tools
- Baixar jar do lombok https://projectlombok.org/download,executar o comando para executar o jar: java -jar lombok.jar e seguir as indicações para encontrar o "eclipse.ini" instalado (ou spring tools IDE).
- Realizar o clone do projeto para máquina local;
- Abrir Spring Tools IDE e importar o projeto maven. 
- Executar a classe principal: DesafioPitangTceApplication.java (nesta classe, existe um pré-load de dados a serem utilizados para fins de teste através do POSTMAN).
- Verificar/Alterar código conforme necessidade. 

# NOTA
-  API /singin foi substituida, temporariamente, pela API /login

