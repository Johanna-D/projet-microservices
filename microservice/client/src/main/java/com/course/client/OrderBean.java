package com.course.client;

public class OrderBean {
    private Long id;
    private Long cartId;
    private double price;

    public OrderBean(Long id, Long cartId, double price) {
        this.id = id;
        this.cartId = cartId;
        this.price = price;
    }
    public OrderBean(){}

    public OrderBean(Long cartId, double price){
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", price=" + price +
                '}';
    }
}
