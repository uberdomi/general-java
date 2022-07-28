package Functions;

public class Identity extends Function {
    private final int dim;
    public Identity(){
        dim=0;
    }
    public Identity(int dim){
        this.dim=dim;
    }
    @Override
    public double value(double input) {
        return input;
    }

    @Override
    public double value(Vector v) {
        if(dim==0){
            return 0;
        }
        if(dim<=v.getInputs().length){
            return v.getInputs()[dim-1];
        }
        return 0;
    }

    @Override
    public Function derivative() {
        return new Constant(1);
    }

    @Override
    public Function pderivative(int dim) {
        return dim == this.dim ? new Constant(1) : new Constant(0);
    }

    @Override
    public String toString(){
        return dim == 0 ? "x" : "x" + dim;
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Identity f){
        return f.dim==dim;
    }
}
