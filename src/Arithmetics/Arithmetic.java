package Arithmetics;

public class Arithmetic {
    private RootList nums;

    public RootList getNums() {
        return nums;
    }

    public Arithmetic(){
        nums = new RootList();
    }
    public Arithmetic(long x){
        nums = new RootList();
        nums.insert(new Rational(x).toRoot());
    }
    public Arithmetic(long x,long y){
        nums = new RootList();
        nums.insert(new Rational(x,y).toRoot());
    }
    public Arithmetic(Rational p){
        nums = new RootList();
        nums.insert(p.toRoot());
    }
    public Arithmetic(Root r){
        nums = new RootList();
        nums.insert(r);
    }

    private Arithmetic(RootList nums){
        this.nums = nums;
        nums.cleanup();
    }


    public Arithmetic add(Arithmetic c){
        RootList sum = new RootList();
        for (Root r : nums){
            sum.insert(r);
        }
        for (Root r : c.nums){
            sum.insert(r);
        }
        return new Arithmetic(sum);
    }
    public Arithmetic add(Rational p){
        RootList sum = new RootList();
        for (Root r : nums){
            sum.insert(r);
        }
        sum.insert(p.toRoot());
        return new Arithmetic(sum);
    }
    public Arithmetic add(Root r){
        RootList sum = new RootList();
        for (Root s : nums){
            sum.insert(s);
        }
        sum.insert(r);
        return new Arithmetic(sum);
    }
    public Arithmetic add(long x){
        RootList sum = new RootList();
        for (Root r : nums){
            sum.insert(r);
        }
        sum.insert(new Rational(x).toRoot());
        return new Arithmetic(sum);
    }
    public Arithmetic sub(Arithmetic c){
        return add(c.neg());
    }
    public Arithmetic sub(long x){
        return add(-x);
    }
    public Arithmetic sub(Rational p){
        return add(p.neg());
    }
    public Arithmetic sub(Root r){
        return add(r.neg());
    }
    public Arithmetic neg(){
        RootList sum = new RootList();
        for (Root r : nums){
            sum.insert(r.neg());
        }
        return new Arithmetic(sum);
    }

    public Arithmetic mul(Arithmetic c){
        RootList prod = new RootList();
        for (Root r : nums){
            for (Root s : c.nums){
                prod.insert(r.mul(s));
            }
        }

        return new Arithmetic(prod);
    }
    public Arithmetic mul(Rational p){
        RootList prod = new RootList();
        for (Root r : nums){
            prod.insert(r.mul(p));
        }

        return new Arithmetic(prod);
    }
    public Arithmetic mul(Root r){
        RootList prod = new RootList();
        for (Root s : nums){
            prod.insert(s.mul(r));
        }

        return new Arithmetic(prod);
    }
    public Arithmetic mul(long x){
        RootList prod = new RootList();
        for (Root r : nums){
            prod.insert(r.mul(x));
        }

        return new Arithmetic(prod);
    }
    public Arithmetic div(Arithmetic q){
        return mul(q.inv());
    }
    public Arithmetic div(Rational p){
        RootList prod = new RootList();
        for (Root r : nums){
            prod.insert(r.div(p));
        }
        return new Arithmetic(prod);
    }
    public Arithmetic div(Root r){
        RootList prod = new RootList();
        for (Root s : nums){
            prod.insert(s.div(r));
        }
        return new Arithmetic(prod);
    }
    public Arithmetic div(long x){
        RootList prod = new RootList();
        for (Root r : nums){
            prod.insert(r.div(x));
        }
        return new Arithmetic(prod);
    }

    public Arithmetic inv(){
        Arithmetic zae = conj();
        Rational nenn = mul(zae).nums.head1();
        return zae.div(nenn);
    }

    private Arithmetic conj(){
        int x=0;
        for(Root r : nums){
            x++;
        }
        Root[] roots = new Root[x];
        x=0;
        for(Root r : nums){
            roots[x]=r;
            //System.out.println(r);
            x++;
        }
        int x2 = pow2(x)-1;
        Arithmetic[] conjs = new Arithmetic[x2];
        for(int i=0; i<x2; i++){
            RootList prod = new RootList();
            for(int j=0; j<x; j++){
                if(i%pow2(j+1)<pow2(j)){
                    prod.insert(roots[j].neg());
                }
                else{
                    prod.insert(roots[j]);
                }
            }
            conjs[i] = new Arithmetic(prod);
            //System.out.println(conjs[i]);
        }
        Arithmetic con = new Arithmetic();
        con = con.add(1);
        for(int i=0; i<x2; i++){
            con = con.mul(conjs[i]);
        }
        //System.out.println(con);
        return con;
    }

    private int pow2(int x){
        int y=1;
        for(int i=0; i<x; i++){
            y *=2;
        }
        return y;
    }

    @Override
    public String toString(){
        return nums.toString();
    }

    public double toDouble(){
        return nums.toDouble();
    }

    public static void main(String[] args) {
        Arithmetic x = new Arithmetic();
        x = x.add(new Root(2)).add(new Root(3)).add(new Root(2)).add(new Root(5)).add(new Rational(6));
        System.out.println(x);
        Arithmetic y = new Arithmetic();
        y = y.add(new Root(2)).add(new Rational(2));
        System.out.println(y);
        y = y.mul(x);
        System.out.println(y);
        y = new Arithmetic();
        y = y.add(new Root(2)).add(new Rational(2));
        System.out.println(y);
        Arithmetic z = y.conj();
        System.out.println(z);
        System.out.println(y.mul(z));
        System.out.println(y.mul(z).nums.head1());
        System.out.println(y.inv());
        System.out.println(y.mul(y.inv()));
        x = new Arithmetic();
        x = x.add(new Root(2)).add(new Root(3)).add(new Root(2)).add(new Root(5)).add(new Rational(6));
        System.out.println(x);
        System.out.println(x.toDouble());
        var xx = x.inv();
        System.out.println(xx);
        System.out.println(xx.toDouble());
        System.out.println(x.mul(xx));
        System.out.println(x.toDouble()*xx.toDouble());
    }
}
