package com.example.BBLWSYSTEM.Repository;

import com.example.BBLWSYSTEM.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<User,String>
{
    // Custom queries if necessary (e.g., find user by email)
    User findByUsername(String username);

}
