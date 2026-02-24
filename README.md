# ☕ Learning Java: Guia de Referência

Este repositório centraliza os conceitos fundamentais para o desenvolvimento e aprendizado em Java.

---

## ⚙️ Configuração do Ambiente

A configuração das variáveis de ambiente é essencial para que o Sistema Operacional reconheça os binários do Java (JDK)
de qualquer diretório via terminal.

### Passo a Passo

1. **Variável `JAVA_HOME`**: Crie uma variável de sistema apontando para o diretório de instalação do seu JDK (ex:
   `C:\Program Files\Java\jdk-xx`).
2. **Variável `Path`**: Adicione a entrada `%JAVA_HOME%\bin` à variável `Path`. Isso permite que você invoque o
   compilador e a JVM (Java Virtual Machine) globalmente.

---

## 💻 Prompt de Comando (CLI)

O fluxo básico de desenvolvimento consiste em transformar o código-fonte em **bytecode** para então executá-lo na JVM.

* **`javac` (Compilador):** Transforma o arquivo `.java` em um arquivo `.class`.
    ```bash
    javac OlaMundo.java
    ```
* **`java` (Execução):** Inicia a Virtual Machine para rodar o bytecode gerado.
    ```bash
    java OlaMundo
    ```

---

## 📂 Estrutura e Nomenclatura

O ecossistema Java preza muito pela padronização. Uma estrutura comum segue o padrão de domínio invertido e separação
por responsabilidades.

### Convenção de Pacotes

A recomendação é utilizar letras minúsculas e organizar de forma hierárquica:
`com.projeto.modulo.funcao`

### Padrões de Escrita (Naming Conventions)

| Tipo                  | Padrão     | Exemplo                |
|:----------------------|:-----------|:-----------------------|
| **Classes**           | PascalCase | `ProcessadorPagamento` |
| **Métodos/Atributos** | camelCase  | `calcularTotal()`      |
| **Constantes**        | SNAKE_CASE | `VALOR_MAXIMO`         |

---

## 📝 Documentação e Comentários

Utilizados para auxiliar o esclarecimento do código para outros desenvolvedores ou para gerar documentação automática.

* **Comentário de uma linha:** `//`
* **Comentário em bloco:** `/* ... */`
* **JavaDoc:** `/** ... */` (Utilizado para descrever comportamentos de métodos e classes)

---