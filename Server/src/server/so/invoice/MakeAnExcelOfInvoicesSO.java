/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.invoice;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zcommon.domain.Invoice;

/**
 *
 * @author Korisnik
 */
public class MakeAnExcelOfInvoicesSO {
    
    public void exportInvoiceToEXCEL(ArrayList<Invoice> invoices) throws FileNotFoundException, IOException {
        //za naziv ce da se uzme rendom broj
        long random = Math.round(Math.random()*100);
        int size = invoices.size();
        String destination = "../EXCELs/inv" + size + random + ".xlsx";
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet");
        
        String[] header = new String[]{"Invoice No.", "Item No.", "Username", "Phone", "Item(product name)", "Item price", "Item quantity", "Total item price", "Total invoice price"};
    
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight((short)4);
        style.setBorderBottom((short)2);
        style.setFont(font);
        
        int counter = 0;
        
                   
        for(int x = 0; x<=invoices.size(); x++) { //prolaz kroz listu ordera
            if (x == 0){
                XSSFRow row = sheet.createRow(x);
                for (int z = 0; z<header.length; z++) {
                    XSSFCell cell = row.createCell(z);
                    cell.setCellValue(header[z]);
                    System.out.println(header[z]);
                    cell.setCellStyle(style);
                }
            } else {
                int itemCounter = 1;
                for (int i = 0; i<invoices.get(x-1).getOrderID().getListOfItem().size(); i++) {   //prolazi kroz listu itema
                    XSSFRow row2 = sheet.createRow(counter + 1);
                    for (int y = 0; y<header.length; y++) { //prolaz kroz hedere
                        XSSFCell cell2 = row2.createCell(y);
                        switch (y) {
                            case 0:
                                cell2.setCellValue(invoices.get(x-1).getInvoiceID());
                                break;
                            case 1:
                                cell2.setCellValue(itemCounter);
                                break;
                            case 2:
                                cell2.setCellValue(invoices.get(x-1).getOrderID().getUserID().getUsername());
                                break;
                            case 3:
                                cell2.setCellValue(invoices.get(x-1).getOrderID().getUserID().getPhoneNumber());
                                break;
                            case 4:
                                cell2.setCellValue(invoices.get(x-1).getOrderID().getListOfItem().get(i).getProductID().getTitle());
                                break;
                            case 5:
                                cell2.setCellValue(invoices.get(x-1).getOrderID().getListOfItem().get(i).getProductID().getPrice().toString());
                                break;
                            case 6:
                                cell2.setCellValue(invoices.get(x-1).getOrderID().getListOfItem().get(i).getQuantity());
                                break;
                            case 7:
                                double price = invoices.get(x-1).getOrderID().getListOfItem().get(i).getProductID().getPrice()*invoices.get(x-1).getOrderID().getListOfItem().get(i).getQuantity();
                                cell2.setCellValue(Double.toString(price));
                                break; 
                            default:
                                break;
                            } //kraj y switch
                    } //kraj for y petlje
                    counter++;
                    itemCounter++;
                } //kraj for bloka za iteme u orderu 
                counter++;
                XSSFRow row3 = sheet.createRow(counter++);
                XSSFCell cell = row3.createCell(header.length-1);
                cell.setCellValue(invoices.get(x-1).getAmount());
            } //kraj else bloka
        } //kraj for ordersa
        
        
        try(OutputStream fileOut = new FileOutputStream(destination)){
            System.out.println("excel created");
            workbook.write(fileOut);
        } 
        
        
    }
    
}
