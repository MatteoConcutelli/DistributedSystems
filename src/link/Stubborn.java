package link;

import ds.util.Message;

public class Stubborn implements Link {

    FairLossy fairLossyLink;
    private static int max_to_send = 10;

    Perfect perfectLink = null;

    public Stubborn() {
        this.fairLossyLink = new FairLossy(this);
    }

    // for the upper layer
    public Stubborn(Perfect perfectLink) {
        this();
        this.perfectLink = perfectLink;
    }

    public void send(Message message) {

        for (int i = 0; i < max_to_send; i++) {
            fairLossyLink.send(message);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void deliver(Message message) {
        if (perfectLink != null) {
            perfectLink.deliver(message);
            return;
        }
        message.getReceiver().receive(message);
    }

    public static void setMax_to_send(int max_to_send) {
        Stubborn.max_to_send = max_to_send;
    }
}
