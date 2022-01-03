package Splines;

import Functions.Polinomial.Constant_Function;
import Functions.Function;
import Primitives.Point;


import java.util.ArrayList;
import java.util.List;


public class Constant_Spline extends Spline{


    public Constant_Spline(double start, double end, List<Function> functions) {
        super(start, end, functions);
    }

    public void init_functions(List<Point> points, double ... parameters){
        this.start = points.get(0).x;
        this.end = points.get(points.size() - 1).x;

        List<Function> functions = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            functions.add(new Constant_Function(points.get(i).y, points.get(i).x, points.get(i + 1).x));
        }
    }
}
