package Functions;

public class Sinus extends Function{
    private Function g;

    public Sinus(){
        super();
        g=new Identity();
    }
    public Sinus(Function g){
        super();
        this.g=g;
    }

    @Override
    public double value(double input) {
        double x = g.value(input);
        double output = 0;
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
                case 1 ->{
                    output+=y;
                }
                case 3 ->{
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
            return new Cosinus();
        }
        return (new ProductF(new Cosinus(g),g.derivative()).simplify());
    }

    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();
        s.append("sin(");
        s.append(g);
        s.append(")");
        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(Sinus f){
        return g.equals(f.g);
    }

    public static void main(String[] args) {
        System.out.println(new Sinus().value(0.78539));
        System.out.println((new Sinus().value(0.78539))*(new Sinus().value(0.78539)));
        System.out.println(new Sinus(new Cosinus()));
        System.out.println(new Sinus(new Cosinus()).value(pi/2));
        System.out.println(new Cosinus(new Cosinus()).value(pi/2));
        System.out.println(new Cosinus(new Sinus()).value(pi));
    }
}
