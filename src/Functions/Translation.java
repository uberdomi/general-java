package Functions;

public class Translation extends Function{
    private final double a;
    public Translation(double a){
        this.a =a;
    }

    @Override
    public double value(double input) {
        return input+a;
    }

    @Override
    public double value(Vector v) {
        return 0;
    }

    @Override
    public Function derivative() {
        return new Constant(1);
    }

    @Override
    public Function pderivative(int dim) {
        return null;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("x ");
        if(a>-eps){
            s.append("+");
        }
        if(infinitesimal(a)){
            s.append((int) a);
        }
        else{
            s.append(a);
        }
        return s.toString();
    }
}
