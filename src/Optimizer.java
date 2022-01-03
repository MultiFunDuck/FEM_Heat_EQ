import Functions.Function;
import Primitives.Point;
import Splines.Line_Spline;
import Splines.Spline;

import java.util.ArrayList;
import java.util.List;

public class Optimizer {

    FEM_Solver solver;
    Function true_solution;
    int num_of_points;
    double start,end;

    List<Point> optimizing_points;

    List<Function> conduct_evolution;
    List<Function> solution_evolution;

    public Optimizer(FEM_Solver solver, int num_of_points) {
        this.solver = solver;
        this.true_solution = solver.get_solution();
        this.num_of_points = num_of_points;
        conduct_evolution = new ArrayList<>();
        solution_evolution = new ArrayList<>();
        this.start = solver.start;
        this.end = solver.end;
    }



    public Function Optimization(){
        init_optimizing_points();

        double dx = 0.25;
        double[] values = new double[num_of_points];
        for(int i = 0; i < num_of_points; i++){
            values[i] = 1.0;
        }

        for(int i = 0; i < 500; i++){
            double[] gradient = get_Gradient(values,dx);

            double step = get_nice_step(values,gradient,dx,dx*dx*dx);

            for(int j = 0; j < num_of_points; j++){
                values[j] += -step * gradient[j];
            }

            //conduct_evolution.add(this.solver.conduct);
            //solution_evolution.add(get_approx_solution());
            set_values_on_points(values);
            System.out.println(Function_To_Optimize(values));
        }


        return get_approx_solution();
    }

    public double get_nice_step(double[] values, double[] gradient, double max_step, double esp){

        double left_bound = 0;
        double right_bound = max_step;
        double gold_ratio = 1.618;

        double[] buf_values = new double[values.length];
        for(int i = 0; i < values.length; i++){
            buf_values[i] = values[i];
        }

        do {
            double x1 = right_bound - (right_bound - left_bound)/gold_ratio;
            double x2 = left_bound + (right_bound  -left_bound)/gold_ratio;

            if(calc_with_step(values,gradient,x1) > calc_with_step(values,gradient,x2)){
                left_bound = x1;
            }
            else{
                right_bound = x2;
            }
        } while(Math.abs(right_bound - left_bound) > esp);

        return (right_bound + left_bound)/2;
    }


    public double calc_with_step(double[] values, double[] gradient, double step){
        double[] buf_values = new double[values.length];
        for(int i = 0; i < values.length; i++){
            buf_values[i] = values[i] - step*gradient[i];
        }

        return Function_To_Optimize(buf_values);
    }


    public double[] get_Gradient(double[] cur_values, double dx){
        double[] buf_values = new double[cur_values.length];
        for(int i = 0; i < cur_values.length; i++){
            buf_values[i] = cur_values[i];
        }

        double[] gradient = new double[cur_values.length];
        for(int i = 0; i < cur_values.length; i++){
            buf_values[i] += dx;
            double a = Function_To_Optimize(buf_values);
            double b = Function_To_Optimize(cur_values);
            gradient[i] = (a-b) /dx;
            buf_values[i] -= dx;
        }
        return gradient;
    }

    public double Function_To_Optimize(double[] values){
        set_values_on_points(values);
        Function approx_solution = get_approx_solution();

        return Solution_Differences(true_solution,approx_solution);
    }



    public void set_values_on_points(double[] values){

        for(int i = 0; i < optimizing_points.size(); i++){
            optimizing_points.get(i).y = values[i];
        }
    }

    public double Solution_Differences(Function true_solution, Function approx_solution){

        double dif = 0;
        double step = (end - start)/25;
        double alpha = 0.8;
        for(int i = 0; i <= 25; i++){
            true_solution.get_derivative();


            dif += Math.abs(true_solution.calculate(start + step * i) - approx_solution.calculate(start + step * i));
            dif += 2*Math.abs(true_solution.get_derivative().calculate(start + step*i) - approx_solution.get_derivative().calculate(start + step*i));

        }

        return dif;


    }

    public void init_optimizing_points(){
        double start = solver.conduct.start;
        double end = solver.conduct.end;

        optimizing_points = new ArrayList<>();
        for(int i = 0; i < num_of_points; i++){
            optimizing_points.add(new Point(start + i*(end - start)/(num_of_points-1), 7));
        }
    }

    public Function get_approx_solution(){

        double start = optimizing_points.get(0).x;
        double end = optimizing_points.get(optimizing_points.size() - 1).x;

        Spline approx_conduct = new Line_Spline(start,end,null);
        approx_conduct.init_functions(optimizing_points);

        solver.setConduct(approx_conduct);

        Function approx_solution = solver.get_solution();

        return approx_solution;
    }

}
