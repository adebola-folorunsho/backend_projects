package doctor.dtos.responses;

import prescription.model.data.PrescriptionStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrescriptionHistoryResponse {


        private String prescriptionId;
        private String pharmacistId;
        private String doctorId;
        private String patientName;
        private String diagnosis;
        private String prescriptionDate;
        private PrescriptionStatus status;
        private List<String> medicineIds;
        private Map<String, String> drugStatus = new HashMap<>();


        public List<String> getMedicineIds() {
            return medicineIds;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setMedicineIds(List<String> medicineIds) {
            this.medicineIds = medicineIds;
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
