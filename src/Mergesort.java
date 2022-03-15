import java.util.Arrays;

public class Mergesort {
    public static int swap=0;

    public static int[] mergeSort(int[] a){
        swap=0;
        StringBuffer sb = new StringBuffer("[");

        if(a==null||a.length==0){
            throw new IllegalArgumentException();
        }
        if(a.length==1){
            sb.append(a[0] + "]");
            System.out.println(sb);
            return a;
        }
        if(a.length==2){
            int x = a[0];
            int y = a[1];
            if(y<x){
                sb.append(a[1] + ", " + a[0] + "]");
                System.out.println(sb);
                swap++;
                return new int[] {y,x};
            }
            else{
                sb.append(a[0] + ", " + a[1] + "]");
                System.out.println(sb);
                return a;
            }
        }

        int[] a1 = Arrays.copyOfRange(a,0,a.length/2);
        int[] a2 = Arrays.copyOfRange(a,a.length/2,a.length);

        a1 = mergeSort(a1);
        a2 = mergeSort(a2);

        int i1=0;
        int i2=0;

        for(int i=0; i<a.length; i++){
            if(i1<a1.length&&i2<a2.length){
                if(a1[i1]<a2[i2]){
                    a[i] = a1[i1++];
                }
                else{
                    a[i] = a2[i2++];
                }
            }
            else{
                if(i1<a1.length){
                    a[i] = a1[i1++];
                }
                else{
                    a[i] = a2[i2++];
                }
            }
            swap++;
            sb.append(a[i] + ", ");
        }

        sb.append("]");
        System.out.println(sb);

        return a;
    }

    public static int[] mergeReverse(int[] a){
        swap=0;
        //StringBuffer sb = new StringBuffer("[");

        if(a==null||a.length==0){
            throw new IllegalArgumentException();
        }
        if(a.length==1){
            //sb.append(a[0] + "]");
            //System.out.println(sb);
            return a;
        }
        if(a.length==2){
            int x = a[0];
            int y = a[1];
            if(y>x){
                //sb.append(a[1] + ", " + a[0] + "]");
                //System.out.println(sb);
                swap++;
                return new int[] {y,x};
            }
            else{
                //sb.append(a[0] + ", " + a[1] + "]");
                //System.out.println(sb);
                return a;
            }
        }

        int[] a1 = Arrays.copyOfRange(a,0,a.length/2);
        int[] a2 = Arrays.copyOfRange(a,a.length/2,a.length);

        a1 = mergeReverse(a1);
        a2 = mergeReverse(a2);

        int i1=0;
        int i2=0;

        for(int i=0; i<a.length; i++){
            if(i1<a1.length&&i2<a2.length){
                if(a1[i1]>a2[i2]){
                    a[i] = a1[i1++];
                }
                else{
                    a[i] = a2[i2++];
                }
            }
            else{
                if(i1<a1.length){
                    a[i] = a1[i1++];
                }
                else{
                    a[i] = a2[i2++];
                }
            }
            swap++;
            //sb.append(a[i] + ", ");
        }

        //sb.append("]");
        //System.out.println(sb);

        return a;
    }

    public static int[] bubbleSort(int[] a){
        swap=0;
        StringBuffer sb = new StringBuffer("[");
        if(a==null||a.length==0){
            throw new IllegalArgumentException();
        }
        if(a.length==1){
            sb.append(a[0] + "]");
            System.out.println(sb);
            return a;
        }
        boolean sorted = false;
        while(!sorted){
            sorted=true;
            sb = new StringBuffer("[");

            for(int i=0; i<a.length-1;i++){
                if(a[i]>a[i+1]){
                    int tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;
                    sorted = false;
                    swap++;
                    sb.append(a[i] + "<->, ");
                }
                else{
                    sb.append(a[i] + ", ");
                }

            }
            sb.append(a[a.length-1] +"]");
            System.out.println(sb);
        }
        return a;
    }

    public static int[] bubbleReverse(int[] a){
        swap=0;
        //StringBuffer sb = new StringBuffer("[");
        if(a==null||a.length==0){
            throw new IllegalArgumentException();
        }
        if(a.length==1){
            //sb.append(a[0] + "]");
            //System.out.println(sb);
            return a;
        }
        boolean sorted = false;
        while(!sorted){
            sorted=true;
            //sb = new StringBuffer("[");

            for(int i=0; i<a.length-1;i++){
                if(a[i]<a[i+1]){
                    int tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;
                    sorted = false;
                    swap++;
                    //sb.append(a[i] + "<->, ");
                }
                else{
                    //sb.append(a[i] + ", ");
                }

            }
            //sb.append(a[a.length-1] +"]");
            //System.out.println(sb);
        }
        return a;
    }

    public static void main(String[] args) {
        int[]c = new int[10000];
        for(int i=0; i<10000; i++){
            c[i] = i;
        }

        /*
        int[]a = mergeSort(new int[] {2,1,314,111,3,7,99,121,169,42,69,110,77,33,256, 692,691,69314,69111,693,697,6999,69121,69169,6942,6969,69110,6977,6933,69256,69122,6931,6956,6975,6936,6912,6918,6921,6937,6923,6917,6927,6913,6988,6986,69144,  122,31,56,75,36,12,18,21,37,23,17,27,13,88,86,144});
        System.out.println("#times " + swap);
        System.out.println("---------------------------------------");
        int[]b = bubbleSort(new int[] {2,1,314,111,3,7,99,121,169,42,69,110,77,33,256, 692,691,69314,69111,693,697,6999,69121,69169,6942,6969,69110,6977,6933,69256,69122,6931,6956,6975,6936,6912,6918,6921,6937,6923,6917,6927,6913,6988,6986,69144,  122,31,56,75,36,12,18,21,37,23,17,27,13,88,86,144});
        System.out.println("#times " + swap);
        System.out.println("---------------------------------------");
        mergeReverse(a);
        System.out.println("#times " + swap);
        System.out.println("---------------------------------------");
        bubbleReverse(b);
        System.out.println("#times " + swap);
        System.out.println("---------------------------------------");

         */

        mergeReverse(c);
        System.out.println("#times " + swap);
        for(int i=0; i<10000; i++){
            c[i] = i;
        }
        bubbleReverse(c);
        System.out.println("#times " + swap);
    }
}
