package com.smart.entities;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name field is required !!")
    @Size(min = 2, max = 20, message = "Min 2 and Max 20 characters allowed !!")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Email is required !!")
    @Email(message = "Invalid email format !!")
    private String email;

    @NotBlank(message="Password field is required !!")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one special character, and be at least 6 characters long"
    )
    private String password;


    @NotBlank(message = "Role is required !!")
    private String role;

    private boolean enabled;

    @NotBlank(message = "Image URL is required !!")
    private String imageUrl;

    @NotBlank(message = "About field cannot be empty !!")
    @Size(max = 500, message = "About section should not exceed 500 characters !!")
    @Column(length = 500)
    private String about;

    // Relationships
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Contact> contacts = new ArrayList<>();

    // Constructors
    public User(int id, String name, String email, String password, String role, boolean enabled, String imageUrl, String about) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.imageUrl = imageUrl;
        this.about = about;
    }

    public User() {
        super();
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public List<Contact> getContacts() { return contacts; }
    public void setContacts(List<Contact> contacts) { this.contacts = contacts; }

    @Override
    public String toString() {
        return "User [id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", password=" + password +
                ", role=" + role +
                ", enabled=" + enabled +
                ", imageUrl=" + imageUrl +
                ", about=" + about +
                ", contacts=" + contacts + "]";
    }
}
