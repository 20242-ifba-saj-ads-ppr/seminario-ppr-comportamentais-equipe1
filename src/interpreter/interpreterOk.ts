
// Interface da expressão
interface Expression {
  interpret(context: string): number;
}

// Expressões terminais
class OneExpression implements Expression {
  interpret(context: string): number {
    return context === "I" ? 1 : 0;
  }
}

class FourExpression implements Expression {
  interpret(context: string): number {
    return context === "IV" ? 4 : 0;
  }
}

class FiveExpression implements Expression {
  interpret(context: string): number {
    return context === "V" ? 5 : 0;
  }
}

// Cliente
const expressions: Expression[] = [
  new OneExpression(),
  new FourExpression(),
  new FiveExpression()
];

const input = "IV";
let result = 0;

for (const expr of expressions) {
  result = expr.interpret(input);
  if (result > 0) break;
}

console.log(`Resultado: ${result}`); // Resultado: 4
