public interface X {
    default void f(A a) {
        System.out.println("X.f(A)");
    }
    /*default void f(B b) {
        System.out.println("X.f(B)");
    }

     */
}
