package RationalMatrix;

public class Matrix {
    private Rational[][] matrix;

    public Matrix(int x, int y){
        this.matrix = new Rational[x][y];
    }

    public Matrix(Rational[][] m){
        this.matrix = m;
    }

    public Matrix(String s) throws IllegalArgumentException{
        String lines[] = s.split("\n");
        Rational[][] matrix = new Rational[lines.length][lines[0].split(";").length];

        for(int i=0; i< lines.length; i++){
            String l[] = lines[i].split(";");
            if(matrix[0].length!=l.length){
                throw new IllegalArgumentException("false parameters");
            }
            else{
                for(int j=0; j<matrix[0].length; j++){
                    matrix[i][j] = Rational.parseRational(l[j]);
                }
            }
        }

        this.matrix = matrix;
    }

    public Matrix(int n, String s) throws IllegalArgumentException{
        String nums[] = s.split(";");
        if(nums.length!=n*n){
            throw new IllegalArgumentException("not quadratic");
        }
        Rational[][] matrix = new Rational[n][n];
        for(int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                matrix[i][j] = new Rational(nums[n*i+j]);
            }
        }
        this.matrix = matrix;
    }

    public static Matrix unit(int h){
        Matrix A = new Matrix(h,h);
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

    public static Matrix big(int h){
        Matrix A = new Matrix(h,h);
        for(int i=0; i<h; i++){
            for (int j=0; j<h; j++){
                A.setXY(i,j,new Rational(((i * 5L) + (j * 7L)) / 13));
                //i%(j+1)==0 ? new Rational(2) : new Rational(1)
            }
        }
        return A;
    }

    public int getX(){
        return this.matrix.length;
    }

    public int getY(){
        if(this.getX()!=0){
            return this.matrix[0].length;
        }
        else return 0;
    }

    public Rational getXY(int x, int y) throws IllegalArgumentException{
        if(x<getX()&&y<getY()&&x>=0&&y>=0){
            return this.matrix[x][y];
        }
        throw new IllegalArgumentException("out of bounds!");
    }

    public void setXY(int x, int y, Rational a) throws IllegalArgumentException{
        if(x<getX()&&y<getY()&&x>=0&&y>=0){
            this.matrix[x][y] = a;
            return;
        }
        throw new IllegalArgumentException("out of bounds!");
    }

    public void setLine(int x, Rational[] a) throws IllegalArgumentException{
        if(x<getX()&&x>=0&&a.length==getY()){
            matrix[x] = a;
            return;
        }
        throw new IllegalArgumentException("out of bounds!");
    }

    public Rational[] getLine(int x) throws IllegalArgumentException{
        if(x<getX()&&x>=0){
            return matrix[x];
        }
        throw new IllegalArgumentException("out of bounds!");
    }

    public Rational[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(Rational[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix T (){
        Matrix A = new Matrix(getY(),getX());
        for(int i=0; i<getX(); i++){
            for (int j=0; j<getY(); j++){
                A.setXY(j,i,getXY(i,j).copy());
            }
        }
        return A;
    }

    public static Rational[][] matrixCopy(Matrix A){
        int x=A.getX();
        int y=A.getY();
        Rational[][] r = new Rational[x][y];
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                r[i][j] = Rational.copy(A.getXY(i,j));
            }
        }
        return r;
    }

    public Matrix copy(){
        return new Matrix(matrixCopy(this));
    }

    public static Rational[] subArray(Rational[] a, int from, int to) throws IllegalArgumentException{
        if(a==null||to>=a.length||from>to||from<0){
            throw new IllegalArgumentException("false parameters");
        }
        Rational[] b = new Rational[to+1-from];
        for(int i=0; i<to+1-from; i++){
            b[i]=Rational.copy(a[i+from]);
        }
        return b;
    }

    public void swapLines (int x, int y){
        if(x!=y){
            Rational[] tmp = getLine(x);
            setLine(x,getLine(y));
            setLine(y,tmp);
        }
    }

    public Matrix add(Matrix A) throws IllegalArgumentException{
        if(A==null||A.getX()!=getX()||A.getY()!=getY()){
            throw new IllegalArgumentException("false parameters!");
        }
        Matrix B = new Matrix(getX(),getY());
        for(int i=0; i<getX(); i++){
            for(int j=0; j<getY(); j++){
                B.setXY(i,j,getXY(i,j).add(A.getXY(i,j)));
            }
        }
        return B;
    }

    public Matrix mul(Matrix A) throws IllegalArgumentException{
        if(A==null||A.getX()!=getY()){
            throw new IllegalArgumentException("false parameters!");
        }
        Matrix B = new Matrix(getX(),A.getY());
        for(int i=0; i<getX(); i++){
            for(int j=0; j<A.getY(); j++){
                Rational r = new Rational(0);
                for(int k=0; k<getY(); k++){
                    r = r.add(getXY(i,k).mul(A.getXY(k,j)));
                }
                B.setXY(i,j,r);
            }
        }
        return B;
    }

    public Matrix mul(Rational x){
        Matrix B = copy();
        for(int i=0; i<getX(); i++){
            for(int j=0; j<getY(); j++){
                B.setXY(i,j,getXY(i,j).mul(x));
            }
        }
        return B;
    }

    public Rational det(){
        if(getX()!=getY()){
            return new Rational(0);
        }
        if(getX()==1){
            return getXY(0,0);
        }
        Rational result= new Rational(1);
        byte sgn = 1;
        int h=getX();
        Matrix A = copy();
        for(int i=0; i<h; i++){
            int l=i;
            while (A.getXY(l,i).equals(0)){
                l++;
                sgn *= -1;
                if(l==h){
                    //System.out.println(A);
                    return new Rational(0);
                }
            }

            A.swapLines(l,i);

            for(int j=i+1;j<h;j++){
                Rational a = A.getXY(j,i).div(A.getXY(i,i));
                for(int k=i; k<h; k++){
                    A.setXY(j,k,A.getXY(j,k).sub(A.getXY(i,k).mul(a)));
                }
            }
        }
        for(int i=0; i<h; i++){
            result = result.mul(A.getXY(i,i));
        }
        //System.out.println(A);
        return result.mul(sgn);
    }
    public Rational detRec(){

        if(getX()!=getY()){
            return new Rational(0);
        }
        if(getX()==1){
            return getXY(0,0);
        }
        Rational result= new Rational(0);
        Rational[][] lines = new Rational[getX()][getY()-1];
        for(int i=0; i<getX(); i++){
            lines[i] = subArray(getLine(i),1,getY()-1);
        }

        Matrix B = new Matrix(getX()-1,getX()-1);
        for(int i=0; i<getX(); i++){
            int k=0;
            for(int j=0; j<getX(); j++){
                if(j!=i){
                    B.setLine(k++,lines[j]);
                }
            }
            if(i%2==0){
                result = result.add(getXY(i,0).mul(B.det()));
            }
            else{
                result = result.sub(getXY(i,0).mul(B.det()));
            }
        }
        return result;
    }

    public Rational detThr(){
        if(getX()!=getY()){
            return new Rational(0);
        }
        Rational result[]= new Rational[1];
        detThread d = new detThread(this,result, 0);
        d.start();
        try {
            d.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }


    public Matrix inv(){
        if(det().equals(0)){
            return null;
        }
        int h=getX();
        Matrix A = copy();
        Matrix B = unit(h);
        for(int i=0; i<h; i++){
            int l=i;
            while (A.getXY(l,i).equals(0)){
                l++;
            }
            A.swapLines(l,i);
            B.swapLines(l,i);

            for(int j=i+1;j<h;j++){
                Rational a = A.getXY(j,i).div(A.getXY(i,i));
                for(int k=0; k<h; k++){
                    A.setXY(j,k,A.getXY(j,k).sub(A.getXY(i,k).mul(a)));
                    B.setXY(j,k,B.getXY(j,k).sub(B.getXY(i,k).mul(a)));
                }
            }
        }

        for(int i=h-1;i>=0;i--){
            Rational a = A.getXY(i,i);
            A.setXY(i,i,new Rational(1));
            for(int k=0; k<h; k++){
                B.setXY(i,k,B.getXY(i,k).div(a));
            }

            for(int j=i-1; j>=0; j--){
                Rational b = A.getXY(j,i);
                A.setXY(j,i,new Rational(0));
                for(int k=0; k<h; k++){
                    B.setXY(j,k,B.getXY(j,k).sub(B.getXY(i,k).mul(b)));
                }
            }

        }
        return B;
    }

    public Matrix invThr(){
        Rational det = det();
        if(det.equals(0)){
            return null;
        }
        int h=getX();
        Matrix C = new Matrix(h,h);
        invThread[][] t = new invThread[h][h];
        for(int i=0; i<h; i++){
            for(int j=0; j<h; j++){
                t[i][j] = new invThread(i,j,this,C,det);
                t[i][j].start();
            }
        }
        for(int i=0; i<h; i++){
            for(int j=0; j<h; j++){
                try {
                    t[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return C.T();
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i=0; i<getX(); i++){
            s.append("|");
            for(int j=0; j<getY(); j++){
                s.append(getXY(i, j).toString()).append("|");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private class detThread extends Thread{
        private Matrix A;
        private Rational[] sums;
        private final int k;
        private detThread(Matrix A, Rational[] sums, int k){
            this.A = A;
            this.sums = sums;
            this.k = k;
        }
        @Override
        public void run(){
            int h = A.getX();
            if(h==1){
                sums[k] = A.getXY(0,0);
            }
            else{
                Rational sum = new Rational(0);
                Rational[][] lines = new Rational[h][h-1];
                for(int i=0; i<h; i++){
                    lines[i] = subArray(A.getLine(i),1,h-1);
                }
                Matrix[] Bs = new Matrix[h];
                Rational[] newSums = new Rational[h];
                detThread[] threads = new detThread[h];
                for(int i=0; i<h; i++){
                    int l=0;
                    Bs[i] = new Matrix(h-1,h-1);
                    for(int j=0; j<h; j++){
                        if(j!=i){
                            Bs[i].setLine(l++,lines[j]);
                        }
                    }
                    threads[i] = new detThread(Bs[i],newSums,i);
                    threads[i].start();
                }

                for(int i=0; i<h; i++){
                    try {
                        threads[i].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for(int i=0; i<h; i++){
                    if(i%2==0){
                        sum = sum.add(A.getXY(i,0).mul(newSums[i]));
                    }
                    else{
                        sum = sum.sub(A.getXY(i,0).mul(newSums[i]));
                    }
                }

                sums[k] = sum;
            }
        }
    }

    private class invThread extends Thread{
        private final int x;
        private final int y;
        private final Matrix A;
        private Matrix C;
        private final Rational det;

        public invThread(int x, int y, Matrix A, Matrix C, Rational det){
            this.x = x;
            this.y = y;
            this.A = A;
            this.C = C;
            this.det = det;
        }


        @Override
        public void run(){
            int h = A.getX();
            Matrix B = new Matrix(h,h);
            for(int i=0; i<h; i++){
                for(int j=0; j<h; j++){
                    if(i==x&&j==y){
                        B.setXY(i,j,new Rational(1));
                    }
                    else if(i==x||j==y){
                        B.setXY(i,j,new Rational(0));
                    }
                    else{
                        B.setXY(i,j,A.getXY(i,j));
                    }
                }
            }
            C.setXY(x,y,B.det().div(det));
        }
    }

    public static void main(String[] args) {
        /*
        Matrix D = big(10);

        System.out.println(D);
        System.out.println(D.det());
        System.out.println("---------------------");


        long startTime = System.nanoTime();
        System.out.println(D.inv());
        long endTime = System.nanoTime();
        long duration1 = (endTime - startTime);
        System.out.println("time: "+ duration1);
        startTime = System.nanoTime();
        System.out.println(D.invThr());
        endTime = System.nanoTime();
        long duration2 = (endTime - startTime);
        System.out.println("time: "+ duration2);
        System.out.println("ratio: " + (double) duration1/duration2);

         */

        Matrix A = new Matrix(3,"1;2;3;4;5;6;7;8;8");
        System.out.println(A);
        System.out.println(A.detRec());
        System.out.println(A.detThr());
        System.out.println(A.det());
        Matrix D = big(14);
        //System.out.println(D.det());
        System.out.println(D);
        System.out.println(D.det());
        Matrix DI = D.inv();
        System.out.println(DI);
        System.out.println(D.mul(DI));

    }
}
