package org.example.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    private String notificationId;

    private String message;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notification() {}

    @PrePersist
    public void ensureId() {
        if (notificationId == null) {
            notificationId = UUID.randomUUID().toString();
        }
    }

    // getters and setters
    public String getNotificationId() { return notificationId; }
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification n = (Notification) o;
        return Objects.equals(notificationId, n.notificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId);
    }
}