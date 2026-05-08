package com.example.HotelmanagmentSystem.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EmailService {


    private  final JavaMailSender mailSender;

    public void sendBookingMail(String toEmail, String customerName, String roomNo) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Hotel Booking Confirmed");

        message.setText(
                "Dear " + customerName + ",\n\n" +
                        "Your room booking has been confirmed.\n" +
                        "Room Number: " + roomNo + "\n\n" +
                        "Thank you for choosing our hotel."
        );

        mailSender.send(message);
    }
}