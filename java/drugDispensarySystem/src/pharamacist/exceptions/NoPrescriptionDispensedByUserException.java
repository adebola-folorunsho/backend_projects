package pharamacist.exceptions;

public class NoPrescriptionDispensedByUserException extends RuntimeException {
    public NoPrescriptionDispensedByUserException(String userId) {
        super(String.format("No Prescription has been dispensed by user %s", userId));
    }
}
