package Functions.Basis;

import Functions.Function;

public class Sine_Function extends Basis_Function {


    double amplitude;
    double length;

    public Sine_Function(double start, double end, int num_of_basis_function, double amplitude) {
        this.start = start;
        this.shift = start;
        this.end = end;
        this.num = num_of_basis_function;
        this.amplitude = amplitude;
        this.length = end - start;
        type = Basis_Function_TYPE.SINE;
        super.start = start;
        super.end = end;
    }

    @Override
    public double calculate(double x) {
        return amplitude * Math.sin(Math.PI* (num+1) *x/(end - start));
    }

    @Override
    public Function get_derivative() {
        return new Cosine_Function(start,end, num,amplitude*Math.PI* (num+1) /length);
    }
}
