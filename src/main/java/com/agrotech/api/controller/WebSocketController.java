package com.agrotech.api.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {

    private int updatedValue=0;
    @MessageMapping("/increment")
    @SendTo("/topic/value")
    public int incrementValue() {
        updatedValue+=1;
        System.out.println(updatedValue);
        return updatedValue;
    }
}