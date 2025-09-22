package pharamacist.controllers;

import pharamacist.services.PharmacistServices;

public class PharmacistController {
    private PharmacistServices services;

    public PharmacistController(PharmacistServices services) {
        this.services = services;
    }

    public void retrievePrescriptionDetails(String id){
        System.out.println(services.retrievePrescriptionDetails(id));
    }

    public void dispenseDrugs(String userId, String prescriptionId){
        System.out.println(services.dispensePrescription(userId, prescriptionId));
    }

    public void viewFulfilledPrescriptions(String userId){
        for(var prescription : services.retrievePrescriptionsByUserId(userId)){
            System.out.println(prescription);
        }
    }
}

