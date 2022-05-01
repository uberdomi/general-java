package Numbers;

public class Arithmetic {
    private Rational c = new Rational(0);
    private RootList d = new RootList();

    public Arithmetic(){}

    public Arithmetic(long x){
        c = new Rational(x);
    }
    public Arithmetic(long x, long y){
        c = new Rational(x,y);
    }

    public Arithmetic(String s){
        int to;
        int from=s.length();
        while(from>0){
            from--;
            to = from;
            if(s.charAt(to)=='}'){
                while(s.charAt(from)!='{'){
                    from--;
                }
                Rational b = new Rational(s.substring(from+1,to));
                from--;
                to=from;
                while(from>=0&&s.charAt(from)!='+'&&s.charAt(from)!='-'){
                    from--;
                }
                if(from==to){
                    if(from>=0&&s.charAt(from)=='-'){
                        d.insert(new Root(b).neg());
                    }
                    else{
                        d.insert(new Root(b));
                    }
                }
                else{
                    Rational a = new Rational(s.substring(from+1,to+1));
                    if(from>=0&&s.charAt(from)=='-'){
                        a = a.neg();
                    }
                    d.insert(new Root(a,b));
                }
            }
            else{
                while(from>=0&&s.charAt(from)!='+'&&s.charAt(from)!='-'){
                    from--;
                }
                c = new Rational(s.substring(from+1,to+1));
                if(from>=0&&s.charAt(from)=='-'){
                    c = c.neg();
                }
            }
        }
        simplify();
    }

    public Arithmetic (Rational x){
        c = x;
        simplify();
    }

    public Arithmetic(Rational x, RootList list){
        c=x;
        d=list;
        simplify();
    }

    public Arithmetic(RootList list){
        d=list;
        simplify();
    }
    public Arithmetic (Rational x, Root... roots){
        c= x;
        for(Root r : roots){
            d.insert(r);
        }
        simplify();
    }

    public Arithmetic (Root... roots){
        for(Root r : roots){
            d.insert(r);
        }
        simplify();
    }

    public void setC(Rational c) {
        this.c = c;
    }

    public RootList getD() {
        return d;
    }

    public Rational getC() {
        return c;
    }

    public void setD(RootList d) {
        this.d = d;
    }

    public void simplify(){
        c.simplify();
        for(Root r : d){
            r.simplify();
        }
        d.cleanup();
    }

    public Arithmetic add(Rational x){
        return new Arithmetic(x.add(x),d.copy());
    }
    public Arithmetic add(Root x){
        RootList tmp = d.copy();
        tmp.insert(x);
        return new Arithmetic(c, tmp);
    }
    public Arithmetic add(long x){
        return new Arithmetic(c.add(x),d.copy());
    }
    public Arithmetic add(Arithmetic x){
        Rational cc = c.add(x.getC());
        RootList dd = d.copy();
        for(Root r : x.getD()){
            dd.insert(r);
        }
        return new Arithmetic(cc,dd);
    }

    public Arithmetic add(Arithmetic... arithmetics){
        Rational cc = new Rational(0);
        RootList dd = new RootList();
        for(Arithmetic x : arithmetics){
            cc = cc.add(x.getC());
            for(Root r : x.getD()){
                dd.insert(r);
            }
        }
        return new Arithmetic(cc,dd);
    }

    public Arithmetic neg(){
        Rational cc = c.neg();
        RootList dd = new RootList();
        for(Root r : d){
            dd.insert(r.neg());
        }
        return new Arithmetic(cc,dd);
    }

    public Arithmetic sub(Rational x){
        return add(x.neg());
    }
    public Arithmetic sub(Root x){
        return add(x.neg());
    }
    public Arithmetic sub(long x){
        return add(-x);
    }

    public Arithmetic sub(Arithmetic x){
        return add(x.neg());
    }

    public Arithmetic mul(Rational x){
        Rational cc = c.mul(x);
        RootList dd = new RootList();
        for(Root r : d){
            dd.insert(r.mul(x));
        }
        return new Arithmetic(cc,dd);
    }
    public Arithmetic mul(Root x){
        Rational cc = new Rational(0);
        RootList dd = new RootList();

        dd.insert(x.mul(c));
        for(Root r : d){
            r = r.mul(x);
            if(r.isRational()){
                cc = cc.add(r.getA());
            }
            else{
                dd.insert(r);
            }
        }
        return new Arithmetic(cc,dd);
    }
    public Arithmetic mul(long x){
        Rational cc = c.mul(x);
        RootList dd = new RootList();
        for(Root r : d){
            dd.insert(r.mul(x));
        }
        return new Arithmetic(cc,dd);
    }
    public Arithmetic mul(Arithmetic x){
        Arithmetic[] arithmetics = new Arithmetic[d.size()+1];
        arithmetics[0] = x.mul(c);
        int i=1;
        for(Root r : d){
            arithmetics[i++] = x.mul(r);
        }
        return add(arithmetics);
    }

    public Arithmetic div(Rational x){
        return mul(x.inv());
    }
    public Arithmetic div(Root x){
        return mul(x.inv());
    }
    public Arithmetic div(long x){
        Arithmetic tmp = new Arithmetic();
        tmp = tmp.add(c.div(x));
        for(Root r : d){
            tmp = tmp.add(r.div(x));
        }
        return tmp;
    }
    public Arithmetic div(Arithmetic x){
        return mul(x.inv());
    }
    public Arithmetic inv(){
        Arithmetic con = conjunction();
        Rational x = mul(con).getC();
        return con.div(x);
    }

    public Arithmetic conjunction(){
        Arithmetic x = new Arithmetic(new Rational(1));
        int k = d.size();
        int m = pow2(k);
        Root[] roots = new Root[k];
        int l=0;
        for(Root r : d){
            roots[l++] = r;
        }

        RootList dd;
        for(int i=1; i<m; i++){
            dd = new RootList();
            for(int j=0; j<k; j++){
                if(i%pow2(j+1)<pow2(j)){
                    dd.insert(roots[j]);
                }
                else{
                    dd.insert(roots[j].neg());
                }
            }
            x = x.mul(new Arithmetic(c,dd));
        }
        return x;
    }

    public int pow2 (int x){
        int y=1;
        for(int i=0; i<x; i++){
            y*=2;
        }
        return y;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        boolean cc = false;
        boolean first = false;
        if(!c.equals(0)){
            s.append(c);
            cc = true;
        }
        for(Root r : d){
            if(!cc&&!first){
                first = true;
            }
            else if(!r.isNegative()){
                s.append('+');
            }
            s.append(r);
        }
        return s.toString();
    }

    public static Arithmetic fibo(int k){
        Arithmetic fi = new Arithmetic("1/2+1/2{5}");
        Arithmetic bif = new Arithmetic("1/2-1/2{5}");
        Arithmetic left = new Arithmetic(1);
        Arithmetic right = new Arithmetic(1);
        Root sqrt5 = new Root(5);
        for(int i=0; i<=k; i++){
            left = left.mul(fi);
            right = right.mul(bif);
        }
        return left.sub(right).div(sqrt5);
    }

    public double toDouble(){
        double x = 0;
        x += c.toDouble();
        for(Root r : d){
            x += r.toDouble();
        }
        return x;
    }

    public static void main(String[] args) {
        Arithmetic a = new Arithmetic("1+{2}+{3}+{5}+{7}+{11}");
        System.out.println(a);
        double aa = a.toDouble();
        System.out.println(aa);
        Arithmetic b = a.conjunction();
        Arithmetic c = a.inv();
        System.out.println("------------");
        System.out.println(b);
        double bb =b.toDouble();
        System.out.println(bb + " conjunction");
        System.out.println("------------");
        System.out.println(c);
        double cc =c.toDouble();
        System.out.println(cc + " inverse");
        System.out.println("------------");
        System.out.println(a.mul(b) + " a * conj");
        System.out.println("------------");
        Arithmetic f = a.mul(c);
        System.out.println(f + "a * inv");
        System.out.println(bb*cc + "conj * inv");
        System.out.println(bb*aa + "conj * a");
        System.out.println(aa*cc + "a * inv");
        System.out.println("------------");
        Arithmetic fi = new Arithmetic("1/2+1/2{5}");
        Arithmetic bif = new Arithmetic("1/2-1/2{5}");
        System.out.println(fi);
        System.out.println(bif);
        System.out.println(fi.inv());
        System.out.println(fi.add(bif));
        System.out.println(fi.mul(fi));
        System.out.println(fi.mul(bif));
        System.out.println(fi.mul(fi).sub(fi));
        for(int i=0; i<21; i++){
            System.out.println(fibo(i) + " " + i);
        }
    }

}
