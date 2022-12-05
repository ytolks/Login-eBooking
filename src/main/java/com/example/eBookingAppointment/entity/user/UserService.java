package com.example.eBookingAppointment.entity.user;

import com.example.eBookingAppointment.registration.EmailValidator;
import com.example.eBookingAppointment.registration.RegistrationRequest;
import com.example.eBookingAppointment.registration.token.ConfirmationToken;
import com.example.eBookingAppointment.registration.token.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private UserRepository userRepository;

    private final static String USER_NOT_FOUND = "user with email %s not found";


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(User user) {
        boolean exists = userRepository.findUserByEmail(user.getEmail()).isPresent();

        if (exists) {
            throw new IllegalStateException("email already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return "signup worked";
    }

    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new UsernameNotFoundException("user not found by id" + id);
        }
        return user;
    }

    @Transactional
    public void updateUserDataById(User updatedUser, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> {
                    user.setEmail(updatedUser.getEmail());
                    user.setTelephoneNumber(updatedUser.getTelephoneNumber());
                    user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
                    user.setUserRole(updatedUser.getUserRole());
                    return userRepository.save(user);
                });
    }
}

