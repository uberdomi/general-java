public class B extends A {
    public void f(A a) {
        System.out.println("B.f(A)");
        a.f(this);
    }

    public void f(B b) {
        System.out.println("B.f(B)");
        //b.f((A) this);
    }




    public static void g(X x) {
        System.out.println("B.g(X)");
    }
 }
