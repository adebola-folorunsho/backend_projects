package user.services;


import org.mindrot.jbcrypt.BCrypt;
import user.data.model.User;
import user.data.repository.UserRepositoryInterface;
import user.dtos.requests.UserLoginRequestDto;
import user.dtos.requests.RegisterUserDto;
import user.dtos.responses.UserLoginResponseDto;
import user.exceptions.IncorrectPasswordException;
import user.exceptions.NonExistentUsernameException;
import user.exceptions.UsernameTakenException;


import java.util.List;
import java.util.Optional;

public class UserServices {
    private final UserRepositoryInterface repository;
    private int counterD = 1;
    private int counterP = 1;
    public UserServices(UserRepositoryInterface repository) {
        this.repository = repository;
    }

    //note
    //check username already exists before registration(authentication)
    //check password follows rules
    //user controller
    // if user can log in, id is hardcoded to logic when trying to find prescription records
    //change username/password
    //validate fields

    public void registerDoctor(RegisterUserDto registerUserDto) {
        checkUsernameExists(registerUserDto.getUserName());
        String hash = BCrypt.hashpw(registerUserDto.getRawPassword(), BCrypt.gensalt());
        repository.save(new User(registerUserDto.getName().toLowerCase(), registerUserDto.getUserName().toLowerCase(), hash, generateIdForDoctor()));
    }

    public void registerPharmacist(RegisterUserDto registerUserDto) {
        checkUsernameExists(registerUserDto.getUserName().toLowerCase());
        String hash = BCrypt.hashpw(registerUserDto.getRawPassword(), BCrypt.gensalt());
        repository.save(new User(registerUserDto.getName().toLowerCase(), registerUserDto.getUserName().toLowerCase(), hash, generateIdForPharmacist()));
    }

    public UserLoginResponseDto login(UserLoginRequestDto loginInfo) {
        User foundUser = authenticate(loginInfo);
        UserLoginResponseDto loginResponse = new UserLoginResponseDto();
        loginResponse.setId(foundUser.getId());
        return loginResponse;
    }

    private User findUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new NonExistentUsernameException(username));
    }

    private void verifyPassword(String rawPassword, String hashedPassword, String username) {
        if (!BCrypt.checkpw(rawPassword, hashedPassword)) {
            throw new IncorrectPasswordException(username);
        }
    }

    private User authenticate(UserLoginRequestDto loginInfo) {
        User user = findUserByUsername(loginInfo.getUsername());
        verifyPassword(loginInfo.getPassword(), user.getHashPassword(), user.getUserName());
        return user;
    }

    private void checkUsernameExists(String username){
        List<User> users = repository.findAll();
        Optional<User> user = users.stream().filter(u -> u.getUserName().equals(username))
                .findFirst();
        if(user.isPresent())throw new UsernameTakenException(username);
    }

    private String generateIdForDoctor(){
        return String.format("DTR%04d", counterD++);
    }


    private String generateIdForPharmacist(){
        return String.format("PMC%04d", counterP++);
    }
}
