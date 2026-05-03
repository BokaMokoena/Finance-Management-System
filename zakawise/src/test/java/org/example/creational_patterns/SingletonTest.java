package org.example.creational_patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {

    @Test
    public void testSingletonInstance() {

        DatabaseConnection a = DatabaseConnection.getInstance();
        DatabaseConnection b = DatabaseConnection.getInstance();

        assertSame(a, b);
    }
}