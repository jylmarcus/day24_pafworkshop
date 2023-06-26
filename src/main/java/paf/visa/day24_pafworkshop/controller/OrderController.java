package paf.visa.day24_pafworkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import paf.visa.day24_pafworkshop.model.Order;
import paf.visa.day24_pafworkshop.service.OrderService;

@Controller
public class OrderController {

    @Autowired OrderService orderService;
    
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("orderForm", new Order());
        return "index";
    }

    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createOrder(@ModelAttribute("orderForm") Order order, Model model) {
        //send order object to service for processing
        orderService.createOrder(order);
        //attach return object to model if needed
        return "success";
    }
}
