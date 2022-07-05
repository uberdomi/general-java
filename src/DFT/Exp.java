package DFT;

public class Exp {
    private static final double eps = 1e-12;
    private double pow;
    public Exp(double x){
        pow=x;
    }

    public double getPow() {
        return pow;
    }

    public double value(double input){
        double output=0;
        double x=1;
        int i=1;
        while(x>eps||x<-eps){
            output+=x;
            x*=input;
            x/=i;
            i++;
        }
        return output;
    }
    public double value(){
        double output=0;
        double x=1;
        int i=1;
        while(x>eps||x<-eps){
            output+=x;
            x*=pow;
            x/=i;
            i++;
        }
        return output;
    }

    public static double exp(double input){
        double output=0;
        double x=1;
        int i=1;
        while(x>eps||x<-eps){
            output+=x;
            x*=input;
            x/=i;
            i++;
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(exp(-1));
        System.out.println(exp(1));
        System.out.println(exp(-1)*exp(1));
    }
}
