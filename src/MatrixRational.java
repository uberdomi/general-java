import java.util.HashMap;
import java.util.Map;

public class MatrixRational {
    private Rational[][] Matrix;

    public MatrixRational(int x, int y){
        this.Matrix = new Rational[x][y];
    }

    public MatrixRational(Rational[][] m){
        this.Matrix = m;
    }

    public MatrixRational(String s) throws IllegalArgumentException{
        String lines[] = s.split("\n");
        Rational[][] matrix = new Rational[lines.length][lines[0].split(";").length];

        for(int i=0; i< lines.length; i++){
            String l[] = lines[i].split(";");
            if(matrix[0].length!=l.length){
                throw new IllegalArgumentException("false parameters");
            }
            else{
                for(int j=0; j<matrix[0].length; j++){
                    matrix[i][j] = parseRational(l[j]);
                }
            }
        }

        Matrix = matrix;
    }

    public static MatrixRational unit(int h){
        MatrixRational A = new MatrixRational(h,h);
        for(int i=0; i<h; i++){
            for (int j=0; j<h; j++){
                if(j==i){
                    A.setXY(i,j,new Rational(1));
                }
                else{
                    A.setXY(i,j,new Rational(0));
                }
            }
        }
        return A;
    }

    public int getX(){
        return this.Matrix.length;
    }

    public int getY(){
        if(this.getX()!=0){
            return this.Matrix[0].length;
        }
        else return 0;
    }

    public Rational getXY(int x, int y){
        return this.Matrix[x][y];
    }

    public void setXY(int x, int y, Rational a){
        this.Matrix[x][y] = a;
    }

    public void setLine(int x, Rational[] a){
        if(a.length==getY()){
            Matrix[x] = a;
        }
    }

    public Rational[] getLine(int x){
        return Matrix[x];
    }

    public Rational[][] getMatrix(){
        return Matrix;
    }

    public static Rational[][] matrixCopy(MatrixRational A){
        int x=A.getX();
        int y=A.getY();
        Rational[][] r = new Rational[x][y];
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                r[i][j] = new Rational(A.getXY(i,j).getP(),A.getXY(i,j).getQ());
            }
        }
        return r;
    }

    public static Rational parseRational (String s) throws IllegalArgumentException{
        if(s==null){
            throw new IllegalArgumentException("null");
        }
        String[] pq = s.split("/");
        int p = parseInt(pq[0]);

        if(pq.length>1){
            int q = parseInt(pq[1]);
            return new Rational(p,q);
        }
        return new Rational(p);
    }

    public static int parseInt (String s) throws IllegalArgumentException{
        if(s==null){
            throw new IllegalArgumentException("null");
        }
        int result=0;
        int mul=1;
        for(int i=s.length()-1; i>=0; i--){
            int num = s.charAt(i) - '1' + 1;
            if(num=='0'-'1'+1){
                num=0;
            }
            if(num<0||num>9){
                if(i==0&&s.charAt(i)=='-'){
                    result = -result;
                }
                else{
                    throw new IllegalArgumentException("NAN");
                }
            }
            else{
                result += num*mul;
                mul*=10;
            }
        }
        return result;
    }

    public static Rational[] subArray(Rational[] a, int from, int to) throws IllegalArgumentException{
        if(a==null||to>=a.length||from>to||from<0){
            throw new IllegalArgumentException("false parameters");
        }
        Rational[] b = new Rational[to+1-from];
        for(int i=0; i<to+1-from; i++){
            b[i]=a[i+from];
        }
        return b;
    }

    @Override
    public String toString(){
        StringBuffer s = new StringBuffer();
        for(int i=0; i<getX(); i++){
            s.append("|");
            for(int j=0; j<getY(); j++){
                s.append(getXY(i,j).toString() + "|");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static MatrixRational addition (MatrixRational matrixA, MatrixRational matrixB) throws IllegalArgumentException{
        if(matrixA.getX()==matrixB.getX()&&matrixA.getY()==matrixB.getY()){
            MatrixRational matrixC = new MatrixRational(matrixA.getX(),matrixA.getY());
            Rational k = Rational.zero;
            for(int i = 0; i<matrixA.getX(); i++){
                for(int j = 0; j<matrixA.getY(); j++){
                    k = matrixA.getXY(i,j).add(matrixB.getXY(i,j));
                    matrixC.setXY(i,j,k);
                }
            }
            return matrixC;
        }
        else throw new IllegalArgumentException("false parameters");
    }

    public static MatrixRational multiplication (MatrixRational A, MatrixRational B) throws IllegalArgumentException{
        if(A.getY()==B.getX()){
            MatrixRational C = new MatrixRational(A.getX(),B.getY());
            Rational sum=Rational.zero;
            for(int i=0; i<A.getX(); i++){
                for(int j=0; j<B.getY(); j++){
                    sum=new Rational(0);
                    for(int k=0; k<A.getY(); k++){
                        sum = sum.add(Rational.mul(A.getXY(i,k),B.getXY(k,j)));
                    }
                    C.setXY(i,j,sum);
                }
            }
            return C;
        }
        else throw new IllegalArgumentException("false parameters");
    }

    public MatrixRational add(MatrixRational B) throws IllegalArgumentException{
        return addition(this,B);
    }
    public MatrixRational mul(MatrixRational B) throws IllegalArgumentException{
        return multiplication(this,B);
    }

    public static Rational det(MatrixRational A){
        if(A.getX()!=A.getY()||A.getX()==0){
            return Rational.zero;
        }
        else if(A.getX()==1){
            return A.getXY(0,0);
        }
        else if(A.getX()==2){
            Rational result = A.getXY(0,0).mul(A.getXY(1,1)).sub(A.getXY(1,0).mul(A.getXY(0,1)));
            return result;
        }
        else{
            Rational result=Rational.zero;
            Rational[][] lines = new Rational[A.getX()][A.getY()-1];
            for(int i=0; i<A.getX(); i++){
                lines[i] = subArray(A.getLine(i),1,A.getY()-1);
            }

            MatrixRational B = new MatrixRational(A.getX()-1,A.getX()-1);
            for(int i=0; i<A.getX(); i++){
                int k=0;
                for(int j=0; j<A.getX(); j++){
                    if(j!=i){
                        B.setLine(k++,lines[j]);
                    }
                }
                if(i%2==0){
                    result = result.add(A.getXY(i,0).mul(det(B)));
                }
                else{
                    result = result.sub(A.getXY(i,0).mul(det(B)));
                }
            }
            return result;
        }
    }

    public Rational det(){
        return det(this);
    }

    public static MatrixRational inverse(MatrixRational a){
        int h=a.getX();
        if(h!=a.getY()){
            return null;
        }

        Rational[][] A = matrixCopy(a);
        Rational[][] B = unit(h).getMatrix();

        Stack used = new Stack();
        Stack unused = new Stack();
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=h-1; i>=0; i--){
            unused.add(i);
        }

        int k=0;
        while(k<h){
            for(Integer i : unused){
                if(!A[i][k].equals(Rational.zero)){
                    unused.remove(k);
                    used.add(k);
                    for(Integer ii : unused){
                        Rational x = A[ii][k].div(A[i][k]);
                        for(int j=0; j<h; j++){
                            A[ii][j] = A[ii][j].sub(A[i][j].mul(x));
                            B[ii][j] = B[ii][j].sub(B[i][j].mul(x));
                        }
                        System.out.println(new MatrixRational(A));
                        System.out.println("------");
                        System.out.println(new MatrixRational(B));
                    }
                    System.out.println(used + " ----- " + unused);
                    System.out.println(i + "------");
                    map.put(i,k);
                    k++;
                    break;
                }

            }
        }


        for(Integer i : used){
            for(Integer ii : unused){
                Rational x = A[i][map.get(ii)].div(A[ii][map.get(ii)]);
                A[i][map.get(ii)] = new Rational(0);
                for(int j=0; j<h; j++){
                    B[i][j] = B[i][j].sub(B[ii][j].mul(x));
                }
            }
            for(int j=0; j<h; j++){
                B[i][j] = B[i][j].div(A[i][map.get(i)]);
            }
            A[i][map.get(i)] = new Rational(1);
            unused.add(i);
        }

        return new MatrixRational(B);
    }

    public static void main(String[] args) {

        /*
        String t="2;1;1\n1;2;3\n1;2;4";
        MatrixRational e = new MatrixRational(t);
        System.out.println(e);
        System.out.println(inverse(e));
        System.out.println();
        System.out.println(multiplication(e,inverse(e)));

         */

        String tt="1;2;3\n1;2;4\n2;1;1";
        MatrixRational ee = new MatrixRational(tt);
        System.out.println(ee);
        System.out.println(inverse(ee));
        System.out.println();
        //System.out.println(multiplication(ee,inverse(ee)));

    }
}


