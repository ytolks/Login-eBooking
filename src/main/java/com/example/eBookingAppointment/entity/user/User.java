package com.example.eBookingAppointment.entity.user;

import com.example.eBookingAppointment.entity.Procedure;
import com.example.eBookingAppointment.registration.token.ConfirmationToken;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "VARCHAR(45)"
    )
    private String email;
    @Column(
            name = "contact_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String telephoneNumber;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)

    @Column (
            nullable = false
    )
    private UserRole userRole;
    private Boolean locked;
    private Boolean enabled;

    public User(String email, String telephoneNumber, String password, UserRole userRole) {
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
        this.userRole = userRole;
    }

    @OneToMany(mappedBy = "user")
    private List<ConfirmationToken> confirmationTokens;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "appointments",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(
                            name = "appointment_user_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "procedure_id",
                    foreignKey = @ForeignKey(
                            name = "appointment_procedure_id")
            )
    )
    private List<Procedure> procedures;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority
                = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
