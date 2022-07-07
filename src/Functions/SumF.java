package Functions;

import java.util.LinkedList;
import java.util.List;

public class SumF extends Function{
    private final List<Function> fs;
    public SumF (List<Function> fs){
        super();
        this.fs = fs;
    }
    public SumF (Function... fs){
        super();
        this.fs = new LinkedList<>(List.of(fs));
    }

    public Function simplify(){
        LinkedList<Constant> cs = new LinkedList<>();
        LinkedList<Function> gs = new LinkedList<>();
        Function g;
        for(Function f : fs){
            if(f instanceof Constant c){
                cs.add(c);
            }
            else if(f instanceof  ProductF p){
                g = p.simplify();
                if(g instanceof Constant c){
                    cs.add(c);
                }
                else{
                    gs.add(g);
                }
            }
            else{
                gs.add(f);
            }
        }
        double x = 0;
        for(Constant c : cs){
            x += c.getC();
        }
        if(x>eps||-x>eps){
            gs.add(new Constant(x));
        }
        return gs.size() == 1 ? gs.pop() : new SumF(gs);
    }
    /*
    public Function simplify(){
        LinkedList<Constant> consts = new LinkedList<>();
        LinkedList<Function> functions = new LinkedList<>();
        LinkedList<Function> functions2 = new LinkedList<>();
        LinkedList<Function> gs = new LinkedList<>();
        boolean one=true;
        for(Function f : fs){
            if(f instanceof Constant c){
                consts.add(c);
            }
            else{
                functions.add(f);
            }
        }
        Function ff,g;
        ProductF tmp;
        ProductF q = null;
        while(!functions.isEmpty()||!functions2.isEmpty()){
            if(one){
                one = false;
                ff = functions.pop();
                while((!functions.isEmpty())&&!(ff instanceof ProductF)){
                    gs.add(ff);
                    ff = functions.pop();
                }
                if(!functions.isEmpty()){
                    q = (ProductF) ff;
                }
                else{
                    gs.add(ff);
                }
                while(!functions.isEmpty()){
                    g = functions.pop();
                    if(g instanceof ProductF p){ //only Products
                        tmp = q.merge(p);
                        if(tmp!=null){
                             q = tmp;
                        }
                        else{
                            functions2.add(p);
                        }
                    }
                    else{ //not products
                        gs.add(g);
                    }
                }
                if(q!=null){
                    if(q.constProd()!=null){
                        consts.add(q.constProd());
                    }
                    else{
                        gs.add(q);
                    }
                }
            }
            else{
                one = true;
                ff = functions2.pop();
                while((!functions2.isEmpty())&&!(ff instanceof ProductF)){
                    gs.add(ff);
                    ff = functions2.pop();
                }
                if(!functions2.isEmpty()){
                    q = (ProductF) ff;
                }
                else{
                    gs.add(ff);
                }
                while(!functions2.isEmpty()){
                    g = functions2.pop();
                    if(g instanceof ProductF p){ //only Products
                        tmp = q.merge(p);
                        if(tmp!=null){
                            q = tmp;
                        }
                        else{
                            functions.add(p);
                        }
                    }
                    else{ //not products
                        gs.add(g);
                    }
                }
                if(q!=null){
                    if(q.constProd()!=null){
                        consts.add(q.constProd());
                    }
                    else{
                        gs.add(q);
                    }
                }
            }
        }

        if(!consts.isEmpty()){
            double x=0;
            for(Constant c : consts){
                x += c.getC();
            }
            gs.add(new Constant(x));
        }

        if(gs.size()==1){
            return gs.pop();
        }
        else{
            return new SumF(gs);
        }
    }

     */

    public List<Function> getFs() {
        return fs;
    }

    @Override
    public double value(double input) {
        double x=0;
        for(Function f : fs){
            if(f!=null){
                x += f.value(input);
            }
        }
        return x;
    }

    @Override
    public Function derivative() {
        LinkedList<Function> gs = new LinkedList<>();
        for(Function f : fs){
            if(f!=null&&!(f instanceof Constant)){
                gs.add(f.derivative());
            }
        }
        return gs.size()==1 ? gs.pop() : new SumF(gs);
    }

    @Override
    public String toString(){
        if(fs==null){
            return "" + 0;
        }
        StringBuilder s = new StringBuilder();
        boolean first=true;

        for(Function f : fs){
            if(f instanceof Constant c){
                if(!first&&c.getC()>-eps){
                    s.append("+");
                }
            }
            else if(f instanceof ProductF p){
                if(!first&&p.extractConst()>-eps){
                    s.append("+");
                }
            }
            else{
                if(!first){
                    s.append("+");
                }
            }
            first = false;
            s.append(f);
        }

        return s.toString();
    }

    public static void main(String[] args) {
        Function f = new Ln(new SumF(new Constant(1),new Identity()));
        System.out.println(f);
        System.out.println(f.derivative().value(0));
        System.out.println(f.derivative().derivative().value(0));
        System.out.println(f.derivative().derivative().derivative().value(0));
        System.out.println(f.derivative().derivative().derivative().derivative().value(0));
        System.out.println(f.derivative().derivative().derivative().derivative().derivative().value(0));
        System.out.println(f.derivative().derivative().derivative().derivative().derivative().derivative().value(0));
    }
}
