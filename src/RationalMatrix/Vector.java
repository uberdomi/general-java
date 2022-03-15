package RationalMatrix;

public class Vector extends Matrix{
    public Vector(int x, int y) throws IllegalArgumentException{
        super(x,1);
        if(y!=1){
            throw new IllegalArgumentException("false parameters");
        }
    }

    public Vector(int x){
        super(x,1);
    }

    public Vector(Rational[][] m) throws IllegalArgumentException{
        super(m);
        if(m==null||m[0].length!=1){
            throw new IllegalArgumentException("false parameters");
        }
    }

    public Vector(Rational... params){
        super((Rational[][]) null);
        Rational[][] matrix = new Rational[params.length][1];
        for(int i=0; i< params.length; i++){
            matrix[i][0] = params[i];
        }
        setMatrix(matrix);
    }

    public Vector(String s) throws IllegalArgumentException{
        super((Rational[][]) null);
        String params[] = s.split(";");
        Rational[][] matrix = new Rational[params.length][1];

        for(int i=0; i< params.length; i++){
            matrix[i][0] = Rational.parseRational(params[i]);
        }
        setMatrix(matrix);
    }

    public Rational scal(Vector v){
        Matrix tmp = T();
        return tmp.mul(v).getXY(0,0);
    }

    public static void main(String[] args) {
        Vector v = new Vector("1;2/3;3/2;4;5");
        Vector u = new Vector("1;1;1;1;2");
        Matrix A = new Matrix("1;0;1;2;0\n1;1;2;2;3");
        System.out.println(v);
        System.out.println(A);
        System.out.println(A.mul(v));
        System.out.println(u);
        System.out.println(v.scal(u));
        Rational[] r = new Rational[10];
        for(int i=0; i<10; i++){
            r[i] = new Rational(i+1,2*i+1);
        }
        Vector w = new Vector(r);
        System.out.println(w);
    }
}
