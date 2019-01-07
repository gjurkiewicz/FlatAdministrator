package com.jurkiewicz.grzegorz.FlatApp2.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class MediaAndService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "mediaandservice_id")
    private int id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private int fee;

    public MediaAndService(@NotNull String name, @NotNull int fee, User user) {
        this.name = name;
        this.fee = fee;
        this.user = user;
    }

    public MediaAndService(@NotNull String name, @NotNull int fee) {
        this.name = name;
        this.fee = fee;
    }

    @ManyToOne
    @JoinColumn
    private User user;

    public MediaAndService() {
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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MediaAndService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fee=" + fee +
                ", user=" + user +
                '}';
    }
}
