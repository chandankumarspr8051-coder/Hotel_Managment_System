package com.example.HotelmanagmentSystem.Controller;

import com.example.HotelmanagmentSystem.DTO.BillDTO;
import com.example.HotelmanagmentSystem.DTO.PaymentRequestDTO;
import com.example.HotelmanagmentSystem.DTO.PaymentResponseDTO;
import com.example.HotelmanagmentSystem.Service.PaymentService;
import com.example.HotelmanagmentSystem.Service.PdfService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    private final PdfService pdfService;

    @GetMapping("/bill/{bookingId}/download")
    public ResponseEntity<byte[]> downloadBill(
            @PathVariable Integer bookingId) throws Exception {

        BillDTO bill = paymentService.generateBill(bookingId);
        byte[] pdfBytes = pdfService.generateBillPdf(bill);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=bill_" + bookingId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.createPayment(requestDTO));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByBooking(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentByBooking(bookingId));
    }

    // ✅ NEW: Bill generate karo
    @GetMapping("/bill/{bookingId}")
    public ResponseEntity<BillDTO> generateBill(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(paymentService.generateBill(bookingId));
    }
}