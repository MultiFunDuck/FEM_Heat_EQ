package Functions.Polinomial;

import Functions.Function;
import Primitives.Point;

public class Square_Function extends Function {

    double A,B,C;

    public Square_Function(double a, double b, double c, double start, double end) {
        A = a;
        B = b;
        C = c;
        this.shift = start;
        this.start = start;
        this.end = end;
        super.start = start;
        super.end = end;
    }

    public Square_Function(double left_slope, Point p1, Point p2){
        double arg = p2.x - p1.x;
        shift = p1.x;
        start = p1.x;
        end = p2.x;
        C = p1.y;
        B = left_slope;
        A = (p2.y - C - B*arg)/(arg*arg);
    }

    @Override
    public double calculate(double x) {
        double arg = x - shift;
        return A*arg*arg + B*arg + C;
    }

    @Override
    public Function get_derivative() {
        return new Line_Function(2*A, B, start,end);
    }
}
