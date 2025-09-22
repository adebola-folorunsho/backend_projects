from typing import NoReturn

from doctor import Doctor
from patient import Patient
from medical_history import MedicalHistory
from patients_medical_records import MedicalRecords
from appointment import Appointment

from doctors import Doctors
from utilities.contact import Contact


class MedicalRecordSystem:
    __id_count_for_patient = 0
    __id_count_for_doctor = 0

    def __init__(self):
        self.__doctors = Doctors()
        self.__patients_medical_records = MedicalRecords()

    def schedule_appointment_for(self, patient_id, reason: str, specialization: str):
        patient_file : MedicalHistory = self.search_patient_using(patient_id)
        appointment  = None
        for doctor in self.__doctors.get_doctors_in(specialization):
            if doctor.is_available:
                appointment = Appointment(patient_id, doctor.id, reason)
                appointment.time = doctor.generate_appointment_date()
                patient_file.add_to_pending(doctor.id, appointment)
                doctor.add_appointment(appointment)
                print(f"Appointment scheduled for {appointment.time} with {appointment.doctor_id}")
                break

    def search_doctor_using(self, doctor_id, specialization: str) -> Doctor:
        found_doctor = None
        found_doctor = next((doctor for doctor in self.__doctors.get_doctors_in(specialization) if doctor.id == doctor_id), None)
        MedicalRecordSystem.raise_exception_if_doctor_is_not_registered(found_doctor)
        return found_doctor

    def search_patient_using(self, patient_id):
        found_patient = self.__patients_medical_records.retrieve_record(patient_id)
        MedicalRecordSystem.raise_exception_if(found_patient)
        return found_patient

    @staticmethod
    def raise_exception_if(patient_is_not_registered: None):
        if patient_is_not_registered is None:
            raise Exception("Patient is not registered")

    @staticmethod
    def raise_exception_if_doctor_is_not_registered(doctor_is_not_registered: None):
        if doctor_is_not_registered is None:
            raise Exception("Doctor is not registered")

    def register_patient(self, first_name: str, last_name: str, date_of_birth: str)-> str:
        patient = Patient(first_name, last_name, date_of_birth)
        MedicalRecordSystem.__id_count_for_patient += 1
        patient.id = f"PTT-{str(MedicalRecordSystem.__id_count_for_patient):>03}"
        medical_history = MedicalHistory(patient)
        self.__patients_medical_records.add_new_record(patient.id, medical_history)
        return patient.id

    def register_doctor(self, first_name: str, last_name: str, date_of_birth: str, specialization : str)-> str:
        doctor = Doctor(first_name, last_name, date_of_birth, specialization)
        MedicalRecordSystem.__id_count_for_doctor += 1
        doctor.id = f"DTR-{str(MedicalRecordSystem.__id_count_for_doctor):>03}"
        self.__doctors.add_doctor_to_database(doctor)
        return doctor.id

    def view_appointment_schedule_for_doctor(self, doctor_id, specialization: str) -> dict[str, Appointment]:
        doctor = self.search_doctor_using(doctor_id, specialization)
        return doctor.view_appointment_schedule_for_doctor()

    def view_medical_history_for(self, patient_id)-> MedicalHistory:
        patient_medical_history = self.__patients_medical_records.retrieve_record(patient_id)
        return patient_medical_history

    def view_patient_pending_appointments(self, patient_id):
        return self.__patients_medical_records.retrieve_record(patient_id).pending_appointments

    def get_number_of_patients_in_system(self):
        return self.__patients_medical_records.number_of_patients()

    def get_number_of_doctors_in_system(self):
        return self.__doctors.get_number_of_doctors()

    def mark_appointment_as_resolved(self, patient_id: str, doctor_id, specialisation: str, remark):
        doctor = self.search_doctor_using(doctor_id, specialisation)
        appointment = doctor.give_remark_for_completed_appointment(patient_id, remark)
        self.search_patient_using(patient_id).remove_resolved_appointment_from_pending(doctor_id)
        self.search_patient_using(patient_id).add(appointment)




