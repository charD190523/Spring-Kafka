package com.example.chatApp.service;

import java.util.Scanner;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class chatService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String username;

    public chatService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;  
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Producer
    public void startSendingMessages() {
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            String mes;
            while (true) {
                mes = sc.nextLine(); 
                kafkaTemplate.send("chat2", username + ": " + mes); 
            }
        }).start(); 
    }

    //Consumer
    @KafkaListener(topics = "chat", groupId = "chatGroup")
    public void receiveMessage(String mes) {
        System.out.println(mes); 
    }
}
