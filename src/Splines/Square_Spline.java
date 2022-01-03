package Splines;

import Functions.Function;
import Functions.Polinomial.Square_Function;
import Primitives.Point;


import java.util.ArrayList;
import java.util.List;

public class Square_Spline extends Spline{

    public Square_Spline(double start, double end, List<Function> functions) {
        super(start, end, functions);
    }

    @Override
    public void init_functions(List<Point> points, double ... parameters) {
        this.start = points.get(0).x;
        this.end = points.get(points.size() - 1).x;

        functions = new ArrayList<>();

        Square_Function first_square = new Square_Function(parameters[0],points.get(0),points.get(1));

        functions.add(first_square);
        for(int i = 1; i < points.size() - 1; i++){
            double left_slope = functions.get(i - 1).get_derivative().get_value(points.get(i).x);
            functions.add(new Square_Function(left_slope, points.get(i), points.get(i + 1)));
        }
    }
}
