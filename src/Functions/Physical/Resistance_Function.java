package Functions.Physical;

import Functions.Function;

public class Resistance_Function extends Function {

    public Resistance_Function(double start, double end) {
        this.start = start;
        this.shift = start;
        this.end = end;
        super.start = start;
        super.end = end;
    }

    @Override
    public double calculate(double x) {
        return (1+(x-start)*5)*5;
    }

    @Override
    public Function get_derivative() {
        return null;
    }
}
