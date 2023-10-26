/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.invoice;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import zcommon.domain.Invoice;

/**
 *
 * @author Korisnik
 */
public class MakeAPDFofInvoice {
    
    String destination;
        
    PdfWriter writer;
    
    PdfDocument pdfDocument;

    Document doc;
    
    String fontBold = "D:\\Projekti\\NetBeansProjects\\iText-Core-8.0.1-only-jars-and-fonts-and-l4j\\Times-New-Roman\\times-new-roman-bold.ttf";
    String fontRegular = "D:\\Projekti\\NetBeansProjects\\iText-Core-8.0.1-only-jars-and-fonts-and-l4j\\Times-New-Roman\\times-new-roman.ttf";
    String fontItalic = "D:\\Projekti\\NetBeansProjects\\iText-Core-8.0.1-only-jars-and-fonts-and-l4j\\Times-New-Roman\\times-new-roman-italic.ttf";
    
    String title;
    
    
    public void exportInvoiceToPDF(ArrayList<Invoice> invoices) throws FileNotFoundException, IOException {
                
        int firstInvoiceID = invoices.get(0).getInvoiceID();
        int firstOrderID = invoices.get(0).getOrderID().getOrderID();
        destination = "../PDFs/invoice" + firstInvoiceID + firstOrderID + ".pdf";

        writer = new PdfWriter(destination);
        pdfDocument = new PdfDocument(writer);
        pdfDocument.addNewPage();
        doc = new Document(pdfDocument);
        
        
        FontProgram fontProgramBold = FontProgramFactory.createFont(fontBold);
        PdfFont fontBold = PdfFontFactory.createFont(fontProgramBold);
        
        title = "INVOICE\n\n"; 
        
        // Creating an Area Break    
        Paragraph pTitle = new Paragraph(title).setFont(fontBold).setTextAlignment(TextAlignment.CENTER);
        doc.add(pTitle);
        
        String message = "Thank you for purchasing at ac249 store! \n Your items: \n";
        Paragraph mess = new Paragraph(message);
        doc.add(mess);
        
        float [] pointColumnWidths = {150F, 150F, 150F, 150F}; 
        Table table = new Table(pointColumnWidths);
         
        table.addCell("No.");
        table.addCell("Title");      // Adding cell to the table       
        table.addCell("Quantity"); 
        table.addCell("Price");
         
        int counter = 0;
        double totalPrice = 0;
        
        for(int i = 0; i<invoices.size(); i++) {
            for(int j = 0; j<invoices.get(i).getOrderID().getListOfItem().size(); j++) {
                table.addCell("" + ++counter);
                table.addCell(invoices.get(i).getOrderID().getListOfItem().get(j).getProductID().getTitle());
                table.addCell(Integer.toString(invoices.get(i).getOrderID().getListOfItem().get(j).getQuantity()));
                double price = invoices.get(i).getOrderID().getListOfItem().get(j).getQuantity()*invoices.get(i).getOrderID().getListOfItem().get(j).getProductID().getPrice();
                table.addCell(Double.toString(price));
            }
            totalPrice += invoices.get(i).getAmount();
        }
        FontProgram fontProgramRegular = FontProgramFactory.createFont(fontRegular);
        PdfFont fontRegular = PdfFontFactory.createFont(fontProgramRegular);
        
        table.setFont(fontRegular);
        doc.add(table);
        
        
        FontProgram fontProgramItalic = FontProgramFactory.createFont(fontItalic);
        PdfFont fontItalic = PdfFontFactory.createFont(fontProgramItalic);
        
        String finalPrice = "\n Your total price is: " + totalPrice;
        Paragraph messForPrice = new Paragraph(finalPrice).setFont(fontItalic); 
        messForPrice.setTextAlignment(TextAlignment.RIGHT);
        
        doc.add(messForPrice);
        
        doc.close();
        
        System.out.println("pdf invoice created");
    }
    
}
