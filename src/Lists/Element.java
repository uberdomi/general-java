package Lists;

public class Element<T> {
    private T t;
    private Element<T> prev;
    private Element<T> next;

    public Element(T t){
        this.t = t;
        prev=null;
        next=null;
    }
    public Element(T t, Element<T> e){
        this.t = t;
        prev=null;
        next =e;
    }
    public Element(T t, Element<T> e1,Element<T> e2){
        this.t = t;
        prev = e1;
        next =e2;
    }

    public Element<T> getNext() {
        return next;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }

    public Element<T> getPrev() {
        return prev;
    }

    public void setPrev(Element<T> prev) {
        this.prev = prev;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
