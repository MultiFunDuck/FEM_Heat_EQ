package Integrators;

import Functions.Function;

public class Rectangle_Integrator extends Integrator{

    public Rectangle_Integrator(double lower_bound, double upper_bound, int num_of_separations) {
        super(lower_bound,upper_bound, num_of_separations);
    }

    @Override
    public double Integrate(Function function) {
        double step = (upper_bound - lower_bound)/num_of_separations;

        double sum = 0;
        for(int i = 0; i < num_of_separations; i++){
            sum += function.calculate(step * i);
        }

        return sum*step;
    }
}
