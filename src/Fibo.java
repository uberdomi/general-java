public class Fibo {
    private double q_n;
    private double d;

    public Fibo (){
        q_n = 1;
       // d=0;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public void calculate (int n){
        if(n>0){
           // d=q_n;
            q_n = 1 + 1/q_n;
           // System.out.println(q_n);
            calculate(n-1);
        }
        else{
            System.out.println(q_n);
        }
    }

    public void h91 (int n){
        double a = Math.PI;
        a /= 3;
        a *= 10000;
        h91c(a,n,1);
    }

    public void h91c (double a, int n, int i){
        if(i<n){
            System.out.println(i + " " + a);
            a *= Math.PI*Math.PI;
            a /= (3*3);
            a /= (2*i);
            a /= ((2*i)+1);
            h91c(a,n,i+1);
        }
        else{
            System.out.println(i + " " + a);
        }
    }

    public void exp (int n){
        double e = 1;
        for(int i=0; i<n; i++){
            e *= (n+1);
            e /= n;
        }
        System.out.println(e);
    }

    public double r(int n){
        double r3 = 3;
        double r = (r3-1)/(n-2);
       // r = (double) 1/n;
        return r;
    }

    public double k2 (int n){
        if(n>0){
            double k =k2(n-1) + (double) 1/(n*n);
            System.out.println(k);
            return k;
        }
        return 0;
    }

    public static void main(String[] args) {
        //new Fibo().calculate(30);
        //new Fibo().exp(100);
       // new Fibo().h91(5);
       // System.out.println(new Fibo().getD() + " ddd");
        System.out.println(new Fibo().r(5));
       // new Fibo().k2(100);
    }
}
