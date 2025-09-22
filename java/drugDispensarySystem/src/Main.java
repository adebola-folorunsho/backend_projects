
import doctor.PrescriptionRequestValidator;

import doctor.controllers.DoctorController;
import doctor.dtos.requests.CreatePrescriptionRequest;
import doctor.services.DoctorServices;
import pharamacist.controllers.PharmacistController;
import pharamacist.services.PharmacistServices;
import prescription.model.repositories.PrescriptionRepoInterface;
import prescription.model.repositories.PrescriptionRepository;
import user.controller.UserController;
import user.data.repository.UserRepository;
import user.dtos.requests.UserInputValidator;
import user.dtos.requests.UserLoginRequestDto;

import user.services.UserServices;
import user.dtos.requests.RegisterUserDto;
import utils.Printer;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private final UserRepository userRepository = new UserRepository();
    private final UserServices userServices = new UserServices(userRepository);
    private final UserController userController = new UserController(userServices);
    private final PrescriptionRepoInterface repo = new PrescriptionRepository();
    private final DoctorServices doctorServices = new DoctorServices(repo);
    private final DoctorController doctorController = new DoctorController(doctorServices);
    private final PharmacistServices services = new PharmacistServices(repo);
    private final PharmacistController pharmacistController = new PharmacistController(services);
    private final Scanner scanner = new Scanner(System.in);5

    public void run() {
        while (true) {
            try {
                String choice = menu();
                switch (choice) {
                    case "1" -> signUp();
                    case "2" -> logIn();
                    case "3" -> {
                        print("Exiting...");
                        return;
                    }
                    default -> print("Invalid option. Try again.");
                }
            } catch (RuntimeException e) {
                print("Error: " + e.getMessage());
            }
        }
    }

    private String menu() {
        return input("""
                1. Sign up
                2. Login
                3. Exit
                """);
    }

    private String input(String message) {
        print(message);
        return scanner.nextLine().trim();
    }

    private void signUp() {
        String roleChoice = signUpMenu();
        switch (roleChoice) {
            case "1" -> {
                userController.registerDoctor(collectUserInfo());
            }
            case "2" -> {
                userController.registerPharmacist(collectUserInfo());
            }
            case "3" -> print("Returning to main menu...");
            default -> print("Invalid role selected.");
        }
    }

    private void logIn() {
        UserLoginRequestDto logInInfo = collectUserInfoForLogIn();
        UserInputValidator.validateString(logInInfo.getUsername());
        String id = userController.login(logInInfo);
        while (true) {
            try {
                print(String.format("Logged in as: @%s", logInInfo.getUsername()));
                boolean idPrefix = id.startsWith("DTR");
                if (idPrefix) {
                    String select = doctorProfile();
                    switch (select) {
                        case "1" -> {
                            CreatePrescriptionRequest createPrescriptionRequest = enterPrescriptionDetails();
                            PrescriptionRequestValidator.validate(createPrescriptionRequest);
                            createPrescriptionRequest.setDoctorId(id);
                            print(doctorController.createPrescription(createPrescriptionRequest));
                        }
                        case "2" -> {
                            doctorController.viewPrescriptionHistory(id);
                        }
                        case "3" -> {
                            print("Signing out...");
                            return;
                        }
                        default -> print("Invalid option. Try again.");
                    }

                } else {
                    String select = pharmacistProfile();
                    switch (select) {
                        case "1" -> {
                            String prescriptionId = input("Enter prescription ID: ");
                            pharmacistController.retrievePrescriptionDetails(prescriptionId);
                        }
                        case "2" -> {
                            String prescriptionId = input("Enter prescription ID: ");
                            pharmacistController.dispenseDrugs(id, prescriptionId);
                        }
                        case "3" -> {
                            pharmacistController.viewFulfilledPrescriptions(id);
                        }
                        case "4" -> {
                            print("Signing out...");
                            return;
                        }

                        default -> print("Invalid option. Try again.");
                    }
                }
            } catch (RuntimeException e) {
                print("Error: " + e.getMessage());
            }
        }
    }

    private String signUpMenu() {
        return input("""
                Select Role
                1. Doctor
                2. Pharmacist
                3. Back to last page
                """);
    }

    private RegisterUserDto collectUserInfo() {
        String fullName = input("Full Name: ");
        String username = input("Enter username: ");
        String password = input("Enter password: ");
        RegisterUserDto registerUserDto =  new RegisterUserDto(fullName, username, password);
        UserInputValidator.validate(registerUserDto);
        return registerUserDto;
    }

    private UserLoginRequestDto collectUserInfoForLogIn() {
        String username = input("Enter username: ");
        String password = input("Enter password: ");
        return new UserLoginRequestDto(username, password);
    }

    private void print(String message) {
        Printer.print(message);
    }

    private String doctorProfile(){
        return input("""
        1. Create Prescription
        2. View all prescriptions issued
        3. Sign out
        """);
    }

    private String pharmacistProfile(){
        return input("""
        1. Retrieve prescription details
        2. Dispense drugs
        3. View dispensed prescriptions history
        4. Sign out
        """);
    }

    private CreatePrescriptionRequest enterPrescriptionDetails() {
        String patientDetails = input("Patient id: ");
        String diagnosisDetails = input("Diagnosis details: ");
        String count = input("How many drugs for patient: ");
        List<String> drugIds = new ArrayList<>();
        for(int index = 0; index < Integer.parseInt(count); index++){
            drugIds.add(input("Enter drug Id: "));
        }
        return new CreatePrescriptionRequest(patientDetails, diagnosisDetails, drugIds);
    }
}

