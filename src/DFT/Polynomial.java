package DFT;

public class Polynomial {
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

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i=coefficients.length-1; i>=0; i--){
            if(coefficients[i]!=0){
                if(coefficients[i]>0){
                    s.append("+");
                }
                s.append(coefficients[i]);
                s.append("x^");
                s.append(i);
            }
        }
        return s.toString();
    }

}
