package register;

/*
* (1, N) Regular Register
*
* Termination: if a correct process invokes an operation, then the operation eventually receives the corresponding
*               confirmation.
*
* Validity: A read that is not concurrent with a write returns the last value written;
*           A read that is concurrent with a write returns the last value written or the value concurrently written.
*
*
* We assume Perfect Failure Detector (P)
*
*/

public class RegularRegister {

    private Integer value;

    public RegularRegister() {
        this.value = null;
    }

    public Integer read() {
        return this.value;
    }

    private void readReturn() {

    }

    public void write(int value) {
        this.value = value;
    }

    private void writeReturn() {

    }

}
