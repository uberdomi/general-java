package Functions;

import java.util.LinkedList;
import java.util.List;

public class DFTbis {
    private static double eps = 1e-12;
    private static double pi = 3.141592653588003;
    //private final static double sqrt2 = 1.4142135623730574;
    private final int n;
    private final Function f;
    //private final int[][] F;
    private final double[] cos,sin;

    public DFTbis(int sampleSize, Function f){
        n = sampleSize;
        this.f = f;
        cos = new double[n];
        sin = new double[n];
        for(int i=0; i<n; i++){
            cos[i] = new Cosinus().value(2*pi*i/n);
            sin[i] = new Sinus().value(2*pi*i/n);
        }
    }

    public String transform(double a, double b, int m){
        double[] samples = new double[n+1];
        double c0=0;
        for(int i=0; i<=n; i++){
            samples[i] = f.value(a+i*(b-a)/n);
            c0 += samples[i];
        }
        c0 /= n;
        //c0 *= sqrt2;
        double aa;
        double bb;
        double alphaSin;
        double alphaCos;
        double[] ak = new double[m];
        double[] bk = new double[m];
        for(int i=1; i<=m; i++){
            alphaSin = new Sinus().value((2*pi*a*i)/(b-a));
            alphaCos = new Cosinus().value((2*pi*a*i)/(b-a));
            aa=0;
            bb=0;
            for(int j=0; j<=n; j++){
                aa += cos[i*j%n]*alphaCos*samples[j];
                aa -= sin[i*j%n]*alphaSin*samples[j];
                bb += cos[i*j%n]*alphaSin*samples[j];
                bb += sin[i*j%n]*alphaCos*samples[j];
            }
            ak[i-1] = aa;
            bk[i-1] = bb;
            ak[i-1] *= 2;
            bk[i-1] *= 2;
            ak[i-1] /= n;
            bk[i-1] /= n;
        }

        StringBuilder s = new StringBuilder();

        if(Function.infinitesimal(c0)){
            s.append((int) c0);
        }
        else{
            s.append(c0);
        }

        if(b-a-2*pi>-eps&&b-a-2*pi<eps){
            for(int i=1; i<=m; i++){
                if(ak[i-1]<-eps||ak[i-1]>eps){
                    if(ak[i-1]>=0){
                        s.append("+");
                    }
                    if(Function.infinitesimal(ak[i-1])){
                        s.append((int) ak[i-1]);
                    }
                    else{
                        s.append(ak[i-1]);
                    }
                    s.append("*cos(");
                    s.append(i);
                    s.append("x)");
                }
                if(bk[i-1]<-eps||bk[i-1]>eps){
                    if(bk[i-1]>=0){
                        s.append("+");
                    }
                    if(Function.infinitesimal(bk[i-1])){
                        s.append((int) bk[i-1]);
                    }
                    else{
                        s.append(bk[i-1]);
                    }
                    s.append("*sin(");
                    s.append(i);
                    s.append("x)");
                }
            }
        }
        else{
            double phi = 2*pi/(b-a);
            for(int i=1; i<=m; i++){
                if(ak[i-1]<-eps||ak[i-1]>eps){
                    if(ak[i-1]>=0){
                        s.append("+");
                    }
                    if(Function.infinitesimal(ak[i-1])){
                        s.append((int) ak[i-1]);
                    }
                    else{
                        s.append(ak[i-1]);
                    }
                    s.append("*cos(");
                    s.append(i*phi);
                    s.append("x)");
                }
                if(bk[i-1]<-eps||bk[i-1]>eps){
                    if(bk[i-1]>=0){
                        s.append("+");
                    }
                    if(Function.infinitesimal(bk[i-1])){
                        s.append((int) bk[i-1]);
                    }
                    else{
                        s.append(bk[i-1]);
                    }
                    s.append("*sin(");
                    s.append(i*phi);
                    s.append("x)");
                }
            }
        }

        return s.toString();
    }

    public static void main(String[] args) {
        DFTbis dft = new DFTbis(51,new Exp());
        System.out.println(dft.transform(-2,2,12));
        dft = new DFTbis(51, new Sinus());
        System.out.println(dft.transform(-2,2,12));
        System.out.println(dft.transform(-2*pi,2*pi,12));
        Function f = new SumF(new Nomial(2),new ProductF(-2,new Nomial(3)),new ProductF(3,new Identity()));
        dft = new DFTbis(51, f);
        System.out.println(dft.transform(-1,1.5,12));
        f = new ProductF(new Translation(-1),new ProductF(new Translation(-1),new Translation(1)));
        dft = new DFTbis(51, f);
        System.out.println(dft.transform(-1,1,12));
        List<Function> l = new LinkedList<>();
        l.add(new Constant(-6));
        l.add(new ProductF(11,new Identity()));
        l.add(new ProductF(-6,new Nomial(2)));
        l.add(new Nomial(3));
        f = new SumF(l);
        System.out.println(f);
        dft = new DFTbis(51, f);
        System.out.println(dft.transform(0,2*pi,12));
        f = new ProductF(0.5,new SumF(new Exp(),new Exp(new ProductF(-1,new Identity()))));
        System.out.println(f);
        dft = new DFTbis(51, f);
        System.out.println(dft.transform(-2,2,20));
        f = new ProductF(0.5,new SumF(new Exp(),new ProductF(-1,new Exp(new ProductF(-1,new Identity())))));
        System.out.println(f);
        dft = new DFTbis(51, f);
        System.out.println(dft.transform(-2,2,20));
    }
}
