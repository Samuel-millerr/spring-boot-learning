# Spring Boot Learning - Quickstart

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-3.9+-orange)
![License](https://img.shields.io/badge/License-MIT-green)

## Overview

**Quickstart** é um projeto educacional desenvolvido para consolidar conceitos fundamentais e avançados do Spring Boot. 
Implementa uma API RESTful completa para gerenciamento de autores e livros, demonstrando padrões de arquitetura em camadas, 
dois enfoques distintos de acesso a dados (JDBC direto vs. JPA/Hibernate ORM), e boas práticas de configuração e testes 
em aplicações Spring Boot.

O projeto foi baseado no conteúdo do vídeo **"The ULTIMATE Guide to Spring Boot: Spring Boot for Beginners"** 
([YouTube](https://www.youtube.com/watch?v=Nv2DERaMx-4)) e complementado com especificações e estudos documentados em 
[Notion](https://app.notion.com/p/30c5288067db809c85d9f7287e0b328c?v=3505288067db8015a844000c6c62f7af).

### Objetivos Pedagógicos

- ✅ Implementação de APIs RESTful com Spring Web MVC
- ✅ Padrão arquitetural em camadas (Controllers → Services → Repository/DAO → Database)
- ✅ Comparação e integração de abordagens: JDBC direto vs. JPA/Hibernate ORM
- ✅ Gerenciamento de múltiplas datasources com configuração customizada
- ✅ Mapeamento de objetos (DTOs) com ModelMapper
- ✅ Testes de integração com MockMvc

---

## Arquitetura

## Modelo de Domínio

### Entidades

#### **Author**
Representa um autor de livros.

| Campo | Tipo | Restrições | Descrição |
|-------|------|-----------|-----------|
| `id` | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| `name` | VARCHAR(255) | NOT NULL | Nome do autor |
| `age` | INT | NOT NULL | Idade do autor |

---

#### **Book**
Representa um livro escrito por um autor.

| Campo | Tipo | Restrições | Descrição |
|-------|------|-----------|-----------|
| `isbn` | TEXT | PK | Identificador único (ISBN) |
| `title` | TEXT | NOT NULL | Título do livro |
| `author_id` | BIGINT | FK → authors | Referência ao autor |

**Relacionamento**: Cada livro pertence a um autor (Many-to-One). Não há relacionamento bidirecional configurado no modelo.

---

## Camadas da Aplicação

### 1. Camada de Apresentação (Controllers)

Os controladores REST definem os endpoints da API e orquestram requisições/respostas.

#### **AuthorController**

| Método | Endpoint | Operação | Descrição |
|--------|----------|----------|-----------|
| POST | `/authors` | CREATE | Cria novo autor |
| GET | `/authors` | READ (List) | Lista todos os autores |
| GET | `/authors/{id}` | READ (Find) | Encontra autor por ID |
| PUT | `/authors/{id}` | UPDATE (Full) | Atualização completa do autor |
| PATCH | `/authors/{id}` | UPDATE (Partial) | Atualização parcial do autor |
| DELETE | `/authors/{id}` | DELETE | Deleta autor |

---

#### **BookController**

| Método | Endpoint | Operação | Descrição |
|--------|----------|----------|-----------|
| PUT | `/books/{isbn}` | UPSERT | Cria ou atualiza livro (idempotente) |
| GET | `/books` | READ (List) | Lista todos os livros |
| GET | `/books/{isbn}` | READ (Find) | Encontra livro por ISBN |
| PATCH | `/books/{isbn}` | UPDATE (Partial) | Atualização parcial do livro |
| DELETE | `/books/{isbn}` | DELETE | Deleta livro |


### 2. Camada de Negócio (Services)

A camada de serviço encapsula lógica de negócio e orquestra operações das camadas inferiores.

#### **AuthorService**

```java
public interface AuthorService {
    Author save(Author author);
    Optional<Author> findOne(Long id);
    List<Author> findAll();
    boolean isExists(Long id);
    Author partialUpdate(Long id, Author author);
    void delete(Long id);
}
```
#### **BookService**

```java
public interface BookService {
    Book createUpdateBook(String isbn, Book book);
    Optional<Book> findOne(String isbn);
    List<Book> findAll();
    boolean isExists(String isbn);
    Book partialUpdate(String isbn, Book book);
    void delete(String isbn);
}
```

---

### 3. Camada de Acesso a Dados (Dual Approach)

O projeto implementa **dois padrões distintos** de acesso a dados para fins educacionais, com separate datasources:

#### **Abordagem 1: JDBC Direto (Data Access Objects)**

**Quando usar**: Controle fino sobre queries, operações complexas, performance crítica.

**Características**:
- ✅ Acesso SQL raw com `JdbcTemplate`
- ✅ Custom `RowMapper` para mapear ResultSet → Entity
- ✅ Queries parametrizadas (protege contra SQL injection)
- ❌ Código verboso
- ❌ Mantém estado manual de objetos

---

#### **Abordagem 2: JPA/Hibernate ORM (Repositories)**

**Quando usar**: CRUD padrão, relacionamentos automáticos, integração fácil.

**Características**:
- ✅ Código minimal (zero boilerplate)
- ✅ Transações automáticas
- ✅ Query derivation automática
- ✅ Suporte a paginação/ordenação
- ⚠️ Menos controle fino sobre queries geradas
- ⚠️ Possível N+1 query problem

---

#### **Configuração de Dual DataSources**

**Por que dois datasources?**
- 🎓 **Propósito educacional**: Demonstrar ambas as abordagens simultaneamente
- 🔀 **Isolamento**: Cada abordagem tem seu próprio banco de dados (portas 5433 vs 5434)
- 📊 **Comparação**: Permite estudar diferenças de padrões lado a lado

---

### 4. Camada de Mapeamento (Mappers)

O projeto utiliza **ModelMapper** para transformação automática entre Entities e DTOs.

**Benefícios**:
- ✅ Separação clara entre modelos (Entity vs DTO)
- ✅ Mapeamento automático por nome/tipo de campo
- ✅ Reducção de boilerplate
- ✅ DTOs específicos para API contracts


---

### Cobertura de Testes

| Componente | Tipo | Cobertura |
|---|---|---|
| Controllers | Integration | ✅ Completa (GET, POST, PUT, PATCH, DELETE) |
| Services | Integration | 📝 Parcial |
| Repositories | Integration | ✅ Completa |
| DAOs | Integration | ✅ Completa |
| Mappers | Unit | ✅ Coverage Jackson |

