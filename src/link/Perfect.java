package link;

import ds.util.Message;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Perfect implements Link {

    private final Stubborn stubbornLink;
    Set<Message> delivered;

    public Perfect() {
        this.stubbornLink = new Stubborn(this);
        delivered = new HashSet<>();
    }

    public void send(Message message) {
        stubbornLink.send(message);
    }

    public void deliver(Message message) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            if (!delivered.contains(message)) {
                delivered.add(message);
                message.getReceiver().receive(message);
            }
        });
        executor.shutdown();
    }
}
