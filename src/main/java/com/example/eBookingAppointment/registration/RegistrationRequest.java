package com.example.eBookingAppointment.registration;

import com.example.eBookingAppointment.entity.user.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String email;
    private final String password;
    private final String telephoneNumber;

}