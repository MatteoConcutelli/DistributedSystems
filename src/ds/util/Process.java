package ds.util;

import link.Link;

import java.util.HashMap;
import java.util.Map;

public class Process extends Thread {

    private int id;
    private static int counter_id = 0;  // for the assignment of the ID

    protected static int number_of_processes = 0;
    // (ID, process)
    protected static Map<Integer, Process> processes = new HashMap<>();

    public Process() {
        counter_id++;
        id = counter_id;

        processes.put(id, this);
        number_of_processes++;

    }

    @Override
    public void run() {
        while(true){

        }
    }

    public void send(Link link, Process receiver, Object payload) {
        link.send(new Message(this, receiver, payload));
    }

    public void receive(Message message) {
        //System.out.println(this.id + ": received from process " + message.getSender().getID() + "\"" + message.getPayload() + "\"");
    }

    /*GETTERS AND SETTERS */

    public int getID() {
        return this.id;
    }

    public static Process getProcessFromID(int id) {
        return processes.get(id);
    }

}

