/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.order;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import zcommon.domain.Invoice;
import zcommon.domain.Order;

/**
 *
 * @author Korisnik
 */
public class MakeAPDFofOrder {
    
    //pdf ce da sadrzi naziv u zavisnosti da li je poslat ORder koji nije odobren ili invoice ordera koji je odobren
    //znaci jedna metoda makeAPDFfromInvoice i jedna makeAPDFfromOrder
    
    //e sad on hoce da moze i od svih ordera da napravi pdf, znaci napravicu metodu koja prima listu, a ako tamo trazi da se exportuje
    //pdf samo od jednog ordera samo cu da napravim listu ordera i samo njega stavim unutra
    
//svakako hocu da se pravi pdf kad se odobri order sa svim order itemima
    
    //naslov
    //tabela:
    // No.      Title       Quantity     Price (qunt*price)
    
    //ukupna cena
    
    String destination;
        
    PdfWriter writer;
    
    PdfDocument pdfDocument;

    Document doc;
    
    String fontBold = "D:\\Projekti\\NetBeansProjects\\iText-Core-8.0.1-only-jars-and-fonts-and-l4j\\Times-New-Roman\\times-new-roman-bold.ttf";
    String fontRegular = "D:\\Projekti\\NetBeansProjects\\iText-Core-8.0.1-only-jars-and-fonts-and-l4j\\Times-New-Roman\\times-new-roman.ttf";
    String fontItalic = "D:\\Projekti\\NetBeansProjects\\iText-Core-8.0.1-only-jars-and-fonts-and-l4j\\Times-New-Roman\\times-new-roman-italic.ttf";
    
    String title;

    public String getDestination() {
        return destination;
    }
    
    public void exportOrderToPDF(ArrayList<Order> orders) throws FileNotFoundException, IOException {

        int firstOrderID = orders.get(0).getOrderID();
        String userOFOrders = orders.get(0).getUserID().getName();
        long random = Math.round(Math.random()*100);
        destination = "../PDFs/order" + userOFOrders + firstOrderID + random + ".pdf";

        //za dest random Math.round(Math.random()*9);
        
        writer = new PdfWriter(destination);
        pdfDocument = new PdfDocument(writer);
        pdfDocument.addNewPage();
        doc = new Document(pdfDocument);
        
        
        FontProgram fontProgramBold = FontProgramFactory.createFont(fontBold);
        PdfFont fontBold = PdfFontFactory.createFont(fontProgramBold);
        
        title = "Thank you for purchasing at ac249 store! \n\n"; 
        
        // Creating an Area Break    
        Paragraph pTitle = new Paragraph(title).setFont(fontBold);
        doc.add(pTitle);
        
        String message = "Your items: \n";
        Paragraph mess = new Paragraph(message);
        doc.add(mess);
        
        float [] pointColumnWidths = {150F, 150F, 150F, 150F}; 
        Table table = new Table(pointColumnWidths);
         
        table.addCell("No.");
        table.addCell("Title");      // Adding cell to the table       
        table.addCell("Quantity"); 
        table.addCell("Price");
         
        Border b1 = new DashedBorder(2);
 
//new Style().setBorderBottom(new DottedBorder(1))
  
        int counter = 0;
        double totalPrice = 0;
        
        for(int i = 0; i<orders.size(); i++) {
            for(int j = 0; j<orders.get(i).getListOfItem().size(); j++) {
                //if (j == (orders.get(i).getListOfItem().size()-1)) {
                 //   table.addCell("" + ++counter).setBorderBottom(b1);
                 //   table.addCell(orders.get(i).getListOfItem().get(j).getProductID().getTitle()).setBorderBottom(b1);
                 //   table.addCell(Integer.toString(orders.get(i).getListOfItem().get(j).getQuantity())).setBorderBottom(b1);
                 //   double price = orders.get(i).getListOfItem().get(j).getQuantity()*orders.get(i).getListOfItem().get(j).getProductID().getPrice();
                 //   table.addCell(Double.toString(price)).setBorderBottom(b1);
                ///} else {
                table.addCell("" + ++counter);
                table.addCell(orders.get(i).getListOfItem().get(j).getProductID().getTitle());
                table.addCell(Integer.toString(orders.get(i).getListOfItem().get(j).getQuantity()));
                double price = orders.get(i).getListOfItem().get(j).getQuantity()*orders.get(i).getListOfItem().get(j).getProductID().getPrice();
                table.addCell(Double.toString(price));
               // }   
            }
        totalPrice += orders.get(i).getTotalAmountPricee();
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
        
        System.out.println("pdf order created");
    }

    
}
