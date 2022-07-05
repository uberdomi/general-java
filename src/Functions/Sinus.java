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
    public Sinus(double x){
        super(x);
        g=new Identity();
    }
    public Sinus(double x,Function g){
        super(x);
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
        return prefactor*output;
    }

    @Override
    public Function derivative() {
        if(prefactor==0){
            return new Constant(0);
        }
        if(g instanceof Identity){
            return new Cosinus(prefactor);
        }
        return new ProductF(prefactor,g.derivative(),new Cosinus(g));
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
        s.append("sin(");
        s.append(g);
        s.append(")");
        return s.toString();
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
