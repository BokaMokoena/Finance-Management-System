package org.example.api;

import org.example.model.Notification;
import org.example.model.User;
import org.example.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public Notification send(@RequestBody Notification notification) {

        User user = notification.getUser();

        return service.send(
                user,
                notification.getMessage()
        );
    }

    @GetMapping("/{userId}")
    public List<Notification> getUserNotifications(
            @PathVariable String userId) {

        return service.getAll();
    }

    @PutMapping("/{id}/read")
    public Notification markRead(@PathVariable String id) {
        return service.markRead(id);
    }
}