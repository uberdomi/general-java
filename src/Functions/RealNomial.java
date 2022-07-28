package Functions;

public class RealNomial extends Function{
    private final double pow;
    private final Function g;
    public RealNomial(double n){
        super();
        pow=n;
        g = new Identity();
    }
    public RealNomial(double n,Function g){
        super();
        pow=n;
        this.g = g;
    }

    public double getPow() {
        return pow;
    }

    public Function getG() {
        return g;
    }

    @Override
    public double value(double input) {
        return new Exp().value(pow*(new Ln().value(g.value(input))));
    }

    @Override
    public double value(Vector v) {
        return new Exp().value(pow*(new Ln().value(g.value(v))));
    }

    @Override
    public Function derivative() {
        if(g instanceof Constant){
            return new Constant(0);
        }
        if(infinitesimal(pow)){
            int a = (int) pow;
            switch (a){
                case 0 -> {
                    return new Constant(0);
                }
                case 1 -> {
                    return g.derivative();
                }
                default -> {
                    if(g instanceof Identity){
                        return (new ProductF(new Constant(a),new Nomial(a-1)).simplify());
                    }
                    else{
                        return (new ProductF((new ProductF(new Constant(a),new Nomial(a-1,g)).simplify()),g.derivative()).simplify());
                    }
                }
            }
        }
        else{
            return (new ProductF((new ProductF(new Constant(pow),new RealNomial(pow-1,g)).simplify()),g.derivative()).simplify());
        }

    }

    @Override
    public Function pderivative(int dim) {
        return (new ProductF((new ProductF(new Constant(pow),new RealNomial(pow-1,g)).simplify()),g.pderivative(dim)).simplify());
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        if(g instanceof SumF){
            s.append("(");
            s.append(g);
            s.append(")");
        }
        else{
            s.append(g);
        }
        s.append("^");
        if(infinitesimal(pow)){
            s.append((int) pow);
        }
        else{
            s.append(pow);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(new RealNomial(21));
        System.out.println(new RealNomial(0.5));
        System.out.println(new RealNomial(0.5).value(2));
    }
}
