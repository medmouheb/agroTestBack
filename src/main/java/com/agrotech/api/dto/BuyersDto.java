package com.agrotech.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class BuyersDto extends BaseDto{

    @NotBlank(message = "BuyerCode is required")
    @Indexed(unique = true)
    @Size(max = 50)
    private String code;
    @Indexed(unique = true)
    @NotBlank(message = "BuyerName is required")

    @Size(max = 50)
    private String name;

    private String email;

    private String farmer;


    private List<String> tags;

    private boolean active =true ;

    @Size(max = 100)
    private String notes;

    private Boolean isDeleted=false;

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
