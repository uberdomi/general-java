package Arithmetics;

import java.util.Iterator;

public class RootList implements Iterable<Root>{
    private Root root;
    private RootList next;

    public RootList(){
        root=new Root(0);
        next=this;
    }

    public Root getRoot() {
        return root;
    }

    public RootList getNext() {
        return next;
    }

    public void setNext(RootList next) {
        this.next = next;
    }

    private RootList (Root r){
        root = r;
        next = null;
    }
    private RootList (Root r, RootList next){
        root = r;
        this.next = next;
    }

    public void insert(Root r){
        r = r.copy();
        if(root.compareRoot(r)==0){
            root.setP(root.getP().add(r.getP()));
        }
        else if(nextDummy()||r.compareRoot(next.getRoot())==1){
            setNext(new RootList(r,next));
        }
        else{
            next.insert(r);
        }
    }

    private boolean nextDummy(){
        return next.root.getQ().equals(0);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        boolean first=true;
        for(Root r : this){
            if(first){
                first = false;
            }
            else if(!r.getP().less(0)){
                s.append("+");
            }
            s.append(r);
        }
        if(first){
            return "0";
        }
        return s.toString();
    }

    public double toDouble(){
        double sum=0;
        for(Root r : this){
            sum += r.toDouble();
        }
        return sum;
    }

    protected Rational head1(){
        if(next.root.getQ().equals(1)){
            return next.root.getP();
        }
        return new Rational(1);
    }

    protected void cleanup(){
        while(!nextDummy()&&next.getRoot().getP().equals(0)){
            setNext(next.getNext());
        }
        if(!nextDummy()){
            next.cleanup();
        }
    }

    @Override
    public Iterator iterator() {
        return new Rooterator(next);
    }

    private class Rooterator implements Iterator<Root>{
        private RootList current;

        private Rooterator (RootList current){
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return !current.getRoot().getQ().equals(0);
        }

        @Override
        public Root next() {
            Root r = current.getRoot();
            current=current.next;
            return r;
        }
    }

    public static void main(String[] args) {
        var l = new RootList();
        l.insert(new Root("2"));
        l.insert(new Root("3"));
        l.insert(new Root("4"));
        l.insert(new Root("5"));
        System.out.println(l);
        l = new RootList();
        System.out.println(l);
    }
}
