package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.OrderDetail;
import com.example.ProjectBinarFood.models.Product;
import com.example.ProjectBinarFood.payloads.requests.OrderDetailDTO;
import com.example.ProjectBinarFood.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;


    public void createOrderDetail(OrderDetailDTO orderDetailDTO){

        OrderDetail orderDetail = new OrderDetail();
        Order order = orderService.findOrderById(orderDetailDTO.getOrder());
        Product product = productService.findById(orderDetailDTO.getProduct());
        double totalPrice = orderDetailDTO.getQuantity() * product.getPrice();

        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setTotalPrice(totalPrice);
        orderDetailRepository.save(orderDetail);

    }

}
