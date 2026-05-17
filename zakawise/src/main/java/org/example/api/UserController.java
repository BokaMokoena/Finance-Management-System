package org.example.api;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user) {

        return service.create(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getSalary(),
                user.getOccupation()
        );
    }

    @GetMapping("/{id}")
    public User get(@PathVariable String id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id,
                       @RequestBody User user) {

        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}