package doctor.dtos.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreatePrescriptionRequest {

    private String doctorId;
    private String patientName;
    private String diagnosis;
    private String dateIssued;
    private List<String> drugs;

    // Constructors
    public CreatePrescriptionRequest() {}

    public CreatePrescriptionRequest(String patientName, String diagnosis, List<String> drugs) {
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy HH:mm:ss");
        this.dateIssued = LocalDateTime.now().format(dtf);
        this.drugs = drugs;
    }

    // Getters and Setters
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<String> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<String> drugs) {
        this.drugs = drugs;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    @Override
    public String toString() {
        return "PrescriptionRequest{" +
                "doctorId='" + doctorId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", drugs=" + drugs +
                '}';
    }
}

