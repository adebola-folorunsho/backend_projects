package doctor.exceptions;

public class NoPrescriptionsIssuedByUserException extends RuntimeException {
    public NoPrescriptionsIssuedByUserException(String id) {
        super(String.format("No prescriptions found for id: %s", id));
    }
}
