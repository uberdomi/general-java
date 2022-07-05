package Functions;

public class Nomial extends Function implements Comparable{
    private final int pow;
    private final Function g;
    public Nomial(double x, int n,Function g){
        super(x);
        pow=n;
        this.g = g;
    }
    public Nomial(double x, int n){
        super(x);
        pow=n;
        g = new Identity();
    }
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
        return prefactor*pow(g.value(input),pow);
    }

    @Override
    public Function derivative() {
        if(prefactor==0){
            return new Constant(0);
        }
        if(g instanceof Constant){
            return new Constant(0);
        }
        switch (pow){
            case 0 -> {
                return new Constant(0);
            }
            case 1 -> {
                return new Constant(prefactor);
            }
            default -> {
                if(g instanceof Identity){
                    return new Nomial(prefactor*(pow-1),pow-1);
                }
                else{
                    return new ProductF(prefactor,g.derivative(),new Nomial(pow-1,pow-1,g));
                }
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        if(!(prefactor-1<eps||1-prefactor<eps)){
            if(prefactor+1<eps||-1-prefactor<eps){
                s.append("-");
            }
            else if(infinitesimal(prefactor)){
                s.append((int) prefactor);
            }
            else{
                s.append(prefactor);
            }
        }

        if(g instanceof Identity){
            s.append("x");
        }
        else{
            s.append("(");
            s.append(g.toString());
            s.append(")");
        }
        s.append("^");
        s.append(pow);
        return s.toString();
    }



    @Override
    public int compareTo(Object o) {
        throw new IllegalArgumentException("illegal comparison");
    }
    public int compareTo(Nomial other){
        if(pow>other.getPow()){
            return 1;
        }
        if(pow<other.getPow()){
            return -1;
        }
        if(prefactor-other.getPrefactor()<eps&&other.getPrefactor()-prefactor<eps){
            return 0;
        }
        if(prefactor>other.getPrefactor()){
            return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Nomial(21,37));
    }
}
