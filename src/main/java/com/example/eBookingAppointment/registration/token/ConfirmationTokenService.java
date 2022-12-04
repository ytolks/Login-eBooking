package com.example.eBookingAppointment.registration.token;

import com.example.eBookingAppointment.entity.user.User;
import com.example.eBookingAppointment.entity.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
     confirmationTokenRepository.save(token);
    }

    public void deleteUserById(Long id){
        confirmationTokenRepository.deleteById(id);
    }

}
