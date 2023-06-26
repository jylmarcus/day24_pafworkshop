package paf.visa.day24_pafworkshop.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paf.visa.day24_pafworkshop.exception.UnableToCreateOrderException;
import paf.visa.day24_pafworkshop.model.Order;
import paf.visa.day24_pafworkshop.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;

    @Transactional
    public boolean createOrder(Order order) {
        order.setOrderDate(new Date(System.currentTimeMillis()));
        if(!orderRepository.createOrder(order) || !orderRepository.createOrderDetails(order)) {
            throw new UnableToCreateOrderException("Incorrect parameters");
        }

        orderRepository.createOrder(order);
        orderRepository.createOrderDetails(order);

        return true;
    }
    
}
