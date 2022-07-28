package Functions;

import java.util.LinkedList;
import java.util.List;

public abstract class Function {
    //TODO
    //multi-dimensional - gradient, multi dim matrices, -> taylor polynom, total differentials
    //partial derivatives ?
    public static final double eps = 1e-12;
    public static final double dx = 1e-2;
    public static final double pi = 3.141592653588003;
    public static final int n = 17;
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

    public Function integral(){
        return new Integral(this);
    }
    public abstract double value(double input);
    public abstract double value(Vector v);
    public abstract Function derivative();
    public abstract Function pderivative(int dim);
    public Vector gradient(Vector v){
        int h=v.getInputs().length;
        if(h<=0){
            return new Vector(0);
        }
        double[] d = new double[h];
        for(int i=0; i<h; i++){
            d[i] = pderivative(i+1).value(v);
        }
        return new Vector(d);
    }
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
    public static Function taylorSeries(double x,Function f){
        double a=1;
        long fac=1;
        Function g;
        if(x<eps&&-x<eps){
            g = new Identity();
        }
        else{
            g = new Translation(-x); //SumF(new Identity(),new Constant(-x))
        }
        List<Function> l = new LinkedList<>();
        Function d = f;
        l.add(new Constant(f.value(x)));
        for(int i=1; i<n; i++){
            d = d.derivative();
            fac*=i;
            a=d.value(x)/fac;
            if(!(a<eps&&-a<eps)){
                l.add(new ProductF(a,new Nomial(i,g)));
            }
        }
        return (new SumF(l).simplify());
    }

    public static Function taylorSeries(double x){
        double a=1;
        Function g;
        if(x<eps&&-x<eps){
            g = new Identity();
        }
        else{
            g = new Translation(-x); //SumF(new Identity(),new Constant(-x))
        }
        List<Function> l = new LinkedList<>();
        l.add(new Constant(1));
        for(int i=1; i<n; i++){
            a/=i;
            /*
            switch (i%4){
                case 1 -> {
                    if(!(a<eps&&-a<eps)){
                        l.add(new ProductF(a,new Nomial(i,g)));
                    }
                }
                case 3 -> {
                    if(!(a<eps&&-a<eps)){
                        l.add(new ProductF(-a,new Nomial(i,g)));
                    }
                }
            }

            switch (i%3){
                case 0 -> {
                    //if(!(a<eps&&-a<eps)){
                        l.add(new ProductF(a,new Nomial(i,g)));
                    //}
                }
                case 3 -> {
                    //if(!(a<eps&&-a<eps)){
                        l.add(new ProductF(-a,new Nomial(i,g)));
                    //}
                }
            }
            */
             if(i%5==0){
                 l.add(new ProductF(a,new Nomial(i,g)));
             }

        }
        return (new SumF(l).simplify());
    }

    public static Function DTS(double x,Function f){
        double[][] derivs = new double[n][2*n+1];
        for(int i=0; i<=n; i++){
            derivs[0][n+i]=f.value(x+i*dx);
            derivs[0][n-i]=f.value(x-i*dx);
        }
        for(int i=1; i<n; i++){
            for(int j=0; j<=n-i; j++){
                derivs[i][n+j]=(derivs[i-1][n+j+1]-derivs[i-1][n+j-1])/(2*dx*i);
                derivs[i][n-j]=(derivs[i-1][n-j+1]-derivs[i-1][n-j-1])/(2*dx*i);
            }
        }
        List<Function> l = new LinkedList<>();
        Function g = new SumF(new Nomial(1), new Constant(-x));
        boolean zero = x<eps&&-x<eps;
        l.add(new Constant(derivs[0][n]));
        for(int i=1; i<n; i++){
            if(zero){
                l.add(new ProductF(derivs[i][n],new Nomial(i)));
            }
            else{
                l.add(new ProductF(derivs[i][n],new Nomial(i,g)));
            }
        }
        return new SumF(l).simplify();
    }

    public static Function DTS(double x,double[][] derivs){
        if(derivs==null||derivs.length!=n||derivs[0].length!=2*n+1){
            throw new IllegalArgumentException("wrong params");
        }
        for(int i=1; i<n; i++){
            for(int j=0; j<=n-i; j++){
                derivs[i][n+j]=(derivs[i-1][n+j+1]-derivs[i-1][n+j-1])/(2*dx*i);
                derivs[i][n-j]=(derivs[i-1][n-j+1]-derivs[i-1][n-j-1])/(2*dx*i);
            }
        }
        List<Function> l = new LinkedList<>();
        Function g = new SumF(new Nomial(1), new Constant(-x));
        boolean zero = x<eps&&-x<eps;
        l.add(new Constant(derivs[0][n]));
        for(int i=1; i<n; i++){
            if(zero){
                l.add(new ProductF(derivs[i][n],new Nomial(i)));
            }
            else{
                l.add(new ProductF(derivs[i][n],new Nomial(i,g)));
            }
        }
        return new SumF(l).simplify();
    }

    public static void main(String[] args) {
        System.out.println(taylorSeries(0));
        /*
        System.out.println(new Ln(new SumF(new Constant(1),new Identity())));
        System.out.println(taylorSeries(0,new Ln(new SumF(new Constant(1),new Identity()))));
        Function f = new ProductF(new Sinus(new Nomial(2)),new SumF(new Nomial(3), new ProductF(new Constant(-1),new Nomial(1))));
        System.out.println(f);
        //System.out.println(taylorSeries(0,f));
        f = new Exp(new Sinus());
        System.out.println(f);
        System.out.println(DTS(0,f));
        System.out.println(taylorSeries(0,f));
        //System.out.println(taylorSeries(0,f));
        f = new Sinus();
        System.out.println(f);
        System.out.println(DTS(0,f));
        System.out.println(taylorSeries(0,f));
        System.out.println("--------------");
        System.out.println(f.integral());
        f= new Exp();
        System.out.println(f);
        System.out.println(f.integral());
        f= new Exp(new ProductF(-1,new Nomial(2)));
        System.out.println(f);
        System.out.println(f.integral());
        f= new Ln(new SumF(new Constant(1), new Identity()));
        System.out.println(f);
        System.out.println(f.integral());

         */
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
