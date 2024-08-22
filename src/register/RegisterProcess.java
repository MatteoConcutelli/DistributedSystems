package register;

import ds.util.Message;
import ds.util.Process;

public class RegisterProcess extends Process {

    private static Process writer;
    RegularRegister local_register;

    public RegisterProcess() {
        this.local_register = new RegularRegister();

    }

    public void readFromTheRegister() {
        local_register.read();
    }

    public static void setWriter(Process writer) {
        RegisterProcess.writer = writer;
    }

    public void writeOnTheRegister() {
        if (!this.equals(writer)) {
            return;
        }

        // TODO bebcast
    }
/*
    @Override
    public void deliver(Message message) {
        super.deliver(message);
        //local_register.write(message.getPayload());
    }
*/
}
