package prescription.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prescription.model.data.Prescription;
import prescription.model.repositories.PrescriptionRepository;

import static org.junit.jupiter.api.Assertions.*;


class PrescriptionRepositoryTest {
    PrescriptionRepository prescriptionRepository;

    @BeforeEach
    void setUp() {
        prescriptionRepository = new PrescriptionRepository();
    }

    @Test
    void test_savePrescription_countIsOne(){
        Prescription prescription = new Prescription();
        prescriptionRepository.save(prescription);
        assertEquals(1, prescriptionRepository.count());
    }

    @Test
    void test_saveTwoPrescriptions_countIsTwo(){
        Prescription prescription = new Prescription();
        prescriptionRepository.save(prescription);
        Prescription prescription2 = new Prescription();
        prescriptionRepository.save(prescription2);
        assertEquals(2, prescriptionRepository.count());
    }

    @Test
    void test_findPrescriptionById_returnPrescription(){
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId("123");
        prescriptionRepository.save(prescription);
        assertEquals(prescription, prescriptionRepository.findByPrescriptionId(prescription.getPrescriptionId()));
    }

    @Test
    void test_findPrescriptionById_returnNull_ifPrescriptionNotFound(){
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId("0000");
        assertNull(prescriptionRepository.findByPrescriptionId("123"));
    }

    @Test
    void test_findPrescriptionByUserId_returnPrescription(){
        Prescription prescription = new Prescription();
        prescription.setDoctorId("123");
        prescriptionRepository.save(prescription);
        assertEquals(prescription, prescriptionRepository.findPrescriptionsByUserId(prescription.getDoctorId()).get(0));
    }
    @Test
    void test_findPrescriptionByUserId_returnEmptyList_ifPrescriptionNotFound(){
        Prescription prescription = new Prescription();
        prescription.setDoctorId("0000");
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId(prescription.getDoctorId()).size());
    }

    @Test
    void test_deleteById_deletePrescription(){
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId("123");
        prescriptionRepository.save(prescription);
        prescriptionRepository.deleteById(prescription.getPrescriptionId());
        assertNull(prescriptionRepository.findByPrescriptionId(prescription.getPrescriptionId()));
    }

    @Test
    void test_deleteById_deletePrescription_ifPrescriptionNotFound(){
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId("0000");
        prescriptionRepository.deleteById(prescription.getPrescriptionId());
        assertNull(prescriptionRepository.findByPrescriptionId(prescription.getPrescriptionId()));
        assertEquals(0, prescriptionRepository.count());

    }

    @Test
    void test_deleteById_deletePrescription_ifPrescriptionFound(){
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId("123");
        prescriptionRepository.save(prescription);
        prescriptionRepository.deleteById(prescription.getPrescriptionId());
        assertNull(prescriptionRepository.findByPrescriptionId(prescription.getPrescriptionId()));
        assertEquals(0, prescriptionRepository.count());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId(prescription.getDoctorId()).size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId(prescription.getPharmacistId()).size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId("0000").size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId(null).size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId("").size());

    }

    @Test
    void test_deleteById_deletePrescription_ifPrescriptionFound_andCountIsZero(){
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId("123");
        prescriptionRepository.save(prescription);
        prescriptionRepository.deleteById(prescription.getPrescriptionId());
        assertNull(prescriptionRepository.findByPrescriptionId(prescription.getPrescriptionId()));
        assertEquals(0, prescriptionRepository.count());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId(prescription.getDoctorId()).size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId(prescription.getPharmacistId()).size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId("0000").size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId(null).size());
        assertEquals(0, prescriptionRepository.findPrescriptionsByUserId("").size());
        assertEquals(0, prescriptionRepository.count());

    }






}