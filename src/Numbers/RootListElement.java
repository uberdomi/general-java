package Numbers;

public class RootListElement {
    private Root root;
    private RootListElement next;

    public RootListElement(Root r){
        root = r;
        next = null;
    }
    public RootListElement(Root r, RootListElement next){
        root = r;
        this.next = next;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root r) {
        root = r;
    }

    public RootListElement getNext() {
        return next;
    }

    public void setNext(RootListElement next) {
        this.next = next;
    }

    public void insert(Root r){
        if(root.compareRoot(r)==0){
            root = root.addRoot(r);
        }
        else if(next==null){
            next = new RootListElement(r.copy());
        }
        else if(next.getRoot().compareRoot(r)>0){
            RootListElement tmp = next;
            next = new RootListElement(r.copy(),tmp);
        }
        else next.insert(r);
    }

    public void cleanup(){
        while(next!=null&&getNext().getRoot().isRational()){
            next = getNext().getNext();
        }
        if(next!=null){
            next.cleanup();
        }
    }
}
