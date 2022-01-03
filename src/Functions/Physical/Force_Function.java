package Functions.Physical;

import Functions.Function;

public class Force_Function extends Function {

    public Force_Function(double start, double end) {
        this.start = start;
        this.shift = start;
        this.end = end;
        super.start = start;
        super.end = end;
    }

    @Override
    public double calculate(double x) {
        return Math.abs(x-(start+end)/2) < 0.05 ? 1: (x-start)*(end-x);
    }

    @Override
    public Function get_derivative() {
        return null;
    }
}
