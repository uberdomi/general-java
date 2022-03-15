public class Monitor {
    private boolean running;

    public Monitor(){
        running = false;
    }

    public synchronized void start() throws InterruptedException {
        while(running){
            wait();
        }
        running = true;
    }

    public synchronized void end(){
        running = false;
        notify();
    }
}
