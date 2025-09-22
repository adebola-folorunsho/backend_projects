from typing import NoReturn

from appointment import Appointment

class DoctorAppointments:
    def __init__(self):
        self.__appointments : dict[str, Appointment] = {}

    def add_appointments(self, patient_id, appointment: Appointment) -> NoReturn:
        self.__appointments[patient_id] = appointment

    def get_pending_appointments(self):
        return self.__appointments

    def number_of_appointments(self) -> int:
        return len(self.__appointments)