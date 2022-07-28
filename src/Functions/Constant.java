package Functions;

public class Constant extends Function{
    private final double c;
    private final Vector v;
    public Constant(double c){
        this.c=c;
        v = new Vector(1);
    }
    public Constant(double c,Vector v){
        this.c=c;
        this.v = v;
    }
    public Constant(Vector v){
        this.c=1;
        this.v=v;
    }

    public double getC() {
        return c;
    }

    @Override
    public double value(double input) {
        return c;
    }

    @Override
    public double value(Vector v) {
        return c;
    }

    @Override
    public Function derivative() {
        return new Constant(0);
    }

    @Override
    public Function pderivative(int dim) {
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
        if(v.isOne()){
            return s.toString();
        }
        boolean first=true;
        s.append("(");
        for(double d : v.getInputs()){
            if(!first){
                s.append(",");
            }
            if(Function.infinitesimal(d)){
                s.append((int) d);
            }
            else{
                s.append(d);
            }
            first=false;
        }
        if(first){
            return "0";
        }

        s.append(")");
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
