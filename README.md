# ☕ Learning Java: Guia de Referência

Este repositório centraliza os conceitos fundamentais para o desenvolvimento e aprendizado em Java.
[Tópico 01 - Instalação, configuração e introdução ao Java](#tópico-01---instalação-configuração-e-introdução-ao-java)
[Tópico 02 - Tipos Primitivos](#tópico-02---tipos-primitivos)
---

# Tópico 01 - Instalação, configuração e introdução ao Java

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

**Classes:** - PascalCase - ***ProcessadorPagamento*** \
**Métodos/Atributos:** - camelCase - ***calcularTotal()*** \
**Constantes:** SNAKE_CASE - ***VALOR_MAXIMO***

---

## 📝 Documentação e Comentários

Utilizados para auxiliar o esclarecimento do código para outros desenvolvedores ou para gerar documentação automática.

* **Comentário de uma linha:** `//`
* **Comentário em bloco:** `/* ... */`
* **JavaDoc:** `/** ... */` (Utilizado para descrever comportamentos de métodos e classes)

---

# Tópico 02 - Tipos Primitivos

Os tipos primitivos são a base do Java. Eles guardam valores simples diretamente na memória, consistem em palavras reservadas e são escritos totalmente em letras minúsculas.

## Lista de Tipos Primitivos
* **Inteiros:** `byte`, `short`, `int`, `long`
* **Decimais (Ponto Flutuante):** `float`, `double`
* **Caractere:** `char` (armazena um único caractere Unicode)
* **Lógico:** `boolean` (`true` ou `false`)

---

## Literais e Casting

No Java, precisamos diferenciar como o valor é escrito (**Literal**) de como ele é convertido (**Casting**).

### 1. Literais (Sufixos)
Para tipos específicos, adicionamos uma letra ao final do valor para que o Java não o confunda com `int` ou `double`:
* **float:** Adicione um `F` ao final (ex: `float n = 10.5F;`).
* **long:** Adicione um `L` ao final (ex: `long n = 1000L;`).

### 2. Casting
O **Casting** é a conversão explícita de um tipo para outro. Consiste em colocar o tipo desejado entre parênteses à frente da variável ou valor.

* **Exemplo de Casting:**
```java
double valor = 9.99;
int valorInteiro = (int) valor; // Resultado: 9 (perda de precisão)
```

# Tópico 03 - Operadores
## Aritiméticos
São processos que podem ser realizados dentro do java com o proposito matématico, segue abaixo os principais operadores:
+, -. /, *

## Relacionais
São operadores utilizados para validar dois diferentes valores resultando em um valor booleano, sendo os principais:
 ==, >=, <= !=.

## Lógicos
Se consistem em operadores de avaliação de condições, sendo eles o **&&** - AND, **||** - OR, **!** - NOT

## Atribuição
Operadores que atribuem algo a variavél, sendo eles o =, +=, -=., *=, /=, %=

# Tópico 04 - Estruturas de condicionais

As estruturas condicionais são usadas para tomar decisões no programa. Elas verificam uma condição e executam um bloco de código apenas quando essa condição for verdadeira.

## if
O `if` testa uma condição booleana.

```java
if (condicao) {
    // código executado se a condição for verdadeira
}
```

Operador de negação !
O operador ! inverte o valor booleano.

```
if (!condicao) {
// código executado se a condição for falsa
}
```