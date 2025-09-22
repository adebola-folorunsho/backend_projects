package prescription.model.data;

import java.util.List;

public class Prescription {
    private String prescriptionId;
    private String pharmacistId;
    private String doctorId;
    private String patientName;
    private String prescriptionDate;
    private PrescriptionStatus status;
    private List<String> drugIds;
    private String diagnosis;



    {
        this.status = PrescriptionStatus.PENDING;
    }



    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

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

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    public String getPharmacistId() {
        return pharmacistId;
    }
    public void setPharmacistId(String pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public List<String> getDrugIds() {
        return drugIds;
    }
    public void setDrugIds(List<String> ids){
        this.drugIds = ids;
    }

    public String toString(){
        return String.format("""
                Prescription Id: %s
                Doctor Id: %s
                Patient Name: %s
                Prescription Date: %s
                Status: %s""",
                this.prescriptionId, this.doctorId, this.patientName, this.prescriptionDate, this.status);
    }
}


