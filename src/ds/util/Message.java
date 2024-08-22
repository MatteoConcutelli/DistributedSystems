package ds.util;

public class Message {

    private final Process sender;
    private final Process receiver;

    private final Object payload;

    public Message(Process sender, Process receiver, Object payload) {
        this.sender = sender;
        this.receiver = receiver;
        this.payload = payload;

    }

    public Process getSender() {
        return this.sender;
    }

    public Process getReceiver() {
        return this.receiver;
    }

    public Object getPayload() {
        return this.payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", payload='" + payload + '\'' +
                '}';
    }
}
