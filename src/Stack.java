import java.util.Iterator;

public class Stack implements Iterable<Integer>{
    private Element top;

    public Stack(){
        top = null;
    }

    public void add(int x){
        Element tmp = top;
        top = new Element(x,tmp);
    }
    public int pop(){
        if(top!=null){
            int x=top.getValue();
            top = top.getNext();
            return x;
        }
        else return 0;
    }
    public void remove(int x){
        if(top==null){
            return;
        }
        if(top.getValue()==x){
            top = top.getNext();
            return;
        }
        for(Element e : top){
            if(e.getNext()!=null&&e.getNext().getValue()==x){
                e.setNext(e.getNext().getNext());
                return;
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Stackerator(top);
    }

    @Override
    public String toString(){
        StringBuffer s = new StringBuffer();
        for(Integer i : this){
            s.append(i);
            s.append("_");
        }
        return s.length()>0 ? s.substring(0,s.length()-1) : "-";
    }

    private class Stackerator implements Iterator<Integer>{
        private Element e;

        private Stackerator(Element e){
            this.e = e;
        }

        @Override
        public boolean hasNext() {
            return e!=null;
        }

        @Override
        public Integer next() {
            Integer i = e.getValue();
            e = e.getNext();
            return i;
        }
    }
}
