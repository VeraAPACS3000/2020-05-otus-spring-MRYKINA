package ru.otus.spring.booksServiceForFeign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookControllerFeign {

    @GetMapping("/api/updateStatus")
    public int getNewStatus() throws InterruptedException {
        //Thread.sleep(100000);
        int newStatus = 107;
        return newStatus;
    }

}
