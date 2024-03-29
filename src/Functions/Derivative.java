package Functions;

public class Derivative extends Function{
    private Function g;
    public Derivative(Function g){
        super();
        this.g = g;
    }

    @Override
    public double value(double input) {
        return (g.value(input+dx)-g.value(input-dx))/(2*dx);
    }

    @Override
    public double value(Vector v) {
        return 0;
    }

    @Override
    public Function derivative() {
        return new Derivative(this);
    }

    @Override
    public Function pderivative(int dim) {
        return new Derivative(this);
    }

    @Override
    public String toString(){
        //return "(" + g + ")'";
        return g + "'";
    }
}
