from typing import List

from doctor import Doctor


class Doctors:
    def __init__(self) -> None:
       self.__doctors : dict[str, List[Doctor]] = {'Surgeon' : [], "cardiologist" : [], "pediatrician" : [], "neurologist" : []}
       
    def add_doctor_to_database(self, doctor : Doctor):
        self.__doctors[doctor.specialisation].append(doctor)

    def get_doctors_in(self, specialisation: str) -> list[Doctor]:
        return self.__doctors[specialisation]

    def get_number_of_doctors(self):
       return sum(len(doctors) for doctors in self.__doctors.values())

