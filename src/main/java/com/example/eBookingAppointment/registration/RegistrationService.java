package com.example.eBookingAppointment.registration;

import com.example.eBookingAppointment.user.User;
import com.example.eBookingAppointment.user.UserRole;
import com.example.eBookingAppointment.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                new User(
                        request.getEmail(),
                        request.getTelephoneNumber(),
                        request.getPassword(),
                        UserRole.ROLE_ADMIN));
    }

}
