package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.BillDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateBillPdf(BillDTO bill) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Add title
        document.add(new Paragraph("HOTEL BILL", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph(" "));

        // Add details
        document.add(new Paragraph("Booking ID: " + bill.getBookingId()));
        document.add(new Paragraph("Name: " + bill.getUserName()));
        document.add(new Paragraph("Email: " + bill.getUserEmail()));
        document.add(new Paragraph("Room: " + bill.getRoomNumber()));
        document.add(new Paragraph("Check-in: " + bill.getCheckIn()));
        document.add(new Paragraph("Check-out: " + bill.getCheckOut()));
        document.add(new Paragraph(" "));

        // Add amounts
        document.add(new Paragraph("Subtotal: ₹" + bill.getSubtotal()));
        document.add(new Paragraph("Tax (10%): ₹" + bill.getTax()));
        document.add(new Paragraph("Total: ₹" + bill.getTotalAmount(),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

        document.close();
        return baos.toByteArray();
    }
}
