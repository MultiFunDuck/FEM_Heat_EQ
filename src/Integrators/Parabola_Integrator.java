package Integrators;

import Functions.Function;

import java.security.UnresolvedPermission;

public class Parabola_Integrator extends Integrator{

    public Parabola_Integrator(double lower_bound, double upper_bound, int num_of_separations) {
        super(lower_bound,upper_bound, num_of_separations);
    }

    @Override
    public double Integrate(Function function) {


        double step = (upper_bound - lower_bound)/(2*num_of_separations);

        double sum = (function.calculate(lower_bound) + function.calculate(upper_bound));
        for(int i = 1; i < num_of_separations; i++){
            sum += 2*function.calculate(lower_bound + step * (2*i));
        }

        for(int i = 1; i <= num_of_separations; i++){
            sum += 4*function.calculate( lower_bound + step * (2*i - 1));
        }

        return sum*step/3;
    }

}
