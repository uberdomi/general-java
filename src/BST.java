import java.util.NoSuchElementException;

public class BST implements Iterable<Integer>{
    private Integer content;
    private BST parent,left,right;

    public BST(Integer c, BST p, BST l, BST r){
        content = c;
        left = l;
        right = r;
        parent = p;
    }

    public void insert(Integer i){
        if(content==null){
            content = i;
        }
        else{
            if(i<content){
                if(left==null){
                    left=new BST(i,this,null,null);
                }
                else{
                    left.insert(i);
                }
            }
            if(i>content){
                if(right==null){
                    right=new BST(i,this,null,null);
                }
                else{
                    right.insert(i);
                }
            }
        }
    }

    public class TreeTerator implements java.util.Iterator<Integer>{
        private BST current;
        private boolean hasLeft,hasMid,hasRight;
        private TreeTerator left,right;

        public TreeTerator(BST bst){
            current = bst;
            hasMid = true;
            if(bst.left!=null){
                left = new TreeTerator(bst.left);
                hasLeft = true;
            }
            else{
                left=null;
                hasLeft=false;
            }
            if(bst.right!=null){
                right = new TreeTerator(bst.right);
                hasRight = true;
            }
            else{
                right=null;
                hasRight=false;
            }
        }

        @Override
        public Integer next() {
            if(!(hasRight||hasLeft||hasMid)){
                throw new NoSuchElementException();
            }

            if(hasLeft){
                Integer i = left.next();
                hasLeft = left.hasNext();
                return i;
            }
            if(hasMid){
                hasMid=false;
                return current.content;
            }
            Integer i = right.next();
            hasRight = right.hasNext();
            return i;
        }

        @Override
        public boolean hasNext() {
            return hasRight||hasLeft||hasMid;
        }
    }

    public TreeTerator iterator(){
        return new TreeTerator(this);
    }

    @Override
    public String toString(){
        StringBuffer s = new StringBuffer("{");
        if(left!=null){
            s.append(left.toString()+"-");
        }
        s.append(content.toString());
        if(right!=null){
            s.append("-"+right.toString());
        }
        s.append("}");
        return s.toString();
    }

    public static void main(String[] args) {
        var b = new BST(null,null,null,null);
        //for(int i=1;i<9;i++){
        //    b.insert(i);
        //}

        /*
        b.insert(5);
        b.insert(2);
        b.insert(1);
        b.insert(4);
        b.insert(3);
        b.insert(9);
        b.insert(18);
        b.insert(20);
        b.insert(19);
        b.insert(21);

         */

        for(int i=1; i<=1024; i*=2){
            for(int j=1; j<=i; j++){
                b.insert(1024/i*j);
            }
        }

        System.out.println(b);


        StringBuffer s= new StringBuffer("");
        for(Integer i : b){
            //s.append(i + ", ");
            System.out.println(i);
        }
        //System.out.println(s);


    }
}
