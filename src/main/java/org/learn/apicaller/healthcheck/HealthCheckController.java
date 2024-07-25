package org.learn.apicaller.healthcheck;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("health")
public class HealthCheckController {
    @GetMapping("check")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(String.format("Health OK\nTimeStamped: %s", new Date()));
    }
}
