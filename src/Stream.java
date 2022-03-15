import java.io.File;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Stream {
    private static List<Integer> list = new LinkedList<>();
    private static Integer[] array = {};

    public Stream(){
        list = new LinkedList<>();
    }

    public static void sout(){
        list = list.stream().map(i -> 2*i).filter(i -> i%4!=0).collect(Collectors.toList());
        String t = Arrays.stream(array)
                .map(i -> i+10)
                .filter(i -> i%3 != 0)
                .map(i -> i.toString())
                .collect(Collectors.joining("/"));
        int j=1;
        for(Integer i : list){
            System.out.println( j++ + ") " + i);
        }
        System.out.println(t);
    }

    public static int sum(){
        return list.stream().map(i -> (i<0) ? -i : i).reduce(0,(i,j) -> i+j);
    }

    public static void sort(){
        BiFunction<Integer,Integer,Integer> f = (a,b) -> (a%5==b%5)? a-b : a%5 - b%5;
        //a.compareTo(b)
        //f.apply(a,b)
        list = list.stream().sorted((a,b) -> f.apply(a,b)).collect(Collectors.toList());
    }

    public static int max(){
        return list.stream().reduce(list.stream().findFirst().get(),(i,j) -> i>j ? i : j);
    }

    public static void doo(){
        list.stream().forEach(i -> {
            hi(i);
            System.out.println("aha " + i);
        });
        //list.stream().forEach(i -> System.out.println("hi " + i));
    }

    public static void hi(int i){
        System.out.println("hi " + i);
    }

    public static void print(Integer... ints) {
        String msg = Arrays.stream(ints).map(i -> i.toString()).collect(Collectors.joining(", "));
        System.out.println(msg);
    }

    public static void mod3(){
        System.out.println(list.stream()
                .filter(i -> i%3 !=0)
                .map(i -> i%3 + "]-" + i)
                .map(s -> "[" + s)
                .collect(Collectors.joining(", ")));
    }

    public static void test(){
        list = list.stream().map(i->i*2).filter(i->i%3!=0).sorted((a,b)->b-a).collect(Collectors.toList());
        System.out.println(list);
        Arrays.stream(array).forEach(i->{
            System.out.print("hi ");
        });
        System.out.println(list.stream().reduce(1,(a,b)->a*b));
        System.out.println(list.stream().map(i->i.toString()).collect(Collectors.joining("-")));
    }

    public List<Integer> a(){
        return list.stream().map(i -> i * 2).filter(i -> i%4!=0).collect(Collectors.toList());
    }

    public static void update(Collection c){
        list.addAll(c);
    }
    public static void update(Integer[] i){
        array = i;
    }

    public static void show(){
        System.out.println(list.stream()
                .map(i -> i.toString())
                .reduce("",(i,j) -> i + ", " + j).substring(2));
        System.out.println(Arrays.stream(array)
                .map(i -> "" + i)
                .reduce("",(i,j) -> i + ", " + j).substring(2));
    }

    public static void main(String[] args) {


        List<Integer> x = new LinkedList();
        for(int  i=0;i<5;i++){
            x.add(i);
        }
        for(int  i=9;i<13;i++){
            x.add(i);
        }
        for(int  i=5;i<9;i++){
            x.add(i);
        }

        Integer[] y = {1,2,3,4,5,6,7,8,9,10};


        update(x);
        update(y);

        test();

        //show();
        //System.out.println();

        //sort();
        //show();

       // mod3();

    }

}
