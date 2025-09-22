from src.personal import Personal

class Patient(Personal):

    def __init__(self, first_name: str, last_name: str, date_of_birth: str):
        super().__init__(first_name = first_name, last_name = last_name, date_of_birth = date_of_birth)

"""
    @property
    def medical_history(self)-> MedicalHistory:
        return self.__medical_history.get_appointments()

    @property
    def pending_appointments(self) -> PendingAppointments:
        return self.__pending_appointments.get_pending_appointments()

    def __str__(self):
        return f
Patient ID: {self.__id}
Name: {self.name}
Date of Birth: {self.date_of_birth}


"""