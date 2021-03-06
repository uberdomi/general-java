package Functions;

public class Constant extends Function{
    private final double c;
    public Constant(double c){
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
        StringBuilder s = new StringBuilder();
        if(infinitesimal(c)){
            s.append((int) c);
        }
        else{
            s.append(c);
        }
        return s.toString();
    }
    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Constant a){
        return c==a.getC();
    }
}
