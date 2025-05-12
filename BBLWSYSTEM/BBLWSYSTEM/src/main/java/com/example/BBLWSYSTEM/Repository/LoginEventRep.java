package com.example.BBLWSYSTEM.Repository;

import com.example.BBLWSYSTEM.Entity.LoginEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginEventRep extends JpaRepository<LoginEvent,Integer>
{
    // Find the last 5 login events by username, sorted by login time in descending order
    List<LoginEvent> findTop5ByUsernameOrderByLoginTimeDesc(String username);

}
