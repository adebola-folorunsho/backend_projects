package prescription.model.repositories;

import prescription.model.data.Prescription;

import java.util.List;


public interface PrescriptionRepoInterface {

        void save(Prescription prescription);

        Prescription findByPrescriptionId(String id);

        List<Prescription> findPrescriptionsByUserId(String id);

        int count();

        void deleteById(String id);

    }


