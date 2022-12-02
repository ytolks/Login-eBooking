package com.example.eBookingAppointment.registration;

import com.example.eBookingAppointment.user.User;
import com.example.eBookingAppointment.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private UserService userService;

    @GetMapping("/user-list")
    public List<User> showUsers(){
        return userService.showAllUsers();
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

}
