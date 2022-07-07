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
        return output;
    }

    @Override
    public Function derivative() {
        if(g instanceof Identity){
            return new Exp();
        }
        return (new ProductF(new Exp(g),g.derivative()).simplify());
    }

    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();

        s.append("exp(");
        s.append(g);
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Exp f){
        return g.equals(f.g);
    }

    public static void main(String[] args) {
        System.out.println(new Exp().value(1));
    }
}
