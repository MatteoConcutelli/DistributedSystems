package lamport;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import ds.util.LogicalClock;
import ds.util.Message;
import ds.util.Process;
import link.Link;
import link.Perfect;


public class LamportProcess extends Process {

    private final LogicalClock scalar_clock;   // logical clock

    private MEMessage my_request = null;

    private final PriorityBlockingQueue<MEMessage> request_queue;
    private final boolean[] reply_received;

    public LamportProcess() {
        this.scalar_clock = new LogicalClock();

        this.request_queue = new PriorityBlockingQueue<>(11,Comparator.comparingInt(MEMessage::getTimestamp));
        this.reply_received = new boolean[number_of_processes + 1];
        this.reply_received[0] = true;

    }

    @Override
    public void run() {
        try {
            // Simulate random work before requesting the critical section
            Thread.sleep(new Random().nextInt(1000));
            requestCriticalSection();

            // Simulate work inside the critical section
            Thread.sleep(500);

            releaseCriticalSection();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void requestCriticalSection() {

        my_request = new MEMessage(MEMessage.Type.REQ, this.getID(), scalar_clock.getClock());

        System.out.println("\n\n Process " + getID()+ " requested critical section at time " + my_request.getTimestamp() + "!!\n\n");
        sendMessageToAll(my_request);

        while (!canEnterCriticalSection()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted");
                }
            }
        }

        enterCriticalSection();
    }

    private void releaseCriticalSection() {

        my_request = null;
        this.scalar_clock.increaseClock();

        MEMessage release_message = new MEMessage(MEMessage.Type.RLS, this.getID(), scalar_clock.getClock());
        sendMessageToAll(release_message);
        System.out.println("Process " + this.getID() + " released critical section at time " + release_message.getTimestamp());
        resetAcks();

    }

    private void sendMessageToAll(MEMessage message) {
        this.scalar_clock.increaseClock();
        for (Process p: processes.values()) {
            if (p.getID() != getID()) {
                send(new Perfect(), p, message);
            }
        }
        this.send(new Perfect(),this, message);
    }

    private void receiveMessage(Message message) {

        int sender_id = message.getSender().getID();
        int receiver_id = message.getReceiver().getID();

        MEMessage mem_message = ((MEMessage)message.getPayload());
        scalar_clock.updateClock(mem_message.getTimestamp());

        switch (mem_message.getType()) {
            case REQ:
                System.out.println(receiver_id + ": REQ received from process " + sender_id + " at time " + mem_message.getTimestamp() + "\n");
                request_queue.add(mem_message);
                reply(mem_message.getProcess_id());
                break;
            case RLS:
                System.out.println(receiver_id + ": RLS received from process " + sender_id + " at time " + mem_message.getTimestamp() + "\n");
                request_queue.poll();
                break;
            case ACK:
                System.out.println(receiver_id + ": ACK received from process " + sender_id + " at time " + mem_message.getTimestamp() + "\n");
                reply_received[mem_message.getProcess_id()] = true;
                break;
        }
    }

    private void reply(int to_process_id) {
        MEMessage replyMessage = new MEMessage(MEMessage.Type.ACK, getID(), scalar_clock.getClock());
        receive(new Message(this, getProcessFromID(to_process_id),replyMessage));
    }

    private synchronized boolean canEnterCriticalSection() {
        notifyAll();
        MEMessage top_request = request_queue.peek();   // read the last request on the queue
        return top_request != null && top_request.getProcess_id() == this.getID() && allRepliesReceived();
    }

    private boolean allRepliesReceived() {
        for (boolean received : reply_received) {
            if (!received) {
                return false;
            }
        }
        System.out.println(this.getID() + ": all reply are received!! at time " + scalar_clock.getClock());
        return true;
    }

    private void enterCriticalSection() {
        System.out.println(this.getID() + ": entered critical section at time " + scalar_clock.getClock());
    }

    private void resetAcks() {
        Arrays.fill(this.reply_received, false);
        this.reply_received[0] = true;
    }


    /* OVERRIDE */
    @Override
    public void send(Link link, Process receiver, Object payload) {
        super.send(link, receiver, payload);
    }

    @Override
    public void receive(Message message) {
        super.receive(message);
        receiveMessage(message);
    }

}
