package doctor;



import doctor.dtos.requests.CreatePrescriptionRequest;

import java.util.List;

public class PrescriptionRequestValidator {
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._]+$";

    public static void validate(CreatePrescriptionRequest createPrescriptionRequest) {
        validateString(createPrescriptionRequest.getPatientName());
        validateString(createPrescriptionRequest.getDiagnosis());
        validateDrugs(createPrescriptionRequest.getDrugs());
    }

    private static void validateString(String string) {
        if (string == null || string.isBlank()) {
            throw new IllegalArgumentException("Patient name cannot be null, empty, or blank.");
        }
        if (!string.matches(USERNAME_PATTERN)) {
                throw new IllegalArgumentException("This field can only contain letters, numbers, underscores, and periods.");
        }
    }

    private static void validateDrugs(List<String> drugs) {
        if (drugs == null || drugs.isEmpty()) {
            throw new IllegalArgumentException("Drugs list cannot be null or empty.");
        }

        for (String drug : drugs) {
           validateString(drug);
        }
    }


}
