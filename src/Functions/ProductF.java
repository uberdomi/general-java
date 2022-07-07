package Functions;

import java.util.LinkedList;
import java.util.List;

public class ProductF extends Function{
    private final Function f1,f2;

    public ProductF(Function f1,Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public double value(double input) {
        return f1.value(input)*f2.value(input);
    }

    @Override
    public Function derivative() {
        if(f1 instanceof Constant && f2 instanceof Constant){
            return new Constant(0);
        }
        if(f1 instanceof Constant){
            return (new ProductF(f1,f2.derivative()).simplify());
        }
        if(f2 instanceof Constant){
            return (new ProductF(f2,f1.derivative()).simplify());
        }
        return (new SumF(new ProductF(f1,f2.derivative()),new ProductF(f2,f1.derivative())).simplify());
    }

    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();

        double prefactor=1;
        boolean con=false;
        if(f1 instanceof Constant c){
            prefactor *= c.getC();
            con=true;
        }
        if(f2 instanceof Constant c){
            prefactor *= c.getC();
            con=true;
        }

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
        if(f1 instanceof SumF){
            s.append("(");
            s.append(f1);
            s.append(")");
        }
        else if(!(f1 instanceof Constant)){
            s.append(f1);
        }
        if(!con){
            s.append("*");
        }
        if(f2 instanceof SumF){
            s.append("(");
            s.append(f2);
            s.append(")");
        }
        else if(!(f2 instanceof Constant)){
            s.append(f2);
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(ProductF f){
        return (f1.equals(f.f1)&&f2.equals(f.f2))||(f1.equals(f.f2)&&f2.equals(f.f1));
    }

    public ProductF merge(ProductF f){
        Function a = new Identity();
        Function b = new Identity();
        boolean c1=false;
        boolean c2=false;
        double x1=0;
        double x2=0;
        if(f1 instanceof Constant c){
            a = f2;
            x1 = c.getC();
            c1=true;
        }
        if(f2 instanceof Constant c){
            a = f1;
            x1 = c.getC();
            c1=true;
        }
        if(f.f1 instanceof Constant c){
            b = f.f2;
            x2 = c.getC();
            c2=true;
        }
        if(f.f2 instanceof Constant c){
            b = f.f1;
            x2 = c.getC();
            c2=true;
        }
        if(c1&&c2&&a.equals(b)){
            return new ProductF(new Constant(x1+x2),a);
        }
        return null;
    }
    public Constant constProd(){
        if((f1 instanceof Constant c1)&&(f2 instanceof Constant c2)){
            return new Constant(c1.getC()*c2.getC());
        }
        return null;
    }

    public double extractConst(){
        double x=1;
        if(f1 instanceof Constant c){
            x*=c.getC();
        }
        if(f1 instanceof ProductF p){
            x*=p.extractConst();
        }
        if(f2 instanceof Constant c){
            x*=c.getC();
        }
        if(f2 instanceof ProductF p){
            x*=p.extractConst();
        }
        return x;
    }
    public Function extractFunction(){
        if((f1 instanceof Constant)&&(f2 instanceof Constant)){
            return new Constant(1);
        }
        if(f1 instanceof Constant){
            if(f2 instanceof ProductF p){
                return p.extractFunction();
            }
            else{
                return f2;
            }
        }
        if(f2 instanceof Constant){
            if(f1 instanceof ProductF p){
                return p.extractFunction();
            }
            else{
                return f1;
            }
        }
        Function g1,g2;
        if(f1 instanceof ProductF p){
            g1 = p.extractFunction();
        }
        else{
            g1=f1;
        }
        if(f2 instanceof ProductF p){
            g2 = p.extractFunction();
        }
        else{
            g2=f2;
        }
        if(g1 instanceof Constant){
            return g2;
        }
        if(g2 instanceof Constant){
            return g1;
        }
        return new ProductF(g1,g2);
    }
    public Function simplify(){
        double x=extractConst();
        Function f = extractFunction();
        if(x-1<eps&&1-x<eps){
            return f;
        }
        if(f instanceof Constant c){
            return new Constant(x*c.getC());
        }
        return new ProductF(new Constant(x),f);
    }
}
