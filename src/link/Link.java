package link;

import ds.util.Message;

public interface Link {

    void send(Message message);
    void deliver(Message message);

}
