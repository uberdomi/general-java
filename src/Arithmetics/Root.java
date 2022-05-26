package Arithmetics;

public class Root {
    private Rational p,q;
    public static final double eps = 0.00000001;

    public Root(Rational p, Rational q){
        this.p = p;
        this.q = q;
        simplify();
    }
    public Root(Rational q){
        p = new Rational(1);
        this.q = q;
        simplify();
    }
    public Root(long x){
        p = new Rational(1);
        q = new Rational(x);
        simplify();
    }
    public Root(String s){
        p = new Rational(1);
        q = new Rational(s);
        simplify();
    }
    public Root(String s,String t){
        p = new Rational(s);
        q = new Rational(t);
        simplify();
    }

    public void setP(Rational p) {
        this.p = p;
    }

    public void setQ(Rational q) {
        this.q = q;
        simplify();
    }

    public Rational getP() {
        return p;
    }

    public Rational getQ() {
        return q;
    }


    public void simplify() throws IllegalArgumentException{
        if(q.less(0)){
            throw new IllegalArgumentException("negative root");
        }
        long a = q.getA();
        long b = q.getB();
        long mul=1;
        long div=1;
        for(long i=2;i*i<=a;i++){
            while(a%(i*i)==0){
                mul *= i;
                a /= (i*i);
            }
        }
        for(long i=2;i*i<=b;i++){
            while(b%(i*i)==0){
                div *= i;
                b /= (i*i);
            }
        }
        q = new Rational(a,b);
        p = p.mul(mul).div(div);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        if(p.equals(-1)){
            s.append("-");
        }
        else if(!p.equals(1)||q.equals(1)){
            s.append(p);
        }
        if(!q.equals(1)){
            s.append("{");
            s.append(q);
            s.append("}");
        }

        return s.toString();
    }

    public int compareRoot(Root r){
        if(q.equals(r.getQ())){
            return 0;
        }
        if(q.less(r.getQ())){
            return -1;
        }
        return 1;
    }

    public double toDouble(){
        double p = this.p.toDouble();
        double q = this.q.toDouble();
        double x = 1000;
        double y=0;
        while(y*y-q>eps||q-y*y>eps){
            while(y*y<q){
                y+=x;
            }
            y-=x;
            x/=10;
        }
        return p*y;
    }

    public Root neg(){
        return new Root(p.neg(),q);
    }

    public Root mul(Root s){
        return new Root(p.mul(s.getP()),q.mul(s.getQ()));
    }
    public Root mul(long x){
        return new Root(p.mul(x),q);
    }
    public Root mul(Rational x){
        return new Root(p.mul(x),q);
    }
    public Root div(Root s){
        return new Root(p.div(s.getP()),q.div(s.getQ()));
    }
    public Root div(long x){
        return new Root(p.div(x),q);
    }
    public Root div(Rational x){
        return new Root(p.div(x),q);
    }

    public Root copy(){
        return new Root(p.copy(),q.copy());
    }

    public static void main(String[] args) {
        var r = new Root("32/16");
        System.out.println(r);
        System.out.println(r.toDouble());
        System.out.println(r.toDouble()*r.toDouble());
        r = new Root("32/27");
        System.out.println(r);
    }
}
