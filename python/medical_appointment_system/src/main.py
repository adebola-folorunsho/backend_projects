

from medical_record_system import MedicalRecordSystem
from utilities.contact import Contact

def main():
    system = MedicalRecordSystem()

    while True:
        print("\n=== Medical Record System ===")
        print("1. Register new patient")
        print("2. Register new doctor")
        print("3. Schedule appointment")
        print("4. View doctor's appointment schedule")
        print("5. View patient's medical history")
        print("6. Mark appointment as resolved")
        print("7. View number of patients")
        print("8. View number of doctors")
        print("9. View patient's pending appointments")
        print("10. Exit")

        choice = input("Select an option: ")

        if choice == '1':
            first = input("First name: ")
            last = input("Last name: ")
            dob = input("Date of birth (DD/MM/YYYY): ")
            patient_id = system.register_patient(first, last, dob)
            print("Patient registered successfully.")
            print(f"Patient registered with ID: {patient_id}")

        elif choice == '2':
            first = input("First name: ")
            last = input("Last name: ")
            dob = input("Date of birth (DD/MM/YYYY): ")
            specialization = input("Specialization: ").capitalize()
            doctor_id = system.register_doctor(first, last, dob, specialization)
            print(f"Doctor registered with ID: {doctor_id}")

        elif choice == '3':
            patient_id = input("Patient ID: ")
            reason = input("Reason for appointment: ")
            specialization = input("Specialization needed: ")
            try:
                system.schedule_appointment_for(patient_id, reason, specialization)
            except Exception as e:
                print(e)

        elif choice == '4':
            doctor_id = input("Doctor ID: ")
            specialization = input("Specialization: ")
            try:
                appointments = system.view_appointment_schedule_for_doctor(doctor_id, specialization)
                print(appointments)
            except Exception as e:
                print(f"{e}")

        elif choice == '5':
            patient_id = input("Patient ID: ")
            try:
                history = system.view_medical_history_for(patient_id)
                print(history)
            except Exception as e:
                print(f"{e}")

        elif choice == '6':
            patient_id = input("Patient ID: ")
            doctor_id = input("Doctor ID: ")
            specialization = input("Doctor's Specialization: ")
            remark = input("Enter remark: ")
            try:
                system.mark_appointment_as_resolved(patient_id, doctor_id, specialization, remark)
                print("Appointment marked as resolved.")
            except Exception as e:
                print(f"{e}")

        elif choice == '7':
            print(f"Number of patients: {system.get_number_of_patients_in_system()}")

        elif choice == '8':
            print(f"Number of doctors: {system.get_number_of_doctors_in_system()}")

        elif choice == '9':
            patient_id = input("Patient ID: ")
            try:
                history = system.view_patient_pending_appointments(patient_id)
                print(history)
            except Exception as e:
                print(f"{e}")

        elif choice == '10':
            print("Exiting Medical Record System.")
            break

        else:
            print("Invalid option. Try again.")

if __name__ == "__main__":
    main()
