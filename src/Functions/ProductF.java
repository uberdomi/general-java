package Functions;

import java.util.LinkedList;
import java.util.List;

public class ProductF extends Function{
    private final Function f1,f2;

    public ProductF(Function f1,Function f2) {
        super();
        this.f1 = f1;
        this.f2 = f2;
    }
    public ProductF(double x,Function f1,Function f2) {
        super(x);
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public double value(double input) {
        return prefactor*f1.value(input)*f2.value(input);
    }

    @Override
    public Function derivative() {
        List<Function> l = new LinkedList<>();
        l.add(new ProductF(f1,f2.derivative()));
        l.add((new ProductF(f2,f1.derivative())));
        return new SumF(prefactor,l);
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
        if(f1 instanceof SumF){
            s.append("(");
            s.append(f1);
            s.append(")");
        }
        else{
            s.append(f1);
        }
        s.append("*");
        if(f2 instanceof SumF){
            s.append("(");
            s.append(f2);
            s.append(")");
        }
        else{
            s.append(f1);
        }
        return s.toString();
    }
}
