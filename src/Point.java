public class Point {
    private double a,b;

    public Point(double a, double b){
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    private void rotate(){
        double c=getA();
        setA(-getB());
        setB(c);
    }

    public void rotation(){
        System.out.println("(" + getA() +";" + getB() + ")");
        rotate();
        System.out.println("(" + getA() +";" + getB() + ")");
    }

    public static void main(String[] args) {
        Point p = new Point(2,1);
        p.rotation();
    }

}

