package com.example.eBookingAppointment.entity;

import com.example.eBookingAppointment.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (
            updatable = false
    )
    private Long Id;
    @Column (
            nullable = false

    )
    private String procedureType;
    @Column(
            nullable = false
    )
    private BigDecimal price;

    @Column(
            nullable = false
    )
    private LocalDateTime createdAt;





    @ManyToMany(mappedBy = "procedures")
    private List<User> users;


    @Override
    public String toString() {
        return "Procedure{" +
                "Id=" + Id +
                ", procedureType='" + procedureType + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
