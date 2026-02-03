<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/H2-Database-blue" alt="H2 Database">
</p>

## PROJETO PESSOAL - LIBRARY MANAGEMENT SYSTEM 📚

#### 📖 Descrição:
Sistema de gerenciamento de biblioteca desenvolvido com **Clean Architecture** e **CQRS (Command Query Responsibility Segregation)**. Esta API REST permite operações CRUD completas para livros, utilizando uma arquitetura limpa que separa responsabilidades em camadas distintas: Domain, Application e Infrastructure.

O sistema oferece:
- Cadastro, consulta, atualização e exclusão de livros
- Separação clara entre operações de leitura (Queries) e escrita (Commands)
- Persistência de dados utilizando banco H2 em memória
- Testes unitários e de integração abrangentes

Este projeto demonstra boas práticas de desenvolvimento com:
- Arquitetura limpa para manutenibilidade
- CQRS para otimização de leituras e escritas
- Testes automatizados para garantia de qualidade
- Padrões de design como Repository, Service Layer e Command Handler

---

## 🔁 Arquitetura Utilizada

### Clean Architecture + CQRS

```
┌─────────────────────────────────────┐
│         Infrastructure Layer        │  ← Controllers, Repositories
├─────────────────────────────────────┤
│        Application Layer            │  ← Services, Orchestration
├─────────────────────────────────────┤
│          Domain Layer               │  ← Entities, Commands, Queries, Handlers
└─────────────────────────────────────┘
```

**Fluxo de Operações:**

1. **Cliente** → **Controller** (Infrastructure): Recebe requisições HTTP
2. **Controller** → **Application Service**: Orquestra operações de negócio
3. **Application Service** → **Command/Query Handlers** (Domain): Processa comandos ou consultas
4. **Handlers** → **Repository** (Infrastructure): Interage com banco de dados
5. **Repository** → **Database** (H2): Persistência de dados
6. Resposta retorna pelo caminho inverso até o cliente

**Separação CQRS:**
- **Commands** (Escrita): Create, Update, Delete → Manipulam estado
- **Queries** (Leitura): GetAll, GetById → Consultam dados
- **Handlers** dedicados para cada tipo de operação

---

#### ⚡ Funcionalidades:
1. 📚 **Cadastro de Livros**: Adicionar novos livros ao catálogo
2. 📖 **Consulta de Livros**: Buscar livros por ID ou listar todos
3. ✏️ **Atualização de Livros**: Modificar informações de livros existentes
4. 🗑️ **Exclusão de Livros**: Remover livros do catálogo
5. 🔍 **Busca Otimizada**: Consultas eficientes com CQRS
6. 🌐 **Integração com Open Library**: Buscar livros em fontes externas
7. 📥 **Importação de Livros**: Adicionar livros externos ao catálogo local
8. ✅ **Testes Abrangentes**: Cobertura unitária e de integração

#### Métodos de execução:

### 🖥️ **Rodar Localmente**
Requisitos para execução:
- ☕ Java 21
- 📦 Gradle (wrapper incluído)

##### 📁 Configuração:
O projeto utiliza H2 Database em memória por padrão. Para alterar, edite `application.properties`:

```properties
# H2 Database (padrão)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Para executar:
```sh
./gradlew bootRun
```

O servidor iniciará na porta 8080. Console H2 disponível em: http://localhost:8080/h2-console

### 🔠 Como funciona a arquitetura?
- **Clean Architecture**: Separação em camadas com dependências direcionadas inward
- **CQRS**: Commands para escrita, Queries para leitura, handlers dedicados
- **Repository Pattern**: Abstração de acesso a dados
- **Service Layer**: Orquestração de operações de negócio
- **Dependency Injection**: Injeção automática de dependências via Spring

#### 🛠️ Tecnologias utilizadas:
- ☕ **Java 21**
- 🍃 **Spring Boot 4.0.2**
- ✨ **Spring WebFlux** (APIs reativas)
- 🤖 **WebClient** (chamadas HTTP reativas)
- 📂 **H2 Database** (em memória)
- 🗄️ **Spring Data JPA**
- 🔍 **Open Library API** (fonte externa de livros)
- 🧪 **JUnit 5** (testes)
- 📦 **Gradle** (build)
- 🔧 **Lombok** (boilerplate reduction)

---

## 📌 Endpoints e exemplos de uso:

### 📚 1 - Listar Todos os Livros:
```bash
GET /api/books
```

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "isbn": "9780132350884"
  }
]
```

### 🔖 2 - Buscar Livro por ID:
```bash
GET /api/books/{id}
```

**Exemplo:**
```bash
curl -X GET http://localhost:8080/api/books/1
```

### ➕ 3 - Criar Novo Livro:
```bash
POST /api/books
Content-Type: application/json

{
  "title": "Domain-Driven Design",
  "author": "Eric Evans",
  "isbn": "9780321125217"
}
```

**Exemplo com curl:**
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Domain-Driven Design","author":"Eric Evans","isbn":"9780321125217"}'
```

### ✏️ 4 - Atualizar Livro:
```bash
PUT /api/books/{id}
Content-Type: application/json

{
  "title": "Domain-Driven Design: Tackling Complexity in the Heart of Software",
  "author": "Eric Evans",
  "isbn": "9780321125217"
}
```

#### 🔍 5 - Buscar Livros Externos (Open Library API):
```bash
GET /api/books/search?query=clean+code
```

**Exemplo de resposta:**
```json
[
  {
    "id": null,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "isbn": "9780132350884"
  }
]
```

### ➕ 6 - Importar Livro da Busca Externa:
```bash
POST /api/books/import
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "9780132350884"
}
```

**Resposta:** Retorna o livro criado no banco local.

---

## 💻 Código Principal

### 📚 Entidade Book (Domain Layer)
```java
package com.wzzy.library.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
}
```

### ⚡ Command Handler (Domain Layer)
```java
package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.model.Book;
import com.wzzy.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBookCommandHandler {

    @Autowired
    private BookRepository bookRepository;

    public Book handle(CreateBookCommand command) {
        Book book = new Book();
        book.setTitle(command.getTitle());
        book.setAuthor(command.getAuthor());
        book.setIsbn(command.getIsbn());
        return bookRepository.save(book);
    }
}
```

### 🔧 Application Service (Application Layer)
```java
package com.wzzy.library.application.service;

import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.handler.CreateBookCommandHandler;
import com.wzzy.library.domain.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookApplicationService {

    @Autowired
    private CreateBookCommandHandler createBookCommandHandler;

    public Book createBook(CreateBookCommand command) {
        return createBookCommandHandler.handle(command);
    }
}
```

### 🌐 REST Controller (Infrastructure Layer)
```java
package com.wzzy.library.infrastructure.controller;

import com.wzzy.library.application.service.BookApplicationService;
import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookApplicationService bookApplicationService;

    @PostMapping
    public Book createBook(@RequestBody CreateBookCommand command) {
        return bookApplicationService.createBook(command);
    }
}
```

---

## 🧪 Testes

### Executar Todos os Testes:
```sh
./gradlew test
```

### Executar Apenas Testes Unitários:
```sh
./gradlew test --tests "*Test"
```

### Executar Apenas Testes de Integração:
```sh
./gradlew test --tests "*IntegrationTest"
```

**Cobertura de Testes:**
- ✅ **Unit Tests**: Handlers, Services (com mocks)
- ✅ **Integration Tests**: API endpoints completos
- ✅ **JSON Fixtures**: Dados de teste em arquivos JSON

---

## 🚚 Autor

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/codewesleylima" title="Wesley Lima">
        <img src="https://media.licdn.com/dms/image/v2/D4D03AQH8pgDMsT7zMw/profile-displayphoto-crop_800_800/B4DZs03OodH8AM-/0/1766118457145?e=1771459200&v=beta&t=D6FdPuUquCE43BWEXzglcI1zw_pMWO2PiYUJViZHQGQ" width="100px;" alt="Foto do Wesley Lima"/><br>
        <sub>
          <b>Wesley Lima</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](https://github.com/codewesleylima/library/blob/main/LICENSE) para mais detalhes.

---

*Desenvolvido com ❤️ utilizando Clean Architecture e CQRS*</content>
<parameter name="filePath">/home/wesley/projetos/java-projetos/library/README.md