from re import search
from typing import NoReturn
from status_enum import Status


class Appointment:
    def __init__(self, patient_id: str, doctor_id: str, reason_for: str) -> NoReturn:
        self.__patient = patient_id
        self.__doctor = doctor_id
        self.__reason = reason_for
        self.__time = None
        self.__status = Status.SCHEDULED
        self.__remark = None

    @property
    def reason(self) -> str: return self.__reason

    @property
    def time(self) -> str: return self.__time

    @time.setter
    def time(self, time_input: str) -> NoReturn:
        self.__time = time_input

    @property
    def patient_id(self) -> str: return self.__patient

    @property
    def doctor_id(self) -> str: return self.__doctor

    @staticmethod
    def __validate_reason(reason: str) -> NoReturn:
        if not search(r"(\b\w+-.\b.)*(\bw+-.\b)", reason):
            raise Exception("Invalid reason")

    @property
    def remark(self) -> str: return self.__remark

    @remark.setter
    def remark(self, doctor_remark: str) -> None:
        self.__remark = doctor_remark

    @property
    def is_resolved(self) -> bool:
        return self.__status == Status.RESOLVED

    def complete(self, remarks) -> NoReturn:
        self.remark = remarks
        self.__status = Status.RESOLVED

    def __str__(self) -> str:
        return f"""
Patient
{self.__patient}
Doctor
{self.__doctor}
Reason: {self.reason}
Appointment Time: {self.time.ctime()}
Doctor Remark: {self.remark}"""