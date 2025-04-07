package interpreter;

public class ConversionExpression implements Expression {
    private final double value;
    private final UnitExpression fromUnit;
    private final UnitExpression toUnit;

    public ConversionExpression(double value, UnitExpression fromUnit, UnitExpression toUnit) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    @Override
    public double interpret() {
        double base = fromUnit.toBase(value);
        return toUnit.fromBase(base);
    }
}
