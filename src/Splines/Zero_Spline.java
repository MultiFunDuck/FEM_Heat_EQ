package Splines;

import Functions.Function;
import Functions.Polinomial.Zero_Function;
import Primitives.Point;


import java.util.ArrayList;
import java.util.List;

public class Zero_Spline extends Spline{


    public Zero_Spline(double start, double end, List<Function> functions) {
        super(start, end, functions);
    }

    @Override
    public void init_functions(List<Point> points, double ... parameters) {
        this.start = points.get(0).x;
        this.end = points.get(points.size() - 1).x;

        functions = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++){
            functions.add(new Zero_Function());
        }
    }


    @Override
    public Function get_derivative() {
        return null;
    }
}
