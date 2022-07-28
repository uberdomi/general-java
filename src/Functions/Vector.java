package Functions;

import java.util.Arrays;

public class Vector {
    private final double[] inputs;
    public Vector(double...ds){
        inputs = ds;
    }
    public boolean isOne(){
        return (inputs!=null)&&inputs.length==1&&(inputs[0]-1<Function.eps)&&(1-inputs[0]<Function.eps);
    }

    public double[] getInputs() {
        return inputs;
    }

    public Vector add(Vector w){
        int h1 = inputs.length;
        int h2 = w.inputs.length;
        int h = h1 > h2 ? h1 : h2;
        double[] d = new double[h];
        for(int i=0; i<h; i++){
            d[i] = (i<h1 ? inputs[i] : 0) + (i<h2 ? w.inputs[i] : 0);
        }
        return new Vector(d);
    }
    public Vector mul(double x){
        int h = inputs.length;
        double[] d = new double[h];
        for(int i=0; i<h; i++){
            d[i] = inputs[i]*x;
        }
        return new Vector(d);
    }

    public static Vector e_n(int n){
        if(n<=0){
            return new Vector(0);
        }
        double[] d= new double[n];
        d[n-1] = 1;
        return new Vector(d);
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        boolean first=true;
        s.append("(");
        for(double d : inputs){
            if(!first){
              s.append(",");
            }
            if(Function.infinitesimal(d)){
                s.append((int) d);
            }
            else{
                s.append(d);
            }
            first=false;
        }
        if(first){
            s.append("0");
        }

        s.append(")");
        return s.toString();
    }

    public static void main(String[] args) {
        Function f = new ProductF(new Sinus(new Identity(1)),new Cosinus(new Identity(2)));
        System.out.println(f);
        System.out.println(new Vector(Function.pi/4,Function.pi/4));
        System.out.println(f.value(new Vector(Function.pi/4,Function.pi/4)));
        System.out.println(f.pderivative(1));
        System.out.println(f.pderivative(2));
        System.out.println(f.gradient(new Vector(Function.pi/4,Function.pi/4)));
        f = new ProductF(new Sinus(new Identity(1)),new Exp(new Identity(2)));
        System.out.println(f.value(new Vector(1,0)));
        System.out.println(f.pderivative(1));
        System.out.println(f.pderivative(2));
        System.out.println(f.gradient(new Vector(1,0)));
    }
}
