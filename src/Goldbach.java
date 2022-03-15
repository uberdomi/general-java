public class Goldbach {
    public static boolean Prim(int x)
    {
        for(int j=2; j*j<=x; j++){
            if(x%j==0){
                return false;
            }
        }
        return true;
    }

    public static int Goldbach (int y){
        int xx=0;
        int yy=y/2;
        if (yy%2==0)
        {
            xx=yy-1;
        }
        else {
            xx=yy;
        }
        for (int pol=xx; pol>2; pol=pol-2)
        {
            if (Prim(pol)&&Prim(y-pol))
            {
                return  pol;
            }
        }
        return 0;
    }

    public static int Goldbach1 (int ResultingSum){
        int PrimeComponent = 0;
        if(ResultingSum%4==0){
            PrimeComponent = ResultingSum/2 - 1;
        }
        else{
            PrimeComponent = ResultingSum/2;
        }

        while(!Prim(PrimeComponent)||!Prim(ResultingSum-PrimeComponent)){
            PrimeComponent -= 2;
        }
        return PrimeComponent;
    }

    public static void main(String[] args) {
        System.out.println(Goldbach(2000));
        System.out.println(Goldbach1(2000));
    }


}
