package Functions;

public class Exp extends Function{
    private Function g;

    public Exp(){
        super();
        g=new Identity();
    }
    public Exp(Function g){
        super();
        this.g=g;
    }
    public Exp(double x){
        super(x);
        g=new Identity();
    }
    public Exp(double x,Function g){
        super(x);
        this.g=g;
    }

    @Override
    public double value(double input) {
        double x = g.value(input);
        double output = 1;
        double y = 1;
        int i = 1;
        while(y>eps||-y>eps){
            y*=x;
            y/=i;
            output+=y;
            i++;
        }
        return prefactor*output;
    }

    @Override
    public Function derivative() {
        if(prefactor==0){
            return new Constant(0);
        }
        if(g instanceof Identity){
            return new Exp(prefactor);
        }
        return new ProductF(prefactor,g.derivative(),new Exp(g));
    }

    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();
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
        s.append("exp(");
        s.append(g);
        s.append(")");
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Exp().value(1));
    }
}
