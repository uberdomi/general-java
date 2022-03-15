package Numbers;

public class Rational{
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

    static long parseLong (String s) throws IllegalArgumentException{
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

    public Rational copy(){
        return new Rational(p,q);
    }

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public void simplify(){
        if(q<0){
            q=-q;
            p=-p;
        }
        long x = NWD(p,q);
        p/=x;
        q/=x;
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

    public Rational add(Rational x) {
        return new Rational(p*x.getQ()+q*x.getP(),q*x.getQ());
    }

    public Arithmetic add(Root x) {
        return new Arithmetic(this,x);
    }

    public Rational add(long x) {
        return new Rational(p+q*x,q);
    }

    public Rational sub(Rational x) {
        return new Rational(p*x.getQ()-q*x.getP(),q*x.getQ());
    }

    public Rational sub(long x) {
        return new Rational(p-q*x,q);
    }

    public Arithmetic sub(Root x){
        return new Arithmetic(this,x.neg());
    }

    public Rational neg() {
        return new Rational(-p,q);
    }

    public Rational mul(Rational x) {
        return new Rational(p*x.getP(),q*x.getQ());
    }

    public Rational div(Rational x) {
        return new Rational(p*x.getQ(),q*x.getP());
    }

    public Rational mul(long x) {
        return new Rational(x*p,q);
    }

    public Rational div(long x) {
        return new Rational(p,q*x);
    }

    public Rational inv() {
        return new Rational(q,p);
    }

    public double toDouble() {
        return (double)p/q;
    }

    public boolean equals(Rational r){
        return(r.getP()*getQ()==r.getQ()*getP());
    }

    public boolean equals(long x){
        return p==q*x;
    }

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

    public boolean isNegative(){
        simplify();
        return p<0;
    }

    public static void main(String[] args) {
        Rational e = new Rational(0);
        Rational x = new Rational(1);
        System.out.println(e.div(x));
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

        System.out.println("1/2+{5}/2");
    }
}

