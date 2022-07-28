package Functions;

public class Nomial extends Function{
    private final int pow;
    private final Function g;
    public Nomial(int n){
        super();
        pow=n;
        g = new Identity();
    }
    public Nomial(int n,Function g){
        super();
        pow=n;
        this.g = g;
    }

    public int getPow() {
        return pow;
    }

    public Function getG() {
        return g;
    }

    @Override
    public double value(double input) {
        return pow(g.value(input),pow);
    }

    @Override
    public double value(Vector v) {
        return pow(g.value(v),pow);
    }

    @Override
    public Function derivative() {
        if(g instanceof Constant){
            return new Constant(0);
        }
        switch (pow){
            case 0 -> {
                return new Constant(0);
            }
            case 1 -> {
                return g.derivative();
            }
            default -> {
                if(g instanceof Identity){
                    return (new ProductF(new Constant(pow),new Nomial(pow-1)).simplify());
                }
                else{
                    return (new ProductF((new ProductF(new Constant(pow),new Nomial(pow-1,g)).simplify()),g.derivative()).simplify());
                }
            }
        }
    }

    @Override
    public Function pderivative(int dim) {
        return (new ProductF((new ProductF(new Constant(pow),new Nomial(pow-1,g)).simplify()),g.pderivative(dim)).simplify());
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        /*
        if(prefactor-1>eps||1-prefactor>eps){ //not 1
            if(prefactor+1<eps&&-1-prefactor<eps){ //eq -1
                s.append("-");
            }
            else if(infinitesimal(prefactor)){
                s.append((int) prefactor);
            }
            else{
                s.append(prefactor);
            }
        }

         */

        if(g instanceof SumF){
            s.append("(");
            s.append(g);
            s.append(")");
        }
        else{
            s.append(g);
        }
        s.append("^");
        s.append(pow);
        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Nomial f){
        return pow==f.getPow()&&g.equals(f.g);
    }

    public static void main(String[] args) {
        System.out.println(new Nomial(21));
    }
}
