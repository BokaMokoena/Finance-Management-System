package org.example.api;

import org.example.model.Notification;
import org.example.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications", description = "Notification management APIs")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Send notification")
    public Notification send(@RequestBody Notification notification) {
        return service.send(
                notification.getUser(),
                notification.getMessage()
        );
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user notifications")
    public List<Notification> getUserNotifications(@PathVariable String userId) {
        return service.getAll();
    }

    @PutMapping("/{id}/read")
    @Operation(summary = "Mark notification as read")
    public Notification markRead(@PathVariable String id) {
        return service.markRead(id);
    }
}