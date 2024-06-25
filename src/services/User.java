package src.services;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String email;
    private String role;
    private String password;
    private String phoneNumber;
    private String isActive;
    LocalDate created_at;
    LocalDate updated_at;

    public User() {
    }

    public User(int id, String name, String email, String role, String phoneNumber, String isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.created_at = LocalDate.now();
        this.updated_at = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

}
