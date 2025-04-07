# Interpreter

## Intenção  
Dado uma linguagem, define uma representação para sua gramática junto com um interpretador que usa a representação para interpretar sentenças dessa linguagem.

## Também conhecido como  
- Analisador de expressões  
- Interpretador de gramática  

## Motivação  
Considere um sistema que precisa interpretar comandos ou expressões simples, como fórmulas matemáticas ou instruções de uma linguagem de domínio específico (DSL).  
Ao invés de criar regras complexas usando condicionais ou expressões regulares, o padrão Interpreter propõe representar cada regra da gramática com uma classe separada, o que torna o sistema mais estruturado, extensível e orientado a objetos.  

Por exemplo, imagine a construção de um interpretador de expressões booleanas do tipo:  
```
true AND false OR true
```

Uma abordagem ingênua exigiria a análise e execução dessas expressões diretamente em código procedural. O padrão Interpreter encapsula cada parte da expressão em classes, como `AndExpression`, `OrExpression`, e `BooleanLiteral`, facilitando a extensão e manutenção do código.

## Aplicabilidade  
Use o padrão Interpreter quando:

- O problema puder ser expresso como uma linguagem formal ou estrutura gramatical.
- Você deseja construir um interpretador reutilizável e extensível.
- A gramática é simples e de tamanho moderado (caso contrário, o número de classes pode se tornar excessivo).

---

## Exemplo Aplicado  
### Interpretador de Expressões Booleanas

```plantuml
interface Expression {
    +interpret(): boolean
}

class BooleanLiteral {
    -value: boolean
    +interpret(): boolean
}

class AndExpression {
    -left: Expression
    -right: Expression
    +interpret(): boolean
}

class OrExpression {
    -left: Expression
    -right: Expression
    +interpret(): boolean
}

Expression <|.. BooleanLiteral  
Expression <|.. AndExpression  
Expression <|.. OrExpression  

BooleanLiteral --> Expression  
AndExpression --> Expression  
OrExpression --> Expression  
```

---

## Estrutura GOF



---

## Participantes

- **AbstractExpression (`Expression`)**  
  Declara a interface comum para todas as expressões.

- **TerminalExpression (`BooleanLiteral`)**  
  Representa elementos terminais da gramática. Implementa a operação de interpretação diretamente.

- **NonTerminalExpression (`AndExpression`, `OrExpression`)**  
  Representa regras compostas da gramática. Usa outras expressões para interpretar resultados.

- **Context (opcional)**  
  Contém informações globais necessárias durante a interpretação (ex.: variáveis, ambiente, etc.).

- **Client**  
  Constrói a árvore de sintaxe abstrata e inicia a interpretação.

---

## Colaborações

- O cliente constrói a árvore de expressão utilizando instâncias de `Expression`.
- A interpretação recursiva ocorre de forma hierárquica, respeitando a estrutura da árvore sintática.

---

## Consequências

### Benefícios
- **Flexível e extensível:** Fácil adicionar novas regras à gramática.
- **Orientado a objetos:** Encapsula cada parte da linguagem em uma classe.
- **Reutilização:** Regras podem ser combinadas e reutilizadas em outras expressões.

### Desvantagens
- **Explosão de classes:** Cada regra da gramática exige uma nova classe.
- **Pouco eficiente:** Em linguagens complexas, pode ser menos eficiente que interpretadores manuais ou gerados por ferramentas como ANTLR.

---

## Implementação

### Definir a interface `Expression`

```ts
export interface Expression {
  interpret(): boolean;
}
```

### Criar expressões terminais

```ts
export class BooleanLiteral implements Expression {
  constructor(private value: boolean) {}
  interpret(): boolean {
    return this.value;
  }
}
```

### Criar expressões não-terminais

```ts
export class AndExpression implements Expression {
  constructor(private left: Expression, private right: Expression) {}
  interpret(): boolean {
    return this.left.interpret() && this.right.interpret();
  }
}
```

```ts
export class OrExpression implements Expression {
  constructor(private left: Expression, private right: Expression) {}
  interpret(): boolean {
    return this.left.interpret() || this.right.interpret();
  }
}
```

### Cliente

```ts
const expr = new OrExpression(
  new AndExpression(new BooleanLiteral(true), new BooleanLiteral(false)),
  new BooleanLiteral(true)
);

console.log(expr.interpret()); // true
```

---

## Usos Conhecidos

- **SQL Engines**: Interpretação de comandos em tempo real.
- **Expressões regulares**: Cada parte pode ser representada como uma expressão.
- **Compiladores ou interpretadores simples**.
- **DSLs (Domain-Specific Languages)** integradas a aplicações.

---

## Padrões Relacionados

- **Composite**: A árvore de sintaxe abstrata é uma estrutura composta.
- **Iterator**: Pode ser usado para percorrer a árvore de forma controlada.
- **Visitor**: Permite adicionar operações sobre os elementos da árvore sem alterar suas classes.

---

## Conclusão  
O padrão Interpreter fornece uma forma estruturada e orientada a objetos de representar e interpretar linguagens simples. Embora não seja ideal para gramáticas complexas, é extremamente útil em cenários onde expressões e regras precisam ser compostas e interpretadas dinamicamente com flexibilidade.

---

## Referências

- GAMMA, Erich et al. *Padrões de Projeto: Soluções Reutilizáveis de Software Orientado a Objetos*. Bookman, 2007.  
- SHVETS, Alexandre. *Dive Into Design Patterns*, v.2021-1.16.  
- Refactoring.Guru – [Interpreter Pattern](https://refactoring.guru/design-patterns/interpreter)
