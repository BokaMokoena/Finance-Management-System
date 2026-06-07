package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId("user123");
        testUser.setName("John Doe");
        testUser.setEmail("john.doe@example.com");
        testUser.setSalary(75000.0);
        testUser.setOccupation("Software Engineer");
    }

    @Test
    void create_shouldReturnCreatedUser_whenValidInput() throws Exception {
        when(userService.create(anyString(), anyString(), anyString(), anyDouble(), anyString()))
                .thenReturn(testUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("user123")))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")))
                .andExpect(jsonPath("$.salary", is(75000.0)))
                .andExpect(jsonPath("$.occupation", is("Software Engineer")));

        verify(userService, times(1))
                .create("user123", "John Doe", "john.doe@example.com", 75000.0, "Software Engineer");
    }

    @Test
    void create_shouldHandleUserWithMinimalData() throws Exception {
        User minimalUser = new User();
        minimalUser.setUserId("user456");
        minimalUser.setName("Jane");
        minimalUser.setEmail("jane@example.com");

        when(userService.create(eq("user456"), eq("Jane"), eq("jane@example.com"), isNull(), isNull()))
                .thenReturn(minimalUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(minimalUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("user456")))
                .andExpect(jsonPath("$.name", is("Jane")));

        verify(userService, times(1))
                .create("user456", "Jane", "jane@example.com", null, null);
    }

    @Test
    void get_shouldReturnUser_whenUserExists() throws Exception {
        when(userService.get("user123")).thenReturn(testUser);

        mockMvc.perform(get("/api/users/user123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("user123")))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));

        verify(userService, times(1)).get("user123");
    }

    @Test
    void get_shouldThrowException_whenUserNotFound() throws Exception {
        when(userService.get("nonexistent"))
                .thenThrow(new RuntimeException("User not found with id: nonexistent"));

        mockMvc.perform(get("/api/users/nonexistent"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).get("nonexistent");
    }

    @Test
    void update_shouldReturnUpdatedUser_whenValidInput() throws Exception {
        User updatedUser = new User();
        updatedUser.setUserId("user123");
        updatedUser.setName("John Updated");
        updatedUser.setEmail("john.updated@example.com");
        updatedUser.setSalary(85000.0);
        updatedUser.setOccupation("Senior Software Engineer");

        when(userService.update(eq("user123"), org.mockito.ArgumentMatchers.any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/user123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Updated")))
                .andExpect(jsonPath("$.email", is("john.updated@example.com")))
                .andExpect(jsonPath("$.salary", is(85000.0)))
                .andExpect(jsonPath("$.occupation", is("Senior Software Engineer")));

        verify(userService, times(1)).update(eq("user123"), org.mockito.ArgumentMatchers.any(User.class));
    }

    @Test
    void update_shouldThrowException_whenUserNotFound() throws Exception {
        User updateData = new User();
        updateData.setName("New Name");

        when(userService.update(eq("nonexistent"), org.mockito.ArgumentMatchers.any(User.class)))
                .thenThrow(new RuntimeException("User not found with id: nonexistent"));

        mockMvc.perform(put("/api/users/nonexistent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).update(eq("nonexistent"), org.mockito.ArgumentMatchers.any(User.class));
    }

    @Test
    void update_shouldAllowPartialUpdate() throws Exception {
        User partialUpdate = new User();
        partialUpdate.setUserId("user123");
        partialUpdate.setName("Updated Name Only");
        partialUpdate.setEmail(testUser.getEmail());
        partialUpdate.setSalary(testUser.getSalary());
        partialUpdate.setOccupation(testUser.getOccupation());

        when(userService.update(eq("user123"), org.mockito.ArgumentMatchers.any(User.class))).thenReturn(partialUpdate);

        mockMvc.perform(put("/api/users/user123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partialUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Name Only")));

        verify(userService, times(1)).update(eq("user123"), org.mockito.ArgumentMatchers.any(User.class));
    }

    @Test
    void delete_shouldDeleteUser_whenUserExists() throws Exception {
        doNothing().when(userService).delete("user123");

        mockMvc.perform(delete("/api/users/user123"))
                .andExpect(status().isOk());

        verify(userService, times(1)).delete("user123");
    }

    @Test
    void delete_shouldThrowException_whenUserNotFound() throws Exception {
        doThrow(new RuntimeException("User not found with id: nonexistent"))
                .when(userService).delete("nonexistent");

        mockMvc.perform(delete("/api/users/nonexistent"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).delete("nonexistent");
    }

    @Test
    void create_shouldHandleNullUserId() throws Exception {
        User invalidUser = new User();
        invalidUser.setName("Test");
        invalidUser.setEmail("test@example.com");
        invalidUser.setUserId(null);

        when(userService.create(isNull(), anyString(), anyString(), anyDouble(), anyString()))
                .thenReturn(invalidUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isOk());

        verify(userService, times(1))
                .create(isNull(), eq("Test"), eq("test@example.com"), isNull(), isNull());
    }

    @Test
    void create_shouldHandleInvalidEmail() throws Exception {
        User invalidEmailUser = new User();
        invalidEmailUser.setUserId("user789");
        invalidEmailUser.setName("Test User");
        invalidEmailUser.setEmail("invalid-email");

        when(userService.create(anyString(), anyString(), eq("invalid-email"), isNull(), isNull()))
                .thenReturn(invalidEmailUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmailUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("invalid-email")));

        verify(userService, times(1))
                .create("user789", "Test User", "invalid-email", null, null);
    }

    @Test
    void create_shouldHandleNegativeSalary() throws Exception {
        User negativeSalaryUser = new User();
        negativeSalaryUser.setUserId("user999");
        negativeSalaryUser.setName("Test");
        negativeSalaryUser.setEmail("test@example.com");
        negativeSalaryUser.setSalary(-1000.0);

        when(userService.create(anyString(), anyString(), anyString(), eq(-1000.0), anyString()))
                .thenReturn(negativeSalaryUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(negativeSalaryUser)))
                .andExpect(status().isOk());

        verify(userService, times(1))
                .create("user999", "Test", "test@example.com", -1000.0, null);
    }
}
