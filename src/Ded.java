public class Ded extends Thread{

    private Ded partner;
    private int number;
    public Ded(int n){number = n;}

    public void setPartner(Ded p){
        partner = p;
    }
    public void run(){
        System.out.println(number + " hey");
        try {
            partner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(number + " heyo");
    }

    public static void main(String[] args) {
        var d1 = new Ded(1);
        var d2 = new Ded(2);
        var d3 = new Ded(3);

        d1.setPartner(d2);
        d2.setPartner(d3);
        d3.setPartner(d1);

        d1.start();
        d2.start();
        d3.start();
        System.out.println("started");

        try{
            d1.join();
            d2.join();
            d3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("end");
        }
    }
}
