from appointment import Appointment

class PendingAppointments:
    def __init__(self):
        self.__appointments : dict[str, list[Appointment]] = {}

    def add_appointment(self, appointment: Appointment) -> None:
        if appointment.doctor_id in self.__appointments.keys():
            self.__appointments[appointment.doctor_id].append(appointment)
        self.__appointments[appointment.doctor_id] = list(set(self.__appointments[appointment.doctor_id]))

    def remove_appointment(self, appointment: Appointment) -> None:
        pass

    def get_pending_appointments(self):
        return self.__appointments


    



