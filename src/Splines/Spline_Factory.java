package Splines;

public class Spline_Factory {

    double start, end, num_of_points;
    Spline_TYPE type;


    public Spline_Factory(double start, double end, double num_of_points, Spline_TYPE type) {
        this.start = start;
        this.end = end;
        this.num_of_points = num_of_points;
        this.type = type;
    }

    public Spline get_Spline(){
        switch (type){
            case ZERO:
                return new Zero_Spline(start,end,null);
            case CONSTANT:
                return new Constant_Spline(start,end,null);

            case LINE:
                return new Line_Spline(start,end,null);
            case SQUARE:
                return new Square_Spline(start,end,null);

        }
        return null;
    }
}
