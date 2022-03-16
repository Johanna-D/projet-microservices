package com.example.cart;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> products;

    public Cart(Long id, List<CartItem> products) {
        this.id = id;
        this.products = products;
    }
    public Cart(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }
    public void addProduct(CartItem item){
        if(id != null) { // si le panier n'existe pas

        }
        for( CartItem c: this.products){
            if(item.getId()==getId()){
                c.setQuantity(c.getQuantity()+1);
            }
            else{
                this.products.add(item);
            }
        }

    }
}
