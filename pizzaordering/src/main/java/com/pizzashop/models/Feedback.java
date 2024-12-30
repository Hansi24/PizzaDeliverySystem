package com.pizzashop.models;

import jakarta.persistence.*;



@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "conformOrder_id", nullable = false)
    private ConformOrder conformOrder;

    @Column(nullable = false)
    private String feedback;

    @Column(nullable = false)
    private int rating; // Rating out of 5

    public Feedback(User user, String feedback, ConformOrder conformOrder, int rating) {
        this.user = user;
        this.feedback = feedback;
        this.conformOrder = conformOrder;
        this.rating = rating;
    }

    public Feedback() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public ConformOrder getConformOrder() {
        return conformOrder;
    }

    public void setConformOrder(ConformOrder conformOrder) {
        this.conformOrder = conformOrder;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
