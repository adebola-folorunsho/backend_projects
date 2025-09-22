package prescription.exceptions;

public class UnverifiedPrescriptionException extends RuntimeException {
    public UnverifiedPrescriptionException(String id) {
        super(String.format("Unverified prescription with id: %s", id));
    }
}
