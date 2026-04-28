package com.example.bakery.model;

/**
 * User - Base abstract class for all system users.
 * OOP: Encapsulation (private fields + getters/setters), Abstraction (abstract method),
 * Inheritance base (Customer and Staff extend this class).
 * Lecture 01, 02, 04, 05
 */
public abstract class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;

    public User() {}

    public User(int id, String username, String password, String email, String phone, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Abstract method forces subclasses to provide specific behavior (Lecture 05)
    public abstract String getDashboardPath();

    // Method Overriding - toString (Lecture 04)
    @Override
    public String toString() {
        return id + "," + username + "," + password + "," + email + "," + phone + "," + role;
    }

    // Getters and Setters (Encapsulation - Lecture 02)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
