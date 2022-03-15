public class Sample implements Runnable{
    private String name;
    private int k;
    public Sample (String s){
        name = s;
        k=0;
    }
    public Sample (String s, int i){
        name = s;
        k = i;
    }

    private void doSth(){
        for (int i=0; i<100; i++){
            System.out.println(name + " " + i);
        }

        System.out.println(name + " SCHLUSS");
    }
    private void sayHi(){
        for (int i=0; i<50; i++){
            System.out.println(name + " hi " + i);
        }
        notifyAll();
        System.out.println("half - waiting " + name);
        try {
            wait();
        } catch (Exception e) {
            System.out.println("ups!");;
        }
        for (int i=50; i<100; i++){
            System.out.println(name + " hi " + i);
        }
        System.out.println("continue " + name);
        System.out.println(name + " SCHLUSS");
    }
    @Override
    public void run(){
        sayHi();
    }

    public static void main(String[] args) {
       // new Thread(new Sample("A")).start();
       // new Thread(new Sample("B")).start();

        var a = new Thread(new Sample("a"));
        var b = new Thread(new Sample("b"));
        var c = new Thread(new Sample("c"));
        var d = new Thread(new Sample("d"));
        var e = new Thread(new Sample("e"));

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();


    }
}
