import java.util.Iterator;

public class Element implements Iterable<Element> {
    private Element next;
    private int value;

    public Element(int x, Element e){
        value = x;
        next = e;
    }

    public int getValue() {
        return value;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    @Override
    public Iterator iterator() {
        return new Elementerator(this);
    }


    private class Elementerator implements Iterator<Element>{
        private Element e;

        private Elementerator(Element e){
            this.e = e;
        }

        @Override
        public boolean hasNext() {
            return e!=null;
        }

        @Override
        public Element next() {
            Element tmp = e;
            e = e.next;
            return tmp;
        }
    }
}
