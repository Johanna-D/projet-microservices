package com.course.client;

import java.util.List;

public class OrderBean {
    private Long id;
    private Long cartId;
    private double price;
    private List<OrderItemBean> orderItems;

    public OrderBean(Long id, Long cartId, double price, List<OrderItemBean> orderItems) {
        this.id = id;
        this.cartId = cartId;
        this.price = price;
        this.orderItems = orderItems;
    }

    public OrderBean(){}

    public OrderBean(Long cartId, double price,  List<OrderItemBean> orderItems){
        this.cartId = cartId;
        this.price = price;
        this.orderItems = orderItems;
    }

    public List<OrderItemBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemBean> orderItems) {
        this.orderItems = orderItems;
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
                ", orderItems=" + orderItems +
                '}';
    }
}
