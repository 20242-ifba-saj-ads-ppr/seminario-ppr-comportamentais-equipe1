package interpreter;

public class Kilometer implements UnitExpression {
    @Override
    public double toBase(double value) {
        return value * 1000; // metros
    }

    @Override
    public double fromBase(double value) {
        return value / 1000;
    }
}
