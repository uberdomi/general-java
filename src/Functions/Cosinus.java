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
    public Cosinus(double x){
        super(x);
        g=new Identity();
    }
    public Cosinus(double x,Function g){
        super(x);
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
        return prefactor*output;
    }

    @Override
    public Function derivative() {
        if(prefactor==0){
            return new Constant(0);
        }
        if(g instanceof Identity){
            return new Sinus(-prefactor);
        }
        return new ProductF(-prefactor,g.derivative(),new Sinus(g));
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

        s.append("cos(");
        s.append(g);
        s.append(")");
        return s.toString();
    }
}
