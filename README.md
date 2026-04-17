# 🎵 ScreenSound - Cadastro de Artistas e Músicas

Projeto desenvolvido com **Spring Boot 4.0.5** e **Java 21**, com o objetivo de gerenciar um cadastro simples de artistas e suas músicas via aplicação em modo console.

## 📌 Descrição

O **ScreenSound** é uma aplicação backend que permite:

- Cadastrar artistas
- Cadastrar músicas associadas a artistas
- Listar artistas e músicas
- Buscar músicas por artista
- Remover artistas
- Consultar informações sobre artistas (integração com serviço externo)

A aplicação roda em modo console, com um menu interativo para execução das operações.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.5
- Spring Data JPA
- Hibernate
- Maven
- Banco de dados relacional (configurável)

---

## 📂 Estrutura do projeto

```
src/main/java/com.github.aldenyjr.screensound
│
├── models          → Entidades (Artista, Musica, TipoArtista)
├── repository      → Interfaces JPA (ArtistaRepository)
├── services        → Regras de negócio (ConsultaOpenAi)
├── principal       → Classe com menu interativo
└── ScreensoundApplication → Classe principal (Spring Boot)
```

---

## ⚙️ Funcionalidades

Menu disponível na aplicação:

```
1 - Cadastrar artistas
2 - Cadastrar músicas
3 - Listar artistas
4 - Listar músicas
5 - Buscar músicas por artista
6 - Deletar artista
7 - Pesquisar sobre o artista
0 - Sair
```

---

## 🧠 Modelo de domínio

### Artista
- Nome
- Tipo (SOLO, DUPLA, BANDA)
- Lista de músicas

### Música
- Título
- Relacionamento com artista

---

## 🔗 Relacionamentos

- Um **Artista** pode ter várias **Músicas** (OneToMany)
- Uma **Música** pertence a um único **Artista** (ManyToOne)

---

## ▶️ Como executar o projeto

### Pré-requisitos:
- Java 21
- Maven

### Passos:

```
# Clonar o repositório
git clone https://github.com/seu-usuario/screensound.git

# Entrar no diretório
cd screensound

# Rodar a aplicação
mvn spring-boot:run
```

---

## 🧪 Exemplo de uso

Ao iniciar a aplicação, você verá um menu no console:

```
Informe o nome do artista que deseja saber mais:
```

Você pode navegar pelas opções digitando o número correspondente.

---

## 🔌 Integração externa

O projeto possui um serviço (`ConsultaOpenAi`) que permite buscar informações sobre artistas, enriquecendo os dados exibidos no console.


---

## 📄 Licença

Este projeto é de uso educacional.
