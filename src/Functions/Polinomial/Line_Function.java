package Functions.Polinomial;


import Functions.Function;
import Functions.Polinomial.Constant_Function;
import Primitives.Point;

public class Line_Function extends Function {

    public double A, B;

    public Line_Function(double a, double b, double start, double end) {
        A = a;
        B = b;
        this.start = start;
        this.shift = start;
        this.end = end;
        super.start = start;
        super.end = end;
    }

    public Line_Function(Point p1, Point p2){
        start = p1.x;
        end = p2.x;
        shift = p1.x;
        A = (p2.y - p1.y)/(p2.x - p1.x);
        B = p1.y;
    }

    @Override
    public double calculate(double x) {
        double arg = x - shift;
        return A*arg + B;
    }

    @Override
    public Function get_derivative() {
        return new Constant_Function(A, this.start, this.end);
        };

}
