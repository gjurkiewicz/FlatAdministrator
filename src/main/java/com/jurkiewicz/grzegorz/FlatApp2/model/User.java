package com.jurkiewicz.grzegorz.FlatApp2.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private int flatNumber;

    @Column
    private String parkingNumber;

    @Column (name = "email")
    @NotNull
    @Email
    private String email;

    @Column (name = "password")
    @NotNull
    @Length(min = 5)
    private String password;

    @Column(name = "active")
    private int active;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name ="user_id" ),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles ;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn (name = "user_id")
    List<MediaAndService> mediaAndServiceList = new ArrayList<>();


    public void addProduct(MediaAndService  mediaAndService) {
        mediaAndServiceList.add(mediaAndService);
        mediaAndService.setUser(this);
    }

    public void removeProduct(MediaAndService mediaAndService) {
        mediaAndServiceList.remove(mediaAndService);
        mediaAndService.setUser(null);
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(String parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<MediaAndService> getMediaAndServiceList() {
        return mediaAndServiceList;
    }

    public void setMediaAndServiceList(List<MediaAndService> mediaAndServiceList) {
        this.mediaAndServiceList = mediaAndServiceList;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", flatNumber=" + flatNumber +
                ", parkingNumber='" + parkingNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", mediaAndServiceList=" + mediaAndServiceList +
                '}';
    }
}