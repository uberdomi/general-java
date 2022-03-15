public class ThreadIncreaser extends Thread{
    private static int count;
    private static final Monitor m = new Monitor();
    private String name;

    public ThreadIncreaser(String s){
        name = s;
    }

    @Override
    public void run(){
        for(int i=0; i<1000; i++){
            try {
                m.start();
                int tmp = count;
                System.out.println(name + " read " + tmp);
                tmp++;
                count = tmp;
                System.out.println(name + " set " + tmp);
                m.end();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        var t1 = new ThreadIncreaser("1");
        var t2 = new ThreadIncreaser("2");
        var t3 = new ThreadIncreaser("3");
        t1.start();
        t2.start();
        t3.start();
    }
}
