package DFT;

public class DFT {
    private final int sampleSize;
    private final Polynomial f;
    private final int[][] F;
    private final ComplexRoot[] qs;

    public DFT(int n,Polynomial f) {
        sampleSize = n;
        this.f = f;
        F = new int[n][n];
        qs = new ComplexRoot[n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                F[i][j] = (i*j)%n;
            }
            qs[i] = new ComplexRoot(n,i);
        }
    }

    public double[][] transform(double a, double b){
        double[] v = new double[sampleSize];
        double dx = (b - a)/(sampleSize-1);
        for(int i=0; i<sampleSize; i++){
            v[i] = f.value(b-dx*i);
            //System.out.println("f(" + (a+dx*i) + ") = " + v[i]);
            v[i] /= sampleSize;
        }
        double[][] ds = new double[sampleSize][2];
        for(int i=0; i<sampleSize; i++){
            for(int j=0; j<sampleSize; j++){
                ds[i][0] += qs[F[i][j]].getCosISin()[0]*v[j];
                ds[i][1] += qs[F[i][j]].getCosISin()[1]*v[j];
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
    public static String toPlot(double[][] d){
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
            s.append(i);
            s.append("*x)");
            if(d[i][1]>=0){
                s.append("+");
            }
            s.append(d[i][1]);
            s.append("sin(");
            s.append(i);
            s.append("*x)");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Polynomial f = new Polynomial(new double[] {-6,11,-6,1});
        System.out.println(f);
        DFT dft = new DFT(51,f);
        double[][] d = dft.asbs(0,2*SinusCosinus.pi);
        System.out.println(toPlot(d));
    }
}
