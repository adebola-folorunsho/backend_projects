package prescription.model.repositories;

import prescription.model.data.Prescription;

import java.util.ArrayList;
import java.util.List;


public class PrescriptionRepository implements PrescriptionRepoInterface {

        private final List<Prescription> prescriptions = new ArrayList<>();

        public void save(Prescription prescription){
            prescriptions.add(prescription);
        }

        @Override
        public Prescription findByPrescriptionId(String id) {
            for (Prescription prescription : prescriptions) {
                if (prescription.getPrescriptionId().equals(id)) {
                    return prescription;
                }
            }
            return null;
        }

        @Override
        public List<Prescription> findPrescriptionsByUserId(String id) {
            List<Prescription> foundPrescriptions = new ArrayList<>();
            for (Prescription prescription : prescriptions) {
                if (prescription.getDoctorId().equals(id) || prescription.getPharmacistId().equals(id)) {
                    foundPrescriptions.add(prescription);
                }
            }
            return foundPrescriptions;
        }

        public void deleteById(String id){
            prescriptions.removeIf(prescription -> prescription.getPrescriptionId().equals(id));
        }

        public int count(){
            return prescriptions.size();
        }


    }


