package lamport;


import ds.util.LogicalClock;

public class MEMessage {

    public enum Type {
        REQ,
        ACK,
        RLS
    }

    Type type;

    private int process_id;
    private int timestamp;

    public MEMessage(Type type, int process_id, int timestamp) {

        this.type = type;

        this.process_id = process_id;
        this.timestamp = timestamp;
    }

    public int getProcess_id() {
        return process_id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MEMessage{" +
                "type=" + type + '}';
    }
}
