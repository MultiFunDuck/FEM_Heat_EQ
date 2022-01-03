package Functions.Basis;

import Functions.Function;

public class Polynomial_Function extends Basis_Function{

    double amplitude;
    double length;

    public Polynomial_Function(double start, double end, int num_of_basis_function, double amplitude) {
        this.start = start;
        this.shift = start;
        this.end = end;
        this.num = num_of_basis_function;
        this.amplitude = amplitude;
        this.length = end - start;
        type = Basis_Function_TYPE.POLYNOMIAL;
        super.start = start;
        super.end = end;
    }

    @Override
    public double calculate(double x) {
        return (x-start)*(x-end);
    }

    @Override
    public Function get_derivative() {
        return null;
    }
}
