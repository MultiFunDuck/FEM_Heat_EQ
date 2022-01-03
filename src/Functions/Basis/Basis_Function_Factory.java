package Functions.Basis;

public class Basis_Function_Factory {

    Basis_Function_TYPE type;
    private double start, end;



    public Basis_Function_Factory(Basis_Function_TYPE type, double start, double end){
        this.start = start;
        this.end = end;
        this.type = type;

    };

    public Basis_Function get_Basis_Function(int num){
        switch(this.type){
            case COSINE:
                return new Cosine_Function(start,end,num,Math.sqrt(2/(end - start)));
            case SINE:
                return new Sine_Function(start,end,num,Math.sqrt(2/(end - start)));
        }
        return null;
    }

}
