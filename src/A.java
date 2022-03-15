public class A implements X {
    public static void g(X x) {
        System.out.println("A.g(X)");
        ((B) x).g(new A());
    }

    /*public void f(B b) {
        System.out.println("A.f(B)");
        //b.f((A) this);
    }

     */
}

