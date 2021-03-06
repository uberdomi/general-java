package Arithmetics;

public class Fibo {
    private Rational a,b;
    private final Arithmetic lambda1, lambda2;
    private final Rational f0, f1;
    //f_n+2 = a*f_n+1 + b*f_n

    public Fibo (long a, long b, long f0, long f1){
        this.a = new Rational(a);
        this.b = new Rational(b);
        this.f0 = new Rational(f0);
        this.f1 = new Rational(f1);
        var l1 = new Arithmetic(a);
        var l2 = new Arithmetic(a);
        Root delta = new Root(a*a+4*b);
        l1 = l1.add(delta);
        l2 = l2.sub(delta);
        l1 = l1.div(2);
        l2 = l2.div(2);
        lambda1 = l1;
        lambda2 = l2;

    }

    public Fibo (long a, long b, Rational f0, Rational f1){
        this.a = new Rational(a);
        this.b = new Rational(b);
        this.f0 = f0;
        this.f1 = f1;
        var l1 = new Arithmetic(a);
        var l2 = new Arithmetic(a);
        Root delta = new Root(a*a+4*b);
        l1 = l1.add(delta);
        l2 = l2.sub(delta);
        l1 = l1.div(2);
        l2 = l2.div(2);
        lambda1 = l1;
        lambda2 = l2;
    }

    public Fibo (Rational a, Rational b, Rational f0, Rational f1){
        this.a = a;
        this.b = b;
        this.f0 = f0;
        this.f1 = f1;
        var l1 = new Arithmetic(a);
        var l2 = new Arithmetic(a);
        Root delta = new Root(a.mul(a).add(b.mul(4)));
        l1 = l1.add(delta);
        l2 = l2.sub(delta);
        l1 = l1.div(2);
        l2 = l2.div(2);
        lambda1 = l1;
        lambda2 = l2;
    }

    public Rational fnRec (int n){
        if(n==0){
            return f0;
        }
        return fnRec(n,f0,f1);
    }

    public Rational fnRec (int n, Rational fb, Rational fa){
        if(n==1){
            return fa;
        }
        else{
            return fnRec(n-1,fa,fa.mul(a).add(fb.mul(b)));
        }
    }
    public Arithmetic fn (int n){
        Arithmetic l2l1 = lambda2.sub(lambda1);
        Arithmetic l1n = lambda1.powN(n);
        Arithmetic l2n = lambda2.powN(n);
        return new Arithmetic(f0).mul(l1n.mul(lambda2).sub(lambda1.mul(l2n))).add(new Arithmetic(f1).mul(l2n.sub(l1n))).div(l2l1);
    }

    public static void main(String[] args) {
        Fibo fib = new Fibo(1,1,0,1);
        int h=20;
        long startTime = System.nanoTime();
        for(int i=0; i<h; i++){
            System.out.println(fib.fnRec(i));
        }
        long endTime = System.nanoTime();
        long duration1 = (endTime - startTime);
        System.out.println("time: "+ duration1);
        System.out.println("-------");

        startTime = System.nanoTime();
        for(int i=0; i<h; i++){
            System.out.println(fib.fn(i));
        }
        endTime = System.nanoTime();
        long duration2 = (endTime - startTime);
        System.out.println("time: "+ duration2);
        System.out.println("ratio (rec/expl): " + (double)duration1/duration2);

        System.out.println("-------");

        fib = new Fibo(1,2,0,1);

        startTime = System.nanoTime();
        for(int i=0; i<h; i++){
            System.out.println(fib.fnRec(i));
        }
        endTime = System.nanoTime();
        duration1 = (endTime - startTime);
        System.out.println("time: "+ duration1);
        System.out.println("-------");

        startTime = System.nanoTime();
        for(int i=0; i<h; i++){
            System.out.println(fib.fn(i));
        }
        endTime = System.nanoTime();
        duration2 = (endTime - startTime);
        System.out.println("time: "+ duration2);
        System.out.println("ratio (rec/expl): " + (double)duration1/duration2);

    }
}
