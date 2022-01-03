package Functions.Basis;

import Functions.Function;

public class Cosine_Function extends Basis_Function {

    double amplitude;
    double length;

    public Cosine_Function(double start, double end, int frequency, double amplitude) {
        this.start = start;
        this.shift = start;
        this.end = end;
        this.num = frequency;
        this.amplitude = amplitude;
        this.length = end - start;
        type = Basis_Function_TYPE.COSINE;
        super.start = start;
        super.end = end;
    }

    @Override
    public double calculate(double x) {
        return amplitude * Math.cos(Math.PI* (num+1) *x/length);
    }

    @Override
    public Function get_derivative() {
        return new Sine_Function(start,end, num,amplitude* (num+1) *Math.PI/length);
    }
}
