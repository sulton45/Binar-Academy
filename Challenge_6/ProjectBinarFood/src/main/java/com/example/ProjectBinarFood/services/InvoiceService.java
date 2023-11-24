package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.OrderDetail;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class InvoiceService {

    public void generateOrderDetailPDFReport(List<OrderDetail> orderDetails, String pdfFilePath) {
        try {
            PdfWriter writer = new PdfWriter(pdfFilePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (OrderDetail orderDetail : orderDetails) {
                document.add(new Paragraph("Merchant ID: " + orderDetail.getOrder().getMerchantId()));
                document.add(new Paragraph("Merchant Name: " + orderDetail.getProduct().getMerchant().getMerchantName()));
                document.add(new Paragraph("Produk: " + orderDetail.getProduct().getProductName()));
                document.add(new Paragraph("Quantity: " + orderDetail.getQuantity()));
                document.add(new Paragraph("Total Harga: " + orderDetail.getTotalPrice()));
                document.add(new Paragraph("--------------------------------------"));
            }

            document.close();
            System.out.println("Invoice Sukses di Print.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public void generateAllOrdersPDFReport(List<Order> orders, String pdfFilePath) {
        try {
            PdfWriter writer = new PdfWriter(pdfFilePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (Order order : orders) {
                document.add(new Paragraph("Order ID: " + order.getId()));
                document.add(new Paragraph("Order Time: " + order.getOrderTime()));
                document.add(new Paragraph("Destination Address: " + order.getDestinationAdress()));

                // Get order details for the current order
                List<OrderDetail> orderDetails = order.getOrderDetail();
                for (OrderDetail orderDetail : orderDetails) {
                    document.add(new Paragraph("Merchant Name: " + orderDetail.getProduct().getMerchant().getMerchantName()));
                    document.add(new Paragraph("Product Name: " + orderDetail.getProduct().getProductName()));
                    document.add(new Paragraph("Quantity: " + orderDetail.getQuantity()));
                    document.add(new Paragraph("Total Price: " + orderDetail.getTotalPrice()));
                    document.add(new Paragraph("--------------------------------------"));
                }

                document.add(new Paragraph("======================================"));
            }

            document.close();
            System.out.println("Semua Invoice Sukses di Print.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
