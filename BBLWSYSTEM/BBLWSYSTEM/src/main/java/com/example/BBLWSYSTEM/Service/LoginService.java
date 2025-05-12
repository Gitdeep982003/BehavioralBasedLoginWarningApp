package com.example.BBLWSYSTEM.Service;

import com.example.BBLWSYSTEM.Entity.LoginEvent;
import com.example.BBLWSYSTEM.Repository.LoginEventRep;
import com.example.BBLWSYSTEM.Repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginEventRep loginEventRep;

    @Autowired
    private UserRep userRep;

    public boolean processLogin(String username, String ipAddress) {
        LocalDateTime now = LocalDateTime.now();

        // Step 1: Get recent login history for the user
        List<LoginEvent> recentLogins = loginEventRep.findTop5ByUsernameOrderByLoginTimeDesc(username);

        // Step 2: Check if login is suspicious
        boolean suspicious = isSuspiciousLogin(now, ipAddress, recentLogins);

        // Step 3: Save login attempt
        LoginEvent event = new LoginEvent();
        event.setUsername(username);
        event.setIpAddress(ipAddress);
        event.setLoginTime(now);
        event.setSuspicious(suspicious);
        loginEventRep.save(event);

        // Optional: Email alert logic if suspicious
        // if (suspicious) sendAlert(userRepository.findByUsername(username));

        return suspicious;
    }

    private boolean isSuspiciousLogin(LocalDateTime now, String ip, List<LoginEvent> history) {
        for (LoginEvent past : history) {
            // IP address mismatch
            if (!past.getIpAddress().equals(ip)) {
                return true;
            }

            // Odd login hours (e.g., 12am–5am)
            int hour = now.getHour();
            if (hour < 5 || hour > 23) {
                return true;
            }

            // Too many recent attempts (e.g., more than 3 in last 10 mins)
            if (past.getLoginTime().isAfter(now.minusMinutes(10))) {
                long count = history.stream()
                        .filter(e -> e.getLoginTime().isAfter(now.minusMinutes(10)))
                        .count();
                if (count >= 3) {
                    return true;
                }
            }
        }

        return false;
    }
}
