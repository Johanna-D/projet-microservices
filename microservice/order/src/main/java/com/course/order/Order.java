package com.course.order;

import javax.persistence.*;

@Entity(name="MyOrder")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long cartId;
    private double price;

    public Order(){}

    public Order(Long id, Long cartId, double price) {
        this.id = id;
        this.cartId = cartId;
        this.price = price;
    }

    public Order(Long cartId) {
        this.cartId = cartId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", price=" + price +
                '}';
    }


}