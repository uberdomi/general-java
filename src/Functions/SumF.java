package Functions;

import java.util.LinkedList;
import java.util.List;

public class SumF extends Function{
    //TODO
    //constanTS -> constanT, nomials -> sorted polynom, sin, cos, exp, ln, arcsin?
    private final List<Function> fs;
    public SumF (List<Function> fs){
        super();
        //fs = sort(fs);
        this.fs = fs;
    }
    public SumF (double x,List<Function> fs){
        super(x);
        //fs = sort(fs);
        this.fs = fs;
    }

    private List<Function> sort(List<Function> gs){
        //TODO
        return gs;
    }

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
        return prefactor*x;
    }

    @Override
    public Function derivative() {
        List<Function> gs = new LinkedList<>();
        for(Function f : fs){
            if(f!=null){
                gs.add(f.derivative());
            }
        }
        return new SumF(prefactor,gs);
    }

    @Override
    public String toString(){
        if(fs==null||prefactor==0){
            return "" + 0;
        }
        StringBuilder s = new StringBuilder();
        if(prefactor-1>eps||1-prefactor>eps){ //not 1
            if(prefactor+1<eps&&-1-prefactor<eps){ //eq -1
                s.append("-");
            }
            else if(infinitesimal(prefactor)){
                s.append((int) prefactor);
            }
            else{
                s.append(prefactor);
            }
            s.append("(");
        }

        for(Function f : fs){
            if(f.prefactor>=0){
                s.append("+");
            }
            s.append(f);
        }

        if(prefactor-1>eps||1-prefactor>eps){
            s.append(")");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        List<Function> l = new LinkedList<>();
        //l.add(new Constant(-6));
        //l.add(new Nomial(-6,1));
        //l.add(new Nomial(11,2));
        //l.add(new Nomial(3));
        SumF f = new SumF(l);
        System.out.println(f);
    }
}
