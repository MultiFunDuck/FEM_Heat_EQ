package Functions.Polinomial;

import Functions.Function;

public class Constant_Function extends Function {

    double Const;

    public Constant_Function(double aConst, double start, double end) {
        this.start = start;
        this.shift = start;
        this.end = end;
        Const = aConst;
        super.start = start;
        super.end = end;
    }

    @Override
    public double calculate(double x) {
        return Const;
    }

    @Override
    public Function get_derivative() {
        return new Zero_Function();
    }
}
