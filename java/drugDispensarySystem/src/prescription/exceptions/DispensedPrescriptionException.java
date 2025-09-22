package prescription.exceptions;

public class DispensedPrescriptionException extends RuntimeException {
    public DispensedPrescriptionException(String id) {
        super(String.format("Prescription with id: %s has already been dispensed", id));;
    }

}
