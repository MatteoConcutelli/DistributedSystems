package failuredetector;

import ds.util.Message;
import ds.util.Process;
import link.Link;
import link.Perfect;

import java.util.*;
/*
public class ExcludeOnTimeout extends FailureDetector {


    private Map<Integer, Process> alive;
    private Set<Process> detected;
    private final int timer;

    private boolean is_crashed = false;

    public ExcludeOnTimeout(int timer) {
        super();
        this.alive = new HashMap<>(processes);
        this.detected = new HashSet<>();
        this.timer = timer;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (this) {
                try {
                    wait(this.timer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Process p: processes.values()) {
                    if (!(alive.containsKey(p.getID())) && !(detected.contains(p))) {
                        detected.add(p);
                        System.out.println("Process: " + p.getId() + ", is crashed!!");
                    }
                }

                this.alive.clear();

                for (Process p: processes.values()) {
                    this.send(new Perfect(10), new Message(this.id, p.getID(), "HBREQUEST"));
                    System.out.println("HB request sent to the process " + p.getId() + " !");
                }

                System.out.println("End sending requests.\n\n");

            }
        }
    }

    public void crash() {
        synchronized (this) {
            try {
                is_crashed = true;
                wait(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void deliver(Message message) {
        if (!is_crashed) {
            super.deliver(message);
            if ("HBREQUEST".equals(message.getPayload())) {
                this.send(new Perfect(10), new Message(this, message.getSender(), "HBREPLY"));

            }
            else if ("HBREPLY".equals(message.getPayload())) {
                System.out.println("HB reply received from process " + message.getSender().getId() + " !");
                alive.put(message.getSender().getId(), message.getSender());
            }
        }
    }

    @Override
    public void send(Link link, Message message) {
        super.send(link, message);
    }

}
*/
