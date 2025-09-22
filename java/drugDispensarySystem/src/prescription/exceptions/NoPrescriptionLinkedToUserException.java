package prescription.exceptions;

public class NoPrescriptionLinkedToUserException extends RuntimeException {
    public NoPrescriptionLinkedToUserException(String userId)
    {
        super(String.format("No prescriptions found for user with id: %s", userId));
    }
}
