package Splines;

import Functions.Function;
import Primitives.Point;


import java.util.ArrayList;
import java.util.List;

public abstract class Spline extends Function{
    double start, end;
    List<Function> functions;


    public Spline(double start, double end, List<Function> functions) {
        this.start = start;
        this.end = end;
        this.functions = functions;
        super.start = start;
        super.end = end;
    }

    public abstract void init_functions(List<Point> points, double ... parameters);


    @Override
    public double calculate(double x){
        for (Function function:functions
             ) {
            if((function.start <= x) && (x <= function.end)){
                return function.calculate(x);
            }
        }
        System.out.println("\nx is out of spline's range" + ", x = " + x + " ,start = "+ start+" ,end = "+end);
        return 0;
    }

    @Override
    public Function get_derivative() {
        List<Function> derivative_functions = new ArrayList<>();
        for(Function function : functions){
            derivative_functions.add(function.get_derivative());
        }
        return new Spline(start, end, derivative_functions){

            @Override
            public void init_functions(List<Point> points, double... parameters) {
                System.out.println("\nTrying to initialize functions for derivative spline, which already has functions");
            }
        };
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for(Function function:functions){
            stringBuffer.append(function.toString());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }
}
