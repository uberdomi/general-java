package Functions;

public class Constant extends Function{
    private final double c;
    public Constant(double c){
        super();
        this.c=c;
    }

    public double getC() {
        return c;
    }

    @Override
    public double value(double input) {
        return c;
    }

    @Override
    public Function derivative() {
        return new Constant(0);
    }

    @Override
    public String toString(){
        return "" + c;
    }
}
