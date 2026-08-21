# Polynomial Linked List

Aplicação Java de terminal para representar e manipular polinômios por meio de uma lista simplesmente encadeada. O trabalho foi desenvolvido na disciplina de **Estruturas de Dados** durante a graduação em Ciência da Computação.

## Visão geral

Cada termo é representado por um monômio com coeficiente e expoente. Os monômios são armazenados em nós encadeados e mantidos em ordem decrescente de expoente, permitindo exibição consistente e combinação de termos semelhantes.

## Problema e objetivo

O objetivo é aplicar operações de listas encadeadas a um domínio matemático. A implementação constrói um tipo abstrato de dados para polinômios sem recorrer às coleções prontas da biblioteca padrão do Java.

## Principais funcionalidades

- inserção ordenada de monômios;
- combinação de termos com expoentes iguais;
- remoção e consulta de termos por expoente;
- soma e subtração de polinômios;
- multiplicação por escalar e entre polinômios;
- cálculo do valor numérico para um valor de `x`;
- menu interativo com validação de entradas;
- testes das principais operações.

## Tecnologias utilizadas

- Java;
- API padrão do Java (`Scanner`, `StringBuilder` e `Math`);
- compilação direta com o JDK, sem bibliotecas externas.

## Conceitos acadêmicos aplicados

- lista simplesmente encadeada;
- nós e referências;
- inserção, busca, remoção e percurso;
- tipos abstratos de dados;
- encapsulamento e orientação a objetos;
- decomposição de responsabilidades;
- tratamento de lista vazia e cancelamento de termos.

## Estrutura do projeto

```text
polynomial-linked-list/
├── README.md
└── src/
    ├── Main.java
    ├── ListaPolinomio.java
    ├── Monomio.java
    ├── No.java
    ├── OperacoesPolinomio.java
    └── TestePolinomio.java
```

## Como executar

Pré-requisito: JDK instalado, com `java` e `javac` disponíveis. A partir deste diretório:

```bash
javac -d out src/*.java
java -cp out Main
```

Para executar os testes:

```bash
java -cp out TestePolinomio
```

No PowerShell, caso a expansão de `src/*.java` não esteja disponível, compile com:

```powershell
javac -d out (Get-ChildItem src\*.java)
```

## Aprendizados e desafios

O projeto exercita a manutenção manual dos vínculos entre nós e o tratamento de inserções em diferentes posições da lista. Outro ponto relevante é garantir que termos com o mesmo expoente sejam combinados e que um coeficiente resultante igual a zero remova corretamente o termo.

A separação entre a estrutura do polinômio, as operações algébricas e a interface de terminal também demonstra a aplicação de responsabilidades distintas no código.

## Demonstração

A interação ocorre pelo terminal. O menu permite cadastrar dois polinômios e selecionar as operações disponíveis. O repositório original não contém capturas de tela ou outros recursos visuais.
