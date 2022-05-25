package Arithmetics;

public class Rational {
    private long a,b;

    public Rational(long a, long b){
        this.a = a;
        this.b = b;
        simplify();
    }
    public Rational(long a){
        this.a = a;
        this.b = 1;
    }

    public Rational(String s)throws IllegalArgumentException {
        String[] ab = s.split("/");
        if(ab.length==2){
            a = parseLong(ab[0]);
            b = parseLong(ab[1]);
            simplify();
        }
        else if(ab.length==1){
            a = parseLong(ab[0]);
            b = 1;
        }
        else{
            throw new IllegalArgumentException("wrong parameters!");
        }

    }
    public static Rational parseRational(String s) throws IllegalArgumentException {
        String[] ab = s.split("/");
        if(ab.length!=2){
            throw new IllegalArgumentException("wrong parameters!");
        }
        return new Rational(parseLong(ab[0]),parseLong(ab[1]));
    }
    private static long parseLong(String s) throws IllegalArgumentException{
        long sum = 0;
        long pow = 1;
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(c<'0'||c>'9'){
                if(i==0&&c=='-'){
                    sum *= -1;
                }
                else{
                    throw new IllegalArgumentException("NaN");
                }
            }
            else{
                sum += (c-'0')*pow;
                pow *= 10;
            }
        }
        return sum;
    }

    public void setA(long a) {
        this.a = a;
    }

    public void setB(long b) {
        this.b = b;
    }

    public long getA() {
        return a;
    }

    public long getB() {
        return b;
    }

    public Rational add(Rational q){
        return new Rational(a*q.getB()+b*q.getA(),b*q.getB());
    }
    public Rational add(long x){
        return add(new Rational(x));
    }
    public Rational sub(Rational q){
        return add(q.neg());
    }
    public Rational sub(long x){
        return sub(new Rational(x));
    }
    public Rational neg(){
        return new Rational(-a,b);
    }
    public Rational mul(Rational q){
        return new Rational(a*q.getA(),b*q.getB());
    }
    public Rational mul(long x){
        return mul(new Rational(x));
    }
    public Rational div(Rational q){
        return mul(q.inv());
    }
    public Rational div(long x){
        return div(new Rational(x));
    }
    public Rational inv(){
        return new Rational(b,a);
    }



    // \in O(log(ab))
    private static long ggT(long a, long b){
        if(a<0){
            a*=-1;
        }
        if(b<0){
            b*=-1;
        }
        if(b>a){
            return ggT(b,a);
        }
        if(b==0){
            return a == 0 ? 1 : a;
        }
        return ggT(b,a%b);
    }

    private void simplify(){
        if(b<0){
            a *= -1;
            b *= -1;
        }
        long c = ggT(a,b);
        a /= c;
        b /= c;
    }
    @Override
    public String toString(){
        return b!=1 ? a+"/"+b : a+"";
    }
    public boolean equals(Rational q){
        return a*q.getB()==b*q.getA();
    }
    public boolean equals(long x){
        return b*x==a;
    }

    public double toDouble(){
        return (double) a/b;
    }

    public boolean less(Rational q){
        return a*q.getB()>b*q.getA();
    }

    public boolean less(long x){
        return b*x>a;
    }

    public Root toRoot(){
        return new Root(this,new Rational(1));
    }

    public static void main(String[] args) {
        System.out.println('1'+0);
        System.out.println('2'+1);
        System.out.println('9'+1);
        System.out.println('0'+1);
        System.out.println(parseLong("-1234")+parseLong("111"));
        var p = parseRational("21/-37");
        var q = parseRational("42/-69");
        System.out.println(p);
        System.out.println(q);
        System.out.println(ggT(12,18));
        System.out.println(p.add(q));
        System.out.println(p.mul(q));
        System.out.println(p.sub(q));
        System.out.println(p.div(q));
    }
}
