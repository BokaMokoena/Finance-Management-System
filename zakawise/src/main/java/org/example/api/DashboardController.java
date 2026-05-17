package org.example.api;

import org.example.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public Map<String, Double> getSummary(
            @PathVariable String userId) {

        return service.getSummary();
    }
}