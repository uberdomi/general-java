import java.io.Console;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static LinkedList<Integer> lista = new LinkedList<>();
    public static boolean nul3(String s){
        var ss= Objects.requireNonNull(s);
        return ss.length()>2;
    }

    public Main(){

    }

    public static int div(int a, int b){
        int c=0;
        try{
            c = a/b;
            c = c==0? 2 : 1;
        }
        catch(ArithmeticException e){
            System.out.println("falsche Eingabe!");
        }
        finally {
            System.out.println(c);
        }
        return c;
    }

    public static long fac(int n){
        if(n<=1){
            return 1;
        }
        else {
            return n*fac(n-1);
        }
    }

    public static void eh (int x){
        for(int i=0; i<x; i++){
            if(i==6|i==9){
                continue;
            }
            System.out.println(i);
        }
    }

    public static int same(int[]a){
        if(a==null||a.length==0){
            throw new RuntimeException();
        }
        else{
            return sameHelp(a,1,1,1);
        }
    }

    public static int sameHelp(int[]a,int i, int max, int tmp){
        if(i==a.length){
            return max;
        }
        else{
            if(a[i]==a[i-1]){
                tmp++;
            }
            else{
                tmp=1;
            }
            if(tmp>max){
                max=tmp;
            }

            return sameHelp(a,i+1,max,tmp);
        }
    }

    public static boolean overload(){
        return !overload();
    }


    public static void main(String[] args) {
        System.out.println(2137%1);
        System.out.println(0%1);
        System.out.println(-3%6);
        System.out.println(-3%6);
    }
}
