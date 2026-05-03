package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;

    private List<User> users = new ArrayList<>();

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void save(Object obj) {
        System.out.println("Saved: " + obj);
    }

    public void update(Object obj) {
        System.out.println("Updated: " + obj);
    }

    public void delete(Object obj) {
        System.out.println("Deleted: " + obj);
    }
}