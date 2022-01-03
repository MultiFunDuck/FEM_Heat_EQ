
import Functions.Physical.*;
import Functions.Basis.*;
import Functions.Function;
import Integrators.Integrator;
import Integrators.Parabola_Integrator;
import Integrators.Rectangle_Integrator;
import Integrators.Trapezoid_Integrator;
import Primitives.Point;
import Splines.Spline;
import Splines.Square_Spline;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String []args) {

        Basis_Function_TYPE basis_type = Basis_Function_TYPE.SINE;
        int num_of_integration_separations = 80;
        double x_start = 0;
        double x_end = 1;
        int dim_of_basis = 30;

        int window_width = 1200;
        int window_length = 840;
        int ox_seps_num = 1000;



        Integrator integrator = new Parabola_Integrator(x_start,x_end,num_of_integration_separations);

        Function conduct = new Conductive_Function(x_start, x_end);
        Function resist = new Resistance_Function(x_start,x_end);
        Function force = new Force_Function(x_start,x_end);

        Basis_Function_Factory factory = new Basis_Function_Factory(basis_type,x_start,x_end);

        FEM_Solver solver = new FEM_Solver(conduct,resist,force,factory,integrator,dim_of_basis);


        Function solution  = solver.get_solution();



        Line_Graph_Drawer drawer = new Line_Graph_Drawer(window_width,window_length);

        drawer.draw_function_graph(force.get_values_dataset(ox_seps_num), "force graph");

        drawer.draw_function_graph(resist.get_values_dataset(ox_seps_num),"resist graph");

        drawer.draw_function_graph(conduct.get_values_dataset(ox_seps_num),"conduct");

        drawer.draw_function_graph(solution.get_values_dataset(ox_seps_num),"solution graph");




      Optimizer optimizer = new Optimizer(solver,20);
      Function approx_solution = optimizer.Optimization();

       drawer.draw_function_graph(optimizer.solver.conduct.get_values_dataset(ox_seps_num),"approx conduct");
       drawer.draw_function_graph(approx_solution.get_values_dataset(ox_seps_num),"approx solution");

    }
}
