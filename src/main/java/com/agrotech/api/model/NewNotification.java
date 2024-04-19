package com.agrotech.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Getter
@Setter
public class NewNotification {



    private String content;

    private LocalDateTime createdAt=LocalDateTime.now();
}
