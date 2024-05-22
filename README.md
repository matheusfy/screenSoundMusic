
<!-- ![Thumbnail GitHub](https://user-images.githubusercontent.com/8989346/123303345-171fc980-d4f4-11eb-84ae-cb0e49bfb126.png) -->
  
# ScreenSoundMusic

Desafio final disponibilizado pela alura para implementação de uma aplicação que possibilita ao usuário cadastrar, consultar e se informar sobre um artista ou banda.


## 🔨 Funcionalidades do projeto

- `Cadastrar artista`: informando o nome e o formato do grupo se SOLO, DUPLA ou BANDA possibilita o cadastro.
- `Cadastrar musica`: informando o nome da musica, estilo de musica (pop, k-pop, sertanejo, funk etc...) e artista faz o cadastro da musica com sucesso. Caso o artista ainda não esteja cadastrado, pede para que o usuário faça o cadastro.
- `Buscar músicas cadastradas`: realiza a listagem de todas as músicas já cadastradas.
- `Buscar música por artista`: Ao informar o nome de um artista ou banda, é buscado no banco de dados os as músicas que pertencem ao artista.
- `Buscar informações sobre artista`: Dado o nome da banda ou do artista, providencia ao user um resumo sobre. 

## ✔️ Técnicas e tecnologias utilizadas

- `Java 17`: Linguagem principal para desenvolver a aplicação.
- `Springboot`:
  - `JPA/Hibernate`: responsável para fazer a comunicação com o banco de dados e todo o gerenciamento de criação das tabelas para as entidades e relacionamento.
- `Gradle:` Para gerenciamento das dependências e builds
- `OpenAI:`dependência da OpenAI para que conseguissemos nos comunicar com seus serviços e fazer o uso da IA Generativa. 
- `Postgres:`Banco de dados para que nós conseguissemos persistir os dados inseridos pelo usuário com SQL.

## 🛠️ Abrir e rodar o projeto

**Apresente as instruções necessárias para abrir e executar o projeto**

## 📚 Mais informações do curso

> curso de Java: persistência de dados e consultas com Spring Data JPA oferecido pela Alura em conjunto com a Oracle. [Link](https://cursos.alura.com.br/course/java-persistencia-dados-consultas-spring-data-jpa)
