from enum import Enum

class Status(Enum):
    PENDING = "Appointment has been scheduled"
    SCHEDULED = "Appointment has been confirmed"
    RESOLVED = "Appointment has been resolved"
