package pharamacist.services;


import drugs.repositories.Drugs;
import pharamacist.exceptions.NoPrescriptionDispensedByUserException;
import prescription.exceptions.DispensedPrescriptionException;
import prescription.exceptions.UnverifiedPrescriptionException;
import prescription.model.data.Prescription;
import prescription.model.data.PrescriptionStatus;
import prescription.model.repositories.PrescriptionRepoInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PharmacistServices {
    private PrescriptionRepoInterface prescriptionRepository;
    private final Drugs drugRepository = new Drugs();
    private Map<String, String> drugStatus = new HashMap<>();

    public PharmacistServices() {
    }

    public PharmacistServices(PrescriptionRepoInterface prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    private void verifyPrescription(Prescription prescription) {
        if (prescription == null) {
            throw new UnverifiedPrescriptionException(prescription.getPrescriptionId());
        }
        if (prescription.getStatus() != PrescriptionStatus.PENDING) {
            throw new DispensedPrescriptionException(prescription.getPrescriptionId());
        }

    }

    public Prescription retrievePrescriptionDetails(String prescriptionId) {
        Prescription prescription = prescriptionRepository.findByPrescriptionId(prescriptionId);
        verifyPrescription(prescription);
        return prescription;
    }

    public Prescription dispensePrescription(String userId, String prescriptionId) {
        Prescription prescription = prescriptionRepository.findByPrescriptionId(prescriptionId);
        List<String> drugIds = prescription.getDrugIds();
        verifyPrescription(prescription);
//        verifyDrugs(prescription.getDrugIds());
        prescriptionRepository.deleteById(prescriptionId);
        prescription.setStatus(PrescriptionStatus.DISPENSED);
        prescription.setPharmacistId(userId);
        prescriptionRepository.save(prescription);
        return prescription;
    }

    public List<Prescription> retrievePrescriptionsByUserId(String userId) {
        List<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByUserId(userId);
        if (prescriptions.isEmpty()) throw new NoPrescriptionDispensedByUserException(userId);
        List<Prescription> prescriptionHistories = new ArrayList<>();
        prescriptions.forEach(prescriptionHistories::add);
        return prescriptionHistories;
    }

//    private void verifyDrugs(List<String> drugIds){
//        for(String drugId : drugIds){
//            if (drugRepository.get(drugId) == null){
//                drugStatus.put(drugId, "UNAVAILABLE");
//            }
//            else{
//                drugStatus.put(drugId, "DISPENSED");
//            }
//        }
//
//    }

}



