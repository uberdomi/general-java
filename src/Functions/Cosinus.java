package Functions;

public class Cosinus extends Function{
    private Function g;

    public Cosinus(){
        super();
        g=new Identity();
    }
    public Cosinus(Function g){
        super();
        this.g=g;
    }

    @Override
    public double value(double input) {
        double x = g.value(input);
        double output = 1;
        double y = 1;
        if(x > pi){
            int div = (int) (x/(2*pi));
            x -= 2*pi*div;
        }
        else if(x < -pi){
            int div = (int) (-x/(2*pi));
            x += 2*pi*div;
        }
        int i=1;
        while(y>eps||-y>eps){
            y*=x;
            y/=i;
            switch (i%4){
                case 0 ->{
                    output+=y;
                }
                case 2 ->{
                    output-=y;
                }
            }
            i++;
        }
        return output;
    }

    @Override
    public Function derivative() {
        if(g instanceof Identity){
            return new ProductF(new Constant(-1), new Sinus());
        }
        return (new ProductF(new ProductF(new Constant(-1), new Sinus()),g.derivative()).simplify());
    }

    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();

        s.append("cos(");
        s.append(g);
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Cosinus f){
        return g.equals(f.g);
    }
}
