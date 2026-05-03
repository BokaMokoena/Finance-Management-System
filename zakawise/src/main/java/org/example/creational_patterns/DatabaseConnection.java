package org.example.creational_patterns;

public class DatabaseConnection {

    // volatile ensures visibility across threads
    private static volatile DatabaseConnection instance;

    // private constructor prevents external instantiation
    private DatabaseConnection() {
        System.out.println("Database connection created");
    }

    // double-checked locking (BEST PRACTICE)
    public static DatabaseConnection getInstance() {

        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }

        return instance;
    }

    public void connect() {
        System.out.println("Connecting to database...");
    }
}