package Functions.Physical;

import Functions.Function;

public class Conductive_Function extends Function {

    public Conductive_Function(double start, double end) {
        this.start = start;
        this.shift = start;
        this.end = end;
        super.start = start;
        super.end = end;

    }

    @Override
    public double calculate(double x) {
        return 1;
    }

    @Override
    public Function get_derivative() {
        return null;
    }
}
