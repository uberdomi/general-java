package DFT;

public class Polynomial {
    private static final double eps = 1e-12;

    private final double[] coefficients;

    public Polynomial(double[] coefficients){
        this.coefficients = coefficients;
    }

    public double[] getCoefficients() {
        return coefficients;
    }


    public double value(double input){
        double output = 0;
        int h = coefficients.length;
        double x = 1;
        for(int i=0; i<h; i++){
            output += coefficients[i]*x;
            x *= input;
        }
        return output;
    }

    public Polynomial derivative(){
        if(coefficients==null){
            return null;
        }
        int h = coefficients.length;
        if(h==0||h==1){
            return new Polynomial(new double[] {0});
        }
        double[] derivs = new double[h-1];
        for(int i=0; i<h-1; i++){
            derivs[i] = (i+1)*coefficients[i+1];
        }
        return new Polynomial(derivs);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        boolean first=true;
        for(int i=coefficients.length-1; i>=0; i--){
            if(coefficients[i]!=0){
                if(coefficients[i]>0&&!first){
                    s.append("+");
                }
                first=false;
                if(infinitesimal(coefficients[i])){
                    if((int) coefficients[i]!=1){
                        s.append((int) coefficients[i]);
                    }
                }
                else{
                    s.append(coefficients[i]);
                }
                s.append("x^");
                s.append(i);
            }
        }
        return s.toString();
    }

    private boolean infinitesimal(double x){
        int y = (int) x;
        return (x - y < eps) && (y - x < eps);
    }
    public static void main(String[] args) {
        System.out.println(new Polynomial(new double[] {2,1,3,7}));
        System.out.println(new Polynomial(new double[] {2,1,3,7}).derivative());
        System.out.println((int) 2.0);
        System.out.println((int) 2.5);
        System.out.println((int) -2.0);
        System.out.println((int) -2.5);
    }
}
