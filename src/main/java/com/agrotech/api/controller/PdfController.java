package com.agrotech.api.controller;

import com.agrotech.api.services.PdfService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/pdfgenerate")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/download-pdf")
    public void downloadPdf(@RequestParam String title, @RequestParam String content, HttpServletResponse response) throws IOException, DocumentException, DocumentException {
        System.out.println("ddddddd");
        Context context = new Context();
//        context.setVariable("title", title);
//        context.setVariable("content", content);

        byte[] pdfBytes = pdfService.generatePdf("template", context);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=generated.pdf");
        response.getOutputStream().write(pdfBytes);
    }
}
