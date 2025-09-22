package user.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import user.data.repository.MockRepository;
import user.data.repository.UserRepositoryInterface;
import user.dtos.requests.RegisterUserDto;
import user.dtos.requests.UserLoginRequestDto;
import user.dtos.responses.UserLoginResponseDto;
import user.exceptions.IncorrectPasswordException;
import user.exceptions.NonExistentUsernameException;
import user.exceptions.UsernameTakenException;


import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {

    private UserServices userServices;
    private UserRepositoryInterface fakeRepository;

    @BeforeEach
    void setUp() {
        fakeRepository = new MockRepository();
        userServices = new UserServices(fakeRepository);
    }

    @Test
    void registerDoctor_usernameAlreadyExists_throwsException() {
        RegisterUserDto dto = new RegisterUserDto("John", "doc1", "pass123");
        userServices.registerDoctor(dto);

        RegisterUserDto duplicate = new RegisterUserDto("John", "doc1", "pass123");

        assertThrows(UsernameTakenException.class, () -> userServices.registerDoctor(duplicate));
    }

    @Test
    void registerPharmacist_usernameAlreadyExists_caseSensitive_throwsException() {
        RegisterUserDto dto = new RegisterUserDto("Anna", "pharma1", "pass123");
        userServices.registerPharmacist(dto);

        RegisterUserDto duplicate = new RegisterUserDto("Anna", "PHARMA1", "pass123");// Case difference

        assertThrows(UsernameTakenException.class, () -> userServices.registerPharmacist(duplicate));
    }

    @Test
    void login_doctorCorrectCredentials_returnsDoctorId() {
        RegisterUserDto dto = new RegisterUserDto("Jane", "debola123", "securePass");
        userServices.registerDoctor(dto);

        UserLoginRequestDto loginDto = new UserLoginRequestDto("debola123", "securePass");

        UserLoginResponseDto response = userServices.login(loginDto);

        assertNotNull(response);
        assertEquals("DTR0001", response.getId()); // First doctor = 0001
    }

    @Test
    void login_nonExistentUsername_throwsException() {
        UserLoginRequestDto loginDto = new UserLoginRequestDto("moFlaj", "pass123");

        assertThrows(NonExistentUsernameException.class, () -> userServices.login(loginDto));
    }

    @Test
    void login_existingUsername_wrongPassword_throwsException() {
        RegisterUserDto dto = new RegisterUserDto("Tom", "tommy", "correctPass");
        userServices.registerPharmacist(dto);

        UserLoginRequestDto loginDto = new UserLoginRequestDto("tommy", "wrongPass");

        assertThrows(IncorrectPasswordException.class, () -> userServices.login(loginDto));
    }

    @Test
    void login_existingUsername_wrongCasePassword_throwsException() {
        RegisterUserDto dto = new RegisterUserDto("Lisa", "lisa1", "MyPass");
        userServices.registerDoctor(dto);

        UserLoginRequestDto loginDto = new UserLoginRequestDto("lisa1", "mypASS");

        assertThrows(IncorrectPasswordException.class, () -> userServices.login(loginDto));
    }

    @Test
    void registerDoctor_generatesIncrementalIds() {
        RegisterUserDto doc1 = new RegisterUserDto("Doc1", "d1", "1234");
        RegisterUserDto doc2 = new RegisterUserDto("Doc2", "d2", "5678");

        userServices.registerDoctor(doc1);
        userServices.registerDoctor(doc2);

        assertEquals("DTR0001", fakeRepository.findByUsername("d1").get().getId());
        assertEquals("DTR0002", fakeRepository.findByUsername("d2").get().getId());
    }

    @Test
    void registerPharmacist_generatesSeparateIdsFromDoctors() {
        RegisterUserDto user1 = new RegisterUserDto("Pharma1", "p1", "1234");
        RegisterUserDto user2 = new RegisterUserDto("Doc1", "d1", "5678");

        userServices.registerPharmacist(user1);
        userServices.registerDoctor(user2);

        assertEquals("PMC0001", fakeRepository.findByUsername("p1").get().getId());
        assertEquals("DTR0001", fakeRepository.findByUsername("d1").get().getId());
    }

    @Test
    void usernamesAreLowercasedBeforeSaving() {
        RegisterUserDto dto = new RegisterUserDto("Nina", "NINA1", "pass");

        userServices.registerDoctor(dto);

        assertTrue(fakeRepository.findByUsername("nina1").isPresent());
        assertFalse(fakeRepository.findByUsername("NINA1").isPresent());
    }

}
