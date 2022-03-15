import java.util.HashMap;
import java.util.Map;

public class Matrix {
    private int[][] Matrix;

    public Matrix(int x, int y){
        this.Matrix = new int[x][y];
    }

    public Matrix(String s) throws IllegalArgumentException{
        String lines[] = s.split("\n");
        int[][] matrix = new int[lines.length][lines[0].split(";").length];

        for(int i=0; i< lines.length; i++){
            String l[] = lines[i].split(";");
            if(matrix[0].length!=l.length){
                throw new IllegalArgumentException("false parameters");
            }
            else{
                for(int j=0; j<matrix[0].length; j++){
                    matrix[i][j] = parseInt(l[j]);
                }
            }
        }

        Matrix = matrix;
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

    public int getXY(int x, int y){
        return this.Matrix[x][y];
    }

    public void setXY(int x, int y, int a){
        this.Matrix[x][y] = a;
    }

    public void setLine(int x, int[] a){
        if(a.length==getY()){
            Matrix[x] = a;
        }
    }

    public int[] getLine(int x){
        return Matrix[x];
    }

    public int[][] getMatrix(){
        return Matrix;
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

    public static int[] subArray(int[] a, int from, int to) throws IllegalArgumentException{
        if(a==null||to>=a.length||from>to||from<0){
            throw new IllegalArgumentException("false parameters");
        }
        int[] b = new int[to+1-from];
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
                s.append(getXY(i,j) + "|");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static Matrix addition (Matrix matrixA, Matrix matrixB) throws IllegalArgumentException{
        if(matrixA.getX()==matrixB.getX()&&matrixA.getY()==matrixB.getY()){
            Matrix matrixC = new Matrix(matrixA.getX(),matrixA.getY());
            int k = 0;
            for(int i = 0; i<matrixA.getX(); i++){
                for(int j = 0; j<matrixA.getY(); j++){
                    k = matrixA.getXY(i,j) + matrixB.getXY(i,j);
                    matrixC.setXY(i,j,k);
                }
            }
            return matrixC;
        }
        else throw new IllegalArgumentException("false parameters");
    }

    public static Matrix multiplication (Matrix A, Matrix B) throws IllegalArgumentException{
        if(A.getY()==B.getX()){
            Matrix C = new Matrix(A.getX(),B.getY());
            int sum=0;
            for(int i=0; i<A.getX(); i++){
                for(int j=0; j<B.getY(); j++){
                    sum=0;
                    for(int k=0; k<A.getY(); k++){
                        sum += A.getXY(i,k)*B.getXY(k,j);
                    }
                    C.setXY(i,j,sum);
                }
            }
            return C;
        }
        else throw new IllegalArgumentException("false parameters");
    }

    public Matrix add(Matrix B) throws IllegalArgumentException{
        return addition(this,B);
    }
    public Matrix mul(Matrix B) throws IllegalArgumentException{
        return multiplication(this,B);
    }

    public static int det(Matrix A){
        if(A.getX()!=A.getY()||A.getX()==0){
            return 0;
        }
        else if(A.getX()==1){
            return A.getXY(0,0);
        }
        else if(A.getX()==2){
            int result = A.getXY(0,0)*A.getXY(1,1)-A.getXY(1,0)*A.getXY(0,1);
            return result;
        }
        else{
            int result=0;
            int[][] lines = new int[A.getX()][A.getY()-1];
            for(int i=0; i<A.getX(); i++){
                lines[i] = subArray(A.getLine(i),1,A.getY()-1);
            }

            Matrix B = new Matrix(A.getX()-1,A.getX()-1);
            for(int i=0; i<A.getX(); i++){
                int k=0;
                for(int j=0; j<A.getX(); j++){
                    if(j!=i){
                        B.setLine(k++,lines[j]);
                    }
                }
                if(i%2==0){
                    result+= A.getXY(i,0)*det(B);
                }
                else{
                    result-= A.getXY(i,0)*det(B);
                }
            }
            return result;
        }
    }

    public int det(){
        return det(this);
    }

    public static Rational[][] inverse(Matrix a){
        int h=a.getX();
        if(h!=a.getY()){
            return null;
        }

        Rational[][] A = new Rational[h][h];
        for(int i=0; i<h; i++){
            for (int j=0; j<h; j++){
                A[i][j] = new Rational(a.getXY(i,j));
            }
        }
        Rational[][] B = new Rational[h][h];
        for(int i=0; i<h; i++){
            for (int j=0; j<h; j++){
                if(j==i){
                    B[i][j] = new Rational(1);
                }
                else{
                    B[i][j] = new Rational(0);
                }
            }
        }

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
                    }
                    map.put(i,k);
                    break;
                }
            }
            k++;
        }


        for(Integer i : used){
            for(Integer ii : unused){
                Rational x = A[i][map.get(ii)].div(A[ii][map.get(ii)]);
                A[i][map.get(ii)] = new Rational(0);
                for(int j=0; j<h; j++){
                    B[i][j] = B[i][j].sub(B[ii][j].mul(x));
                }
            }
            unused.add(i);
        }

        return B;
    }

    public static void main(String[] args) {
        Matrix a = new Matrix("1;0;2;1\n0;2;3;1\n1;-1;0;0\n1;0;1;1");
        System.out.println(a);
        System.out.println(det(a));
    }
}
