package DFT;

public class SinusCosinus {
    private static final int n=30;
    public static double pi = 3.141592653588003;
    public static double[] cosSin(double input) {
        double sin = 0;
        double cos = 1;
        double x = 1;

        if(input > pi){
            int div = (int) (input/(2*pi));
            input -= 2*pi*div;
        }
        else if(input < -pi){
            int div = (int) (-input/(2*pi));
            input += 2*pi*div;
        }

        for (int j = 1; j <= n; j++) {
            x *= input;
            x /= j;

            switch (j % 4) {
                case 0 -> {
                    cos += x;
                }
                case 1 -> {
                    sin += x;
                }
                case 2 -> {
                    cos -= x;
                }
                case 3 -> {
                    sin -= x;
                }
            }
        }
        return new double[]{cos, sin};
    }

    public static double pi(){
        double pi = 1;
        double dx = 1;
        double eps = 1e-12;
        double cos = cosSin(pi)[0];
        while(cos>eps||cos<-eps){ //first root of cosine
            while(cosSin(pi)[0]>0){
                pi += dx;
            }
            pi -= dx;
            dx /= 10;
            cos = cosSin(pi)[0];
        }
        return 2*pi;
    }

    public static void main(String[] args) {
        double[] d = cosSin(0.78539);
        System.out.println("cosinus " + d[0] + ", sinus " + d[1]);
        System.out.println("Pi: " + pi());
        System.out.println(d[0]*d[0]+d[1]*d[1]);
        System.out.println("-----------");
        d = cosSin(4.569513309090912);
        System.out.println("cosinus " + d[0] + ", sinus " + d[1]);
        System.out.println(d[0]*d[0]+d[1]*d[1]);
        System.out.println("---------");
        d = cosSin(0.78539);
        System.out.println("cosinus " + d[0] + ", sinus " + d[1]);
        d = cosSin(0.78539+200*pi);
        System.out.println("cosinus " + d[0] + ", sinus " + d[1]);
    }
}
