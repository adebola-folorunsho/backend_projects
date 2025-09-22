from datetime import datetime

from appointment import Appointment
from patient import Patient


class MedicalHistory:
    def __init__(self, patient: Patient):
        self.__patient = patient
        record = None
        self.__unresolved_appointments : dict[str, Appointment] = {}
        self.__records: dict[str, Appointment] = {}

    def add(self, resolved_appointment: Appointment) -> None:
        date_added = datetime.now().ctime()  # or datetime.now().isoformat(sep=" ", timespec="minutes")
        record: Appointment = resolved_appointment
        self.__records.update({date_added: record})

    def add_to_pending(self, doctor_id, unresolved_appointment: Appointment) -> None:
        self.__unresolved_appointments.update({doctor_id: unresolved_appointment})

    @property
    def pending_appointments(self):
        return self.__unresolved_appointments

    def remove_resolved_appointment_from_pending(self, doctor_id) -> Appointment | None:
        self.__unresolved_appointments.pop(doctor_id, None)

    def __display_history(self):
        history = [f"{date_added}\n{resolved_appointment.__str__()}\n{solution}"
                   for date_added, record in self.__records.items() for resolved_appointment, solution in
                   self.__records.items()]
        return "\n".join(history)

    @property
    def id(self) -> str:
        return self.__patient.id

    def __str__(self) -> str:
        return f"""
Medical History for patient with ID {self.__patient.id}
{self.__display_history()}"""
