package interpreter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String input = "10 km to miles";
        String[] parts = input.split(" ");
        double value = Double.parseDouble(parts[0]);
        String from = parts[1];
        String to = parts[3];

        Map<String, UnitExpression> unitMap = Map.of(
            "km", new Kilometer(),
            "miles", new Mile()
        );

        Expression expression = new ConversionExpression(
            value,
            unitMap.get(from),
            unitMap.get(to)
        );

        System.out.printf("Resultado: %.4f%n", expression.interpret()); // Resultado: 6.2137...
    }
}
