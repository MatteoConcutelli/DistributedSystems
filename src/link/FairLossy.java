package link;

import ds.util.Message;
import java.util.Random;

public class FairLossy implements Link {

    private final Random random;
    private double lossProb;

    private Stubborn stubbornLink = null;

    public FairLossy () {
        random = new Random();
        this.lossProb = random.nextDouble();
        this.lossProb = (this.lossProb - (-3)) / (3 - (-3)); // Normalizzazione tra 0 e 1
        this.lossProb = Math.max(0, Math.min(1, this.lossProb)); // Clamping tra 0 e 1

    }

    // for the upper layer
    public FairLossy(Stubborn stubborn) {
        this();
        this.stubbornLink = stubborn;
    }

    public void send(Message message) {
        if (random.nextDouble() > lossProb) {
            this.deliver(message);
        }
        this.lossProb = random.nextGaussian();
    }

    public void deliver(Message message) {
        if (stubbornLink != null) {
            stubbornLink.deliver(message);
            return;
        }
        message.getReceiver().receive(message);
    }

}
