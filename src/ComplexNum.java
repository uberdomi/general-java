import java.math.*;
public class ComplexNum {
    private int Re;
    private int Im;

    public ComplexNum(int x, int y){
        Re = x;
        Im = y;
    }

    public int getRe(){
        return Re;
    }
    public int getIm(){
        return Im;
    }

    public static ComplexNum Rotate (int a1, int a2, int b1, int b2, int c1, int c2){
        int x = b1-c1;
        int y = b2-c2;

        int xx = x*a1 - y*a2;
        int yy = y*a1 +x*a2;

        xx += c1;
        yy += c2;

        return new ComplexNum(xx,yy);
    }
    public static ComplexNum Addition (ComplexNum z1, ComplexNum z2){
        int a = z1.Re + z2.Re;
        int b = z1.Im + z2.Im;
        return new ComplexNum(a,b);
    }

    public static ComplexNum Multiplication (ComplexNum z1, ComplexNum z2){
        int a = z1.Re*z2.Re - z1.Im*z2.Im;
        int b = z1.Im*z2.Re + z1.Re*z2.Im;
        return new ComplexNum(a,b);
    }

    public static double AbsoluteValue (ComplexNum z){
        int a = z.getRe();
        int b = z.getIm();
        int abs = a*a + b*b; //
        //ComplexNum zConjugate = new ComplexNum(z.getRe(),-z.getIm());
        //ComplexNum Absolute = Multiplication(z,zConjugate);
        //return Math.sqrt((double) Absolute.getRe());
        return Math.sqrt((double) abs);
    }

    public static ComplexNum Rotate1 (ComplexNum DegreeRotation,ComplexNum RotatedNum,ComplexNum PointRotation){
        ComplexNum NegPointRotation = new ComplexNum((-1)*PointRotation.getRe(),(-1)*PointRotation.getIm());
        ComplexNum TransformedNum = Addition(RotatedNum, NegPointRotation);
        TransformedNum = Multiplication(TransformedNum, DegreeRotation);
        return Addition(TransformedNum, PointRotation);
    }

    public static void main(String[] args) {
        ComplexNum a = new ComplexNum(0,1);
        ComplexNum b = new ComplexNum(4,2);
        ComplexNum c = new ComplexNum(3,1);
        System.out.println(Rotate(0,1,4,2,3,1).getRe());
        System.out.println(Rotate(0,1,4,2,3,1).getIm());
        System.out.println(Rotate1(a,b,c).getRe());
        System.out.println(Rotate1(a,b,c).getIm());
    }
}
