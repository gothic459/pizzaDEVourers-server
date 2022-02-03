package pizzadevourers.server.pdfGeneration;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pizzadevourers.server.databasePojo.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Creates an .pdf file for each and every order made by the user. Name of the file is order UUID passed in constructor.
 */
public class PdfReceiptGenerator {
    private static final String name_of_restaurant = "pizzaDEVourers";
    private static final String description = "u nas nie uswiadczysz buga";
    private static final String adress_of_resturant = "ul. Mickiewicza 8, 33-100 Tarnów";
    private static final String telephone = "+48 555 213 799";

    private Font bold = FontFactory.getFont(FontFactory.COURIER,24, Font.BOLD, BaseColor.BLACK);
    private Font normal = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);


    /** Total in PLN */
    private int total;
    /** Total tax in PLN */
    private int total_tax;
    /** LinkedList of Product objects - which user has ordered */
    private LinkedList<Product> orderedProducts;
    /** LinkedList of Integers - quantities of products that user has ordered. */
    private LinkedList<Integer> quantities;
    /** Unique ID of receipt (used as filename) */
    private String receiptUUID;


    public PdfReceiptGenerator(String receiptUUID, LinkedList<Product> op, LinkedList<Integer> quantities, int total, int tax){
        this.receiptUUID = receiptUUID;
        this.orderedProducts = op;
        this.quantities = quantities;
        this.total = total;
        this.total_tax = tax;
    }

    /**
     * Generates the receipt from pre-defined template. Saves it as {{receiptUUID}}.pdf
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public void generateReceipt() throws FileNotFoundException, DocumentException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(receiptUUID + ".pdf"));

        doc.open();

        printHeader(doc);

        PdfPTable table = new PdfPTable(2);

        for(int i=0;i<orderedProducts.size();i++)
        {
            addProduct(i,table);
            addPrice(i,table);
        }

        addRow(table,"RAZEM",total,FontFactory.COURIER_BOLD,14);
        doc.add(Chunk.NEWLINE);
        addRow(table,"Suma opodatkowania", total_tax, FontFactory.COURIER,14);

        doc.add(Chunk.NEWLINE);
        doc.add(table);
        doc.add(Chunk.NEWLINE);

        PdfPTable tableTotal = new PdfPTable(2);
        addRow(tableTotal,"SUMA: ",total,FontFactory.COURIER_BOLD,22);

        doc.add(tableTotal);

        printFooter(doc);

        doc.close();

        total_tax = 0;
        total = 0;
    }

    /**
     * Adds price to currently rendered item. Price is multiplied by quantities of product.
     * @param index index of item which is getting printed on the receipt.
     * @param table reference to table. Every cell gets added to table at the end of method.
     */
    private void addPrice(int index, PdfPTable table){
        int price = orderedProducts.get(index).getPrice() * quantities.get(index);
        PdfPCell cell = new PdfPCell(new Phrase((price/100) + "," + (Math.abs(price%100)==0 ? "00" : Math.abs(price%100)) +" PLN", FontFactory.getFont(FontFactory.COURIER, 13)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
    }

    /**
     * Adds product name to currently rendered item.
     * @param index index of item which is getting printed on the receipt.
     * @param table reference to table. Every cell gets added to table at the end of method.
     */
    private void addProduct(int index, PdfPTable table){
        PdfPCell cell = new PdfPCell(new Phrase(orderedProducts.get(index).getName() + " * " +  quantities.get(index) + "szt", FontFactory.getFont(FontFactory.COURIER, 13)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
    }

    /**
     * Adds row to a PdfTable.
     * @param table reference to parent table
     * @param phase phase to be printed into parent table
     * @param amount amount of money to be printed into the parent table
     * @param font_type type of font used in printing
     * @param font_size size of font used in printing
     */
    private void addRow(PdfPTable table, String phase, int amount,String font_type, int font_size){
        PdfPCell cell = new PdfPCell(new Phrase(phase, FontFactory.getFont(font_type, font_size)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        PdfPCell cell2 = new PdfPCell(new Phrase(amount/100 + "," + (Math.abs(amount%100)==0 ? "00" : Math.abs(amount%100)) +" PLN", FontFactory.getFont(font_type, font_size)));
        cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell2.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell2);
    }

    /**
     * Prints header of the document. It's part of the template.
     * @param doc reference to document for header to be printed on.
     * @throws DocumentException
     */
    private void printHeader(Document doc) throws DocumentException {

        Paragraph name = new Paragraph(name_of_restaurant, bold);
        Paragraph address = new Paragraph(adress_of_resturant, normal);
        Paragraph phone = new Paragraph(telephone, normal);
        Paragraph motto = new Paragraph(description, normal);
        Paragraph pf = new Paragraph("PARAGON FISKALNY", bold);

        motto.setAlignment(Element.ALIGN_CENTER);
        address.setAlignment(Element.ALIGN_CENTER);
        phone.setAlignment(Element.ALIGN_CENTER);
        name.setAlignment(Element.ALIGN_CENTER);
        pf.setAlignment(Element.ALIGN_CENTER);
        motto.setAlignment(Element.ALIGN_CENTER);

        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat hr = new SimpleDateFormat("HH:mm");

        doc.add(name);
        doc.add(motto);
        doc.add(Chunk.NEWLINE);
        doc.add(address);
        doc.add(phone);

        PdfPTable dateTable = new PdfPTable(2);

        PdfPCell dateCell = new PdfPCell(new Phrase((date.format(today) + ""), FontFactory.getFont(FontFactory.COURIER, 16)));
        dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateCell.setBorder(Rectangle.NO_BORDER);
        dateTable.addCell(dateCell);

        PdfPCell HourCell = new PdfPCell(new Phrase((hr.format(today) + ""), FontFactory.getFont(FontFactory.COURIER, 16)));
        HourCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        HourCell.setBorder(Rectangle.NO_BORDER);
        dateTable.addCell(HourCell);

        doc.add(Chunk.NEWLINE);
        doc.add(dateTable);
        doc.add(Chunk.NEWLINE);
        doc.add(pf);
        doc.add(Chunk.NEWLINE);
    }

    /**
     * Prints footer of the document. It's part of the template.
     * @param doc reference to document for header to be printed on.
     * @throws DocumentException
     */
    private void printFooter(Document doc) throws DocumentException {
        Paragraph receipt_id = new Paragraph("ID rachunku: " + receiptUUID, normal);
        Paragraph thank_you = new Paragraph("DZIEKUJEMY I ZAPRASZAMY PONOWNIE!", normal);

        receipt_id.setAlignment(Element.ALIGN_CENTER);
        thank_you.setAlignment(Element.ALIGN_CENTER);

        doc.add(Chunk.NEWLINE);
        doc.add(receipt_id);
        doc.add(thank_you);
    }
}