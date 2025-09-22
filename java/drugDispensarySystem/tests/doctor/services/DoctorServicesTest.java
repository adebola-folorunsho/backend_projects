package doctor.services;

import doctor.dtos.requests.CreatePrescriptionRequest;
import doctor.exceptions.NoPrescriptionsIssuedByUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prescription.model.data.Prescription;
import prescription.model.repositories.FakePrescriptionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServicesTest {

    private FakePrescriptionRepository fakeRepo;
    private DoctorServices doctorServices;

    @BeforeEach
    void setUp() {
        fakeRepo = new FakePrescriptionRepository();
        doctorServices = new DoctorServices(fakeRepo);
    }

    @Test
    void issuePrescription_shouldSaveAndReturnId() {
        CreatePrescriptionRequest request = new CreatePrescriptionRequest("PAT001", "Flu", List.of("DRG001", "DRG002"));
        request.setDoctorId("DTR001");
        String prescriptionId = doctorServices.issuePrescription(request);

        assertNotNull(prescriptionId);
        List<Prescription> prescriptions = fakeRepo.findPrescriptionsByUserId("DTR001");
        assertEquals(1, prescriptions.size());
        assertEquals("PSC1", prescriptions.getFirst().getPrescriptionId());
    }

    @Test
    void getPrescriptionsByDoctor_shouldReturnAllPrescriptions() {
        CreatePrescriptionRequest request1 = new CreatePrescriptionRequest("PAT001", "Malaria", List.of("DRG001"));
        CreatePrescriptionRequest request2 = new CreatePrescriptionRequest("PAT002", "Cold", List.of("DRG002"));

        request1.setDoctorId("DTR123");

        request2.setDoctorId("DTR123");

        doctorServices.issuePrescription(request1);
        doctorServices.issuePrescription(request2);

        List<Prescription> prescriptions = doctorServices.getPrescriptionsByDoctor("DTR123");

        assertEquals(2, prescriptions.size());
    }

    @Test
    void getPrescriptionsByDoctor_shouldThrowExceptionWhenNoneFound() {
        assertThrows(NoPrescriptionsIssuedByUserException.class, () -> {
            doctorServices.getPrescriptionsByDoctor("NON_EXISTENT_ID");
        });
    }
}