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

    @Override
    public double value(double input) {
        double x = g.value(input) > 1 ? g.value(input) : 1/g.value(input); //x>=1
        if(x<=0){
            throw new IllegalArgumentException("negative log!");
        }
        double output = 0; //exp(output) == 1 <= g(input)
        double y = 10;
        Exp e = new Exp();
        while(e.value(output)-x>eps||x-e.value(output)>eps){
            while(e.value(output)<x){
                output+=y;
            }
            output-=y;
            y/=10;
        }
        return g.value(input) > 1 ? output : -output;
    }

    @Override
    public Function derivative() {
        if(g instanceof Identity){
            return new Nomial(-1);
        }
        return (new ProductF(new Nomial(-1,g),g.derivative()).simplify());
    }

    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();

        s.append("ln(");
        s.append(g);
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Ln f){
        return g.equals(f.g);
    }

    public static void main(String[] args) {

        System.out.println(new Ln().value(2));
        System.out.println(new Exp().value(new Ln().value(2)));
        System.out.println(new Ln().value(new Exp().value(2)));
        System.out.println(new Ln().value(0.5));
        System.out.println(new Exp().value(new Ln().value(0.5)));
        System.out.println(new Ln().value(new Exp().value(0.5)));


        //System.out.println(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp()))))))).value(1));
        System.out.println(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp()))))))).derivative());
        System.out.println(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp()))))))).derivative().value(3));
        System.out.println(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp()))))))).derivative().derivative());
        System.out.println(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp(new Ln(new Exp()))))))).derivative().derivative().value(4));
    }
}
