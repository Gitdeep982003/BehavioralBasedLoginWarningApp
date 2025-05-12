package com.example.BBLWSYSTEM.Controller;

import com.example.BBLWSYSTEM.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Example: POST /api/login?username=john&ip=192.168.1.1
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String ip) {
        boolean suspicious = loginService.processLogin(username, ip);

        if (suspicious) {
            return ResponseEntity.ok("⚠️ Suspicious login detected!");
        } else {
            return ResponseEntity.ok("✅ Login successful.");
        }
    }
}
