package com.example.BBLWSYSTEM.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LoginEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated ID for each event
    private String username;  // The username of the person attempting to log in
    private String ipAddress;  // The IP address from which the login is attempted
    private LocalDateTime loginTime;  // The time of the login attempt
    private boolean suspicious;  // Whether the login is suspicious or not

    public LoginEvent() {
    }

    public LoginEvent(Long id, String username, String ipAddress, LocalDateTime loginTime, boolean suspicious) {
        this.id = id;
        this.username = username;
        this.ipAddress = ipAddress;
        this.loginTime = loginTime;
        this.suspicious = suspicious;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public void setSuspicious(boolean suspicious) {
        this.suspicious = suspicious;
    }
}
