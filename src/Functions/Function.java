package Functions;

import java.util.LinkedList;
import java.util.List;

public abstract class Function {
    public static final double eps = 1e-12;
    public static final double dx = 1e-3;
    public static final double pi = 3.141592653588003;
    //protected double prefactor=1;

    /*
    public Function(double x){
        prefactor = x;
    }
    public Function(){
        prefactor = 1;
    }

    public double getPrefactor() {
        return prefactor;
    }

    public void setPrefactor(double prefactor) {
        this.prefactor = prefactor;
    }

     */

    public abstract double value(double input);
    public abstract Function derivative();
    public Function deriv(){
        return new Derivative(this);
    }
    public static double pow(double x,int n){
        double y=1;
        while(n!=0){
            if(n>0){
                y*=x;
                n--;
            }
            else{
                if(x==0){
                    throw new IllegalArgumentException("division by 0!");
                }
                y/=x;
                n++;
            }
        }
        return y;
    }
    public static boolean infinitesimal(double x){
        int y = (int) x;
        return (x - y < eps) && (y - x < eps);
    }
    public static SumF taylorSeries(double x,Function f){
        double a=1;
        long fac=1;
        Function g;
        if(x<eps&&-x<eps){
            g = new Identity();
        }
        else{
            g = new SumF(new Identity(),new Constant(-x));
        }
        List<Function> l = new LinkedList<>();
        Function d = f;
        l.add(new Constant(f.value(x)));
        for(int i=1; i<9; i++){
            d = d.derivative();
            fac*=i;
            a=d.value(x)/fac;
            if(!(a<eps&&-a<eps)){
                l.add(new ProductF(new Constant(a),new Nomial(i,g)));
            }
        }
        return new SumF(l);
    }

    public static void main(String[] args) {
        System.out.println(new Ln(new SumF(new Constant(1),new Identity())));
        System.out.println(taylorSeries(0,new Ln(new SumF(new Constant(1),new Identity()))));
        Function f = new ProductF(new Sinus(new Nomial(2)),new SumF(new Nomial(3), new ProductF(new Constant(-1),new Nomial(1))));
        System.out.println(f);
        System.out.println(taylorSeries(0,f));
        f = new Exp(new Sinus());
        System.out.println(f);
        System.out.println(taylorSeries(0,f));
        /*
        double x=0;
        //Function f = new Cosinus();
        Function f = new Sinus();
        Function g = new Sinus().deriv().deriv().deriv().deriv();
        for(int i=0; i<1000; i++){
            System.out.println(x + " " + (f.value(x)-g.value(x)));
            x+=dx;
        }

         */
    }
}
