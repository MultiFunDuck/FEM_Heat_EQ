package Functions.Polinomial;

import Functions.Function;

public class Zero_Function extends Function {

    @Override
    public double calculate(double x) {
        return 0;
    }

    @Override
    public Function get_derivative() {
        System.out.println("Trying to get derivative from zero_function");
        return null;
    }
}
