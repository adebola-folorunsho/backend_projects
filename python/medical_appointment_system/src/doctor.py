from datetime import datetime, timedelta

from appointment import Appointment
from doctor_appointments import DoctorAppointments
from personal import Personal

class Doctor(Personal):
    def __init__(self, first_name: str, last_name: str, date_of_birth: str, specialty: str) -> None:
        super().__init__(first_name, last_name, date_of_birth)
        self.__id = None
        self.specialisation = specialty  # can also be a list
        self.__appointments = DoctorAppointments()
        self.__appointment_date_ranges: list[range] = []

    @property
    def is_available(self) -> bool:
        return self.__appointments.number_of_appointments() < 5

    @property
    def id(self) -> str:
        return self.__id

    @id.setter
    def id(self, doctor_id: str) -> None:
        self.__id = doctor_id

    @property
    def specialisation(self) -> str:
        return self.__specialisation

    @specialisation.setter
    def specialisation(self, specialisation_input: str) -> None:
        self.__specialisation = specialisation_input

    def generate_appointment_date(self) -> datetime:
        start_date = (datetime.now() + timedelta(weeks=1)).replace(minute=0, second=0, microsecond=0)
        if not self.__check_timeslot(start_date): start_date = start_date + timedelta(days=1)
        start_in_seconds = start_date.timestamp().__int__()
        end_in_seconds = (start_date + timedelta(minutes=30)).timestamp().__int__()
        self.__appointment_date_ranges.append(range(start_in_seconds, end_in_seconds))
        return start_date

    def __check_timeslot(self, start_date: datetime) -> bool:
        for date_range in self.__appointment_date_ranges:
            if start_date.timestamp().__int__() in date_range: return False
        return True

    def add_appointment(self, appointment: Appointment) -> None:
        self.__appointments.add_appointments(appointment.patient_id, appointment)

    def give_remark_for_completed_appointment(self, patient_id, remarks) -> Appointment:
        self.__appointments.get_pending_appointments()[patient_id].complete(remarks)

        return self.__appointments.get_pending_appointments().pop(patient_id, None)

    def view_appointment_schedule_for_doctor(self):
        return self.__appointments

    def __str__(self):
        return f"""
Medical ID: {self.__id}
Name: {self.first_name + " " + self.last_name}
Date of Birth: {self.date_of_birth}
Specialisation: {self.specialisation.value}
"""
