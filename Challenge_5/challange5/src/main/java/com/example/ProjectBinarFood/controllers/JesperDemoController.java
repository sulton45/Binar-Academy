package com.example.ProjectBinarFood.controllers;

import java.io.IOException;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import com.example.ProjectBinarFood.models.Item;
import com.example.ProjectBinarFood.repositories.ItemRepository;
import com.example.ProjectBinarFood.services.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("binarfood/invoice/")
public class JesperDemoController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    JasperReportService jasperReportService;
    @GetMapping("item-report/{format}")
    public ResponseEntity<Resource> getItemReport(@PathVariable String format)
            throws JRException, IOException {

        //dapatkan data
        List<Item> itemList = itemRepository.findAll();

        //olah data di jasper
        byte[] reportContent = jasperReportService.getItemReport(itemList, format);

        //response sebagai file
        ByteArrayResource resource = new ByteArrayResource(reportContent);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("item-report." + format)
                                .build().toString())
                .body(resource);
    }
}