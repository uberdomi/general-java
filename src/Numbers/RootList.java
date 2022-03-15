package Numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RootList implements Iterable<Root>{
    private RootListElement head;

    public RootList(){
        head = null;
    }

    public void insert(Root r){
        if(head==null){
            head = new RootListElement(r.copy());
        }
        else if(head.getRoot().compareRoot(r)>0){
            RootListElement tmp = head;
            head = new RootListElement(r.copy(),tmp);
        }
        else{
            head.insert(r);
        }
    }

    public void cleanup(){
        while(head!=null&&head.getRoot().isRational()){
            head = head.getNext();
        }
        if(head!=null){
            head.cleanup();
        }
    }

    public int size(){
        if(head==null){
            return 0;
        }
        int x=0;
        for(Root r : this){
            x++;
        }
        return x;
    }

    public RootList copy(){
        RootList tmp = new RootList();
        for(Root r : this){
            tmp.insert(r);
        }
        return tmp;
    }

    @Override
    public Iterator<Root> iterator() {
        return new Rooterator(head);
    }

    private class Rooterator implements Iterator<Root>{

        private RootListElement current;

        private Rooterator(RootListElement e){
            current = e;
        }
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Root next() {
            if(current==null){
                throw new NoSuchElementException();
            }
            Root r = current.getRoot();
            current = current.getNext();
            return r;
        }
    }
}
