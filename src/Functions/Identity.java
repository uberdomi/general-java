package Functions;

public class Identity extends Function {
    public Identity(){
        super();
    }
    @Override
    public double value(double input) {
        return input;
    }

    @Override
    public Function derivative() {
        return new Constant(1);
    }

    @Override
    public String toString(){
        return "x";
    }
}
