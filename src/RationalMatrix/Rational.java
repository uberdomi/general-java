package RationalMatrix;

public class Rational {
    private long p,q;

    public Rational(long p, long q){
        this.p = p;
        this.q = q;
        simplify();
    }

    public Rational(long p){
        this.p = p;
        this.q = 1;
    }

    public Rational(String s){
        p = parseRational(s).getP();
        q = parseRational(s).getQ();
        simplify();
    }

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public void setP(long p) {
        this.p = p;
    }

    public void setQ(long q) {
        this.q = q;
    }

    private void simplify(){
        if(q<0){
            q=-q;
            p=-p;
        }
        long x = NWD(p,q);
        setP(p/x);
        setQ(q/x);
    }

    private long NWD(long a, long b){
        if(a==0&&b==0){
            return 1;
        }
        if(b<0){
            return NWD(a,-b);
        }
        if(a<0){
            return NWD(-a,b);
        }
        if(a==0){
            return b;
        }
        if(b==0){
            return a;
        }
        if(a==1||b==1){
            return 1;
        }
        if(b>a){
            return NWD(a,b%a);
        }
        else return NWD(a%b,b);
    }

    public static int parseInt (String s) throws IllegalArgumentException{
        if(s==null){
            throw new IllegalArgumentException("null");
        }
        int result=0;
        int mul=1;
        for(int i=s.length()-1; i>=0; i--){
            int num = s.charAt(i) - '1' + 1;
            if(num=='0'-'1'+1){
                num=0;
            }
            if(num<0||num>9){
                if(i==0&&s.charAt(i)=='-'){
                    result = -result;
                }
                else{
                    throw new IllegalArgumentException("NAN");
                }
            }
            else{
                result += num*mul;
                mul*=10;
            }
        }
        return result;
    }

    public static long parseLong (String s) throws IllegalArgumentException{
        if(s==null){
            throw new IllegalArgumentException("null");
        }
        long result=0;
        long mul=1;
        for(int i=s.length()-1; i>=0; i--){
            int num = s.charAt(i) - '1' + 1;
            if(num=='0'-'1'+1){
                num=0;
            }
            if(num<0||num>9){
                if(i==0&&s.charAt(i)=='-'){
                    result = -result;
                }
                else{
                    throw new IllegalArgumentException("NAN");
                }
            }
            else{
                result += num*mul;
                mul*=10;
            }
        }
        return result;
    }

    public static Rational parseRational (String s) throws IllegalArgumentException{
        if(s==null){
            throw new IllegalArgumentException("null");
        }
        String[] pq = s.split("/");
        long p = parseLong(pq[0]);

        if(pq.length>1){
            long q = parseLong(pq[1]);
            return new Rational(p,q);
        }
        return new Rational(p);
    }

    public Rational add(Rational r){
        return new Rational(p*r.getQ()+q*r.getP(),q*r.getQ());
    }
    public Rational add(long x){
        return new Rational(p+q*x,q);
    }
    public Rational neg(){
        return new Rational(-p,q);
    }
    public Rational sub(Rational r){
        return add(r.neg());
    }
    public Rational sub(long x){
        return new Rational(p-q*x,q);
    }
    public Rational mul(Rational r){
        return new Rational(p*r.getP(),q*r.getQ());
    }
    public Rational mul(long x){
        return new Rational(p*x,q);
    }
    public Rational inv(){
        return new Rational(q,p);
    }
    public Rational div(Rational r){
        return mul(r.inv());
    }
    public Rational div(long x){
        return new Rational(p,q*x);
    }

    public static Rational copy(Rational x){
        return new Rational(x.getP(),x.getQ());
    }
    public Rational copy(){
        return new Rational(p,q);
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

    public boolean equals(Rational r){
        return(r.getP()*getQ()==r.getQ()*getP());
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    public boolean equals(int x){
        return p==q*x;
    }

    public double toDouble(){
        return (double) p/q;
    }

    public static void main(String[] args) {
        Rational e = new Rational(0);
        Rational x = new Rational(1);
        for (int i=1; i<10; i++){
            e = e.add(x.inv());
            x = x.mul(i);
            System.out.println(e);
            System.out.println(e.toDouble());
        }
        Rational o = new Rational(-2,0);
        System.out.println(o);
        System.out.println(o.toDouble());
        double a = 1;
        a/=3;
        System.out.println(a);
        a*=7;
        System.out.println(a);
        a*=3;
        System.out.println(a);
        a/=7;
        System.out.println(a);

        System.out.println("(2)^{1/2}");
    }
}
