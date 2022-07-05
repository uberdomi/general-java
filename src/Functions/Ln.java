package Functions;

public class Ln extends Function{
    private Function g;

    public Ln(){
        super();
        g=new Identity();
    }
    public Ln(Function g){
        super();
        this.g=g;
    }
    public Ln(double x){
        super(x);
        g=new Identity();
    }
    public Ln(double x,Function g){
        super(x);
        this.g=g;
    }

    @Override
    public double value(double input) {
        if(input<=0){
            throw new IllegalArgumentException("negative log!");
        }
        double x = input > 1 ? input : 1/input; //x>=1
        double output = 0; //exp(output) == 1 <= input
        double y = 10;
        Exp e = new Exp();
        while(e.value(output)-x>eps||x-e.value(output)>eps){
            while(e.value(output)<x){
                output+=y;
            }
            output-=y;
            y/=10;
        }
        return input > 1 ? prefactor*output : -prefactor*output;
    }

    @Override
    public Function derivative() {
        if(prefactor==0){
            return new Constant(0);
        }
        if(g instanceof Identity){
            return new Nomial(prefactor,-1);
        }
        return new ProductF(prefactor,g.derivative(),new Nomial(-1,g));
    }

    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();
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
        s.append("ln(");
        s.append(g);
        s.append(")");
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Ln().value(2));
        System.out.println(new Exp().value(new Ln().value(2)));
        System.out.println(new Ln().value(new Exp().value(2)));
        System.out.println(new Ln().value(0.5));
        System.out.println(new Exp().value(new Ln().value(0.5)));
        System.out.println(new Ln().value(new Exp().value(0.5)));
        System.out.println(new Ln(new Exp()).derivative());
    }
}
