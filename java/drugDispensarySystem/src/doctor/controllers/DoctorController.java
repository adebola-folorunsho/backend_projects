package doctor.controllers;



import doctor.dtos.requests.CreatePrescriptionRequest;
import doctor.services.DoctorServices;
import prescription.model.data.Prescription;

import java.util.List;

public class DoctorController {

    private final DoctorServices doctorService;

    public DoctorController(DoctorServices doctorService) {
        this.doctorService = doctorService;
    }

    public String createPrescription(CreatePrescriptionRequest request) {
        String prescriptionId = doctorService.issuePrescription(request);
        return String.format("Prescription created successfully. Prescription ID: %s", prescriptionId);
    }

    public void viewPrescriptionHistory(String doctorId) {
        List<Prescription> prescriptions = doctorService.getPrescriptionsByDoctor(doctorId);
        for (Prescription p : prescriptions) {
            System.out.println(p);
        }
    }
}

