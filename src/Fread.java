public class Fread{
    private int count;

    public Fread(int n){
        count = n;
    }

    public void sout (){
        var t1 = new thret(this,1);
        var t2 = new thret(this,2);
        var t3 = new thret(this,3);
        var t4 = new thret(this,4);
        var t5 = new thret(this,5);
        var t6 = new thret(this,6);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    public void inc(int num){
        System.out.println(num + " inc in");
        synchronized (this){
            int tmp = getCount();
            System.out.println(num + " read " + tmp);
            tmp = tmp + 1;
            setCount(tmp);
            System.out.println(num + " set " + tmp);
        }
        System.out.println(num + " inc out");
    }

    public void dec(int num){
        System.out.println(num + " dec in");
        synchronized (this){
            int tmp = getCount();
            System.out.println(num + " read " + tmp);
            tmp = tmp - 1;
            setCount(tmp);
            System.out.println(num + " set " + tmp);
        }
        System.out.println(num + " dec out");
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private class thret extends Thread{
        private Fread fred;
        private int num;

        public thret(Fread f, int num){
            this.num = num;
            fred = f;
        }

        @Override
        public void run(){
            if(num%2==0){
                for(int i=0; i<100; i++){
                    fred.inc(num);
                }
            }
            else{
                for(int i=0; i<100; i++){
                    fred.dec(num);
                }
            }

            System.out.println("-----------------"+ num + " finished-----------------");
        }
    }

    public static void main(String[] args) {
        var f = new Fread(0);
       // int ok = f.calc(1,2,5);
       // System.out.println(ok);
        f.sout();
    }
}
