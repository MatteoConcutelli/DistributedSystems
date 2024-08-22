/*
    HOW TO USE THIS PROJECT

    This project is an implementation of what I did in my Distributed System course. In particular is an implementation
    of the first modelling and abstraction did in the course.

    Here we talk about processes and messages. Processes want to communicate, but they need a channel in which they
    communicate, these channels are called Links. Using a link two processes are able to send each other, messages.

    The abstraction consist into start from the model of a physical link (I simulate the physical link with the
    FairLossy class) and add layers on it until we reach the Perfect link point to point.


    Important: here there is no concept of time, for example in the stubborn link there is no delta that the process
                would have to wait between two sends.
 */


import ds.util.Process;
import link.FairLossy;
import link.Link;
import link.Perfect;
import link.Stubborn;

public class MainPP2P {
    public static void main(String[] args) {

        Process p1 = new Process();
        Process p2 = new Process();

        Link fairLossyLink = new FairLossy();
        Link stubbornLink = new Stubborn();
        Link perfectLink = new Perfect();


        p1.send(fairLossyLink, p2, "Hi from process 1 using the FairLossyLink!\n ");
        p1.send(stubbornLink, p2, "Hi from process 1 using the StubbornLink!\n ");
        p1.send(perfectLink, p2, "Hi from process 1 using the PerfectLink!\n ");

    }
}