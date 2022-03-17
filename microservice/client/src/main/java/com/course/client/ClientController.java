package com.course.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    @Autowired
    private MsOrderProxy msOrderProxy;

    Long currentCartId;

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }


    @PostMapping(value = "/{panierId}")
    public Long currentCartId(@PathVariable String panierId)
    {

        if(panierId.equals("undefined") || panierId.equals("null")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");
        }
        else {
            currentCartId = Long.parseLong(panierId);
            return currentCartId;
        }
    }
    @RequestMapping("/product-detail/{id}")
    public String description(Model model, @PathVariable Long id) {
        ProductBean product = msProductProxy.get(id).get();
        model.addAttribute("product",product);
        return "product-detail";
    }

    @RequestMapping(value = { "/product-detail/{id}/{panierId}" }, method = RequestMethod.POST) // quand je reçois une methode de type POST sur cet url, je rentre dans la fcnt en dessous
    public ResponseEntity<CartBean> addToCart(Model model, @PathVariable Long id, @PathVariable String panierId) {
        if(panierId.equals("undefined") || panierId.equals("null")) {
            CartItemBean cartItemBean = new CartItemBean(id,1 ); //un cartItemBean est juste un id de produit avec sa quantité (contrairement au produit, qui a nom, id, image, descr,....)
            List<CartItemBean> cartItemBeansList = new ArrayList<>();
            cartItemBeansList.add(cartItemBean);
            CartBean cartData = new CartBean(cartItemBeansList);
            return msCartProxy.createNewCart(cartData);
        }
        else{
            Long panierIdLong = Long.parseLong(panierId);
            CartItemBean cartItemBean = new CartItemBean(id,1 );
            return msCartProxy.addProductToCart(panierIdLong, cartItemBean);
        }

    }

    @RequestMapping("/cart/{panierId}")
    public String panier(Model model,  @PathVariable String panierId) {
        Long panierIdLong = Long.parseLong(panierId);
        CartBean cart = msCartProxy.getCart(panierIdLong).get();
        model.addAttribute("cart",cart);
        List<CartItemBean> items = cart.getProducts();
        model.addAttribute("cartItems", items);
        return "cart";
    }

    @RequestMapping("/order/{panierId}")
    public String order(Model model,  @PathVariable String panierId) {
        Long panierIdLong = Long.parseLong(panierId);
        CartBean cart = msCartProxy.getCart(panierIdLong).get();

        // créer une variable prixTotal
        // boucle sur le panier, pour chaque produit dans le panier on récupere son prix
        // et on fait prixTotal += prixProduit

        double totalPrice = 0.0;
        List<CartItemBean> items = cart.getProducts(); // produits dans le panier
        List<Double> prices = new ArrayList<>();
        List<OrderItemBean> orderItems = new ArrayList<>();
        for(CartItemBean item : items){
            Long itemID = item.getProductId();
            Optional<ProductBean>  product = msProductProxy.get(itemID);
            double productPrice = product.get().getPrice();
            prices.add(productPrice); // pour avoir la liste des prix

            OrderItemBean orderItemBean = new OrderItemBean(itemID,item.getQuantity(),productPrice);
            orderItems.add(orderItemBean);
            totalPrice += productPrice * item.getQuantity();
        }

        // créer un order qui prend cartID et le prixTotal
        // afficher sur la page les produits du cart avec leur prixProduit
        // afficher le prix total
        // supprimer le cart

        OrderBean orderData = new OrderBean(panierIdLong,totalPrice);
        msOrderProxy.createNewOrder(orderData);

        model.addAttribute("myorder",orderData);
        model.addAttribute("orderItems", orderItems);


        return "order";
    }

}