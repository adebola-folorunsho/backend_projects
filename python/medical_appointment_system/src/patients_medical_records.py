from medical_history import MedicalHistory

class MedicalRecords:
    def __init__(self):
        self.__patients_medical_records : dict[str, MedicalHistory]  = {}

    def add_new_record(self, patient_id: str, medical_history: MedicalHistory) -> None:
        self.__patients_medical_records.update({patient_id: medical_history})

    def retrieve_record(self, patient_id: str) -> MedicalHistory | None:
        return self.__patients_medical_records.get(patient_id)

    def number_of_patients(self) -> int:
        return len(self.__patients_medical_records.keys())

