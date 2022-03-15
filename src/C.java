public class C<T extends X> extends B {
    T t;

    public void f(A a) {
        System.out.println("C.f(A)");
        t.f(this);
    }
    public void h(A a) {
        System.out.println("C.h(A)");
    }


    /*public void f(B b) {
        System.out.println("C.f(B)");
        t.f(this);
    }*/
    public static void g(X x) {
        System.out.println("C.g(X)");
    }

    public static void main(String[] args) {
        A a = new A();
        A ab = new B();
        B b = new B();
        C<A> c = new C<A>();
        c.t = a;
        A ac = new C<B>();

        c.f(b);
        System.out.println();
        b.f(c);
        System.out.println();
        c.f(c);
        System.out.println();
        /*
        a.f(a); // Aufruf 1
        System.out.println();
        a.g(b); // Aufruf 2
        System.out.println();
        ((X) b).f(c); // Aufruf 3
        System.out.println();
        //ac.t.f(A); // Aufruf 4

        //cast ab.g(a); // Aufruf 5
        System.out.println();
        c.f(b); // Aufruf 6
        System.out.println();
        //null ((C<B>)ac).t.f(a); // Aufruf 7

        //((C<X>)c).f(c); // Aufruf 8
        */
    }
 }
