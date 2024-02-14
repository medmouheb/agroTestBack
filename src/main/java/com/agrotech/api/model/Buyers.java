package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Buyers")
public class Buyers extends BaseEntity{

    @NotBlank(message = "BuyerCode is required")
    @Indexed(unique = true)
    @Size(max = 50)
    private String code;
    @Indexed(unique = true)
    @NotBlank(message = "BuyerName is required")

    @Size(max = 50)
    private String name;


    private String email;

    private boolean active =true ;
    private HashSet<String> tags;

    @Size(max = 100)
    private String notes;

    private String address="";

    private Boolean isDeleted=false;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    public void addTags(HashSet<String> tags) {
        for(String t :tags){
            this.tags.add(t);
        }
    }

}
