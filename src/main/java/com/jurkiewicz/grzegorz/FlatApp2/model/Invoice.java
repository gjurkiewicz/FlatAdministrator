package com.jurkiewicz.grzegorz.FlatApp2.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_id")
    private int id;

    @Column
    @NotNull
    private String status;

    @Column
    @NotNull
    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Invoice(@NotNull String status, @NotNull int amount, User user) {
        this.status = status;
        this.amount = amount;
        this.user = user;
    }
    public Invoice(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }
}
