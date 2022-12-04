package com.example.eBookingAppointment.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);

//    @Modifying
//    @Query("update User u set u.telephoneNumber=:telephoneNumber where u.id =:id")
//    void updateContactInfoById(@Param(value = "telephoneNumber") String telephoneNumber,
//                         @Param(value = "id") Long Id);
}
