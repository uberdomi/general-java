public class Rational{
    private int p,q;

    public static final Rational zero = new Rational(0);
    public static final Rational one = new Rational(1);
    public Rational(int p, int q){
        this.p = p;
        this.q = q;
        simplify();
    }

    public Rational(int p){
        this.p = p;
        this.q = 1;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void setQ(int q) {
        this.q = q;
    }

    private void simplify(){
        if(q!=0){
            int x = NWD(p,q);
            setP(p/x);
            setQ(q/x);
        }
    }

    private int NWD(int a, int b){
        if(a==0&&b==0){
            return 1;
        }
        if(a==0){
            return b;
        }
        if(b==0){
            return a;
        }
        if(b<0){
            return NWD(a,-b);
        }
        if(a<0){
            return NWD(-a,b);
        }
        if(a==1||b==1){
            return 1;
        }
        if(b>a){
            return NWD(a,b%a);
        }
        else return NWD(a%b,b);
    }

    public static Rational add(Rational a, Rational b){
        Rational x = new Rational(a.getP()*b.getQ()+b.getP()*a.getQ(),a.getQ()*b.getQ());
        x.simplify();
        return x;
    }

    public Rational add(Rational a){
        return add(this,a);
    }
    public Rational add(int x){
        return add(this,new Rational(x));
    }

    public static Rational mul(Rational a, Rational b){
        Rational x = new Rational(a.getP()*b.getP(),a.getQ()*b.getQ());
        x.simplify();
        return x;
    }

    public Rational mul(Rational a){
        return mul(this,a);
    }
    public Rational mul(int x){
        return mul(this,new Rational(x));
    }

    public static Rational neg(Rational a){
        int tmp = a.getP();
        a.setP(-tmp);
        return a;
    }

    public Rational neg(){
        return neg(this);
    }

    public static Rational sub(Rational a, Rational b){
        return add(a,neg(b));
    }

    public Rational sub(Rational a){
        return sub(this,a);
    }
    public Rational sub(int x){
        return sub(this,new Rational(x));
    }

    public static Rational inv(Rational a){
        return new Rational(a.getQ(),a.getP());
    }
    public Rational inv(){
        return inv(this);
    }

    public static Rational div(Rational a, Rational b){
        return mul(a,inv(b));
    }
    public Rational div(Rational a){
        return div(this,a);
    }
    public Rational div(int x){
        return div(this,new Rational(x));
    }


    @Override
    public String toString(){
        simplify();
        StringBuffer s = new StringBuffer();

        s.append(p);
        if(q!=1){
            s.append("/");
            s.append(q);
        }

        return s.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Rational r){
            return(r.getP()*getQ()==r.getQ()*getP());
        }
        else return false;
    }

    public boolean equals(int x){
        return p==q*x;
    }

    public static void main(String[] args) {
        var a = new Rational(12,35);
        var b = new Rational(7,6);
        System.out.println(a);
        System.out.println(b);
        System.out.println(mul(a,b));
        System.out.println(add(a,b));
        System.out.println(div(a,b));
        System.out.println(sub(a,b));
        System.out.println(add(add(a,b),sub(a,b)));
    }

}
