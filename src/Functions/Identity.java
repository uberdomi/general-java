package Functions;

public class Identity extends Function {
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

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Identity f){
        return true;
    }
}
