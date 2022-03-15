import java.io.Console;

public class Wurzel {
    private static int a;
    private static int b;
    private static int c;

    public Wurzel(int a, int b,int c){
        Wurzel.a = a;
        Wurzel.b = b;
        Wurzel.c = c;

    }

public String Result(){
        int i = b*b - 4*a*c;
        if(i<0){
            i *= -1;
            return "Nullstellen: (" + -1*b + " +- " + "i*sqrt(" + i + "))/" + 2*a;
        }
        else{
            return "Nullstellen: (" + -1*b + " +- " + "sqrt(" + i + "))/" + 2*a;
        }

}

    public String exResult(){
        int i = b*b - 4*a*c;
        double d1;
        double d2;
        long x=4;
        long y=1;
        long xx;
        if(i<0){
            i *= -1;
            for(int j =0; j<4;j++){
                xx =x;
                x = x*x + i*y*y;
                y = 2*xx*y;
            }
            if(y==0){
                y=1;
            }
            d1= (-1)*b;
            d1 = d1/2/a;
            d2= x;
            d2 = d2/y/2/a;
            return "Nullstellen: " + d1 + " +- " + "i" + d2 ;
        }
        else{
            for(int j =0; j<4;j++){
                xx =x;
                x = x*x + i*y*y;
                y = 2*xx*y;
                System.out.println(x + " " + y);
            }
            if(y==0){
                y=1;
            }
            d1= (-1)*b;
            d1 = d1/2/a;
            d2= x;
            d2 = d2/y/2/a;
            double d3=d1+d2;
            double d4=d1-d2;
            return "Nullstellen: " + d3 + " , " + d4 ;
        }

    }

    public static double sqrt(int i){
        double d1 = 0;
        long x=4;
        long y=1;
        long xx;
        if(i<0){
            i = -i;
        }

            for(int j =0; j<3;j++){
                xx =x;
                x = xx*xx + i*y*y;
                y *= 2*xx;
                System.out.println(x + "/" + y);
            }
            if(y==0){
                y=1;
            }
            d1= x;
            d1 /= y;
        return d1;
    }

    public static void main(String[] args) {

        Wurzel w1 = new Wurzel(1,2,1);
        System.out.println(w1.Result());
        System.out.println(w1.exResult());

        Wurzel w2 = new Wurzel(1,0,3);
        System.out.println(w2.Result());
        System.out.println(w2.exResult());
        //System.out.println(Wurzel.sqrt(3));
        Wurzel w3 = new Wurzel(1,-1,-1);
        System.out.println(w3.Result());
        System.out.println(w3.exResult());
        System.out.println("-----------");
        System.out.println(sqrt(2)*sqrt(2));

    }
}
