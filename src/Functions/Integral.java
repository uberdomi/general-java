package Functions;

import java.util.LinkedList;
import java.util.List;

public class Integral extends Function{
    private final Function f;
    private final double x;
    private final Function taylor;

    public Integral(Function f){
        this.f = f;
        x=0;

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
        for(int i=0; i<n; i++){
            if(zero){
                l.add(new ProductF(derivs[i][n]/(i+1),new Nomial(i+1)));
            }
            else{
                l.add(new ProductF(derivs[i][n]/(i+1),new Nomial(i+1,g)));
            }
        }
        taylor = new SumF(l).simplify();
    }
    public Integral(double x,Function f){
        this.f = f;
        this.x = x;

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
        for(int i=0; i<n; i++){
            if(zero){
                l.add(new ProductF(derivs[i][n]/(i+1),new Nomial(i+1)));
            }
            else{
                l.add(new ProductF(derivs[i][n]/(i+1),new Nomial(i+1,g)));
            }
        }
        taylor = new SumF(l).simplify();
    }

    @Override
    public double value(double input) {
        return taylor.value(input);
    }

    @Override
    public double value(Vector v) {
        return taylor.value(v);
    }

    @Override
    public Function derivative() {
        return f;
    }

    @Override
    public Function pderivative(int dim) {
        return f;
    }

    @Override
    public String toString(){
        return taylor.toString();
    }
}
