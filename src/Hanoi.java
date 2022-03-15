

public class Hanoi {
    private int topA;
    private int topB;
    private int topC;
    private int[] A;
    private int[] B;
    private int[] C;
    private int height;
    private int no;

    public Hanoi (int height){
        topA = height - 1;
        topB = -1;
        topC = -1;
        A = new int[height];
        B = new int[height];
        C = new int[height];
        for(int i=0; i<height; i++){
            A[i] = height - i;
            B[i] = 0;
            C[i] = 0;
        }
        this.height = height;
        no = 1;
    }

    public byte free(byte from, byte to){
        return (byte) (3 - from - to);
    }

    public void Tower(){
        Output();
        Move(this.height,(byte)0,(byte)2);
    }

    public void Output(){
        System.out.println(no + "------");
        no++;
        for(int i=height-1; i>=0; i--){
            System.out.println("|" + A[i] + "|" + B[i] + "|" + C[i] + "|");
        }
        System.out.println("-------");
        System.out.println();
    }

    public void Rearrange(byte from, byte to){
        int moved = 0;
        switch (from){
            case 0 -> {
                moved = A[topA];
                A[topA] = 0;
                topA--;
            }
            case 1 -> {
                moved = B[topB];
                B[topB] = 0;
                topB--;
            }
            case 2 -> {
                moved = C[topC];
                C[topC] = 0;
                topC--;
            }
        }

        switch (to){
            case 0 -> {
                topA++;
                A[topA] = moved;
            }
            case 1 -> {
                topB++;
                B[topB] = moved;
            }
            case 2 -> {
                topC++;
                C[topC] = moved;
            }
        }
    }

    public void Move (int height, byte from, byte to){
        if(height>0){
            byte free = free(from,to);
            Move(height-1,from,free);
            Rearrange(from,to);
            Output();
            Move(height-1,free,to);
        }
    }

    public static void main(String[] args) {
        Hanoi h = new Hanoi(11);
        h.Tower();
       //System.out.println(Math.log(8)/Math.log(2));
    }

}
