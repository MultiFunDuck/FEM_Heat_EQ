package Functions;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Function {

    public double start,end, shift;

    public double get_value(double x){
        if((start <= x) && (x <= end)){
            return calculate(x);
        }
        System.out.println("x = " + x + "is out of function bounds: start = " + start + ",end = " + end);
        return 0;
    }

    public XYDataset get_values_dataset(int num_of_separations){
        double[] data = new double[num_of_separations];
        double[] plots = new double[num_of_separations];

        for (int i = 0; i < num_of_separations; i++) {
            double x_cur = ((double) i) / (num_of_separations - 1);
            data[i] = this.calculate(x_cur);
            plots[i] = x_cur;
        }

        XYSeries dataset =  new XYSeries("Function_Data", false,true);
        for(int i = 0; i < data.length; i++){
            dataset.add(data[i],plots[i]);
        }
        XYDataset XYdataset = new XYSeriesCollection(dataset);
        return XYdataset;
    }


    public abstract double calculate(double x);

    public abstract Function get_derivative();



    @Override
    public String toString() {
        return "Function{" +
                "start=" + start +
                ", end=" + end +
                ", shift=" + shift +
                '}';
    }
}
