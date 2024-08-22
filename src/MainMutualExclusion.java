import lamport.LamportProcess;

public class MainMutualExclusion {
    public static void main(String[] args) {

        LamportProcess p1 = new LamportProcess();
        LamportProcess p2 = new LamportProcess();

        p1.start();
        p2.start();

    }
}
