import Functions.Basis.Basis_Function;
import Functions.Basis.Basis_Function_Factory;
import Functions.Function;
import Integrators.Integrator;
import Jama.Matrix;

import java.util.ArrayList;
import java.util.List;


public class FEM_Solver {

    public Operator o = new Operator();

    public Function conduct,resist,force;

    public Basis_Function_Factory factory;

    public Integrator integrator;

    public int dim_of_basis;

    public double start,end;

    public FEM_Solver(Function conduct, Function resist, Function force, Basis_Function_Factory factory, Integrator integrator, int dim_of_basis) {
        this.conduct = conduct;
        this.resist = resist;
        this.force = force;
        this.factory = factory;
        this.integrator = integrator;
        this.dim_of_basis = dim_of_basis;
        this.start = conduct.start;
        this.end = conduct.end;
    }

    public Function get_solution(){

        Matrix resolvent = get_resolving_FEM_matrix(integrator,
                conduct,
                resist,
                factory,
                dim_of_basis);

        Matrix left_side = get_FEM_left_side(integrator, force, factory, dim_of_basis);

        Matrix x = resolvent.solve(left_side);


        List<Basis_Function> basis = o.get_basis(factory, dim_of_basis);
        List<Double> coefs = o.Cast_2D_double_array_t0_2DList(x.transpose().getArray()).get(0);
        Function solution = o.Sum_Of_Function_Series(basis, coefs);

        return solution;
    }



    public Matrix get_resolving_FEM_matrix(Integrator integrator,
                                           Function conductive_function,
                                           Function resistance_function,
                                           Basis_Function_Factory factory, int dim_of_basis){

        return (conduction_part_of_resolving_Matrix(integrator,conductive_function,factory,dim_of_basis).plus(
                resistance_part_of_resolving_Matrix(integrator,resistance_function,factory,dim_of_basis)));

    }


    public Matrix conduction_part_of_resolving_Matrix(Integrator integrator,
                                                      Function conductive_function,
                                                      Basis_Function_Factory factory, int dim_of_basis){

        List<Basis_Function> basis = o.get_basis(factory,dim_of_basis);

        List<List<Double>> Double_Matrix = new ArrayList<>();

        for(int i = 0; i < basis.size(); i++){

            List<Double> buf = new ArrayList<>();

            for(int j = 0; j < basis.size(); j++){

                buf.add(o.Integrate_Functions_Product(integrator,
                        conductive_function,
                        basis.get(i).get_derivative(),
                        basis.get(j).get_derivative()));
            }

            Double_Matrix.add(buf);
        }

        return o.get_Matrix_from_2DList(Double_Matrix);
    }

    public Matrix resistance_part_of_resolving_Matrix(Integrator integrator,
                                                      Function resistance_function,
                                                      Basis_Function_Factory factory, int dim_of_basis){

        List<Basis_Function> basis = o.get_basis(factory,dim_of_basis);

        List<List<Double>> Double_Matrix = new ArrayList<>();

        for(int i = 0; i < basis.size(); i++){

            List<Double> buf = new ArrayList<>();

            for(int j = 0; j < basis.size(); j++){

                buf.add(o.Integrate_Functions_Product(integrator,
                        resistance_function,
                        basis.get(i),
                        basis.get(j)));
            }

            Double_Matrix.add(buf);
        }

        return o.get_Matrix_from_2DList(Double_Matrix);
    }


    public Matrix get_FEM_left_side(Integrator integrator,
                                    Function force_function,
                                    Basis_Function_Factory factory, int dim_of_basis){
        List<Double> left_side = new ArrayList<>();

        for(int i = 0; i < dim_of_basis; i++){
            left_side.add(o.Integrate_Functions_Product(integrator,force_function, factory.get_Basis_Function(i)));
        }

        return o.get_column_from_List(left_side);
    }




    public void setConduct(Function conduct) {
        this.conduct = conduct;
    }

    public void setResist(Function resist) {
        this.resist = resist;
    }

    public void setForce(Function force) {
        this.force = force;
    }
}
