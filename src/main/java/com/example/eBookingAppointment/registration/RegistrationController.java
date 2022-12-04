package com.example.eBookingAppointment.registration;

import com.example.eBookingAppointment.user.User;
import com.example.eBookingAppointment.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> showUsers(){
        return userService.showAllUsers();
    }

    @PostMapping("/registration")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

}
