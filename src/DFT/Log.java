package DFT;

public class Log {
    private static final double eps = 1e-12;

    public double value(double input){
        if(input<0){
            throw new IllegalArgumentException("negative logarithm!");
        }
        double output=0;
        int sgn= input>1 ? 1 : -1;
        //flip input
        double x=10;
        while(Exp.exp(output)-input>eps||input-Exp.exp(output)>eps){
            while(input>sgn*Exp.exp(output)){}
        }
        return 0;
    }
}
