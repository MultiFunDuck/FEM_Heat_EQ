import Functions.Basis.Basis_Function;
import Functions.Basis.Basis_Function_Factory;
import Functions.Basis.Basis_Function_TYPE;
import Functions.Function;
import Functions.Physical.Conductive_Function;
import Functions.Physical.Force_Function;
import Functions.Physical.Resistance_Function;
import Integrators.Integrator;
import Jama.Matrix;

import java.util.*;

public class Operator {


    public double Integrate_Functions_Product(Integrator integrator, Function ... functions){


        return integrator.Integrate(new Function() {

            @Override
            public double calculate(double x) {
                double value = 1;
                for(Function function:functions){
                    value *= function.calculate(x);
                }
                return value;
            }

            @Override
            public Function get_derivative() {
                return null;
            }

        });
    }

    public Function Sum_Of_Function_Series(List<Basis_Function> functions, List<Double> coefficients){

        return new Function() {


            @Override
            public double calculate(double x) {

                double value = 0;
                for(int i = 0; i < functions.size() - 1; i++){
                    value += coefficients.get(i) * functions.get(i).calculate(x);
                }
                return value;
            }

            @Override
            public Function get_derivative() {
                return new Function() {
                    @Override
                    public double calculate(double x) {
                        double value = 0;
                        for(int i = 0; i < functions.size() - 1; i++){
                            value += coefficients.get(i) * functions.get(i).get_derivative().calculate(x);
                        }
                        return value;
                    }

                    @Override
                    public Function get_derivative() {
                        return new Function() {
                            @Override
                            public double calculate(double x) {
                                double value = 0;
                                for(int i = 0; i < functions.size() - 1; i++){
                                    value += coefficients.get(i) * functions.get(i).get_derivative().get_derivative().calculate(x);
                                }
                                return value;
                            }

                            @Override
                            public Function get_derivative() {
                                return null;
                            }
                        };
                    }
                };
            }
        };

    }


    public List<Basis_Function> get_basis(Basis_Function_Factory factory, int dim_of_basis){

        List<Basis_Function> basis = new ArrayList<>();
        for(int i = 0; i < dim_of_basis; i++){
            basis.add(factory.get_Basis_Function(i));
        }
        return basis;
    }

    public Matrix get_Matrix_from_2DList(List<List<Double>> from){
        Matrix to_which = new Matrix(Cast_2DList_to_2D_double_array(from));
        return to_which;
    }

    public Matrix get_column_from_List(List<Double> from){
        double[] values = Cast_List_to_double_array(from);
        Matrix to_which = new Matrix(values, values.length);
        return to_which;
    }

    public List<List<Double>> Cast_2D_double_array_t0_2DList(double[][] from){
        List<List<Double>> to_which = new ArrayList<>();

        for(int i = 0; i < from.length; i++){
            to_which.add(Cast_double_array_to_List(from[i]));
        }
        return to_which;

    }

    public double[][] Cast_2DList_to_2D_double_array(List<List<Double>> from){

        double[][] to_which = new double[from.size()][from.get(0).size()];
        for(int i = 0; i < from.size(); i++){
            to_which[i] = Cast_List_to_double_array(from.get(i));
        }
        return to_which;
    }

    public double[] Cast_List_to_double_array(List<Double> from){
        double[] to_which = new double[from.size()];
        for(int i = 0; i < from.size(); i++){
            to_which[i] = from.get(i);
        }
        return to_which;
    }

    public List<Double> Cast_double_array_to_List(double[] from){

        List<Double> to_which = new ArrayList<>();
        for(int i = 0; i < from.length; i++){
            to_which.add(from[i]);
        }
        return to_which;
    }
}
