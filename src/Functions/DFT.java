package Functions;

import java.util.LinkedList;
import java.util.List;

public class DFT {
    private static double eps = 1e-12;
    private static double pi = 3.141592653588003;
    private final int sampleSize;
    private final Function f;
    private final int[][] F;
    private final double[] cos,sin;

    public DFT(int n, Function f) {
        sampleSize = n;
        this.f = f;
        F = new int[n][n];
        cos = new double[n];
        sin = new double[n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                F[i][j] = (i*j)%n;
            }
            cos[i] = new Cosinus().value(2*pi*i/n);
            sin[i] = new Sinus().value(2*pi*i/n);
        }
    }

    public double[][] transform(double a, double b){
        double[] v = new double[sampleSize];
        double dx = (b - a)/(sampleSize-1);
        for(int i=0; i<sampleSize; i++){
            v[i] = f.value(b-dx*i);
            v[i] /= sampleSize;
        }
        double[][] ds = new double[sampleSize][2];
        for(int i=0; i<sampleSize; i++){
            for(int j=0; j<sampleSize; j++){
                ds[i][0] += cos[F[i][j]]*v[j];
                ds[i][1] += sin[F[i][j]]*v[j];
            }
        }

        return ds;
    }
    public double[][] asbs(double a, double b){
        double[][] ds = transform(a,b);
        double[][] asbs = new double[sampleSize][2];
        for(int i=0; i<sampleSize; i++){
            asbs[i][0] = 2*ds[i][0];
            asbs[i][1] = -2*ds[i][1];
        }
        asbs[0][0] /= 2;
        return asbs;
    }

    public static String tab(double[][] d){
        StringBuilder s = new StringBuilder();
        for(int i=0; i<d.length; i++){
            for (int j=0; j<d[0].length; j++){
                s.append(d[i][j]);
                s.append("|");
            }
            s.append(" ");
            s.append(i);
            s.append("\n");
        }
        return s.toString();
    }

    public static boolean infinitesimal(double x){
        int y = (int) x;
        return (x - y < eps) && (y - x < eps);
    }

    public static String toPlot(double[][] d,double a, double b){
        double phi = (2*pi)/(b-a);
        if(d[0].length!=2){
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(int i=0; i<d.length; i++){
            if(d[i][0]>0){
                s.append("+");
            }
            s.append(d[i][0]);
            s.append("cos(");
            if(infinitesimal(phi*i)){
                s.append((int)(phi*i));
            }
            else{
                s.append(i*phi);
            }
            s.append("*x+(");
            if(infinitesimal(-a*phi*i)){
                s.append((int)-a*phi*i);
            }
            else{
                s.append(-a*phi*i);
            }
            s.append("))");
            if(d[i][1]>=0){
                s.append("+");
            }
            s.append(d[i][1]);
            s.append("sin(");
            if(infinitesimal(phi*i)){
                s.append((int)(phi*i));
            }
            else{
                s.append(i*phi);
            }
            s.append("*x+(");
            if(infinitesimal(-a*phi*i)){
                s.append((int)-a*phi*i);
            }
            else{
                s.append(-a*phi*i);
            }
            s.append("))");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        /*
        List<Function> l = new LinkedList<>();
        l.add(new Constant(-6));
        l.add(new Nomial(11,1));
        l.add(new Nomial(-6,2));
        l.add(new Nomial(3));
        SumF f = new SumF(l);
        System.out.println(f);
        DFT dft = new DFT(51,f);
        double[][] d = dft.asbs(0,2*pi);
        System.out.println(toPlot(d,0,2*pi));
        d = dft.asbs(0,pi);
        System.out.println(toPlot(d,0,pi));
        l = new LinkedList<>();
        l.add(new Exp());
        l.add(new Nomial(-3,2));
        l.add(new Sinus(new Nomial(2)));
        f= new SumF(0.1,l);
        System.out.println(f);
        dft = new DFT(51,f);
        //d = dft.asbs(0,2*pi);
        //System.out.println(toPlot(d,0,2*pi));
        d = dft.asbs(-4,5);
        System.out.println(toPlot(d,-4,5));

         */
        DFT dft = new DFT(51,new Exp());
        double[][] d = dft.asbs(-2,2);
        System.out.println(toPlot(d,-2,2));
    }

}


