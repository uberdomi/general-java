package Numbers;

public class Root{
    private Rational a;
    private Rational b;

    public Root(Rational b){
        a = new Rational(1);
        this.b = b.copy();
        simplify();
    }

    public Root(Rational a, Rational b){
        this.a = a.copy();
        this.b = b.copy();
        simplify();
    }

    public Root(String b){
        this.a = new Rational(1);
        this.b = new Rational(b);
        simplify();
    }

    public Root(String a, String b){
        this.a = new Rational(a);
        this.b = new Rational(b);
        simplify();
    }

    public Root (long b){
        a = new Rational(1);
        this.b = new Rational(b);
        simplify();
    }
    public Root (long pb, long qb){
        a = new Rational(1);
        b = new Rational(pb,qb);
        simplify();
    }
    public Root (long pa, long qa, long pb, long qb){
        a = new Rational(pa,qa);
        b = new Rational(pb,qb);
        simplify();
    }

    public Root copy(){
        return new Root(a.copy(),b.copy());
    }

    public Rational getB() {
        return b;
    }

    public Rational getA() {
        return a;
    }

    public void setA(Rational a) {
        this.a = a;
    }

    public void setB(Rational b) {
        this.b = b;
    }

    public void simplify() throws IllegalArgumentException{
        if(b.isNegative()){
            throw new IllegalArgumentException("negative root");
        }
        else{
            b.simplify();
            long p = b.getP();
            long q = b.getQ();
            for(long i=2; i*i<=p; i++){
                long tmp = p;
                while(tmp%(i*i)==0){
                    tmp/=(i*i);
                    b = b.div(i*i);
                    a = a.mul(i);
                }
            }
            for(long i=2; i*i<q; i++){
                long tmp = q;
                while(tmp%(i*i)==0){
                    tmp/=(i*i);
                    b = b.mul(i*i);
                    a = a.div(i);
                }
            }
            a.simplify();
        }
    }

    public Arithmetic add(Rational x){
        return new Arithmetic(x,this);
    }

    public Arithmetic add(Root x){
        return new Arithmetic(x,this);
    }

    public Arithmetic add(long x) {
        return new Arithmetic(new Rational(x),this);
    }

    public Root addRoot(Root x) throws IllegalArgumentException{
        if(x.compareRoot(this)==0){
            return new Root(a.add(x.getA()),b);
        }
        else{
            throw new IllegalArgumentException("wrong root");
        }
    }

    public Arithmetic sub(Rational x){
        return new Arithmetic(x.neg(),this);
    }

    public Arithmetic sub(Root x){
        return new Arithmetic(x.neg(),this);
    }

    public Arithmetic sub(long x) {
        return new Arithmetic(new Rational(-x),this);
    }

    public Root neg() {
        return new Root(a.neg(),b);
    }

    public Root mul(Rational x) {
        return new Root(a.mul(x),b);
    }

    public Root mul(Root x) {
        return new Root(a.mul(x.getA()),b.mul(x.getB()));
    }

    public Root mul(long x) {
        return new Root(a.mul(x),b);
    }

    public Root div(Rational x) {
        return new Root(a.div(x),b);
    }

    public Root div(Root x) {
        return new Root(a.div(x.getA()),b.div(x.getB()));
    }

    public Root div(long x) {
        return new Root(a.div(x),b);
    }

    public Root inv() {
        return new Root(a.inv(),b.inv());
    }

    public long compareRoot(Root other){
        simplify();
        other.simplify();
        return b.getP()*other.getB().getQ() - b.getQ()*other.getB().getP();
    }

    public boolean isNegative(){
        return a.isNegative();
    }

    public boolean isRational(){
        return a.equals(0)||b.equals(1);
    }

    public static double sqrt(long x){
        double d = 1000;
        double result=0;
        while(result*result-x>0.000001||result*result-x<-0.000001){
            while(result*result<x){
                result += d;
            }
            result-=d;
            d/=10;
        }
        return result;
    }

    public double toDouble() {
        return this.a.toDouble()*sqrt(b.getP())/sqrt(b.getQ());
    }

    @Override
    public String toString(){
        simplify();
        StringBuffer s = new StringBuffer();

        if(!b.equals(1)){
            if(a.equals(-1)){
                s.append("-");
            }
            else if(!a.equals(1)){
                s.append(a.toString());
            }
            s.append("{").append(b.toString()).append("}");
            return s.toString();
        }
        else{
            return a.toString();
        }
    }

    public static void main(String[] args) {

        Root w = new Root("1/3");
        System.out.println(w);
        double x = w.toDouble();
        System.out.println(x);
        System.out.println(x*x);
        Root a = new Root(21);
        Root b = new Root(37);
        Root c = new Root(84);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(a.toDouble());
        System.out.println(b.toDouble());
        System.out.println(c.toDouble());
        System.out.println(a.compareRoot(b));
        System.out.println(b.compareRoot(a));
        System.out.println(a.compareRoot(c));
    }
}
