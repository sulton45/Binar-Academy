package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.OrderDetail;
import com.example.ProjectBinarFood.repositories.OrderDetailRepository;
import com.example.ProjectBinarFood.services.InvoiceService;
import com.example.ProjectBinarFood.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("binarfood/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/pdf/{orderId}")
    public String generateOrderDetailPDF(@PathVariable UUID orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return "Order not found.";
        }
        List<OrderDetail> orderDetails = order.getOrderDetail();


        String pdfFilePath = "Invoice_Order_" + orderId + ".pdf";;

        invoiceService.generateOrderDetailPDFReport(orderDetails, pdfFilePath);

        return "PDF report generated successfully.";
    }

    @GetMapping("/pdf/all")
    public String generateAllOrdersPDF() {

        List<Order> orders = orderService.getAllOrdersWithDetails();


        String pdfFilePath = "AllOrders.pdf";

        invoiceService.generateAllOrdersPDFReport(orders, pdfFilePath);

        return "PDF report for all orders generated successfully.";
    }
}

