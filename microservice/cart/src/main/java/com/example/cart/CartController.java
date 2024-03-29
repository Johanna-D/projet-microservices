package com.example.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping(value = "/cart")
    public ResponseEntity<Cart> createNewCart(@RequestBody Cart cartData)
    {
        Cart cart = cartRepository.saveAndFlush(cartData);
        if (cart == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }
    @GetMapping(value = "/cart/{id}")
    public Optional<Cart> getCart(@PathVariable Long id)
    {
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");
        return cart;
    }

    @PostMapping(value = "/cart/{id}")
    @Transactional
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long id, @RequestBody CartItem cartItem)
    {
        Cart cart = cartRepository.getById(id);
        if (cart == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");
        cart.addProduct(cartItem);
        cart = cartRepository.save(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    @PostMapping(value = "/cart/delete/{id}")
    public void deleteCart(@PathVariable Long id){
        Cart cart = cartRepository.getById(id);
        cartRepository.delete(cart);
    }


}
