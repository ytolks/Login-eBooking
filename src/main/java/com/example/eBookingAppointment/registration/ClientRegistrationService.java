package com.example.eBookingAppointment.registration;

import com.example.eBookingAppointment.entity.user.User;
import com.example.eBookingAppointment.entity.user.UserRepository;
import com.example.eBookingAppointment.entity.user.UserRole;
import com.example.eBookingAppointment.entity.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientRegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final UserRepository userRepository;

    public String registerClient(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        Optional<User> userByUserRole = userRepository.findUserByUserRole(UserRole.ROLE_ADMIN);
        boolean existingRole = userByUserRole.isPresent();

        if (!isValidEmail ) {
            throw new IllegalStateException("email not valid");
        }

        if (existingRole) {
            return userService.signUpUser(
                    new User(
                            request.getEmail(),
                            request.getTelephoneNumber(),
                            request.getPassword(),
                            UserRole.ROLE_CLIENT));

        }
        return userService.signUpUser(
                new User(
                        request.getEmail(),
                        request.getTelephoneNumber(),
                        request.getPassword(),
                        UserRole.ROLE_ADMIN));
    }
}
