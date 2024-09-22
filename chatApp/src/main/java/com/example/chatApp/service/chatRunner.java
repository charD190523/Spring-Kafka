package com.example.chatApp.service;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class chatRunner implements CommandLineRunner {
    //Sử dụng màn hình console để chạy
    private final chatService chatSv;

    public chatRunner(chatService chatSv) {
        this.chatSv = chatSv;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = sc.nextLine();

        chatSv.setUsername(username);

        chatSv.startSendingMessages();

    }
}
