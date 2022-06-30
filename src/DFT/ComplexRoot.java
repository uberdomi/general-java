package DFT;

public class ComplexRoot {
    private final int order;
    private final int power;
    private final double[] cosISin;

    public ComplexRoot(int n){
        order = n;
        power = 1;
        cosISin = new double[] {1,0};
    }

    public ComplexRoot(int n, int power){
        order = n;
        this.power = power;
        //System.out.println(power + " to power: " + (2*SinusCosinus.pi*(power%n)/n));
        cosISin = SinusCosinus.cosSin(2*SinusCosinus.pi*(power%n)/n);
    }

    public double[] getCosISin() {
        return cosISin;
    }
    @Override
    public String toString(){
        if(cosISin[1]>0){
            return cosISin[0] + "+i" + cosISin[1];
        }
        return cosISin[0] + "-i" + -cosISin[1];
    }
}
