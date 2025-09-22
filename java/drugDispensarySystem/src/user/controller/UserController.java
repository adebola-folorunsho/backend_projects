package user.controller;

import user.dtos.requests.UserLoginRequestDto;
import user.dtos.requests.RegisterUserDto;
import user.dtos.responses.UserLoginResponseDto;
import user.services.UserServices;
import utils.Printer;


public class UserController {
    private final UserServices userService;

    public UserController(UserServices userService) {
        this.userService = userService;
    }

    public void registerDoctor(RegisterUserDto registerUserDto) {
        registerUserDto.setUserName(registerUserDto.getUserName().toLowerCase());
        userService.registerDoctor(registerUserDto);
        Printer.print("Registration successful");
    }

    public void registerPharmacist(RegisterUserDto registerUserDto) {
        userService.registerPharmacist(registerUserDto);
        Printer.print("Registration successful");
    }

    //in main/system, when user logs in now call the Doctor feature or Pharmacy feature depending on their roles
    public String login(UserLoginRequestDto userLoginRequestDto) {
        userLoginRequestDto.setUsername(userLoginRequestDto.getUsername().toLowerCase());
        return String.format(userService.login(userLoginRequestDto).getId());
    }
}
