package doctor.services;


import doctor.dtos.requests.CreatePrescriptionRequest;
import doctor.exceptions.NoPrescriptionsIssuedByUserException;
import prescription.model.data.Prescription;
import prescription.model.repositories.PrescriptionRepoInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorServices {
    private PrescriptionRepoInterface repository;
    private int counter = 1;

    public DoctorServices(PrescriptionRepoInterface repository){
        this.repository = repository;
    }

    public String issuePrescription(CreatePrescriptionRequest request){
        Prescription prescription = mapToPrescription(request);
        prescription.setDoctorId(request.getDoctorId());
        repository.save(prescription);
        return prescription.getPrescriptionId();
    }

    public List<Prescription> getPrescriptionsByDoctor(String doctorId){
        List<Prescription> prescriptions = repository.findPrescriptionsByUserId(doctorId);
        if(prescriptions.isEmpty())throw new NoPrescriptionsIssuedByUserException(doctorId);
        return new ArrayList<>(prescriptions);
    }

//    public void updatePrescription(UpdatePrescriptionRequest updateDto){
//        Optional<Prescription> prescription = repository.findById(updateDto.getPrescriptionId());
//    }

    private String idGenerator(){
        return "PSC" + counter++;
    }

    private Prescription mapToPrescription(CreatePrescriptionRequest request){
        Prescription prescription = new Prescription();
        prescription.setPatientName(request.getPatientName());
        prescription.setDiagnosis(request.getDiagnosis());
        prescription.setDrugIds(request.getDrugs());
        prescription.setPrescriptionId(idGenerator());
        prescription.setPrescriptionDate(request.getDateIssued());
        return prescription;
    }



}
