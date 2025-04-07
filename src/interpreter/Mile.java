package interpreter;

public class Mile implements UnitExpression {
    @Override
    public double toBase(double value) {
        return value * 1609.34; // metros
    }

    @Override
    public double fromBase(double value) {
        return value / 1609.34;
    }
}