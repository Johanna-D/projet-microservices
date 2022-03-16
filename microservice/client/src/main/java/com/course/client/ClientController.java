package com.course.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;
    @Autowired
    private MsProductProxy msCartProxy;

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }
    @RequestMapping("/product-detail/{id}")
    public String description(Model model, @PathVariable Long id) {
        ProductBean product = msProductProxy.get(id).get();
        model.addAttribute("product",product);
        return "product-detail";
    }

    @RequestMapping(value = { "/product-detail/{id}" }, method = RequestMethod.POST)
    public String addToCart(Model model, @PathVariable Long id) {
        ProductBean product = msProductProxy.get(id).get();
        msCartProxy.addProductToCart();
        // if cart exist, ajouter dans le cart existant
        // sinon, créer un nouveau cart



        if (product != null ) {


            return "redirect:/index";
        }

        return "product-detail";
    }
}