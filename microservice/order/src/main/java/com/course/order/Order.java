package com.course.order;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long cartId;
    private Long price;

    public Order(){}

    public Order(Long id, Long cartId, Long price) {
        this.id = id;
        this.cartId = cartId;
        this.price = price;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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