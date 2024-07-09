package com.agrotech.api.dto;

import com.agrotech.api.model.BaseEntity;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryNoteDto extends BaseEntity {
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;

    private String farmer;

    private String buyer;

    private LocalDateTime deliveryDate;

    private String deliveryInstruction;

    private String goodsDescription;

    private String product;

    private int quantity;

    private String additionalNotes;


    private Boolean isDeleted=false;


}
