package com.sales.crm.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfGenerationService {

    public void generatePdf(String text, String filePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            document.open();

            Paragraph paragraph = new Paragraph(text);
            document.add(paragraph);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions
        }
    }
}

