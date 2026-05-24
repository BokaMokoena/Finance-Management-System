package org.example.api;

import org.example.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Financial summary APIs")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get dashboard summary")
    public Map<String, Double> getSummary(@PathVariable String userId) {
        return service.getSummary(userId);
    }
}