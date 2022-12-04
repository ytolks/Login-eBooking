package com.example.eBookingAppointment.registration;

import com.example.eBookingAppointment.entity.user.User;
import com.example.eBookingAppointment.entity.user.UserService;
import com.example.eBookingAppointment.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@AllArgsConstructor
public class RegistrationController {

    private AdminRegistrationService adminRegistrationService;
    private ConfirmationTokenService confirmationTokenService;
    private UserService userService;

    @GetMapping(path = "admin/user/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> showUsers(){
        return userService.showAllUsers();
    }

    @GetMapping
    public String welcome(){
        return "Welcome to AlinaNails appointment resource!";
    }

    @GetMapping(path="admin/user/list/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User findUserId(@PathVariable (value = "id") Long id){
        return userService.findUserById(id);

    }

    @DeleteMapping(path="admin/user/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUserById(@PathVariable (value = "id") Long id){
        confirmationTokenService.deleteUserById(id);
    }

    @PostMapping(path = "user/registration")
    public String register(@RequestBody RegistrationRequest request){
        return adminRegistrationService.register(request);
    }

    @PutMapping(path="admin/update/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateUserData(@RequestBody User user,
                               @PathVariable(value = "id") Long id){
        userService.updateUserDataById(user,id);
    }

}
