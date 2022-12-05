package com.example.eBookingAppointment.registration.token;

import com.example.eBookingAppointment.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false
    )
    private String token;
    @Column(
            nullable = false
    )
    private LocalDateTime dateCreated;
    @Column(
            nullable = false
    )
    private LocalDateTime dateExpiredAt;

    @ManyToOne(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    private LocalDateTime confirmedAt;

    public ConfirmationToken(String token,
                             LocalDateTime dateCreated,
                             LocalDateTime dateExpiredAt,
                             User user) {
        this.token = token;
        this.dateCreated = dateCreated;
        this.dateExpiredAt = dateExpiredAt;
        this.user = user;
    }
}
