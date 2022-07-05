package Functions;

public abstract class Function {
    public static double eps = 1e-12;
    public static double pi = 3.141592653588003;
    protected double prefactor=1;

    public Function(double x){
        prefactor = x;
    }
    public Function(){
        prefactor = 1;
    }

    public double getPrefactor() {
        return prefactor;
    }

    public abstract double value(double input);
    public abstract Function derivative();
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
}
