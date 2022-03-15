package Lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleList<T extends Comparable> implements Iterable<T>{
    //also sorted
    private Element<T> head;
    private Element<T> tail;

    public DoubleList(){
        head=null;
        tail=null;
    }

    public void setTail(Element<T> tail) {
        this.tail = tail;
    }

    public void setHead(Element<T> head) {
        this.head = head;
    }

    public Element<T> getHead() {
        return head;
    }

    public Element<T> getTail() {
        return tail;
    }

    public void insert(T t) throws IllegalArgumentException{
        if(t==null){
            throw new IllegalArgumentException();
        }
        Element<T> x = new Element<>(t);
        if(head==null){
            head=x;
            tail=x;
            return;
        }
        if(t.compareTo(head.getT())<0){
            Element<T> tmp = head;
            head=x;
            x.setNext(tmp);
            tmp.setPrev(x);
            return;
        }
        Element<T> tmp = head;
        while(tmp.getT().compareTo(t)<0){
            if(tmp.getNext()==null){
                x.setPrev(tmp);
                tmp.setNext(x);
                tail=x;
                return;
            }
            else{
                tmp=tmp.getNext();
            }
        }
        tmp.getNext().setPrev(x);
        x.setNext(tmp.getNext());
        x.setPrev(tmp);
        tmp.setNext(x);
    }




    @Override
    public Iterator iterator() {
        return new Listerator(head);
    }

    private class Listerator implements Iterator<T>{
        Element<T> e;

        private Listerator(Element<T> e){
            this.e = e;
        }
        @Override
        public boolean hasNext() {
            return e!=null;
        }

        @Override
        public T next() throws NoSuchElementException {
            if(e==null){
                throw new NoSuchElementException();
            }

            Element<T> tmp = e;
            e = e.getNext();
            return tmp.getT();
        }
    }
}
