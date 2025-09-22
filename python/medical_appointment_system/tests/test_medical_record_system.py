import unittest

from doctor import Doctor
from medical_record_system import MedicalRecordSystem
from patient import Patient


class MyTestCase(unittest.TestCase):

    def test_medical_record_system_has_no_doctors(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_doctors_in_system())

    def test_register_new_doctor_to_medical_record_system(self):
        mds = MedicalRecordSystem()
        mds.register_doctor("adebola", "folorunsho", "2/7/2021", "Surgeon")
        self.assertEqual(1, mds.get_number_of_doctors_in_system())

    def test_register_two_doctors_to_medical_record_system(self):
        mds = MedicalRecordSystem()
        mds.register_doctor("adebola", "folorunsho", "2/7/2021", "Surgeon")
        self.assertEqual(1, mds.get_number_of_doctors_in_system())
        mds.register_doctor("funke", "damilola", "2/7/2021", "Surgeon")
        self.assertEqual(2, mds.get_number_of_doctors_in_system())

    def test_system_assigns_id_to_doctors_after_registration(self):
        mds = MedicalRecordSystem()
        unregistered_doctor = Doctor("adebola", "folorunsho", "2/7/2021", "Surgeon")
        self.assertTrue(unregistered_doctor.id is None, "unregistered_doctor")
        self.assertEqual(0, mds.get_number_of_doctors_in_system())
        registered_doctor_id = mds.register_doctor("adebola", "folorunsho", "2/7/2021", "Surgeon")
        self.assertFalse(registered_doctor_id is None, "registered_doctor")
        self.assertEqual(1, mds.get_number_of_doctors_in_system())

    def test_unique_ids_are_assigned_to_registered_doctors(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_doctors_in_system())
        first_doctor_id = mds.register_doctor("adebola", "folorunsho", "2/7/2021", "Surgeon")
        first_doctor = mds.search_doctor_using(first_doctor_id, 'Surgeon')
        assert first_doctor is not None, "first_doctor"
        assert first_doctor.id is not None, "first_doctor_id"
        second_doctor_id = mds.register_doctor("funke", "damilola", "2/7/2021", "Surgeon")
        second_doctor = mds.search_doctor_using(second_doctor_id, 'Surgeon')
        assert second_doctor is not None, "second_doctor"
        assert second_doctor.id is not None, "second_doctor_id"
        assert first_doctor.id != second_doctor.id, "id's are similar"

    def test_medical_record_system_has_no_patients(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_doctors_in_system())

    def test_register_new_patient_to_medical_record_system(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_patients_in_system())
        mds.register_patient("adebola", "folorunsho", "21/8/2006")
        self.assertEqual(1, mds.get_number_of_patients_in_system())

    def test_register_two_new_patients_to_medical_record_system(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_patients_in_system())
        mds.register_patient("adebola", "folorunsho", "21/8/2006")
        self.assertEqual(1, mds.get_number_of_patients_in_system())
        mds.register_patient("adebola", "folorunsho", "21/8/2006")
        self.assertEqual(2, mds.get_number_of_patients_in_system())

    def test_unregistered_patient_obj_has_no_id(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_patients_in_system())
        unregistered_patient = Patient("adebola", "folorunsho", "13/9/2002")
        self.assertTrue(unregistered_patient.id is None, "unregistered_patient")

    def test_registered_patient_obj_has_id(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_patients_in_system())
        unregistered_patient = Patient("adebola", "folorunsho", "13/9/2002")
        self.assertTrue(unregistered_patient.id is None, "unregistered_patient")
        registered_patient_id = mds.register_patient("adebola", "folorunsho", "13/9/2002")
        registered_patient = mds.search_patient_using(registered_patient_id)
        self.assertFalse(registered_patient.id is None, "registered_patient")
        self.assertEqual(1, mds.get_number_of_patients_in_system())

    def test_unique_ids_are_assigned_to_registered_patients(self):
        mds = MedicalRecordSystem()
        self.assertEqual(0, mds.get_number_of_patients_in_system())
        first_new_patient_id = mds.register_patient("adebola", "folorunsho", "13/9/2002")
        first_new_patient = mds.search_patient_using(first_new_patient_id)
        self.assertTrue(first_new_patient.id is not None, "no id has been assigned")
        self.assertEqual(1, mds.get_number_of_patients_in_system())
        second_new_patient_id = mds.register_patient("adebola", "folorunsho", "13/9/2002")
        second_new_patient = mds.search_patient_using(second_new_patient_id)
        self.assertEqual(2, mds.get_number_of_patients_in_system())
        self.assertTrue(second_new_patient.id is not None, "no id has been assigned")
        self.assertNotEqual(first_new_patient.id, second_new_patient.id, "ID's are similar")
        third_new_patient_id = mds.register_patient("wonu", "folorunsho","16/7/2001")
        third_new_patient = mds.search_patient_using(third_new_patient_id)
        self.assertTrue(third_new_patient.id is not None, "no id has been assigned")
        self.assertEqual(3, mds.get_number_of_patients_in_system())
        self.assertNotEqual(third_new_patient.id, first_new_patient.id, "ID's are similar")
        self.assertNotEqual(third_new_patient.id, second_new_patient.id, "ID's are similar")

    def test_doctor_id(self):
        mds = MedicalRecordSystem()
        doctor_id = mds.register_doctor("Jane", "Doe", "20/2/2001", "Surgeon")
        self.assertTrue(doctor_id.startswith("DTR-"))

    def test_patient_id(self):
        mds = MedicalRecordSystem()
        patient_id = mds.register_patient("Jane", "Doe", "20/2/2001")
        self.assertTrue(patient_id.startswith("PTT-"))

    def test_patient_schedule_appointments(self):
        mds = MedicalRecordSystem()
        patient_id = mds.register_patient("Jane", "Doe", "20/2/2001")
        self.assertEqual(0, len(mds.view_medical_history_for(patient_id).pending_appointments))
        doctor_id = mds.register_doctor("Jane", "Doe", "20/2/2001", "Surgeon")
        mds.schedule_appointment_for(patient_id, 'Headache', "Surgeon")
        self.assertEqual(1, len(mds.view_medical_history_for(patient_id).pending_appointments))
        self.assertEqual(1, len(mds.view_appointment_schedule_for_doctor(doctor_id, "Surgeon")))


if __name__ == '__main__':
    unittest.main()
