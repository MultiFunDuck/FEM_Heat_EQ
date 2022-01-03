package Integrators;

import Functions.Function;

public class Trapezoid_Integrator extends Integrator{

    public Trapezoid_Integrator(double lower_bound, double upper_bound, int num_of_separations) {
        super(lower_bound,upper_bound, num_of_separations);
    }

    @Override
    public double Integrate(Function function) {
        double step = (upper_bound - lower_bound)/num_of_separations;

        double sum = (function.calculate(lower_bound) + function.calculate(upper_bound))/2;
        for(int i = 1; i < num_of_separations; i++){
            sum += function.calculate(lower_bound + step * i);
        }

        return sum*step;
    }
}
